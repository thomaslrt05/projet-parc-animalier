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
            String sqlInstruction = "SELECT c.label \"careSheet\", a.name, a.code \"codeAnimal\" , b.label \"breedLabel\", c.date FROM caresheet c LEFT JOIN animal a ON c.animal = a.code LEFT JOIN breed b ON a.breed = b.id LEFT JOIN species s ON b.specification = s.id WHERE s.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, species);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                if (!data.wasNull()) {
                    CareSheetResearch search = new CareSheetResearch(data.getString("caresheet"), data.getString("name"), data.getString("codeAnimal"), data.getString("breedLabel"), data.getDate("date"));
                    allData.add(search);
                }
            }
        } catch (SQLException e) {
            String message = "Impossible de récuperer les données de la recherche";
            throw new CareSheetResearchException(message);
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
