package policlinica;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinanteTableItem {

    private String numeComplet;
    private String details;
    private String sum;

    public FinanteTableItem(){
        numeComplet = null;
        details = null;
        sum = null;
    }

    public FinanteTableItem(String nume, String details, String sum) {
        this.numeComplet = nume;
        this.details = details;
        this.sum = sum;
    }

    public String getNumeComplet() { return numeComplet; }
    public void setNumeComplet(String nume) { this.numeComplet = nume; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getSum() { return sum; }
    public void setSum(String sum) { this.sum = sum; }

    public static ObservableList<FinanteTableItem> getSample(){
        ObservableList<FinanteTableItem> list = FXCollections.observableArrayList();
        list.add(new FinanteTableItem("Cocos Marian", "detalii 1", "1425"));
        list.add(new FinanteTableItem("Capitol cap", "luna cutare", "0"));
        list.add(new FinanteTableItem("Isus e sus", "unitatea cutare", "infinit"));
        list.add(new FinanteTableItem("Cravila", "specialitatea cutare", "99999"));
        list.add(new FinanteTableItem("Andre", "alta luna", "0"));

        return list;
    }

}
