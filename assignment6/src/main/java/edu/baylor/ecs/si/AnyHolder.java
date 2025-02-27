package edu.baylor.ecs.si;

public class AnyHolder<T extends Bicycle> {
    private final T bicycle;

    public AnyHolder(T bicycle){
        this.bicycle = bicycle;
    }
    
    public T getBicycle(){
        return bicycle;
    }

    @Override
    public String toString(){
        return "Generic " + bicycle.getClass().getSimpleName();
    }
}
