package sample.AlgorithmControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TSPUtils {

    private static final int WIDTH =1250;
    private static final int HEIGHT = 800;
    private final static Random R = new Random(10000);

    public static ArrayList<TSPGene> cities = generateData(35);

    public void setCities(sample.AlgorithmControllers.TSPGene[] cities, int numDataPoints) {
        TSPUtils.cities = generateData(numDataPoints);
    }

    public int getCitiesSize(){
        return TSPUtils.cities.size();
    }

    public static ArrayList<sample.AlgorithmControllers.TSPGene> generateData(final int numDataPoints) {
        final ArrayList<sample.AlgorithmControllers.TSPGene> data = new ArrayList<>();
        for(int i = 0; i < numDataPoints; i++) {
            data.add(new TSPGene(i, TSPUtils.randomIndex(WIDTH), TSPUtils.randomIndex(HEIGHT)));
        }
        return data;
    }
    public static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }
}
