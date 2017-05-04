package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-one association to RolesVista
	@OneToMany(mappedBy="role")
	private List<RolesVista> rolesVistas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="role")
	private List<Usuario> usuarios;

	public Role() {
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
		rolesVista.setRole(this);

		return rolesVista;
	}

	public RolesVista removeRolesVista(RolesVista rolesVista) {
		getRolesVistas().remove(rolesVista);
		rolesVista.setRole(null);

		return rolesVista;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRole(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRole(null);

		return usuario;
	}

}