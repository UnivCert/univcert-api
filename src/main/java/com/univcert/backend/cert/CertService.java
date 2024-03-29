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
import org.springframework.scheduling.annotation.Async;
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
            return makeSuccessAPI(univDto);

        return makeFailAPI(univDto);
    }

    private JSONObject makeSuccessAPI(UnivAndEmailDto univDto) {
        JSONObject obj = new JSONObject();
        obj.put("univ_email", univDto.getEmail());
        obj.put("domain", univDto.getEmail().split("@")[1]);
        obj.put("univ_name", univDto.getUnivName());
        obj.put("status",200);
        obj.put("success",true);
        return obj;
    }

    private JSONObject makeFailAPI(UnivAndEmailDto univDto) {
        JSONObject obj = new JSONObject();
        obj.put("expected", UnivMail.getDomain(univDto.getUnivName()));
        obj.put("domain", univDto.getEmail().split("@")[1]);
        obj.put("status",400);
        obj.put("success",false);
        return obj;
    }

    private boolean validateUnivDomain(String email, String univName) {
        String domain = UnivMail.getDomain(univName);
        return email.contains(domain);
    }

    public JSONObject validateUnivName(String univName) {
        String domain = UnivMail.getDomain(univName);
        if(domain.isBlank())
            return PropertyUtil.response(false);
        return PropertyUtil.response(true);
    }

    @Transactional
    public MailForm checkErrorAndMakeForm(CertifyDto dto) {
        User user = userRepository.findByAPI_KEYFetchCertList(dto.getKey()).orElseThrow(ApiNotFoundException::new);
        if(dto.isUniv_check()){
            if(!validateUnivDomain(dto.getEmail(), dto.getUnivName()))
                throw new DomainMisMatchException();
        }
        Optional<Cert> existCert = checkCountAndCertified(dto);
        String code = String.valueOf((int)((Math.random()*10 +1) * 1000));

        if(existCert.isPresent()){
            Cert cert = existCert.get();
            cert.updateCodeAndPlusCount(code);
        }
        else{
            Cert cert = Cert.builder()
                    .email(dto.getEmail())
                    .univName(dto.getUnivName())
                    .code(code)
                    .certified(false).build();
            cert.setUser(user);
            certRepository.save(cert);
            user.getCertList().add(cert);
        }
        return new MailForm(dto.getEmail(), user.getTeamName(), code);
    }

    private Optional<Cert> checkCountAndCertified(CertifyDto dto) {
        Optional<Cert> existCert = certRepository.findCertByEmail(dto.getEmail());
        if(existCert.isPresent()){
            Cert cert = existCert.get();
            if(cert.getCount()>20)
                throw new CountOverException("일일 시도 가능 횟수(20회) 초과입니다.");
            if(cert.isCertified())
                throw new AlreadyCertifiedException();
        }
        return existCert;
    }

    @Async
    public void sendMail(MailForm form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("univcertofficial@gmail.com");
        message.setTo(form.getEmail());
        message.setSubject(form.getTeamName()+" : 인증 메일 코드를 확인해주세요");
        message.setText(form.getTeamName()+"에서 요청한 인증번호 : "+ form.getCode()); //1000~9999
        emailSender.send(message);
    }



    @Transactional
    public JSONObject receiveMail(CodeResponseDto codeDto) {
        JSONObject obj = new JSONObject();
        Cert cert = certRepository.findCertByEmail(codeDto.getEmail()).orElseThrow(CertNotFoundException::new);
        if(cert.getCode().equals(codeDto.getCode()) && !cert.isCertified()){
            cert.setCertified();
            obj.put("success", true);
            obj.put("univName", cert.getUnivName());
            obj.put("certified_email",cert.getEmail());
            obj.put("certified_date", cert.getCreatedDate());
            return obj;
        }
        return PropertyUtil.responseMessage("일치하지 않는 인증코드입니다.");
    }

    @Transactional(readOnly = true)
    public JSONObject getStatus(StatusDto dto) {
        JSONObject obj = new JSONObject();
        Cert cert = certRepository.findCertByEmail(dto.getEmail()).orElseThrow(CertNotFoundException::new);
        if(cert.isCertified()){
            obj.put("success", true);
            obj.put("certified_date", cert.getCreatedDate().toString());
            return obj;
        }
        return PropertyUtil.responseMessage("인증되지 않은 메일입니다.");
    }

    @Transactional(readOnly = true)
    public JSONObject getCertifiedList(String API_KEY) {
        User user = userRepository.findByAPI_KEYFetchCertList(API_KEY).orElseThrow(ApiNotFoundException::new);
        List<ResponseListForm> list = new ArrayList<>();
        user.getCertList().forEach(cert -> list.add(new ResponseListForm(cert)));
        return PropertyUtil.response(list);
    }

    @Transactional
    public JSONObject clearList(String API_KEY) {
        User user = userRepository.findByAPI_KEYFetchCertList(API_KEY).orElseThrow(ApiNotFoundException::new);
        certRepository.deleteAll(user.getCertList());
        return PropertyUtil.response(true);
    }

    @Transactional
    public JSONObject clear(String API_KEY, String email) {
        User user = userRepository.findByAPI_KEYFetchCertList(API_KEY).orElseThrow(ApiNotFoundException::new);
        Cert cert = certRepository.findCertByEmail(email)
                .orElseThrow(CertNotFoundException::new);
        if(cert.getUser().getId().equals(user.getId()))
            certRepository.deleteById(cert.getId());
        else
            PropertyUtil.responseMessage("해당 API_KEY 보유자가 인증한 이메일이 아닙니다.");
        return PropertyUtil.response(true);
    }
}
