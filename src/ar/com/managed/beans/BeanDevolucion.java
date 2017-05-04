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
import ar.com.clases.auxiliares.Devoluciones;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesCliente;
import model.entity.Cuota;
import model.entity.CuotasVenta;
import model.entity.Devolucione;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOCuota;
import dao.interfaces.DAOCuotaVenta;
import dao.interfaces.DAODevolucion;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;
import dao.interfaces.DAOVentaDetalle;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanDevolucion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanDevolucionDAO}")
	private DAODevolucion devolucionDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
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
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
	private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaVentaDAO}")
	private DAOCuotaVenta cuotaVentaDAO;
	
	private List<Devolucione> listaDevoluciones;
	private List<Devolucione> filteredDevoluciones;
	private List<Producto> listaProductos;
	private List<Cliente> listaClientes;
	private Devolucione devolucione;
	private Usuario usuario;
	private UnidadMovil unidadMovil;
	private Date fechaDesde;
	private Date fechaHasta;
	private String nroImei;
	private int idProducto;
	private int idCliente;

	public DAODevolucion getDevolucionDAO() {
		return devolucionDAO;
	}

	public void setDevolucionDAO(DAODevolucion devolucionDAO) {
		this.devolucionDAO = devolucionDAO;
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

	public List<Devolucione> getListaDevoluciones() {
		return listaDevoluciones;
	}

	public void setListaDevoluciones(List<Devolucione> listaDevoluciones) {
		this.listaDevoluciones = listaDevoluciones;
	}

	public List<Devolucione> getFilteredDevoluciones() {
		return filteredDevoluciones;
	}

	public void setFilteredDevoluciones(List<Devolucione> filteredDevoluciones) {
		this.filteredDevoluciones = filteredDevoluciones;
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

	public Devolucione getDevolucione() {
		return devolucione;
	}

	public void setDevolucione(Devolucione devolucione) {
		this.devolucione = devolucione;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public UnidadMovil getUnidadMovil() {
		return unidadMovil;
	}

	public void setUnidadMovil(UnidadMovil unidadMovil) {
		this.unidadMovil = unidadMovil;
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

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
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

	public String goDevoluciones(Usuario user){
		listaDevoluciones = new ArrayList<Devolucione>();
		filteredDevoluciones = new ArrayList<Devolucione>();
		listaDevoluciones = devolucionDAO.getLista(true);
		filteredDevoluciones = listaDevoluciones;
		listaProductos = new ArrayList<Producto>();
		listaClientes = new ArrayList<Cliente>();
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = productoDAO.getLista(true, rub);
		listaClientes = clienteDAO.getLista(true);
		devolucione = new Devolucione();
		usuario = new Usuario();
		fechaDesde = null;
		fechaHasta = null;
		usuario = user;		
		return "devoluciones";
	}
	
	public String goDevolucion(){
		nroImei = "";
		idCliente = 0;
		unidadMovil = new UnidadMovil();
		devolucione = new Devolucione();
		listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(true);
		devolucione.setFecha(new Date());
		return "devolucion";
	}
	
	public List<UnidadMovil> completeText(String query){
		List<UnidadMovil> listAux = unidadMovilDAO.getLike(query);
//		List<String> lista = new ArrayList<String>();
//		for (UnidadMovil unidadMovil : listAux) {
//			lista.add(unidadMovil.getNroImei());
//		}
		return listAux;
	}
	
	public void filtro(){
		listaDevoluciones = new ArrayList<Devolucione>();
		filteredDevoluciones = new ArrayList<Devolucione>();
		if(idProducto == 0 && idCliente == 0 && fechaDesde == null && fechaHasta == null){
			listaDevoluciones = devolucionDAO.getLista(true);
		}
		if(idProducto != 0 && idCliente == 0 && fechaDesde == null && fechaHasta == null){
			Producto prod = new Producto();
			prod.setId(idProducto);
			listaDevoluciones = devolucionDAO.getLista(true, prod);
		}
		if(idProducto == 0 && idCliente != 0 && fechaDesde == null && fechaHasta == null){
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaDevoluciones = devolucionDAO.getLista(true, cli);
		}
		if(idProducto == 0 && idCliente == 0 && fechaDesde != null && fechaHasta != null){
			listaDevoluciones = devolucionDAO.getLista(true, fechaDesde, fechaHasta);
		}
		if(idProducto != 0 && idCliente != 0 && fechaDesde == null && fechaHasta == null){
			Producto prod = new Producto();
			prod.setId(idProducto);
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaDevoluciones = devolucionDAO.getLista(true, cli, prod);
		}
		if(idProducto != 0 && idCliente == 0 && fechaDesde != null && fechaHasta != null){
			Producto prod = new Producto();
			prod.setId(idProducto);
			listaDevoluciones = devolucionDAO.getLista(true, prod, fechaDesde, fechaHasta);
		}
		if(idProducto == 0 && idCliente != 0 && fechaDesde != null && fechaHasta != null){
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaDevoluciones = devolucionDAO.getLista(true, cli, fechaDesde, fechaHasta);
		}
		if(idProducto != 0 && idCliente != 0 && fechaDesde != null && fechaHasta != null){
			Producto prod = new Producto();
			prod.setId(idProducto);
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaDevoluciones = devolucionDAO.getLista(true, cli, prod, fechaDesde, fechaHasta);
		}
		filteredDevoluciones = listaDevoluciones;
	}
	
	public void baja(Devolucione dev){
		FacesMessage msg = null;
		try {
			int idProd = dev.getProducto().getId();
			int idCli = dev.getCliente().getId();
			String imei = dev.getNroImei();
			
			String nombreTabla = dev.getNombreMovimiento();
			int idMov = dev.getIdMovimiento();
			float precioV = dev.getPrecioUnidad();
			Date fechaAltaConsig = dev.getFechaAltaConsignacion();
			Date fechaVentaConsig = dev.getFechaVentaConsignacion();
			int idVentaCons = dev.getIdConVenta();
			boolean vendido = dev.getVendido();
			
			Producto prod = productoDAO.get(idProd);
			Cliente cli = clienteDAO.get(idCli);			
			UnidadMovil unidad = unidadMovilDAO.get(imei);			
			
			if (nombreTabla.equals("Venta")) {
				Venta venta = ventaDAO.get(idMov);
				if (venta.getEstado()) {
					VentasDetalle ventDetalle = ventaDetalleDAO.get(venta, prod);
					if (ventDetalle.getId() != 0) {
						int cant = ventDetalle.getCantidad() + 1;
						float subtotal = ventDetalle.getSubtotal() + precioV;
						
						ventDetalle.setCantidad(cant);
						ventDetalle.setSubtotal(subtotal);
						
						VentasDetalleUnidad ventaUnidad = new VentasDetalleUnidad();
						ventaUnidad.setEliminado(false);
						ventaUnidad.setNroImei(imei);
						ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
						ventaUnidad.setPrecioVenta(precioV);
						ventaUnidad.setUnidadMovil(unidad);
						ventaUnidad.setVentasDetalle(ventDetalle);
						
						ventaDetalleDAO.update(ventDetalle);
						ventaDetalleUnidadDAO.insertar(ventaUnidad);
					} else {
						int cant = 1;
						float subtotal = precioV;
						ventDetalle = new VentasDetalle();
						ventDetalle.setAccesorio(false);
						ventDetalle.setCantidad(cant);
						ventDetalle.setEliminado(false);
						ventDetalle.setPrecioVenta(precioV);
						ventDetalle.setProducto(prod);
						ventDetalle.setSubtotal(subtotal);
						ventDetalle.setVenta(venta);
						int idDetalle = ventaDetalleDAO.insertar(ventDetalle);
						ventDetalle.setId(idDetalle);
						
						VentasDetalleUnidad ventaUnidad = new VentasDetalleUnidad();
						ventaUnidad.setEliminado(false);
						ventaUnidad.setNroImei(imei);
						ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
						ventaUnidad.setPrecioVenta(precioV);
						ventaUnidad.setUnidadMovil(unidad);
						ventaUnidad.setVentasDetalle(ventDetalle);
						ventaDetalleUnidadDAO.insertar(ventaUnidad);
					}
					float total = venta.getMonto() + precioV;
					venta.setMonto(total);
					venta.setFechaMod(new Date());
					venta.setUsuario3(usuario);
					
					CuentaCorriente cuenta = new CuentaCorriente();
					CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
					if(venta.getTipo().equals("c.c.")){
						ccCliente.setIdMovimiento(idMov);
						ccCliente.setNombreTabla("Venta");
						cuenta.deleteCuentaCorriente(ccCliente);
					}
					if(venta.getTipo().equals("ctdo.")){
						cuentaCorrienteDAO.deletePorMovimientoCliente(idMov, "Venta", cli);
								
						MovimientoCaja movCaja = new MovimientoCaja();
						Caja mov = new Caja();
						mov.setIdMovimiento(idMov);
						mov.setNombreTabla("Venta");
						movCaja.deleteCaja(mov);
					}
					
					int stock = prod.getStock() - 1;
					prod.setStock(stock);
					productoDAO.update(prod);
						
					ventaDAO.update(venta);
					//Insercion de CC
					ccCliente = new CuentasCorrientesCliente();	
					ccCliente.setCliente(cli);
					ccCliente.setDebe(total);
					ccCliente.setDetalle("Venta nro: " + idMov);				
					ccCliente.setFecha(venta.getFecha());
					ccCliente.setIdMovimiento(idMov);
					ccCliente.setMonto(total);
					ccCliente.setNombreTabla("Venta");
					ccCliente.setUsuario(usuario);
					cuenta.insertarCC(ccCliente);
					//Si es una venta de contado, inserto el pago en CC y en Caja
					if (venta.getTipo().equals("ctdo.")) {
						ccCliente = new CuentasCorrientesCliente();								
						ccCliente.setCliente(cli);
						ccCliente.setDetalle("Pago de contado - Venta nro: " + idMov);
						ccCliente.setFecha(venta.getFecha());
						ccCliente.setHaber(total);
						ccCliente.setIdMovimiento(idMov);
						ccCliente.setMonto(total);
						ccCliente.setNombreTabla("Venta");
						ccCliente.setUsuario(usuario);
						cuenta.insertarCC(ccCliente);
						MovimientoCaja movimientoCaja = new MovimientoCaja();
						Caja caja = new Caja();
						caja.setConcepto("Cobro de Venta nro: " + idMov);
						caja.setFecha(venta.getFecha());
						caja.setIdMovimiento(idMov);
						caja.setMonto(total);
						caja.setNombreTabla("Venta");
						caja.setUsuario(usuario);
						movimientoCaja.insertarCaja(caja);
					}
				} else {					
					venta.setCliente(cli);
					venta.setEstado(true);
					venta.setFechaMod(new Date());
					venta.setMonto(precioV);
					venta.setUsuario3(usuario);
					ventaDAO.update(venta);
					
					int cant = 1;
					float subtotal = precioV;
					
					VentasDetalle ventDetalle = new VentasDetalle();
					ventDetalle.setAccesorio(false);
					ventDetalle.setCantidad(cant);
					ventDetalle.setEliminado(false);
					ventDetalle.setPrecioVenta(precioV);
					ventDetalle.setProducto(prod);
					ventDetalle.setSubtotal(subtotal);
					ventDetalle.setVenta(venta);
					int idDetalle = ventaDetalleDAO.insertar(ventDetalle);
					ventDetalle.setId(idDetalle);
					
					VentasDetalleUnidad ventaUnidad = new VentasDetalleUnidad();
					ventaUnidad.setEliminado(false);
					ventaUnidad.setNroImei(imei);
					ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
					ventaUnidad.setPrecioVenta(precioV);
					ventaUnidad.setUnidadMovil(unidad);
					ventaUnidad.setVentasDetalle(ventDetalle);
					ventaDetalleUnidadDAO.insertar(ventaUnidad);
					
					CuentaCorriente cuenta = new CuentaCorriente();
					CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
					
					int stock = prod.getStock() - 1;
					prod.setStock(stock);
					productoDAO.update(prod);
					
					//Insercion de CC
					ccCliente.setCliente(cli);
					ccCliente.setDebe(precioV);
					ccCliente.setDetalle("Venta nro: " + idMov);				
					ccCliente.setFecha(venta.getFecha());
					ccCliente.setIdMovimiento(idMov);
					ccCliente.setMonto(precioV);
					ccCliente.setNombreTabla("Venta");
					ccCliente.setUsuario(usuario);
					cuenta.insertarCC(ccCliente);
					//Si es una venta de contado, inserto el pago en CC y en Caja
					if (venta.getTipo().equals("ctdo.")) {
						ccCliente = new CuentasCorrientesCliente();								
						ccCliente.setCliente(cli);
						ccCliente.setDetalle("Pago de contado - Venta nro: " + idMov);
						ccCliente.setFecha(venta.getFecha());
						ccCliente.setHaber(precioV);
						ccCliente.setIdMovimiento(idMov);
						ccCliente.setMonto(precioV);
						ccCliente.setNombreTabla("Venta");
						ccCliente.setUsuario(usuario);
						cuenta.insertarCC(ccCliente);
						MovimientoCaja movimientoCaja = new MovimientoCaja();
						Caja caja = new Caja();
						caja.setConcepto("Cobro de Venta nro: " + idMov);
						caja.setFecha(venta.getFecha());
						caja.setIdMovimiento(idMov);
						caja.setMonto(precioV);
						caja.setNombreTabla("Venta");
						caja.setUsuario(usuario);
						movimientoCaja.insertarCaja(caja);
					}
				}
				unidad.setEnStock(false);
				unidad.setEnVenta(true);				
			}
			if (nombreTabla.equals("Consignacion")) {
				Consignacion consignacion = consignacionDAO.get(idMov);
				if (consignacion.getEstado()) {
					ConsignacionsDetalle consDetalle = consignacionDetalleDAO.get(consignacion, prod);
					if (consDetalle.getId() != 0) {
						int cant = consDetalle.getCantidad() + 1;
						float subtotal = consDetalle.getSubtotal() + precioV;
						
						consDetalle.setCantidad(cant);
						consDetalle.setSubtotal(subtotal);
						
						ConsignacionsDetalleUnidad consignacionUnidad = new ConsignacionsDetalleUnidad();
						consignacionUnidad.setEliminado(false);
						consignacionUnidad.setNroImei(imei);
						consignacionUnidad.setFechaAlta(fechaAltaConsig);
						consignacionUnidad.setFechaVenta(fechaVentaConsig);
						consignacionUnidad.setVendido(vendido);
						consignacionUnidad.setPrecioCompra(unidad.getPrecioCompra());
						consignacionUnidad.setPrecioVenta(precioV);
						consignacionUnidad.setConsignacionsDetalle(consDetalle);
						
						consignacionDetalleDAO.update(consDetalle);
						consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
					} else {
						int cant = 1;
						float subtotal = precioV;
						consDetalle = new ConsignacionsDetalle();
						consDetalle.setCantidad(cant);
						consDetalle.setEliminado(false);
						consDetalle.setPrecioVenta(precioV);
						consDetalle.setProducto(prod);
						consDetalle.setSubtotal(subtotal);
						consDetalle.setConsignacion(consignacion);
						int idDetalle = consignacionDetalleDAO.insertar(consDetalle);
						consDetalle.setId(idDetalle);
						
						ConsignacionsDetalleUnidad consignacionUnidad = new ConsignacionsDetalleUnidad();
						consignacionUnidad.setEliminado(false);
						consignacionUnidad.setEnabled(true);
						consignacionUnidad.setNroImei(imei);
						consignacionUnidad.setFechaAlta(fechaAltaConsig);
						consignacionUnidad.setFechaVenta(fechaVentaConsig);
						consignacionUnidad.setVendido(vendido);
						consignacionUnidad.setPrecioCompra(unidad.getPrecioCompra());
						consignacionUnidad.setPrecioVenta(precioV);
						consignacionUnidad.setConsignacionsDetalle(consDetalle);
						consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
					}
					float total = consignacion.getMonto() + precioV;
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
					consignacion.setMonto(precioV);
					consignacion.setUsuario3(usuario);
					consignacionDAO.update(consignacion);
					
					int cant = 1;
					float subtotal = precioV;
					ConsignacionsDetalle consDetalle = new ConsignacionsDetalle();
					consDetalle.setCantidad(cant);
					consDetalle.setEliminado(false);
					consDetalle.setPrecioVenta(precioV);
					consDetalle.setProducto(prod);
					consDetalle.setSubtotal(subtotal);
					consDetalle.setConsignacion(consignacion);
					int idDetalle = consignacionDetalleDAO.insertar(consDetalle);
					consDetalle.setId(idDetalle);
					
					ConsignacionsDetalleUnidad consignacionUnidad = new ConsignacionsDetalleUnidad();
					consignacionUnidad.setEliminado(false);
					consignacionUnidad.setNroImei(imei);
					consignacionUnidad.setFechaAlta(fechaAltaConsig);
					consignacionUnidad.setFechaVenta(fechaVentaConsig);
					consignacionUnidad.setVendido(vendido);
					consignacionUnidad.setPrecioCompra(unidad.getPrecioCompra());
					consignacionUnidad.setPrecioVenta(precioV);
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
				
				unidad.setEnConsignacion(true);
			}
			if (vendido) {
				VentasCon ventCon = ventaConsignacionDAO.get(idVentaCons);
				if (ventCon.getEstado()) {
					VentasConsDetalle ventDetalle = ventaConsignacionDetalleDAO.get(ventCon, prod);
					if (ventDetalle.getId() != 0) {
						int cant = ventDetalle.getCantidad() + 1;
						float subtotal = ventDetalle.getSubtotal() + precioV;
						
						ventDetalle.setCantidad(cant);
						ventDetalle.setSubtotal(subtotal);
						
						VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
						ventaUnidad.setEliminado(false);
						ventaUnidad.setNroImei(imei);
						ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
						ventaUnidad.setPrecioVenta(precioV);
						ventaUnidad.setVentasConsDetalle(ventDetalle);
						
						ventaConsignacionDetalleDAO.update(ventDetalle);
						ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
					} else {
						int cant = 1;
						float subtotal = precioV;
						ventDetalle = new VentasConsDetalle();
						ventDetalle.setCantidad(cant);
						ventDetalle.setEliminado(false);
						ventDetalle.setPrecioVenta(precioV);
						ventDetalle.setProducto(prod);
						ventDetalle.setSubtotal(subtotal);
						ventDetalle.setVentasCon(ventCon);
						int idDetalle = ventaConsignacionDetalleDAO.insertar(ventDetalle);
						ventDetalle.setId(idDetalle);
						
						VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
						ventaUnidad.setEliminado(false);
						ventaUnidad.setNroImei(imei);
						ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
						ventaUnidad.setPrecioVenta(precioV);
						ventaUnidad.setVentasConsDetalle(ventDetalle);
						ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
					}
					float total = ventCon.getMonto() + precioV;
					ventCon.setMonto(total);
					ventCon.setFechaMod(new Date());
					ventCon.setUsuario3(usuario);
					
					CuentaCorriente cuenta = new CuentaCorriente();
					CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();	
					ccCliente.setIdMovimiento(ventCon.getId());
					ccCliente.setNombreTabla("VentasCon");
					cuenta.deleteCuentaCorriente(ccCliente);
					
					int enConsignacion = prod.getEnConsignacion();
					enConsignacion = enConsignacion - 1;
					prod.setEnConsignacion(enConsignacion);
					productoDAO.update(prod);
					
					ventaConsignacionDAO.update(ventCon);
					
					//Insercion de CC
					ccCliente = new CuentasCorrientesCliente();	
					ccCliente.setCliente(cli);
					ccCliente.setDebe(total);
					ccCliente.setDetalle("Venta Consignación nro: " + idMov);				
					ccCliente.setFecha(ventCon.getFecha());
					ccCliente.setIdMovimiento(idMov);
					ccCliente.setMonto(total);
					ccCliente.setNombreTabla("VentasCon");
					ccCliente.setUsuario(usuario);
					cuenta.insertarCC(ccCliente);
				} else {
					ventCon.setCliente(cli);
					ventCon.setEstado(true);
					ventCon.setFechaMod(new Date());
					ventCon.setMonto(precioV);
					ventCon.setUsuario3(usuario);
					ventaConsignacionDAO.update(ventCon);
					
					int cant = 1;
					float subtotal = precioV;
					VentasConsDetalle ventDetalle = new VentasConsDetalle();
					ventDetalle.setCantidad(cant);
					ventDetalle.setEliminado(false);
					ventDetalle.setPrecioVenta(precioV);
					ventDetalle.setProducto(prod);
					ventDetalle.setSubtotal(subtotal);
					ventDetalle.setVentasCon(ventCon);
					int idDetalle = ventaConsignacionDetalleDAO.insertar(ventDetalle);
					ventDetalle.setId(idDetalle);
					
					VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
					ventaUnidad.setEliminado(false);
					ventaUnidad.setNroImei(imei);
					ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
					ventaUnidad.setPrecioVenta(precioV);
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
					ccCliente.setDebe(precioV);
					ccCliente.setDetalle("Venta Consignación nro: " + idMov);				
					ccCliente.setFecha(ventCon.getFecha());
					ccCliente.setIdMovimiento(idMov);
					ccCliente.setMonto(precioV);
					ccCliente.setNombreTabla("VentasCon");
					ccCliente.setUsuario(usuario);
					cuenta.insertarCC(ccCliente);
				}
				unidad.setEnStock(false);
				unidad.setEnVenta(true);	
			}
			unidad.setDevuelto(false);
			unidad.setEliminado(false);						
			unidad.setEstado(true);
			unidad.setFechaMod(new Date());
			unidad.setUsuario3(usuario);
			int updtUnidad = unidadMovilDAO.update(unidad);
			if (updtUnidad != 0) {
				dev.setEstado(false);
				dev.setFechaBaja(new Date());
				dev.setUsuario2(usuario);
				
				int updDevolucion = devolucionDAO.update(dev);					
				if (updDevolucion != 0) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró la baja de la Devolución!", null);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la baja de la Devolución!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al actualizar la Unidad Móvil!", null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public String guardar(){
		try {
			nroImei = "";
			String retorno = "";
			FacesMessage msg = null;			
			if (devolucione.getFecha() != null && unidadMovil.getId() != 0 && idCliente != 0) {
				if (!unidadMovil.getEnGarantiaCliente() && !unidadMovil.getEnGarantiaProveedor()) {
					nroImei = unidadMovil.getNroImei();
					Cuota cuota = cuotaDAO.get(nroImei);
					CuotasVenta cuotaVenta = cuotaVentaDAO.get(nroImei);					
					if (cuota.getId() != 0) {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Móvil seleccionado posee Cuotas! Realice la baja de las mismas primero!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return "";
					}
					if (cuotaVenta.getId() != 0) {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Móvil seleccionado posee Cuotas! Realice la baja de las mismas primero!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return "";
					}
					boolean enConsignacion = false;
					boolean enVenta = false;
					boolean enVentaCons = false;
					boolean existe = false;
					VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(nroImei);
					VentasConsDetalle ventaConDetalle = new VentasConsDetalle();
					VentasCon ventaCon = new VentasCon();
					if (ventaConsUnidad.getId() != 0) {				
						ventaConDetalle = ventaConsignacionDetalleDAO.get(ventaConsUnidad.getVentasConsDetalle().getId());
						ventaCon = ventaConsignacionDAO.get(ventaConDetalle.getVentasCon().getId());
						if (ventaCon.getCliente().getId() == idCliente) {
							existe = true;
							enVentaCons = true;
						}					
					}
					ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(nroImei);
					ConsignacionsDetalle consignacionDetalle = new ConsignacionsDetalle();
					Consignacion consignacion = new Consignacion();
					if (consignacionUnidad.getId() != 0) {						
						consignacionDetalle = consignacionDetalleDAO.get(consignacionUnidad.getConsignacionsDetalle().getId());
						consignacion = consignacionDAO.get(consignacionDetalle.getConsignacion().getId());
						if (consignacion.getCliente().getId() == idCliente) {
							existe = true;
							enConsignacion = true;
						}					
					}
					VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(nroImei);
					VentasDetalle ventaDetalle = new VentasDetalle();
					Venta venta = new Venta();
					if (ventaUnidad.getId() != 0) {
						ventaDetalle = ventaDetalleDAO.get(ventaUnidad.getVentasDetalle().getId());
						venta = ventaDAO.get(ventaDetalle.getVenta().getId());
						if (venta.getCliente().getId() == idCliente) {
							existe = true;
							enVenta = true;
						}
					}				
					if (existe) {
						boolean actualizo = true;
						int idMovimiento = 0;
						int idVentaCon = 0;
						String nombreMov = "";
						float precioU = 0;
						Date fechaAltaConsig = null;
						Date fechaVentaConsig = null;
						boolean vendido = false;
						Producto prod = productoDAO.get(unidadMovil.getProducto().getId());
						Cliente cli = clienteDAO.get(idCliente);
						String telefono = prod.getNombre();
						//Baja de la Venta de Consignacion
						if (enVentaCons) {
							float precioUnidad = ventaConsUnidad.getPrecioVenta();
							//VentasConsDetalle ventaConsDetalle = ventaConsignacionDetalleDAO.get(ventaConsUnidad.getVentasConsDetalle().getId());
							//VentasCon ventaCon = ventaConsignacionDAO.get(ventaConsDetalle.getVentasCon().getId());
							float precioVenta = ventaCon.getMonto() - precioUnidad;
							float precioDetalle = ventaConDetalle.getSubtotal() - precioUnidad;
							int cantidadDetalle = ventaConDetalle.getCantidad() - 1;	
							//Obtengo parametros que se guardan en Devolucione
							idVentaCon = ventaCon.getId();
							vendido = true;
							//Baja de venta, para realizar el alta si la venta tiene mas de un item
							CuentaCorriente cuenta = new CuentaCorriente();
							CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
							ccCliente.setIdMovimiento(ventaCon.getId());
							ccCliente.setNombreTabla("VentasCon");
							int deleteCC = cuenta.deleteCuentaCorriente(ccCliente);
							if(deleteCC == 0){
								actualizo = false;
							}
							//Actualizo producto le sumo uno a en_consignacion
							int enConsig = prod.getEnConsignacion();
							enConsig = enConsig + 1;
							prod.setEnConsignacion(enConsig);
							int updateProd = productoDAO.update(prod);
							if(updateProd == 0){
								actualizo = false;
							}
							//Actualizo Unidad de venta de consignacion
							ventaConsUnidad.setEliminado(true);
							int updUnidad = ventaConsignacionDetalleUnidadDAO.update(ventaConsUnidad);
							if (updUnidad == 0) {
								actualizo = false;
							}
							//Si el precio == 0, significa que existia ese solo item en la venta, se da de baja por completo
							if (precioVenta == 0) {							
								ventaConDetalle.setEliminado(true);
								ventaCon.setEstado(false);
								ventaCon.setFechaBaja(new Date());
								ventaCon.setUsuario2(usuario);
								
								int updDetalle = ventaConsignacionDetalleDAO.update(ventaConDetalle);
								int updVenta = ventaConsignacionDAO.update(ventaCon);
								if (updDetalle == 0 || updVenta == 0) {
									actualizo = false;
								}
							} else {
								ventaCon.setMonto(precioVenta);
								ventaCon.setFechaMod(new Date());
								ventaCon.setUsuario3(usuario);
								
								//Si cantidad detalle es != 0 el detalle posee mas de un item, para == 0 baja de detalle por tener un solo item
								if (cantidadDetalle != 0) {
									ventaConDetalle.setSubtotal(precioDetalle);
									ventaConDetalle.setCantidad(cantidadDetalle);
								} else {
									ventaConDetalle.setEliminado(true);
								}							
								int updDetalle = ventaConsignacionDetalleDAO.update(ventaConDetalle);
								int updVenta = ventaConsignacionDAO.update(ventaCon);
								if (updDetalle == 0 || updVenta == 0) {
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
						//Baja de la Consignacion
						if (enConsignacion) {
//							precioVenta = consignacionUnidad.getPrecioVenta();
							float precioUnidad = consignacionUnidad.getPrecioVenta();
							//ConsignacionsDetalle consignacionDetalle = consignacionDetalleDAO.get(consignacionUnidad.getConsignacionsDetalle().getId());
							//Consignacion consignacion = consignacionDAO.get(consignacionDetalle.getConsignacion().getId());
							float precioVenta = consignacion.getMonto() - precioUnidad;
							float precioDetalle = consignacionDetalle.getSubtotal() - precioUnidad;
							int cantidadDetalle = consignacionDetalle.getCantidad() - 1;						
							int idConsignacion = consignacion.getId();
							//Obtengo parametros que se guardan en Devolucione
							idMovimiento = idConsignacion;
							nombreMov = "Consignacion";
							precioU = precioUnidad;	
							fechaAltaConsig = consignacionUnidad.getFechaAlta();
							fechaVentaConsig = consignacionUnidad.getFechaVenta();
							//Actualizo producto, en este caso le sumo uno al stock y le resto uno a consignacion
							int stock = prod.getStock() + 1;
							int enConsig = prod.getEnConsignacion() - 1;
							prod.setStock(stock);
							prod.setEnConsignacion(enConsig);
							int updateProd = productoDAO.update(prod);
							if(updateProd == 0){
								actualizo = false;
							}
							//Actualizo Unidad en consignacion
							consignacionUnidad.setEliminado(true);
							consignacionUnidad.setEnabled(false);
							consignacionUnidad.setFechaBaja(new Date());
							consignacionUnidad.setFechaVenta(null);
							consignacionUnidad.setVendido(false);
							int updUnidad = consignacionDetalleUnidadDAO.update(consignacionUnidad);
							if (updUnidad == 0) {
								actualizo = false;
							}
							//Si el precio == 0, significa que existia ese solo item, se da de baja por completo el movimiento
							if (precioVenta == 0) {
								consignacionDetalle.setEliminado(true);
								consignacion.setEstado(false);
								consignacion.setFechaBaja(new Date());
								consignacion.setUsuario2(usuario);							
								int updDetalle = consignacionDetalleDAO.update(consignacionDetalle);
								int updVenta = consignacionDAO.update(consignacion);
								if (updDetalle == 0 || updVenta == 0) {
									actualizo = false;
								}
							} else {
								consignacion.setMonto(precioVenta);
								consignacion.setFechaMod(new Date());
								consignacion.setUsuario3(usuario);							
								//Si cantidad detalle es != 0 el detalle posee mas de un item, para == 0 baja de detalle por tener un solo item
								if (cantidadDetalle != 0) {
									consignacionDetalle.setSubtotal(precioDetalle);
									consignacionDetalle.setCantidad(cantidadDetalle);
								} else {
									consignacionDetalle.setEliminado(true);
								}
								int updDetalle = consignacionDetalleDAO.update(consignacionDetalle);
								int updVenta = consignacionDAO.update(consignacion);
								if (updDetalle == 0 || updVenta == 0) {
									actualizo = false;
								}
							}
						}
						//Baja de la Venta
						if (enVenta) {
							float precioUnidad = ventaUnidad.getPrecioVenta();
							//VentasDetalle ventaDetalle = ventaDetalleDAO.get(ventaUnidad.getVentasDetalle().getId());
							//Venta venta = ventaDAO.get(ventaDetalle.getVenta().getId());
							float precioVenta = venta.getMonto() - precioUnidad;
							float precioDetalle = ventaDetalle.getSubtotal() - precioUnidad;
							int cantidadDetalle = ventaDetalle.getCantidad() - 1;						
							int idVenta = venta.getId();
							//Obtengo parametros que se guardan en Devolucione
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
							//Actualizo stock de producto
							int stock = prod.getStock() + 1;
							prod.setStock(stock);
							int updateProd = productoDAO.update(prod);
							if(updateProd == 0){
								actualizo = false;
							}
							//Actualizo unidad de venta
							ventaUnidad.setEliminado(true);							
							int updUnidad = ventaDetalleUnidadDAO.update(ventaUnidad);
							if (updUnidad == 0) {
								actualizo = false;
							}
							//Si el precio == 0, significa que existia ese solo item en la venta, se da de baja por completo
							if (precioVenta == 0) {						
								ventaDetalle.setEliminado(true);
								venta.setEstado(false);
								venta.setFechaBaja(new Date());
								venta.setUsuario2(usuario);
								
								int updDetalle = ventaDetalleDAO.update(ventaDetalle);
								int updVenta = ventaDAO.update(venta);
								if (updDetalle == 0 || updVenta == 0) {
									actualizo = false;
								}
							} else {
								venta.setMonto(precioVenta);
								venta.setFechaMod(new Date());
								venta.setUsuario3(usuario);
								
								//Si cantidad detalle es != 0 el detalle posee mas de un item, para == 0 baja de detalle por tener un solo item
								if (cantidadDetalle != 0) {
									ventaDetalle.setSubtotal(precioDetalle);
									ventaDetalle.setCantidad(cantidadDetalle);
								} else {
									ventaDetalle.setEliminado(true);
								}
								
								int updDetalle = ventaDetalleDAO.update(ventaDetalle);
								int updVenta = ventaDAO.update(venta);
								if (updDetalle == 0 || updVenta == 0) {
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
						if (actualizo) {
							unidadMovil.setDevuelto(true);
							unidadMovil.setEnStock(true);
							unidadMovil.setEstado(true);
							unidadMovil.setEliminado(false);
							unidadMovil.setEnConsignacion(false);						
							unidadMovil.setEnVenta(false);
							unidadMovil.setFechaMod(new Date());
							unidadMovil.setUsuario3(usuario);
							int updtUnidad = unidadMovilDAO.update(unidadMovil);
							if (updtUnidad != 0) {
								devolucione.setFechaAltaConsignacion(fechaAltaConsig);
								devolucione.setFechaVentaConsignacion(fechaVentaConsig);
								devolucione.setIdConVenta(idVentaCon);
								devolucione.setIdMovimiento(idMovimiento);
								devolucione.setNombreMovimiento(nombreMov);
								devolucione.setPrecioUnidad(precioU);
								devolucione.setNroImei(nroImei);
								devolucione.setProducto(prod);
								devolucione.setTelefono(telefono);
								devolucione.setCliente(cli);
								devolucione.setEstado(true);
								devolucione.setFechaAlta(new Date());
								devolucione.setUsuario1(usuario);
								devolucione.setVendido(vendido);
								int idDevolucion = devolucionDAO.insertar(devolucione);
								if (idDevolucion != 0) {
									idCliente = 0;
									idProducto = 0;
									listaDevoluciones = new ArrayList<Devolucione>();
									filteredDevoluciones = new ArrayList<Devolucione>();
									listaDevoluciones = devolucionDAO.getLista(true);
									filteredDevoluciones = listaDevoluciones;
									msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolución registrada!", null);
									retorno = "devoluciones";
								} else {
									msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Devolución!", null);
								}
							} else {
								msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al registrar la Unidad Móvil!", null);
							}
						} else {
							msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al reversar el movimiento correspondiente a la Unidad Móvil! "
									+ "Contáctese con su proveedor de servicio!", null);
						}
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo registrar la Devolución, el Cliente no coincide con el movimiento "
						+ "relacionado al Móvil!", null);
					}
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede registrar la Devolución, el Móvil se encuentra en Garantía!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo registrar la Devolución, la fecha, el cliente "
						+ "y el nro de imei son obligatorios!", null);				
			}			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al guardar la Devolución!" 
			+ " Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public void verMovimiento(Devolucione dev) {
		devolucione = new Devolucione();
		devolucione = dev;
	}
	
	public void generarReporteDevoluciones(List<Devolucione> listDevolucions){
		Reporte reporte = new Reporte();
		//DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Devoluciones> listaDevolucion = new ArrayList<Devoluciones>();
		for (Devolucione dev : listDevolucions) {
			Devoluciones devoluciones = new Devoluciones();
			devoluciones.setCliente(dev.getCliente().getNombreNegocio());
			devoluciones.setObservaciones(dev.getDescripcion());
			devoluciones.setFechaIngreso(formatoFecha.format(dev.getFecha()));
			devoluciones.setNroImei(dev.getNroImei());
			devoluciones.setProducto(dev.getProducto().getNombre());
			listaDevolucion.add(devoluciones);
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
		reporte.generar(parametros, listaDevolucion, "devoluciones", "inline");
	}
	
	public void generarReporteComprobante(Devolucione dev){
		Reporte reporte = new Reporte();
		//DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Devoluciones> listaDevolucion = new ArrayList<Devoluciones>();
		Devoluciones devoluciones = new Devoluciones();
		devoluciones.setCliente(dev.getCliente().getNombreNegocio());
		devoluciones.setObservaciones(dev.getDescripcion());
		devoluciones.setFechaIngreso(formatoFecha.format(dev.getFecha()));
		devoluciones.setNroImei(dev.getNroImei());
		devoluciones.setProducto(dev.getProducto().getNombre());
		devoluciones.setNombreMovimiento(dev.getNombreMovimiento());
		devoluciones.setNroMovimiento(Integer.toString(dev.getIdMovimiento()));
		listaDevolucion.add(devoluciones);
		reporte.generar(parametros, listaDevolucion, "devolucion", "attachment");
	}

}
