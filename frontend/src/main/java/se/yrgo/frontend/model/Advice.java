package se.yrgo.frontend.model;

public class Advice {
    private String message;
    private boolean actionRequired;

    public Advice() {
    }

    public Advice(String message, boolean actionRequired) {
        this.message = message;
        this.actionRequired = actionRequired;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isActionRequired() {
        return actionRequired;
    }

    public void setActionRequired(boolean actionRequired) {
        this.actionRequired = actionRequired;
    }
}