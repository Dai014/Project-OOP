package sample.PSOControllers;

import java.util.ArrayList;
import java.util.Collections;
import sample.AlgorithmControllers.Route;

public class PSOptimization {

    public ArrayList<Particle> particlesList = new ArrayList<Particle>();
    public Route gBest = null;
    public Location gBestLocation;


    public PSOptimization(Route route)
    {
//        System.out.println("Initial distance : "+(int)route.getFullRouteDistance()+" km");
        for(int i = 0; i < PSOConstants.maximumParticles ; i++)
        {
            Collections.shuffle(route.cities); //trộn mảng
            this.particlesList.add(new Particle(new Route(route)));
        }
        gBestLocation = new Location(new ArrayList<Double>());
        initilizeVelocites();
        initilizeLocations();
        sort();
        gBest = new Route(this.particlesList.get(0).pBest);
    }

    public void sort()
    {
        int n = this.particlesList.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (this.particlesList.get(j).pBest.getFullRouteDistance() > this.particlesList.get(j+1).pBest.getFullRouteDistance())
                {
                    Particle temp = this.particlesList.get(j);
                    this.particlesList.set(j,this.particlesList.get(j+1));
                    this.particlesList.set(j+1,temp);
                }
    }

    public void initilizeVelocites()
    {
        for(Particle p : this.particlesList)
        {
            for(int i = 0; i < Particle.problemSize - 1 ; i++)
            {
                p.velocity.velocityDimensions.add(PSOConstants.random.nextDouble() * 2.0 - 1.0);
            }
        }
    }

    public void initilizeLocations()
    {
        for(Particle p : this.particlesList)
        {
            for(int i = 0; i < Particle.problemSize - 1 ; i++)
            {
                p.location.locations.add((double)i);
                p.locationPBest.locations.add((double)i);
            }
        }
        for (int i = 0; i < Particle.problemSize - 1 ; i++)
        {
            gBestLocation.getLocations().add((double)i);
        }
    }

}