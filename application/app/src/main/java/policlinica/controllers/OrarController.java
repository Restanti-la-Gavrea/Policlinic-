package policlinica.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OrarController implements Initializable {

    @FXML private Label calendarDetailLbl;
    @FXML private Button previousBtn;
    @FXML private Button nextBtn;

    private Label[][] matrix;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matrix = new Label[5][7];
        matrix[0][0] = cell00;matrix[0][1] = cell01;matrix[0][2] = cell02;matrix[0][3] = cell03;matrix[0][4] = cell04;matrix[0][5] = cell05;matrix[0][6] = cell06;
        matrix[1][0] = cell10;matrix[1][1] = cell11;matrix[1][2] = cell12;matrix[1][3] = cell13;matrix[1][4] = cell14;matrix[1][5] = cell15;matrix[1][6] = cell16;
        matrix[2][0] = cell20;matrix[2][1] = cell21;matrix[2][2] = cell22;matrix[2][3] = cell23;matrix[2][4] = cell24;matrix[2][5] = cell25;matrix[2][6] = cell26;
        matrix[3][0] = cell30;matrix[3][1] = cell31;matrix[3][2] = cell32;matrix[3][3] = cell33;matrix[3][4] = cell34;matrix[3][5] = cell35;matrix[3][6] = cell36;
        matrix[4][0] = cell40;matrix[4][1] = cell41;matrix[4][2] = cell42;matrix[4][3] = cell43;matrix[4][4] = cell44;matrix[4][5] = cell45;matrix[4][6] = cell46;

    }

    @FXML public void previousMonth(){

    }

    @FXML public void nextMonth(){

    }

    @FXML public void calendarClickHandler(MouseEvent e){

        for(int i=0; i<5; i++)
            for(int j=0; j<7; j++)
                {
                    if(matrix[i][j].getStyleClass().size() > 2)
                         matrix[i][j].getStyleClass().remove(2);
                    //matrix[i][j].getStyleClass().add("calendarItem");
                }

        Label cell = (Label)e.getSource();
        cell.getStyleClass().add("selectedItem");
    }


}
