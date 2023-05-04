package dataAccess;

import Exceptions.AddAnimalException;
import model.*;
import Exceptions.*;

import java.util.ArrayList;

public interface DaoAccess {

    public void addAnimal(Animal animal) throws AddAnimalException;
    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException;

}
