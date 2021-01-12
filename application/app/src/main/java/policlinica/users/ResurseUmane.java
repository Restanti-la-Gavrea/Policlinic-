package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.AngajatTableItem;
import policlinica.calendar.CalendarSaptamanal;
import policlinica.calendar.Day;

public class ResurseUmane extends User {

	public ResurseUmane(ResultSet result) {
		super(result);
	}

	public ResurseUmane() {
		super();
	}

	public ArrayList<AngajatTableItem> getArrayOfDateAngajati() {
		ArrayList<AngajatTableItem> listaAngajati = new ArrayList<>();
		ResultSet result = getDateAngajati("", "", "");
		try {
			while (result.next()) {
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

	public Boolean setConcediu(User user, Day dayin, Day dayout) {
		String comanda = "";
		try {
			if (getConcediu(user.getNrContract()).next())
			{
				comanda = getStringUpdateConcediu(user, dayin, dayout);
			}else {
				comanda = getStringInsertConcediu(user, dayout, dayout);
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(comanda);
		return executeUpdate(comanda);
	}
	public Boolean setOrarGeneric(CalendarSaptamanal calendar) {
		Boolean t = true;
		for (int i = 0 ; i < 7;i++) {
			t = t && setDayOfOrarGeneric(calendar.getDay(i));
		}
		return t;
	}

	public Boolean setDayOfOrarGeneric(Day day) {
		String comanda = null;
		if (day.getIntervalorar().equals("liber") || day.getIntervalorar().equals("")) {
			comanda = "delete from orargeneric " + getOrarGenericCondition(day);
			return executeUpdate(comanda);
		}
		ResultSet result = getOrarGeneric(day.getNrContract(), day.getNameDayOfWeek());

		try {
			if (result.next()) {
				comanda = getStringUpdateOrarGeneric(day);
			} else {
				comanda = getStringInsertOrarGeneric(day);
			}
		} catch (SQLException e) {
			System.out.println("probleme in set Orar Generic");
			return false;
		}
		return executeUpdate(comanda);
	}

	public Boolean setOrarSpecific(Day day) {
		String comanda = null;
		if (day.getIntervalorar().equals("liber") || day.getIntervalorar().equals("")) {
			comanda = "delete from orarspecific " + getOrarSpecificCondition(day);
			return executeUpdate(comanda);
		}
		ResultSet result = getOrarSpecific(day.getNrContract(), day.getStringDate());
		try {
			if (result.next()) {
				comanda = getStringUpdateOrarSpecific(day);
			} else {
				comanda = getStringInsertOrarSpecific(day);
			}
		} catch (SQLException e) {
			System.out.println("probleme in set Orar Specific");
			return false;
		}
		return executeUpdate(comanda);
	}

	public ResultSet getDateAngajati(String nume, String prenume, String functie) {
		String comanda = "Select * from DatePersonale ";
		Boolean conditie = false;// verifica daca a fost deja impus o conditie
		if (nume != null && nume != "") {

			comanda += "where nume = '" + nume + "'";
			conditie = true;
		}
		if (prenume != null && prenume != "") {
			if (conditie) {
				comanda += " and ";
			} else
				comanda += "where ";

			comanda += "prenume = '" + prenume + "'";
			conditie = true;
		}
		if (functie != null && functie != "") {
			if (conditie) {
				comanda += " and ";
			} else
				comanda += "where ";
			comanda += "functie = '" + functie + "'";
			conditie = true;
		}
		comanda += ";";
		return executeSelect(comanda);
	}

	public ResultSet getOrarGeneric(String nrContract, String dayOfWeek) {
		String comanda = "SELECT * FROM orar where nrcontract = " + nrContract + " and ziSaptamana = '" + dayOfWeek
				+ "';";
		return executeSelect(comanda);
	}

	public ResultSet getOrarSpecific(String nrContract, String date) {
		String comanda = "SELECT * FROM ExceptiiOrarMedic where nrcontract = " + nrContract
				+ " and ziCalendaristica = '" + date + "';";
		return executeSelect(comanda);
	}

	public ResultSet getConcediu(String nrContract) {
		String comanda = "SELECT * FROM Concediu where nrcontract = " + nrContract + ";";
		return executeSelect(comanda);
	}

	protected String getStringUpdateConcediu(User user, Day dayin, Day dayout) {
		String comanda = "Update Concediu Set";
		comanda += "   dataIncepere = '" + dayin.getStringDate();
		comanda += "' , dataTerminare = '" + dayout.getStringDate();
		comanda += "'   where nrContract = " + user.getNrContract() + " ;";
		return comanda;
	}
	protected String getStringInsertConcediu(User user, Day dayin, Day dayout) {
		String comanda = "Insert into Concediu (nrContract,dataIncepere ,dataTerminare)  values ";
		comanda += "(" + user.getNrContract() + ",";
		comanda += "'" + dayin.getStringDate() + "',";
		comanda += "'" + dayout.getStringDate() + "');";
		return comanda;
	}

	protected String getStringUpdateOrarGeneric(Day day) {
		String comanda = "Update OrarGeneric Set";
		comanda += "   intervalOrar = '" + day.getIntervalorar();
		comanda += "' , nrUnitate = " + day.getNrUnitate();
		comanda += getOrarGenericCondition(day);
		return comanda;
	}

	protected String getStringInsertOrarGeneric(Day day) {
		String comanda = "Insert into orarGeneric (ziSaptamana,intervalOrar ,nrunitate,nrcontract)  values ";
		comanda += "('" + day.getNameDayOfWeek() + "',";
		comanda += "'" + day.getIntervalorar() + "',";
		comanda += day.getNrUnitate() + ",";
		comanda += day.getNrContract() + ");";
		return comanda;
	}

	protected String getStringUpdateOrarSpecific(Day day) {
		String comanda = "Update OrarSpecific Set";
		comanda += "   intervalOrar = '" + day.getIntervalorar();
		comanda += "' , nrUnitate = " + day.getNrUnitate();
		comanda += getOrarSpecificCondition(day);
		return comanda;
	}

	protected String getStringInsertOrarSpecific(Day day) {
		String comanda = "Insert into orarSpecific (ziCalendaristica,intervalOrar ,nrunitate,nrcontract)  values ";
		comanda += "('" + day.getStringDate() + "',";
		comanda += "'" + day.getIntervalorar() + "',";
		comanda += day.getNrUnitate() + ",";
		comanda += day.getNrContract() + ");";
		return comanda;
	}
	protected  String getOrarSpecificCondition(Day day) {
		return "   where ziCalendaristica = '" + day.getStringDate()
			+ "' and nrContract = " + day.getNrContract() + " ;";
	}
	protected  String getOrarGenericCondition(Day day) {
		return "   where ziSaptamana = '" + day.getNameDayOfWeek() +
		 "' and nrContract = " + day.getNrContract() + " ;";
	}

}
