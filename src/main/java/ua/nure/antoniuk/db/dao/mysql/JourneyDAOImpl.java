package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.JourneyDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.Journey;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.Status;
import ua.nure.antoniuk.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JourneyDAOImpl implements JourneyDAO {
    private static final Logger LOGGER = Logger.getLogger(JourneyDAOImpl.class);
    private static final String GET_ALL_JOURNEYS = "SELECT * FROM journeys";

    @Override
    public List<Journey> getAll() {
        Connection connection = ConnectionManager.getConnection();
        List<Journey> journeys = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_JOURNEYS);
            while (resultSet.next()) {
                journeys.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return journeys;
    }

    @Override
    public int create(Journey entity) {
        return 0;
    }

    @Override
    public Optional<Journey> read(int id) {
        return Optional.empty();
    }

    @Override
    public boolean update(Journey entity) {
        return false;
    }

    @Override
    public boolean delete(Journey entity) {
        return false;
    }

    private Journey extract(ResultSet resultSet) throws SQLException {
        Journey journey = new Journey();
        journey.setId(resultSet.getInt("id"));
        journey.setDate(Util.getCalendarByDate(resultSet.getTimestamp("date")));
        journey.setStatus(Status.valueOf(resultSet.getString("status").toUpperCase()));
        journey.setPrice(resultSet.getFloat("price"));
        journey.setWeight(resultSet.getFloat("weight"));
        journey.setMaterial(Material.valueOf(resultSet.getString("type").toUpperCase()));
        journey.setVolume(resultSet.getFloat("volume"));
        journey.setIdManager(resultSet.getInt("id_manager"));
        journey.setIdCar(Objects.isNull(resultSet.getInt("cars_id")) ? 0 : resultSet.getInt("cars_id"));
        if (!Objects.isNull(resultSet.getTimestamp("date_finish"))) {
            journey.setDateFinish(Util.getCalendarByDate(resultSet.getDate("date_finish")));
        }
        journey.setFrom(resultSet.getString("from"));
        journey.setWhere(resultSet.getString("where"));
        return journey;
    }
}
