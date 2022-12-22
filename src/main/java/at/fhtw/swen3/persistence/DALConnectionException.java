package at.fhtw.swen3.persistence;

public class DALConnectionException extends DALException {
    public DALConnectionException(String errorMessage, String functionName, String className) {
        super(errorMessage, functionName, className);
    }
}
