package com.controller.member.mail;

import javax.mail.PasswordAuthentication;

public class SendEmail extends javax.mail.Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		
		System.out.println("PasswordAuthentication");
	
		return new PasswordAuthentication("아이디", "이차인증패스워드");
	
	}
}