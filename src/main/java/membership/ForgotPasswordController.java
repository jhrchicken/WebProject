package membership;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import smtp.NaverSMTP;
import utils.JSFunction;

@WebServlet("/membership/forgotpassword.do")
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("../Membership/forgot-password.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디와 이메일이 일치하는지 확인
		String id = request.getParameter("id");
		String to = request.getParameter("to");
		MemberDAO memberdao = new MemberDAO();
		MemberDTO memberdto = memberdao.getMemberDTO(id);
		if (!(memberdto.getEmail()).equals(to)) {
			JSFunction.alertLocation(response, "아이디와 이메일이 일치하지 않습니다.", "../membership/forgotpassword.do");
			return;
		}
		
		// 폼값(이메일 내용) 저장
		Map<String, String> emailInfo = new HashMap<String, String>();
		emailInfo.put("from", "qazwsx9445@naver.com"); // 보내는 사람
		emailInfo.put("to", request.getParameter("to")); // 받는 사람
		String content = "비밀번호는 " + memberdto.getPassword() + " 입니다.";
		
		// 텍스트 포맷
		emailInfo.put("content", content);
		emailInfo.put("format", "text/plain;charset=UTF-8"); // 포맷
		
		emailInfo.put("subject", "비밀번호를 확인하세요."); // 제목
		
		try {
			// 메일전송 클래스 생성
			NaverSMTP smtpServer = new NaverSMTP();
			// 전송
			smtpServer.emailSending(emailInfo);
			JSFunction.alertLocation(response, "이메일 전송에 성공했습니다. 이메일을 확인해주세요.", "../membership/login.do");
		}
		catch (Exception e) {
			JSFunction.alertLocation(response, "이메일 전송에 실패했습니다. 다시 시도해주세요.", "../membership/forgotpassword.do");
		}
		
	}
}
