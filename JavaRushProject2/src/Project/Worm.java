package Project;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Worm extends Herbivore {
    private double weight = 0.01;
    private int maxCount = 1000;
    private int maxCountOfChildren = 250;
    private int speed = 0;
    private double foodNeed = 0;
    private double fullness = 0;
    private Location location;

    public Worm(Location location){
        this.location = location;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public void setFullness(double fullness) {
        this.fullness = fullness;
    }

    @Override
    public double getFoodNeed() {
        return foodNeed;
    }

    @Override
    public double getFullness() {
        return fullness;
    }

    @Override
    public void walk(Location location) {}

    @Override
    public void reproduce(Location location) {
        try {
            if (location.animals.stream().filter(animal -> animal instanceof Worm).toList().size() > 1 && location.animals.stream().filter(animal -> animal instanceof Worm).toList().size() < maxCount) {
                Random random = new Random();
                int children = random.nextInt(maxCountOfChildren);
                int i;
                for (i = 0; i < children; i++) {
                    if (location.animals.stream().filter(animal -> animal instanceof Worm).toList().size() >= maxCount)
                        break;
                    location.animals.add(new Worm(location));
                }
                if (i > 1)
                    System.out.println("В локации " + location.getI() + " " + location.getJ() + " родилось " + i + " гусениц!");
            }
        }
        catch (Exception e){

        }
    }

    @Override
    public void run() {
        this.eat(location, this);
        this.reproduce(location);
    }
}
