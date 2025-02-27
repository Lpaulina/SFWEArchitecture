package edu.baylor.ecs.si;

public class TestBikes {
    public static void main(String[] args) {
        Bicycle bike01, bike02, bike03;

        bike01 = new Bicycle(20, 10, 1, Color.RED);
        bike02 = new MountainBike(20, 10, 5, "Dual", Color.BLUE);
        bike03 = new RoadBike(40, 20, 8, 23, Color.GREEN);

        bike01.printDescription();
        bike02.printDescription();
        bike03.printDescription();

        
        BasicService service1 = new BasicService();
        BasicService service2 = new MountainBikeService();
        BasicService service3 = new RoadBikeService();

        System.out.println("\nSingle dispatch:");
        service1.accept(bike01);
        service2.accept(bike02);
        service3.accept(bike03);

        
        System.out.println("\nDouble dispatch:");
        bike01.visit(service1);
        bike02.visit(service2);
        bike03.visit(service3);

        System.out.println("\nCar:");
        Car car = new Car();
        car.accept(bike01);
        bike02.visit(car);
        bike03.visit(car);

        System.out.println("\nAnyHolderCar:");
        AnyHolderCar anyHolderCar = new AnyHolderCar<>();
        anyHolderCar.accept(bike01);
        anyHolderCar.accept(bike02);
        anyHolderCar.accept(bike03);
        
    }
}
