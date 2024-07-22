package qnaboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/qnaboard/edit.do")
public class EditController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/* 수정페이지로 진입하면 기존 게시물의 내용을 작성폼에 설정 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 파라미터로 전달된 일련번호를 통해 기존의 게시물을 인출한다. 상세보기에서 사용한 메서드를 그대로 사용하면 된다. */
		String num = request.getParameter("num");
		QnaboardDAO dao = new QnaboardDAO();
		QnaboardDTO dto = dao.selectView(num);
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null && !(dto.getId().equals((String)session.getAttribute("id")))) {
			JSFunction.alertLocation(response, "작성자 본인만 수정할 수 있습니다.", "../qnaboard/view.do?num="+num);
			return;
		}
		// DTO를 request 영역에 저장한 후 수정페이지로 포워드
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("../Qnaboard/qnaboardEdit.jsp").forward(request, response);
	}
	
	/* 수정 완료 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 전달된 값 저장
		String num = request.getParameter("num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		System.out.println(num);
		System.out.println(title);
		System.out.println(content);
		// DTO에 저장
		QnaboardDTO dto = new QnaboardDTO();
		dto.setNum(Integer.parseInt(num));
		dto.setTitle(title);
		dto.setContent(content);
		// DB에 수정 내용 적용
		QnaboardDAO dao = new QnaboardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		if (result == 1) {
			response.sendRedirect("../qnaboard/view.do?num=" + num);
		}
		else {
			JSFunction.alertLocation(response, "실패", "../qnaboard/view.do?num=" + num);
		}

	}
	
	
	
	
	
	
	
	
	
	
}
