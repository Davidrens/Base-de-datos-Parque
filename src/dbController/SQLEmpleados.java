package dbController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cargos;
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
	
	public static void insertarDatos(Empleados empleado) throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLInsert
	
		insert(empleado,c);
		Conexion.closeConnection(c);
			
	}
	
	/*public static void buscarDatos() throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLSearch
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre empleado: ");
		String searchName = reader.readLine();
		
		search(searchName);
		
			Conexion.closeConnection(c);
			
	}*/
	
	public static void actualizarDatos(int id, int sueldo) throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		String sql = "UPDATE Empleados SET sueldo =? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, sueldo);
		prep.setInt(2, id);
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
		System.out.println("\nTabla Empleados borrada");
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos(int id) throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		String sql = "DELETE FROM Empleados WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
		System.out.println("\nBorrado completado");
			Conexion.closeConnection(c);
			
	}
	
	
	public static void printEmpleados(Connection c) throws SQLException {
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
			String nombreZona = SQLZonas.getNombreZona(zona_id, c);
			String nombreCargo = SQLCargos.getNombreCargo(cargo_id,c);
			Empleados empleado = new Empleados(nombre, cargo_id, zona_id, sueldo);
			empleado.setId(id);
			System.out.println(empleado + "    nombre_zona: "+ nombreZona+"    nombre_cargo: "+nombreCargo);
		}
		}
		rs.close();
		stmt.close();
	}
	
	public static Cargos getCargos(int cargo_id, Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Cargos WHERE id = "+ cargo_id;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int id = rs.getInt("Id");
		String nombre = rs.getString("Nombre");
		Cargos cargo = new Cargos( nombre);
		cargo.setId(id);
		rs.close();
		stmt.close();
		return cargo;
	}
	
	public static Zona getZonas(int zona_id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Zonas WHERE id = "+ zona_id;
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int id = rs.getInt("Id");
		String nombre = rs.getString("Nombre");
		Zona zona = new Zona(nombre);
		zona.setId(id);
		rs.close();
		stmt.close();
		return zona;
	}

	public static void insert(Empleados empleado,Connection c ) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Empleados (Nombre, Cargo_id, Zona_id, Sueldo) "
				+ "VALUES ('" + empleado.getNombre() + "', '" + empleado.getCargo_id()	+ "', '" + empleado.getZona_id()+ "', '" + empleado.getSueldo() + "');";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public static void search(String searchname) throws SQLException {
		Connection c =Conexion.openConnection();
		String sql = "SELECT * FROM Empleados Where nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, searchname);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Nombre");
			int cargo_id = rs.getInt("Cargo_id");
			String nombreCargo = SQLCargos.getNombreCargo(cargo_id, c);
			int zona_id = rs.getInt("Zona_id");
			String nombreZona = SQLZonas.getNombreZona(zona_id, c);
			int sueldo = rs.getInt("Sueldo");
			System.out.println("Id: " + id + "\nNombre: "+ name + "\nCargo: " + nombreCargo +
					"\nZona: " + nombreZona + "\nSueldo: " + sueldo);
		}
		}else {
			System.out.println("No hubo resultados");
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		System.out.println("Busqueda Completada");
		Conexion.closeConnection(c);
	}
	
}