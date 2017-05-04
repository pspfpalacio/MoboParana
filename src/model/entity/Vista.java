package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vistas database table.
 * 
 */
@Entity
@Table(name="vistas")
@NamedQuery(name="Vista.findAll", query="SELECT v FROM Vista v")
public class Vista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to RolesVista
	@OneToMany(mappedBy="vista")
	private List<RolesVista> rolesVistas;

	public Vista() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RolesVista> getRolesVistas() {
		return this.rolesVistas;
	}

	public void setRolesVistas(List<RolesVista> rolesVistas) {
		this.rolesVistas = rolesVistas;
	}

	public RolesVista addRolesVista(RolesVista rolesVista) {
		getRolesVistas().add(rolesVista);
		rolesVista.setVista(this);

		return rolesVista;
	}

	public RolesVista removeRolesVista(RolesVista rolesVista) {
		getRolesVistas().remove(rolesVista);
		rolesVista.setVista(null);

		return rolesVista;
	}

}