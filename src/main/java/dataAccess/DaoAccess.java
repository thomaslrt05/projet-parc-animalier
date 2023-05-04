package dataAccess;

import Exceptions.AddAnimalException;
import model.*;
import Exceptions.*;

import java.util.ArrayList;

public interface DaoAccess {

    void addAnimal(Animal animal) throws AddAnimalException;
    ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException;

}
