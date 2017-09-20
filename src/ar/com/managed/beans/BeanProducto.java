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

import org.apache.log4j.Logger;

import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Productos;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.Stock;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.VentasDetalleUnidad;
import model.entity.HistorialMovil;
import dao.interfaces.DAOCompraDetalleUnidad;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOHistorialMovil;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAORubro;
import dao.interfaces.DAOStock;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVentaDetalle;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanProducto.class);
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleDAO}")
	private DAOVentaDetalle ventaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleUnidadDAO}")
	private DAOCompraDetalleUnidad compraDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanRubroDAO}")
	private DAORubro rubroDAO;
	
	@ManagedProperty(value = "#{BeanStockDAO}")
	private DAOStock stockDAO;
	
	@ManagedProperty(value = "#{BeanHistorialMovilDAO}")
	private DAOHistorialMovil historialMovilDAO;
	
	private List<Producto> listaProductos;
	private List<Producto> filteredProductos;
	private List<UnidadMovil> listaUnidadMovils;
	private List<UnidadMovil> filteredUnidadMovils;
	private List<UnidadMovil> listaUnidadMovilsDelete;
	private List<Rubro> listaRubros;
	private List<Stock> listaStocks;
	private Producto producto;
	private Usuario usuario;
	private UnidadMovil unidadMovil;
	private String headerText;
	private String nroImei;
	private String descripcion;
	private int estado;
	private int idRubro;
	private int cantidad;
	private float precioCompra;

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

	public DAOConsignacionDetalle getConsignacionDetalleDAO() {
		return consignacionDetalleDAO;
	}

	public void setConsignacionDetalleDAO(
			DAOConsignacionDetalle consignacionDetalleDAO) {
		this.consignacionDetalleDAO = consignacionDetalleDAO;
	}

	public DAOConsignacionDetalleUnidad getConsignacionDetalleUnidadDAO() {
		return consignacionDetalleUnidadDAO;
	}

	public void setConsignacionDetalleUnidadDAO(
			DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO) {
		this.consignacionDetalleUnidadDAO = consignacionDetalleUnidadDAO;
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

	public DAOCompraDetalleUnidad getCompraDetalleUnidadDAO() {
		return compraDetalleUnidadDAO;
	}

	public void setCompraDetalleUnidadDAO(
			DAOCompraDetalleUnidad compraDetalleUnidadDAO) {
		this.compraDetalleUnidadDAO = compraDetalleUnidadDAO;
	}

	public DAORubro getRubroDAO() {
		return rubroDAO;
	}

	public void setRubroDAO(DAORubro rubroDAO) {
		this.rubroDAO = rubroDAO;
	}

	public DAOStock getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(DAOStock stockDAO) {
		this.stockDAO = stockDAO;
	}

	public DAOHistorialMovil getHistorialMovilDAO() {
		return historialMovilDAO;
	}

	public void setHistorialMovilDAO(DAOHistorialMovil historialMovilDAO) {
		this.historialMovilDAO = historialMovilDAO;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Producto> getFilteredProductos() {
		return filteredProductos;
	}

	public void setFilteredProductos(List<Producto> filteredProductos) {
		this.filteredProductos = filteredProductos;
	}

	public List<UnidadMovil> getListaUnidadMovils() {
		return listaUnidadMovils;
	}

	public void setListaUnidadMovils(List<UnidadMovil> listaUnidadMovils) {
		this.listaUnidadMovils = listaUnidadMovils;
	}

	public List<UnidadMovil> getFilteredUnidadMovils() {
		return filteredUnidadMovils;
	}

	public void setFilteredUnidadMovils(List<UnidadMovil> filteredUnidadMovils) {
		this.filteredUnidadMovils = filteredUnidadMovils;
	}

	public List<UnidadMovil> getListaUnidadMovilsDelete() {
		return listaUnidadMovilsDelete;
	}

	public void setListaUnidadMovilsDelete(List<UnidadMovil> listaUnidadMovilsDelete) {
		this.listaUnidadMovilsDelete = listaUnidadMovilsDelete;
	}

	public List<Rubro> getListaRubros() {
		return listaRubros;
	}

	public void setListaRubros(List<Rubro> listaRubros) {
		this.listaRubros = listaRubros;
	}

	public List<Stock> getListaStocks() {
		return listaStocks;
	}

	public void setListaStocks(List<Stock> listaStocks) {
		this.listaStocks = listaStocks;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String goProductos(Usuario user){
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		listaUnidadMovilsDelete = new ArrayList<UnidadMovil>();
		Rubro rub = new Rubro();
		rub.setId(1);
//		listaProductos = productoDAO.getLista(true, rub);
		listaProductos = productoDAO.getLista(rub);
		filteredProductos = listaProductos;
		usuario = new Usuario();
		usuario = user;
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		estado = 0;
		precioCompra = 0;
		nroImei = "";
		return "productos";
	}
	
	public String goAccesorios(Usuario user){
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(2);
//		listaProductos = productoDAO.getLista(true, rub);
		listaProductos = productoDAO.getLista(rub);
		filteredProductos = listaProductos;
		usuario = new Usuario();
		usuario = user;
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		estado = 0;
		precioCompra = 0;
		nroImei = "";
		return "accesorios";
	}
	
	public String goUsados(Usuario user){
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(3);
		listaProductos = productoDAO.getLista(true, rub);
		filteredProductos = listaProductos;
		usuario = new Usuario();
		usuario = user;
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		estado = 0;
		precioCompra = 0;
		nroImei = "";
		return "usados";
	}
	
	public String goProductoNuevo(){
		producto = new Producto();
		headerText = "Móvil Nuevo";
		return "producto";
	}
	
	public String goAccesorioNuevo(){
		producto = new Producto();
		headerText = "Accesorio Nuevo";
		return "accesorio";
	}
	
	public String goUsadoNuevo(){
		producto = new Producto();
		headerText = "Móvil Usado";
		return "usado";
	}
	
	public String goProductoEditar(Producto prod){
		producto = new Producto();
		producto = prod;
		headerText = "Modificar Móvil";
		return "producto";
	}
	
	public String goAccesorioEditar(Producto prod){
		producto = new Producto();
		producto = prod;
		headerText = "Modificar Accesorio";
		return "accesorio";
	}
	
	public String goUsadoEditar(Producto prod){
		producto = new Producto();
		producto = prod;
		headerText = "Modificar Móvil Usado";
		return "usado";
	}
	
	public String volver() {
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		listaUnidadMovilsDelete = new ArrayList<UnidadMovil>();
		Rubro rub = new Rubro();
		rub.setId(1);
//		listaProductos = productoDAO.getLista(true, rub);
		listaProductos = productoDAO.getLista(rub);
		filteredProductos = listaProductos;
		producto = new Producto();
		unidadMovil = new UnidadMovil();
		estado = 0;
		precioCompra = 0;
		nroImei = "";
		return "productos";
	}
	
	public void filtroProducto(){
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(1);
		if(estado == 0){
			listaProductos = productoDAO.getLista(rub);
		}
		if(estado != 0){
			if(estado == 1){
				listaProductos = productoDAO.getLista(true, rub);
			}
			if(estado == 2){
				listaProductos = productoDAO.getLista(false, rub);
			}
		}
		filteredProductos = listaProductos;
	}
	
	public void filtroAccesorios(){
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(2);
		if(estado == 0){
			listaProductos = productoDAO.getLista(rub);
		}
		if(estado != 0){
			if(estado == 1){
				listaProductos = productoDAO.getLista(true, rub);
			}
			if(estado == 2){
				listaProductos = productoDAO.getLista(false, rub);
			}
		}
		filteredProductos = listaProductos;
	}
	
	public void filtroUsados(){
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(3);
		if(estado == 0){
			listaProductos = productoDAO.getLista(rub);
		}
		if(estado != 0){
			if(estado == 1){
				listaProductos = productoDAO.getLista(true, rub);
			}
			if(estado == 2){
				listaProductos = productoDAO.getLista(false, rub);
			}
		}
		filteredProductos = listaProductos;
	}
	
	public void alta(Producto prod){
		FacesMessage msg = null;
		try {
			prod.setEstado(true);
			prod.setFechaAlta(new Date());
			prod.setUsuario1(usuario);
			if(productoDAO.update(prod) != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta registrada.", null);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar el alta, "
						+ "intente nuevamente.", null);
			}
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar el alta, "
					+ "error: " + e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(Producto prod){
		FacesMessage msg = null;
		try {
			int enCons = getEnConsignacion(prod);
			if (enCons <= 0) {
				prod.setEstado(false);
				prod.setFechaBaja(new Date());
				prod.setUsuario2(usuario);				
				if(productoDAO.update(prod) != 0){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja registrada.", null);
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar la baja, "
							+ "intente nuevamente.", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible realizar la baja, "
						+ "posee equipos en consignación.", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar la baja, "
					+ "error: " + e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public int getEnStock(Producto prod) {
		int enStock = 0;
		List<UnidadMovil> listStocks = unidadMovilDAO.getListaEnStock(true, prod, false, true);
		enStock = listStocks.size();
		return enStock;
	}
	
	public int getEnStockAccesorio(Producto prod) {
		List<Stock> listaStocks = stockDAO.getLista(prod);
		int stock = 0;
		for (Stock sProd : listaStocks) {
			stock = stock + sProd.getCantidad();
		}
		return stock;
	}
	
	public int getEnConsignacion(Producto prod) {
		int enConsignacion = 0;
		List<UnidadMovil> listConsignacions = unidadMovilDAO.getListaEnStock(true, prod, true, true);
		enConsignacion = listConsignacions.size();
		return enConsignacion;
	}
	
	public String guardarProducto(){
		FacesMessage msg = null;
		String retorno = "";
		if(!producto.getMarca().isEmpty() && !producto.getModelo().isEmpty()){
			String nombre = producto.getMarca() + " - " + producto.getModelo();
			Rubro rubro = new Rubro();
			rubro.setId(1);
			producto.setNombre(nombre);
			producto.setRubro(rubro);
			int idProducto = 0;			
			if(producto.getId() != 0){
				producto.setFechaMod(new Date());
				producto.setUsuario3(usuario);
				idProducto = productoDAO.update(producto);
			}else{
				producto.setFechaAlta(new Date());
				producto.setUsuario1(usuario);
				producto.setEstado(true);
				idProducto = productoDAO.insertar(producto);
			}
			if(idProducto != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto guardado!", null);
				retorno = "productos";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Producto, "
						+ "intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Marca y modelo son campos obligatorios!", null);
		}
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
//		listaProductos = productoDAO.getLista(true, rub);
		listaProductos = productoDAO.getLista(rub);
		filteredProductos = listaProductos;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarAccesorio(){
		FacesMessage msg = null;
		String retorno = "";
		if(!producto.getMarca().isEmpty() && !producto.getModelo().isEmpty()){
			String nombre = producto.getMarca() + " - " + producto.getModelo();
			Rubro rubro = new Rubro();
			rubro.setId(2);
			producto.setNombre(nombre);
			producto.setRubro(rubro);
			int idProducto = 0;			
			if(producto.getId() != 0){
				producto.setFechaMod(new Date());
				producto.setUsuario3(usuario);
				idProducto = productoDAO.update(producto);
			}else{
				producto.setFechaAlta(new Date());
				producto.setUsuario1(usuario);
				producto.setEstado(true);
				idProducto = productoDAO.insertar(producto);
			}
			if(idProducto != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto guardado!", null);
				retorno = "accesorios";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Producto, "
						+ "intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Marca y modelo son campos obligatorios!", null);
		}
		Rubro rub = new Rubro();
		rub.setId(2);
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		listaProductos = productoDAO.getLista(true, rub);
		filteredProductos = listaProductos;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarUsado(){
		FacesMessage msg = null;
		String retorno = "";
		if(!producto.getMarca().isEmpty() && !producto.getModelo().isEmpty()){
			String nombre = producto.getMarca() + " - " + producto.getModelo();
			Rubro rubro = new Rubro();
			rubro.setId(3);
			producto.setNombre(nombre);
			producto.setRubro(rubro);
			int idProducto = 0;			
			if(producto.getId() != 0){
				producto.setFechaMod(new Date());
				producto.setUsuario3(usuario);
				idProducto = productoDAO.update(producto);
			}else{
				producto.setFechaAlta(new Date());
				producto.setUsuario1(usuario);
				producto.setEstado(true);
				idProducto = productoDAO.insertar(producto);
			}
			if(idProducto != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto guardado!", null);
				retorno = "usados";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Producto, "
						+ "intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Marca y modelo son campos obligatorios!", null);
		}
		Rubro rub = new Rubro();
		rub.setId(3);
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		listaProductos = productoDAO.getLista(true, rub);
		filteredProductos = listaProductos;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String verStock(Producto prod){
		precioCompra = 0;
		nroImei = "";
		unidadMovil = new UnidadMovil();
		producto = new Producto();
		producto = prod;
		listaUnidadMovils = null;
		filteredUnidadMovils = null;
		listaUnidadMovilsDelete = new ArrayList<UnidadMovil>();
		listaUnidadMovils = new ArrayList<UnidadMovil>();
		filteredUnidadMovils = new ArrayList<UnidadMovil>();
		listaUnidadMovils = unidadMovilDAO.getListaEnStock(true, prod, false, true);
		filteredUnidadMovils = listaUnidadMovils;
		return "stock";
	}
	
	public String verStockDesdeHistorial(String imei){
		log.info("Imei: " + imei);
		UnidadMovil unidad = unidadMovilDAO.get(imei);
		precioCompra = 0;
		nroImei = "";
		unidadMovil = new UnidadMovil();
		producto = new Producto();
		producto = unidad.getProducto();
		listaUnidadMovils = null;
		filteredUnidadMovils = null;
		listaUnidadMovilsDelete = new ArrayList<UnidadMovil>();
		listaUnidadMovils = new ArrayList<UnidadMovil>();
		filteredUnidadMovils = new ArrayList<UnidadMovil>();
		listaUnidadMovils = unidadMovilDAO.getListaEnStock(true, producto, false, true);
		filteredUnidadMovils = listaUnidadMovils;
		return "stock";
	}
	
	public void verEnConsignacion(Producto prod) {
		producto = new Producto();
		producto = prod;
		listaUnidadMovils = null;
		filteredUnidadMovils = null;
		listaUnidadMovils = new ArrayList<UnidadMovil>();
		filteredUnidadMovils = new ArrayList<UnidadMovil>();
		listaUnidadMovils = unidadMovilDAO.getListaEnStock(true, prod, true, true);
		filteredUnidadMovils = listaUnidadMovils;
	}
	
	public void verStockAccesorio(Producto prod){
		precioCompra = 0;
		cantidad = 0;
		producto = new Producto();
		producto = prod;
		listaStocks = new ArrayList<Stock>();
		listaStocks = stockDAO.getLista(prod);
	}
	
	public String verStockUsado(Producto prod){
		precioCompra = 0;
		nroImei = "";
		unidadMovil = new UnidadMovil();
		producto = new Producto();
		producto = prod;
		listaUnidadMovils = null;
		filteredUnidadMovils = null;
		listaUnidadMovils = new ArrayList<UnidadMovil>();
		filteredUnidadMovils = new ArrayList<UnidadMovil>();
		listaUnidadMovils = unidadMovilDAO.getListaEnStock(true, prod);
		filteredUnidadMovils = listaUnidadMovils;
		return "stockUsados";
	}
	
	public void agregarStock(){
		unidadMovil = new UnidadMovil();
		if(!nroImei.isEmpty() && precioCompra != 0){
			log.info("Imei: " + nroImei + " precioCompra: " + precioCompra);
			UnidadMovil unidad = new UnidadMovil();
			unidad = unidadMovilDAO.get(nroImei);
			if(unidad.getId() == 0){
				boolean noExiste = true;
				for(UnidadMovil unidad2 : listaUnidadMovils){
					if(unidad2.getNroImei().equals(nroImei)){
						noExiste = false;
					}
				}
				if(noExiste){
					filteredUnidadMovils = new ArrayList<UnidadMovil>();
					unidadMovil.setEstado(true);
					unidadMovil.setEnStock(true);
					unidadMovil.setEliminado(false);
					unidadMovil.setPrecioCompra(precioCompra);
					unidadMovil.setNroImei(nroImei);
					unidadMovil.setDescripcion(descripcion);
					unidadMovil.setProducto(producto);
					listaUnidadMovils.add(unidadMovil);	
					filteredUnidadMovils = listaUnidadMovils;
					nroImei = "";
					descripcion = "";
					
				}else{
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei ya se encuentra en la lista!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei ya se encuentra registrado!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei y/o el Precio de Compra no pueden estar vac�os!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void agregStock(){
		if (precioCompra != 0) {
			boolean noExiste = true;
			int cant = producto.getStock();
			cant = cant + cantidad;
			producto.setStock(cant);
			productoDAO.update(producto);
			List<Stock> listAux = listaStocks;
			listaStocks = new ArrayList<Stock>();
			for(Stock stock : listAux){
				if(precioCompra == stock.getPrecioCompra()){
					noExiste = false;
					cantidad = cantidad + stock.getCantidad();
					stock.setCantidad(cantidad);
					stock.setUsuario3(usuario);
					stock.setFechaMod(new Date());
					stockDAO.update(stock);
				}
				listaStocks.add(stock);
			}
			if(noExiste){
				Stock stock = new Stock();
				stock.setCantidad(cantidad);
				stock.setFechaAlta(new Date());
				stock.setPrecioCompra(precioCompra);
				stock.setProducto(producto);
				stock.setUsuario1(usuario);
				int idStock = stockDAO.insertar(stock);
				stock.setId(idStock);
				listaStocks.add(stock);
			}
			cantidad = 0;
			precioCompra = 0;
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Precio de Compra es obligatorio!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void quitarStock(UnidadMovil unidad){
		List<UnidadMovil> listAux = new ArrayList<UnidadMovil>();
		listAux = listaUnidadMovils;
		unidad.setEnStock(false);
		unidad.setBajaStock(true);
		unidad.setFechaBajaStock(new Date());
		unidad.setUsuario4(usuario);
		listaUnidadMovilsDelete.add(unidad);
		listaUnidadMovils = null;
		filteredUnidadMovils = null;
		listaUnidadMovils = new ArrayList<UnidadMovil>();
		filteredUnidadMovils = new ArrayList<UnidadMovil>();
		for (UnidadMovil unidadMovil : listAux) {
			if (!unidad.getNroImei().equals(unidadMovil.getNroImei())) {
				listaUnidadMovils.add(unidadMovil);
			}
		}
		filteredUnidadMovils = listaUnidadMovils;
	}
	
	public String guardarStock(){
		FacesMessage msg = null;
		String retorno = "";
		boolean insert = true;
		int insertHm = 0;
		int cant = 0;
		for (UnidadMovil unidadMovil : listaUnidadMovils) {
			//Pregunta si existe la unidadMovil, si existe no se inserta y sino si
			String nroImei = unidadMovil.getNroImei();
			UnidadMovil unidad = unidadMovilDAO.get(nroImei);
			if(unidad.getId() == 0){
				log.info("Insert nuevo stock");
				//Seteo valores predeterminados para stock nuevo
				unidadMovil.setEnStock(true);
				unidadMovil.setEstado(true);
				unidadMovil.setDevuelto(false);
				unidadMovil.setEliminado(false);
				unidadMovil.setEnCompra(false);
				unidadMovil.setEnConsignacion(false);
				unidadMovil.setEnVenta(false);
				unidadMovil.setProducto(producto);
				//Seteo auditoria
				unidadMovil.setFechaAlta(new Date());
				unidadMovil.setUsuario1(usuario);
				int insertUnidadMovil = unidadMovilDAO.insertar(unidadMovil);
				if(insertUnidadMovil == 0){
					insert = false;
				}else {
					log.info("Unidad movil guardada: " + insertUnidadMovil);
					HistorialMovil hm = new HistorialMovil();
					hm.setFecha(new Date());
					hm.setUsuario(usuario);
					hm.setImei(unidadMovil.getNroImei());
					hm.setTipo("ALTA STOCK");
					hm.setDescripcion("Alta de stock del movil");
					hm.setIdMovimiento(0);
					insertHm = historialMovilDAO.insert(hm);
				}
				
			}else{
				insertHm = 1;
				//Seteo auditoria para editar
				unidadMovil.setFechaMod(new Date());
				unidadMovil.setUsuario3(usuario);
				if(unidadMovilDAO.update(unidadMovil) == 0){
					insert = false;
				}else {
					log.info("Unidad movil actualizada: " + unidadMovil.getId());
				}
			}
			cant++;
		}
		for (UnidadMovil unidadMovil : listaUnidadMovilsDelete) {
			//Seteo valores de baja
			unidadMovil.setEnStock(false);
			unidadMovil.setEstado(false);
			unidadMovil.setEnConsignacion(false);
			unidadMovil.setEnVenta(false);
			unidadMovil.setBajaStock(true);
			unidadMovil.setFechaBajaStock(new Date());
			unidadMovil.setUsuario4(usuario);
			unidadMovil.setProducto(producto);
			//Seteo auditoria
			unidadMovil.setFechaBaja(new Date());
			unidadMovil.setUsuario2(usuario);
			unidadMovilDAO.update(unidadMovil);
		}
		producto.setStock(cant);
		int updateProd = productoDAO.update(producto);
		if(insert && updateProd != 0 && insertHm != 0){
			insertHm = 0;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Stock modificado correctamente!", null);
			retorno = "productos";
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al actualizar el Stock!", null);
		}
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = productoDAO.getLista(true, rub);
		filteredProductos = listaProductos;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarStockUsados(){
		FacesMessage msg = null;
		boolean delete = true;
		String retorno = "";
		for(UnidadMovil unidad : unidadMovilDAO.getListaEnStock(true, producto)){
			ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(unidad.getNroImei());
			VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(unidad.getNroImei());
			if(consignacionUnidad.getId() == 0 && ventaUnidad.getId() == 0){
				if(unidadMovilDAO.deletePorImei(unidad.getNroImei()) == 0){
					delete = false;
				}
			}
		}
		if(delete){
			boolean insert = true;
			int cant = 0;
			for (UnidadMovil unidadMovil : listaUnidadMovils) {
				ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(unidadMovil.getNroImei());
				VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(unidadMovil.getNroImei());
				if(consignacionUnidad.getId() == 0 && ventaUnidad.getId() == 0){
//					unidadMovil.setEnStock(true);
//					unidadMovil.setEstado(true);
					unidadMovil.setFechaAlta(new Date());
					unidadMovil.setUsuario1(usuario);
					unidadMovil.setProducto(producto);
					if(unidadMovilDAO.insertar(unidadMovil) == 0){
						insert = false;
					}
				}else{
					unidadMovil.setFechaMod(new Date());
					unidadMovil.setUsuario3(usuario);
					if(unidadMovilDAO.update(unidadMovil) == 0){
						insert = false;
					}
				}
				cant++;
			}
			producto.setStock(cant);
			if(insert && productoDAO.update(producto) != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Stock modificado correctamente!", null);
				retorno = "usados";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al actualizar el Stock!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al actualizar el Stock!", null);
		}
		listaProductos = new ArrayList<Producto>();
		filteredProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(3);
		listaProductos = productoDAO.getLista(true, rub);
		filteredProductos = listaProductos;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public boolean enConsignacion(UnidadMovil unidad){
		ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(unidad.getNroImei());
		if(consignacionUnidad.getId() != 0){
			ConsignacionsDetalle consignacionDetalle = consignacionUnidad.getConsignacionsDetalle();
			Consignacion consignacion = consignacionDetalle.getConsignacion();
			if(consignacion.getEstado()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean enConsignacionVenta(UnidadMovil unidad){
		if(unidad.getEnConsignacion()){
			return true;
		}else{
			if(unidad.getEnVenta()){
				return true;
			}else{
				if(unidad.getEnCompra()){
					return true;
				}else{
					return false;
				}
			}
		}
	}
	
	public void onCellEdit(UnidadMovil unidad){
		List<UnidadMovil> listAux = listaUnidadMovils;
		listaUnidadMovils = new ArrayList<UnidadMovil>();
		filteredUnidadMovils = new ArrayList<UnidadMovil>();
		for (UnidadMovil unidad2 : listAux) {
			if(unidad2.getNroImei().equals(unidad.getNroImei())){
				unidad2.setDescripcion(unidad.getDescripcion());
				unidad2.setPrecioCompra(unidad.getPrecioCompra());
			}
			listaUnidadMovils.add(unidad2);
		}
		filteredUnidadMovils = listaUnidadMovils;
	}
	
	public void onCellCancel(UnidadMovil unidad){
	}
	
	public void onStockEdit(Stock stock){
		List<Stock> listAux = listaStocks;
		listaStocks = new ArrayList<Stock>();
		int cant = 0;
		for (Stock stock2 : listAux) {
			if(stock2.getId() == stock.getId()){
				stock2.setPrecioCompra(stock.getPrecioCompra());
				stock2.setCantidad(stock.getCantidad());
				stockDAO.update(stock2);
			}
			cant = cant + stock2.getCantidad();
			listaStocks.add(stock2);
		}
		producto.setStock(cant);
		productoDAO.update(producto);
	}
	
	public void onStockCancel(Stock stock){
	}
	
	public void generarReporte(String nombre){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Productos> listProducto = new ArrayList<Productos>();
		for (Producto prod : filteredProductos) {
			Productos productos = new Productos();
			productos.setCosto(formatoMonto.format(prod.getPrecioCosto()));
			productos.setDescripcion(prod.getDescripcion());
			productos.setNombre(prod.getNombre());
			productos.setStock(prod.getStock());
			listProducto.add(productos);
		}
		reporte.generar(parametros, listProducto, nombre, "inline");
	}
	
	public void generarXLS(String nombre) {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Productos> listProducto = new ArrayList<Productos>();
		for (Producto prod : filteredProductos) {
			Productos productos = new Productos();
			productos.setCosto(formatoMonto.format(prod.getPrecioCosto()));
			productos.setDescripcion(prod.getDescripcion());
			productos.setNombre(prod.getNombre());
			productos.setStock(prod.getStock());
			listProducto.add(productos);
		}
		reporte.exportXls(parametros, listProducto, nombre, "inline");
	}
	
	public boolean checkStocks() {
		Rubro rub = new Rubro();
		rub.setId(1);
		List<Producto> listProductos = productoDAO.getListaDebajoMinimo(rub);
		boolean minimo = false;
		if (!listProductos.isEmpty()) {
			minimo = true;
		}
		return minimo;
	}
	
	public String goStocksMinimo() {
		try {
			Rubro rub = new Rubro();
			rub.setId(1);
			listaProductos = new ArrayList<Producto>();
			filteredProductos = new ArrayList<Producto>();
			listaProductos = productoDAO.getListaDebajoMinimo(rub);
			filteredProductos = listaProductos;
			return "sinstocks";
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible redirigir hacia el formulario, Intente nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
}
