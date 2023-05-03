package Exceptions;

public class DataException extends Exception{
    private String message;

    public DataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur du a la base de données :\n   " + message;
    }
}
