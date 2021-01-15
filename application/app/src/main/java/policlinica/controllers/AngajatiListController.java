package policlinica.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.AngajatTableItem;
import policlinica.users.ResurseUmane;
import policlinica.users.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AngajatiListController implements Initializable {

    private ResurseUmane user;

    @FXML TableView<AngajatTableItem> angajatiTable;

    @FXML TableColumn<AngajatTableItem, String> nrContractCol;
    @FXML TableColumn<AngajatTableItem, String> numeCol;
    @FXML TableColumn<AngajatTableItem, String> prenumeCol;
    @FXML TableColumn<AngajatTableItem, String> postCol;
    @FXML TableColumn<AngajatTableItem, String> nrTelefonCol;

    @FXML Button seeDetailsBtn;

    private VBox userDataLayout;
    private UserController userController;

    private VBox orarLayout;
    private OrarController orarController;

    private BorderPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userDataLayout = null;
        userController = null;
        main = null;

        nrContractCol.setCellValueFactory(new PropertyValueFactory<>("nrContract"));
        nrTelefonCol.setCellValueFactory(new PropertyValueFactory<>("nrTelefon"));
        numeCol.setCellValueFactory(new PropertyValueFactory<>("nume"));
        prenumeCol.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));

       // angajatiTable.setItems(AngajatTableItem.getSampleList());
    }

    public void fillWithEmployees(VBox userDataLayout, UserController userController, BorderPane main ){
        this.userDataLayout = userDataLayout;
        this.userController = userController;
        this.main = main;
        seeDetailsBtn.setText("Vezi Detalii");
        fillWithEmployees();
    }

    public void fillWithEmployees(VBox orarLayout, OrarController orarController, BorderPane main ){
        this.orarLayout = orarLayout;
        this.orarController = orarController;
        this.main = main;
        seeDetailsBtn.setText("Vezi Orar");
        fillWithEmployees();
    }

    private void fillWithEmployees(){
        ArrayList<AngajatTableItem> angajati = user.getArrayOfDateAngajati();

        ObservableList<AngajatTableItem> list = FXCollections.observableArrayList(angajati);
        //aici scoti toti angajatii

        angajatiTable.setItems(list);
    }

    public void setButtonForDetails(){
        seeDetailsBtn.setOnAction(e -> {
            AngajatTableItem temp = angajatiTable.getItems().get(angajatiTable.getSelectionModel().getFocusedIndex());
            User tempUser = new User(temp.getNrContract());

            userController.showEditBtn();
            userController.switchToDetails();

            userController.setNumeLbl(tempUser.getNume());
            userController.setPrenumeLbl(tempUser.getPrenume());
            userController.setAdresaLbl(tempUser.getAdresa());
            userController.setAngajatDataLbl(tempUser.getDataAngajarii());
            userController.setEmailLbl(tempUser.getEmail());
            userController.setIbanLbl(tempUser.getIban());
            userController.setNrContractLbl(tempUser.getNrContract());
            userController.setNrTelefonLbl(tempUser.getNrTelefon());

            userController.setEditedUser(tempUser);

            main.setCenter(userDataLayout);

        });
    }

    public void setButtonForOrar(){
        seeDetailsBtn.setOnAction(e -> {

            AngajatTableItem temp = angajatiTable.getItems().get(angajatiTable.getSelectionModel().getFocusedIndex());
            User tempUser = new User(temp.getNrContract());

            if(temp.getPost().equals("m"))
                orarController.showS();
            else
                orarController.hideS();

            orarController.setUserShowCalendar(tempUser);

            main.setCenter(orarLayout);
        });
    }

    public void setUser(ResurseUmane user){
        this.user = user;
    }

}
