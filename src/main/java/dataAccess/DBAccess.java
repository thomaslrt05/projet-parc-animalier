package dataAccess;
import model.*;
import Exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import model.Species;

public class DBAccess implements DaoAccess{
    private Connection connection;
    public DBAccess()  {
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
                    "FROM library.remark r\n" +
                    "LEFT JOIN library.employee e ON r.author = e.matricule\n" +
                    "LEFT JOIN library.fonction f ON e.position = f.id\n" +
                    "LEFT JOIN library.caresheet c ON r.animal = c.animal\n" +
                    "WHERE c.date = r.date AND e.position = ? ORDER BY c.label, e.lastName";
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
                    // TODO
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
            return !data.wasNull();
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la table \"Fonction\"";
            throw new AnimalExistsException(message);
        }
    }


}
