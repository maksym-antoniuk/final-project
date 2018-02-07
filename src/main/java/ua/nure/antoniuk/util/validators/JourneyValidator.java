package ua.nure.antoniuk.util.validators;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.JourneyDTO;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.Util;

import java.util.*;

public class JourneyValidator {
    private static final Logger LOGGER = Logger.getLogger(JourneyValidator.class);

    private static final String DIRECTION = ".{5}";
    private static final String FLOAT = "\\d+|(\\d+\\.\\d+)";

    public Map<String, String> validate(JourneyDTO journey) {
        Map<String, String> errors = new HashMap<>();
        if (!Util.isMatch(DIRECTION, journey.getFrom())) {
            errors.put(Constants.DIRECTION_FROM, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(DIRECTION, journey.getWhere())) {
            errors.put(Constants.DIRECTION_WHERE, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(FLOAT, journey.getWeight())) {
            errors.put(Constants.WEIGHT, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(FLOAT, journey.getVolume())) {
            errors.put(Constants.VOLUME, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(FLOAT, journey.getPrice())) {
            errors.put(Constants.PRICE, Constants.INVALID_FORMAT);
        }
        if (!Arrays.asList(Material.values()).contains(Material.valueOf(journey.getMaterial().toUpperCase()))) {
            errors.put(Constants.MATERIAL, Constants.INVALID_TYPE);
        }
        return errors;
    }
}
