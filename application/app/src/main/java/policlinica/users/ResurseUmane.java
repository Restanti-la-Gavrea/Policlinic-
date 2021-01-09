package policlinica.users;

import java.sql.ResultSet;

public class ResurseUmane extends User{

	
	public ResurseUmane(ResultSet result) {
		super(result);
	}
	public ResurseUmane() {
		super();
	}

	//Functia cauta toti angajati cu un anumit nume sau prenume sau functie
	//Daca nu se vrea cautare in functie de prenume de exemplu pur si simpulu la prenume 
	//se pune "" sau null
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
}
