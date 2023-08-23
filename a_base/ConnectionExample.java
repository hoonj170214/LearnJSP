package a_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionExample {

	public static void main(String[] args) {
		// DataBase 인증된 사용자 정보로 Session
		// 정보를 저장하는 객체
		Connection conn = null;
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sqldb";
		String user = "root";
		String password = "12345";
		
		try {
			Class.forName(driver);
			System.out.println("Driver Class load 완료");
			// Connection을 받아오는 첫번째 방법
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("conn-1 : " + conn);
			// 외부 리소스 자원 해제
			conn.close();
			
			// 2번째 방법
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sqldb?user=root&password=12345"
			);
			System.out.println("conn - 2 : " + conn);
			conn.close();
			
			// 3번째 방법
			// java.util.Properties import
			// import 단축키 ctrl + shift + o
			Properties prop = new Properties();
			prop.setProperty("user", user);
			prop.setProperty("password", password);
			System.out.println(prop.getProperty("user"));
			System.out.println(prop.getProperty("password"));
			conn = DriverManager.getConnection(url,prop);
			System.out.println("conn - 3 : " + conn);
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// syso + ctrl+ space
			System.out.println("Driver Class를 찾을 수 없음");
		} catch (SQLException e) {
			System.out.println("DB 연결 정보 오류 : " + e.getMessage());
		}
		
	}

}




