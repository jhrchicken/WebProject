package databoard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/databoard/write.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 100000)
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			JSFunction.alertLocation(response, "로그인 후 이용해 주세요.", "../Membership/login.jsp");
			return;
		}
		request.getRequestDispatcher("../Databoard/databoardWrite.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일업로드
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(request, saveDirectory);
		} catch (Exception e) {
			e.printStackTrace();
			JSFunction.alertLocation(response, "파일 업로드에 실패했습니다.", "../freeboard/write.do");
			return;
		}

		// 파일 외 업로드
		DataboardDTO dto = new DataboardDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setId(request.getParameter("id"));
		// 파일명 변경 및 저장
		if (originalFileName != "") {
			String saveFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			dto.setOfile(originalFileName);
			dto.setSfile(saveFileName);
		}
		// DB에 값 저장
		DataboardDAO dao = new DataboardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		if (result == 1) {
			response.sendRedirect("../databoard/list.do");
		} else {
			JSFunction.alertLocation(response, "글쓰기에 실패했습니다.", "../databoard/write.do");
		}
	}

}
