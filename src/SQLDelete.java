import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cargos;
import model.Empleados;
import model.Zona;

public class SQLDelete {


	// Put connection here so it can be used in several methods
	private static Connection c;

	private static void printEmpleados() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Empleados";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String nombre = rs.getString("Nombre");
			Cargos cargo_id = getCargos(rs.getInt("Cargo_id"));	
			Zona zona_id = getZonas(rs.getInt("Zona_id"));
			Integer sueldo = rs.getInt("Sueldo");
			Empleados empleado = new Empleados(id, nombre, cargo_id, zona_id, sueldo);
			System.out.println(empleado);
		}
		rs.close();
		stmt.close();
	}
	
	private static Cargos getCargos(int cargo_id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Cargos WHERE id = "+ cargo_id;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int id = rs.getInt("Id");
		String nombre = rs.getString("Nombre");
		Cargos cargo = new Cargos(id, nombre);
		rs.close();
		stmt.close();
		return cargo;
	}
	
	private static Zona getZonas(int zona_id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Zonas WHERE id = "+ zona_id;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int id = rs.getInt("Id");
		String nombre = rs.getString("Nombre");
		Zona zona = new Zona(id, nombre);
		rs.close();
		stmt.close();
		return zona;
	}


	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			// Note that we are using the class' connection
			c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Remove an employee: beginning
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose an employee to delete, type its ID: ");
			printEmpleados();
			int cargo_id = Integer.parseInt(reader.readLine());
			String sql = "DELETE FROM Empleados WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, cargo_id);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
			// Remove an employee: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
