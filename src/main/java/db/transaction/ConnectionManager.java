package db.transaction;

import java.sql.Connection;

public class ConnectionManager {

    private static ThreadLocal<Connection> connections = new ThreadLocal<>();

    public static Connection getConnection() {
        return connections.get();
    }

    public static void setConnection(Connection connection) {
        connections.set(connection);
    }
}
