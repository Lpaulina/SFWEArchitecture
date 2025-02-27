package edu.baylor.ecs.si;

import java.util.ArrayList;
import java.util.List;

public class AnyHolderCar<T extends Car> {
    private final List<AnyHolder<? extends Bicycle>> carHolders = new ArrayList<>(4);

    public <T extends Bicycle> void accept(T cycle){
        if (carHolders.size() < 4){
            carHolders.add(new AnyHolder<>(cycle));
            System.out.println(cycle.getClass().getSimpleName() + " added to car.");
        }
        else {
            System.out.println("Car is full.");
        }
    }
}
