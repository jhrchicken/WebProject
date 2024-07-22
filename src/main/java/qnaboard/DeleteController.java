package qnaboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/qnaboard/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		QnaboardDAO dao = new QnaboardDAO();
		// 세션 검사
		QnaboardDTO dto = dao.selectView(num);
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null && !(dto.getId().equals((String)session.getAttribute("id")))) {
			JSFunction.alertLocation(response, "작성자 본인만 삭제할 수 있습니다.", "../qnaboard/view.do?num="+num);
			return;
		}
		// 게시물 삭제
		int result = dao.deletePost(num);
		// 게시물이 삭제된 경우
		if (result == 1) {
			JSFunction.alertLocation(response, "삭제되었습니다.", "../qnaboard/list.do");
		}
		// 게시물이 삭제되지 않은 경우
		else {
			JSFunction.alertBack(response, "삭제에 실패했습니다.");
		}
	}

}
