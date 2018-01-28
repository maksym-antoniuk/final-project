package ua.nure.antoniuk.db.dao;

import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;

import java.util.Optional;

public interface PotentialCarDAO extends CRUD<PotentialCar> {

    Optional<PotentialCar> getByIdDriver(int idDriver);

    boolean isExistCarByIdPotentialUser(PotentialUser user);

}
