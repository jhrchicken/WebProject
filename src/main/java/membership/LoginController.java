package membership;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CookieManager;
import utils.JSFunction;

@WebServlet("/membership/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 아이디 저장하기 체크
		String loginId = CookieManager.readCookie(request, "loginId");
		if (!loginId.equals("")) {
			request.setAttribute("loginId", loginId);
			request.setAttribute("cookieCheck", "checked");
		}
		request.getRequestDispatcher("../Membership/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인폼에서 전송한 값
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		// DB 연결 및 아이디와 패스워드 인증
		MemberDAO dao = new MemberDAO();
		MemberDTO memberDTO = dao.getMemberDTO(id, password);
		// 로그인 성공
		if (memberDTO.getId() != null) {
			HttpSession session = request.getSession();
			session.setAttribute("id", memberDTO.getId());
			session.setAttribute("password", memberDTO.getPassword());
			session.setAttribute("name", memberDTO.getName());
			session.setAttribute("email", memberDTO.getEmail());
			session.setAttribute("tel", memberDTO.getTel());
			// 아이디 저장기능
			String save_check = request.getParameter("save_check");
			if (save_check != null) {
				CookieManager.makeCookie(response, "loginId", id, 86400);
			}
			else {
				CookieManager.deleteCookie(response, "loginId");
			}
			response.sendRedirect("../index.jsp");
		}
		// 로그인 실패
		else {
			JSFunction.alertBack(response, "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
		}

	}

}
