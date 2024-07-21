package freeboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CookieManager;

@WebServlet("/freeboard/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FreeboardDAO dao = new FreeboardDAO();
		// 파라미터로 전달된 일련번호 받기
		String num = request.getParameter("num");

		// 게시물 증가 (쿠키)
		HttpSession sessoin = request.getSession();
		String id = (String) sessoin.getAttribute("id");
		if (id != null) {
			String numStr = CookieManager.readCookie(request, "freeboard-view-"+ id + "-" + num);
			// 쿠키 없으면 해당 페이지에 처음 방문했다는 것이므로 조회수 증가 및 쿠키 생성
			if (numStr.equals("")) {
				// 하루짜리 쿠키를 해당 게시물 번호로 생성 (방문한 흔적 남기기)
				CookieManager.makeCookie(response, "freeboard-view-"+ id + "-" + num, "freeboard-view-"+ id + "-" + num, 86400);
				// 게시물 조회수 증가
				dao.updateVisitCount(num);
			}
		}

		// 게시물 인출
		FreeboardDTO dto = dao.selectView(num);
		dao.close();
		/* 내용의 경우 Enter를 통해 줄바꿈을 하게 되므로 웹브라우저 출력 시 <br> 태그로 변경해야 한다. */
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));

		// 게시물이 저장된 DTO 객체를 request 영역에 저장하고 JSP로 포워드한다.
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("../UIUX/freeboardView.jsp").forward(request, response);
	}
}
