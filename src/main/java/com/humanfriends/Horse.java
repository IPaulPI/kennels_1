package com.humanfriends;

class Horse extends PackAnimals {
    public Horse(String name, Date birthDate) {
        super(name, birthDate);
    }
    public String gallop() {
        return "Horse is galloping!";
    }
}
