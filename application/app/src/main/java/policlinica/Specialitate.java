package policlinica;

public class Specialitate {

    private String nrSpecialitate;
    private String nume;

    public Specialitate(){
        nrSpecialitate = "";
        nume = "";
    }

    public Specialitate(String nrSpecialitate, String nume) {
        this.nrSpecialitate = nrSpecialitate;
        this.nume = nume;
    }

    public String getNrSpecialitate() { return nrSpecialitate; }
    public void setNrSpecialitate(String nrSpecialitate) { this.nrSpecialitate = nrSpecialitate; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
}
