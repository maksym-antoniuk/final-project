package ua.nure.antoniuk.db.dao;

import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;

import java.util.List;
import java.util.Optional;

public interface PotentialCarDAO extends CRUD<PotentialCar> {

    Optional<PotentialCar> getByIdDriver(int idDriver);

    boolean isExistCarByIdPotentialUser(PotentialUser user);

    boolean isExist(String carNumber);

    List<PotentialCar> getAll();

    boolean delete(int id);
}
