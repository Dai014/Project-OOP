package sample.GAControllers;

import javafx.geometry.Orientation;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.AlgorithmControllers.AlgorithmController;
import sample.main.Main;


public class GeneticAlgorithmController extends AlgorithmController {

    private GeneticAlgorithmView geneticAlgorithmView = new GeneticAlgorithmView();

    AnchorPane mainRoot;

    public void start(Stage primaryStage) throws Exception {
        mainRoot = new AnchorPane();

        Scene scene = new Scene(mainRoot, WIDTH, HEIGHT);
        Separator separator = new Separator();

        primaryStage.setTitle("Genetic Algorithm");
        primaryStage.setScene(scene);

        Button button = new Button();
        button.setOnAction(event -> {
            try {
                handleReturnToMenuButton(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Label label = new Label();
        geneticAlgorithmView.setPrefHeight(HEIGHT);
        geneticAlgorithmView.setPrefWidth(WIDTH);
        mainRoot.getChildren().add(geneticAlgorithmView);
        addSeparator(separator, mainRoot);

        addButton(button, mainRoot, "Return to Menu", 280);

        String totalCities = geneticAlgorithmView.totalCities();
        addLabel(new Label(), mainRoot,"Population Size: " + this.geneticAlgorithmView.population.getPopulation().size(), 100);
        addLabel(new Label(),mainRoot,totalCities,60);
        addLabel(label,mainRoot, "", 140);

        geneticAlgorithmView.draw(label);

        primaryStage.setFullScreen(true);
        primaryStage.show();

    }

}
