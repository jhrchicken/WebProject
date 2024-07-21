package databoard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class DataboardDAO extends JDBConnect {

	// 생성자
	public DataboardDAO() {
		super();
	}

	// 게시물의 수 카운트 메서드
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		// 쿼리문 작성
		String query = "SELECT COUNT(*) FROM databoard";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		// 쿼리문 실행
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("자료실 게시물에 실패했습니다.");
			e.printStackTrace();
		}
		return totalCount;
	}

	// 목록에 출력할 게시물을 페이지 단위로 얻어오는 메서드
	public List<DataboardDTO> selectListPage(Map<String, Object> map) {
		// mvcboard 테이블을 대상으로 하므로 타입매개변수 확인 필요함
		List<DataboardDTO> databoard = new Vector<DataboardDTO>();
		// 쿼리문 작성
		String query = "SELECT * FROM  (SELECT Tb.*, ROWNUM rNum FROM (SELECT * FROM databoard ";
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
			// 결과를 DTO에 저장
			while (rs.next()) {
				DataboardDTO dto = new DataboardDTO();
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setVisitcount(rs.getInt(6));
				dto.setOfile(rs.getString(7));
				dto.setSfile(rs.getString(8));
				dto.setDowncount(rs.getInt(9));
				databoard.add(dto);
			}
		} catch (Exception e) {
			System.out.println("자료실 게시물 조회에 실패했습니다.");
			e.printStackTrace();
		}
		return databoard;
	}

	// 게시물 입력 메서드
	public int insertWrite(DataboardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO databoard (num, title, content, id, ofile, sfile) "
					+ "VALUES (seq_databoard_num.NEXTVAL, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("자료실 게시물 입력에 실패했습니다.");
			e.printStackTrace();
		}
		return result;
	}

	// 상세보기 메서드
	public DataboardDTO selectView(String num) {
		DataboardDTO dto = new DataboardDTO();
		// 쿼리문 작성
		String query = "SELECT * FROM databoard WHERE num=?";
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			// 결과를 DTO에 저장
			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setVisitcount(rs.getInt(6));
				dto.setOfile(rs.getString(7));
				dto.setSfile(rs.getString(8));
				dto.setDowncount(rs.getInt(9));
			}
		} catch (Exception e) {
			System.out.println("자료실 게시물 상세보기에 실패했습니다.");
			e.printStackTrace();
		}
		return dto;
	}

	// 조회수 증가 메서드
	public void updateVisitCount(String num) {
		String query = "UPDATE databoard SET visitcount = visitcount+1 WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("자료실 게시물 조회수 증가에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
	// 파일 다운로드수 증가 메서드
	public void downCountPlus(String num) {
		String query = "UPDATE databoard SET downcount = downcount+1 WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("자료실 게시물 파일 다운로드수 증가에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
	// 게시물 삭제 메서드
	public int deletePost(String num) {
		int result = 0;
		try {
			String query = "DELETE FROM databoard WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("자료실 게시물 삭제에 실패했습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 게시물 수정 메서드
	public int updatePost(DataboardDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE databoard SET title=?, content=?, ofile=?, sfile=? WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			psmt.setString(5, dto.getNum());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("자료실 게시물 수정에 실패했습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
