package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final ActionDelegate ACTION = () -> System.out.println("Совершается действие");
    private static final String pointsList = new StringBuilder()
            .append("1.Добавить элемент в корень меню").append(System.lineSeparator())
            .append("2.Добавить элемент к родительскому элементу").append(System.lineSeparator())
            .append("3.Вызвать действие пункта меню").append(System.lineSeparator())
            .append("4.Вывестю меню в консоль").append(System.lineSeparator())
            .append("5.Выход из программы").append(System.lineSeparator()).toString();

    private void menuAction() {
        Menu menu = new SimpleMenu();
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(pointsList);
            outer:
            while (true) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                String answer;
                switch (choice) {
                    case 1:
                        System.out.println("Введите название элемента");
                        answer = scanner.nextLine();
                        menu.add(null, answer, ACTION);
                        System.out.println(System.lineSeparator());
                        break;
                    case 2:
                        System.out.println("Введите название родительского элемента");
                        String parent = scanner.nextLine();
                        System.out.println("Введите название дочернего элемента");
                        String child = scanner.nextLine();
                        menu.add(parent, child, ACTION);
                        System.out.println(System.lineSeparator());
                        break;
                    case 3:
                        System.out.println("Введите название элемента");
                        answer = scanner.nextLine();
                        menu.select(answer).ifPresent(m -> m.getActionDelegate().delegate());
                        System.out.println(System.lineSeparator());
                        break;
                    case 4:
                        Printer printer = new Printer();
                        printer.print(menu);
                        System.out.println(System.lineSeparator());
                        break;
                    case 5:
                        break outer;
                    default:
                        System.out.println("Такого пункта нет!");
                        System.out.println(System.lineSeparator());
                }
                System.out.println(pointsList);
            }
        }
    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp();
        todoApp.menuAction();
    }
}
