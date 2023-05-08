package business;
import Exceptions.*;
import model.*;
import dataAccess.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Manager {
    private DBAccess dao;

    public Manager() {
        this.dao = new DBAccess();
    }

    public void addAnimal(Animal animal) throws AddAnimalException {
        dao.addAnimal(animal);
    }
    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException {
        return dao.careSheetSearch(species);
    }

    public ArrayList<RemarkByFonction> remarkByFonctions(String fonction) throws RemarkByFonctionsException{
        return dao.remarkByFonctions(fonction);
    }

    public ArrayList<TreatmentByMedicine> treatmentByMedicineResearch (String name) throws MedecineResearchException{
        return dao.treatmentByMedicineResearch(name);
    }

    public ArrayList<Medicine> listMedicine () throws ListMedicineException{
        return dao.listMedicine();
    }

    public void modifyAnimal(Animal animal) throws ModifyAnimalException{
        dao.modifyAnimal(animal);
    }

    public void deleteAnimal(String code) throws DeleteAnimalException{
        dao.deleteAnimal(code);
    }

    public ArrayList<Species> listSpecies() throws ListSpeciesException {
        return dao.listSpecies();
    }

    public ArrayList<Breed> listBreed () throws ListBreedException{
        return dao.listBreed();
    }

    public ArrayList<Fonction> listFonctions () throws ListFonctionsException{
        return dao.listFonctions();
    }

    public boolean animalExists(String code) throws AnimalExistsException{
        return dao.animalExists(code);
    }

    public ArrayList<Animal> getAllAnimals () throws GetAllAnimalsException{
        return dao.getAllAnimals();
    }

    public void endConnection() throws EndConnectionException{
        dao.endConnection();
    }

    public Breed getBreed(String species) throws GetBreedException{
        return dao.getBreed(species);
    }

    public ArrayList<Animal> getAnimalsBySpecies(String codeAnimal) throws GetAnimalsException{
        return dao.getAnimalsBySpecies(codeAnimal);
    }

    public ArrayList<PreparationSheet> getListPreparations(String code) throws ListPreparationsException{
        return dao.getListPreparations(code);
    }
}
