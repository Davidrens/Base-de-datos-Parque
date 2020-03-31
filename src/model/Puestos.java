package model;

public class Puestos {
	
	private Integer id;
	private String nombre;
	private int zona_id;
	
	public Puestos(String nombre, int zona_id) {
		this.nombre = nombre;
		this.zona_id = zona_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getZona_id() {
		return zona_id;
	}
	public void setZona_id(int zona_id) {
		this.zona_id = zona_id;
	}
	

}
