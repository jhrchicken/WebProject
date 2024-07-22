package qnaboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commentboard.CommentboardDAO;
import commentboard.CommentboardDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CookieManager;

@WebServlet("/qnaboard/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QnaboardDAO dao = new QnaboardDAO();
		// 파라미터로 전달된 일련번호 받기
		String num = request.getParameter("num");

		// 게시물 증가 (쿠키)
		HttpSession sessoin = request.getSession();
		String id = (String) sessoin.getAttribute("id");
		if (id != null) {
			String numStr = CookieManager.readCookie(request, "qnaboard-view-"+ id + "-" + num);
			// 쿠키 없으면 해당 페이지에 처음 방문했다는 것이므로 조회수 증가 및 쿠키 생성
			if (numStr.equals("")) {
				// 하루짜리 쿠키를 해당 게시물 번호로 생성 (방문한 흔적 남기기)
				CookieManager.makeCookie(response, "qnaboard-view-"+ id + "-" + num, "qnaboard-view-"+ id + "-" + num, 86400);
				// 게시물 조회수 증가
				dao.updateVisitCount(num);
			}
		}

		// 게시물 인출
		QnaboardDTO dto = dao.selectView(num);
		dao.close();
		/* 내용의 경우 Enter를 통해 줄바꿈을 하게 되므로 웹브라우저 출력 시 <br> 태그로 변경해야 한다. */
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));

		// 댓글
		CommentboardDAO commentdao = new CommentboardDAO();
		// 댓글 리스트
		List<CommentboardDTO> commentLists = commentdao.selectListPage(num);
		commentdao.close();
		// View (JSP페이지)로 전달할 데이터를 request 영역에 저장
		request.setAttribute("commentLists", commentLists);
		
		// 게시물이 저장된 DTO 객체를 request 영역에 저장하고 JSP로 포워드한다.
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("../Qnaboard/qnaboardView.jsp").forward(request, response);
	}
}
