package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLZonas {
	
	
public static String getNombreZona(int id,Connection c) throws SQLException {
	
		String nombre ="";
		String sql = "SELECT Nombre FROM Zonas Where id LIKE ? ";
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

public static int getId(String nombreZona, Connection c) throws SQLException {
	int id = 0;
	String sql = "SELECT Id FROM Zonas Where Nombre LIKE ? ";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, nombreZona);
	ResultSet rs = prep.executeQuery();
	if(rs != null) {
	while(rs.next()) {

		 id = rs.getInt("Id");
		return id;
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
	//  SQLPrint
		SQLZonas.printZonas(c);
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
		String sql1 = "Drop table Zonas" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		System.out.println("\nTabla Zona borrada");
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
			Conexion.closeConnection(c);
			
	}
	public static void printZonas(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Zonas";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String tipo = rs.getString("Nombre");
			System.out.println("id: " + id + " Nombre: "+ tipo);
		}
		rs.close();
		stmt.close();
	}

}