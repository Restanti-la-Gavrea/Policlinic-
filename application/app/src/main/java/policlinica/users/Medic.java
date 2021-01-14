package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import policlinica.RaportMedical;
import policlinica.calendar.*;

public class Medic extends Medical {

	public Medic(ResultSet result) {
		super(result);
	}

	public Medic(String nrContract) {
		super(nrContract);
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
		double salariuLunar =super.getSalariu(month, year);
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

	public RaportMedical getRaport(String nrProgramare) {
		RaportMedical raport = null;
		String comanda = "Select * from RaportComplet " + "where nrProgramare = " + nrProgramare;
		ResultSet result = executeSelect(comanda);
		try {
			if (result.next()) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Boolean updateRaport(RaportMedical raport) {
		String comanda = "Update Raport Set" +
				" medicRecomandare = " + raport.getMedicRecomandare().getNrContract() + 
				" asistentContract = " + raport.getAsistent().getNrContract() + 
				" simptome = " + "'" + raport.getSimptome() + "'" + 
				" diagnostic = " + "'" + raport.getDiagnostic() + "'" +
				" recomandari = " + "'" + raport.getRecomandari() + "'" +
				" parafat = " + raport.isParafat() + 
				" where nrRaport = " + raport.getNrRaport() + ";";
		if (!executeUpdate(comanda))
			return false;
		///Ramane de facut update pe servicii
		return true;
	}
	public Boolean insertRaport(RaportMedical raport) {
		String comanda = "insert into raport values (" +
				raport.getNrProgramare() + "," +
				raport.getMedicRecomandare().getNrContract() + "," +
				raport.getAsistent().getNrContract() + "," +
				raport.getSimptome() + "," +
				raport.getDiagnostic() + ","+
				raport.getRecomandari() + "," +
				raport.isParafat() + ");";
		if (!executeUpdate(comanda))
			return false;
		
		return true;
	}

	public boolean UpdateRaport() {
		return false;
	}

}
