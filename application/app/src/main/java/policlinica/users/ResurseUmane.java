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
		ResultSet result = getOrarGeneric(day.getNrContract(), day.getNameDayOfWeek());
		String comanda = null;
		try {
			if (result.next()) {
				comanda = getStringUpdateOrarGeneric(day);
			}
			else {
				comanda = getStringInsertOrarGeneric(day);
			}
		} catch (SQLException e) {
			System.out.println("probleme in set Orar Generic");
			return false;
		}
		return executeUpdate(comanda);
	}
	public Boolean setOrarSpecific(Day day,String orar) {
		ResultSet result = getOrarSpecific(day.getNrContract(), day.getNameDayOfWeek());
		String comanda = null;
		try {
			if (result.next()) {
				comanda = getStringUpdateOrarGeneric(day);
			}
			else {
				comanda = getStringInsertOrarGeneric(day);
			}
		} catch (SQLException e) {
			System.out.println("probleme in set Orar Generic");
			return false;
		}
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
	protected String getStringUpdateOrarGeneric(Day day) {
		String comanda = "Update OrarGeneric Set";
		comanda += "   intervalOrar = '" + day.getIntervalorar();
		comanda += "' , nrUnitate = " + day.getNrUnitate();
		comanda += "   where ziSaptamana = '" + day.getNameDayOfWeek() ;
		comanda += "' and nrContract = " + day.getNrContract() + " ;" ;
		return comanda;
	}
	protected String getStringInsertOrarGeneric(Day day) {
		String comanda = "Insert into orarGeneric (ziSaptamana,intervalOrar ,nrunitate,nrcontract)  values ";
		comanda += "('" + day.getNameDayOfWeek()+ "',";
		comanda += "'" + day.getIntervalorar()+ "',";
		comanda += day.getNrUnitate()+ ",";
		comanda += day.getNrContract()+ ");";
		return comanda;
	}
	protected String getStringUpdateOrarSpecific(Day day) {
		String comanda = "Update OrarGeneric Set";
		comanda += "   intervalOrar = '" + day.getIntervalorar();
		comanda += "' , nrUnitate = " + day.getNrUnitate();
		comanda += "   where ziCalendaristica = '" + day.getStringDate() ;
		comanda += "' and nrContract = " + day.getNrContract() + " ;" ;
		return comanda;
	}
	protected String getStringInsertOrarSpecific(Day day) {
		String comanda = "Insert into orarSpecific (ziCalendaristica,intervalOrar ,nrunitate,nrcontract)  values ";
		comanda +="('" + day.getStringDate()+ "',";
		comanda +="'" + day.getIntervalorar()+ "',";
		comanda += day.getNrUnitate()+ ",";
		comanda += day.getNrContract()+ ");";
		return comanda;
	}


}
