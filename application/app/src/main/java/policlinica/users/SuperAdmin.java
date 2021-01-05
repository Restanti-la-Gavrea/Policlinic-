package policlinica.users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperAdmin extends ResurseUmane {

	public SuperAdmin(ResultSet result) {
		super(result);
	}
	public SuperAdmin() {
		super();
	}
	public Boolean setContract(String nrContract,String nume,String prenume,String salariu,String nrOre,String functie,String nrUnitate) {
		String comanda = getStringSetContract(nrContract, nume, prenume, salariu, nrOre, functie, nrUnitate);
		executeUpdate(comanda);
		return true;
	}
	public Boolean setDateAngajat(String angajatCNP,String adresa,String nrTelefon,String email,String iban,String nrContract,String dataAngajarii) {
		String comanda = GetStringSetDateAngajat(angajatCNP, adresa, nrTelefon, email, iban, nrContract, dataAngajarii);
		executeUpdate(comanda);
		return true;
	}
	public Boolean setUserData(String nrContract,String username,String pwd,String tip) {
		String comanda = getStringSetUserData(nrContract, username, pwd, tip);
		return executeUpdate(comanda);
	}
	
	
	protected String getStringSetContract(String nrContract,String nume,String prenume,String salariu,String nrOre,String functie,String nrUnitate) {
		String comanda = "Update Contract Set";
		comanda += "   nume = '" + nume;
		comanda += "' , prenume = '" + prenume;
		comanda += "' , salariu = " + salariu;
		comanda += " , nrOre = " + nrOre;
		comanda += " , functie = '" + functie;
		comanda += "' , nrUnitate = " + nrUnitate;
		comanda += "   where nrContract = " + nrContract + " ;" ;
		return comanda;
	}
	protected String GetStringSetDateAngajat(String angajatCNP,String adresa,String nrTelefon,String email,String iban,String nrContract,String dataAngajarii) {
		String comanda = "Update DateAngajat Set";
		comanda += "   adresa = '" + adresa;
		comanda += "' , nrTelefon = '" + nrTelefon;
		comanda += "' , email = '" + email;
		comanda += "' , iban = '" + iban;
		comanda += "' , nrContract = " + nrContract;
		comanda += " , dataAngajarii = '" + dataAngajarii;
		comanda += "'   where angajatCNP = " + angajatCNP + " ;" ;
		return comanda;
	}
	protected String  getStringSetUserData(String nrContract,String username,String pwd,String tip) {
		String comanda = "Update UserData Set";
		comanda += "   username = '" + username;
		comanda += "' , pwd = '" + pwd;
		comanda += "' , tip = '" + tip;
		comanda += "'   where nrContract = " + nrContract + " ;" ;
		return comanda;
	}
}
