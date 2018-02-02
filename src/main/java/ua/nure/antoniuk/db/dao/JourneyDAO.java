package ua.nure.antoniuk.db.dao;

import ua.nure.antoniuk.db.builder.Filter;
import ua.nure.antoniuk.dto.JourneyDisplayDTO;
import ua.nure.antoniuk.entity.Journey;

import java.util.List;

public interface JourneyDAO extends CRUD<Journey>{

    List<Journey> getAll();

    boolean setCarToJourney(int journeyId, int carId);

    boolean acceptCarToJourney(int journeyId, int carId);

    boolean isHasCar(int carId, int journeyId);

    boolean confirm(int id);

    List<JourneyDisplayDTO> getJourneys(Filter filter);
}
