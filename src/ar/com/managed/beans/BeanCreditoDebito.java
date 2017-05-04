package ar.com.managed.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ar.com.clases.CuentaCorriente;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Proveedore;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOProveedor;

@ManagedBean
@SessionScoped
public class BeanCreditoDebito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	private List<Cliente> listaClientes;
	private List<Proveedore> listaProveedores;
	private Usuario usuario;
	private Date fecha;
	private String concepto;
	private int idPersona;
	private float monto;
	private int idTipo;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	
	public String goCreditoDebitoCliente(Usuario user){
		usuario = new Usuario();
		usuario = user;
		idPersona = 0;
		concepto = "";
		monto = 0;
		idTipo = 0;
		fecha = new Date();
		return "creditodebitocliente";
	}
	
	public String goCreditoDebitoProveedor(Usuario user){
		usuario = new Usuario();
		usuario = user;
		idPersona = 0;
		concepto = "";
		monto = 0;
		idTipo = 0;
		fecha = new Date();
		return "creditodebitoproveedor";
	}
	
	public String guardarEnCliente(){
		FacesMessage msg = null;
		String retorno = "";
		if(fecha != null && idPersona != 0 && monto != 0){
			String tipo = "";
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
			Cliente cliente = clienteDAO.get(idPersona);
			if(idTipo == 0){
				tipo = "Crédito";
				ccCliente.setHaber(monto);
			}
			if(idTipo == 1){
				tipo = "Débito";
				ccCliente.setDebe(monto);
			}
			ccCliente.setCliente(cliente);
			ccCliente.setDetalle(tipo + " - Concepto: " + concepto);
			ccCliente.setFecha(fecha);			
			ccCliente.setMonto(monto);
			ccCliente.setUsuario(usuario);
			int idCuentaCorriente = cuenta.insertarCC(ccCliente);
			if(idCuentaCorriente != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Movimiento de " + tipo + " generado con éxito", null);
				retorno = "creditodebitocliente";
				idPersona = 0;
				concepto = "";
				monto = 0;
				idTipo = 0;
				fecha = new Date();
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al generar el movimiento de " + tipo 
						+ ", inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Cliente y Monto no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarEnProveedor(){
		FacesMessage msg = null;
		String retorno = "";
		if(fecha != null && idPersona != 0 && monto != 0){
			String tipo = "";
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
			Proveedore proveedor = proveedorDAO.get(idPersona);
			if(idTipo == 0){
				tipo = "Crédito";
				ccProveedor.setHaber(monto);
			}
			if(idTipo == 1){
				tipo = "Débito";
				ccProveedor.setDebe(monto);
			}
			ccProveedor.setProveedore(proveedor);
			ccProveedor.setDetalle(tipo + " - Concepto: " + concepto);
			ccProveedor.setFecha(fecha);
			ccProveedor.setMonto(monto);
			ccProveedor.setUsuario(usuario);
			int idCuentaCorriente = cuenta.insertarCC(ccProveedor);
			if(idCuentaCorriente != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Movimiento de " + tipo + " generado con éxito", null);
				retorno = "creditodebitoproveedor";
				idPersona = 0;
				concepto = "";
				monto = 0;
				idTipo = 0;
				fecha = new Date();
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al generar el movimiento de " + tipo 
						+ ", inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Proveedor y Monto no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

}
