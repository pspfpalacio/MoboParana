package ar.com.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.entity.Role;
import model.entity.RolesVista;
import model.entity.Usuario;
import model.entity.Vista;
import dao.interfaces.DAORole;
import dao.interfaces.DAORoleVista;
import dao.interfaces.DAOVista;

@ManagedBean
@SessionScoped
public class BeanRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanRoleDAO}")
	private DAORole roleDAO;
	
	@ManagedProperty(value = "#{BeanVistaDAO}")
	private DAOVista vistaDAO;
	
	@ManagedProperty(value = "#{BeanRoleVistaDAO}")
	private DAORoleVista roleVistaDAO;
	
	private List<Role> listaRoles;
	private List<Role> filteredRoles;
	private List<String> listaVistas;
	private List<String> selectedVistas;
	private Role role;
	private Usuario usuario;
	private String headerText;
	
	public DAORole getRoleDAO() {
		return roleDAO;
	}
	
	public void setRoleDAO(DAORole roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	public DAOVista getVistaDAO() {
		return vistaDAO;
	}

	public void setVistaDAO(DAOVista vistaDAO) {
		this.vistaDAO = vistaDAO;
	}

	public DAORoleVista getRoleVistaDAO() {
		return roleVistaDAO;
	}

	public void setRoleVistaDAO(DAORoleVista roleVistaDAO) {
		this.roleVistaDAO = roleVistaDAO;
	}

	public List<Role> getListaRoles() {
		return listaRoles;
	}
	
	public void setListaRoles(List<Role> listaRoles) {
		this.listaRoles = listaRoles;
	}
	
	public List<Role> getFilteredRoles() {
		return filteredRoles;
	}
	
	public void setFilteredRoles(List<Role> filteredRoles) {
		this.filteredRoles = filteredRoles;
	}
	
	public List<String> getListaVistas() {
		List<Vista> listAux = vistaDAO.getLista();
		listaVistas = new ArrayList<String>();
		for (Vista vista : listAux) {
			String nombre = vista.getNombre();
			listaVistas.add(nombre);
		}
		return listaVistas;
	}

	public void setListaVistas(List<String> listaVistas) {
		this.listaVistas = listaVistas;
	}

	public List<String> getSelectedVistas() {
		return selectedVistas;
	}

	public void setSelectedVistas(List<String> selectedVistas) {
		this.selectedVistas = selectedVistas;
	}

	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getHeaderText() {
		return headerText;
	}
	
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	
	public String goRoles(Usuario user){
		listaRoles = new ArrayList<Role>();
		filteredRoles = new ArrayList<Role>();
		usuario = new Usuario();
		usuario = user;
		listaRoles = roleDAO.getLista();
		filteredRoles = listaRoles;
		return "roles";
	}
	
	public String goRolNuevo(){
		role = new Role();
		headerText = "Rol Nuevo";
		selectedVistas = new ArrayList<String>();
		return "role";
	}
	
	public String goRolEditar(Role rol){
		role = new Role();
		headerText = "Modificar Rol";
		role = rol;
		List<RolesVista> listAux = roleVistaDAO.getLista(rol);
		selectedVistas = new ArrayList<String>();
		for (RolesVista rolesVista : listAux) {
			selectedVistas.add(rolesVista.getVista().getNombre());
		}
		return "role";
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		if(!role.getNombre().isEmpty()){
			int idRole = 0;
			boolean update = false;
			if(role.getId() != 0){
				idRole = roleDAO.update(role);
				update = true;
			}else{
				idRole = roleDAO.insertar(role);
			}
			if(idRole != 0){
				role.setId(idRole);
				if(update){
					roleVistaDAO.deleteVistasPorRol(role);
				}
				List<Vista> listAux = vistaDAO.getLista();
				boolean insert = true;
				for(String nombre : selectedVistas){
					for(Vista vista : listAux){
						if(nombre.equals(vista.getNombre())){
							RolesVista roleVista = new RolesVista();
							roleVista.setRole(role);
							roleVista.setVista(vista);
							if(roleVistaDAO.insertar(roleVista) == 0){
								insert = false;
							}
						}
					}
				}
				if(insert){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rol guardado!", null);
					retorno = "roles";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar las Vistas asociadas al Rol, "
							+ "inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al guardar el Rol, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre no puede estar vacío!", null);
		}
		listaRoles = new ArrayList<Role>();
		filteredRoles = new ArrayList<Role>();
		listaRoles = roleDAO.getLista();
		filteredRoles = listaRoles;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

}
