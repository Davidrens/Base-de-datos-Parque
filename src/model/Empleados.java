package model;
import java.util.ArrayList;
public class Empleados {
	private Integer id;
	private String nombre;
	private int cargo_id;
	private int zona_id;
	private int sueldo;
	private ArrayList<int[]> atraccion_id = new ArrayList<int[]>();
	private ArrayList<int[]> puesto_id = new ArrayList<int[]>();
	
	public Empleados() {
		this.id =0;
		this.nombre="";
		this.cargo_id=0;
		this.zona_id = 0;
		this.sueldo = 0;
	}
	public Empleados(String nombre, int cargo_id, int zona_id, int sueldo) {
		this.nombre = nombre;
		this.cargo_id = cargo_id;
		this.zona_id = zona_id;
		this.sueldo= sueldo;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCargo_id() {
		return cargo_id;
	}
	
	public void setCargo_id(int cargo_id) {
		this.cargo_id = cargo_id;
	}
	
	public int getZona_id() {
		return zona_id;
	}
	
	public void setZona_id(int zona_id) {
		this.zona_id = zona_id;
	}
	
	public int getSueldo() {
		return sueldo;
	}
	
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public ArrayList<int[]> getAtraccion_id() {
		return atraccion_id;
	}

	public void setAtraccion_id(ArrayList<int[]> atraccion_id) {
		this.atraccion_id = atraccion_id;
	}

	public ArrayList<int[]> getPuesto_id() {
		return puesto_id;
	}

	public void setPuesto_id(ArrayList<int[]> puesto_id) {
		this.puesto_id = puesto_id;
	}
	
	public String toString() {
		String string ="ID: "+ getId()+ "  Nombre: "+ getNombre()+ "  Cargo_id: "+getCargo_id()+ "   Zona_id: "+ getZona_id() + "   Sueldo: "+getSueldo();
		return string; 
	}
	

}
