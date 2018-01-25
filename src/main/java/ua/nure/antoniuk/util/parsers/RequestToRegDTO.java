package ua.nure.antoniuk.util.parsers;

import ua.nure.antoniuk.dto.DriverRegDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RequestToRegDTO {

    public DriverRegDTO getDriverDTOFromRequest(HttpServletRequest request) {
        DriverRegDTO dto = new DriverRegDTO(request);
        return null;
    }


}
