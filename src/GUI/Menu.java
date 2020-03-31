package GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import dbController.SQLCreate;

public class Menu {

	public static void Interfaz() throws SQLException, IOException{
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        int opcion;

        while (!salir) {
            try {
                System.out.println("Seleccione la funci�n deseada\n1) Crear tablas \n2) Borrar tabla \n3) Insertar datos en tabla \n"
                		+ "4) Obtener informaci�n de una tabla"+ " \n5) Buscar datos en una tabla \n6) Realizar actualizaci�n de datos en una tabla \n"
                				+ "7) Borrar datos de una tabla \n8) Salir");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion: Crear tablas");
                        SQLCreate.Create();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion: Borrar tabla");
	                    DropTable.DropTable();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion: Insertar datos en tabla");
	                    SelecTabla.SelecTabla(opcion,sc);
                        break;
                    case 4:
                    	System.out.println("Has seleccionado la opcion: Obtener informaci�n de una tabla");
	                    SelecTabla.SelecTabla(opcion,sc);
                        break;
                    case 5:
                    	System.out.println("Has seleccionado la opcion: Buscar datos en una tabla");
	                    SelecTabla.SelecTabla(opcion,sc);
                    	break;
                    	
                    case 6:
                    	System.out.println("Has seleccionado la opcion: Realizar una actualizaci�n de datos en una tabla ");
	                    SelecTabla.SelecTabla(opcion,sc);
                    	break;
                    	
                    case 7:
                    	System.out.println("Has seleccionado la opci�n: Borrar datos de una tabla");
	                    SelecTabla.SelecTabla(opcion,sc);
                    	break;
                    	
                    case 8:
                    	salir = true;
                    	break;
                    default:
                        System.out.println("Solo n�meros entre 1 y 8");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un n�mero");
                sc.next();
            }
        }
        
        sc.close();
        
    }
 }




