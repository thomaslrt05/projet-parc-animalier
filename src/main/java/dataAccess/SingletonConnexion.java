package dataAccess;
import Exceptions.DataException;
import model.*;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection uniqueInstance;

    private SingletonConnexion(){}

    public static Connection getInstance() {
        if(uniqueInstance == null){
            try {
                uniqueInstance = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "Amélie", "1235");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return uniqueInstance;
    }
}

