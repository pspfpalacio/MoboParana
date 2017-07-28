package ar.com.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ar.com.clases.auxiliares.StockDisponible;
import model.entity.Compra;
import model.entity.ComprasDetalle;
import model.entity.ComprasDetalleUnidad;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.Devolucione;
import model.entity.GarantiasCliente;
import model.entity.GarantiasProveedore;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;
import dao.interfaces.DAOCompraDetalleUnidad;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAODevolucion;
import dao.interfaces.DAOGarantiasCliente;
import dao.interfaces.DAOGarantiasProveedor;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanBuscar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleUnidadDAO}")
	private DAOCompraDetalleUnidad compraDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanDevolucionDAO}")
	private DAODevolucion devolucionDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanGarantiasClienteDAO}")
	private DAOGarantiasCliente garantiasClienteDAO;
	
	@ManagedProperty(value = "#{BeanGarantiasProveedorDAO}")
	private DAOGarantiasProveedor garantiasProveedorDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	private List<Producto> listaProductos;
	private List<StockDisponible> listaStocks;
	private List<Consignacion> listaConsignacions;
	private List<Venta> listaVentas;
	private List<ListaPrecio> listaListaPrecios;
	private Usuario usuario;
	private Compra compra;
	private Venta venta;
	private Consignacion consignacion;
	private Producto producto;
	private Devolucione devolucion;
	private UnidadMovil unidadMovil;
	private VentasCon ventasCon;
	private GarantiasCliente garantiasCliente;
	private GarantiasProveedore garantiasProveedor;
	private ListaPrecioProducto listaProducto;
	private String nroImei;
	private String disponibleConsignacion;
	private int idProducto;
	private int idListaPrecio;
	private boolean panelProducto;
	private boolean panelCompra;
	private boolean tableCompra;
	private boolean panelVenta;
	private boolean tableVenta;
	private boolean panelConsignacion;
	private boolean tableConsignacion;
	private boolean panelVentaCons;
	private boolean tableVentaCons;
	private boolean panelDevolucion;
	private boolean panelGarantia;
	private boolean panelStocks;
	private boolean panelConsignacionProducto;
	private boolean panelVentaProducto;
	private boolean panelGarantiaCliente;
	private boolean panelGarantiaProveedor;
	private boolean panelListaPrecio;
	private boolean opcionGarantia2;
	private boolean opcionGarantia3;
	private boolean bajaStock;

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public DAOCompraDetalleUnidad getCompraDetalleUnidadDAO() {
		return compraDetalleUnidadDAO;
	}

	public void setCompraDetalleUnidadDAO(
			DAOCompraDetalleUnidad compraDetalleUnidadDAO) {
		this.compraDetalleUnidadDAO = compraDetalleUnidadDAO;
	}

	public DAOVentaDetalleUnidad getVentaDetalleUnidadDAO() {
		return ventaDetalleUnidadDAO;
	}

	public void setVentaDetalleUnidadDAO(DAOVentaDetalleUnidad ventaDetalleUnidadDAO) {
		this.ventaDetalleUnidadDAO = ventaDetalleUnidadDAO;
	}

	public DAOConsignacionDetalleUnidad getConsignacionDetalleUnidadDAO() {
		return consignacionDetalleUnidadDAO;
	}

	public void setConsignacionDetalleUnidadDAO(
			DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO) {
		this.consignacionDetalleUnidadDAO = consignacionDetalleUnidadDAO;
	}

	public DAODevolucion getDevolucionDAO() {
		return devolucionDAO;
	}

	public void setDevolucionDAO(DAODevolucion devolucionDAO) {
		this.devolucionDAO = devolucionDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOConsignacion getConsignacionDAO() {
		return consignacionDAO;
	}

	public void setConsignacionDAO(DAOConsignacion consignacionDAO) {
		this.consignacionDAO = consignacionDAO;
	}

	public DAOVentaConsignacion getVentaConsignacionDAO() {
		return ventaConsignacionDAO;
	}

	public void setVentaConsignacionDAO(DAOVentaConsignacion ventaConsignacionDAO) {
		this.ventaConsignacionDAO = ventaConsignacionDAO;
	}

	public DAOVentaConsignacionDetalleUnidad getVentaConsignacionDetalleUnidadDAO() {
		return ventaConsignacionDetalleUnidadDAO;
	}

	public void setVentaConsignacionDetalleUnidadDAO(
			DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO) {
		this.ventaConsignacionDetalleUnidadDAO = ventaConsignacionDetalleUnidadDAO;
	}

	public DAOVenta getVentaDAO() {
		return ventaDAO;
	}

	public void setVentaDAO(DAOVenta ventaDAO) {
		this.ventaDAO = ventaDAO;
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

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<StockDisponible> getListaStocks() {
		return listaStocks;
	}

	public void setListaStocks(List<StockDisponible> listaStocks) {
		this.listaStocks = listaStocks;
	}

	public List<Consignacion> getListaConsignacions() {
		return listaConsignacions;
	}

	public void setListaConsignacions(List<Consignacion> listaConsignacions) {
		this.listaConsignacions = listaConsignacions;
	}

	public List<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public List<ListaPrecio> getListaListaPrecios() {
		return listaListaPrecios;
	}

	public void setListaListaPrecios(List<ListaPrecio> listaListaPrecios) {
		this.listaListaPrecios = listaListaPrecios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Devolucione getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucione devolucion) {
		this.devolucion = devolucion;
	}

	public UnidadMovil getUnidadMovil() {
		return unidadMovil;
	}

	public void setUnidadMovil(UnidadMovil unidadMovil) {
		this.unidadMovil = unidadMovil;
	}

	public VentasCon getVentasCon() {
		return ventasCon;
	}

	public void setVentasCon(VentasCon ventasCon) {
		this.ventasCon = ventasCon;
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

	public ListaPrecioProducto getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(ListaPrecioProducto listaProducto) {
		this.listaProducto = listaProducto;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}
	
	public String getDisponibleConsignacion() {
		return disponibleConsignacion;
	}

	public void setDisponibleConsignacion(String disponibleConsignacion) {
		this.disponibleConsignacion = disponibleConsignacion;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdListaPrecio() {
		return idListaPrecio;
	}

	public void setIdListaPrecio(int idListaPrecio) {
		this.idListaPrecio = idListaPrecio;
	}

	public boolean isPanelProducto() {
		return panelProducto;
	}

	public void setPanelProducto(boolean panelProducto) {
		this.panelProducto = panelProducto;
	}

	public boolean isPanelCompra() {
		return panelCompra;
	}

	public void setPanelCompra(boolean panelCompra) {
		this.panelCompra = panelCompra;
	}

	public boolean isTableCompra() {
		return tableCompra;
	}

	public void setTableCompra(boolean tableCompra) {
		this.tableCompra = tableCompra;
	}

	public boolean isPanelVenta() {
		return panelVenta;
	}

	public void setPanelVenta(boolean panelVenta) {
		this.panelVenta = panelVenta;
	}

	public boolean isTableVenta() {
		return tableVenta;
	}

	public void setTableVenta(boolean tableVenta) {
		this.tableVenta = tableVenta;
	}

	public boolean isPanelConsignacion() {
		return panelConsignacion;
	}

	public void setPanelConsignacion(boolean panelConsignacion) {
		this.panelConsignacion = panelConsignacion;
	}

	public boolean isTableConsignacion() {
		return tableConsignacion;
	}

	public void setTableConsignacion(boolean tableConsignacion) {
		this.tableConsignacion = tableConsignacion;
	}

	public boolean isPanelVentaCons() {
		return panelVentaCons;
	}

	public void setPanelVentaCons(boolean panelVentaCons) {
		this.panelVentaCons = panelVentaCons;
	}

	public boolean isTableVentaCons() {
		return tableVentaCons;
	}

	public void setTableVentaCons(boolean tableVentaCons) {
		this.tableVentaCons = tableVentaCons;
	}

	public boolean isPanelDevolucion() {
		return panelDevolucion;
	}

	public void setPanelDevolucion(boolean panelDevolucion) {
		this.panelDevolucion = panelDevolucion;
	}

	public boolean isPanelGarantia() {
		return panelGarantia;
	}

	public void setPanelGarantia(boolean panelGarantia) {
		this.panelGarantia = panelGarantia;
	}

	public boolean isPanelStocks() {
		return panelStocks;
	}

	public void setPanelStocks(boolean panelStocks) {
		this.panelStocks = panelStocks;
	}

	public boolean isPanelConsignacionProducto() {
		return panelConsignacionProducto;
	}

	public void setPanelConsignacionProducto(boolean panelConsignacionProducto) {
		this.panelConsignacionProducto = panelConsignacionProducto;
	}

	public boolean isPanelVentaProducto() {
		return panelVentaProducto;
	}

	public void setPanelVentaProducto(boolean panelVentaProducto) {
		this.panelVentaProducto = panelVentaProducto;
	}

	public boolean isPanelGarantiaCliente() {
		return panelGarantiaCliente;
	}

	public void setPanelGarantiaCliente(boolean panelGarantiaCliente) {
		this.panelGarantiaCliente = panelGarantiaCliente;
	}

	public boolean isPanelGarantiaProveedor() {
		return panelGarantiaProveedor;
	}

	public void setPanelGarantiaProveedor(boolean panelGarantiaProveedor) {
		this.panelGarantiaProveedor = panelGarantiaProveedor;
	}

	public boolean isPanelListaPrecio() {
		return panelListaPrecio;
	}

	public void setPanelListaPrecio(boolean panelListaPrecio) {
		this.panelListaPrecio = panelListaPrecio;
	}

	public boolean isOpcionGarantia2() {
		return opcionGarantia2;
	}

	public void setOpcionGarantia2(boolean opcionGarantia2) {
		this.opcionGarantia2 = opcionGarantia2;
	}

	public boolean isOpcionGarantia3() {
		return opcionGarantia3;
	}

	public void setOpcionGarantia3(boolean opcionGarantia3) {
		this.opcionGarantia3 = opcionGarantia3;
	}

	public boolean isBajaStock() {
		return bajaStock;
	}

	public void setBajaStock(boolean bajaStock) {
		this.bajaStock = bajaStock;
	}

	public String goBusqueda(Usuario user){
		usuario = new Usuario();
		compra = new Compra();
		venta = new Venta();
		consignacion = new Consignacion();
		producto = new Producto();
		devolucion = new Devolucione();
		unidadMovil = new UnidadMovil();
		listaProducto = new ListaPrecioProducto();
		usuario = user;
		nroImei = "";
		panelProducto = false;
		panelCompra = false;
		tableCompra = false;
		panelVenta = false;
		tableVenta = false;
		panelConsignacion = false;
		tableConsignacion = false;
		panelVentaCons = false;
		tableVentaCons = false;
		panelDevolucion = false;
		panelGarantia = false;
		panelStocks = false;
		panelConsignacionProducto = false;
		panelVentaProducto = false;
		panelGarantiaCliente = false;
		panelGarantiaProveedor = false;
		panelListaPrecio = false;
		listaProductos = new ArrayList<Producto>();
		listaStocks = new ArrayList<StockDisponible>();
		listaConsignacions = new ArrayList<Consignacion>();
		listaVentas = new ArrayList<Venta>();
		listaListaPrecios = new ArrayList<ListaPrecio>();
		idProducto = 0;
		idListaPrecio = 0;
		Rubro rubro = new Rubro();
		garantiasCliente = new GarantiasCliente();
		garantiasProveedor = new GarantiasProveedore();
		rubro.setId(1);
		listaProductos = productoDAO.getLista(rubro);
		listaListaPrecios = listaPrecioDAO.getLista(true);
		return "busqueda";
	}
	
	public List<UnidadMovil> completeText(String query){
		List<UnidadMovil> listAux = unidadMovilDAO.getLike(query);
		List<String> lista = new ArrayList<String>();
		for (UnidadMovil unidadMovil : listAux) {
			String unidad = unidadMovil.getNroImei() + " - " + unidadMovil.getProducto().getNombre();
			lista.add(unidad);
		}
		return listAux;
	}
	
	public void buscar(){
		compra = new Compra();
		venta = new Venta();
		consignacion = new Consignacion();
		producto = new Producto();
		devolucion = new Devolucione();
		garantiasCliente = new GarantiasCliente();
		garantiasProveedor = new GarantiasProveedore();
		listaProducto = new ListaPrecioProducto();
		panelProducto = false;
		panelCompra = false;
		tableCompra = false;
		panelVenta = false;
		tableVenta = false;
		panelConsignacion = false;
		tableConsignacion = false;
		panelVentaCons = false;
		tableVentaCons = false;
		panelDevolucion = false;
		panelGarantia = false;
		panelStocks = false;
		panelConsignacionProducto = false;
		panelVentaProducto = false;
		panelGarantiaCliente = false;
		panelGarantiaProveedor = false;
		panelListaPrecio = false;
		bajaStock = false;
		try{
			if(unidadMovil.getId() == 0 && idProducto == 0){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un nro de imei o seleccionar un producto!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			if(unidadMovil.getId() != 0 && idProducto != 0){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un nro de imei o seleccionar un producto, no puede realizar una búsqueda por ambos!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			if(unidadMovil.getId() != 0 && idProducto == 0){
				nroImei = unidadMovil.getNroImei();
				if (unidadMovil.getBajaStock()) {
					bajaStock = true;
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El móvil ha sido dado de baja desde el stock, presione en 'Volver al stock' para habilitarlo nuevamente.", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
	//			UnidadMovil unidad = unidadMovilDAO.get(nroImei);
				if(unidadMovil.getId() != 0){
					producto = unidadMovil.getProducto();
					panelProducto = true;
					ComprasDetalleUnidad compraUnidad = compraDetalleUnidadDAO.get(nroImei);
					if(compraUnidad.getId() != 0){
						ComprasDetalle compraDetalle = compraUnidad.getComprasDetalle();
						compra = compraDetalle.getCompra();
						if(compra.getEstado()){
							panelCompra = true;
							tableCompra = true;
						}
					}
					VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(nroImei);
					if(ventaUnidad.getId() != 0){
						VentasDetalle ventaDetalle = ventaUnidad.getVentasDetalle();
						venta = ventaDetalle.getVenta();
						if(venta.getEstado()){
							panelVenta = true;
							tableVenta = true;
						}
					}
					ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(nroImei);
					if(consignacionUnidad.getId() != 0){
						if(consignacionUnidad.getVendido()){
							disponibleConsignacion = "Móvil Vendido";
						}else{
							disponibleConsignacion = "Móvil Disponible para Venta";
						}
						ConsignacionsDetalle consignacionDetalle = consignacionUnidad.getConsignacionsDetalle();
						consignacion = consignacionDetalle.getConsignacion();
						if(consignacion.getEstado()){
							panelConsignacion = true;
							tableConsignacion = true;
						}
					}
					VentasConsDetalleUnidad ventaConsUnidad = ventaConsignacionDetalleUnidadDAO.get(nroImei);
					if(ventaConsUnidad.getId() != 0){
						VentasConsDetalle ventaConsDetalle = ventaConsUnidad.getVentasConsDetalle();
						ventasCon = ventaConsDetalle.getVentasCon();
						if(ventasCon.getEstado()){
							panelVentaCons = true;
							tableVentaCons = true;
						}
					}
					List<GarantiasCliente> listaGarantiasClientes = garantiasClienteDAO.getLista(nroImei);
					if (!listaGarantiasClientes.isEmpty()) {
						for (GarantiasCliente gCliente : listaGarantiasClientes) {
							garantiasCliente = new GarantiasCliente();
							garantiasCliente = gCliente;
						}
						if (garantiasCliente.getId() != 0) {
							panelGarantiaCliente = true;
							if (garantiasCliente.getFinalizado()) {
								opcionGarantia2 = false;
								opcionGarantia3 = false;
								if (garantiasCliente.getResolucion().equals("Cambio de Equipo")) {
									opcionGarantia2 = true;
								}
								if (garantiasCliente.getResolucion().equals("No posee arreglo")) {
									opcionGarantia3 = true;
								}
							}
						}
					}
					List<GarantiasProveedore> listaGarantiasProveedores = garantiasProveedorDAO.getLista(nroImei);
					if (!listaGarantiasProveedores.isEmpty()) {						
						for (GarantiasProveedore garantiasProveedore : listaGarantiasProveedores) {
							garantiasProveedor = new GarantiasProveedore();
							garantiasProveedor = garantiasProveedore;
						}
						if (garantiasProveedor.getId() != 0) {
							panelGarantiaProveedor = true;
							if (garantiasProveedor.getFinalizado()) {
								opcionGarantia2 = false;
								opcionGarantia3 = false;
								if (garantiasProveedor.getConcepto().equals("Entrega por garantía")) {
									if (garantiasProveedor.getResolucion().equals("Cambio de Equipo")) {
										opcionGarantia2 = true;
									}
									if (garantiasProveedor.getResolucion().equals("No posee arreglo")) {
										opcionGarantia3 = true;
									}
								}							
							}
						}
					}
				}else{
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei no tiene ningún producto asociado!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
			unidadMovil = new UnidadMovil();
			if(unidadMovil.getId() == 0 && idProducto != 0 && idListaPrecio == 0){
				nroImei = " - ";
				panelProducto = true;
				producto = productoDAO.get(idProducto);
				listaStocks = new ArrayList<StockDisponible>();
				listaConsignacions = new ArrayList<Consignacion>();
				listaVentas = new ArrayList<Venta>();
				List<UnidadMovil> listAux = unidadMovilDAO.getListaEnStockOrdenPrecio(true, true, producto);
				List<UnidadMovil> listAux2 = new ArrayList<UnidadMovil>();
				boolean primera = true;
				float precioAnterior = 0;
				int cantidad = 0;
				StockDisponible stockDisponible = new StockDisponible();
				for (UnidadMovil unidadMovil : listAux) {
					if(primera){
						cantidad = cantidad + 1;
						precioAnterior = unidadMovil.getPrecioCompra();
						listAux2.add(unidadMovil);
						primera = false;
					}else{
						if(precioAnterior == unidadMovil.getPrecioCompra()){
							cantidad = cantidad + 1;
							listAux2.add(unidadMovil);
						}else{
							stockDisponible = new StockDisponible();
							stockDisponible.setCantidad(cantidad);
							stockDisponible.setListaUnidadMovils(listAux2);
							stockDisponible.setPrecio(precioAnterior);
							listaStocks.add(stockDisponible);
							cantidad = 0;
							precioAnterior = unidadMovil.getPrecioCompra();
							listAux2 = new ArrayList<UnidadMovil>();
							cantidad = cantidad + 1;
							listAux2.add(unidadMovil);
						}
					}
				}
				stockDisponible = new StockDisponible();
				stockDisponible.setCantidad(cantidad);
				stockDisponible.setListaUnidadMovils(listAux2);
				stockDisponible.setPrecio(precioAnterior);
				listaStocks.add(stockDisponible);
				if(!listaStocks.isEmpty()){
					panelStocks = true;
				}
				listaConsignacions = consignacionDAO.getLista(producto);
				if(!listaConsignacions.isEmpty()){
					panelConsignacionProducto = true;
				}
				listaVentas = ventaDAO.getLista(producto);
				if (!listaVentas.isEmpty()) {
					panelVentaProducto = true;
				}
			}
			if(unidadMovil.getId() == 0 && idProducto != 0 && idListaPrecio != 0){
				producto = new Producto();
				listaProducto = new ListaPrecioProducto();
				ListaPrecio lista = new ListaPrecio();
				
				producto = productoDAO.get(idProducto);				
				lista = listaPrecioDAO.get(idListaPrecio);
				listaProducto = listaPrecioDAO.getItemProducto(lista, producto);
				if (listaProducto.getId() != 0) {
					panelListaPrecio = true;
				} else {
					panelListaPrecio = false;
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No Existe Producto para esa Lista de Precio", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei no tiene ningún producto asociado!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void volverStock() {
		System.out.println("volverStock()");
		try {
			UnidadMovil unidad = unidadMovilDAO.getBajaStock(nroImei);
			if (unidad.getId() != 0) {
				unidad.setBajaStock(false);
				unidad.setEstado(true);
				unidad.setEnStock(true);
				unidad.setFechaMod(new Date());
				unidad.setUsuario3(usuario);
				int updt = unidadMovilDAO.update(unidad);
				if (updt != 0) {
					bajaStock = false;
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registró nuevamente en el stock con éxito.", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al volver al stock el móvil, error: No se pudo actualizar el estado del móvill.", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al volver al stock el móvil, error: El número de imei no corresponde a un único móvil.", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al volver al stock el móvil, error: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
