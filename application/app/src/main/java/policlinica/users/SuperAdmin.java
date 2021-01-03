package policlinica.users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperAdmin extends ResurseUmane {
	public SuperAdmin(int nrContract, String username) {
		super(nrContract, username);
	}
	public Boolean setContract(int nrContract,String nume,String prenume,String salariu,String nrOre,String functie,String nrUnitate) {
		String comanda = getStringSetContract(nrContract, nume, prenume, salariu, nrOre, functie, nrUnitate);
		executeStatement(comanda);
		return true;
	}
	public Boolean setDateAngajat(long angajatCNP,String adresa,String nrTelefon,String email,String iban,String nrContract,String dataAngajarii) {
		String comanda = GetStringSetDateAngajat(angajatCNP, adresa, nrTelefon, email, iban, nrContract, dataAngajarii);
		executeStatement(comanda);
		return true;
	}
	public void setUserData(int nrContract,String username,String pwd,String tip) {
		String comanda = getStringSetUserData(nrContract, username, pwd, tip);
		executeStatement(comanda);
	}
	
	
	protected String getStringSetContract(int nrContract,String nume,String prenume,String salariu,String nrOre,String functie,String nrUnitate) {
		ResultSet result = getDataById(nrContract);
		String comanda = "Update Contract Set";
		comanda += "   nume = " + nume;
		comanda += " , prenume = " + prenume;
		comanda += " , salariu = " + salariu;
		comanda += " , nrOre = " + nrOre;
		comanda += " , functie = " + functie;
		comanda += " , nrUnitate = " + nrUnitate;
		comanda += "   where nrContract = " + Integer.toString(nrContract) + " ;" ;
		return comanda;
	}
	protected String GetStringSetDateAngajat(long angajatCNP,String adresa,String nrTelefon,String email,String iban,String nrContract,String dataAngajarii) {
		String comanda = "Update DateAngajat Set";
		comanda += "   adresa = " + adresa;
		comanda += " , nrTelefon = " + nrTelefon;
		comanda += " , email = " + email;
		comanda += " , iban = " + iban;
		comanda += " , nrContract = " + nrContract;
		comanda += " , dataAngajarii = " + dataAngajarii;
		comanda += "   where angajatCNP = " + Long.toString(angajatCNP) + " ;" ;
		return comanda;
	}
	protected String  getStringSetUserData(int nrContract,String username,String pwd,String tip) {
		String comanda = "Update UserData Set";
		comanda += "   username = " + username;
		comanda += " , pwd = " + pwd;
		comanda += " , tip = " + tip;
		comanda += "   where nrContract = " + Long.toString(nrContract) + " ;" ;
		return comanda;
	}
}
