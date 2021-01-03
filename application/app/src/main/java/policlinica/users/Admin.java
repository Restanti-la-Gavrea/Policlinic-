package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends SuperAdmin {

	public Admin(int nrContract, String username) {
		super(nrContract, username);
	}
	public Boolean setContract(int nrContract,String nume,String prenume,String salariu,String nrOre,String functie,String nrUnitate) {
		try {
			ResultSet result = getDataById(nrContract);
				if (result.getString("tip").equals("user")) {
					String comanda = getStringSetContract(nrContract, nume, prenume, salariu, nrOre, functie, nrUnitate);
					executeStatement(comanda);
					return true;
				}
		}catch (SQLException e) {
		      printSqlErrorMessage(e);
	    }
		return false;
	}
	public Boolean setDateAngajat(long angajatCNP,String adresa,String nrTelefon,String email,String iban,String nrContract,String dataAngajarii) {
		try {
			ResultSet result = getDataByCNP(angajatCNP);
				if (result.getString("tip").equals("user")) {
					String comanda = GetStringSetDateAngajat(angajatCNP, adresa, nrTelefon, email, iban, nrContract, dataAngajarii);
					executeStatement(comanda);
					return true;
				}
		}catch (SQLException e) {
		      printSqlErrorMessage(e);
	    }
		return false;
	}
	public void setUserData(int nrContract,String username,String pwd,String tip) {
		String comanda = getStringSetUserData(nrContract, username, pwd, tip);
		executeStatement(comanda);
	}
	
}
