package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import policlinica.MonthName;
import policlinica.calendar.Calendar;
import policlinica.calendar.CalendarAux;
import policlinica.calendar.Day;
import policlinica.users.AsistentMedical;
import policlinica.users.Medic;
import policlinica.users.ResurseUmane;
import policlinica.users.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrarController implements Initializable {

    @FXML private Label cell00;
    @FXML private Label cell01;
    @FXML private Label cell02;
    @FXML private Label cell03;
    @FXML private Label cell04;
    @FXML private Label cell05;
    @FXML private Label cell06;

    @FXML private Label cell10;
    @FXML private Label cell11;
    @FXML private Label cell12;
    @FXML private Label cell13;
    @FXML private Label cell14;
    @FXML private Label cell15;
    @FXML private Label cell16;

    @FXML private Label cell20;
    @FXML private Label cell21;
    @FXML private Label cell22;
    @FXML private Label cell23;
    @FXML private Label cell24;
    @FXML private Label cell25;
    @FXML private Label cell26;

    @FXML private Label cell30;
    @FXML private Label cell31;
    @FXML private Label cell32;
    @FXML private Label cell33;
    @FXML private Label cell34;
    @FXML private Label cell35;
    @FXML private Label cell36;

    @FXML private Label cell40;
    @FXML private Label cell41;
    @FXML private Label cell42;
    @FXML private Label cell43;
    @FXML private Label cell44;
    @FXML private Label cell45;
    @FXML private Label cell46;

    @FXML private Label cell50;
    @FXML private Label cell51;
    @FXML private Label cell52;
    @FXML private Label cell53;
    @FXML private Label cell54;
    @FXML private Label cell55;
    @FXML private Label cell56;

    @FXML private Label calendarDetailLbl;
    @FXML private Button previousBtn;
    @FXML private Button nextBtn;

    private ResurseUmane user;
    private User userCalendar;
    private Calendar calendar;

    private int month;
    private int year;

    private int selLblRow;
    private int selLblCol;

    private Label[][] matrix;

    @FXML Button saptamanalBtn;
    @FXML Button specificBtn;
    @FXML Button concediiBtn;

    private VBox orarEditLayout;
    private BorderPane main;
    private OrarEditController orarEditController;

    @FXML private HBox hrControls;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matrix = new Label[6][7];
        matrix[0][0] = cell00;matrix[0][1] = cell01;matrix[0][2] = cell02;matrix[0][3] = cell03;matrix[0][4] = cell04;matrix[0][5] = cell05;matrix[0][6] = cell06;
        matrix[1][0] = cell10;matrix[1][1] = cell11;matrix[1][2] = cell12;matrix[1][3] = cell13;matrix[1][4] = cell14;matrix[1][5] = cell15;matrix[1][6] = cell16;
        matrix[2][0] = cell20;matrix[2][1] = cell21;matrix[2][2] = cell22;matrix[2][3] = cell23;matrix[2][4] = cell24;matrix[2][5] = cell25;matrix[2][6] = cell26;
        matrix[3][0] = cell30;matrix[3][1] = cell31;matrix[3][2] = cell32;matrix[3][3] = cell33;matrix[3][4] = cell34;matrix[3][5] = cell35;matrix[3][6] = cell36;
        matrix[4][0] = cell40;matrix[4][1] = cell41;matrix[4][2] = cell42;matrix[4][3] = cell43;matrix[4][4] = cell44;matrix[4][5] = cell45;matrix[4][6] = cell46;
        matrix[5][0] = cell50;matrix[5][1] = cell51;matrix[5][2] = cell52;matrix[5][3] = cell53;matrix[5][4] = cell54;matrix[5][5] = cell55;matrix[5][6] = cell56;

        selLblCol = -1;
        selLblRow = -1;
        calendar = null;
        userCalendar = null;

        main = null;
        orarEditController = null;
        orarEditLayout = null;

        specificBtn.managedProperty().bind(specificBtn.visibleProperty());

        hrControls.managedProperty().bind(hrControls.visibleProperty());
    }

    @FXML public void previousMonth(){
        if(userCalendar != null)
        {
            month--;
            if (month<0)
            {
                year--;
                month = 11;
            }
            createCalendar(userCalendar.getNrContract());
        }
    }

    @FXML public void nextMonth(){
        if(userCalendar != null)
        {
            month++;
            if (month>11)
            {
                year++;
                month = 0;
            }
            createCalendar(userCalendar.getNrContract());
        }
    }

    @FXML public void calendarClickHandler(MouseEvent e){
        Label cell = (Label)e.getSource();
/*
        for(int index = 0; index < cell.getStyleClass().size(); index++) {
            System.out.println(cell.getStyleClass().get(index));
        }
        System.out.println();
*/
        if(cell.getStyleClass().size() > 1)
        {
            cell.getStyleClass().add("selectedItem");

            if(selLblCol > -1 && selLblRow > -1)
                matrix[selLblRow][selLblCol].getStyleClass().remove(2);

            for(int i=0; i<6; i++)
                for(int j=0; j<7; j++) {
                    if (matrix[i][j] == cell) {
                        selLblCol = j;
                        selLblRow = i;
                    }
                }
        }
    }

    public void setUserShowCalendar(User user){
        this.userCalendar = user;

        System.out.println(user.getClass());

        month = CalendarAux.getCurrentMonth();
        year = CalendarAux.getCurrentYear();
        createCalendar(user.getNrContract());
    }

    public void hideS(){
        specificBtn.setVisible(false);
    }
    public void showS(){
        specificBtn.setVisible(true);
    }

    public void refreshCalendar(){
        createCalendar(userCalendar.getNrContract());
    }

    private void createCalendar(String nrContract){
        calendar = new Calendar(nrContract, ""+year, ""+(month+1));

        for(int i=0; i<6; i++)
            for(int j=0; j<7; j++)
            {
                Day zi = calendar.getDay(i,j);
                if(zi != null){
                    String temp = "";
                    ArrayList<String> intervale = zi.getArrayOfIntervalOrar();
                    for(String s: intervale)
                        temp += s + "\n";
                    matrix[i][j].setText("" + zi.getDayOfMonth() + "\n" + temp); // + zi.getInterval()
                    if(matrix[i][j].getStyleClass().size() == 1)
                        matrix[i][j].getStyleClass().add("calendarItem");
                    if(matrix[i][j].getStyleClass().size() == 3)
                        matrix[i][j].getStyleClass().remove(2);
                }
                else {
                    matrix[i][j].setText("");
                    while(matrix[i][j].getStyleClass().size()>1)
                        matrix[i][j].getStyleClass().remove(1);
                }
            }

        selLblRow = -1;
            selLblCol = -1;

        calendarDetailLbl.setText(MonthName.getMonthName(month)+", "+year);
    }

    @FXML public void editSaptamanal(){
        orarEditController.showSaptamanal(user, userCalendar);
        main.setCenter(orarEditLayout);
    }
    @FXML public void editSpecific(){
        if(selLblCol >= 0 && selLblRow >= 0)
        {
            orarEditController.showSpecific(user, userCalendar, calendar.getDay(selLblRow, selLblCol));
            main.setCenter(orarEditLayout);
        }
    }
    @FXML public void editConcedii(){
        orarEditController.showConcediu(user, userCalendar);
        main.setCenter(orarEditLayout);
    }

    public void setContext(VBox orarEditLayout, BorderPane main, OrarEditController orarEditController, ResurseUmane user){
        this.orarEditLayout = orarEditLayout;
        this.orarEditController = orarEditController;
        this.main = main;
        this.user = user;
    }

    public void showHrControls(){
        hrControls.setVisible(true);
    }
    public void hideHrControls(){
        hrControls.setVisible(false);
    }

}
