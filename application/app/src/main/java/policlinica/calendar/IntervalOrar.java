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
	public static String formeazaInterval(String oraInitiala,String durata) {
		String[] mere= durata.split(":");
		durata = mere[0] + ":" + mere[1];
		Date ora1 = null;
		Date ora2 = null;
		try {
			ora1 = format.parse(oraInitiala);
			ora2 = format.parse(durata);
			ora2 = new Date(ora1.getTime() + ora2.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
