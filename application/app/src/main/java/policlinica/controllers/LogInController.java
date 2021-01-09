package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import policlinica.Test;
import policlinica.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    private User user;

    //login Screen elements
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML private Button logInBtn;
    @FXML private Button testBtn;

    @FXML private Label errLogInLbl;

    private Scene mainScene;
    private MainController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errLogInLbl.managedProperty().bind(errLogInLbl.visibleProperty());
        errLogInLbl.setVisible(false);
    }

    @FXML public void logIn () throws Exception{

        String username = usernameField.getText();
        String password = passwordField.getText();

        user = (new User()).Autentificator(username, password);

        if(user == null)
        {
            errLogInLbl.setVisible(true);
            return;
        }

        //change scene
        double height = ((Stage)(Stage.getWindows().get(0))).getHeight();
        double width = ((Stage)(Stage.getWindows().get(0))).getWidth();

        //mainController.setButtons(false, false, false, false, false);

        Stage appStage = (Stage)(Stage.getWindows().get(0));

        appStage.setScene(mainScene);
        appStage.setWidth(width);
        appStage.setHeight(height);
    }

    @FXML public void test(){
        new Test();
    }

    public TextField getUsernameField() { return usernameField; }
    public PasswordField getPasswordField() { return passwordField; }
    public Button getLogInBtn() { return logInBtn; }
    public Button getTestBtn() { return testBtn; }

    public void setMainController(MainController mainController) { this.mainController = mainController; }
    public void setMainScene(Scene mainScene) { this.mainScene = mainScene; }


}
