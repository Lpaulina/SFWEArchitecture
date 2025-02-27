package edu.baylor.ecs.si;

public interface BicycleVisitor {
    public void accept(Bicycle b);
    public void accept(MountainBike b);
    public void accept(RoadBike b);

}
