package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import ar.com.clases.auxiliares.Gastos;
import model.entity.Gasto;
import model.entity.Usuario;
import dao.interfaces.DAOGasto;

@ManagedBean
@SessionScoped
public class BeanGasto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanGastoDAO}")
	private DAOGasto gastoDAO;
	
	private List<Gasto> listaGastos;
	private List<Gasto> filteredGastos;
	private Gasto gasto;
	private Usuario usuario;
	private Date fechaDesde;
	private Date fechaHasta;
	private String headerText;
	private int estado;

	public DAOGasto getGastoDAO() {
		return gastoDAO;
	}

	public void setGastoDAO(DAOGasto gastoDAO) {
		this.gastoDAO = gastoDAO;
	}

	public List<Gasto> getListaGastos() {
		return listaGastos;
	}

	public void setListaGastos(List<Gasto> listaGastos) {
		this.listaGastos = listaGastos;
	}

	public List<Gasto> getFilteredGastos() {
		return filteredGastos;
	}

	public void setFilteredGastos(List<Gasto> filteredGastos) {
		this.filteredGastos = filteredGastos;
	}

	public Gasto getGasto() {
		return gasto;
	}

	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public String goGastos(Usuario user){
		listaGastos = new ArrayList<Gasto>();
		filteredGastos = new ArrayList<Gasto>();
		usuario = new Usuario();
		fechaDesde = null;
		fechaHasta = null;
		usuario = user;
		listaGastos = gastoDAO.getLista(true);
		filteredGastos = listaGastos;
		return "gastos";
	}
	
	public String goGastoNuevo(){
		headerText = "Gasto Nuevo";
		gasto = new Gasto();
		gasto.setFecha(new Date());
		return "gasto";
	}
	
	public void filtro(){
		listaGastos = new ArrayList<Gasto>();
		filteredGastos = new ArrayList<Gasto>();
		if(fechaHasta == null && fechaDesde == null){
			listaGastos = gastoDAO.getLista(true);
		}
		if(fechaHasta != null && fechaDesde != null){
			listaGastos = gastoDAO.getLista(true, fechaDesde, fechaHasta);
		}
		filteredGastos = listaGastos;
	}
	
	public void baja(Gasto gast){
		FacesMessage msg = null;
		gast.setEstado(false);
		gast.setFechaBaja(new Date());
		gast.setUsuario2(usuario);
		if(gastoDAO.update(gast) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Gasto!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Baja el Gasto, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		if(gasto.getFecha() != null){
			gasto.setFechaAlta(new Date());
			gasto.setUsuario1(usuario);
			gasto.setEstado(true);
			int idGasto = gastoDAO.insertar(gasto);
			if(idGasto != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gasto guardado!", null);
				retorno = "gastos";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Gasto, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha no puede estar vacío!", null);
		}
		listaGastos = new ArrayList<Gasto>();
		filteredGastos = new ArrayList<Gasto>();
		listaGastos = gastoDAO.getLista(true);
		filteredGastos = listaGastos;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarReporte(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Gastos> listGasto = new ArrayList<Gastos>();
		for (Gasto gast : filteredGastos) {
			Gastos gastos = new Gastos();
			gastos.setDescripcion(gast.getDescripcion());
			gastos.setFecha(formatoFecha.format(gast.getFecha()));
			gastos.setMonto(formatoMonto.format(gast.getMonto()));
			listGasto.add(gastos);
		}
//		if(estado == 0){
//			parametros.put("estado", "Todos");
//		}
//		if(estado == 1){
//			parametros.put("estado", "Alta");
//		}
//		if(estado == 2){
//			parametros.put("estado", "Baja");
//		}
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.generar(parametros, listGasto, "gastos", "inline");
	}

}
