package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.Programare;
import policlinica.users.Medical;
import policlinica.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgramareController implements Initializable {

    @FXML TableView<Programare> programareTable;
    @FXML TableColumn<Programare, String> numePacientCol;
    @FXML TableColumn<Programare, String> specialitateCol;
    @FXML TableColumn<Programare, String> dataOraCol;
    @FXML TableColumn<Programare, String> achitatCol;
    @FXML TableColumn<Programare, String> raportCol;

    @FXML Button raportBtn;
    @FXML Button plataBtn;
    @FXML Button programareBtn;

    private VBox raportLayout;
    private RaportController raportController;
    private VBox creareProgramareLayout;
    private CreareProgramareController creareProgramareController;

    private BorderPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //butoanele care se vad, se schimba in functie de cine la vede
        raportBtn.managedProperty().bind(raportBtn.visibleProperty());
        plataBtn.managedProperty().bind(plataBtn.visibleProperty());
        programareBtn.managedProperty().bind(programareBtn.visibleProperty());

        numePacientCol.setCellValueFactory(new PropertyValueFactory<>("numeComplet"));
        specialitateCol.setCellValueFactory(new PropertyValueFactory<>("specialitate"));
        dataOraCol.setCellValueFactory(new PropertyValueFactory<>("dataOra"));
        achitatCol.setCellValueFactory(new PropertyValueFactory<>("achitat"));
        raportCol.setCellValueFactory(new PropertyValueFactory<>("raport"));
    }

    @FXML public void creeazaRaport(){

        //solutie temporara
        raportController.prepareNewRaport(new Programare());
        main.setCenter(raportLayout);
    }
    @FXML public void creeazaPlata(){
        //TODO: tot ce tine de plata, chitanta, bon fiscal etc.
    }
    @FXML public void creeazaProgramare(){
        main.setCenter(creareProgramareLayout);
    }

    public void setContext(Medical user, VBox creareProgramareLayout, CreareProgramareController c, VBox raportLayout, RaportController r, BorderPane main){
        this.creareProgramareLayout = creareProgramareLayout;
        creareProgramareController = c;
        this.raportLayout = raportLayout;
        raportController = r;
        this.main = main;
        //TODO: ascuns butoane in functie tipul medical



    }

}
