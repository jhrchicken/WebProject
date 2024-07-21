package databoard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/databoard/download.do")
public class DownloadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 다로드 링크를 통해 전달되는 3개의 파라미터 받아오기
		String ofile = request.getParameter("ofile");
		String sfile = request.getParameter("sfile");
		String num = request.getParameter("num");
		// 다운로드 메서드 호출 및 파일 다운로드
		FileUtil.download(request, response, "/Uploads", sfile, ofile);
		// 다운로드 완료 후 카운트 증가
		DataboardDAO dao = new DataboardDAO();
		dao.downCountPlus(num);
		dao.close();
	}

}