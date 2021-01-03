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
	Connection connection ;
	int nrContract;
	String username;
	
	public User(int nrContract, String username) {
		this.connection = setup();
		this.nrContract = nrContract;
		this.username = username;
	}
	
	//Ia toate datele utilizatorului curent
	public ResultSet getDateUser(){
		String comanda = "Select * from Datepersonale where nrContract = " +
							Integer.toString(nrContract) + ";";
		return getResult(comanda);
	}
	private Connection setup(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
			ex.printStackTrace(System.err);
		}
		try {
			return DriverManager.getConnection(URL + DB_NAME + USER + PASSWORD);
		}
		catch(Exception e) {
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + ((SQLException) e).getSQLState());
		    System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
		}
		return null;
		
	}
	protected ResultSet getResult(String statement){
		try {
			PreparedStatement s = connection.prepareStatement(statement);
			return s.executeQuery();
		}
		catch(Exception e){
			System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + ((SQLException) e).getSQLState());
		    System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
		}
		return null;
	}	
}
