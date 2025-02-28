package com.humanfriends;

import java.util.Comparator;
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

    public boolean trainAnimal(String name, String command) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                animal.addCommand(command);
                return true;
            }
        }
	return false;
    }

    public void showAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal.getInfo());
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.name.equals(name)) {
                return animal;
            }
        }
        return null;
    }

    public int getAnimalCount() {
        return animals.size();
    }


    public List<Animal> listAnimalsByBirthDate() {
        animals.sort(Comparator.comparing(a -> a.birthDate));
        return animals;
    }
}

