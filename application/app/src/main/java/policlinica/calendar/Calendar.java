package policlinica.calendar;

import java.time.YearMonth;

import policlinica.users.User;

public class Calendar {
	private Day[][] calendar = new Day[6][7];//calendar[week][dayofweek]
	public Calendar(String nrContract,String year,String mounth) {
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
			if (dayOfWeek == 7)
				week++;
		}
	}
	
}
