package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.MedicAux;
import policlinica.Pacient;
import policlinica.RaportMedical;
import policlinica.Serviciu;
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
	public ArrayList<Serviciu> getListaServicii(String nrProgramare) {
		String comanda = "Select * from ServiciuPerProgramare inner join  Programare on ServiciuPerProgramare.nrProgramare = Programare.nrProgramare where"
						+ " Programare.nrProgramare = " + nrProgramare + ";";
		ResultSet rezultat = executeSelect(comanda);
		String specialitate = null;
		try {
			if (rezultat.next()) {
				specialitate = rezultat.getString("nrSpecialitate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSqlErrorMessage("listaServicii/Programari , receptioner");
		}
		ArrayList<Serviciu> lista = new ArrayList<Serviciu>();
		comanda = "Select nrServiciu from Serviciu where nrSpecialitate = " + specialitate + ";";
		rezultat = executeSelect(comanda);
		try {
			while (rezultat.next()) {
				lista.add(new Serviciu(rezultat.getString("nrServiviu")));
			}
		} catch (SQLException e) {
			
		}
		return lista;
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
	public ArrayList <MedicAux> getListaAsistenti(){
		ArrayList <MedicAux> listaAsistenti =  new ArrayList<>();
		try {
			String comanda = "Select * from tipasistentmedical,contract "
					+ " where tipasistentmedical.nrContract = contract.nrContract ;";
			ResultSet result = executeSelect(comanda);
			while (result.next()) {
				MedicAux Asistent = new MedicAux(result.getString("nrcontract"),
														result.getString("nume"),
														result.getString("prenume"));
				listaAsistenti.add(Asistent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaAsistenti;
	}

	@Override
	public double getSalariu(int month, int year) {
		double salariuLunar = super.getSalariu(month, year);
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
	public ArrayList<RaportMedical> getListaRapoare(Pacient pacient){
		ArrayList<RaportMedical> listaRapoarte = new ArrayList<>();
		try {
			String comanda = "Select * from VizualizareIstoric "
					+ " where nrpacient =   " + pacient.getNrPacient() + ";";
			ResultSet result = executeSelect(comanda);
			if (result.next()) {
				RaportMedical raport = getRaport(result.getString("nrprogramare") );
				if (raport != null)
					listaRapoarte.add(raport);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaRapoarte;
	}

	public RaportMedical getRaport(String nrProgramare) {

		ArrayList<Serviciu> listaServicii = new ArrayList<>();
		RaportMedical raport = null;
		try {
			String comanda = "Select * from RaportCompletMedicContract " + " where nrProgramare = " + nrProgramare + ";";
			ResultSet result = executeSelect(comanda);
			if (result!=null && result.next()) {
				raport = new RaportMedical();

				// Tabela Raport
				raport.setNrRaport(result.getString("nrraport"));
				raport.setNrProgramare(result.getString("nrprogramare"));
				raport.setSimptome(result.getString("simptome"));
				raport.setDiagnostic(result.getString("diagnostic"));
				raport.setRecomandari(result.getString("recomandari"));
				raport.setParafat(result.getBoolean("parafat"));
				
				// Tabela Programare
				raport.setDataProgramare(result.getString("datap"));
				raport.setOraProgramare(result.getString("ora"));

				MedicAux medic = new MedicAux(
						result.getString("nrcontract"), 
						result.getString("nume"),
						result.getString("prenume"));
				raport.setMedic(medic);
				Pacient pacient = new Pacient(
						result.getString("nrpacient"), 
						result.getString("numepacient"),
						result.getString("prenumepacient"));
				raport.setPacient(pacient);

				Serviciu serviciu = new Serviciu(
						result.getString("nrserviciu"), 
						result.getString("numeserviciu"),
						result.getString("rezultat"));
				listaServicii.add(serviciu);
				raport.setServiciu(listaServicii);
			}
			else 
				return null; // Aici se poate termina programul
			
			while (result!=null && result.next()) {//Se cauta si restul de servicii
				Serviciu serviciu = new Serviciu(
						result.getString("nrserviciu"), 
						result.getString("numeserviciu"),
						result.getString("rezultat"));
				listaServicii.add(serviciu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			String comanda = "select * from medic,raport,Contract" +
							 " where medic.nrContract = raport.medicRecomandare" +
							 " and medic.nrContract = Contract.nrContract" +
							 " and raport.NrRaport = " + raport.getNrRaport() + ";";
			ResultSet result = executeSelect(comanda);
			if (result!=null && result.next()) {
				MedicAux medicRecomandat = new MedicAux(result.getString("nrcontract"),
														result.getString("nume"),
														result.getString("prenume"));
				raport.setMedicRecomandare(medicRecomandat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Se cauta asistentul
		try {
			String comanda = "Select * from RaportCompletAsistentContract " + 
							 " where nrProgramare = " + nrProgramare + ";";
			ResultSet result = executeSelect(comanda);
			if (result.next()) {
				MedicAux Asistent = new MedicAux(result.getString("nrcontract"),
														result.getString("nume"),
														result.getString("prenume"));
				raport.setAsistent(Asistent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return raport;
	}

	public Boolean updateRaport(RaportMedical raport) {
		String comanda = "Update Raport Set" + " medicRecomandare = " + raport.getMedicRecomandare().getNrContract()
				+ " asistentContract = " + raport.getAsistent().getNrContract() + " simptome = " + "'"
				+ raport.getSimptome() + "'" + " diagnostic = " + "'" + raport.getDiagnostic() + "'" + " recomandari = "
				+ "'" + raport.getRecomandari() + "'" + " parafat = " + raport.isParafat() + " where nrRaport = "
				+ raport.getNrRaport() + ";";
		if (!executeUpdate(comanda))
			return false;
		setListaServiciuPerProgramare(raport.getServiciu(), comanda);
		return true;
	}

	public Boolean insertRaport(RaportMedical raport) {
		String comanda = "insert into raport values (" + raport.getNrProgramare() + ","
				+ raport.getMedicRecomandare().getNrContract() + "," + raport.getAsistent().getNrContract() + ","
				+ raport.getSimptome() + "," + raport.getDiagnostic() + "," + raport.getRecomandari() + ","
				+ raport.isParafat() + ");";
		if (!executeUpdate(comanda))
			return false;
		setListaServiciuPerProgramare(raport.getServiciu(), comanda);
		return true;
	}

}
