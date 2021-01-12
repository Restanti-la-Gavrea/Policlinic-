package policlinica;

public class MedicAux {
	private String nrContract; 
	private String nume; 
	private String prenume; 
	public MedicAux(String x, String y,String z) {
		nume = y; 
		prenume = z; 
		nrContract = x;
	}
	public String getNrContract() {
		return nrContract;
	}
	public void setNrContract(String nrContract) {
		this.nrContract = nrContract;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
}
