package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.entity.EmailEntity;
import com.happiestminds.projectallocationsystem.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSenderImpl javaMailSender;
	
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendInviteEmails(EmailEntity emailDto) throws MessagingException, Exception {
		List<String> emailIds = emailDto.getEmailIds();
		for (String email : emailIds) {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage, true);
			mimeMessageHelper.setSubject(emailDto.getSubject());
			mimeMessageHelper.setTo(email);
			mimeMessageHelper.setText(emailDto.getMessage(), true);
			javaMailSender.send(mimeMessage);
		}
		
	}
	@Override
	public void sendMail(String sendToEmail, String mailText, String subject) {
		SimpleMailMessage mailMsg = new SimpleMailMessage();
		mailMsg.setTo(sendToEmail);
		mailMsg.setText(mailText);
		mailMsg.setSubject(subject);
		mailMsg.setFrom("Hapiestminds  <noreply@happiestminds.com>");
		javaMailSender.send(mailMsg);
	}

}
