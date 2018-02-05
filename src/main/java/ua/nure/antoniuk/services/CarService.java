package ua.nure.antoniuk.services;

import ua.nure.antoniuk.db.dao.CarDAO;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.dto.CarDTO;
import ua.nure.antoniuk.dto.CarGarageDTO;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.util.StatusCar;
import ua.nure.antoniuk.util.validators.CarValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CarService {
    private TransactionManager transactionManager;
    private PotentialCarDAO potentialCarDAO;
    private CarDAO carDAO;
    private CarValidator carValidator;

    public CarService(TransactionManager transactionManager, PotentialCarDAO potentialCarDAO, CarDAO carDAO) {
        this.carDAO = carDAO;
        this.transactionManager = transactionManager;
        this.potentialCarDAO = potentialCarDAO;
        carValidator = new CarValidator();
    }

    public int create(PotentialCar car) {
        int id = transactionManager.execute(() -> potentialCarDAO.create(car));
        car.setId(id);
        return id;
    }

    public int create(Car car) {
        int id = transactionManager.execute(() -> carDAO.create(car));
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
        return car;
    }

    public List<Car> getAllCars() {
        return transactionManager.executeWithoutTransaction(() -> carDAO.getAllCars());
    }

    public List<Car> getDriversCars(int id) {
        return transactionManager.executeWithoutTransaction(() -> {
            List<Car> cars = carDAO.getCarsByIdDriver(id);
            for (Car car : cars) {
                System.out.println(car);
            }
            return cars;
        });
    }

    public List<Car> getJourneyCars(int journey) {
        return transactionManager.executeWithoutTransaction(() -> carDAO.getCarsByIdJourney(journey));
    }

    public List<CarGarageDTO> getGarage(int id) {
        return transactionManager.executeWithoutTransaction(() -> carDAO.getGarageById(id));
    }

    public String changeStatusCar(int idCar, int idUser) {
        return transactionManager.execute(() -> {
            Optional<Car> optionalCar = carDAO.read(idCar);
            if (!optionalCar.isPresent()) {
                return "Car doesn't exist";
            }
            Car car = optionalCar.get();
            if (car.getIdDriver() != idUser) {
                return "This is not your car";
            }
            if (Objects.equals(car.getStatus(), StatusCar.OK)) {
                car.setStatus(StatusCar.BROKEN);
            } else {
                car.setStatus(StatusCar.OK);
            }
            carDAO.update(car);
            return "";
        });
    }

    public Map<String, String> validateCreateCar(CarDTO carDTO) {
        Map<String, String> errors = carValidator.validateCreate(carDTO);
        if (errors.isEmpty()) {
            if (transactionManager.execute(() -> carDAO.isExist(carDTO.getCarNumber()) || potentialCarDAO.isExist(carDTO.getCarNumber()))) {
                errors.put("Number", "Already exist");
            }
        }
        return errors;
    }

    public List<PotentialCar> getPotentialCars() {
        return transactionManager.executeWithoutTransaction(() -> potentialCarDAO.getAll());
    }

    public PotentialCar getPotentialCar(int id) {
        return transactionManager.executeWithoutTransaction(() -> {
            Optional<PotentialCar> optional = potentialCarDAO.read(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
              return new PotentialCar();
            }
        });
    }

    public String acceptCar(int id) {
        return transactionManager.execute(() -> {
            String error = "";
            Optional<PotentialCar> potentialCar = potentialCarDAO.read(id);
            if (potentialCar.isPresent()) {
                Car car = potentialCarToCar(potentialCar.get());
                carDAO.create(car);
                potentialCarDAO.delete(potentialCar.get().getId());
            } else {
                error = "Car doesn't exist";
            }
            return error;
        });
    }


    public String removePotentialCar(int id) {
        return transactionManager.execute(() -> {
            String error = "";
            Optional<PotentialCar> potentialCar = potentialCarDAO.read(id);
            if (potentialCar.isPresent()) {
                potentialCarDAO.delete(potentialCar.get().getId());
            } else {
                error = "Car doesn't exist";
            }
            return error;
        });
    }

    public String deleteCar(int id) {
        return transactionManager.execute(() -> {
            Optional<Car> optionalCar = carDAO.read(id);
            if (!optionalCar.isPresent()) {
                return "Car doesn't exist";
            }
            carDAO.delete(optionalCar.get());
            return "";
        });
    }

    public Map<String,String> validateEditCar(CarDTO carDTO) {
        Map<String, String> errors = carValidator.validateEdit(carDTO);
        if (errors.isEmpty()) {
            System.out.println(carDTO);
            errors = transactionManager.execute(() -> {
                Map<String, String> error = new HashMap<>();
                if (!carDAO.read(Integer.parseInt(carDTO.getId())).isPresent()) {
                    error.put("Car", "Don't exist");
                    return error;
                } else if (carDAO.isExistExcept(carDTO.getCarNumber(), Integer.parseInt(carDTO.getId())) || potentialCarDAO.isExist(carDTO.getCarNumber())) {
                    error.put("Number", "Already exist");
                }
                return error;
            });
        }
        return errors;
    }

    public boolean update(Car car) {
        return transactionManager.execute(() -> {
            Optional<Car> car1 = carDAO.read(car.getId());
            if (!car1.isPresent()) {
                return false;
            }
            Car car3 = new Car();
            car3.setId(car.getId());
            car3.setMaxWeight(car.getMaxWeight());
            car3.setMaxVolume(car.getMaxVolume());
            car3.setBodywork(car.getBodywork());
            car3.setNumber(car.getNumber());
            car3.setExist(car1.get().getExist());
            car3.setStatus(car1.get().getStatus());
            System.out.println(car3);
            return carDAO.update(car3);
        });
    }
}
