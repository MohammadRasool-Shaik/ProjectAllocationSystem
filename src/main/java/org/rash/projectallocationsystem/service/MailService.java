package org.rash.projectallocationsystem.service;

import javax.mail.MessagingException;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.Dto.EmailDto;
import org.rash.projectallocationsystem.Dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface MailService {

	public void sendInviteEmails(EmailDto inputEailDto) throws MessagingException;

	public void sendMail(String sendToEmail, String mailText, String subject) throws MessagingException ;

	@Transactional(readOnly = false)
	UserDto forgotPassword(String email) throws MessagingException, HibernateException;

	@Transactional(readOnly = false)
	UserDto changePassword(String userName, String password, String newPassword);
}
