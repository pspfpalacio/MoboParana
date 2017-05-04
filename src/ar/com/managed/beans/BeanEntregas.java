package ar.com.managed.beans;

import java.io.IOException;
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

import ar.com.clases.CuentaCorriente;
import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Pagos;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.CuentasCorrientesCliente;
import model.entity.EntregaConsignacion;
import model.entity.Usuario;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOEntregaConsignacion;

@ManagedBean
@SessionScoped
public class BeanEntregas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanEntregaConsignacionDAO}")
	private DAOEntregaConsignacion entregaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	private List<EntregaConsignacion> listaEntregaConsignacions;
	private EntregaConsignacion entregaConsignacion;
	private Consignacion consignacion;
	private Usuario usuario;

	public DAOEntregaConsignacion getEntregaConsignacionDAO() {
		return entregaConsignacionDAO;
	}

	public void setEntregaConsignacionDAO(
			DAOEntregaConsignacion entregaConsignacionDAO) {
		this.entregaConsignacionDAO = entregaConsignacionDAO;
	}

	public DAOConsignacion getConsignacionDAO() {
		return consignacionDAO;
	}

	public void setConsignacionDAO(DAOConsignacion consignacionDAO) {
		this.consignacionDAO = consignacionDAO;
	}

	public List<EntregaConsignacion> getListaEntregaConsignacions() {
		return listaEntregaConsignacions;
	}

	public void setListaEntregaConsignacions(
			List<EntregaConsignacion> listaEntregaConsignacions) {
		this.listaEntregaConsignacions = listaEntregaConsignacions;
	}

	public EntregaConsignacion getEntregaConsignacion() {
		return entregaConsignacion;
	}

	public void setEntregaConsignacion(EntregaConsignacion entregaConsignacion) {
		this.entregaConsignacion = entregaConsignacion;
	}

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void goEntregaConsignacion(Consignacion consig, Usuario user) {
		usuario = new Usuario();
		usuario = user;
		consignacion = new Consignacion();
		consignacion = consig;
		entregaConsignacion = new EntregaConsignacion();
		entregaConsignacion.setConsignacion(consig);	
		entregaConsignacion.setFecha(new Date());
		listaEntregaConsignacions = new ArrayList<EntregaConsignacion>();
		listaEntregaConsignacions = entregaConsignacionDAO.getLista(true, consig);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("entregas.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void guardar() {
		FacesMessage msg = null;
		if (entregaConsignacion.getFecha() != null) {
			entregaConsignacion.setEstado(true);
			int idEntrega = entregaConsignacionDAO.insertar(entregaConsignacion);
			if (idEntrega != 0) {
				entregaConsignacion.setId(idEntrega);
				Cliente cliente = consignacion.getCliente();
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				ccCliente.setCliente(cliente);
				ccCliente.setDetalle("Entrega Consignacion Nro: " + consignacion.getId() 
						+ " - Concepto: " + entregaConsignacion.getConcepto());
				ccCliente.setFecha(entregaConsignacion.getFecha());
				ccCliente.setHaber(entregaConsignacion.getMonto());
				ccCliente.setIdMovimiento(idEntrega);
				ccCliente.setNombreTabla("EntregaConsignacion");
				ccCliente.setMonto(entregaConsignacion.getMonto());
				ccCliente.setUsuario(usuario);
				int idCuentaCorriente = cuenta.insertarCC(ccCliente);
				Caja caja = new Caja();
				caja.setConcepto("Entrega recibida de: " + cliente.getNombreNegocio() + " - Concepto: Entrega de Consignacion Nro " + consignacion.getId());
				caja.setFecha(entregaConsignacion.getFecha());
				caja.setIdMovimiento(idEntrega);
				caja.setMonto(entregaConsignacion.getMonto());
				caja.setNombreTabla("EntregaConsignacion");
				caja.setUsuario(usuario);
				int idCaja = movimientoCaja.insertarCaja(caja);
				if (idCuentaCorriente != 0 && idCaja != 0) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Entrega guardada!", null);
					entregaConsignacion = new EntregaConsignacion();
					entregaConsignacion.setConsignacion(consignacion);
					entregaConsignacion.setFecha(new Date());
					listaEntregaConsignacions = new ArrayList<EntregaConsignacion>();
					listaEntregaConsignacions = entregaConsignacionDAO.getLista(true, consignacion);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la C.C. y la Caja!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al insertar la Entrega!", null);
			}			
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha no puede estar vacía!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void cancelar() {
		entregaConsignacion = new EntregaConsignacion();
		entregaConsignacion.setConsignacion(consignacion);
		entregaConsignacion.setFecha(new Date());
	}
	
	public void volver() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("consignaciones.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void baja(EntregaConsignacion entreg) {
		FacesMessage msg = null;
		if (!entreg.getCuota()) {
			entreg.setEstado(false);
			int estadoBaja = entregaConsignacionDAO.update(entreg);
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente cuentaAnterior = new CuentasCorrientesCliente();
			cuentaAnterior.setIdMovimiento(entreg.getId());
			cuentaAnterior.setNombreTabla("EntregaConsignacion");
			int deleteCuenta = cuenta.deleteCuentaCorriente(cuentaAnterior);
			MovimientoCaja movimientoCaja = new MovimientoCaja();
			Caja caja = new Caja();
			caja.setIdMovimiento(entreg.getId());
			caja.setNombreTabla("EntregaConsignacion");
			int deleteCaja = movimientoCaja.deleteCaja(caja);
			if (estadoBaja != 0 && deleteCuenta != 0 && deleteCaja != 0) {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Entrega!", null);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un Error al dar de baja la Entrega!", null);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible dar de baja la entrega, "
					+ "corresponde a una cuota! Las cuotas se dan de baja desde la pestaña Cuotas!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void generarReporte(){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Pagos> listPagos= new ArrayList<Pagos>();
		for (EntregaConsignacion entrega : listaEntregaConsignacions) {
			Pagos pagos = new Pagos();
			pagos.setConcepto(entrega.getConcepto());
			pagos.setFecha(formatoFecha.format(entrega.getFecha()));
			pagos.setMonto(formatoMonto.format(entrega.getMonto()));
			listPagos.add(pagos);
		}
		parametros.put("cliente", consignacion.getCliente().getNombreNegocio());
		parametros.put("nroConsignacion", consignacion.getId());
		reporte.generar(parametros, listPagos, "entregas", "inline");
	}
	
	public void generarReporteComprobante(EntregaConsignacion entrega){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Pagos> listPagos = new ArrayList<Pagos>();
		Pagos pagos = new Pagos();
		pagos.setNroConsignacion(Integer.toString(entrega.getConsignacion().getId()));
		pagos.setPersona(entrega.getConsignacion().getCliente().getNombreNegocio());
		pagos.setFecha(formatoFecha.format(entrega.getFecha()));
		pagos.setMonto(formatoMonto.format(entrega.getMonto()));
		pagos.setConcepto(entrega.getConcepto());
		listPagos.add(pagos);
		reporte.generar(parametros, listPagos, "entrega", "attachment");
	}
}
