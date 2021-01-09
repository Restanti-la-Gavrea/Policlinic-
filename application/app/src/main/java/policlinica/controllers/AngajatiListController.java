package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import policlinica.AngajatTableItem;

import java.net.URL;
import java.util.ResourceBundle;

public class AngajatiListController implements Initializable {

    @FXML
    TableView<AngajatTableItem> angajatiTable;
    @FXML
    TableColumn<AngajatTableItem, String> nrContractCol;
    @FXML TableColumn<AngajatTableItem, String> numeCol;
    @FXML TableColumn<AngajatTableItem, String> prenumeCol;
    @FXML TableColumn<AngajatTableItem, String> postCol;
    @FXML TableColumn<AngajatTableItem, String> nrTelefonCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nrContractCol.setCellValueFactory(new PropertyValueFactory<>("nrContract"));
        nrTelefonCol.setCellValueFactory(new PropertyValueFactory<>("nrTelefon"));
        numeCol.setCellValueFactory(new PropertyValueFactory<>("nume"));
        prenumeCol.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));
        angajatiTable.setItems(AngajatTableItem.getSampleList());
    }
}
