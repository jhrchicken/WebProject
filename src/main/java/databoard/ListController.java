package databoard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

@WebServlet("/databoard/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB 연결
		DataboardDAO dao = new DataboardDAO();
		// 게시물의 구간 및 검색어 관련 파라미터 저장을 위한 Map 계열의 컬렉션 생성
		Map<String, Object> map = new HashMap<String, Object>();
		// 검색어 파라미터가 있는 경우 값을 받은 후 Map에 저장한다.
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}

		// 게시물의 개수 카운트
		int totalCount = dao.selectCount(map);
		// 페이지 처리 시작
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		// 현재 페이지 번호 설정 (첫 진입 시에는 무조건 1페이지로 설정)
		int pageNum = 1;
		String pageTemp = request.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals("")) {
			pageNum = Integer.parseInt(pageTemp);
		}
		// 목록에 출력할 게시물의 범위를 계산하여 Map에 저장
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
		map.put("start", start);
		map.put("end", end);
		// 페이지 처리 끝

		// 게시물 리스트
		List<DataboardDTO> databoardLists = dao.selectListPage(map);
		dao.close();
		// 페이지 번호를 String으로 반환
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../databoard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		// View (JSP페이지)로 전달할 데이터를 request 영역에 저장
		request.setAttribute("databoardLists", databoardLists);
		request.setAttribute("map", map);
		// 포워드
		request.getRequestDispatcher("../UIUX/databoard.jsp").forward(request, response);
	}

}
