package com.direction.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class MailHelper {

	private static final Logger log = Logger.getLogger(MailHelper.class);

	private static Properties props = new Properties();
	private static Session session;
	private static String fromName;
	private static String username;
	private static String password;
	private static String subject;
	private static String body;

	static {
		createSessionAndInitParameter();
	}

	private static void createSessionAndInitParameter() {
		try {
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.ssl.trust","smtp.gmail.com");
				//log.debug("mail.smtp.ssl.trust :" + SystemConfig.MAIL_HOST);
			
				props.put("mail.smtp.port", 587);
				
			
				props.put("mail.smtp.auth", "true");
				//log.debug("mail.smtp.auth :" + SystemConfig.MAIL_SMTP_AUTH);
			
				props.put("mail.smtp.starttls.enable", "true");
				//log.debug("mail.smtp.starttls.enable :" + SystemConfig.MAIL_SMTP_STARTTLS_ENABLE);
			
				username = "crousbackend@gmail.com";
				log.debug("MAIL_USERNAME : crousbackend@gmail.com" );
			
				password = "crousbackend@123";
				log.debug("MAIL_PASSWORD : " );
			
				session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		}catch(Exception e)
		{
			log.error(Util.convertExceptionToString(e));
		}

	}

	/**
	 * Get Template Email
	 * 
	 * @param servletContext
	 * @param templateName
	 * @param countryCode
	 * @return
	 * @throws IOException
	 */
//	public static String getTemplates(String templateName) throws IOException {
//		ClassLoader classLoader = new MailHelper().getClass().getClassLoader();
//		File file = new File(classLoader.getResource("email_template/" + templateName).getFile());
//		return FileUtils.readFileToString(file, "UTF-8");
//	}

	public static void sendMail(String from, String fromName, List<String> tos, List<String> ccs, String subject,
			String body, List<String> attachmentFiles, List<String> attachmentNames)
			throws UnsupportedEncodingException {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(fromName != null ? new InternetAddress(from, fromName) : new InternetAddress(from));
			for (String to : tos) {
				InternetAddress add = new InternetAddress(to);
				message.addRecipient(RecipientType.TO, add);
			}

			if (ccs != null) {
				for (String cc : ccs) {
					InternetAddress add = new InternetAddress(cc);
					message.addRecipient(RecipientType.CC, add);
				}
			}

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			MimeBodyPart bodyPart = new MimeBodyPart(headers, body.getBytes("UTF-8"));
			message.setSubject(subject, "UTF-8");
			bodyPart.setText(body, "UTF-8", "html");
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bodyPart);

			// Attachment file
			if (attachmentFiles != null && attachmentFiles.size() > 0) {
				for (String file : attachmentFiles) {
					File f = new File(file);
					if (f.exists()) {
						BodyPart messageBodyPart = new MimeBodyPart();
						DataSource source = new FileDataSource(file);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(attachmentNames.get(attachmentFiles.indexOf(file)));
						mp.addBodyPart(messageBodyPart);
					}
				}
			}
			// End

			message.setContent(mp, "UTF-8");
			Transport.send(message);

			Gson gson = new Gson();
			log.info("Just has been sent a mail. About email sent -> subject: " + subject + ", to: " + gson.toJson(tos)
					+ ", cc: " + gson.toJson(ccs));

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public static void sendMailWithAttachment(String to, String cc, String subject, String body, DataSource source,
			String filename) throws UnsupportedEncodingException {

		if (to == null || to.isEmpty()) {
			log.error("Cannot sent email because from/to is null or empty");
			return;
		}

		List<String> tos = new ArrayList<>();
		tos.add(to);

		List<String> ccs = null;
		if (cc != null && !cc.isEmpty()) {
			ccs = new ArrayList<>();
			ccs.add(cc);
		}

		sendMailWithAttachment(username, fromName, tos, ccs, subject, body, source, filename);
	}

	public static void sendMailWithAttachment(List<String> tos, List<String> ccs, String subject, String body,
			DataSource source, String filename) throws UnsupportedEncodingException {

		if (tos == null || tos.isEmpty()) {
			log.error("Cannot sent email because from/to is null or empty");
			return;
		}

		// DEBUG CODE
		tos.add("thitranthanh@gmail.com");

		sendMailWithAttachment(username, fromName, tos, ccs, subject, body, source, filename);
	}

	public static void sendMail(String to, String cc, String subject, String body) throws UnsupportedEncodingException {

		if (to == null || to.isEmpty()) {
			log.error("Cannot sent email because from/to is null or empty");
			return;
		}

		List<String> tos = new ArrayList<>();
		tos.add(to);

		List<String> ccs = null;
		if (cc != null && !cc.isEmpty()) {
			ccs = new ArrayList<>();
			ccs.add(cc);
		}

		sendMail(username, fromName, tos, ccs, subject, body);
	}

	public static void sendMail(String from, String fromName, List<String> tos, List<String> ccs, String subject,
			String body) throws UnsupportedEncodingException {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(fromName != null ? new InternetAddress(from, fromName) : new InternetAddress(from));
			for (String to : tos) {
				InternetAddress add = new InternetAddress(to);
				message.addRecipient(RecipientType.TO, add);
			}

			if (ccs != null) {
				for (String cc : ccs) {
					InternetAddress add = new InternetAddress(cc);
					message.addRecipient(RecipientType.CC, add);
				}
			}

			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			MimeBodyPart bodyPart = new MimeBodyPart(headers, body.getBytes("UTF-8"));
			message.setSubject(subject, "UTF-8");
			bodyPart.setText(body, "UTF-8", "html");
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bodyPart);
			message.setContent(mp, "UTF-8");
			Transport.send(message);

			Gson gson = new Gson();
			log.info("Just has been sent a mail. About email sent -> subject: " + subject + ", to: " + gson.toJson(tos)
					+ ", cc: " + gson.toJson(ccs));

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void sendMailWithAttachment(String from, String fromName, List<String> tos, List<String> ccs,
			String subject, String body, DataSource source, String filename) throws UnsupportedEncodingException {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(fromName != null ? new InternetAddress(from, fromName) : new InternetAddress(from));
			for (String to : tos) {
				InternetAddress add = new InternetAddress(to);
				message.addRecipient(RecipientType.TO, add);
			}

			if (ccs != null) {
				for (String cc : ccs) {
					InternetAddress add = new InternetAddress(cc);
					message.addRecipient(RecipientType.CC, add);
				}
			}

			// Put subject in message
			message.setSubject(subject, "UTF-8");

			// Create the message part
			InternetHeaders headers = new InternetHeaders();
			headers.addHeader("Content-type", "text/html; charset=UTF-8");
			BodyPart content = new MimeBodyPart(headers, body.getBytes("UTF-8"));

			Multipart multipart = new MimeMultipart();

			// add content
			multipart.addBodyPart(content);

			// add attachment
			BodyPart attachment = new MimeBodyPart();
			attachment.setDataHandler(new DataHandler(source));
			attachment.setFileName(filename);
			multipart.addBodyPart(attachment);

			// Put parts in message
			message.setContent(multipart);

			Transport.send(message);

			Gson gson = new Gson();
			log.info("Just has been sent a mail. About email sent -> subject: " + subject + ", to: " + gson.toJson(tos)
					+ ", cc: " + gson.toJson(ccs));

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
