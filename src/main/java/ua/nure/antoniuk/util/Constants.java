package ua.nure.antoniuk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Constants {
    public static final String MANAGER_ROLE = "manager";
    public static final String DRIVER_ROLE = "driver";
    public static final String ADMIN_ROLE = "admin";

    public static final String TANK_TYPE_BODYWORK = "tank";
    public static final String BULK_TYPE_BODYWORK = "bulk";
    public static final String CAR_TYPE_BODYWORK = "car";
    public static final String ANIMAL_TYPE_BODYWORK = "animal";
    public static final String CONTAINER_TYPE_BODYWORK = "container";

    public static final int MANAGER = 1;
    public static final int DRIVER = 2;
    public static final int ADMIN = 3;

    //Error handlers
    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone number";
    public static final String PASSWORD = "Password";

    public static final String CAR_NUMBER = "Car number";
    public static final String CAR_MARK = "Car mark";
    public static final String CAR_MODEL = "Car model";
    public static final String CAPACITY = "Car volume capacity";
    public static final String VOLUME = "Volume";
    public static final String MAX_WEIGHT = "Car max weight";
    public static final String WEIGHT = "Weight";
    public static final String BODYWORK = "Car bodywork";
    public static final String DIRECTION_FROM = "Point of departure";
    public static final String DIRECTION_WHERE = "Destination";
    public static final String MATERIAL = "Material";
    public static final String PRICE = "Price";

    //Error validate body
    public static final String INVALID_FORMAT = "Invalid format";

    public static final String INVALID_TYPE = "Invalid car bodywork";

    public static final String INCORRECT = "Incorrect";


    //Parameters names
    public static final String PARAM_ROLE = "role";

    public static final String PARAM_DRIVER_NAME = "driver_name";
    public static final String PARAM_DRIVER_SURNAME = "driver_surname";
    public static final String PARAM_DRIVER_EMAIL = "driver_email";
    public static final String PARAM_DRIVER_PHONE = "driver_phone";
    public static final String PARAM_DRIVER_CAR_NUMBER = "car_number";
    public static final String PARAM_DRIVER_CAR_MARK = "car_mark";
    public static final String PARAM_DRIVER_CAR_MODEL = "car_model";
    public static final String PARAM_DRIVER_CAR_BODYWORK = "type_bodywork";
    public static final String PARAM_DRIVER_CAR_CAPACITY = "capacity";
    public static final String PARAM_DRIVER_CAR_VOLUME = "volume";

    public static final String PARAM_MANAGER_NAME = "manager_name";
    public static final String PARAM_MANAGER_SURNAME = "manager_surname";
    public static final String PARAM_MANAGER_EMAIL = "manager_email";
    public static final String PARAM_MANAGER_PHONE = "manager_phone";

    public static final String PARAM_LOGIN_EMAIL = "login_email";
    public static final String PARAM_LOGIN_PASSWORD = "login_password";

    public static final String EMAIL_ALREADY_EXIST = "email already exists";

    //Service names attribute
    public static final String SERVICE_REGISTRATION = "registration_service";
    public static final String SERVICE_USER = "user_service";
    public static final String SERVICE_CAR = "car_service";
    public static final String SERVICE_LOGIN = "login_service";
    public static final String SERVICE_JOURNEY = "journey_service";
    public static final String SERVICE_LOGOUT = "logout_service";
    public static final String SERVICE_FILE = "file_service";
    public static final String SERVICE_FILTRATION = "filtration_service";
    //Attribute names
    public static final String REGISTRATION_ERROR = "reg_error";
    public static final String REGISTRATION_DTO_MANAGER = "reg_dto_manager";
    public static final String REGISTRATION_DTO_DRIVER = "reg_dto_driver";

    public static final List<String> BODYWORK_LIST = new ArrayList<>();

    public static final String ONLINE_USERS = "onlineUsers";
    public static final String ALL_USERS = "allUsers";
    public static final String POTENTIAL_MANAGERS = "managers";
    public static final String POTENTIAL_DRIVERS = "drivers";
    public static final String ALL_CARS = "allCars";

    //Session attributes
    public static final String SESSION_USER = "user";


    static {
        BODYWORK_LIST.add(Bodywork.ANIMAL.getBodywork());
        BODYWORK_LIST.add(Bodywork.BULK.getBodywork());
        BODYWORK_LIST.add(Bodywork.CAR.getBodywork());
        BODYWORK_LIST.add(Bodywork.TANK.getBodywork());
        BODYWORK_LIST.add(Bodywork.CONTAINER.getBodywork());
    }

    public static final int CODE_INCORRECT_EMAIL = 1;
    public static final int CODE_INCORRECT_PASSWORD = 2;

    public static final String COUNTER = "session-counter";
    private Constants() {

        throw new UnsupportedOperationException("You can`t create instance of this class");
    }
}
