package com.humanfriends;

import java.time.LocalDate;

class Hamster extends Pets {
    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public String roll() {
        return "Hamster is rolling!";
    }
}
