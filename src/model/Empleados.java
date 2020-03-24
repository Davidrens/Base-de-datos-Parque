package model;
import java.util.ArrayList;
public class Empleados {
	private Integer id;
	private String nombre;
	private Integer cargo_id;
	private Integer zona_id;
	private Integer sueldo;
	private ArrayList<int[]> atraccion_id = new ArrayList<int[]>();
	private ArrayList<int[]> puesto_id = new ArrayList<int[]>();
	
	
	public Empleados(Integer id, String nombre, Cargo cargo_id2, Zona zona_id2, Integer sueldo) {
		// TODO Auto-generated constructor stub
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
	
	
	

}
