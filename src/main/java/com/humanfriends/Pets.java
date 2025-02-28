package com.humanfriends;

import java.time.LocalDate;

// Домашние животные
abstract class Pets extends Animal {
    public Pets(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}
