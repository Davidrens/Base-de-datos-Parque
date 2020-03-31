package dbController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Atracciones;


public class SQLAtracciones {
	
	
public static String getNombreAtraccion(int id,Connection c) throws SQLException {
		
		
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
		
		return null;
		
	}

public static int getId(String nombreAtraccion,Connection c) throws SQLException {
	int id = 0;
	
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
	return id;
}

	
	
	public static void obtenerInfo() throws SQLException{
		Connection c =Conexion.openConnection();
	//  SQLSelect
		printAtracciones(c);
		
		Conexion.closeConnection(c);
		
	}
	
	public static void insertarDatos(Atracciones atraccion) throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLInsert
		insert(atraccion,c);
			Conexion.closeConnection(c);
			
	}
	
	public static void buscarDatos() throws SQLException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLSearch
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nNombre atracción: ");
		String searchName = reader.readLine();
		search(searchName,c);
		
			Conexion.closeConnection(c);
			
	}
	
	public static void actualizarDatos() throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLUpdate
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nElija una atracción, introduzca su ID: ");
		printAtracciones(c);
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
	
	/*public static Atracciones search(String searchname, Connection c) throws SQLException {
		Atracciones atraccion= null;
		String sql = "SELECT * FROM Atracciones Where nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, searchname);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Nombre");
			int zona_id = rs.getInt("Zona_id");
			atraccion = new Atracciones(name, zona_id);
			atraccion.setId(id);
			atraccion.setEspera(c);
			String sql2 = "UPDATE Atracciones SET espera ='"+atraccion.getEspera()+"' WHERE id='"+ atraccion.getId() +"' ";
			PreparedStatement prep2 =c.prepareStatement(sql2);
			prep2.executeUpdate();
			
		}
		}else {
			System.out.println("No hubo resultados");
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		System.out.println("Busqueda Completada");
		return atraccion;
	}
	*/
	public static void search(String searchname, Connection c) throws SQLException {
		String sql = "SELECT * FROM Atracciones Where nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, searchname);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Nombre");
			int zona_id = rs.getInt("Zona_id");
			String nombreZona = SQLZonas.getNombreZona(zona_id, c);
			int espera =rs.getInt("Espera");
			System.out.println("Id: " + id + "\nNombre: "+ name +
					"\nZona: " + nombreZona + "\nEspera: " + espera);
			
			
		}
		}else {
			System.out.println("No hubo resultados");
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		System.out.println("Busqueda Completada");
		
	}
	
	
	public static void insert(Atracciones atraccion, Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Atracciones (Nombre, Zona_id, Espera) "
				+ "VALUES ('" + atraccion.getNombre() + "', '" + atraccion.getZona_id()	+ "', '" + atraccion.getEspera() + "');";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public static void printAtracciones(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Atracciones";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String nombre = rs.getString("Nombre");	
			int zona_id = rs.getInt("Zona_id");
			String nombreZona = SQLZonas.getNombreZona(zona_id,c);
			int espera = rs.getInt("Espera");
			System.out.println("id: " + id + " Nombre: "+ nombre + " Zona: "+ nombreZona+ " Espera: "+espera);
		}
		rs.close();
		stmt.close();
	}
	

}