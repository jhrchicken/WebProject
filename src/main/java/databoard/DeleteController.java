package databoard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/databoard/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num = request.getParameter("num");
		DataboardDAO dao = new DataboardDAO();
		// 세션 검사
		DataboardDTO dto = dao.selectView(num);
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null && !(dto.getId().equals((String) session.getAttribute("id")))) {
			JSFunction.alertLocation(response, "작성자 본인만 삭제할 수 있습니다.", "../databoard/view.do?num=" + num);
			return;
		}
		// 게시물 삭제
		int result = dao.deletePost(num);
		if (result == 1) {
			String saveFileName = dto.getSfile();
			FileUtil.deleteFile(request, "/Uploads", saveFileName);
		}
		JSFunction.alertLocation(response, "삭제되었습니다.", "../databoard/list.do");
	}
}
