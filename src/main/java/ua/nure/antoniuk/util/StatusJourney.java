package ua.nure.antoniuk.util;

public enum StatusJourney {
    NEW("new"), ON_PROCESS("on_process"), OLD("old"), CANCELED("canceled");

    private String status;

    StatusJourney(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
