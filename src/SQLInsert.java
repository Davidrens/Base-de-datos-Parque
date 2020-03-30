

	
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

	public class SQLInsert {
		public static void main(String args[]) {
			try {
				// Open database connection
				Class.forName("org.sqlite.JDBC");
				Connection c = DriverManager.getConnection("jdbc:sqlite:./db/parque.db");
				c.createStatement().execute("PRAGMA foreign_keys=ON");
				System.out.println("Database connection opened.");
				
				// Get the employee info from the command prompt
				System.out.println("Introduzca información del empleado:");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Nombre: ");
				String nombre = reader.readLine();
				System.out.print("Cargo_id: ");
				String cargo_id = reader.readLine();
				System.out.print("Zona_id: ");
				String zona_id = reader.readLine();
				System.out.print("Sueldo: ");
				String sueldo = reader.readLine();
				

				// Insert new record: begin
				Statement stmt = c.createStatement();
				String sql = "INSERT INTO Empleados (Nombre, Cargo_id, Sueldo, Zona_id) "
						+ "VALUES ('" + nombre + "', '" + cargo_id	+ "',  '" + sueldo +"','"+ zona_id+"');";
				stmt.executeUpdate(sql);
				stmt.close();
				//TAMBIEN SE PODRÍA HACER CON UN PREPARED STATEMENT DE LA SIGUIENTE MANERA
				/*
				String sql = "INSERT INTO Empleados (Nombre, Cargo_id , Zona_id , Sueldo, Puesto_id, Atraccion_id) "
						+ "VALUES (?,?,?,?,?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setString(1, nombre);
				prep.setInt(2, cargo_id);
				prep.setInt(3, zona_id);
				prep.setInt(4, sueldo);
				prep.setInt(5, puesto_id);
				prep.setInt(6, atraccion_id);
				
				prep.executeUpdate();
				prep.close();
				*/
				
				System.out.println("Informacion del empleado procesada");
				System.out.println("Datos insertados");
				// Insert new record: end
				
				//INSERCION DE DATOS EN TABLA ATRACCIONES
				System.out.println("Introduzca informacion de la atracción:");
				System.out.print("Nombre: ");
				String nombre2 = reader.readLine();
				System.out.print("Zona_id: ");
				int zona_id2 = Integer.parseInt(reader.readLine());
				System.out.print("Clientes: ");
				int clientes = Integer.parseInt(reader.readLine());
				System.out.print("Espera: ");
				int espera = Integer.parseInt(reader.readLine());
				
				
				String sq2 = "INSERT INTO Atracciones (Nombre, Zona_id , Clientes , Espera) "
						+ "VALUES (?,?,?,?);";
				PreparedStatement prep = c.prepareStatement(sq2);
				prep.setString(1, nombre2);
				prep.setInt(2, zona_id2);
				prep.setInt(3, clientes);
				prep.setInt(4, espera);
				
				prep.executeUpdate();
				prep.close();
				
				System.out.println("Informacion de la atracción procesada");
				System.out.println("Datos insertados");
				//INSERCION DE DATOS DE LA ATRACCION FINALIZADA
				
				//INSERCION DE UNA NUEVA ZONA
				
				System.out.println("Introduzca informacion de la zona:");
				System.out.print("Nombre: ");
				String nombre3 = reader.readLine();
				String sq3 = "INSERT INTO Zona (Nombre) "
						+ "VALUES (?);";
				PreparedStatement prep2 = c.prepareStatement(sq3);
				prep.setString(1, nombre3);
				
				prep2.executeUpdate();
				prep2.close();
				
				System.out.println("Informacion de la zona procesada");
				System.out.println("Datos insertados");
				//INSERCION DE LA ZONA FINALIZADA
				
				

				// Close database connection
				c.close();
				System.out.println("Database connection closed.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

