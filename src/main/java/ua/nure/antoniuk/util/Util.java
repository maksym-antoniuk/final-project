package ua.nure.antoniuk.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Util {

    public static Role getRole(String role) {
        switch (role) {
            case Constants.ADMIN_ROLE:
                return Role.ADMIN;
            case Constants.DRIVER_ROLE:
                return Role.DRIVER;
             default:
                return Role.MANAGER;
        }
    }

    public static Calendar getCalendarByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String hash(String password) {
        return "";
    }

    public static boolean isMatch(String regex, String field) {
        return Pattern.compile(regex).matcher(field).find();
    }

    public static int typeRole(HttpServletRequest request) {
        if (Role.MANAGER.getRole().equals(request.getParameter(Constants.PARAM_ROLE))) {
            return Constants.MANAGER;
        }
        if (Role.DRIVER.getRole().equals(request.getParameter(Constants.PARAM_ROLE))) {
            return Constants.DRIVER;
        }
        if (Role.ADMIN.getRole().equals(request.getParameter(Constants.PARAM_ROLE))) {
            return Constants.ADMIN;
        }
        return 0;
    }

    private Util() {
        throw new UnsupportedOperationException("You can`t create an instance of this class");
    }

    public static Bodywork getBodywork(String bodywork) {
        switch (bodywork) {
            case Constants.TANK_TYPE_BODYWORK:
                return Bodywork.TANK;
            case Constants.BULK_TYPE_BODYWORK:
                return Bodywork.BULK;
            case Constants.CAR_TYPE_BODYWORK:
                return Bodywork.CAR;
            case Constants.ANIMAL_TYPE_BODYWORK:
                return Bodywork.ANIMAL;
            default:
                return Bodywork.CONTAINER;
        }
    }
}
