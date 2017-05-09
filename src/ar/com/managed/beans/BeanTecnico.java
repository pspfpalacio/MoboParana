package ar.com.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ar.com.clases.Reporte;
import model.entity.Tecnico;
import model.entity.Usuario;
import dao.interfaces.DAOTecnico;

@ManagedBean
@SessionScoped
public class BeanTecnico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanTecnicoDAO}")
	private DAOTecnico tecnicoDAO;
	
	private List<Tecnico> listaTecnicos;
	private Tecnico tecnico;
	private Usuario usuario;
	private String headerText;

	public DAOTecnico getTecnicoDAO() {
		return tecnicoDAO;
	}

	public void setTecnicoDAO(DAOTecnico tecnicoDAO) {
		this.tecnicoDAO = tecnicoDAO;
	}

	public List<Tecnico> getListaTecnicos() {
		return listaTecnicos;
	}

	public void setListaTecnicos(List<Tecnico> listaTecnicos) {
		this.listaTecnicos = listaTecnicos;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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

	public String goTecnicos(Usuario user) {
		usuario = new Usuario();
		listaTecnicos = new ArrayList<Tecnico>();
		usuario = user;
		listaTecnicos = tecnicoDAO.getLista();
		return "tecnicos";
	}
	
	public String goNuevo() {
		headerText = "Nuevo Técnico";
		tecnico = new Tecnico();
		return "tecnico";
	}
	
	public String goEditar(Tecnico tec) {
		headerText = "Editar Técnico";
		tecnico = new Tecnico();
		tecnico = tec;
		return "tecnico";
	}
	
	public void baja(Tecnico tec) {
		FacesMessage msg = null;
		try {
			tec.setEstado(false);
			tec.setFechaBaja(new Date());
			tec.setUsuario2(usuario);
			if(tecnicoDAO.update(tec) != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de técnico exitosa!", null);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de baja el técnico, "
						+ "inténtelo nuevamente!", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de baja el Técnico, error: "
					+ e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void alta(Tecnico tec) {
		FacesMessage msg = null;
		try {
			tec.setEstado(true);
			tec.setFechaMod(new Date());
			tec.setUsuario3(usuario);
			if(tecnicoDAO.update(tec) != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta de técnico exitosa!", null);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de alta el técnico, "
						+ "inténtelo nuevamente!", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de alta el Técnico, error: "
					+ e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardar() {
		FacesMessage msg = null;
		String retorno = "";
		try {
			if(!tecnico.getApellidoNombre().isEmpty()){
				int idTecnico = 0;			
				if(tecnico.getId() != 0){
					tecnico.setFechaMod(new Date());
					tecnico.setUsuario3(usuario);
					idTecnico = tecnicoDAO.update(tecnico);				
				}else{
					tecnico.setEstado(true);
					tecnico.setFechaAlta(new Date());
					tecnico.setUsuario1(usuario);
					idTecnico = tecnicoDAO.insertar(tecnico);
				}
				if(idTecnico != 0){
					listaTecnicos = new ArrayList<Tecnico>();
					listaTecnicos = tecnicoDAO.getLista();				
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Técnico guardado!", null);
					retorno = "tecnicos";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Técnico, "
							+ "inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Apellido y Nombre "
						+ "no puede estar vacío!", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al registrar el Técnico, error: "
					+ e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarPdf() {
		if (!listaTecnicos.isEmpty()) {
			try {				
				Reporte reporte = new Reporte();
				Map<String, Object> parametros = new HashMap<String, Object>();						
				reporte.generar(parametros, listaTecnicos, "tecnicos", "inline");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! Error original: " + e.getMessage(), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public void generarXls() {
		if (!listaTecnicos.isEmpty()) {
			try {				
				Reporte reporte = new Reporte();
				Map<String, Object> parametros = new HashMap<String, Object>();								
				reporte.exportXls(parametros, listaTecnicos, "excelTecnicos", "inline");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! Error original: " + e.getMessage(), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

}
