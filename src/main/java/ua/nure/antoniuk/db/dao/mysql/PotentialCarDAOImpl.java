package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.util.Bodywork;
import ua.nure.antoniuk.util.Extractor;
import ua.nure.antoniuk.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class PotentialCarDAOImpl implements PotentialCarDAO {
    private final static Logger LOGGER = Logger.getLogger(PotentialCarDAOImpl.class);
    private static final String ADD_POTENTIAL_CAR = "INSERT INTO potential_cars (number, photo, max_weight, max_volume, users_id, potential_users_id, type_bodywork, mark, model) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String GET_BY_ID_DRIVER = "SELECT * FROM potential_cars WHERE potential_users_id = ? LIMIT 1";

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
        return Optional.empty();
    }

    @Override
    public boolean update(PotentialCar entity) {
        throw new UnsupportedOperationException("You can`t update potential users, only delete");
    }

    @Override
    public boolean delete(PotentialCar entity) {
        return false;
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
