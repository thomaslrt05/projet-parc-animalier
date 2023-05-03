package dataAccess;

import Exceptions.AddAnimalException;
import model.*;
import Exceptions.*;

public interface DaoAccess {

    public default void addAnimal(Animal animal) throws AddAnimalException {}
}
