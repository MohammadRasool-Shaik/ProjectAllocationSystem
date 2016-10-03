package com.happiestminds.projectallocationsystem.service;

import javax.mail.MessagingException;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.EmailDto;
import com.happiestminds.projectallocationsystem.Dto.UserDto;

public interface MailService {

	public void raiseAllocationRequestEmail(EmailDto inputEailDto) throws MessagingException;

	public void sendMail(String sendToEmail, String mailText, String subject) throws MessagingException ;

	@Transactional(readOnly = false)
	UserDto forgotPassword(String email) throws MessagingException, HibernateException;

	@Transactional(readOnly = false)
	UserDto changePassword(String userName, String password, String newPassword);
}
