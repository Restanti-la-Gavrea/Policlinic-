package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.users.Medic;
import policlinica.users.Medical;

import java.net.URL;
import java.util.ResourceBundle;

public class CreareProgramareController implements Initializable {

    @FXML private TextField numePacientFld;
    @FXML private TextField prenumePacientFld;
    @FXML private TextField cnpFld;

    @FXML private ListView<String> specialitateList;
    @FXML private ListView<String> serviciiList;
    @FXML private ListView<String> medicList;

    @FXML private DatePicker datePicker;
    @FXML private Spinner<String> oraPicker;

    @FXML private Button createBtn;
    @FXML private Button backBtn;

    @FXML private Label errLbl;

    private Medical user;
    private VBox programareLayout;
    private ProgramareController programareController;
    private BorderPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML public void creeazaProgramare(){
        //TODO: efectiv crearea programarii
    }
    @FXML public void goBack(){
        main.setCenter(programareLayout);
    }

    @FXML public void setContext(Medical user, VBox programareLayout, ProgramareController programareController, BorderPane main){
        errLbl.setVisible(false);
        this.user = user;
        this.programareLayout = programareLayout;
        this.programareController = programareController;
        this.main = main;
    }

}
