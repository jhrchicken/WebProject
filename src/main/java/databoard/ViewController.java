package databoard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CookieManager;

@WebServlet("/databoard/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		// DB 연결
		DataboardDAO dao = new DataboardDAO();
		
		// 게시물 증가 (쿠키)
		HttpSession sessoin = request.getSession();
		String id = (String) sessoin.getAttribute("id");
		if (id != null) {
			String numStr = CookieManager.readCookie(request, "databoard-view-"+ id + "-" + num);
			// 쿠키 없으면 해당 페이지에 처음 방문했다는 것이므로 조회수 증가 및 쿠키 생성
			if (numStr.equals("")) {
				// 하루짜리 쿠키를 해당 게시물 번호로 생성 (방문한 흔적 남기기)
				CookieManager.makeCookie(response, "databoard-view-"+ id + "-" + num, "databoard-view-"+ id + "-" + num, 86400);
				// 게시물 조회수 증가
				dao.updateVisitCount(num);
			}
		}
		
		DataboardDTO dto = dao.selectView(num);
		dao.close();
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		// 파일 처리
		if (dto.getSfile() != null) {
			String ext = dto.getSfile().substring(dto.getSfile().lastIndexOf("."));
			request.setAttribute("ext", ext);
		}
		// 게시물이 저장된 DTO 객체를 request 영역에 저장하고 JSP로 포워드한다.
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("../UIUX/databoardView.jsp").forward(request, response);
	}

}
