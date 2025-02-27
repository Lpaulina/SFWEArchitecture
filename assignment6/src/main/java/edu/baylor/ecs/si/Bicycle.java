package edu.baylor.ecs.si;

public class Bicycle {
    public int cadence;
    public int gear;
    public int speed;
    public Color color;

    public Bicycle(int startCadence, int startSpeed, int startGear, Color startColor) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
        color = startColor;
    }

    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

    public void printDescription(){
        System.out.println("\nBike is " + "in gear " + this.gear
                + " with a cadence of " + this.cadence +
                " and travelling at a speed of " + this.speed + " the color is " + this.color + ".");
    }

    public void visit(BasicService bs) {
        bs.accept(this);
    }

    public void visit(BicycleVisitor servis){
            servis.accept(this);
    }
}
