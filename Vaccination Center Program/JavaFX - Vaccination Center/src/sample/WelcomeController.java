package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class WelcomeController {
    @FXML
    public Button ContinueButton ;
    //close the welcome window and opens the interface
    public void Continue() throws Exception {
        Stage stage = (Stage) ContinueButton.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("SYFRIUS");
        primaryStage.setScene(new Scene(root, 1475, 638));
        primaryStage.show();

    }
}
