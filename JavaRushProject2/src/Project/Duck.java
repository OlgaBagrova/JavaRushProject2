package Project;

import java.util.ArrayList;
import java.util.Random;

public class Duck extends Herbivore {

    private double weight = 1;
    private int maxCount = 200;
    private int maxCountOfChildren = 20;
    private int speed = 4;
    private double foodNeed = 0.15;
    private double fullness = 0.1;
    private Location location;

    public Duck(Location location) {
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
    public double getFoodNeed() {
        return foodNeed;
    }

    @Override
    public double getFullness() {
        return fullness;
    }

    @Override
    public void setFullness(double fullness) {
        this.fullness = fullness;
    }

    @Override
    public void walk(Location location) {
        Random random = new Random();
        int step = random.nextInt(1, speed+1);

        try {if (random.nextInt() % 2 == 0) {
                if ((location.getI() + step) < Main.locations.length && Main.locations[location.getI() + step][location.getJ()].animals.stream().filter(animal -> animal instanceof Duck).toList().size() < maxCount) {
                    Main.locations[location.getI() + step][location.getJ()].animals.add(this);
                    location.animals.remove(this);
                    System.out.println("Утка улетела из локации " + location.getI() + " " + location.getJ() + " в локацию " + (location.getI() + step) + " " + location.getJ());
                    this.location = Main.locations[location.getI() + step][location.getJ()];
                }
            } else{
                if (location.getJ() + step < Main.locations[0].length && Main.locations[location.getI()][location.getJ() + step].animals.stream().filter(animal -> animal instanceof Duck).toList().size() < maxCount) {
                    Main.locations[location.getI()][location.getJ() + step].animals.add(this);
                    location.animals.remove(this);
                    System.out.println("Утка улетела из локации " + location.getI() + " " + location.getJ() + " в локацию " + location.getI() + " " + (location.getJ() + step));
                    this.location = Main.locations[location.getI()][location.getJ() + step];
                }
            }
        }
        catch (Exception e){

        }
    }

    @Override
    public synchronized void eat(Location location, Animal animal) {
        Random random = new Random();
        int countOfFood = random.nextInt(1, 3);
        try {
            if (countOfFood == 1) {
                super.eat(location, animal);
            } else {
                int probability = random.nextInt(100);
                if (probability < 90) {
                    boolean willDuckEatWorm = location.animals.stream().filter(worm -> worm instanceof Worm).toList().isEmpty();
                    if (!willDuckEatWorm) {
                        Animal food = location.animals.stream().filter(worm -> worm instanceof Worm).toList().get(0);
                        location.animals.remove(food);
                        fullness = fullness + food.getWeight();
                    }
                }
            }
        }
        catch (Exception e){

        }
    }

    @Override
    public void reproduce(Location location) {
        try{
            if (location.animals.stream().filter(animal -> animal instanceof Duck).toList().size() > 1 && location.animals.stream().filter(animal -> animal instanceof Duck).toList().size() < maxCount) {
                Random random = new Random();
                int children = random.nextInt(maxCountOfChildren);
                int i;
                for (i = 0; i < children; i++) {
                    if (location.animals.stream().filter(animal -> animal instanceof Duck).toList().size() >= maxCount)
                        break;
                    location.animals.add(new Duck(location));
                }
                if (i > 0)
                    System.out.println("В локации " + location.getI() + " " + location.getJ() + " родилось " + i + " утят!");
            }
        }
        catch (Exception e){

        }
    }

    @Override
    public void run() {
        this.eat(location, this);
        this.reproduce(location);
        this.walk(location);
        fullness = fullness - 0.05;
        if (fullness <= 0) {
            System.out.println("Утка в локации " + location.getI() + " " + location.getJ() + " умерла от голода.");
            location.animals.remove(this);
        }
    }
}
