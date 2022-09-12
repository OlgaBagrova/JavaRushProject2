package Project;

public abstract class Alive implements Runnable {
    public abstract double getWeight();

    public abstract int getMaxCount();

    abstract public void reproduce(Location location);

}
