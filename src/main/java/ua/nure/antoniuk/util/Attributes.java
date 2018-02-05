package ua.nure.antoniuk.util;

public abstract class Attributes {

    //Session attributes
    public static final String SESSION_CARS = "driverCars";
    public static final String SESSION_USER = "user";
    public static final String SESSION_FILTER_JOURNEY = "filterJourney";
    public static final String SESSION_LOCALE = "locale";

    public static final String SESSION_ERROR_ADD_CAR_TO_JOURNEY = "errorCarJourney";
    public static final String SESSION_ERROR_ACCEPT_CAR_TO_JOURNEY = "errorAcceptCarJourney";
    public static final String SESSION_ERROR_UPLOAD = "errorUpload";
    public static final String SESSION_ERROR_CREATE_CAR = "createCarError";
    public static final String SESSION_ERROR_STATUS_CAR = "errorStatusCar";
    public static final String SESSION_ERROR_ACCEPT_POTENTIAL_CAR = "errorAcceptPotentialCar";
    public static final String SESSION_ERROR_REMOVE_POTENTIAL_CAR = "errorRemovePotentialCar";
    public static final String SESSION_ERROR_DELETE_CAR = "errorDeleteCar";
    public static final String SESSION_ERROR_EDIT_CAR = "errorEditCar";

    //Request Attributes
    public static final String REQUEST_GARAGE_CARS = "garageCars";
    public static final String REQUEST_PORTFOLIO = "portfolio";
    public static final String REQUEST_POTENTIAL_CARS = "potentialCars";
    public static final String SESSION_DTO_CAR = "dto";

    private Attributes() {
        throw new UnsupportedOperationException("You can't create instance of this class");
    }


}
