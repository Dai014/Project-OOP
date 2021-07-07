package sample.HCControllers;

import sample.AlgorithmControllers.Route;
import sample.AlgorithmControllers.TSPGene;

public class hillClimbingAlgorithm {

    public Route getNeighborhoodSolution(Route route)
    {
        int random1 = 0 ;
        int random2 = 0 ;

        while(random1==random2){
            random1 = (int) (route.cities.size()* Math.random());
            random2 = (int) (route.cities.size()* Math.random());
        }
        TSPGene city_1 = route.cities.get(random1);
        TSPGene city_2 = route.cities.get(random2);
        route.cities.set(random2,city_1);
        route.cities.set(random1,city_2);
        return route;
    }
}
