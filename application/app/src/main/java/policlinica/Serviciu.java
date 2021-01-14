package policlinica;

public class Serviciu {

    private String nrServiciu;
    private String nume;
    private String rezultat;

    public Serviciu(String nrServiciu, String nume, String rezultat) {
        this.nrServiciu = nrServiciu;
        this.nume = nume;
        this.rezultat = rezultat;
    }

    public Serviciu(){
        nrServiciu = null;
        nume = null;
        rezultat = null;
    }

    public String getNrServiciu() { return nrServiciu; }
    public void setNrServiciu(String nrServiciu) { this.nrServiciu = nrServiciu; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getRezultat() { return rezultat; }
    public void setRezultat(String rezultat) { this.rezultat = rezultat; }
}
