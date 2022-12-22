package at.fhtw.swen3.services.exceptions;

public class BLDataNotFoundException extends BLException {

    public BLDataNotFoundException(String errorMessage, String functionName, String className) {
        super(errorMessage, functionName, className);
    }
}
