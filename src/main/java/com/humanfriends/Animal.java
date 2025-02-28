package com.humanfriends;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

abstract class Animal {
    protected String name;
    protected LocalDate birthDate;
    protected List<String> commands;

    public Animal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }

    public String getName() {  // Добавляем метод getName()
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getInfo() {
        return "Name: " + name + ", Birth Date: " + birthDate + ", Commands: " + commands;
    }
}
