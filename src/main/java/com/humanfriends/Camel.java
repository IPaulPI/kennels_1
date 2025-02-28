package com.humanfriends;

import java.time.LocalDate;

class Camel extends PackAnimals {
    public Camel(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
    public String carryLoad() {
        return "Camel is carrying load!";
    }
}
