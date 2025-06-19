package com.kennels;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/HumanFriends";
    private static final String USER = "kennel_user";
    private static final String PASSWORD = "securepassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Создание таблиц, если они не существуют
            stmt.execute("CREATE TABLE IF NOT EXISTS Pets (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "Name VARCHAR(50) NOT NULL," +
                "Type VARCHAR(20) NOT NULL," +
                "BirthDate DATE NOT NULL," +
                "Commands TEXT)");
                
            stmt.execute("CREATE TABLE IF NOT EXISTS PackAnimals (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "Name VARCHAR(50) NOT NULL," +
                "Type VARCHAR(20) NOT NULL," +
                "BirthDate DATE NOT NULL," +
                "Commands TEXT)");
                
            stmt.execute("CREATE TABLE IF NOT EXISTS HorsesAndDonkeys AS " +
                "SELECT * FROM PackAnimals WHERE Type IN ('Horse', 'Donkey')");
                
            stmt.execute("CREATE TABLE IF NOT EXISTS AllAnimals AS " +
                "SELECT ID, Name, Type, BirthDate, Commands FROM Pets " +
                "UNION ALL " +
                "SELECT ID, Name, Type, BirthDate, Commands FROM PackAnimals");
        }
    }

    public static void saveAnimal(Animal animal) throws SQLException {
        String table = (animal instanceof Pets) ? "Pets" : "PackAnimals";
        String sql = String.format(
            "INSERT INTO %s (Name, Type, BirthDate, Commands) VALUES (?, ?, ?, ?)",
            table
        );

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getType());
            stmt.setDate(3, Date.valueOf(animal.getBirthDate()));
            stmt.setString(4, String.join(", ", animal.getCommands()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }
        }
    }

    public static List<Animal> getAllAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT 'Pets' as source, ID, Name, Type, BirthDate, Commands FROM Pets " +
                     "UNION ALL " +
                     "SELECT 'PackAnimals' as source, ID, Name, Type, BirthDate, Commands FROM PackAnimals";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Animal animal = createAnimalFromResultSet(rs);
                if (animal != null) {
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    public static List<Animal> getHorsesAndDonkeys() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT ID, Name, Type, BirthDate, Commands FROM HorsesAndDonkeys";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Animal animal = createAnimalFromResultSet(rs);
                if (animal != null) {
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    public static List<Animal> getAllAnimalsCombined() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT ID, Name, Type, BirthDate, Commands, " +
                     "CASE WHEN Type IN ('Dog','Cat','Hamster') THEN 'Pets' " +
                     "ELSE 'PackAnimals' END AS Source " +
                     "FROM AllAnimals";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Animal animal = createAnimalFromResultSet(rs);
                if (animal != null) {
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    public static List<Animal> getYoungAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT ID, Name, Type, BirthDate, Commands FROM (" +
                     "SELECT * FROM Pets UNION ALL SELECT * FROM PackAnimals" +
                     ") AS AllAnimals " +
                     "WHERE BirthDate BETWEEN DATE_SUB(CURDATE(), INTERVAL 3 YEAR) " +
                     "AND DATE_SUB(CURDATE(), INTERVAL 1 YEAR)";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Animal animal = createAnimalFromResultSet(rs);
                if (animal != null) {
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    public static void updateAnimalCommands(int id, List<String> commands) throws SQLException {
        // Сначала пробуем обновить в таблице Pets
        String sql = "UPDATE Pets SET Commands = ? WHERE ID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, String.join(", ", commands));
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            
            if (rows == 0) {
                // Если не найдено в Pets, пробуем в PackAnimals
                sql = "UPDATE PackAnimals SET Commands = ? WHERE ID = ?";
                try (PreparedStatement stmt2 = conn.prepareStatement(sql)) {
                    stmt2.setString(1, String.join(", ", commands));
                    stmt2.setInt(2, id);
                    stmt2.executeUpdate();
                }
            }
        }
    }

    private static Animal createAnimalFromResultSet(ResultSet rs) throws SQLException {
        String type = rs.getString("Type");
        String name = rs.getString("Name");
        LocalDate birthDate = rs.getDate("BirthDate").toLocalDate();
        String commandsStr = rs.getString("Commands");
        String[] commands = commandsStr != null ? commandsStr.split(",\\s*") : new String[0];

        Animal animal = switch (type.toLowerCase()) {
            case "dog" -> new Dog(name, birthDate);
            case "cat" -> new Cat(name, birthDate);
            case "hamster" -> new Hamster(name, birthDate);
            case "horse" -> new Horse(name, birthDate);
            case "camel" -> new Camel(name, birthDate);
            case "donkey" -> new Donkey(name, birthDate);
            default -> null;
        };

        if (animal != null) {
            animal.setId(rs.getInt("ID"));
            for (String command : commands) {
                if (!command.trim().isEmpty()) {
                    animal.addCommand(command.trim());
                }
            }
        }
        return animal;
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn.isValid(2);
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
