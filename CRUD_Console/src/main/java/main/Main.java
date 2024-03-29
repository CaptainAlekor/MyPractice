package main;

import tools.CRUDAssistant;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (CRUDAssistant crud = new CRUDAssistant()) {
            crud.readAllUsers();
        } catch (SQLException e) {
            CRUDAssistant.handleSQLException(e);
        }
    }
}