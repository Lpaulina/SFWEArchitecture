package edu.baylor.ecs.si;

public class BasicService {
    public void accept(Bicycle bicycle){
        System.out.println("fixing Bicycle");
    }

    public void accept(MountainBike bicycle){
        System.out.println("fixing MountainBike");
    }

    public void accept(RoadBike bicycle){
        System.out.println("fixing RoadBike");
    }

}
