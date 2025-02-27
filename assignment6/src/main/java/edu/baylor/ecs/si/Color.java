package edu.baylor.ecs.si;

public enum Color  {
    RED("Red", 1),
    BLUE("Blue", 2),
    GREEN("Green", 3);

    public final String name;
    public final int id;

    Color(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name + " (ID: " + id + ")";
    }
}
