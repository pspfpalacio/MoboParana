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

import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Cajas;
import model.entity.Caja;
import model.entity.Usuario;
import dao.interfaces.DAOCaja;

@ManagedBean
@SessionScoped
public class BeanCaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCajaDAO}")
	private DAOCaja cajaDAO;
	
	private List<Caja> listaCajas;
	private List<Caja> filteredCajas;
	private Caja caja;
	private Usuario usuario;
	private Date fechaDesde;
	private Date fechaHasta;
	private int tipo;

	public DAOCaja getCajaDAO() {
		return cajaDAO;
	}

	public void setCajaDAO(DAOCaja cajaDAO) {
		this.cajaDAO = cajaDAO;
	}

	public List<Caja> getListaCajas() {
		return listaCajas;
	}

	public void setListaCajas(List<Caja> listaCajas) {
		this.listaCajas = listaCajas;
	}

	public List<Caja> getFilteredCajas() {
		return filteredCajas;
	}

	public void setFilteredCajas(List<Caja> filteredCajas) {
		this.filteredCajas = filteredCajas;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
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
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String goCajas(Usuario user){
		listaCajas = new ArrayList<Caja>();
		filteredCajas = new ArrayList<Caja>();
		listaCajas = cajaDAO.getLista();
		filteredCajas = listaCajas;
		usuario = new Usuario();
		caja = new Caja();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		return "caja";
	}
	
	public void filtro(){
		listaCajas = new ArrayList<Caja>();
		filteredCajas = new ArrayList<Caja>();
		if(fechaDesde == null && fechaHasta == null){
			listaCajas = cajaDAO.getLista();
		}
		if(fechaDesde != null && fechaHasta != null){
			listaCajas = cajaDAO.getLista(fechaDesde, fechaHasta);
		}
		filteredCajas = listaCajas;
	}
	
	public void nuevo(){
		caja = new Caja();
	}
	
	public void guardar(){
		FacesMessage msg = null;
		float monto = caja.getMonto();
		if(tipo == 1){
			monto = monto * (-1);
		}
		caja.setMonto(monto);
		caja.setUsuario(usuario);
		MovimientoCaja cajaMovimiento = new MovimientoCaja();
		if(cajaMovimiento.insertarCaja(caja) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Movimiento de Caja guardado!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Movimiento de Caja, "
					+ "inténtelo nuevamente!", null);
		}
		listaCajas = new ArrayList<Caja>();
		filteredCajas = new ArrayList<Caja>();
		listaCajas = cajaDAO.getLista();
		filteredCajas = listaCajas;
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void generarReporte(){
		Reporte reporte = new Reporte();
		List<Cajas> listCaja = new ArrayList<Cajas>();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		for (Caja caj : filteredCajas) {
			Cajas cajas = new Cajas();
			cajas.setConcepto(caj.getConcepto());
			cajas.setFecha(formatoFecha.format(caj.getFecha()));
			cajas.setMonto(formatoMonto.format(caj.getMonto()));
			cajas.setSaldo(formatoMonto.format(caj.getSaldo()));
			listCaja.add(cajas);
		}
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.generar(parametros, listCaja, "caja", "inline");
	}

}
