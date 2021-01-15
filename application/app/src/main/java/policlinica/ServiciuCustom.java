package policlinica;

public class ServiciuCustom extends Serviciu{
	private String pret; 
	private String durata; 
	private String id = "-1"; 
	ServiciuCustom(String nrContract){
		 
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