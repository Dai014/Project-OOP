package sample.GAControllers;

import sample.AlgorithmControllers.TSPGene;
import sample.AlgorithmControllers.TSPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TSPPopulation {

    private List<TSPChromosome> population;
    private final int initialSize;

    public TSPPopulation(final ArrayList<TSPGene> points, final int initialSize) {
        this.population = init(points, initialSize);
        this.initialSize = initialSize;
    }

    public List<TSPChromosome> getPopulation() {
        return this.population;
    }

    public TSPChromosome getAlpha() {
        return this.population.get(0);
    }

    private List<TSPChromosome> init(final List<TSPGene> points, final int initialSize) {
        final List<TSPChromosome> eden = new ArrayList<>();
        for(int i = 0; i < initialSize; i++) {
            final TSPChromosome chromosome = TSPChromosome.create(points);
            eden.add(chromosome); // add vào eden
        }
        return eden;
    }

    public void update() {
        doSort();
        doCrossOver();
        doMutation();
        doSort();
        doSelection();
    }

    public void doSort(){
        this.population.sort(Comparator.comparingDouble(TSPChromosome::getDistance));
    }

    public void doSelection() {
        this.population = this.population.stream().limit(this.initialSize).collect(Collectors.toList());
    }

    public List<TSPChromosome> getMutationChromosome(){
        final List<TSPChromosome> newPopulation = new ArrayList<>();
        for(int i = 0; i < this.population.size()/10; i++) {
            final TSPChromosome mutation = this.population.get(TSPUtils.randomIndex(this.population.size())).mutate();
            newPopulation.add(mutation);
        }
        return newPopulation;
    }

    public void doMutation() {
        this.population.addAll(getMutationChromosome());
    }

    public List<TSPChromosome> getCrossOverChromosome(){
        TSPChromosome chromosome0 = population.get(0);
        TSPChromosome chromosome1 = population.get(1);
        final List<TSPChromosome> newPopulation = new ArrayList<>();
        newPopulation.addAll(Arrays.asList(chromosome0.crossOver(chromosome1)));
        return newPopulation;
    }

    public void doCrossOver() {
        this.population.addAll(getCrossOverChromosome());
    }
}
