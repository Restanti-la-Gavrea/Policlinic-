package policlinica.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import policlinica.users.*;

public class Calendar {
	private Day[][] calendar = new Day[6][7];//calendar[week][dayofweek]
	public Calendar() {
		
	}
	public Calendar(String nrContract,String year,String mounth) {
		// umple matricea cu obiecte Day (Null daca ziua nu face parte din luna curenta)
		//see function setDayInformation
		for (int i = 0 ; i < 6; i++) {
			for(int j = 0 ; j < 7; j++)
				calendar[i][j] = null;
		}
		YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year), Integer.parseInt(mounth));
		int week = 0;
		int daysInMonth = yearMonthObject.lengthOfMonth();
		for (int day = 1 ; day <= daysInMonth ; day++) {
			Day curentDay = new Day(year + "-" + mounth + "-" + Integer.toString(day));
			int dayOfWeek = curentDay.getIntDayOfWeek();
			calendar[week][dayOfWeek] = curentDay;
			curentDay.setDayInformation(nrContract);
			if (dayOfWeek == 7)
				week++;
		}
	}
	public Day getDay(int i,int j) {
		if (i>=0 && i <6 && j <7 && j >= 0)
			return calendar[i][j];
		return null;
	}
	public Day getDay(String dayofmounth) {
		int nrDay = Integer.parseInt(dayofmounth);
		for (int k = 1 ; k <= 42;k++) {
			int i = (k-1)/ 7;
			int j = k % 7;
			Day day = getDay(i,j);
			if (day != null)
				nrDay--;
			if (nrDay == 0)
				return day;
		}
		return null;
	}
	
}
