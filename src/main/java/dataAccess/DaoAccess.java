package dataAccess;


import model.*;
import Exceptions.*;
import java.util.ArrayList;

public interface DaoAccess {

    public void addAnimal(Animal animal) throws AddAnimalException;
    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException;
    public ArrayList<RemarkByFonction> remarkByFonctions(String fonction) throws RemarkByFonctionsException;
    public ArrayList<TreatmentByMedicine> treatmentByMedicineResearch (String name) throws MedecineResearchException;
    public ArrayList<Animal> getAllAnimals () throws GetAllAnimalsException;
    public ArrayList<Medicine> listMedicine () throws ListMedicineException;
    public void modifyAnimal(Animal animal) throws ModifyAnimalException;
    public void deleteAnimal(String code) throws DeleteAnimalException;
    public ArrayList<Species> listSpecies() throws ListSpeciesException;
    public ArrayList<Breed> listBreed () throws ListBreedException;
    public ArrayList<Fonction> listFonctions () throws ListFonctionsException;
    public boolean animalExists(String code) throws AnimalExistsException;
    public void endConnection() throws EndConnectionException;
    public Breed getBreed(String species) throws GetBreedException;
    public ArrayList<Animal> getAnimalsBySpecies(String codeAnimal) throws GetAnimalsException;
    public ArrayList<PreparationSheet> getListPreparations(String code) throws ListPreparationsException;
    public void modifyPreparationSheet(String employeId,int code) throws ModifyPreparationsheetException;
    public Species getSpecies(String code) throws GetSpeciesException;
    public ArrayList<Employee> listEmployees() throws ListEmployeesException;


}
