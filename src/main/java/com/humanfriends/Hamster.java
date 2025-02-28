package com.humanfriends;

class Hamster extends Pets {
    public Hamster(String name, Date birthDate) {
        super(name, birthDate);
    }
    public String roll() {
        return "Hamster is rolling!";
    }
}
