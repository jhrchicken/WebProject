package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

// 자주 사용하는 Javascipt의 함수를 클래스로 정의
public class JSFunction {

	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ "		alert('"+ msg + "');"
					+ "		location.href='" + url + "';"
					+ "</script>";
			writer.println(script);
		} catch (Exception e) {
			
		}
		
	}

	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String sctipt = ""
					+ "<script>"
					+ "		alert('" + msg + "');"
					+ "		history.back();"
					+ "</script>";
			writer.println(sctipt);
		} catch (Exception e) {
			
		}
	}
	
}
