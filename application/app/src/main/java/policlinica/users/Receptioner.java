package policlinica.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Receptioner extends Medical {
	
	public Receptioner(ResultSet result) {
		super(result);
	}
	public Receptioner() {
		super();
	}
	
	public boolean registerPatient(String Nume, String Prenume, String cnp) {
		try {
			ResultSet rs = executeSelect("Select nrPacient from Pacient");
			while (rs.next()) {
				if(rs.getString("NrPacient").equals(cnp)) {
					return true; 
				}
			}
			boolean p = executeUpdate("Insert into Pacient Values(" + cnp + "," + Nume + "," + Prenume + ");");
			return true;
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
			return false;
		}
	}
	public boolean generarePlata(String nrProgramare) {
		int sum = 0;
		try {
			ResultSet rs = executeSelect("Select Serviciu.pret as pret from Serviciu inner join ServiciuPerProgramare on Serviciu.nrServiciu = "
					+ "ServiciuPerProgramare.nrServiciu where nrProgramre = " + nrProgramare + ";");
			while(rs.next()) {
				sum += rs.getInt("pret");
			}
			executeUpdate("Insert into Plata(suma,ziPlata,nrProgramare) values (" + sum + "," + Calendar.getInstance() + "," + nrProgramare + ");");
			return true;
		}
		catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
			return false;
		}
	}
	public boolean creeareProgramare(String numePacient, String prenumePacient, String cnp,String[] listaServicii, String nrCMedic , String data,String ora){
		if(!registerPatient(numePacient,prenumePacient,cnp))
			return false;
		boolean rs = executeUpdate("Insert into Programare(dataP,ora,nrCMedic,nrPacient) "
				+ "values (" + data + "," + ora + "," + nrCMedic + "," + cnp + ");");
		ResultSet p = executeSelect("Select last_insert_rowid() as nrProgramare;");
		if(rs) 
			return false; 
		for(String itterator : listaServicii) {
			try {
				rs = executeUpdate("Insert into ServiciuPerProgramare(nrServiciu , nrProgramare) values (" +itterator + "," + p.getString("nrProgramare") +");");
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + ((SQLException) e).getSQLState());
				System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
				return false;
			}
		}
		return true; 
	}
}
