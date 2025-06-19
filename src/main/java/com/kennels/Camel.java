package com.kennels;

import java.time.LocalDate;

public class Camel extends PackAnimals {
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Camel";
    }
}
