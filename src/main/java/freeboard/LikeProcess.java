package freeboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.CookieManager;

@WebServlet("/freeboard/like.do")
public class LikeProcess extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FreeboardDAO freeboarddao = new FreeboardDAO();
		// 파라미터로 전달된 일련번호 받기
		String num = request.getParameter("num");
		
		// 좋아요 수 증가
		HttpSession sessoin = request.getSession();
		String id = (String) sessoin.getAttribute("id");
		if (id != null) {
			String numStr = CookieManager.readCookie(request, "freeboard-like-"+ id + "-" + num);
			// 쿠키 없으면 해당 페이지에 좋아요를 누른 적이 없다는 것이므로 좋아요 수 올리기 및 쿠키 생성
			if (numStr.equals("")) {
				CookieManager.makeCookie(response, "freeboard-like-"+ id + "-" + num, "freeboard-like-"+ id + "-" + num, 86400);
				freeboarddao.updateLikeCount(num);
			}
		}
		
		int likecount = freeboarddao.getLikeCount(num);
		request.setAttribute("likecount", likecount);
		request.getRequestDispatcher("../Freeboard/freeboardLike.jsp").forward(request, response);
	}
	
}
