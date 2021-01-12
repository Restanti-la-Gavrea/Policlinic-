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

	public Boolean setContract(String nrContract, String nume, String prenume, String salariu, String nrOre,
			String functie, String nrUnitate) {
		ResultSet result = getDataById(nrContract);
		User user = new User(nrContract);
		if (conditieAdmin(user)) {
			String comanda = getStringSetContract(nrContract, nume, prenume, salariu, nrOre, functie, nrUnitate);
			return executeUpdate(comanda);
		}
		return false;
	}

	public Boolean setDateAngajat(String angajatCNP, String adresa, String nrTelefon, String email, String iban,
			String nrContract, String dataAngajarii) {
		ResultSet result = getDataByCNP(angajatCNP);
		User user = new User(nrContract);
		if (conditieAdmin(user)) {
			String comanda = GetStringSetDateAngajat(angajatCNP, adresa, nrTelefon, email, iban, nrContract,
					dataAngajarii);
			return executeUpdate(comanda);
		}
		return false;
	}

	public Boolean setUserData(String nrContract, String username, String pwd, String tip) {
		ResultSet result = getDataById(nrContract);
		User user = new User(nrContract);
		if (conditieAdmin(user)) {
			String comanda = getStringSetUserData(nrContract, username, pwd, tip);
			return executeUpdate(comanda);
		}
		return false;
	}

	private Boolean conditieAdmin(User user) {
		if (user.nrContract.equals(this.nrContract))
			return true;
		if (user.tip.equals("user"))
			return true;
		return false;
	}

}
