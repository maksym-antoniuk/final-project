package ua.nure.antoniuk.db.dao;

import ua.nure.antoniuk.entity.Journey;

import java.util.List;

public interface JourneyDAO extends CRUD<Journey>{

    List<Journey> getAll();
}
