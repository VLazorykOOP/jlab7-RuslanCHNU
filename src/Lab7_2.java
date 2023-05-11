//Лабораторна робота №7
//Завдання 1.2

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class Lab7_2 {
    private static volatile boolean stop = false;

    public static void main(String[] args) {
        // Створюємо список для зберігання назв віршів
        List<String> poems = new ArrayList<>();

        // Додаємо назви віршів деякого автора до списку
        poems.add("Тече вода в ярі");
        poems.add("Червона калина");
        poems.add("Моя мрія");
        poems.add("І знову вітер, і знову дощ");
        poems.add("Ой у лузі червона калина");
        poems.add("Все летить і все минає");

        // Створення кнопки зупинки
        JButton stopButton = new JButton("Зупинити інтелект");
        stopButton.addActionListener(e -> stop = true);

        // Створення кнопки відновлення роботи інтелекту
        JButton resumeButton = new JButton("Відновити роботу інтелекту");
        resumeButton.addActionListener(e -> stop = false);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Вірші");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(stopButton);
        buttonPanel.add(resumeButton);

        frame.add(scrollPane);
        frame.add(buttonPanel);

        frame.pack();
        frame.setVisible(true);

        while (true) {
            // Перевірка, чи потрібно зупинити роботу інтелекту
            while (stop) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Помилка: " + e.getMessage());
                }
            }

            // Сортуємо список за зростанням довжини рядків
            Collections.sort(poems, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s1.length() - s2.length();
                }
            });

            // Виводимо вірші у відсортованому порядку на екран та в текстове поле
            System.out.println("Відсортовані вірші:");
            textArea.append("Відсортовані вірші:\n");
            for (String poem : poems) {
                System.out.println(poem);
                textArea.append(poem + "\n");
            }
            System.out.println();
            textArea.append("\n");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Помилка: " + e.getMessage());
            }

            // Очистка текстового поля
            textArea.setText("");
        }
    }
}