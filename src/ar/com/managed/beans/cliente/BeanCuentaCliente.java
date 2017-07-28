package ar.com.managed.beans.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOUsuario;

@ManagedBean
@SessionScoped
public class BeanCuentaCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	private List<CuentasCorrientesCliente> listaCuentasCorrientes;
	private Usuario usuario;
	private Cliente cliente;
	private Date fechaDesde;
	private Date fechaHasta;

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public List<CuentasCorrientesCliente> getListaCuentasCorrientes() {
		return listaCuentasCorrientes;
	}

	public void setListaCuentasCorrientes(
			List<CuentasCorrientesCliente> listaCuentasCorrientes) {
		this.listaCuentasCorrientes = listaCuentasCorrientes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public String goCuentaCorriente(Usuario user){
		try {
			fechaDesde = null;
			fechaHasta = null;
			cliente = new Cliente();
			usuario = new Usuario();
			cliente = user.getCliente();
			usuario = user;		
			listaCuentasCorrientes = new ArrayList<CuentasCorrientesCliente>();		
			listaCuentasCorrientes = cuentaCorrienteDAO.getLista(cliente);
			if(!listaCuentasCorrientes.isEmpty()){
				return "cuentascorrientes";
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
	                    "No se puede redirigir al formulario de cuentas corrientes, no existen movimientos.", "Ante cualquier duda contáctese con el administrador."));
				return "";
			}
		} catch(Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Ocurrió un error al redirigir al formulario de cuentas corrientes.", "Contáctese con el administrador."));
			return "";
		}
	}
	
	public void buscar(){
		if (fechaDesde == null && fechaHasta == null) {
			listaCuentasCorrientes = new ArrayList<CuentasCorrientesCliente>();			
			listaCuentasCorrientes = cuentaCorrienteDAO.getLista(cliente);			
		}
		if(fechaDesde != null && fechaHasta != null){
			listaCuentasCorrientes = new ArrayList<CuentasCorrientesCliente>();			
			listaCuentasCorrientes = cuentaCorrienteDAO.getLista(cliente, fechaDesde, fechaHasta);			
		}		
	}

}
