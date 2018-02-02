package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.Journey;

public class JourneyDisplayDTO {
    private Journey journey;
    private int countCar;
    private int idDriver;

    public Journey getJourney() {
        return journey;
    }

    public JourneyDisplayDTO setJourney(Journey journey) {
        this.journey = journey;
        return this;
    }

    public int getCountCar() {
        return countCar;
    }

    public JourneyDisplayDTO setCountCar(int countCar) {
        this.countCar = countCar;
        return this;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public JourneyDisplayDTO setIdDriver(int idDriver) {
        this.idDriver = idDriver;
        return this;
    }

    @Override
    public String toString() {
        return "JourneyDisplayDTO{" +
                "journey=" + journey.toString() +
                ", countCar=" + countCar +
                ", idDriver=" + idDriver +
                '}';
    }
}
