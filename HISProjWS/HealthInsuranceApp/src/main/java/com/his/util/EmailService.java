package com.his.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String to,String from,String subject,String body) {
		try {
			MimeMessage mimeMsg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMsg, true);
			
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(body);
			
			mailSender.send(mimeMsg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
