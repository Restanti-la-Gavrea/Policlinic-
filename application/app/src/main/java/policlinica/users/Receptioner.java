package policlinica.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;
import policlinica.Specialitate;
import policlinica.Serviciu;

public class Receptioner extends Medical {

	public Receptioner(ResultSet result) {
		super(result);
	}

	public Receptioner() {
		super();
	}

	public ArrayList<Specialitate> getSpecialitati() {
		ArrayList<Specialitate> lista = new ArrayList<Specialitate>();
		ResultSet rs = executeSelect("Select * from Specialitati");
		try {
			while (rs.next()) {
				lista.add(new Specialitate(rs.getString("nrSpecialitate"), rs.getString("nume")));
			}
		} catch (Exception e) {
			printSqlErrorMessage("Receptioner, get specialitati ");
		}
		return lista;
	}

	public ArrayList<Serviciu> getServicii(String nrSpecialitate) {
		ArrayList<Serviciu> lista = new ArrayList<Serviciu>();
		ResultSet rs = executeSelect("Select nrServiciu from Serviciu where nrSpecialitate = NULL or nrSpecialitate = "
				+ nrSpecialitate + ";");
		try {
			while (rs.next()) {
				lista.add(new Serviciu(rs.getString("nrServiciu")));
			}
		} catch (Exception e) {
			printSqlErrorMessage("Receptioner, get specialitati ");
		}
		return lista;
	}

	public ArrayList<Medic> getListaMedici(String nrSpecialitate) {
		ArrayList<Medic> lista = new ArrayList<Medic>();
		ResultSet rs = executeSelect(
				"Select nrContract from Medic inner join SpecialitateMedic inner join Specialitate "
						+ "on Specialitate.nrSpecialitate = SpecialitateMedic.nrSpecialitate and SpecialitateMedic.nrContract = Medic.nrContract  where Specilaitate.nrSpecialitate = "
						+ nrSpecialitate + ";");
		try {
			while (rs.next()) {
				lista.add(new Medic(rs.getString("nrContract")));
			}
		} catch (Exception e) {
			printSqlErrorMessage("Receptioner, get specialitati ");
		}
		return lista;
	}

	public boolean registerPatient(String Nume, String Prenume, String cnp) {
		try {
			ResultSet rs = executeSelect("Select nrPacient from Pacient");
			while (rs.next()) {
				if (rs.getString("NrPacient").equals(cnp)) {
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
			ResultSet rs = executeSelect(
					"Select Serviciu.pret as pret from Serviciu inner join ServiciuPerProgramare on Serviciu.nrServiciu = "
							+ "ServiciuPerProgramare.nrServiciu where nrProgramre = " + nrProgramare + ";");
			while (rs.next()) {
				sum += rs.getInt("pret");
			}
			executeUpdate("Insert into Plata(suma,ziPlata,nrProgramare) values (" + sum + "," + Calendar.getInstance()
					+ "," + nrProgramare + ");");
			return true;
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
			return false;
		}
	}

	public boolean creeareProgramare(String numePacient, String prenumePacient, String cnp, String[] listaServicii,
			String nrCMedic, String data, String ora) {
		if (!registerPatient(numePacient, prenumePacient, cnp))
			return false;
		boolean rs = executeUpdate("Insert into Programare(dataP,ora,nrCMedic,nrPacient) " + "values (" + data + ","
				+ ora + "," + nrCMedic + "," + cnp + ");");
		ResultSet p = executeSelect("Select last_insert_rowid() as nrProgramare;");
		if (rs)
			return false;
		for (String itterator : listaServicii) {
			try {
				rs = executeUpdate("Insert into ServiciuPerProgramare(nrServiciu , nrProgramare) values (" + itterator
						+ "," + p.getString("nrProgramare") + ");");
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
