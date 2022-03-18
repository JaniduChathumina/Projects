package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.util.Date;

public class Controller {

    @FXML
        public Button Generate ;
    @FXML
        public Button Clear;
    @FXML
        public TextField FirstName;
    @FXML
        private TextField Surname;
    @FXML
        private DatePicker DOB;
    @FXML
        private TextField NIC;
    @FXML
        private TextField City;
    @FXML
        private ChoiceBox Vaccination;
    @FXML
        private TextField BoothNumber;

    @FXML
    public Label PName;
    @FXML
    public Label PSurname;
    @FXML
    public Label PDOB;
    @FXML
    public Label PCity;
    @FXML
    public Label PNIC;
    @FXML
    public Label PVType;
    @FXML
    public Label Date;
    @FXML
    public Label BoothNum;

    //clears all the information in the form and receipt
    public void clear() throws Exception {
        Alert a1 = new Alert(Alert.AlertType.NONE,
                "Successfully Cleared the Information", ButtonType.OK);

        a1.show();
        FirstName.clear();
        Surname.clear();
        DOB.getEditor().clear();
        NIC.clear();
        City.clear();
        Vaccination.setValue("AstraZeneca");
        BoothNumber.clear();

        PName.setText("");
        PSurname.setText("");
        PDOB.setText("");
        PCity.setText("");
        PNIC.setText("");
        PVType.setText("");
        BoothNum.setText("");
        Date.setText("");

    }
    //generates the receipt by the collected information
    public void generate() throws Exception {
        Alert a2 = new Alert(Alert.AlertType.NONE,
                "Receipt Generated Successfully", ButtonType.OK);

        a2.show();


        PName.setText(FirstName.getText());
        PSurname.setText(Surname.getText());
        PDOB.setText(String.valueOf(DOB.getValue()));
        PCity.setText(City.getText());
        PNIC.setText(NIC.getText());
        PVType.setText(String.valueOf(Vaccination.getValue()));
        BoothNum.setText(BoothNumber.getText());
        java.util.Date date=java.util.Calendar.getInstance().getTime();
        Date.setText(String.valueOf(date));


    }


}
