package policlinica.users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperAdmin extends User {
	//Functia cauta toti angajati cu un anumit nume sau prenume sau functie
	//Daca nu se vrea cautare in functie de prenume de exemplu pur si simpulu la prenume 
	//se pune "" sau null
	
	public void searchDateAngajati(String nume ,String prenume,String functie) {
		String comanda = "Select * from DatePersonale where ";
		Boolean conditie = false;//verifica daca a fost deja impus o conditie
		if (nume != null && nume != "")
		{
			comanda += "nume = " + nume;
			conditie = true;
		}
		if (prenume != null && prenume != "")
		{
			if (conditie)
				comanda += " and ";
			comanda += "prenume = " + prenume;
			conditie = true;
		}
		if (functie != null && functie != "")
		{
			if (conditie)
				comanda += " and ";
			comanda += "functie = " + functie;
			conditie = true;
		}
		comanda+= ";";
			
	}
}
