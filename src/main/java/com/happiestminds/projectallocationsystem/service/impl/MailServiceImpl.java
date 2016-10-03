package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.happiestminds.projectallocationsystem.Dto.EmailDto;
import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService {

	private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class.getSimpleName());

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Autowired
	private UserDAO userDAO;

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendInviteEmails(EmailDto requestData) throws MessagingException{
		List<String> emailIds = requestData.getEmailIds();
		for (String email : emailIds) {
			MimeMessage generateRequest = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(generateRequest, true);
			mimeMessageHelper.setSubject(requestData.getSubject());
			mimeMessageHelper.setTo(email);
			mimeMessageHelper.setText(requestData.getMessage(), true);
			javaMailSender.send(generateRequest);
		}

	}

	@Override
	public void sendMail(String sendToEmail, String mailText, String subject) throws MessagingException {
		SimpleMailMessage mailMsg = new SimpleMailMessage();
		mailMsg.setTo(sendToEmail);
		mailMsg.setText(mailText);
		mailMsg.setSubject(subject);
		mailMsg.setFrom("Hapiestminds  <noreply@happiestminds.com>");
		javaMailSender.send(mailMsg);
	}

	@Override
	public boolean forgotPassword(String email) throws MessagingException, HibernateException {
		boolean isMailSend = false;
		UserEntity user = userDAO.findByUserByEmail(email);
		if (user != null) {
			String password = createPassword();
			userDAO.updateUserPWD(user.getUserName(), createHash(password));
			sendForgotPasswordMail(user.getUserName(), user.getEmailId(), password);
			isMailSend = true;
		}
		return isMailSend;
	}

	public String createHash(String password) {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

	public String createPassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	@Async
	public void sendForgotPasswordMail(String userName, String sendToEmail, String password) throws MessagingException {
		try {
			String mailText = "Hi " + userName + ",\n\n" + "You recently asked to reset your password:\t" + password;
			sendMail(sendToEmail, mailText, "Login Details");
		} catch (Exception ex) {
			logger.error("Error sending email for the  user: {}", ex);
			logger.error("Error sending email for the  user: {} userName: " + userName + " sendToEmail:" + sendToEmail);
		}
	}

	@Override
	public boolean changePassword(String userName, String password, String newPassword) throws HibernateException {
		boolean isPasswordChanged = false;
		UserEntity user = userDAO.findByUserName(userName);
		if (user != null) {
			String userPassword = user.getPassword();
			if (password != null && userPassword.equals(createHash(password))) {
				userDAO.updateUserPWD(user.getUserName(), createHash(newPassword));
				isPasswordChanged = true;
			}
		}
		return isPasswordChanged;
	}
}
