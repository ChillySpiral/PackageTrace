package at.fhtw.swen3.services.exceptions;

import lombok.Getter;

@Getter
public class DALException extends Exception {

    public final String functionName;
    public final String className;
    public DALException(String errorMessage, String functionName, String className) {
        super(errorMessage);
        this.functionName = functionName;
        this.className = className;
    }
}
