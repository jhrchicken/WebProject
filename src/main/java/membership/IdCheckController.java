package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

@WebServlet("/membership/idcheck.do")
public class IdCheckController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 아이디 중복여부 확인
		MemberDAO dao = new MemberDAO();
		int isDup = dao.selectMember(id);
		dao.close();
		System.out.println("중복여부 : " + isDup);
		if (isDup == 1) {
			JSFunction.alertLocation(response, "사용할 수 없는 아이디입니다.", "../membership/register.do");
		}
		else {
			JSFunction.alertLocation(response, "사용가능한 아이디입니다.", "../membership/register.do");
		}
	}
	
}