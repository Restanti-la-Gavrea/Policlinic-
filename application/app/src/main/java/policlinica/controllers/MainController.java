package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //main menu buttons
    @FXML
    Button userBtn;
    @FXML Button administratorBtn;
    @FXML Button angajatiBtn;
    @FXML Button orarBtn;
    @FXML Button finanteBtn;
    @FXML Button pacientiBtn;
    @FXML Button programariBtn;
    @FXML Button serviciiBtn;
    @FXML Button logOutBtn;

    @FXML
    BorderPane main;

    private Scene logInScene;
    private LogInController logInController;

    private VBox userDataLayout;
    private UserController userController;
    private ScrollPane angajatiListLayout;
    private AngajatiListController angajatiListController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        administratorBtn.managedProperty().bind(administratorBtn.visibleProperty());
        angajatiBtn.managedProperty().bind(angajatiBtn.visibleProperty());
        pacientiBtn.managedProperty().bind(pacientiBtn.visibleProperty());
        programariBtn.managedProperty().bind(pacientiBtn.visibleProperty());
        serviciiBtn.managedProperty().bind(pacientiBtn.visibleProperty());
    }

    @FXML public void setUserLayout() throws Exception{
        main.setCenter(userDataLayout);
    }
    @FXML public void setAdministratorLayout() throws Exception{

    }
    @FXML public void setAngajatiLayout() throws Exception{
        main.setCenter(angajatiListLayout);

    }
    @FXML public void setOrarLayout(){
    }
    @FXML public void setFinanteLayout(){
    }
    @FXML public void setPacientiLayout(){
    }
    @FXML public void setProgramariLayout(){
    }
    @FXML public void setServiciiLayout(){
    }
    @FXML public void logOut(){
        double height = ((Stage)(Stage.getWindows().get(0))).getHeight();
        double width = ((Stage)(Stage.getWindows().get(0))).getWidth();

        Stage appStage = (Stage)(Stage.getWindows().get(0));
        appStage.setScene(logInScene);
        appStage.setWidth(width);
        appStage.setHeight(height);
    }

    public void setLogInController(LogInController logInController) { this.logInController = logInController; }
    public void setLogInScene(Scene logInScene) { this.logInScene = logInScene; }

    public void setButtons(boolean administratorBtn, boolean angajatiBtn, boolean pacientiBtn, boolean programariBtn, boolean serviciiBtn){
        this.administratorBtn.setVisible(administratorBtn);
        this.angajatiBtn.setVisible(angajatiBtn);
        this.pacientiBtn.setVisible(pacientiBtn);
        this.programariBtn.setVisible(programariBtn);
        this.serviciiBtn.setVisible(serviciiBtn);
    }

    public void subLayoutLoading() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userLayout.fxml"));
        userDataLayout = loader.load();
        userController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/angajatiList.fxml"));
        angajatiListLayout = loader.load();
        angajatiListController = loader.getController();

    }

}
