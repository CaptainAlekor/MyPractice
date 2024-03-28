import tools.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try (DBHelper db = new DBHelper();
             PreparedStatement statement = db.getConnection().prepareStatement("select * from test_table;");
        ) {
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                System.out.println(result.getInt("value"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}