package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class DriverRegDTO{

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String carNumber;
    private String carMark;
    private String carModel;
    private String bodywork;
    private String capacity;
    private String volume;

    public DriverRegDTO(HttpServletRequest request) {
        name = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_NAME));
        surname = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_SURNAME));
        email = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_EMAIL));
        phone = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_PHONE));
        carNumber = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_NUMBER));
        carMark = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_MARK));
        carModel = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_MODEL));
        bodywork = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_BODYWORK));
        capacity = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_CAPACITY));
        volume = StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_CAR_VOLUME));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverRegDTO that = (DriverRegDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getCarNumber(), that.getCarNumber()) &&
                Objects.equals(getCarMark(), that.getCarMark()) &&
                Objects.equals(getCarModel(), that.getCarModel()) &&
                Objects.equals(getBodywork(), that.getBodywork()) &&
                Objects.equals(getCapacity(), that.getCapacity()) &&
                Objects.equals(getVolume(), that.getVolume());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname(), getEmail(), getPhone(), getCarNumber(), getCarMark(), getCarModel(), getBodywork(), getCapacity(), getVolume());
    }

    @Override
    public String toString() {
        return "DriverRegDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", carMark='" + carMark + '\'' +
                ", carModel='" + carModel + '\'' +
                ", bodywork='" + bodywork + '\'' +
                ", capacity='" + capacity + '\'' +
                ", maxWeight='" + volume + '\'' +
                '}';
    }

    public PotentialUser extractPotentialUser() {
        PotentialUser user = new PotentialUser();
        user.setName(name);
        user.setLastname(surname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(Util.getRole(Constants.DRIVER_ROLE));
        return user;
    }

    public PotentialCar extractPotentialCar() {
        PotentialCar car = new PotentialCar();
        car.setMark(carMark);
        car.setModel(carModel);
        car.setNumber(carNumber);
        car.setBodywork(Util.getBodywork(bodywork));
        car.setMaxVolume(Float.parseFloat(volume));
        car.setMaxWeight(Float.parseFloat(capacity));
        return car;
    }
}
