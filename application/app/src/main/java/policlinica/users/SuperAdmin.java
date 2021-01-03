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
	
	public void searchAngajati(String nume ,String prenume,String functie) {
		String comanda = "Select * from DatePersonale where ";
		int nr = 0;
		if (nume != null && nume != "")
		{
			comanda += "nume = " + nume;
			nr++;
		}
		if (prenume != null && prenume != "")
		{
			if (nr!=0)
				comanda += " and ";
			comanda += "prenume = " + prenume;
			nr++;
		}
		if (functie != null && functie != "")
		{
			if (nr!=0)
				comanda += " and ";
			comanda += "functie = " + functie;
			nr++;
		}
		comanda+= ";";
			
	}
}
