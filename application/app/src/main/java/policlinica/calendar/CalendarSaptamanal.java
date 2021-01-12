package policlinica.calendar;

import java.time.YearMonth;

public class CalendarSaptamanal {
	private Day[] calendar = new Day[7];//calendar[week][dayofweek]
	public CalendarSaptamanal() {
		
	}
	public CalendarSaptamanal(String nrContract) {
		for (int i = 0 ; i < 7 ; i ++) {
			calendar[i] = new Day("2021-01-" + (10 + i));
			calendar[i].setDayGenericInformation(nrContract);
			System.out.println(i+" "+ calendar[i].getStringDate() + " " + calendar[i].getNameDayOfWeek());
		}
	}
	public Day getDay(int i) {
		if (i>=0 && i <7)
			return calendar[i];
		return null;
	}
	public Day getDay(String dayOfWeek) {
		for(int i = 0 ; i < 7; i++) {
			if (calendar[i].getNameDayOfWeek().equals(dayOfWeek));
				return calendar[i]; 
		}
		return null;
	}
}
