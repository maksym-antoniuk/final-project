package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.PotentialCarDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.PotentialCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PotentialCarDAOImpl implements PotentialCarDAO {
    private final static Logger LOGGER = Logger.getLogger(PotentialCarDAOImpl.class);
    private static final String ADD_POTENTIAL_CAR = "INSERT INTO potential_cars (number, photo, max_weight, max_volume, users_id, potential_users_id, type_bodywork, mark, model) VALUES (?,?,?,?,?,?,?,?,?)";

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
}
