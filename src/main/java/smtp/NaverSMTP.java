package smtp;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NaverSMTP {
	// 서버정보와 인증정보
	private final Properties serverInfo;
	private final Authenticator auth;

	public NaverSMTP() {
		// 네이버 SMTP 서버 접속 정보
		serverInfo = new Properties();
		serverInfo.put("mail.smtp.host", "smtp.naver.com");
		serverInfo.put("mail.smtp.port", "465");
		serverInfo.put("mail.smtp.ssl.enable", true);
		serverInfo.put("mail.smtp.ssl.trust", "smtp.naver.com");
		serverInfo.put("mail.smtp.starttls.enable", "true");
		serverInfo.put("mail.smtp.auth", "true");
		serverInfo.put("mail.smtp.debug", "true");
		serverInfo.put("mail.smtp.socketFactory.port", "465");
		serverInfo.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		serverInfo.put("mail.smtp.socketFactory.fallback", "false");

		// 사용자 인증 정보
		auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("qazwsx9445@naver.com", "harim9445");
			}
		};
	}
	
	public void emailSending(Map<String, String> mailInfo) throws MessagingException {
		// 1. 세션 생성
		Session session = Session.getInstance(serverInfo, auth);
		session.setDebug(true);
		
		// 2. 메세지 작성
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mailInfo.get("from"))); // 보내는 사람
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailInfo.get("to"))); // 받는 사람
		msg.setSubject(mailInfo.get("subject")); // 제목
		msg.setContent(mailInfo.get("content"), mailInfo.get("format")); // 내용
		
		System.out.println("메서드쪽 보내는사람 = " + mailInfo.get("from"));
		System.out.println("메서드쪽 받는사람 = " + mailInfo.get("to"));
		System.out.println("메서드쪽 제목 = " + mailInfo.get("subject"));
		System.out.println("메서드쪽 내용 = " + mailInfo.get("content"));
		System.out.println("메서드쪽 포맷 = " + mailInfo.get("format"));

		// 3. 전송
		Transport.send(msg);
	}

}
