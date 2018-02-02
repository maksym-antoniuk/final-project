package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.Journey;
import ua.nure.antoniuk.util.Mapping;
import ua.nure.antoniuk.util.Material;

import javax.servlet.http.HttpServletRequest;

public class JourneyDTO {
    private String from;
    private String where;
    private String weight;
    private String volume;
    private String material;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public JourneyDTO setFromRequest(HttpServletRequest request) {
        setFrom(request.getParameter("from"));
        setWhere(request.getParameter("where"));
        setWeight(request.getParameter("weight"));
        setVolume(request.getParameter("volume"));
        setMaterial(request.getParameter("material"));
        setPrice(request.getParameter("price"));
        return this;
    }

    public Journey extract() {
        Journey journey = new Journey();
        journey.setFrom(from);
        journey.setWhere(where);
        journey.setWeight(Float.parseFloat(weight));
        journey.setVolume(Float.parseFloat(volume));
        journey.setMaterial(Material.valueOf(material.toUpperCase()));
        journey.setPrice(Float.parseFloat(price));
        return journey;
    }
}
