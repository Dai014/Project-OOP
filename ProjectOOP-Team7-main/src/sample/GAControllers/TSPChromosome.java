package sample.GAControllers;

import sample.AlgorithmControllers.TSPGene;
import sample.AlgorithmControllers.TSPUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TSPChromosome {
    private final List<TSPGene> chromosome;
    private final double fitness;

    private TSPChromosome(final List<TSPGene> chromosome) {
        this.chromosome = chromosome;
        this.fitness = getDistance();
    }

    public static TSPChromosome create(final List<TSPGene> points) {
        final List<TSPGene> genes = points;
        Collections.shuffle(genes); // đảo để tạo gene mới
        return new TSPChromosome(genes);
    }

    public List<TSPGene> getChromosome() { return this.chromosome; }

    public double getDistance() {
        double total = 0.0f;
        for(int i = 0; i < this.chromosome.size() - 1; i++) {
            total += this.chromosome.get(i).distance(this.chromosome.get(i+1));
        }
        return total;
    }

    public TSPChromosome[] crossOver(final TSPChromosome other) {

        final List<TSPGene>[] myDNA = split(this.chromosome);
        final List<TSPGene>[] otherDNA = split(other.getChromosome());

        final List<TSPGene> firstCrossOver = new ArrayList<>(myDNA[0]);
        for(TSPGene gene : otherDNA[0]) {
            if(!firstCrossOver.contains(gene)) {
                firstCrossOver.add(gene);
            }
        }
        for(TSPGene gene : otherDNA[1]) {
            if(!firstCrossOver.contains(gene)) {
                firstCrossOver.add(gene);
            }
        }

        final List<TSPGene> secondCrossOver = new ArrayList<>(otherDNA[1]);

        for(TSPGene gene : myDNA[0]) {
            if(!secondCrossOver.contains(gene)) {
                secondCrossOver.add(gene);
            }
        }
        for(TSPGene gene : myDNA[1]) {
            if(!secondCrossOver.contains(gene)) {
                secondCrossOver.add(gene);
            }
        }

        if(firstCrossOver.size() != TSPUtils.cities.size() ||
                secondCrossOver.size() != TSPUtils.cities.size()) {
            throw new RuntimeException("oops!");
        }

        return new TSPChromosome[] { new TSPChromosome(firstCrossOver), new TSPChromosome(secondCrossOver)
        };
    }

    public TSPChromosome mutate() {
        final List<TSPGene> copy = new ArrayList<>(this.chromosome);
        int indexA = TSPUtils.randomIndex(copy.size());
        int indexB = TSPUtils.randomIndex(copy.size());
        while(indexA == indexB) {
            indexA = TSPUtils.randomIndex(copy.size());
            indexB = TSPUtils.randomIndex(copy.size());
        }
        Collections.swap(copy, indexA, indexB);
        return new TSPChromosome(copy);
    }

    public  List<TSPGene>[] split(final List<TSPGene> list) {
        final List<TSPGene> first = new ArrayList<>();
        final List<TSPGene> second = new ArrayList<>();
        final int size = list.size();
        final int partitionIndex = 1 + TSPUtils.randomIndex(size);
        IntStream.range(0, size).forEach(i -> {
            if(i < (size+1)/partitionIndex) {
                first.add(list.get(i));
            } else {
                second.add(list.get(i));
            }
        });
        return (List<TSPGene>[]) new List[] {first, second};
    }
}
