package com.humanfriends;

import java.util.ArrayList;
import java.util.List;

abstract class Animal {
    protected String name;          // Имя животного
    protected String birthDate;     // Дата рождения
    protected List<String> commands; // Список команд

    public Animal(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public String getCommands() {
        return commands.isEmpty() ? "Нет команд" : String.join(", ", commands);
    }

    public String getInfo() {
        return name + " (род. " + birthDate + ") — команды: " + getCommands();
    }
}
