package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the datos_email database table.
 * 
 */
@Entity
@Table(name="datos_email")
@NamedQuery(name="DatosEmail.findAll", query="SELECT d FROM DatosEmail d")
public class DatosEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String correo;

	private String password;

	private String servidor;

	public DatosEmail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServidor() {
		return this.servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

}