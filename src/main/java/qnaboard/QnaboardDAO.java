package qnaboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class QnaboardDAO extends JDBConnect {

	// 생성자
	public QnaboardDAO() {
		super();
	}
	
	// 게시물 수 카운트 메서드
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM qnaboard";
		// 검색어가 있는 경우
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		// 쿼리문 실행
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 수 카운트에 실패했습니다.");
			e.printStackTrace();
		}
		return totalCount;
	}

	// 목록에 출력할 게시물을 페이지 단위로 얻어오는 메서드
	public List<QnaboardDTO> selectListPage(Map<String, Object> map) {
		List<QnaboardDTO> qnaboard = new Vector<QnaboardDTO>();
		// 페이지 처리를 위한 서브쿼리문 작성
		String query = "SELECT * FROM  (SELECT Tb.*, ROWNUM rNum FROM (SELECT * FROM qnaboard ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY num DESC) Tb) WHERE rNum BETWEEN ? AND ?";
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			// qnaboard 리스트에 저장
			while (rs.next()) {
				QnaboardDTO dto = new QnaboardDTO();
				
				dto.setNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setVisitcount(rs.getInt(6));
				
				qnaboard.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회에 실패했습니다.");
			e.printStackTrace();
		}
		return qnaboard;
	}
	
	// 글쓰기 메서드
	public int insertWrite(QnaboardDTO dto) {
		int result = 0;
		// 쿼리문 실행
		try {
			String query = "INSERT INTO qnaboard (num, title, content, id) VALUES (seq_qnaboard_num.NEXTVAL, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력에 실패했습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시물 상세보기 메서드
	public QnaboardDTO selectView(String num) {
		QnaboardDTO dto = new QnaboardDTO();
		String query = "SELECT * FROM qnaboard WHERE num=?";
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			// 결과를 DTO에 저장
			if (rs.next()) {
				dto.setNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setVisitcount(rs.getInt(6));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기에 실패했습니다.");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 게시물 조회수 증가 메서드
	public void updateVisitCount(String num) {
		String query = "UPDATE qnaboard SET visitcount=visitcount + 1 WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
	// 게시물 삭제 메서드
	public int deletePost(String num) {
		int result = 0;
		try {
			String query = "DELETE FROM qnaboard WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 삭제에 실패하였습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시물 수정 메서드
	public int updatePost(QnaboardDTO dto) {
		int result = 0;
		try {
			// 쿼리문 작성
			String query = "UPDATE qnaboard SET title=?, content=? WHERE num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNum());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
}
