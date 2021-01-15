package policlinica;
import policlinica.users.*;
import java.sql.*;
public class ServiciuCustom extends Serviciu{
	private String pret; 
	private String durata; 
	private String id = "-1"; 
	ServiciuCustom(String nrContract,String nrServiciu){
		User u = new User();
		 ResultSet rs = u.executeSelect("Select * from Serviciu inner join ServiciuCustom on Serviciu.nrServiciu = ServiciuCustom.nrServiciu"
		 		+ " where nrContract = " + nrContract + " and Serviciu.nrServiciu = " + nrServiciu+ ";");
		 try {
			if(rs.next()) {
				 this.setNrServiciu(nrServiciu);
				 this.setNume(rs.getString("nume"));
				 this.pret = rs.getString("newpret");
				 this.durata = rs.getString("newdurata"); 
				 this.id = rs.getString("id");
			 }
			else {
				rs = u.executeSelect("Select * from Serviciu where nrServiciu = " + nrServiciu + ";");
				if(rs.next()) {
					 this.setNrServiciu(nrServiciu);
					 this.setNume(rs.getString("nume"));
					 this.pret = rs.getString("pret");
					 this.durata = rs.getString("durata"); 
					 this.id = rs.getString("-1");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPret() {
		return pret;
	}
	public void setPret(String pret) {
		this.pret = pret;
	}
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}