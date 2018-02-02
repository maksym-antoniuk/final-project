package ua.nure.antoniuk.util;

public abstract class Parameters {

    public static final String ADD_CAR_TO_JOURNEY = "addCarToJourney";
    public static final String ACCEPT_CAR_TO_JOURNEY = "acceptCarToJourney";
    public static final String CHANGE_CAR_STATUS = "changeStatus";

    private Parameters() {
        throw new UnsupportedOperationException("You can't create instance of this class");
    }
}
