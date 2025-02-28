package com.humanfriends;

import java.time.LocalDate;

// Вьючные животные
abstract class PackAnimals extends Animal {
    public PackAnimals(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}
