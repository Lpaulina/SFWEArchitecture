package edu.baylor.ecs.si;

public class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(int startCadence, int startSpeed, int startGear,String suspensionType, Color startColor) {
        super(startCadence, startSpeed, startGear, startColor);
        this.setSuspension(suspensionType);
    }

    public String getSuspension() {
        return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    @Override
    public void printDescription(){
        System.out.println("\nBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + " with suspension " + this.suspension + " the color is " + this.color + ".");
    }

    @Override
    public void visit(BasicService bs) {
        bs.accept(this);
    }

    public void visit(BicycleVisitor servis){
        servis.accept(this);
}
}
