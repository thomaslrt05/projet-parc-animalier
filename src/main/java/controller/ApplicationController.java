package controller;
import Exceptions.AddAnimalException;
import Exceptions.CareSheetResearchException;
import Exceptions.listSpeciesException;
import business.*;
import model.Animal;
import model.CareSheetResearch;
import model.Species;

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

    public ArrayList<Species> listSpecies() throws listSpeciesException {
        return manager.listSpecies();
    }
}
