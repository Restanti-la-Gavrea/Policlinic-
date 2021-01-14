package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.RaportMedical;
import policlinica.users.AsistentMedical;
import policlinica.users.Medic;
import policlinica.users.Medical;

import java.net.URL;
import java.util.ResourceBundle;

public class RaportController implements Initializable {

    private VBox returnLayout;
    private Medical user;
    private BorderPane main;

    private RaportMedical raportMedical;

    @FXML private Label detailsLbl;
    @FXML private Label numePacientLbl;
    @FXML private Label numeMedicLbl;
    @FXML private ChoiceBox<String> medicRecomandantBox;

    @FXML private TextArea simptomeArea;
    @FXML private ListView<String> serviciiList;
    @FXML private TextArea rezultatArea;
    @FXML private TextArea diagnosticArea;
    @FXML private TextArea recomandariArea;

    @FXML private Button createBtn;
    @FXML private Button updateBtn;
    @FXML private Button pBtn;
    @FXML private Button backButton;

    @FXML private Label errLbl;

    RaportMedical workingRaport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBtn.managedProperty().bind(createBtn.visibleProperty());
        updateBtn.managedProperty().bind(updateBtn.visibleProperty());
        pBtn.managedProperty().bind(pBtn.visibleProperty());

        errLbl.managedProperty().bind(pBtn.visibleProperty());
        errLbl.setVisible(false);

    }

    @FXML public void goBack(){
        errLbl.setVisible(false);
        raportMedical = null;
        main.setCenter(returnLayout);
    }

    @FXML public void createRaport(){


        goBack();
    }

    @FXML public void updateRaport(){

        goBack();
    }

    @FXML public void parafeaza(){
        if(user instanceof Medic && !(user instanceof AsistentMedical))
        {
            System.out.println("intra doar daca e " + user.getClass());
        }


        goBack();
    }

    public void setRaportMedical(RaportMedical raportMedical){
        this.raportMedical = raportMedical;
    }

    public void setContext(Medical user, VBox returnLayout,  BorderPane main) {
        //asta se executa cand se apasa pe butonul pacienti
        errLbl.setVisible(false);

        this.main = main;
        this.user = user;
        this.returnLayout = returnLayout;
    }

    public void prepareNewRaport(){
        if(user instanceof Medic && !(user instanceof AsistentMedical))
            pBtn.setVisible(true);
        else pBtn.setVisible(false);

        updateBtn.setVisible(false);
        createBtn.setVisible(true);

        fillData();
        unlock();
    }

    public void loadRaport(RaportMedical raportMedical){
        this.raportMedical = raportMedical;

        if(user instanceof Medic && !(user instanceof AsistentMedical))
            pBtn.setVisible(true);
        else pBtn.setVisible(false);

        updateBtn.setVisible(true);
        createBtn.setVisible(false);

        fillData();

        if(raportMedical.isParafat())
            lock();
        else
            unlock();
    }

    private void fillData(){

    }

    private void lock(){
        simptomeArea.setDisable(true);
        rezultatArea.setDisable(true);
        diagnosticArea.setDisable(true);
        recomandariArea.setDisable(true);

        updateBtn.setVisible(false);
        createBtn.setVisible(false);
        pBtn.setVisible(false);
    }
    private void unlock(){
        simptomeArea.setDisable(false);
        rezultatArea.setDisable(false);
        diagnosticArea.setDisable(false);
        recomandariArea.setDisable(false);
    }
}
