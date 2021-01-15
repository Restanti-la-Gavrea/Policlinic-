package policlinica.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import policlinica.*;

public class Economic extends User {

	public Economic(ResultSet result) {
		super(result);
	}

	public Economic() {
		super();
	}

	public double getProfit(int month, int year) {
		double profitPoliclinica = 0.0;
		ResultSet rs = executeSelect(
				"select suma,comision from platimedic where MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year + ";");
		try {
			while (rs.next()) {
				profitPoliclinica += (rs.getInt("suma") * (100 - rs.getInt("comision"))) / 100;
			}
			rs = executeSelect("Select salariu,nrOre from Contract");
			while (rs.next()) {
				profitPoliclinica -= rs.getDouble("salariu") * rs.getDouble("nrOre");
			}
		} catch (Exception e) {
			printSqlErrorMessage("getProfit,economic");
		}
		return profitPoliclinica;
	}

	public double getSalariu(String nrContract, int month, int year) {
		double salariu = 0.0;
		ResultSet rs;
		try {
			rs = executeSelect("Select salariu,nrOre,functie from Contract where nrContract = " + nrContract + ";");
			if (rs.next())
			{
				User myMedic = new User(nrContract); 
				salariu = myMedic.getSalariu(month,year); 
			}
			if (rs != null && rs.getString("functie") == "m") {
				double aux = profitMedic(nrContract, month, year);
				rs = executeSelect("Select comision from Medic where nrContract =  " + nrContract + ";");
				int comision = 0;
				if (rs.next()) {
					comision = rs.getInt("comision");
				}
				aux += salariu;
				salariu += (aux * comision) / (100 - comision);
			}
		} catch (Exception e) {
			printSqlErrorMessage("getSalariu,economic");
		}
		return salariu;
	}

	public double profitMedic(String nrContract, int month, int year) {
		double profit = 0.0;
		ResultSet rs = executeSelect("select suma,comision from platimedic where MONTH(ziPlata) = " + month
				+ " and YEAR(ziPlata) = " + year + " and nrCMedic = " + nrContract + ";");
		try {
			while (rs.next()) {
				profit += (rs.getInt("suma") * (100 - rs.getInt("comision"))) / 100;
			}
		} catch (Exception e) {
			printSqlErrorMessage("ProfitMedic, medic");
		}
		profit -= this.getSalariu(nrContract, month, year);
		return profit;
	}

	// profitul adus de Medicul X in ultimele nrMonths luni
	// O matrice cu coloanele fiind nrContract, liniile luna , reprezentand profitul
	// pe care l-a adus fiecare medic
	public FinanteTableItem[] getListaProfitGeneratDeMedici(int month, int year) {
		FinanteTableItem[] result = new FinanteTableItem[0];
		try {
			ResultSet rs = executeSelect("Select Count(*) As Count from Medic");
			if (rs.next())
				result = new FinanteTableItem[rs.getInt("Count")];
			rs = executeSelect("Select nrContract from Medic");
			int j = 0;
			while (rs.next()) {
				double auxresult = profitMedic(rs.getString("nrContract"), month, year);
				ResultSet aux = executeSelect(
						"Select nume,prenume from Contract where nrContract = " + nrContract + ";");
				if (aux.next())
					result[j].setNumeComplet(aux.getString("nume") + aux.getString("prenume"));
				result[j].setSum(Double.toString(auxresult));
				j++;
			}
		} catch (Exception e) {
			printSqlErrorMessage("ListaProfitGeneratMEdici, economic");
		}
		return result;
	}

	// Profitul adus de medicul X la fiecare specialitate
	public FinanteTableItem[] profitPerSpecialitate(String nrContract, int month, int year) {
		FinanteTableItem[] profit = new FinanteTableItem[0];
		double[] profit1 = new double[0];
		try {
			ResultSet rs = executeSelect("Select Count(*) As Count from Specialitate");
			if (rs.next()) {
				profit = new FinanteTableItem[rs.getInt("Count") + 1];
				profit1 = new double[rs.getInt("count") + 1];
			} else
				profit = null;
			double profitTotalMedic = profitMedic(nrContract, month, year);
			profit1[0] = profitTotalMedic;
			rs = executeSelect("Select nrSpecialitate,nume from Specialitate");
			int j = 1;
			while (rs.next()) {
				
				ResultSet aux = executeSelect(
						"Select suma from Serviciu inner join ServiciuPerProgramare inner join Programare inner join Plata on Serviciu.nrServiciu"
								+ "= ServiciuperProgramare.nrServiciu and ServiciuperProgramare.nrProgramare = Plata.nrProgramare and Programare.nrProgramare = Plata.nrProgramare where nrCMedic = "
								+ nrContract + " and  MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year
								+ " and Serviciu.nrSpecialitate = " + rs.getString("nrSpecialitate") + ";"); 
				while (aux.next()) {
					profit1[j] += aux.getInt("suma");
				}
				profit[j] = new FinanteTableItem();
				aux = executeSelect("Select nume,prenume from Contract where nrContract = " + nrContract + ";");
				if (aux.next()) {
					profit[j].setNumeComplet(aux.getString("nume") + aux.getString("prenume"));
					if (j == 1) {
						profit[0] = new FinanteTableItem();
						profit[0].setNumeComplet(aux.getString("nume") + aux.getString("prenume"));
						profit[0].setDetails("Total");
						profit[0].setSum(Double.toString(profit1[0]));
					}
				}
				profit[j].setDetails(rs.getString("nume"));
				profit[j].setSum(Double.toString(profit1[j]));
				j++;
			}
		} catch (Exception e) {
			printSqlErrorMessage("profitperSpecialitate, economic");
		}
		return profit;
	}

	// profitul pe care l-a adus medicul X la fiecare unitate, pe prima coloana
	// fiind profitul total
	public FinanteTableItem[] profitPerUnitate(String nrContract, int month, int year) {
		FinanteTableItem[] profit = null;
		double[] result1 = new double[0];
		try {
			ResultSet rs = executeSelect("Select Count(*) as Count from UnitateMedicala");
			int count = 1;
			if (rs.next()) {
				count = rs.getInt("Count");
				result1 = new double[rs.getInt("Count") + 1];
				profit = new FinanteTableItem[rs.getInt("Count") + 1];
			}
			result1[0] = profitMedic(nrContract, month, year);
			rs = executeSelect("Select nrUnitate,nume from UnitateMedicala");
			int j = 1;
			ResultSet aux;
			while (rs.next()) {
				String b = rs.getString("nrUnitate");
				aux = executeSelect(
						"Select suma from Cabinet inner join Programare inner join Plata on Cabinet.nrCabinet"
								+ "= Programare.nrCabinet and Programare.nrProgramare = Plata.nrProgramare where nrCMedic = "
								+ nrContract + " and  MONTH(ziPlata) = " + month + " and YEAR(ziPlata) = " + year
								+ " and Cabinet.nrUnitate = " + b + ";");
				while (aux != null && aux.next()) {
					int suma = aux.getInt("suma");
					result1[j] += suma;
				}
				result1[j] += result1[0] / (count * 1.00001);
				aux = executeSelect("Select nume,prenume from Contract where nrContract = " + nrContract + ";");
				profit[j] = new FinanteTableItem();
				if (aux.next()) {
					profit[j].setNumeComplet(aux.getString("nume") + aux.getString("prenume"));
					if (j == 1) {
						profit[0] = new FinanteTableItem();
						profit[0].setNumeComplet(aux.getString("nume") + aux.getString("prenume"));
						profit[0].setDetails("Total");
						profit[0].setSum(Double.toString(result1[0]));
					}
				}
				profit[j].setDetails(rs.getString("nume"));
				profit[j].setSum(Double.toString(result1[j]));
				j++;
			}
		} catch (Exception e) {
			printSqlErrorMessage("profitperUnitate, economic");
		}
		return profit;
	}
}
