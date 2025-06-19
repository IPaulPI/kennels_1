package com.kennels;

import java.time.LocalDate;

public class Cat extends Pets {
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Cat";
    }
}
