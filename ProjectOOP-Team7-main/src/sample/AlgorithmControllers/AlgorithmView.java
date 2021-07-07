package sample.AlgorithmControllers;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import static sample.AlgorithmControllers.AlgorithmController.HEIGHT;
import static sample.AlgorithmControllers.AlgorithmController.WIDTH;


public abstract class AlgorithmView extends Region{

    public abstract double getShortestPath();

    public abstract String totalCities();

    public abstract void drawBest(GraphicsContext gc, Color color);

    public void drawXY(GraphicsContext gc){
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,WIDTH,HEIGHT);
        gc.setLineWidth(0.5);
        gc.setStroke(Color.BLACK);
        for(int i = 0 ; i < WIDTH; i+=30){
            gc.strokeLine(i,0,i,HEIGHT);
        }
        for(int i = 0 ; i < HEIGHT; i+=30){
            gc.strokeLine(0,i,WIDTH,i);
        }
    }
}
