package policlinica.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.Pacient;
import policlinica.RaportMedical;
import policlinica.users.Medic;
import policlinica.users.Medical;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PacientiController implements Initializable {

    @FXML Button nextContextBtn;
    @FXML Button backContextBtn;
    @FXML ListView<String> contextList;

    private ArrayList<Pacient> pacienti;
    private ArrayList<RaportMedical> rapoarteMedicale;

    private Medic user;
    private VBox raportLayout;
    private RaportController raportController;
    private BorderPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backContextBtn.managedProperty().bind(backContextBtn.visibleProperty());
        backContextBtn.setVisible(false);

        nextContextBtn.setOnAction(this::showReportsHandler);

        pacienti = new ArrayList<>();
        rapoarteMedicale = new ArrayList<>();
    }

    private void showReportsHandler(ActionEvent e){ showReports();}
    public void showReports(){

        String numePacient = contextList.getSelectionModel().getSelectedItem();
        System.out.println("P selected: " + numePacient);

        if(numePacient != null){
            Pacient tempPacient = null;
            for(Pacient p: pacienti) {
                if(p.hasFullName(numePacient))
                    tempPacient = p;
            }

            rapoarteMedicale = user.getListaRapoare(tempPacient);


            ObservableList<String> list = FXCollections.observableArrayList();
            for(RaportMedical r: rapoarteMedicale)
            {
                list.add(r.getNrRaport()+" "+r.getNumeMedic()+" "+r.getDataProgramare());
                System.out.println(r.getDataProgramare() + " here i tried to put a day");
            }

            contextList.setItems(list);

            backContextBtn.setVisible(true);
            nextContextBtn.setOnAction(this::openReportHandler);
            nextContextBtn.setText("Vezi Raportul");
            backContextBtn.setOnAction(this::showPacientiHandler);
            backContextBtn.setText("Vezi Pacientii");
        }
    }

    private void showPacientiHandler(ActionEvent e){ showPacienti();}
    public void showPacienti(){

        pacienti = user.getListaPacienti();

        ObservableList<String> list = FXCollections.observableArrayList();
        for(Pacient p: pacienti) {
            list.add(p.getNume()+" "+p.getPrenume());
        }
        contextList.setItems(list);

        nextContextBtn.setOnAction(this::showReportsHandler);
        nextContextBtn.setText("Arata Rapoartele Pacientului");
        backContextBtn.setVisible(false);
    }

    private void openReportHandler(ActionEvent e){ openReport();}
    public void openReport(){

        String raportDetails = contextList.getSelectionModel().getSelectedItem();
        System.out.println("R selected: " + raportDetails);

       if(raportDetails != null){
           RaportMedical tempRaport = null;

           for(RaportMedical r: rapoarteMedicale)
               if(r.hasTheseDetails(raportDetails))
                   tempRaport = r;

           raportController.loadRaport(tempRaport);

           main.setCenter(raportLayout);
       }
    }

    public void setContext(Medic user, VBox raportLayout, RaportController raportController, BorderPane main){
        //asta se executa cand se apasa pe butonul pacienti
        pacienti = user.getListaPacienti();

        this.main = main;
        this.user = user;
        this.raportLayout = raportLayout;
        this.raportController = raportController;

        ObservableList<String> list = FXCollections.observableArrayList();
        /*for(RaportMedical r: rapoarteMedicale)
            list.add(r.getNrRaport()+" "+r.getNumeMedic()+" "+r.getDataProgramare());*/
        for(Pacient p: pacienti) {
            list.add(p.getNume()+" "+p.getPrenume());
        }
        contextList.setItems(list);
    }

}
