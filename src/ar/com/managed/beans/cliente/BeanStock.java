package ar.com.managed.beans.cliente;

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

import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Moviles;
import ar.com.clases.auxiliares.StockMoviles;
import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOUsuario;

@ManagedBean
@SessionScoped
public class BeanStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	private List<ConsignacionsDetalle> listaConsignacionsDetalles;
	private List<StockMoviles> listaStockMoviles;
	private Usuario usuario;
	private Cliente cliente;
	private Consignacion consignacion;
	private String headerText;
	private int cantidadTotal;
	private float montoTotal;

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
	
	public DAOConsignacion getConsignacionDAO() {
		return consignacionDAO;
	}

	public void setConsignacionDAO(DAOConsignacion consignacionDAO) {
		this.consignacionDAO = consignacionDAO;
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

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
	}

	public List<ConsignacionsDetalle> getListaConsignacionsDetalles() {
		return listaConsignacionsDetalles;
	}

	public void setListaConsignacionsDetalles(
			List<ConsignacionsDetalle> listaConsignacionsDetalles) {
		this.listaConsignacionsDetalles = listaConsignacionsDetalles;
	}

	public List<StockMoviles> getListaStockMoviles() {
		return listaStockMoviles;
	}

	public void setListaStockMoviles(List<StockMoviles> listaStockMoviles) {
		this.listaStockMoviles = listaStockMoviles;
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

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	
	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String goStock(Usuario user) {
		cliente = new Cliente();
		consignacion = new Consignacion();
		usuario = new Usuario();
		usuario = user;
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		listaStockMoviles = new ArrayList<StockMoviles>();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		cantidadTotal = 0;
		montoTotal = 0;
		cliente = user.getCliente();
		headerText = "Stock de Moviles en Consignacion";
		try {			
			consignacion = consignacionDAO.get(cliente, true);
							
			List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consignacion);
			for (ConsignacionsDetalle consignacionsDetalle : listAux) {
				List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle, false);
				StockMoviles stockMoviles = new StockMoviles();		
				List<Moviles> listaMoviles = new ArrayList<Moviles>();
				int cantDetalle = 0;
				float montDetalle = 0;
				for (ConsignacionsDetalleUnidad detalleUnidad : listAux1) {
					Moviles moviles = new Moviles();
					//Obtengo precio de venta actual desde lista de precio
					ListaPrecio lista = detalleUnidad.getListaPrecio();
					Producto prod = detalleUnidad.getProducto();
					ListaPrecioProducto precioProducto = listaPrecioDAO.getItemProducto(lista, prod);
					float precioV = precioProducto.getPrecioVenta();
					//Seteo valores de moviles
					moviles.setFechaAlta(formatoFecha.format(detalleUnidad.getFechaAlta()));
					moviles.setNroImei(detalleUnidad.getNroImei());
					moviles.setPrecioUnitario(formatoMonto.format(precioV));
					listaMoviles.add(moviles);
					//Sumo subtotales
					cantDetalle = cantDetalle + 1;
					montDetalle = montDetalle + precioV;					
				}
				stockMoviles.setCantidad(cantDetalle);
				stockMoviles.setProducto(consignacionsDetalle.getProducto().getNombre());
				stockMoviles.setSubtotal(formatoMonto.format(montDetalle));
				stockMoviles.setListaMoviles(listaMoviles);
				if (cantDetalle != 0) {
					listaStockMoviles.add(stockMoviles);
//					listaConsignacionsDetalles.add(consignacionsDetalle);
					cantidadTotal = cantidadTotal + cantDetalle;	
					montoTotal = montoTotal + montDetalle;
				}				
			}
			return "stocks";		
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error! No se puede redirigir al formulario, error: " 
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}

	public void generarReporte() {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<StockMoviles> listStocks = new ArrayList<StockMoviles>();		
		for (StockMoviles sMoviles : listaStockMoviles) {
			StockMoviles stockMoviles = new StockMoviles();
			stockMoviles.setCantidad(sMoviles.getCantidad());
			stockMoviles.setProducto(sMoviles.getProducto());
			stockMoviles.setSubtotal(sMoviles.getSubtotal());
			for (Moviles movil : sMoviles.getListaMoviles()) {
				stockMoviles.setFechaAlta(movil.getFechaAlta());
				stockMoviles.setNroImei(movil.getNroImei());
				stockMoviles.setPrecioUnitario(movil.getPrecioUnitario());
				listStocks.add(stockMoviles);
			}
		}		
		parametros.put("cliente", usuario.getCliente().getApellidoNombre());
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		parametros.put("cantidadTotal", cantidadTotal);
		parametros.put("fechaEmision", formatoFecha.format(new Date()));
		parametros.put("usuarioEmision", usuario.getUsername());		
		reporte.generar(parametros, listStocks, "stock_clientes", "inline");
	}

}
