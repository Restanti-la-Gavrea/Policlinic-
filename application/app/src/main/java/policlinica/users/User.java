package policlinica.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	private final static String URL = "jdbc:mysql://localhost:3306/";
	private final static String DB_NAME = "policlinica";
	private final static String USER = "root";
	private final static String PASSWORD = "1234";
	
    public static void main(String[] args) {
		
		Connection c;
		
		try {
			c=DriverManager.getConnection(URL+DB_NAME, USER, PASSWORD);

			PreparedStatement s= c.prepareStatement("select * from datepersonale");
			ResultSet rez= s.executeQuery();
			while (rez.next()) {
				
				System.out.println("Numeangajat: " + rez.getString("name"));
				}
		}
		catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + ((SQLException) e).getSQLState());
		    System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
		}
		
    }
}
