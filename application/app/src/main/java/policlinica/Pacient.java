package policlinica;

public class Pacient {

    private String nrPacient;
    private String nume;
    private String prenume;

    public Pacient(String nrPacient, String nume, String prenume) {
        this.nrPacient = nrPacient;
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getNrPacient() { return nrPacient; }
    public void setNrPacient(String nrPacient) { this.nrPacient = nrPacient; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }

    //urmatoarea funtie e facuta pentru mine de mine
    public boolean hasFullName(String fullName){
        //ii dai numele complet si verifica daca se potriveste
        return fullName.equals(nume+" "+prenume);
    }

}
