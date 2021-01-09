package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    //userTab labels
    @FXML private Label numeLbl;
    @FXML private Label prenumeLbl;
    @FXML private Label nrContractLbl;
    @FXML private Label adresaLbl;
    @FXML private Label ibanLbl;
    @FXML private Label nrTelefonLbl;
    @FXML private Label emailLbl;
    @FXML private Label angajatDataLbl;
    @FXML private Button userEditBtn;


    @FXML public void editUser(){

    }

    public void setNumeLbl(String nume){numeLbl.setText(nume);}
    public void setPrenumeLbl(String prenume){prenumeLbl.setText(prenume);}
    public void setNrContractLbl(String nrContract){nrContractLbl.setText(nrContract);}
    public void setAdresaLbl(String adresa){adresaLbl.setText(adresa);}
    public void setIbanLbl(String iban){ibanLbl.setText(iban);}
    public void setNrTelefonLbl(String nrTelefon){nrTelefonLbl.setText(nrTelefon);}
    public void setEmailLbl(String email){emailLbl.setText(email);}
    public void setAngajatDataLbl(String angajatData){angajatDataLbl.setText(angajatData);}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userEditBtn.managedProperty().bind(userEditBtn.visibleProperty());
    }
}
