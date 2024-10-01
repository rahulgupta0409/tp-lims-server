package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.config.mailservice.model.Mail;
import com.tilakpathology.application.Tilak.Pathology.App.config.mailservice.service.MailService;
import com.tilakpathology.application.Tilak.Pathology.App.dao.OtpRepository;
import com.tilakpathology.application.Tilak.Pathology.App.model.Otp;
import com.tilakpathology.application.Tilak.Pathology.App.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;
import static com.tilakpathology.application.Tilak.Pathology.App.constants.RegexpConstants.OTP_MAX_VALUE;
import static com.tilakpathology.application.Tilak.Pathology.App.constants.RegexpConstants.OTP_MIN_VALUE;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private MailService mailService;

    @Override
    public Otp generateOtp(String email) {
        Random rand = new Random();
        Integer randomOTP = rand.nextInt(OTP_MAX_VALUE - OTP_MIN_VALUE + 1) + OTP_MIN_VALUE;
        Otp otp = Otp.builder()
                .otpId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .OTP(randomOTP)
                .createDate(LocalDateTime.now().toString())
                .email(email)
                .build();
        otpRepository.save(otp);
        sendMailToUser(email, randomOTP);
        return otp;
    }

    @Override
    public String validateOtp(String email, Integer otp) {
        List<Otp> dbOtp = otpRepository.findOtpByEmail(email);
        Optional<Otp> otpRes = dbOtp.stream().filter(otp1 -> otp1.getEmail().equalsIgnoreCase(email))
                .sorted(Comparator.comparing(Otp::getCreateDate).reversed())
                .findFirst();
        if(otpRes.isPresent()){
            if(Objects.equals(otp, otpRes.get().getOTP())){
                return "otp";
            }else{
                return "fail";
            }
        }
        return  null;
    }

    private void sendMailToUser(String userEmail, Integer otp){
        Mail mail = new Mail();
        mail.setMailFrom("rahulguptaharsh081218@gmail.com");
        mail.setMailTo(userEmail);
        mail.setMailContent(otp.toString());
        mail.setMailSubject("Your OTP for Account Creation");
        mailService.sendEmail(mail);
    }


}
