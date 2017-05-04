package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
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
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Proveedores;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Proveedore;
import model.entity.Usuario;
import dao.interfaces.DAOProveedor;

@ManagedBean
@SessionScoped
public class BeanProveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	private List<Proveedore> listaProveedores;
	private List<Proveedore> filteredProveedores;
	private Proveedore proveedor;
	private Usuario usuario;
	private String headerText;
	private int estado;
	private float saldo;
	private float saldoAnterior;

	public DAOProveedor getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(DAOProveedor proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public List<Proveedore> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public List<Proveedore> getFilteredProveedores() {
		return filteredProveedores;
	}

	public void setFilteredProveedores(List<Proveedore> filteredProveedores) {
		this.filteredProveedores = filteredProveedores;
	}

	public Proveedore getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedore proveedor) {
		this.proveedor = proveedor;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(float saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public String goProveedores(Usuario user){
		listaProveedores = new ArrayList<Proveedore>();
		filteredProveedores = new ArrayList<Proveedore>();
		listaProveedores = proveedorDAO.getLista(true);
		filteredProveedores = listaProveedores;
		estado = 0;
		usuario = new Usuario();
		usuario = user;
		return "proveedores";
	}
	
	public String goProveedorNuevo(){
		proveedor = new Proveedore();
		headerText = "Proveedor Nuevo";
		return "proveedor";
	}
	
	public String goProveedorEditar(Proveedore prov){
		proveedor = new Proveedore();
		headerText = "Modificar Proveedor";
		proveedor = prov;
		saldoAnterior = prov.getSaldo();
		saldo = saldoAnterior;
		return "proveedor";
	}
	
	public void filtroProveedor(){
		listaProveedores = new ArrayList<Proveedore>();
		filteredProveedores = new ArrayList<Proveedore>();
		if(estado == 0){
			listaProveedores = proveedorDAO.getLista();
		}
		if(estado == 1){
			listaProveedores = proveedorDAO.getLista(true);
		}
		if(estado == 2){
			listaProveedores = proveedorDAO.getLista(false);
		}
		filteredProveedores = listaProveedores;
	}
	
	public void alta(Proveedore prov){
		FacesMessage msg = null;
		prov.setEstado(true);
		prov.setFechaAlta(new Date());
		prov.setUsuario1(usuario);
		if(proveedorDAO.update(prov) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta de Proveedor!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Alta el Proveedor, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(Proveedore prov){
		FacesMessage msg = null;
		prov.setEstado(false);
		prov.setFechaBaja(new Date());
		prov.setUsuario2(usuario);
		if(proveedorDAO.update(prov) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Proveedor!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Baja el Proveedor, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardarProveedor(){
		FacesMessage msg = null;
		String retorno = "";
		if(!proveedor.getApellidoNombre().isEmpty() && !proveedor.getNombreNegocio().isEmpty()){
			int idProveedor = 0;
			boolean update = false;
			if(proveedor.getId() != 0){
				proveedor.setFechaMod(new Date());
				proveedor.setUsuario3(usuario);
				idProveedor = proveedorDAO.update(proveedor);
				update = true;
			}else{
				proveedor.setEstado(true);
				proveedor.setFechaAlta(new Date());
				proveedor.setUsuario1(usuario);
				idProveedor = proveedorDAO.insertar(proveedor);
			}
			if(idProveedor != 0){
				proveedor.setId(idProveedor);
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
				if(update){
					if(saldoAnterior != saldo){
						ccProveedor.setProveedore(proveedor);
						ccProveedor.setDetalle("Modificación de Cuenta Corriente");
						ccProveedor.setFecha(new Date());
						ccProveedor.setUsuario(usuario);
						float sldo = saldo;
						float monto = 0;
						if(sldo > saldoAnterior){
							monto = sldo - saldoAnterior;
							ccProveedor.setDebe(monto);
						}
						if(sldo < saldoAnterior){
							monto = saldoAnterior - sldo;
							ccProveedor.setHaber(monto);
						}
						ccProveedor.setMonto(monto);
						cuenta.insertarCC(ccProveedor);
					}
				}else{
					if(saldo != 0){
						ccProveedor.setProveedore(proveedor);
						ccProveedor.setDetalle("Inicialización de Cuenta Corriente");
						ccProveedor.setFecha(new Date());
						ccProveedor.setUsuario(usuario);
						float sldo = saldo;
						if(sldo > 0){
							ccProveedor.setDebe(sldo);
						}
						if(sldo < 0){
							sldo = sldo * (-1);
							ccProveedor.setHaber(sldo);
						}
						ccProveedor.setMonto(sldo);
						cuenta.insertarCC(ccProveedor);
					}
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor guardado!", null);
				retorno = "proveedores";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Proveedor, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Apellido y Nombre, Nombre de Negocio "
					+ "no pueden estar vacíos!", null);
		}
		listaProveedores = new ArrayList<Proveedore>();
		filteredProveedores = new ArrayList<Proveedore>();
		listaProveedores = proveedorDAO.getLista(true);
		filteredProveedores = listaProveedores;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarReporte(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Proveedores> listProveedor = new ArrayList<Proveedores>();
		for (Proveedore prov : filteredProveedores) {
			Proveedores proveedores = new Proveedores();
			proveedores.setApellidoNombre(prov.getApellidoNombre());
			proveedores.setBanco(prov.getBanco());
			proveedores.setDireccion(prov.getDireccion());
			proveedores.setEmail(prov.getEmail());
			proveedores.setNombreNegocio(prov.getNombreNegocio());
			proveedores.setNroCliente(prov.getNroCliente());
			proveedores.setNroCuenta(prov.getNroCuenta());
			proveedores.setSaldo(formatoMonto.format(prov.getSaldo()));
			proveedores.setSucursal(prov.getSucursal());
			proveedores.setTelefono(prov.getTelefono());
			listProveedor.add(proveedores);
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
		reporte.generar(parametros, listProveedor, "proveedores", "inline");
	}

}
