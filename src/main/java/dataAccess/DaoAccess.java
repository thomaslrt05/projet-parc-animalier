package dataAccess;

import Exceptions.AddAnimalException;
import model.*;
import Exceptions.*;

import java.util.ArrayList;

public interface DaoAccess {

    public void addAnimal(Animal animal) throws AddAnimalException;
    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException;
    public ArrayList<Species> listSpecies() throws ListSpeciesException;
    public ArrayList<Breed> listBreed () throws ListBreedException;
    public ArrayList<Fonction> listFonctions () throws ListFonctionsException;
    public boolean animalExists(String code) throws AnimalExistsException;

}
