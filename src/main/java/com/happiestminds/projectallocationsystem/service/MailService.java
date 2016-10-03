package com.happiestminds.projectallocationsystem.service;

import javax.mail.MessagingException;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.EmailDto;

public interface MailService {

	public void sendInviteEmails(EmailDto inputEailDto) throws MessagingException;

	public void sendMail(String sendToEmail, String mailText, String subject) throws MessagingException;

	@Transactional(readOnly = false)
	boolean forgotPassword(String email) throws MessagingException, HibernateException;

	@Transactional(readOnly = false)
	boolean changePassword(String userName, String password, String newPassword) throws HibernateException;
}
