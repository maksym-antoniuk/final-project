package ua.nure.antoniuk.db.dao;

import ua.nure.antoniuk.entity.Car;

import java.util.List;

public interface CarDAO extends CRUD<Car> {

    List<Car> getAllCars();

    List<Car> getCarsByIdDriver(int idDriver);

    List<Car> getCarsByIdJourney(int idJourney);

    boolean isRegisteredOnJourney(int journeyid, int carid);

    boolean isOnRoad(int carid);

    boolean isRegisteredOnJourney(int carid);
}
