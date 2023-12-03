package pl.kj.m33t.m33tbackend.exception;

//TODO: add exception handler based on the aspect, psb:
// https://www.baeldung.com/exception-handling-for-rest-with-spring#controlleradvice

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
