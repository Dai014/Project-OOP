package sample.PSOControllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.AlgorithmControllers.AlgorithmController;
import sample.AlgorithmControllers.Route;
import sample.AlgorithmControllers.TSPUtils;

public class PSOcontroller extends AlgorithmController {

    private final PSOview psoView;

    private final Route route;
    private TSPUtils tspUtils;

    AnchorPane mainRoot;

    public PSOcontroller() {
        this.psoView = new PSOview();
        this.route = new Route(sample.AlgorithmControllers.TSPUtils.cities);
    }

    public void addPSOview(PSOview psoView, AnchorPane anchorPane){
        psoView.setLayoutX(0);
        psoView.setLayoutY(0);
        psoView.setPrefHeight(700);
        psoView.setPrefWidth(1009);
        anchorPane.getChildren().add(psoView);
    }

    public void start(Stage primaryStage) throws Exception {
        mainRoot = new AnchorPane();
        PSOview psoView = new PSOview();
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
        Label label3 = new Label();
        addPSOview(psoView,mainRoot);
        addSeparator(separator, mainRoot);
        addButton(button, mainRoot, "Return to Menu", 280);
        addLabel(label, mainRoot, "MaximumParticles : " + PSOConstants.maximumParticles, 100);
        addLabel(label1,mainRoot, psoView.totalCities(), 60);
        addLabel(label2,mainRoot, "", 180);
        addLabel(label3,mainRoot, "", 140);
        Scene scene = new Scene(mainRoot, 1009, 700);
        primaryStage.setTitle("Particle Swarm Optimization Algorithm");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        psoView.draw(label2,label3);

    }
}
