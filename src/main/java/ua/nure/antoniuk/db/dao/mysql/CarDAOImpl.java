package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.CarDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.dto.CarGarageDTO;
import ua.nure.antoniuk.entity.Car;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.exceptions.DBException;
import ua.nure.antoniuk.util.Bodywork;
import ua.nure.antoniuk.util.StatusCar;
import ua.nure.antoniuk.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAOImpl implements CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAOImpl.class);
    private static final String ADD_CAR = "INSERT INTO cars (number, mark, model, type_bodywork, max_weight, max_volume, id_driver) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_ALL_CAR = "SELECT * FROM cars";
    private static final String GET_DRIVERS_CARS = "SELECT * FROM cars WHERE id_driver = ?";
    private static final String IF_REGISTER_ON_JOURNEY = "SELECT * FROM journeys_has_cars WHERE journeys_id = ? AND cars_id = ? LIMIT 1";
    private static final String IS_ON_ROAD = "SELECT * FROM cars INNER JOIN journeys_has_cars car ON cars.id = car.cars_id INNER JOIN journeys j ON car.journeys_id = j.id WHERE car.cars_id = ? AND car.accept = 'yes' AND j.status = 'on_process'";
    private static final String GET_CAR_BY_ID = "SELECT * FROM cars WHERE id = ?";
    private static final String GET_JOURNEY_CARS = "SELECT * FROM cars INNER JOIN journeys_has_cars car ON cars.id = car.cars_id WHERE car.journeys_id = ?";
    private static final String GET_GARAGE_BY_ID = "SELECT c.id, c.number, c.mark, c.model, c.photo, c.type_bodywork, c.max_weight, c.max_volume, c.id_driver, c.status, count(jc.accept), 0 FROM final.cars c\n" +
            "LEFT OUTER JOIN final.journeys_has_cars jc ON c.id = jc.cars_id WHERE jc.accept = 'yes' AND c.id_driver = ? GROUP BY jc.cars_id\n" +
            "UNION\n" +
            "SELECT c.id, c.number, c.mark, c.model, c.photo, c.type_bodywork, c.max_weight, c.max_volume, c.id_driver, c.status, 0, count(jc.accept) FROM final.cars c\n" +
            "LEFT OUTER JOIN final.journeys_has_cars jc ON c.id = jc.cars_id WHERE jc.accept = 'no' AND c.id_driver = ? GROUP BY jc.cars_id;";
    private static final String UPDATE_CAR = "UPDATE cars SET max_weight = ?, max_volume = ?, type_bodywork = ?, status = ?, photo = ? WHERE id = ?";

    @Override
    public int create(Car entity) {
        Connection connection = ConnectionManager.getConnection();
        int id = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getNumber());
            preparedStatement.setString(2, entity.getMark());
            preparedStatement.setString(3, entity.getModel());
            preparedStatement.setString(4, entity.getBodywork().getBodywork());
            preparedStatement.setFloat(5, entity.getMaxWeight());
            preparedStatement.setFloat(6, entity.getMaxVolume());
            preparedStatement.setInt(7, entity.getIdDriver());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
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
    public Optional<Car> read(int id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<Car> car = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car = Optional.of(extract(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return car;
    }

    @Override
    public boolean update(Car entity) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR)) {
            preparedStatement.setFloat(1, entity.getMaxWeight());
            preparedStatement.setFloat(2, entity.getMaxVolume());
            preparedStatement.setString(3, entity.getBodywork().getBodywork());
            preparedStatement.setString(4, entity.getStatus().getStatus());
            preparedStatement.setString(5, entity.getPathImg());
            preparedStatement.setInt(6, entity.getId());
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Update car " + entity.getId(), e);
        }
        return result;
    }

    @Override
    public boolean delete(Car entity) {
        return false;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CAR);
            while (resultSet.next()) {
                cars.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByIdDriver(int idDriver) {
        List<Car> cars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_DRIVERS_CARS)) {
            preparedStatement.setInt(1, idDriver);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            //throw new DBException("getCarsByIdDriver " + idDriver, e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByIdJourney(int idJourney) {
        List<Car> cars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_JOURNEY_CARS)) {
            preparedStatement.setInt(1, idJourney);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(extract(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public boolean isRegisteredOnJourney(int journeyid, int carid) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(IF_REGISTER_ON_JOURNEY)) {
            preparedStatement.setInt(1, journeyid);
            preparedStatement.setInt(2, carid);
            if (preparedStatement.executeQuery().next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean isOnRoad(int carid) {
        boolean result = false;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(IS_ON_ROAD)) {
            preparedStatement.setInt(1, carid);
            if (preparedStatement.executeQuery().next()) {
                result = true; }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean isRegisteredOnJourney(int carid) {
        return false;
    }

    @Override
    public List<CarGarageDTO> getGarageById(int id) {
        List<CarGarageDTO> cars = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_GARAGE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(new CarGarageDTO().setCar(extract(resultSet)).setCountPerformed(resultSet.getInt(11)).setCountSubscribed(resultSet.getInt(11) + resultSet.getInt(12)));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Get Garage By id", e);
        }
        return cars;
    }

    private Car extract(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setNumber(resultSet.getString("number"));
        car.setMark(resultSet.getString("mark"));
        car.setModel(resultSet.getString("model"));
        car.setPathImg(StringUtil.stringOrEmptyString(resultSet.getString("photo")));
        car.setBodywork(Bodywork.valueOf(resultSet.getString("type_bodywork").toUpperCase()));
        car.setMaxWeight(resultSet.getFloat("max_weight"));
        car.setMaxVolume(resultSet.getFloat("max_volume"));
        car.setIdDriver(resultSet.getInt("id_driver"));
        car.setStatus(StatusCar.valueOf(resultSet.getString("status").toUpperCase()));
        return car;
    }
}