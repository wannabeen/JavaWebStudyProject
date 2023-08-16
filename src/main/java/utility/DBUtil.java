package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {

	/*
	 * public static final String driver = "com.mysql.cj.jdbc.Driver"; public static
	 * void initConnection() {
	 * 
	 * try { // 1. lib 폴더에 추가한 라이브러리를 불러온다.
	 * Class.forName("com.mysql.cj.jdbc.Driver");
	 * System.out.println("Driver Loading Success"); // 드라이버 로딩 성공 } catch
	 * (ClassNotFoundException e) { System.out.println("BD Driver를 찾지 못했습니다."); //
	 * 드라이버 로딩 실패 e.printStackTrace(); } }
	 * 
	 * public static Connection getConnection() { // DB 접속 정보 Connection conn =
	 * null;
	 * 
	 * try { // 불러온 라이브러리의 getConnection() 메서드를 사용해 DB에 접속한다. conn =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb", "root",
	 * "1234"); // DB 포로토콜 IP PROT DB명 사용자명 비밀번호
	 * System.out.println("Connection Success"); // DB 연결 성공 } catch (SQLException
	 * e) { System.out.println("DB를 연결하지 못했습니다."); // DB 연결 실패 e.printStackTrace();
	 * } return conn; }
	 */

	public static Connection getConnection() {
		Connection conn = null;
		
		try {
		//JNDI에 접근하기 위해 기본 경로(java:comp/env)를 지정
		Context ctx = new InitialContext();
		Context envContext = (Context) ctx.lookup("java:comp/env");
		
		// 톰캣 context.xml에 설정한 name 값 "jdbc/mysql"을 이용해 톰캣이 미리 연결한 DataSource를 받아온다.
		DataSource ds = (DataSource) envContext.lookup("jdbc/mysql");
		
		conn = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("conn.close() Fail");
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement psmt) {
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				System.out.println("psmt.close() Fail");
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("rs.close() Fail");
				e.printStackTrace();
			}
		}
	}

	public static void closes(Connection conn, PreparedStatement psmt) {
		closes(conn, psmt, null);
	}

	public static void closes(Connection conn, PreparedStatement psmt, ResultSet rs) {
		close(rs);
		close(psmt);
		close(conn);
	}
}
