package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.ProductoUnidad;
import ar.com.clases.reportes.Ganancia;
import ar.com.clases.reportes.GananciaDetalle;
import ar.com.clases.reportes.RankingCliente;
import ar.com.clases.reportes.RankingProducto;
import ar.com.clases.reportes.RankingProveedor;
import ar.com.clases.reportes.VentasDetalleRanking;
import ar.com.clases.reportes.VentasRanking;
import dao.interfaces.DAOCaja;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOCompra;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOGasto;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOProveedor;
import dao.interfaces.DAOStock;
import dao.interfaces.DAOStockVentaDetalle;
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
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Gasto;
import model.entity.Producto;
import model.entity.Proveedore;
import model.entity.Rubro;
import model.entity.Stock;
import model.entity.StocksVentasDetalle;
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
public class BeanReporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanReporte.class);
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleDAO}")
	private DAOVentaDetalle ventaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanCompraDAO}")
	private DAOCompra compraDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanCajaDAO}")
	private DAOCaja cajaDAO;
	
	@ManagedProperty(value = "#{BeanGastoDAO}")
	private DAOGasto gastoDAO;
	
	@ManagedProperty(value = "#{BeanStockDAO}")
	private DAOStock stockDAO;
	
	@ManagedProperty(value = "#{BeanStockVentaDetalleDAO}")
	private DAOStockVentaDetalle stockVentaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	private List<RankingCliente> listaRankingCliente;
	private List<RankingCliente> filteredRankingCliente;
	private List<RankingProveedor> listaRankingProveedor;
	private List<RankingProveedor> filteredRankingProveedor;
	private List<RankingProducto> listaRankingProducto;
	private List<RankingProducto> filteredRankingProducto;
	private List<ProductoUnidad> listaProductoUnidads;
	private List<Ganancia> listaGanancia;
	private List<Ganancia> filteredGanancia;
	private List<Producto> listaProductos;
	private List<Cliente> listaClientes;
	private List<Proveedore> listaProveedores;
	private List<String> listaIdClientes;
	private List<String> listaIdProveedores;
	private List<Compra> listaCompras;
	private Usuario usuario;
	private Producto producto;
	private Ganancia gananciaObj;
	private Date fechaDesde;
	private Date fechaHasta;
	private String encabezado;
	private int cantidadTotal;
	private int idProducto;
	private int nroVenta;
	private int idTipoMovimiento;
	private int idTipoReporte;
	private int idCliente;
	private int idProveedor;
	private int idTipoProducto;
	private int idEstado;
	private float montoTotal;
	private float gananciaTotal;
	private float totalCaja;
	private float totalccProveedor;
	private float totalccCliente;
	private float totalGastos;
	private float totalStockProductos;
	private float totalStockProductosUsados;
	private float totalStockAccesorios;
	private float totalActivos;
	private float totalPasivos;
	private float totalPatrimonio;
	private boolean gananciaVentas;
	private boolean gananciaProductos;
	private boolean onVenta;
	private boolean habilitaPersona;
	private boolean esCliente;
	private boolean tablaCliente;
	private boolean tablaProveedor;

	public DAOVenta getVentaDAO() {
		return ventaDAO;
	}

	public void setVentaDAO(DAOVenta ventaDAO) {
		this.ventaDAO = ventaDAO;
	}

	public DAOVentaDetalle getVentaDetalleDAO() {
		return ventaDetalleDAO;
	}

	public void setVentaDetalleDAO(DAOVentaDetalle ventaDetalleDAO) {
		this.ventaDetalleDAO = ventaDetalleDAO;
	}

	public DAOVentaDetalleUnidad getVentaDetalleUnidadDAO() {
		return ventaDetalleUnidadDAO;
	}

	public void setVentaDetalleUnidadDAO(DAOVentaDetalleUnidad ventaDetalleUnidadDAO) {
		this.ventaDetalleUnidadDAO = ventaDetalleUnidadDAO;
	}

	public DAOCompra getCompraDAO() {
		return compraDAO;
	}

	public void setCompraDAO(DAOCompra compraDAO) {
		this.compraDAO = compraDAO;
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

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public DAOCaja getCajaDAO() {
		return cajaDAO;
	}

	public void setCajaDAO(DAOCaja cajaDAO) {
		this.cajaDAO = cajaDAO;
	}

	public DAOGasto getGastoDAO() {
		return gastoDAO;
	}

	public void setGastoDAO(DAOGasto gastoDAO) {
		this.gastoDAO = gastoDAO;
	}

	public DAOStock getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(DAOStock stockDAO) {
		this.stockDAO = stockDAO;
	}

	public DAOStockVentaDetalle getStockVentaDetalleDAO() {
		return stockVentaDetalleDAO;
	}

	public void setStockVentaDetalleDAO(DAOStockVentaDetalle stockVentaDetalleDAO) {
		this.stockVentaDetalleDAO = stockVentaDetalleDAO;
	}

	public DAOVentaConsignacion getVentaConsignacionDAO() {
		return ventaConsignacionDAO;
	}

	public void setVentaConsignacionDAO(DAOVentaConsignacion ventaConsignacionDAO) {
		this.ventaConsignacionDAO = ventaConsignacionDAO;
	}

	public DAOVentaConsignacionDetalle getVentaConsignacionDetalleDAO() {
		return ventaConsignacionDetalleDAO;
	}

	public void setVentaConsignacionDetalleDAO(
			DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO) {
		this.ventaConsignacionDetalleDAO = ventaConsignacionDetalleDAO;
	}

	public DAOVentaConsignacionDetalleUnidad getVentaConsignacionDetalleUnidadDAO() {
		return ventaConsignacionDetalleUnidadDAO;
	}

	public void setVentaConsignacionDetalleUnidadDAO(
			DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO) {
		this.ventaConsignacionDetalleUnidadDAO = ventaConsignacionDetalleUnidadDAO;
	}

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public List<RankingCliente> getListaRankingCliente() {
		return listaRankingCliente;
	}

	public void setListaRankingCliente(List<RankingCliente> listaRankingCliente) {
		this.listaRankingCliente = listaRankingCliente;
	}

	public List<RankingCliente> getFilteredRankingCliente() {
		return filteredRankingCliente;
	}

	public void setFilteredRankingCliente(List<RankingCliente> filteredRankingCliente) {
		this.filteredRankingCliente = filteredRankingCliente;
	}
	
	public List<RankingProveedor> getListaRankingProveedor() {
		return listaRankingProveedor;
	}

	public void setListaRankingProveedor(List<RankingProveedor> listaRankingProveedor) {
		this.listaRankingProveedor = listaRankingProveedor;
	}

	public List<RankingProveedor> getFilteredRankingProveedor() {
		return filteredRankingProveedor;
	}

	public void setFilteredRankingProveedor(List<RankingProveedor> filteredRankingProveedor) {
		this.filteredRankingProveedor = filteredRankingProveedor;
	}

	public List<RankingProducto> getListaRankingProducto() {
		return listaRankingProducto;
	}

	public void setListaRankingProducto(List<RankingProducto> listaRankingProducto) {
		this.listaRankingProducto = listaRankingProducto;
	}

	public List<RankingProducto> getFilteredRankingProducto() {
		return filteredRankingProducto;
	}

	public void setFilteredRankingProducto(List<RankingProducto> filteredRankingProducto) {
		this.filteredRankingProducto = filteredRankingProducto;
	}

	public List<ProductoUnidad> getListaProductoUnidads() {
		return listaProductoUnidads;
	}

	public void setListaProductoUnidads(List<ProductoUnidad> listaProductoUnidads) {
		this.listaProductoUnidads = listaProductoUnidads;
	}

	public List<Ganancia> getListaGanancia() {
		return listaGanancia;
	}

	public void setListaGanancia(List<Ganancia> listaGanancia) {
		this.listaGanancia = listaGanancia;
	}

	public List<Ganancia> getFilteredGanancia() {
		return filteredGanancia;
	}

	public void setFilteredGanancia(List<Ganancia> filteredGanancia) {
		this.filteredGanancia = filteredGanancia;
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

	public List<String> getListaIdClientes() {
		return listaIdClientes;
	}

	public void setListaIdClientes(List<String> listaIdClientes) {
		this.listaIdClientes = listaIdClientes;
	}

	public List<String> getListaIdProveedores() {
		return listaIdProveedores;
	}

	public void setListaIdProveedores(List<String> listaIdProveedores) {
		this.listaIdProveedores = listaIdProveedores;
	}

	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Ganancia getGananciaObj() {
		return gananciaObj;
	}

	public void setGananciaObj(Ganancia gananciaObj) {
		this.gananciaObj = gananciaObj;
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

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getNroVenta() {
		return nroVenta;
	}

	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}

	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}

	public int getIdTipoReporte() {
		return idTipoReporte;
	}

	public void setIdTipoReporte(int idTipoReporte) {
		this.idTipoReporte = idTipoReporte;
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

	public int getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public float getGananciaTotal() {
		return gananciaTotal;
	}

	public void setGananciaTotal(float gananciaTotal) {
		this.gananciaTotal = gananciaTotal;
	}

	public float getTotalCaja() {
		return totalCaja;
	}

	public void setTotalCaja(float totalCaja) {
		this.totalCaja = totalCaja;
	}

	public float getTotalccProveedor() {
		return totalccProveedor;
	}

	public void setTotalccProveedor(float totalccProveedor) {
		this.totalccProveedor = totalccProveedor;
	}

	public float getTotalccCliente() {
		return totalccCliente;
	}

	public void setTotalccCliente(float totalccCliente) {
		this.totalccCliente = totalccCliente;
	}

	public float getTotalGastos() {
		return totalGastos;
	}

	public void setTotalGastos(float totalGastos) {
		this.totalGastos = totalGastos;
	}

	public float getTotalStockProductos() {
		return totalStockProductos;
	}

	public void setTotalStockProductos(float totalStockProductos) {
		this.totalStockProductos = totalStockProductos;
	}

	public float getTotalStockProductosUsados() {
		return totalStockProductosUsados;
	}

	public void setTotalStockProductosUsados(float totalStockProductosUsados) {
		this.totalStockProductosUsados = totalStockProductosUsados;
	}

	public float getTotalStockAccesorios() {
		return totalStockAccesorios;
	}

	public void setTotalStockAccesorios(float totalStockAccesorios) {
		this.totalStockAccesorios = totalStockAccesorios;
	}

	public float getTotalActivos() {
		return totalActivos;
	}

	public void setTotalActivos(float totalActivos) {
		this.totalActivos = totalActivos;
	}

	public float getTotalPasivos() {
		return totalPasivos;
	}

	public void setTotalPasivos(float totalPasivos) {
		this.totalPasivos = totalPasivos;
	}

	public float getTotalPatrimonio() {
		return totalPatrimonio;
	}

	public void setTotalPatrimonio(float totalPatrimonio) {
		this.totalPatrimonio = totalPatrimonio;
	}

	public boolean isGananciaVentas() {
		return gananciaVentas;
	}

	public void setGananciaVentas(boolean gananciaVentas) {
		this.gananciaVentas = gananciaVentas;
	}

	public boolean isGananciaProductos() {
		return gananciaProductos;
	}

	public void setGananciaProductos(boolean gananciaProductos) {
		this.gananciaProductos = gananciaProductos;
	}

	public boolean isOnVenta() {
		return onVenta;
	}

	public void setOnVenta(boolean onVenta) {
		this.onVenta = onVenta;
	}

	public boolean isHabilitaPersona() {
		return habilitaPersona;
	}

	public void setHabilitaPersona(boolean habilitaPersona) {
		this.habilitaPersona = habilitaPersona;
	}

	public boolean isEsCliente() {
		return esCliente;
	}

	public void setEsCliente(boolean esCliente) {
		this.esCliente = esCliente;
	}

	public boolean isTablaCliente() {
		return tablaCliente;
	}

	public void setTablaCliente(boolean tablaCliente) {
		this.tablaCliente = tablaCliente;
	}

	public boolean isTablaProveedor() {
		return tablaProveedor;
	}

	public void setTablaProveedor(boolean tablaProveedor) {
		this.tablaProveedor = tablaProveedor;
	}

	public String goRankingCliente(Usuario user){
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		cantidadTotal = 0;
		montoTotal = 0;
		gananciaTotal = 0;
		listaRankingCliente = new ArrayList<RankingCliente>();
		filteredRankingCliente = new ArrayList<RankingCliente>();
		return "rankingCliente";
	}	

	public void buscarRankingCliente(){
		if(fechaDesde != null && fechaHasta != null){
			int mesDesde = fechaDesde.getMonth();
			int mesHasta = fechaHasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				cantidadTotal = 0;
				montoTotal = 0;
				gananciaTotal = 0;
				listaRankingCliente = new ArrayList<RankingCliente>();
				filteredRankingCliente = new ArrayList<RankingCliente>();
				List<RankingCliente> listAux = new ArrayList<RankingCliente>();
				List<Cliente> listaCliente = clienteDAO.getLista(true);
				for (Cliente cliente : listaCliente) {
					RankingCliente rankingCliente = new RankingCliente();
					List<VentasRanking> listVentasAux = new ArrayList<VentasRanking>();
					rankingCliente.setCliente(cliente);
					float monto = 0;
					float costo = 0;
					int cant = 0;
					List<Venta> listaVenta = ventaDAO.getLista(true, cliente, fechaDesde, fechaHasta);				
					for (Venta venta : listaVenta) {
						VentasRanking ventasRanking = new VentasRanking();
						cant = cant + 1;
						monto = monto + venta.getMonto();
						ventasRanking.setClase("Venta");
						ventasRanking.setId(venta.getId());
						ventasRanking.setFecha(venta.getFecha());
						ventasRanking.setTipo(venta.getTipo());
						ventasRanking.setCliente(venta.getCliente().getNombreNegocio());
						ventasRanking.setMonto(venta.getMonto());
						listVentasAux.add(ventasRanking);
						List<VentasDetalle> listDetalles = ventaDetalleDAO.getLista(venta);
						for (VentasDetalle ventasDetalle : listDetalles) {
							if(ventasDetalle.getAccesorio()){
								List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
								for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
									Stock stock = stocksVentasDetalle.getStock();
									int cantDetalle = stocksVentasDetalle.getCantidad();
									float costoStock = stock.getPrecioCompra() * cantDetalle;
									costo = costo + costoStock;
								}
							}else{
								for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
									costo = costo + ventasDetalleUnidad.getPrecioCompra();
								}
							}
						}
					}
					List<VentasCon> listaVentasCon = ventaConsignacionDAO.getLista(cliente, fechaDesde, fechaHasta);
					for (VentasCon ventasCon : listaVentasCon) {
						VentasRanking ventasRanking = new VentasRanking();
						cant = cant + 1;
						monto = monto + ventasCon.getMonto();
						ventasRanking.setClase("Venta de Consignaci�n");
						ventasRanking.setId(ventasCon.getId());
						ventasRanking.setFecha(ventasCon.getFecha());
						ventasRanking.setTipo(ventasCon.getTipo());
						ventasRanking.setCliente(ventasCon.getCliente().getNombreNegocio());
						ventasRanking.setMonto(ventasCon.getMonto());
						listVentasAux.add(ventasRanking);
						List<VentasConsDetalle> listDetalles = ventaConsignacionDetalleDAO.getLista(ventasCon);
						for (VentasConsDetalle ventasDetalle : listDetalles) {
							List<VentasConsDetalleUnidad> listaUnidades = ventaConsignacionDetalleUnidadDAO.getLista(ventasDetalle);
							for (VentasConsDetalleUnidad ventasUnidad : listaUnidades) {
								costo = costo + ventasUnidad.getPrecioCompra();
							}
						}
					}
					float ganancia = monto - costo;
					rankingCliente.setMonto(monto);
					rankingCliente.setGanancia(ganancia);
					rankingCliente.setCantidad(cant);
					Collections.sort(listVentasAux, new Comparator(){
						@Override
						public int compare(Object p1, Object p2) {
							// TODO Auto-generated method stub
							return ((VentasRanking) p2).getFecha()
									.compareTo(((VentasRanking) p1).getFecha());
						}
					});
					rankingCliente.setListaVentas(listVentasAux);
					montoTotal = montoTotal + monto;
					gananciaTotal = gananciaTotal + ganancia;
					cantidadTotal = cantidadTotal + cant;
					if (cant != 0) {
						listAux.add(rankingCliente);
					}
				}
				
				Collections.sort(listAux, new Comparator(){
					@Override
					public int compare(Object p1, Object p2) {
						// TODO Auto-generated method stub
						return new Float(((RankingCliente) p2).getMonto())
								.compareTo(new Float(((RankingCliente) p1).getMonto()));
					}
				});
				listaRankingCliente = listAux;
				filteredRankingCliente = listaRankingCliente;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La diferencia entre ambas Fechas no puede superar los 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas Fechas!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void reporteRankingCliente(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "-");
			parametros.put("hasta", "-");
		}
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		parametros.put("gananciaTotal", formatoMonto.format(gananciaTotal));
		parametros.put("cantidadTotal", cantidadTotal);
		reporte.generar(parametros, listaRankingCliente, "rankingCliente", "inline");
	}
	
	public String goRankingProveedor(Usuario user){
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		montoTotal = 0;
		cantidadTotal = 0;
		listaRankingProveedor = new ArrayList<RankingProveedor>();
		filteredRankingProveedor = new ArrayList<RankingProveedor>();
		return "rankingProveedor";
	}
	
	public void buscarRankingProveedor(){
		if(fechaDesde != null && fechaHasta != null){
			int mesDesde = fechaDesde.getMonth();
			int mesHasta = fechaHasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				montoTotal = 0;
				cantidadTotal = 0;
				listaRankingProveedor = new ArrayList<RankingProveedor>();
				filteredRankingProveedor = new ArrayList<RankingProveedor>();
				List<RankingProveedor> listAux = new ArrayList<RankingProveedor>();
				List<Proveedore> listaProveedor = proveedorDAO.getLista(true);
				for (Proveedore proveedore : listaProveedor) {
					RankingProveedor rankingProveedor = new RankingProveedor();
					float monto = 0;
					int cant = 0;
					List<Compra> listaCompra = compraDAO.getLista(true, proveedore, fechaDesde, fechaHasta);
					for (Compra compra : listaCompra) {
						monto = monto + compra.getMonto();
						cant = cant + 1;
					}
					rankingProveedor.setCantidad(cant);
					rankingProveedor.setListaCompras(listaCompra);
					rankingProveedor.setMonto(monto);
					rankingProveedor.setProveedor(proveedore);
					montoTotal = montoTotal + monto;
					cantidadTotal = cantidadTotal + cant;
					if (cant != 0) {
						listAux.add(rankingProveedor);
					}
				}
				
				Collections.sort(listAux, new Comparator(){
					@Override
					public int compare(Object p1, Object p2) {
						// TODO Auto-generated method stub
						return new Float(((RankingProveedor) p2).getMonto())
								.compareTo(new Float(((RankingProveedor) p1).getMonto()));
					}
				});
				listaRankingProveedor = listAux;
				filteredRankingProveedor = listaRankingProveedor;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La diferencia entre ambas Fechas no puede superar los 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas Fechas!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void reporteRankingProveedor(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "");
			parametros.put("hasta", "");
		}
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		parametros.put("cantidadTotal", cantidadTotal);
		reporte.generar(parametros, filteredRankingProveedor, "rankingProveedor", "inline");
	}
	
	public String goRankingProducto(Usuario user){
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		cantidadTotal = 0;
		montoTotal = 0;
		gananciaTotal = 0;
		listaRankingProducto = new ArrayList<RankingProducto>();
		filteredRankingProducto = new ArrayList<RankingProducto>();
		return "rankingProducto";
	}
	
	public void buscarRankingProducto(){
		if(fechaDesde != null && fechaHasta != null){
			int mesDesde = fechaDesde.getMonth();
			int mesHasta = fechaHasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				cantidadTotal = 0;
				montoTotal = 0;
				gananciaTotal = 0;
				listaRankingProducto = new ArrayList<RankingProducto>();
				filteredRankingProducto = new ArrayList<RankingProducto>();
				List<RankingProducto> listAux = new ArrayList<RankingProducto>();
				List<Producto> listaProducto = productoDAO.getLista(true);
				for (Producto producto : listaProducto) {
					RankingProducto rankingProducto = new RankingProducto();
					float monto = 0;
					float costo = 0;
					int cant = 0;
					List<VentasRanking> listVentas = new ArrayList<VentasRanking>();
					//List<Venta> listaVenta = new ArrayList<Venta>();
					List<VentasDetalle> listaVentaDetalle = ventaDetalleDAO.getLista(producto, true, fechaDesde, fechaHasta);
					for (VentasDetalle ventasDetalle : listaVentaDetalle) {
						VentasRanking ventasRanking = new VentasRanking();
						monto = monto + ventasDetalle.getSubtotal();
						cant = cant + ventasDetalle.getCantidad();
						Venta vent = ventasDetalle.getVenta();
						ventasRanking.setClase("Venta");
						ventasRanking.setId(vent.getId());
						ventasRanking.setFecha(vent.getFecha());
						ventasRanking.setTipo(vent.getTipo());
						if (vent.getCliente() != null) {
							ventasRanking.setCliente(vent.getCliente().getNombreNegocio());
						} else {
							ventasRanking.setCliente(vent.getConsumidorFinal());
						}						
						ventasRanking.setMonto(vent.getMonto());
						//listaVenta.add(ventasDetalle.getVenta());
						listVentas.add(ventasRanking);
						if(ventasDetalle.getAccesorio()){
							List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
							for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
								Stock stock = stocksVentasDetalle.getStock();
								int cantDetalle = stocksVentasDetalle.getCantidad();
								float costoStock = stock.getPrecioCompra() * cantDetalle;
								costo = costo + costoStock;
							}
						}else{
							for(VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)){
								costo = costo + ventasDetalleUnidad.getPrecioCompra();
							}
						}
					}
					List<VentasConsDetalle> listaVentasConsDetalle = ventaConsignacionDetalleDAO.getLista(producto, true, fechaDesde, fechaHasta);
					for (VentasConsDetalle ventasConsDetalle : listaVentasConsDetalle) {
						VentasRanking ventasRanking = new VentasRanking();
						monto = monto + ventasConsDetalle.getSubtotal();
						cant = cant + ventasConsDetalle.getCantidad();
						VentasCon ventCon = ventasConsDetalle.getVentasCon();
						ventasRanking.setClase("Venta de Consignaci�n");
						ventasRanking.setId(ventCon.getId());
						ventasRanking.setFecha(ventCon.getFecha());
						ventasRanking.setTipo(ventCon.getTipo());
						ventasRanking.setCliente(ventCon.getCliente().getNombreNegocio());
						ventasRanking.setMonto(ventCon.getMonto());
						//listaVenta.add(ventasDetalle.getVenta());
						listVentas.add(ventasRanking);
						List<VentasConsDetalleUnidad> listaVentasConsDetalleUnidad = ventaConsignacionDetalleUnidadDAO.getLista(ventasConsDetalle);
						for(VentasConsDetalleUnidad ventasConsDetalleUnidad : listaVentasConsDetalleUnidad){
							costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
						}
					}
					Collections.sort(listVentas, new Comparator(){
						@Override
						public int compare(Object p1, Object p2) {
							// TODO Auto-generated method stub
							return ((VentasRanking) p2).getFecha()
									.compareTo(((VentasRanking) p1).getFecha());
						}
					});
					float ganancia = monto - costo;
					rankingProducto.setCantidad(cant);
					rankingProducto.setCosto(costo);
					rankingProducto.setGanancia(ganancia);					
					rankingProducto.setListaVentas(listVentas);
//					rankingProducto.setListaVentas(listaVenta);
					rankingProducto.setMonto(monto);
					rankingProducto.setProducto(producto);
					cantidadTotal = cantidadTotal + cant;
					montoTotal = montoTotal + monto;
					gananciaTotal = gananciaTotal + ganancia;
					if (cant != 0) {
						listAux.add(rankingProducto);
					}
				}
				
				Collections.sort(listAux, new Comparator(){
					@Override
					public int compare(Object p1, Object p2) {
						// TODO Auto-generated method stub
						return new Integer(((RankingProducto) p2).getCantidad())
								.compareTo(new Integer(((RankingProducto) p1).getCantidad()));
					}
				});
				listaRankingProducto = listAux;
				filteredRankingProducto = listaRankingProducto;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La diferencia entre ambas Fechas no puede superar los 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas Fechas!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void reporteRankingProducto(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "");
			parametros.put("hasta", "");
		}
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		parametros.put("gananciaTotal", formatoMonto.format(gananciaTotal));
		parametros.put("cantidadTotal", cantidadTotal);
		reporte.generar(parametros, filteredRankingProducto, "rankingProducto", "inline");
	}
	
	public String goGanacias(Usuario user){
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		listaGanancia = new ArrayList<Ganancia>();
		filteredGanancia = new ArrayList<Ganancia>();
		listaProductos = new ArrayList<Producto>();
		listaProductos = productoDAO.getLista(true);
		gananciaProductos = false;
		gananciaVentas = false;
		idProducto = 0;
		producto = new Producto();
		return "reporteGanancia";
	}
	
	public void buscarGanancias(){		
		if(fechaDesde != null && fechaHasta != null){
			int mesDesde = fechaDesde.getMonth();
			int mesHasta = fechaHasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if(diferencia <= 3){
				cantidadTotal = 0;
				montoTotal = 0;
				gananciaTotal = 0;
				List<Ganancia> listAux = new ArrayList<Ganancia>();
				if(idProducto == 0){
					gananciaVentas = true;
					gananciaProductos = false;				
					List<Venta> listaVenta = ventaDAO.getLista(true, fechaDesde, fechaHasta);
					for (Venta venta : listaVenta) {
						Ganancia ganancia = new Ganancia();
						float costo = 0;
						List<VentasDetalleRanking> listDetalleAux = new ArrayList<VentasDetalleRanking>();
						List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
						for (VentasDetalle ventasDetalle : listaVentasDetalle) {
							VentasDetalleRanking ventDetalleAux = new VentasDetalleRanking();
							ventDetalleAux.setCantidad(ventasDetalle.getCantidad());
							ventDetalleAux.setProducto(ventasDetalle.getProducto().getNombre());
							ventDetalleAux.setPrecioVenta(ventasDetalle.getPrecioVenta());
							ventDetalleAux.setSubtotal(ventasDetalle.getSubtotal());
							listDetalleAux.add(ventDetalleAux);
							if(ventasDetalle.getAccesorio()){
								List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
								for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
									Stock stock = stocksVentasDetalle.getStock();
									int cantDetalle = stocksVentasDetalle.getCantidad();
									float costoStock = stock.getPrecioCompra() * cantDetalle;
									costo = costo + costoStock;
								}
							}else{
								for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
									costo = costo + ventasDetalleUnidad.getPrecioCompra();
								}
							}
						}
						float fGanancia = venta.getMonto() - costo;
						ganancia.setClase("Venta");
						ganancia.setId(venta.getId());
						ganancia.setFecha(venta.getFecha());
						if (venta.getCliente() != null) {
							ganancia.setCliente(venta.getCliente().getNombreNegocio());
						} else {
							ganancia.setCliente(venta.getConsumidorFinal());
						}
						ganancia.setTipo(venta.getTipo());
						ganancia.setCosto(costo);
						ganancia.setGanancia(fGanancia);
						ganancia.setListaDetallesRanking(listDetalleAux);
						//ganancia.setListaVentasDetalle(listaVentasDetalle);
						ganancia.setMonto(venta.getMonto());
						//ganancia.setVenta(venta);
						cantidadTotal = cantidadTotal + 1;
						montoTotal = montoTotal + venta.getMonto();
						gananciaTotal = gananciaTotal + fGanancia;
						listAux.add(ganancia);
					}
					List<VentasCon> listaVentasCon = ventaConsignacionDAO.getLista(true, fechaDesde, fechaHasta);
					for (VentasCon ventasCon : listaVentasCon) {
						Ganancia ganancia = new Ganancia();
						float costo = 0;
						List<VentasDetalleRanking> listDetalleAux = new ArrayList<VentasDetalleRanking>();
						List<VentasConsDetalle> listaVentasDetalle = ventaConsignacionDetalleDAO.getLista(ventasCon);
						for (VentasConsDetalle ventasDetalle : listaVentasDetalle) {
							VentasDetalleRanking ventDetalleAux = new VentasDetalleRanking();
							ventDetalleAux.setCantidad(ventasDetalle.getCantidad());
							ventDetalleAux.setProducto(ventasDetalle.getProducto().getNombre());
							ventDetalleAux.setPrecioVenta(ventasDetalle.getPrecioVenta());
							ventDetalleAux.setSubtotal(ventasDetalle.getSubtotal());
							listDetalleAux.add(ventDetalleAux);
							
							List<VentasConsDetalleUnidad> listVentsConsDetalleUnidad = ventaConsignacionDetalleUnidadDAO.getLista(ventasDetalle);
							for (VentasConsDetalleUnidad ventasDetalleUnidad : listVentsConsDetalleUnidad) {
								costo = costo + ventasDetalleUnidad.getPrecioCompra();
							}
							
						}
						float fGanancia = ventasCon.getMonto() - costo;
						ganancia.setClase("Venta de Consignaci�n");
						ganancia.setId(ventasCon.getId());
						ganancia.setFecha(ventasCon.getFecha());
						ganancia.setCliente(ventasCon.getCliente().getNombreNegocio());
						ganancia.setTipo(ventasCon.getTipo());
						ganancia.setCosto(costo);
						ganancia.setGanancia(fGanancia);
						ganancia.setListaDetallesRanking(listDetalleAux);
						//ganancia.setListaVentasDetalle(listaVentasDetalle);
						ganancia.setMonto(ventasCon.getMonto());
						//ganancia.setVenta(venta);
						cantidadTotal = cantidadTotal + 1;
						montoTotal = montoTotal + ventasCon.getMonto();
						gananciaTotal = gananciaTotal + fGanancia;
						listAux.add(ganancia);
					}
				}else{
					gananciaVentas = false;
					gananciaProductos = true;
					producto = new Producto();
					producto = productoDAO.get(idProducto);
					List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(producto, true, fechaDesde, fechaHasta);
					for (VentasDetalle ventasDetalle : listaVentasDetalle) {
						Ganancia ganancia = new Ganancia();
						float costo = 0;
						if(ventasDetalle.getAccesorio()){
							List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
							for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
								Stock stock = stocksVentasDetalle.getStock();
								int cantDetalle = stocksVentasDetalle.getCantidad();
								float costoStock = stock.getPrecioCompra() * cantDetalle;
								costo = costo + costoStock;
							}
						}else{
							for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
								costo = costo + ventasDetalleUnidad.getPrecioCompra();
							}
						}
						float fGanancia = ventasDetalle.getSubtotal() - costo;
						Venta venta = ventasDetalle.getVenta();
						//ganancia.setVenta(venta);
						ganancia.setClase("Venta");
						ganancia.setId(venta.getId());
						ganancia.setFecha(venta.getFecha());
						if (venta.getCliente() != null) {
							ganancia.setCliente(venta.getCliente().getNombreNegocio());
						} else {
							ganancia.setCliente(venta.getConsumidorFinal());
						}
						ganancia.setTipo(venta.getTipo());
						ganancia.setCantidad(ventasDetalle.getCantidad());
						ganancia.setCosto(costo);
						ganancia.setGanancia(fGanancia);
						ganancia.setMonto(ventasDetalle.getSubtotal());
						//ganancia.setProducto(producto);
						cantidadTotal = cantidadTotal + 1;
						montoTotal = montoTotal + ventasDetalle.getSubtotal();
						gananciaTotal = gananciaTotal + fGanancia;
						listAux.add(ganancia);
					}
					List<VentasConsDetalle> listaVentasConsDetalle = ventaConsignacionDetalleDAO.getLista(producto, true, fechaDesde, fechaHasta);
					for (VentasConsDetalle ventasConDetalle : listaVentasConsDetalle) {
						Ganancia ganancia = new Ganancia();
						float costo = 0;
						List<VentasConsDetalleUnidad> listaUnidades = ventaConsignacionDetalleUnidadDAO.getLista(ventasConDetalle);
						for (VentasConsDetalleUnidad ventasDetalleUnidad : listaUnidades) {
							costo = costo + ventasDetalleUnidad.getPrecioCompra();
						}
						
						float fGanancia = ventasConDetalle.getSubtotal() - costo;
						VentasCon ventasCon = ventasConDetalle.getVentasCon();
						//ganancia.setVenta(venta);
						ganancia.setClase("Venta de Consignaci�n");
						ganancia.setId(ventasCon.getId());
						ganancia.setFecha(ventasCon.getFecha());
						ganancia.setCliente(ventasCon.getCliente().getNombreNegocio());
						ganancia.setTipo(ventasCon.getTipo());
						ganancia.setCantidad(ventasConDetalle.getCantidad());
						ganancia.setCosto(costo);
						ganancia.setGanancia(fGanancia);
						ganancia.setMonto(ventasConDetalle.getSubtotal());
						//ganancia.setProducto(producto);
						cantidadTotal = cantidadTotal + 1;
						montoTotal = montoTotal + ventasConDetalle.getSubtotal();
						gananciaTotal = gananciaTotal + fGanancia;
						listAux.add(ganancia);
					}
				}
				listaGanancia = new ArrayList<Ganancia>();
				filteredGanancia = new ArrayList<Ganancia>();
				listaGanancia = listAux;
				filteredGanancia = listaGanancia;
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La diferencia entre ambas Fechas no puede superar los 3 meses por condiciones de performance!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas Fechas!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public String goGanaciasVenta(Usuario user){
		usuario = new Usuario();
		usuario = user;
		fechaDesde = null;
		fechaHasta = null;
		nroVenta = 0;
		montoTotal = 0;
		listaGanancia = new ArrayList<Ganancia>();
		filteredGanancia = new ArrayList<Ganancia>();
		gananciaProductos = false;
		gananciaVentas = false;
		onVenta = true;
		return "reporteGananciaVenta";
	}
	
	public void buscarGananciaVenta() {
		if (nroVenta != 0) {
			gananciaVentas = false;
			gananciaProductos = true;
			float costo = 0;
			gananciaObj = new Ganancia();
			List<GananciaDetalle> listaGananciaDetalle = new ArrayList<GananciaDetalle>();
			if (onVenta) {
				Venta venta = ventaDAO.get(nroVenta);
				List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
				for (VentasDetalle ventasDetalle : listaVentasDetalle) {
					GananciaDetalle gananciaDetalle = new GananciaDetalle();
					gananciaDetalle.setVentaDetalle(ventasDetalle);
					gananciaDetalle.setProducto(ventasDetalle.getProducto());
					gananciaDetalle.setCantidad(ventasDetalle.getCantidad());
					gananciaDetalle.setMonto(ventasDetalle.getSubtotal());
					float costoDetalle = 0;
					if (ventasDetalle.getAccesorio()) {
						List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
						for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
							Stock stock = stocksVentasDetalle.getStock();
							int cantDetalle = stocksVentasDetalle.getCantidad();
							float costoStock = stock.getPrecioCompra() * cantDetalle;
							costo = costo + costoStock;
							costoDetalle = costoDetalle + costoStock;
						}
					} else {
						for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
							costo = costo + ventasDetalleUnidad.getPrecioCompra();
							costoDetalle = costoDetalle + ventasDetalleUnidad.getPrecioCompra();
						}
					}
					gananciaDetalle.setCosto(costoDetalle);
					gananciaDetalle.setGanancia(ventasDetalle.getSubtotal() - costoDetalle);
					listaGananciaDetalle.add(gananciaDetalle);
				}
				float fGanancia = venta.getMonto() - costo;
				gananciaObj.setClase("Venta");
				gananciaObj.setId(venta.getId());
				gananciaObj.setFecha(venta.getFecha());
				if (venta.getCliente() != null) {
					gananciaObj.setCliente(venta.getCliente().getNombreNegocio());
				} else {
					gananciaObj.setCliente(venta.getConsumidorFinal());
				}
				gananciaObj.setTipo(venta.getTipo());
				gananciaObj.setCosto(costo);
				gananciaObj.setGanancia(fGanancia);
				//gananciaObj.setListaVentasDetalle(listaVentasDetalle);
				gananciaObj.setListaGananciaDetalle(listaGananciaDetalle);
				gananciaObj.setMonto(venta.getMonto());
				//gananciaObj.setVenta(venta);
				montoTotal = venta.getMonto();
				gananciaTotal = fGanancia;
			} else {
				VentasCon ventasCon = ventaConsignacionDAO.get(nroVenta);
				List<VentasConsDetalle> listaVentasConsDetalle = ventaConsignacionDetalleDAO.getLista(ventasCon);
				for (VentasConsDetalle ventasConsDetalle : listaVentasConsDetalle) {
					GananciaDetalle gananciaDetalle = new GananciaDetalle();
					
					gananciaDetalle.setProducto(ventasConsDetalle.getProducto());
					gananciaDetalle.setCantidad(ventasConsDetalle.getCantidad());
					gananciaDetalle.setMonto(ventasConsDetalle.getSubtotal());
					float costoDetalle = 0;
					List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventasConsDetalle);
					for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
						costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
						costoDetalle = costoDetalle + ventasConsDetalleUnidad.getPrecioCompra();
					}
					gananciaDetalle.setCosto(costoDetalle);
					gananciaDetalle.setGanancia(ventasConsDetalle.getSubtotal() - costoDetalle);
					listaGananciaDetalle.add(gananciaDetalle);
				}
				float fGanancia = ventasCon.getMonto() - costo;
				gananciaObj.setClase("Venta de Consignaci�n");
				gananciaObj.setId(ventasCon.getId());
				gananciaObj.setFecha(ventasCon.getFecha());
				gananciaObj.setCliente(ventasCon.getCliente().getNombreNegocio());
				gananciaObj.setTipo(ventasCon.getTipo());
				gananciaObj.setCosto(costo);
				gananciaObj.setGanancia(fGanancia);
				//gananciaObj.setListaVentasDetalle(listaVentasDetalle);
				gananciaObj.setListaGananciaDetalle(listaGananciaDetalle);
				gananciaObj.setMonto(ventasCon.getMonto());
				montoTotal = ventasCon.getMonto();
				gananciaTotal = fGanancia;
			}			
		}else{
			if (fechaDesde != null && fechaHasta != null) {
				gananciaVentas = true;
				gananciaProductos = false;
				int mesDesde = fechaDesde.getMonth();
				int mesHasta = fechaHasta.getMonth();
				int diferencia = mesHasta - mesDesde;
				if (diferencia <= 3) {
					cantidadTotal = 0;
					montoTotal = 0;
					gananciaTotal = 0;
					List<Ganancia> listAux = new ArrayList<Ganancia>();		
					if (onVenta) {
						encabezado = "ventas";
						List<Venta> listaVenta = ventaDAO.getLista(true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					} else {
						encabezado = "ventas de consignaci�n";
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getLista(true, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalle> listaVentasConsDetalle = ventaConsignacionDetalleDAO.getLista(ventCon);
							for (VentasConsDetalle ventasConsDetalle : listaVentasConsDetalle) {
								List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
								for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
									costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
								}							
							}
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					Collections.sort(listAux, new Comparator(){
						@Override
						public int compare(Object p1, Object p2) {
							// TODO Auto-generated method stub
							return ((Ganancia) p2).getFecha()
									.compareTo(((Ganancia) p1).getFecha());
						}
					});
					listaGanancia = new ArrayList<Ganancia>();
					filteredGanancia = new ArrayList<Ganancia>();
					listaGanancia = listAux;
					filteredGanancia = listaGanancia;
				}else{
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La diferencia entre ambas Fechas no puede superar los 3 meses por condiciones de performance!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas Fechas!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public void verGananciaVenta(Ganancia ganancia) {
		gananciaProductos = true;
		String claas = ganancia.getClase();
		gananciaObj = new Ganancia();
		gananciaObj = ganancia;
		if (claas.equals("Venta")) {
			Venta venta = ventaDAO.get(ganancia.getId());
			float costo = 0;
			List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
			List<GananciaDetalle> listaGananciaDetalle = new ArrayList<GananciaDetalle>();
			for (VentasDetalle ventasDetalle : listaVentasDetalle) {
				GananciaDetalle gananciaDetalle = new GananciaDetalle();
				gananciaDetalle.setVentaDetalle(ventasDetalle);
				gananciaDetalle.setProducto(ventasDetalle.getProducto());
				gananciaDetalle.setCantidad(ventasDetalle.getCantidad());
				gananciaDetalle.setMonto(ventasDetalle.getSubtotal());
				float costoDetalle = 0;
				if (ventasDetalle.getAccesorio()) {
					List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
					for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
						Stock stock = stocksVentasDetalle.getStock();
						int cantDetalle = stocksVentasDetalle.getCantidad();
						float costoStock = stock.getPrecioCompra() * cantDetalle;
						costo = costo + costoStock;
						costoDetalle = costoDetalle + costoStock;
					}
				} else {
					for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
						costo = costo + ventasDetalleUnidad.getPrecioCompra();
						costoDetalle = costoDetalle + ventasDetalleUnidad.getPrecioCompra();
					}
				}
				gananciaDetalle.setCosto(costoDetalle);
				gananciaDetalle.setGanancia(ventasDetalle.getSubtotal() - costoDetalle);
				listaGananciaDetalle.add(gananciaDetalle);
			}
			float fGanancia = venta.getMonto() - costo;
			gananciaObj.setClase("Venta");
			gananciaObj.setId(venta.getId());
			gananciaObj.setFecha(venta.getFecha());
			if (venta.getCliente() != null) {
				gananciaObj.setCliente(venta.getCliente().getNombreNegocio());
			} else {
				gananciaObj.setCliente(venta.getConsumidorFinal());
			}
			gananciaObj.setTipo(venta.getTipo());
			gananciaObj.setCosto(costo);
			gananciaObj.setGanancia(fGanancia);
			//gananciaObj.setListaVentasDetalle(listaVentasDetalle);
			gananciaObj.setListaGananciaDetalle(listaGananciaDetalle);
			gananciaObj.setMonto(venta.getMonto());
			//gananciaObj.setVenta(venta);
			//montoTotal = venta.getMonto();
			//gananciaTotal = fGanancia;
		} else {
			VentasCon ventasCon = ventaConsignacionDAO.get(ganancia.getId());
			float costo = 0;
			List<VentasConsDetalle> listaVentasConsDetalle = ventaConsignacionDetalleDAO.getLista(ventasCon);
			List<GananciaDetalle> listaGananciaDetalle = new ArrayList<GananciaDetalle>();
			for (VentasConsDetalle ventasConsDetalle : listaVentasConsDetalle) {
				GananciaDetalle gananciaDetalle = new GananciaDetalle();
				gananciaDetalle.setProducto(ventasConsDetalle.getProducto());
				gananciaDetalle.setCantidad(ventasConsDetalle.getCantidad());
				gananciaDetalle.setMonto(ventasConsDetalle.getSubtotal());
				float costoDetalle = 0;
				List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventasCon);
				for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
					costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
					costoDetalle = costoDetalle + ventasConsDetalleUnidad.getPrecioCompra();
				}
				gananciaDetalle.setCosto(costoDetalle);
				gananciaDetalle.setGanancia(ventasConsDetalle.getSubtotal() - costoDetalle);
				listaGananciaDetalle.add(gananciaDetalle);
			}
			float fGanancia = ventasCon.getMonto() - costo;
			gananciaObj.setClase("Venta de Consignaci�n");
			gananciaObj.setId(ventasCon.getId());
			gananciaObj.setFecha(ventasCon.getFecha());
			gananciaObj.setCliente(ventasCon.getCliente().getNombreNegocio());
			gananciaObj.setTipo(ventasCon.getTipo());
			gananciaObj.setCosto(costo);
			gananciaObj.setGanancia(fGanancia);
			//gananciaObj.setListaVentasDetalle(listaVentasDetalle);
			gananciaObj.setListaGananciaDetalle(listaGananciaDetalle);
			gananciaObj.setMonto(ventasCon.getMonto());
			//gananciaObj.setVenta(venta);
			//montoTotal = venta.getMonto();
			//gananciaTotal = fGanancia;
		}	
	}
	
	public void reporteGanancia(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		if(fechaDesde != null && fechaHasta != null){
			parametros.put("desde", formatoFecha.format(fechaDesde));
			parametros.put("hasta", formatoFecha.format(fechaHasta));
		}else{
			parametros.put("desde", "");
			parametros.put("hasta", "");
		}
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		parametros.put("gananciaTotal", formatoMonto.format(gananciaTotal));
		parametros.put("cantidadTotal", cantidadTotal);
		String nombre = "gananciaVenta";
		if(idProducto != 0){
			Producto prod = productoDAO.get(idProducto);
			parametros.put("producto", prod.getNombre());
			nombre = "gananciaProducto";
		}
		reporte.generar(parametros, listaGanancia, nombre, "inline");
	}
	
	public void gananciaDeVenta() {
		Reporte reporte = new Reporte();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("clase", gananciaObj.getClase());
		parametros.put("nroVenta", Integer.toString(gananciaObj.getId()));
		parametros.put("cliente", gananciaObj.getCliente());
		parametros.put("fecha", gananciaObj.getFechaString());
		parametros.put("tipoVenta", gananciaObj.getTipo());
		parametros.put("montoTotal", gananciaObj.getMontoString());
		parametros.put("costoTotal", gananciaObj.getCostoString());
		parametros.put("gananciaTotal", gananciaObj.getGananciaString());
		reporte.generar(parametros, gananciaObj.getListaGananciaDetalle(), "gananciaDeVenta", "inline");
	}
	
	public String goPatrimonio(Usuario user){
		usuario = new Usuario();
		usuario = user;
		totalActivos = 0;
		totalCaja = 0;
		totalccCliente = 0;
		totalccProveedor = 0;
		totalGastos = 0;
		totalPasivos = 0;
		totalPatrimonio = 0;
		totalStockProductos = 0;
		totalStockProductosUsados = 0;
		totalStockAccesorios = 0;
		List<Proveedore> listaProveedores = proveedorDAO.getLista(true);
		for (Proveedore proveedore : listaProveedores) {
			totalccProveedor = totalccProveedor + getSaldoProveedor(proveedore);
		}
		List<Cliente> listaClientes = clienteDAO.getLista(true);
		for (Cliente cliente : listaClientes) {
//			System.out.println("Cliente " + cliente.getNombreNegocio());
//			System.out.println("Saldo en cliente " + cliente.getSaldo());
//			System.out.println("Saldo metodo " + getSaldoCliente(cliente));	
//			System.out.println("Total antes cc " + totalccCliente);
			totalccCliente = totalccCliente + getSaldoCliente(cliente);
//			System.out.println("Total cc " + totalccCliente);
//			System.out.println(" ");
		}
		Rubro rub = new Rubro();
		rub.setId(1);
		List<UnidadMovil> listaUnidadMovils = unidadMovilDAO.getListaEnStock(true, true, false, rub);
		System.out.println("size " + listaUnidadMovils.size());
		for (UnidadMovil unidadMovil : listaUnidadMovils) {
//			System.out.println("Movil " + unidadMovil.getProducto().getNombre());
//			System.out.println("Imei " + unidadMovil.getNroImei());
//			System.out.println("Precio Compra " + unidadMovil.getPrecioCompra());
//			System.out.println("Total hasta el momento " + totalStockProductos);
			totalStockProductos = totalStockProductos + unidadMovil.getPrecioCompra();
		}
		List<Stock> listaStock = stockDAO.getListaEnStock(true);
		for (Stock stock : listaStock) {
			float stockPrecio = stock.getPrecioCompra() * stock.getCantidad();
			totalStockAccesorios = totalStockAccesorios + stockPrecio;
		}
		List<Caja> listaCaja = cajaDAO.getListaSinOrden();
		for (Caja caja : listaCaja) {
			totalCaja = caja.getSaldo();
		}
		List<Gasto> listaGastos = gastoDAO.getLista(true);
		for (Gasto gasto : listaGastos) {
			totalGastos = totalGastos + gasto.getMonto();
		}
//		totalCaja = 0;
		totalActivos = totalccCliente + totalStockProductos + totalStockAccesorios;
		totalPasivos = totalccProveedor + totalGastos;
		totalPatrimonio = totalActivos - totalPasivos;
		return "reportePatrimonio";
	}
	
	public float getSaldoCliente(Cliente cli) {
		List<CuentasCorrientesCliente> listCuentasCorrientes = cuentaCorrienteDAO.getLista(cli);
//		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		float saldo = 0;		
		if (!listCuentasCorrientes.isEmpty()) {
			CuentasCorrientesCliente ccCliente = listCuentasCorrientes.get(0);
			saldo = ccCliente.getSaldo();
		}
//		String saldoCli = formatoMonto.format(saldo);
		return saldo;
	}
	
	public float getSaldoProveedor(Proveedore prov) {
		List<CuentasCorrientesProveedore> listCuentasCorrientes = cuentaCorrienteDAO.getListaProveedor(prov);
//		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		float saldo = 0;		
		if (!listCuentasCorrientes.isEmpty()) {
			CuentasCorrientesProveedore ccProveedor = listCuentasCorrientes.get(0);
			saldo = ccProveedor.getSaldo();
		}
//		String saldoProv = formatoMonto.format(saldo);
		return saldo;
	}
	
	public void reportePatrimonio(){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("caja", formatoMonto.format(totalCaja));
		parametros.put("activos", formatoMonto.format(totalActivos));
		parametros.put("ccCliente", formatoMonto.format(totalccCliente));
		parametros.put("ccProveedor", formatoMonto.format(totalccProveedor));
		parametros.put("gastos", formatoMonto.format(totalGastos));
		parametros.put("pasivos", formatoMonto.format(totalPasivos));
		parametros.put("patrimonio", formatoMonto.format(totalPatrimonio));
		parametros.put("productos", formatoMonto.format(totalStockProductos));
		parametros.put("accesorios", formatoMonto.format(totalStockAccesorios));
//		parametros.put("usados", formatoMonto.format(totalStockProductosUsados));
		parametros.put("fecha", formatoFecha.format(new Date()));
		List<String> lista = new ArrayList<String>();
		lista.add(" ");
		reporte.generar(parametros, lista, "patrimonio", "inline");
	}
	
	public String goReporteDinamico(Usuario user) {
		try {
			fechaDesde = null;
			fechaHasta = null;
			idTipoMovimiento = 0;
			idProducto = 0;
			idTipoReporte = 0;
			habilitaPersona = false;
			tablaCliente = false;
			tablaProveedor = false;
			gananciaProductos = false;
			listaIdClientes = new ArrayList<String>();
			listaIdProveedores = new ArrayList<String>();
			listaProductos = new ArrayList<Producto>();
			listaClientes = new ArrayList<Cliente>();
			listaProveedores = new ArrayList<Proveedore>();
			
			listaProductos = productoDAO.getLista(true);
			listaClientes = clienteDAO.getLista(true);
			listaProveedores = proveedorDAO.getLista(true);
			return "reporteDinamico";
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurri� un error al cargar el formulario! Error: " 
		+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public void onChangeTipoMovimiento() {
		habilitaPersona = false;
		tablaCliente = false;
		tablaProveedor = false;
		gananciaProductos = false;
		listaIdClientes = new ArrayList<String>();
		listaIdProveedores = new ArrayList<String>();
		if (idTipoMovimiento == 1) {
			habilitaPersona = true;
			esCliente = false;
		}
		if (idTipoMovimiento == 2 || idTipoMovimiento == 3) {
			habilitaPersona = true;
			esCliente = true;
		}
	}
	
	public void buscarDinamico() {
		try {
			listaGanancia = new ArrayList<Ganancia>();
			listaCompras = new ArrayList<Compra>();
			tablaCliente = false;
			tablaProveedor = false;
			gananciaProductos = false;
			if (idTipoMovimiento == 1) {//Compras
				if (idProducto == 0) {//Todos los productos
					listaCompras = buscarPorProveedorTodosProductos(listaIdProveedores, idTipoReporte, fechaDesde, fechaHasta);
				} else {//Producto en particular
					listaCompras = buscarPorProveedorProducto(idProducto, listaIdProveedores, idTipoReporte, fechaDesde, fechaHasta);
				}
				tablaCliente = false;
				tablaProveedor = true;
				gananciaProductos = false;
			}
			if (idTipoMovimiento == 2) {//Ventas
				if (idProducto == 0) {//Todos los productos
					listaGanancia = buscarVentasPorClienteTodosProductos(listaIdClientes, idTipoReporte, fechaDesde, fechaHasta);					
				} else {//Producto seleccionado
					listaGanancia = buscarVentasPorClienteProducto(idProducto, listaIdClientes, idTipoReporte, fechaDesde, fechaHasta);					
				}
				tablaCliente = true;
				tablaProveedor = false;
				gananciaProductos = false;
			}
			if (idTipoMovimiento == 3) {//VentasConsignacion
				if (idProducto == 0) {//Todos los productos
					listaGanancia = buscarVentasConsigPorClienteTodosProductos(listaIdClientes, idTipoReporte, fechaDesde, fechaHasta);					
				} else {//Producto seleccionado
					listaGanancia = buscarVentasConsigPorClienteProducto(idProducto, listaIdClientes, idTipoReporte, fechaDesde, fechaHasta);					
				}
				tablaCliente = true;
				tablaProveedor = false;
				gananciaProductos = false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! "
					+ "Intentelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public List<Compra> buscarPorProveedorTodosProductos(List<String> listProvs, int idReporte, Date desde, Date hasta) {
		List<Compra> listaCompra = new ArrayList<Compra>();
		if (desde != null && hasta != null) {
			int mesDesde = desde.getMonth();
			int mesHasta = hasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				if (listProvs.isEmpty()) {//Todos los Proveedores
					if (idReporte == 0) {//Ninguno
						montoTotal = 0;
						cantidadTotal = 0;
						listaCompra = compraDAO.getListaOrderFecha(true, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					} 
					if (idReporte == 1) {//Monto
						montoTotal = 0;
						cantidadTotal = 0;
						listaCompra = compraDAO.getListaOrderMonto(true, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					}
				} else {//Proveedores en Particular
					if (idReporte == 0) {//Ninguno
						montoTotal = 0;
						cantidadTotal = 0;
						List<Proveedore> listaP = new ArrayList<Proveedore>();
						for (String string : listProvs) {
							Proveedore prov = proveedorDAO.get(Integer.parseInt(string));
							listaP.add(prov);
						}
						listaCompra = compraDAO.getListaOrderFecha(true, listaP, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					} 
					if (idReporte == 1) {//Monto
						montoTotal = 0;
						cantidadTotal = 0;
						List<Proveedore> listaP = new ArrayList<Proveedore>();
						for (String string : listProvs) {
							Proveedore prov = proveedorDAO.get(Integer.parseInt(string));
							listaP.add(prov);
						}
						listaCompra = compraDAO.getListaOrderMonto(true, listaP, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					}
				}
				return listaCompra;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El rango entre fechas no "
						+ "puede ser mayor a 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return listaCompra;
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas fechas "
					+ "con un rango no mayor a 3 meses!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return listaCompra;
		}
	}
	
	public List<Compra> buscarPorProveedorProducto(int idProd, List<String> listProvs, int idReporte, Date desde, Date hasta) {
		List<Compra> listaCompra = new ArrayList<Compra>();
		if (desde != null && hasta != null) {
			Producto prod = productoDAO.get(idProd);
			int mesDesde = desde.getMonth();
			int mesHasta = hasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {				
				if (listProvs.isEmpty()) {//Todos los Proveedores
					if (idReporte == 0) {//Ninguno
						montoTotal = 0;
						cantidadTotal = 0;
						listaCompra = compraDAO.getListaOrderFecha(prod, true, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					} else {//Monto
						montoTotal = 0;
						cantidadTotal = 0;
						listaCompra = compraDAO.getListaOrderMonto(prod, true, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					}
				} else {//Proveedores en Particular
					if (idReporte == 0) {//Ninguno
						montoTotal = 0;
						cantidadTotal = 0;
						List<Proveedore> listaP = new ArrayList<Proveedore>();
						for (String string : listProvs) {
							Proveedore prov = proveedorDAO.get(Integer.parseInt(string));
							listaP.add(prov);
						}
						listaCompra = compraDAO.getListaOrderFecha(prod, listaP, true, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					} 
					if (idReporte == 1) {//Monto
						montoTotal = 0;
						cantidadTotal = 0;
						List<Proveedore> listaP = new ArrayList<Proveedore>();
						for (String string : listProvs) {
							Proveedore prov = proveedorDAO.get(Integer.parseInt(string));
							listaP.add(prov);
						}
						listaCompra = compraDAO.getListaOrderMonto(prod, listaP, true, fechaDesde, fechaHasta);
						for (Compra compra : listaCompra) {
							montoTotal = montoTotal + compra.getMonto();
							cantidadTotal = cantidadTotal + 1;
						}
					}
				}
				return listaCompra;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El rango entre fechas no "
						+ "puede ser mayor a 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return listaCompra;
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas fechas "
					+ "con un rango no mayor a 3 meses!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return listaCompra;
		}
	}
	
	public List<Ganancia> buscarVentasPorClienteTodosProductos(List<String> listClients, int idReporte, Date desde, Date hasta) {
		if (desde != null && hasta != null) {
			int mesDesde = desde.getMonth();
			int mesHasta = hasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				List<Ganancia> listAux = new ArrayList<Ganancia>();
				if (listClients.isEmpty()) {//Todos los Clientes
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();			
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();			
						List<Venta> listaVenta = ventaDAO.getListaOrderMonto(true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 2) {//Ganancia
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();			
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				} else {//Clientes en Particular
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(true, listaC, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<Venta> listaVenta = ventaDAO.getListaOrderMonto(true, listaC, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
					}
					if (idReporte == 2) {//Ganancia
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(true, listaC, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				}
				return listAux;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El rango entre fechas no "
						+ "puede ser mayor a 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				List<Ganancia> lista = new ArrayList<Ganancia>();
				return lista;
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas fechas "
					+ "con un rango no mayor a 3 meses!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			List<Ganancia> lista = new ArrayList<Ganancia>();
			return lista;
		}
	}
	
	public List<Ganancia> buscarVentasPorClienteProducto(int idProd, List<String> listClients, int idReporte, Date desde, Date hasta) {
		if (desde != null && hasta != null) {
			Producto prod = productoDAO.get(idProd);
			int mesDesde = desde.getMonth();
			int mesHasta = hasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				List<Ganancia> listAux = new ArrayList<Ganancia>();
				if (listClients.isEmpty()) {//Todos los Clientes
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();			
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(prod, true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();			
						List<Venta> listaVenta = ventaDAO.getListaOrderMonto(prod, true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 2) {//Ganancia
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();			
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(prod, true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				} else {//Clientes en Particular
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(prod, listaC, true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<Venta> listaVenta = ventaDAO.getListaOrderMonto(prod, listaC, true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
					}
					if (idReporte == 2) {//Ganancia
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<Venta> listaVenta = ventaDAO.getListaOrderFecha(prod, listaC, true, fechaDesde, fechaHasta);
						for (Venta venta : listaVenta) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasDetalle> listaVentasDetalle = ventaDetalleDAO.getLista(venta);
							for (VentasDetalle ventasDetalle : listaVentasDetalle) {
								if(ventasDetalle.getAccesorio()){
									List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
									for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
										Stock stock = stocksVentasDetalle.getStock();
										int cantDetalle = stocksVentasDetalle.getCantidad();
										float costoStock = stock.getPrecioCompra() * cantDetalle;
										costo = costo + costoStock;
									}
								}else{
									for (VentasDetalleUnidad ventasDetalleUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)) {
										costo = costo + ventasDetalleUnidad.getPrecioCompra();
									}
								}
							}
							float fGanancia = venta.getMonto() - costo;
							ganancia.setClase("Venta");
							ganancia.setId(venta.getId());
							ganancia.setFecha(venta.getFecha());
							if (venta.getCliente() != null) {
								ganancia.setCliente(venta.getCliente().getNombreNegocio());
							} else {
								ganancia.setCliente(venta.getConsumidorFinal());
							}
							ganancia.setTipo(venta.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(venta.getMonto());
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + venta.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);							
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				}
				return listAux;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El rango entre fechas no "
						+ "puede ser mayor a 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				List<Ganancia> lista = new ArrayList<Ganancia>();
				return lista;
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas fechas "
					+ "con un rango no mayor a 3 meses!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			List<Ganancia> lista = new ArrayList<Ganancia>();
			return lista;
		}
	}
	
	public List<Ganancia> buscarVentasConsigPorClienteTodosProductos(List<String> listClients, int idReporte, Date desde, Date hasta) {
		if (desde != null && hasta != null) {
			int mesDesde = desde.getMonth();
			int mesHasta = hasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				List<Ganancia> listAux = new ArrayList<Ganancia>();
				if (listClients.isEmpty()) {//Todos los Clientes
					if (idReporte == 0) {//Fecha						
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();						
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();						
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderMonto(true, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 2) {//Ganancia
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();						
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				} else {//Clientes en Particular
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, listaC, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderMonto(true, listaC, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 2) {//Ganancias
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, listaC, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				}
				return listAux;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El rango entre fechas no "
						+ "puede ser mayor a 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				List<Ganancia> lista = new ArrayList<Ganancia>();
				return lista;
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas fechas "
					+ "con un rango no mayor a 3 meses!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			List<Ganancia> lista = new ArrayList<Ganancia>();
			return lista;
		}
	}
	
	public List<Ganancia> buscarVentasConsigPorClienteProducto(int idProd, List<String> listClients, int idReporte, Date desde, Date hasta) {
		if (desde != null && hasta != null) {
			Producto prod = productoDAO.get(idProd);
			int mesDesde = desde.getMonth();
			int mesHasta = hasta.getMonth();
			int diferencia = mesHasta - mesDesde;
			if (diferencia <= 3) {
				List<Ganancia> listAux = new ArrayList<Ganancia>();
				if (listClients.isEmpty()) {//Todos los Clientes
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, prod, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderMonto(true, prod, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 2) {//Ganancias
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
//						List<Ganancia> listAux = new ArrayList<Ganancia>();
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, prod, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});
					}
				} else {//Proveedores en Particular
					if (idReporte == 0) {//Fecha
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, listaC, prod, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 1) {//Monto
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderMonto(true, listaC, prod, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
					}
					if (idReporte == 2) {//Ganancias
						cantidadTotal = 0;
						montoTotal = 0;
						gananciaTotal = 0;
						List<Cliente> listaC = new ArrayList<Cliente>();
						for (String string : listClients) {
							Cliente cli = clienteDAO.get(Integer.parseInt(string));
							listaC.add(cli);
						}
						List<VentasCon> listaVentasCon = ventaConsignacionDAO.getListaOrderFecha(true, listaC, prod, fechaDesde, fechaHasta);
						for (VentasCon ventCon : listaVentasCon) {
							Ganancia ganancia = new Ganancia();
							float costo = 0;
							List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventCon);
							for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listaUnidads) {
								costo = costo + ventasConsDetalleUnidad.getPrecioCompra();
							}							
							float fGanancia = ventCon.getMonto() - costo;
							ganancia.setClase("Venta de Consignaci�n");
							ganancia.setId(ventCon.getId());
							ganancia.setFecha(ventCon.getFecha());
							ganancia.setCliente(ventCon.getCliente().getNombreNegocio());
							ganancia.setTipo(ventCon.getTipo());
							ganancia.setCosto(costo);
							ganancia.setGanancia(fGanancia);
							//ganancia.setListaVentasDetalle(listaVentasDetalle);
							ganancia.setMonto(ventCon.getMonto());						
							cantidadTotal = cantidadTotal + 1;
							montoTotal = montoTotal + ventCon.getMonto();
							gananciaTotal = gananciaTotal + fGanancia;
							listAux.add(ganancia);
						}
						Collections.sort(listAux, new Comparator(){
							@Override
							public int compare(Object p1, Object p2) {
								// TODO Auto-generated method stub
								return new Float(((Ganancia) p2).getGanancia())
										.compareTo(new Float(((Ganancia) p1).getGanancia()));
							}
						});						
					}
				}
				return listAux;
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El rango entre fechas no "
						+ "puede ser mayor a 3 meses!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				List<Ganancia> lista = new ArrayList<Ganancia>();
				return lista;
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar ambas fechas "
					+ "con un rango no mayor a 3 meses!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			List<Ganancia> lista = new ArrayList<Ganancia>();
			return lista;
		}
	}
	
	public String goReporteStock(Usuario user) {
		idTipoProducto = 0;
		idEstado = 0;
		listaProductoUnidads = new ArrayList<ProductoUnidad>();		
		usuario = new Usuario();
		usuario = user;
		return "reporteStock";
	}
	
	public void buscarStock() {		
		log.info("buscarStock() - tipoProducto: " + idTipoProducto + " estado: " + idEstado);
		if (idTipoProducto != 0) {
			listaProductoUnidads = new ArrayList<ProductoUnidad>();
			List<Producto> listProducts = new ArrayList<Producto>();
			Rubro rub = new Rubro();
			rub.setId(idTipoProducto);//1-MOVILES; 2-ACCESORIOS
			switch (idEstado) {
			case 0://TODOS
				listProducts = productoDAO.getLista(rub);
				break;
			case 1://ACTIVOS
				listProducts = productoDAO.getLista(true, rub);
				break;
			case 2://DESACTIVOS
				listProducts = productoDAO.getLista(false, rub);
				break;
			default://TODOS
				listProducts = productoDAO.getLista(rub);
				break;
			}
			if (idTipoProducto == 1) {
				for (Producto prod : listProducts) {									
					List<UnidadMovil> listaUMovils = unidadMovilDAO.getListaEnStock(true, prod, false, true);
					List<UnidadMovil> listaUConsig = unidadMovilDAO.getListaEnStock(true, prod, true, true);
					int stock = listaUMovils.size();
					int consig = listaUConsig.size();
					String marcaModelo = prod.getNombre();
					ProductoUnidad productoU = new ProductoUnidad();
					productoU.setMarcaModelo(marcaModelo);
					productoU.setStock(stock);
					productoU.setConsignacion(consig);
					productoU.setEnStocks(listaUMovils);
					productoU.setEnConsignacions(listaUConsig);
					listaProductoUnidads.add(productoU);					
				}
			}
			if (idTipoProducto == 2) {
				for (Producto prod : listProducts) {
					ProductoUnidad productoU = new ProductoUnidad();				
					List<Stock> listaStocks = stockDAO.getLista(prod);
					int stock = 0;
					for (Stock sProd : listaStocks) {
						stock = stock + sProd.getCantidad();
					}
					String marcaModelo = prod.getNombre();				
					productoU.setMarcaModelo(marcaModelo);
					productoU.setStock(stock);
					listaProductoUnidads.add(productoU);
				}
			}
			log.info("Lista Producto size() " + listaProductoUnidads.size());
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un tipo de producto!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void generarPdfStock() {
		if (!listaProductoUnidads.isEmpty()) {
			try {
				String report = "";
				String estado = "";
				switch (idTipoProducto) {
				case 1:
					report = "reporteStock";
					break;
				case 2:
					report = "reporteStockA";
					break;
				}
				switch (idEstado) {
				case 0:
					estado = "Todos";
					break;
				case 1:
					estado = "Activos";
					break;
				case 2:
					estado = "Desactivos";
					break;
				}
				Reporte reporte = new Reporte();
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("estado", estado);				
				reporte.generar(parametros, listaProductoUnidads, report, "inline");
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! Error original: " + e.getMessage(), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}
	
	public void generarXlsStock() {
		if (!listaProductoUnidads.isEmpty()) {
			try {
				String report = "";
				String estado = "";
				switch (idTipoProducto) {
				case 1:
					report = "excelStock";
					break;
				case 2:
					report = "excelStockA";
					break;
				}
				switch (idEstado) {
				case 0:
					estado = "Todos";
					break;
				case 1:
					estado = "Activos";
					break;
				case 2:
					estado = "Desactivos";
					break;
				}
				Reporte reporte = new Reporte();
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("estado", estado);				
				reporte.exportXls(parametros, listaProductoUnidads, report, "inline");
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! Error original: " + e.getMessage(), null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
	}

}
