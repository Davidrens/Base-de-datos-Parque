package test;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dbController.Conexion;
import dbController.SQLClientes;
import model.Clientes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SQLSel {


	public static void main(String args[]) throws SQLException, IOException {
		
        Scanner sc = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre: ");
		String nombre = reader.readLine();
		System.out.print("\nEdad: ");
		int edad = Integer.parseInt(reader.readLine());
		System.out.print("\nAltura: ");
		int altura = Integer.parseInt(reader.readLine());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.print("\nFecha entrada (año-mes-día): ");
		String Sentrada = reader.readLine();
		LocalDate entrada =LocalDate.parse(Sentrada,formatter);					
		System.out.print("\nFecha salida (año-mes-día): ");
		String Ssalida = reader.readLine();
		LocalDate salida = LocalDate.parse(Ssalida,formatter);
		System.out.print("\nFamilia numerosa: ");
		boolean numerosa = sc.nextBoolean();
		Boolean ciclo = true;
		while(ciclo){
		System.out.println("Añadir lugar en el que se encuentra: 1)Puesto 2)Atraccion");
		int elegir = Integer.parseInt(reader.readLine());
		switch(elegir) {
		case 1:
			System.out.print("\nPuesto: ");
			String puesto = reader.readLine();
			Clientes Pcliente = new Clientes(edad, altura, nombre, entrada, salida, numerosa);
			ciclo = false;
			Pcliente.setPuesto_id(puesto);
			break;
		case 2:
			System.out.println("\nAtraccion:");
			String atraccion = reader.readLine();
			Clientes Acliente = new Clientes(edad, altura,nombre, entrada, salida, numerosa);
			ciclo = false;
			Acliente.setAtraccion_id(atraccion);
			System.out.println(""+Acliente);
			Connection c = Conexion.openConnection();
			//  SQLInsert
			// Insert new record: begin
			if(Acliente.getPuesto_id()==0) {
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO Clientes (Nombre, Edad, Altura, Fecha_entrada, Fecha_salida, Familia_numerosa, Atraccion_id) "
					+ "VALUES ('" + Acliente.getNombre() + "', '" + Acliente.getEdad()+ "', '" + Acliente.getAltura() + "', '" + Acliente.getFechaentrada() + "', '" + Acliente.getFechasalida() + "','" + "', '" + Acliente.getNumerosa() 
					+ Acliente.getAtraccion_id()+"');";
			stmt.executeUpdate(sql);
			stmt.close();
			}else {
				/* Statement stmt = c.createStatement();
				String sql = "INSERT INTO Clientes (Nombre, Edad, Altura, Fecha_entrada, Fecha_salida, Familia_numerosa, Puesto_id) "
						+ "VALUES ('" + cliente.getNombre() + "', '" + cliente.getEdad()+ "', '" + cliente.getAltura() + "', '" + cliente.getFechaentrada() + "', '" + cliente.getFechasalida() + "','" + "', '" + cliente.getNumerosa() 
						+ cliente.getPuesto_id()+"');";
				stmt.executeUpdate(sql);
				stmt.close(); */
			}
			Conexion.closeConnection(c);
			break;
		 default:
			break;
		}
		}
		
			
		
	}
}
