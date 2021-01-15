package policlinica;

import policlinica.users.User;
import java.sql.*;

public class Serviciu {

	private String nrServiciu;
	private String nume;
	private String rezultat;

	public Serviciu(String nrServiciu, String nume, String rezultat) {
		this.nrServiciu = nrServiciu;
		this.nume = nume;
		this.rezultat = rezultat;
	}

	public Serviciu() {
		nrServiciu = null;
		nume = null;
		rezultat = null;
	}
	public Serviciu(String nrServiciu) {
		ResultSet rs = (new User()).executeSelect("Select * from Serviciu inner join ServiciuPerProgramare on\r\n"
				+ "				 Serviciu.nrServiciu = ServiciuPerProgramare.nrServiciu where\r\n"
				+ "				 Serviciu.nrServiciu = " + nrServiciu + ";");
		try {
			if (rs.next()) {
				this.nrServiciu = rs.getString("nrServiciu");
				this.nume = rs.getString("nume");
				this.rezultat = rs.getString("rezultat");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getNrServiciu() {
		return nrServiciu;
	}

	public void setNrServiciu(String nrServiciu) {
		this.nrServiciu = nrServiciu;
	}

	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getRezultat() {
		return rezultat;
	}
	public void setRezultat(String rezultat) {
		this.rezultat = rezultat;
	}
}
