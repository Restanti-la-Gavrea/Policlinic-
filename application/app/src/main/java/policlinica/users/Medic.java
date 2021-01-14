package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import policlinica.calendar.*;

public class Medic extends Medical {

	public Medic(ResultSet result) {
		super(result);
	}

	public Medic() {
		super();
	}

	public double profitMedic(int month, int year) {
		double profit = 0.0;
		ResultSet rs = executeSelect("select suma from platimedic where MONTH(ziPlata) = " + month
				+ " and YEAR(ziPlata) = " + year + " and nrCMedic = " + nrContract + ";");
		try {
			while (rs.next()) {
				profit += (rs.getInt("suma") * (100 - rs.getInt("comision"))) / 100;
			}
		} catch (Exception e) {
			printSqlErrorMessage("ProfitMedic, medic");
		}
		profit -= super.getSalariu(month, year);
		return profit;
	}
	@Override
	public double getSalariu(int month, int year) {
		double salariuLunar = this.getSalariu(month, year);
		ResultSet rs = executeSelect("Select comision from Medic where nrContract =  " + nrContract + ";");
		int comision = 0;
		try {
			if (rs.next()) {
				comision = rs.getInt("comision");
			}
		} catch (Exception e) {
			printSqlErrorMessage("getsalariu, medic");
		}
		
		double aux = profitMedic(month, year);
		aux += salariuLunar;
		salariuLunar += (aux * comision) / (100 - comision);
		return salariuLunar;
	}

}
