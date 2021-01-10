package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    @FXML private TextField numeFld;
    @FXML private TextField prenumeFld;
    @FXML private TextField nrContractFld;
    @FXML private TextField adresaFld;
    @FXML private TextField ibanFld;
    @FXML private TextField nrTelefonFld;
    @FXML private TextField emailFld;
    @FXML private DatePicker angajatFld;
    @FXML private Button submitBtn;

    @FXML public void editUser(){
        numeFld.setVisible(true);
        prenumeFld.setVisible(true);
        nrTelefonFld.setVisible(true);
        nrContractFld.setVisible(true);
        adresaFld.setVisible(true);
        ibanFld.setVisible(true);
        emailFld.setVisible(true);
        angajatFld.setVisible(true);

        submitBtn.setVisible(true);

        numeLbl.setVisible(false);
        prenumeLbl.setVisible(false);
        nrTelefonLbl.setVisible(false);
        nrContractLbl.setVisible(false);
        adresaLbl.setVisible(false);
        ibanLbl.setVisible(false);
        emailLbl.setVisible(false);
        angajatDataLbl.setVisible(false);

        userEditBtn.setVisible(false);
    }

    @FXML public void editSubmit(){
        numeFld.setVisible(false);
        prenumeFld.setVisible(false);
        nrTelefonFld.setVisible(false);
        nrContractFld.setVisible(false);
        adresaFld.setVisible(false);
        ibanFld.setVisible(false);
        emailFld.setVisible(false);
        angajatFld.setVisible(false);

        submitBtn.setVisible(false);

        numeLbl.setVisible(true);
        prenumeLbl.setVisible(true);
        nrTelefonLbl.setVisible(true);
        nrContractLbl.setVisible(true);
        adresaLbl.setVisible(true);
        ibanLbl.setVisible(true);
        emailLbl.setVisible(true);
        angajatDataLbl.setVisible(true);

        userEditBtn.setVisible(true);
    }

    public void setNumeLbl(String nume){numeLbl.setText(nume);numeFld.setText(nume);}
    public void setPrenumeLbl(String prenume){prenumeLbl.setText(prenume);prenumeFld.setText(prenume);}
    public void setNrContractLbl(String nrContract){nrContractLbl.setText(nrContract);nrContractFld.setText(nrContract);}
    public void setAdresaLbl(String adresa){adresaLbl.setText(adresa);adresaFld.setText(adresa);}
    public void setIbanLbl(String iban){ibanLbl.setText(iban);ibanFld.setText(iban);}
    public void setNrTelefonLbl(String nrTelefon){nrTelefonLbl.setText(nrTelefon);nrTelefonFld.setText(nrTelefon);}
    public void setEmailLbl(String email){emailLbl.setText(email);emailFld.setText(email);}
    public void setAngajatDataLbl(String angajatData){angajatDataLbl.setText(angajatData);}
    //TODO: auto-fill date

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userEditBtn.managedProperty().bind(userEditBtn.visibleProperty());

        numeLbl.managedProperty().bind(numeLbl.visibleProperty());
        numeFld.managedProperty().bind(numeFld.visibleProperty());
        prenumeLbl.managedProperty().bind(prenumeLbl.visibleProperty());
        prenumeFld.managedProperty().bind(prenumeFld.visibleProperty());
        nrContractLbl.managedProperty().bind(nrContractLbl.visibleProperty());
        nrContractFld.managedProperty().bind(nrContractFld.visibleProperty());
        adresaLbl.managedProperty().bind(adresaLbl.visibleProperty());
        adresaFld.managedProperty().bind(adresaFld.visibleProperty());
        ibanLbl.managedProperty().bind(ibanLbl.visibleProperty());
        ibanFld.managedProperty().bind(ibanFld.visibleProperty());
        nrTelefonLbl.managedProperty().bind(nrTelefonLbl.visibleProperty());
        nrTelefonFld.managedProperty().bind(nrTelefonFld.visibleProperty());
        emailLbl.managedProperty().bind(emailLbl.visibleProperty());
        emailFld.managedProperty().bind(emailFld.visibleProperty());
        angajatDataLbl.managedProperty().bind(angajatDataLbl.visibleProperty());
        angajatFld.managedProperty().bind(angajatFld.visibleProperty());

        userEditBtn.managedProperty().bind(userEditBtn.visibleProperty());
        submitBtn.managedProperty().bind(submitBtn.visibleProperty());

        numeFld.setVisible(false);
        prenumeFld.setVisible(false);
        nrTelefonFld.setVisible(false);
        nrContractFld.setVisible(false);
        adresaFld.setVisible(false);
        ibanFld.setVisible(false);
        emailFld.setVisible(false);
        angajatFld.setVisible(false);
        submitBtn.setVisible(false);
    }
}
