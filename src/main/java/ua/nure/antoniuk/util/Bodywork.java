package ua.nure.antoniuk.util;

public enum Bodywork {
    TANK("tank"), BULK("bulk"), ANIMAL("animal"), CONTAINER("container"), CAR("car");

    private String bodywork;

    Bodywork(String bodywork) {
        this.bodywork = bodywork;
    }

    public String getBodywork() {
        return bodywork;
    }
}
