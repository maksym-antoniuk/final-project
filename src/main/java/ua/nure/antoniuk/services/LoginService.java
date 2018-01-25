package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.LoginDTO;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.validators.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private final static Logger LOGGER = Logger.getLogger(LoginService.class);
    private LoginValidator validator;
    private UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
        validator = new LoginValidator();
    }

    public Map<String, String> validate(HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        LoginDTO loginDTO = new LoginDTO(request);
        errors.putAll(validator.validate(loginDTO));
        if (!errors.isEmpty()) {
            return errors;
        }
        switch (userService.tryToLogin(loginDTO.extractUser())) {
            case Constants.CODE_INCORRECT_EMAIL:
                errors.put(Constants.EMAIL, Constants.INCORRECT_EMAIL);
                LOGGER.trace(errors.get(Constants.EMAIL));
                break;
            case Constants.CODE_INCORRECT_PASSWORD:
                errors.put(Constants.PASSWORD, Constants.INCORRECT_PASSWORD);
                LOGGER.trace(errors.get(Constants.PASSWORD));
                break;
        }
        return errors;
    }

    public User getByEmail(HttpServletRequest request) {
        return userService.getByEmail(request.getParameter(Constants.PARAM_LOGIN_EMAIL));
    }
}
