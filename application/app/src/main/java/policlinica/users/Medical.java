package policlinica.users;

import java.sql.ResultSet;
import policlinica.Pacient;
import policlinica.Serviciu;

import java.util.ArrayList;
import java.sql.*;

public class Medical extends User {

	public Medical(ResultSet result) {
		super(result);
	}

	public Medical(String nrContract) {
		super(nrContract);
	}

	public Medical() {
		super();
	}

	public ArrayList<Pacient> getListaPacienti() {
		ArrayList<Pacient> lista = new ArrayList<Pacient>();
		ResultSet rs = executeSelect("Select * from Pacient");
		try {
			while (rs.next()) {
				lista.add(new Pacient(rs.getString("nrPacient"), rs.getString("nume"), rs.getString("prenume")));
			}
		} catch (Exception e) {
			printSqlErrorMessage("getListaPacienti,medical");
		}
		return lista;
	}
	public void setListaPacienti(ArrayList<Serviciu> lista, String nrProgramare) {
		deleteAllServiciuPerProgramare(nrProgramare);
		for (int i = 0 ; i < lista.size(); i ++) {
			
		}
	}
//	public Boolean insertServiciuPerProgramare(Serviciu serviciu) {
//		String comanda = "Insert into ServiciuPerProgramare(nrServiciu , nrProgramare,rezultat) values" +
//						"(" + serviciu.getNrServiciu() + ""
//		rs = executeUpdate("Insert into ServiciuPerProgramare(nrServiciu , nrProgramare) values (" + itterator
//				+ "," + p.getString("nrProgramare") + ");");
//	}
	public Boolean deleteAllServiciuPerProgramare(String nrProgramare) {
		String comanda = "delete from ServiciuPerProgramare where nrProgramare = " +  nrProgramare;
		return executeUpdate(comanda);
	}

}
