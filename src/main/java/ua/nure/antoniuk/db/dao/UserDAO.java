package ua.nure.antoniuk.db.dao;


import ua.nure.antoniuk.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends CRUD<User> {

    Optional<User> getByEmail(String email);

    int tryToLogin(String email, String password);

    boolean isExist(String email);

    List<User> getUsers();
}
