package exception;

public class EntityNotExistException extends RuntimeException { //EXCEPTION

    public EntityNotExistException(String message) {
        super(message);
    }
}
