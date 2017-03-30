package com.github.ledoyen.enjine.metamodel.validation;


public class SimpleViolation implements Violation {
    private final String _reason;

    public SimpleViolation(String reason) {
        this._reason = reason;
    }

    @Override
    public String getReason() {
        return _reason;
    }

    @Override
    public int hashCode() {
        return getReason().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SimpleViolation && ((SimpleViolation) obj).getReason().equals(getReason());
    }

    @Override
    public String toString() {
        return getReason();
    }
}
