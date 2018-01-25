package ua.nure.antoniuk.util;

import java.util.Objects;

public abstract class StringUtil {

    public static String stringOrDefault(String string, String defaultString) {
        return Objects.isNull(string) ? defaultString : string;
    }

    public static String stringOrEmptyString(String string) {
        return stringOrDefault(string, "");
    }

    private StringUtil(){
        throw new UnsupportedOperationException("You can`t create instance of this class");
    }
}
