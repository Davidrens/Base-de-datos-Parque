package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dbController.SQLAtracciones;
import dbController.SQLPuestos;

public class Clientes {
	
	private Integer id;
	private int edad;
	private Integer altura;
	private String nombre;
	private LocalDate fechaentrada;
	private LocalDate fechasalida;
	private Boolean numerosa;
	private Integer puesto_id;
	private Integer atraccion_id;
	private ArrayList<int[]> ofertas_id = new ArrayList<int[]>();
	
	
	
	public Clientes( int edad, Integer altura, String nombre, LocalDate fechaentrada, LocalDate fechasalida,
			Boolean numerosa) {
		this.edad = edad;
		this.altura = altura;
		this.nombre = nombre;
		this.fechaentrada = fechaentrada;
		this.fechasalida = fechasalida;
		this.numerosa = numerosa;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaentrada() {
		return fechaentrada;
	}
	public void setFechaentrada(LocalDate fechaentrada) {
		this.fechaentrada = fechaentrada;
	}
	public LocalDate getFechasalida() {
		return fechasalida;
	}
	public void setFechasalida(LocalDate fechasalida) {
		this.fechasalida = fechasalida;
	}
	public Boolean getNumerosa() {
		return numerosa;
	}
	public void setNumerosa(Boolean numerosa) {
		this.numerosa = numerosa;
	}
	public Integer getPuesto_id() {
		return puesto_id;
	}
	public void setPuesto_id(String puesto, Connection c) throws SQLException {
		int puesto_id = SQLPuestos.getId(puesto,c);
		this.puesto_id = puesto_id;
		this.atraccion_id=0;
	}
	public Integer getAtraccion_id() {
		return atraccion_id;
	}
	public void setAtraccion_id(String atraccion,Connection c) throws SQLException {
		int atraccion_id = SQLAtracciones.getId(atraccion,c);
		System.out.println(""+atraccion_id);
		this.atraccion_id = atraccion_id;
		this.puesto_id=0;
	}
	public ArrayList<int[]> getOfertas_id() {
		return ofertas_id;
	}
	public void setOfertas_id(ArrayList<int[]> ofertas_id) {
		this.ofertas_id = ofertas_id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "Clientes [id=" + id + ", edad=" + edad + ", altura=" + altura + ", nombre=" + nombre + ", fechaentrada="
				+ fechaentrada + ", fechasalida=" + fechasalida + ", numerosa=" + numerosa + ", puesto_id=" + puesto_id
				+ ", atraccion_id=" + atraccion_id + ", ofertas_id=" + ofertas_id + "]";
	}

}
