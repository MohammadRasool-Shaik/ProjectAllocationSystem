package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.Dto.UserDto;
import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;
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
	public void raiseAllocationRequestEmail(EmailDto emailData) throws MessagingException {
		List<String> emailIds = emailData.getEmailIds();
		String [] toMailIds = emailIds.toArray(new String[emailIds.size()]);
		MimeMessage generateRequest = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(generateRequest, true);
		mimeMessageHelper.setSubject(emailData.getSubject());
		mimeMessageHelper.setTo(toMailIds[0]);
		mimeMessageHelper.setBcc(toMailIds);
		mimeMessageHelper.setFrom(emailData.getFromMailId());
		mimeMessageHelper.setText(emailData.getMessage(), true);
		javaMailSender.send(generateRequest);
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

	@Override
	public UserDto forgotPassword(String email) {
		UserDto userResponse = new UserDto();
		StatusDto status = new StatusDto(StatusCodeEnum.FAILURE);
		UserEntity user = userDAO.findByUserByEmail(email);
		if (user != null) {
			String password = createPassword();
			userDAO.updateUserPWD(user.getUserName(), createHash(password));
			sendForgotPasswordMail(user.getUserName(), user.getEmailId(), password);
			status.setStatusCode(StatusCodeEnum.SUCCESS);
			status.setStatusMessage("Your password successfully changed, you will get temporary password to " + email + " mailId");
		} else {
			status.setStatusMessage(email + " with user not exist, Try emailId with you registered");
		}
		userResponse.setStatusDto(status);
		return userResponse;
	}

	@Async
	public void sendForgotPasswordMail(String userName, String sendToEmail, String password) {
		try {
			String mailText = "Hi " + userName + ",\n\n" + "You recently asked to reset your password:\t" + password;
			sendMail(sendToEmail, mailText, "Login Details");
		} catch (Exception ex) {
			logger.error("Error sending email for the  user: {}", ex);
			logger.error("Error sending email for the  user: {} userName: " + userName + " sendToEmail:" + sendToEmail);
			throw ex;
		}
	}

	public String createHash(String password) {
		return DigestUtils.md5DigestAsHex(password.getBytes());
	}

	public String createPassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	@Override
	public UserDto changePassword(String userName, String password, String newPassword) {
		UserDto userResponse = new UserDto();
		userResponse.setUserName(userName);
		StatusDto statusDto = new StatusDto(StatusCodeEnum.FAILURE);
		UserEntity user = userDAO.findByUserName(userName);

		String userPassword = user.getPassword();
		if (password != null && userPassword.equals(createHash(password))) {
			userDAO.updateUserPWD(user.getUserName(), createHash(newPassword));
			statusDto.setStatusCode(StatusCodeEnum.SUCCESS);
			statusDto.setStatusMessage("Password Successfully Changed");
		} else {
			statusDto.setStatusCode(StatusCodeEnum.USERCHANGEPASSWORDWRONGMESSAGE);
		}
		userResponse.setStatusDto(statusDto);
		return userResponse;
	}
}
