package controller;
import Exceptions.AddAnimalException;
import Exceptions.CareSheetResearchException;
import business.*;
import model.Animal;
import model.CareSheetResearch;

import java.util.ArrayList;

public class ApplicationController {

    private Manager manager;

    public ApplicationController()  {
        this.manager = new Manager();
    }

    public void addAnimal(Animal animal) throws AddAnimalException{
        manager.addAnimal(animal);
    }

    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException{
        return manager.careSheetSearch(species);
    }
}
