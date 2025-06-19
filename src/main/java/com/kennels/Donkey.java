package com.kennels;

import java.time.LocalDate;

public class Donkey extends PackAnimals {
    public Donkey(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Donkey";
    }
}
