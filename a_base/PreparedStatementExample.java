package a_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExample {

	public static void main(String[] args) {
		Connection conn = null;
		// PREPARE  EXECUTE 를 이용하여
		// 동적 쿼리 작업을 수행하는 객체
		// 수행 쿼리를 미리 등록 시켜놓고 질의 수행에
		// 필요한 데이터(값을) 나중에 대입하여 작업 수행
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sqldb",
				"root",
				"12345"
			);
			System.out.println("DB 연결 완료");
			// PREPARE 등록할 질의문
			String sql = "SELECT name, mdate FROM userTbl WHERE userID = ?";
			pstmt = conn.prepareStatement(sql);
			// pstmt.setString(wild card 자리 수, 십입할 data);
			pstmt.setString(1, "YJS");
			// 완성된 질의문 수행후 결과 반환
			rs = pstmt.executeQuery();
			// 검색된 행정보가 존재하면 해당 행으로 이동, 없으면 false 이므로 조건문 패스
			if(rs.next()) {
				//  1      2
				// name, mdate
				String name = rs.getString(1);
				java.util.Date mdate = rs.getDate(2);
				System.out.println(name+":"+mdate);
			}
			rs.close();
			pstmt.close();
			//                                  1            2           3                4
			sql = "UPDATE userTbl SET mobile1 = ?, mobile2 = ?, height = ? WHERE userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "010");
			pstmt.setInt(3, 187);
			pstmt.setString(2, "94867166");
			pstmt.setString(4, "CGG");
			
			int result = pstmt.executeUpdate();
			System.out.println("수정된 행의 개수 : " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {}
			
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {}
			
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {}
			
		}
	}

}










