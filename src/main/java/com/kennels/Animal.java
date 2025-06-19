package com.kennels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private static int totalCount = 0;
    private int id;
    private String name;
    private LocalDate birthDate;
    private List<String> commands = new ArrayList<>();

    public Animal(String name, LocalDate birthDate) {
        this.id = ++totalCount;
        this.name = name;
        this.birthDate = birthDate;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public abstract String getType();
    
    public void setId(int id) {
        this.id = id;
        // Обновляем счетчик, чтобы новые ID были больше существующих
        if (id >= totalCount) {
            totalCount = id + 1;
        }
    }
}
