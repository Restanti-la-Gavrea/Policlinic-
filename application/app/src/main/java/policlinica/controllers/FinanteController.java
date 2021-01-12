package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import policlinica.FinanteTableItem;

import java.net.URL;
import java.util.ResourceBundle;

public class FinanteController implements Initializable {

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

       // viewBox.getItems().addAll("Salariul Propriu", "Profitul Propriu", "Profitul Total", "Profitul Medic/Unitate", "Profitul Medic/Specialitate");
       // detailBox.getItems().addAll("Aici Lista de medici", "medic1", "medic2");

        extraBox.managedProperty().bind(extraBox.visibleProperty());

        numeCol.setCellValueFactory(new PropertyValueFactory<>("numeComplet"));
        detailsCol.setCellValueFactory(new PropertyValueFactory<>("details"));
        sumCol.setCellValueFactory(new PropertyValueFactory<>("sum"));

        finanteTable.setItems(FinanteTableItem.getSample());
    }

    @FXML public void goPrevious(){}
    @FXML public void goNext(){}

}
