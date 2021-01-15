package policlinica.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;
import policlinica.Specialitate;
import policlinica.Serviciu;
import policlinica.Programare;
import policlinica.MedicAux;
import policlinica.Pacient;
import policlinica.calendar.*;

public class Receptioner extends Medical {

	public Receptioner(ResultSet result) {
		super(result);
	}

	public Receptioner() {
		super();
	}

	public ArrayList<Specialitate> getSpecialitati() {
		ArrayList<Specialitate> lista = new ArrayList<Specialitate>();
		ResultSet rs = executeSelect("Select * from Specialitate");
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
			boolean p = executeUpdate("Insert into Pacient Values(" + cnp + ",\"" + Nume + "\",\"" + Prenume + "\");");
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
					"Select Serviciu.pret,nrServiviu,nrCMedic as pret from Serviciu inner join ServiciuPerProgramare on Serviciu.nrServiciu = "
							+ "ServiciuPerProgramare.nrServiciu where nrProgramre = " + nrProgramare + ";");
			while (rs.next()) {
				ResultSet nrCMedic = executeSelect(
						"Select nrCMedic from Programare where nrProgramare = " + nrProgramare + ";");
				ResultSet aux = executeSelect("Select newPret from ServiciuCustom where ServiciuCustom.nrServiciu = "
						+ rs.getString("nrServiciu") + " and nrContract = " + nrCMedic.getString("nrCMedic") + ";");
				if (aux.next()) 
					sum += aux.getInt("newPret");
				 else
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
	public String[] durateServiciiProgramare(String nrProgramare) {
		ResultSet rs = executeSelect(
				"Select Count(*) as count from ServiciuPerProgramare inner join  Programare on ServiciuPerProgramare.nrProgramare = Programare.nrProgramare where"
						+ " Programare.nrProgramare = " + nrProgramare + ";");
		try {
			String[] durate = new String[rs.getInt("count")];
			rs = executeSelect(
					"Select durata from ServiciuPerProgramare inner join  Programare on ServiciuPerProgramare.nrProgramare = Programare.nrProgramare where"
							+ " Programare.nrProgramare = " + nrProgramare + ";");
			int i = 0;
			while (rs.next()) {
				durate[i] = rs.getString("durata");
			}
			return durate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteProgramare(String nrProgramare) {
		return executeUpdate("Delete from Programare where nrProgramare = " + nrProgramare + ";");
	}

	public boolean creeareProgramare(Programare p) {
		if (!registerPatient(p.getNumePacient(), p.getPrenumePacient(), p.getCnpPacient()))
			return false;
		boolean rs = executeUpdate(
				"Insert into Programare(dataP,ora,nrCMedic,nrPacient) " + "values (\"" + p.getDay().getStringDate() + "\",\""
						+ p.getDay().getIntervalorar() + "\"," + p.getNrCMedic() + "," + p.getCnpPacient() + ");");
		ResultSet aux = executeSelect(
				"Select * from Programare where (Select MAX(nrProgramare) as i from Programare) =  nrProgramare;");
		
		if (!rs)
			return false;
		setListaServiciuPerProgramare(p.getServicii(), p.getNrProgramare());
		try {
			aux.next(); 
			ResultSet aux1 = executeSelect("Select nrProgramare,ora from Programare where dataP = "
					+ aux.getString("dataP") + " and nrCMedic = " + aux.getString("nrCMedic") + ";");
			String intervale = "";
			while (aux1.next()) {
				String[] durate = durateServiciiProgramare(aux1.getString("nrProgramare"));
				intervale += " " + IntervalOrar.formeazaInterval(aux1.getString("ora"), durate);
			}
			IntervalOrar myInterval = new IntervalOrar(intervale);
			if (myInterval.isIntercalat()) {
				deleteAllServiciuPerProgramare(aux1.getString("nrProgramare"));
				deleteProgramare(aux1.getString("nrProgramare"));
			}
			aux1 = executeSelect("Select nrProgramare,ora from Programare where dataP = "
					+ aux.getString("dataP") + " and nrCabinet = " + aux.getString("nrCabinet") + ";");
			intervale = "";
			while (aux1.next()) {
				String[] durate = durateServiciiProgramare(aux1.getString("nrProgramare"));
				intervale += " " + IntervalOrar.formeazaInterval(aux1.getString("ora"), durate);
			}
			System.out.println(" intai aici"); 
			myInterval = new IntervalOrar(intervale);
			if (myInterval.isIntercalat()) {
				System.out.println("aici"); 
				deleteAllServiciuPerProgramare(aux1.getString("nrProgramare"));
				deleteProgramare(aux1.getString("nrProgramare"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
