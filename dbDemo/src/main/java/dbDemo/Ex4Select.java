package dbDemo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.iapi.error.StandardException;

public class Ex4Select {
	private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_CONNECTION = "jdbc:derby:ex1connect;";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public static void main(String[] argv) {
		try {
			selectRecordsFromDbUserTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void selectRecordsFromDbUserTable() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(selectTableSQL);
			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			if (rs.next() == false) {
				System.out.println("ResultSet in empty in Java");
			} else {
				do {
					String userid = rs.getString("USER_ID");
					String username = rs.getString("USERNAME");
					System.out.println("userid : " + userid);
					System.out.println("username : " + username);
				} while (rs.next());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			if(e.getCause() instanceof StandardException) {
				System.err.println(e.getCause());
				System.err.println("Most likely connection open already (and new cannot be opened)");
				
			}
			System.exit(0);
		}
		return dbConnection;
	}
}