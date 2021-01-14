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
	public Boolean setListaServiciuPerProgramare(ArrayList<Serviciu> lista, String nrProgramare) {
		deleteAllServiciuPerProgramare(nrProgramare);
		Boolean mere = true;
		for (int i = 0 ; i < lista.size(); i ++) {
			mere = mere && insertServiciuPerProgramare(lista.get(i), nrProgramare);
		}
		return mere;
	}
	public Boolean insertServiciuPerProgramare(Serviciu serviciu,String nrProgramare) {
		String comanda = "Insert into ServiciuPerProgramare(nrServiciu , nrProgramare,rezultat) values" +
						"(" + serviciu.getNrServiciu() + "," +nrProgramare + ",'" +
						serviciu.getRezultat() + "')";
		return executeUpdate(comanda);
	}
	public Boolean deleteAllServiciuPerProgramare(String nrProgramare) {
		String comanda = "delete from ServiciuPerProgramare where nrProgramare = " +  nrProgramare;
		return executeUpdate(comanda);
	}

}
