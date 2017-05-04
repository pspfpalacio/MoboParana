package ar.com.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ar.com.clases.Helper;
import model.entity.Cliente;
import model.entity.Role;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAORole;
import dao.interfaces.DAOUsuario;

@ManagedBean
@SessionScoped
public class BeanUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanRoleDAO}")
	private DAORole roleDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	private List<Usuario> listaUsuarios;
	private List<Usuario> filteredUsuarios;
	private List<Role> listaRoles;
	private List<Cliente> listaClientes;
	private Usuario usuario;
	private Usuario usuarioAuditoria;
	private Helper helper;
	private String headerText;
	private String newPass;
	private String repeatPass;
	private String texto;
	private int estado;
	private int idRole;
	private int idCliente;
	private boolean checkPass;

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DAORole getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(DAORole roleDAO) {
		this.roleDAO = roleDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public List<Role> getListaRoles() {
		listaRoles = roleDAO.getLista();
		return listaRoles;
	}

	public void setListaRoles(List<Role> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(Usuario usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Helper getHelper() {
		return helper;
	}

	public void setHelper(Helper helper) {
		this.helper = helper;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public boolean isCheckPass() {
		return checkPass;
	}

	public void setCheckPass(boolean checkPass) {
		this.checkPass = checkPass;
	}

	public String goUsuarios(Usuario user){
		listaUsuarios = new ArrayList<Usuario>();
		filteredUsuarios = new ArrayList<Usuario>();
		usuarioAuditoria = new Usuario();
		usuarioAuditoria = user;
		listaUsuarios = usuarioDAO.getLista(true);
		filteredUsuarios = listaUsuarios;
		estado = 0;
		idRole = 0;
		return "usuarios";
	}
	
	public String goUsuarioNuevo(){
		headerText = "Usuario Nuevo";
		usuario = new Usuario();
		listaClientes = new ArrayList<Cliente>();
		idRole = 0;
		idCliente = 0;
		newPass = "";
		repeatPass = "";
		checkPass = false;
		listaClientes = clienteDAO.getLista(true);
		return "usuario";
	}
	
	public String goUsuarioEditar(Usuario user){
		headerText = "Modificar Usuario";
		usuario = new Usuario();
		usuario = user;
		idRole = user.getRole().getId();
		idCliente = 0;
		if (user.getCliente() != null) {
			idCliente = user.getCliente().getId();
		}
		listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(true);
		repeatPass = "";
		newPass = "";
		checkPass = false;
		return "usuario";
	}
	
	public void handleKeyEvent() {
		if (newPass.equals(repeatPass)) {
			texto = "Ok";
			checkPass = false;
		} else {
			texto = "No";
			checkPass = true;
		}
	}
	
	public void filtro(){
		listaUsuarios = new ArrayList<Usuario>();
		filteredUsuarios = new ArrayList<Usuario>();
		if(idRole == 0){
			listaUsuarios = usuarioDAO.getLista(true);
		}
		if(idRole != 0){
			Role rol = new Role();
			rol.setId(idRole);
			listaUsuarios = usuarioDAO.getLista(true, rol);
		}
		filteredUsuarios = listaUsuarios;
	}
	
	public void alta(Usuario user){
		FacesMessage msg = null;
		user.setFechaMod(new Date());
		user.setUsuario3(usuarioAuditoria);
		user.setEstado(true);
		if(usuarioDAO.update(user) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta de Usuario!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Alta el Usuario, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(Usuario user){
		FacesMessage msg = null;
		user.setFechaBaja(new Date());
		user.setUsuario2(usuarioAuditoria);
		user.setEstado(false);
		if(usuarioDAO.update(user) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Usuario!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Baja el Usuario, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		boolean helpearPass = true;
		if(usuario.getId() != 0){
			if(repeatPass.isEmpty()){
				repeatPass = usuario.getPassword();
				helpearPass = false;
			}
		}
		if(!usuario.getApellidoNombre().isEmpty() && !usuario.getUsername().isEmpty() && !repeatPass.isEmpty() && idRole != 0){
			if(helpearPass){
				helper = new Helper();
				String password = helper.EncodePassword(repeatPass);
				usuario.setPassword(password);
			}
			Role rol = new Role();
			rol.setId(idRole);
			usuario.setRole(rol);
			if (idRole == 3) {
				Cliente cli = clienteDAO.get(idCliente);
				usuario.setCliente(cli);
			} else {
				usuario.setCliente(null);
			}
			int idUsuario = 0;
			if(usuario.getId() != 0){
				usuario.setFechaMod(new Date());
				usuario.setUsuario3(usuarioAuditoria);
				idUsuario = usuarioDAO.update(usuario);
			}else{
				usuario.setEstado(true);
				usuario.setFechaAlta(new Date());
				usuario.setUsuario1(usuarioAuditoria);
				idUsuario = usuarioDAO.insertar(usuario);
			}
			if(idUsuario != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado!", null);
				retorno = "usuarios";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Usuario, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Apellido y Nombre, Username, Contraseña, y Rol No pueden estar vacíos!", null);
		}
		listaUsuarios = new ArrayList<Usuario>();
		filteredUsuarios = new ArrayList<Usuario>();
		listaUsuarios = usuarioDAO.getLista(true);
		filteredUsuarios = listaUsuarios;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

}
