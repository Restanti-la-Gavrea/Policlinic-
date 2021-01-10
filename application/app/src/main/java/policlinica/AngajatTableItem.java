package policlinica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AngajatTableItem {
    private String nrContract;
    private String nume;
    private String prenume;
    private String post;
    private String nrTelefon;

    public AngajatTableItem(){
        this.nrContract = null;
        this.nume = null;
        this.prenume = null;
        this.post = null;
        this.nrTelefon = null;
    }

    public AngajatTableItem(String nrContract, String nume, String prenume, String post, String nrTelefon) {
        this.nrContract = nrContract;
        this.nume = nume;
        this.prenume = prenume;
        this.post = post;
        this.nrTelefon = nrTelefon;
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
    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getNrTelefon() {
        return nrTelefon;
    }
    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    static public ObservableList<AngajatTableItem> getSampleList(){
        ObservableList<AngajatTableItem> list = FXCollections.observableArrayList();
        list.add(new AngajatTableItem("134", "Baragan", "Adrei-Iustin", "Doctor", "112"));
        list.add(new AngajatTableItem("666", "Bledea", "Dragos-Ioan", "HR", "911"));
        list.add(new AngajatTableItem("1536", "Badaran", "Adi", "Asistent", "0750000000"));
        return list;
    }
}
