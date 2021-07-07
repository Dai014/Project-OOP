package sample.AlgorithmControllers;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.GAControllers.GeneticAlgorithmView;
import sample.main.Main;


public class AlgorithmController {

    public static final int WIDTH = 1277;
    public static final int HEIGHT = 900;

    public void addButton(Button button, AnchorPane anchorPane,String name, int y) {
        button.setLayoutX(1350);
        button.setLayoutY(y);
        button.setText(name);
        button.setPrefHeight(25);
        button.setPrefWidth(119);
        button.setMnemonicParsing(false);
        anchorPane.getChildren().add(button);
    }

    public void addSeparator(Separator separator, AnchorPane anchorPane) {
        separator.setLayoutY(0);
        separator.setOrientation(Orientation.VERTICAL);
        separator.setLayoutX(827);
        separator.setPrefHeight(10000);
        separator.setPrefWidth(900);
        anchorPane.getChildren().add(separator);
    }

    public void addLabel(Label label, AnchorPane anchorPane, String name, int y) {
        label.setLayoutX(1330);
        label.setLayoutY(y);
        label.setPrefHeight(17);
        label.setText(name);
        label.setFont(new Font(16));
        label.setStyle("-fx-font: Bold");
        label.setPrefWidth(400);
        anchorPane.getChildren().add(label);
    }

    public void handleReturnToMenuButton(Button button) throws Exception {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        Main mainController = new Main();
        mainController.start(new Stage());
    }

}
