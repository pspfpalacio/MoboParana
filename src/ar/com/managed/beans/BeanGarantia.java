package ar.com.managed.beans;

import java.io.Serializable;
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
import ar.com.clases.auxiliares.Garantias;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOCompra;
import dao.interfaces.DAOCompraDetalle;
import dao.interfaces.DAOCompraDetalleUnidad;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOCuota;
import dao.interfaces.DAOCuotaVenta;
import dao.interfaces.DAOGarantiasCliente;
import dao.interfaces.DAOGarantiasProveedor;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOProveedor;
import dao.interfaces.DAOTecnico;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;
import dao.interfaces.DAOVentaDetalle;
import dao.interfaces.DAOVentaDetalleUnidad;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.Compra;
import model.entity.ComprasDetalle;
import model.entity.ComprasDetalleUnidad;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Cuota;
import model.entity.CuotasVenta;
import model.entity.GarantiasCliente;
import model.entity.GarantiasProveedore;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import model.entity.Proveedore;
import model.entity.Rubro;
import model.entity.Tecnico;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanGarantia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanGarantiasClienteDAO}")
	private DAOGarantiasCliente garantiasClienteDAO;
	
	@ManagedProperty(value = "#{BeanGarantiasProveedorDAO}")
	private DAOGarantiasProveedor garantiasProveedorDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleUnidadDAO}")
	private DAOCompraDetalleUnidad compraDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleDAO}")
	private DAOCompraDetalle compraDetalleDAO;
	
	@ManagedProperty(value = "#{BeanCompraDAO}")
	private DAOCompra compraDAO;	
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleDAO}")
	private DAOVentaDetalle ventaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;

	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;	
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;	
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
	private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaVentaDAO}")
	private DAOCuotaVenta cuotaVentaDAO;
	
	@ManagedProperty(value = "#{BeanTecnicoDAO}")
	private DAOTecnico tecnicoDAO;
	
	private List<GarantiasCliente> listaGarantiasClientes;
	private List<GarantiasCliente> filteredGarantiasClientes;
	private List<GarantiasProveedore> listaGarantiasProveedores;
	private List<GarantiasProveedore> filteredGarantiasProveedores;
	private List<Producto> listaProductos;
	private List<Cliente> listaClientes;
	private List<Proveedore> listaProveedores;
	private List<Tecnico> listaTecnicos;
	private GarantiasCliente garantiasCliente;
	private GarantiasProveedore garantiasProveedor;
	private Usuario usuario;
	private Cliente cliente;
	private Proveedore proveedor;
	private UnidadMovil unidadMovil;
	private Producto producto;
	private Tecnico tecnico;
	private Date fechaDesde;
	private Date fechaHasta;
	private String headerText;
	private int estado;
	private int idProducto;
	private int idCliente;
	private int idProveedor;
	private int idResolucion;
	private int idTecnico;
	private boolean abrir;
	private boolean cerrar;
	private boolean opcion1;
	private boolean opcion2;
	private boolean opcion3;
	private boolean ningunaAccion;
	private boolean concepto;
	private boolean porReparacion;
	private boolean porGarantia;

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

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
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

	public DAOCompraDetalleUnidad getCompraDetalleUnidadDAO() {
		return compraDetalleUnidadDAO;
	}

	public void setCompraDetalleUnidadDAO(
			DAOCompraDetalleUnidad compraDetalleUnidadDAO) {
		this.compraDetalleUnidadDAO = compraDetalleUnidadDAO;
	}

	public DAOCompraDetalle getCompraDetalleDAO() {
		return compraDetalleDAO;
	}

	public void setCompraDetalleDAO(DAOCompraDetalle compraDetalleDAO) {
		this.compraDetalleDAO = compraDetalleDAO;
	}

	public DAOCompra getCompraDAO() {
		return compraDAO;
	}

	public void setCompraDAO(DAOCompra compraDAO) {
		this.compraDAO = compraDAO;
	}

	public DAOVentaDetalleUnidad getVentaDetalleUnidadDAO() {
		return ventaDetalleUnidadDAO;
	}

	public void setVentaDetalleUnidadDAO(DAOVentaDetalleUnidad ventaDetalleUnidadDAO) {
		this.ventaDetalleUnidadDAO = ventaDetalleUnidadDAO;
	}

	public DAOVentaDetalle getVentaDetalleDAO() {
		return ventaDetalleDAO;
	}

	public void setVentaDetalleDAO(DAOVentaDetalle ventaDetalleDAO) {
		this.ventaDetalleDAO = ventaDetalleDAO;
	}

	public DAOVenta getVentaDAO() {
		return ventaDAO;
	}

	public void setVentaDAO(DAOVenta ventaDAO) {
		this.ventaDAO = ventaDAO;
	}

	public DAOConsignacionDetalleUnidad getConsignacionDetalleUnidadDAO() {
		return consignacionDetalleUnidadDAO;
	}

	public void setConsignacionDetalleUnidadDAO(DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO) {
		this.consignacionDetalleUnidadDAO = consignacionDetalleUnidadDAO;
	}

	public DAOConsignacionDetalle getConsignacionDetalleDAO() {
		return consignacionDetalleDAO;
	}

	public void setConsignacionDetalleDAO(
			DAOConsignacionDetalle consignacionDetalleDAO) {
		this.consignacionDetalleDAO = consignacionDetalleDAO;
	}

	public DAOConsignacion getConsignacionDAO() {
		return consignacionDAO;
	}

	public void setConsignacionDAO(DAOConsignacion consignacionDAO) {
		this.consignacionDAO = consignacionDAO;
	}

	public DAOVentaConsignacionDetalleUnidad getVentaConsignacionDetalleUnidadDAO() {
		return ventaConsignacionDetalleUnidadDAO;
	}

	public void setVentaConsignacionDetalleUnidadDAO(
			DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO) {
		this.ventaConsignacionDetalleUnidadDAO = ventaConsignacionDetalleUnidadDAO;
	}

	public DAOVentaConsignacionDetalle getVentaConsignacionDetalleDAO() {
		return ventaConsignacionDetalleDAO;
	}

	public void setVentaConsignacionDetalleDAO(
			DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO) {
		this.ventaConsignacionDetalleDAO = ventaConsignacionDetalleDAO;
	}

	public DAOVentaConsignacion getVentaConsignacionDAO() {
		return ventaConsignacionDAO;
	}

	public void setVentaConsignacionDAO(DAOVentaConsignacion ventaConsignacionDAO) {
		this.ventaConsignacionDAO = ventaConsignacionDAO;
	}

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
	}

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
	}

	public DAOCuotaVenta getCuotaVentaDAO() {
		return cuotaVentaDAO;
	}

	public void setCuotaVentaDAO(DAOCuotaVenta cuotaVentaDAO) {
		this.cuotaVentaDAO = cuotaVentaDAO;
	}

	public DAOTecnico getTecnicoDAO() {
		return tecnicoDAO;
	}

	public void setTecnicoDAO(DAOTecnico tecnicoDAO) {
		this.tecnicoDAO = tecnicoDAO;
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

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Proveedore> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public List<Tecnico> getListaTecnicos() {
		return listaTecnicos;
	}

	public void setListaTecnicos(List<Tecnico> listaTecnicos) {
		this.listaTecnicos = listaTecnicos;
	}

	public GarantiasCliente getGarantiasCliente() {
		return garantiasCliente;
	}

	public void setGarantiasCliente(GarantiasCliente garantiasCliente) {
		this.garantiasCliente = garantiasCliente;
	}

	public GarantiasProveedore getGarantiasProveedor() {
		return garantiasProveedor;
	}

	public void setGarantiasProveedor(GarantiasProveedore garantiasProveedor) {
		this.garantiasProveedor = garantiasProveedor;
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

	public UnidadMovil getUnidadMovil() {
		return unidadMovil;
	}

	public void setUnidadMovil(UnidadMovil unidadMovil) {
		this.unidadMovil = unidadMovil;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public int getIdResolucion() {
		return idResolucion;
	}

	public void setIdResolucion(int idResolucion) {
		this.idResolucion = idResolucion;
	}

	public int getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}

	public boolean isAbrir() {
		return abrir;
	}

	public void setAbrir(boolean abrir) {
		this.abrir = abrir;
	}

	public boolean isCerrar() {
		return cerrar;
	}

	public void setCerrar(boolean cerrar) {
		this.cerrar = cerrar;
	}

	public boolean isOpcion1() {
		return opcion1;
	}

	public void setOpcion1(boolean opcion1) {
		this.opcion1 = opcion1;
	}

	public boolean isOpcion2() {
		return opcion2;
	}

	public void setOpcion2(boolean opcion2) {
		this.opcion2 = opcion2;
	}

	public boolean isOpcion3() {
		return opcion3;
	}

	public void setOpcion3(boolean opcion3) {
		this.opcion3 = opcion3;
	}

	public boolean isNingunaAccion() {
		return ningunaAccion;
	}

	public void setNingunaAccion(boolean ningunaAccion) {
		this.ningunaAccion = ningunaAccion;
	}

	public boolean isConcepto() {
		return concepto;
	}

	public void setConcepto(boolean concepto) {
		this.concepto = concepto;
	}

	public boolean isPorReparacion() {
		return porReparacion;
	}

	public void setPorReparacion(boolean porReparacion) {
		this.porReparacion = porReparacion;
	}

	public boolean isPorGarantia() {
		return porGarantia;
	}

	public void setPorGarantia(boolean porGarantia) {
		this.porGarantia = porGarantia;
	}

	public String goGarantiasClientes(Usuario user){
		listaGarantiasClientes = new ArrayList<GarantiasCliente>();
		filteredGarantiasClientes = new ArrayList<GarantiasCliente>();		
		listaGarantiasClientes = garantiasClienteDAO.getLista();
		filteredGarantiasClientes = listaGarantiasClientes;
		listaClientes = new ArrayList<Cliente>();
		listaProductos = new ArrayList<Producto>();
		listaClientes = clienteDAO.getLista(true);
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = productoDAO.getLista(true, rub);
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		estado = 0;
		idCliente = 0;
		idProducto = 0;
		return "garantiasclientes";
	}
	
	public String goGarantiasProveedor(Usuario user){
		listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
		filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
		listaGarantiasProveedores = garantiasProveedorDAO.getLista();
		filteredGarantiasProveedores = listaGarantiasProveedores;
		listaProveedores = new ArrayList<Proveedore>();
		listaProductos = new ArrayList<Producto>();
		listaProveedores = proveedorDAO.getLista(true);
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = productoDAO.getLista(true, rub);
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		idProveedor = 0;
		idProducto = 0;
		return "garantiasproveedores";
	}
	
	public String goNuevaGarantiaCliente(){
		headerText = "Nuevo Ticket de Garantía";
		abrir = true;
		cerrar = false;
		garantiasCliente = new GarantiasCliente();
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		cliente = new Cliente();
		listaTecnicos = new ArrayList<Tecnico>();		
		listaTecnicos = tecnicoDAO.getLista(true);
		idTecnico = 0;
		return "garantiacliente";
	}
	
	public String goNuevaGarantiaProveedor(){
		headerText = "Nuevo Ticket de Garantía";
		abrir = true;
		cerrar = false;
		concepto = false;
		garantiasProveedor = new GarantiasProveedore();
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		return "garantiaproveedor";
	}
	
	public String goEditarGarantiaCliente(GarantiasCliente gCliente) {
		headerText = "Editar Ticket de Garantía";		
		garantiasCliente = new GarantiasCliente();
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		cliente = new Cliente();
		garantiasCliente = gCliente;
		listaTecnicos = new ArrayList<Tecnico>();		
		listaTecnicos = tecnicoDAO.getLista();
		idTecnico = 0;
		if (gCliente.getTecnico() != null) {
			idTecnico = gCliente.getTecnico().getId();
		}
		return "editargarantiacliente";
	}
	
	public String goCerrarGarantiaCliente(GarantiasCliente garantCli){
		headerText = "Cerrar Ticket de Garantía";
		garantiasCliente = new GarantiasCliente();
		producto = new Producto();
		cliente = new Cliente();
		unidadMovil = new UnidadMovil();
		abrir = false;
		cerrar = true;
		opcion1 = true;
		opcion2 = false;
		opcion3 = false;
		ningunaAccion = true;
		idResolucion = 0;
		garantiasCliente = garantCli;
		garantiasCliente.setFallaDefinitiva(garantCli.getFalla());				
		return "garantiacliente";
	}
	
	public String goCerrarGarantiaProveedor(GarantiasProveedore garantProv){
		headerText = "Cerrar Ticket de Garantía";
		garantiasProveedor = new GarantiasProveedore();
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		abrir = false;
		cerrar = true;
		opcion1 = true;
		opcion2 = false;
		opcion3 = false;
		ningunaAccion = false;
		porReparacion = false;
		porGarantia = false;
		idResolucion = 0;
		garantiasProveedor = garantProv;
		garantiasProveedor.setFallaDefinitiva(garantProv.getFalla());
		if (garantProv.getConcepto().equals("Entrega por reparación")) {
			porReparacion = true;
		} else {
			porGarantia = true;
		}
		return "garantiaproveedor";
	}
	
	public List<UnidadMovil> completeText(String query){
		List<UnidadMovil> listAux = unidadMovilDAO.getLike(query);
		return listAux;
	}
	
	public void onChangeResolucion() {
		if (idResolucion == 0) {
			opcion1 = true;
			opcion2 = false;
			opcion3 = false;
		}
		if (idResolucion == 1) {
			opcion1 = false;
			opcion2 = true;
			opcion3 = false;
		}
		if (idResolucion == 2) {
			opcion1 = false;
			opcion2 = false;
			opcion3 = true;
		}
	}
	
	public void bajaCliente(GarantiasCliente garanCliente) {
		if (garanCliente.getFinalizado()) {
			FacesMessage msg = null;
			try {
				String resolucion1 = "Mismo Equipo";
				String resolucion2 = "Cambio de Equipo";
				String resolucion3 = "No posee arreglo";
				
				if (garanCliente.getResolucion().equals(resolucion1)) {
					garanCliente.setEstado(false);
					garanCliente.setFechaBaja(new Date());
					garanCliente.setUsuario2(usuario);
					int updGarantia = garantiasClienteDAO.update(garanCliente);
					if (updGarantia != 0) {
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
					}
				}
				
				if (garanCliente.getResolucion().equals(resolucion2)) {
					String imeiFalla = garanCliente.getImeiFalla();
					String imeiReintegro = garanCliente.getImeiReintegro();	
					UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
					UnidadMovil unidadReintegro = unidadMovilDAO.get(imeiReintegro);
					Producto prodFalla = garanCliente.getProducto1();
					Producto prodReintegro = garanCliente.getProducto2();
					
					boolean NoExisteV = true;
					boolean NoExisteC = true;
					boolean NoExisteVC = true;				
					
					VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(imeiFalla);
					if (ventaUnidad.getId() != 0) {
						NoExisteV = false;
					}
					ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(imeiFalla);
					if (consignacionUnidad.getId() != 0) {
						NoExisteC = false;
					}
					VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(imeiFalla);
					if (ventaConsUnidad.getId() != 0) {
						NoExisteVC = false;
					}
					if (NoExisteV && NoExisteC && NoExisteVC) {
						
						//Cambio de un equipo por otro en la venta, consignacion o ventaconsignacion
						boolean existeV = false;
						boolean existeC = false;
						boolean existeVC = false;
						VentasDetalleUnidad ventaUnidadF = ventaDetalleUnidadDAO.get(imeiReintegro);
						if (ventaUnidadF.getId() != 0) {
							existeV = true;
						}
						ConsignacionsDetalleUnidad consignacionUnidadF = consignacionDetalleUnidadDAO.get(imeiReintegro);
						if (consignacionUnidadF.getId() != 0) {
							existeC = true;
						}
						VentasConsDetalleUnidad ventaConsUnidadF = ventaConsignacionDetalleUnidadDAO.get(imeiReintegro);
						if (ventaConsUnidadF.getId() != 0) {
							existeVC = true;
						}
						if (existeV) {
							if (prodFalla.getId() == prodReintegro.getId()) {
								Cuota cuota = cuotaDAO.get(imeiReintegro);
								if (cuota.getId() != 0) {
									cuota.setNroImei(imeiFalla);
									cuotaDAO.update(cuota);
								}
								CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiReintegro);
								if (cuotaVenta.getId() != 0) {
									cuotaVenta.setNroImei(imeiFalla);
									cuotaVentaDAO.update(cuotaVenta);
								}
								ventaUnidadF.setNroImei(imeiFalla);
								ventaUnidadF.setEliminado(false);
								ventaUnidadF.setPrecioCompra(unidadFalla.getPrecioCompra());
								ventaUnidadF.setUnidadMovil(unidadFalla);
								
								int updtVenta = ventaDetalleUnidadDAO.update(ventaUnidadF);
								if (updtVenta != 0) {
									unidadFalla.setDevuelto(false);
									unidadFalla.setEnStock(false);
									unidadFalla.setEnVenta(true);
									unidadFalla.setEstado(true);
									unidadFalla.setFechaMod(new Date());
									unidadFalla.setUsuario3(usuario);
									
									unidadReintegro.setEnStock(true);
									unidadReintegro.setEnVenta(false);
									unidadReintegro.setEstado(true);
									unidadReintegro.setEliminado(false);
									unidadReintegro.setFechaMod(new Date());
									unidadReintegro.setUsuario3(usuario);								
									int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
									int updtMovilVta = unidadMovilDAO.update(unidadReintegro);
									if (updtMovilVta != 0 && updtMvilFalla != 0) {
										garanCliente.setEstado(false);
										garanCliente.setFechaBaja(new Date());
										garanCliente.setUsuario2(usuario);
										
										int updGarantia = garantiasClienteDAO.update(garanCliente);
										if (updGarantia != 0) {
											msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
										}
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
									}								
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Venta correspondiente!", null);
								}
							} else {
								Cuota cuota = cuotaDAO.get(imeiReintegro);
								CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiReintegro);
								if (cuota.getId() == 0 && cuotaVenta.getId() == 0) {
									VentasDetalle ventaDetalleR = ventaDetalleDAO.get(ventaUnidadF.getVentasDetalle().getId());
									Venta venta = ventaDAO.get(ventaDetalleR.getVenta().getId());
									Cliente cli = clienteDAO.get(venta.getCliente().getId());
									float montoDescuento = 0;
									float montoSumado = 0;
									boolean existeDetalle = false;
									List<VentasDetalle> detallesVenta = ventaDetalleDAO.getLista(venta);
									VentasDetalle ventDetalleN = new VentasDetalle();
									for (VentasDetalle ventasD : detallesVenta) {
										if (ventasD.getProducto().getId() == prodFalla.getId()) {
											existeDetalle = true;
											ventDetalleN = ventasD;
										}
									}
									if (existeDetalle) {								
										//Alta Nuevo Detalle Unidad
										int cantNueva = ventDetalleN.getCantidad();										
										montoSumado = ventDetalleN.getPrecioVenta();
										cantNueva = cantNueva + 1;
										float subtotal = cantNueva * montoSumado;
										ventDetalleN.setCantidad(cantNueva);
										ventDetalleN.setSubtotal(subtotal);
										VentasDetalleUnidad ventaUnidadN = new VentasDetalleUnidad();
										ventaUnidadN.setEliminado(false);
										ventaUnidadN.setNroImei(imeiFalla);
										ventaUnidadN.setPrecioCompra(unidadFalla.getPrecioCompra());
										ventaUnidadN.setPrecioVenta(ventDetalleN.getPrecioVenta());										
										ventaUnidadN.setUnidadMovil(unidadFalla);
										ventaUnidadN.setVentasDetalle(ventDetalleN);
										ventaDetalleDAO.update(ventDetalleN);
										ventaDetalleUnidadDAO.insertar(ventaUnidadN);																	
										//Baja Detalle
										if (ventaDetalleR.getCantidad() == 1) {
											ventaDetalleR.setEliminado(true);									
										} else {
											int cantF = ventaDetalleR.getCantidad();
											cantF = cantF - 1;
											ventaDetalleR.setCantidad(cantF);
										}								
										ventaDetalleDAO.update(ventaDetalleR);
										//Baja Venta Unidad
										montoDescuento = ventaUnidadF.getPrecioVenta();
										ventaUnidadF.setEliminado(true);
										ventaDetalleUnidadDAO.update(ventaUnidadF);
										//Actualizacion Monto Venta
										float montoActual = venta.getMonto();
										montoActual = montoActual - montoDescuento;
										montoActual = montoActual + montoSumado;
										venta.setMonto(montoActual);
										ventaDAO.update(venta);	
										//Actualizacion Cuenta Corriente
										int idVenta = venta.getId();
										//Baja de venta en Cuenta Corriente, para realizar el alta luego
										CuentaCorriente cuenta = new CuentaCorriente();
										CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
										if(venta.getTipo().equals("c.c.")){
											ccCliente.setIdMovimiento(venta.getId());
											ccCliente.setNombreTabla("Venta");
											cuenta.deleteCuentaCorriente(ccCliente);
										}
										if(venta.getTipo().equals("ctdo.")){									
											cuentaCorrienteDAO.deletePorMovimientoCliente(idVenta, "Venta", cli);
											MovimientoCaja movCaja = new MovimientoCaja();
											Caja mov = new Caja();
											mov.setIdMovimiento(idVenta);
											mov.setNombreTabla("Venta");
											movCaja.deleteCaja(mov);
										}								
										//Insercion de CC
										ccCliente = new CuentasCorrientesCliente();	
										ccCliente.setCliente(cli);
										ccCliente.setDebe(montoActual);
										ccCliente.setDetalle("Venta nro: " + idVenta);				
										ccCliente.setFecha(venta.getFecha());
										ccCliente.setIdMovimiento(idVenta);
										ccCliente.setMonto(montoActual);
										ccCliente.setNombreTabla("Venta");
										ccCliente.setUsuario(usuario);
										cuenta.insertarCC(ccCliente);
										//Si es una venta de contado, inserto el pago en CC y en Caja
										if (venta.getTipo().equals("ctdo.")) {
											ccCliente = new CuentasCorrientesCliente();								
											ccCliente.setCliente(cli);
											ccCliente.setDetalle("Pago de contado - Venta nro: " + idVenta);
											ccCliente.setFecha(venta.getFecha());
											ccCliente.setHaber(montoActual);
											ccCliente.setIdMovimiento(idVenta);
											ccCliente.setMonto(montoActual);
											ccCliente.setNombreTabla("Venta");
											ccCliente.setUsuario(usuario);
											cuenta.insertarCC(ccCliente);
											MovimientoCaja movimientoCaja = new MovimientoCaja();
											Caja caja = new Caja();
											caja.setConcepto("Cobro de Venta nro: " + idVenta);
											caja.setFecha(venta.getFecha());
											caja.setIdMovimiento(idVenta);
											caja.setMonto(montoActual);
											caja.setNombreTabla("Venta");
											caja.setUsuario(usuario);
											movimientoCaja.insertarCaja(caja);
										}
										//Baja en Venta y Alta en Stock de Unidad									
										unidadReintegro.setEnStock(true);
										unidadReintegro.setEnVenta(false);
										unidadReintegro.setEstado(true);
										unidadReintegro.setConFalla(false);
										unidadReintegro.setDevuelto(false);
										unidadReintegro.setEliminado(false);
										unidadReintegro.setEnConsignacion(false);
										unidadReintegro.setEnGarantiaCliente(false);									
										unidadReintegro.setFechaMod(new Date());
										unidadReintegro.setUsuario3(usuario);
										//Alta en Venta y Baja en Stock de Unidad
										unidadFalla.setConFalla(false);
										unidadFalla.setEnConsignacion(false);
										unidadFalla.setDevuelto(false);
										unidadFalla.setEnStock(false);
										unidadFalla.setEnVenta(true);
										unidadFalla.setEstado(true);
										unidadFalla.setEliminado(false);
										unidadFalla.setEnGarantiaCliente(false);
										unidadFalla.setFechaMod(new Date());									
										unidadFalla.setUsuario3(usuario);								
										int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
										int updtMovilVta = unidadMovilDAO.update(unidadReintegro);				
										//Actualizacion de Stock en productos
										int stockF = prodFalla.getStock();
										int stockR = prodReintegro.getStock();
										stockF = stockF - 1;
										stockR = stockR + 1;
										prodFalla.setStock(stockF);
										prodReintegro.setStock(stockR);
										productoDAO.update(prodFalla);
										productoDAO.update(prodReintegro);
										if (updtMovilVta != 0 && updtMvilFalla != 0) {
											//Actualizacion garantia
											garanCliente.setEstado(false);
											garanCliente.setFechaBaja(new Date());
											garanCliente.setUsuario2(usuario);
											
											int updGarantia = garantiasClienteDAO.update(garanCliente);
											if (updGarantia != 0) {
												listaGarantiasClientes = new ArrayList<GarantiasCliente>();
												filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
												listaGarantiasClientes = garantiasClienteDAO.getLista();
												filteredGarantiasClientes = listaGarantiasClientes;
												msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
											}
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
										}								
									} else {
										ListaPrecioProducto precioProducto = new ListaPrecioProducto();
										ListaPrecio listaPrecio = listaPrecioDAO.get(cli.getListaPrecio().getId());								
										if (listaPrecio.getId() != 0) {
											precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, prodFalla);
											if (precioProducto.getId() != 0 && precioProducto.getPrecioVenta() != 0) {
												ventDetalleN.setAccesorio(false);
												ventDetalleN.setCantidad(1);
												ventDetalleN.setEliminado(false);
												ventDetalleN.setPrecioVenta(precioProducto.getPrecioVenta());
												ventDetalleN.setProducto(prodFalla);
												ventDetalleN.setSubtotal(precioProducto.getPrecioVenta());
												ventDetalleN.setVenta(venta);
												int idVentaDetalleN = ventaDetalleDAO.insertar(ventDetalleN);
												ventDetalleN.setId(idVentaDetalleN);
												VentasDetalleUnidad ventaUnidadN = new VentasDetalleUnidad();
												ventaUnidadN.setEliminado(false);
												ventaUnidadN.setNroImei(imeiFalla);
												ventaUnidadN.setPrecioCompra(unidadFalla.getPrecioCompra());
												ventaUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
												montoSumado = precioProducto.getPrecioVenta();
												ventaUnidadN.setUnidadMovil(unidadFalla);
												ventaUnidadN.setVentasDetalle(ventDetalleN);
												ventaDetalleUnidadDAO.insertar(ventaUnidadN);
												//Baja Detalle
												if (ventaDetalleR.getCantidad() == 1) {
													ventaDetalleR.setEliminado(true);									
												} else {
													int cantF = ventaDetalleR.getCantidad();
													cantF = cantF - 1;
													ventaDetalleR.setCantidad(cantF);
												}								
												ventaDetalleDAO.update(ventaDetalleR);
												//Baja Venta Unidad
												montoDescuento = ventaUnidadF.getPrecioVenta();
												ventaUnidadF.setEliminado(true);
												ventaDetalleUnidadDAO.update(ventaUnidadF);
												//Actualizacion Monto Venta
												float montoActual = venta.getMonto();
												montoActual = montoActual - montoDescuento;
												montoActual = montoActual + montoSumado;
												venta.setMonto(montoActual);
												ventaDAO.update(venta);
												//Actualizacion Cuenta Corriente
												int idVenta = venta.getId();
												//Baja de venta en Cuenta Corriente, para realizar el alta luego
												CuentaCorriente cuenta = new CuentaCorriente();
												CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
												if(venta.getTipo().equals("c.c.")){
													ccCliente.setIdMovimiento(venta.getId());
													ccCliente.setNombreTabla("Venta");
													cuenta.deleteCuentaCorriente(ccCliente);
												}
												if(venta.getTipo().equals("ctdo.")){									
													cuentaCorrienteDAO.deletePorMovimientoCliente(idVenta, "Venta", cli);
													MovimientoCaja movCaja = new MovimientoCaja();
													Caja mov = new Caja();
													mov.setIdMovimiento(idVenta);
													mov.setNombreTabla("Venta");
													movCaja.deleteCaja(mov);
												}								
												//Insercion de CC
												ccCliente = new CuentasCorrientesCliente();	
												ccCliente.setCliente(cli);
												ccCliente.setDebe(montoActual);
												ccCliente.setDetalle("Venta nro: " + idVenta);				
												ccCliente.setFecha(venta.getFecha());
												ccCliente.setIdMovimiento(idVenta);
												ccCliente.setMonto(montoActual);
												ccCliente.setNombreTabla("Venta");
												ccCliente.setUsuario(usuario);
												cuenta.insertarCC(ccCliente);
												//Si es una venta de contado, inserto el pago en CC y en Caja
												if (venta.getTipo().equals("ctdo.")) {
													ccCliente = new CuentasCorrientesCliente();								
													ccCliente.setCliente(cli);
													ccCliente.setDetalle("Pago de contado - Venta nro: " + idVenta);
													ccCliente.setFecha(venta.getFecha());
													ccCliente.setHaber(montoActual);
													ccCliente.setIdMovimiento(idVenta);
													ccCliente.setMonto(montoActual);
													ccCliente.setNombreTabla("Venta");
													ccCliente.setUsuario(usuario);
													cuenta.insertarCC(ccCliente);
													MovimientoCaja movimientoCaja = new MovimientoCaja();
													Caja caja = new Caja();
													caja.setConcepto("Cobro de Venta nro: " + idVenta);
													caja.setFecha(venta.getFecha());
													caja.setIdMovimiento(idVenta);
													caja.setMonto(montoActual);
													caja.setNombreTabla("Venta");
													caja.setUsuario(usuario);
													movimientoCaja.insertarCaja(caja);
												}
												//Baja en Venta y Alta en Stock de Unidad									
												unidadReintegro.setEnStock(true);
												unidadReintegro.setEnVenta(false);
												unidadReintegro.setEstado(true);
												unidadReintegro.setConFalla(false);
												unidadReintegro.setDevuelto(false);
												unidadReintegro.setEliminado(false);
												unidadReintegro.setEnConsignacion(false);
												unidadReintegro.setEnGarantiaCliente(false);									
												unidadReintegro.setFechaMod(new Date());
												unidadReintegro.setUsuario3(usuario);
												//Alta en Venta y Baja en Stock de Unidad
												unidadFalla.setConFalla(false);
												unidadFalla.setEnConsignacion(false);
												unidadFalla.setDevuelto(false);
												unidadFalla.setEnStock(false);
												unidadFalla.setEnVenta(true);
												unidadFalla.setEstado(true);
												unidadFalla.setEliminado(false);
												unidadFalla.setEnGarantiaCliente(false);
												unidadFalla.setFechaMod(new Date());									
												unidadFalla.setUsuario3(usuario);								
												int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
												int updtMovilVta = unidadMovilDAO.update(unidadReintegro);		
												//Actualizacion de Stock en productos
												int stockF = prodFalla.getStock();
												int stockR = prodReintegro.getStock();
												stockF = stockF - 1;
												stockR = stockR + 1;
												prodFalla.setStock(stockF);
												prodReintegro.setStock(stockR);
												productoDAO.update(prodFalla);
												productoDAO.update(prodReintegro);
												if (updtMovilVta != 0 && updtMvilFalla != 0) {
													//Actualizacion garantia
													garanCliente.setEstado(false);
													garanCliente.setFechaBaja(new Date());
													garanCliente.setUsuario2(usuario);
													
													int updGarantia = garantiasClienteDAO.update(garanCliente);
													if (updGarantia != 0) {
														listaGarantiasClientes = new ArrayList<GarantiasCliente>();
														filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
														listaGarantiasClientes = garantiasClienteDAO.getLista();
														filteredGarantiasClientes = listaGarantiasClientes;
														msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
													} else {
														msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
													}
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
												}
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee Precio "
														+ "para ese Producto en la Lista de Precio", null);
											}									
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee "
													+ "Lista de Precio", null);
										}									
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar la baja de garantía, el Móvil de Reintegro esta registrado en Cuotas, "
											+ "realice las bajas de las mismas primero!", null);
								}								
							}
						}
						if (existeC) {
							if (prodFalla.getId() == prodReintegro.getId()) {
								Cuota cuota = cuotaDAO.get(imeiReintegro);
								if (cuota.getId() != 0) {
									cuota.setNroImei(imeiFalla);
									cuotaDAO.update(cuota);
								}
								CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiReintegro);
								if (cuotaVenta.getId() != 0) {
									cuotaVenta.setNroImei(imeiFalla);
									cuotaVentaDAO.update(cuotaVenta);
								}
								consignacionUnidadF.setNroImei(imeiFalla);
								consignacionUnidadF.setEliminado(false);
								consignacionUnidadF.setPrecioCompra(unidadFalla.getPrecioCompra());
								consignacionUnidadF.setUnidadMovil(unidadFalla);
								
								int updtConsignacion = consignacionDetalleUnidadDAO.update(consignacionUnidadF);
								if (updtConsignacion != 0) {
									unidadFalla.setDevuelto(false);
									unidadFalla.setEnStock(true);
									unidadFalla.setEnConsignacion(true);
									unidadFalla.setEstado(true);
									unidadFalla.setFechaMod(new Date());
									unidadFalla.setUsuario3(usuario);
									
									unidadReintegro.setEnStock(true);
									unidadReintegro.setEnVenta(false);
									unidadReintegro.setEnConsignacion(false);
									unidadReintegro.setEstado(true);
									unidadReintegro.setEliminado(false);
									unidadReintegro.setFechaMod(new Date());
									unidadReintegro.setUsuario3(usuario);							
									int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
									int updtMovilVta = unidadMovilDAO.update(unidadReintegro);
									if (updtMvilFalla != 0 && updtMovilVta != 0) {
										garanCliente.setEstado(false);
										garanCliente.setFechaBaja(new Date());
										garanCliente.setUsuario2(usuario);
										
										int updGarantia = garantiasClienteDAO.update(garanCliente);
										if (updGarantia != 0) {
											msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
										}
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
									}								
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Consignación correspondiente!", null);
								}
							} else {
								Cuota cuota = cuotaDAO.get(imeiReintegro);
								CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiReintegro);
								if (cuota.getId() == 0 && cuotaVenta.getId() == 0) {
									ConsignacionsDetalle consignacionDetalleR = consignacionDetalleDAO.get(consignacionUnidadF.getConsignacionsDetalle().getId());
									Consignacion consignacion = consignacionDAO.get(consignacionDetalleR.getConsignacion().getId());
									Cliente cli = clienteDAO.get(consignacion.getCliente().getId());
									boolean existeDetalle = false;
									List<ConsignacionsDetalle> detallesConsig = consignacionDetalleDAO.getLista(consignacion);
									ConsignacionsDetalle consignacionDetalleN = new ConsignacionsDetalle();
									for (ConsignacionsDetalle consignacionsD : detallesConsig) {
										if (consignacionsD.getProducto().getId() == prodFalla.getId()) {
											existeDetalle = true;
											consignacionDetalleN = consignacionsD;
										}
									}
									if (existeDetalle) {
										//Alta Nuevo Detalle Unidad
										int cantNueva = consignacionDetalleN.getCantidad();
										cantNueva = cantNueva + 1;
										float subtotal = consignacionDetalleN.getSubtotal() + consignacionDetalleN.getPrecioVenta();
										consignacionDetalleN.setCantidad(cantNueva);
										consignacionDetalleN.setSubtotal(subtotal);
										ConsignacionsDetalleUnidad consignacionUnidadN = new ConsignacionsDetalleUnidad();
										consignacionUnidadN.setConsignacionsDetalle(consignacionDetalleN);
										consignacionUnidadN.setEliminado(false);
										consignacionUnidadN.setEnabled(true);
										consignacionUnidadN.setFechaAlta(consignacionUnidadF.getFechaAlta());
										consignacionUnidadN.setFechaVenta(consignacionUnidadF.getFechaVenta());
										consignacionUnidadN.setNroImei(imeiFalla);
										consignacionUnidadN.setPrecioCompra(unidadFalla.getPrecioCompra());
										consignacionUnidadN.setPrecioVenta(consignacionDetalleN.getPrecioVenta());
										consignacionUnidadN.setTipoVenta(consignacionUnidadF.getTipoVenta());
										consignacionUnidadN.setUnidadMovil(unidadFalla);
										consignacionUnidadN.setVendido(consignacionUnidadF.getVendido());
										consignacionDetalleUnidadDAO.insertar(consignacionUnidadN);
										consignacionDetalleDAO.update(consignacionDetalleN);
										//Baja Detalle
										if (consignacionDetalleR.getCantidad() == 1) {
											consignacionDetalleR.setEliminado(true);											
										} else {
											int cantF = consignacionDetalleR.getCantidad();
											cantF = cantF - 1;
											consignacionDetalleR.setCantidad(cantF);
										}
										consignacionDetalleDAO.update(consignacionDetalleR);
										//Baja Unidad										
										consignacionUnidadF.setEliminado(true);
										consignacionUnidadF.setEnabled(false);
										consignacionUnidadF.setFechaBaja(new Date());										
										consignacionDetalleUnidadDAO.update(consignacionUnidadF);
										//Baja en Consignacion y Alta en Stock de Unidad									
										unidadReintegro.setEnStock(true);
										unidadReintegro.setEnVenta(false);
										unidadReintegro.setEstado(true);
										unidadReintegro.setConFalla(false);
										unidadReintegro.setDevuelto(false);
										unidadReintegro.setEliminado(false);
										unidadReintegro.setEnConsignacion(false);
										unidadReintegro.setEnGarantiaCliente(false);									
										unidadReintegro.setFechaMod(new Date());
										unidadReintegro.setUsuario3(usuario);
										//Alta en Consignacion y Baja en Stock de Unidad
										unidadFalla.setConFalla(false);
										unidadFalla.setEnConsignacion(true);
										unidadFalla.setDevuelto(false);
										unidadFalla.setEnStock(false);
										unidadFalla.setEnVenta(false);
										unidadFalla.setEstado(true);
										unidadFalla.setEliminado(false);
										unidadFalla.setEnGarantiaCliente(false);
										unidadFalla.setFechaMod(new Date());									
										unidadFalla.setUsuario3(usuario);								
										int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
										int updtMovilVta = unidadMovilDAO.update(unidadReintegro);
										//Actualizacion de Consignacion en productos
										int enConsigF = prodFalla.getEnConsignacion();
										int enConsigC = prodReintegro.getEnConsignacion();
										enConsigF = enConsigF + 1;
										enConsigC = enConsigC - 1;
										prodFalla.setEnConsignacion(enConsigF);
										prodReintegro.setEnConsignacion(enConsigC);
										productoDAO.update(prodFalla);
										productoDAO.update(prodReintegro);
										if (updtMovilVta != 0 && updtMvilFalla != 0) {
											//Actualizacion garantia
											garanCliente.setEstado(false);
											garanCliente.setFechaBaja(new Date());
											garanCliente.setUsuario2(usuario);										
											int updGarantia = garantiasClienteDAO.update(garantiasCliente);
											if (updGarantia != 0) {
												listaGarantiasClientes = new ArrayList<GarantiasCliente>();
												filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
												listaGarantiasClientes = garantiasClienteDAO.getLista();
												filteredGarantiasClientes = listaGarantiasClientes;
												msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
											}
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
										}
									} else {
										ListaPrecioProducto precioProducto = new ListaPrecioProducto();
										ListaPrecio listaPrecio = listaPrecioDAO.get(cli.getListaPrecio().getId());								
										if (listaPrecio.getId() != 0) {
											precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, prodFalla);
											if (precioProducto.getId() != 0 && precioProducto.getPrecioVenta() != 0) {
												consignacionDetalleN.setCantidad(1);
												consignacionDetalleN.setConsignacion(consignacion);
												consignacionDetalleN.setEliminado(false);
												consignacionDetalleN.setPrecioVenta(precioProducto.getPrecioVenta());
												consignacionDetalleN.setProducto(prodFalla);
												consignacionDetalleN.setSubtotal(precioProducto.getPrecioVenta());
												consignacionDetalleN.setTipoVenta(consignacionDetalleR.getTipoVenta());
												consignacionDetalleN.setVendido(consignacionDetalleR.getVendido());
												int idConsignacionDetalleN = consignacionDetalleDAO.insertar(consignacionDetalleN);
												consignacionDetalleN.setId(idConsignacionDetalleN);
												ConsignacionsDetalleUnidad consignacionUnidadN = new ConsignacionsDetalleUnidad();
												consignacionUnidadN.setConsignacionsDetalle(consignacionDetalleN);
												consignacionUnidadN.setEliminado(false);
												consignacionUnidadN.setEnabled(true);
												consignacionUnidadN.setFechaAlta(consignacionUnidadF.getFechaAlta());
												consignacionUnidadN.setFechaVenta(consignacionUnidadF.getFechaVenta());
												consignacionUnidadN.setNroImei(imeiFalla);
												consignacionUnidadN.setPrecioCompra(unidadFalla.getPrecioCompra());
												consignacionUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
												consignacionUnidadN.setTipoVenta(consignacionUnidadF.getTipoVenta());
												consignacionUnidadN.setUnidadMovil(unidadFalla);
												consignacionUnidadN.setVendido(consignacionUnidadF.getVendido());
												consignacionDetalleUnidadDAO.insertar(consignacionUnidadN);	
												//Baja Detalle
												if (consignacionDetalleR.getCantidad() == 1) {
													consignacionDetalleR.setEliminado(true);											
												} else {
													int cantF = consignacionDetalleR.getCantidad();
													cantF = cantF - 1;
													consignacionDetalleR.setCantidad(cantF);
												}
												consignacionDetalleDAO.update(consignacionDetalleR);										
												//Baja Venta Unidad										
												consignacionUnidadF.setEliminado(true);
												consignacionDetalleUnidadDAO.update(consignacionUnidadF);
												//Baja en Consignacion y Alta en Stock de Unidad									
												unidadReintegro.setEnStock(true);
												unidadReintegro.setEnVenta(false);
												unidadReintegro.setEstado(true);
												unidadReintegro.setConFalla(false);
												unidadReintegro.setDevuelto(false);
												unidadReintegro.setEliminado(false);
												unidadReintegro.setEnConsignacion(false);
												unidadReintegro.setEnGarantiaCliente(false);									
												unidadReintegro.setFechaMod(new Date());
												unidadReintegro.setUsuario3(usuario);
												//Alta en Consignacion y Baja en Stock de Unidad
												unidadFalla.setConFalla(false);
												unidadFalla.setEnConsignacion(true);
												unidadFalla.setDevuelto(false);
												unidadFalla.setEnStock(false);
												unidadFalla.setEnVenta(false);
												unidadFalla.setEstado(true);
												unidadFalla.setEliminado(false);
												unidadFalla.setEnGarantiaCliente(false);
												unidadFalla.setFechaMod(new Date());									
												unidadFalla.setUsuario3(usuario);								
												int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
												int updtMovilVta = unidadMovilDAO.update(unidadReintegro);
												//Actualizacion de Consignacion en productos
												int enConsigF = prodFalla.getEnConsignacion();
												int enConsigC = prodReintegro.getEnConsignacion();
												enConsigF = enConsigF + 1;
												enConsigC = enConsigC - 1;
												prodFalla.setEnConsignacion(enConsigF);
												prodReintegro.setEnConsignacion(enConsigC);
												productoDAO.update(prodFalla);
												productoDAO.update(prodReintegro);
												if (updtMovilVta != 0 && updtMvilFalla != 0) {
													//Actualizacion garantia
													garanCliente.setEstado(false);
													garanCliente.setFechaBaja(new Date());
													garanCliente.setUsuario2(usuario);										
													int updGarantia = garantiasClienteDAO.update(garantiasCliente);
													if (updGarantia != 0) {
														listaGarantiasClientes = new ArrayList<GarantiasCliente>();
														filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
														listaGarantiasClientes = garantiasClienteDAO.getLista();
														filteredGarantiasClientes = listaGarantiasClientes;
														msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
													} else {
														msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
													}
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
												}
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee Precio "
														+ "para ese Producto en la Lista de Precio", null);
											}									
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee "
													+ "Lista de Precio", null);
										}
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar la baja de garantía, el Móvil de Reintegro esta registrado en Cuotas, "
											+ "realice las bajas de las mismas primero!", null);
								}																
							}
						}
						if (existeVC) {
							if (prodFalla.getId() == prodReintegro.getId()) {
								Cuota cuota = cuotaDAO.get(imeiReintegro);
								if (cuota.getId() != 0) {
									cuota.setNroImei(imeiFalla);
									cuotaDAO.update(cuota);
								}
								CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiReintegro);
								if (cuotaVenta.getId() != 0) {
									cuotaVenta.setNroImei(imeiFalla);
									cuotaVentaDAO.update(cuotaVenta);
								}
								ventaConsUnidadF.setNroImei(imeiFalla);
								ventaConsUnidadF.setEliminado(false);
								ventaConsUnidadF.setPrecioCompra(unidadFalla.getPrecioCompra());
								
								int updtVentaCons = ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidadF);
								if (updtVentaCons != 0) {
									unidadFalla.setDevuelto(false);
									unidadFalla.setEnStock(false);
									unidadFalla.setEnVenta(true);
									unidadFalla.setEstado(true);
									unidadFalla.setFechaMod(new Date());
									unidadFalla.setUsuario3(usuario);
									
									int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
									if (updtMvilFalla != 0) {
										garanCliente.setEstado(false);
										garanCliente.setFechaBaja(new Date());
										garanCliente.setUsuario2(usuario);
										
										int updGarantia = garantiasClienteDAO.update(garanCliente);
										if (updGarantia != 0) {
											msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
										}
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Venta de Consignación correspondiente!", null);
								}
							} else {
								Cuota cuota = cuotaDAO.get(imeiReintegro);
								CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiReintegro);
								if (cuota.getId() == 0 && cuotaVenta.getId() == 0) {
									VentasConsDetalle ventConsDetalleR = ventaConsignacionDetalleDAO.get(ventaConsUnidadF.getVentasConsDetalle().getId());							
									VentasCon ventasCon = ventaConsignacionDAO.get(ventConsDetalleR.getVentasCon().getId());
									Cliente cli = clienteDAO.get(ventasCon.getCliente().getId());								
									float montoDescuento = 0;
									float montoSumado = 0;
									boolean existeDetalle = false;
									List<VentasConsDetalle> detallesVentaCons = ventaConsignacionDetalleDAO.getLista(ventasCon);
									VentasConsDetalle ventConsDetalleN = new VentasConsDetalle();
									for (VentasConsDetalle ventasConsD : detallesVentaCons) {
										if (ventasConsD.getProducto().getId() == prodFalla.getId()) {
											existeDetalle = true;
											ventConsDetalleN = ventasConsD;
										}
									}
									if (existeDetalle) {
										//Alta Nuevo Detalle Unidad
										int cantNueva = ventConsDetalleN.getCantidad();
										montoSumado = ventConsDetalleN.getPrecioVenta();
										cantNueva = cantNueva + 1;
										float subtotal = cantNueva * montoSumado;
										ventConsDetalleN.setCantidad(cantNueva);
										ventConsDetalleN.setSubtotal(subtotal);
										VentasConsDetalleUnidad ventaConsUnidadN = new VentasConsDetalleUnidad();
										ventaConsUnidadN.setConsignacionsDetalleUnidad(ventaConsUnidadF.getConsignacionsDetalleUnidad());
										ventaConsUnidadN.setEliminado(false);
										ventaConsUnidadN.setNroImei(imeiFalla);
										ventaConsUnidadN.setPrecioCompra(unidadFalla.getPrecioCompra());
										ventaConsUnidadN.setPrecioVenta(ventConsDetalleN.getPrecioVenta());
										ventaConsUnidadN.setVentasConsDetalle(ventConsDetalleN);												
										ventaConsignacionDetalleUnidadDAO.insertar(ventaConsUnidadN);
										ventaConsignacionDetalleDAO.update(ventConsDetalleN);
										//Baja Detalle
										if (ventConsDetalleR.getCantidad() == 1) {
											ventConsDetalleR.setEliminado(true);									
										} else {
											int cantF = ventConsDetalleR.getCantidad();
											cantF = cantF - 1;
											ventConsDetalleR.setCantidad(cantF);
										}								
										ventaConsignacionDetalleDAO.update(ventConsDetalleR);
										//Baja Venta Unidad
										montoDescuento = ventaConsUnidadF.getPrecioVenta();
										ventaConsUnidadF.setEliminado(true);
										ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidadF);
										//Actualizacion Monto Venta
										float montoActual = ventasCon.getMonto();
										montoActual = montoActual - montoDescuento;
										montoActual = montoActual + montoSumado;
										ventasCon.setMonto(montoActual);
										ventaConsignacionDAO.update(ventasCon);
										//Actualizacion Cuenta Corriente
										int idVenta = ventasCon.getId();
										//Baja de venta en Cuenta Corriente, para realizar el alta luego
										CuentaCorriente cuenta = new CuentaCorriente();
										CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();								
										ccCliente.setIdMovimiento(ventasCon.getId());
										ccCliente.setNombreTabla("VentasCon");
										cuenta.deleteCuentaCorriente(ccCliente);
										//Insercion de CC
										ccCliente = new CuentasCorrientesCliente();							
										ccCliente.setCliente(cli);
										ccCliente.setDebe(montoActual);
										ccCliente.setDetalle("Venta Consignación nro: " + idVenta);				
										ccCliente.setFecha(ventasCon.getFecha());
										ccCliente.setIdMovimiento(idVenta);
										ccCliente.setMonto(montoActual);
										ccCliente.setNombreTabla("VentasCon");
										ccCliente.setUsuario(usuario);
										cuenta.insertarCC(ccCliente);
										//Baja en Venta y Alta en Stock de Unidad
										unidadReintegro.setEnStock(true);
										unidadReintegro.setEnVenta(false);
										unidadReintegro.setEstado(true);
										unidadReintegro.setConFalla(false);
										unidadReintegro.setDevuelto(false);
										unidadReintegro.setEliminado(false);
										unidadReintegro.setEnConsignacion(false);
										unidadReintegro.setEnGarantiaCliente(false);									
										unidadReintegro.setFechaMod(new Date());
										unidadReintegro.setUsuario3(usuario);
										//Alta en Venta y Baja en Stock de Unidad
										unidadFalla.setConFalla(false);
										unidadFalla.setEnConsignacion(false);
										unidadFalla.setDevuelto(false);
										unidadFalla.setEnStock(false);
										unidadFalla.setEnVenta(true);
										unidadFalla.setEstado(true);
										unidadFalla.setEliminado(false);
										unidadFalla.setEnGarantiaCliente(false);
										unidadFalla.setFechaMod(new Date());									
										unidadFalla.setUsuario3(usuario);								
										int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
										int updtMovilVta = unidadMovilDAO.update(unidadReintegro);												
										//Actualizacion de Stock en productos
										int stockF = prodFalla.getStock();
										int stockR = prodReintegro.getStock();
										stockF = stockF - 1;
										stockR = stockR + 1;
										prodFalla.setStock(stockF);
										prodReintegro.setStock(stockR);
										productoDAO.update(prodFalla);
										productoDAO.update(prodReintegro);									
										if (updtMovilVta != 0 && updtMvilFalla != 0) {
											//Actualizacion garantia
											garanCliente.setEstado(false);
											garanCliente.setFechaBaja(new Date());
											garanCliente.setUsuario2(usuario);
											
											int updGarantia = garantiasClienteDAO.update(garanCliente);
											if (updGarantia != 0) {
												listaGarantiasClientes = new ArrayList<GarantiasCliente>();
												filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
												listaGarantiasClientes = garantiasClienteDAO.getLista();
												filteredGarantiasClientes = listaGarantiasClientes;
												msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
											}
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
										}
									} else {
										ListaPrecioProducto precioProducto = new ListaPrecioProducto();
										ListaPrecio listaPrecio = listaPrecioDAO.get(cli.getListaPrecio().getId());
										if (listaPrecio.getId() != 0) {
											precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, prodFalla);
											if (precioProducto.getId() != 0 && precioProducto.getPrecioVenta() != 0) {											
												ventConsDetalleN.setCantidad(1);
												ventConsDetalleN.setConsignacionsDetalle(ventConsDetalleR.getConsignacionsDetalle());
												ventConsDetalleN.setEliminado(false);
												ventConsDetalleN.setPrecioVenta(precioProducto.getPrecioVenta());
												ventConsDetalleN.setProducto(prodFalla);
												ventConsDetalleN.setSubtotal(precioProducto.getPrecioVenta());
												ventConsDetalleN.setVentasCon(ventasCon);										
												int idVentaDetalleN = ventaConsignacionDetalleDAO.insertar(ventConsDetalleN);
												ventConsDetalleN.setId(idVentaDetalleN);
												VentasConsDetalleUnidad ventaConsUnidadN = new VentasConsDetalleUnidad();
												ventaConsUnidadN.setConsignacionsDetalleUnidad(ventaConsUnidadF.getConsignacionsDetalleUnidad());
												ventaConsUnidadN.setEliminado(false);
												ventaConsUnidadN.setNroImei(imeiFalla);
												ventaConsUnidadN.setPrecioCompra(unidadFalla.getPrecioCompra());
												ventaConsUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
												montoSumado = precioProducto.getPrecioVenta();
												ventaConsUnidadN.setVentasConsDetalle(ventConsDetalleN);										
												ventaConsignacionDetalleUnidadDAO.insertar(ventaConsUnidadN);
												//Baja Detalle
												if (ventConsDetalleR.getCantidad() == 1) {
													ventConsDetalleR.setEliminado(true);									
												} else {
													int cantF = ventConsDetalleR.getCantidad();
													cantF = cantF - 1;
													ventConsDetalleR.setCantidad(cantF);
												}
												ventaConsignacionDetalleDAO.update(ventConsDetalleR);
												//Baja Venta Unidad
												montoDescuento = ventaConsUnidadF.getPrecioVenta();
												ventaConsUnidadF.setEliminado(true);
												ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidadF);
												//Actualizacion Monto Venta
												float montoActual = ventasCon.getMonto();
												montoActual = montoActual - montoDescuento;
												montoActual = montoActual + montoSumado;
												ventasCon.setMonto(montoActual);
												ventaConsignacionDAO.update(ventasCon);
												//Actualizacion Cuenta Corriente
												int idVenta = ventasCon.getId();
												//Baja de venta en Cuenta Corriente, para realizar el alta luego
												CuentaCorriente cuenta = new CuentaCorriente();
												CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();								
												ccCliente.setIdMovimiento(ventasCon.getId());
												ccCliente.setNombreTabla("VentasCon");
												cuenta.deleteCuentaCorriente(ccCliente);											
												//Insercion de CC
												ccCliente = new CuentasCorrientesCliente();							
												ccCliente.setCliente(cli);
												ccCliente.setDebe(montoActual);
												ccCliente.setDetalle("Venta Consignación nro: " + idVenta);				
												ccCliente.setFecha(ventasCon.getFecha());
												ccCliente.setIdMovimiento(idVenta);
												ccCliente.setMonto(montoActual);
												ccCliente.setNombreTabla("VentasCon");
												ccCliente.setUsuario(usuario);
												cuenta.insertarCC(ccCliente);											
												//Baja en Venta y Alta en Stock de Unidad
												unidadReintegro.setEnStock(true);
												unidadReintegro.setEnVenta(false);
												unidadReintegro.setEstado(true);
												unidadReintegro.setConFalla(false);
												unidadReintegro.setDevuelto(false);
												unidadReintegro.setEliminado(false);
												unidadReintegro.setEnConsignacion(false);
												unidadReintegro.setEnGarantiaCliente(false);									
												unidadReintegro.setFechaMod(new Date());
												unidadReintegro.setUsuario3(usuario);
												//Alta en Venta y Baja en Stock de Unidad
												unidadFalla.setConFalla(false);
												unidadFalla.setEnConsignacion(false);
												unidadFalla.setDevuelto(false);
												unidadFalla.setEnStock(false);
												unidadFalla.setEnVenta(true);
												unidadFalla.setEstado(true);
												unidadFalla.setEliminado(false);
												unidadFalla.setEnGarantiaCliente(false);
												unidadFalla.setFechaMod(new Date());									
												unidadFalla.setUsuario3(usuario);								
												int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
												int updtMovilVta = unidadMovilDAO.update(unidadReintegro);												
												//Actualizacion de Stock en productos
												int stockF = prodFalla.getStock();
												int stockR = prodReintegro.getStock();
												stockF = stockF - 1;
												stockR = stockR + 1;
												prodFalla.setStock(stockF);
												prodReintegro.setStock(stockR);
												productoDAO.update(prodFalla);
												productoDAO.update(prodReintegro);
												if (updtMovilVta != 0 && updtMvilFalla != 0) {
													//Actualizacion garantia
													garanCliente.setEstado(false);
													garanCliente.setFechaBaja(new Date());
													garanCliente.setUsuario2(usuario);
													
													int updGarantia = garantiasClienteDAO.update(garanCliente);
													if (updGarantia != 0) {
														listaGarantiasClientes = new ArrayList<GarantiasCliente>();
														filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
														listaGarantiasClientes = garantiasClienteDAO.getLista();
														filteredGarantiasClientes = listaGarantiasClientes;
														msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
													} else {
														msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
													}
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
												}
												
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee Precio "
														+ "para ese Producto en la Lista de Precio", null);
											}									
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee "
													+ "Lista de Precio", null);
										}
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar la baja de garantía, el Móvil de Reintegro esta registrado en Cuotas, "
											+ "realice las bajas de las mismas primero!", null);
								}															
							}
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede registrar la baja del ticket, el nro imei que esta en falla, ha sido asignado a un movimiento (Venta o Consignación)!", null);
					}
				}
				
				if (garanCliente.getResolucion().equals(resolucion3)) {
					String accion = "Ninguna Acción";
					
					if (garanCliente.getAccionRealizar().equals(accion)) {
						String imeiFalla = garanCliente.getImeiFalla();
						UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
						
						unidadFalla.setDevuelto(false);
						unidadFalla.setConFalla(false);
						unidadFalla.setFechaMod(new Date());
						unidadFalla.setUsuario3(usuario);
						
						int updtMovil = unidadMovilDAO.update(unidadFalla);
						if (updtMovil != 0) {
							garanCliente.setEstado(false);
							garanCliente.setFechaBaja(new Date());
							garanCliente.setUsuario2(usuario);
							
							int updGarantia = garantiasClienteDAO.update(garanCliente);					
							if (updGarantia != 0) {
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
						}
						
					} else {
						
						String imeiFalla = garanCliente.getImeiFalla();
						UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
						Producto prod = productoDAO.get(unidadFalla.getProducto().getId());
						Cliente cli = clienteDAO.get(garanCliente.getCliente().getId());
						
						int idMovimiento = garanCliente.getIdMovimiento();
						String nombreTabla = garanCliente.getNombreMovimiento();						
						float precioU = garanCliente.getPrecioUnidad();
						int idVentaCon = garanCliente.getIdConVenta();
						Date fechaAltaCon = garanCliente.getFechaAltaConsignacion();
						Date fechaVentaCon = garanCliente.getFechaVentaConsignacion();
						boolean vendido = garanCliente.getVendido();
						
						if (nombreTabla.equals("Venta")) {
							Venta vent = ventaDAO.get(idMovimiento);
							if (vent.getEstado()) {
								VentasDetalle ventDetalle = ventaDetalleDAO.get(vent, prod);						
								if (ventDetalle.getId() != 0) {
									int cant = ventDetalle.getCantidad() + 1;
									float subtotal = ventDetalle.getSubtotal() + precioU;
									
									ventDetalle.setCantidad(cant);
									ventDetalle.setSubtotal(subtotal);
									
									VentasDetalleUnidad ventaUnidad = new VentasDetalleUnidad();
									ventaUnidad.setEliminado(false);
									ventaUnidad.setNroImei(imeiFalla);
									ventaUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
									ventaUnidad.setPrecioVenta(precioU);
									ventaUnidad.setUnidadMovil(unidadFalla);
									ventaUnidad.setVentasDetalle(ventDetalle);
									
									ventaDetalleDAO.update(ventDetalle);
									ventaDetalleUnidadDAO.insertar(ventaUnidad);
								} else {
									int cant = 1;
									float subtotal = precioU;
									ventDetalle = new VentasDetalle();
									ventDetalle.setAccesorio(false);
									ventDetalle.setCantidad(cant);
									ventDetalle.setEliminado(false);
									ventDetalle.setPrecioVenta(precioU);
									ventDetalle.setProducto(prod);
									ventDetalle.setSubtotal(subtotal);
									ventDetalle.setVenta(vent);
									int idDetalle = ventaDetalleDAO.insertar(ventDetalle);
									ventDetalle.setId(idDetalle);
									
									VentasDetalleUnidad ventaUnidad = new VentasDetalleUnidad();
									ventaUnidad.setEliminado(false);
									ventaUnidad.setNroImei(imeiFalla);
									ventaUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
									ventaUnidad.setPrecioVenta(precioU);
									ventaUnidad.setUnidadMovil(unidadFalla);
									ventaUnidad.setVentasDetalle(ventDetalle);
									ventaDetalleUnidadDAO.insertar(ventaUnidad);
								}
								float total = vent.getMonto() + precioU;
								vent.setMonto(total);
								vent.setFechaMod(new Date());
								vent.setUsuario3(usuario);
								
								CuentaCorriente cuenta = new CuentaCorriente();
								CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
								if(vent.getTipo().equals("c.c.")){
									ccCliente.setIdMovimiento(vent.getId());
									ccCliente.setNombreTabla("Venta");
									cuenta.deleteCuentaCorriente(ccCliente);
								}
								if(vent.getTipo().equals("ctdo.")){
									cuentaCorrienteDAO.deletePorMovimientoCliente(idMovimiento, "Venta", cli);
											
									MovimientoCaja movCaja = new MovimientoCaja();
									Caja mov = new Caja();
									mov.setIdMovimiento(idMovimiento);
									mov.setNombreTabla("Venta");
									movCaja.deleteCaja(mov);
								}
								
								int stock = prod.getStock() - 1;
								prod.setStock(stock);
								productoDAO.update(prod);
								
								ventaDAO.update(vent);
								
								//Insercion de CC
								ccCliente = new CuentasCorrientesCliente();	
								ccCliente.setCliente(cli);
								ccCliente.setDebe(total);
								ccCliente.setDetalle("Venta nro: " + idMovimiento);				
								ccCliente.setFecha(vent.getFecha());
								ccCliente.setIdMovimiento(idMovimiento);
								ccCliente.setMonto(total);
								ccCliente.setNombreTabla("Venta");
								ccCliente.setUsuario(usuario);
								cuenta.insertarCC(ccCliente);
								//Si es una venta de contado, inserto el pago en CC y en Caja
								if (vent.getTipo().equals("ctdo.")) {
									ccCliente = new CuentasCorrientesCliente();								
									ccCliente.setCliente(cli);
									ccCliente.setDetalle("Pago de contado - Venta nro: " + idMovimiento);
									ccCliente.setFecha(vent.getFecha());
									ccCliente.setHaber(total);
									ccCliente.setIdMovimiento(idMovimiento);
									ccCliente.setMonto(total);
									ccCliente.setNombreTabla("Venta");
									ccCliente.setUsuario(usuario);
									cuenta.insertarCC(ccCliente);
									MovimientoCaja movimientoCaja = new MovimientoCaja();
									Caja caja = new Caja();
									caja.setConcepto("Cobro de Venta nro: " + idMovimiento);
									caja.setFecha(vent.getFecha());
									caja.setIdMovimiento(idMovimiento);
									caja.setMonto(total);
									caja.setNombreTabla("Venta");
									caja.setUsuario(usuario);
									movimientoCaja.insertarCaja(caja);
								}
							} else {
								vent.setCliente(cli);
								vent.setEstado(true);
								vent.setFechaMod(new Date());
								vent.setMonto(precioU);
								vent.setUsuario3(usuario);
								ventaDAO.update(vent);
								
								int cant = 1;
								float subtotal = precioU;
								VentasDetalle ventDetalle = new VentasDetalle();
								ventDetalle.setAccesorio(false);
								ventDetalle.setCantidad(cant);
								ventDetalle.setEliminado(false);
								ventDetalle.setPrecioVenta(precioU);
								ventDetalle.setProducto(prod);
								ventDetalle.setSubtotal(subtotal);
								ventDetalle.setVenta(vent);
								int idDetalle = ventaDetalleDAO.insertar(ventDetalle);
								ventDetalle.setId(idDetalle);
								
								VentasDetalleUnidad ventaUnidad = new VentasDetalleUnidad();
								ventaUnidad.setEliminado(false);
								ventaUnidad.setNroImei(imeiFalla);
								ventaUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
								ventaUnidad.setPrecioVenta(precioU);
								ventaUnidad.setUnidadMovil(unidadFalla);
								ventaUnidad.setVentasDetalle(ventDetalle);
								ventaDetalleUnidadDAO.insertar(ventaUnidad);
								
								CuentaCorriente cuenta = new CuentaCorriente();
								CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
								
								int stock = prod.getStock() - 1;
								prod.setStock(stock);
								productoDAO.update(prod);
								
								//Insercion de CC
								ccCliente.setCliente(cli);
								ccCliente.setDebe(precioU);
								ccCliente.setDetalle("Venta nro: " + idMovimiento);				
								ccCliente.setFecha(vent.getFecha());
								ccCliente.setIdMovimiento(idMovimiento);
								ccCliente.setMonto(precioU);
								ccCliente.setNombreTabla("Venta");
								ccCliente.setUsuario(usuario);
								cuenta.insertarCC(ccCliente);
								//Si es una venta de contado, inserto el pago en CC y en Caja
								if (vent.getTipo().equals("ctdo.")) {
									ccCliente = new CuentasCorrientesCliente();								
									ccCliente.setCliente(cli);
									ccCliente.setDetalle("Pago de contado - Venta nro: " + idMovimiento);
									ccCliente.setFecha(vent.getFecha());
									ccCliente.setHaber(precioU);
									ccCliente.setIdMovimiento(idMovimiento);
									ccCliente.setMonto(precioU);
									ccCliente.setNombreTabla("Venta");
									ccCliente.setUsuario(usuario);
									cuenta.insertarCC(ccCliente);
									MovimientoCaja movimientoCaja = new MovimientoCaja();
									Caja caja = new Caja();
									caja.setConcepto("Cobro de Venta nro: " + idMovimiento);
									caja.setFecha(vent.getFecha());
									caja.setIdMovimiento(idMovimiento);
									caja.setMonto(precioU);
									caja.setNombreTabla("Venta");
									caja.setUsuario(usuario);
									movimientoCaja.insertarCaja(caja);
								}
							}
							unidadFalla.setEnStock(false);
							unidadFalla.setEnVenta(true);
						}
						if (nombreTabla.equals("Consignacion")) {
							Consignacion consignacion = consignacionDAO.get(idMovimiento);
							if (consignacion.getEstado()) {
								ConsignacionsDetalle consDetalle = consignacionDetalleDAO.get(consignacion, prod);
								if (consDetalle.getId() != 0) {
									int cant = consDetalle.getCantidad() + 1;
									float subtotal = consDetalle.getSubtotal() + precioU;
									
									consDetalle.setCantidad(cant);
									consDetalle.setSubtotal(subtotal);
									
									ConsignacionsDetalleUnidad consignacionUnidad = new ConsignacionsDetalleUnidad();
									consignacionUnidad.setEliminado(false);
									consignacionUnidad.setNroImei(imeiFalla);
									consignacionUnidad.setFechaAlta(fechaAltaCon);
									consignacionUnidad.setFechaVenta(fechaVentaCon);
									consignacionUnidad.setVendido(vendido);
									consignacionUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
									consignacionUnidad.setPrecioVenta(precioU);
									consignacionUnidad.setConsignacionsDetalle(consDetalle);
									
									consignacionDetalleDAO.update(consDetalle);
									consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
								} else {
									int cant = 1;
									float subtotal = precioU;
									consDetalle = new ConsignacionsDetalle();
									consDetalle.setCantidad(cant);
									consDetalle.setEliminado(false);
									consDetalle.setPrecioVenta(precioU);
									consDetalle.setProducto(prod);
									consDetalle.setSubtotal(subtotal);
									consDetalle.setConsignacion(consignacion);
									int idDetalle = consignacionDetalleDAO.insertar(consDetalle);
									consDetalle.setId(idDetalle);
									
									ConsignacionsDetalleUnidad consignacionUnidad = new ConsignacionsDetalleUnidad();
									consignacionUnidad.setEliminado(false);
									consignacionUnidad.setNroImei(imeiFalla);
									consignacionUnidad.setFechaAlta(fechaAltaCon);
									consignacionUnidad.setFechaVenta(fechaVentaCon);
									consignacionUnidad.setVendido(vendido);
									consignacionUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
									consignacionUnidad.setPrecioVenta(precioU);
									consignacionUnidad.setConsignacionsDetalle(consDetalle);
									consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
								}
								float total = consignacion.getMonto() + precioU;
								consignacion.setMonto(total);
								consignacion.setFechaMod(new Date());
								consignacion.setUsuario3(usuario);
								
								int enConsignacion = prod.getEnConsignacion();
								int enStock = prod.getStock();
								enConsignacion = enConsignacion + 1;
								enStock = enStock - 1;
								prod.setStock(enStock);
								prod.setEnConsignacion(enConsignacion);
								productoDAO.update(prod);
								
								consignacionDAO.update(consignacion);
							} else {
								consignacion.setCliente(cli);
								consignacion.setEstado(true);
								consignacion.setFechaMod(new Date());
								consignacion.setMonto(precioU);
								consignacion.setUsuario3(usuario);
								consignacionDAO.update(consignacion);
								
								int cant = 1;
								float subtotal = precioU;
								ConsignacionsDetalle consDetalle = new ConsignacionsDetalle();
								consDetalle.setCantidad(cant);
								consDetalle.setEliminado(false);
								consDetalle.setPrecioVenta(precioU);
								consDetalle.setProducto(prod);
								consDetalle.setSubtotal(subtotal);
								consDetalle.setConsignacion(consignacion);
								int idDetalle = consignacionDetalleDAO.insertar(consDetalle);
								consDetalle.setId(idDetalle);
								
								ConsignacionsDetalleUnidad consignacionUnidad = new ConsignacionsDetalleUnidad();
								consignacionUnidad.setEliminado(false);
								consignacionUnidad.setNroImei(imeiFalla);
								consignacionUnidad.setFechaAlta(fechaAltaCon);
								consignacionUnidad.setFechaVenta(fechaVentaCon);
								consignacionUnidad.setVendido(vendido);
								consignacionUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
								consignacionUnidad.setPrecioVenta(precioU);
								consignacionUnidad.setConsignacionsDetalle(consDetalle);
								consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
																
								int enConsignacion = prod.getEnConsignacion();
								int enStock = prod.getStock();
								enConsignacion = enConsignacion + 1;
								enStock = enStock - 1;
								prod.setStock(enStock);
								prod.setEnConsignacion(enConsignacion);
								productoDAO.update(prod);
							}
							
							unidadFalla.setEnConsignacion(true);
						}	
						if (vendido) {
							VentasCon ventCon = ventaConsignacionDAO.get(idVentaCon);
							if (ventCon.getEstado()) {
								VentasConsDetalle ventDetalle = ventaConsignacionDetalleDAO.get(ventCon, prod);
								if (ventDetalle.getId() != 0) {
									int cant = ventDetalle.getCantidad() + 1;
									float subtotal = ventDetalle.getSubtotal() + precioU;
									
									ventDetalle.setCantidad(cant);
									ventDetalle.setSubtotal(subtotal);
									
									VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
									ventaUnidad.setEliminado(false);
									ventaUnidad.setNroImei(imeiFalla);
									ventaUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
									ventaUnidad.setPrecioVenta(precioU);
									ventaUnidad.setVentasConsDetalle(ventDetalle);
									
									ventaConsignacionDetalleDAO.update(ventDetalle);
									ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
								} else {
									int cant = 1;
									float subtotal = precioU;
									ventDetalle = new VentasConsDetalle();
									ventDetalle.setCantidad(cant);
									ventDetalle.setEliminado(false);
									ventDetalle.setPrecioVenta(precioU);
									ventDetalle.setProducto(prod);
									ventDetalle.setSubtotal(subtotal);
									ventDetalle.setVentasCon(ventCon);
									int idDetalle = ventaConsignacionDetalleDAO.insertar(ventDetalle);
									ventDetalle.setId(idDetalle);
									
									VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
									ventaUnidad.setEliminado(false);
									ventaUnidad.setNroImei(imeiFalla);
									ventaUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
									ventaUnidad.setPrecioVenta(precioU);
									ventaUnidad.setVentasConsDetalle(ventDetalle);
									ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
								}
								float total = ventCon.getMonto() + precioU;
								ventCon.setMonto(total);
								ventCon.setFechaMod(new Date());
								ventCon.setUsuario3(usuario);
								
								CuentaCorriente cuenta = new CuentaCorriente();
								CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
								if(ventCon.getTipo().equals("c.c.")){
									ccCliente.setIdMovimiento(ventCon.getId());
									ccCliente.setNombreTabla("VentasCon");
									cuenta.deleteCuentaCorriente(ccCliente);
								}
								
								int enConsignacion = prod.getEnConsignacion();
								enConsignacion = enConsignacion - 1;
								prod.setEnConsignacion(enConsignacion);
								productoDAO.update(prod);
								
								ventaConsignacionDAO.update(ventCon);
								
								//Insercion de CC
								ccCliente = new CuentasCorrientesCliente();	
								ccCliente.setCliente(cli);
								ccCliente.setDebe(total);
								ccCliente.setDetalle("Venta Consignación nro: " + idVentaCon);				
								ccCliente.setFecha(ventCon.getFecha());
								ccCliente.setIdMovimiento(idVentaCon);
								ccCliente.setMonto(total);
								ccCliente.setNombreTabla("VentasCon");
								ccCliente.setUsuario(usuario);
								cuenta.insertarCC(ccCliente);
							} else {
								ventCon.setCliente(cli);
								ventCon.setEstado(true);
								ventCon.setFechaMod(new Date());
								ventCon.setMonto(precioU);
								ventCon.setUsuario3(usuario);
								ventaConsignacionDAO.update(ventCon);
								
								int cant = 1;
								float subtotal = precioU;
								VentasConsDetalle ventDetalle = new VentasConsDetalle();
								ventDetalle.setCantidad(cant);
								ventDetalle.setEliminado(false);
								ventDetalle.setPrecioVenta(precioU);
								ventDetalle.setProducto(prod);
								ventDetalle.setSubtotal(subtotal);
								ventDetalle.setVentasCon(ventCon);
								int idDetalle = ventaConsignacionDetalleDAO.insertar(ventDetalle);
								ventDetalle.setId(idDetalle);
								
								VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
								ventaUnidad.setEliminado(false);
								ventaUnidad.setNroImei(imeiFalla);
								ventaUnidad.setPrecioCompra(unidadFalla.getPrecioCompra());
								ventaUnidad.setPrecioVenta(precioU);
								ventaUnidad.setVentasConsDetalle(ventDetalle);
								ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
								
								CuentaCorriente cuenta = new CuentaCorriente();
								CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
								
								int enConsignacion = prod.getEnConsignacion();
								enConsignacion = enConsignacion - 1;
								prod.setEnConsignacion(enConsignacion);
								productoDAO.update(prod);
								
								//Insercion de CC
								ccCliente.setCliente(cli);
								ccCliente.setDebe(precioU);
								ccCliente.setDetalle("Venta Consignación nro: " + idVentaCon);				
								ccCliente.setFecha(ventCon.getFecha());
								ccCliente.setIdMovimiento(idVentaCon);
								ccCliente.setMonto(precioU);
								ccCliente.setNombreTabla("VentasCon");
								ccCliente.setUsuario(usuario);
								cuenta.insertarCC(ccCliente);
							}
							unidadFalla.setEnStock(false);
							unidadFalla.setEnVenta(true);
						}					
						
						unidadFalla.setDevuelto(false);
						unidadFalla.setEliminado(false);						
						unidadFalla.setEstado(true);
						unidadFalla.setConFalla(false);
						unidadFalla.setFechaMod(new Date());
						unidadFalla.setUsuario3(usuario);
						int updtUnidad = unidadMovilDAO.update(unidadFalla);
						if (updtUnidad != 0) {
							garanCliente.setEstado(false);
							garanCliente.setFechaBaja(new Date());
							garanCliente.setUsuario2(usuario);
							
							int updGarantia = garantiasClienteDAO.update(garanCliente);					
							if (updGarantia != 0) {
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
						}
					}
				}
			} catch (Exception e) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al iniciar el Ticket de Garantía. "
						+ "Error original: " + e.getMessage(), null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = null;
			try {
				String imei = garanCliente.getImeiFalla();
				UnidadMovil unidad = unidadMovilDAO.get(imei);
				unidad.setEnGarantiaCliente(false);
				int updtMovil = unidadMovilDAO.update(unidad);
				if (updtMovil != 0) {
					garanCliente.setEstado(false);
					garanCliente.setFechaBaja(new Date());
					garanCliente.setUsuario2(usuario);
					int updtGarantia = garantiasClienteDAO.update(garanCliente);
					if (updtGarantia != 0) {
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al dar de baja el Ticket de Garantía! Inténtelo nuevamente!", null);
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al dar de baja el Ticket de Garantía! Inténtelo nuevamente!", null);
				}
			} catch(Exception e) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al iniciar el Ticket de Garantía. "
						+ "Error original: " + e.getMessage(), null);
			}	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void bajaProveedor(GarantiasProveedore garanProveedor) {
		if (garanProveedor.getFinalizado()) {
			FacesMessage msg = null;
			if (garanProveedor.getConcepto().equals("Entrega por reparación")) {
				String imei = garanProveedor.getImeiFalla();
				int idGarantia = garanProveedor.getId();
				
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
				ccProveedor.setIdMovimiento(idGarantia);
				ccProveedor.setNombreTabla("GarantiasProveedor");
				cuenta.deleteCuentaCorriente(ccProveedor);
				garanProveedor.setEstado(false);
				garanProveedor.setFechaBaja(new Date());
				garanProveedor.setUsuario2(usuario);
				UnidadMovil unidad = unidadMovilDAO.get(imei);
				unidad.setConFalla(false);
				unidad.setUsuario3(usuario);
				unidad.setFechaMod(new Date());
				int updtMovil = unidadMovilDAO.update(unidad);
				int updGarantia = garantiasProveedorDAO.update(garanProveedor);
				if (updGarantia != 0 && updtMovil != 0) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Ticket registrada!", null);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
				}								
			} else {
				String resolucion1 = "Mismo Equipo";
				String resolucion2 = "Cambio de Equipo";
				String resolucion3 = "No posee arreglo";
				if (garanProveedor.getResolucion().equals(resolucion1)) {
					garanProveedor.setEstado(false);
					garanProveedor.setFechaBaja(new Date());
					garanProveedor.setUsuario2(usuario);
					int updtGarantia = garantiasProveedorDAO.update(garanProveedor);
					if (updtGarantia != 0) {
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Ticket registrada!", null);
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
					}
				}
				if (garanProveedor.getResolucion().equals(resolucion2)) {
					String imeiFalla = garanProveedor.getImeiFalla();
					String imeiReintegro = garanProveedor.getImeiReintegro();
					UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
					if (unidadFalla.getId() == 0) {
						UnidadMovil unidadReintegro = unidadMovilDAO.get(imeiReintegro);
						unidadReintegro.setNroImei(imeiFalla);
						unidadReintegro.setFechaMod(new Date());
						unidadReintegro.setUsuario3(usuario);
						int updtMovil = unidadMovilDAO.update(unidadReintegro);
						if (updtMovil != 0) {
							boolean actualizo = true;
							ComprasDetalleUnidad unidadCompra = compraDetalleUnidadDAO.get(imeiReintegro);
							if (unidadCompra.getId() != 0) {								
								unidadCompra.setNroImei(imeiFalla);
								int updtCompra = compraDetalleUnidadDAO.update(unidadCompra);
								if (updtCompra == 0) {
									actualizo = false;
								}
							}
							VentasDetalleUnidad unidadVenta = ventaDetalleUnidadDAO.get(imeiReintegro);
							if (unidadVenta.getId() != 0) {
								unidadVenta.setNroImei(imeiFalla);
								int updtVenta = ventaDetalleUnidadDAO.update(unidadVenta);
								if (updtVenta == 0) {
									actualizo = false;
								}
							}
							ConsignacionsDetalleUnidad unidadConsignacion = consignacionDetalleUnidadDAO.get(imeiReintegro);
							if (unidadConsignacion.getId() != 0) {
								unidadConsignacion.setNroImei(imeiFalla);
								int updtConsignacion = consignacionDetalleUnidadDAO.update(unidadConsignacion);
								if (updtConsignacion == 0) {
									actualizo = false;
								}
							}
							VentasConsDetalleUnidad unidadVentaCons = ventaConsignacionDetalleUnidadDAO.get(imeiReintegro);
							if (unidadVentaCons.getId() != 0) {
								unidadVentaCons.setNroImei(imeiFalla);
								int updtVentaCons = ventaConsignacionDetalleUnidadDAO.update(unidadVentaCons);
								if (updtVentaCons == 0) {
									actualizo = false;
								}
							}
							if (actualizo) {
								garanProveedor.setFechaBaja(new Date());
								garanProveedor.setEstado(false);
								garanProveedor.setUsuario2(usuario);							
								int updGarantia = garantiasProveedorDAO.update(garanProveedor);
								if (updGarantia != 0) {
									msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Ticket registrado!", null);
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja del Ticket!", null);
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al actualizar el/los movimientos "
										+ "asociados a la Unidad Movil", null);
							}	
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al actualizar la Unidad Móvil asociada!", null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de imei ya se encuentra registrado al producto " 
								+ unidadFalla.getProducto().getNombre(), null);
					}
				}
				if (garanProveedor.getResolucion().equals(resolucion3)) {
					String accion = "Ninguna Acción";
					if (garanProveedor.equals(accion)) {
						String imeiFalla = garanProveedor.getImeiFalla();
						UnidadMovil unidad = unidadMovilDAO.get(imeiFalla);
						unidad.setConFalla(false);
						unidad.setFechaMod(new Date());
						unidad.setUsuario3(usuario);
						int updtUnidad = unidadMovilDAO.update(unidad);
						if (updtUnidad != 0) {
							garanProveedor.setEstado(false);
							garanProveedor.setFechaBaja(new Date());
							garanProveedor.setUsuario2(usuario);
							int updGarantia = garantiasProveedorDAO.update(garanProveedor);
							if (updGarantia != 0) {
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Ticket registrado!", null);
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Baja del Ticket!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al actualizar el Móvil!", null);
						}
					} else {
						String imeiFalla = garanProveedor.getImeiFalla();
						UnidadMovil unidad = unidadMovilDAO.get(imeiFalla);
						ComprasDetalleUnidad compraUnidad = compraDetalleUnidadDAO.get(imeiFalla);
						boolean actualizo = true;
						if (compraUnidad.getId() != 0) {
							compraUnidad.setConFalla(false);
							int updtCompraUnidad = compraDetalleUnidadDAO.update(compraUnidad);
							if (updtCompraUnidad == 0) {
								actualizo = false;
							}
						}
						if (actualizo) {
							unidad.setConFalla(false);
							unidad.setFechaMod(new Date());
							unidad.setUsuario3(usuario);
							int updtUnidad = unidadMovilDAO.update(unidad);
							if (updtUnidad != 0) {
								garanProveedor.setEstado(false);
								garanProveedor.setFechaBaja(new Date());
								garanProveedor.setUsuario2(usuario);
								int updGarantia = garantiasProveedorDAO.update(garanProveedor);
								if (updGarantia != 0) {
									//Registro en cuenta corriente
									CuentaCorriente cuenta = new CuentaCorriente();
									CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
									ccProveedor.setIdMovimiento(garanProveedor.getId());
									ccProveedor.setNombreTabla("GarantiasProveedor");
									int updtCuenta = cuenta.deleteCuentaCorriente(ccProveedor);
									if (updtCuenta != 0) {
										msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Ticket registrado!", null);
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Baja del Movimiento en Cuenta Corriente!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Baja del Ticket!", null);
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Unidad Móvil!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el movimiento correspondiente a la Unidad Móvil! "
									+ "Contáctese con su proveedor de servicio!", null);
						}
					}					
				}
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = null;
			try {
				String imei = garanProveedor.getImeiFalla();
				UnidadMovil unidad = unidadMovilDAO.get(imei);
				unidad.setEnGarantiaProveedor(false);
				int updtMovil = unidadMovilDAO.update(unidad);
				if (updtMovil != 0) {
					garanProveedor.setEstado(false);
					garanProveedor.setFechaBaja(new Date());
					garanProveedor.setUsuario2(usuario);
					int updtGarantia = garantiasProveedorDAO.update(garanProveedor);
					if (updtGarantia != 0) {
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja del Ticket de Garantía!", null);
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al dar de baja el Ticket de Garantía! Inténtelo nuevamente!", null);
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al dar de baja el Ticket de Garantía! Inténtelo nuevamente!", null);
				}
			} catch (Exception e) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al iniciar el Ticket de Garantía. "
						+ "Error original: " + e.getMessage(), null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void verMovimientoCliente(GarantiasCliente garanCliente) {
		garantiasCliente = new GarantiasCliente();
		garantiasCliente = garanCliente;
		opcion2 = false;
		opcion3 = false;
		if (garanCliente.getFinalizado()) {
			if (garanCliente.getResolucion().equals("Cambio de Equipo")) {
				opcion2 = true;
			}
			if (garanCliente.getResolucion().equals("No posee arreglo")) {
				opcion3 = true;
			}
		}		
	}
	
	public void verMovimientoProveedor(GarantiasProveedore garanProveedor) {
		garantiasProveedor = new GarantiasProveedore();
		garantiasProveedor = garanProveedor;
		opcion2 = false;
		opcion3 = false;
		if (garanProveedor.getConcepto().equals("Entrega por garantía")) {
			if (garanProveedor.getFinalizado()) {
				if (garanProveedor.getResolucion().equals("Cambio de Equipo")) {
					opcion2 = true;
				}
				if (garanProveedor.getResolucion().equals("No posee arreglo")) {
					opcion3 = true;
				}
			}			
		}
	}
	
	public void buscarCliente() {
		listaGarantiasClientes = new ArrayList<GarantiasCliente>();
		if (fechaDesde == null && fechaHasta == null && idCliente == 0 && idProducto == 0) {
			listaGarantiasClientes = garantiasClienteDAO.getLista();
		}
		if (fechaDesde != null && fechaHasta != null && idCliente == 0 && idProducto == 0) {
			listaGarantiasClientes = garantiasClienteDAO.getLista(fechaDesde, fechaHasta);
		}
		if (fechaDesde == null && fechaHasta == null && idCliente != 0 && idProducto != 0) {
			Cliente cli = clienteDAO.get(idCliente);
			Producto prod = productoDAO.get(idProducto);
			listaGarantiasClientes = garantiasClienteDAO.getLista(cli, prod);
		}
		if (fechaDesde == null && fechaHasta == null && idCliente != 0 && idProducto == 0) {
			Cliente cli = clienteDAO.get(idCliente);
			listaGarantiasClientes = garantiasClienteDAO.getLista(cli);
		}
		if (fechaDesde == null && fechaHasta == null && idCliente == 0 && idProducto != 0) {
			Producto prod = productoDAO.get(idProducto);
			listaGarantiasClientes = garantiasClienteDAO.getLista(prod);
		}
		if (fechaDesde != null && fechaHasta != null && idCliente != 0 && idProducto == 0) {
			Cliente cli = clienteDAO.get(idCliente);
			listaGarantiasClientes = garantiasClienteDAO.getLista(fechaDesde, fechaHasta, cli);
		}
		if (fechaDesde != null && fechaHasta != null && idCliente == 0 && idProducto != 0) {
			Producto prod = productoDAO.get(idProducto);
			listaGarantiasClientes = garantiasClienteDAO.getLista(fechaDesde, fechaHasta, prod);
		}
		if (fechaDesde != null && fechaHasta != null && idCliente != 0 && idProducto != 0) {
			Cliente cli = clienteDAO.get(idCliente);
			Producto prod = productoDAO.get(idProducto);
			listaGarantiasClientes = garantiasClienteDAO.getLista(fechaDesde, fechaHasta, cli, prod);
		}
	}
	
	public String guardarCliente() {
		String retorno = "";	
		FacesMessage msg = null;
		try {
			if (garantiasCliente.getFechaIngreso() != null && unidadMovil.getId() != 0 && idCliente != 0 
					&& !garantiasCliente.getFalla().isEmpty() && garantiasCliente.getFalla() != null && idTecnico != 0) {
				boolean existeV = false;
				boolean existeC = false;
				boolean existeVC = false;
				boolean mismoCliente = false;
				String nroImei = unidadMovil.getNroImei();
				Venta venta = new Venta();
				Consignacion consignacion = new Consignacion();
				VentasCon ventasCon = new VentasCon();
				VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(nroImei);
				if (ventaUnidad.getId() != 0) {
					existeV = true;
					VentasDetalle ventaDetalle = ventaUnidad.getVentasDetalle();
					venta = ventaDetalle.getVenta();
					int idCli = venta.getCliente().getId();
					if (idCli == idCliente) {
						mismoCliente = true;
					}
				}
				ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(nroImei);
				if (consignacionUnidad.getId() != 0) {
					existeC = true;
					ConsignacionsDetalle consignacionDetalle = consignacionUnidad.getConsignacionsDetalle();
					consignacion = consignacionDetalle.getConsignacion();
					int idCli = consignacion.getCliente().getId();
					if (idCli == idCliente) {
						mismoCliente = true;
					}
				}
				VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(nroImei);
				if (ventaConsUnidad.getId() != 0) {
					existeVC = true;
					VentasConsDetalle ventaConsDetalle = ventaConsUnidad.getVentasConsDetalle();
					ventasCon = ventaConsDetalle.getVentasCon();
					int idCli = ventasCon.getCliente().getId();
					if (idCli == idCliente) {
						mismoCliente = true;
					}
				}
				cliente = clienteDAO.get(idCliente);
				tecnico = tecnicoDAO.get(idTecnico);
				producto = unidadMovil.getProducto(); 
				if (existeV || existeC || existeVC) {
					boolean paso = true;
					if (existeV && !mismoCliente) {
						paso = false;
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Cliente no corresponde a la Venta "
								+ "asociada a ese Nro de Imei", null);
					}
					if (existeC && !mismoCliente) {
						paso = false;
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Cliente no corresponde a la Consignación "
								+ "asociada a ese Nro de Imei", null);
					}
					if (existeVC && !mismoCliente) {
						paso = false;
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Cliente no corresponde a la Venta de Consignación "
								+ "asociada a ese Nro de Imei", null);
					}
					if (paso) {
						unidadMovil.setEnGarantiaCliente(true);
						int updtMovil = unidadMovilDAO.update(unidadMovil);
						if (updtMovil != 0) {
							garantiasCliente.setCliente(cliente);
							garantiasCliente.setEstado(true);
							garantiasCliente.setFechaAlta(new Date());
							garantiasCliente.setFinalizado(false);
							garantiasCliente.setImeiFalla(nroImei);
							garantiasCliente.setProducto1(producto);
							garantiasCliente.setTecnico(tecnico);
							garantiasCliente.setTelefonoFalla(producto.getNombre());
							garantiasCliente.setUsuario1(usuario);
							int idGarantia = garantiasClienteDAO.insertar(garantiasCliente);
							if (idGarantia != 0) {
								idCliente = 0;
								idProducto = 0;
								idTecnico = 0;
								listaGarantiasClientes = new ArrayList<GarantiasCliente>();
								filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
								listaGarantiasClientes = garantiasClienteDAO.getLista();
								filteredGarantiasClientes = listaGarantiasClientes;
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Garantía registrada!", null);
								retorno = "garantiasclientes";
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket de garantía.", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil.", null);
						}						
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existen Ventas o Consignaciones o Ventas de Cosignaciones "
							+ "asociadas a ese Nro de Imei", null);
				}				
			} else {
				String mensaje = "";
				if (garantiasCliente.getFechaIngreso() == null) {
					mensaje = mensaje + "Fecha de Ingreso, ";
				}
				if (unidadMovil.getId() == 0) {
					mensaje = mensaje + "Unidad Móvil, ";
				}
				if (idCliente == 0) {
					mensaje = mensaje + "Cliente, ";
				}
				if (garantiasCliente.getFalla().isEmpty() || garantiasCliente.getFalla() == null) {
					mensaje = mensaje + "Falla, ";
				}
				if (idTecnico == 0) {
					mensaje = mensaje + "Técnico.";
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Los siguientes parámetros no pueden estar vacíos. "
						+ "Parámetros: " + mensaje, null);
			}
		} catch(Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al iniciar el Ticket de Garantía. "
					+ "Error original: " + e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String editarCliente() {
		String retorno = "";	
		FacesMessage msg = null;
		try {
			if (!garantiasCliente.getFalla().isEmpty() && garantiasCliente.getFalla() != null && idTecnico != 0) {				
				tecnico = tecnicoDAO.get(idTecnico);
				garantiasCliente.setFechaMod(new Date());
				garantiasCliente.setUsuario3(usuario);
				garantiasCliente.setTecnico(tecnico);
				int uptGarantia = garantiasClienteDAO.update(garantiasCliente);
				if (uptGarantia != 0) {
					idCliente = 0;
					idProducto = 0;
					idTecnico = 0;
					listaGarantiasClientes = new ArrayList<GarantiasCliente>();
					filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
					listaGarantiasClientes = garantiasClienteDAO.getLista();
					filteredGarantiasClientes = listaGarantiasClientes;
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Garantía registrada!", null);
					retorno = "garantiasclientes";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket de garantía.", null);
				}				
			} else {
				String mensaje = "";				
				if (garantiasCliente.getFalla().isEmpty() || garantiasCliente.getFalla() == null) {
					mensaje = mensaje + "Falla, ";
				}
				if (idTecnico == 0) {
					mensaje = mensaje + "Técnico.";
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Los siguientes parámetros no pueden estar vacíos. "
						+ "Parámetros: " + mensaje, null);
			}
		} catch(Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al iniciar el Ticket de Garantía. "
					+ "Error original: " + e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarProveedor() {
		String retorno = "";	
		FacesMessage msg = null;
		try {
			if (garantiasProveedor.getFechaIngreso() != null && unidadMovil.getId() != 0 && idProveedor != 0 
					&& !garantiasProveedor.getFalla().isEmpty() && garantiasProveedor.getFalla() != null) {
				boolean existeC = false;
				boolean mismoProveedor = false;
				String concep;
				String nroImei = unidadMovil.getNroImei();
				if (concepto) {
					concep = "Entrega por garantía";					
					Compra compra = new Compra();
					ComprasDetalleUnidad compraUnidad = compraDetalleUnidadDAO.get(nroImei);
					if (compraUnidad.getId() != 0) {
						existeC = true;
						ComprasDetalle compraDetalle = compraUnidad.getComprasDetalle();
						compra = compraDetalle.getCompra();
						int idProv = compra.getProveedore().getId();
						if (idProv == idProveedor) {
							mismoProveedor = true;
						}
					}
				} else {
					concep = "Entrega por reparación";
					existeC = true;
					mismoProveedor = true;
				}
				proveedor = proveedorDAO.get(idProveedor);
				producto = unidadMovil.getProducto(); 
				if (existeC) {
					boolean paso = true;
					if (existeC && !mismoProveedor) {
						paso = false;
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Proveedor no corresponde a la Compra "
								+ "asociada a ese Nro de Imei", null);
					}
					if (paso) {
						unidadMovil.setEnGarantiaProveedor(true);
						int updtMovil = unidadMovilDAO.update(unidadMovil);
						if (updtMovil != 0) {
							garantiasProveedor.setConcepto(concep);
							garantiasProveedor.setEstado(true);
							garantiasProveedor.setFechaAlta(new Date());
							garantiasProveedor.setFinalizado(false);
							garantiasProveedor.setImeiFalla(nroImei);
							garantiasProveedor.setProducto1(producto);
							garantiasProveedor.setProveedore(proveedor);
							garantiasProveedor.setTelefonoFalla(producto.getNombre());
							garantiasProveedor.setUsuario1(usuario);
							int idGarantia = garantiasProveedorDAO.insertar(garantiasProveedor);
							if (idGarantia != 0) {
								idProveedor = 0;
								idProducto = 0;
								listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
								filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
								listaGarantiasProveedores = garantiasProveedorDAO.getLista();
								filteredGarantiasProveedores = listaGarantiasProveedores;
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Garantía registrada!", null);
								retorno = "garantiasproveedores";
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket de garantía.", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil.", null);
						}						
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existen Ventas o Consignaciones o Ventas de Cosignaciones "
							+ "asociadas a ese Nro de Imei", null);
				}				
			} else {
				String mensaje = "";
				if (garantiasProveedor.getFechaIngreso() == null) {
					mensaje = mensaje + "Fecha de Ingreso, ";
				}
				if (unidadMovil.getId() == 0) {
					mensaje = mensaje + "Unidad Móvil, ";
				}
				if (idProveedor == 0) {
					mensaje = mensaje + "Proveedor, ";
				}
				if (garantiasProveedor.getFalla().isEmpty() || garantiasProveedor.getFalla() == null) {
					mensaje = mensaje + "Falla, ";
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Los siguientes parámetros no pueden estar vacíos. "
						+ "Parámetros: " + mensaje, null);
			}
		} catch(Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al iniciar el Ticket de Garantía. "
					+ "Error original: " + e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String confirmarCliente() {
		try {
			String retorno = "";
			FacesMessage msg = null;
			//Devolucion de mismo equipo
			if (opcion1) {
				String imei = garantiasCliente.getImeiFalla();
				String resol = "Mismo Equipo";
				String telefono = garantiasCliente.getTelefonoFalla();
				Producto prod = garantiasCliente.getProducto1();
				UnidadMovil unidad = unidadMovilDAO.get(imei);
				unidad.setEnGarantiaCliente(false);
				unidad.setFechaMod(new Date());
				unidad.setUsuario3(usuario);
				int updtUnidad = unidadMovilDAO.update(unidad);
				if (updtUnidad != 0) {
					garantiasCliente.setFechaMod(new Date());
					garantiasCliente.setFinalizado(true);
					garantiasCliente.setImeiReintegro(imei);
					garantiasCliente.setProducto2(prod);
					garantiasCliente.setResolucion(resol);
					garantiasCliente.setTelefonoReintegro(telefono);
					garantiasCliente.setUsuario3(usuario);
					int updGarantia = garantiasClienteDAO.update(garantiasCliente);
					if (updGarantia != 0) {
						listaGarantiasClientes = new ArrayList<GarantiasCliente>();
						filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
						listaGarantiasClientes = garantiasClienteDAO.getLista();
						filteredGarantiasClientes = listaGarantiasClientes;
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
						retorno = "garantiasclientes";
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
				}				
			}
			//Cambio de un equipo por otro
			if (opcion2) {
				boolean NoExisteV = true;
				boolean NoExisteC = true;
				boolean NoExisteVC = true;
				producto = new Producto();				
				String nroImei = unidadMovil.getNroImei();
				producto = unidadMovil.getProducto();
				VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(nroImei);
				if (ventaUnidad.getId() != 0) {
					NoExisteV = false;
				}
				ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(nroImei);
				if (consignacionUnidad.getId() != 0) {
					NoExisteC = false;
				}
				VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(nroImei);
				if (ventaConsUnidad.getId() != 0) {
					NoExisteVC = false;
				}
				if (NoExisteV && NoExisteC && NoExisteVC) {
					String resol = "Cambio de Equipo";
					String telefono = unidadMovil.getProducto().getNombre();
					Producto prod = unidadMovil.getProducto();
					//Cambio de un equipo por otro en la venta, consignacion o ventaconsignacion
					boolean existeV = false;
					boolean existeC = false;
					boolean existeVC = false;
					String imeiFalla = garantiasCliente.getImeiFalla();
					VentasDetalleUnidad ventaUnidadF = ventaDetalleUnidadDAO.get(imeiFalla);
					if (ventaUnidadF.getId() != 0) {
						existeV = true;
					}
					ConsignacionsDetalleUnidad consignacionUnidadF = consignacionDetalleUnidadDAO.get(imeiFalla);
					if (consignacionUnidadF.getId() != 0) {
						existeC = true;
					}
					VentasConsDetalleUnidad ventaConsUnidadF = ventaConsignacionDetalleUnidadDAO.get(imeiFalla);
					if (ventaConsUnidadF.getId() != 0) {
						existeVC = true;
					}
					if (existeV) {
						if (prod.getId() == garantiasCliente.getProducto1().getId()) {
							Cuota cuota = cuotaDAO.get(imeiFalla);
							if (cuota.getId() != 0) {
								cuota.setNroImei(nroImei);
								cuotaDAO.update(cuota);
							}
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);
							if (cuotaVenta.getId() != 0) {
								cuotaVenta.setNroImei(nroImei);
								cuotaVentaDAO.update(cuotaVenta);
							}
							ventaUnidadF.setNroImei(nroImei);
							ventaUnidadF.setEliminado(false);
							ventaUnidadF.setPrecioCompra(unidadMovil.getPrecioCompra());
							ventaUnidadF.setUnidadMovil(unidadMovil);
							int updtVenta = ventaDetalleUnidadDAO.update(ventaUnidadF);
							if (updtVenta != 0) {
								unidadMovil.setEnStock(false);
								unidadMovil.setEnVenta(true);
								unidadMovil.setEstado(true);
								unidadMovil.setFechaMod(new Date());
								unidadMovil.setUsuario3(usuario);
								UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
								unidadFalla.setDevuelto(true);
								unidadFalla.setEnStock(true);
								unidadFalla.setEnVenta(false);
								unidadFalla.setEstado(true);
								unidadFalla.setEliminado(false);
								unidadFalla.setEnGarantiaCliente(false);
								unidadFalla.setFechaMod(new Date());
								unidadFalla.setUsuario3(usuario);								
								int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
								int updtMovilVta = unidadMovilDAO.update(unidadMovil);
								if (updtMovilVta != 0 && updtMvilFalla != 0) {
									garantiasCliente.setFechaMod(new Date());
									garantiasCliente.setFinalizado(true);
									garantiasCliente.setImeiReintegro(nroImei);
									garantiasCliente.setProducto2(prod);
									garantiasCliente.setResolucion(resol);
									garantiasCliente.setTelefonoReintegro(telefono);
									garantiasCliente.setUsuario3(usuario);
									int updGarantia = garantiasClienteDAO.update(garantiasCliente);
									if (updGarantia != 0) {
										listaGarantiasClientes = new ArrayList<GarantiasCliente>();
										filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
										listaGarantiasClientes = garantiasClienteDAO.getLista();
										filteredGarantiasClientes = listaGarantiasClientes;
										msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
										retorno = "garantiasclientes";
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
								}								
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Venta correspondiente!", null);
							}
						} else {
							Cuota cuota = cuotaDAO.get(imeiFalla);							
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);							
							if (cuota.getId() == 0 && cuotaVenta.getId() == 0) {	
								VentasDetalle ventDetalleF = ventaDetalleDAO.get(ventaUnidadF.getVentasDetalle().getId());							
								Venta venta = ventaDAO.get(ventDetalleF.getVenta().getId());
								Cliente cli = clienteDAO.get(venta.getCliente().getId());
								Producto prodCambio = productoDAO.get(unidadMovil.getProducto().getId());
								Producto prodFalla = productoDAO.get(ventDetalleF.getProducto().getId());
								float montoDescuento = 0;
								float montoSumado = 0;
								boolean existeDetalle = false;
								List<VentasDetalle> detallesVenta = ventaDetalleDAO.getLista(venta);
								VentasDetalle ventDetalleN = new VentasDetalle();
								for (VentasDetalle ventasD : detallesVenta) {
									if (ventasD.getProducto().getId() == prodCambio.getId()) {
										existeDetalle = true;
										ventDetalleN = ventasD;
									}
								}							
								if (existeDetalle) {
									//Alta Nuevo Detalle Unidad
									int cantNueva = ventDetalleN.getCantidad();
									cantNueva = cantNueva + 1;
									montoSumado = ventDetalleN.getPrecioVenta();
									float subtotal = cantNueva * montoSumado;
									ventDetalleN.setCantidad(cantNueva);
									ventDetalleN.setSubtotal(subtotal);
									VentasDetalleUnidad ventaUnidadN = new VentasDetalleUnidad();
									ventaUnidadN.setEliminado(false);
									ventaUnidadN.setNroImei(nroImei);
									ventaUnidadN.setPrecioCompra(unidadMovil.getPrecioCompra());
									ventaUnidadN.setPrecioVenta(montoSumado);									
									ventaUnidadN.setUnidadMovil(unidadMovil);
									ventaUnidadN.setVentasDetalle(ventDetalleN);
									ventaDetalleUnidadDAO.insertar(ventaUnidadN);
									ventaDetalleDAO.update(ventDetalleN);								
									//Baja Detalle
									if (ventDetalleF.getCantidad() == 1) {
										ventDetalleF.setEliminado(true);									
									} else {
										int cantF = ventDetalleF.getCantidad();
										cantF = cantF - 1;
										ventDetalleF.setCantidad(cantF);
									}								
									ventaDetalleDAO.update(ventDetalleF);
									//Baja Venta Unidad
									montoDescuento = ventaUnidadF.getPrecioVenta();
									ventaUnidadF.setEliminado(true);
									ventaDetalleUnidadDAO.update(ventaUnidadF);
									//Actualizacion Monto Venta
									float montoActual = venta.getMonto();
									montoActual = montoActual - montoDescuento;
									montoActual = montoActual + montoSumado;
									venta.setMonto(montoActual);
									ventaDAO.update(venta);	
									//Actualizacion Cuenta Corriente
									int idVenta = venta.getId();
									//Baja de venta en Cuenta Corriente, para realizar el alta luego
									CuentaCorriente cuenta = new CuentaCorriente();
									CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
									if(venta.getTipo().equals("c.c.")){
										ccCliente.setIdMovimiento(venta.getId());
										ccCliente.setNombreTabla("Venta");
										cuenta.deleteCuentaCorriente(ccCliente);
									}
									if(venta.getTipo().equals("ctdo.")){									
										cuentaCorrienteDAO.deletePorMovimientoCliente(idVenta, "Venta", cli);
										MovimientoCaja movCaja = new MovimientoCaja();
										Caja mov = new Caja();
										mov.setIdMovimiento(idVenta);
										mov.setNombreTabla("Venta");
										movCaja.deleteCaja(mov);
									}								
									//Insercion de CC
									ccCliente = new CuentasCorrientesCliente();	
									ccCliente.setCliente(cli);
									ccCliente.setDebe(montoActual);
									ccCliente.setDetalle("Venta nro: " + idVenta);				
									ccCliente.setFecha(venta.getFecha());
									ccCliente.setIdMovimiento(idVenta);
									ccCliente.setMonto(montoActual);
									ccCliente.setNombreTabla("Venta");
									ccCliente.setUsuario(usuario);
									cuenta.insertarCC(ccCliente);
									//Si es una venta de contado, inserto el pago en CC y en Caja
									if (venta.getTipo().equals("ctdo.")) {
										ccCliente = new CuentasCorrientesCliente();								
										ccCliente.setCliente(cli);
										ccCliente.setDetalle("Pago de contado - Venta nro: " + idVenta);
										ccCliente.setFecha(venta.getFecha());
										ccCliente.setHaber(montoActual);
										ccCliente.setIdMovimiento(idVenta);
										ccCliente.setMonto(montoActual);
										ccCliente.setNombreTabla("Venta");
										ccCliente.setUsuario(usuario);
										cuenta.insertarCC(ccCliente);
										MovimientoCaja movimientoCaja = new MovimientoCaja();
										Caja caja = new Caja();
										caja.setConcepto("Cobro de Venta nro: " + idVenta);
										caja.setFecha(venta.getFecha());
										caja.setIdMovimiento(idVenta);
										caja.setMonto(montoActual);
										caja.setNombreTabla("Venta");
										caja.setUsuario(usuario);
										movimientoCaja.insertarCaja(caja);
									}
									//Alta en Venta y Baja en Stock de Unidad
									unidadMovil.setEnStock(false);
									unidadMovil.setEnVenta(true);
									unidadMovil.setEstado(true);
									unidadMovil.setFechaMod(new Date());
									unidadMovil.setUsuario3(usuario);
									//Baja en Venta y Alta en Stock de Unidad
									UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
									unidadFalla.setDevuelto(true);
									unidadFalla.setEnStock(true);
									unidadFalla.setEnVenta(false);
									unidadFalla.setEstado(true);
									unidadFalla.setEliminado(false);
									unidadFalla.setEnGarantiaCliente(false);
									unidadFalla.setFechaMod(new Date());
									unidadFalla.setUsuario3(usuario);								
									int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
									int updtMovilVta = unidadMovilDAO.update(unidadMovil);				
									//Actualizacion de Stock en productos
									int stockF = prodFalla.getStock();
									int stockC = prodCambio.getStock();
									stockF = stockF + 1;
									stockC = stockC - 1;
									prodFalla.setStock(stockF);
									prodCambio.setStock(stockC);
									productoDAO.update(prodFalla);
									productoDAO.update(prodCambio);
									if (updtMovilVta != 0 && updtMvilFalla != 0) {
										//Actualizacion garantia
										garantiasCliente.setFechaMod(new Date());
										garantiasCliente.setFinalizado(true);
										garantiasCliente.setImeiReintegro(nroImei);
										garantiasCliente.setProducto2(prodCambio);
										garantiasCliente.setResolucion(resol);
										garantiasCliente.setTelefonoReintegro(prodCambio.getNombre());
										garantiasCliente.setUsuario3(usuario);
										int updGarantia = garantiasClienteDAO.update(garantiasCliente);
										if (updGarantia != 0) {
											listaGarantiasClientes = new ArrayList<GarantiasCliente>();
											filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
											listaGarantiasClientes = garantiasClienteDAO.getLista();
											filteredGarantiasClientes = listaGarantiasClientes;
											msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
											retorno = "garantiasclientes";
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
										}
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
									}								
								} else {
									ListaPrecioProducto precioProducto = new ListaPrecioProducto();
									ListaPrecio listaPrecio = listaPrecioDAO.get(cli.getListaPrecio().getId());
									if (listaPrecio.getId() != 0) {
										precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
										if (precioProducto.getId() != 0 && precioProducto.getPrecioVenta() != 0) {
											ventDetalleN.setAccesorio(false);
											ventDetalleN.setCantidad(1);
											ventDetalleN.setEliminado(false);
											ventDetalleN.setPrecioVenta(precioProducto.getPrecioVenta());
											ventDetalleN.setProducto(prodCambio);
											ventDetalleN.setSubtotal(precioProducto.getPrecioVenta());
											ventDetalleN.setVenta(venta);
											int idVentaDetalleN = ventaDetalleDAO.insertar(ventDetalleN);
											ventDetalleN.setId(idVentaDetalleN);
											VentasDetalleUnidad ventaUnidadN = new VentasDetalleUnidad();
											ventaUnidadN.setEliminado(false);
											ventaUnidadN.setNroImei(nroImei);
											ventaUnidadN.setPrecioCompra(unidadMovil.getPrecioCompra());
											ventaUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
											montoSumado = precioProducto.getPrecioVenta();
											ventaUnidadN.setUnidadMovil(unidadMovil);
											ventaUnidadN.setVentasDetalle(ventDetalleN);
											ventaDetalleUnidadDAO.insertar(ventaUnidadN);
											//Baja Detalle
											if (ventDetalleF.getCantidad() == 1) {
												ventDetalleF.setEliminado(true);									
											} else {
												int cantF = ventDetalleF.getCantidad();
												cantF = cantF - 1;
												ventDetalleF.setCantidad(cantF);
											}								
											ventaDetalleDAO.update(ventDetalleF);
											//Baja Venta Unidad
											montoDescuento = ventaUnidadF.getPrecioVenta();
											ventaUnidadF.setEliminado(true);
											ventaDetalleUnidadDAO.update(ventaUnidadF);
											//Actualizacion Monto Venta
											float montoActual = venta.getMonto();
											montoActual = montoActual - montoDescuento;
											montoActual = montoActual + montoSumado;
											venta.setMonto(montoActual);
											ventaDAO.update(venta);
											//Actualizacion Cuenta Corriente
											int idVenta = venta.getId();
											//Baja de venta en Cuenta Corriente, para realizar el alta luego
											CuentaCorriente cuenta = new CuentaCorriente();
											CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
											if(venta.getTipo().equals("c.c.")){
												ccCliente.setIdMovimiento(venta.getId());
												ccCliente.setNombreTabla("Venta");
												cuenta.deleteCuentaCorriente(ccCliente);
											}
											if(venta.getTipo().equals("ctdo.")){									
												cuentaCorrienteDAO.deletePorMovimientoCliente(idVenta, "Venta", cli);
												MovimientoCaja movCaja = new MovimientoCaja();
												Caja mov = new Caja();
												mov.setIdMovimiento(idVenta);
												mov.setNombreTabla("Venta");
												movCaja.deleteCaja(mov);
											}								
											//Insercion de CC
											ccCliente = new CuentasCorrientesCliente();	
											ccCliente.setCliente(cli);
											ccCliente.setDebe(montoActual);
											ccCliente.setDetalle("Venta nro: " + idVenta);				
											ccCliente.setFecha(venta.getFecha());
											ccCliente.setIdMovimiento(idVenta);
											ccCliente.setMonto(montoActual);
											ccCliente.setNombreTabla("Venta");
											ccCliente.setUsuario(usuario);
											cuenta.insertarCC(ccCliente);
											//Si es una venta de contado, inserto el pago en CC y en Caja
											if (venta.getTipo().equals("ctdo.")) {
												ccCliente = new CuentasCorrientesCliente();								
												ccCliente.setCliente(cli);
												ccCliente.setDetalle("Pago de contado - Venta nro: " + idVenta);
												ccCliente.setFecha(venta.getFecha());
												ccCliente.setHaber(montoActual);
												ccCliente.setIdMovimiento(idVenta);
												ccCliente.setMonto(montoActual);
												ccCliente.setNombreTabla("Venta");
												ccCliente.setUsuario(usuario);
												cuenta.insertarCC(ccCliente);
												MovimientoCaja movimientoCaja = new MovimientoCaja();
												Caja caja = new Caja();
												caja.setConcepto("Cobro de Venta nro: " + idVenta);
												caja.setFecha(venta.getFecha());
												caja.setIdMovimiento(idVenta);
												caja.setMonto(montoActual);
												caja.setNombreTabla("Venta");
												caja.setUsuario(usuario);
												movimientoCaja.insertarCaja(caja);
											}
											//Alta en Venta y Baja en Stock de Unidad
											unidadMovil.setEnStock(false);
											unidadMovil.setEnVenta(true);
											unidadMovil.setEstado(true);
											unidadMovil.setFechaMod(new Date());
											unidadMovil.setUsuario3(usuario);
											//Baja en Venta y Alta en Stock de Unidad
											UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
											unidadFalla.setDevuelto(true);
											unidadFalla.setEnStock(true);
											unidadFalla.setEnVenta(false);
											unidadFalla.setEstado(true);
											unidadFalla.setEliminado(false);
											unidadFalla.setEnGarantiaCliente(false);
											unidadFalla.setFechaMod(new Date());
											unidadFalla.setUsuario3(usuario);								
											int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
											int updtMovilVta = unidadMovilDAO.update(unidadMovil);			
											//Actualizacion de Stock en productos
											int stockF = prodFalla.getStock();
											int stockC = prodCambio.getStock();
											stockF = stockF + 1;
											stockC = stockC - 1;
											prodFalla.setStock(stockF);
											prodCambio.setStock(stockC);
											productoDAO.update(prodFalla);
											productoDAO.update(prodCambio);
											if (updtMovilVta != 0 && updtMvilFalla != 0) {
												//Actualizacion garantia
												garantiasCliente.setFechaMod(new Date());
												garantiasCliente.setFinalizado(true);
												garantiasCliente.setImeiReintegro(nroImei);
												garantiasCliente.setProducto2(prodCambio);
												garantiasCliente.setResolucion(resol);
												garantiasCliente.setTelefonoReintegro(prodCambio.getNombre());
												garantiasCliente.setUsuario3(usuario);
												int updGarantia = garantiasClienteDAO.update(garantiasCliente);
												if (updGarantia != 0) {
													listaGarantiasClientes = new ArrayList<GarantiasCliente>();
													filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
													listaGarantiasClientes = garantiasClienteDAO.getLista();
													filteredGarantiasClientes = listaGarantiasClientes;
													msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
													retorno = "garantiasclientes";
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
												}
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
											}
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee Precio "
													+ "para ese Producto en la Lista de Precio", null);
										}									
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee "
												+ "Lista de Precio", null);
									}
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el Móvil con Falla esta registrado en Cuotas, "
										+ "realice las bajas de las mismas primero!", null);
							}													
						}
					}
					if (existeC) {
						if (prod.getId() == garantiasCliente.getProducto1().getId()) {
							Cuota cuota = cuotaDAO.get(imeiFalla);
							if (cuota.getId() != 0) {
								cuota.setNroImei(nroImei);
								cuotaDAO.update(cuota);
							}
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);
							if (cuotaVenta.getId() != 0) {
								cuotaVenta.setNroImei(nroImei);
								cuotaVentaDAO.update(cuotaVenta);
							}
							consignacionUnidadF.setNroImei(nroImei);
							consignacionUnidadF.setEliminado(false);
							consignacionUnidadF.setPrecioCompra(unidadMovil.getPrecioCompra());
							consignacionUnidadF.setUnidadMovil(unidadMovil);
							int updtConsignacion = consignacionDetalleUnidadDAO.update(consignacionUnidadF);
							if (updtConsignacion != 0) {
								unidadMovil.setEnStock(true);
								unidadMovil.setEnConsignacion(true);
								unidadMovil.setEstado(true);
								unidadMovil.setFechaMod(new Date());
								unidadMovil.setUsuario3(usuario);
								UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
								unidadFalla.setDevuelto(true);
								unidadFalla.setEnStock(true);
								unidadFalla.setEnVenta(false);
								unidadFalla.setEnConsignacion(false);
								unidadFalla.setEstado(true);
								unidadFalla.setEliminado(false);
								unidadFalla.setEnGarantiaCliente(false);
								unidadFalla.setFechaMod(new Date());
								unidadFalla.setUsuario3(usuario);								
								int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
								int updtMovilVta = unidadMovilDAO.update(unidadMovil);
								if (updtMvilFalla != 0 && updtMovilVta != 0) {
									garantiasCliente.setFechaMod(new Date());
									garantiasCliente.setFinalizado(true);
									garantiasCliente.setImeiReintegro(nroImei);
									garantiasCliente.setProducto2(prod);
									garantiasCliente.setResolucion(resol);
									garantiasCliente.setTelefonoReintegro(telefono);
									garantiasCliente.setUsuario3(usuario);
									int updGarantia = garantiasClienteDAO.update(garantiasCliente);
									if (updGarantia != 0) {
										listaGarantiasClientes = new ArrayList<GarantiasCliente>();
										filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
										listaGarantiasClientes = garantiasClienteDAO.getLista();
										filteredGarantiasClientes = listaGarantiasClientes;
										msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
										retorno = "garantiasclientes";
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
								}								
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Consignación correspondiente!", null);
							}
						} else {
							Cuota cuota = cuotaDAO.get(imeiFalla);							
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);							
							if (cuota.getId() == 0 && cuotaVenta.getId() == 0) {								
								ConsignacionsDetalle consignacionDetalleF = consignacionDetalleDAO.get(consignacionUnidadF.getConsignacionsDetalle().getId());
								Consignacion consignacion = consignacionDAO.get(consignacionDetalleF.getConsignacion().getId());
								Cliente cli = clienteDAO.get(consignacion.getCliente().getId());
								Producto prodCambio = productoDAO.get(unidadMovil.getProducto().getId());
								Producto prodFalla = productoDAO.get(consignacionDetalleF.getProducto().getId());
								boolean existeDetalle = false;
								List<ConsignacionsDetalle> detallesConsig = consignacionDetalleDAO.getLista(consignacion);
								ConsignacionsDetalle consignacionDetalleN = new ConsignacionsDetalle();
								for (ConsignacionsDetalle consignacionsD : detallesConsig) {
									if (consignacionsD.getProducto().getId() == prodCambio.getId()) {
										existeDetalle = true;
										consignacionDetalleN = consignacionsD;
									}
								}
								ListaPrecioProducto precioProducto = new ListaPrecioProducto();
								ListaPrecio listaPrecio = listaPrecioDAO.get(cli.getListaPrecio().getId());
								if (listaPrecio.getId() != 0) {
									precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
									if (precioProducto.getId() != 0 && precioProducto.getPrecioVenta() != 0) {
										if (existeDetalle) {
											//Alta Nuevo Detalle Unidad
											int cantNueva = consignacionDetalleN.getCantidad();
											cantNueva = cantNueva + 1;
											float subtotal = consignacionDetalleN.getSubtotal() + precioProducto.getPrecioVenta();
											consignacionDetalleN.setCantidad(cantNueva);
											consignacionDetalleN.setSubtotal(subtotal);
											ConsignacionsDetalleUnidad consignacionUnidadN = new ConsignacionsDetalleUnidad();
											consignacionUnidadN.setConsignacionsDetalle(consignacionDetalleN);
											consignacionUnidadN.setEliminado(false);
											consignacionUnidadN.setEnabled(true);
											consignacionUnidadN.setFechaAlta(consignacionUnidadF.getFechaAlta());
											consignacionUnidadN.setFechaVenta(consignacionUnidadF.getFechaVenta());
											consignacionUnidadN.setNroImei(nroImei);
											consignacionUnidadN.setPrecioCompra(unidadMovil.getPrecioCompra());
											consignacionUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
											consignacionUnidadN.setTipoVenta(consignacionUnidadF.getTipoVenta());
											consignacionUnidadN.setUnidadMovil(unidadMovil);
											consignacionUnidadN.setVendido(consignacionUnidadF.getVendido());
											consignacionDetalleUnidadDAO.insertar(consignacionUnidadN);
											consignacionDetalleDAO.update(consignacionDetalleN);																						
											//Baja Detalle
											if (consignacionDetalleF.getCantidad() == 1) {
												consignacionDetalleF.setEliminado(true);											
											} else {
												int cantF = consignacionDetalleF.getCantidad();
												cantF = cantF - 1;
												consignacionDetalleF.setCantidad(cantF);
											}								
											consignacionDetalleDAO.update(consignacionDetalleF);
											//Baja Venta Unidad										
											consignacionUnidadF.setEliminado(true);
											consignacionUnidadF.setEnabled(false);
											consignacionUnidadF.setFechaBaja(new Date());										
											consignacionDetalleUnidadDAO.update(consignacionUnidadF);
											//Alta en Consignacion 
											unidadMovil.setEnStock(true);
											unidadMovil.setEnConsignacion(true);
											unidadMovil.setEstado(true);
											unidadMovil.setFechaMod(new Date());
											unidadMovil.setUsuario3(usuario);
											//Baja en Consignacion 
											UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
											unidadFalla.setDevuelto(true);
											unidadFalla.setEnStock(true);
											unidadFalla.setEnVenta(false);
											unidadFalla.setEnConsignacion(false);
											unidadFalla.setEstado(true);
											unidadFalla.setEliminado(false);
											unidadFalla.setEnGarantiaCliente(false);
											unidadFalla.setFechaMod(new Date());
											unidadFalla.setUsuario3(usuario);
											int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
											int updtMovilVta = unidadMovilDAO.update(unidadMovil);									
											//Actualizacion de Consignacion en productos
											int enConsigF = prodFalla.getEnConsignacion();
											int enConsigC = prodCambio.getEnConsignacion();
											enConsigF = enConsigF - 1;
											enConsigC = enConsigC + 1;
											prodFalla.setEnConsignacion(enConsigF);
											prodCambio.setEnConsignacion(enConsigC);
											productoDAO.update(prodFalla);
											productoDAO.update(prodCambio);
											if (updtMovilVta != 0 && updtMvilFalla != 0) {
												//Actualizacion garantia
												garantiasCliente.setFechaMod(new Date());
												garantiasCliente.setFinalizado(true);
												garantiasCliente.setImeiReintegro(nroImei);
												garantiasCliente.setProducto2(prodCambio);
												garantiasCliente.setResolucion(resol);
												garantiasCliente.setTelefonoReintegro(prodCambio.getNombre());
												garantiasCliente.setUsuario3(usuario);
												int updGarantia = garantiasClienteDAO.update(garantiasCliente);
												if (updGarantia != 0) {
													listaGarantiasClientes = new ArrayList<GarantiasCliente>();
													filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
													listaGarantiasClientes = garantiasClienteDAO.getLista();
													filteredGarantiasClientes = listaGarantiasClientes;
													msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
													retorno = "garantiasclientes";
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
												}
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
											}									
										} else {
											consignacionDetalleN.setCantidad(1);
											consignacionDetalleN.setConsignacion(consignacion);
											consignacionDetalleN.setEliminado(false);
											consignacionDetalleN.setPrecioVenta(precioProducto.getPrecioVenta());
											consignacionDetalleN.setProducto(prodCambio);
											consignacionDetalleN.setSubtotal(precioProducto.getPrecioVenta());
											consignacionDetalleN.setTipoVenta(consignacionDetalleF.getTipoVenta());
											consignacionDetalleN.setVendido(consignacionDetalleF.getVendido());
											int idConsignacionDetalleN = consignacionDetalleDAO.insertar(consignacionDetalleN);
											consignacionDetalleN.setId(idConsignacionDetalleN);
											ConsignacionsDetalleUnidad consignacionUnidadN = new ConsignacionsDetalleUnidad();
											consignacionUnidadN.setConsignacionsDetalle(consignacionDetalleN);
											consignacionUnidadN.setEliminado(false);
											consignacionUnidadN.setEnabled(true);
											consignacionUnidadN.setFechaAlta(consignacionUnidadF.getFechaAlta());
											consignacionUnidadN.setFechaVenta(consignacionUnidadF.getFechaVenta());
											consignacionUnidadN.setNroImei(nroImei);
											consignacionUnidadN.setPrecioCompra(unidadMovil.getPrecioCompra());
											consignacionUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
											consignacionUnidadN.setTipoVenta(consignacionUnidadF.getTipoVenta());
											consignacionUnidadN.setUnidadMovil(unidadMovil);
											consignacionUnidadN.setVendido(consignacionUnidadF.getVendido());
											consignacionDetalleUnidadDAO.insertar(consignacionUnidadN);										
											//Baja Detalle
											if (consignacionDetalleF.getCantidad() == 1) {
												consignacionDetalleF.setEliminado(true);											
											} else {
												int cantF = consignacionDetalleF.getCantidad();
												cantF = cantF - 1;
												consignacionDetalleF.setCantidad(cantF);
											}
											consignacionDetalleDAO.update(consignacionDetalleF);										
											//Baja Venta Unidad										
											consignacionUnidadF.setEliminado(true);
											consignacionDetalleUnidadDAO.update(consignacionUnidadF);
											//Alta en Consignacion 
											unidadMovil.setEnStock(true);
											unidadMovil.setEnConsignacion(true);
											unidadMovil.setEstado(true);
											unidadMovil.setFechaMod(new Date());
											unidadMovil.setUsuario3(usuario);
											//Baja en Consignacion 
											UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
											unidadFalla.setDevuelto(true);
											unidadFalla.setEnStock(true);
											unidadFalla.setEnVenta(false);
											unidadFalla.setEnConsignacion(false);
											unidadFalla.setEstado(true);
											unidadFalla.setEliminado(false);
											unidadFalla.setEnGarantiaCliente(false);
											unidadFalla.setFechaMod(new Date());
											unidadFalla.setUsuario3(usuario);
											int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
											int updtMovilVta = unidadMovilDAO.update(unidadMovil);									
											//Actualizacion de Consignacion en productos
											int enConsigF = prodFalla.getEnConsignacion();
											int enConsigC = prodCambio.getEnConsignacion();
											enConsigF = enConsigF - 1;
											enConsigC = enConsigC + 1;
											prodFalla.setEnConsignacion(enConsigF);
											prodCambio.setEnConsignacion(enConsigC);
											productoDAO.update(prodFalla);
											productoDAO.update(prodCambio);									
											if (updtMovilVta != 0 && updtMvilFalla != 0) {
												//Actualizacion garantia
												garantiasCliente.setFechaMod(new Date());
												garantiasCliente.setFinalizado(true);
												garantiasCliente.setImeiReintegro(nroImei);
												garantiasCliente.setProducto2(prodCambio);
												garantiasCliente.setResolucion(resol);
												garantiasCliente.setTelefonoReintegro(prodCambio.getNombre());
												garantiasCliente.setUsuario3(usuario);
												int updGarantia = garantiasClienteDAO.update(garantiasCliente);
												if (updGarantia != 0) {
													listaGarantiasClientes = new ArrayList<GarantiasCliente>();
													filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
													listaGarantiasClientes = garantiasClienteDAO.getLista();
													filteredGarantiasClientes = listaGarantiasClientes;
													msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
													retorno = "garantiasclientes";
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
												}
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
											}
										}
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee Precio "
												+ "para ese producto en la Lista de Precio", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee "
											+ "Lista de Precio", null);
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el Móvil con Falla esta registrado en Cuotas, "
										+ "realice las bajas de las mismas primero!", null);
							}							
						}
					}
					if (existeVC) {
						if (prod.getId() == garantiasCliente.getProducto1().getId()) {
							Cuota cuota = cuotaDAO.get(imeiFalla);
							if (cuota.getId() != 0) {
								cuota.setNroImei(nroImei);
								cuotaDAO.update(cuota);
							}
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);
							if (cuotaVenta.getId() != 0) {
								cuotaVenta.setNroImei(nroImei);
								cuotaVentaDAO.update(cuotaVenta);
							}
							ventaConsUnidadF.setNroImei(nroImei);
							ventaConsUnidadF.setEliminado(false);
							ventaConsUnidadF.setPrecioCompra(unidadMovil.getPrecioCompra());
							int updtVentaCons = ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidadF);
							if (updtVentaCons != 0) {
								unidadMovil.setEnStock(false);
								unidadMovil.setEnConsignacion(true);
								unidadMovil.setEstado(true);
								unidadMovil.setEnVenta(true);
								unidadMovil.setFechaMod(new Date());
								unidadMovil.setUsuario3(usuario);
								int updtMovilVta = unidadMovilDAO.update(unidadMovil);
								if (updtMovilVta != 0) {
									garantiasCliente.setFechaMod(new Date());
									garantiasCliente.setFinalizado(true);
									garantiasCliente.setImeiReintegro(nroImei);
									garantiasCliente.setProducto2(prod);
									garantiasCliente.setResolucion(resol);
									garantiasCliente.setTelefonoReintegro(telefono);
									garantiasCliente.setUsuario3(usuario);
									int updGarantia = garantiasClienteDAO.update(garantiasCliente);
									if (updGarantia != 0) {
										listaGarantiasClientes = new ArrayList<GarantiasCliente>();
										filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
										listaGarantiasClientes = garantiasClienteDAO.getLista();
										filteredGarantiasClientes = listaGarantiasClientes;
										msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
										retorno = "garantiasclientes";
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Venta de Consignación correspondiente!", null);
							}
						} else {
							Cuota cuota = cuotaDAO.get(imeiFalla);							
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);							
							if (cuota.getId() == 0 && cuotaVenta.getId() == 0) {
								VentasConsDetalle ventConsDetalleF = ventaConsignacionDetalleDAO.get(ventaConsUnidadF.getVentasConsDetalle().getId());							
								VentasCon ventasCon = ventaConsignacionDAO.get(ventConsDetalleF.getVentasCon().getId());
								Cliente cli = clienteDAO.get(ventasCon.getCliente().getId());
								Producto prodCambio = productoDAO.get(unidadMovil.getProducto().getId());
								Producto prodFalla = productoDAO.get(ventConsDetalleF.getProducto().getId());
								float montoDescuento = 0;
								float montoSumado = 0;
								boolean existeDetalle = false;
								List<VentasConsDetalle> detallesVentaCons = ventaConsignacionDetalleDAO.getLista(ventasCon);
								VentasConsDetalle ventConsDetalleN = new VentasConsDetalle();
								for (VentasConsDetalle ventasConsD : detallesVentaCons) {
									if (ventasConsD.getProducto().getId() == prodCambio.getId()) {
										existeDetalle = true;
										ventConsDetalleN = ventasConsD;
									}
								}							
								if (existeDetalle) {
									//Alta Nuevo Detalle Unidad
									int cantNueva = ventConsDetalleN.getCantidad();
									montoSumado = ventConsDetalleN.getPrecioVenta();
									cantNueva = cantNueva + 1;									
									float subtotal = montoSumado * cantNueva;
									ventConsDetalleN.setCantidad(cantNueva);
									ventConsDetalleN.setSubtotal(subtotal);
									VentasConsDetalleUnidad ventaConsUnidadN = new VentasConsDetalleUnidad();
									ventaConsUnidadN.setConsignacionsDetalleUnidad(ventaConsUnidadF.getConsignacionsDetalleUnidad());
									ventaConsUnidadN.setEliminado(false);
									ventaConsUnidadN.setNroImei(nroImei);
									ventaConsUnidadN.setPrecioCompra(unidadMovil.getPrecioCompra());
									ventaConsUnidadN.setPrecioVenta(ventConsDetalleN.getPrecioVenta());
									ventaConsUnidadN.setVentasConsDetalle(ventConsDetalleN);												
									ventaConsignacionDetalleUnidadDAO.insertar(ventaConsUnidadN);
									ventaConsignacionDetalleDAO.update(ventConsDetalleN);
									//Baja Detalle
									if (ventConsDetalleF.getCantidad() == 1) {
										ventConsDetalleF.setEliminado(true);									
									} else {
										int cantF = ventConsDetalleF.getCantidad();
										cantF = cantF - 1;
										ventConsDetalleF.setCantidad(cantF);
									}								
									ventaConsignacionDetalleDAO.update(ventConsDetalleF);
									//Baja Venta Unidad
									montoDescuento = ventaConsUnidadF.getPrecioVenta();
									ventaConsUnidadF.setEliminado(true);
									ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidadF);
									//Actualizacion Monto Venta
									float montoActual = ventasCon.getMonto();
									montoActual = montoActual - montoDescuento;
									montoActual = montoActual + montoSumado;
									ventasCon.setMonto(montoActual);
									ventaConsignacionDAO.update(ventasCon);	
									//Actualizacion Cuenta Corriente
									int idVenta = ventasCon.getId();
									//Baja de venta en Cuenta Corriente, para realizar el alta luego
									CuentaCorriente cuenta = new CuentaCorriente();
									CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();								
									ccCliente.setIdMovimiento(ventasCon.getId());
									ccCliente.setNombreTabla("VentasCon");
									cuenta.deleteCuentaCorriente(ccCliente);								
									//Insercion de CC
									ccCliente = new CuentasCorrientesCliente();							
									ccCliente.setCliente(cli);
									ccCliente.setDebe(montoActual);
									ccCliente.setDetalle("Venta Consignación nro: " + idVenta);				
									ccCliente.setFecha(ventasCon.getFecha());
									ccCliente.setIdMovimiento(idVenta);
									ccCliente.setMonto(montoActual);
									ccCliente.setNombreTabla("VentasCon");
									ccCliente.setUsuario(usuario);
									cuenta.insertarCC(ccCliente);
									//Alta en Venta y Baja en Stock de Unidad
									unidadMovil.setEnStock(false);
									unidadMovil.setEnConsignacion(false);
									unidadMovil.setEnVenta(true);
									unidadMovil.setEstado(true);
									unidadMovil.setFechaMod(new Date());
									unidadMovil.setUsuario3(usuario);
									//Baja en Venta y Alta en Stock de Unidad
									UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
									unidadFalla.setDevuelto(true);
									unidadFalla.setEnConsignacion(false);
									unidadFalla.setEnStock(true);
									unidadFalla.setEnVenta(false);
									unidadFalla.setEstado(true);
									unidadFalla.setEliminado(false);
									unidadFalla.setEnGarantiaCliente(false);
									unidadFalla.setFechaMod(new Date());
									unidadFalla.setUsuario3(usuario);								
									int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
									int updtMovilVta = unidadMovilDAO.update(unidadMovil);				
									//Actualizacion de Stock en productos
									int stockF = prodFalla.getStock();
									int stockC = prodCambio.getStock();
									stockF = stockF + 1;
									stockC = stockC - 1;
									prodFalla.setStock(stockF);
									prodCambio.setStock(stockC);
									productoDAO.update(prodFalla);
									productoDAO.update(prodCambio);
									if (updtMovilVta != 0 && updtMvilFalla != 0) {
										//Actualizacion garantia
										garantiasCliente.setFechaMod(new Date());
										garantiasCliente.setFinalizado(true);
										garantiasCliente.setImeiReintegro(nroImei);
										garantiasCliente.setProducto2(prodCambio);
										garantiasCliente.setResolucion(resol);
										garantiasCliente.setTelefonoReintegro(prodCambio.getNombre());
										garantiasCliente.setUsuario3(usuario);
										int updGarantia = garantiasClienteDAO.update(garantiasCliente);
										if (updGarantia != 0) {
											listaGarantiasClientes = new ArrayList<GarantiasCliente>();
											filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
											listaGarantiasClientes = garantiasClienteDAO.getLista();
											filteredGarantiasClientes = listaGarantiasClientes;
											msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
											retorno = "garantiasclientes";
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
										}
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
									}
									
								} else {
									ListaPrecioProducto precioProducto = new ListaPrecioProducto();
									ListaPrecio listaPrecio = listaPrecioDAO.get(cli.getListaPrecio().getId());
									if (listaPrecio.getId() != 0) {
										precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
										if (precioProducto.getId() != 0 && precioProducto.getPrecioVenta() != 0) {
											ventConsDetalleN.setCantidad(1);
											ventConsDetalleN.setConsignacionsDetalle(ventConsDetalleF.getConsignacionsDetalle());
											ventConsDetalleN.setEliminado(false);
											ventConsDetalleN.setPrecioVenta(precioProducto.getPrecioVenta());
											ventConsDetalleN.setProducto(prodCambio);
											ventConsDetalleN.setSubtotal(precioProducto.getPrecioVenta());
											ventConsDetalleN.setVentasCon(ventasCon);										
											int idVentaDetalleN = ventaConsignacionDetalleDAO.insertar(ventConsDetalleN);
											ventConsDetalleN.setId(idVentaDetalleN);
											VentasConsDetalleUnidad ventaConsUnidadN = new VentasConsDetalleUnidad();
											ventaConsUnidadN.setConsignacionsDetalleUnidad(ventaConsUnidadF.getConsignacionsDetalleUnidad());
											ventaConsUnidadN.setEliminado(false);
											ventaConsUnidadN.setNroImei(nroImei);
											ventaConsUnidadN.setPrecioCompra(unidadMovil.getPrecioCompra());
											ventaConsUnidadN.setPrecioVenta(precioProducto.getPrecioVenta());
											montoSumado = precioProducto.getPrecioVenta();
											ventaConsUnidadN.setVentasConsDetalle(ventConsDetalleN);										
											ventaConsignacionDetalleUnidadDAO.insertar(ventaConsUnidadN);
											//Baja Detalle
											if (ventConsDetalleF.getCantidad() == 1) {
												ventConsDetalleF.setEliminado(true);									
											} else {
												int cantF = ventConsDetalleF.getCantidad();
												cantF = cantF - 1;
												ventConsDetalleF.setCantidad(cantF);
											}								
											ventaConsignacionDetalleDAO.update(ventConsDetalleF);
											//Baja Venta Unidad
											montoDescuento = ventaConsUnidadF.getPrecioVenta();
											ventaConsUnidadF.setEliminado(true);
											ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidadF);
											//Actualizacion Monto Venta
											float montoActual = ventasCon.getMonto();
											montoActual = montoActual - montoDescuento;
											montoActual = montoActual + montoSumado;
											ventasCon.setMonto(montoActual);
											ventaConsignacionDAO.update(ventasCon);
											//Actualizacion Cuenta Corriente
											int idVenta = ventasCon.getId();
											//Baja de venta en Cuenta Corriente, para realizar el alta luego
											CuentaCorriente cuenta = new CuentaCorriente();
											CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();								
											ccCliente.setIdMovimiento(ventasCon.getId());
											ccCliente.setNombreTabla("VentasCon");
											cuenta.deleteCuentaCorriente(ccCliente);								
											//Insercion de CC
											ccCliente = new CuentasCorrientesCliente();							
											ccCliente.setCliente(cli);
											ccCliente.setDebe(montoActual);
											ccCliente.setDetalle("Venta Consignación nro: " + idVenta);				
											ccCliente.setFecha(ventasCon.getFecha());
											ccCliente.setIdMovimiento(idVenta);
											ccCliente.setMonto(montoActual);
											ccCliente.setNombreTabla("VentasCon");
											ccCliente.setUsuario(usuario);
											cuenta.insertarCC(ccCliente);										
											//Alta en Venta y Baja en Stock de Unidad
											unidadMovil.setEnStock(false);
											unidadMovil.setEnConsignacion(false);
											unidadMovil.setEnVenta(true);
											unidadMovil.setEstado(true);
											unidadMovil.setFechaMod(new Date());
											unidadMovil.setUsuario3(usuario);
											//Baja en Venta y Alta en Stock de Unidad
											UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
											unidadFalla.setDevuelto(true);
											unidadFalla.setEnConsignacion(false);
											unidadFalla.setEnStock(true);
											unidadFalla.setEnVenta(false);
											unidadFalla.setEstado(true);
											unidadFalla.setEliminado(false);
											unidadFalla.setEnGarantiaCliente(false);
											unidadFalla.setFechaMod(new Date());
											unidadFalla.setUsuario3(usuario);								
											int updtMvilFalla = unidadMovilDAO.update(unidadFalla);
											int updtMovilVta = unidadMovilDAO.update(unidadMovil);			
											//Actualizacion de Stock en productos
											int stockF = prodFalla.getStock();
											int stockC = prodCambio.getStock();
											stockF = stockF + 1;
											stockC = stockC - 1;
											prodFalla.setStock(stockF);
											prodCambio.setStock(stockC);
											productoDAO.update(prodFalla);
											productoDAO.update(prodCambio);
											if (updtMovilVta != 0 && updtMvilFalla != 0) {
												//Actualizacion garantia
												garantiasCliente.setFechaMod(new Date());
												garantiasCliente.setFinalizado(true);
												garantiasCliente.setImeiReintegro(nroImei);
												garantiasCliente.setProducto2(prodCambio);
												garantiasCliente.setResolucion(resol);
												garantiasCliente.setTelefonoReintegro(prodCambio.getNombre());
												garantiasCliente.setUsuario3(usuario);
												int updGarantia = garantiasClienteDAO.update(garantiasCliente);
												if (updGarantia != 0) {
													listaGarantiasClientes = new ArrayList<GarantiasCliente>();
													filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
													listaGarantiasClientes = garantiasClienteDAO.getLista();
													filteredGarantiasClientes = listaGarantiasClientes;
													msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
													retorno = "garantiasclientes";
												} else {
													msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
												}
											} else {
												msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar las Unidades Móviles!", null);
											}
										} else {
											msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee Precio "
													+ "para ese Producto en la Lista de Precio", null);
										}									
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el cliente no posee "
												+ "Lista de Precio", null);
									}
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede realizar el movimiento de garantía, el Móvil con Falla esta registrado en Cuotas, "
										+ "realice las bajas de las mismas primero!", null);
							}							
						}
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede cerrar el ticket, el nro imei ingresado para cambio corresponde a un movimiento(Venta o Consignación)", null);
				}
			}
			//No posee arreglo, devolucion del dinero o devolucion sin accion
			if (opcion3) {
				//Falta opcion3 si es monto a favor del cliente se realiza la devolucion del equipo y monto a favor del cliente
				//Si no se realiza ninguna accion se cierra la garantia
				String resol = "No posee arreglo";
				String nroImei = garantiasCliente.getImeiFalla();
				String telefono = garantiasCliente.getProducto1().getNombre();
				Producto prod = productoDAO.get(garantiasCliente.getProducto1().getId());
				Cliente cli = clienteDAO.get(garantiasCliente.getCliente().getId());
				UnidadMovil unidMovil = unidadMovilDAO.get(nroImei);
				if (ningunaAccion) {
					unidMovil.setDevuelto(true);
					unidMovil.setConFalla(true);
					unidMovil.setEnGarantiaCliente(false);
					unidMovil.setFechaMod(new Date());
					unidMovil.setUsuario3(usuario);
					int updtMovil = unidadMovilDAO.update(unidMovil);
					if (updtMovil != 0) {
						garantiasCliente.setAccionRealizar("Ninguna Acción");
						garantiasCliente.setFechaMod(new Date());
						garantiasCliente.setFinalizado(true);
						garantiasCliente.setImeiReintegro(nroImei);
						garantiasCliente.setProducto2(prod);
						garantiasCliente.setResolucion(resol);
						garantiasCliente.setTelefonoReintegro(telefono);
						garantiasCliente.setUsuario3(usuario);
						int updGarantia = garantiasClienteDAO.update(garantiasCliente);					
						if (updGarantia != 0) {
							listaGarantiasClientes = new ArrayList<GarantiasCliente>();
							filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
							listaGarantiasClientes = garantiasClienteDAO.getLista();
							filteredGarantiasClientes = listaGarantiasClientes;
							msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
							retorno = "garantiasclientes";
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
					}					
				} else {
					boolean actualizo = true;
					VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(nroImei);
					int idMovimiento = 0;
					int idVentaCon = 0;
					boolean vendido = false;
					Date fechaAltaCon = null;
					Date fechaVentaCon = null;
					String nombreMov = "";
					float precioU = 0;
					if (ventaUnidad.getId() != 0) {
						float precioUnidad = ventaUnidad.getPrecioVenta();
						VentasDetalle ventaDetalle = ventaDetalleDAO.get(ventaUnidad.getVentasDetalle().getId());
						Venta venta = ventaDAO.get(ventaDetalle.getVenta().getId());
						float precioVenta = venta.getMonto() - precioUnidad;
						float precioDetalle = ventaDetalle.getSubtotal() - precioUnidad;
						int cantidadDetalle = ventaDetalle.getCantidad() - 1;						
						int idVenta = venta.getId();
						idMovimiento = idVenta;
						nombreMov = "Venta";
						precioU = precioUnidad;
						//Baja de venta, para realizar el alta si la venta tiene mas de un item
						CuentaCorriente cuenta = new CuentaCorriente();
						CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
						if(venta.getTipo().equals("c.c.")){
							ccCliente.setIdMovimiento(venta.getId());
							ccCliente.setNombreTabla("Venta");
							int deleteCC = cuenta.deleteCuentaCorriente(ccCliente);
							if(deleteCC == 0){
								actualizo = false;
							}
						}
						if(venta.getTipo().equals("ctdo.")){
							if(cuentaCorrienteDAO.deletePorMovimientoCliente(idVenta, "Venta", cli) == 0){
								actualizo = false;
							}
							MovimientoCaja movCaja = new MovimientoCaja();
							Caja mov = new Caja();
							mov.setIdMovimiento(idVenta);
							mov.setNombreTabla("Venta");
							if(movCaja.deleteCaja(mov) == 0){
								actualizo = false;
							}
						}
						//Actualizo producto
						int stock = prod.getStock() + 1;
						prod.setStock(stock);
						int updateProd = productoDAO.update(prod);
						if(updateProd == 0){
							actualizo = false;
						}
						//Si el precio == 0, significa que existia ese solo item en la venta, se da de baja por completo
						if (precioVenta == 0) {		
							ventaUnidad.setEliminado(true);
							ventaDetalle.setEliminado(true);
							venta.setEstado(false);
							venta.setFechaBaja(new Date());
							venta.setUsuario2(usuario);
							int updUnidad = ventaDetalleUnidadDAO.update(ventaUnidad);
							int updDetalle = ventaDetalleDAO.update(ventaDetalle);
							int updVenta = ventaDAO.update(venta);
							if (updUnidad == 0 || updDetalle == 0 || updVenta == 0) {
								actualizo = false;
							}
						} else {
							venta.setMonto(precioVenta);
							venta.setFechaMod(new Date());
							venta.setUsuario3(usuario);
							ventaUnidad.setEliminado(true);
							//Si cantidad detalle es != 0 el detalle posee mas de un item, para == 0 baja de detalle por tener un solo item
							if (cantidadDetalle != 0) {
								ventaDetalle.setSubtotal(precioDetalle);
								ventaDetalle.setCantidad(cantidadDetalle);
							} else {
								ventaDetalle.setEliminado(true);
							}
							int updUnidad = ventaDetalleUnidadDAO.update(ventaUnidad);
							int updDetalle = ventaDetalleDAO.update(ventaDetalle);
							int updVenta = ventaDAO.update(venta);
							if (updUnidad == 0 || updDetalle == 0 || updVenta == 0) {
								actualizo = false;
							}
							//Insercion de CC
							ccCliente = new CuentasCorrientesCliente();	
							ccCliente.setCliente(cli);
							ccCliente.setDebe(precioVenta);
							ccCliente.setDetalle("Venta nro: " + idVenta);				
							ccCliente.setFecha(venta.getFecha());
							ccCliente.setIdMovimiento(idVenta);
							ccCliente.setMonto(precioVenta);
							ccCliente.setNombreTabla("Venta");
							ccCliente.setUsuario(usuario);
							cuenta.insertarCC(ccCliente);
							//Si es una venta de contado, inserto el pago en CC y en Caja
							if (venta.getTipo().equals("ctdo.")) {
								ccCliente = new CuentasCorrientesCliente();								
								ccCliente.setCliente(cli);
								ccCliente.setDetalle("Pago de contado - Venta nro: " + idVenta);
								ccCliente.setFecha(venta.getFecha());
								ccCliente.setHaber(precioVenta);
								ccCliente.setIdMovimiento(idVenta);
								ccCliente.setMonto(precioVenta);
								ccCliente.setNombreTabla("Venta");
								ccCliente.setUsuario(usuario);
								cuenta.insertarCC(ccCliente);
								MovimientoCaja movimientoCaja = new MovimientoCaja();
								Caja caja = new Caja();
								caja.setConcepto("Cobro de Venta nro: " + idVenta);
								caja.setFecha(venta.getFecha());
								caja.setIdMovimiento(idVenta);
								caja.setMonto(precioVenta);
								caja.setNombreTabla("Venta");
								caja.setUsuario(usuario);
								movimientoCaja.insertarCaja(caja);
							}
						}
						
					}
					VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(nroImei);
					if (ventaConsUnidad.getId() != 0) {
						float precioUnidad = ventaConsUnidad.getPrecioVenta();
						VentasConsDetalle ventaConsDetalle = ventaConsignacionDetalleDAO.get(ventaConsUnidad.getVentasConsDetalle().getId());
						VentasCon ventaCon = ventaConsignacionDAO.get(ventaConsDetalle.getVentasCon().getId());
						float precioVenta = ventaCon.getMonto() - precioUnidad;
						float precioDetalle = ventaConsDetalle.getSubtotal() - precioUnidad;
						int cantidadDetalle = ventaConsDetalle.getCantidad() - 1;						
						idVentaCon = ventaCon.getId();
						vendido = true;
						precioU = precioUnidad;
						//Baja de venta, para realizar el alta si la venta tiene mas de un item
						CuentaCorriente cuenta = new CuentaCorriente();
						CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
						ccCliente.setIdMovimiento(ventaCon.getId());
						ccCliente.setNombreTabla("VentasCon");
						int deleteCC = cuenta.deleteCuentaCorriente(ccCliente);
						if(deleteCC == 0){
							actualizo = false;
						}
						int enConsignacion = prod.getEnConsignacion();
						enConsignacion = enConsignacion + 1;
						prod.setEnConsignacion(enConsignacion);
						int updateProd = productoDAO.update(prod);
						if(updateProd == 0){
							actualizo = false;
						}
						//Si el precio == 0, significa que existia ese solo item en la venta, se da de baja por completo
						if (precioVenta == 0) {		
							ventaConsUnidad.setEliminado(true);
							ventaConsDetalle.setEliminado(true);
							ventaCon.setEstado(false);
							ventaCon.setFechaBaja(new Date());
							ventaCon.setUsuario2(usuario);
							int updUnidad = ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidad);
							int updDetalle = ventaConsignacionDetalleDAO.update(ventaConsDetalle);
							int updVenta = ventaConsignacionDAO.update(ventaCon);
							if (updUnidad == 0 || updDetalle == 0 || updVenta == 0) {
								actualizo = false;
							}
						} else {
							ventaCon.setMonto(precioVenta);
							ventaCon.setFechaMod(new Date());
							ventaCon.setUsuario3(usuario);
							ventaConsUnidad.setEliminado(true);
							//Si cantidad detalle es != 0 el detalle posee mas de un item, para == 0 baja de detalle por tener un solo item
							if (cantidadDetalle != 0) {
								ventaConsDetalle.setSubtotal(precioDetalle);
								ventaConsDetalle.setCantidad(cantidadDetalle);
							} else {
								ventaConsDetalle.setEliminado(true);
							}
							int updUnidad = ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidad);
							int updDetalle = ventaConsignacionDetalleDAO.update(ventaConsDetalle);
							int updVenta = ventaConsignacionDAO.update(ventaCon);
							if (updUnidad == 0 || updDetalle == 0 || updVenta == 0) {
								actualizo = false;
							}
							//Insercion de CC
							ccCliente = new CuentasCorrientesCliente();							
							ccCliente.setCliente(cli);
							ccCliente.setDebe(precioVenta);
							ccCliente.setDetalle("Venta Consignación nro: " + idVentaCon);				
							ccCliente.setFecha(ventaCon.getFecha());
							ccCliente.setIdMovimiento(idVentaCon);
							ccCliente.setMonto(precioVenta);
							ccCliente.setNombreTabla("VentasCon");
							ccCliente.setUsuario(usuario);
							cuenta.insertarCC(ccCliente);
						}
					}
					ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(nroImei);
					if (consignacionUnidad.getId() != 0) {
//						precioVenta = consignacionUnidad.getPrecioVenta();
						float precioUnidad = consignacionUnidad.getPrecioVenta();
						ConsignacionsDetalle consignacionDetalle = consignacionDetalleDAO.get(consignacionUnidad.getConsignacionsDetalle().getId());
						Consignacion consignacion = consignacionDAO.get(consignacionDetalle.getConsignacion().getId());
						float precioVenta = consignacion.getMonto() - precioUnidad;
						float precioDetalle = consignacionDetalle.getSubtotal() - precioUnidad;
						int cantidadDetalle = consignacionDetalle.getCantidad() - 1;						
						int idConsignacion = consignacion.getId();
						fechaAltaCon = consignacionUnidad.getFechaAlta();
						fechaVentaCon = consignacionUnidad.getFechaVenta();
						idMovimiento = idConsignacion;
						nombreMov = "Consignacion";
						precioU = precioUnidad;						
						int stock = prod.getStock() + 1;
						int enConsignacion = prod.getEnConsignacion() - 1;
						prod.setStock(stock);
						prod.setEnConsignacion(enConsignacion);
						int updateProd = productoDAO.update(prod);
						if(updateProd == 0){
							actualizo = false;
						}
						//Si el precio == 0, significa que existia ese solo item en la venta, se da de baja por completo
						if (precioVenta == 0) {		
							consignacionUnidad.setEliminado(true);
							consignacionUnidad.setEnabled(false);
							consignacionUnidad.setFechaBaja(new Date());
							consignacionUnidad.setFechaVenta(null);
							consignacionUnidad.setVendido(false);
							consignacionDetalle.setEliminado(true);
							consignacion.setEstado(false);
							consignacion.setFechaBaja(new Date());
							consignacion.setUsuario2(usuario);
							int updUnidad = consignacionDetalleUnidadDAO.update(consignacionUnidad);
							int updDetalle = consignacionDetalleDAO.update(consignacionDetalle);
							int updVenta = consignacionDAO.update(consignacion);
							if (updUnidad == 0 || updDetalle == 0 || updVenta == 0) {
								actualizo = false;
							}
						} else {
							consignacion.setMonto(precioVenta);
							consignacion.setFechaMod(new Date());
							consignacion.setUsuario3(usuario);
							consignacionUnidad.setEliminado(true);
							consignacionUnidad.setEnabled(false);
							consignacionUnidad.setFechaBaja(new Date());
							consignacionUnidad.setFechaVenta(null);
							consignacionUnidad.setVendido(false);
							//Si cantidad detalle es != 0 el detalle posee mas de un item, para == 0 baja de detalle por tener un solo item
							if (cantidadDetalle != 0) {
								consignacionDetalle.setSubtotal(precioDetalle);
								consignacionDetalle.setCantidad(cantidadDetalle);
							} else {
								consignacionDetalle.setEliminado(true);
							}
							int updUnidad = consignacionDetalleUnidadDAO.update(consignacionUnidad);
							int updDetalle = consignacionDetalleDAO.update(consignacionDetalle);
							int updVenta = consignacionDAO.update(consignacion);
							if (updUnidad == 0 || updDetalle == 0 || updVenta == 0) {
								actualizo = false;
							}
						}						
					}					
					if (actualizo) {
						unidMovil.setEnStock(true);
						unidMovil.setDevuelto(true);
						unidMovil.setEliminado(false);
						unidMovil.setEnConsignacion(false);
						unidMovil.setEnStock(true);
						unidMovil.setEnVenta(false);
						unidMovil.setEstado(true);
						unidMovil.setConFalla(true);
						unidMovil.setEnGarantiaCliente(false);
						unidMovil.setFechaMod(new Date());
						unidMovil.setUsuario3(usuario);
						int updtUnidad = unidadMovilDAO.update(unidMovil);
						if (updtUnidad != 0) {
							garantiasCliente.setFechaAltaConsignacion(fechaAltaCon);
							garantiasCliente.setFechaVentaConsignacion(fechaVentaCon);
							garantiasCliente.setIdConVenta(idVentaCon);
							garantiasCliente.setIdMovimiento(idMovimiento);
							garantiasCliente.setNombreMovimiento(nombreMov);
							garantiasCliente.setPrecioUnidad(precioU);
							garantiasCliente.setAccionRealizar("Monto a favor del cliente");
							garantiasCliente.setFechaMod(new Date());
							garantiasCliente.setFinalizado(true);
							garantiasCliente.setImeiReintegro(nroImei);
							garantiasCliente.setProducto2(prod);
							garantiasCliente.setResolucion(resol);
							garantiasCliente.setTelefonoReintegro(telefono);
							garantiasCliente.setUsuario3(usuario);
							garantiasCliente.setVendido(vendido);
							int updGarantia = garantiasClienteDAO.update(garantiasCliente);
							if (updGarantia != 0) {
								listaGarantiasClientes = new ArrayList<GarantiasCliente>();
								filteredGarantiasClientes = new ArrayList<GarantiasCliente>();
								listaGarantiasClientes = garantiasClienteDAO.getLista();
								filteredGarantiasClientes = listaGarantiasClientes;
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
								retorno = "garantiasclientes";
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Unidad Móvil!", null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al reversar el movimiento correspondiente a la Unidad Móvil! "
								+ "Contáctese con su proveedor de servicio!", null);
					}
				}
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al cerrar el Ticket de Garantía. "
					+ "Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public String confirmarProveedor() {
		try {
			String retorno = "";
			FacesMessage msg = null;
			if (porReparacion) {
				String imei = garantiasProveedor.getImeiFalla();				
				String telefono = garantiasProveedor.getTelefonoFalla();
				Producto prod = garantiasProveedor.getProducto1();
				UnidadMovil unidad = unidadMovilDAO.get(imei);
				unidad.setEnGarantiaProveedor(false);
				unidad.setFechaMod(new Date());
				unidad.setUsuario3(usuario);
				unidad.setConFalla(true);
				String resol = "Sin Reparación";
				if (idResolucion == 0) {
					unidad.setConFalla(false);
					resol = "Reparado";
				}
				int updtMovil = unidadMovilDAO.update(unidad);
				if (updtMovil != 0) {
					garantiasProveedor.setFechaMod(new Date());
					garantiasProveedor.setFinalizado(true);
					garantiasProveedor.setImeiReintegro(imei);
					garantiasProveedor.setProducto2(prod);
					garantiasProveedor.setResolucion(resol);
					garantiasProveedor.setTelefonoReintegro(telefono);
					garantiasProveedor.setUsuario3(usuario);					
					int updGarantia = garantiasProveedorDAO.update(garantiasProveedor);
					if (garantiasProveedor.getCosto() > 0) {
						//Registro en cuenta corriente si el costo es != 0
						CuentaCorriente cuenta = new CuentaCorriente();
						CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
						ccProveedor.setProveedore(garantiasProveedor.getProveedore());
						ccProveedor.setDebe(garantiasProveedor.getCosto());
						ccProveedor.setDetalle("Garantia de Móvil: " + telefono);				
						ccProveedor.setFecha(garantiasProveedor.getFechaIngreso());
						ccProveedor.setIdMovimiento(updGarantia);
						ccProveedor.setMonto(garantiasProveedor.getCosto());
						ccProveedor.setNombreTabla("GarantiasProveedor");
						ccProveedor.setUsuario(usuario);
						cuenta.insertarCC(ccProveedor);
					}
					if (updGarantia != 0) {
						listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
						filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
						listaGarantiasProveedores = garantiasProveedorDAO.getLista();
						filteredGarantiasProveedores = listaGarantiasProveedores;
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
						retorno = "garantiasproveedores";
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
				}	
			}
			if (porGarantia) {
				if (opcion1) {
					String imei = garantiasProveedor.getImeiFalla();
					String resol = "Mismo Equipo";
					String telefono = garantiasProveedor.getTelefonoFalla();
					Producto prod = garantiasProveedor.getProducto1();
					UnidadMovil unidad = unidadMovilDAO.get(imei);
					unidad.setEnGarantiaProveedor(false);
					unidad.setFechaMod(new Date());
					unidad.setUsuario3(usuario);
					int updtMovil = unidadMovilDAO.update(unidad);
					if (updtMovil != 0) {
						garantiasProveedor.setFechaMod(new Date());
						garantiasProveedor.setFinalizado(true);
						garantiasProveedor.setImeiReintegro(imei);
						garantiasProveedor.setProducto2(prod);
						garantiasProveedor.setResolucion(resol);
						garantiasProveedor.setTelefonoReintegro(telefono);
						garantiasProveedor.setUsuario3(usuario);
						int updGarantia = garantiasProveedorDAO.update(garantiasProveedor);
						if (updGarantia != 0) {
							listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
							filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
							listaGarantiasProveedores = garantiasProveedorDAO.getLista();
							filteredGarantiasProveedores = listaGarantiasProveedores;
							msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
							retorno = "garantiasproveedores";
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
					}				
				}
				if (opcion2) {
					if (!garantiasProveedor.getImeiReintegro().isEmpty() && garantiasProveedor.getImeiReintegro() != null) {
						String resol = "Cambio de Equipo";
						String imeiFalla = garantiasProveedor.getImeiFalla();
						String imeiReintegro = garantiasProveedor.getImeiReintegro();
						String telefono = garantiasProveedor.getTelefonoFalla();
						Producto prod = garantiasProveedor.getProducto1();
						UnidadMovil unidadReintegro = unidadMovilDAO.get(imeiReintegro);
						if (unidadReintegro.getId() == 0) {
							UnidadMovil unidadFalla = unidadMovilDAO.get(imeiFalla);
							unidadFalla.setNroImei(imeiReintegro);
							unidadFalla.setEnGarantiaProveedor(false);
							unidadFalla.setFechaMod(new Date());
							unidadFalla.setUsuario3(usuario);
							int updtMovil = unidadMovilDAO.update(unidadFalla);
							Cuota cuota = cuotaDAO.get(imeiFalla);
							if (cuota.getId() != 0) {
								cuota.setNroImei(imeiReintegro);
								cuotaDAO.update(cuota);
							}
							CuotasVenta cuotaVenta = cuotaVentaDAO.get(imeiFalla);
							if (cuotaVenta.getId() != 0) {
								cuotaVenta.setNroImei(imeiReintegro);
								cuotaVentaDAO.update(cuotaVenta);
							}
							if (updtMovil != 0) {
								boolean actualizo = true;
								ComprasDetalleUnidad unidadCompra = compraDetalleUnidadDAO.get(imeiFalla);
								if (unidadCompra.getId() != 0) {
									unidadCompra.setNroImei(imeiReintegro);
									int updtCompra = compraDetalleUnidadDAO.update(unidadCompra);
									if (updtCompra == 0) {
										actualizo = false;
									}
								}
								VentasDetalleUnidad unidadVenta = ventaDetalleUnidadDAO.get(imeiFalla);
								if (unidadVenta.getId() != 0) {
									unidadVenta.setNroImei(imeiReintegro);
									int updtVenta = ventaDetalleUnidadDAO.update(unidadVenta);
									if (updtVenta == 0) {
										actualizo = false;
									}
								}
								ConsignacionsDetalleUnidad unidadConsignacion = consignacionDetalleUnidadDAO.get(imeiFalla);
								if (unidadConsignacion.getId() != 0) {
									unidadConsignacion.setNroImei(imeiReintegro);
									int updtConsignacion = consignacionDetalleUnidadDAO.update(unidadConsignacion);
									if (updtConsignacion == 0) {
										actualizo = false;
									}
								}
								VentasConsDetalleUnidad unidadVentaCons = ventaConsignacionDetalleUnidadDAO.get(imeiFalla);
								if (unidadVentaCons.getId() != 0) {
									unidadVentaCons.setNroImei(imeiReintegro);
									int updtVentaCons = ventaConsignacionDetalleUnidadDAO.update(unidadVentaCons);
									if (updtVentaCons == 0) {
										actualizo = false;
									}
								}
								if (actualizo) {
									garantiasProveedor.setFechaMod(new Date());
									garantiasProveedor.setFinalizado(true);
									garantiasProveedor.setImeiReintegro(imeiReintegro);
									garantiasProveedor.setProducto2(prod);
									garantiasProveedor.setResolucion(resol);
									garantiasProveedor.setTelefonoReintegro(telefono);
									garantiasProveedor.setUsuario3(usuario);							
									int updGarantia = garantiasProveedorDAO.update(garantiasProveedor);
									if (updGarantia != 0) {
										listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
										filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
										listaGarantiasProveedores = garantiasProveedorDAO.getLista();
										filteredGarantiasProveedores = listaGarantiasProveedores;
										msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
										retorno = "garantiasproveedores";
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al actualizar el/los movimientos "
											+ "asociados a la Unidad Movil", null);
								}						
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al actualizar la Unidad Móvil!", null);
							}					
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de imei ya se encuentra registrado al producto " 
						+ unidadReintegro.getProducto().getNombre(), null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de imei de reintegro es obligatorio!", null);
					}
				}
				if (opcion3) {
					String resol = "No posee arreglo";
					String telefono = garantiasProveedor.getProducto1().getNombre();
					Producto prod = productoDAO.get(garantiasProveedor.getProducto1().getId());
					Proveedore prov = proveedorDAO.get(garantiasProveedor.getProveedore().getId());
					String imeiFalla = garantiasProveedor.getImeiFalla();
					UnidadMovil unidad = unidadMovilDAO.get(imeiFalla);
					if (ningunaAccion) {			
						unidad.setConFalla(true);
						unidad.setEliminado(false);
						unidad.setEnGarantiaProveedor(false);
						unidad.setEstado(true);
						unidad.setFechaMod(new Date());
						unidad.setUsuario3(usuario);
						int updtUnidad = unidadMovilDAO.update(unidad);
						if (updtUnidad != 0) {
							garantiasProveedor.setAccionRealizar("Ninguna Acción");
							garantiasProveedor.setFechaMod(new Date());
							garantiasProveedor.setFinalizado(true);
							garantiasProveedor.setImeiReintegro(imeiFalla);
							garantiasProveedor.setProducto2(prod);
							garantiasProveedor.setResolucion(resol);
							garantiasProveedor.setTelefonoReintegro(telefono);
							garantiasProveedor.setUsuario3(usuario);
							int updGarantia = garantiasProveedorDAO.update(garantiasProveedor);
							if (updGarantia != 0) {
								listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
								filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
								listaGarantiasProveedores = garantiasProveedorDAO.getLista();
								filteredGarantiasProveedores = listaGarantiasProveedores;
								msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
								retorno = "garantiasproveedores";
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al actualizar el Móvil!", null);
						}
					} else {
//						ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(imeiFalla);
//						VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(imeiFalla);
//						VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(imeiFalla);
//						if (consignacionUnidad.getId() == 0 && ventaConsUnidad.getId() == 0 && ventaUnidad.getId() == 0) {
						boolean actualizo = true;
						ComprasDetalleUnidad compraUnidad = compraDetalleUnidadDAO.get(imeiFalla);
						int idMovimiento = 0;
						String nombreMov = "";
						float precioUnidad = unidad.getPrecioCompra();
						if (compraUnidad.getId() != 0) {
							compraUnidad.setConFalla(true);
							int updtCompraUnidad = compraDetalleUnidadDAO.update(compraUnidad);
							if (updtCompraUnidad == 0) {
								actualizo = false;
							}
							ComprasDetalle compraDetalle = compraDetalleDAO.get(compraUnidad.getComprasDetalle().getId());
							Compra compra = compraDAO.get(compraDetalle.getCompra().getId());
							idMovimiento = compra.getId();
							nombreMov = "Compra";
						}
						if (actualizo) {
							unidad.setEliminado(false);
							unidad.setEstado(true);
							unidad.setConFalla(true);
							unidad.setEnGarantiaProveedor(false);
							unidad.setFechaMod(new Date());
							unidad.setUsuario3(usuario);
							int updtUnidad = unidadMovilDAO.update(unidad);
							if (updtUnidad != 0) {
								garantiasProveedor.setIdMovimiento(idMovimiento);
								garantiasProveedor.setNombreMovimiento(nombreMov);
								garantiasProveedor.setPrecioUnidad(precioUnidad);
								garantiasProveedor.setAccionRealizar("Monto a favor");
								garantiasProveedor.setFechaMod(new Date());
								garantiasProveedor.setFinalizado(true);
								garantiasProveedor.setImeiReintegro(imeiFalla);
								garantiasProveedor.setProducto2(prod);
								garantiasProveedor.setResolucion(resol);
								garantiasProveedor.setTelefonoReintegro(telefono);
								garantiasProveedor.setUsuario3(usuario);
								int updGarantia = garantiasProveedorDAO.update(garantiasProveedor);
								if (updGarantia != 0) {
									//Registro en cuenta corriente
									CuentaCorriente cuenta = new CuentaCorriente();
									CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
									ccProveedor.setProveedore(prov);
									ccProveedor.setHaber(precioUnidad);
									ccProveedor.setDetalle("Garantia de Móvil: " + telefono);				
									ccProveedor.setFecha(garantiasProveedor.getFechaIngreso());
									ccProveedor.setIdMovimiento(updGarantia);
									ccProveedor.setMonto(precioUnidad);
									ccProveedor.setNombreTabla("GarantiasProveedor");
									ccProveedor.setUsuario(usuario);
									int updtCuenta = cuenta.insertarCC(ccProveedor);
									if (updtCuenta != 0) {
										listaGarantiasProveedores = new ArrayList<GarantiasProveedore>();
										filteredGarantiasProveedores = new ArrayList<GarantiasProveedore>();
										listaGarantiasProveedores = garantiasProveedorDAO.getLista();
										filteredGarantiasProveedores = listaGarantiasProveedores;
										msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket registrado!", null);
										retorno = "garantiasproveedores";
									} else {
										msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Movimiento en Cuenta Corriente!", null);
									}
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar el Ticket!", null);
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Unidad Móvil!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al reversar el movimiento correspondiente a la Unidad Móvil! "
									+ "Contáctese con su proveedor de servicio!", null);
						}
//						} else {
//							if (consignacionUnidad.getId() != 0) {
//								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El movimiento no puede realizarse, el Móvil esta asociado a una Consignación! "
//										+ "Realice la baja correspondiente!", null);
//							}
//							if (ventaUnidad.getId() != 0) {
//								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El movimiento no puede realizarse, el Móvil esta asociado a una Venta! "
//										+ "Realice la baja correspondiente!", null);
//							}
//						}						
					}
				}
			}			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al cerrar el Ticket de Garantía. "
					+ "Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public List<UnidadMovil> completeTextEntregar(String query){
		List<UnidadMovil> listAux = unidadMovilDAO.getLike(true, true, unidadMovil.getProducto(), query);
		List<String> lista = new ArrayList<String>();
		for (UnidadMovil unidadMovil : listAux) {
			lista.add(unidadMovil.getNroImei());
		}
		return listAux;
	}
	
	public void generarReporteCliente(List<GarantiasCliente> lista){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Garantias> listGarantia = new ArrayList<Garantias>();
		for (GarantiasCliente garan : lista) {
			Garantias garantias = new Garantias();
			garantias.setFechaIngreso(garan.getFechaIngreso());
			garantias.setPersona(garan.getCliente().getApellidoNombre());
			garantias.setProducto(garan.getTelefonoFalla());
			garantias.setImeiFalla(garan.getImeiFalla());			
			garantias.setResolucion(garan.getResolucion());
			if (garan.getFinalizado()) {
				garantias.setTipo("SI");
			} else {
				garantias.setTipo("NO");
			}
			listGarantia.add(garantias);
		}
		if(idCliente == 0){
			parametros.put("cliente", "Todos");
		}else{
			Cliente cli = clienteDAO.get(idCliente);
			parametros.put("cliente", cli.getNombreNegocio());
		}
		if(idProducto == 0){
			parametros.put("producto", "Todos");
		}else{
			Producto prod = productoDAO.get(idProducto);
			parametros.put("producto", prod.getNombre());
		}
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.generar(parametros, listGarantia, "garantiasCliente", "inline");
	}
	
	public void generarReporteProveedores(List<GarantiasProveedore> lista){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Garantias> listGarantia = new ArrayList<Garantias>();
		for (GarantiasProveedore garan : lista) {
			Garantias garantias = new Garantias();
			garantias.setConcepto(garan.getConcepto());
			garantias.setFechaIngreso(garan.getFechaIngreso());
			garantias.setPersona(garan.getProveedore().getApellidoNombre());
			garantias.setProducto(garan.getTelefonoFalla());
			garantias.setImeiFalla(garan.getImeiFalla());			
			garantias.setResolucion(garan.getResolucion());
			if (garan.getFinalizado()) {
				garantias.setTipo("SI");
			} else {
				garantias.setTipo("NO");
			}
			listGarantia.add(garantias);
		}
		if(idCliente == 0){
			parametros.put("cliente", "Todos");
		}else{
			Cliente cli = clienteDAO.get(idCliente);
			parametros.put("cliente", cli.getNombreNegocio());
		}
		if(idProducto == 0){
			parametros.put("producto", "Todos");
		}else{
			Producto prod = productoDAO.get(idProducto);
			parametros.put("producto", prod.getNombre());
		}
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.generar(parametros, listGarantia, "garantiasProveedor", "inline");
	}
	
	public void generarReporteComprobanteCliente(GarantiasCliente garanCliente){
		Reporte reporte = new Reporte();
//		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Garantias> listGarantia = new ArrayList<Garantias>();
		Garantias garantias = new Garantias();
		if (garanCliente.getFinalizado()) {
			garantias.setAccion(garanCliente.getAccionRealizar());
			garantias.setFallaFinal(garanCliente.getFallaDefinitiva());
			garantias.setFallaInicial(garanCliente.getFalla());
			garantias.setFechaIngreso(garanCliente.getFechaIngreso());
			garantias.setImeiCambio(garanCliente.getImeiReintegro());
			garantias.setImeiFalla(garanCliente.getImeiFalla());
			garantias.setObservaciones(garanCliente.getObservaciones());
			garantias.setPersona(garanCliente.getCliente().getApellidoNombre());
			garantias.setProducto(garanCliente.getTelefonoFalla());
			garantias.setResolucion(garanCliente.getResolucion());
			garantias.setTipo("Ticket de Garantía Cerrado");
		} else {
			garantias.setAccion(" - ");
			garantias.setFallaFinal(" - ");
			garantias.setFallaInicial(garanCliente.getFalla());
			garantias.setFechaIngreso(garanCliente.getFechaIngreso());
			garantias.setImeiCambio(" - ");
			garantias.setImeiFalla(garanCliente.getImeiFalla());
			garantias.setObservaciones(garanCliente.getObservaciones());
			garantias.setPersona(garanCliente.getCliente().getApellidoNombre());
			garantias.setProducto(garanCliente.getTelefonoFalla());
			garantias.setResolucion(" - ");
			garantias.setTipo("Ticket de Garantía Abierto");
		}
		listGarantia.add(garantias);
		reporte.generar(parametros, listGarantia, "garantiaCliente", "attachment");
	}
	
	public void generarReporteComprobanteProveedor(GarantiasProveedore garanProveedor){
		Reporte reporte = new Reporte();
//		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Garantias> listGarantia = new ArrayList<Garantias>();
		Garantias garantias = new Garantias();
		if (garanProveedor.getFinalizado()) {
			garantias.setAccion(garanProveedor.getAccionRealizar());
			garantias.setConcepto(garanProveedor.getConcepto());
			garantias.setCosto(garanProveedor.getCosto());
			garantias.setFallaFinal(garanProveedor.getFallaDefinitiva());
			garantias.setFallaInicial(garanProveedor.getFalla());
			garantias.setFechaIngreso(garanProveedor.getFechaIngreso());
			garantias.setImeiCambio(garanProveedor.getImeiReintegro());
			garantias.setImeiFalla(garanProveedor.getImeiFalla());
			garantias.setObservaciones(garanProveedor.getObservaciones());
			garantias.setPersona(garanProveedor.getProveedore().getApellidoNombre());
			garantias.setProducto(garanProveedor.getTelefonoFalla());
			garantias.setResolucion(garanProveedor.getResolucion());
			garantias.setTipo("Ticket de Garantía Cerrado");
		} else {
			garantias.setAccion(" - ");
			garantias.setConcepto(garanProveedor.getConcepto());
			garantias.setCosto(garanProveedor.getCosto());
			garantias.setFallaFinal(" - ");
			garantias.setFallaInicial(garanProveedor.getFalla());
			garantias.setFechaIngreso(garanProveedor.getFechaIngreso());
			garantias.setImeiCambio(" - ");
			garantias.setImeiFalla(garanProveedor.getImeiFalla());
			garantias.setObservaciones(garanProveedor.getObservaciones());
			garantias.setPersona(garanProveedor.getProveedore().getApellidoNombre());
			garantias.setProducto(garanProveedor.getTelefonoFalla());
			garantias.setResolucion(" - ");
			garantias.setTipo("Ticket de Garantía Abierto");
		}
		listGarantia.add(garantias);
		reporte.generar(parametros, listGarantia, "garantiaProveedor", "attachment");
	}

}
