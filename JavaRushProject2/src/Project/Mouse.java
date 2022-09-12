package Project;

import java.util.Random;

public class Mouse extends Herbivore {

    private double weight = 0.05;
    private int maxCount = 50;
    private int maxCountOfChildren = 50;
    private int speed = 1;
    private double foodNeed = 0.01;
    private double fullness = 0.0005;
    private Location location;

    public Mouse(Location location) {
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
        try {
            int step = random.nextInt(1, speed + 1);
            if (random.nextInt() % 2 == 0) {
                if ((location.getI() + step) < Main.locations.length && Main.locations[location.getI() + step][location.getJ()].animals.stream().filter(animal -> animal instanceof Mouse).toList().size() < maxCount) {
                    Main.locations[location.getI() + step][location.getJ()].animals.add(this);
                    location.animals.remove(this);
                    System.out.println("Мышь ушла из локации " + location.getI() + " " + location.getJ() + " в локацию " + (location.getI() + step) + " " + location.getJ());
                    this.location = Main.locations[location.getI() + step][location.getJ()];
                }
            } else {
                if (location.getJ() + step < Main.locations[0].length && Main.locations[location.getI()][location.getJ() + step].animals.stream().filter(animal -> animal instanceof Mouse).toList().size() < maxCount) {
                    Main.locations[location.getI()][location.getJ() + step].animals.add(this);
                    location.animals.remove(this);
                    System.out.println("Мышь ушла из локации " + location.getI() + " " + location.getJ() + " в локацию " + location.getI() + " " + (location.getJ() + step));
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
        try {
            int countOfFood = random.nextInt(1, 3);
            if (countOfFood == 1) {
                super.eat(location, animal);
            } else {
                int probability = random.nextInt(100);
                if (probability < 90) {
                    boolean willMouseEatWorm = location.animals.stream().filter(worm -> worm instanceof Worm).toList().isEmpty();
                    if (!willMouseEatWorm) {
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
        try {
            if (location.animals.stream().filter(animal -> animal instanceof Mouse).toList().size() > 1 && location.animals.stream().filter(animal -> animal instanceof Mouse).toList().size() < maxCount) {
                Random random = new Random();
                int children = random.nextInt(maxCountOfChildren);
                int i;
                for (i = 0; i < children; i++) {
                    if (location.animals.stream().filter(animal -> animal instanceof Mouse).toList().size() >= maxCount)
                        break;
                    location.animals.add(new Mouse(location));
                }
                if (i > 1)
                    System.out.println("В локации " + location.getI() + " " + location.getJ() + " родилось " + i + " мышат!");
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
        fullness = fullness - 0.0001;
        if (fullness <= 0) {
            System.out.println("Мышь в локации " + location.getI() + " " + location.getJ() + " умерла от голода.");
            location.animals.remove(this);
        }
    }
}
