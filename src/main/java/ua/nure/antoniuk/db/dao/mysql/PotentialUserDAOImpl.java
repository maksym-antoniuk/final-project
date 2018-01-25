package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.PotentialUserDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.PotentialCar;
import ua.nure.antoniuk.entity.PotentialUser;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PotentialUserDAOImpl implements PotentialUserDAO {
    private final static Logger LOGGER = Logger.getLogger(PotentialUserDAOImpl.class);

    private static final String ADD_POTENTIAL_USER = "INSERT INTO potential_users (name, surname, email, role, phone) VALUES (?,?,?,?,?)";
    private static final String GET_POTENTIAL_USER_BY_EMAIL = "SELECT * FROM potential_users WHERE email=? LIMIT 1";
    private static final String GET_POTENTIAL_USER_BY_ID = "SELECT * FROM potential_users WHERE id=?";
    private static final String GET_ALL_POTENTIAL_USERS = "SELECT * FROM potential_users";

    @Override
    public Optional<PotentialUser> getByEmail(String email) {
        Connection connection = ConnectionManager.getConnection();
        Optional<PotentialUser> optionalUser = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(GET_POTENTIAL_USER_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                optionalUser = Optional.of(extractPotentialUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return optionalUser;
    }

    @Override
    public List<PotentialUser> getAll() {
        List<PotentialUser> users = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_POTENTIAL_USERS);
            while (resultSet.next()) {
                users.add(extractPotentialUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public int create(PotentialUser entity) {
        Connection connection = ConnectionManager.getConnection();
        int id = 0;
        try (PreparedStatement prst = connection.prepareStatement(ADD_POTENTIAL_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prst.setString(1, entity.getName());
            prst.setString(2, entity.getLastname());
            prst.setString(3, entity.getEmail());
            prst.setString(4, entity.getRole().getRole());
            prst.setString(5, entity.getPhone());
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
    public Optional<PotentialUser> read(int id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<PotentialUser> optionalUser = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(GET_POTENTIAL_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                optionalUser = Optional.of(extractPotentialUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean update(PotentialUser entity) {
        throw new UnsupportedOperationException("You can`t update potential users");
    }

    @Override
    public boolean delete(PotentialUser entity) {
        return false;
    }

    private PotentialUser extractPotentialUser(ResultSet resultSet) throws SQLException {
        PotentialUser user = new PotentialUser();
        user.setName(resultSet.getString("name"));
        user.setLastname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setRole(Util.getRole(resultSet.getString("role")));
        user.setId(resultSet.getInt("id"));
        return user;
    }
}
