package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import policlinica.users.Medical;

import java.net.URL;
import java.util.ResourceBundle;

public class PacientiController implements Initializable {

    @FXML Button contextBtn;
    @FXML ListView<String> contextList;

    private Medical user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUser(Medical user){
        this.user = user;
    }

}
