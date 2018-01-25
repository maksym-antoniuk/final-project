package ua.nure.antoniuk.entity;

import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.Status;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Journey implements Serializable {

    private int id;
    private Calendar date;
    private Calendar dateFinish;
    private Status status;
    private float price;
    private float weight;
    private Material material;
    private float volume;
    private int idManager;
    private int idCar;
    private String from;
    private String where;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date.getTime());
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public String getDateFinish() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(dateFinish.getTime());
    }

    public void setDateFinish(Calendar dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", date=" + date +
                ", dateFinish=" + dateFinish +
                ", status=" + status.getStatus() +
                ", price=" + price +
                ", weight=" + weight +
                ", material=" + material.getMaterial() +
                ", volume=" + volume +
                ", idManager=" + idManager +
                ", idCar=" + idCar +
                ", from='" + from + '\'' +
                ", where='" + where + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return getId() == journey.getId() &&
                Float.compare(journey.getPrice(), getPrice()) == 0 &&
                Float.compare(journey.getWeight(), getWeight()) == 0 &&
                Float.compare(journey.getVolume(), getVolume()) == 0 &&
                getIdManager() == journey.getIdManager() &&
                getIdCar() == journey.getIdCar() &&
                Objects.equals(getDate(), journey.getDate()) &&
                Objects.equals(getDateFinish(), journey.getDateFinish()) &&
                getStatus() == journey.getStatus() &&
                getMaterial() == journey.getMaterial() &&
                Objects.equals(getFrom(), journey.getFrom()) &&
                Objects.equals(getWhere(), journey.getWhere());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getDateFinish(), getStatus(), getPrice(), getWeight(), getMaterial(), getVolume(), getIdManager(), getIdCar(), getFrom(), getWhere());
    }
}
