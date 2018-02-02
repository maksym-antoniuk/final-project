package ua.nure.antoniuk.entity;

import ua.nure.antoniuk.util.Bodywork;
import ua.nure.antoniuk.util.StatusCar;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private int id;
    private String number;
    private String mark;
    private String model;
    private String pathImg;
    private Bodywork bodywork;
    private float maxWeight;
    private float maxVolume;
    private int idDriver;
    private StatusCar status;

    public StatusCar getStatus() {
        return status;
    }

    public void setStatus(StatusCar status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }

    public void setBodywork(Bodywork bodywork) {
        this.bodywork = bodywork;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public float getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(float maxVolume) {
        this.maxVolume = maxVolume;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getId() == car.getId() &&
                Float.compare(car.getMaxWeight(), getMaxWeight()) == 0 &&
                Float.compare(car.getMaxVolume(), getMaxVolume()) == 0 &&
                getIdDriver() == car.getIdDriver() &&
                Objects.equals(getNumber(), car.getNumber()) &&
                Objects.equals(getMark(), car.getMark()) &&
                Objects.equals(getModel(), car.getModel()) &&
                Objects.equals(getPathImg(), car.getPathImg()) &&
                getBodywork() == car.getBodywork() &&
                getStatus() == car.getStatus();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getNumber(), getMark(), getModel(), getPathImg(), getBodywork(), getMaxWeight(), getMaxVolume(), getIdDriver(), getStatus());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", pathImg='" + pathImg + '\'' +
                ", bodywork=" + bodywork.getBodywork() +
                ", maxWeight=" + maxWeight +
                ", maxVolume=" + maxVolume +
                ", idDriver=" + idDriver +
                ", status=" + status.getStatus() +
                '}';
    }
}
