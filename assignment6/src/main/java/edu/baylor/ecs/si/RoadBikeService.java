package edu.baylor.ecs.si;

public class RoadBikeService extends BasicService{
    @Override
    public void accept(Bicycle bicycle){
        System.out.println("fixing Bicycle");
    }

    public void accept(RoadBike bicycle){
        System.out.println("fixing Road Bike");
    }
}
