package policlinica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.calendar.*;
import policlinica.users.*;

public class Test {

	public Test() {
		//cautareSuperAdmin();
		//updateContract();
		//createUser();
		//CalendarDayTest();
		verificaCevaListaDeAngaati();
	}
	private void cautareSuperAdmin() {
		System.out.println("Testul a inceput");
		SuperAdmin user = new SuperAdmin();
		ResultSet rs = user.getDateAngajati("","","");
	    try {
			while (rs.next()) {
			     System.out.println(rs.getString("adresa"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void createUser() {
		System.out.println("A inceput");
		User user = (new User()).Autentificator("gabor", "0000");
		System.out.println(user.getNume());
		System.out.println(user.getPrenume());
		System.out.println(user.getUsername());
		System.out.println(user.getFunctie());
		System.out.println(user.getTip());
		System.out.println(user.getAdresa());
		
	}
	private void CalendarDayTest() {
		Day day = new Day("2008-11-11");
		day.setDayInformation( "1");
		System.out.println(day.getIntervalorar());
	}
	private void verificaCevaListaDeAngaati() {
		ArrayList <AngajatTableItem>lista = new ArrayList<>();
		lista = (new ResurseUmane()).getArrayOfDateAngajati();
		System.out.println(lista.size());
		
	}

}
