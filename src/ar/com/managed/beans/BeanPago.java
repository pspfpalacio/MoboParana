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

import ar.com.clases.CuentaCorriente;
import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Pagos;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.PagosCliente;
import model.entity.PagosProveedore;
import model.entity.Proveedore;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOPago;
import dao.interfaces.DAOProveedor;

@ManagedBean
@SessionScoped
public class BeanPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanPagoDAO}")
	private DAOPago pagoDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	private List<Cliente> listaClientes;
	private List<Proveedore> listaProveedores;
	private PagosCliente pagoCliente;
	private PagosProveedore pagoProveedore;
	private Usuario usuario;
	private int idCliente;
	private int idProveedor;

	public DAOPago getPagoDAO() {
		return pagoDAO;
	}

	public void setPagoDAO(DAOPago pagoDAO) {
		this.pagoDAO = pagoDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOProveedor getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(DAOProveedor proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public List<Cliente> getListaClientes() {
		listaClientes = clienteDAO.getLista(true);
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Proveedore> getListaProveedores() {
		listaProveedores = proveedorDAO.getLista(true);
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public PagosCliente getPagoCliente() {
		return pagoCliente;
	}

	public void setPagoCliente(PagosCliente pagoCliente) {
		this.pagoCliente = pagoCliente;
	}

	public PagosProveedore getPagoProveedore() {
		return pagoProveedore;
	}

	public void setPagoProveedore(PagosProveedore pagoProveedore) {
		this.pagoProveedore = pagoProveedore;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public String goPagoCliente(Usuario user){
		idCliente = 0;
		pagoCliente = new PagosCliente();
		pagoCliente.setFecha(new Date());
		usuario = new Usuario();
		usuario = user;
		return "pagocliente";
	}
	
	public String goPagoProveedor(Usuario user){
		idProveedor = 0;
		pagoProveedore = new PagosProveedore();
		pagoProveedore.setFecha(new Date());
		usuario = new Usuario();
		usuario = user;
		return "pagoproveedor";
	}
	
	public String guardarPagoCliente(){
		FacesMessage msg = null;
		String retorno = "";
		if(pagoCliente.getFecha() != null && idCliente != 0 && pagoCliente.getMonto() != 0){
			Cliente cliente = clienteDAO.get(idCliente);
			pagoCliente.setCliente(cliente);
			pagoCliente.setFechaAlta(new Date());
			pagoCliente.setUsuario(usuario);
			int idPago = pagoDAO.insertar(pagoCliente);
			if(idPago != 0){
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				String conceptoP = pagoCliente.getConcepto();
				Date fechaP = pagoCliente.getFecha();
				float montoP = pagoCliente.getMonto();
				String nombreNegocio = cliente.getNombreNegocio();
				ccCliente.setCliente(cliente);
				ccCliente.setDetalle("Pago recibido - Concepto: " + conceptoP);
				ccCliente.setFecha(fechaP);
				ccCliente.setHaber(montoP);
				ccCliente.setIdMovimiento(idPago);
				ccCliente.setNombreTabla("PagosCliente");
				ccCliente.setMonto(montoP);
				ccCliente.setUsuario(usuario);
				int idCuentaCorriente = cuenta.insertarCC(ccCliente);
				Caja caja = new Caja();
				caja.setConcepto("Pago recibido de: " + nombreNegocio + " - Concepto : " + conceptoP);
				caja.setFecha(fechaP);
				caja.setIdMovimiento(idPago);
				caja.setMonto(montoP);
				caja.setNombreTabla("PagosCliente");
				caja.setUsuario(usuario);
				int idCaja = movimientoCaja.insertarCaja(caja);
				if(idCuentaCorriente != 0 && idCaja != 0){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago registrado!", null);
					idCliente = 0;
					pagoCliente = new PagosCliente();
					pagoCliente.setFecha(new Date());
					retorno = "pagocliente";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
							+ "inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
						+ "inténtelo nuevamente!", null);
			}			
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Cliente y Monto no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarPagoProveedor(){
		FacesMessage msg = null;
		String retorno = "";
		if(pagoProveedore.getFecha() != null && idProveedor != 0 && pagoProveedore.getMonto() != 0){
			Proveedore proveedore = proveedorDAO.get(idProveedor);
			pagoProveedore.setProveedore(proveedore);
			pagoProveedore.setFechaAlta(new Date());
			pagoProveedore.setUsuario(usuario);
			int idPago = pagoDAO.insertar(pagoProveedore);
			if(idPago != 0){
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
				String conceptoP = pagoProveedore.getConcepto();
				Date fechaP = pagoProveedore.getFecha();
				float montoP = pagoProveedore.getMonto();
				String nombreNegocio = proveedore.getNombreNegocio();
				ccProveedor.setProveedore(proveedore);
				ccProveedor.setDetalle("Pago recibido - Concepto: " + conceptoP);
				ccProveedor.setFecha(fechaP);
				ccProveedor.setHaber(montoP);
				ccProveedor.setIdMovimiento(idPago);
				ccProveedor.setNombreTabla("PagosProveedore");
				ccProveedor.setMonto(montoP);
				ccProveedor.setUsuario(usuario);
				int idCuentaCorriente = cuenta.insertarCC(ccProveedor);
				Caja caja = new Caja();
				caja.setConcepto("Pago realizado a: " + nombreNegocio + " - Concepto : " + conceptoP);
				caja.setFecha(fechaP);
				caja.setIdMovimiento(idPago);
				float montoCaja = (-1) * montoP;
				caja.setMonto(montoCaja);
				caja.setNombreTabla("PagosProveedore");
				caja.setUsuario(usuario);
				int idCaja = movimientoCaja.insertarCaja(caja);
				if(idCuentaCorriente != 0 && idCaja != 0){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago registrado!", null);
					idProveedor = 0;
					pagoProveedore = new PagosProveedore();
					pagoProveedore.setFecha(new Date());
					retorno = "pagoproveedor";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
							+ "inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
						+ "inténtelo nuevamente!", null);
			}			
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Proveedor y Monto no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarReporte(int idPagoC, int idPagoP){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Pagos> listPago = new ArrayList<Pagos>();
		if(idPagoC != 0){
			PagosCliente pagoC = pagoDAO.getPagoCliente(idPagoC);
			Pagos pagos = new Pagos();
			pagos.setConcepto(pagoC.getConcepto());
			pagos.setFecha(formatoFecha.format(pagoC.getFecha()));
			pagos.setMonto(formatoMonto.format(pagoC.getMonto()));
			pagos.setPersona("Recibi de: " + pagoC.getCliente().getNombreNegocio());
			listPago.add(pagos);
		}
		if(idPagoP != 0){
			PagosProveedore pagoP = pagoDAO.getPagoProveedore(idPagoP);
			Pagos pagos = new Pagos();
			pagos.setConcepto(pagoP.getConcepto());
			pagos.setFecha(formatoFecha.format(pagoP.getFecha()));
			pagos.setMonto(formatoMonto.format(pagoP.getMonto()));
			pagos.setPersona("Entregue a: " + pagoP.getProveedore().getNombreNegocio());
			listPago.add(pagos);
		}
		reporte.generar(parametros, listPago, "pago", "attachment");
	}

}
