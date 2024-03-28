package tools;

import io.github.cdimascio.dotenv.Dotenv;
import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper implements AutoCloseable {
    private static final String DB_URL = Dotenv.load().get("DB_URL");
    private static final String DB_USERNAME = Dotenv.load().get("DB_USERNAME");
    private static final String DB_PASSWORD = Dotenv.load().get("DB_PASSWORD");

    private Connection connection;

    public DBHelper() throws Exception {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            if (DB_URL == null || DB_USERNAME == null || DB_PASSWORD == null) {
                throw new Exception("Missing one or more database connection parameters");
            }

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
