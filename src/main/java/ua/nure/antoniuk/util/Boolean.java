package ua.nure.antoniuk.util;

public enum Boolean {
    YES("yes"), NO("no");

    private String bool;

    Boolean(String bool) {
        this.bool = bool;
    }

    public String getBool() {
        return bool;
    }
}
