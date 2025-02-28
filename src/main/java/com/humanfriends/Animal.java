package com.humanfriends;

import java.util.*;

// Базовый класс Animal
abstract class Animal {
    protected String name;
    protected Date birthDate;
    protected List<String> commands;

    public Animal(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
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
