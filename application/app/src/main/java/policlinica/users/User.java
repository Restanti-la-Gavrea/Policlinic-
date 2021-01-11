package policlinica.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import policlinica.AngajatTableItem;

public class User {
	
	private final static String URL = "jdbc:mysql://localhost:3306/";
	private final static String DB_NAME = "policlinica";
	private final static String USER = "root";
	private final static String PASSWORD = "1234";
	protected Connection connection ;
	protected String nrContract;
	protected String username;
	protected String nume;
	protected String prenume;
	protected String nrTelefon;
	protected String email;
	protected String iban;
	protected String adresa;
	protected String dataAngajarii;
	protected String cnp;
	protected String password;
	protected String functie;
	protected String tip;
	
	public User() {
		this.connection = setup();
	}
	public User(String nrContract) {
		ResultSet result = this.getDataById(nrContract);
		this.getUserDataFromResultSet(result);
	}
	public User(ResultSet result) {
		this.connection = setup();
		this.getUserDataFromResultSet(result);
		
	}
	public void getUserDataFromResultSet(ResultSet result) {
		try {
			this.nrContract = result.getString("nrContract");
			this.username = result.getString("username");
			this.nume = result.getString("nume");
			this.prenume =result.getString("prenume") ;
			this.nrTelefon = result.getString("nrTelefon");
			this.email = result.getString("email");
			this.iban = result.getString("iban");
			this.adresa = result.getString("adresa");
			this.dataAngajarii = result.getString("DataAngajarii");
			this.cnp = result.getString("angajatCNP");
			this.password =result.getString("pwd");
			this.functie = result.getString("functie");
			this.tip = result.getString("tip");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public User Autentificator(String username,String password) {
		ResultSet result = getDataByUsername(username,password);
		 try {
				if (result.next()) {
				     String functie = result.getString("functie");
				     User user = null;
				     if (functie.equals("hr")) {
				    	 String tip = result.getString("tip");
				    	 if (tip.equals("Sadmin")) {
				    		 user = new SuperAdmin(result);
				    	 }else if (tip.equals("admin")) {
				    		 user = new Admin(result);
				    	 }else if (tip.equals("user")) {
				    		 user = new ResurseUmane(result);
				    	 }
				     }else if (functie.equals("eco")) {
				    		 user = new Economic(result);
				     }else if (functie.equals("rec")) {
			    		 user = new Receptioner(result);
				     }else if (functie.equals("as")) {
			    		 user = new AsistentMedical(result);
				     }else if (functie.equals("m")) {
			    		 user = new Medic(result);
				     }
				     return user;
					}
			} catch (SQLException e) {
				printSqlErrorMessage(e);
				return null;
			}
		return null;
	}
	//Ia datele utilizatorului actual
	public ResultSet getUserData() {
		return getDataById(nrContract);
	}
	
	//Ia toate datele utilizatorului curent
	protected ResultSet getDataByUsername(String username,String password){
		String comanda = "Select * from Datepersonale where username = '" +
							username + "' and pwd = '" + password + "';";
		return executeSelect(comanda);
	}
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
	public String getNrContract() {
		return nrContract;
	}
	public void setNrContract(String nrContract) {
		this.nrContract = nrContract;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	public String getNrTelefon() {
		return nrTelefon;
	}
	public void setNrTelefon(String nrTelefon) {
		this.nrTelefon = nrTelefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getDataAngajarii() {
		return dataAngajarii;
	}
	public void setDataAngajarii(String dataAngajarii) {
		this.dataAngajarii = dataAngajarii;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFunctie() {
		return functie;
	}
	public void setFunctie(String functie) {
		this.functie = functie;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
}
