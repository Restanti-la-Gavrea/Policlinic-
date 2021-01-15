package policlinica.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import policlinica.MedicAux;
import policlinica.Pacient;
import policlinica.RaportMedical;
import policlinica.Serviciu;
import policlinica.ServiciuCustom;
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
		String comanda = "Select * from ServiciuPerProgramare inner join Serviciu inner join  Programare on ServiciuPerProgramare.nrProgramare = Programare.nrProgramare "
				+ "and serviciuperprogramare.nrServiciu = serviciu.nrServiciu where "
				+ "						 Programare.nrProgramare = " + nrProgramare + ";";
		ResultSet rezultat = executeSelect(comanda);
		String specialitate = null;
		try {
			if (rezultat.next()) {
				specialitate = rezultat.getString("nrSpecialitate");
			}
		} catch (SQLException e) {
			printSqlErrorMessage("getListaServicii Medic");
		}
		ArrayList<Serviciu> lista = new ArrayList<Serviciu>();
		comanda = "Select nrServiciu from Serviciu where nrSpecialitate = " + specialitate + ";";
		rezultat = executeSelect(comanda);
		System.out.println(comanda);
		try {
			while (rezultat.next()) {
				lista.add(new Serviciu(rezultat.getString("nrServiciu")));
			}
		} catch (SQLException e) {
			System.err.println("Eroare in getListaServicii");
		}
		return lista;
	}
	
	public Boolean setServiciuCustom(ServiciuCustom serviciu) {
		
		if (serviciu.getId().equals("-1")) {
			String comanda = "Select * from serviciu where nrServiciu = "+serviciu.getNrServiciu() + ";";
			ResultSet result = executeSelect(comanda);
			try {
				if (result.next()) {
					String pret = result.getString("pret");
					String durata = result.getString("durata");
					if (!pret.equals(serviciu.getPret()) || !durata.equals(serviciu.getDurata())) {
						comanda = "insert into serviciucustom values "
								+ " (" + serviciu.getNrServiciu() +
								", " + this.getNrContract() +
								", " + serviciu.getPret() +
								" ,'" + serviciu.getDurata() + " ' );";
						System.out.println(comanda);
						return executeUpdate(comanda);
					}
				}
				else 
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;	
			}
		}
		String comanda = "Update serviciucustom Set"
				+ " newpret = " + serviciu.getPret() 
				+ ", newDurata = '"+ serviciu.getDurata() + "' "
				+ "where ID = " + serviciu.getId() + ";";
		System.out.println(comanda);
		return executeUpdate(comanda);
				
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
	
	public double getMinuteWorked(int month,int year) {
		String comanda = "SELECT * FROM realizarebon where"
				+ " nrcmedic = " + this.getNrContract() +
				" and year(datap) = " + year 
				+ " and month(datap) = " + month + ";";
		ResultSet result = executeSelect(comanda);
		String durate ="";
		System.out.println(comanda);
		try {
			while(result.next()) {
				String durata = result.getString("newdurata");
				if ( durata == null) {
					durata = result.getString("durata");
				}
				if (!durate.equals(""))
					durate+=" ";
				durate+=durata;
			}
		} catch (SQLException e) {
			return 0.0;
		}
		System.out.println(durate);
		String intervalString = IntervalOrar.formeazaInterval("00:00:00",durate.split(" ") );
		System.out.println(intervalString);
		IntervalOrar interval = new IntervalOrar(intervalString);
		return interval.getMinutesIntervale();
	}
	
	public double getSalariuMedic(int month,int year) {
		double nrMinute = getMinuteWorked(month,year);
		double salariuLunar = Double.parseDouble(salariu) * nrMinute / 60;
		return salariuLunar;
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
				+ ", asistentContract = " + raport.getAsistent().getNrContract() + ", simptome = " + "'"
				+ raport.getSimptome() + "'" + ", diagnostic = " + "'" + raport.getDiagnostic() + "'" + ", recomandari = "
				+ "'" + raport.getRecomandari() + "'" + ", parafat = " + raport.isParafat() + " where nrRaport = "
				+ raport.getNrRaport() + ";";
		if (!executeUpdate(comanda))
			return false;
		setListaServiciuPerProgramare(raport.getServiciu(), raport.getNrProgramare());
		return true;
	}

	public Boolean insertRaport(RaportMedical raport) {
		String comanda = "insert into raport values (" + raport.getNrProgramare() + ","
				+ raport.getMedicRecomandare().getNrContract() + "," + raport.getAsistent().getNrContract() + ","
				+ raport.getSimptome() + "," + raport.getDiagnostic() + "," + raport.getRecomandari() + ","
				+ raport.isParafat() + ");";
		System.out.println(comanda);
		if (!executeUpdate(comanda))
			return false;
		setListaServiciuPerProgramare(raport.getServiciu(), raport.getNrProgramare());
		return true;
	}

}
