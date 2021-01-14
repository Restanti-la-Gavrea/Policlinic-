package policlinica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.calendar.*;
import policlinica.users.*;

public class Test {

	public Test() {
		verificaListaRapoarte();
	}
	private void verificaListaRapoarte() {
		Medic medic = new Medic();
		RaportMedical raport = medic.getRaport("1");
		System.out.println(raport.getNumeMedic());
	}

}
