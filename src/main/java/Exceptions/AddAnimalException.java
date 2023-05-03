package Exceptions;

import java.sql.SQLException;

public class AddAnimalException extends Exception{

    private String message;

    public AddAnimalException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de l'ajout dans la base de donnée :\n " + message;
    }


}
