package policlinica.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.MedicAux;
import policlinica.Programare;
import policlinica.Serviciu;
import policlinica.Specialitate;
import policlinica.calendar.CalendarAux;
import policlinica.calendar.Day;
import policlinica.users.Medic;
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
    @FXML private ChoiceBox<String> oraPicker;

    @FXML private Button createBtn;
    @FXML private Button backBtn;

    @FXML private Label errLbl;

    private Receptioner user;
    private VBox programareLayout;
    private ProgramareController programareController;
    private BorderPane main;

    private ArrayList<Specialitate> specialitati;
    private ArrayList<Serviciu> servicii;
    private ArrayList<Medic> mediciAux;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specialitateList.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

                    Specialitate temp = specialitati.get(specialitateList.getSelectionModel().getSelectedIndex());
                    servicii =  user.getServicii(temp.getNrSpecialitate());
                    ObservableList<String> list = FXCollections.observableArrayList();
                    for(Serviciu s: servicii)
                        list.add(s.getNume());
                    serviciiList.setItems(list);

                    mediciAux = user.getListaMedici(temp.getNrSpecialitate());
                    ObservableList<String> mediciList = FXCollections.observableArrayList();
                    for(Medic m: mediciAux)
                        mediciList.add(m.getNume()+" "+m.getPrenume());
                    medicList.setItems(mediciList);
                });

        serviciiList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        serviciiList.getSelectionModel().getSelectedIndices().addListener(
                (ListChangeListener.Change<? extends Integer> c) -> {

                });

        medicList.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

                });


        ObservableList<String> times = FXCollections.observableArrayList();
        times.addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");
        oraPicker.getItems().addAll(times);
    }

    @FXML public void creeazaProgramare(){
        String numePacient = numePacientFld.getText();
        if(numePacient == null || numePacient.equals("")) return;
        String prenumePacient = prenumePacientFld.getText();
        if(prenumePacient == null || prenumePacient.equals("")) return;
        String cnpPacient = cnpFld.getText();
        if(cnpPacient == null || cnpPacient.equals("")) return;;
        int specialitateIndex = specialitateList.getSelectionModel().getSelectedIndex();
        if( specialitateIndex == -1) return;
        ObservableList<Integer> serviciiIndexes = serviciiList.getSelectionModel().getSelectedIndices();
        for(Integer o: serviciiIndexes) {
            if(o == -1)
                return;
        }
        int medicIndex = medicList.getSelectionModel().getSelectedIndex();
        if(medicIndex == -1) return;
        String date = datePicker.getEditor().getText();
        if(date == null || date.equals("")) return;
        date = CalendarAux.parseToSQLDate(date);
        String ora = oraPicker.getValue();
        if(ora == null || ora.equals("")) return;

        Programare p = new Programare();
        p.setNumePacient(numePacient);
        p.setPrenumePacient(prenumePacient);
        p.setCnpPacient(cnpPacient);
        p.setSpecialitate(specialitati.get(specialitateIndex));
        ArrayList<Serviciu> list = new ArrayList<>();
        for(Integer o: serviciiIndexes)
            list.add(servicii.get(o.intValue()));
        p.setServicii(list);

        p.setDay(new Day(date));
        p.getDay().setIntervalOrar(ora);

        user.creeareProgramare(p);


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
        System.out.println("lungime get specialitati:" + specialitati.size());
        specialitateList.getItems().clear();
        for(Specialitate s: specialitati)
            specialitateList.getItems().add(s.getNume());
    }
}
