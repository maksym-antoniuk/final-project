package ua.nure.antoniuk.services;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.JourneyDAO;
import ua.nure.antoniuk.db.transaction.TransactionManager;
import ua.nure.antoniuk.entity.Journey;

import java.util.List;

public class JourneyService {
    private static final Logger LOGGER = Logger.getLogger(JourneyService.class);

    private JourneyDAO journeyDAO;
    TransactionManager transactionManager;

    public JourneyService(JourneyDAO journeyDAO, TransactionManager transactionManager) {
        this.journeyDAO = journeyDAO;
        this.transactionManager = transactionManager;
    }

    public List<Journey> getJourneys() {
        return transactionManager.executeWithoutTransaction(() -> journeyDAO.getAll());
    }
}
