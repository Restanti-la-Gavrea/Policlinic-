package policlinica.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.Programare;
import policlinica.users.AsistentMedical;
import policlinica.users.Medic;
import policlinica.users.Medical;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProgramareController implements Initializable {

    private Medical user;
    private ArrayList<Programare> programari;

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

        int i = programareTable.getSelectionModel().getSelectedIndex();
        if(i != -1){

            raportController.prepareNewRaport(programari.get(i));
            main.setCenter(raportLayout);
        }
    }
    @FXML public void creeazaPlata(){
        //TODO: tot ce tine de plata, chitanta, bon fiscal etc.
    }
    @FXML public void creeazaProgramare(){ main.setCenter(creareProgramareLayout); }

    public void setContext(Medical user, VBox creareProgramareLayout, CreareProgramareController c, VBox raportLayout, RaportController r, BorderPane main){
        this.creareProgramareLayout = creareProgramareLayout;
        creareProgramareController = c;
        this.raportLayout = raportLayout;
        raportController = r;
        this.main = main;
        this.user = user;

        programari = user.getProgramari();

        ObservableList<Programare> list = FXCollections.observableArrayList(programari);
        programareTable.setItems(list);

        if(user instanceof Medic){
            raportBtn.setVisible(true);
            plataBtn.setVisible(false);
            programareBtn.setVisible(false);
        }
        else{
            raportBtn.setVisible(false);
            plataBtn.setVisible(true);
            programareBtn.setVisible(true);
        }

    }

}
