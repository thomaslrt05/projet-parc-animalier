package Exceptions;

import java.sql.SQLException;

public class AddAnimalException extends Exception{
    public AddAnimalException(String message) {
        super(message);
    }
}
