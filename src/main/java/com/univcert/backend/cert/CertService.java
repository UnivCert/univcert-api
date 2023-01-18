package com.univcert.backend.cert;

import com.univcert.backend.PropertyUtil;
import com.univcert.backend.cert.dto.CertifyDto;
import com.univcert.backend.cert.dto.UnivAndEmailDto;
import com.univcert.backend.error.UserNotFoundException;
import com.univcert.backend.user.User;
import com.univcert.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CertService {

    private final JavaMailSender emailSender;
    private final UserRepository userRepository;
    private final CertRepository certRepository;


    public JSONObject tryOut(UnivAndEmailDto univDto) {
        String domain = UnivMail.getDomain(univDto.getName());
        if(univDto.getEmail().contains(domain))
            return PropertyUtil.response(true);
        return PropertyUtil.responseMessage(univDto.getEmail()+" 메일이 아닙니다.");
    }

    @Transactional
    public JSONObject requestCertify(CertifyDto dto) {
        User user = userRepository.findByAPI_KEYFetchCertList(dto.getAPI_KEY()).orElseThrow(UserNotFoundException::new);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sinzakofficial@gmail.com");
        message.setTo(dto.getEmail());
        message.setSubject(user.getTeamName()+" : 대학인증 메일 코드를 확인해주세요");
        String code = String.valueOf((int)((Math.random()*10 +1) * 1000));
        message.setText(user.getTeamName()+"에서 요청한 인증번호 : "+ code); //1000~9999
        emailSender.send(message);
        Optional<Cert> existCert = certRepository.findCertByEmail(dto.getEmail());
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
//
//    @Transactional
//    public JSONObject receiveMail(User User, MailDto mailDto) {
//        Cert savedCert = certRepository.findCertByUnivEmail(mailDto.getAddress()).orElseThrow();
//
//        if(savedCert.getCode().equals(mailDto.getCode())){
//            User user = userRepository.findByEmail(User.getEmail()).orElseThrow(UserNotFoundException::new);
//            savedCert.setVerified();
//
//            if(UnivMail.needCheck(mailDto.getUniv())){   /** 인증이 필요한 대학만 진행. **/
//                boolean result = UnivMail.certUniv(mailDto.getUniv(),mailDto.getAddress());
//                System.out.println("result = "+ result);
//                if(!result)
//                    return PropertyUtil.responseMessage("일치하지 않는 인증코드입니다.");
//            }
//            user.updateCertifiedUniv(mailDto.getUniv(), mailDto.getAddress());
//
//            return PropertyUtil.response(true);
//        }
//        return PropertyUtil.responseMessage("일치하지 않는 인증코드입니다.");
//
//    }
//
//    @Transactional
//    public JSONObject certifyUniv(User User, UnivDto dto){
//        User user = userRepository.findByEmail(User.getEmail()).orElseThrow(UserNotFoundException::new);
//        Optional<Cert> savedCert = certRepository.findCertByUnivEmail(dto.getUniv_email());
//        Long certId;
//        if(savedCert.isEmpty())
//            certId = certRepository.save(new Cert(dto.getUniv_email(), "", "temp", true)).getId();
//        else
//        { /** 대학 이메일 인증 실패했을 때는 이메일이 남아있으니까 **/
//            Cert cert = savedCert.get();
//            if(!cert.isVerified())
//                certId = cert.getId();
//            else
//                return PropertyUtil.responseMessage("이미 인증 처리된 이메일입니다.");
//        }
//        user.updateCertifiedUniv(dto.getUniv(),dto.getUniv_email()); /** 일단은 허용해주고, 나중에 거짓말이면 대학 미인증으로 바꾸면 됨.**/
//        return PropertyUtil.response(certId);
//    }
//
//    @Transactional
//    public JSONObject uploadUnivCard(Long id, MultipartFile file){
//        Cert cert = certRepository.findById(id).orElseThrow(InstanceNotFoundException::new);
//        String url = s3Service.uploadImage(file);
//        cert.updateImageUrl(url);
//        return PropertyUtil.response(true);
//    }


}
