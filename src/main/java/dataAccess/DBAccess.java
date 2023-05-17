package dataAccess;
import model.*;
import Exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;


public class DBAccess implements DaoAccess{
    private Connection connection;
    public DBAccess() throws SingletonConnexionException{
            this.connection = SingletonConnexion.getInstance();
    }


    public void addAnimal(Animal animal) throws AddAnimalException {
        String sqlInstruction = "insert into animal (code, name, sex, isDangerous, weight, arrivalDate, nickName, breed) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, animal.getCode());
            preparedStatement.setString(2, animal.getName());
            preparedStatement.setString(3, animal.getSex().getLabel());
            preparedStatement.setBoolean(4, animal.getDangerous());
            preparedStatement.setDouble(5, animal.getWeight());
            java.util.Date arrivalDate = animal.getArrivalDate();
            java.sql.Date sqlArrivalDate = new java.sql.Date(arrivalDate.getTime());
            preparedStatement.setDate(6, sqlArrivalDate);
            preparedStatement.setString(7, animal.getNickName());
            preparedStatement.setString(8, animal.getBreed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = "Erreur lors de l'ajout de l'animal";
            throw new AddAnimalException(message);
        }
    }

    public ArrayList<CareSheetResearch> careSheetSearch(String species) throws CareSheetResearchException {
        ArrayList<CareSheetResearch> allData = new ArrayList<CareSheetResearch>();
        try {
            String sqlInstruction =  "SELECT c.label \"careSheet\", a.name, a.code \"codeAnimal\" , b.label \"breedLabel\", c.date \n" +
                    "FROM caresheet c \n" +
                    "LEFT JOIN animal a ON c.animal = a.code \n" +
                    "LEFT JOIN breed b ON a.breed = b.id \n" +
                    "LEFT JOIN species s ON b.specification = s.id \n" +
                    "WHERE s.id =  ?\n" +
                    "ORDER BY s.label, a.name";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, species);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    String name = data.getString("name");
                    String label = data.getString("careSheet");
                    String code = data.getString("codeAnimal");
                    String labelBreed = data.getString("breedLabel");
                    java.util.Date date = data.getDate("date");

                    CareSheetResearch search = new CareSheetResearch(label,code,name,labelBreed,date);
                    allData.add(search);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la recherche";
            throw new CareSheetResearchException(message);
        }
        return allData;
    }

    public ArrayList<RemarkByFonction> remarkByFonctions(String fonction) throws RemarkByFonctionsException {
        ArrayList<RemarkByFonction> allData = new ArrayList<RemarkByFonction>();
        try {
            String sqlInstruction = "SELECT e.lastName, e.firstName, c.label, r.description \n" +
                    "FROM remark r\n" +
                    "LEFT JOIN employee e ON r.author = e.matricule\n" +
                    "LEFT JOIN fonction f ON e.position = f.id\n" +
                    "LEFT JOIN caresheet c ON r.animal = c.animal\n" +
                    "WHERE c.date = r.date AND e.position = ?\n" +
                    "ORDER BY c.label, e.lastName;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, fonction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    RemarkByFonction search = new RemarkByFonction(data.getString("lastName"), data.getString("firstName"), data.getString("label"), data.getString("description"));
                    allData.add(search);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la recherche";
            throw new RemarkByFonctionsException(message);
        }
        return allData;
    }

    public ArrayList<TreatmentByMedicine> treatmentByMedicineResearch (String name) throws MedecineResearchException{
        ArrayList<TreatmentByMedicine> allData = new ArrayList<TreatmentByMedicine>();
        try {
            String sqlInstruction = "SELECT p.quantity, s.date, t.description\n" +
                    "FROM preparationsheet p\n" +
                    "LEFT JOIN medicine m ON p.preparation = m.name \n" +
                    "LEFT JOIN prescriptionsheet s ON p.detail = s.number \n" +
                    "LEFT JOIN treatment t ON s.prescription = t.idCare \n" +
                    "WHERE m.name = ? \n" +
                    "ORDER BY t.idCare, s.number;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, name);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    TreatmentByMedicine medicine = new TreatmentByMedicine(data.getString("description"),data.getDouble("quantity"), data.getDate("date") );
                    allData.add(medicine);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données pour la recherche demandée";
            throw new MedecineResearchException(message);
        }
        return allData;
    }

    public ArrayList<Medicine> listMedicine () throws ListMedicineException{
        ArrayList<Medicine> listOfMedicine = new ArrayList<Medicine>();
        try {
            String sqlInstruction = "SELECT * FROM medicine";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    Medicine medicine = new Medicine(data.getString("name"), data.getString("unitOfMesurement"), data.getString("instruction"));
                    listOfMedicine.add(medicine);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Médicament\"";
            throw new ListMedicineException(message);
        }
        return listOfMedicine;
    }

    public ArrayList<Animal> getAllAnimals () throws GetAllAnimalsException{
        ArrayList<Animal> listOfAnimals = new ArrayList<Animal>();
        try {
            String sqlInstruction = "SELECT * FROM animal";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                if (!data.wasNull()) {
                    String code = data.getString("code");
                    String name = data.getString("name");
                    String sexGet = data.getString("sex");
                    Gender sex = Gender.valueOf(sexGet);
                    Boolean isDangerous = data.getBoolean("isDangerous");
                    Double weight = data.getDouble("weight");
                    Date arrivalDate = data.getDate("arrivalDate");
                    String nickName = data.getString("nickname");
                    String breed = data.getString("breed");

                    Animal animal = new Animal(code,name,arrivalDate,sex,isDangerous,weight,breed,nickName);
                    listOfAnimals.add(animal);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Animal\"";
            throw new GetAllAnimalsException(message);
        }
        return listOfAnimals;
    }

    public void modifyAnimal(Animal animal) throws ModifyAnimalException{
        try {
            String sqlInstruction = "UPDATE animal \n" +
                    "SET name = ?, \n" +
                    "    sex = ?, \n" +
                    "    isDangerous = ?, \n" +
                    "    weight = ?, \n" +
                    "    arrivalDate = ?, \n" +
                    "    nickName = ?, \n" +
                    "    breed = ? \n" +
                    "WHERE code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(8, animal.getCode());
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getSex().getLabel());
            preparedStatement.setBoolean(3, animal.getDangerous());
            preparedStatement.setDouble(4, animal.getWeight());
            java.util.Date arrivalDate = animal.getArrivalDate();
            java.sql.Date sqlArrivalDate = new java.sql.Date(arrivalDate.getTime());
            preparedStatement.setDate(5, sqlArrivalDate);
            preparedStatement.setString(6, animal.getNickName());
            preparedStatement.setString(7, animal.getBreed());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = "Impossible de modifier cet animal";
            throw new ModifyAnimalException(message);
        }
    }

    public void deleteAnimal(String code) throws DeleteAnimalException {
        try {
            String sqlInstruction = "DELETE FROM animal\n" +
                    "WHERE code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = "Impossible de supprimer cet animal";
            throw new DeleteAnimalException(message);
        }
    }


    public ArrayList<Species> listSpecies() throws ListSpeciesException {
        ArrayList<Species> listOfSpecies = new ArrayList<Species>();
        try {
            String sqlInstruction = "SELECT s.label, s.id FROM species s;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    Species species = new Species(data.getString("id"), data.getString("label"));
                    listOfSpecies.add(species);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"species\"";
            throw new ListSpeciesException(message);
        }
        return listOfSpecies;
    }

    public ArrayList<Breed> listBreed () throws ListBreedException {
        ArrayList<Breed> listOfBreed = new ArrayList<Breed>();
        try {
            String sqlInstruction = "SELECT * FROM breed";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    Breed breed = new Breed(data.getString("id"), data.getString("label"), data.getString("specification"));
                    listOfBreed.add(breed);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Breed\"";
            throw new ListBreedException(message);
        }
        return listOfBreed;
    }

    public ArrayList<Fonction> listFonctions () throws ListFonctionsException{
        ArrayList<Fonction> listOfFonctions = new ArrayList<Fonction>();
        try {
            String sqlInstruction = "SELECT * FROM fonction";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    Fonction fonction = new Fonction(data.getString("id"), EnumRank.valueOf(data.getString("position")), data.getString("label"));
                    listOfFonctions.add(fonction);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Fonction\"";
            throw new ListFonctionsException(message);
        }
        return listOfFonctions;
    }

    public boolean animalExists(String code) throws AnimalExistsException{
        try {
            String sqlInstruction = "SELECT * FROM animal a WHERE a.code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, code);
            ResultSet data = preparedStatement.executeQuery();
            boolean hasData = data.next();
            return hasData;
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Animal\"";
            throw new AnimalExistsException(message);
        }
    }

    public void endConnection() throws EndConnectionException{
        try {
            connection.close();
        }
        catch (SQLException exception) {
            String message = "Impossible de fermer la connection";
            throw new EndConnectionException(message);
        }
    }

    public Breed getBreed(String species) throws GetBreedException{
        Breed breed = null;
        try {
            String sqlInstruction = "SELECT * FROM breed a WHERE a.specification = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, species);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                breed = new Breed(data.getString("id"), data.getString("label"), data.getString("specification"));
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Breed\"";
            throw new GetBreedException(message);
        }
        return breed;
    }

    public ArrayList<Animal> getAnimalsBySpecies(String codeAnimal) throws GetAnimalsException{
        ArrayList<Animal> listOfAnimals = new ArrayList<Animal>();
        try {
            String sqlInstruction = "SELECT a.code, a.name, a.sex, a.isDangerous, a.weight, a.arrivalDate, a.nickName, a.breed " +
                    "FROM animal a " +
                    "LEFT JOIN breed b ON a.breed = b.id " +
                    "LEFT JOIN species s ON b.specification = s.id " +
                    "WHERE s.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, codeAnimal);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                if (!data.wasNull()) {
                    String code = data.getString("code");
                    String name = data.getString("name");
                    String sexGet = data.getString("sex");
                    Gender sex = Gender.valueOf(sexGet);
                    Boolean isDangerous = data.getBoolean("isDangerous");
                    Double weight = data.getDouble("weight");
                    Date arrivalDate = data.getDate("arrivalDate");
                    String nickName = data.getString("nickname");
                    String breed = data.getString("breed");

                    Animal animal = new Animal(code,name,arrivalDate,sex,isDangerous,weight,breed,nickName);
                    listOfAnimals.add(animal);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Animals\"";
            throw new GetAnimalsException(message);
        }
        return listOfAnimals;
    }

    public ArrayList<PreparationSheet> getListPreparations(String code) throws ListPreparationsException{
        ArrayList<PreparationSheet> listOfPreparations = new ArrayList<PreparationSheet>();
        try {
            String sqlInstruction = "SELECT * " +
                    "FROM preparationsheet p " +
                    "LEFT JOIN animal a ON p.attachment = a.code " +
                    "WHERE p.creation IS NULL AND a.code = ? " +
                    "ORDER BY p.date DESC;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, code);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                if (!data.wasNull()) {
                    PreparationSheet preparation = new PreparationSheet(data.getInt("number"), data.getDate("date"), data.getDouble("quantity"), data.getString("posology"), data.getString("creation"),data.getString("attachment"), data.getInt("detail"), data.getString("preparation"));
                    listOfPreparations.add(preparation);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données pour les préparations";
            throw new ListPreparationsException(message);
        }
        return listOfPreparations;
    }

    public void modifyPreparationSheet(String employeId, int code) throws ModifyPreparationsheetException{
        try {
            String sqlInstruction = "UPDATE preparationsheet p SET `creation` = ? WHERE (p.number = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, employeId);
            preparedStatement.setInt(2, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = "Impossible de modifier cette fiche";
            throw new ModifyPreparationsheetException(message);
        }
    }

    public Species getSpecies(String code) throws GetSpeciesException{
        Species species = null;
        try {
            String sqlInstruction = "SELECT s.id, s.label " +
                    "FROM breed b " +
                    "LEFT JOIN species s ON b.specification = s.id " +
                    "WHERE b.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, code);
            ResultSet data = preparedStatement.executeQuery();
            if (data.next()) {
                species = new Species(data.getString("id"), data.getString("label"));
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Species\"";
            throw new GetSpeciesException(message);
        }
        return species;
    }

    public ArrayList<Employee> listEmployees() throws ListEmployeesException {
        ArrayList<Employee> listOfEmployees = new ArrayList<Employee>();
        try {
            String sqlInstruction = "SELECT * FROM employee;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    Employee employee = new Employee(data.getString("matricule"),
                            data.getString("lastName"),
                            data.getString("firstName"),
                            data.getString("supervisor"),
                            data.getString("position"));
                    listOfEmployees.add(employee);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Employee\"";
            throw new ListEmployeesException(message);
        }
        return listOfEmployees;
    }

}
