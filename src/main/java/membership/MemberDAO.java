package membership;

import common.JDBConnect;
import freeboard.FreeboardDTO;
import jakarta.servlet.ServletContext;

public class MemberDAO extends JDBConnect {

	// 생성자
	public MemberDAO() {
		super(); 
	}
	
	// 회원가입 메서드
	public int registMember (MemberDTO dto) {
		int result = 0;
		
		try {
			String query = "INSERT INTO member (id, password, name, email, tel) VALUES (?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(2, dto.getPassword());
			psmt.setString(1, dto.getId());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getTel());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("회원 등록 중 오류가 발생했습니다.");
			e.printStackTrace();
		}

		return result;
	}
	
	public MemberDTO getMemberDTO(String id) {
		// 회원정보를 저장하기 위한 인스턴스 생성
		MemberDTO dto = new MemberDTO();
		// 쿼리문 작성
		String query = "SELECT * FROM member WHERE id=?";
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			// 회원정보가 있다면 DTO 객체에 저장
			if (rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setTel(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 회원정보를 저장한 인스턴스를 반환
		return dto;
	}

	// 회원정보 메서드
	public MemberDTO getMemberDTO(String id, String password) {
		// 회원정보를 저장하기 위한 인스턴스 생성
		MemberDTO dto = new MemberDTO();
		// 쿼리문 작성
		String query = "SELECT * FROM member WHERE id=? AND password=?";
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			// 회원정보가 있다면 DTO 객체에 저장
			if (rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setTel(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 회원정보를 저장한 인스턴스를 반환
		return dto;
	}
	
	// 회원정보 수정 메서드
	public int updateProfile (MemberDTO dto) {
		int result = 0;
		
		try {
			String query = "UPDATE member SET password=?, name=?, email=?, tel=? WHERE id=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPassword());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getTel());
			psmt.setString(5, dto.getId());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("회원정보 수정에 실패하였습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원탈퇴 메서드
	public int deleteMember(String id) {
		int result = 0;
		try {
			String query = "DELETE FROM member WHERE id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("회원탈퇴에 실패하였습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원검색 메서드
	public int selectMember(String id) {
		int result = 0;
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=?";
		// 쿼리문 실행
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			// 결과를 DTO에 저장
			if (rs.next()) {
				result = 1;
			}
		}
		catch (Exception e) {
			System.out.println("회원검색에 실패했습니다.");
			e.printStackTrace();
		}
		return result;
	}
	
}
