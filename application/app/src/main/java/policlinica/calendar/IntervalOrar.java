package policlinica.calendar;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class IntervalOrar {
	private ArrayList <Date> ore1 = new ArrayList<>();
	private ArrayList <Date> ore2 = new ArrayList<>();
	private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	public IntervalOrar(String intervale){
		if (intervale == null ||intervale.equals("liber")||intervale.equals("")||intervale.equals("concediu"))
			intervale ="10:00-10:00";
		String[] intervals = intervale.split(" ");
		for (int i = 0 ; i <intervals.length; i ++) {
			String []ore = intervals[i].split("-");
			try {
				Date ora1 = format.parse(ore[0]);
				Date ora2 = format.parse(ore[1]);
				ore1.add(ora1);
				ore2.add(ora2);
			} catch (ParseException e) {
				System.out.println("format interval orar gresit");
			}
		}
	}
	public int getMinutesIntervale() {
		long suma = (long)0 ;
		for (int i = 0 ; i < ore1.size(); i ++) {
			long difference = ore2.get(i).getTime() - ore1.get(i).getTime(); 
			difference = Math.abs(difference)/60000;
			suma = suma + difference;
		}
		return (int)suma;
	}
	

}
