package commentboard;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import qnaboard.QnaboardDAO;
import qnaboard.QnaboardDTO;
import utils.JSFunction;

@WebServlet("/commentboard/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 댓글쓰기 페이지 진입
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			JSFunction.alertLocation(response, "로그인 후 이용해 주세요.", "../membership/login.do");
			return;
		}
		System.out.println("진입 성공");
		// View를 보여주기 위함
		QnaboardDAO dao = new QnaboardDAO();
		String num = request.getParameter("num");
		QnaboardDTO dto = dao.selectView(num);
		dao.close();
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		CommentboardDAO commentdao = new CommentboardDAO();
		List<CommentboardDTO> commentLists = commentdao.selectListPage(num);
		commentdao.close();
		request.setAttribute("commentLists", commentLists);
		request.setAttribute("dto", dto);

		request.getRequestDispatcher("../Qnaboard/commentboardWrite.jsp").forward(request, response);
	}
	
	// 댓글 작성
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		int ref = Integer.parseInt(request.getParameter("ref"));

		// 폼값을 DTO에 저장
		CommentboardDTO dto = new CommentboardDTO();
		dto.setContent(content);
		dto.setId(id);
		dto.setRef(ref);
		// DAO를 통해 DB에 값을 저장
		CommentboardDAO dao = new CommentboardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		// insert에 성공하면 목록으로 이동하고, 실패하면 쓰기 페이지로 이동한다.
		if (result == 1) {
			// 글쓰기 성공
			response.sendRedirect("../qnaboard/view.do?num="+ref);
		} else {
			// 글쓰기 실패
			JSFunction.alertLocation(response, "글쓰기에 실패했습니다.", "../commentboard/write.do");
		}

	}

}
