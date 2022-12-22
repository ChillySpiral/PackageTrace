package at.fhtw.swen3.persistence;

public class DALDataNotFondException extends  DALException {
    public DALDataNotFondException(String errorMessage, String functionName, String className) {
        super(errorMessage, functionName, className);
    }

    @Override
    public String toString() {
        return this.getMessage() + " in class " + this.getClassName() + " in function" + this.getFunctionName();
    }
}
