package policlinica.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	//Ia datele utilizatorului actual
	public ResultSet getUserData() {
		return getDataById(Integer.toString(nrContract));
	}
	
	//Ia toate datele utilizatorului curent
	protected ResultSet getDataById(String nrContract){
		String comanda = "Select * from Datepersonale where nrContract = " +
							nrContract + ";";
		return executeSelect(comanda);
	}
	protected ResultSet getDataByCNP(String cnp){
		String comanda = "Select * from Datepersonale where angajatCNP = " +
							cnp + ";";
		return executeSelect(comanda);
	}
	private Connection setup(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
			ex.printStackTrace(System.err);
		}
		try {
			return DriverManager.getConnection(URL + DB_NAME , USER , PASSWORD);
		}
		catch(Exception e) {
			printSqlErrorMessage(e);
		}
		return null;
		
	}
	protected ResultSet executeSelect(String selectString){
		try {
			PreparedStatement s = connection.prepareStatement(selectString);
			return s.executeQuery();
		}
		catch(SQLException e){
			printSqlErrorMessage(e);
		}
		return null;
	}
	protected Boolean executeUpdate(String updateString){
		try {
			PreparedStatement s = connection.prepareStatement(updateString);
		   s.executeUpdate();
		   return true;
		}
		catch(SQLException e){
			printSqlErrorMessage(e);
			return false;
		}
	}
	protected void printSqlErrorMessage(Exception e) {
		System.out.println("SQLException: " + e.getMessage());
	    System.out.println("SQLState: " + ((SQLException) e).getSQLState());
	    System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
	}
}
