package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.exceptions.DBException;
import ua.nure.antoniuk.util.Bodywork;
import ua.nure.antoniuk.util.Extractor;
import ua.nure.antoniuk.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PotentialCarDAOImpl implements PotentialCarDAO {
    private final static Logger LOGGER = Logger.getLogger(PotentialCarDAOImpl.class);
    private static final String ADD_POTENTIAL_CAR = "INSERT INTO potential_cars (number, photo, max_weight, max_volume, users_id, potential_users_id, type_bodywork, mark, model) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String GET_BY_ID_DRIVER = "SELECT * FROM potential_cars WHERE potential_users_id = ? LIMIT 1";
    private static final String DELETE_POTENTIAL_CAR = "DELETE FROM potential_cars WHERE potential_users_id = ?";
    private static final String IS_EXIST_NUMBER = "SELECT * FROM potential_cars WHERE number = ? LIMIT 1";
    private static final String GET_ALL = "SELECT * FROM potential_cars WHERE users_id > 0";
    private static final String GET_BY_ID = "SELECT * FROM potential_cars WHERE id = ? LIMIT 1";
    private static final String DELETE_POTENTIAL_CAR_BY_ID = "DELETE FROM potential_cars WHERE id = ?";
    @Override
    public int create(PotentialCar entity) {
        Connection connection = ConnectionManager.getConnection();
        int id = 0;
        try (PreparedStatement prst = connection.prepareStatement(ADD_POTENTIAL_CAR, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, entity.getNumber());
            prst.setString(2, entity.getPathImg());
            prst.setFloat(3, entity.getMaxWeight());
            prst.setFloat(4, entity.getMaxVolume());
            prst.setObject(5, (entity.getIdUser() > 0) ? entity.getIdUser() : null);
            prst.setObject(6, (entity.getIdPotentialUser() > 0) ? entity.getIdPotentialUser() : null);
            prst.setString(7, entity.getBodywork().getBodywork());
            prst.setString(8, entity.getMark());
            prst.setString(9, entity.getModel());
            prst.executeUpdate();
            ResultSet rs = prst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public Optional<PotentialCar> read(int id) {
        Optional<PotentialCar> potentialCar = Optional.empty();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                potentialCar = Optional.of(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Potential car read " + id, e);
        }
        return potentialCar;
    }

    @Override
    public boolean update(PotentialCar entity) {
        throw new UnsupportedOperationException("You can`t update potential users, only delete");
    }

    @Override
    public boolean delete(PotentialCar entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POTENTIAL_CAR)) {
            preparedStatement.setInt(1, (entity.getIdPotentialUser() + entity.getIdUser()));
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
    public Optional<PotentialCar> getByIdDriver(int idDriver) {
        Connection connection = ConnectionManager.getConnection();
        Optional<PotentialCar> car = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_DRIVER)) {
            preparedStatement.setInt(1, idDriver);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = Optional.of(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return car;
    }

    @Override
    public boolean isExistCarByIdPotentialUser(PotentialUser user) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_DRIVER)) {
            preparedStatement.setInt(1, user.getId());
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
    public boolean isExist(String carNumber) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST_NUMBER)) {
            preparedStatement.setString(1, carNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Car isExist " + carNumber, e);
        }
        return result;
    }


    @Override
    public List<PotentialCar> getAll() {
        List<PotentialCar> potentialCars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                potentialCars.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Get all potential car", e);
        }
        return potentialCars;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_POTENTIAL_CAR_BY_ID)) {
            preparedStatement.setInt(1, (id));
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public PotentialCar extract(ResultSet value) throws SQLException {
        PotentialCar potentialCar = new PotentialCar();
        potentialCar.setId(value.getInt(1));
        potentialCar.setNumber(value.getString(2));
        potentialCar.setPathImg(StringUtil.stringOrEmptyString(value.getString(3)));
        potentialCar.setMaxWeight(value.getFloat(4));
        potentialCar.setIdUser(Objects.isNull(value.getInt(5)) ? 0 : value.getInt(5));
        potentialCar.setIdPotentialUser((Objects.isNull(value.getInt(6)) ? 0 :value.getInt(6)));
        potentialCar.setBodywork(Bodywork.valueOf(value.getString(7).toUpperCase()));
        potentialCar.setMaxVolume(value.getFloat(8));
        potentialCar.setMark(value.getString(9));
        potentialCar.setModel(value.getString(10));
        return potentialCar;
    }

}
