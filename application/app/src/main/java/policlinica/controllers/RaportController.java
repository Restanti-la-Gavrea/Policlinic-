package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.RaportMedical;
import policlinica.users.Medical;

import java.net.URL;
import java.util.ResourceBundle;

public class RaportController implements Initializable {

    private VBox returnLayout;
    private Medical user;
    private BorderPane main;

    private RaportMedical raportMedical;

    @FXML Button backButton;
    @FXML
    TextArea text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setDisable(true);
    }

    @FXML public void goBack(){
        main.setCenter(returnLayout);
    }

    public void setRaportMedical(RaportMedical raportMedical){
        this.raportMedical = raportMedical;
    }

    public void setContext(Medical user, VBox returnLayout,  BorderPane main) {
        //asta se executa cand se apasa pe butonul pacienti

        this.main = main;
        this.user = user;
        this.returnLayout = returnLayout;
    }
}
