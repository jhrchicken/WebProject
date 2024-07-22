package commentboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class CommentboardDAO extends JDBConnect {

	// 생성자
	public CommentboardDAO() {
		super();
	}
	
	// 댓글 선택 메서드
	public CommentboardDTO selectView(String num) {
		// 하나의 레코드를 저장하기 위한 DTO인스턴스 생성
		CommentboardDTO dto = new CommentboardDTO();
		
		String query = "SELECT * FROM commentboard WHERE num=?";
		
		try {
			// 쿼리문의 인파라미터를 설정한 후 쿼리문 실행
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setNum(rs.getInt(1));
				dto.setContent(rs.getString(2));
				dto.setId(rs.getString(3));
				dto.setPostdate(rs.getDate(4));
				dto.setRef(rs.getInt(5));
			}
		} catch (Exception e) {
			System.out.println("댓글 선택에 실패했습니다.");
			e.printStackTrace();
		}
		return dto;
	}

	// 댓글목록을 얻어오는 메서드
	public List<CommentboardDTO> selectListPage(String ref) {
		List<CommentboardDTO> commentboard = new Vector<CommentboardDTO>();
		// 서브쿼리문 작성
		String query = "SELECT * FROM commentboard WHERE ref=? ORDER BY num DESC";
		
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, ref);
			rs = psmt.executeQuery();
			// commentboard 리스트에 저장
			while (rs.next()) {
				CommentboardDTO dto = new CommentboardDTO();
				dto.setNum(rs.getInt(1));
				dto.setContent(rs.getString(2));
				dto.setId(rs.getString(3));
				dto.setPostdate(rs.getDate(4));
				dto.setRef(rs.getInt(5));

				commentboard.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("댓글 조회에 실패했습니다.");
			e.printStackTrace();
		}
		return commentboard;
	}
	
	// 글쓰기 메서드
	public int insertWrite(CommentboardDTO dto) {
		int result = 0;
		// 쿼리문 실행
		try {
			String query = "INSERT INTO commentboard (num, content, id, ref) VALUES (seq_commentboard_num.NEXTVAL, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getId());
			psmt.setInt(3, dto.getRef());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("댓글 작성에 실패했습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 댓글 삭제 메서드
	public int deletePost(String num) {
		int result = 0;
		try {
			String query = "DELETE FROM commentboard WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("댓글 삭제에 실패하였습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 댓글 수정 메서드
	public int updatePost(CommentboardDTO dto) {
		int result = 0;
		
		try {
			// 쿼리문 작성
			String query = "UPDATE commentboard SET content=? WHERE num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getContent());
			psmt.setInt(2, dto.getNum());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("댓글 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
}
