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
import policlinica.users.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private User user;

    //main menu buttons
    @FXML Button userBtn;
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

    private VBox orarEditLayout;
    private OrarEditController orarEditController;

    private VBox pacientiLayout;
    private PacientiController pacientiController;

    private VBox raportLayout;
    private RaportController raportController;

    private VBox programareLayout;
    private ProgramareController programareController;

    private VBox creareProgramareLayout;
    private CreareProgramareController creareProgramareController;

    private VBox serviciiLayout;
    private ServiciiController serviciiController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        angajatiBtn.managedProperty().bind(angajatiBtn.visibleProperty());
        pacientiBtn.managedProperty().bind(pacientiBtn.visibleProperty());
        programariBtn.managedProperty().bind(programariBtn.visibleProperty());
        serviciiBtn.managedProperty().bind(serviciiBtn.visibleProperty());
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

        userController.setEditedUser(user);
        userController.switchToDetails();

        if(user instanceof SuperAdmin)
            userController.showEditBtn();
        else
            userController.hideEditBtn();

        main.setCenter(userDataLayout);
    }

    @FXML public void setAngajatiLayout() throws Exception{
        angajatiListController.fillWithEmployees(userDataLayout, userController, main);
        angajatiListController.setButtonForDetails();

        main.setCenter(angajatiListLayout);
    }
    @FXML public void setOrarLayout(){
        if (user instanceof ResurseUmane) {
            angajatiListController.fillWithEmployees(orarLayout, orarController, main);
            angajatiListController.setButtonForOrar();
            orarController.setContext(orarEditLayout, main, orarEditController, (ResurseUmane)user);
            orarEditController.setContext(orarLayout, orarController, main);
            main.setCenter(angajatiListLayout);
        }
        else {
            orarController.setUserShowCalendar(user);
            orarController.hideHrControls();
            main.setCenter(orarLayout);
        }
    }
    @FXML public void setFinanteLayout(){
        finanteController.setUser(user);
        main.setCenter(finanteLayout);
    }
    @FXML public void setPacientiLayout(){
        pacientiController.setContext((Medic)user, raportLayout, raportController, main);
        raportController.setContext((Medic)user, pacientiLayout, main);
        main.setCenter(pacientiLayout);
    }
    @FXML public void setProgramariLayout(){
        programareController.setContext((Medical)user, creareProgramareLayout, creareProgramareController, raportLayout, raportController, main);
        if(user instanceof Medic)
            raportController.setContext((Medic)user, programareLayout, main);
        if(user instanceof  Receptioner)
            creareProgramareController.setContext((Receptioner) user, programareLayout, programareController, main);

        main.setCenter(programareLayout);
    }
    @FXML public void setServiciiLayout(){
        main.setCenter(serviciiLayout);
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

    public void setButtons( boolean angajatiBtn, boolean pacientiBtn, boolean programariBtn, boolean serviciiBtn){
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

        loader = new FXMLLoader(getClass().getResource("/orarEditLayout.fxml"));
        orarEditLayout = loader.load();
        orarEditController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/pacientiLayout.fxml"));
        pacientiLayout = loader.load();
        pacientiController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/raportLayout.fxml"));
        raportLayout = loader.load();
        raportController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/programareLayout.fxml"));
        programareLayout = loader.load();
        programareController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/creareProgramareLayout.fxml"));
        creareProgramareLayout = loader.load();
        creareProgramareController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/serviciiCustom.fxml"));
        serviciiLayout = loader.load();
        serviciiController = loader.getController();

    }

    public void setUser(User user) {
        this.user = user;

        if(user instanceof SuperAdmin) {
            angajatiBtn.setVisible(true);
            pacientiBtn.setVisible(false);
            programariBtn.setVisible(false);
            serviciiBtn.setVisible(false);
            angajatiListController.setUser((ResurseUmane) user);
            userController.setUser((SuperAdmin)user);
            orarController.showHrControls();

            userController.showEditBtn();
            return;
        }
        else {
            userController.hideEditBtn();
        }

        if(user instanceof ResurseUmane) {
            angajatiBtn.setVisible(true);
            angajatiListController.setUser((ResurseUmane) user);
            orarController.showHrControls();
        }
        else{
            orarController.hideHrControls();
            angajatiBtn.setVisible(false);
        }

        if(user instanceof AsistentMedical){
            serviciiBtn.setVisible(false);
            pacientiBtn.setVisible(true);
            programariBtn.setVisible(false);
        } else if(user instanceof Medic){
            serviciiBtn.setVisible(true);
            pacientiBtn.setVisible(true);
            programariBtn.setVisible(true);
        } else if(user instanceof Receptioner) {
            pacientiBtn.setVisible(false);
            programariBtn.setVisible(true);
            serviciiBtn.setVisible(false);
        } else{
            pacientiBtn.setVisible(false);
            programariBtn.setVisible(false);
            serviciiBtn.setVisible(false);
        }
    }
}
