package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roles_vistas database table.
 * 
 */
@Entity
@Table(name="roles_vistas")
@NamedQuery(name="RolesVista.findAll", query="SELECT r FROM RolesVista r")
public class RolesVista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_roles")
	private Role role;

	//bi-directional many-to-one association to Vista
	@ManyToOne
	@JoinColumn(name="id_vistas")
	private Vista vista;

	public RolesVista() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Vista getVista() {
		return this.vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

}