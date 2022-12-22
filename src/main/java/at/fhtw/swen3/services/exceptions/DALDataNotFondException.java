package at.fhtw.swen3.services.exceptions;

public class DALDataNotFondException extends  DALException {
    public DALDataNotFondException(String errorMessage, String functionName, String className) {
        super(errorMessage, functionName, className);
    }

    @Override
    public String toString() {
        return this.getMessage() + " in class " + this.getClassName() + " in function" + this.getFunctionName();
    }
}
