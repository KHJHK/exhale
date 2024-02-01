package com.ssafy.exhale.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtil {
    private final JavaMailSender javaMailSender;
    public void sendCertificationMail(String email, String certificationNumber) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        String subject = "[날: 숨] 인증 메일 입니다";
        String htmlContent = getCertificationMessage(certificationNumber);
        messageHelper.setTo(email);
        messageHelper.setSubject(subject);
        messageHelper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    private  String getCertificationMessage(String certificationNumber){
        String content = "";
        content += "<h2 style='text-align:center;'>비밀번호 찾기를 위한 인증코드입니다.<br/>아래 코드를 [날: 숨] 웹 페이지에 입력해주세요.</h2>";
        content += "<h3 style='text-align:center;'>인증코드는 <strong style='font-size:32px;letter-spacing: 8px'>" + certificationNumber + "</strong>입니다.</h3>";
        return content;
    }
}
