package com.kennels;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatabaseHandler.initializeDatabase();
            showMainMenu();
        } catch (SQLException e) {
            System.err.println("Ошибка базы данных: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void showMainMenu() throws SQLException {
        while (true) {
            System.out.println("\n=== Реестр животных питомника ===");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Показать список команд животного");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Показать животных по дате рождения");
            System.out.println("5. Показать общее количество животных");
            System.out.println("6. Показать лошадей и ослов (объединенная таблица)");
            System.out.println("7. Показать животных в возрасте 1-3 лет");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> addNewAnimal();
                case 2 -> showAnimalCommands();
                case 3 -> trainAnimal();
                case 4 -> showAnimalsByBirthDate();
                case 5 -> showTotalAnimalsCount();
                case 6 -> showHorsesAndDonkeys();
                case 7 -> showYoungAnimals();
                case 0 -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void addNewAnimal() throws SQLException {
        System.out.println("\n=== Добавление нового животного ===");
        System.out.println("Выберите тип животного:");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("4. Лошадь");
        System.out.println("5. Верблюд");
        System.out.println("6. Осел");
        System.out.print("Ваш выбор: ");
        
        int type = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        
        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        
        Animal animal = switch (type) {
            case 1 -> new Dog(name, birthDate);
            case 2 -> new Cat(name, birthDate);
            case 3 -> new Hamster(name, birthDate);
            case 4 -> new Horse(name, birthDate);
            case 5 -> new Camel(name, birthDate);
            case 6 -> new Donkey(name, birthDate);
            default -> throw new IllegalArgumentException("Неверный тип животного");
        };
        
        // Добавление начальных команд
        System.out.print("Введите команды через запятую (например, Сидеть, Лежать): ");
        String[] commands = scanner.nextLine().split(",");
        for (String command : commands) {
            animal.addCommand(command.trim());
        }
        
        DatabaseHandler.saveAnimal(animal);
        System.out.printf("Животное %s добавлено с ID %d\n", animal.getName(), animal.getId());
    }

    private static void showAnimalCommands() throws SQLException {
        System.out.println("\n=== Список команд животного ===");
        System.out.print("Введите ID животного: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        
        List<Animal> animals = DatabaseHandler.getAllAnimals();
        animals.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .ifPresentOrElse(
                animal -> {
                    System.out.printf("Команды животного %s (ID %d):\n", 
                        animal.getName(), animal.getId());
                    if (animal.getCommands().isEmpty()) {
                        System.out.println("Нет обученных команд");
                    } else {
                        animal.getCommands().forEach(System.out::println);
                    }
                },
                () -> System.out.println("Животное с ID " + id + " не найдено")
            );
    }

    private static void trainAnimal() throws SQLException {
        System.out.println("\n=== Обучение новой команде ===");
        System.out.print("Введите ID животного: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        
        System.out.print("Введите новую команду: ");
        String newCommand = scanner.nextLine();
        
        List<Animal> animals = DatabaseHandler.getAllAnimals();
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                animal.addCommand(newCommand);
                DatabaseHandler.updateAnimalCommands(id, animal.getCommands());
                System.out.printf("Животное %s (ID %d) обучено команде '%s'\n",
                    animal.getName(), animal.getId(), newCommand);
                return;
            }
        }
        System.out.println("Животное с ID " + id + " не найдено");
    }

    private static void showAnimalsByBirthDate() throws SQLException {
        System.out.println("\n=== Список животных по дате рождения ===");
        List<Animal> animals = DatabaseHandler.getAllAnimals();
        
        if (animals.isEmpty()) {
            System.out.println("В реестре нет животных");
            return;
        }
        
        // Сортировка по дате рождения
        animals.sort((a1, a2) -> a1.getBirthDate().compareTo(a2.getBirthDate()));
        
        System.out.println("ID | Имя         | Тип       | Дата рождения | Команд");
        System.out.println("------------------------------------------------");
        for (Animal animal : animals) {
            System.out.printf("%2d | %-10s | %-8s | %12s | %d\n",
                animal.getId(),
                animal.getName(),
                animal.getClass().getSimpleName(),
                animal.getBirthDate(),
                animal.getCommands().size());
        }
    }

    private static void showTotalAnimalsCount() throws SQLException {
        int count = DatabaseHandler.getAllAnimals().size();
        System.out.println("\nОбщее количество животных в питомнике: " + count);
    }

    private static void showHorsesAndDonkeys() throws SQLException {
        System.out.println("\n=== Лошади и ослы (объединенная таблица) ===");
        List<Animal> animals = DatabaseHandler.getHorsesAndDonkeys();
        
        if (animals.isEmpty()) {
            System.out.println("Нет данных о лошадях и ослах");
            return;
        }
        
        System.out.println("ID | Имя         | Тип    | Дата рождения | Команд");
        System.out.println("--------------------------------------------");
        for (Animal animal : animals) {
            System.out.printf("%2d | %-10s | %-5s | %12s | %d\n",
                animal.getId(),
                animal.getName(),
                animal.getClass().getSimpleName(),
                animal.getBirthDate(),
                animal.getCommands().size());
        }
    }

    private static void showYoungAnimals() throws SQLException {
        System.out.println("\n=== Животные в возрасте 1-3 лет ===");
        List<Animal> animals = DatabaseHandler.getAllAnimals();
        
        if (animals.isEmpty()) {
            System.out.println("В реестре нет животных");
            return;
        }
        
        LocalDate now = LocalDate.now();
        boolean found = false;
        
        System.out.println("ID | Имя         | Тип       | Возраст (лет) | Команд");
        System.out.println("----------------------------------------------------");
        
        for (Animal animal : animals) {
            Period period = Period.between(animal.getBirthDate(), now);
            int years = period.getYears();
            
            if (years >= 1 && years <= 3) {
                System.out.printf("%2d | %-10s | %-8s | %13d | %d\n",
                    animal.getId(),
                    animal.getName(),
                    animal.getClass().getSimpleName(),
                    years,
                    animal.getCommands().size());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Нет животных в возрасте 1-3 лет");
        }
    }
}
