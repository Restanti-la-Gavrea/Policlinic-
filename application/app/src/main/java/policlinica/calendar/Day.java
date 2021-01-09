package policlinica.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Day {
	private String intervalorar;
	private Date date;
	private SimpleDateFormat simpleDateFormat; 
	public Day( String date) {
		super();
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			System.err.println("Nu s-a reusit transformarea String in date");
			e.printStackTrace();
		}
	}
	public int getIntDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return  c.get(Calendar.DAY_OF_WEEK);
	}
	
	public String getIntervalorar() {
		return intervalorar;
	}
	public void setIntervalorar(String intervalorar) {
		this.intervalorar = intervalorar;
	}
	public String getStringDate() {
		return simpleDateFormat.format(this.date);
	}
	

}
