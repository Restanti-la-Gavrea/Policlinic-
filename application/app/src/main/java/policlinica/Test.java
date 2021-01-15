package policlinica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.calendar.*;
import policlinica.users.*;

public class Test {

	public Test() {
		verificaLista("1");
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
	public void verificaLista(String nrSpecialitate) {
		Receptioner t = new Receptioner();
		ArrayList<Medic> rez = t.getListaMedici("1");
		for( Medic m : rez ) {
			System.out.println(m.getNrContract()); 
		}
	}
	public void verificaProgramare() {
		Programare p = new Programare();
		  p.setNrProgramare("25");
	      p.setNumePacient("boris");
	      p.setPrenumePacient("boris");
	        p.setCnpPacient("18");
	        p.setSpecialitate(new Specialitate("1","ecografie")); 
	        ArrayList<Serviciu>  d  = new ArrayList<>();; 
	        p.setServicii(d); 
	        p.setNrCMedic("3");
	        //numeMedic = null;
	        Day day =  new Day("2022-08-26"); 
	        day.setIntervalOrar("16:00:00");
	       p.setDay(day);
	       Receptioner t = new Receptioner(); 
	       t.creeareProgramare(p);
	     //   dataOra = nu,36,16);
	}
}
