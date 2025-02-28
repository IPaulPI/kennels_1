package com.humanfriends;

import java.time.LocalDate;

class Horse extends PackAnimals {
    public Horse(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public String gallop() {
        return "Horse is galloping!";
    }
}
