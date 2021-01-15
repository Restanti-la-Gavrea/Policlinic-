package policlinica;

public class ServiciuCustom extends Serviciu{
	private String pret; 
	private String durata; 
	ServiciuCustom(String pret,String durata, String nrServiciu, String nume, String rezultat){
		super(nrServiciu,nume,rezultat);
		this.pret = pret; 
		this.durata = durata; 
	}
}