package policlinica;
import java.sql.ResultSet;
import java.sql.SQLException;

import policlinica.users.*;

public class Test {

	public Test() {
		SuperAdmin user = new SuperAdmin(1,"Mori");
		ResultSet rs = user.getDateAngajati("","","m");
	    try {
			while (rs.next()) {
			     System.out.println(rs.getString("adresa"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
