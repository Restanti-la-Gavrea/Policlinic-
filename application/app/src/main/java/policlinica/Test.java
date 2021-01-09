package policlinica;
import java.sql.ResultSet;
import java.sql.SQLException;

import policlinica.calendar.Day;
import policlinica.users.*;

public class Test {

	public Test() {
		//cautareSuperAdmin();
		//updateContract();
		//createUser();
		CalendarTest();
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
	private void CalendarTest() {
		Day day = new Day("2021-1-9");
		System.out.println(day.getIntDayOfWeek());
	}

}
