package com.happiestminds.projectallocationsystem.service;

import javax.mail.MessagingException;

import com.happiestminds.projectallocationsystem.entity.EmailEntity;

public interface MailService {

	public void sendInviteEmails(EmailEntity inputEailDto) throws MessagingException, Exception;
	public void sendMail(String sendToEmail, String mailText, String subject); 
}
