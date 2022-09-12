package Project;

import java.util.ArrayList;
import java.util.Random;

public class Wolf extends Predator{

    private double weight = 50;
    private int maxCount = 30;
    private int maxCountOfChildren = 3;
    private int speed = 3;
    private double foodNeed = 8;
    private double fullness = 3;
    private Location location;

    public Wolf(Location location){
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
        {
            Random random = new Random();
            int step = random.nextInt(1, speed+1);
            try {
                if (random.nextInt() % 2 == 0) {
                    if ((location.getI() + step) < Main.locations.length && Main.locations[location.getI() + step][location.getJ()].animals.stream().filter(animal -> animal instanceof Wolf).toList().size() < maxCount) {
                        Main.locations[location.getI() + step][location.getJ()].animals.add(this);
                        location.animals.remove(this);
                        System.out.println("Волк ушел из локации " + location.getI() + " " + location.getJ() + " в локацию " + (location.getI() + step) + " " + location.getJ());
                        this.location = Main.locations[location.getI() + step][location.getJ()];
                    }
                } else {
                    if (location.getJ() + step < Main.locations[0].length && Main.locations[location.getI()][location.getJ() + step].animals.stream().filter(animal -> animal instanceof Wolf).toList().size() < maxCount) {
                        Main.locations[location.getI()][location.getJ() + step].animals.add(this);
                        location.animals.remove(this);
                        System.out.println("Волк ушел из локации " + location.getI() + " " + location.getJ() + " в локацию " + location.getI() + " " + (location.getJ() + step));
                        this.location = Main.locations[location.getI()][location.getJ() + step];
                    }
                }
            }
            catch (Exception e){

            }
        }
    }

    @Override
    public void reproduce(Location location) {
        try {
            if (location.animals.stream().filter(animal -> animal instanceof Wolf).toList().size() > 1 && location.animals.stream().filter(animal -> animal instanceof Wolf).toList().size() < maxCount) {
                Random random = new Random();
                int children = random.nextInt(maxCountOfChildren);
                int i;
                for (i = 0; i < children; i++) {
                    if (location.animals.stream().filter(animal -> animal instanceof Wolf).toList().size() >= maxCount)
                        break;
                    location.animals.add(new Wolf(location));
                }
                if (i > 1)
                    System.out.println("В локации " + location.getI() + " " + location.getJ() + " родилось " + i + " волчат!");
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
            System.out.println("Волк в локации " + location.getI() + " " + location.getJ() + " умер от голода.");
            location.animals.remove(this);
        }
    }
}
