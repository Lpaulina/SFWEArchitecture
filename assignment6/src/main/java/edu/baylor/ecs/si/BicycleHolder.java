package edu.baylor.ecs.si;

public class BicycleHolder {
    private final Bicycle bicycle;

    public BicycleHolder(Bicycle bicycle) {
        this.bicycle = bicycle;
	}

    public Bicycle getBicycle(){
        return this.bicycle;
    }

    @Override
    public String toString(){
        return bicycle.getClass().getSimpleName() + " holder";
    }
}
