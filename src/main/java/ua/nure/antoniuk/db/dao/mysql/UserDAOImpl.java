package ua.nure.antoniuk.db.dao.mysql;

import org.apache.log4j.Logger;
import ua.nure.antoniuk.db.dao.UserDAO;
import ua.nure.antoniuk.db.transaction.ConnectionManager;
import ua.nure.antoniuk.entity.User;
import ua.nure.antoniuk.util.Util;

import java.sql.*;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    private static final String ADD_USER = "INSERT INTO users (name, surname, email, password, role, photo, phone) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ? LIMIT 1";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET email=?, phone=?, photo=?, password=?, salary=? WHERE id=?";
    private static final String CALL_TRY_TO_LOGIN = "{? = call try_to_login(?,?)}";

    @Override
    public Optional<User> getByEmail(String email) {
        Connection connection = ConnectionManager.getConnection();
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                optionalUser = Optional.of(extractUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return optionalUser;
    }

    @Override
    public int tryToLogin(String email, String password) {
        Connection connection = ConnectionManager.getConnection();
        int code;
        try (CallableStatement callableStatement = connection.prepareCall(CALL_TRY_TO_LOGIN)) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, email);
            callableStatement.setString(3, password);
            callableStatement.execute();
            code = callableStatement.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return code;
    }

    @Override
    public int create(User entity) {
        Connection connection = ConnectionManager.getConnection();
        int id = 0;
        try (PreparedStatement prst = connection.prepareStatement(ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            introduceUser(entity, prst);
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
    public Optional<User> read(int id) {
        Connection connection = ConnectionManager.getConnection();
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                optionalUser = Optional.of(extractUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean update(User entity) {
        Connection connection = ConnectionManager.getConnection();
        boolean res;
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPhone());
            ps.setString(3, entity.getImgPath());
            ps.setString(4, entity.getPassword());
            ps.setFloat(5, entity.getSalary());
            res = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException();
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getNString("name"));
        user.setLastname(resultSet.getNString("surname"));
        user.setEmail(resultSet.getNString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setRole(Util.getRole(resultSet.getNString("role")));
        user.setSalary(resultSet.getFloat("salary"));
        user.setImgPath(resultSet.getString("photo"));
        user.setId(resultSet.getInt("id"));
        user.setDateReg(Util.getCalendarByDate(resultSet.getDate("datareg")));
        return user;
    }

    private void introduceUser(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, Util.hash(user.getPassword()));
        preparedStatement.setString(5, user.getRole().getRole());
        preparedStatement.setString(6, user.getImgPath());
        preparedStatement.setString(7, user.getPhone());
    }
}
