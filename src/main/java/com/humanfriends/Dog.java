package com.humanfriends;

class Dog extends Pets {
    public Dog(String name, Date birthDate) {
        super(name, birthDate);
    }
    public String bark() {
        return "Woof!";
    }
}
