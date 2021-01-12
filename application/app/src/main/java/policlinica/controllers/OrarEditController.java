package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OrarEditController implements Initializable {

    private VBox orarLayout;
    BorderPane main;

    @FXML private VBox saptamanalBox;
    @FXML private TextField luniFld;
    @FXML private TextField martiFld;
    @FXML private TextField miercuriFld;
    @FXML private TextField joiFld;
    @FXML private TextField vineriFld;
    @FXML private TextField sambataFld;
    @FXML private TextField duminicaFld;
    @FXML private Button saptamanalSubmitBtn;

    @FXML private VBox specificBox;
    @FXML private Label specificDayLbl;
    @FXML private TextField specificFld;
    @FXML private Button specificSubmitBtn;

    @FXML private VBox concediuBox;
    @FXML private DatePicker firstDayFld;
    @FXML private DatePicker lastDayFld;
    @FXML private Button concediuSubmitBtn;

    @FXML public void submitSaptamanal(){

    }
    @FXML public void submitSpecific(){

    }
    @FXML public void submitConcediu(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concediuBox.managedProperty().bind(concediuBox.visibleProperty());
        saptamanalBox.managedProperty().bind(saptamanalBox.visibleProperty());
        specificBox.managedProperty().bind(specificBox.visibleProperty());
    }

    public void showSaptamanal(){
        concediuBox.setVisible(false);
        saptamanalBox.setVisible(true);
        specificBox.setVisible(false);
    }
    public void showSpecific(){
        concediuBox.setVisible(false);
        saptamanalBox.setVisible(false);
        specificBox.setVisible(true);
    }
    public void showConcediu(){
        concediuBox.setVisible(true);
        saptamanalBox.setVisible(false);
        specificBox.setVisible(false);
    }

    @FXML public void goBack(){
        main.setCenter(orarLayout);
    }

    public void setContext(VBox orarLayout, BorderPane main){
        this.orarLayout = orarLayout;
        this.main = main;
    }
}
