package dbController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DropTable {
	
	public DropTable() throws SQLException {
		
		Boolean salir = false;
	    Scanner sc = new Scanner(System.in);
	    int opcion;
	
		System.out.println("Seleccione la tabla que desea borrar: \n1) Clientes \n2) Empleados \n3) Atracciones \n4) Puestos"
			+ "\n5) Zona \n6) Cargo \n7) Salir\"");
	
		 while (!salir) {
		        try {
		            System.out.println("Seleccione la tabla deseada\n1)Clientes \n2)Empleados \n3) Atracciones \n4) Puestos "
		            		+ "\n5) Zona \n6) Cargo \n7) Salir");
		            opcion = sc.nextInt();
		            switch (opcion) {
		                case 1:
		                    System.out.println("Has seleccionado la tabla de Clientes");
		                    SQLClientes.borrarTabla();
		                    break;
		                 
		                case 2:  
		                    System.out.println("Has seleccionado la tabla de Empleados");
		                    SQLEmpleados.borrarTabla();
		                    break;
		                
		                case 3:  
		                    System.out.println("Has seleccionado la tabla de Atracciones");
		                    SQLAtracciones.borrarTabla();
		                    break;
		                   
		                case 4:  
		                    System.out.println("Has seleccionado la tabla de Puestos");
		                    SQLPuestos.borrarTabla();
		                    break;    
		                 
		                case 5:  
		                    System.out.println("Has seleccionado la tabla de Zona");
		                    SQLZona.borrarTabla();
		                    break;
		                    
		                case 6:  
		                    System.out.println("Has seleccionado la tabla de Cargo");
		                    SQLCargo.borrarTabla();
		                    break;
		                    
		                case 7:  
		                	salir = true;
		                	break;
		                default:
		                    System.out.println("Solo n�meros entre 1 y 7");
		            }
		        } catch (InputMismatchException e) {
		            System.out.println("Debes insertar un n�mero");
		            sc.next();
		        }
	}
		 sc.close();
		 
	}

}
