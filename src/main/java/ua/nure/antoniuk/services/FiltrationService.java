package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.builder.FilterJourney;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.StatusJourney;
import ua.nure.antoniuk.util.Util;

import java.util.Arrays;
import java.util.Objects;

public class FiltrationService {
    private static final Logger LOGGER = Logger.getLogger(FiltrationService.class);
    public static final String ID = "id";
    public static final String DATE_START = "date";
    public static final String DATE_FINISH = "dateFinish";
    public static final String STATUS_NEW = "new";
    public static final String STATUS_OLD = "old";
    public static final String STATUS_ON_PROCESS = "perform";
    public static final String STATUS_CANCELED = "canceled";
    public static final String PRICE = "price";
    public static final String CAPACITY = "capacity";
    public static final String VOLUME = "volume";
    public static final String MATERIAL_BULK = "bulk";
    public static final String MATERIAL_LIQUID = "liquid";
    public static final String MATERIAL_SOLID = "solid";
    public static final String MATERIAL_CAR = "car";
    public static final String MATERIAL_ANIMAL = "animal";
    public static final String USER = "user";

    public void setParameter(String parameter, FilterJourney filterJourney) {
        LOGGER.trace(parameter);
        switch (parameter) {
            case ID:{
                if (Objects.isNull(filterJourney.getAscId())) {
                    filterJourney.setAscId(true);
                } else {
                    filterJourney.setAscId(!filterJourney.getAscId());
                }
                filterJourney.setAscDateFinish(null);
                filterJourney.setAscDateStart(null);
                filterJourney.setAscPrice(null);
                filterJourney.setAscCapacity(null);
                filterJourney.setAscVolume(null);
                break;
            }
            case DATE_START:{
                if (Objects.isNull(filterJourney.getAscDateStart())) {
                    filterJourney.setAscDateStart(true);
                } else {
                    filterJourney.setAscDateStart(!filterJourney.getAscDateStart());
                }
                filterJourney.setAscId(null);
                filterJourney.setAscDateFinish(null);
                filterJourney.setAscPrice(null);
                filterJourney.setAscCapacity(null);
                filterJourney.setAscVolume(null);
                break;
            }
            case DATE_FINISH:{
                if (Objects.isNull(filterJourney.getAscDateFinish())) {
                    filterJourney.setAscDateFinish(true);
                } else {
                    filterJourney.setAscDateFinish(!filterJourney.getAscDateFinish());
                }
                filterJourney.setAscId(null);
                filterJourney.setAscDateStart(null);
                filterJourney.setAscPrice(null);
                filterJourney.setAscCapacity(null);
                filterJourney.setAscVolume(null);
                filterJourney.setStatusJourneys(Arrays.asList(StatusJourney.OLD));
                break;
            }
            case STATUS_NEW:{
                if (filterJourney.getStatusJourneys().contains(StatusJourney.NEW)) {
                    filterJourney.getStatusJourneys().remove(StatusJourney.NEW);
                } else {
                    filterJourney.getStatusJourneys().add(StatusJourney.NEW);
                }
                break;
            }
            case STATUS_ON_PROCESS:{
                if (filterJourney.getStatusJourneys().contains(StatusJourney.ON_PROCESS)) {
                    filterJourney.getStatusJourneys().remove(StatusJourney.ON_PROCESS);
                } else {
                    filterJourney.getStatusJourneys().add(StatusJourney.ON_PROCESS);
                }
                break;
            }
            case STATUS_OLD:{
                if (filterJourney.getStatusJourneys().contains(StatusJourney.OLD)) {
                    filterJourney.getStatusJourneys().remove(StatusJourney.OLD);
                } else {
                    filterJourney.getStatusJourneys().add(StatusJourney.OLD);
                }
                break;
            }
            case STATUS_CANCELED:{
                if (filterJourney.getStatusJourneys().contains(StatusJourney.CANCELED)) {
                    filterJourney.getStatusJourneys().remove(StatusJourney.CANCELED);
                } else {
                    filterJourney.getStatusJourneys().add(StatusJourney.CANCELED);
                }
                break;
            }
            case PRICE:{
                if (Objects.isNull(filterJourney.getAscPrice())) {
                    filterJourney.setAscPrice(true);
                } else {
                    filterJourney.setAscPrice(!filterJourney.getAscPrice());
                }
                filterJourney.setAscDateFinish(null);
                filterJourney.setAscDateStart(null);
                filterJourney.setAscId(null);
                filterJourney.setAscCapacity(null);
                filterJourney.setAscVolume(null);
                break;
            }
            case CAPACITY:{
                if (Objects.isNull(filterJourney.getAscCapacity())) {
                    filterJourney.setAscCapacity(true);
                } else {
                    filterJourney.setAscCapacity(!filterJourney.getAscCapacity());
                }
                filterJourney.setAscDateFinish(null);
                filterJourney.setAscDateStart(null);
                filterJourney.setAscId(null);
                filterJourney.setAscPrice(null);
                filterJourney.setAscVolume(null);
                break;
            }
            case VOLUME:{
                if (Objects.isNull(filterJourney.getAscVolume())) {
                    filterJourney.setAscVolume(true);
                } else {
                    filterJourney.setAscVolume(!filterJourney.getAscVolume());
                }
                filterJourney.setAscDateFinish(null);
                filterJourney.setAscDateStart(null);
                filterJourney.setAscId(null);
                filterJourney.setAscCapacity(null);
                filterJourney.setAscPrice(null);
                break;
            }
            case MATERIAL_ANIMAL:{
                if (filterJourney.getMaterials().contains(Material.ANIMAL)) {
                    filterJourney.getMaterials().remove(Material.ANIMAL);
                } else {
                    filterJourney.getMaterials().add(Material.ANIMAL);
                }
                break;
            }
            case MATERIAL_BULK:{
                if (filterJourney.getMaterials().contains(Material.BULK)) {
                    filterJourney.getMaterials().remove(Material.BULK);
                } else {
                    filterJourney.getMaterials().add(Material.BULK);
                }
                break;
            }
            case MATERIAL_SOLID:{
                if (filterJourney.getMaterials().contains(Material.SOLID)) {
                    filterJourney.getMaterials().remove(Material.SOLID);
                } else {
                    filterJourney.getMaterials().add(Material.SOLID);
                }
                break;
            }
            case MATERIAL_LIQUID:{
                if (filterJourney.getMaterials().contains(Material.LIQUID)) {
                    filterJourney.getMaterials().remove(Material.LIQUID);
                } else {
                    filterJourney.getMaterials().add(Material.LIQUID);
                }
                break;
            }
            case MATERIAL_CAR:{
                if (filterJourney.getMaterials().contains(Material.CAR)) {
                    filterJourney.getMaterials().remove(Material.CAR);
                } else {
                    filterJourney.getMaterials().add(Material.CAR);
                }
                break;
            }
            case USER:{
                filterJourney.setOnlyMy(!filterJourney.isOnlyMy());
                break;
            }
        }
        if (Util.isMatch("page\\d+", parameter)) {
            filterJourney.setPage(Integer.parseInt(parameter.replace("page", "")));
            return;
        }
        if (Util.isMatch("count\\d+", parameter)) {
            int count = Integer.parseInt(parameter.replace("count", ""));
            LOGGER.trace(count);
            if(count > 0 && count < 1000){
                filterJourney.setCountRows(count);
            }
        }
        filterJourney.setPage(1);
    }
}
