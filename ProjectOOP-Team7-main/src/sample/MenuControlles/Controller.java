
package sample.MenuControlles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.GAControllers.GeneticAlgorithmController;
import sample.HCControllers.hillClimbingAlgorithmController;
import sample.PSOControllers.PSOcontroller;
import sample.GenerateDataControlles.GenerateDataController;

public class Controller {
    public AnchorPane anchorPane;
    public ChoiceBox ChoiceBox;
    public Button submitButton;


    @FXML   // Handle for Generate Data Controller
    public void handleGenerateDataMenuItem() throws Exception {
        GenerateDataController generateDataController = new GenerateDataController();
        generateDataController.start(new Stage());
    }

    @FXML   // Handle for Submit button click
    public void handleSubmitButtonClick() throws Exception{
        String selected= (String) ChoiceBox.getSelectionModel().getSelectedItem();
        if(selected.equals("Genetic Algorithm"))
        {
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            GeneticAlgorithmController geneticAlgorithmController = new GeneticAlgorithmController();
            geneticAlgorithmController.start(new Stage());
        }else if(selected.equals("Hill Climbing")){
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            hillClimbingAlgorithmController hillClimbingalgorithmController = new hillClimbingAlgorithmController();
            hillClimbingalgorithmController.start(new Stage());
        }else if(selected.equals("Particle Swarm Optimization")){
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
            PSOcontroller psoController = new PSOcontroller();
            psoController.start(new Stage());
        }
    }

}