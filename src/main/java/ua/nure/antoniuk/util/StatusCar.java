package ua.nure.antoniuk.util;

public enum StatusCar {
    OK("ok"), BROKEN("broken");

    private String status;

    StatusCar(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
