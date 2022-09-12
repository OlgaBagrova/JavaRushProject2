package Project;

public abstract class Animal extends Alive {

    public abstract void setFullness(double fullness);

    public abstract double getFoodNeed();

    public abstract double getFullness();

    public abstract void eat(Location location, Animal animal);

    public abstract void walk(Location location);
}


