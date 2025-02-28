package com.humanfriends;

import java.util.ArrayList;
import java.util.List;

class Registry {
    private List<Animal> animals;

    public Registry() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public String getAnimalInfo(String name) {
        for (Animal animal : animals) {
            if (animal.name.equals(name)) {
                return animal.getInfo();
            }
        }
        return "Animal not found.";
    }

    public void trainAnimal(String name, String command) {
        for (Animal animal : animals) {
            if (animal.name.equals(name)) {
                animal.addCommand(command);
                return;
            }
        }
    }

    public List<Animal> listAnimalsByBirthDate() {
        animals.sort(Comparator.comparing(a -> a.birthDate));
        return animals;
    }
}
