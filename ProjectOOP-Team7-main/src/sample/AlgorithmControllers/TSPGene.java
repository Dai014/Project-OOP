package sample.AlgorithmControllers;

public class TSPGene {
    private final int key;
    private final int x;
    private final int y;

    public TSPGene(int key, int x, int y) {
        this.key = key;
        this.x = x;
        this.y = y;
    }

    public int getKey() { return this.key;}

    public int getX() { return this.x;}

    public int getY() { return this.y;}

    public double distance(final TSPGene other) {
        int xDistance = Math.abs(getX() - other.getX());
        int yDistance = Math.abs(getY() - other.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        return distance;
    }
}
