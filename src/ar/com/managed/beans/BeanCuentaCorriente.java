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

import org.apache.log4j.Logger;

import ar.com.clases.CuentaCorriente;
import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Cuentas;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.Compra;
import model.entity.Consignacion;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.CuotasDetalle;
import model.entity.CuotasVentasDetalle;
import model.entity.Devolucione;
import model.entity.EntregaConsignacion;
import model.entity.EquipoPendientePago;
import model.entity.GarantiasCliente;
import model.entity.GarantiasProveedore;
import model.entity.PagosCliente;
import model.entity.PagosProveedore;
import model.entity.Producto;
import model.entity.Proveedore;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasCon;
import dao.interfaces.DAOCompra;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOCuotaDetalle;
import dao.interfaces.DAOCuotaVentaDetalle;
import dao.interfaces.DAODevolucion;
import dao.interfaces.DAOEntregaConsignacion;
import dao.interfaces.DAOEquipoPendientePago;
import dao.interfaces.DAOGarantiasCliente;
import dao.interfaces.DAOGarantiasProveedor;
import dao.interfaces.DAOPago;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaConsignacion;

@ManagedBean
@SessionScoped
public class BeanCuentaCorriente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanCuentaCorriente.class);
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	@ManagedProperty(value = "#{BeanPagoDAO}")
	private DAOPago pagoDAO;
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanDevolucionDAO}")
	private DAODevolucion devolucionDAO;
	
	@ManagedProperty(value = "#{BeanCompraDAO}")
	private DAOCompra compraDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanEntregaConsignacionDAO}")
	private DAOEntregaConsignacion entregaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDetalleDAO}")
	private DAOCuotaDetalle cuotaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanCuotaVentaDetalleDAO}")
	private DAOCuotaVentaDetalle cuotaVentaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanGarantiasClienteDAO}")
	private DAOGarantiasCliente garantiasClienteDAO;
	
	@ManagedProperty(value = "#{BeanGarantiasProveedorDAO}")
	private DAOGarantiasProveedor garantiasProveedorDAO;
	
	@ManagedProperty(value = "#{BeanEquipoPendientePagoDAO}")
	private DAOEquipoPendientePago equipoPendientePagoDAO;
	
	private List<CuentasCorrientesCliente> listaCuentasCorrientesClientes;
	private List<CuentasCorrientesCliente> filteredCuentasCorrientesClientes;
	private List<CuentasCorrientesProveedore> listaCuentasCorrientesProveedores;
	private List<CuentasCorrientesProveedore> filteredCuentasCorrientesProveedores;
	private List<GarantiasCliente> listaGarantiasClientes;
	private List<GarantiasCliente> filteredGarantiasClientes;
	private List<GarantiasProveedore> listaGarantiasProveedores;
	private List<GarantiasProveedore> filteredGarantiasProveedores;
	private List<Devolucione> listaDevolucionClientes;
	private List<Devolucione> filteredDevolucionClientes;
	private List<Producto> listaProductos;
	private Usuario usuario;
	private Cliente cliente;
	private Proveedore proveedor;
	private PagosCliente pagoCliente;
	private PagosProveedore pagoProveedore;
	private EntregaConsignacion entregaConsignacion;
	private CuotasDetalle cuotasDetalle;
	private CuotasVentasDetalle cuotasVentasDetalle;
	private Compra compra;
	private Venta venta;
	private Consignacion consignacion;
	private Devolucione devolucion;
	private VentasCon ventasCon;
	private Date fechaInicio;
	private Date fechaFin;
	private int idProducto;
	private boolean pago;
	private boolean panelEntrega;
	private boolean panelCuota;
	private boolean panelCuotaVenta;
	private boolean panelCompra;
	private boolean panelVenta;
	private boolean panelConsignacion;
	private boolean panelVentaCons;
	private boolean onVenta;
	private boolean existeGarantia;
	private boolean existeDevolucion;

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public DAOPago getPagoDAO() {
		return pagoDAO;
	}

	public void setPagoDAO(DAOPago pagoDAO) {
		this.pagoDAO = pagoDAO;
	}

	public DAOVenta getVentaDAO() {
		return ventaDAO;
	}

	public void setVentaDAO(DAOVenta ventaDAO) {
		this.ventaDAO = ventaDAO;
	}

	public DAODevolucion getDevolucionDAO() {
		return devolucionDAO;
	}

	public void setDevolucionDAO(DAODevolucion devolucionDAO) {
		this.devolucionDAO = devolucionDAO;
	}

	public DAOCompra getCompraDAO() {
		return compraDAO;
	}

	public void setCompraDAO(DAOCompra compraDAO) {
		this.compraDAO = compraDAO;
	}

	public DAOConsignacion getConsignacionDAO() {
		return consignacionDAO;
	}

	public void setConsignacionDAO(DAOConsignacion consignacionDAO) {
		this.consignacionDAO = consignacionDAO;
	}

	public DAOEntregaConsignacion getEntregaConsignacionDAO() {
		return entregaConsignacionDAO;
	}

	public void setEntregaConsignacionDAO(
			DAOEntregaConsignacion entregaConsignacionDAO) {
		this.entregaConsignacionDAO = entregaConsignacionDAO;
	}

	public DAOCuotaDetalle getCuotaDetalleDAO() {
		return cuotaDetalleDAO;
	}

	public void setCuotaDetalleDAO(DAOCuotaDetalle cuotaDetalleDAO) {
		this.cuotaDetalleDAO = cuotaDetalleDAO;
	}

	public DAOCuotaVentaDetalle getCuotaVentaDetalleDAO() {
		return cuotaVentaDetalleDAO;
	}

	public void setCuotaVentaDetalleDAO(DAOCuotaVentaDetalle cuotaVentaDetalleDAO) {
		this.cuotaVentaDetalleDAO = cuotaVentaDetalleDAO;
	}

	public DAOVentaConsignacion getVentaConsignacionDAO() {
		return ventaConsignacionDAO;
	}

	public void setVentaConsignacionDAO(DAOVentaConsignacion ventaConsignacionDAO) {
		this.ventaConsignacionDAO = ventaConsignacionDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOGarantiasCliente getGarantiasClienteDAO() {
		return garantiasClienteDAO;
	}

	public void setGarantiasClienteDAO(DAOGarantiasCliente garantiasClienteDAO) {
		this.garantiasClienteDAO = garantiasClienteDAO;
	}

	public DAOGarantiasProveedor getGarantiasProveedorDAO() {
		return garantiasProveedorDAO;
	}

	public void setGarantiasProveedorDAO(DAOGarantiasProveedor garantiasProveedorDAO) {
		this.garantiasProveedorDAO = garantiasProveedorDAO;
	}

	public DAOEquipoPendientePago getEquipoPendientePagoDAO() {
		return equipoPendientePagoDAO;
	}

	public void setEquipoPendientePagoDAO(DAOEquipoPendientePago equipoPendientePagoDAO) {
		this.equipoPendientePagoDAO = equipoPendientePagoDAO;
	}

	public List<CuentasCorrientesCliente> getListaCuentasCorrientesClientes() {
		return listaCuentasCorrientesClientes;
	}

	public void setListaCuentasCorrientesClientes(
			List<CuentasCorrientesCliente> listaCuentasCorrientesClientes) {
		this.listaCuentasCorrientesClientes = listaCuentasCorrientesClientes;
	}

	public List<CuentasCorrientesCliente> getFilteredCuentasCorrientesClientes() {
		return filteredCuentasCorrientesClientes;
	}

	public void setFilteredCuentasCorrientesClientes(
			List<CuentasCorrientesCliente> filteredCuentasCorrientesClientes) {
		this.filteredCuentasCorrientesClientes = filteredCuentasCorrientesClientes;
	}

	public List<CuentasCorrientesProveedore> getListaCuentasCorrientesProveedores() {
		return listaCuentasCorrientesProveedores;
	}

	public void setListaCuentasCorrientesProveedores(
			List<CuentasCorrientesProveedore> listaCuentasCorrientesProveedores) {
		this.listaCuentasCorrientesProveedores = listaCuentasCorrientesProveedores;
	}

	public List<CuentasCorrientesProveedore> getFilteredCuentasCorrientesProveedores() {
		return filteredCuentasCorrientesProveedores;
	}

	public void setFilteredCuentasCorrientesProveedores(
			List<CuentasCorrientesProveedore> filteredCuentasCorrientesProveedores) {
		this.filteredCuentasCorrientesProveedores = filteredCuentasCorrientesProveedores;
	}
	
	public List<GarantiasCliente> getListaGarantiasClientes() {
		return listaGarantiasClientes;
	}

	public void setListaGarantiasClientes(
			List<GarantiasCliente> listaGarantiasClientes) {
		this.listaGarantiasClientes = listaGarantiasClientes;
	}

	public List<GarantiasCliente> getFilteredGarantiasClientes() {
		return filteredGarantiasClientes;
	}

	public void setFilteredGarantiasClientes(
			List<GarantiasCliente> filteredGarantiasClientes) {
		this.filteredGarantiasClientes = filteredGarantiasClientes;
	}

	public List<GarantiasProveedore> getListaGarantiasProveedores() {
		return listaGarantiasProveedores;
	}

	public void setListaGarantiasProveedores(
			List<GarantiasProveedore> listaGarantiasProveedores) {
		this.listaGarantiasProveedores = listaGarantiasProveedores;
	}

	public List<GarantiasProveedore> getFilteredGarantiasProveedores() {
		return filteredGarantiasProveedores;
	}

	public void setFilteredGarantiasProveedores(
			List<GarantiasProveedore> filteredGarantiasProveedores) {
		this.filteredGarantiasProveedores = filteredGarantiasProveedores;
	}

	public List<Devolucione> getListaDevolucionClientes() {
		return listaDevolucionClientes;
	}

	public void setListaDevolucionClientes(List<Devolucione> listaDevolucionClientes) {
		this.listaDevolucionClientes = listaDevolucionClientes;
	}

	public List<Devolucione> getFilteredDevolucionClientes() {
		return filteredDevolucionClientes;
	}

	public void setFilteredDevolucionClientes(
			List<Devolucione> filteredDevolucionClientes) {
		this.filteredDevolucionClientes = filteredDevolucionClientes;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
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

	public Proveedore getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedore proveedor) {
		this.proveedor = proveedor;
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

	public EntregaConsignacion getEntregaConsignacion() {
		return entregaConsignacion;
	}

	public void setEntregaConsignacion(EntregaConsignacion entregaConsignacion) {
		this.entregaConsignacion = entregaConsignacion;
	}

	public CuotasDetalle getCuotasDetalle() {
		return cuotasDetalle;
	}

	public void setCuotasDetalle(CuotasDetalle cuotasDetalle) {
		this.cuotasDetalle = cuotasDetalle;
	}

	public CuotasVentasDetalle getCuotasVentasDetalle() {
		return cuotasVentasDetalle;
	}

	public void setCuotasVentasDetalle(CuotasVentasDetalle cuotasVentasDetalle) {
		this.cuotasVentasDetalle = cuotasVentasDetalle;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public Devolucione getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucione devolucion) {
		this.devolucion = devolucion;
	}

	public VentasCon getVentasCon() {
		return ventasCon;
	}

	public void setVentasCon(VentasCon ventasCon) {
		this.ventasCon = ventasCon;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public boolean isPanelEntrega() {
		return panelEntrega;
	}

	public void setPanelEntrega(boolean panelEntrega) {
		this.panelEntrega = panelEntrega;
	}

	public boolean isPanelCuota() {
		return panelCuota;
	}

	public void setPanelCuota(boolean panelCuota) {
		this.panelCuota = panelCuota;
	}

	public boolean isPanelCuotaVenta() {
		return panelCuotaVenta;
	}

	public void setPanelCuotaVenta(boolean panelCuotaVenta) {
		this.panelCuotaVenta = panelCuotaVenta;
	}

	public boolean isPanelCompra() {
		return panelCompra;
	}

	public void setPanelCompra(boolean panelCompra) {
		this.panelCompra = panelCompra;
	}

	public boolean isPanelVenta() {
		return panelVenta;
	}

	public void setPanelVenta(boolean panelVenta) {
		this.panelVenta = panelVenta;
	}

	public boolean isPanelConsignacion() {
		return panelConsignacion;
	}

	public void setPanelConsignacion(boolean panelConsignacion) {
		this.panelConsignacion = panelConsignacion;
	}

	public boolean isPanelVentaCons() {
		return panelVentaCons;
	}

	public void setPanelVentaCons(boolean panelVentaCons) {
		this.panelVentaCons = panelVentaCons;
	}

	public boolean isOnVenta() {
		return onVenta;
	}

	public void setOnVenta(boolean onVenta) {
		this.onVenta = onVenta;
	}

	public boolean isExisteGarantia() {
		return existeGarantia;
	}

	public void setExisteGarantia(boolean existeGarantia) {
		this.existeGarantia = existeGarantia;
	}
	
	public boolean isExisteDevolucion() {
		return existeDevolucion;
	}

	public void setExisteDevolucion(boolean existeDevolucion) {
		this.existeDevolucion = existeDevolucion;
	}

	public String goCuentaCorrienteCliente(Cliente cli, Usuario user){
		String retorno = "";
		idProducto = 0;
		pago = false;
		panelEntrega = false;
		panelCuota = false;
		panelCuotaVenta = false;
		panelVenta = false;
		panelConsignacion = false;
		panelVentaCons = false;
		onVenta = false;
		existeGarantia = false;
		existeDevolucion = false;
		pagoCliente = new PagosCliente();
		entregaConsignacion = new EntregaConsignacion();
		cuotasDetalle = new CuotasDetalle();
		cuotasVentasDetalle = new CuotasVentasDetalle();
		venta = new Venta();
		consignacion = new Consignacion();
		devolucion = new Devolucione();
		ventasCon = new VentasCon();
		fechaInicio = null;
		fechaFin = null;
		listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
		filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
		listaGarantiasClientes = new ArrayList<GarantiasCliente>();
		filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
		listaDevolucionClientes = new ArrayList<Devolucione>();
		filteredDevolucionClientes = new ArrayList<Devolucione>();
		listaProductos = new ArrayList<Producto>();
		listaProductos = productoDAO.getLista(true);
		listaCuentasCorrientesClientes = cuentaCorrienteDAO.getLista(cli);
		listaGarantiasClientes = garantiasClienteDAO.getLista(cli);
		listaDevolucionClientes = devolucionDAO.getLista(true, cli);
		filteredGarantiasClientes = listaGarantiasClientes;
		filteredDevolucionClientes = listaDevolucionClientes;
		if (!listaGarantiasClientes.isEmpty()) {
			existeGarantia = true;
		}
		if (!listaDevolucionClientes.isEmpty()) {
			existeDevolucion = true;
		}
		usuario = new Usuario();
		cliente = new Cliente();
		usuario = user;
		cliente = cli;
		if(!listaCuentasCorrientesClientes.isEmpty()){
			filteredCuentasCorrientesClientes = listaCuentasCorrientesClientes;
			retorno = "cuentacliente";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Cliente no posee movimientos en su Cuenta Corriente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return retorno;
	}
	
	public String goCuentaCorrienteProveedor(Proveedore prov, Usuario user){
		String retorno = "";
		pago = false;
		panelCompra = false;
		existeGarantia = false;
		pagoProveedore = new PagosProveedore();
		compra = new Compra();
		fechaInicio = null;
		fechaFin = null;
		listaCuentasCorrientesProveedores = new ArrayList<CuentasCorrientesProveedore>();
		filteredCuentasCorrientesProveedores = new ArrayList<CuentasCorrientesProveedore>();
		listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
		filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
		listaCuentasCorrientesProveedores = cuentaCorrienteDAO.getListaProveedor(prov);
		listaGarantiasProveedores = garantiasProveedorDAO.getLista(prov);
		filteredGarantiasProveedores = listaGarantiasProveedores;
		if (!listaGarantiasProveedores.isEmpty()) {
			existeGarantia = true;
		}		
		usuario = new Usuario();
		proveedor = new Proveedore();
		usuario = user;
		proveedor = prov;
		if(!listaCuentasCorrientesProveedores.isEmpty()){
			filteredCuentasCorrientesProveedores = listaCuentasCorrientesProveedores;
			retorno = "cuentaproveedor";
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Proveedor no posee movimientos en su Cuenta Corriente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return retorno;
	}
	
	public void filtroCliente(){
		if (fechaInicio == null && fechaFin == null && idProducto == 0) {
			listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
			filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
			listaCuentasCorrientesClientes = cuentaCorrienteDAO.getLista(cliente);
			filteredCuentasCorrientesClientes = listaCuentasCorrientesClientes;
		}
		if(fechaInicio != null && fechaFin != null && idProducto == 0){
			listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
			filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
			listaCuentasCorrientesClientes = cuentaCorrienteDAO.getLista(cliente, fechaInicio, fechaFin);
			filteredCuentasCorrientesClientes = listaCuentasCorrientesClientes;
		}
		if (fechaInicio == null && fechaFin == null && idProducto != 0) {
			Producto producto = productoDAO.get(idProducto);			
			if (onVenta) {
				List<Venta> listAuxVenta = ventaDAO.getLista(producto, cliente);
				if (listAuxVenta.isEmpty()) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen ventas realizadas al Cliente " +
							cliente.getNombreNegocio() + ", para ese producto!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					List<CuentasCorrientesCliente> listAuxCC = new ArrayList<CuentasCorrientesCliente>();
					for (Venta ven : listAuxVenta) {
						int nroVenta = ven.getId();
						List<CuentasCorrientesCliente> listCCs = cuentaCorrienteDAO.getLista(nroVenta, "Venta");
						for (CuentasCorrientesCliente cuentasCorrientesCliente : listCCs) {
							listAuxCC.add(cuentasCorrientesCliente);
						}
					}
					listaCuentasCorrientesClientes = listAuxCC;
					filteredCuentasCorrientesClientes = listAuxCC;
				}					
			} else {
				List<VentasCon> listAuxVentaCon = ventaConsignacionDAO.getLista(producto, cliente);
				if (listAuxVentaCon.isEmpty()) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen ventas de consignaci�n realizadas al Cliente " +
							cliente.getNombreNegocio() + ", para ese producto!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					List<CuentasCorrientesCliente> listAuxCC = new ArrayList<CuentasCorrientesCliente>();					
					for (VentasCon ventCon : listAuxVentaCon) {
						int nroVentCon = ventCon.getId();
						CuentasCorrientesCliente ccCli = cuentaCorrienteDAO.get(nroVentCon, "VentasCon");
						listAuxCC.add(ccCli);
					}
					listaCuentasCorrientesClientes = listAuxCC;
					filteredCuentasCorrientesClientes = listAuxCC;
				}
			}			
		}
		if (fechaInicio != null && fechaFin != null && idProducto != 0) {
			Producto producto = productoDAO.get(idProducto);
			if (onVenta) {
				List<Venta> listAuxVenta = ventaDAO.getLista(producto, cliente);
				if (listAuxVenta.isEmpty()) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen ventas realizadas al Cliente " +
							cliente.getNombreNegocio() + ", para ese producto!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					List<CuentasCorrientesCliente> listAuxCC = cuentaCorrienteDAO.getLista(cliente, fechaInicio, fechaFin);
					List<CuentasCorrientesCliente> listAuxCCDef = new ArrayList<CuentasCorrientesCliente>();
					for (CuentasCorrientesCliente cuentasCorrientesCliente : listAuxCC) {
						int idMov = cuentasCorrientesCliente.getIdMovimiento();
						String nombreTabla = cuentasCorrientesCliente.getNombreTabla();
						if (nombreTabla.equals("Venta")) {
							for (Venta ven : listAuxVenta) {
								int nroVenta = ven.getId();
								if (nroVenta == idMov) {
									listAuxCCDef.add(cuentasCorrientesCliente);
								}
							}
						}
					}				
					listaCuentasCorrientesClientes = listAuxCCDef;
					filteredCuentasCorrientesClientes = listAuxCCDef;
				}
			} else {
				List<VentasCon> listAuxVentaCon = ventaConsignacionDAO.getLista(producto, cliente);
				if (listAuxVentaCon.isEmpty()) {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen ventas de consignaci�n realizadas al Cliente " +
							cliente.getNombreNegocio() + ", para ese producto!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					filteredCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					List<CuentasCorrientesCliente> listAuxCC = cuentaCorrienteDAO.getLista(cliente, fechaInicio, fechaFin);
					List<CuentasCorrientesCliente> listAuxCCDef = new ArrayList<CuentasCorrientesCliente>();
					for (CuentasCorrientesCliente cuentasCorrientesCliente : listAuxCC) {
						int idMov = cuentasCorrientesCliente.getIdMovimiento();
						String nombreTabla = cuentasCorrientesCliente.getNombreTabla();
						if (nombreTabla.equals("VentasCon")) {
							for (VentasCon ventCon : listAuxVentaCon) {
								int nroVentCon = ventCon.getId();
								if (nroVentCon == idMov) {
									listAuxCCDef.add(cuentasCorrientesCliente);
								}
							}
						}
					}				
					listaCuentasCorrientesClientes = listAuxCCDef;
					filteredCuentasCorrientesClientes = listAuxCCDef;
				}
			}				
		}
	}
	
	public void filtroProveedor(){
		if(fechaInicio != null && fechaFin != null){
			listaCuentasCorrientesProveedores = new ArrayList<CuentasCorrientesProveedore>();
			filteredCuentasCorrientesProveedores = new ArrayList<CuentasCorrientesProveedore>();
			listaCuentasCorrientesProveedores = cuentaCorrienteDAO.getListaProveedor(proveedor, fechaInicio, fechaFin);
			filteredCuentasCorrientesProveedores = listaCuentasCorrientesProveedores;
		}
	}
	
	public void verMovimientoCliente(CuentasCorrientesCliente ccCliente){
		pago = false;
		panelEntrega = false;
		panelCuota = false;
		panelCuotaVenta = false;
		if(ccCliente.getIdMovimiento() != 0){
			if(ccCliente.getNombreTabla().equals("PagosCliente")){
				pago = true;
				pagoCliente = pagoDAO.getPagoCliente(ccCliente.getIdMovimiento());
			}
			if(ccCliente.getNombreTabla().equals("EntregaConsignacion")){
				panelEntrega = true;
				entregaConsignacion = entregaConsignacionDAO.get(ccCliente.getIdMovimiento());
			}
			if(ccCliente.getNombreTabla().equals("CuotasDetalle")){
				panelCuota = true;
				cuotasDetalle = cuotaDetalleDAO.get(ccCliente.getIdMovimiento());
			}
			if(ccCliente.getNombreTabla().equals("CuotasVentasDetalle")){
				panelCuotaVenta = true;
				cuotasVentasDetalle = cuotaVentaDetalleDAO.get(ccCliente.getIdMovimiento());
			}
			if(ccCliente.getNombreTabla().equals("Venta")){
				panelVenta = true;
				venta = ventaDAO.get(ccCliente.getIdMovimiento());
				
			}
			if(ccCliente.getNombreTabla().equals("Consignacion")){
				panelConsignacion = true;
				consignacion = consignacionDAO.get(ccCliente.getIdMovimiento());
			}
			if(ccCliente.getNombreTabla().equals("VentasCon")){
				panelVentaCons = true;
				ventasCon = ventaConsignacionDAO.get(ccCliente.getIdMovimiento());
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No Existe ning�n movimiento asociado!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void verMovimientoProveedor(CuentasCorrientesProveedore ccProveedor){
		pago = false;
		if(ccProveedor.getIdMovimiento() != 0){
			if(ccProveedor.getNombreTabla().equals("PagosProveedore")){
				pago = true;
				pagoProveedore = pagoDAO.getPagoProveedore(ccProveedor.getIdMovimiento());
			}
			if(ccProveedor.getNombreTabla().equals("Compra")){
				panelCompra = true;
				compra = compraDAO.get(ccProveedor.getIdMovimiento());
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No Existe ning�n movimiento asociado!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void eliminarMovimientoCliente(CuentasCorrientesCliente ccCliente) {
		log.info("bajaMovimientoCliente() - ccCliente id: " + ccCliente.getId());
		try {
			CuentaCorriente cuenta = new CuentaCorriente();
			int deleteCC = cuenta.deleteCuentaCorrientePorId(ccCliente);
			
			if (deleteCC != 0) {
				if (ccCliente.getIdMovimiento() != 0) {
					
					PagosCliente pagosC = pagoDAO.getPagoCliente(ccCliente.getIdMovimiento());
					MovimientoCaja movCaja = new MovimientoCaja();
					
					Caja mov = new Caja();				
					mov.setIdMovimiento(pagosC.getId());
					mov.setNombreTabla("PagosCliente");
					int deleteMC = movCaja.deleteCaja(mov);
					log.info("deleteCC: " + deleteCC + " - deleteMC: " + deleteMC);
									
					if (deleteCC != 0 && deleteMC != 0) {
						List<EquipoPendientePago> listaEquiposPagos = equipoPendientePagoDAO.getLista(pagosC.getCliente(), pagosC, true, true);
						boolean flagEpp = true;
						for (EquipoPendientePago equipoPago : listaEquiposPagos) {
							equipoPago.setFechaMod(new Date());
							equipoPago.setPagado(false);
							equipoPago.setPagosCliente(null);
							int updateEpp = equipoPendientePagoDAO.update(equipoPago); 
							log.info("equipoPago id: " + equipoPago.getId() + " - updateEpp: " + updateEpp);
							if (updateEpp == 0) {
								flagEpp = false;
							}
						}
						if (flagEpp) {
							pagosC.setEnabled(false);
							pagosC.setFechaBaja(new Date());
							pagosC.setUsuario2(usuario);
							int updatePago = pagoDAO.update(pagosC);
							log.info("pago id: " + pagosC.getId() + " - updatePago: " + updatePago);
							if (updatePago != 0) {
								listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
								listaCuentasCorrientesClientes = cuentaCorrienteDAO.getLista(cliente);
								FacesContext.getCurrentInstance().addMessage(null, 
										new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de movimiento registrada!", null));
							} else {
								FacesContext.getCurrentInstance().addMessage(null, 
										new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave al dar de baja el pago!", null));
							}
						} else {
							FacesContext.getCurrentInstance().addMessage(null, 
									new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave al dar de baja los equipos "
											+ "relacionados al pago!", null));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(null, 
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave al dar de baja los movimientos "
										+ "relacionados al registro!", null));
					}
				} else {
					listaCuentasCorrientesClientes = new ArrayList<CuentasCorrientesCliente>();
					listaCuentasCorrientesClientes = cuentaCorrienteDAO.getLista(cliente);
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de movimiento registrada!", null));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de baja el movimiento!", null));
			}			
		} catch (Exception e) {
			log.error("Error en bajaPagosClientes() - Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error grave al dar de baja el movimiento!", null));
		}
	}
	
	public void generarReporteCliente(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuentas> listaCuentas = new ArrayList<Cuentas>();
		for (CuentasCorrientesCliente ccCliente : filteredCuentasCorrientesClientes) {
			Cuentas cuenta = new Cuentas();
			cuenta.setDebe(formatoMonto.format(ccCliente.getDebe()));
			cuenta.setDetalle(ccCliente.getDetalle());
			cuenta.setFecha(formatoFecha.format(ccCliente.getFecha()));
			cuenta.setHaber(formatoMonto.format(ccCliente.getHaber()));
			cuenta.setSaldo(formatoMonto.format(ccCliente.getSaldo()));
			listaCuentas.add(cuenta);
		}
		if(fechaInicio != null && fechaFin != null){
			parametros.put("desde", formatoFecha.format(fechaInicio));
			parametros.put("hasta", formatoFecha.format(fechaFin));
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		parametros.put("persona", "Cliente - Apellido y Nombre: " + cliente.getApellidoNombre() + " - Nombre de Negocio: " + cliente.getNombreNegocio());
		reporte.generar(parametros, listaCuentas, "cuentascorrientes", "inline");
	}
	
	public void generarReporteProveedor(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuentas> listaCuentas = new ArrayList<Cuentas>();
		for (CuentasCorrientesProveedore ccProveedor : filteredCuentasCorrientesProveedores) {
			Cuentas cuenta = new Cuentas();
			cuenta.setDebe(formatoMonto.format(ccProveedor.getDebe()));
			cuenta.setDetalle(ccProveedor.getDetalle());
			cuenta.setFecha(formatoFecha.format(ccProveedor.getFecha()));
			cuenta.setHaber(formatoMonto.format(ccProveedor.getHaber()));
			cuenta.setSaldo(formatoMonto.format(ccProveedor.getSaldo()));
			listaCuentas.add(cuenta);
		}
		if(fechaInicio != null && fechaFin != null){
			parametros.put("desde", formatoFecha.format(fechaInicio));
			parametros.put("hasta", formatoFecha.format(fechaFin));
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		parametros.put("persona", "Proveedor - Apellido y Nombre: " + proveedor.getApellidoNombre() + " - Nombre de Negocio: " + proveedor.getNombreNegocio());
		reporte.generar(parametros, listaCuentas, "cuentascorrientes", "inline");
	}
	
	public String getSaldoCliente(Cliente cli) {
		List<CuentasCorrientesCliente> listCuentasCorrientes = cuentaCorrienteDAO.getLista(cli);
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		float saldo = 0;		
		if (!listCuentasCorrientes.isEmpty()) {
			CuentasCorrientesCliente ccCliente = listCuentasCorrientes.get(0);
			saldo = ccCliente.getSaldo();
		}
		String saldoCli = formatoMonto.format(saldo);
		return saldoCli;
	}
	
	public String getSaldoProveedor(Proveedore prov) {
		List<CuentasCorrientesProveedore> listCuentasCorrientes = cuentaCorrienteDAO.getListaProveedor(prov);
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		float saldo = 0;		
		if (!listCuentasCorrientes.isEmpty()) {
			CuentasCorrientesProveedore ccProveedor = listCuentasCorrientes.get(0);
			saldo = ccProveedor.getSaldo();
		}
		String saldoProv = formatoMonto.format(saldo);
		return saldoProv;
	}

}
