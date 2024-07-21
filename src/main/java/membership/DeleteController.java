package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

@WebServlet("/membership/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDAO dao = new MemberDAO();
		// 멤버 삭제
		int result = dao.deleteMember(id);
		// 성공 실패 확인
		if (result == 1) {
			JSFunction.alertLocation(response, "회원 탈퇴 되었습니다.", "../membership/login.do");
		}
		else {
			JSFunction.alertBack(response, "회원 탈퇴에 실패했습니다.");
		}
	}
	
}
