package ua.nure.antoniuk.util;

public enum Material {
    LIQUID("liquid"), BULK("bulk"), SOLID("solid"), ANIMAL("animal"), CAR("car");

    private String material;

    Material(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }
}
