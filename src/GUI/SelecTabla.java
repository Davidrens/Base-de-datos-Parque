package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import dbController.Conexion;
import dbController.SQLAtracciones;
import dbController.SQLCargos;
import dbController.SQLClientes;
import dbController.SQLEmpleados;
import dbController.SQLPuestos;
import dbController.SQLZonas;
import model.Atracciones;
import model.Clientes;
import model.Empleados;
import model.Puestos;

public class SelecTabla {
	
	public static void SelecTablas(int funcion, Scanner sc) throws SQLException, IOException{
		Boolean salir = false;
	    int opcion2;
	    Connection c = null;

	    while (!salir) {
	        try {
	            System.out.println("Seleccione la tabla deseada\n1)Clientes \n2)Empleados \n3) Atracciones \n4) Puestos "
	            		+ "\n5) Zona \n6) Cargo \n7) Salir");
	            opcion2 = sc.nextInt();
	            switch (opcion2) {
	                case 1:
	                    System.out.println("Has seleccionado la tabla de Clientes");
	                    switch(funcion) {
	                    case 3:
	                    	System.out.println("Introduzca información del Cliente:");
	    					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    					  
	    					System.out.println("Nombre: ");
	    					String nombre = reader.readLine();
	    					System.out.println("Edad: ");
	    					int edad = Integer.parseInt(reader.readLine());
	    					System.out.println("Altura: ");
	    					int altura = Integer.parseInt(reader.readLine());
	    					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    					System.out.println("Fecha entrada (yyyy-mm-dd): ");
	    					String Sentrada = reader.readLine();
	    					LocalDate entrada =LocalDate.parse(Sentrada,formatter);					
	    					System.out.println("Fecha salida (yyyy-mm-dd): ");
	    					String Ssalida = reader.readLine();
	    					LocalDate salida = LocalDate.parse(Ssalida,formatter);
	    					System.out.println("Familia numerosa(boolean): ");
	    					boolean numerosa = sc.nextBoolean();
	    					Boolean ciclo = true;
	    					while(ciclo){
	    					System.out.println("Añadir lugar en el que se encuentra: 1)Puesto 2)Atraccion");
	    					int elegir = Integer.parseInt(reader.readLine());
	    					switch(elegir) {
	    						case 1:
	    							c = Conexion.openConnection();
	    							SQLPuestos.printPuestos(c);
	    							System.out.println("Puesto (nombre): ");
	    							String puesto = reader.readLine();
	    							Clientes cliente1 = new Clientes(edad, altura, nombre, entrada, salida, numerosa);
	    							ciclo = false;
	    							cliente1.setPuesto_id(puesto, c);
	    							Conexion.closeConnection(c);
	    							SQLClientes.insertarDatos(cliente1);
	    							break;
	    						case 2:
	    							c = Conexion.openConnection();
	    							SQLAtracciones.printAtracciones(c);
	    							System.out.println("Atraccion(nombre):");
	    							String atraccion = reader.readLine();
	    							Clientes cliente2 = new Clientes(edad, altura, nombre, entrada, salida, numerosa);
	    							ciclo = false;
	    							cliente2.setAtraccion_id(atraccion,c);
	    							Conexion.closeConnection(c);
	    							SQLClientes.insertarDatos(cliente2);
	    							break;
	    						default:
	    							break;
	    					}
	    					}
	                    	break;
	                    case 4:
	                    	SQLClientes.obtenerInfo();
	                    	break;
	                    case 5:
	                    	SQLClientes.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLClientes.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLClientes.borrarDatos();
	                    	break;
	                    	
	                    default:
	                    		break;
	                    }
	                    break;
	                case 2:
	                    System.out.println("Has seleccionado la tabla de Empleados");
	                    switch(funcion) {
	                    case 3:
	                		System.out.println("Introduzca información del empleado:");
	                		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	                		System.out.println("Nombre: ");
	                		String nombre = reader.readLine();
	                		c = Conexion.openConnection();
	                		SQLCargos.printCargos(c);
	                		System.out.println("Cargo(nombre): ");
	                		String cargo = reader.readLine();
	                		int cargo_id = SQLCargos.getId(cargo,c);
	                		SQLZonas.printZonas(c);
	                		System.out.println("Zona: ");
	                		String zona = reader.readLine();
	                		int zona_id = SQLZonas.getId(zona,c);
	                		Conexion.closeConnection(c);
	                		System.out.println("Sueldo: ");
	                		int sueldo = Integer.parseInt(reader.readLine());
	                		Empleados empleado = new Empleados(nombre,cargo_id,zona_id,sueldo);
	                    	SQLEmpleados.insertarDatos(empleado);
	                    	break;
	                    case 4:
	                    	SQLEmpleados.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	BufferedReader reader3 = new BufferedReader(new InputStreamReader(System.in));
	                		System.out.print("Nombre empleado del que desea obtener informacion: ");
	                		String searchName = reader3.readLine();
	                    	SQLEmpleados.search(searchName);
	                    	break;
	                    case 6:
	                    	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
	                    	System.out.println("\nElija a un empleado, introduzca su ID: ");
	                    	SQLEmpleados.obtenerInfo();
	                    	int empleado_id = Integer.parseInt(reader1.readLine());
	                    	System.out.println("\nIntroduzca nuevo sueldo del empleado: ");
	                    	int nuevo_sueldo = Integer.parseInt(reader1.readLine());	                    	
	                    	SQLEmpleados.actualizarDatos(empleado_id, nuevo_sueldo);
	                    	break; 
	                    case 7:
	                		BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
	                		System.out.println("\nElija un empleado a borrar, teclee su ID: ");
	                		SQLEmpleados.obtenerInfo();
	                		int eleccion = Integer.parseInt(reader2.readLine());
	                    	SQLEmpleados.borrarDatos(eleccion);
	                    	break;
	                    	
	                    default:
	                    		break;
	                    }

	                    break;
	                case 3:
	                    System.out.println("Has seleccionado la tabla de Atracciones");
	                    switch(funcion) {
	                    case 2:
	                    	
	                    	
	                    	break;
	                    	case 3:
	                    		System.out.println("Introduzca información de la Atracción:");
	                    		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	                    		System.out.println("Nombre: ");
	                    		String nombre = reader.readLine();
	                    		c = Conexion.openConnection();
	                    		SQLZonas.printZonas(c);
	                    		System.out.println("Zona(nombre): ");
	                    		String zona = reader.readLine();
	                    		int zona_id = SQLZonas.getId(zona,c);
	                    		Atracciones atraccion =new Atracciones(nombre,zona_id); 
	                    		Conexion.closeConnection(c);
	                    		SQLAtracciones.insertarDatos(atraccion);
	                    	break;
	                    case 4:
	                    	SQLAtracciones.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLAtracciones.buscarDatos();
	                    	break;
	                    case 6:
	                    	/*System.out.println("Has seleccionado la opcion: Consultar espera de Atracciones");
	                    	
	                    	SQLAtracciones.obtenerInfo();
	                    	System.out.println("Seleccione el nombre de la Atracción para consultar");
	                    	BufferedReader reader4 = new BufferedReader(new InputStreamReader(System.in));
	                    	String searchname = reader4.readLine();
	                    	Atracciones atraccion1 = SQLAtracciones.search(searchname, c);
	                    	System.out.println("La espera de la atraccion: "+atraccion1.getNombre()+" Estimada: "+atraccion1.getEspera()+" min");
	                    	SQLAtracciones.actualizarDatos();*/
	                    	System.out.println("Seleccione el nombre de la Atracción para consultar");
	                    	BufferedReader reader4 = new BufferedReader(new InputStreamReader(System.in));
	                    	String searchname = reader4.readLine();
	                    	SQLAtracciones.search(searchname, c);
	                    	
	                    	break; 
	                    case 7:
	                    	SQLAtracciones.borrarDatos();
	                    	break;
	                    	
	                    default:
	                    		break;
	                    }

	                    break;
	                case 4:
	                	System.out.println("Has seleccionado la tabla Puestos");
	                	switch(funcion) {
	                	case 2:
	                	
	                		break;
	                    case 3:
	                    	System.out.println("Introduzca información del Puesto:");
                    		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    		System.out.println("Nombre: ");
                    		String nombre = reader.readLine();
                    		c = Conexion.openConnection();
                    		SQLZonas.printZonas(c);
                    		System.out.println("Zona(nombre): ");
                    		String zona = reader.readLine();
                    		int zona_id = SQLZonas.getId(zona,c);
                    		Puestos puesto =new Puestos(nombre,zona_id);
                    		Conexion.closeConnection(c);
	                    	SQLPuestos.insertarDatos(puesto);
	                    	break;
	                    case 4:
	                    	SQLPuestos.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLPuestos.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLPuestos.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLPuestos.borrarDatos();
	                    	break;
	                    	
	                    default:
	                    		break;
	                    }
	                    break;
	                    
	                case 5:
	                	System.out.println("Has seleccionado la tabla Zona");
	                	switch(funcion) {
	                	case 2:
	                		
	                		break;
	                	
	                    case 3:
	                    	System.out.println("No disponible");
	                    	//SQLZonas.insertarDatos();
	                    	break;
	                    case 4:
	                    	SQLZonas.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLZonas.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLZonas.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLZonas.borrarDatos();
	                    	break;
	                    	
	                    default:
	                    		break;
	                    }
	                    break;
	                    
	                case 6:
	                	System.out.println("Has seleccionado la tabla Cargo");
	                	switch(funcion) {
	                	case 2:
	                		
	                		break;
	                	
	                    case 3:
	                    	SQLCargos.insertarDatos();
	                    	break;
	                    case 4:
	                    	SQLCargos.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLCargos.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLCargos.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLCargos.borrarDatos();
	                    	break;
	                    	
	                    default:
	                    		break;
	                    }
	                    break;
	                case 7:
	                	salir = true;
	                	break;
	                default:
	                    System.out.println("Solo números entre 1 y 7");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Debes insertar un número");
	            sc.next();
	        }
	    }
	}
}