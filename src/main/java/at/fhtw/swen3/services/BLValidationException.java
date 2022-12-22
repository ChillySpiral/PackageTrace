package at.fhtw.swen3.services;

public class BLValidationException extends BLException {
    public final String violations;

    public BLValidationException(String errorMessage, String functionName, String className, String violations) {
        super(errorMessage, functionName, className);
        this.violations = violations;
    }
}
