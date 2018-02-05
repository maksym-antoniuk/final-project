package ua.nure.antoniuk.util;

public abstract class Parameters {

    public static final String ADD_CAR_TO_JOURNEY = "addCarToJourney";
    public static final String ACCEPT_CAR_TO_JOURNEY = "acceptCarToJourney";
    public static final String CHANGE_CAR_STATUS = "changeStatus";
    public static final String UPLOAD_IMG_CAR = "uploadImgCar";
    public static final String UPLOAD_IMG_USER = "uploadImgUser";
    public static final String CREATE_CAR = "create";
    public static final String DELETE_CAR = "delete";
    public static final String EDIT_CAR = "edit";

    private Parameters() {
        throw new UnsupportedOperationException("You can't create instance of this class");
    }
}
