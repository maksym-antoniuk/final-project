package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.util.Bodywork;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Parameters;
import ua.nure.antoniuk.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

public class CarDTO {
    private String id;
    private String carNumber;
    private String carMark;
    private String carModel;
    private String bodywork;
    private String capacity;
    private String volume;

    public CarDTO(HttpServletRequest request) {
        id = StringUtil.stringOrEmptyString(request.getParameter(Parameters.EDIT_CAR));
        carNumber = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_NUMBER));
        carMark = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_MARK));
        carModel = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_MODEL));
        bodywork = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_BODYWORK));
        capacity = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_CAPACITY));
        volume = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_VOLUME));
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getBodywork() {
        return bodywork;
    }

    public void setBodywork(String bodywork) {
        this.bodywork = bodywork;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PotentialCar toPotentialCar() {
        PotentialCar car = new PotentialCar();
        car.setMark(carMark);
        car.setModel(carModel);
        car.setNumber(carNumber);
        car.setMaxWeight(Float.parseFloat(capacity));
        car.setMaxVolume(Float.parseFloat(volume));
        car.setBodywork(Bodywork.valueOf(bodywork.toUpperCase()));
        return car;
    }

    public Car toCar() {
        Car car = new Car();
        car.setId(Integer.parseInt(id));
        car.setMark(carMark);
        car.setModel(carModel);
        car.setNumber(carNumber);
        car.setMaxWeight(Float.parseFloat(capacity));
        car.setMaxVolume(Float.parseFloat(volume));
        car.setBodywork(Bodywork.valueOf(bodywork.toUpperCase()));
        return car;
    }
}
