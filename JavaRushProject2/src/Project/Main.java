package Project;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    static int x;
    static int y;
    static int i;
    static int j;
    public static Location[][] locations;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размеры острова:");
        x = scanner.nextInt();
        y = scanner.nextInt();
        locations = new Location[x][y];
        System.out.println("Введите время действия симуляции (в секундах):");
        int time = scanner.nextInt();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (i = 0; i < x; i++) {
            for (j = 0; j < y; j++) {
                locations[i][j] = new Location(i, j);
                scheduledExecutorService.scheduleWithFixedDelay(locations[i][j], 100, 1000, TimeUnit.MILLISECONDS);
            }
        }

        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduledExecutorService.shutdown();

        if (scheduledExecutorService.isShutdown()){
            while (!scheduledExecutorService.isTerminated()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (scheduledExecutorService.isTerminated()){
            for (i=0; i<x; i++) {
                for (j=0; j<y; j++){
                    System.out.println("На локации "+ i+ " "+j+" осталось:\n" +
                            "Растений "+locations[i][j].plants.size()+
                            "\nМедведей "+locations[i][j].animals.stream().filter(animal -> animal instanceof Bear).toList().size()+
                            "\nКоров "+locations[i][j].animals.stream().filter(animal -> animal instanceof Cow).toList().size()+
                            "\nОленей "+locations[i][j].animals.stream().filter(animal -> animal instanceof Deer).toList().size()+
                            "\nУток "+locations[i][j].animals.stream().filter(animal -> animal instanceof Duck).toList().size()+
                            "\nОрлов "+locations[i][j].animals.stream().filter(animal -> animal instanceof Eagle).toList().size()+
                            "\nЛисиц "+locations[i][j].animals.stream().filter(animal -> animal instanceof Fox).toList().size()+
                            "\nКоз "+locations[i][j].animals.stream().filter(animal -> animal instanceof Goat).toList().size()+
                            "\nЛошадей "+locations[i][j].animals.stream().filter(animal -> animal instanceof Horse).toList().size()+
                            "\nМышей "+locations[i][j].animals.stream().filter(animal -> animal instanceof Mouse).toList().size()+
                            "\nСвиней "+locations[i][j].animals.stream().filter(animal -> animal instanceof Pig).toList().size()+
                            "\nКроликов "+locations[i][j].animals.stream().filter(animal -> animal instanceof Rabbit).toList().size()+
                            "\nОвец "+locations[i][j].animals.stream().filter(animal -> animal instanceof Sheep).toList().size()+
                            "\nЗмей "+locations[i][j].animals.stream().filter(animal -> animal instanceof Snake).toList().size()+
                            "\nВолков "+locations[i][j].animals.stream().filter(animal -> animal instanceof Wolf).toList().size()+
                            "\nГусениц "+locations[i][j].animals.stream().filter(animal -> animal instanceof Worm).toList().size());
                }
            }
            System.out.println("Потоки остановлены.");
        }
    }

}
