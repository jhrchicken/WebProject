package freeboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/freeboard/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// 글쓰기 페이지 진입
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			JSFunction.alertLocation(response, "로그인 후 이용해 주세요.", "../UIUX/login.jsp");
			return;
		}
		request.getRequestDispatcher("../UIUX/freeboardWrite.jsp").forward(request, response);
	}
	
	/* 글쓰기 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼값을 DTO에 저장
		FreeboardDTO dto = new FreeboardDTO();
		dto.setId(request.getParameter("id"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("content"));
		// DAO를 통해 DB에 값을 저장
		FreeboardDAO dao = new FreeboardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		// insert에 성공하면 목록으로 이동하고, 실패하면 쓰기 페이지로 이동한다.
		if (result == 1) {
			// 글쓰기 성공
			response.sendRedirect("../freeboard/list.do");
		} else {
			// 글쓰기 실패
			JSFunction.alertLocation(response, "글쓰기에 실패했습니다.", "../freeboard/write.do");
		}
		
	}

}
