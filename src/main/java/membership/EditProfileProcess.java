package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/membership/editProfile.do")
public class EditProfileProcess extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			JSFunction.alertLocation(response, "로그인 후 이용해 주세요.", "../membership/login.do");
			return;
		}
		request.getRequestDispatcher("../UIUX/profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원정보 수정 폼에 입력된 값
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		// 회원정보를 DTO에 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPassword(password);
		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);
		// DB에 수정한 회원정보를 반영
		MemberDAO dao = new MemberDAO();
		int result = dao.updateProfile(dto);
		// 수정 성공
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", dto.getId());
			session.setAttribute("password", dto.getPassword());
			session.setAttribute("name", dto.getName());
			session.setAttribute("email", dto.getEmail());
			session.setAttribute("tel", dto.getTel());
			JSFunction.alertLocation(response, "회원정보 수정이 완료되었습니다.", "../membership/editProfile.do");
		}
		// 수정 실패
		else {
			JSFunction.alertLocation(response, "회원정보 수정에 실패했습니다.", "../membership/editProfile.do");
		}
	}

}
