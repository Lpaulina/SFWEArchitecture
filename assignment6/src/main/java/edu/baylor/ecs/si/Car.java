package edu.baylor.ecs.si;

import java.util.ArrayList;
import java.util.List;

public class Car implements BicycleVisitor{
    List<BicycleHolder> carHolders = new ArrayList<BicycleHolder>(4);

    public void accept(Bicycle bike) {
        if (carHolders.size() < 4) {
            carHolders.add(new BicycleHolder(bike));
            System.out.println("Bicycle added to car.");
        }
        else{
            System.out.println("Cannot add Bicycle because car is full.");
        }
    }
    public void accept(MountainBike bike) {
        if (carHolders.size() < 4) {
            carHolders.add(new MountainBikeHolder(bike));
            System.out.println("MountainBike added to car.");
        }
        else{
            System.out.println("Cannot add MountainBike because car is full.");
        }
    }

    public void accept(RoadBike bike) {
        if (carHolders.size() < 4) {
            carHolders.add(new RoadBikeHolder(bike));
            System.out.println("RoadBike added to car.");
        }
        else{
            System.out.println("Cannot add MountainBike because car is full.");
        }
    }
}
