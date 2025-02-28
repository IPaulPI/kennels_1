package com.humanfriends;

import java.time.LocalDate;

class Donkey extends PackAnimals {
    public Donkey(String name, LocalDate birthDate) {
        super(name, birthDate);
    }

    public String carryLoad() {
        return "Donkey is carrying load!";
    }
}
