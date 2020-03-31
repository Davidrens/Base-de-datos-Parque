package dbController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCargo {
	
	//le pasas el id del cargo y te devuelve el nombre
	public static String getNombreCargo(int id) throws SQLException {
		
		Connection c =Conexion.openConnection();
		
		String sql = "SELECT Nombre FROM Cargos Where id LIKE ? ";
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
	
public static int getId(String nombreCargo) throws SQLException {
		
		Connection c =Conexion.openConnection();
		
		String sql = "SELECT id FROM Cargos Where Nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, nombreCargo);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {

			int id =  rs.getInt("Id");
			return id;
		}
		
		}else {
			System.out.println("No hubo resultados");
			return 0;
		}
		
		// CLOSE Statement
		rs.close();
		prep.close();
		
		Conexion.closeConnection(c);
		return 0;
		
	}

	
	public static void obtenerInfo() throws SQLException{
		Connection c =Conexion.openConnection();
	//  SQLSelect
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
	

}
