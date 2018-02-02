package ua.nure.antoniuk.db.builder;

import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.StatusJourney;

import java.util.List;

public class FilterJourney extends Filter {

    private boolean ascDateStart;
    private boolean ascDateFinish;
    private List<StatusJourney> statusJourneys;
    private boolean ascPrice;
    private List<Material> materials;

}
