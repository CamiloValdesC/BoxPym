package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class Persona implements Serializable{
	private static final long serialVersionUID = 3915943470667748844L;
	
	private String 	nombre;
	private String	apellido;
	private	String	dv;
	private int		run;
	private	int		idPersona;
	
	/*************************************************************************************************/
	
	public Persona(){}
	
	public Persona(int idPersona){
		this.idPersona	=	idPersona;
	}
	
	public Persona(int run, String dv, String nombre, String apellido){
		this.run		=	run;
		this.dv			=	dv;
		this.nombre		=	nombre;
		this.apellido	=	apellido;
	}
	
	public Persona(int idPersona, int run, String dv, String nombre, String apellido){
		this.idPersona	=	idPersona;
		this.run		=	run;
		this.dv			=	dv;
		this.nombre		=	nombre;
		this.apellido	=	apellido;
	}
	
	/*************************************************************************************************/
		
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}	
	
}