package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.CarDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.Car;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CarDAOImpl implements CarDAO {
    private static final Logger LOGGER = Logger.getLogger(CarDAOImpl.class);
    private static final String ADD_CAR = "INSERT INTO cars (number, mark, model, type_bodywork, max_weight, max_volume, id_driver) VALUES (?,?,?,?,?,?,?)";

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
        return Optional.empty();
    }

    @Override
    public boolean update(Car entity) {
        return false;
    }

    @Override
    public boolean delete(Car entity) {
        return false;
    }
}