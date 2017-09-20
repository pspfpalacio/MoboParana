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
import ar.com.clases.auxiliares.Comprobante;
import ar.com.clases.auxiliares.DetalleComprobante;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuotasVenta;
import model.entity.HistorialMovil;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.Stock;
import model.entity.StocksVentasDetalle;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOCuotaVenta;
import dao.interfaces.DAOHistorialMovil;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOStock;
import dao.interfaces.DAOStockVentaDetalle;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaDetalle;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanVenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanVenta.class);
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleDAO}")
	private DAOVentaDetalle ventaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanStockDAO}")
	private DAOStock stockDAO;
	
	@ManagedProperty(value = "#{BeanStockVentaDetalleDAO}")
	private DAOStockVentaDetalle stockVentaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanCuotaVentaDAO}")
	private DAOCuotaVenta cuotaVentaDAO;
	
	@ManagedProperty(value = "#{BeanHistorialMovilDAO}")
	private DAOHistorialMovil historialMovilDAO;
	
	private List<Venta> listaVentas;
	private List<Venta> filteredVentas;
	private List<Cliente> listaClientes;
	private List<VentasDetalle> listaVentasDetalles;
	private List<Producto> listaProductos;
	private List<ListaPrecio> listaPrecios;
	private Venta venta;
	private Usuario usuario;
	private ListaPrecio listaPrecio;
	private Producto producto;
	private UnidadMovil unidadMovil;
	private Cliente cliente;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAnterior;
	private String tipo;
	private String tipoAnterior;
	private String nroImei;
	private String headerText;
	private int idCliente;
	private int idClienteAnterior;
	private int idProducto;
	private int idTipo;
	private int estado;
	private int nroVenta;
	private int stockDisponible;
	private int cantidad;
	private int cantidadTotal;
	private int idListaPrecio;
	private float precioVenta;
	private float montoTotal;
	private float montoAnterior;
	private boolean nueva;
	private boolean imeiValido;
	private boolean ningunProducto;
	private boolean productoVenta;
	private boolean productoConsignacion;
	private boolean stockProducto;
	private boolean panelMovil;
	private boolean panelAccesorio;
	private boolean modificarTipo;

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

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
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

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public DAOConsignacionDetalleUnidad getConsignacionDetalleUnidadDAO() {
		return consignacionDetalleUnidadDAO;
	}

	public void setConsignacionDetalleUnidadDAO(
			DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO) {
		this.consignacionDetalleUnidadDAO = consignacionDetalleUnidadDAO;
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

	public DAOCuotaVenta getCuotaVentaDAO() {
		return cuotaVentaDAO;
	}

	public void setCuotaVentaDAO(DAOCuotaVenta cuotaVentaDAO) {
		this.cuotaVentaDAO = cuotaVentaDAO;
	}

	public DAOHistorialMovil getHistorialMovilDAO() {
		return historialMovilDAO;
	}

	public void setHistorialMovilDAO(DAOHistorialMovil historialMovilDAO) {
		this.historialMovilDAO = historialMovilDAO;
	}

	public List<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public List<Venta> getFilteredVentas() {
		return filteredVentas;
	}

	public void setFilteredVentas(List<Venta> filteredVentas) {
		this.filteredVentas = filteredVentas;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<VentasDetalle> getListaVentasDetalles() {
		return listaVentasDetalles;
	}

	public void setListaVentasDetalles(List<VentasDetalle> listaVentasDetalles) {
		this.listaVentasDetalles = listaVentasDetalles;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<ListaPrecio> getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(List<ListaPrecio> listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ListaPrecio getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public UnidadMovil getUnidadMovil() {
		return unidadMovil;
	}

	public void setUnidadMovil(UnidadMovil unidadMovil) {
		this.unidadMovil = unidadMovil;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Date getFechaAnterior() {
		return fechaAnterior;
	}

	public void setFechaAnterior(Date fechaAnterior) {
		this.fechaAnterior = fechaAnterior;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoAnterior() {
		return tipoAnterior;
	}

	public void setTipoAnterior(String tipoAnterior) {
		this.tipoAnterior = tipoAnterior;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdClienteAnterior() {
		return idClienteAnterior;
	}

	public void setIdClienteAnterior(int idClienteAnterior) {
		this.idClienteAnterior = idClienteAnterior;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getNroVenta() {
		return nroVenta;
	}

	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public int getIdListaPrecio() {
		return idListaPrecio;
	}

	public void setIdListaPrecio(int idListaPrecio) {
		this.idListaPrecio = idListaPrecio;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public float getMontoAnterior() {
		return montoAnterior;
	}

	public void setMontoAnterior(float montoAnterior) {
		this.montoAnterior = montoAnterior;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public boolean isNueva() {
		return nueva;
	}

	public void setNueva(boolean nueva) {
		this.nueva = nueva;
	}

	public boolean isImeiValido() {
		return imeiValido;
	}

	public void setImeiValido(boolean imeiValido) {
		this.imeiValido = imeiValido;
	}

	public boolean isNingunProducto() {
		return ningunProducto;
	}

	public void setNingunProducto(boolean ningunProducto) {
		this.ningunProducto = ningunProducto;
	}

	public boolean isProductoVenta() {
		return productoVenta;
	}

	public void setProductoVenta(boolean productoVenta) {
		this.productoVenta = productoVenta;
	}

	public boolean isProductoConsignacion() {
		return productoConsignacion;
	}

	public void setProductoConsignacion(boolean productoConsignacion) {
		this.productoConsignacion = productoConsignacion;
	}

	public boolean isStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(boolean stockProducto) {
		this.stockProducto = stockProducto;
	}

	public boolean isPanelMovil() {
		return panelMovil;
	}

	public void setPanelMovil(boolean panelMovil) {
		this.panelMovil = panelMovil;
	}

	public boolean isPanelAccesorio() {
		return panelAccesorio;
	}

	public void setPanelAccesorio(boolean panelAccesorio) {
		this.panelAccesorio = panelAccesorio;
	}

	public boolean isModificarTipo() {
		return modificarTipo;
	}

	public void setModificarTipo(boolean modificarTipo) {
		this.modificarTipo = modificarTipo;
	}

	public String goVentas(Usuario user){
		listaVentas = new ArrayList<Venta>();
		filteredVentas = new ArrayList<Venta>();
		listaVentas = ventaDAO.getLista(true);
		filteredVentas = listaVentas;
		listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(true);
		estado = 0;
		idCliente = 0;
		fechaInicio = null;
		fechaFin = null;
		modificarTipo = true;
		usuario = new Usuario();
		usuario = user;
		return "ventas";
	}
	
	public String goVentaNueva(){
		venta = new Venta();
		venta.setFecha(new Date());
		listaProductos = new ArrayList<Producto>();
		listaClientes = new ArrayList<Cliente>();
		listaVentasDetalles = new ArrayList<VentasDetalle>();
		listaPrecios = new ArrayList<ListaPrecio>();
		listaClientes = clienteDAO.getLista(true);
		listaPrecios = listaPrecioDAO.getLista(true);
		producto = new Producto();
		cliente = new Cliente();
		listaPrecio = new ListaPrecio();
		unidadMovil = new UnidadMovil();
		idCliente = 0;
		montoTotal = 0;
		cantidad = 0;
		cantidadTotal = 0;
		idTipo = 0;
		idProducto = 0;
		idListaPrecio = 0;
		tipo = "ctdo.";
		nroImei = "";
		headerText = "Nueva Venta";
		nroVenta = 1;
		modificarTipo = true;
		List<Venta> listAux = ventaDAO.getListaSinOrden();
		for (Venta venta : listAux) {
			nroVenta = venta.getId() + 1;
		}
		nueva = true;
		panelMovil = false;
		panelAccesorio = false;
		return "venta";
	}
	
	public String goVentaEditar(Venta vent){
		cantidadTotal = 0;
		idCliente = 0;
		idClienteAnterior = 0;
		modificarTipo = true;
		nroVenta = vent.getId();
		tipo = vent.getTipo();
		montoTotal = vent.getMonto();
		producto = new Producto();
		listaPrecio = new ListaPrecio();
		unidadMovil = new UnidadMovil();
		cliente = new Cliente();
		venta = new Venta();
		venta = vent;
		if(vent.getCliente() != null){
			idCliente = vent.getCliente().getId();
			idClienteAnterior = vent.getCliente().getId();
			int idListaPre = vent.getCliente().getListaPrecio().getId();
			listaPrecio = listaPrecioDAO.get(idListaPre);
			idListaPrecio = vent.getCliente().getListaPrecio().getId();
			cliente = clienteDAO.get(idCliente);
			modificarTipo = false;
		}
		List<CuotasVenta> listCuotVents = cuotaVentaDAO.getLista(vent);
		if (!listCuotVents.isEmpty()) {
			modificarTipo = true;
		}
		montoAnterior = vent.getMonto();
		fechaAnterior = vent.getFecha();		
		nueva = false;
		headerText = "Modificar Venta";
		List<VentasDetalle> listAux = ventaDetalleDAO.getLista(vent);
		listaVentasDetalles = new ArrayList<VentasDetalle>();
		listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista();
		for (VentasDetalle ventasDetalle : listAux) {
			List<VentasDetalleUnidad> listAux1 = ventaDetalleUnidadDAO.getLista(ventasDetalle);
			ventasDetalle.setVentasDetalleUnidads(listAux1);
			listaVentasDetalles.add(ventasDetalle);
			cantidadTotal = cantidadTotal + ventasDetalle.getCantidad();
		}
		panelMovil = false;
		panelAccesorio = false;
		listaProductos = new ArrayList<Producto>();
		listaPrecios = new ArrayList<ListaPrecio>();
		listaPrecios = listaPrecioDAO.getLista(true);
		cantidad = 0;
		idTipo = 0;
		idProducto = 0;		
		return "venta";
	}
	
	public void onChangeCliente(){
		listaPrecio = new ListaPrecio();
		modificarTipo = true;
		if(idCliente != 0){
			cliente = new Cliente();
			cliente = clienteDAO.get(idCliente);
			listaPrecio = cliente.getListaPrecio();
			idListaPrecio = cliente.getListaPrecio().getId();
			modificarTipo = false;
			tipo = "c.c.";
		}else{
			tipo = "ctdo.";
		}
	}
	
	public void onChangeTipo(){
		Rubro rubro = new Rubro();
		if(idTipo == 0){
			panelMovil = false;
			panelAccesorio = false;
		}
		if(idTipo == 1){
			panelMovil = true;
			panelAccesorio = false;
		}
		if(idTipo == 2){
			panelMovil = false;
			panelAccesorio = true;
			rubro.setId(2);
			listaProductos = new ArrayList<Producto>();
			listaProductos = productoDAO.getLista(true, rubro);
		}
	}
	
	public void onChangeAccesorio(){
		if(idProducto == 0){
			precioVenta = 0;
		}else{
			imeiValido = true;
			ningunProducto = false;
			productoVenta = false;
			productoConsignacion = false;
			stockProducto = true;
			producto = new Producto();
			producto = productoDAO.get(idProducto);
			stockDisponible = producto.getStock();
			if(listaPrecio.getId() != 0){
				ListaPrecioProducto precioProducto = new ListaPrecioProducto();
				precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
				float costoPromedio = producto.getCostoPromedio();
				precioVenta = precioProducto.getPrecioVenta();
				if (costoPromedio != 0) {
					if (precioVenta < costoPromedio) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El precio de venta: $" + precioVenta 
								+ " es menor al costo promedio: $" + costoPromedio, null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}
			}else{
				if (idCliente == 0) {
					float costoPromedio = producto.getCostoPromedio();
					if (costoPromedio == 0) {
						precioVenta = producto.getPrecioCosto();
					} else {
						precioVenta = costoPromedio;
					}
				} else {
					precioVenta = 0;
				}				
			}			
		}
	}
	
	public void onChangeListaPrecio() {
		if (idListaPrecio != 0) {
			listaPrecio = new ListaPrecio();
			listaPrecio = listaPrecioDAO.get(idListaPrecio);
			if (producto != null) {
				if (producto.getId() != 0) {
					ListaPrecioProducto precioProducto = new ListaPrecioProducto();
					precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
					precioVenta = precioProducto.getPrecioVenta();
				}
			}
		}
	}
	
	public List<UnidadMovil> completeText(String query){
		List<UnidadMovil> listAux = unidadMovilDAO.getLike(true, true, query);
		List<String> lista = new ArrayList<String>();
		for (UnidadMovil unidadMovil : listAux) {
			lista.add(unidadMovil.getNroImei());
		}
		return listAux;
	}
	
	public void onBlurNroImei(){
		producto = new Producto();
		precioVenta = 0;
		if(unidadMovil.getId() != 0){
			imeiValido = true;
			nroImei = unidadMovil.getNroImei();
			if (!unidadMovil.getEnGarantiaCliente() && !unidadMovil.getEnGarantiaProveedor()) {
				if(unidadMovil.getEnStock()){
					ningunProducto = false;				
					boolean noAgregado = true;
					if(unidadMovil.getEnVenta()){
						noAgregado = false;
					}
					for(VentasDetalle ventaDetalle : listaVentasDetalles){
						for(VentasDetalleUnidad ventaUnidad : ventaDetalle.getVentasDetalleUnidads()){
							if(ventaUnidad.getNroImei().equals(unidadMovil.getNroImei())){
								noAgregado = false;
							}
						}
					}
					if(noAgregado){
						productoVenta = false;
						boolean enConsignacion = false;
						if(unidadMovil.getEnConsignacion()){
							enConsignacion = true;							
						}
						if(!enConsignacion){
							productoConsignacion = false;
							if(unidadMovil.getEnStock() && unidadMovil.getEstado() && !unidadMovil.getEliminado()){
								stockProducto = true;
								producto = unidadMovil.getProducto();
								if(listaPrecio.getId() != 0){
									ListaPrecioProducto precioProducto = new ListaPrecioProducto();
									precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
									float costoPromedio = producto.getCostoPromedio();
									precioVenta = precioProducto.getPrecioVenta();
									if (costoPromedio != 0) {
										if (precioVenta < costoPromedio) {
											FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El precio de venta: $" + precioVenta 
													+ " es menor al costo promedio: $" + costoPromedio, null);
											FacesContext.getCurrentInstance().addMessage(null, msg);
										}
									}								
								}else{
									if (idCliente == 0) {
										float costoPromedio = producto.getCostoPromedio();
										if (costoPromedio == 0) {
											precioVenta = producto.getPrecioCosto();
										} else {
											precioVenta = costoPromedio;
										}
									} else {
										precioVenta = 0;
									}	
								}
							}else{
								stockProducto = false;
								FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei no corresponde a ningun producto en Stock!", null);
								FacesContext.getCurrentInstance().addMessage(null, msg);
							}
						}else{
							productoConsignacion = true;
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto asociado a una Consignacion!", null);
							FacesContext.getCurrentInstance().addMessage(null, msg);
						}
					}else{
						productoVenta = true;
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto asociado a Venta!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}else{
					ningunProducto = true;
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei no corresponde a ningun producto en Stock!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				ningunProducto = true;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto en Garantia!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		}else{
			imeiValido = false;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un nro de Imei valido!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void agregarProducto(){
		if(panelAccesorio){
			if(cantidad > stockDisponible){
				stockProducto = false;
			}
		}
		if(imeiValido && !ningunProducto && !productoVenta && !productoConsignacion && stockProducto && precioVenta != 0){
			if(producto.getId() != 0 && unidadMovil != null){
				List<VentasDetalle> listAux = new ArrayList<VentasDetalle>();
				boolean noExiste = true;
				for (VentasDetalle ventasDetalle : listaVentasDetalles) {
					if(producto.getId() == ventasDetalle.getProducto().getId()){
						noExiste = false;
						if(panelMovil){
							List<VentasDetalleUnidad> listaUnidades = ventasDetalle.getVentasDetalleUnidads();
//							List<VentasDetalleUnidad> listaAux = new ArrayList<VentasDetalleUnidad>();
//							for (VentasDetalleUnidad ventasDetalleUnidad : listaUnidades) {
//								ventasDetalleUnidad.setPrecioVenta(precioVenta);
//								listaAux.add(ventasDetalleUnidad);
//							}
							VentasDetalleUnidad unidad = new VentasDetalleUnidad();
							unidad.setNroImei(unidadMovil.getNroImei());
							unidad.setPrecioCompra(unidadMovil.getPrecioCompra());
							unidad.setPrecioVenta(precioVenta);
							unidad.setUnidadMovil(unidadMovil);
							unidad.setListaPrecio(listaPrecio);
							listaUnidades.add(unidad);
							ventasDetalle.setAccesorio(false);
							cantidadTotal = cantidadTotal - ventasDetalle.getCantidad();
							montoTotal = montoTotal - ventasDetalle.getSubtotal();
							ventasDetalle.setCantidad(ventasDetalle.getCantidad() + 1);
							ventasDetalle.setPrecioVenta(precioVenta);
							ventasDetalle.setProducto(producto);
							float subtotal = ventasDetalle.getSubtotal() + precioVenta;
							ventasDetalle.setSubtotal(subtotal);
							ventasDetalle.setVentasDetalleUnidads(listaUnidades);
							montoTotal = montoTotal + subtotal;
							cantidadTotal = cantidadTotal + ventasDetalle.getCantidad();
						}
						if(panelAccesorio){
							ventasDetalle.setAccesorio(true);
							cantidadTotal = cantidadTotal - ventasDetalle.getCantidad();
							montoTotal = montoTotal - ventasDetalle.getSubtotal();
							ventasDetalle.setCantidad(ventasDetalle.getCantidad() + cantidad);
							ventasDetalle.setPrecioVenta(precioVenta);
							ventasDetalle.setProducto(producto);
							ventasDetalle.setListaPrecio(listaPrecio);
							ventasDetalle.setSubtotal(precioVenta * ventasDetalle.getCantidad());
							montoTotal = montoTotal + ventasDetalle.getSubtotal();
							cantidadTotal = cantidadTotal + ventasDetalle.getCantidad();
						}
					}
					listAux.add(ventasDetalle);
				}
				if(noExiste){
					VentasDetalle detalle = new VentasDetalle();
					if(panelMovil){
						List<VentasDetalleUnidad> listaUnidades = new ArrayList<VentasDetalleUnidad>();
						VentasDetalleUnidad unidad = new VentasDetalleUnidad();
						unidad.setNroImei(unidadMovil.getNroImei());
						unidad.setPrecioCompra(unidadMovil.getPrecioCompra());
						unidad.setPrecioVenta(precioVenta);
						unidad.setUnidadMovil(unidadMovil);
						unidad.setListaPrecio(listaPrecio);
						listaUnidades.add(unidad);
						detalle.setAccesorio(false);
						detalle.setCantidad(1);
						detalle.setPrecioVenta(precioVenta);
						detalle.setProducto(producto);
						detalle.setSubtotal(precioVenta);
						detalle.setVentasDetalleUnidads(listaUnidades);
						listAux.add(detalle);
						montoTotal = montoTotal + precioVenta;
						cantidadTotal = cantidadTotal + 1;
					}
					if(panelAccesorio){
						detalle.setAccesorio(true);
						detalle.setCantidad(cantidad);
						detalle.setPrecioVenta(precioVenta);
						detalle.setProducto(producto);
						detalle.setListaPrecio(listaPrecio);
						detalle.setSubtotal((cantidad * precioVenta));
						listAux.add(detalle);
						montoTotal = montoTotal + detalle.getSubtotal();
						cantidadTotal = cantidadTotal + cantidad;
					}
				}
				listaVentasDetalles = new ArrayList<VentasDetalle>();
				listaVentasDetalles = listAux;
				producto = new Producto();
				unidadMovil = new UnidadMovil();
				nroImei = "";
				precioVenta = 0;
				cantidad = 0;
				idProducto = 0;
				stockDisponible = 0;
			}else{
				nroImei = "";
				precioVenta = 0;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo agregar. Debe existir un Producto!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = null;
			if(!imeiValido){
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un nro de Imei valido!", null);
			}
			if(ningunProducto){
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe existir un producto!", null);
			}
			if(productoVenta){
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto asociado a Venta!", null);
			}
			if(productoConsignacion){
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto asociado a una Consignacion!", null);
			}
			if(!stockProducto){
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No corresponde ningun producto en Stock!", null);
			}
			if(precioVenta == 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El precio de venta es obligatorio!", null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void quitarProducto(VentasDetalle detalle){
		List<VentasDetalle> listAux = new ArrayList<VentasDetalle>();
		for (VentasDetalle ventasDetalle : listaVentasDetalles) {
			if(ventasDetalle.getProducto().getId() != detalle.getProducto().getId()){
				listAux.add(ventasDetalle);
			}else{
				montoTotal = montoTotal - ventasDetalle.getSubtotal();
				cantidadTotal = cantidadTotal - ventasDetalle.getCantidad();
			}
		}
		listaVentasDetalles = new ArrayList<VentasDetalle>();
		listaVentasDetalles = listAux;
	}
	
	public void quitarUnidadDetalle(VentasDetalle detalle, VentasDetalleUnidad imei){
		List<VentasDetalleUnidad> listAux = new ArrayList<VentasDetalleUnidad>();
		for(VentasDetalleUnidad unidadDetalle : detalle.getVentasDetalleUnidads()){
			if(!unidadDetalle.getNroImei().equals(imei.getNroImei())){
				listAux.add(unidadDetalle);
			}
		}
		montoTotal = montoTotal - imei.getPrecioVenta();
		float subtot = detalle.getSubtotal() -  imei.getPrecioVenta();
		cantidadTotal = cantidadTotal - 1;
		int cant = detalle.getCantidad() - 1;
		detalle.setVentasDetalleUnidads(listAux);
		detalle.setSubtotal(subtot);
		detalle.setCantidad(cant);
		List<VentasDetalle> listAux1 = new ArrayList<VentasDetalle>();
		if (listAux.isEmpty()) {
			for(VentasDetalle ventaDetalle : listaVentasDetalles){
				if(ventaDetalle.getProducto().getId() != detalle.getProducto().getId()){
					listAux1.add(ventaDetalle);
				}
			}
		} else {
			for(VentasDetalle ventaDetalle : listaVentasDetalles){
				if(ventaDetalle.getProducto().getId() != detalle.getProducto().getId()){
					listAux1.add(ventaDetalle);
				}else{
					listAux1.add(detalle);
				}
			}
		}
		listaVentasDetalles = new ArrayList<VentasDetalle>();
		listaVentasDetalles = listAux1;
	}
	
	public List<VentasDetalle> getDetalleDeVenta(Venta vent){
		List<VentasDetalle> listAux = new ArrayList<VentasDetalle>();
		listAux = ventaDetalleDAO.getLista(vent);
		return listAux;
	}
	
	public void filtro(){
		listaVentas = new ArrayList<Venta>();
		filteredVentas = new ArrayList<Venta>();
		if(idCliente == 0 && fechaInicio == null && fechaFin == null){
			listaVentas = ventaDAO.getLista(true);
		}
		if(idCliente != 0 && fechaInicio == null && fechaFin == null){
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaVentas = ventaDAO.getLista(true, cli);
		}
		if(idCliente == 0 && fechaInicio != null && fechaFin != null){
			listaVentas = ventaDAO.getLista(true, fechaInicio, fechaFin);
		}
		if(idCliente != 0 && fechaInicio != null && fechaFin != null){
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaVentas = ventaDAO.getLista(true, cli, fechaInicio, fechaFin);
		}
		filteredVentas = listaVentas;
	}
	
	public void baja(Venta vent){
		log.info("Baja de venta: " + venta.getId());
		FacesMessage msg = null;
		try {
			boolean falla = false;
			boolean noCuota = true;
			List<VentasDetalle> listVentDet = ventaDetalleDAO.getLista(vent);
			for (VentasDetalle ventasDetalle : listVentDet) {
				falla = conFalla(ventasDetalle);
			}
			List<CuotasVenta> listaCuotas = cuotaVentaDAO.getLista(true, vent);
			if (listaCuotas.isEmpty()) {
				noCuota = false;
			}
			if(!falla && !noCuota){
				boolean actualizo = true;
				int idVent = vent.getId();
				if (vent.getCliente() != null) {
					CuentaCorriente cuenta = new CuentaCorriente();
					CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
					int idCli = vent.getCliente().getId();
					Cliente cli = clienteDAO.get(idCli);				
					if(vent.getTipo().equals("c.c.")){
						ccCliente.setIdMovimiento(idVent);
						ccCliente.setNombreTabla("Venta");
						int deleteCC = cuenta.deleteCuentaCorriente(ccCliente);
						if(deleteCC == 0){
							actualizo = false;
						}
					}
					if(vent.getTipo().equals("ctdo.")){
						if(cuentaCorrienteDAO.deletePorMovimientoCliente(idVent, "Venta", cli) == 0){
							actualizo = false;
						}
						MovimientoCaja movCaja = new MovimientoCaja();
						Caja mov = new Caja();
						mov.setIdMovimiento(idVent);
						mov.setNombreTabla("Venta");
						if(movCaja.deleteCaja(mov) == 0){
							actualizo = false;
						}
					}
				} else {
					MovimientoCaja movCaja = new MovimientoCaja();
					Caja mov = new Caja();
					mov.setIdMovimiento(idVent);
					mov.setNombreTabla("Venta");
					if(movCaja.deleteCaja(mov) == 0){
						actualizo = false;
					}
				}
				for (VentasDetalle ventasDetalle : listVentDet) {
					int idProd = ventasDetalle.getProducto().getId();
					Producto prod = productoDAO.get(idProd);
					int stock = prod.getStock() + ventasDetalle.getCantidad();
					prod.setStock(stock);
					int updateProd = productoDAO.update(prod);
					if(updateProd == 0){
						actualizo = false;
					}
					if(ventasDetalle.getAccesorio()){
						List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
						for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
							Stock stk = stocksVentasDetalle.getStock();
							int cantStock = stk.getCantidad() + stocksVentasDetalle.getCantidad();
							stk.setCantidad(cantStock);
							stockDAO.update(stk);
						}
					}else{
						for(VentasDetalleUnidad ventasUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)){
							String nroImei = ventasUnidad.getNroImei();
							UnidadMovil unidad = unidadMovilDAO.get(nroImei);
							unidad.setEnStock(true);
							unidad.setEnVenta(false);
							int updateUnid = unidadMovilDAO.update(unidad);
							if(updateUnid == 0){
								actualizo = false;
							}else {
								HistorialMovil hm = new HistorialMovil();
								hm.setFecha(new Date());
								hm.setUsuario(usuario);
								hm.setImei(nroImei);
								hm.setTipo("BAJA VENTA");
								hm.setDescripcion("Baja de Venta: " + vent.getId());
								hm.setIdMovimiento(0);
								log.info("Tipo de movimiento (historial): " + hm.getTipo());
								historialMovilDAO.insert(hm);
							}
						}
						int deleteVentDet = ventaDetalleUnidadDAO.deletePorDetalle(ventasDetalle);
						if(deleteVentDet == 0){
							actualizo = false;
						}
					}				
				}
				vent.setEstado(false);
				vent.setFechaBaja(new Date());
				vent.setUsuario2(usuario);
				int updateVent = ventaDAO.update(vent);
				if(updateVent != 0 && actualizo){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Venta!", null);
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al dar de Baja la Venta, "
							+ "Intente nuevamente!", null);
				}
			}else{
				String mensaje = "";
				if (falla) {
					mensaje = "La Venta posee Moviles en Garantia, realice la baja correspondiente e "
						+ "intente nuevamente!";
				}
				if (noCuota) {
					mensaje = mensaje + " La venta posee Moviles en Cuotas, realice la baja de los mismos "
							+ "para realizar la baja de la Venta!";
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, null);
			}
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No pudo ser posible dar de baja la Venta, intente nuevamente mas tarde!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public boolean conFalla(VentasDetalle ventaDetalle){
		boolean falla = false;
		List<VentasDetalleUnidad> listAux = ventaDetalleUnidadDAO.getLista(ventaDetalle);
		for (VentasDetalleUnidad ventasDetalleUnidad : listAux) {
			String nroImei = ventasDetalleUnidad.getNroImei();
			UnidadMovil unidad = unidadMovilDAO.get(nroImei);
//			boolean estadoU = unidad.getConFalla();
			boolean garantiaU = unidad.getEnGarantiaCliente();
//			if(estadoU){
//				falla = true;
//			}
			if(garantiaU){
				falla = true;
			}
		}
		return falla;
	}
	
	public boolean conFallaUnidad(VentasDetalleUnidad ventaUnidad){
		boolean falla = false;
		UnidadMovil unidad = unidadMovilDAO.get(ventaUnidad.getNroImei());
		if(unidad.getConFalla()){
			falla = true;
		}
		if(unidad.getEnGarantiaCliente()){
			falla = true;
		}
		return falla;
	}
	
	public boolean noBajaDetalle(VentasDetalle ventaDetalle) {
		boolean noBaja = false;
		List<VentasDetalleUnidad> listAux = ventaDetalleUnidadDAO.getLista(ventaDetalle);
		for (VentasDetalleUnidad ventasDetalleUnidad : listAux) {
			String imei = ventasDetalleUnidad.getNroImei();
			UnidadMovil unidad = unidadMovilDAO.get(imei);
			boolean garantiaU = unidad.getEnGarantiaCliente();
			if(garantiaU){
				noBaja = true;
			}
			CuotasVenta cuotaVenta = cuotaVentaDAO.get(imei);
			if (cuotaVenta.getId() != 0) {
				noBaja = true;
			}
		}
		return noBaja;
	}
	
	public boolean noBajaUnidad(VentasDetalleUnidad ventaUnidad) {
		boolean noBaja = false;
		String imei = ventaUnidad.getNroImei();
		UnidadMovil unidad = unidadMovilDAO.get(imei);
		if(unidad.getConFalla()){
			noBaja = true;
		}
		if(unidad.getEnGarantiaCliente()){
			noBaja = true;
		}
		CuotasVenta cuotaVenta = cuotaVentaDAO.get(imei);
		if (cuotaVenta.getId() != 0) {
			noBaja = true;
		}
		return noBaja;
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		if(venta.getFecha() != null && !listaVentasDetalles.isEmpty() && montoTotal != 0 && idListaPrecio != 0){
			log.info("Venta - monto total: "+ montoTotal);
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
			if(idCliente == 0){
				tipo = "ctdo.";
			}
			venta.setEstado(true);
			venta.setFechaAlta(new Date());
			venta.setMonto(montoTotal);
			venta.setTipo(tipo);
			venta.setUsuario1(usuario);
			Date fechaVen = venta.getFecha();
			if(idCliente != 0){
				Cliente cli = clienteDAO.get(idCliente);
				venta.setCliente(cli);
				float saldoCli = cli.getSaldo();
				venta.setSaldoCliente(saldoCli);
				ccCliente.setCliente(cli);
				ccCliente.setDebe(montoTotal);
				ccCliente.setDetalle("Venta nro: " + nroVenta);				
				ccCliente.setFecha(fechaVen);
				ccCliente.setIdMovimiento(nroVenta);
				ccCliente.setMonto(montoTotal);
				ccCliente.setNombreTabla("Venta");
				ccCliente.setUsuario(usuario);
				cuenta.insertarCC(ccCliente);
			}
			if(tipo.equals("ctdo.")){
				if(idCliente != 0){
					ccCliente = new CuentasCorrientesCliente();
					Cliente cli = clienteDAO.get(idCliente);
					ccCliente.setCliente(cli);
					ccCliente.setDetalle("Pago de contado - Venta nro: " + nroVenta);
					ccCliente.setFecha(fechaVen);
					ccCliente.setHaber(montoTotal);
					ccCliente.setIdMovimiento(nroVenta);
					ccCliente.setMonto(montoTotal);
					ccCliente.setNombreTabla("Venta");
					ccCliente.setUsuario(usuario);
					cuenta.insertarCC(ccCliente);
				}
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				Caja caja = new Caja();
				caja.setConcepto("Cobro de Venta nro: " + nroVenta);
				caja.setFecha(fechaVen);
				caja.setIdMovimiento(nroVenta);
				caja.setMonto(montoTotal);
				caja.setNombreTabla("Venta");
				caja.setUsuario(usuario);
				movimientoCaja.insertarCaja(caja);
			}
			int idVenta = ventaDAO.insertar(venta);
			if(idVenta != 0){
				log.info("idVenta: " + idVenta);
				boolean inserto = true;
				venta.setId(idVenta);
				for (VentasDetalle ventasDetalle : listaVentasDetalles) {
					int idProd = ventasDetalle.getProducto().getId();
					Producto prod = productoDAO.get(idProd);
					int cantidad = prod.getStock() - ventasDetalle.getCantidad();
					prod.setStock(cantidad);
					productoDAO.update(prod);
					List<VentasDetalleUnidad> listAux = ventasDetalle.getVentasDetalleUnidads();
					ventasDetalle.setVentasDetalleUnidads(null);
					ventasDetalle.setVenta(venta);					
					int idDetalle = ventaDetalleDAO.insertar(ventasDetalle);
					if(idDetalle != 0){
						log.info("idDetalle: " + idDetalle);
						ventasDetalle.setId(idDetalle);						
						boolean insertoUnidad = true;
						if(ventasDetalle.getAccesorio()){
							List<Stock> listaStock = stockDAO.getLista(prod);
							boolean noDesconto = true;
							int descuento = ventasDetalle.getCantidad();
							for (Stock stock : listaStock) {
								if(noDesconto){
									StocksVentasDetalle stocksVentasDetalle = new StocksVentasDetalle();
									if(stock.getCantidad() >= descuento){
										noDesconto = false;
										int cant = stock.getCantidad() - descuento;
										stock.setCantidad(cant);
										stock.setFechaMod(new Date());
										stock.setUsuario3(usuario);
										stocksVentasDetalle.setCantidad(descuento);
										stocksVentasDetalle.setStock(stock);
										stocksVentasDetalle.setVentasDetalle(ventasDetalle);
										stockDAO.update(stock); 
										stockVentaDetalleDAO.insertar(stocksVentasDetalle);										
									}else{
										descuento = descuento - stock.getCantidad();
										stocksVentasDetalle.setCantidad(stock.getCantidad());
										stocksVentasDetalle.setStock(stock);
										stocksVentasDetalle.setVentasDetalle(ventasDetalle);
										stock.setCantidad(0);	
										stock.setFechaMod(new Date());
										stock.setUsuario3(usuario);
										stockDAO.update(stock);
										stockVentaDetalleDAO.insertar(stocksVentasDetalle);
									}
								}
							}
						}else{
							for(VentasDetalleUnidad ventasUnidad : listAux){
								int idUnidad = ventasUnidad.getUnidadMovil().getId();
								UnidadMovil unidad = unidadMovilDAO.get(idUnidad);
								log.info("Imei Unidad Movil: " + unidad.getNroImei());
								unidad.setEnStock(false);
								unidad.setEnVenta(true);
								ventasUnidad.setVentasDetalle(ventasDetalle);
								log.info("Lista Precio: " + ventasUnidad.getListaPrecio().getId());
								int idDetalleUnidad = ventaDetalleUnidadDAO.insertar(ventasUnidad);
								log.info("idDetalleUnidad: " + idDetalleUnidad);
								int updateUnidad = unidadMovilDAO.update(unidad);
								log.info("Unidad Update: " + updateUnidad);
								if(idDetalleUnidad == 0 && updateUnidad == 0){
									insertoUnidad = false;
									break;
								} else {
									HistorialMovil hm = new HistorialMovil();
									hm.setFecha(new Date());
									hm.setUsuario(usuario);
									hm.setImei(unidad.getNroImei());
									hm.setTipo("VENTA");
									hm.setDescripcion("Venta: " + usuario.getApellidoNombre());
									hm.setIdMovimiento(idVenta);
									log.info("Tipo de movimiento (historial): " + hm.getTipo());
									historialMovilDAO.insert(hm);
								}
							}
						}
						if(!insertoUnidad){
							inserto = false;
							break;
						}
					}else{
						inserto = false;
						break;
					}
				}
				if(inserto){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta guardada!", null);
					retorno = "ventas";
					listaClientes = new ArrayList<Cliente>();
					listaClientes = clienteDAO.getLista(true);
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Detalle de la Venta! "
							+ "Intente nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la Venta! "
						+ "Intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Monto Total y el Detalle de la Venta y la lista de precios son obligatorios!", null);
		}		
		listaVentas = new ArrayList<Venta>();
		filteredVentas = new ArrayList<Venta>();
		listaVentas = ventaDAO.getLista(true);
		filteredVentas = listaVentas;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String modifcar(){
		FacesMessage msg = null;
		String retorno = "";
		if(venta.getFecha() != null && !listaVentasDetalles.isEmpty() && montoTotal != 0){
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
			if(venta.getTipo().equals("c.c.")){
				ccCliente.setIdMovimiento(nroVenta);
				ccCliente.setNombreTabla("Venta");
				cuenta.deleteCuentaCorriente(ccCliente);
			}
			if(venta.getTipo().equals("ctdo.")){
				if(venta.getCliente() != null){
					int idCli = venta.getCliente().getId();
					Cliente cli = clienteDAO.get(idCli);
					cuentaCorrienteDAO.deletePorMovimientoCliente(nroVenta, "Venta", cli);
				}
				MovimientoCaja movCaja = new MovimientoCaja();
				Caja mov = new Caja();
				mov.setIdMovimiento(nroVenta);
				mov.setNombreTabla("Venta");
				movCaja.deleteCaja(mov);
			}
			venta.setEstado(true);
			venta.setFechaMod(new Date());
			venta.setMonto(montoTotal);
			venta.setTipo(tipo);
			venta.setUsuario3(usuario);
			int idVen = venta.getId();
			Date fechaVen = venta.getFecha();
			if(idCliente != 0){
				Cliente cli = clienteDAO.get(idCliente);
				venta.setCliente(cli);
				venta.setSaldoCliente(cli.getSaldo());
				ccCliente.setCliente(cli);
				ccCliente.setDebe(montoTotal);
				ccCliente.setDetalle("Venta nro: " + idVen);
				ccCliente.setFecha(fechaVen);
				ccCliente.setIdMovimiento(idVen);
				ccCliente.setMonto(montoTotal);
				ccCliente.setNombreTabla("Venta");
				ccCliente.setUsuario(usuario);
				cuenta.insertarCC(ccCliente);
			}
			if(tipo.equals("ctdo.")){
				if(idCliente != 0){
					ccCliente = new CuentasCorrientesCliente();
					Cliente cli = clienteDAO.get(idCliente);	
					ccCliente.setCliente(cli);
					ccCliente.setDetalle("Pago de contado - Venta nro: " + idVen);
					ccCliente.setFecha(fechaVen);
					ccCliente.setHaber(montoTotal);
					ccCliente.setIdMovimiento(idVen);
					ccCliente.setMonto(montoTotal);
					ccCliente.setNombreTabla("Venta");
					ccCliente.setUsuario(usuario);
					cuenta.insertarCC(ccCliente);
				}
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				Caja caja = new Caja();
				caja.setConcepto("Cobro de Venta nro: " + idVen);
				caja.setFecha(fechaVen);
				caja.setIdMovimiento(idVen);
				float montoCaja = montoTotal;
				caja.setMonto(montoCaja);
				caja.setNombreTabla("Venta");
				caja.setUsuario(usuario);
				movimientoCaja.insertarCaja(caja);
			}
			int idVenta = ventaDAO.update(venta);
			if(idVenta != 0){
				venta.setId(idVenta);
				boolean deleteDetalleUnidad = true;
				for (VentasDetalle ventasDetalle : ventaDetalleDAO.getLista(venta)) {
					int idProd = ventasDetalle.getProducto().getId();
					Producto prod = productoDAO.get(idProd);
					int stock = prod.getStock() + ventasDetalle.getCantidad();
					prod.setStock(stock);
					productoDAO.update(prod);
					if(ventasDetalle.getAccesorio()){
						List<StocksVentasDetalle> listaStocks = stockVentaDetalleDAO.getLista(ventasDetalle);
						for (StocksVentasDetalle stocksVentasDetalle : listaStocks) {
							Stock stk = stocksVentasDetalle.getStock();
							int cantStock = stk.getCantidad() + stocksVentasDetalle.getCantidad();
							stk.setCantidad(cantStock);
							if(stockDAO.update(stk) == 0){
								deleteDetalleUnidad = false;
							}
						}
						stockVentaDetalleDAO.delete(ventasDetalle);
					}else{
						for(VentasDetalleUnidad ventaUnidad : ventaDetalleUnidadDAO.getLista(ventasDetalle)){
							String imeiUndadMvile = ventaUnidad.getUnidadMovil().getNroImei();
							UnidadMovil unidad = unidadMovilDAO.get(imeiUndadMvile);
							if(unidad.getEstado()){
								unidad.setEnStock(true);
								unidad.setEnVenta(false);
								int updaUnidadMov = unidadMovilDAO.update(unidad);
								if(updaUnidadMov == 0){
									deleteDetalleUnidad = false;
								}
							}
						}
						int deletePorDetalle = ventaDetalleUnidadDAO.deletePorDetalle(ventasDetalle);
						if(deletePorDetalle == 0){
							deleteDetalleUnidad = false;
						}
					}
					ventaDetalleDAO.delete(ventasDetalle);
				}
				if(deleteDetalleUnidad){
					boolean inserto = true;
					for (VentasDetalle ventasDetalle : listaVentasDetalles) {
						int idProd = ventasDetalle.getProducto().getId();
						Producto prod = productoDAO.get(idProd);
						int cantidad = prod.getStock() - ventasDetalle.getCantidad();
						prod.setStock(cantidad);
						productoDAO.update(prod);
						List<VentasDetalleUnidad> listAux = ventasDetalle.getVentasDetalleUnidads();
						ventasDetalle.setVentasDetalleUnidads(null);
						ventasDetalle.setVenta(venta);
						int idDetalle = ventaDetalleDAO.insertar(ventasDetalle);
						if(idDetalle != 0){
							ventasDetalle.setId(idDetalle);	
							if(ventasDetalle.getAccesorio()){
								List<Stock> listaStock = stockDAO.getLista(prod);
								boolean noDesconto = true;
								int descuento = ventasDetalle.getCantidad();
								for (Stock stock : listaStock) {
									if(noDesconto){
										StocksVentasDetalle stocksVentasDetalle = new StocksVentasDetalle();
										if(stock.getCantidad() >= descuento){
											noDesconto = false;
											int cant = stock.getCantidad() - descuento;
											stock.setCantidad(cant);
											stock.setFechaMod(new Date());
											stock.setUsuario3(usuario);
											stocksVentasDetalle.setCantidad(descuento);
											stocksVentasDetalle.setStock(stock);
											stocksVentasDetalle.setVentasDetalle(ventasDetalle);
											stockDAO.update(stock);
											stockVentaDetalleDAO.insertar(stocksVentasDetalle);										
										}else{
											descuento = descuento - stock.getCantidad();
											stocksVentasDetalle.setCantidad(stock.getCantidad());
											stocksVentasDetalle.setStock(stock);
											stocksVentasDetalle.setVentasDetalle(ventasDetalle);
											stock.setCantidad(0);	
											stock.setFechaMod(new Date());
											stock.setUsuario3(usuario);
											stockDAO.update(stock);
											stockVentaDetalleDAO.insertar(stocksVentasDetalle);
										}
									}
								}
							}else{
								for(VentasDetalleUnidad ventasUnidad : listAux){
									String imeiMovile = ventasUnidad.getUnidadMovil().getNroImei();
									UnidadMovil unidad = unidadMovilDAO.get(imeiMovile);
									unidad.setEnStock(false);	
									unidad.setEnVenta(true);
									unidadMovilDAO.update(unidad);
									ventasUnidad.setVentasDetalle(ventasDetalle);
									ventaDetalleUnidadDAO.insertar(ventasUnidad);
								}
							}
						}else{
							inserto = false;
							break;
						}
					}
					if(inserto){
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta guardada!", null);
						retorno = "ventas";
						listaClientes = new ArrayList<Cliente>();
						listaClientes = clienteDAO.getLista(true);
					}else{
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Detalle de la Venta! "
								+ "Intente nuevamente!", null);
					}
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar la Unidad Movil del Detalle de la Venta! "
							+ "Intente nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la Venta! "
						+ "Intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Monto Total y el Detalle de la Venta son obligatorios!", null);
		}
		listaVentas = new ArrayList<Venta>();
		filteredVentas = new ArrayList<Venta>();
		listaVentas = ventaDAO.getLista(true);
		filteredVentas = listaVentas;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarReporteLista(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Comprobante> listaComprobante = new ArrayList<Comprobante>();
		for (Venta vent : filteredVentas) {
			Comprobante comprobante = new Comprobante();
			List<DetalleComprobante> listDetalleComprobante = new ArrayList<DetalleComprobante>();
			for (VentasDetalle ventaDetalle : getDetalleDeVenta(vent)) {
				DetalleComprobante detalle = new DetalleComprobante();
				detalle.setCantidad(ventaDetalle.getCantidad());
				detalle.setDetalle(ventaDetalle.getProducto().getNombre());
				detalle.setPrecioUnitario(formatoMonto.format(ventaDetalle.getPrecioVenta()));
				detalle.setSubtotal(formatoMonto.format(ventaDetalle.getSubtotal()));
				listDetalleComprobante.add(detalle);
			}
			comprobante.setFecha(formatoFecha.format(vent.getFecha()));
			comprobante.setListaDetalles(listDetalleComprobante);
			comprobante.setMonto(formatoMonto.format(vent.getMonto()));
			comprobante.setNumero(vent.getId());
			if(vent.getCliente() != null){
				comprobante.setPersona(vent.getCliente().getNombreNegocio());
			}else{
				comprobante.setPersona(vent.getConsumidorFinal());
			}
			comprobante.setTipo(vent.getTipo());
			listaComprobante.add(comprobante);
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
		if(idCliente == 0){
			parametros.put("cliente", "Todos");
		}else{
			Cliente cli = clienteDAO.get(idCliente);
			parametros.put("cliente", cli.getNombreNegocio());
		}
		if(fechaInicio != null && fechaFin != null){
			parametros.put("desde", formatoFecha.format(fechaInicio));
			parametros.put("hasta", formatoFecha.format(fechaFin));			
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.generar(parametros, listaComprobante, "ventas", "inline");
	}
	
	public void generarReporteComprobante(Venta vent){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		for(VentasDetalle ventaDetalle : getDetalleDeVenta(vent)){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(ventaDetalle.getCantidad());
			detalle.setDetalle(ventaDetalle.getProducto().getNombre());
			detalle.setPrecioUnitario(formatoMonto.format(ventaDetalle.getPrecioVenta()));
			detalle.setSubtotal(formatoMonto.format(ventaDetalle.getSubtotal()));
			listaDetalle.add(detalle);
			cant = cant + ventaDetalle.getCantidad();
		}
		parametros.put("numero", vent.getId());
		parametros.put("fecha", formatoFecha.format(vent.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("tipo", vent.getTipo());
		parametros.put("montoTotal", formatoMonto.format(vent.getMonto()));
		String nombreReporte = "ventaDirecta";
		if(vent.getCliente() != null){
			nombreReporte = "venta";
			parametros.put("persona", vent.getCliente().getNombreNegocio());
			parametros.put("saldo", formatoMonto.format(vent.getCliente().getSaldo()));
		}else{
			parametros.put("persona", vent.getConsumidorFinal());
		}
		reporte.generar(parametros, listaDetalle, nombreReporte, "attachment");
	}

}
