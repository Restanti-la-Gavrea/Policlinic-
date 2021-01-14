package policlinica;

public class RaportMedical {

    private int nrRaport;
    private int nrProgramare;
    //urmatoarele sunt informatiile din programare
    private String dataProgramare;
    private String oraProgramare;
    //informatii despre Medic
    private String numeMedic;
    private String prenumeMedic;
    private String nrCMedic;
    //pacient
    private Pacient pacient;

    private String simptome;
    private String diagnostic;
    private String recomandari;

    private String[] serviciu;
    private String[] rezultatServiciu;

    private boolean parafat;

   //o sa fac contructori pe masura ce-mi trebuie
   public RaportMedical(){
        nrRaport = -1;
        dataProgramare = null;
        oraProgramare = null;
        numeMedic = null;
        prenumeMedic = null;
        nrCMedic = null;
        pacient = null;
        simptome = null;
        diagnostic = null;
        recomandari = null;

        serviciu = null;
        
        rezultatServiciu = null;

        parafat = false;
    }

    public RaportMedical(int nrProgramare, String dataProgramare, String oraProgramare, String numeMedic, String prenumeMedic, String nrCMedic, Pacient pacient, String[] serviciu) {
        this.nrProgramare = nrProgramare;
        this.dataProgramare = dataProgramare;
        this.oraProgramare = oraProgramare;
        this.numeMedic = numeMedic;
        this.prenumeMedic = prenumeMedic;
        this.nrCMedic = nrCMedic;
        this.pacient = pacient;
        this.serviciu = serviciu;

        simptome = null;
        diagnostic = null;
        rezultatServiciu = null;
        recomandari = null;

        parafat = false;
    }

    //funtiile de set nu fac nimic daca e parafat raportul
    public int getNrRaport() { return nrRaport; }
    public void setNrRaport(int nrRaport) { if(!parafat) this.nrRaport = nrRaport; }
    public String getDataProgramare() { return dataProgramare; }
    public void setDataProgramare(String dataProgramare) { if(!parafat)  this.dataProgramare = dataProgramare; }
    public String getOraProgramare() { return oraProgramare;  }
    public void setOraProgramare(String oraProgramare) { if(!parafat)  this.oraProgramare = oraProgramare; }
    public String getNumeMedic() {return numeMedic; }
    public void setNumeMedic(String numeMedic) { if(!parafat) this.numeMedic = numeMedic; }
    public String getPrenumeMedic() { return prenumeMedic; }
    public void setPrenumeMedic(String prenumeMedic) { if(!parafat) this.prenumeMedic = prenumeMedic; }
    public String getNrCMedic() { return nrCMedic;}
    public void setNrCMedic(String nrCMedic) { if(!parafat)  this.nrCMedic = nrCMedic; }
    public Pacient getPacient() { return pacient;  }
    public void setPacient(Pacient pacient) { if(!parafat) this.pacient = pacient; }
    public String getSimptome() {return simptome; }
    public void setSimptome(String simptome) { if(!parafat) this.simptome = simptome; }
    public String getDiagnostic() {  return diagnostic;  }
    public void setDiagnostic(String diagnostic) { if(!parafat) this.diagnostic = diagnostic; }
    public String getRecomandari() { return recomandari;  }
    public void setRecomandari(String recomandari) { if(!parafat) this.recomandari = recomandari; }
    public String[] getServiciu() { return serviciu;  }
    public void setServiciu(String[] serviciu) { if(!parafat) this.serviciu = serviciu; }
    public String[] getRezultatServiciu() { return rezultatServiciu; }
    public void setRezultatServiciu(String[] rezultatServiciu) { if(!parafat) this.rezultatServiciu = rezultatServiciu; }
    public void parafeaza(){ if(!parafat) parafat = true; }
    public boolean isParafat(){ return parafat;}

    public int getNrProgramare() {
		return nrProgramare;
	}

	public void setNrProgramare(int nrProgramare) {
		this.nrProgramare = nrProgramare;
	}

	public void setParafat(boolean parafat) {
		this.parafat = parafat;
	}

	public String getServiciuIndex(int i){return serviciu[i];}
    public String getRezultatIndex(int i){return rezultatServiciu[i];}

    //urmatoarea funcitie e facuta de mine pentru mine
    public boolean hasTheseDetails(String details){
       return details.equals(nrRaport+" "+numeMedic+" "+dataProgramare);
    }
}
