package policlinica.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.Specialitate;
import policlinica.users.Medical;
import policlinica.users.Receptioner;

import java.net.URL;
import java.util.ArrayList;
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

    private Receptioner user;
    private VBox programareLayout;
    private ProgramareController programareController;
    private BorderPane main;

    private ArrayList<Specialitate> specialitati;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specialitateList.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {



                });

        serviciiList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        serviciiList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) -> {

                });

        medicList.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {



                });

    }

    @FXML public void creeazaProgramare(){
        //TODO: efectiv crearea programarii
    }
    @FXML public void goBack(){
        main.setCenter(programareLayout);
    }

    @FXML public void setContext(Receptioner user, VBox programareLayout, ProgramareController programareController, BorderPane main){
        errLbl.setVisible(false);
        this.user = user;
        this.programareLayout = programareLayout;
        this.programareController = programareController;
        this.main = main;

        specialitati = user.getSpecialitati();
        specialitateList.getItems().clear();
        for(Specialitate s: specialitati)
            specialitateList.getItems().add(s.getNume());
    }

}
