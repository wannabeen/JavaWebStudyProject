package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import utility.DBUtil;

public class MemberDao {
	public MemberDto selectOneById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDto member = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM member WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idx = rs.getInt("idx");
				String pw = rs.getString("pw");
				String nickname = rs.getString("nickname");
				String tel = rs.getString("tel");
				LocalDateTime joinDateTime = rs.getTimestamp("joinDateTime").toLocalDateTime();
				
				member = new MemberDto(idx, id, pw, nickname, tel, joinDateTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closes(conn, pstmt, rs);
		}
		
		return member;
	}
	
	public MemberDto selectOneByTel(String tel) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDto member = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM member WHERE tel = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int idx = rs.getInt("idx");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String nickname = rs.getString("nickname");
				LocalDateTime joinDateTime = rs.getTimestamp("joinDateTime").toLocalDateTime();
				
				member = new MemberDto(idx, id, pw, nickname, tel, joinDateTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closes(conn, pstmt, rs);
		}
		
		return member;
	}
	
	public int insertByMemberDto(MemberDto member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "INSERT INTO member(id, pw, nickname, tel) VALUES(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getNickname());
			pstmt.setString(4, member.getTel());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closes(conn, pstmt);
		}
		
		return count;
	}

}