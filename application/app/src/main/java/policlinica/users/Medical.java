package policlinica.users;

import policlinica.MedicAux;
import policlinica.Pacient;
import policlinica.Programare;
import policlinica.Serviciu;

import java.util.ArrayList;
import java.sql.*;

public class Medical extends User {

	public Medical(ResultSet result) {
		super(result);
	}

	public Medical(String nrContract) {
		super(nrContract);
	}

	public Medical() {
		super();
	}

	public ArrayList<Pacient> getListaPacienti() {
		ArrayList<Pacient> lista = new ArrayList<Pacient>();
		ResultSet rs = executeSelect("Select * from Pacient");
		try {
			while (rs.next()) {
				lista.add(new Pacient(rs.getString("nrPacient"), rs.getString("nume"), rs.getString("prenume")));
			}
		} catch (Exception e) {
			printSqlErrorMessage("getListaPacienti,medical");
		}
		return lista;
	}
	public Boolean setListaServiciuPerProgramare(ArrayList<Serviciu> lista, String nrProgramare) {
		deleteAllServiciuPerProgramare(nrProgramare);
		Boolean mere = true;
		for (int i = 0 ; i < lista.size(); i ++) {
			mere = mere && insertServiciuPerProgramare(lista.get(i), nrProgramare);
		}
		return mere;
	}
	public Boolean insertServiciuPerProgramare(Serviciu serviciu,String nrProgramare) {
		String comanda = "Insert into ServiciuPerProgramare(nrServiciu , nrProgramare,rezultat) values" +
						"(" + serviciu.getNrServiciu() + "," +nrProgramare + ",'" +
						serviciu.getRezultat() + "')";
		return executeUpdate(comanda);
	}
	public Boolean deleteAllServiciuPerProgramare(String nrProgramare) {
		String comanda = "delete from ServiciuPerProgramare where nrProgramare = " +  nrProgramare;
		return executeUpdate(comanda);
	}
	public ArrayList<Serviciu> getListaServiciiPerProgramare(String nrProgramare) {
		ArrayList<Serviciu> lista = new ArrayList<Serviciu>();
		ResultSet rs = executeSelect(
				"Select nrServiciu from ServiciuPerProgramare inner join  Programare on ServiciuPerProgramare.nrProgramare = Programare.nrProgramare where"
						+ " Programare.nrProgramare = " + nrProgramare + ";");
		try {
			while (rs.next()) {
				lista.add(new Serviciu(rs.getString("nrServiviu")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			printSqlErrorMessage("listaServicii/Programari , receptioner");
		}
		return lista;
	}
	public ArrayList<Programare> getProgramari() {
		ArrayList<Programare> lista = new ArrayList<Programare>();
		ResultSet rs = executeSelect("Select * from Programare");
		try {
			while (rs.next()) {
				ResultSet aux = executeSelect(
						"Select * from Raport where nrProgramre = " + rs.getString("nrProgramare") + ";");	
				Programare p = new Programare();
				p.setRaport("true");
				p.setAchitat("false");
				if (!aux.next()) {
					ResultSet aux1 = executeSelect(
							"Select nume, prenume from Contract where nrContract = " + rs.getString("nrCMedic") + ";");
					if (aux1.next()) {
						p.setMedic(new MedicAux(rs.getString("nrCMedic"), aux1.getString("nume"),
								aux1.getString("prenume")));
					}
					aux1 = executeSelect("Select * from Pacient where nrPacient = " + rs.getString("nrPacient") + ";");
					if (aux1.next()) {
						p.setPacient(new Pacient(aux1.getString("nrPacient"), aux1.getString("nume"),
								aux1.getString("prenume")));
					}
					p.setServicii(getListaServiciiPerProgramare(rs.getString("nrProgramare")));
					p.setNrProgramare(rs.getString("nrProgramare"));
					p.setRaport("false");
				}
				aux = executeSelect("Select * from Plata where nrProgramare = " + rs.getString("nrProgramare") + ";");
				if(!aux.next()) {
					ResultSet aux1 = executeSelect(
							"Select nume, prenume from Contract where nrContract = " + rs.getString("nrCMedic") + ";");
					if (aux1.next()) {
						p.setMedic(new MedicAux(rs.getString("nrCMedic"), aux1.getString("nume"),
								aux1.getString("prenume")));
					}
					aux1 = executeSelect("Select * from Pacient where nrPacient = " + rs.getString("nrPacient") + ";");
					if (aux1.next()) {
						p.setPacient(new Pacient(aux1.getString("nrPacient"), aux1.getString("nume"),
								aux1.getString("prenume")));
					}
					p.setServicii(getListaServiciiPerProgramare(rs.getString("nrProgramare")));
					p.setNrProgramare(rs.getString("nrProgramare"));
					p.setAchitat("false");
				}
				if(p.isAchitat() || p.isRaport()) {
					lista.add(p); 
				}
			}
		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + ((SQLException) e).getSQLState());
			System.out.println("VendorError: " + ((SQLException) e).getErrorCode());
		}
		return lista;
	}


}
