package dbController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Puestos;

public class SQLPuestos {
	
	

public static String getNombrePuesto(int id, Connection c) throws SQLException {
		
		String sql = "SELECT Tipo FROM Puestos Where id LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {

			String tipo = rs.getString("Nombre");
			return tipo;
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

public static int getId(String nombrePuesto,Connection c) throws SQLException {
	int id =0;
	
	String sql = "SELECT id FROM Puestos Where Tipo LIKE ? ";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, nombrePuesto);
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
		printPuestos(c);
		
		
		Conexion.closeConnection(c);
		
	}
	
	public static void insertarDatos(Puestos puesto) throws SQLException{
		Connection c =Conexion.openConnection();
		
		insert(puesto,c);
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
		String sql1 = "Drop table Puestos" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		System.out.println("\nTabla Puestos borrada");
		
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
			Conexion.closeConnection(c);
			
	}
	public static void printPuestos(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Puestos";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("Id");
			String tipo = rs.getString("Tipo");	
			int zona_id = rs.getInt("Zona_id");
			String nombreZona = SQLZonas.getNombreZona(zona_id,c);
			System.out.println("id: " + id + " Nombre: "+ tipo + " Zona:"+ nombreZona);
		}
		rs.close();
		stmt.close();
	}
	
	public static void insert(Puestos puesto, Connection c) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Puestos (Nombre, Zona_id) "
				+ "VALUES ('" + puesto.getNombre() + "', '" + puesto.getZona_id()	+ "');";
		stmt.executeUpdate(sql);
		stmt.close();
	}
	

}