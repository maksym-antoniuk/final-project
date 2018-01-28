package ua.nure.antoniuk.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;
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

    private StringUtil(){
        throw new UnsupportedOperationException("You can`t create instance of this class");
    }
}
