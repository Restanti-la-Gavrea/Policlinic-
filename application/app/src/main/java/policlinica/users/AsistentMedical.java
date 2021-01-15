package policlinica.users;

import java.sql.ResultSet;

import policlinica.calendar.Calendar;

public class AsistentMedical extends Medic {

	public AsistentMedical(ResultSet result) {
		super(result);
	}
	public AsistentMedical() {
		super();
	}
	public double getSalariu(int month, int year) {
		Calendar calendar = new Calendar(this.getNrContract(), Integer.toString(year), Integer.toString(month));
		int nrMinute = calendar.getMinutesWorked();
		double salariuLunar = Double.parseDouble(salariu) * nrMinute / 60;
		return salariuLunar;
	}


}
