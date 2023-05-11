//Лабораторна робота №7
//Завдання 1.1

import java.util.Random;

public class Lab7_1 {
    public static void main(String[] args) {
        // Створюємо потік для кроликів
        Thread rabbitsThread = new Thread(() -> {
            int x = 0; // початкова позиція кроликів
            int v = 5; // швидкість руху кроликів
            int n = 5; // час до зміни напрямку руху кроликів
            Random random = new Random();
            int direction = random.nextInt(360); // початковий напрямок руху кроликів
            while (!Thread.currentThread().isInterrupted()) {
                // Рухаємо кроликів у поточному напрямку на відстань v
                x += v * Math.cos(Math.toRadians(direction));
                // Генеруємо випадкове число для визначення, чи треба змінювати напрямок руху
                if (random.nextInt(n) == 0) {
                    direction = random.nextInt(360); // змінюємо напрямок руху
                }
                System.out.println("Кролики: позиція = " + x + ", напрямок = " + direction);
                try {
                    // Зупиняємо потік на 100 мілісекунд для затримки
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // Якщо потік було перервано, то виходимо з циклу
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Створюємо потік для альбіносів
        Thread albinoThread = new Thread(() -> {
            int x = 0; // початкова позиція альбіносів
            int v = 10; // швидкість руху альбіносів
            int maxX = 100; // кінцева позиція альбіносів
            while (x < maxX && !Thread.currentThread().isInterrupted()) {
                // Рухаємо альбіносів вправо на відстань v
                x += v;
                System.out.println("Альбіноси: позиція = " + x);
                try {
                    // Зупиняємо потік на 500 мілісекунд для затримки
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // Якщо потік було перервано, то виходимо з циклу
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Запускаємо обидва потоки
        rabbitsThread.start();
        albinoThread.start();

        // Зупиняємо потоки через 10 секунд
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rabbitsThread.interrupt();
        albinoThread.interrupt();
    }
}