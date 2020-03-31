package dbController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLOfertas {

	
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
		String sql1 = "Drop table Ofertas" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		System.out.println("\nTabla Ofertas borrada");
		
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
			Conexion.closeConnection(c);
			
	}
	
	
	
	
}
