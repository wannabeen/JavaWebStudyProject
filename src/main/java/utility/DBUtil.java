package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = null;

	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			System.out.println("Driver 로딩 성공!");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studydb","root","1234");
			System.out.println("DB 연결 성공!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결에 실패");
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
