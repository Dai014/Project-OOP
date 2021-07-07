package sample.HCControllers;


import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import sample.AlgorithmControllers.AlgorithmController;
import sample.AlgorithmControllers.Route;
import sample.AlgorithmControllers.TSPUtils;

public class hillClimbingAlgorithmController extends AlgorithmController {

    private final hillClimbingAlgorithm hillclimbingalgorithm;
    private final Route route;
    private TSPUtils tspUtils;


    AnchorPane mainRoot;

    public hillClimbingAlgorithmController() {
        this.hillclimbingalgorithm = new hillClimbingAlgorithm();
        this.route = new Route(tspUtils.cities);
    }

    public void start(Stage primaryStage) throws Exception {
        mainRoot = new AnchorPane();
        hillClimbingAlgorithmView hillClimbingAlgorithmView = new hillClimbingAlgorithmView();
        Separator separator = new Separator();
        Button button = new Button();
        button.setOnAction(event -> {
            try {
                handleReturnToMenuButton(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Label label1 = new Label();
        Label label = new Label();
        Label label2 = new Label();
        hillClimbingAlgorithmView.setPrefHeight(700);
        hillClimbingAlgorithmView.setPrefWidth(1009);
        mainRoot.getChildren().add(hillClimbingAlgorithmView);
        addSeparator(separator, mainRoot);
        addButton(button, mainRoot, "Return to Menu", 280);
        addLabel(label, mainRoot, "",100);
        addLabel(label1,mainRoot, hillClimbingAlgorithmView.totalCities(),60);
        addLabel(label2,mainRoot, "",140);
        Scene scene = new Scene(mainRoot, 1277, 900);
        primaryStage.setTitle("Hill Climbing Algorithm");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        hillClimbingAlgorithmView.draw(label2,label);
    }

}
