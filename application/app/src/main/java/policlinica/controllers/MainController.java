package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import policlinica.users.Admin;
import policlinica.users.ResurseUmane;
import policlinica.users.SuperAdmin;
import policlinica.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private User user;

    //main menu buttons
    @FXML Button userBtn;
    @FXML Button administratorBtn;
    @FXML Button angajatiBtn;
    @FXML Button orarBtn;
    @FXML Button finanteBtn;
    @FXML Button pacientiBtn;
    @FXML Button programariBtn;
    @FXML Button serviciiBtn;
    @FXML Button logOutBtn;

    @FXML BorderPane main;

    private Scene logInScene;
    private LogInController logInController;

    private VBox userDataLayout;
    private UserController userController;

    private VBox angajatiListLayout;
    private AngajatiListController angajatiListController;

    private VBox finanteLayout;
    private FinanteController finanteController;

    private VBox orarLayout;
    private OrarController orarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        administratorBtn.managedProperty().bind(administratorBtn.visibleProperty());
        angajatiBtn.managedProperty().bind(angajatiBtn.visibleProperty());
        pacientiBtn.managedProperty().bind(pacientiBtn.visibleProperty());
        programariBtn.managedProperty().bind(pacientiBtn.visibleProperty());
        serviciiBtn.managedProperty().bind(pacientiBtn.visibleProperty());
    }

    @FXML public void setUserLayout() throws Exception{
        userController.setNumeLbl(user.getNume());
        userController.setPrenumeLbl(user.getPrenume());
        userController.setAdresaLbl(user.getAdresa());
        userController.setAngajatDataLbl(user.getDataAngajarii());
        userController.setEmailLbl(user.getEmail());
        userController.setIbanLbl(user.getIban());
        userController.setNrContractLbl(user.getNrContract());
        userController.setNrTelefonLbl(user.getNrTelefon());

        userController.switchToDetails();
        userController.hideEditBtn();

        main.setCenter(userDataLayout);
    }
    @FXML public void setAdministratorLayout() throws Exception{

    }
    @FXML public void setAngajatiLayout() throws Exception{

        angajatiListController.fillWithEmployees(userDataLayout, userController, main);
        angajatiListController.setButtonForDetails();

        main.setCenter(angajatiListLayout);
    }
    @FXML public void setOrarLayout(){

        angajatiListController.fillWithEmployees(orarLayout, orarController, main);
        angajatiListController.setButtonForOrar();

        main.setCenter(angajatiListLayout);
    }
    @FXML public void setFinanteLayout(){
        main.setCenter(finanteLayout);
    }
    @FXML public void setPacientiLayout(){
    }
    @FXML public void setProgramariLayout(){
    }
    @FXML public void setServiciiLayout(){
    }
    @FXML public void logOut(){

        StackPane layout = new StackPane();
        Label label = new Label("Alege din meniul din stanga pentru a incepe");
        layout.getChildren().add(label);

        main.setCenter(layout);

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


        loader = new FXMLLoader(getClass().getResource("/finanteLayout.fxml"));
        finanteLayout = loader.load();
        finanteController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/orarViewLayout.fxml"));
        orarLayout = loader.load();
        orarController = loader.getController();
    }


    public void setUser(User user) {
        this.user = user;
        if(user instanceof ResurseUmane) {
            angajatiListController.setUser((ResurseUmane) user);
        }
    }
}
