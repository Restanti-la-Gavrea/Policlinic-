package policlinica;

import policlinica.calendar.Day;

public class Programare {

    private String numePacient;
    private String prenumePacient;
    private String numeComplet;
    private String cnpPacient;

    private String specialitate;

    private String[] servicii;

    private String nrCMedic;
    private String numeMedic;

    private Day day;
    private String dataOra; //pentru afisare in tabel;

    private String achitat;
    private String raport;

    public Programare(){
        numePacient = null;
        prenumePacient = null;
        cnpPacient = null;
        specialitate = null;
        servicii = null;
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
        specialitate = null;
        servicii = null;
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
    public String getSpecialitate() { return specialitate; }
    public void setSpecialitate(String specialitate) { this.specialitate = specialitate; }
    public String[] getServicii() { return servicii; }
    public void setServicii(String[] servicii) { this.servicii = servicii; }
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

    public boolean isAchitat(){
        if(achitat != null) return achitat.toLowerCase().equals("true");
        return false;
    }
    public boolean isRaport(){
        if(raport != null) return raport.toLowerCase().equals("true");
        return false;
    }

    private void fillDataOra(){
        if(day != null){
            dataOra = day.getDayOfMonth() + " " + MonthName.getMonthName(day.getDayOfMonth()-1) + " - " + day.getIntervalorar();
        }
    }
}
