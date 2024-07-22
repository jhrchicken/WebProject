package freeboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class FreeboardDAO extends JDBConnect {

	/* 생성자 */
	public FreeboardDAO() {
		super();
	}
	
	/* 게시물 수 카운트 메서드 */
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM freeboard";
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

	/* 목록에 출력할 게시물을 페이지 단위로 얻어오는 메서드 */
	public List<FreeboardDTO> selectListPage(Map<String, Object> map) {
		List<FreeboardDTO> freeboard = new Vector<FreeboardDTO>();
		// 페이지 처리를 위한 서브쿼리문 작성
		String query = "SELECT * FROM  (SELECT Tb.*, ROWNUM rNum FROM (SELECT * FROM freeboard ";
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
			// freeboard 리스트에 저장
			while (rs.next()) {
				FreeboardDTO dto = new FreeboardDTO();
				
				dto.setNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setVisitcount(rs.getInt(6));
				
				freeboard.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회에 실패했습니다.");
			e.printStackTrace();
		}
		return freeboard;
	}
	
	/* 글쓰기 페이지에서 전송한 폼값을 테이블에 insert 하는 메서드 */
	public int insertWrite(FreeboardDTO dto) {
		int result = 0;
		// 쿼리문 실행
		try {
			String query = "INSERT INTO freeboard (num, title, content, id) VALUES (seq_freeboard_num.NEXTVAL, ?, ?, ?)";
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
	
	/* 상세보기를 위해 일련번호에 해당하는 레코드를 인출해서 반환하는 메서드 */
	public FreeboardDTO selectView(String num) {
		FreeboardDTO dto = new FreeboardDTO();
		String query = "SELECT * FROM freeboard WHERE num=?";
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
				dto.setLikecount(rs.getInt(7));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기에 실패했습니다.");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	/* 게시물의 조회수 증가 메서드 */
	public void updateVisitCount(String num) {
		String query = "UPDATE freeboard SET visitcount=visitcount + 1 WHERE num=?";
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
	
	/* 게시물 삭제 메서드 */
	public int deletePost(String num) {
		int result = 0;
		try {
			String query = "DELETE FROM freeboard WHERE num=?";
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
	
	/* 게시물 수정 메서드 */
	public int updatePost(FreeboardDTO dto) {
		int result = 0;
		try {
			// 쿼리문 작성
			String query = "UPDATE freeboard SET title=?, content=? WHERE num=?";
			
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
	
	// 게시물의 좋아요수 증가 메서드
	public void updateLikeCount(String num) {
		String query = "UPDATE freeboard SET likecount=likecount + 1 WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 좋아요 수 증가에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
	public int getLikeCount(String num) {
		int likecount = 0;
		String query = "SELECT likecount FROM freeboard WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
			rs = psmt.executeQuery();
			rs.next();
			likecount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 좋아요 수 구하기에 실패했습니다.");
			e.printStackTrace();
		}		
		
		return likecount;
	}
	
	
	
	
	
}
