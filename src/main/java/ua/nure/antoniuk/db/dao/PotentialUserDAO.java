package ua.nure.antoniuk.db.dao;


import ua.nure.antoniuk.entity.PotentialUser;

import java.util.List;
import java.util.Optional;

public interface PotentialUserDAO extends CRUD<PotentialUser> {

    Optional<PotentialUser> getByEmail(String email);

    List<PotentialUser> getAll();
}
