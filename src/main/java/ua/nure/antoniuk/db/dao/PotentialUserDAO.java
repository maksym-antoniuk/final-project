package ua.nure.antoniuk.db.dao;


import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.util.Role;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PotentialUserDAO extends CRUD<PotentialUser> {

    Optional<PotentialUser> getByEmail(String email);

    List<PotentialUser> getManagers();

    Map<PotentialUser, PotentialCar> getDrivers();
}
