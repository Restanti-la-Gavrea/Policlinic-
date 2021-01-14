package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import policlinica.MonthName;
import policlinica.calendar.CalendarAux;
import policlinica.calendar.CalendarSaptamanal;
import policlinica.calendar.Day;
import policlinica.users.ResurseUmane;
import policlinica.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class OrarEditController implements Initializable {

    private VBox orarLayout;
    private OrarController orarController;
    private BorderPane main;
    private ResurseUmane user;
    private User userCalendar;

    @FXML private VBox saptamanalBox;
    @FXML private TextField luniFld;
    @FXML private TextField martiFld;
    @FXML private TextField miercuriFld;
    @FXML private TextField joiFld;
    @FXML private TextField vineriFld;
    @FXML private TextField sambataFld;
    @FXML private TextField duminicaFld;
    @FXML private Button saptamanalSubmitBtn;
    private CalendarSaptamanal calendarSaptamanal;

    @FXML private VBox specificBox;
    @FXML private Label specificDayLbl;
    @FXML private TextField specificFld;
    @FXML private Button specificSubmitBtn;
    private Day ziSelected;

    @FXML private VBox concediuBox;
    @FXML private DatePicker firstDayFld;
    @FXML private DatePicker lastDayFld;
    @FXML private Button concediuSubmitBtn;

    @FXML public void submitSaptamanal(){
       
        calendarSaptamanal.getDay(0).setIntervalOrar(duminicaFld.getText());
        calendarSaptamanal.getDay(1).setIntervalOrar(luniFld.getText());
        calendarSaptamanal.getDay(2).setIntervalOrar(martiFld.getText());
        calendarSaptamanal.getDay(3).setIntervalOrar(miercuriFld.getText());
        calendarSaptamanal.getDay(4).setIntervalOrar(joiFld.getText());
        calendarSaptamanal.getDay(5).setIntervalOrar(vineriFld.getText());
        calendarSaptamanal.getDay(6).setIntervalOrar(sambataFld.getText());

        user.setOrarGeneric(calendarSaptamanal);

        calendarSaptamanal = null;

        orarController.refreshCalendar();
        main.setCenter(orarLayout);
    }
    @FXML public void submitSpecific(){
        ziSelected.setIntervalOrar(specificFld.getText());
        user.setOrarSpecific(ziSelected);

        ziSelected = null;

        orarController.refreshCalendar();
        main.setCenter(orarLayout);
    }
    @FXML public void submitConcediu(){
        String prima = firstDayFld.getEditor().getText();
        String ultima = lastDayFld.getEditor().getText();

        prima = CalendarAux.parseToSQLDate(prima);
        ultima = CalendarAux.parseToSQLDate(ultima);

        System.out.println(prima + " " + ultima);

        Day primaZi = new Day(prima);
        Day ultimaZi = new Day(ultima);

        user.setConcediu(userCalendar, primaZi, ultimaZi);
        orarController.refreshCalendar();
        main.setCenter(orarLayout);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        concediuBox.managedProperty().bind(concediuBox.visibleProperty());
        saptamanalBox.managedProperty().bind(saptamanalBox.visibleProperty());
        specificBox.managedProperty().bind(specificBox.visibleProperty());

        firstDayFld.setPromptText("Inceputul Concediului");
        lastDayFld.setPromptText("Sfarsitul Concediului");
    }

    public void showSaptamanal(ResurseUmane user, User userCalendar){
        concediuBox.setVisible(false);
        saptamanalBox.setVisible(true);
        specificBox.setVisible(false);
        this.user = user;
        this.userCalendar = userCalendar;

        calendarSaptamanal = new CalendarSaptamanal(userCalendar.getNrContract());

        duminicaFld.setText(calendarSaptamanal.getDay(0).getIntervalorar());
        luniFld.setText(calendarSaptamanal.getDay(1).getIntervalorar());
        martiFld.setText(calendarSaptamanal.getDay(2).getIntervalorar());
        miercuriFld.setText(calendarSaptamanal.getDay(3).getIntervalorar());
        joiFld.setText(calendarSaptamanal.getDay(4).getIntervalorar());
        vineriFld.setText(calendarSaptamanal.getDay(5).getIntervalorar());
        sambataFld.setText(calendarSaptamanal.getDay(6).getIntervalorar());

    }
    public void showSpecific(ResurseUmane user, User userCalendar, Day ziSelected){
        concediuBox.setVisible(false);
        saptamanalBox.setVisible(false);
        specificBox.setVisible(true);
        this.user = user;
        this.userCalendar = userCalendar;

        this.ziSelected = ziSelected;
        specificFld.setText(ziSelected.getIntervalorar());

        specificDayLbl.setText(ziSelected.getNameDayOfWeek() + ", " + ziSelected.getDayOfMonth() + " " + MonthName.getMonthName(ziSelected.getMounthOfYear() - 1));

    }
    public void showConcediu(ResurseUmane user, User userCalendar){
        concediuBox.setVisible(true);
        saptamanalBox.setVisible(false);
        specificBox.setVisible(false);
        this.user = user;
        this.userCalendar = userCalendar;
    }

    @FXML public void goBack(){
        main.setCenter(orarLayout);
    }

    public void setContext(VBox orarLayout, OrarController orarController, BorderPane main){
        this.orarLayout = orarLayout;
        this.main = main;
        this.orarController = orarController;
    }
}
