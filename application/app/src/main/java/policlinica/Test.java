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
		//CalendarDayTest();
		//CalendarTest();
		//verificaCevaListaDeAngaati();
		//testsubmitUser();
		verificaUpdateOrar();
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
	private void testsubmitUser() {
		User user = new User("1");
		SuperAdmin sadm = new SuperAdmin();
		user.setNume("Ion");
		if(sadm.submitUser(user))
			System.out.println("Test 1 reusit");
		sadm = new Admin();
		user.setPrenume("Ionescu");
		if (sadm.submitUser(user))
			System.out.println("Test 2 reusit");
	}
	private void CalendarDayTest() {
		Day day = new Day("2008-11-11");
		day.setDayInformation( "1");
		System.out.println(day.getIntervalorar());
	}
	private void CalendarTest() {
		Calendar calendar = new Calendar("1","2021","1");
		Day day = calendar.getDay("3");
		System.out.println(day.getIntervalorar());
	}
	private void verificaCevaListaDeAngaati() {
		ArrayList <AngajatTableItem>lista = new ArrayList<>();
		lista = (new ResurseUmane()).getArrayOfDateAngajati();
		System.out.println(lista.size());
	}
	private void verificaUpdateOrar() {
		Calendar calendar = new Calendar("3","2021","1");
		Day day = calendar.getDay("3");
		day.setIntervalorar("Saptamansa");
		Admin admin = new Admin();
		admin.setDayOfOrarGeneric(day);
		
	}

}
