package entity;

import util.Bodywork;

import java.io.Serializable;
import java.util.Objects;

public class PotentialCar implements Serializable {

    private int id;
    private String mark;
    private String number;
    private String pathImg;
    private float maxWeight;
    private float maxVolume;
    private int idUser;
    private int idPotentialUser;
    private Bodywork bodywork;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPotentialUser() {
        return idPotentialUser;
    }

    public void setIdPotentialUser(int idPotentialUser) {
        this.idPotentialUser = idPotentialUser;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }

    public void setBodywork(Bodywork bodywork) {
        this.bodywork = bodywork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotentialCar that = (PotentialCar) o;
        return getId() == that.getId() &&
                Float.compare(that.getMaxWeight(), getMaxWeight()) == 0 &&
                Float.compare(that.getMaxVolume(), getMaxVolume()) == 0 &&
                getIdUser() == that.getIdUser() &&
                getIdPotentialUser() == that.getIdPotentialUser() &&
                Objects.equals(getMark(), that.getMark()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getPathImg(), that.getPathImg()) &&
                getBodywork() == that.getBodywork();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getMark(), getNumber(), getPathImg(), getMaxWeight(), getMaxVolume(), getIdUser(), getIdPotentialUser(), getBodywork());
    }

    @Override
    public String toString() {
        return "PotentialCar{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", number='" + number + '\'' +
                ", pathImg='" + pathImg + '\'' +
                ", maxWeight=" + maxWeight +
                ", maxVolume=" + maxVolume +
                ", idUser=" + idUser +
                ", idPotentialUser=" + idPotentialUser +
                ", bodywork=" + bodywork.getBodywork() +
                '}';
    }
}
