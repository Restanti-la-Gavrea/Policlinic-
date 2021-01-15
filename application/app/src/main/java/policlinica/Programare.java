package policlinica;

import policlinica.calendar.Day;

import java.util.ArrayList;

public class Programare {

    private String nrProgramare;

    private String numePacient;
    private String prenumePacient;
    private String numeComplet;
    private String cnpPacient;

    private Specialitate specialitate;
    private String siString;

    private ArrayList<Serviciu> servicii;

    private String nrCMedic;
    private String numeMedic;
    private String prenumeMedic;

    private Day day;
    private String dataOra; //pentru afisare in tabel;

    private String achitat;
    private String raport;

    public Programare(){
        nrProgramare = null;
        numePacient = null;
        prenumePacient = null;
        cnpPacient = null;
        specialitate = new Specialitate();
        servicii = new ArrayList<>();
        nrCMedic = null;
        numeMedic = null;
        day = null;
        dataOra = null;
    }
    public Programare(String numePacient, String prenumePacient, String cnpPacient, Day day) {
        this.numePacient = numePacient;
        this.prenumePacient = prenumePacient;
        numeComplet = numePacient + " " + prenumePacient;
        this.cnpPacient = cnpPacient;
        specialitate = new Specialitate();
        servicii = new ArrayList<>();
        nrCMedic = null;
        numeMedic = null;
        this.day = day;
        fillDataOra();
    }

    public String getNumeComplet() { return numeComplet; }
    public void setNumeComplet(String numeComplet) { this.numeComplet = numeComplet; }
    public String getNumePacient() { return numePacient; }
    public void setNumePacient(String numePacient) { this.numePacient = numePacient;
        if(prenumePacient != null) numeComplet = this.numePacient + " " + prenumePacient; }
    public String getPrenumePacient() { return prenumePacient; }
    public void setPrenumePacient(String prenumePacient) { this.prenumePacient = prenumePacient;
        if(numePacient != null) numeComplet = numePacient + " " + this.prenumePacient;}
    public String getCnpPacient() { return cnpPacient; }
    public void setCnpPacient(String cnpPacient) { this.cnpPacient = cnpPacient; }
    public String getSpecialitate() { return specialitate.getNume(); }
    public void setSpecialitate(String specialitate) { this.specialitate.setNume(specialitate); }
    public ArrayList<Serviciu> getServicii() { return servicii; }
    public void setServicii(ArrayList<Serviciu> servicii) { this.servicii = servicii; }
    public String getNrCMedic() { return nrCMedic; }
    public void setNrCMedic(String nrCMedic) { this.nrCMedic = nrCMedic; }
    public String getNumeMedic() { return numeMedic; }
    public void setNumeMedic(String numeMedic) { this.numeMedic = numeMedic; }
    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; fillDataOra();}
    public String getDataOra() { return dataOra; }
    public void setDataOra(String dataOra) { this.dataOra = dataOra; }

    public String getAchitat() { return achitat; }
    public void setAchitat(String achitat) { this.achitat = achitat; }
    public String getRaport() { return raport; }
    public void setRaport(String raport) { this.raport = raport; }

    public Specialitate getSpecialitateObject(){return specialitate;}
    public void setSpecialitateObject(Specialitate specialitate){ this.specialitate = specialitate;}

    public boolean isAchitat(){
        if(achitat != null) return achitat.toLowerCase().equals("true");
        return false;
    }
    public boolean isRaport(){
        if(raport != null) return raport.toLowerCase().equals("true");
        return false;
    }

    public Pacient getPacient(){ return new Pacient(cnpPacient, numePacient, prenumePacient); }
    public void setPacient(Pacient pacient){
        cnpPacient = pacient.getNrPacient();
        numePacient = pacient.getNume();
        prenumePacient = pacient.getPrenume();
        numeComplet = numePacient+" "+prenumePacient;
    }
    public MedicAux getMedic(){return new MedicAux(nrCMedic, numeMedic, prenumeMedic);}
    public void setMedic(MedicAux medic){
        nrCMedic = medic.getNrContract();
        numeMedic = medic.getNume();
        prenumeMedic = medic.getPrenume();
    }

    public String getNrProgramare() { return nrProgramare; }
    public void setNrProgramare(String nrProgramare) { this.nrProgramare = nrProgramare; }
    public void setSpecialitate(Specialitate specialitate) { this.specialitate = specialitate; siString = specialitate.getNume(); }
    public String getPrenumeMedic() { return prenumeMedic; }
    public void setPrenumeMedic(String prenumeMedic) { this.prenumeMedic = prenumeMedic; }

    public String getSiString() { return siString; }
    public void setSiString(String sString) { this.siString = sString; }

    private void fillDataOra(){
        if(day != null){
            dataOra = day.getDayOfMonth() + " " + MonthName.getMonthName(day.getMounthOfYear()-1) + " - " + day.getIntervalorar();
        }
    }
}
