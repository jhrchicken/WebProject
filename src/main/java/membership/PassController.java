package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/membership/pass.do")
public class PassController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			JSFunction.alertLocation(response, "로그인 후 이용해 주세요.", "../membership/login.do");
			return;
		}
		request.getRequestDispatcher("../Membership/pass.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if (password.equals((String)session.getAttribute("password"))) {
			response.sendRedirect("../membership/editProfile.do");
		}
		else {
			JSFunction.alertLocation(response, "비밀번호를 다시 확인해주세요.", "../membership/pass.do");
		}
	}
	
	
}
