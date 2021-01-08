package policlinica.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Economic extends User {

	public Economic(ResultSet result) {
		super(result);
	}
	public Economic() {
		super();
	}
	private final static String URL = "jdbc:mysql://localhost/";
	private final static String DB_NAME = "policlinica";
	private final static String USER = "?user=root";
	private final static String PASSWORD = "&password=1234";

	private String makeStatement(int month, int year) {
		return "select suma from platimedic where MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year + ";";
	}
	
	private String makeStatementv2(int month, int year, String nrContract) {
		return "select suma from platimedic where MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year
				+ " and nrCMedic = " + nrContract + ";";
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
		// returneaza resultSet-ul statementului dat
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
		// salariu angajat, trebuie gasit un mod de adaptare cu medicul astfel incat orice user care e angajat sa poata apela metoda,idei?
		public double[] getSalariu(String nrContract, int nrMonths) throws SQLException {
			double[] salariu = new double[nrMonths];
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int i = 0;
			Connection c = setup();
			ResultSet rs;
			rs = getResult("Select salariu,functie from Contract where nrContract = " + nrContract + ";", c);
			while (i < nrMonths) {
				rs = getResult("Select salariu,functie from Contract where nrContract = " + nrContract + ";", c);
				if(!rs.next()) break;
				salariu[i] = rs.getInt("salariu");
				i++;
			}
			//partea asta, trebuie cumva adaptat pt medic ca sa pot pune metoda in user, idei?
			if ( rs.next() && rs.getString("functie") == "m") {
				double[] aux = profitMedic(nrContract, nrMonths);
				rs = getResult("Select comision from Medic where nrContract =  " + nrContract + ";", c);
				int comision = 0;
				if(rs.next()) { 
					comision = rs.getInt("comision");
				}
				for (i = 0; i < nrMonths; i++) {
					aux[i] += salariu[i];
					salariu[i] += (aux[i] * comision) / (100 - comision);
				}
			}
			return salariu; 
		}
		// returneaza o lista cu profitul realizat in fiecare luna incepand cu luna curenta 
		public double[] getProfit(int nrOfMonths) throws SQLException {
			double[] profitPoliclinica = new double[nrOfMonths];
			Connection c = setup();
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int i = 0;
			ResultSet rs;
			while (i < nrOfMonths) {
				rs = getResult(makeStatement(month, year), c);
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
		// profitul adus de Medicul X in ultimele nrMonths luni
		public double[] profitMedic(String nrContract, int nrMonths) throws SQLException {
			double[] profit = new double[nrMonths];
			Connection c = setup();
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int i = 0;
			ResultSet rs;
			while (i < nrMonths) {
				rs = getResult(makeStatementv2(month, year, nrContract), c);
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
		// O matrice cu coloanele fiind nrContract, liniile luna , reprezentand profitul pe care l-a adus fiecare medic
		public double[][] getListaProfitGeneratDeMedici(int nrMonths) throws SQLException {
			double[][] result = new double[0][0];
			Connection c = setup();
			ResultSet rs = getResult("Select Count(*) As Count from Medic", c);
			if(rs.next())
				result = new double[nrMonths][rs.getInt("Count")];
			rs = getResult("Select nrContract from Medic", c);
			int j = 0;
			while (rs.next()) {
				double[] auxresult = profitMedic(rs.getString("nrContract"), nrMonths);
				for (int i = 0; i < nrMonths; i++) {
					result[i][j] = auxresult[i];
				}
				j++;
			}
			return result;
		}
		// Profitul adus de medicul X la fiecare specialitate
		public double[][] profitPerSpecialitate(String nrContract, int nrMonths) throws SQLException {
			double[][] profit;
			Connection c = setup();
			ResultSet rs = getResult("Select Count(*) As Count from Specialitate", c);
			
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int i = 0;
			if(rs.next())
				profit = new double[nrMonths][rs.getInt("Count") + 1];
			else 
				profit = null;
			double[] profitTotalMedic = getSalariu(nrContract, nrMonths);
			for (i = 0; i < nrMonths; i++) {
				profit[i][0] = profitTotalMedic[i];
			}
			i = 0;
			
			while (i < nrMonths) {
				
				rs = getResult("Select nrSpecialitate from Specialitate", c);
				int j = 1; 
				while (rs.next()) {
					ResultSet aux = getResult(
									"Select pret from Serviciu inner join ServiciuPerProgramare inner join Programare on Serviciu.nrServiciu"
									+ "= ServiciuperProgramare.nrServiciu and ServiciuperProgramare.nrProgramare = Programare.nrProgramare where nrCMedic = "
									+ nrContract + " and  MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year + " and Serviciu.nrSpecialitate = " + 
									rs.getString("nrSpecialitate") + ";", c);
					
					while(aux.next()) {
						profit[i][j] += aux.getInt("pret");
					}
				}
				j ++;
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
		// profitul pe care l-a adus medicul X la fiecare unitate, pe prima coloana fiind profitul total
		public double[][] profitPerUnitate(String nrContract, int nrMonths) throws SQLException{
			double[][] result; 
			Connection c = setup(); 
			ResultSet rs = getResult("Select count(*) as Count from UnitateMedicala",c);
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int i = 0;
			if(rs.next())
				result = new double[nrMonths][rs.getInt("Count")+1]; 
			else 
				result = null;
			double[] profitTotalMedic = getSalariu(nrContract, nrMonths);
			for (i = 0; i < nrMonths; i++) {
				result[i][0] = profitTotalMedic[i];
			}
			i = 0;
			i = 0;
			while (i < nrMonths) {
				
				rs = getResult("Select nrUnitate from UnitateMedicala",c); 
				int j = 1; 
				while (rs.next()) {
					String b = rs.getString("nrUnitate");
					ResultSet aux = getResult(
									"Select suma from Cabinet inner join Programare inner join Plata on Cabinet.nrCabinet"
									+ "= Programare.nrCabinet and Programare.nrProgramare = Plata.nrProgramare where nrCMedic = "
									+ nrContract + " and  MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year + " and Cabinet.nrUnitate = " + 
									b + ";", c);	
					while(aux.next()) {
						int suma = aux.getInt("suma");
						result[i][j] +=suma ;
						if(i == 0)
							System.out.println(suma);
					}
				}
				j ++;
				if (month != 1) {
					month--;
				} else {
					year--;
					month = 12;
				}
				i++;
			}
			return result; 
		}
}
