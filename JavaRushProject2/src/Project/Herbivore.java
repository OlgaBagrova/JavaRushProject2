package Project;

public abstract class Herbivore extends Animal {
    @Override
    public synchronized void eat(Location location, Animal animal) {
        double fullness = animal.getFullness();
        try {
            if (fullness < animal.getFoodNeed() && !location.plants.isEmpty()) {
                Plant plant = location.plants.stream().findAny().orElse(null);
                fullness = fullness + plant.getWeight();
                location.plants.remove(plant);
                animal.setFullness(fullness);
            }
        }
        catch (Exception e){

        }
    }
}