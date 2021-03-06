package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCargos {
	
	//le pasas el id del cargo y te devuelve el nombre
	public static String getNombreCargo(int id, Connection c) throws SQLException {
		String nombre = null;
		
		String sql = "SELECT Nombre FROM Cargos Where id LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {

			 nombre = rs.getString("Nombre");
		}
		
		}else {
			System.out.println("No hubo resultados");
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		
		return nombre;
		
	}
	
public static int getId(String nombreCargo, Connection c) throws SQLException {
		int id =0;
		String sql = "SELECT id FROM Cargos Where Nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, nombreCargo);
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
		SQLCargos.printCargos(c);
		Conexion.closeConnection(c);
		
	}
	
	public static void insertarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLInsert
		
			Conexion.closeConnection(c);
			
	}
	
	public static void buscarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLSearch
			Conexion.closeConnection(c);
			
	}
	
	public static void actualizarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLUpdate
			Conexion.closeConnection(c);
			
	}
	
	public static void borrarTabla() throws SQLException {
		
		Connection c =Conexion.openConnection();
		
		Statement stmt1 = c.createStatement();
		String sql1 = "Drop table Cargos" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		System.out.println("\nTabla Cargo borrada");
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
			Conexion.closeConnection(c);
			
	}

	public static void printCargos(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Cargos";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String nombre = rs.getString("Nombre");
			System.out.println("id: " + id + " Nombre: "+ nombre);
		}
		rs.close();
		stmt.close();
	}

}