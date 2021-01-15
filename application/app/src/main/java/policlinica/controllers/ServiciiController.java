package policlinica.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import policlinica.Serviciu;
import policlinica.ServiciuCustom;
import policlinica.Specialitate;
import policlinica.users.Medic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServiciiController implements Initializable {

    private Medic user;

    @FXML ListView<String> serviciiList;
    @FXML TextField priceFld;
    @FXML Button submitBtn;

    ArrayList<ServiciuCustom> servicii;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        serviciiList.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {

            int index = serviciiList.getSelectionModel().getSelectedIndex();
            if(index != -1){
              //this does nothing
            }
        });

    }

    @FXML public void changePrice(){
        int index = serviciiList.getSelectionModel().getSelectedIndex();
        if(index != -1){
            servicii.get(index).setPret(priceFld.getText());
            //TODO submit changes
        }
    }

    public void setContext(Medic user){

        this.user = user;

    }
}
