package ua.nure.antoniuk.db.transaction;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(Transaction<T> transaction) {
        Connection connection = null;
        T result = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            ConnectionManager.setConnection(connection);
            result = transaction.execute();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            rollback(connection);
        } finally {
            close(connection);
        }
        return result;
    }

    private void close(AutoCloseable ac) {
        try {
            if (ac != null) {
                ac.close();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
