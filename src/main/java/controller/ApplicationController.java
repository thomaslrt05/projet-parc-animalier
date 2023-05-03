package controller;
import Exceptions.AddAnimalException;
import Exceptions.DataException;
import business.*;
import model.Animal;

public class ApplicationController {

    private Manager manager;

    public ApplicationController()  {
        this.manager = new Manager();
    }

    public void addAnimal(Animal animal) throws AddAnimalException{
        manager.addAnimal(animal);
    }
}
