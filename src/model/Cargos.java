package model;

import java.sql.Connection;
import java.sql.SQLException;

import dbController.SQLCargos;

public class Cargos {
	
	private int id;
	private String nombre;
	

	public Cargos( String nombre) {
		this.nombre = nombre;
		// TODO Auto-generated constructor stub
	}
	public int getId(Connection c) throws SQLException {
		id = SQLCargos.getId(this.nombre, c);
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
	

}
