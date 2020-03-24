package model;

import java.util.ArrayList;

public class Atracciones {
	
	private Integer id;
	private String nombre;
	private Integer zona_id;
	private Integer clientes;
	private int espera;
	private ArrayList<int[]> empleados_id = new ArrayList<int[]>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getZona_id() {
		return zona_id;
	}
	public void setZona_id(Integer zona_id) {
		this.zona_id = zona_id;
	}
	public Integer getClientes() {
		return clientes;
	}
	public void setClientes(Integer clientes) {
		this.clientes = clientes;
	}
	public int getEspera() {
		return espera;
	}
	public void setEspera(int espera) {
		this.espera = espera;
	}
	public ArrayList<int[]> getEmpleados_id() {
		return empleados_id;
	}
	public void setEmpleados_id(ArrayList<int[]> empleados_id) {
		this.empleados_id = empleados_id;
	}

}
