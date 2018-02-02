package ua.nure.antoniuk.entity;

import ua.nure.antoniuk.util.Bodywork;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.StatusCar;
import ua.nure.antoniuk.util.StatusJourney;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Journey implements Serializable {

    private int id;
    private Calendar date;
    private Calendar dateFinish;
    private StatusJourney status;
    private float price;
    private float weight;
    private Material material;
    private float volume;
    private int idManager;
    private String from;
    private String where;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yy HH:mm").format(date.getTime());
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public StatusJourney getStatus() {
        return status;
    }

    public void setStatus(StatusJourney status) {
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
        return new SimpleDateFormat("dd-MM-yy HH:mm").format(dateFinish.getTime());
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


    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", date=" + getDate() +
                ", status=" + status.getStatus() +
                ", price=" + price +
                ", weight=" + weight +
                ", material=" + material.getMaterial() +
                ", volume=" + volume +
                ", idManager=" + idManager +
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
                Objects.equals(getDate(), journey.getDate()) &&
                Objects.equals(getDateFinish(), journey.getDateFinish()) &&
                getStatus() == journey.getStatus() &&
                getMaterial() == journey.getMaterial() &&
                Objects.equals(getFrom(), journey.getFrom()) &&
                Objects.equals(getWhere(), journey.getWhere());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getDateFinish(), getStatus(), getPrice(), getWeight(), getMaterial(), getVolume(), getIdManager(), getFrom(), getWhere());
    }

    public boolean isSuit(Car car) {
        if (weight > car.getMaxWeight()) {
            return false;
        }
        if (volume > car.getMaxVolume()) {
            return false;
        }
        if (material.equals(Material.ANIMAL) && !car.getBodywork().equals(Bodywork.ANIMAL)
                || material.equals(Material.BULK) && !car.getBodywork().equals(Bodywork.BULK)
                || material.equals(Material.CAR) && !car.getBodywork().equals(Bodywork.CAR)
                || material.equals(Material.LIQUID) && !car.getBodywork().equals(Bodywork.TANK)
                || material.equals(Material.SOLID) && !car.getBodywork().equals(Bodywork.CONTAINER)) {
            return false;
        }
        if (car.getStatus().equals(StatusCar.BROKEN)) {
            return false;
        }
        return true;
    }
}
