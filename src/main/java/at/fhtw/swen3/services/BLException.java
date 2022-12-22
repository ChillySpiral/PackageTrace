package at.fhtw.swen3.services;

import lombok.Getter;

@Getter
public class BLException extends Exception {
    public final String functionName;
    public final String className;
    public BLException(String errorMessage, String functionName, String className) {
        super(errorMessage);
        this.functionName = functionName;
        this.className = className;
    }

    @Override
    public String toString() {
        return this.getMessage() + " in class " + this.getClassName() + " in function" + this.getFunctionName();
    }
}
