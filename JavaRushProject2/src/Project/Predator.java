package Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public abstract class Predator extends Animal {
    @Override
    public void eat(Location location, Animal animal) {
        Random random = new Random();
        int countOfFood = random.nextInt(location.animals.size());
        Animal food = location.animals.get(countOfFood);;
        int probability = -1;
        if (animal instanceof Wolf) probability = ProbabilityOfEating.probabilityForWolf(food);
        else if (animal instanceof Bear) probability = ProbabilityOfEating.probabilityForBear(food);
        else if (animal instanceof Eagle)probability = ProbabilityOfEating.probabilityForEagle(food);
        else if(animal instanceof Fox) probability = ProbabilityOfEating.probabilityForFox(food);
        else if (animal instanceof Snake) probability = ProbabilityOfEating.probabilityForSnake(food);

        boolean eatOrNot = random.nextInt(100) < probability;
        try{
            if (animal.getFullness() < animal.getFoodNeed() && eatOrNot) {
                animal.setFullness(animal.getFullness() + food.getWeight());
                if (food instanceof Rabbit) {
                    location.animals.remove(food);
                    System.out.println("Съеден кролик в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Snake) {
                    location.animals.remove(food);
                    System.out.println("Съедена змея в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Fox) {
                    location.animals.remove(food);
                    System.out.println("Съедена лиса в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Horse) {
                    location.animals.remove(food);
                    System.out.println("Съедена лошадь в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Deer) {
                    location.animals.remove(food);
                    System.out.println("Съеден олень в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Mouse) {
                    location.animals.remove(food);
                    System.out.println("Съедена мышь в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Goat) {
                    location.animals.remove(food);
                    System.out.println("Съедена коза в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Sheep) {
                    location.animals.remove(food);
                    System.out.println("Съедена овца в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Pig) {
                    location.animals.remove(food);
                    System.out.println("Съедена свинья в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Cow) {
                    location.animals.remove(food);
                    System.out.println("Съедена корова в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Duck) {
                    location.animals.remove(food);
                    System.out.println("Съедена утка в локации " + location.getI() + " " + location.getJ());
                } else if (food instanceof Worm) {
                    location.animals.remove(food);
                    System.out.println("Съедена гусеница в локации " + location.getI() + " " + location.getJ());
                }
            }
        }
        catch (Exception e){

        }
    }
}