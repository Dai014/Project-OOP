package sample.HCControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.AlgorithmControllers.AlgorithmView;
import sample.AlgorithmControllers.Route;
import sample.AlgorithmControllers.TSPGene;
import sample.AlgorithmControllers.TSPUtils;


public class hillClimbingAlgorithmView extends AlgorithmView {
    private static final int WIDTH = 1277;
    private static final int HEIGHT = 900;
    private Canvas canvas;

    private final hillClimbingAlgorithm hillclimbingalgorithm;
    private static Route currentRoute = new Route();
    private static int check = 1000;
    private double Bshort, Ashort;
    private static int iterationCounter;

    public hillClimbingAlgorithmView(){
        this.hillclimbingalgorithm = new hillClimbingAlgorithm();
        this.canvas = new Canvas(WIDTH,HEIGHT);
        this.getChildren().addAll(canvas);
    }

    public void draw(Label label, Label label2){
        currentRoute = new Route(TSPUtils.cities);
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        t1.setAutoReverse(true);
        t1.getKeyFrames().add(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if ( check % 1000 == 0){
                    Bshort = getShortestPath();
                }
                check++;
                update(label2);
                Ashort = getShortestPath();
                label.setText("Shortest Path: " + String.format("%.2f", getShortestPath()));
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawXY(gc);
                if( Ashort == Bshort ){
                    drawBest(gc, Color.GREEN);
                }else{
                    drawBest(gc, Color.BLACK);
                }
            }
        }));
        t1.play();
    }

    public double getShortestPath() {
        return currentRoute.getFullRouteDistance();
    }

    public String totalCities(){
        TSPUtils tspUtils = new TSPUtils();
        tspUtils.getCitiesSize();
        return ("Total Of cities: " + tspUtils.getCitiesSize());
    }

    private void update(Label label){
        Route neighborhoodSolution;
        neighborhoodSolution = this.hillclimbingalgorithm.getNeighborhoodSolution(new Route(currentRoute)); // on crée une solution voisine
        if (neighborhoodSolution.getFullRouteDistance() <= currentRoute.getFullRouteDistance()) {
            iterationCounter = 0;
            currentRoute = new Route(neighborhoodSolution);
        } else {
            if(iterationCounter < 10000){
                label.setText("Iteration : " + iterationCounter);
            }else{
                label.setText("Iteration : MAX" );
            }
            iterationCounter++;
        }
    }


    public void drawBest(GraphicsContext gc, Color color) {
        for(int i = 0; i < currentRoute.cities.size() - 1; i++) {
            TSPGene gene = currentRoute.cities.get(i);
            TSPGene neighbor = currentRoute.cities.get(i + 1);
            if(color == Color.GREEN){
                gc.setLineWidth(3);
            }
            gc.setStroke(color);
            gc.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        gc.setFill(javafx.scene.paint.Color.RED);
        for(final TSPGene gene : currentRoute.cities) {
            gc.fillOval(gene.getX()-5, gene.getY()-5, 10, 10);
            gc.fillText(String.valueOf(gene.getKey()), gene.getX() -10, gene.getY() -10);

        }
    }
}