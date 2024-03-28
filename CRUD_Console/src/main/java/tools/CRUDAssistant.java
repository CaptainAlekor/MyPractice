package tools;

import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CRUDAssistant implements AutoCloseable {
    private final DBHelper dbHelper;

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


    public CRUDAssistant() {
        dbHelper = new DBHelper();
    }

    public static void handleSQLException(SQLException e) {
        System.out.println("SQL error occurred");
        System.out.println("Info: " + e.getMessage());
        System.out.println("Stacktrace: " + Arrays.toString(e.getStackTrace()));
    }

    public void createUser(String email, String password) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("insert into users (email, password) values (?, ?);")) {
            statement.setString(1, email);
            statement.setString(2, password);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public List<User> readAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("select id, email, password from users;")) {
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                users.add(new User(
                        result.getInt(ID),
                        result.getString(EMAIL),
                        result.getString(PASSWORD)
                ));
            }

            return users;
        } catch (SQLException e) {
            handleSQLException(e);
            return users;
        }
    }
    public User readUserById(int id) {
        User user = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("select id, email, password from users where id = ?;")) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(
                        result.getInt(ID),
                        result.getString(EMAIL),
                        result.getString(PASSWORD));
            }

            return user;
        } catch (SQLException e) {
            handleSQLException(e);
            return user;
        }
    }
    public User readUserByEmail(String email) {
        User user = null;
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("select id, email, password from users where email = ?;")) {
            statement.setString(1, email);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(
                        result.getInt(ID),
                        result.getString(EMAIL),
                        result.getString(PASSWORD));
            }

            return user;
        } catch (SQLException e) {
            handleSQLException(e);
            return user;
        }
    }
    public void updateUserById(int id, String email, String password) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("update users set email = ?, password = ? where id = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateUserEmailById(int id, String email) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("update users set email = ? where id = ?")) {
            statement.setString(1, email);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void updateUserPasswordById(int id, String password) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("update users set password = ? where id = ?")) {
            statement.setString(1, password);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public void deleteUserById(int id) {
        try (PreparedStatement statement = dbHelper.getConnection().prepareStatement("delete from users where id = ?")) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }


    @Override
    public void close() throws SQLException {
        dbHelper.close();
    }
}
