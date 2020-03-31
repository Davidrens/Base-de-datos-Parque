package dbController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLAtracciones {
	
	private static Connection c;
	
public static String getNombreAtraccion(int id) throws SQLException {
		
		Connection c =Conexion.openConnection();
		
		String sql = "SELECT Nombre FROM Atracciones Where id LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {

			String nombre = rs.getString("Nombre");
			return nombre;
		}
		
		}else {
			System.out.println("No hubo resultados");
			return null;
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		
		Conexion.closeConnection(c);
		return null;
		
	}

public static int getId(String nombreAtraccion) throws SQLException {
	int id = 0;
	Connection c =Conexion.openConnection();
	
	String sql = "SELECT id FROM Atracciones Where Nombre LIKE ? ";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, nombreAtraccion);
	ResultSet rs = prep.executeQuery();
	if(rs != null) {
	while(rs.next()) {

		 id =  rs.getInt("Id");
	}
	
	}else {
		System.out.println("No hubo resultados");
	}
	
	// CLOSE Statement
	rs.close();
	prep.close();
	Conexion.closeConnection(c);
	return id;
}

	
	
	public static void obtenerInfo() throws SQLException{
		Connection c =Conexion.openConnection();
	//  SQLSelect
		printAtracciones();
		
		Conexion.closeConnection(c);
		
	}
	
	public static void insertarDatos() throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLInsert
		
		System.out.println("Introduzca información del empleado:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre: ");
		String nombre = reader.readLine();
		System.out.print("Zona: ");
		String zona = reader.readLine();
		int zona_id = SQLZona.getId(zona);
		System.out.print("Espera: ");
		int espera = Integer.parseInt(reader.readLine());
		insert(nombre, zona_id, espera);
			Conexion.closeConnection(c);
			
	}
	
	public static void buscarDatos() throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLSearch
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nNombre atracción: ");
		String searchName = reader.readLine();
		search(searchName);
		
			Conexion.closeConnection(c);
			
	}
	
	public static void actualizarDatos() throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLUpdate
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nElija una atracción, introduzca su ID: ");
		printAtracciones();
		int atraccion_id = Integer.parseInt(reader.readLine());
		System.out.print("\nfije el nuevo tiempo de espera de la atracción: ");
		int nuevaEspera = Integer.parseInt(reader.readLine());
		String sql = "UPDATE Atracciones SET Espera =? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, nuevaEspera);
		prep.setInt(2, atraccion_id);
		prep.executeUpdate();
		System.out.println("\nActualizacion de tiempo de espera realizada");
		
		
			Conexion.closeConnection(c);
			
	}
	
	public static void borrarTabla() throws SQLException {
		
		Connection c =Conexion.openConnection();
		
		Statement stmt1 = c.createStatement();
		String sql1 = "Drop table Atracciones" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		System.out.println("\nTabla Atracciones borrada");
		
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
			Conexion.closeConnection(c);
			
	}
	
	public static void search(String searchname) throws SQLException {
		String sql = "SELECT * FROM Atracciones Where nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, searchname);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Nombre");
			int zona_id = rs.getInt("Zona_id");
			String zona = SQLZona.getNombreZona(zona_id);
			int espera = rs.getInt("Espera");
			System.out.println("\nId: " + id + "\nNombre: "+ name + "\nZona: " + zona + "\nEspera" + espera);
		}
		}else {
			System.out.println("No hubo resultados");
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		System.out.println("Busqueda Completada");
	}
	
	public static void insert(String nombre, int zona_id, int espera ) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Empleados (Nombre, Zona_id, Espera) "
				+ "VALUES ('" + nombre + "', '" + zona_id	+ "', '" + espera + "', '"  +"');";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	private static void printAtracciones() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Atracciones";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String nombre = rs.getString("Nombre");	
			int zona_id = rs.getInt("Zona_id");
			String nombreZona = SQLZona.getNombreZona(zona_id);
			int espera = rs.getInt("Espera");
			System.out.println("id: " + id + "\nNombre: "+ nombre + "\nZona:"+ nombreZona + "\nEspera: " + espera);
		}
		rs.close();
		stmt.close();
	}
	

}
