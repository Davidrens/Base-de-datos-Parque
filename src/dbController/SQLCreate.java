package dbController;
import java.sql.Connection;
import java.sql.Statement;
public class SQLCreate {
		public static void Create() {
			try {
				Connection c = Conexion.openConnection();
				//Create tables: begin
				Statement stmt1 = c.createStatement();
				String sql1 = "CREATE TABLE IF NOT EXISTS \"Ofertas\" (\r\n" + 
						"	\"Id\" INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
						"	\"Tipo\" TEXT  Unique not NULL,\r\n" + 
						"	\"Fecha_inicio\" Text not null,\r\n" + 
						"	\"Fecha_fin\" Text not null,\r\n" + 
						"	\"Descuento\" float NOT NULL \r\n" + 
						")" ;
				stmt1.executeUpdate(sql1);
				stmt1.close();
				
				Statement stmt2 = c.createStatement();
				String sql2 = "CREATE TABLE IF NOT EXISTS \"Cargos\" (\r\n" + 
						"	\"Id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
						"	\"Nombre\" TEXT Unique NOT NULL\r\n" + 
						")" ;
				stmt2.executeUpdate(sql2);
				stmt2.close();
				
				
				Statement stmt3 = c.createStatement();
				String sql3 = "CREATE TABLE IF NOT EXISTS \"Zonas\"(\r\n" + 
						"	\"Id\" Integer not null primary key AUTOINCREMENT,\r\n" + 
						"	\"Nombre\" text unique not null\r\n" + 
						")" ;
				stmt3.executeUpdate(sql3);
				stmt3.close();
				
				Statement stmt4 = c.createStatement();
				String sql4 = "CREATE TABLE IF NOT EXISTS \"Puestos\"(\r\n" + 
						"	\"Id\" INTEGER   PRIMARY KEY AUTOINCREMENT,\r\n" + 
						"	\"Nombre\" TEXT Unique NOT NULL,\r\n" + 
						"	\"Zona_id\" INTEGER NOT NULL,\r\n" + 
						"	Foreign key (Zona_id) References Zonas(Id)\r\n" + 
						")";
				stmt4.executeUpdate(sql4);
				stmt4.close();
				
				Statement stmt5 = c.createStatement();
				String sql5 = "CREATE TABLE IF NOT EXISTS \"Atracciones\"(\r\n" + 
						"	\"Id\" Integer primary key AUTOINCREMENT,\r\n" + 
						"	\"Nombre\" TEXT not null  unique,\r\n" + 
						"	\"Zona_id\" Integer not null,\r\n" + 
						"	\"Espera\" Integer,\r\n" + 
						"	FOREIGN KEY (Zona_id) REFERENCES Zonas(Id)\r\n" + 
						")" ;
				stmt5.executeUpdate(sql5);
				stmt5.close();
				
				Statement stmt6 = c.createStatement();
				String sql6 = "CREATE TABLE IF NOT EXISTS\"Empleados\"(\r\n" + 
						"	\"Id\" Integer  primary key AUTOINCREMENT,\r\n" + 
						"	\"Nombre\" Text not null,\r\n" + 
						"	\"Cargo_id\" Integer not null,\r\n" + 
						"	\"Zona_id\" Integer not null,\r\n" + 
						"	\"Sueldo\" Integer not null,\r\n" + 
						"	FOREIGN KEY (Cargo_id) References Cargos(Id),\r\n" + 
						"	FOREIGN KEY (Zona_id) REFERENCES Zonas(Id)\r\n" + 
						")" ;
				stmt6.executeUpdate(sql6);
				stmt6.close();
				
				Statement stmt7 = c.createStatement();
				String sql7 = "CREATE TABLE IF NOT EXISTS \"Clientes\" (\r\n" + 
						"	\"Id\" Integer Not null Primary key AUTOINCREMENT,\r\n" + 
						"	\"Nombre\" TEXT not null,\r\n" + 
						"	\"Edad\" integer not null,\r\n" + 
						"	\"Altura\" integer not null,\r\n" + 
						"	\"Fecha_entrada\" LocalDate not null,\r\n" + 
						"	\"Fecha_salida\" LocalDate not null,\r\n" + 
						"	\"Familia_numerosa\" boolean not null,\r\n" + 
						"	\"Puesto_id\" integer ,\r\n" + 
						"	\"Atraccion_id\" integer,\r\n" + 
						"	FOREIGN KEY (Puesto_id) REFERENCES Puestos(Id) \r\n" + 
						"	FOREIGN KEY (Atraccion_id) REFERENCES Atracciones(Id) \r\n" + 
						")" ;
				stmt7.executeUpdate(sql7);
				stmt7.close();
				
				Statement stmt8 = c.createStatement();
				String sql8 = "CREATE TABLE IF NOT EXISTS \"Empleado_atraccion\"(\r\n" + 
						"	\"Id\" Integer not null PRIMARY key AUTOINCREMENT,\r\n" + 
						"	\"Empleado_id\" integer not null,\r\n" + 
						"	\"Atraccion_id\" integer not null,\r\n" + 
						"	FOREIGN KEy (Empleado_id) REFERENCES Empleados(Id),\r\n" + 
						"	FOREIGN Key (Atraccion_id) REFERENCES Atracciones(Id)\r\n" + 
						")" ;
				stmt8.executeUpdate(sql8);
				stmt8.close();
				
				Statement stmt9 = c.createStatement();
				String sql9 = "CREATE TABLE IF NOT EXISTS \"Oferta_cliente\"(\r\n" + 
						"	\"Oferta_id\" integer not null,\r\n" + 
						"	\"Cliente_id\" integer not null,\r\n" + 
						"	FOREIGN KEy (Oferta_id) REFERENCES Ofertas(Id),\r\n" + 
						"	FOREIGN Key (Cliente_id) REFERENCES Clientes(Id)\r\n" + 
						")" ;
				stmt9.executeUpdate(sql9);
				stmt9.close();
				
				Statement stmt10 = c.createStatement();
				String sql10 ="CREATE TABLE IF NOT EXISTS \"Empleado_puesto\"(\r\n" + 
						"	\"Empleado_id\" integer not null,\r\n" + 
						"	\"Puesto_id\" integer not null,\r\n" + 
						"	FOREIGN KEy (Empleado_id) REFERENCES Empleados(Id),\r\n" + 
						"	FOREIGN Key (Puesto_id) REFERENCES Puestos(Id)\r\n" + 
						")";
				stmt10.executeUpdate(sql10);
				stmt10.close();
				System.out.println("Tablas creadas");
				//Close DB connection
				Conexion.closeConnection(c);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
