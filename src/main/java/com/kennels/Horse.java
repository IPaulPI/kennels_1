package com.kennels;

import java.time.LocalDate;

public class Horse extends PackAnimals {
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Horse";
    }
}
