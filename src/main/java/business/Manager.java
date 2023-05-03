package business;
import Exceptions.*;
import model.*;
import dataAccess.*;

import java.sql.SQLException;

public class Manager {
    private DBAccess dao;

    public Manager() {
        this.dao = new DBAccess();
    }

    public void addAnimal(Animal animal) throws AddAnimalException {
        dao.addAnimal(animal);
    }
}
