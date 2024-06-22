package webCalendarSpring.api.exception;

public class EventDoesntExistException extends RuntimeException {

    public EventDoesntExistException(String message) {
        super(message);
    }

}
