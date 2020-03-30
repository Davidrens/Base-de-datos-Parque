package dbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
	
	public static Connection openConnection() {
		try {
			//open db connection
			Class.forName("org.sqlite.JDBC");
			Connection c =DriverManager.getConnection("JDBC:sqlite:./db/parque.db");
			c.createStatement().execute("Pragma foreign_keys=ON");
			System.out.println("Database connection openned");
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}

	public static void closeConnection(Connection c) throws SQLException {
		
		c.close();
		System.out.println("DB connection closed");
		
	}
	
}
