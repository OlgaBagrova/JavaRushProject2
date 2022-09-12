package Project;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Location implements Runnable {
    ArrayList<Plant> plants = new ArrayList<>();
    ArrayList<Animal> animals = new ArrayList<>();

    private final int i;
    private final int j;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Location(int i, int j) {
        this.i = i;
        this.j = j;

        for (int k = 0; k < new Plant(this).getMaxCount() / 2; k++) {
            plants.add(new Plant(this));
        }

        for (int k = 0; k < new Rabbit(this).getMaxCount() / 2; k++) {
            animals.add(new Rabbit(this));
        }
        for (int k = 0; k < new Worm(this).getMaxCount() / 2; k++) {
            animals.add(new Worm(this));
        }
        for (int k = 0; k < new Wolf(this).getMaxCount() / 2; k++) {
            animals.add(new Wolf(this));
        }
        for (int k = 0; k < new Bear(this).getMaxCount() / 2; k++) {
            animals.add(new Bear(this));
        }
        for (int k = 0; k < new Fox(this).getMaxCount() / 2; k++) {
            animals.add(new Fox(this));
        }
        for (int k = 0; k < new Snake(this).getMaxCount() / 2; k++) {
            animals.add(new Snake(this));
        }
        for (int k = 0; k < new Eagle(this).getMaxCount() / 2; k++) {
            animals.add(new Eagle(this));
        }
        for (int k = 0; k < new Cow(this).getMaxCount() / 2; k++) {
            animals.add(new Cow(this));
        }
        for (int k = 0; k < new Deer(this).getMaxCount() / 2; k++) {
            animals.add(new Deer(this));
        }
        for (int k = 0; k < new Duck(this).getMaxCount() / 2; k++) {
            animals.add(new Duck(this));
        }
        for (int k = 0; k < new Goat(this).getMaxCount() / 2; k++) {
            animals.add(new Goat(this));
        }
        for (int k = 0; k < new Horse(this).getMaxCount() / 2; k++) {
            animals.add(new Horse(this));
        }
        for (int k = 0; k < new Mouse(this).getMaxCount() / 2; k++) {
            animals.add(new Mouse(this));
        }
        for (int k = 0; k < new Pig(this).getMaxCount() / 2; k++) {
            animals.add(new Pig(this));
        }
        for (int k = 0; k < new Sheep(this).getMaxCount() / 2; k++) {
            animals.add(new Sheep(this));
        }
    }

    @Override
    public void run() {

        System.out.println("Локация " + i + " " + j + ":\n" +
                "Растений " + plants.size() +
                "\nМедведей " + animals.stream().filter(animal -> animal instanceof Bear).toList().size() +
                "\nКоров " + animals.stream().filter(animal -> animal instanceof Cow).toList().size() +
                "\nОленей " + animals.stream().filter(animal -> animal instanceof Deer).toList().size() +
                "\nУток " + animals.stream().filter(animal -> animal instanceof Duck).toList().size() +
                "\nОрлов " + animals.stream().filter(animal -> animal instanceof Eagle).toList().size() +
                "\nЛисиц " + animals.stream().filter(animal -> animal instanceof Fox).toList().size() +
                "\nКоз " + animals.stream().filter(animal -> animal instanceof Goat).toList().size() +
                "\nЛошадей " + animals.stream().filter(animal -> animal instanceof Horse).toList().size() +
                "\nМышей " + animals.stream().filter(animal -> animal instanceof Mouse).toList().size() +
                "\nСвиней " + animals.stream().filter(animal -> animal instanceof Pig).toList().size() +
                "\nКроликов " + animals.stream().filter(animal -> animal instanceof Rabbit).toList().size() +
                "\nОвец " + animals.stream().filter(animal -> animal instanceof Sheep).toList().size() +
                "\nЗмей " + animals.stream().filter(animal -> animal instanceof Snake).toList().size() +
                "\nВолков " + animals.stream().filter(animal -> animal instanceof Wolf).toList().size() +
                "\nГусениц " + animals.stream().filter(animal -> animal instanceof Worm).toList().size());

        ExecutorService executorService = Executors.newFixedThreadPool(15);

        for (int k = 0; k < plants.size(); k++) {
            executorService.execute(plants.get(k));
        }

        for (int k = 0; k < animals.size(); k++) {
            executorService.execute(animals.get(k));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
