package ua.nure.antoniuk.util;

public abstract class Attributes {

    //Session attributes
    public static final String SESSION_CARS = "driverCars";
    public static final String SESSION_USER = "user";

    public static final String SESSION_ERROR_ADD_CAR_TO_JOURNEY = "errorCarJourney";
    public static final String SESSION_ERROR_ACCEPT_CAR_TO_JOURNEY = "errorAcceptCarJourney";

    //Request Attributes
    public static final String REQUEST_GARAGE_CARS = "garageCars";

    private Attributes() {
        throw new UnsupportedOperationException("You can't create instance of this class");
    }


}
