package at.fhtw.swen3.services;

public class BLDataNotFoundException extends BLException {

    public BLDataNotFoundException(String errorMessage, String functionName, String className) {
        super(errorMessage, functionName, className);
    }
}
