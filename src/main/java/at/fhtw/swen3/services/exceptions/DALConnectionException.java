package at.fhtw.swen3.services.exceptions;

public class DALConnectionException extends DALException {
    public DALConnectionException(String errorMessage, String functionName, String className) {
        super(errorMessage, functionName, className);
    }
}
