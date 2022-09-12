package Project;

import java.util.Random;

public class Plant extends Alive {

    private double weight = 1;
    private int maxCount = 500;
    private int maxCountOfChildren = 100;
    private Location location;

    public Plant(Location location){
        this.location = location;
    }

    @Override
    public void reproduce(Location location) {
        Random random = new Random();
        int children = random.nextInt(maxCountOfChildren);
        int i;
        for (i = 0; i < children; i++) {
            if(location.plants.size()>=maxCount) break;
            location.plants.add(new Plant(location));
        }
        if (i>0) System.out.println("Выросло "+i+" растений в локации "+ location.getI()+" "+location.getJ());
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
    public void run() {
        this.reproduce(location);
    }
}
