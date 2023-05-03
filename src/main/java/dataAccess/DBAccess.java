package dataAccess;
import model.*;
import Exceptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccess implements DaoAccess{
    private Connection connection;
    public DBAccess()  {
        this.connection = SingletonConnexion.getInstance();
    }

    public void closeConnection() throws DataException {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException exception) {
            throw new DataException(exception.getMessage());
        }
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
            // TODO oui
            String message = "Erreur lors de l'ajout de l'animal";
            throw new AddAnimalException(e.getMessage());
        }
    }

}
