package policlinica.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.MedicAux;
import policlinica.Programare;
import policlinica.RaportMedical;
import policlinica.Serviciu;
import policlinica.users.AsistentMedical;
import policlinica.users.Medic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RaportController implements Initializable {

    private VBox returnLayout;
    private Medic user;
    private BorderPane main;

    private RaportMedical raportMedical;

    @FXML private Label detailsLbl;
    @FXML private Label numePacientLbl;
    @FXML private Label numeMedicLbl;
    @FXML private ChoiceBox<String> medicRecomandantBox;
    @FXML private ChoiceBox<String> asistentBox;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBtn.managedProperty().bind(createBtn.visibleProperty());
        updateBtn.managedProperty().bind(updateBtn.visibleProperty());
        pBtn.managedProperty().bind(pBtn.visibleProperty());

        errLbl.managedProperty().bind(pBtn.visibleProperty());
        errLbl.setVisible(false);

        serviciiList.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(!raportMedical.isParafat()){
                        String temp = rezultatArea.getText();
                        raportMedical.getServiciu().get((int)old_val).setRezultat(temp);
                    }

                    rezultatArea.setText(raportMedical.getServiciu().get((int)(new_val)).getRezultat());

                });
    }

    @FXML public void goBack(){
        errLbl.setVisible(false);
        raportMedical = null;
        main.setCenter(returnLayout);
    }

    @FXML public void createRaport(){
        fillDataIntoRaport();

        //TODO update la Raport
        //TODO creare raport in baza de date

        goBack();
    }

    @FXML public void updateRaport(){
        fillDataIntoRaport();

        //TODO: dat update la report si in baza de date
        goBack();
    }

    @FXML public void parafeaza(){

        if(updateBtn.visibleProperty().getValue()&&!createBtn.visibleProperty().getValue())
            updateRaport();
        else if (!updateBtn.visibleProperty().getValue()&&createBtn.visibleProperty().getValue())
            createRaport();
        else {System.err.println("Eroare la parafare, nu se detecteaza scena");return;}

        if(!(user instanceof AsistentMedical))
        {
            System.out.println("intra doar daca e " + user.getClass());
        }

        goBack();
    }

    public void setRaportMedical(RaportMedical raportMedical){
        this.raportMedical = raportMedical;
    }

    public void setContext(Medic user, VBox returnLayout,  BorderPane main) {
        //asta se executa cand se apasa pe butonul pacienti
        errLbl.setVisible(false);

        this.main = main;
        this.user = user;
        this.returnLayout = returnLayout;
    }

    public void prepareNewRaport(Programare programare){
        if(!(user instanceof AsistentMedical))
            pBtn.setVisible(true);
        else pBtn.setVisible(false);

        updateBtn.setVisible(false);
        createBtn.setVisible(true);

        raportMedical = new RaportMedical(programare);

        fillDataIntoScreen();
        unlock();
    }

    public void loadRaport(RaportMedical raportMedical){
       if(raportMedical != null){
           this.raportMedical = raportMedical;

           if(!(user instanceof AsistentMedical))
               pBtn.setVisible(true);
           else pBtn.setVisible(false);

           updateBtn.setVisible(true);
           createBtn.setVisible(false);

           fillDataIntoScreen();

           if(raportMedical.isParafat())
           {
               lock();
               updateBtn.setVisible(false);
               pBtn.setVisible(false);
           }
           else
               unlock();
       }
       else{
           setErrorScreen();
           lock();
           pBtn.setVisible(false);
           createBtn.setVisible(false);
           updateBtn.setVisible(false);
           errLbl.setVisible(true);
       }
    }

    private void fillDataIntoScreen(){
        detailsLbl.setText("Raport Nr. " + raportMedical.getNrProgramare() + ", " + raportMedical.getDataProgramare() + " " + raportMedical.getOraProgramare());
        numeMedicLbl.setText(raportMedical.getNumeMedic() + " " + raportMedical.getPrenumeMedic());
        numePacientLbl.setText(raportMedical.getPacient().getNume() + " " + raportMedical.getPacient().getPrenume());

        fillBoxes();

        if(raportMedical.getMedicRecomandare() != null)
            medicRecomandantBox.getSelectionModel().select(raportMedical.getMedicRecomandare().getNume() + " " + raportMedical.getMedicRecomandare().getPrenume());

        if(raportMedical.getAsistent() != null)
            asistentBox.getSelectionModel().select(raportMedical.getAsistent().getNume() + " " + raportMedical.getAsistent().getPrenume());

        recomandariArea.setText(raportMedical.getRecomandari());
        simptomeArea.setText(raportMedical.getSimptome());
        diagnosticArea.setText(raportMedical.getDiagnostic());
    }

    private void fillDataIntoRaport(){

    }

    private void setErrorScreen(){
        detailsLbl.setText("A avut loc o eroare la incarcarea raportului");
        numePacientLbl.setText("Err");
        numeMedicLbl.setText("Err");
        medicRecomandantBox.getItems().removeAll();
        medicRecomandantBox.getItems().add("Err");
        medicRecomandantBox.getSelectionModel().select(0);
        asistentBox.getItems().removeAll();
        asistentBox.getItems().add("Err");
        asistentBox.getSelectionModel().select(0);
        rezultatArea.setText("Err");
        diagnosticArea.setText("Err");
        simptomeArea.setText("Err");
        recomandariArea.setText("Err");
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

    private void fillBoxes(){
        MedicAux[] medici = user.generateListaMedici();
        medicRecomandantBox.getItems().clear();
        for(MedicAux m: medici)
            medicRecomandantBox.getItems().add(m.getNume() + " " + m.getPrenume());

        ArrayList<Serviciu> list = raportMedical.getServiciu();
        serviciiList.getItems().clear();
        for(Serviciu s: list)
            serviciiList.getItems().add(s.getNume());

        ArrayList<MedicAux> asistenti = user.getListaAsistenti();
        asistentBox.getItems().clear();
        for(MedicAux a: asistenti)
            asistentBox.getItems().add(a.getNume() + " " + a.getPrenume());

    }
}
