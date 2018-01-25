package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.dto.DriverRegDTO;
import ua.nure.antoniuk.dto.ManagerRegDTO;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.util.Constants;
import ua.nure.antoniuk.util.Role;
import ua.nure.antoniuk.util.StringUtil;
import ua.nure.antoniuk.util.Util;
import ua.nure.antoniuk.util.validators.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RegistrationService {
    private final static Logger LOGGER = Logger.getLogger(RegistrationService.class);
    private RegistrationValidator validator;
    private UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
        validator = new RegistrationValidator();
    }

    public Map<String, String> validate(HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        switch (Util.typeRole(request)) {
            case Constants.DRIVER: {
                errors.putAll(validator.validateDriverDTO(new DriverRegDTO(request)));
                LOGGER.trace(errors.keySet().toArray());
                break;
            }
            case Constants.MANAGER: {
                errors.putAll(validator.validateManagerDTO(new ManagerRegDTO(request)));
                LOGGER.trace(errors.keySet().toArray());
                break;
            }
            default: {
                errors.put("Role type", "incorrect");
                LOGGER.trace(errors.keySet().toArray());
            }
        }
        if (!errors.isEmpty()) {
            return errors;
        }
        if (userService.isExist(StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_MANAGER_EMAIL))
                + StringUtil.stringOrEmptyString(request.getParameter(Constants.PARAM_DRIVER_EMAIL)))) {
            errors.put(Constants.EMAIL, Constants.EMAIL_ALREADY_EXIST);
        }
        LOGGER.trace(errors.keySet().toArray());
        return errors;
    }

    public boolean register(HttpServletRequest request) {

        switch (Util.typeRole(request)) {
            case Constants.DRIVER:{
                DriverRegDTO dto = new DriverRegDTO(request);
                userService.create(dto.extractPotentialUser(), dto.extractPotentialCar());
                LOGGER.trace(dto.toString());
                return true;
            }
            case Constants.MANAGER:{
                ManagerRegDTO dto = new ManagerRegDTO(request);
                userService.create(dto.extractPotentialUser());
                LOGGER.trace(dto.toString());
                return true;
            }
        }
        return false;
    }
}
