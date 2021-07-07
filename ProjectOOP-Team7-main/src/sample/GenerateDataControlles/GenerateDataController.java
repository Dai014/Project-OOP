package sample.GenerateDataControlles;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.AlgorithmControllers.TSPUtils;

import java.io.File;
import java.net.URL;


public class GenerateDataController {

    @FXML
    public Button submitButton;

    @FXML
    public TextField NumberOfPoints;

    @FXML
    public Label validator;

    public int ValueFromTextField = 0;

    private sample.AlgorithmControllers.TSPGene[] cities;


    public void start(Stage primaryStage) throws Exception {
        URL url = new File("C:\\Users\\Admin\\Desktop\\ProjectOOP-Team7-main\\ProjectOOP-Team7-main\\src\\sample\\resources\\generateData.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Generate Data");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public boolean getValueFromTextField() {
        try {
            ValueFromTextField = Integer.parseInt(NumberOfPoints.getText());
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }


    void drawData() {
        if(!getValueFromTextField()) {
            validator.setText("Invalid number !");
        }
        TSPUtils tspUtils = new TSPUtils();
        tspUtils.setCities(cities,ValueFromTextField);
    }


    @FXML
    void generateData() throws Exception {
        drawData();
        Stage stage = (Stage) NumberOfPoints.getScene().getWindow();
        if(getValueFromTextField()) {
            stage.close();
        }
    }
}
