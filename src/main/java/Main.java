import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String PROPERTIES_PATH = "C:\\Users\\saton\\Desktop\\lanit_team_project\\src\\main\\resources\\DaoProperties.properties";
    private static Connection connection;

    private static final String TABLE = "organizations";
    private static final String GET_ALL_USER = "SELECT * FROM " + TABLE;

    public static void main(String[] args) {
        connection = JdbcUtils.getConnection(PROPERTIES_PATH);

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USER)) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.closeConnection(connection);
        }
    }


}
