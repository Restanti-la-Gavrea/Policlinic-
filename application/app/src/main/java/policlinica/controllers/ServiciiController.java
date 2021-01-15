package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import policlinica.users.Medic;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiciiController implements Initializable {

    private Medic user;

    @FXML ListView<String> serviciiList;
    @FXML TextField priceFld;
    @FXML Button submitBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML public void changePrice(){

    }

    public void setContext(Medic user){
        this.user = user;
    }
}
