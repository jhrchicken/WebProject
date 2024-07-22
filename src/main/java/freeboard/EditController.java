package freeboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/freeboard/edit.do")
public class EditController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/* 수정페이지로 진입하면 기존 게시물의 내용을 작성폼에 설정 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 파라미터로 전달된 일련번호를 통해 기존의 게시물을 인출한다. 상세보기에서 사용한 메서드를 그대로 사용하면 된다. */
		String num = request.getParameter("num");
		FreeboardDAO dao = new FreeboardDAO();
		FreeboardDTO dto = dao.selectView(num);
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null && !(dto.getId().equals((String)session.getAttribute("id")))) {
			JSFunction.alertLocation(response, "작성자 본인만 수정할 수 있습니다.", "../freeboard/view.do?num="+num);
			return;
		}
		// DTO를 request 영역에 저장한 후 수정페이지로 포워드
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("../Freeboard/freeboardEdit.jsp").forward(request, response);
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
		FreeboardDTO dto = new FreeboardDTO();
		dto.setNum(Integer.parseInt(num));
		dto.setTitle(title);
		dto.setContent(content);
		// DB에 수정 내용 적용
		FreeboardDAO dao = new FreeboardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		if (result == 1) {
			response.sendRedirect("../freeboard/view.do?num=" + num);
		}
		else {
			JSFunction.alertLocation(response, "실패", "../freeboard/view.do?num=" + num);
		}

	}
	
	
	
	
	
	
	
	
	
	
}
