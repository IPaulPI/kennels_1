package com.humanfriends;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registry registry = new Registry();
        boolean running = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить животное");
            System.out.println("2. Просмотреть информацию о животном");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Показать всех животных по дате рождения");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя животного: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите тип (Dog, Cat, Hamster, Horse, Donkey): ");
                    String type = scanner.nextLine();
                    System.out.print("Введите дату рождения (yyyy-MM-dd): ");
                    String birthDateString = scanner.nextLine();
                    LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

                    Animal animal = switch (type.toLowerCase()) {
                        case "dog" -> animal = new Dog(name, birthDate);
                        case "cat" -> animal = new Cat(name, birthDate);
                        case "hamster" -> animal = new Hamster(name, birthDate);
                        case "horse" -> animal = new Horse(name, birthDate);
                        case "donkey" -> animal = new Donkey(name, birthDate);
                        default -> null;
                    };

                    if (animal != null) {
                        registry.addAnimal(animal);
                        System.out.println("Животное добавлено!");
                    } else {
                        System.out.println("Неверный тип животного!");
                    }
                    break;

                case 2:
                    System.out.print("Введите имя животного: ");
                    name = scanner.nextLine();
                    String info = registry.getAnimalInfo(name);
                    if (info != null) {
                        System.out.println(info);
                    } else {
                        System.out.println("Животное не найдено!");
                    }
                    break;

                case 3:
                    System.out.print("Введите имя животного: ");
                    name = scanner.nextLine();
                    System.out.print("Введите новую команду: ");
                    String command = scanner.nextLine();
                    if (registry.trainAnimal(name, command)) {
                        System.out.println("Команда добавлена!");
                    } else {
                        System.out.println("Животное не найдено!");
                    }
                    break;

                case 4:
                    for (Animal a : registry.listAnimalsByBirthDate()) {
                        System.out.println(a.getInfo());
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
        scanner.close();
    }
}
