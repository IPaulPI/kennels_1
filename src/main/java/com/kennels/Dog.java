package com.kennels;

import java.time.LocalDate;

public class Dog extends Pets {
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    @Override
    public String getType() {
        return "Dog";
    }
}
