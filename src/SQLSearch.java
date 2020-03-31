import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLSearch {
	public static void main(String args[]) {
	
		try {
			//Open DB connection
			Class.forName("org.sqlite.JDBC");
			Connection c =DriverManager.getConnection("JDBC:sqlite:./db/parque.db");
			c.createStatement().execute("Pragma foreign_keys=ON");
			System.out.println("Database connection openned");
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Nombre empleado: ");
			String searchName = reader.readLine();
		
			// Seleccionar Busqueda
			String sql = "SELECT * FROM Atracciones ";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, searchName);
			ResultSet rs = prep.executeQuery();
			if(rs != null) {
			while(rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Nombre");
				System.out.println(id + ": "+ name);
			}
			}else {
				System.out.println("No hubo resultados");
			}
			
			// CLOSE Statement
			rs.close();
			prep.close();
			System.out.println("Busqueda Completada");
			//Close DB connection
			c.close();
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
