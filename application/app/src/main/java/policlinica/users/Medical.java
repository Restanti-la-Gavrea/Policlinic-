package policlinica.users;

import java.sql.ResultSet;
import policlinica.Pacient;
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

}
