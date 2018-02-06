package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.builder.Filter;
import ua.nure.antoniuk.db.builder.FilterJourney;
import ua.nure.antoniuk.db.dao.JourneyDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.dto.JourneyDisplayDTO;
import ua.nure.antoniuk.entity.Journey;
import ua.nure.antoniuk.exceptions.DBException;
import ua.nure.antoniuk.util.Material;
import ua.nure.antoniuk.util.StatusJourney;
import ua.nure.antoniuk.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JourneyDAOImpl implements JourneyDAO {
    private static final Logger LOGGER = Logger.getLogger(JourneyDAOImpl.class);
    private static final String GET_ALL_JOURNEYS = "SELECT * FROM journeys";
    private static final String ADD_JOURNEY = "INSERT INTO journeys (price, weight, type, volume, id_manager, `from`, `where`) VALUES (?,?,?,?,?,?,?)";
    private static final String ADD_CAR_JOURNEY = "INSERT INTO journeys_has_cars (journeys_id, cars_id) VALUES (?,?)";
    private static final String GET_JOURNEY_BY_ID = "SELECT * FROM journeys WHERE id = ? LIMIT 1";
    private static final String ACCEPT_CAR_JOURNEY = "UPDATE journeys_has_cars SET accept = 'yes' WHERE journeys_id = ? AND cars_id = ?";
    private static final String UPDATE_JOURNEY = "UPDATE journeys SET status = ?, price = ?, weight = ?, type = ?, volume = ?, `from` = ?, `where` = ? WHERE id = ?";
    private static final String IS_HAS_CAR = "SELECT * FROM journeys_has_cars WHERE journeys_id = ? AND cars_id = ?";
    private static final String CONFIRM_BY_ID = "UPDATE journeys SET status = 'old' , date_finish = now() WHERE id = ?";

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
    public boolean setCarToJourney(int journeyId, int carId) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR_JOURNEY)) {
            preparedStatement.setInt(1, journeyId);
            preparedStatement.setInt(2, carId);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean acceptCarToJourney(int journeyId, int carId) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_CAR_JOURNEY)) {
            preparedStatement.setInt(1, journeyId);
            preparedStatement.setInt(2, carId);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean isHasCar(int carId, int journeyId) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(IS_HAS_CAR)) {
            preparedStatement.setInt(1, journeyId);
            preparedStatement.setInt(2, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean confirm(int id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CONFIRM_BY_ID)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("confirm journey " + id, e);
        }
        return result;
    }

    @Override
    public List<JourneyDisplayDTO> getJourneys(FilterJourney filter) {
        List<JourneyDisplayDTO> journeys = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            LOGGER.trace(filter.toSQLQuery());
            ResultSet resultSet = statement.executeQuery(filter.toSQLQuery());
            while (resultSet.next()) {
                journeys.add(new JourneyDisplayDTO().setJourney(extract(resultSet)).setCountCar(resultSet.getInt(12)).setIdDriver(resultSet.getInt(13)));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Get journeys", e);
        }
        return journeys;
    }

    @Override
    public int getCountPages(FilterJourney filter) {
        int count = 0;
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            LOGGER.trace(filter.toSQLQueryCount());
            ResultSet resultSet = statement.executeQuery(filter.toSQLQueryCount());
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Get count pages journeys ", e);
        }
        return (count % filter.getCountRows() == 0) ? (count / filter.getCountRows()) : (count / filter.getCountRows() + 1);
    }

    @Override
    public int create(Journey entity) {
        int id = 0;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_JOURNEY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, entity.getPrice());
            preparedStatement.setFloat(2, entity.getWeight());
            preparedStatement.setString(3, entity.getMaterial().getMaterial());
            preparedStatement.setFloat(4, entity.getVolume());
            preparedStatement.setInt(5, entity.getIdManager());
            preparedStatement.setString(6, entity.getFrom());
            preparedStatement.setString(7, entity.getWhere());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public Optional<Journey> read(int id) {
        Optional<Journey> journey = Optional.empty();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_JOURNEY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                journey = Optional.of(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return journey;
    }

    @Override
    public boolean update(Journey entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_JOURNEY)) {
            preparedStatement.setString(1, entity.getStatus().getStatus());
            preparedStatement.setFloat(2, entity.getPrice());
            preparedStatement.setFloat(3, entity.getWeight());
            preparedStatement.setString(4, entity.getMaterial().getMaterial());
            preparedStatement.setFloat(5, entity.getVolume());
            preparedStatement.setString(6, entity.getFrom());
            preparedStatement.setString(7, entity.getWhere());
            preparedStatement.setInt(8, entity.getId());
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(Journey entity) {
        return false;
    }

    private Journey extract(ResultSet resultSet) throws SQLException {
        Journey journey = new Journey();
        journey.setId(resultSet.getInt("id"));
        journey.setDate(Util.getCalendarByDate(resultSet.getTimestamp("date")));
        journey.setStatus(StatusJourney.valueOf(resultSet.getString("status").toUpperCase()));
        journey.setPrice(resultSet.getFloat("price"));
        journey.setWeight(resultSet.getFloat("weight"));
        journey.setMaterial(Material.valueOf(resultSet.getString("type").toUpperCase()));
        journey.setVolume(resultSet.getFloat("volume"));
        journey.setIdManager(resultSet.getInt("id_manager"));
        if (!Objects.isNull(resultSet.getTimestamp("date_finish"))) {
            journey.setDateFinish(Util.getCalendarByDate(resultSet.getTimestamp("date_finish")));
        } else {
            journey.setDateFinish(null);
        }
        journey.setFrom(resultSet.getString("from"));
        journey.setWhere(resultSet.getString("where"));
        return journey;
    }


}
