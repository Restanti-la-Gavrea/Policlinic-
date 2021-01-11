package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends SuperAdmin {
	public Admin(ResultSet result) {
		super(result);
	}
	public Admin() {
		super();
	}
	public Boolean setContract(String nrContract,String nume,String prenume,String salariu,String nrOre,String functie,String nrUnitate) {
		try {
			ResultSet result = getDataById(nrContract);
				if (result.getString("tip").equals("user")) {
					String comanda = getStringSetContract(nrContract, nume, prenume, salariu, nrOre, functie, nrUnitate);
					return executeUpdate(comanda);
				}
		}catch (SQLException e) {
		      System.out.println("setContract Error");
		      return false;
	    }
		return false;
	}
	public Boolean setDateAngajat(String angajatCNP,String adresa,String nrTelefon,String email,String iban,String nrContract,String dataAngajarii) {
		try {
			ResultSet result = getDataByCNP(angajatCNP);
				if (result.getString("tip").equals("user")) {
					String comanda = GetStringSetDateAngajat(angajatCNP, adresa, nrTelefon, email, iban, nrContract, dataAngajarii);
					return executeUpdate(comanda);
				}
		}catch (SQLException e) {
		      System.err.println("setDateAngajatError");
		      return false;
	    }
		return false;
	}
	public Boolean setUserData(String nrContract,String username,String pwd,String tip) {
		String comanda = getStringSetUserData(nrContract, username, pwd, tip);
		return executeUpdate(comanda);
	}
	
}
