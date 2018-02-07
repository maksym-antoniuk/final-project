package ua.nure.antoniuk.util.validators;


import junit.framework.JUnit4TestAdapter;
import org.junit.Test;
import ua.nure.antoniuk.dto.LoginDTO;

import static org.junit.Assert.assertEquals;

public class TestLoginValidator {

    @Test
    public void testValidateTrue() {
        LoginValidator validator = new LoginValidator();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("qwdqw@gdjdw.ua");
        loginDTO.setPassword("dsasd");
        assertEquals(validator.validate(loginDTO).isEmpty(), true);
    }

    @Test
    public void testValidateFalse() {
        LoginValidator validator = new LoginValidator();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("@gdjdw.ua");
        loginDTO.setPassword("sd");
        assertEquals(validator.validate(loginDTO).isEmpty(), false);
    }
}
