package edu.baylor.ecs.si;

public class MountainBikeService extends BasicService {
    
    @Override
    public void accept(Bicycle bicycle){
        System.out.println("fixing Bicycle");
    }

    public void accept(MountainBike bicycle) {
        System.out.println("fixing Mountain Bike");
    }
}
