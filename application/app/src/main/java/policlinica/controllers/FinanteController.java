package policlinica.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import policlinica.FinanteTableItem;
import policlinica.MedicAux;
import policlinica.MonthName;
import policlinica.calendar.CalendarAux;
import policlinica.users.AsistentMedical;
import policlinica.users.Economic;
import policlinica.users.Medic;
import policlinica.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class FinanteController implements Initializable {

    private User user;
    private int month;
    private int year;

    MedicAux[] medicList;

    private String currentView;

    @FXML ChoiceBox<String> viewBox;
    @FXML ChoiceBox<String> detailBox;
    @FXML HBox extraBox;
    @FXML Label descriptiveLbl;
    @FXML Button previousBtn;
    @FXML Button nextBtn;

    @FXML TableView<FinanteTableItem> finanteTable;
    @FXML TableColumn<FinanteTableItem, String> numeCol;
    @FXML TableColumn<FinanteTableItem, String> detailsCol;
    @FXML TableColumn<FinanteTableItem, String> sumCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        extraBox.managedProperty().bind(extraBox.visibleProperty());

        numeCol.setCellValueFactory(new PropertyValueFactory<>("numeComplet"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        sumCol.setCellValueFactory(new PropertyValueFactory<>("sum"));

        viewBox.setAccessibleText("Alege Vederea");
        detailBox.setAccessibleText("Alege Medicul");

        //finanteTable.setItems(FinanteTableItem.getSample());

        viewBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    try{
                        currentView = viewBox.getItems().get(viewBox.getSelectionModel().getSelectedIndex());}
                    catch (IndexOutOfBoundsException e){
                        System.err.println("IndexOutOfBoundsException , FinanteController");
                        viewBox.getSelectionModel().clearSelection();
                        currentView = null;
                    }
                    updateTable();
                });

        detailBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    updateTable();
                });

        month = CalendarAux.getCurrentMonth();
        year = CalendarAux.getCurrentYear();

        medicList = null;
    }

    public void setUser(User user){
        this.user = user;
        currentView = null;
        medicList = null;
        viewBox.getSelectionModel().clearSelection();

        while(viewBox.getItems().size() > 0) {
            viewBox.getItems().remove(0);
        }

        viewBox.getItems().add("Salariul Propriu");

        if(user instanceof Economic){
            detailBox.setVisible(true);
            viewBox.getItems().addAll("Profitul Total", "Profitul Medic/Unitate", "Profitul Medic/Specialitate");

            medicList = user.generateListaMedici();
            for(MedicAux i: medicList){
                detailBox.getItems().add(i.getNume() + " " + i.getPrenume());
            }
        }
        else
            detailBox.setVisible(false);

        if(user instanceof Medic && !(user instanceof AsistentMedical))
            viewBox.getItems().add("Profitul Propriu");
    }

    @FXML public void goPrevious(){
        month--;
        if (month<0) {
            year--;
            month = 11;
        }
        updateTable();
    }
    @FXML public void goNext(){
        month++;
        if (month>11) {
            year++;
            month = 0;
        }
        updateTable();
    }

    private void updateTable(){
        descriptiveLbl.setText(MonthName.getMonthName(month)+", "+year);

        if(currentView == null)
            return;

        if(currentView.equals("Salariul Propriu")) {
            double temp = user.getSalariu(month+1, year);
            FinanteTableItem tempTable = new FinanteTableItem(user.getNume()+" "+user.getPrenume(), "Salariu", ""+temp);
            ObservableList<FinanteTableItem> list = FXCollections.observableArrayList();
            list.add(tempTable);

            finanteTable.setItems(list);
        }
        if(currentView.equals("Profitul Propriu")){
            double temp = ((Medic)user).profitMedic(month+1, year);
            FinanteTableItem tempTable = new FinanteTableItem(user.getNume()+" "+user.getPrenume(), "Profit adus", ""+temp);
            ObservableList<FinanteTableItem> list = FXCollections.observableArrayList();
            list.add(tempTable);

            finanteTable.setItems(list);
        }
        if(currentView.equals("Profitul Total")){
            double temp = ((Economic)user).getProfit(month+1, year);
            FinanteTableItem tempTable = new FinanteTableItem("Total profit", "lant policlinici", ""+temp);
            ObservableList<FinanteTableItem> list = FXCollections.observableArrayList();
            list.add(tempTable);

            finanteTable.setItems(list);
        }
        if(currentView.equals("Profitul Medic/Unitate")) {
            if(detailBox.getSelectionModel().getSelectedIndex() >= 0) {
                String nrContract = medicList[detailBox.getSelectionModel().getSelectedIndex()].getNrContract();
                FinanteTableItem[] temp = ((Economic)user).profitPerUnitate(nrContract, month+1, year);

                ObservableList<FinanteTableItem> list = FXCollections.observableArrayList(temp);
                finanteTable.setItems(list);
            }
        }
        if(currentView.equals("Profitul Medic/Specialitate")) {
            if(detailBox.getSelectionModel().getSelectedIndex() >= 0) {
                String nrContract = medicList[detailBox.getSelectionModel().getSelectedIndex()].getNrContract();
                FinanteTableItem[] temp = ((Economic)user).profitPerSpecialitate(nrContract, month+1, year);

                ObservableList<FinanteTableItem> list = FXCollections.observableArrayList(temp);
                finanteTable.setItems(list);
            }
        }
    }
}
