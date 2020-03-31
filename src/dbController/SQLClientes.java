package dbController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Clientes;

//import model.Cargo;
//import model.Empleados;
//import model.Zona;

public class SQLClientes {

	public static void obtenerInfo() throws SQLException{
		Connection c = Conexion.openConnection();
		
		printClientes(c);

		Conexion.closeConnection(c);
		
	}
	
public static void insertarDatos(Clientes cliente) throws SQLException, IOException{
		
		Connection c =Conexion.openConnection();
		//  SQLInsert
		System.out.println(""+cliente);
		// Insert new record: begin
		if(cliente.getPuesto_id()==0) {
			System.out.println("Acceso al insert de atracciones");
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Clientes (Nombre, Edad, Altura, Fecha_entrada, Fecha_salida, Familia_numerosa,Atraccion_id) "
				+ "VALUES ('" + cliente.getNombre() + "', '" + cliente.getEdad()+ "', '" + cliente.getAltura() + "', '" + cliente.getFechaentrada() + "', '" + cliente.getFechasalida() + "','" + cliente.getNumerosa() +"', '"+cliente.getAtraccion_id()+"' );";
		stmt.executeUpdate(sql);
		stmt.close();
		}else {
			System.out.println("Acceso al insert de puestos");
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO Clientes (Nombre, Edad, Altura, Fecha_entrada, Fecha_salida, Familia_numerosa, Puesto_id) "
					+ "VALUES ('" + cliente.getNombre() + "', ' "+ cliente.getEdad()+ "', '" + cliente.getAltura() + "', '" + cliente.getFechaentrada() + "', '" + cliente.getFechasalida() + "','" + cliente.getNumerosa()+"',' "+ cliente.getPuesto_id()+" ');";
			stmt.executeUpdate(sql);
			stmt.close();
		}
		Conexion.closeConnection(c);
			
	}
	
	public static void buscarDatos() throws SQLException, IOException {
		Connection c =Conexion.openConnection();
		//  SQLSearch

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre cliente: ");
		String searchName = reader.readLine();
		
		
		String sql = "SELECT * FROM Clientes Where nombre LIKE ? ";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, searchName);
		ResultSet rs = prep.executeQuery();
		if(rs != null) {
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Nombre");
			int edad = rs.getInt("Edad");
			int altura = rs.getInt("Altura");
			String fecha_entrada = rs.getString("Fecha_entrada");
			String fecha_salida = rs.getString("Fecha_salida");
			boolean numerosa = rs.getBoolean("Familia_numerosa");
			int puesto_id =  rs.getInt("Puesto_id");
			String nombrePuesto = SQLPuestos.getNombrePuesto(puesto_id,c);
			int atraccion_id =  rs.getInt("Cargo_id");
			String nombreAtraccion = SQLAtracciones.getNombreAtraccion(atraccion_id,c);
			
			
			System.out.println(id + ": "+ name + ", " + edad + ", " + altura + ", " + fecha_entrada
					+ ", " + fecha_salida + ", "+ "familia numerosa: "+ numerosa + ", " + nombrePuesto + ", " + nombreAtraccion);
		}
		}else {
			System.out.println("No hubo resultados");
		}
			
		
		Conexion.closeConnection(c);
			
	}
	
	public static void actualizarDatos() throws SQLException{
		Connection c =Conexion.openConnection();
		//  SQLUpdate
			Conexion.closeConnection(c);
			
	}
	
	public static void borrarTabla() throws SQLException {
		
		Connection c = Conexion.openConnection();
		
		Statement stmt1 = c.createStatement();
		String sql1 = "Drop table Clientes" ;
		stmt1.executeUpdate(sql1);
		stmt1.close();
		System.out.println("\nTabla Clientes borrada");
		
		
		Conexion.closeConnection(c);
				
	}
	
	public static void borrarDatos() throws SQLException, NumberFormatException, IOException{
		Connection c =Conexion.openConnection();
		//  SQLDelete
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nElija un cliente a eliminar, teclee su ID: \n");
		//printClientes(c);
		int id = Integer.parseInt(reader.readLine());
		String sql = "DELETE FROM Clientes WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
		System.out.println("Deletion finished.");
			Conexion.closeConnection(c);
			
	}
	
	private static void printClientes(Connection c) throws SQLException {
				
		System.out.println("Imprimiendo Clientes");
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Clientes";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs!= null) {
		while (rs.next()) {
			int id = rs.getInt("Id");
			String nombre = rs.getString("Nombre");
			int edad = rs.getInt("Edad");
			int altura = rs.getInt("Altura");
			String fecha_entrada = rs.getString("Fecha_salida");
			String fecha_salida = rs.getString("Fecha_salida");
			boolean numerosa = rs.getBoolean("Familia_numerosa");
			int puesto_id =  rs.getInt("Puesto_id");
			String nombrePuesto = SQLPuestos.getNombrePuesto(puesto_id,c);
			int atraccion_id =  rs.getInt("Atraccion_id");
			String nombreAtraccion = SQLAtracciones.getNombreAtraccion(atraccion_id,c);
			
			
			System.out.println(id + ": "+ nombre + ", " + edad + ", " + altura + ", " + fecha_entrada
					+ ", " + fecha_salida + ", "+ "familia numerosa: "+ numerosa + ", " + nombrePuesto + ", " + nombreAtraccion);
			
		}
		}else System.out.println("No existen datos");
		rs.close();
		stmt.close();
	}
	
	public static int getSumClientes(Connection c,int atraccion_id) throws SQLException {
		int sum =0;
		Statement stmt =c.createStatement();
		String sql = "Select ID FROM Clientes where Atraccion_id ='"+atraccion_id+"' ";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs!= null) {
			while(rs.next()) {
					sum++;
			}
		}
		return sum;
		
	}
	
	

}