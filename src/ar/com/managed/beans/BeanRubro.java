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

import model.entity.Rubro;
import model.entity.Usuario;
import dao.interfaces.DAORubro;

@ManagedBean
@SessionScoped
public class BeanRubro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanRubroDAO}")
	private DAORubro rubroDAO;
	
	private List<Rubro> listaRubros;
	private List<Rubro> filteredRubros;
	private Usuario usuario;
	private Rubro rubro;
	private String headerText;

	public DAORubro getRubroDAO() {
		return rubroDAO;
	}

	public void setRubroDAO(DAORubro rubroDAO) {
		this.rubroDAO = rubroDAO;
	}

	public List<Rubro> getListaRubros() {
		return listaRubros;
	}

	public void setListaRubros(List<Rubro> listaRubros) {
		this.listaRubros = listaRubros;
	}

	public List<Rubro> getFilteredRubros() {
		return filteredRubros;
	}

	public void setFilteredRubros(List<Rubro> filteredRubros) {
		this.filteredRubros = filteredRubros;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	
	public String goRubros(Usuario user){
		usuario = new Usuario();
		usuario = user;
		listaRubros = new ArrayList<Rubro>();
		filteredRubros = new ArrayList<Rubro>();
		listaRubros = rubroDAO.getLista();
		filteredRubros = listaRubros;
		return "rubros";
	}
	
	public String goNuevoRubro(){
		headerText = "Nuevo Rubro";
		rubro = new Rubro();
		return "rubro";
	}
	
	public String goEditarRubro(Rubro rub){
		headerText = "Editar Rubro";
		rubro = new Rubro();
		rubro = rub;
		return "rubro";
	}
	
	public void baja(Rubro rub){
		FacesMessage msg = null;
		rub.setEstado(false);
		rub.setFechaBaja(new Date());
		rub.setUsuario2(usuario);
		if(rubroDAO.update(rub) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Rubro!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de baja el Rubro, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Rubro rub){
		FacesMessage msg = null;
		rub.setEstado(true);
		rub.setFechaMod(new Date());
		rub.setUsuario3(usuario);
		if(rubroDAO.update(rub) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta de Rubro!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de alta el Rubro, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		if(rubro.getNombre() != null){
			int idRubro = 0;
			if(rubro.getId() != 0){
				rubro.setFechaMod(new Date());
				rubro.setUsuario3(usuario);
				idRubro = rubroDAO.update(rubro);
			}else{
				rubro.setEstado(true);
				rubro.setFechaAlta(new Date());
				rubro.setUsuario1(usuario);
				idRubro = rubroDAO.insertar(rubro);
			}
			if(idRubro != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rubro guardado!", null);
				retorno = "rubros";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Rubro!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Nombre no puede estar vacío!", null);
		}
		listaRubros = new ArrayList<Rubro>();
		filteredRubros = new ArrayList<Rubro>();
		listaRubros = rubroDAO.getLista();
		filteredRubros = listaRubros;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

}
