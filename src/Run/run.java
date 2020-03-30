package Run;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

import GUI.Menu;

public class run {
	
	public static void main(String[] args) throws SQLException, IOException {
		try {
				Menu.Interfaz();
		}catch (InputMismatchException e) {
            
        }
	}
}
