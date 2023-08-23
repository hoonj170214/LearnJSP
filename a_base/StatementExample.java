package a_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class StatementExample {

	public static void main(String[] args) {
		// ctrl + shift + o   auto import
		
		// DBMS와 연결된 session 정보를 저장하고 있는 객체
		Connection conn = null;
		// Connection과 연결된 db에 질의 작업을 수행 하고
		// 결과를 반환하는 객체
		Statement stmt = null;
		// 검색(SELECT) 질의의 결과 정보를 저장하는 객체
		ResultSet rs = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class 로드 완료");
			
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sqldb",
				"root",
				"12345"
			);
			System.out.println("conn : " + conn);
			
			// 질의 작업을 수행할 Statement 객체 생성
			stmt = conn.createStatement();
			// 실행할 쿼리를 문자열로 저장
			String sql = "SELECT * FROM usertbl";
			// 검색 질의에 대한 결과를 저장하는 ResultSet 객체를 반환
			rs = stmt.executeQuery(sql);
			// ResultSet.next();
			// 읽어올 행정보가 있으면 해당 위치로 이동한 후 true 반환
			// 이동할 행정보가 없으면 false 반환
			while(rs.next()) {
				// 검색된 테이블의 한 행의 정보
//    1      2        3       4       5       6       7      8				
// userID, name, birthyear, addr, mobile1, mobile2, height, mdate				
				String userId = rs.getString(1);
				String name = rs.getString("name");
				int birthYear = rs.getInt("birthYear");
				String addr = rs.getString("addr");
				// java.util.Date
				Date mdate = rs.getDate(8);
				System.out.println(userId +"-"+name+"-"+birthYear+"-"+addr+"-"+mdate);
			}
			
			rs.close();
			stmt.close();
			//              1       2       3
			sql = "SELECT name, birthYear, addr FROM userTbl WHERE userID = 'BBK'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				String name = rs.getString(1);
				int birthYear = rs.getInt(2);
				String addr = rs.getString(3);
				System.out.println("BBK : " + name +"-"+ birthYear +"-"+ addr);
			}
			rs.close();
			stmt.close();
			
			// executeUpdate(String sql);
			// table의 data조작 쿼리 실행 -- DML
			// java.util.Scanner
			Scanner sc = new Scanner(System.in);
			System.out.println("userID를 입력해 주세요.");
			String id = sc.next();
			System.out.println("이름을 입력해주세요 > ");
			String name = sc.next();
			System.out.println("출생년도를 입력해주세요 ex) 1982 > ");
			int birthYear = sc.nextInt();
			System.out.println("주소를 입력해주세요. > (두글자)");
			String addr = sc.next();
			
			stmt = conn.createStatement();
			sql = "INSERT INTO userTbl(userID,name,birthYear,addr) "
				+ " VALUES('"+id+"','"+name+"',"+birthYear+",'"+addr+"')";
			System.out.println(sql);
			// 매개변수로 전달 받은 Query 실행 후 
			// 적용된 행의 개수를 int type으로 반환
			int result = stmt.executeUpdate(sql);
			System.out.println(result+"개의 행 삽입 완료!");
			stmt.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class 존재하지 않음");
		} catch (SQLException e) {
			System.out.println("DB 연결 정보 오류 : " + e.getMessage());
		}finally {
			try {
				// block 지정 후 alt + s + w + enter
				// try 범위 블럭 지정
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		
		
	} // end main

} // end class

















