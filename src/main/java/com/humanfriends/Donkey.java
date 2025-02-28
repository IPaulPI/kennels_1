package com.humanfriends;

class Donkey extends PackAnimals {
    public Donkey(String name, Date birthDate) {
        super(name, birthDate);
    }
    public String bray() {
        return "Donkey is braying!";
    }
}
