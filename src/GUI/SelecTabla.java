package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import dbController.SQLAtracciones;
import dbController.SQLCargo;
import dbController.SQLClientes;
import dbController.SQLEmpleados;
import dbController.SQLPuestos;
import dbController.SQLZona;
import model.Clientes;

public class SelecTabla {
	
	public static void SelecTabla(int funcion, Scanner sc) throws SQLException, IOException{
		Boolean salir = false;
	    int opcion2;

	    while (!salir) {
	        try {
	            System.out.println("Seleccione la tabla deseada\n1)Clientes \n2)Empleados \n3) Atracciones \n4) Puestos "
	            		+ "\n5) Zona \n6) Cargo \n7) Salir");
	            opcion2 = sc.nextInt();
	            switch (opcion2) {
	                case 1:
	                    System.out.println("Has seleccionado la tabla de Clientes");
	                    switch(funcion) {
	                    case 2:
	                    	
	                    	break;
	                    case 3:
	                    	System.out.println("Introduzca información del Cliente:");
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
	    							Clientes cliente1 = new Clientes(edad, altura, nombre, entrada, salida, numerosa);
	    							ciclo = false;
	    							cliente1.setPuesto_id(puesto);
	    							SQLClientes.insertarDatos(cliente1);
	    							break;
	    						case 2:
	    							System.out.println("\nAtraccion:");
	    							String atraccion = reader.readLine();
	    							Clientes cliente2 = new Clientes(edad, altura, nombre, entrada, salida, numerosa);
	    							ciclo = false;
	    							cliente2.setAtraccion_id(atraccion);
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
	                    case 2:
	                    	break;
	                    case 3:
	                    	SQLEmpleados.insertarDatos();
	                    	break;
	                    case 4:
	                    	SQLEmpleados.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLEmpleados.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLEmpleados.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLEmpleados.borrarDatos();
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
	                    	SQLAtracciones.insertarDatos();
	                    	break;
	                    case 4:
	                    	SQLAtracciones.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLAtracciones.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLAtracciones.actualizarDatos();
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
	                    	SQLPuestos.insertarDatos();
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
	                    	SQLZona.insertarDatos();
	                    	break;
	                    case 4:
	                    	SQLZona.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLZona.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLZona.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLZona.borrarDatos();
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
	                    	SQLCargo.insertarDatos();
	                    	break;
	                    case 4:
	                    	SQLCargo.obtenerInfo();
	                    	break;
	                    	
	                    case 5:
	                    	SQLCargo.buscarDatos();
	                    	break;
	                    case 6:
	                    	SQLCargo.actualizarDatos();
	                    	break; 
	                    case 7:
	                    	SQLCargo.borrarDatos();
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
