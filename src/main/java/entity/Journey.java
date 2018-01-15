package entity;

import util.Material;
import util.Status;

import java.io.Serializable;
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
    private int id_manager;
    private int id_driver;
    private String from;
    private String where;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
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

    public int getId_manager() {
        return id_manager;
    }

    public void setId_manager(int id_manager) {
        this.id_manager = id_manager;
    }

    public Calendar getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Calendar dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", date=" + date +
                ", dateFinish=" + dateFinish +
                ", status=" + status +
                ", price=" + price +
                ", weight=" + weight +
                ", material=" + material +
                ", volume=" + volume +
                ", id_manager=" + id_manager +
                ", id_driver=" + id_driver +
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
                getId_manager() == journey.getId_manager() &&
                getId_driver() == journey.getId_driver() &&
                Objects.equals(getDate(), journey.getDate()) &&
                Objects.equals(getDateFinish(), journey.getDateFinish()) &&
                getStatus() == journey.getStatus() &&
                getMaterial() == journey.getMaterial();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDate(), getDateFinish(), getStatus(), getPrice(), getWeight(), getMaterial(), getVolume(), getId_manager(), getId_driver());
    }
}
