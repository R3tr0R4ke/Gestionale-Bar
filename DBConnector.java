import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static final String url = "jdbc:mysql://localhost:3306/gestionalebar";
	private static final String username = "root";
	private static final String password = "SESSOPAZZO39!";
	
	public static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
