package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

@WebServlet("/membership/register.do")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("../UIUX/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 폼 값을 DTO에 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setTel(request.getParameter("tel"));
		// DAO를 통해 DB에 값을 저장
		MemberDAO dao = new MemberDAO();
		int result = dao.registMember(dto);
		dao.close();
		// 회원가입 성공
		if (result == 1) {
			JSFunction.alertLocation(response, "회원가입이 완료되었습니다.", "../membership/login.do");
		}
		// 회원가입 실패
		else {
			JSFunction.alertLocation(response, "회원가입에 실패했습니다. 다시 시도해주세요.", "../membership/register.do");
		}
	}
	
	
	
	
}
