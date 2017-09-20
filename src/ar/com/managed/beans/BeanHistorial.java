package ar.com.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import dao.interfaces.DAOCompra;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOHistorialMovil;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaConsignacion;
import model.entity.Compra;
import model.entity.Consignacion;
import model.entity.HistorialMovil;
import model.entity.Producto;
import model.entity.UnidadMovil;
import model.entity.Venta;
import model.entity.VentasCon;

@ManagedBean
@SessionScoped
public class BeanHistorial implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanHistorial.class);
	
	@ManagedProperty(value = "#{BeanHistorialMovilDAO}")
	private DAOHistorialMovil historialMovilDAO;
	
	@ManagedProperty(value = "#{BeanCompraDAO}")
	private DAOCompra compraDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	private List<HistorialMovil> historialMovilList;
	private Compra compra;
	private Consignacion consignacion;
	private Venta venta;
	private VentasCon vConsignacion;
	private Producto producto;
	private String imei;
	private boolean gridInfoCompra;
	private boolean gridInfoConsignacion;
	private boolean gridInfoVenta;
	private boolean gridInfoVentaConsignacion;
	
	public DAOHistorialMovil getHistorialMovilDAO() {
		return historialMovilDAO;
	}

	public void setHistorialMovilDAO(DAOHistorialMovil historialMovilDAO) {
		this.historialMovilDAO = historialMovilDAO;
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

	public DAOVentaConsignacion getVentaConsignacionDAO() {
		return ventaConsignacionDAO;
	}

	public void setVentaConsignacionDAO(DAOVentaConsignacion ventaConsignacionDAO) {
		this.ventaConsignacionDAO = ventaConsignacionDAO;
	}

	public DAOVenta getVentaDAO() {
		return ventaDAO;
	}

	public void setVentaDAO(DAOVenta ventaDAO) {
		this.ventaDAO = ventaDAO;
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

	public List<HistorialMovil> getHistorialMovilList() {
		return historialMovilList;
	}

	public void setHistorialMovilList(List<HistorialMovil> historialMovilList) {
		this.historialMovilList = historialMovilList;
	}
	
	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public VentasCon getvConsignacion() {
		return vConsignacion;
	}

	public void setvConsignacion(VentasCon vConsignacion) {
		this.vConsignacion = vConsignacion;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public boolean isGridInfoCompra() {
		return gridInfoCompra;
	}

	public void setGridInfoCompra(boolean gridInfoCompra) {
		this.gridInfoCompra = gridInfoCompra;
	}

	public boolean isGridInfoConsignacion() {
		return gridInfoConsignacion;
	}

	public void setGridInfoConsignacion(boolean gridInfoConsignacion) {
		this.gridInfoConsignacion = gridInfoConsignacion;
	}

	public boolean isGridInfoVenta() {
		return gridInfoVenta;
	}

	public void setGridInfoVenta(boolean gridInfoVenta) {
		this.gridInfoVenta = gridInfoVenta;
	}

	public boolean isGridInfoVentaConsignacion() {
		return gridInfoVentaConsignacion;
	}

	public void setGridInfoVentaConsignacion(boolean gridInfoVentaConsignacion) {
		this.gridInfoVentaConsignacion = gridInfoVentaConsignacion;
	}



	public String goHistorial(){
		return "historial";
	}
	
	public String verHistorial(String imeiSelected) {
		imei = imeiSelected;
		log.info("Imei: " +imei);
		gridInfoCompra = false;
		gridInfoVenta = false;
		gridInfoVentaConsignacion = false;
		gridInfoConsignacion = false;
		compra = new Compra();
		venta = new Venta();
		vConsignacion = new VentasCon();
		consignacion = new Consignacion();
		producto = new Producto();
		producto = unidadMovilDAO.get(imei).getProducto();
		historialMovilList = new ArrayList<HistorialMovil>();
		historialMovilList = historialMovilDAO.getPorImei(imei);
		return "historial";
	}
	
	public void mostrarPaneles(HistorialMovil hm) {
		log.info("Historial Movil: " + hm.getId());
		if(hm.getTipo().equals("COMPRA")) {
			compra = new Compra();
			compra = compraDAO.get(hm.getIdMovimiento());
			gridInfoCompra = true;
			gridInfoVenta = false;
			gridInfoVentaConsignacion = false;
			gridInfoConsignacion = false;
		}
		if(hm.getTipo().equals("VENTA")) {
			venta = new Venta();
			venta = ventaDAO.get(hm.getIdMovimiento());
			gridInfoCompra = false;
			gridInfoVenta = true;
			gridInfoVentaConsignacion = false;
			gridInfoConsignacion = false;
		}
		if(hm.getTipo().equals("VENTA CONSIGNACION")) {
			vConsignacion = new VentasCon();
			vConsignacion = ventaConsignacionDAO.get(hm.getIdMovimiento());
			gridInfoCompra = false;
			gridInfoVenta = false;
			gridInfoVentaConsignacion = true;
			gridInfoConsignacion = false;
		}
		if(hm.getTipo().equals("CONSIGNACION")) {
			consignacion = new Consignacion();
			consignacion = consignacionDAO.get(hm.getIdMovimiento());
			gridInfoCompra = false;
			gridInfoVenta = false;
			gridInfoVentaConsignacion = false;
			gridInfoConsignacion = true;
		}
	}

}
