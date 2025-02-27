package edu.baylor.ecs.si;

public class RoadBike extends Bicycle {
    private int tireWidth;

    public RoadBike(int tireWidth, int startCadence, int startSpeed,int startGear, Color startColor) {
        super(startCadence, startSpeed, startGear, startColor);
        this.setTireWidth(tireWidth);
    }

    public void setTireWidth(int width){
        this.tireWidth = width;
    }

    public int getTireWidth(){
        return this.tireWidth;
    }

    @Override
    public void printDescription(){
        System.out.println("\nBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + " with tires of a width of " + this.tireWidth + " the color is " + this.color + ".");
    }

    @Override
    public void visit(BasicService bs) {
        bs.accept(this);
    }

    public void visit(BicycleVisitor servis){
        servis.accept(this);
}

}
