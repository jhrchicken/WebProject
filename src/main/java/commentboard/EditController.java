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

@WebServlet("/commentboard/edit.do")
public class EditController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 댓글수정 페이지 진입
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			JSFunction.alertLocation(response, "로그인 후 이용해 주세요.", "../membership/login.do");
			return;
		}
		
		String ref = request.getParameter("ref");
		String num = request.getParameter("num");
			
		QnaboardDAO qnaboardDAO = new QnaboardDAO();
		QnaboardDTO qnaboardDTO = qnaboardDAO.selectView(ref);
		qnaboardDAO.close();
		qnaboardDTO.setContent(qnaboardDTO.getContent().replaceAll("\r\n", "<br/>"));
		
		CommentboardDAO commentDAO = new CommentboardDAO();
		CommentboardDTO commentDTO = commentDAO.selectView(num);
		List<CommentboardDTO> commentLists = commentDAO.selectListPage(ref);
		commentDAO.close();
		
		request.setAttribute("commentLists", commentLists);
		request.setAttribute("qnaboardDTO", qnaboardDTO);
		request.setAttribute("commentDTO", commentDTO);
		request.getRequestDispatcher("../UIUX/commentboardEdit.jsp").forward(request, response);
	}
	
	// 댓글 수정
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));

		// 폼값을 DTO에 저장
		CommentboardDTO dto = new CommentboardDTO();
		dto.setNum(num);
		dto.setContent(content);
		// DAO를 통해 DB에 값을 저장
		CommentboardDAO dao = new CommentboardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		// 댓글 수정
		if (result == 1) {
			System.out.println("수정완료");
			response.sendRedirect("../qnaboard/view.do?num="+ref);
		} else {
			JSFunction.alertLocation(response, "댓글 수정에 실패했습니다.", "../commentboard/edit.do");
		}

	}
	
	
	
	
	
	
	
	
	
	

}
