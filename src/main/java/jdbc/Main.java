package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\DaoProperties.properties";
    private static Connection connection;

    private static final String TABLE = "organizations";
    private static final String GET_ORGANIZATION_BY_ID = "SELECT * FROM " + TABLE + " WHERE id = ?";

    public static void main(String[] args) throws ClassNotFoundException{
        connection = JdbcUtils.getConnection(PROPERTIES_PATH);
        int id =1;

        try (PreparedStatement statement = connection.prepareStatement(GET_ORGANIZATION_BY_ID)) {
            statement.setInt(1, id);
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
