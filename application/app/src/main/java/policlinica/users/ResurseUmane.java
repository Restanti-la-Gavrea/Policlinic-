package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.AngajatTableItem;
import policlinica.calendar.Day;

public class ResurseUmane extends User{

	
	public ResurseUmane(ResultSet result) {
		super(result);
	}
	public ResurseUmane() {
		super();
	}
	public ArrayList <AngajatTableItem >getArrayOfDateAngajati(){
		ArrayList <AngajatTableItem >listaAngajati = new ArrayList<>();
		ResultSet result = getDateAngajati("","","");
		try {
			while(result.next()) {
				AngajatTableItem angajat = new AngajatTableItem();
				User user = new User(result);
				angajat.getAngajatTableItemFromUser(user);
				listaAngajati.add(angajat);
			}
		} catch (SQLException e) {
			System.out.println("Eroare in getArrayOfDateAngajati");
		}
		return listaAngajati;
		
	}
	public Boolean setConcediu(User user ,Day dayin,Day dayout) {
		return executeUpdate(getStringSetConcediu(user, dayout, dayout));
	}
	public Boolean setOrarGeneric(Day day,String orar) {
		String comanda = "Update OrarGeneric Set";
		comanda += "   intervalOrar = '" + day.getIntervalorar();
		comanda += "' , intervalOrar = '" + day.getNumeUnitate();
		comanda += "   where ziSaptamana = '" + day.getNameDayOfWeek() + "' " ;
		comanda += " and nrUnitate = " + day.getNrContract() + " ;" ;
		return executeUpdate(comanda);
	}

	
	public ResultSet getDateAngajati(String nume ,String prenume,String functie){
		String comanda = "Select * from DatePersonale ";
		Boolean conditie = false;//verifica daca a fost deja impus o conditie
		if (nume != null && nume != "")
		{
			
			comanda += "where nume = '" + nume + "'";
			conditie = true;
		}
		if (prenume != null && prenume != "")
		{
			if (conditie) {
				comanda += " and ";
			}
			else 
				comanda += "where "; 
				
			comanda += "prenume = '" + prenume + "'";
			conditie = true;
		}
		if (functie != null && functie != "")
		{
			if (conditie) {
				comanda += " and ";
			}
			else 
				comanda += "where "; 
			comanda += "functie = '" + functie + "'";
			conditie = true;
		}
		comanda += ";";	
		return executeSelect(comanda);
	}
	public ResultSet getOrarGeneric(String nrContract,String dayOfWeek) {
		String comanda = "SELECT * FROM orar where nrcontract = " + nrContract + 
				" and ziSaptamana = '" + dayOfWeek + "';";
		return executeSelect(comanda);
	}
	public ResultSet getOrarSpecific(String nrContract,String date) {
		String comanda = "SELECT * FROM ExceptiiOrarMedic where nrcontract = " + nrContract + 
				" and ziCalendaristica = '" + date + "';";
		return executeSelect(comanda);
	}
	public ResultSet getConcediu(String nrContract) {
		String comanda = "SELECT * FROM Concediu where nrcontract = " + nrContract +";";
		return executeSelect(comanda);
	}
	protected String getStringSetConcediu(User user,Day dayin,Day dayout) {
		String comanda = "Update Concediu Set";
		comanda += "   dataIncepere = '" + dayin.getStringDate();
		comanda += "' , dataTerminare = '" + dayout.getStringDate();
		comanda += "'   where nrContract = " + user.getNrContract() + " ;" ;
		return comanda;
	}


}
