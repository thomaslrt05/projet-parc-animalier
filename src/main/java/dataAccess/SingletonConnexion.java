package dataAccess;
import Exceptions.DataException;
import model.*;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection connection;

    private SingletonConnexion() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parc-animalier",
                "root",
                "rootroot2023.");
    }

    public static Connection getInstance() throws DataException {
        if(connection == null){
            try {
                new SingletonConnexion();
            } catch (SQLException e) {
                throw new DataException(e.getMessage());
            }
        }
        return connection;
    }
}

