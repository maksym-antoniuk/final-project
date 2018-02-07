package ua.nure.antoniuk.util.validators;

import ua.nure.antoniuk.dto.CarDTO;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Util;

import java.util.HashMap;
import java.util.Map;

public class CarValidator {
    private static final String CAR_MODEL = "(?U)\\w{2,15}";
    private static final String CAR_MARK = "(?U)\\w{2,15}";
    private static final String CAR_NUMBER = "(?U)\\w{5,15}";
    private static final String CAR_CAPACITY = "^(\\d*\\.?\\d*)$";
    private static final String CAR_MAX_WEIGHT = "^(\\d*\\.?\\d*)$";

    public Map<String, String> validateCreate(CarDTO carDTO) {
        Map<String, String> errors = new HashMap<>();
        if (!Util.isMatch(CAR_MARK, carDTO.getCarMark())) {
            errors.put(Constants.CAR_MARK, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(CAR_MODEL, carDTO.getCarModel())) {
            errors.put(Constants.CAR_MODEL, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(CAR_NUMBER, carDTO.getCarNumber())) {
            errors.put(Constants.CAR_NUMBER, Constants.INVALID_FORMAT);
        }
        if (!Constants.BODYWORK_LIST.contains(carDTO.getBodywork())) {
            errors.put(Constants.BODYWORK, Constants.INVALID_TYPE);
        }
        if (!Util.isMatch(CAR_CAPACITY, carDTO.getVolume())) {
            errors.put(Constants.CAPACITY, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(CAR_MAX_WEIGHT, carDTO.getCapacity())) {
            errors.put(Constants.MAX_WEIGHT, Constants.INVALID_FORMAT);
        }
        return errors;
    }

    public Map<String, String> validateEdit(CarDTO carDTO) {
        Map<String, String> errors = new HashMap<>();
        if (!Util.isMatch(CAR_NUMBER, carDTO.getCarNumber())) {
            errors.put(Constants.CAR_NUMBER, Constants.INVALID_FORMAT);
        }
        if (!Constants.BODYWORK_LIST.contains(carDTO.getBodywork())) {
            errors.put(Constants.BODYWORK, Constants.INVALID_TYPE);
        }
        if (!Util.isMatch(CAR_CAPACITY, carDTO.getCapacity())) {
            errors.put(Constants.CAPACITY, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch(CAR_MAX_WEIGHT, carDTO.getVolume())) {
            errors.put(Constants.MAX_WEIGHT, Constants.INVALID_FORMAT);
        }
        if (!Util.isMatch("\\d+", carDTO.getId())) {
            errors.put("Id", Constants.INVALID_FORMAT);
        }
        return errors;
    }
}
