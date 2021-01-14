package policlinica;

import java.util.ArrayList;

public class RaportMedical {

    private String nrRaport;
    private String nrProgramare;
    //urmatoarele sunt informatiile din programare
    private String dataProgramare;
    private String oraProgramare;
    //informatii despre Medic
    private MedicAux medic;
    //informatii optionale

    private MedicAux medicRecomandare;
    private MedicAux asistent;

    //pacient
    private Pacient pacient;

    private String simptome;
    private String diagnostic;
    private String recomandari;

    private ArrayList<Serviciu> serviciu;

    private boolean parafat;

   //o sa fac contructori pe masura ce-mi trebuie
   public RaportMedical(){
        nrRaport = "";
        nrProgramare = "";
        dataProgramare = "";
        oraProgramare = "";

        medic= null;
        medicRecomandare=null;
        asistent=null;

        pacient = null;
        simptome = "";
        diagnostic = "";
        recomandari = "";

        serviciu = null;

        parafat = false;
    }

    public RaportMedical(String nrRaport, String nrProgramare, String dataProgramare, String oraProgramare, String numeMedic, String prenumeMedic, String nrCMedic, Pacient pacient, ArrayList<Serviciu> serviciu) {
        this.nrRaport = nrRaport;
        this.nrProgramare = nrProgramare;
        this.dataProgramare = dataProgramare;
        this.oraProgramare = oraProgramare;
        medic = new MedicAux(nrCMedic, numeMedic, prenumeMedic);
        this.pacient = pacient;
        this.serviciu = serviciu;

        medicRecomandare = null;
        asistent = null;

        simptome = "";
        diagnostic = "";
        recomandari = "";

        parafat = false;
    }
    public RaportMedical(Programare programare) {
        nrRaport = "";
        pacient = programare.getPacient();
        medic = programare.getMedic();
        dataProgramare = programare.getDay().getStringDate();
        oraProgramare = programare.getDay().getIntervalorar();
        nrProgramare = programare.getNrProgramare();
        serviciu = programare.getServicii();

        medicRecomandare = null;
        asistent = null;

        simptome = "";
        diagnostic = "";
        recomandari = "";

        parafat = false;
    }

    //funtiile de set nu fac nimic daca e parafat raportul
    public String getNrRaport() { return nrRaport; }
    public void setNrRaport(String nrRaport) { if(!parafat) this.nrRaport = nrRaport; }
    public String getDataProgramare() { return dataProgramare; }
    public void setDataProgramare(String dataProgramare) { if(!parafat)  this.dataProgramare = dataProgramare; }
    public String getOraProgramare() { return oraProgramare;  }
    public void setOraProgramare(String oraProgramare) { if(!parafat)  this.oraProgramare = oraProgramare; }
    public String getNumeMedic() {return medic.getNume(); }
    public void setNumeMedic(String numeMedic) { if(!parafat) medic.setNume(numeMedic); }
    public String getPrenumeMedic() { return medic.getPrenume(); }
    public void setPrenumeMedic(String prenumeMedic) { if(!parafat) medic.setPrenume(prenumeMedic); }
    public String getNrCMedic() { return medic.getNrContract();}
    public void setNrCMedic(String nrCMedic) { if(!parafat)  medic.setNrContract(nrCMedic); }
    public Pacient getPacient() { return pacient;  }
    public void setPacient(Pacient pacient) { if(!parafat) this.pacient = pacient; }
    public String getSimptome() {return simptome; }
    public void setSimptome(String simptome) { if(!parafat) this.simptome = simptome; }
    public String getDiagnostic() {  return diagnostic;  }
    public void setDiagnostic(String diagnostic) { if(!parafat) this.diagnostic = diagnostic; }
    public String getRecomandari() { return recomandari;  }
    public void setRecomandari(String recomandari) { if(!parafat) this.recomandari = recomandari; }
    public ArrayList<Serviciu> getServiciu() { return serviciu;  }
    public void setServiciu(ArrayList serviciu) { if(!parafat) this.serviciu = serviciu; }
    public void parafeaza(){ if(!parafat) parafat = true; }
    public boolean isParafat(){ return parafat;}

    public String getNrProgramare() { return nrProgramare; }
	public void setNrProgramare(String nrProgramare) { this.nrProgramare = nrProgramare; }

	public void setParafat(boolean parafat) { this.parafat = parafat; }
	public Serviciu getServiciuIndex(int i){return serviciu.get(i);}
	
	


    public MedicAux getMedic() {
		return medic;
	}

	public void setMedic(MedicAux medic) {
		this.medic = medic;
	}

	public MedicAux getMedicRecomandare() {
		return medicRecomandare;
	}

	public void setMedicRecomandare(MedicAux medicRecomandare) {
		this.medicRecomandare = medicRecomandare;
	}

	public MedicAux getAsistent() {
		return asistent;
	}

	public void setAsistent(MedicAux asistent) {
		this.asistent = asistent;
	}

	//urmatoarea funcitie e facuta de mine pentru mine
    public boolean hasTheseDetails(String details){
       return details.equals(nrRaport+" "+medic.getNume()+" "+dataProgramare);
    }
}
