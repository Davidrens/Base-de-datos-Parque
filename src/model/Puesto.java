package model;

import java.util.ArrayList;

public class Puesto {
	
	private Integer id;
	private String tipo;
	private ArrayList<int[]> empleados_id = new ArrayList<int[]>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ArrayList<int[]> getEmpleados_id() {
		return empleados_id;
	}
	public void setEmpleados_id(ArrayList<int[]> empleados_id) {
		this.empleados_id = empleados_id;
	}

}
