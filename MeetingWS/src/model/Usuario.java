package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class Usuario extends Persona implements Serializable{
	private static final long serialVersionUID = 2748837857727564816L;
	
	private String 	username, password;
	private int		idUsuario;
	
	/*************************************************************************************************/
	
	public Usuario(){}
	
	public Usuario(String username, String password){
		super();
		this.username	=	username;
		this.password	=	password;
	}
	
	public Usuario(int idUsuario, int idPersona, String username, String password){
		super(idPersona);
		this.idUsuario 	=	idUsuario;
		this.username	=	username;
		this.password	=	password;
	}
	
	/*************************************************************************************************/

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}