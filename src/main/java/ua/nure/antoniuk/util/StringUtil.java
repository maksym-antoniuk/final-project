package ua.nure.antoniuk.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import ua.nure.antoniuk.entity.Car;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;


public abstract class StringUtil {

    public static String stringOrDefault(String string, String defaultString) {
        return Objects.isNull(string) ? defaultString : string;
    }

    public static String stringOrEmptyString(String string) {
        return stringOrDefault(string, "");
    }

    public static String generatePassword() {
        return RandomStringUtils.random(7, true, true);
    }

    public static String MD5(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static String toArrayJsonCars(List<Car> cars, String name) {
        if (cars.isEmpty()) {
            return "{\"" + name + "\": []}";
        }
        StringBuilder stringBuilder = new StringBuilder("{\"" + name + "\": [");
        for (Car car : cars) {
            stringBuilder.append("{\"id\":\"").append(car.getId()).append("\",\"number\":\"").append(car.getNumber()).append("\",\"mark\":\"")
                    .append(car.getMark()).append("\",\"model\":\"").append(car.getModel()).append("\",\"bodywork\":\"").append(car.getBodywork().getBodywork())
                    .append("\",\"driverId\":\"").append(car.getIdDriver()).append("\",\"maxWeight\":\"").append(car.getMaxWeight()).append("\",\"maxVolume\":\"")
                    .append(car.getMaxVolume()).append("\",\"status\":\"").append(car.getStatus().getStatus()).append("\"},");
        }
        stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "").append("]}");
        return stringBuilder.toString();
    }

    private StringUtil(){
        throw new UnsupportedOperationException("You can`t create instance of this class");
    }
}
