package commentboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/commentboard/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ref = request.getParameter("ref");
		String num = request.getParameter("num");
		CommentboardDAO dao = new CommentboardDAO();
		// 세션 검사
		CommentboardDTO dto = dao.selectView(num);
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null && !(dto.getId().equals((String)session.getAttribute("id")))) {
			JSFunction.alertLocation(response, "작성자 본인만 삭제할 수 있습니다.", "../qnaboard/view.do?num="+ref);
			return;
		}
		// 댓글 삭제
		int result = dao.deletePost(num);
		if (result == 1) {
			JSFunction.alertLocation(response, "댓글이 삭제되었습니다.", "../qnaboard/view.do?num="+ref);
		}
		else {
			JSFunction.alertBack(response, "댓글 삭제에 실패했습니다.");
		}
	}

}
