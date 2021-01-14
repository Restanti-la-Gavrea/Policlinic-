package policlinica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.calendar.*;
import policlinica.users.*;

public class Test {

	public Test() {
		mere();
	}
	public void mere() {
		String ora1 = "11:00:00";
		String[] intervale = {"00:20:00","00:50:00"};
		System.out.println(IntervalOrar.formeazaInterval(ora1, intervale));
	}
	private void verificaListaRapoarte() {
		Medic medic = new Medic();
		RaportMedical raport = medic.getRaport("1");
		System.out.println(raport.getNumeMedic());
	}

}
