package com.univcert.backend.cert;

import com.univcert.backend.PropertyUtil;
import com.univcert.backend.cert.dto.*;
import com.univcert.backend.error.*;
import com.univcert.backend.user.User;
import com.univcert.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CertService {

    private final JavaMailSender emailSender;
    private final UserRepository userRepository;
    private final CertRepository certRepository;

    public String getCode(String email) {
        Cert cert = certRepository.findCertByEmail(email).orElseThrow(CertNotFoundException::new);
        return cert.getCode();
    }
    public Cert getCert(String email) {
        Cert cert = certRepository.findCertByEmail(email).orElseThrow(CertNotFoundException::new);
        return cert;
    }

    public JSONObject tryOut(UnivAndEmailDto univDto) {
        if (validateUnivDomain(univDto.getEmail(), univDto.getUnivName()))
            return PropertyUtil.response(true);

        throw new DomainMisMatchException("대학과 일치하지 않는 메일 도메인입니다.");
    }

    private boolean validateUnivDomain(String email, String univName) {
        String domain = UnivMail.getDomain(univName);
        if(email.contains(domain))
            return true;
        return false;
    }

    @Transactional
    public JSONObject requestCertify(CertifyDto dto) {
        User user = userRepository.findByAPI_KEYFetchCertList(dto.getAPI_KEY()).orElseThrow(UserNotFoundException::new);
        if(dto.isUniv_check()){
            if(!validateUnivDomain(dto.getEmail(), dto.getUniv()))
                return PropertyUtil.responseMessage(dto.getUniv()+" 메일이 아닙니다.");
        }
        Optional<Cert> existCert = certRepository.findCertByEmail(dto.getEmail());
        if(existCert.isPresent()){
            if(existCert.get().getCount()>3)
                throw new CountOverException("일일 시도 가능 횟수 초과입니다.");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("univcertofficial@gmail.com");
        message.setTo(dto.getEmail());
        message.setSubject(user.getTeamName()+" : 대학인증 메일 코드를 확인해주세요");
        String code = String.valueOf((int)((Math.random()*10 +1) * 1000));
        message.setText(user.getTeamName()+"에서 요청한 인증번호 : "+ code); //1000~9999
        emailSender.send(message);

        if(existCert.isPresent()){
            Cert cert = existCert.get();
            cert.updateCodeAndPlusCount(code);
        }
        else{
            Cert cert = Cert.builder()
                    .email(dto.getEmail())
                    .univName(dto.getUniv())
                    .code(code)
                    .certified(false).build();
            cert.setUser(user);
            certRepository.save(cert);
            user.getCertList().add(cert);
        }
        return PropertyUtil.response(true);
    }

    @Transactional
    public JSONObject receiveMail(CodeResponseDto codeDto) {
        Cert cert = certRepository.findCertByEmail(codeDto.getEmail()).orElseThrow(InstanceNotFoundException::new);
        if(cert.getCode().equals(codeDto.getCode())){
            cert.setCertified();
            return PropertyUtil.response(true);
        }
        return PropertyUtil.responseMessage("일치하지 않는 인증코드입니다.");
    }

    @Transactional(readOnly = true)
    public JSONObject getStatus(StatusDto dto) {
        JSONObject obj = new JSONObject();
        Cert cert = certRepository.findCertByEmail(dto.getEmail()).orElseThrow(CertNotFoundException::new);
        if(cert.isCertified()){
            obj.put("success", true);
            obj.put("certified_date", cert.getCreatedDate());
            return obj;
        }
        return PropertyUtil.responseMessage("인증되지 않은 메일입니다.");
    }

    @Transactional(readOnly = true)
    public JSONObject getCertifiedList(String API_KEY) {
        User user = userRepository.findByAPI_KEYFetchCertList(API_KEY).orElseThrow(ApiNotFoundException::new);
        List<ResponseListForm> list = new ArrayList<>();
        for (Cert cert : user.getCertList()) {
            ResponseListForm responseListForm = ResponseListForm.builder()
                    .email(cert.getEmail())
                    .univName(cert.getUnivName())
                    .count(cert.getCount())
                    .certified(cert.isCertified()).build();
            list.add(responseListForm);
        }
        return PropertyUtil.response(list);
    }
}
