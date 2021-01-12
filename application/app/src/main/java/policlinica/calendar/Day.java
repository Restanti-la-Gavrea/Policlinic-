package policlinica.calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import policlinica.users.Admin;

public class Day {
	private String intervalorar;
	private Date date;
	private SimpleDateFormat simpleDateFormat; 
	private String numeUnitate;
	private int nrUnitate;
	
	public Day(Date date) {
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.date = date;
		this.intervalorar = new String("liber");
		this.nrUnitate = 0;
		this.numeUnitate = null;
	}
	
	public Day( String date) {
		super();
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = simpleDateFormat.parse(date);
			this.intervalorar = new String("liber");
			this.nrUnitate = 0;
			this.numeUnitate = null;
		} catch (ParseException e) {
			System.err.println("Nu s-a reusit transformarea String in date");
			e.printStackTrace();
		}
	}
	
	public int getNrUnitate() {
		return nrUnitate;
	}

	public void setNrUnitate(int nrUnitate) {
		this.nrUnitate = nrUnitate;
	}

	public String getNumeUnitate() {
		return numeUnitate;
	}
	public void setNumeUnitate(String numeUnitate) {
		this.numeUnitate = numeUnitate;
	}
	public int compareTo(Day day2) {
		return this.date.compareTo(day2.date);
	}
	public int getIntDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return  c.get(Calendar.DAY_OF_WEEK);
	}
	public String getNameDayOfWeek() {
		switch (this.getIntDayOfWeek()) {
		case 1: {
			return "Duminica";
		}case 2:{
			return "Luni";
		}case 3:{
			return "Marti";
		}case 4:{
			return "Miercuri";
		}case 5:{
			return "Joi";
		}case 6:{
			return "Vineri";
		}case 7:{
			return "Sambata";
		}
		default:{
			return null;
		}
		}
		
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
	
	public void setDayInformation(String nrContract) {
		///Prioritatea datelor Specific, Concediu, Generic
		//pentru concediu intervalul orar = "concediu" ,nrunitate = 0,nume unitate = null
		//default orar = "liber" , nrunitate = 0 , nume unitate = null
		
		Admin admin = new Admin();
		ResultSet resultGeneric = admin.getOrarGeneric(nrContract, this.getNameDayOfWeek());
		ResultSet resultConcediu = admin.getConcediu(nrContract);
		ResultSet resultSpecific = admin.getOrarSpecific(nrContract,this.getStringDate());
		System.out.println(resultGeneric);
		try {
			//System.out.println("mereMAimuta");
			if (resultGeneric.next())
			{
				this.setIntervalorar(resultGeneric.getString("intervalOrar"));
				this.setNrUnitate(resultGeneric.getInt("nrUnitate"));
				this.setNumeUnitate(resultGeneric.getString("numeUM"));
			}
			if (resultConcediu.next())
			{
				Day dayin = new Day(resultConcediu.getString("dataIncepere"));
				Day dayout = new Day(resultConcediu.getString("dataTerminare"));
				if (this.compareTo(dayin) >= 0 && this.compareTo(dayout) <= 0) {
					this.setIntervalorar("concediu");
					this.setNrUnitate(0);
					this.setNumeUnitate(null);
				}
			}
			if (resultSpecific.next())
			{
				this.setIntervalorar(resultSpecific.getString("intervalOrar"));
				this.setNrUnitate(resultSpecific.getInt("nrunitate"));
				this.setNumeUnitate(resultSpecific.getString("numeUM"));
			}
		} catch (SQLException e) {
			SQLError(e);
		}
		
	}
	private void SQLError(SQLException e) {
		System.out.println("Problems in getDayInformation in Calendar Class");
		e.printStackTrace();
	}
	

}
