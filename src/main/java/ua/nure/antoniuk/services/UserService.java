package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.dao.PotentialUserDAO;
import ua.nure.antoniuk.db.dao.UserDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private TransactionManager transactionManager;
    private PotentialUserDAO potentialUserDAO;
    private PotentialCarDAO potentialCarDAO;
    private UserDAO userDAO;

    public UserService(TransactionManager transactionManager, PotentialUserDAO potentialUserDAO, PotentialCarDAO potentialCarDAO, UserDAO userDAO) {
        this.transactionManager = transactionManager;
        this.potentialUserDAO = potentialUserDAO;
        this.potentialCarDAO = potentialCarDAO;
        this.userDAO = userDAO;
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
            System.out.println(userId);
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

    public int tryToLogin(User user) {
        return transactionManager.execute(() -> userDAO.tryToLogin(user.getEmail(), user.getPassword()));
    }

    public User getByEmail(String email) {
        LOGGER.trace("User service #getByLogin" + "email");
        return transactionManager.execute(() -> userDAO.getByEmail(email).get());
    }

    public List<PotentialUser> getPotentialUsers() {
        return transactionManager.execute(() -> potentialUserDAO.getAll());
    }

}
