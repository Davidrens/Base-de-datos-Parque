package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dbController.SQLClientes;

public class Atracciones {
	
	private Integer id;
	private String nombre;
	private Integer zona_id;
	private Integer clientes;
	private int espera;
	private ArrayList<int[]> empleados_id = new ArrayList<int[]>();
	
	
	
	
	public Atracciones() {
		this.id =0;
		this.nombre="";
		this.zona_id = 0;
		this.espera =0;
	}
	public Atracciones(String nombre, int zona_id) {
		this.nombre = nombre;
		this.zona_id = zona_id;
		this.espera =0;
	}
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
		
		return this.espera;
	}
	public void setEspera(Connection c) throws SQLException {
		int clientes = SQLClientes.getSumClientes(c,this.id);
		this.espera = clientes * 4;
	}
	public ArrayList<int[]> getEmpleados_id() {
		return empleados_id;
	}
	public void setEmpleados_id(ArrayList<int[]> empleados_id) {
		this.empleados_id = empleados_id;
	}

}
