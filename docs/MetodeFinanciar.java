package F;

import java.sql.*;
import java.util.Calendar;

public class Main {
	private final static String URL = "jdbc:mysql://localhost/";
	private final static String DB_NAME = "policlinica";
	private final static String USER = "?user=root";
	private final static String PASSWORD = "&password=1234";

	private String makeStatement(int month, int year) {
		return "select suma from platimedic where MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year + ";";
	}

	private String makeStatementv2(int month, int year, int nrContract) {
		return "select suma from platimedic where MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year
				+ "and nrCMedic = " + nrContract + ";";
	}

	// Setup la conexiune + returneaza conexiunea , arunca exceptie
	private Connection setup() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
			ex.printStackTrace(System.err);
		}
		try {
			return DriverManager.getConnection(URL + DB_NAME + USER + PASSWORD);
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
			return null;
		}
	}

	private ResultSet getResult(String statement, Connection c) {
		try {
			PreparedStatement s;
			s = c.prepareStatement(statement);
			return s.executeQuery();
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
			return null;
		}
	}

	public double[] getProfit(int nrOfMonths) throws SQLException{
		double[] profitPoliclinica = new double[nrOfMonths];
		Connection c = setup();
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int i = 0;
		ResultSet rs;
		while (i < nrOfMonths) {
			rs = getResult(makeStatement(month, year), c);
			// ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				profitPoliclinica[i] += (rs.getInt("suma") * (100 - rs.getInt("comision"))) / 100;
			}
			rs = getResult("Select salariu from Contract", c);
			while (rs.next()) {
				profitPoliclinica[i] -= rs.getInt("salariu");
			}
			if (month != 1) {
				month--;
			} else {
				year--;
				month = 12;
			}
			i++;
		}
		return profitPoliclinica;
	}

	public double[] profitMedic(int nrContract, int nrMonths) throws SQLException {
		double[] profit = new double[nrMonths];
		Connection c = setup();
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int i = 0;
		ResultSet rs;
		while (i < nrMonths) {
			rs = getResult(makeStatementv2(month, year, nrContract), c);
			// ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				profit[i] += (rs.getInt("suma") * (100 - rs.getInt("comision"))) / 100;
			}
			rs = getResult("Select salariu from Contract where nrContract =" + nrContract + ";", c);
			while (rs.next()) {
				profit[i] -= rs.getInt("salariu");
			}
			if (month != 1) {
				month--;
			} else {
				year--;
				month = 12;
			}
			i++;
		}
		return profit;
	}

	public double[][] getListaProfitGeneratDeMedici(int nrMonths) throws SQLException {
		double[][] result;
		Connection c = setup();
		ResultSet rs = getResult("Select Count(*) As Count from Medic", c);
		result = new double[nrMonths][rs.getInt("Count")];
		rs = getResult("Select nrContract from Medic", c);
		int j = 0;
		while (rs.next()) {
			double[] auxresult = profitMedic(rs.getInt("nrContract"), nrMonths);
			for (int i = 0; i < nrMonths; i++) {
				result[i][j] = auxresult[i];
			}
			j++;
		}
		return result;
	}

	public double[] getSalariu(int nrContract, int nrMonths) throws SQLException{
		double[] salariu = new double[nrMonths]; 
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int i = 0;
		Connection c = setup();
		ResultSet rs;
		rs = getResult("Select salariu,functie from Contract where nrContract = " + nrContract + ";",c); 
		while (i < nrMonths) {
			rs = getResult("Select salariu,functie from Contract where nrContract = " + nrContract + ";",c); 
			salariu[i]= rs.getInt("salariu");
			i++;
		}
		if(rs.getString("functie") == "m"){
			double[] aux = profitMedic(nrContract,nrMonths);
			rs = getResult("Select comision from Medic where nrContract =  " + nrContract + ";",c);
			int comision = rs.getInt("comision"); 
			for(i = 0; i < nrMonths; i ++) {
				aux[i] += salariu[i];
				salariu[i] += (aux[i] * comision)/(100 - comision); 
			}
		}
		return salariu; 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main test = new Main();
		double[] a = new double[0]; 
		try {
		a = test.getProfit(12);
		}
		catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
		}
		for (double c : a) {
			System.out.print(c + " ");
		}
	}
}
