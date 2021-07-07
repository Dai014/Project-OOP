package sample.GAControllers;

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
import sample.AlgorithmControllers.TSPGene;
import sample.AlgorithmControllers.TSPUtils;

import static sample.GAControllers.GeneticAlgorithmController.WIDTH;


public class GeneticAlgorithmView extends AlgorithmView {
    public Canvas canvas;
    public final TSPPopulation population;

    private double Bshort, Ashort;
    private static int check = 0;

    public GeneticAlgorithmView(){
        this.population = new TSPPopulation(TSPUtils.cities, 50);
        this.canvas = new Canvas(WIDTH,GeneticAlgorithmController.HEIGHT);
        this.getChildren().addAll(canvas);
    }

    public void draw(Label label){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        t1.setAutoReverse(true);
        t1.getKeyFrames().add(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if ( check % 50 == 0){
                    Bshort = getShortestPath();
                }
                check++;
                update();
                Ashort = getShortestPath();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawXY(gc);
                label.setText("Shortest Path: " + String.format("%.2f", getShortestPath()));
                if(Ashort == Bshort){
                    drawBest(gc, Color.GREEN);
                }else {
                    drawBest(gc, Color.BLACK);
                }
            }
        }));
        t1.play();
    }

    public void update() {
        this.population.update();
    }

    public double getShortestPath(){
        return this.population.getAlpha().getDistance();
    }

    public String totalCities(){
        final java.util.List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        return ("Total Of cities: " + chromosome.size());
    }


    public void drawBest(javafx.scene.canvas.GraphicsContext gc, Color color) {
        final java.util.List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        for(int i = 0; i < chromosome.size() - 1; i++) {
            TSPGene gene = chromosome.get(i);
            TSPGene neighbor = chromosome.get(i + 1);
            if(color == Color.GREEN){
                gc.setLineWidth(3);
            }
            gc.setStroke(color);
            gc.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }

        gc.setFill(Color.RED);
        for(final TSPGene gene : chromosome) {
            gc.fillOval(gene.getX() - 5, gene.getY() - 5, 10, 10);
            gc.fillText(String.valueOf(gene.getKey()), gene.getX() -10, gene.getY() -10);

        }
    }

}
