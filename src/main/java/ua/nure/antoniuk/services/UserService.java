package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.CarDAO;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.dao.PotentialUserDAO;
import ua.nure.antoniuk.db.dao.UserDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private TransactionManager transactionManager;
    private PotentialUserDAO potentialUserDAO;
    private PotentialCarDAO potentialCarDAO;
    private UserDAO userDAO;
    private CarDAO carDAO;

    public UserService(TransactionManager transactionManager, PotentialUserDAO potentialUserDAO, PotentialCarDAO potentialCarDAO, UserDAO userDAO, CarDAO carDAO) {
        this.transactionManager = transactionManager;
        this.potentialUserDAO = potentialUserDAO;
        this.potentialCarDAO = potentialCarDAO;
        this.userDAO = userDAO;
        this.carDAO = carDAO;
    }

    public boolean isExist(String email) {
        return false;
    }

    public int create(PotentialUser user) {
        int id = transactionManager.execute(() -> potentialUserDAO.create(user));
        user.setId(id);
        return id;
    }

    public int create(PotentialUser user, PotentialCar car) {
        return transactionManager.execute(() -> {
            int userId = potentialUserDAO.create(user);
            car.setIdPotentialUser(userId);
            int idCar = potentialCarDAO.create(car);
            user.setId(userId);
            car.setId(idCar);
            return userId;
        });
    }

    public int create(User user) {
        int id = transactionManager.execute(() -> userDAO.create(user));
        user.setId(id);
        return id;
    }

    public int create(User user, Car car, PotentialUser potentialUser, PotentialCar potentialCar) {
        return transactionManager.execute(() -> {
            int userId = userDAO.create(user);
            car.setIdDriver(userId);
            int idCar = carDAO.create(car);
            user.setId(userId);
            car.setId(idCar);
            potentialCarDAO.delete(potentialCar);
            potentialUserDAO.delete(potentialUser);
            return userId;
        });
    }

    public int tryToLogin(User user) {
        return transactionManager.execute(() -> userDAO.tryToLogin(user.getEmail(), user.getPassword()));
    }

    public PotentialUser getPotentialUserById(int id) {
        return transactionManager.executeWithoutTransaction(() -> potentialUserDAO.read(id).get());
    }

    public User getByEmail(String email) {
        LOGGER.trace("User service #getByLogin" + "email");
        return transactionManager.executeWithoutTransaction(() -> userDAO.getByEmail(email).get());
    }

    public List<PotentialUser> getPotentialManagers() {
        return transactionManager.executeWithoutTransaction(() -> potentialUserDAO.getManagers());
    }

    public Map<PotentialUser, PotentialCar> getPotentialDrivers() {
        return transactionManager.executeWithoutTransaction(() -> potentialUserDAO.getDrivers());
    }



    public User potentialUserToUser(PotentialUser potentialUser) {
        User user = new User();
        user.setEmail(potentialUser.getEmail());
        user.setRole(potentialUser.getRole());
        user.setName(potentialUser.getName());
        user.setLastname(potentialUser.getLastname());
        user.setPhone(potentialUser.getPhone());
        return user;
    }

}
