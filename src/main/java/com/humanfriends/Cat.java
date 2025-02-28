package com.humanfriends;

class Cat extends Pets {
    public Cat(String name, Date birthDate) {
        super(name, birthDate);
    }
    public String pounce() {
        return "Cat is pouncing!";
    }
}
