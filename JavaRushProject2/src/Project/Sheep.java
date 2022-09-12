package Project;

import java.util.Random;

public class Sheep extends Herbivore{

    private double weight = 70;
    private int maxCount = 140;
    private int maxCountOfChildren = 10;
    private int speed = 3;
    private double foodNeed = 15;
    private double fullness = 5;
    private Location location;

    public Sheep(Location location){
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
                if ((location.getI() + step) < Main.locations.length && Main.locations[location.getI() + step][location.getJ()].animals.stream().filter(animal -> animal instanceof Sheep).toList().size() < maxCount) {
                    Main.locations[location.getI() + step][location.getJ()].animals.add(this);
                    location.animals.remove(this);
                    System.out.println("Овца ушла из локации " + location.getI() + " " + location.getJ() + " в локацию " + (location.getI() + step) + " " + location.getJ());
                    this.location = Main.locations[location.getI() + step][location.getJ()];
                }
            } else {
                if (location.getJ() + step < Main.locations[0].length && Main.locations[location.getI()][location.getJ() + step].animals.stream().filter(animal -> animal instanceof Sheep).toList().size() < maxCount) {
                    Main.locations[location.getI()][location.getJ() + step].animals.add(this);
                    location.animals.remove(this);
                    System.out.println("Овца ушла из локации " + location.getI() + " " + location.getJ() + " в локацию " + location.getI() + " " + (location.getJ() + step));
                    this.location = Main.locations[location.getI()][location.getJ() + step];
                }
            }
        }
        catch (Exception e){

        }
    }

    @Override
    public void reproduce(Location location) {
        try {
            if (location.animals.stream().filter(animal -> animal instanceof Sheep).toList().size() > 1 && location.animals.stream().filter(animal -> animal instanceof Sheep).toList().size() < maxCount) {
                Random random = new Random();
                int children = random.nextInt(maxCountOfChildren);
                int i;
                for (i = 0; i < children; i++) {
                    if (location.animals.stream().filter(animal -> animal instanceof Sheep).toList().size() >= maxCount)
                        break;
                    location.animals.add(new Sheep(location));
                }
                if (i > 1)
                    System.out.println("В локации " + location.getI() + " " + location.getJ() + " родилось " + i + " ягнят!");
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
        fullness = fullness - 1;
        if (fullness <= 0) {
            System.out.println("Овца в локации " + location.getI() + " " + location.getJ() + " умерла от голода.");
            location.animals.remove(this);
        }
    }
}
