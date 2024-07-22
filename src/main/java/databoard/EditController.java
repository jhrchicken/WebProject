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

@WebServlet("/databoard/edit.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 100000)
public class EditController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시물 인출
		String num = request.getParameter("num");
		DataboardDAO dao = new DataboardDAO();
		DataboardDTO dto = dao.selectView(num);
		// 세션 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null && !(dto.getId().equals((String)session.getAttribute("id")))) {
			JSFunction.alertLocation(response, "작성자 본인만 수정할 수 있습니다.", "../databoard/view.do?num="+num);
			return;
		}
		// DTO를 request 영역에 저장한 후 수정페이지로 포워드
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("../Databoard/databoardEdit.jsp").forward(request, response);
	}
	
	// 수정
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDirectory = request.getServletContext().getRealPath("/Uploads");
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(request, saveDirectory);
		}
		catch (Exception e) {
			JSFunction.alertBack(response, "파일 업로드 오류입니다.");
			return;
		}
		// 파라미터 값
		String num = request.getParameter("num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String prevOfile = request.getParameter("prevOfile");
		String prevSfile = request.getParameter("prevSfile");
		// DTO에 저장
		DataboardDTO dto = new DataboardDTO();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setId(id);
		// 파일 업로드
		if (originalFileName != "") {
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			dto.setOfile(originalFileName);
			dto.setSfile(savedFileName);
			FileUtil.deleteFile(request, "/Uploads", prevSfile);
		}
		else {
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		// DB에 수정내용 적용
		DataboardDAO dao = new DataboardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		if (result == 1) {
			response.sendRedirect("../databoard/view.do?num="+num);
		}
		else {
			JSFunction.alertLocation(response, "자료실 게시물 수정에 실패했습니다.", "../databoard/edit.do?num="+num);
		}

	}

}
