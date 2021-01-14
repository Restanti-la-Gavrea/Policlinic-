package policlinica;

public class MedicAux {
	private String nrContract; 
	private String nume; 
	private String prenume; 
	public MedicAux(String nrContract, String nume,String prenume) {
		this.nume = nume;
		this.prenume = prenume;
		this.nrContract = nrContract;
	}

	public MedicAux(){
		nume = null;
		prenume = null;
		nrContract = null;
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
