package com.humanfriends;

import java.time.LocalDate;

class Cat extends Pets {
    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public String pounce() {
        return "Cat is pouncing!";
    }
}
