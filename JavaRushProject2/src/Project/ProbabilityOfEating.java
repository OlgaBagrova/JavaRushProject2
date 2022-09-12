package Project;

import java.nio.channels.Pipe;
import java.util.Random;

public class ProbabilityOfEating {
    public static int probabilityForWolf(Animal animal){
        if(animal instanceof Horse) return 10;
        else if(animal instanceof Deer) return 15;
        else if (animal instanceof Rabbit) return 60;
        else if(animal instanceof Mouse) return 80;
        else if(animal instanceof Goat) return 60;
        else if (animal instanceof Sheep) return 70;
        else if (animal instanceof Pig) return 15;
        else if (animal instanceof Cow) return 10;
        else if (animal instanceof Duck) return 40;
        else return 0;
    }

    public static int probabilityForSnake(Animal animal){
        if (animal instanceof Rabbit) return 20;
        else if(animal instanceof Mouse) return 40;
        else if (animal instanceof Duck) return 10;
        else if (animal instanceof Fox) return 15;
        else return 0;
    }

    public static int probabilityForFox(Animal animal){
        if (animal instanceof Rabbit) return 70;
        else if(animal instanceof Mouse) return 90;
        else if (animal instanceof Duck) return 60;
        else if (animal instanceof Worm) return 40;
        else return 0;
    }

    public static int probabilityForBear(Animal animal){
        if(animal instanceof Horse) return 40;
        else if(animal instanceof Deer) return 80;
        else if (animal instanceof Rabbit) return 80;
        else if(animal instanceof Mouse) return 90;
        else if(animal instanceof Goat) return 70;
        else if (animal instanceof Sheep) return 70;
        else if (animal instanceof Pig) return 50;
        else if (animal instanceof Cow) return 20;
        else if (animal instanceof Duck) return 10;
        else if (animal instanceof Snake) return 80;
        else return 0;
    }

    public static int probabilityForEagle(Animal animal){
        if (animal instanceof Rabbit) return 90;
        else if(animal instanceof Mouse) return 90;
        else if (animal instanceof Duck) return 80;
        else if (animal instanceof Fox) return 10;
        else return 0;
    }
}
