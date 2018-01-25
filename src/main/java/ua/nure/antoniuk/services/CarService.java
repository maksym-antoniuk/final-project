package ua.nure.antoniuk.services;

import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.entity.PotentialCar;

public class CarService {
    private TransactionManager transactionManager;
    private PotentialCarDAO potentialCarDAO;

    public CarService(TransactionManager transactionManager, PotentialCarDAO potentialCarDAO) {
        this.transactionManager = transactionManager;
        this.potentialCarDAO = potentialCarDAO;
    }

    public int create(PotentialCar car) {
        int id = transactionManager.execute(() -> potentialCarDAO.create(car));
        car.setId(id);
        return id;
    }

}
