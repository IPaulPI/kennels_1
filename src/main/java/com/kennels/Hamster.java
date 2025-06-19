package com.kennels;

import java.time.LocalDate;

public class Hamster extends Pets {
    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Hamster";
    }
}
