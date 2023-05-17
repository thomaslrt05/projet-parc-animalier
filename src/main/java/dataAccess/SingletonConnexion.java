package dataAccess;
import Exceptions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection uniqueInstance;

    private SingletonConnexion(){}

    public static Connection getInstance() throws SingletonConnexionException{
        if(uniqueInstance == null){
            try {
                uniqueInstance = DriverManager.getConnection("jdbc:mysql://localhost:3306/parc-animalier", "root", "rootroot2023.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                String message =  "Erreur lors de l'ouverture de la connection";
                throw new SingletonConnexionException(message);
            }
        }
        return uniqueInstance;
    }
}

