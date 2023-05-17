package controller;
import Exceptions.*;
import business.*;
import model.*;

import java.util.ArrayList;

public class ApplicationController {

    private Manager manager;

    public ApplicationController() throws SingletonConnexionException {
        this.manager = new Manager();
    }

    public void addAnimal(Animal animal) throws AddAnimalException{
        manager.addAnimal(animal);
    }

    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException{
        return manager.careSheetSearch(species);
    }

    public ArrayList<RemarkByFonction> remarkByFonctions(String fonction) throws RemarkByFonctionsException{
        return manager.remarkByFonctions(fonction);
    }

    public ArrayList<TreatmentByMedicine> treatmentByMedicineResearch (String name) throws MedecineResearchException, EmptyNameException {
        return manager.treatmentByMedicineResearch(name);
    }

    public ArrayList<Medicine> listMedicine () throws ListMedicineException{
        return manager.listMedicine();
    }

    public void modifyAnimal(Animal animal) throws ModifyAnimalException{
        manager.modifyAnimal(animal);
    }

    public void deleteAnimal(String code) throws DeleteAnimalException{
        manager.deleteAnimal(code);
    }
    

    public ArrayList<Breed> listBreed () throws ListBreedException{
        return manager.listBreed();
    }

    public ArrayList<Fonction> listFonctions () throws ListFonctionsException{
        return manager.listFonctions();
    }

    public boolean animalExists(String code) throws AnimalExistsException{
        return manager.animalExists(code);
    }

    public ArrayList<Species> listSpecies() throws ListSpeciesException {
        return manager.listSpecies();
    }

    public ArrayList<Animal> getAllAnimals () throws GetAllAnimalsException{
        return manager.getAllAnimals();
    }

    public void endConnection() throws EndConnectionException{
        manager.endConnection();
    }

    public Breed getBreed(String species) throws GetBreedException{
        return manager.getBreed(species);
    }

    public ArrayList<Animal> getAnimalsBySpecies(String codeAnimal) throws GetAnimalsException{
        return manager.getAnimalsBySpecies(codeAnimal);
    }

    public ArrayList<PreparationSheet> getListPreparations(String code) throws ListPreparationsException{
        return manager.getListPreparations(code);
    }

    public void modifyPreparationsheet(String employeId,int code) throws ModifyPreparationsheetException{
        manager.modifyPreparationSheet(employeId,code);
    }

    public Species getSpecies(String code) throws GetSpeciesException{
        return manager.getSpecies(code);
    }

    public ArrayList<Employee> listEmployees() throws ListEmployeesException{
        return manager.listEmployees();
    }
}
