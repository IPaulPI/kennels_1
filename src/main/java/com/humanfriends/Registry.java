package com.humanfriends;

import java.util.ArrayList;
import java.util.List;

class Registry {
    private List<Animal> animals = new ArrayList<>();
    private static int animalCount = 0;

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animalCount++;
    }

    public void showAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Реестр пуст.");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal.getInfo());
        }
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.name.equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
