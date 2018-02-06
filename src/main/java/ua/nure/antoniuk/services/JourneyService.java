package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.builder.Filter;
import ua.nure.antoniuk.db.builder.FilterJourney;
import ua.nure.antoniuk.db.dao.CarDAO;
import ua.nure.antoniuk.db.dao.JourneyDAO;
import ua.nure.antoniuk.db.dao.UserDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.dto.JourneyDisplayDTO;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.Journey;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.StatusJourney;

import java.util.List;
import java.util.Objects;

public class JourneyService {
    private static final Logger LOGGER = Logger.getLogger(JourneyService.class);

    private JourneyDAO journeyDAO;
    private CarDAO carDAO;
    private UserDAO userDAO;
    private UserService userSevice;
    TransactionManager transactionManager;


    public JourneyService(JourneyDAO journeyDAO, TransactionManager transactionManager, CarDAO carDAO, UserDAO userDAO, UserService userSevice) {
        this.userSevice = userSevice;
        this.journeyDAO = journeyDAO;
        this.transactionManager = transactionManager;
        this.carDAO = carDAO;
        this.userDAO = userDAO;
    }

    public List<Journey> getJourneys() {
        return transactionManager.executeWithoutTransaction(() -> journeyDAO.getAll());
    }

    public List<JourneyDisplayDTO> getJourneys(FilterJourney filter) {
        return transactionManager.execute(() -> {
            filter.setCountPages(journeyDAO.getCountPages(filter));
            LOGGER.trace(filter.getCountPages());
            return journeyDAO.getJourneys(filter);
        });
    }

    public int create(Journey journey) {
        return transactionManager.execute(() -> journeyDAO.create(journey));
    }

    public String addCarToJourney(int journeyId, int carId) {
        return transactionManager.execute(() ->{
            Journey journey = journeyDAO.read(journeyId).get();
            if (!journey.isSuit(carDAO.read(carId).get())) {
                return "Car don't suit for this journey";
            }
            if (!Objects.equals(journey.getStatus().getStatus(), StatusJourney.NEW.getStatus())) {
                return "Journey already started";
            }
            if (carDAO.isRegisteredOnJourney(journeyId, carId)) {
                return "Car already subscribed";
            }
            //if (carDAO.isOnRoad(carId)) {
              //  return "Car now in journey";
            //}
            journeyDAO.setCarToJourney(journeyId, carId);
            return "";
        });
    }

    public String acceptToJourney(int journeyId, int carId) {
        return transactionManager.execute(() -> {
            Journey journey = journeyDAO.read(journeyId).get();
            Car car = carDAO.read(carId).get();
            if (car.getId() == 0) {
                return "Car don't exist";
            }
            if (journey.getId() == 0) {
                return "Journey don't exist";
            }
            if (!Objects.equals(journey.getStatus().getStatus(), StatusJourney.NEW.getStatus())) {
                return "Journey already started";
            }
            if (!journeyDAO.isHasCar(carId, journeyId)) {
                return "Car don't subscribed to this journey";
            }
            if (!journey.isSuit(carDAO.read(carId).get())) {
                return "Car don't suit for this journey";
            }
            List<Car> cars = carDAO.getCarsByIdDriver(userDAO.getDriverByIdCar(carId).get().getId());
            for (Car c : cars) {
                if (carDAO.isOnRoad(c.getId())) {
                    return "Driver now in journey";
                }
            }
            journeyDAO.acceptCarToJourney(journeyId, carId);
            journey.setStatus(StatusJourney.ON_PROCESS);
            journeyDAO.update(journey);
            return "";
        });
    }

    public boolean cancel(int cancel) {
        LOGGER.trace("tesr");
        return transactionManager.execute(() -> {
            Journey journey = journeyDAO.read(cancel).get();
            journey.setStatus(StatusJourney.CANCELED);
            return journeyDAO.update(journey);

        });
    }


    public boolean confirm(int confirm, User user) {
        return transactionManager.execute(() -> {
            Journey journey = journeyDAO.read(confirm).get();
            user.setSalary(user.getSalary() + journey.getPrice());
            userDAO.update(user);
            return journeyDAO.confirm(confirm);
        });
    }
}
