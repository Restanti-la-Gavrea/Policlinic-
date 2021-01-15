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
	public void mere() {
		String mere = "09:20-10:10 10:10-12:00 10:05-11:00";
		IntervalOrar interval = new IntervalOrar(mere);
		System.out.println(interval.isIntercalat(0, 1));
		System.out.println(interval.isIntercalat());
	}
	private void verificaListaRapoarte() {
		Medic medic = new Medic();
		//RaportMedical raport = medic.getRaport("1");
		medic.getListaServicii("3");
	}

}
