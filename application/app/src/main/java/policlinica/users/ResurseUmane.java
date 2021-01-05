package policlinica.users;

import java.sql.ResultSet;

public class ResurseUmane extends User{

	public ResurseUmane(int nrContract, String username) {
		super(nrContract, username);
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
	
}
