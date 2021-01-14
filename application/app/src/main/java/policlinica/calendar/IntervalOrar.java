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
	public static String formeazaInterval(String oraInitiala,String[] durate) {	
		
		String [] mere = oraInitiala.split(":");
		int ora1 = Integer.parseInt(mere[0]);
		int minut1 = Integer.parseInt(mere[1]);
		int ora2 = ora1;
		int minut2 =minut1;
		for (int i = 0 ;i < durate.length ; i++) {
			String durata = durate[i];
			mere= durata.split(":");
			int ora3 = Integer.parseInt(mere[0]);
			int minut3 = Integer.parseInt(mere[1]);
			ora2 += ora3;
			minut2 += minut3;
		}
		return format.format(ora1) + "-" + format.format(ora2);
	}
	public Boolean isIntercalat() {
		for (int i = 0 ; i <ore1.size()-1;i++) {
			for (int j = 1 ; j <ore1.size(); i++) {
				if (!verificaintercalare(i, j))
					return false;
			}
		}
		return true;
	}
	private Boolean verificaintercalare(int i , int j) {
		Date x1 = ore1.get(i);
		Date x2 = ore1.get(j);
		Date y1 = ore2.get(i);
		Date y2 = ore2.get(j);
		if (x2.compareTo(y1)>0 || y2.compareTo(x1) <0) {
			return true;
		}
		return false;
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
