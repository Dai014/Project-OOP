module javaFXdemo2 {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;

    exports sample.GAControllers to javafx.graphics, javafx.fxml;
    opens sample.GAControllers to javafx.fxml;

    opens sample;
    exports sample.GenerateDataControlles to javafx.fxml, javafx.graphics;
    opens sample.GenerateDataControlles to javafx.fxml;
    opens sample.main to javafx.fxml;
    exports sample.main to javafx.fxml, javafx.graphics;
    exports sample.AlgorithmControllers to javafx.fxml, javafx.graphics;
    opens sample.AlgorithmControllers to javafx.fxml;
    exports sample.MenuControlles to javafx.fxml, javafx.graphics;
    opens sample.MenuControlles to javafx.fxml;
}