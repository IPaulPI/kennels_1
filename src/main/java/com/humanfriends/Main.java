package com.humanfriends;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registry registry = new Registry();

        while (true) {
            System.out.println("\n1. Добавить собаку");
            System.out.println("2. Показать всех животных");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя собаки: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите дату рождения (YYYY-MM-DD): ");
                    String birthDate = scanner.nextLine();
                    Dog dog = new Dog(name, birthDate);

                    System.out.print("Введите команды через запятую (например, 'Сидеть, Лежать'): ");
                    String[] commands = scanner.nextLine().split(",");
                    for (String command : commands) {
                        dog.addCommand(command.trim());
                    }

                    registry.addAnimal(dog);
                    System.out.println("Собака добавлена!");
                    break;
                case 2:
                    registry.showAnimals();
                    break;
                case 3:
                    if (registry.getAnimals().isEmpty()) {
                        System.out.println("Реестр пуст.");
                        break;
                    }
                    System.out.print("Введите имя животного, которого хотите обучить: ");
                    String searchName = scanner.nextLine();
                    Animal foundAnimal = registry.findAnimalByName(searchName);
                    if (foundAnimal == null) {
                        System.out.println("Животное не найдено.");
                    } else {
                        System.out.print("Введите новую команду: ");
                        String newCommand = scanner.nextLine();
                        foundAnimal.addCommand(newCommand);
                        System.out.println("Команда добавлена!");
                    }
                    break;
                case 4:
                    System.out.println("Всего животных: " + Registry.getAnimalCount());
                    return;
                default:
                    System.out.println("Некорректный ввод.");
            }
        }
    }
}
