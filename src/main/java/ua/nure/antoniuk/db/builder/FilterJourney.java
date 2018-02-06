package ua.nure.antoniuk.db.builder;

import java.lang.String;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.Role;
import ua.nure.antoniuk.util.StatusJourney;

import java.util.*;

public class FilterJourney extends Filter {
    private Boolean ascId;
    private Boolean ascDateStart;
    private Boolean ascDateFinish;
    private List<StatusJourney> statusJourneys;
    private Boolean ascPrice;
    private Boolean ascCapacity;
    private Boolean ascVolume;
    private List<Material> materials;
    private Integer idUser;
    private Integer page;
    private Role role;
    private boolean onlyMy;
    private int countPages;
    private int countRows;

    public FilterJourney(){page = 1;}

    public FilterJourney(Role role, int idUser) {
        page = 1;
        countRows = 10;
        this.idUser = idUser;
        this.role = role;
        if (Objects.equals(role, Role.ADMIN)) {
            setDefaultAdmin();
        }
        if (Objects.equals(role, Role.DRIVER)) {
            setDefaultDriver();
        }
        if (Objects.equals(role, Role.MANAGER)) {
            setDefaultManager();
        }
    }

    public FilterJourney setDefaultAdmin() {
        setAscDateStart(false);
        setStatusJourneys(new ArrayList<>(Arrays.asList(StatusJourney.NEW, StatusJourney.ON_PROCESS, StatusJourney.OLD, StatusJourney.CANCELED)));
        setMaterials(new ArrayList<>(Arrays.asList(Material.BULK, Material.ANIMAL, Material.CAR, Material.LIQUID, Material.SOLID)));
        return this;
    }

    private FilterJourney setDefaultDriver() {
        setAscDateStart(false);
        setStatusJourneys(new ArrayList<>(Arrays.asList(StatusJourney.NEW)));
        setMaterials(new ArrayList<>(Arrays.asList(Material.BULK, Material.ANIMAL, Material.CAR, Material.LIQUID, Material.SOLID)));
        return this;
    }

    private FilterJourney setDefaultManager() {
        setAscDateStart(false);
        setOnlyMy(true);
        setStatusJourneys(new ArrayList<>(Arrays.asList(StatusJourney.NEW, StatusJourney.ON_PROCESS, StatusJourney.OLD, StatusJourney.CANCELED)));
        setMaterials(new ArrayList<>(Arrays.asList(Material.BULK, Material.ANIMAL, Material.CAR, Material.LIQUID, Material.SOLID)));
        return this;
    }

    public int getCountRows() {
        return countRows;
    }

    public void setCountRows(int countRows) {
        this.countRows = countRows;
    }

    public int getCountPages() {
        return countPages;
    }

    public void setCountPages(int countPages) {
        this.countPages = countPages;
    }

    public Boolean getAscCapacity() {
        return ascCapacity;
    }

    public void setAscCapacity(Boolean ascCapacity) {
        this.ascCapacity = ascCapacity;
    }

    public Boolean getAscVolume() {
        return ascVolume;
    }

    public void setAscVolume(Boolean ascVolume) {
        this.ascVolume = ascVolume;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public boolean isOnlyMy() {
        return onlyMy;
    }

    public void setOnlyMy(boolean onlyMy) {
        this.onlyMy = onlyMy;
    }

    private String count() {
        return "SELECT count(*) from (SELECT count(*) FROM journeys j LEFT OUTER JOIN journeys_has_cars jc ON j.id = journeys_id LEFT OUTER JOIN cars c ON cars_id = c.id";
    }

    private String fields() {
        return "SELECT j.*, count(jc.cars_id), if(j.id IN (select id from journeys INNER JOIN journeys_has_cars on id=journeys_id where accept='yes'), c.id_driver, 0) FROM journeys j LEFT OUTER JOIN journeys_has_cars jc ON j.id = journeys_id LEFT OUTER JOIN cars c ON cars_id = c.id";
    }

    private String where(){
        StringBuilder s = new StringBuilder("");
        if(!getStatusJourneys().isEmpty() || !getStatusJourneys().isEmpty() || !Objects.isNull(idUser)){
            s.append(" WHERE ");
            if (!getStatusJourneys().isEmpty()) {
                s.append("j.status IN (");
                for (StatusJourney statusJourney : getStatusJourneys()) {
                    s.append("\'").append(statusJourney.getStatus()).append("\',");
                }
                s.replace(s.length() - 1, s.length(), "");
                s.append(")");
            }
            if (!getMaterials().isEmpty() && !getStatusJourneys().isEmpty()) {
                s.append(" AND ");
            }
            if (!getMaterials().isEmpty()) {
                s.append("j.type IN (");
                for (Material material : getMaterials()) {
                    s.append("\'").append(material.getMaterial()).append("\',");
                }
                s.replace(s.length() - 1, s.length(), "");
                s.append(") ");
            }
            if ((!getMaterials().isEmpty() || !getStatusJourneys().isEmpty()) && onlyMy) {
                s.append(" AND ");
                if (Objects.equals(Role.DRIVER, role)) {
                    s.append("c.id_driver = ").append(getIdUser());
                } else {
                    s.append("j.id_manager = ").append(getIdUser());
                }
            }
        }
        return s.toString();
    }

    private String orderBy(){
        StringBuilder sb = new StringBuilder(" ORDER BY ");
        if (!Objects.isNull(ascId)) {
            sb.append("j.id").append(ascId ? " ASC " : " DESC ");
        }
        if (!Objects.isNull(ascDateStart)) {
            sb.append("j.date").append(ascDateStart ? " ASC " : " DESC ");
        }
        if (!Objects.isNull(ascDateFinish)) {
            sb.append("j.date_finish").append(ascDateFinish ? " ASC " : " DESC ");
        }
        if (!Objects.isNull(ascPrice)) {
            sb.append("j.price").append(ascPrice ? " ASC " : " DESC ");
        }
        if (!Objects.isNull(ascCapacity)) {
            sb.append("j.weight").append(ascCapacity ? " ASC " : " DESC ");
        }
        if (!Objects.isNull(ascVolume)) {
            sb.append("j.volume").append(ascVolume ? " ASC " : " DESC ");
        }
        return sb.toString();
    }

    private String limit() {
        return " LIMIT " + (page * countRows - countRows) + "," + countRows + ";";
    }

    private String groupBy() {
        return " GROUP BY j.id ";
    }

    @Override
    public String toSQLQuery(){
        return fields() + where() + groupBy() + orderBy() + limit();
    }

    @Override
    public String toSQLQueryCount() {
        return count() + where() + groupBy() + orderBy() + ") as s;";
    }
}
