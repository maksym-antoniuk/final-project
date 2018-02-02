package ua.nure.antoniuk.dto;

import ua.nure.antoniuk.entity.Car;

import java.util.Objects;

public class CarGarageDTO {

    private Car car;
    private int countSubscribed;
    private int countPerformed;

    public Car getCar() {
        return car;
    }

    public CarGarageDTO setCar(Car car) {
        this.car = car;
        return this;
    }

    public int getCountSubscribed() {
        return countSubscribed;
    }

    public CarGarageDTO setCountSubscribed(int countSubscribed) {
        this.countSubscribed = countSubscribed;
        return this;
    }

    public int getCountPerformed() {
        return countPerformed;
    }

    public CarGarageDTO setCountPerformed(int countPerformed) {
        this.countPerformed = countPerformed;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarGarageDTO that = (CarGarageDTO) o;
        return getCountSubscribed() == that.getCountSubscribed() &&
                getCountPerformed() == that.getCountPerformed() &&
                Objects.equals(getCar(), that.getCar());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCar(), getCountSubscribed(), getCountPerformed());
    }

    @Override
    public String toString() {
        return "CarGarageDTO{" +
                "car=" + car.toString() +
                ", countSubscribed=" + countSubscribed +
                ", countPerformed=" + countPerformed +
                '}';
    }


}
