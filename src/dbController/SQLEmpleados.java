package dbController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cargo;
import model.Empleados;
import model.Zona;

public class SQLEmpleados {
	
	private static Connection c;
	
	public static void obtenerInfo() throws SQLException{
		Connection c =Conexion.openConnection();
	//  SQLSelect
		
		printEmpleados(c);
		
		Conexion.closeConnection(c);
		
	}
	
	public static void insertarDatos() throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLInsert
		
		System.out.println("Introduzca información del empleado:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre: ");
		String nombre = reader.readLine();
		System.out.print("Cargo: ");
		String cargo = reader.readLine();
		int cargo_id = SQLCargo.getId(cargo);
		System.out.print("Zona: ");
		String zona = reader.readLine();
		int zona_id = SQLZona.getId(zona);
		System.out.print("Sueldo: ");
		int sueldo = Integer.parseInt(reader.readLine());
		
		insert(nombre, cargo_id, zona_id, sueldo);
		
		
		
		
			Conexion.closeConnection(c);
			
	}
	
	public static void buscarDatos() throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLSearch
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre empleado: ");
		String searchName = reader.readLine();
		
		search(searchName);
		
			Conexion.closeConnection(c);
			
	}
	
	public static void actualizarDatos() throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLUpdate
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Elija a un empleado, introduzca su ID: ");
		printEmpleados(c);
		int empleado_id = Integer.parseInt(reader.readLine());
		System.out.print("fije el nuevo sueldo del empleado: ");
		int nuevoSueldo = Integer.parseInt(reader.readLine());
		String sql = "UPDATE Empleados SET sueldo =? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, nuevoSueldo);
		prep.setInt(2, empleado_id);
		prep.executeUpdate();
		System.out.println("\nActualizacion de saldo realizada");
		
			Conexion.closeConnection(c);
			
	}
	
	public static void borrarTabla() throws SQLException {
		
		Connection c =Conexion.openConnection();
		
		Statement stmt1 = c.createStatement();
		String sql1 = "Drop table Empleados" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nElija un empleado a borrar, teclee su ID: ");
		printEmpleados(c);
		int cargo_id = Integer.parseInt(reader.readLine());
		String sql = "DELETE FROM Empleados WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, cargo_id);
		prep.executeUpdate();
		System.out.println("\nBorrado completado");
			Conexion.closeConnection(c);
			
	}
	
	
	private static void printEmpleados(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Empleados";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs != null) {
		while (rs.next()) {
			int id = rs.getInt("Id");
			String nombre = rs.getString("Nombre");
			int cargo_id = rs.getInt("Cargo_id");	
			int zona_id = rs.getInt("Zona_id");
			int sueldo = rs.getInt("Sueldo");
			Empleados empleado = new Empleados(id, nombre, cargo_id, zona_id, sueldo);
			System.out.println(empleado);
		}
		}
		rs.close();
		stmt.close();
	}
	
	private static Cargo getCargos(int cargo_id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Cargos WHERE id = "+ cargo_id;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int id = rs.getInt("Id");
		String nombre = rs.getString("Nombre");
		Cargo cargo = new Cargo(id, nombre);
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

	public static void insert(String nombre, int cargo_id, int zona_id, int sueldo ) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Empleados (Nombre, Cargo_id, Zona_id, Sueldo, Puesto_id, Atraccion_id) "
				+ "VALUES ('" + nombre + "', '" + cargo_id	+ "', '" + zona_id + "', '" + sueldo + "', '"  +"');";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public static void search(String searchname) throws SQLException {
		String sql = "SELECT * FROM Empleados Where nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, searchname);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Nombre");
			System.out.println(id + ": "+ name);
		}
		}else {
			System.out.println("No hubo resultados");
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		System.out.println("Busqueda Completada");
	}
	
}
