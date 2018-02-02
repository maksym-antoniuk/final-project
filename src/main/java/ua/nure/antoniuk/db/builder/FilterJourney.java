package ua.nure.antoniuk.db.builder;

import java.lang.String;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.StatusJourney;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterJourney extends Filter {
    private Boolean ascId;
    private Boolean ascDateStart;
    private Boolean ascDateFinish;
    private List<StatusJourney> statusJourneys;
    private Boolean ascPrice;
    private List<Material> materials;
    private Integer idUser;

    public FilterJourney() { }

    public FilterJourney setDefaultAdmin() {
        setAscDateStart(false);
        setStatusJourneys(new ArrayList<>(Arrays.asList(StatusJourney.NEW, StatusJourney.ON_PROCESS, StatusJourney.OLD, StatusJourney.CANCELED)));
        setMaterials(new ArrayList<>(Arrays.asList(Material.BULK, Material.ANIMAL, Material.CAR, Material.LIQUID, Material.SOLID)));
        return this;
    }

    public Boolean getAscId() {
        return ascId;
    }

    public void setAscId(Boolean ascId) {
        this.ascId = ascId;
    }

    public Boolean getAscDateStart() {
        return ascDateStart;
    }

    public void setAscDateStart(Boolean ascDateStart) {
        this.ascDateStart = ascDateStart;
    }

    public Boolean getAscDateFinish() {
        return ascDateFinish;
    }

    public void setAscDateFinish(Boolean ascDateFinish) {
        this.ascDateFinish = ascDateFinish;
    }

    public List<StatusJourney> getStatusJourneys() {
        return statusJourneys;
    }

    public void setStatusJourneys(List<StatusJourney> statusJourneys) {
        this.statusJourneys = statusJourneys;
    }

    public Boolean getAscPrice() {
        return ascPrice;
    }

    public void setAscPrice(Boolean ascPrice) {
        this.ascPrice = ascPrice;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    private String where(){
        StringBuilder s = new StringBuilder("");
        if(!getStatusJourneys().isEmpty() || !getStatusJourneys().isEmpty()){

        }
        return s.toString();
    }

    public String getQuery(){
        return "SELECT j.id, j.date, j.date_finish, j.`from`, j.id_manager, j.price, j.status, j.type, j.volume, j.weight, j.`where`, count(jc.cars_id), if(jc.accept = 'yes',c.id_driver, 0) FROM journeys j LEFT OUTER JOIN journeys_has_cars jc ON j.id = journeys_id LEFT OUTER JOIN cars c ON cars_id = c.id " +

                " GROUP BY j.id;";
    }
}
