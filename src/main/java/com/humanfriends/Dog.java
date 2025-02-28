package com.humanfriends;

import java.time.LocalDate;

class Dog extends Pets {
    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public String bark() {
        return "Woof!";
    }
}
