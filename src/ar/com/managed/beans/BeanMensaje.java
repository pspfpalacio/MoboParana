package ar.com.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.entity.Mensaje;
import model.entity.Usuario;
import dao.interfaces.DAOMensaje;
import dao.interfaces.DAOUsuario;

@ManagedBean
@SessionScoped
public class BeanMensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanMensajeDAO}")
	private DAOMensaje mensajeDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	private List<Mensaje> listaMensajes;
	private Mensaje mensaje;
	private Usuario usuario;
	private String mensajeNuevo;
	private boolean verPanel;

	public DAOMensaje getMensajeDAO() {
		return mensajeDAO;
	}

	public void setMensajeDAO(DAOMensaje mensajeDAO) {
		this.mensajeDAO = mensajeDAO;
	}

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensajeNuevo() {
		return mensajeNuevo;
	}

	public void setMensajeNuevo(String mensajeNuevo) {
		this.mensajeNuevo = mensajeNuevo;
	}

	public boolean isVerPanel() {
		return verPanel;
	}

	public void setVerPanel(boolean verPanel) {
		this.verPanel = verPanel;
	}

	public String goMensajes(Usuario user) {
		listaMensajes = new ArrayList<Mensaje>();
		usuario = new Usuario();
		mensaje = new Mensaje();
		verPanel = false;
		usuario = user;
		listaMensajes = mensajeDAO.getLista();
		return "mensajes";
	}
	
	public boolean onIdle() {
		List<Mensaje> listMensaje = new ArrayList<Mensaje>(); 
		listMensaje = mensajeDAO.getLista(false);
		if (listMensaje.size() > 0) {
			mensajeNuevo = "(" + Integer.toString(listMensaje.size()) + ")";
//	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//	                                        "Mensaje Nuevo.", "Tiene mensajes nuevos!"));
		} else {
			mensajeNuevo = "";
		}
		return false;
    }
	
	public void verMensaje(Mensaje msj) {
		try {
			mensaje = new Mensaje();
			mensaje = msj;
			verPanel = true;
			msj.setVisto(true);
			mensajeDAO.update(msj);
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Ocurrió un error grave al abrir el mensaje.", "Contáctese con el administrador!"));
		}		
	}

}
