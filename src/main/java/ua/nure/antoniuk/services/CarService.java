package ua.nure.antoniuk.services;

import ua.nure.antoniuk.db.dao.CarDAO;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.PotentialCar;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private TransactionManager transactionManager;
    private PotentialCarDAO potentialCarDAO;
    private CarDAO carDAO;

    public CarService(TransactionManager transactionManager, PotentialCarDAO potentialCarDAO, CarDAO carDAO) {
        this.carDAO = carDAO;
        this.transactionManager = transactionManager;
        this.potentialCarDAO = potentialCarDAO;
    }

    public int create(PotentialCar car) {
        int id = transactionManager.execute(() -> potentialCarDAO.create(car));
        car.setId(id);
        return id;
    }

    public PotentialCar getPotentialCarByIdDriver(int idDriver) {
        return transactionManager.executeWithoutTransaction(() -> potentialCarDAO.getByIdDriver(idDriver).get());
    }

    public Car potentialCarToCar(PotentialCar potentialCar) {
        Car car = new Car();
        car.setIdDriver(potentialCar.getIdPotentialUser() + potentialCar.getIdUser());
        car.setBodywork(potentialCar.getBodywork());
        car.setMark(potentialCar.getMark());
        car.setModel(potentialCar.getModel());
        car.setNumber(potentialCar.getNumber());
        car.setMaxVolume(potentialCar.getMaxVolume());
        car.setMaxWeight(potentialCar.getMaxWeight());
        car.setPathImg(potentialCar.getPathImg());
        return car;
    }

    public List<Car> getAllCars() {
        return transactionManager.executeWithoutTransaction(() -> carDAO.getAllCars());
    }

    public List<Car> getDriversCars(int id) {
        return transactionManager.executeWithoutTransaction(() -> carDAO.getCarsByIdDriver(id));
    }

    public List<Car> getJourneyCars(int journey) {
        return transactionManager.executeWithoutTransaction(() -> carDAO.getCarsByIdJourney(journey));
    }
}
