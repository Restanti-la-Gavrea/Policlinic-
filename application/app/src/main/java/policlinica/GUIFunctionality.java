package policlinica;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIFunctionality {

    public void logIn() throws Exception{
        double height = ((Stage)(Stage.getWindows().get(0))).getHeight() - 39;
        double width = ((Stage)(Stage.getWindows().get(0))).getWidth() - 16;

        Parent mainLayout = FXMLLoader.load(getClass().getResource("/mainLayout.fxml"));
        Scene mainScene = new Scene(mainLayout, width, height);
        mainScene.getStylesheets().add("mainStyle.css");
        Stage appStage = (Stage)(Stage.getWindows().get(0));

        appStage.setScene(mainScene);
    }

    public void showUserData(BorderPane pane) throws Exception{
        VBox temp = FXMLLoader.load(getClass().getResource("/userLayout.fxml"));
        pane.setCenter(temp);
    }

    public void showUserData(AngajatTableItem angajat,BorderPane pane) throws Exception{
        VBox temp = FXMLLoader.load(getClass().getResource("/userLayout.fxml"));
        pane.setCenter(temp);
    }

    public void showAngajatiList(BorderPane pane) throws Exception{
        ScrollPane temp = FXMLLoader.load(getClass().getResource("/angajatiList.fxml"));
        pane.setCenter(temp);
    }

    public void logOut() throws Exception{
        double height = ((Stage)(Stage.getWindows().get(0))).getHeight() - 39;
        double width = ((Stage)(Stage.getWindows().get(0))).getWidth() - 16;

        Parent logInLayout = FXMLLoader.load(getClass().getResource("/logInLayout.fxml"));
        Scene tempScene = new Scene(logInLayout, width, height);
        tempScene.getStylesheets().add("logInStyle.css");
        Stage appStage = (Stage)(Stage.getWindows().get(0));
        appStage.setScene(tempScene);
    }

}
