package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
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

import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Comprobante;
import ar.com.clases.auxiliares.Cuotas;
import ar.com.clases.auxiliares.DetalleComprobante;
import ar.com.clases.auxiliares.DetalleComprobanteUnidad;
import ar.com.clases.auxiliares.Moviles;
import ar.com.clases.auxiliares.StockMoviles;
import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.EConsignacion;
import model.entity.EConsignacionsDetalle;
import model.entity.EConsignacionsDetalleUnidad;
import model.entity.HistorialMovil;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuota;
import dao.interfaces.DAOEConsignacion;
import dao.interfaces.DAOEConsignacionDetalle;
import dao.interfaces.DAOEConsignacionDetalleUnidad;
import dao.interfaces.DAOHistorialMovil;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanConsignacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanConsignacion.class);
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
	private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanEConsignacionDAO}")
	private DAOEConsignacion eConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanEConsignacionDetalleDAO}")
	private DAOEConsignacionDetalle eConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanEConsignacionDetalleUnidadDAO}")
	private DAOEConsignacionDetalleUnidad eConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanHistorialMovilDAO}")
	private DAOHistorialMovil historialMovilDAO;
	
	private List<Consignacion> listaConsignacions;
	private List<Consignacion>filteredConsignacions;
	private List<Cliente> listaClientes;
	private List<Producto> listaProductos;
	private List<ConsignacionsDetalle> listaConsignacionsDetalles;
	private List<ConsignacionsDetalleUnidad> listaConsignacionsDetallesUnidad;
	private List<ConsignacionsDetalle> listaConsignacionsDetallesQuitar;
	private List<ConsignacionsDetalleUnidad> listaConsignacionsDetallesUnidadQuitar;
	private List<ConsignacionsDetalleUnidad> selectionConsignacionsDetallesUnidad;
	private List<ConsignacionsDetalleUnidad> listaConsignacionsDetalleUnidadCuotas;
	private List<Cuotas> listaCuotas;
	private List<VentasConsDetalle> listaVentasConsDetalles;
	private List<ListaPrecio> listaPrecios;
	private List<EConsignacionsDetalle> listaEConsignacionsDetalles;
	private List<EConsignacion> listaEConsignacions;
	private List<StockMoviles> listaStockMoviles;
	private Consignacion consignacion;
	private Usuario usuario;
	private ListaPrecio listaPrecio;
	private UnidadMovil unidadMovil;
	private Producto producto;
	private Cliente cliente;
	private VentasCon ventasCon;
	private EConsignacion eConsignacion;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAgrega;
	private String headerText;
	private String nroImei;
	private String tipo;
	private int idCliente;
	private int idProducto;
	private int estado;
	private int nroConsignacion;
	private int cantidadTotal;
	private int cantidadVentaTotal;
	private int nroVenta;
	private int idListaPrecio;
	private float precioVenta;
	private float montoTotal;
	private float montoVentaTotal;
	private boolean nueva;

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

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
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

	public DAOEConsignacion geteConsignacionDAO() {
		return eConsignacionDAO;
	}

	public void seteConsignacionDAO(DAOEConsignacion eConsignacionDAO) {
		this.eConsignacionDAO = eConsignacionDAO;
	}

	public DAOEConsignacionDetalle geteConsignacionDetalleDAO() {
		return eConsignacionDetalleDAO;
	}

	public void seteConsignacionDetalleDAO(
			DAOEConsignacionDetalle eConsignacionDetalleDAO) {
		this.eConsignacionDetalleDAO = eConsignacionDetalleDAO;
	}

	public DAOEConsignacionDetalleUnidad geteConsignacionDetalleUnidadDAO() {
		return eConsignacionDetalleUnidadDAO;
	}

	public void seteConsignacionDetalleUnidadDAO(
			DAOEConsignacionDetalleUnidad eConsignacionDetalleUnidadDAO) {
		this.eConsignacionDetalleUnidadDAO = eConsignacionDetalleUnidadDAO;
	}

	public DAOHistorialMovil getHistorialMovilDAO() {
		return historialMovilDAO;
	}

	public void setHistorialMovilDAO(DAOHistorialMovil historialMovilDAO) {
		this.historialMovilDAO = historialMovilDAO;
	}

	public List<Consignacion> getListaConsignacions() {
		return listaConsignacions;
	}

	public void setListaConsignacions(List<Consignacion> listaConsignacions) {
		this.listaConsignacions = listaConsignacions;
	}

	public List<Consignacion> getFilteredConsignacions() {
		return filteredConsignacions;
	}

	public void setFilteredConsignacions(List<Consignacion> filteredConsignacions) {
		this.filteredConsignacions = filteredConsignacions;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<ConsignacionsDetalle> getListaConsignacionsDetalles() {
		return listaConsignacionsDetalles;
	}

	public void setListaConsignacionsDetalles(
			List<ConsignacionsDetalle> listaConsignacionsDetalles) {
		this.listaConsignacionsDetalles = listaConsignacionsDetalles;
	}

	public List<ConsignacionsDetalleUnidad> getListaConsignacionsDetallesUnidad() {
		return listaConsignacionsDetallesUnidad;
	}

	public void setListaConsignacionsDetallesUnidad(List<ConsignacionsDetalleUnidad> listaConsignacionsDetallesUnidad) {
		this.listaConsignacionsDetallesUnidad = listaConsignacionsDetallesUnidad;
	}

	public List<ConsignacionsDetalle> getListaConsignacionsDetallesQuitar() {
		return listaConsignacionsDetallesQuitar;
	}

	public void setListaConsignacionsDetallesQuitar(
			List<ConsignacionsDetalle> listaConsignacionsDetallesQuitar) {
		this.listaConsignacionsDetallesQuitar = listaConsignacionsDetallesQuitar;
	}

	public List<ConsignacionsDetalleUnidad> getListaConsignacionsDetallesUnidadQuitar() {
		return listaConsignacionsDetallesUnidadQuitar;
	}

	public void setListaConsignacionsDetallesUnidadQuitar(
			List<ConsignacionsDetalleUnidad> listaConsignacionsDetallesUnidadQuitar) {
		this.listaConsignacionsDetallesUnidadQuitar = listaConsignacionsDetallesUnidadQuitar;
	}

	public List<ConsignacionsDetalleUnidad> getSelectionConsignacionsDetallesUnidad() {
		return selectionConsignacionsDetallesUnidad;
	}

	public void setSelectionConsignacionsDetallesUnidad(
			List<ConsignacionsDetalleUnidad> selectionConsignacionsDetallesUnidad) {
		this.selectionConsignacionsDetallesUnidad = selectionConsignacionsDetallesUnidad;
	}

	public List<ConsignacionsDetalleUnidad> getListaConsignacionsDetalleUnidadCuotas() {
		return listaConsignacionsDetalleUnidadCuotas;
	}

	public void setListaConsignacionsDetalleUnidadCuotas(
			List<ConsignacionsDetalleUnidad> listaConsignacionsDetalleUnidadCuotas) {
		this.listaConsignacionsDetalleUnidadCuotas = listaConsignacionsDetalleUnidadCuotas;
	}

	public List<Cuotas> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<Cuotas> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public List<VentasConsDetalle> getListaVentasConsDetalles() {
		return listaVentasConsDetalles;
	}

	public void setListaVentasConsDetalles(
			List<VentasConsDetalle> listaVentasConsDetalles) {
		this.listaVentasConsDetalles = listaVentasConsDetalles;
	}

	public List<ListaPrecio> getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(List<ListaPrecio> listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public List<EConsignacionsDetalle> getListaEConsignacionsDetalles() {
		return listaEConsignacionsDetalles;
	}

	public void setListaEConsignacionsDetalles(
			List<EConsignacionsDetalle> listaEConsignacionsDetalles) {
		this.listaEConsignacionsDetalles = listaEConsignacionsDetalles;
	}

	public List<EConsignacion> getListaEConsignacions() {
		return listaEConsignacions;
	}

	public void setListaEConsignacions(List<EConsignacion> listaEConsignacions) {
		this.listaEConsignacions = listaEConsignacions;
	}

	public List<StockMoviles> getListaStockMoviles() {
		return listaStockMoviles;
	}

	public void setListaStockMoviles(List<StockMoviles> listaStockMoviles) {
		this.listaStockMoviles = listaStockMoviles;
	}

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public VentasCon getVentasCon() {
		return ventasCon;
	}

	public void setVentasCon(VentasCon ventasCon) {
		this.ventasCon = ventasCon;
	}

	public EConsignacion geteConsignacion() {
		return eConsignacion;
	}

	public void seteConsignacion(EConsignacion eConsignacion) {
		this.eConsignacion = eConsignacion;
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

	public Date getFechaAgrega() {
		return fechaAgrega;
	}

	public void setFechaAgrega(Date fechaAgrega) {
		this.fechaAgrega = fechaAgrega;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getNroConsignacion() {
		return nroConsignacion;
	}

	public void setNroConsignacion(int nroConsignacion) {
		this.nroConsignacion = nroConsignacion;
	}

	public int getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	public int getCantidadVentaTotal() {
		return cantidadVentaTotal;
	}

	public void setCantidadVentaTotal(int cantidadVentaTotal) {
		this.cantidadVentaTotal = cantidadVentaTotal;
	}

	public int getNroVenta() {
		return nroVenta;
	}

	public void setNroVenta(int nroVenta) {
		this.nroVenta = nroVenta;
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

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public float getMontoVentaTotal() {
		return montoVentaTotal;
	}

	public void setMontoVentaTotal(float montoVentaTotal) {
		this.montoVentaTotal = montoVentaTotal;
	}

	public boolean isNueva() {
		return nueva;
	}

	public void setNueva(boolean nueva) {
		this.nueva = nueva;
	}

	public String goConsignaciones(Usuario user){
		usuario = new Usuario();
		usuario = user;
		listaConsignacions = new ArrayList<Consignacion>();
		filteredConsignacions = new ArrayList<Consignacion>();
		listaConsignacions = consignacionDAO.getLista(true);
		filteredConsignacions = listaConsignacions;
		consignacion = new Consignacion();
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		listaConsignacionsDetallesUnidad = new ArrayList<ConsignacionsDetalleUnidad>();
		selectionConsignacionsDetallesUnidad = new ArrayList<ConsignacionsDetalleUnidad>();
		listaConsignacionsDetalleUnidadCuotas = new ArrayList<ConsignacionsDetalleUnidad>();
		listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(true);
		listaProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = productoDAO.getLista(true, rub);
		fechaInicio = null;
		fechaFin = null;
		estado = 0;
		idCliente = 0;
		idProducto = 0;
		return "consignaciones";
	}
	
	public String goConsignacionNueva(){
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();		
		listaPrecios = new ArrayList<ListaPrecio>();
		listaClientes = new ArrayList<Cliente>();		
		List<Cliente> listCliAux = clienteDAO.getLista(true);		
		for (Cliente cli : listCliAux) {
			Consignacion cons = consignacionDAO.get(cli, true);
			if (cons.getId() == 0) {
				listaClientes.add(cli);
			}
		}
		listaPrecios = listaPrecioDAO.getLista(true);
		consignacion = new Consignacion();
		consignacion.setFecha(new Date());
		producto = new Producto();
		listaPrecio = new ListaPrecio();
		unidadMovil = new UnidadMovil();
		cliente = new Cliente();
		idCliente = 0;
		montoTotal = 0;
		cantidadTotal = 0;
		idListaPrecio = 0;
		nroImei = "";
		nueva = true;
		headerText = "Nueva Consignacion";
		nroConsignacion = 1;
		List<Consignacion> listAux = consignacionDAO.getLista();
		for (Consignacion consignacion : listAux) {
			nroConsignacion = consignacion.getId() + 1;
		}
		return "consignacion";
	}
	
	public String goConsignacionEditar(Consignacion consig){
		try {
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
			listaClientes = new ArrayList<Cliente>();
			listaClientes = clienteDAO.getLista();
			consignacion = new Consignacion();
			producto = new Producto();
			listaPrecio = new ListaPrecio();
			unidadMovil = new UnidadMovil();
			cliente = new Cliente();
			nroImei = "";
			cantidadTotal = 0;
			nueva = false;
			headerText = "Modificar Consignacion";
			consignacion = consig;
			nroConsignacion = consig.getId();
			montoTotal = consig.getMonto();
			idCliente = consig.getCliente().getId();
			cliente = clienteDAO.get(idCliente);
			listaPrecio = consig.getCliente().getListaPrecio();
			List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consig);
			for (ConsignacionsDetalle consignacionsDetalle : listAux) {
				List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle);
				consignacionsDetalle.setConsignacionsDetalleUnidads(listAux1);
				listaConsignacionsDetalles.add(consignacionsDetalle);
				cantidadTotal = cantidadTotal + consignacionsDetalle.getCantidad();
			}
			return "consignacion";
		}catch (Exception e) {
			System.out.println(e.getMessage());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL ABRIR LA CONSIGNACION, INTENTELO NUEVAMENTE! SI EL ERROR PERSISTE CONTACTESE CON SU PROVEEDOR", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);			
			return "";
		}
	}
	
	public void goAgregar(Consignacion consig) {
		try {
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
			listaPrecios = new ArrayList<ListaPrecio>();
			consignacion = new Consignacion();		
			producto = new Producto();
			listaPrecio = new ListaPrecio();
			unidadMovil = new UnidadMovil();
			cliente = new Cliente();
			idCliente = 0;
			montoTotal = 0;
			cantidadTotal = 0;
			nroImei = "";
			fechaAgrega = null;
			headerText = "Agregar a Consignacion";
			listaPrecios = listaPrecioDAO.getLista(true);
			consignacion = consig;
			cliente = clienteDAO.get(consig.getCliente().getId());
			listaPrecio = consig.getCliente().getListaPrecio();
			idListaPrecio = consig.getCliente().getListaPrecio().getId();
			fechaAgrega = new Date();
			FacesContext.getCurrentInstance().getExternalContext().redirect("agregarconsignacion.xhtml");
//			return "agregarconsignacion";
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al abrir el formulario! error: " 
					 + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
//			return "";
		}		
	}
	
	public String goQuitar(Consignacion consig) {
		try {
			listaConsignacionsDetallesQuitar = new ArrayList<ConsignacionsDetalle>();
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();			
			consignacion = new Consignacion();			
			cliente = new Cliente();
			nroImei = "";
			cantidadTotal = 0;
			montoTotal = 0;
			headerText = "Quitar Moviles en Consignacion";
			consignacion = consig;			
			cliente = clienteDAO.get(consig.getCliente().getId());
			List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consig);			
			for (ConsignacionsDetalle consignacionsDetalle : listAux) {
				List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle, false);
				int cantDetalle = 0;
				float montDetalle = 0;
				for (ConsignacionsDetalleUnidad detalleUnidad : listAux1) {
					cantDetalle = cantDetalle + 1;
					montDetalle = montDetalle + detalleUnidad.getPrecioLista();					
				}
				consignacionsDetalle.setCantidad(cantDetalle);
				consignacionsDetalle.setSubtotal(montDetalle);
				consignacionsDetalle.setConsignacionsDetalleUnidads(listAux1);
				if (cantDetalle != 0) {
					listaConsignacionsDetalles.add(consignacionsDetalle);
					listaConsignacionsDetallesQuitar.add(consignacionsDetalle);
					cantidadTotal = cantidadTotal + cantDetalle;
					montoTotal = montoTotal + montDetalle;
				}				
			}
			return "quitarconsignacion";			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al abrir el formulario! Error: "
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public String goVerStock(Consignacion consig) {
		try {
			DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
			listaStockMoviles = new ArrayList<StockMoviles>();
			consignacion = new Consignacion();
			cliente = new Cliente();
			montoTotal = 0;
			cantidadTotal = 0;		
			consignacion = consig;
			cliente = clienteDAO.get(consig.getCliente().getId());
			headerText = "Stock de Moviles Sin Vender de Consignacion";	
											
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
//						listaConsignacionsDetalles.add(consignacionsDetalle);
					cantidadTotal = cantidadTotal + cantDetalle;	
					montoTotal = montoTotal + montDetalle;
				}				
			}
//			
//			
//			List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consig);
//			for (ConsignacionsDetalle consignacionsDetalle : listAux) {
//				List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle, false);
//				int cantDetalle = 0;
//				float montDetalle = 0;
//				for (ConsignacionsDetalleUnidad detalleUnidad : listAux1) {
//					cantDetalle = cantDetalle + 1;
//					montDetalle = montDetalle + detalleUnidad.getPrecioVenta();					
//				}
//				consignacionsDetalle.setCantidad(cantDetalle);
//				consignacionsDetalle.setSubtotal(montDetalle);
//				consignacionsDetalle.setConsignacionsDetalleUnidads(listAux1);
//				if (cantDetalle != 0) {
//					listaConsignacionsDetalles.add(consignacionsDetalle);
//					cantidadTotal = cantidadTotal + cantDetalle;
//					montoTotal = montoTotal + montDetalle;
//				}				
//			}
			return "stockconsignacion";		
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error! No se puede redirigir al formulario, error: " 
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}	
	}
	
	public String goVerVentas(Consignacion consig) {
		try {
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
			consignacion = new Consignacion();
			cliente = new Cliente();
			montoTotal = 0;
			cantidadTotal = 0;		
			consignacion = consig;
			cliente = clienteDAO.get(consig.getCliente().getId());
			headerText = "Historico de Ventas de Consignacion";	
			List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consig);
			for (ConsignacionsDetalle consignacionsDetalle : listAux) {
				List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle, true);
				int cantDetalle = 0;
				float montDetalle = 0;
				for (ConsignacionsDetalleUnidad detalleUnidad : listAux1) {
					cantDetalle = cantDetalle + 1;
					montDetalle = montDetalle + detalleUnidad.getPrecioLista();					
				}
				consignacionsDetalle.setCantidad(cantDetalle);
				consignacionsDetalle.setSubtotal(montDetalle);
				consignacionsDetalle.setConsignacionsDetalleUnidads(listAux1);
				if (cantDetalle != 0) {
					listaConsignacionsDetalles.add(consignacionsDetalle);
					cantidadTotal = cantidadTotal + cantDetalle;
					montoTotal = montoTotal + montDetalle;
				}
			}
			return "historicoventasconsignacion";		
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error! No se puede redirigir al formulario, error: " 
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}	
	}
	
	public String goHistorico(Consignacion consig) {
		try {
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
			consignacion = new Consignacion();
			cliente = new Cliente();
			montoTotal = 0;
			cantidadTotal = 0;		
			consignacion = consig;
			cliente = clienteDAO.get(consig.getCliente().getId());
			headerText = "Historico de Consignacion";	
			List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consig);
			for (ConsignacionsDetalle consignacionsDetalle : listAux) {
				List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle);
				consignacionsDetalle.setConsignacionsDetalleUnidads(listAux1);
				listaConsignacionsDetalles.add(consignacionsDetalle);
				cantidadTotal = cantidadTotal + consignacionsDetalle.getCantidad();
				montoTotal = montoTotal + consignacionsDetalle.getSubtotal();
			}
			return "historicoconsignacion";			
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error! No se puede redirigir al formulario, error: " 
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public String goEntregasMoviles(Consignacion consig) {
		try {
			listaEConsignacions = new ArrayList<EConsignacion>();
			consignacion = new Consignacion();
			fechaInicio = null;
			fechaFin = null;
			listaEConsignacions = eConsignacionDAO.getLista(consig);
			consignacion = consig;
			return "consignacionentregas";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}		
	}
	
	public String volver(){
		listaConsignacions = new ArrayList<Consignacion>();
		filteredConsignacions = new ArrayList<Consignacion>();
		listaConsignacions = consignacionDAO.getLista(true);
		filteredConsignacions = listaConsignacions;
		consignacion = new Consignacion();
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		listaConsignacionsDetallesUnidad = new ArrayList<ConsignacionsDetalleUnidad>();
		selectionConsignacionsDetallesUnidad = new ArrayList<ConsignacionsDetalleUnidad>();
		listaConsignacionsDetalleUnidadCuotas = new ArrayList<ConsignacionsDetalleUnidad>();
		listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(true);
		listaProductos = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProductos = productoDAO.getLista(true, rub);
		fechaInicio = null;
		fechaFin = null;
		estado = 0;
		idCliente = 0;
		idProducto = 0;
		return "consignaciones";
	}
	
	public List<ConsignacionsDetalle> getDetalleConsignacion(Consignacion consig){
		List<ConsignacionsDetalle> listAux = consignacionDetalleDAO.getLista(consig);	
		List<ConsignacionsDetalle> listaDetalles = new ArrayList<ConsignacionsDetalle>();
		for (ConsignacionsDetalle consignacionsDetalle : listAux) {
			List<ConsignacionsDetalleUnidad> listAux1 = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle, false);
			int cantDetalle = 0;
			float montDetalle = 0;
			for (ConsignacionsDetalleUnidad detalleUnidad : listAux1) {
				cantDetalle = cantDetalle + 1;
				montDetalle = montDetalle + detalleUnidad.getPrecioLista();					
			}
			consignacionsDetalle.setCantidad(cantDetalle);
			consignacionsDetalle.setSubtotal(montDetalle);
			consignacionsDetalle.setConsignacionsDetalleUnidads(listAux1);
			if (cantDetalle != 0) {
				listaDetalles.add(consignacionsDetalle);
			}				
		}		
		return listaDetalles;
	}
	
	public List<ConsignacionsDetalle> getDetalleConsignacionVendido(Consignacion consig) {
		List<ConsignacionsDetalle> listAux = consignacionDetalleUnidadDAO.getListaDetalleVendido(consig, true);
		return listAux;
	}
	
	public List<ConsignacionsDetalleUnidad> getDetalleUnidadConsignacion(ConsignacionsDetalle consigDetalle){
		List<ConsignacionsDetalleUnidad> listAux = consignacionDetalleUnidadDAO.getLista(consigDetalle);
		return listAux;
	}
	
	public List<ConsignacionsDetalleUnidad> getDetalleUnidadConsignacionVendido(ConsignacionsDetalle consigDetalle){
		List<ConsignacionsDetalleUnidad> listAux = consignacionDetalleUnidadDAO.getLista(consigDetalle, true);
		return listAux;
	}
	
	public void onChangeCliente(){
		listaPrecio = new ListaPrecio();
		cliente = new Cliente();
		if(idCliente != 0){
			cliente = new Cliente();
			cliente = clienteDAO.get(idCliente);
			listaPrecio = cliente.getListaPrecio();
			idListaPrecio = cliente.getListaPrecio().getId();
		}
	}
	
	public List<UnidadMovil> completeText(String query){
		List<UnidadMovil> listAux = unidadMovilDAO.getLike(true, true, query);
		return listAux;
	}
	
	public void onBlurNroImei(){
		producto = new Producto();
		precioVenta = 0;
		if(unidadMovil.getId() != 0){
			nroImei = unidadMovil.getNroImei();
			if (!unidadMovil.getEnGarantiaCliente() && !unidadMovil.getEnGarantiaProveedor()) {
				if(unidadMovil.getEnStock()){
					boolean noAgregado = true;
					if(unidadMovil.getEnConsignacion()){
						noAgregado = false;
					}
					for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
						for(ConsignacionsDetalleUnidad consignacionUnidad : consignacionDetalle.getConsignacionsDetalleUnidads()){
							if(consignacionUnidad.getNroImei().equals(unidadMovil.getNroImei())){
								noAgregado = false;
							}
						}
					}
					if(noAgregado){
						boolean enVenta = false;
						if(unidadMovil.getEnVenta()){
							enVenta = true;
						}
						if(!enVenta){
							if(unidadMovil.getEnStock() && unidadMovil.getEstado() && !unidadMovil.getEliminado()){
								producto = unidadMovil.getProducto();
								if(listaPrecio.getId() != 0){
									ListaPrecioProducto precioProducto = new ListaPrecioProducto();
									precioProducto = listaPrecioDAO.getItemProducto(listaPrecio, producto);
									precioVenta = precioProducto.getPrecioVenta();
									float costoPromedio = producto.getCostoPromedio();
									if (costoPromedio != 0) {
										if (precioVenta < costoPromedio) {
											FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El precio de venta: $" + precioVenta 
													+ " es menor al costo promedio: $" + costoPromedio, null);
											FacesContext.getCurrentInstance().addMessage(null, msg);
										}
									}	
								}else{
									precioVenta = 0;
								}					
							}else{
								FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei no corresponde a ningun producto en Stock!", null);
								FacesContext.getCurrentInstance().addMessage(null, msg);
							}
						}else{
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto asociado a una Venta!", null);
							FacesContext.getCurrentInstance().addMessage(null, msg);
						}
					}else{
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto ya Agregado a Consignacion!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}else{
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei no corresponde a ningun producto en Stock!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El nro de Imei corresponde a un producto en Garantia!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un nro de Imei valido!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
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
	
	public void agregarProducto(){
		if(producto.getId() != 0 && unidadMovil != null && precioVenta != 0){
			List<ConsignacionsDetalle> listAux = new ArrayList<ConsignacionsDetalle>();
			boolean noExiste = true;
			for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
				if(producto.getId() == consignacionDetalle.getProducto().getId()){
					noExiste = false;
					List<ConsignacionsDetalleUnidad> listaUnidades = consignacionDetalle.getConsignacionsDetalleUnidads();
					ConsignacionsDetalleUnidad unidad = new ConsignacionsDetalleUnidad();
					unidad.setNroImei(unidadMovil.getNroImei());
					unidad.setPrecioCompra(unidadMovil.getPrecioCompra());
					unidad.setPrecioLista(precioVenta);
					unidad.setUnidadMovil(unidadMovil);
					unidad.setFechaAlta(new Date());
					unidad.setListaPrecio(listaPrecio);
					unidad.setProducto(producto);
					listaUnidades.add(unidad);
					float subtotal = consignacionDetalle.getSubtotal();
					montoTotal = montoTotal - subtotal;
					consignacionDetalle.setCantidad(consignacionDetalle.getCantidad() + 1);
					consignacionDetalle.setPrecioVenta(precioVenta);
					consignacionDetalle.setProducto(producto);
					subtotal = subtotal + precioVenta;
					consignacionDetalle.setSubtotal(subtotal);
					consignacionDetalle.setConsignacionsDetalleUnidads(listaUnidades);
					montoTotal = montoTotal + subtotal;
					cantidadTotal = cantidadTotal + 1;
				}
				listAux.add(consignacionDetalle);
			}
			if(noExiste){
				ConsignacionsDetalle detalle = new ConsignacionsDetalle();
				List<ConsignacionsDetalleUnidad> listaUnidades = new ArrayList<ConsignacionsDetalleUnidad>();
				ConsignacionsDetalleUnidad unidad = new ConsignacionsDetalleUnidad();
				unidad.setNroImei(unidadMovil.getNroImei());
				unidad.setPrecioCompra(unidadMovil.getPrecioCompra());
				unidad.setPrecioLista(precioVenta);
				unidad.setUnidadMovil(unidadMovil);
				unidad.setFechaAlta(new Date());
				unidad.setListaPrecio(listaPrecio);
				unidad.setProducto(producto);
				listaUnidades.add(unidad);
				detalle.setCantidad(1);
				detalle.setPrecioVenta(precioVenta);
				detalle.setProducto(producto);
				detalle.setSubtotal(precioVenta);
				detalle.setConsignacionsDetalleUnidads(listaUnidades);
				listAux.add(detalle);
				montoTotal = montoTotal + precioVenta;
				cantidadTotal = cantidadTotal + 1;
			}
			listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
			listaConsignacionsDetalles = listAux;
			producto = new Producto();
			unidadMovil = new UnidadMovil();
			nroImei = "";
			precioVenta = 0;
		}else{
			nroImei = "";
			precioVenta = 0;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se pudo agregar. "
					+ "Debe existir un Producto!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void quitarProducto(ConsignacionsDetalle detalle){
		List<ConsignacionsDetalle> listAux = new ArrayList<ConsignacionsDetalle>();
		for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
			if(consignacionDetalle.getProducto().getId() != detalle.getProducto().getId()){
				listAux.add(consignacionDetalle);
			}else{
				montoTotal = montoTotal - consignacionDetalle.getSubtotal();
				cantidadTotal = cantidadTotal - consignacionDetalle.getCantidad();
			}
		}
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		listaConsignacionsDetalles = listAux;
	}
	
	public void quitarUnidadDetalle(ConsignacionsDetalle detalle, ConsignacionsDetalleUnidad imei){
		List<ConsignacionsDetalleUnidad> listAux = new ArrayList<ConsignacionsDetalleUnidad>();
		for(ConsignacionsDetalleUnidad unidadDetalle : detalle.getConsignacionsDetalleUnidads()){
			if(!unidadDetalle.getNroImei().equals(imei.getNroImei())){
				listAux.add(unidadDetalle);
			}
		}
		montoTotal = montoTotal - imei.getPrecioLista();
		float subtot = detalle.getSubtotal() -  imei.getPrecioLista();
		cantidadTotal = cantidadTotal - 1;
		int cant = detalle.getCantidad() - 1;
		detalle.setConsignacionsDetalleUnidads(listAux);
		detalle.setSubtotal(subtot);
		detalle.setCantidad(cant);
		List<ConsignacionsDetalle> listAux1 = new ArrayList<ConsignacionsDetalle>();
		for(ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles){
			if(consignacionDetalle.getProducto().getId() != detalle.getProducto().getId()){
				listAux1.add(consignacionDetalle);
			}else{
				if (cant != 0) {
					listAux1.add(detalle);
				}
			}
		}
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		listaConsignacionsDetalles = listAux1;
	}
	
	public void filtro(){
		listaConsignacions = new ArrayList<Consignacion>();
		filteredConsignacions = new ArrayList<Consignacion>();
		if(idCliente == 0 && fechaInicio == null && fechaFin == null){
			listaConsignacions = consignacionDAO.getLista(true);
		}
		if(idCliente != 0 && fechaInicio == null && fechaFin == null){
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaConsignacions = consignacionDAO.getLista(true, cli);
		}
		if(idCliente == 0 && fechaInicio != null && fechaFin != null){
			listaConsignacions = consignacionDAO.getLista(true, fechaInicio, fechaFin);
		}
		if(estado != 0 && idCliente == 0 && fechaInicio != null && fechaFin != null){
			if(estado == 1){
				listaConsignacions = consignacionDAO.getLista(true, fechaInicio, fechaFin);
			}
			if(estado == 2){
				listaConsignacions = consignacionDAO.getLista(false, fechaInicio, fechaFin);
			}
		}
		if(idCliente != 0 && fechaInicio != null && fechaFin != null){
			Cliente cli = new Cliente();
			cli.setId(idCliente);
			listaConsignacions = consignacionDAO.getLista(true, cli, fechaInicio, fechaFin);
		}
		filteredConsignacions = listaConsignacions;
	}
	
	public void filtroProducto(){
		listaConsignacions = new ArrayList<Consignacion>();
		filteredConsignacions = new ArrayList<Consignacion>();
		if(idProducto != 0){
			Producto prod = productoDAO.get(idProducto);
			listaConsignacions = consignacionDAO.getListaProductoDisponible(prod);
			filteredConsignacions = listaConsignacions;
		}else{
			listaConsignacions = consignacionDAO.getLista(true);
			filteredConsignacions = listaConsignacions;
		}
	}
	
	public void filtroEntregasMoviles() {
		listaEConsignacions = new ArrayList<EConsignacion>();
		if (fechaInicio != null && fechaFin != null) {
			listaEConsignacions = eConsignacionDAO.getLista(consignacion, fechaInicio, fechaFin);
		} else {
			listaEConsignacions = eConsignacionDAO.getLista(consignacion);
		}
	}
	
	public void baja(Consignacion consig){
		FacesMessage msg = null;
		try {
			boolean enStock = true;
			List<ConsignacionsDetalleUnidad> listaUnidadsEnStock = consignacionDetalleUnidadDAO.getLista(consig, false);
			if (listaUnidadsEnStock.isEmpty()) {
				enStock = false;
			}
			if (enStock) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede registrar la baja de Consignacion, posee moviles en Stock! "
						+ "Realice la baja de los mismos primero!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				consig.setEstado(false);
				consig.setFechaBaja(new Date());
				consig.setUsuario2(usuario);
				int updtConsig = consignacionDAO.update(consig);
				if (updtConsig != 0) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Consignacion!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al dar de Baja la Consignacion, "
							+ "Intente nuevamente!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}			
//			boolean noVendida = true;
//			for(ConsignacionsDetalleUnidad consignacionUnidad : consignacionDetalleUnidadDAO.getLista(consig)){
//				String imei = consignacionUnidad.getNroImei();
//				UnidadMovil unidad = unidadMovilDAO.get(imei);
//				if (unidad.getId() != 0) {
//					if (unidad.getEnGarantiaCliente()) {
//						noVendida = false;
//					}
//				}
//				if(consignacionUnidad.getVendido()){
//					noVendida = false;
//				}
//			}			
//			if(noVendida){
//				for(ConsignacionsDetalleUnidad consignacionUnidad : consignacionDetalleUnidadDAO.getLista(consig)){
//					UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
//					unidad.setEnConsignacion(false);
//					unidadMovilDAO.update(unidad);
//					Producto prod = productoDAO.get(unidad.getProducto().getId());
//					int stock = prod.getStock() + 1;
//					int enConsig = prod.getEnConsignacion() - 1;
//					prod.setStock(stock);
//					prod.setEnConsignacion(enConsig);
//					productoDAO.update(prod);
//				}
//				consig.setEstado(false);
//				consig.setFechaBaja(new Date());
//				consig.setUsuario2(usuario);
//				if(consignacionDAO.update(consig) != 0){
//					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Consignaci�n!", null);
//				}else{
//					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurri� un Error al dar de Baja la Consignaci�n, "
//							+ "Int�ntelo nuevamente!", null);
//				}
//			}else{
//				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede dar de baja la consignacion, posee unidades Vendidas!", null);
//			}			
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar la baja de consignacion! error original: " 
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		if(consignacion.getFecha() != null && idCliente != 0 && !listaConsignacionsDetalles.isEmpty() && montoTotal != 0){
			log.info("Monto: " + montoTotal + " Cliente: " + idCliente);
			Cliente cli = clienteDAO.get(idCliente);			
			Consignacion consig = consignacionDAO.get(cli, false);
			if (consig.getId() == 0) {
				log.info("idConsignacion: " + consig.getId());
				consignacion.setCliente(cli);
				consignacion.setEstado(true);
				consignacion.setFechaAlta(new Date());
				consignacion.setMonto(montoTotal);
				consignacion.setUsuario1(usuario);
				int idConsignacion = consignacionDAO.insertar(consignacion);
				if(idConsignacion != 0){
					consignacion.setId(idConsignacion);
					//Inserto entrega
					eConsignacion = new EConsignacion();
					eConsignacion.setCliente(cli);
					eConsignacion.setConsignacion(consignacion);
					eConsignacion.setFecha(new Date());
					eConsignacion.setFechaAlta(new Date());
					eConsignacion.setMonto(montoTotal);
					eConsignacion.setUsuario(usuario);
					int idECon = eConsignacionDAO.insertar(eConsignacion);
					eConsignacion.setId(idECon);
					log.info("idECon: " + idECon);
					
					boolean inserto = true;
					consignacion.setId(idConsignacion);
					listaEConsignacionsDetalles = new ArrayList<EConsignacionsDetalle>();
					for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
						List<ConsignacionsDetalleUnidad> listAux = consignacionDetalle.getConsignacionsDetalleUnidads();
						consignacionDetalle.setConsignacionsDetalleUnidads(null);
						consignacionDetalle.setConsignacion(consignacion);					
						int idDetalle = consignacionDetalleDAO.insertar(consignacionDetalle);
						log.info("idDetalle: " + idDetalle);
						if(idDetalle != 0){
							consignacionDetalle.setId(idDetalle);		
							//Inserto Entrega Detalle
							List<EConsignacionsDetalleUnidad> listaEConsigUnidad = new ArrayList<EConsignacionsDetalleUnidad>();
							EConsignacionsDetalle eConsigDetalle = new EConsignacionsDetalle();
							eConsigDetalle.setCantidad(consignacionDetalle.getCantidad());
							eConsigDetalle.setEConsignacion(eConsignacion);
							eConsigDetalle.setProducto(consignacionDetalle.getProducto());
							eConsigDetalle.setSubtotal(consignacionDetalle.getSubtotal());
							int idEConDet = eConsignacionDetalleDAO.insertar(eConsigDetalle);
							eConsigDetalle.setId(idEConDet);
							log.info("idEConDet: " + idEConDet);
							
							boolean insertoUnidad = true;
							for(ConsignacionsDetalleUnidad consignacionUnidad : listAux){
								consignacionUnidad.setEliminado(false);
								consignacionUnidad.setEnabled(true);
								consignacionUnidad.setFechaAlta(new Date());
								consignacionUnidad.setConsignacionsDetalle(consignacionDetalle);
								UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
								unidad.setEnConsignacion(true);
								unidadMovilDAO.update(unidad);
								Producto prod = productoDAO.get(unidad.getProducto().getId());
								int stock = prod.getStock() - 1;
								int enConsig = prod.getEnConsignacion() + 1;
								prod.setStock(stock);
								prod.setEnConsignacion(enConsig);
								productoDAO.update(prod);
								int idDetalleUnidad = consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
								log.info("idDetalleUnidad: " + idDetalleUnidad);
								
								//Inserto Entrega Unidad
								EConsignacionsDetalleUnidad eConsigDetUnidad = new EConsignacionsDetalleUnidad();
								eConsigDetUnidad.setEConsignacionsDetalle(eConsigDetalle);
								eConsigDetUnidad.setListaPrecio(consignacionUnidad.getListaPrecio());
								eConsigDetUnidad.setNroImei(consignacionUnidad.getNroImei());
								eConsigDetUnidad.setPrecioVenta(consignacionUnidad.getPrecioLista());
								eConsigDetUnidad.setProducto(consignacionUnidad.getProducto());
								eConsignacionDetalleUnidadDAO.insertar(eConsigDetUnidad);
								if(idDetalleUnidad == 0){
									insertoUnidad = false;
									break;
								}/*else {
									HistorialMovil hm = new HistorialMovil();
									hm.setFecha(new Date());
									hm.setUsuario(usuario);
									hm.setImei(eConsigDetUnidad.getNroImei());
									hm.setTipo("CONSIGNACION");
									hm.setDescripcion("Consignacion: " + cliente.getApellidoNombre());
									hm.setIdMovimiento(idConsignacion);
									historialMovilDAO.insert(hm);
								}*/
								listaEConsigUnidad.add(eConsigDetUnidad);
							}
							eConsigDetalle.setEConsignacionsDetalleUnidads(listaEConsigUnidad);
							listaEConsignacionsDetalles.add(eConsigDetalle);
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
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consignacion guardada!", null);
						retorno = "consignacionentrega";
						listaClientes = new ArrayList<Cliente>();
						listaClientes = clienteDAO.getLista(true);
					}else{
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Detalle de Consignacion! "
								+ "Intente nuevamente!", null);
					}
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la Consignacion! "
							+ "Intente nuevamente!", null);
				}				
			} else {
				float monto = consig.getMonto();
				monto = monto + montoTotal;
				int idConsig = consig.getId();
				log.info("idConsig: " + idConsig);
				consignacion.setId(idConsig);
				consignacion.setCliente(cli);
				consignacion.setEstado(true);
				consignacion.setFechaMod(new Date());
				consignacion.setMonto(monto);
				consignacion.setUsuario3(usuario);
				int updateConsignacion = consignacionDAO.update(consignacion);
				log.info("updateConsignacion: " + updateConsignacion);
				if(updateConsignacion != 0){
					//Inserto entrega
					eConsignacion = new EConsignacion();
					eConsignacion.setCliente(cli);
					eConsignacion.setConsignacion(consignacion);
					eConsignacion.setFecha(new Date());
					eConsignacion.setFechaAlta(new Date());
					eConsignacion.setMonto(montoTotal);
					eConsignacion.setUsuario(usuario);
					int idECon = eConsignacionDAO.insertar(eConsignacion);
					log.info("idECon: " + idECon);
					eConsignacion.setId(idECon);
					
					boolean inserto = true;
					consignacion.setId(updateConsignacion);
					for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
						boolean existe = false;
						List<ConsignacionsDetalle> listDetalle = consignacionDetalleDAO.getLista(consig);
						for (ConsignacionsDetalle consignacionDet : listDetalle) {
							if (consignacionDetalle.getProducto().getId() == consignacionDet.getProducto().getId()) {
								int idConsignacionDetalle = consignacionDet.getId();
								int cantProd = consignacionDet.getCantidad();
								float subtot = consignacionDet.getSubtotal();
								cantProd = cantProd + consignacionDetalle.getCantidad();
								subtot = subtot + consignacionDetalle.getSubtotal();
								consignacionDetalle.setCantidad(cantProd);
								consignacionDetalle.setSubtotal(subtot);
								consignacionDetalle.setId(idConsignacionDetalle);
								existe = true;
							}
						}
						List<ConsignacionsDetalleUnidad> listAux = consignacionDetalle.getConsignacionsDetalleUnidads();
						consignacionDetalle.setConsignacionsDetalleUnidads(null);
						consignacionDetalle.setConsignacion(consignacion);					
						int idDetalle = 0;
						if (existe) {
							idDetalle = consignacionDetalleDAO.update(consignacionDetalle);
						} else {
							idDetalle = consignacionDetalleDAO.insertar(consignacionDetalle);
						}
						log.info("idDetalle: " + idDetalle);
						if(idDetalle != 0){
							consignacionDetalle.setId(idDetalle);		
							//Inserto Entrega Detalle
							List<EConsignacionsDetalleUnidad> listaEConsigUnidad = new ArrayList<EConsignacionsDetalleUnidad>();
							EConsignacionsDetalle eConsigDetalle = new EConsignacionsDetalle();
							eConsigDetalle.setCantidad(consignacionDetalle.getCantidad());
							eConsigDetalle.setEConsignacion(eConsignacion);
							eConsigDetalle.setProducto(consignacionDetalle.getProducto());
							eConsigDetalle.setSubtotal(consignacionDetalle.getSubtotal());
							int idEConDet = eConsignacionDetalleDAO.insertar(eConsigDetalle);
							eConsigDetalle.setId(idEConDet);
							
							boolean insertoUnidad = true;
							for(ConsignacionsDetalleUnidad consignacionUnidad : listAux){
								consignacionUnidad.setEnabled(true);
								consignacionUnidad.setEliminado(false);
								consignacionUnidad.setFechaAlta(new Date());
								consignacionUnidad.setConsignacionsDetalle(consignacionDetalle);
								UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
								unidad.setEnConsignacion(true);
								unidadMovilDAO.update(unidad);
								Producto prod = productoDAO.get(unidad.getProducto().getId());
								int stock = prod.getStock() - 1;
								int enConsig = prod.getEnConsignacion() + 1;
								prod.setStock(stock);
								prod.setEnConsignacion(enConsig);
								productoDAO.update(prod);
								int idDetalleUnidad = consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
								log.info("idDetalleUnidad: " + idDetalleUnidad);
								
								//Inserto Entrega Unidad
								EConsignacionsDetalleUnidad eConsigDetUnidad = new EConsignacionsDetalleUnidad();
								eConsigDetUnidad.setEConsignacionsDetalle(eConsigDetalle);
								eConsigDetUnidad.setListaPrecio(consignacionUnidad.getListaPrecio());
								eConsigDetUnidad.setNroImei(consignacionUnidad.getNroImei());
								eConsigDetUnidad.setPrecioVenta(consignacionUnidad.getPrecioLista());
								eConsigDetUnidad.setProducto(consignacionUnidad.getProducto());
								eConsignacionDetalleUnidadDAO.insertar(eConsigDetUnidad);
								if(idDetalleUnidad == 0){
									insertoUnidad = false;
									break;
								} /*else {
									HistorialMovil hm = new HistorialMovil();
									hm.setFecha(new Date());
									hm.setUsuario(usuario);
									hm.setImei(eConsigDetUnidad.getNroImei());
									hm.setTipo("CONSIGNACION");
									hm.setDescripcion("Consignacion: " + cliente.getApellidoNombre());
									hm.setIdMovimiento(consig.getId());
									historialMovilDAO.insert(hm);
								}*/
								listaEConsigUnidad.add(eConsigDetUnidad);
							}
							eConsigDetalle.setEConsignacionsDetalleUnidads(listaEConsigUnidad);
							listaEConsignacionsDetalles.add(eConsigDetalle);
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
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consignacion guardada!", null);
						retorno = "consignacionentrega";
						listaClientes = new ArrayList<Cliente>();
						listaClientes = clienteDAO.getLista(true);
					}else{
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Detalle de Consignacion! "
								+ "Intente nuevamente!", null);
					}
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la Consignacion! "
							+ "Intente nuevamente!", null);
				}
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Cliente, el Monto Total y el Detalle de Consignacion son obligatorios!", null);
		}		
		listaConsignacions = new ArrayList<Consignacion>();
		filteredConsignacions = new ArrayList<Consignacion>();
		listaConsignacions = consignacionDAO.getLista(true);
		filteredConsignacions = listaConsignacions;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String agregar() {
		try {
			if (fechaAgrega != null && !listaConsignacionsDetalles.isEmpty() && montoTotal != 0 && cantidadTotal != 0) {
				log.info("montoTotal: " + montoTotal);
				float monto = consignacion.getMonto();
				monto = monto + montoTotal;			
				consignacion.setEstado(true);
				consignacion.setFechaMod(new Date());
				consignacion.setMonto(monto);
				consignacion.setUsuario3(usuario);
				int updCons = consignacionDAO.update(consignacion);
				log.info("updCons: " + updCons);
				if (updCons != 0) {
					//Inserto entrega
					eConsignacion = new EConsignacion();
					eConsignacion.setCliente(consignacion.getCliente());
					eConsignacion.setConsignacion(consignacion);
					eConsignacion.setFecha(new Date());
					eConsignacion.setFechaAlta(new Date());
					eConsignacion.setMonto(montoTotal);
					eConsignacion.setUsuario(usuario);
					int idECon = eConsignacionDAO.insertar(eConsignacion);
					log.info("idECon: " + idECon);
					eConsignacion.setId(idECon);
					listaEConsignacionsDetalles = new ArrayList<EConsignacionsDetalle>();
					boolean inserto = true;
					List<ConsignacionsDetalle> listDetallesAux = listaConsignacionsDetalles;
					for (ConsignacionsDetalle consignacionDetalle : listDetallesAux) {
						//Inserto Entrega Detalle
						List<EConsignacionsDetalleUnidad> listaEConsigUnidad = new ArrayList<EConsignacionsDetalleUnidad>();
						EConsignacionsDetalle eConsigDetalle = new EConsignacionsDetalle();
						eConsigDetalle.setCantidad(consignacionDetalle.getCantidad());
						eConsigDetalle.setEConsignacion(eConsignacion);
						eConsigDetalle.setProducto(consignacionDetalle.getProducto());
						eConsigDetalle.setSubtotal(consignacionDetalle.getSubtotal());
						int idEConDet = eConsignacionDetalleDAO.insertar(eConsigDetalle);
						eConsigDetalle.setId(idEConDet);
						List<ConsignacionsDetalleUnidad> listAux = consignacionDetalle.getConsignacionsDetalleUnidads();
						System.out.println("ListaAux: " + listAux.isEmpty());
						for(ConsignacionsDetalleUnidad consignacionUnidad : listAux){
							//Inserto Entrega Unidad
							EConsignacionsDetalleUnidad eConsigDetUnidad = new EConsignacionsDetalleUnidad();
							eConsigDetUnidad.setEConsignacionsDetalle(eConsigDetalle);
							eConsigDetUnidad.setListaPrecio(consignacionUnidad.getListaPrecio());								
							eConsigDetUnidad.setNroImei(consignacionUnidad.getNroImei());
							eConsigDetUnidad.setPrecioVenta(consignacionUnidad.getPrecioLista());
							eConsigDetUnidad.setProducto(consignacionUnidad.getProducto());
							eConsignacionDetalleUnidadDAO.insertar(eConsigDetUnidad);
							listaEConsigUnidad.add(eConsigDetUnidad);
						}
						eConsigDetalle.setEConsignacionsDetalleUnidads(listaEConsigUnidad);
						listaEConsignacionsDetalles.add(eConsigDetalle);
					}
					for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
						boolean existe = false;
						List<ConsignacionsDetalle> listDetalle = consignacionDetalleDAO.getLista(consignacion);
						for (ConsignacionsDetalle consigDetalle : listDetalle) {
							System.out.println("Compare Prod: " + (consignacionDetalle.getProducto().getId() == consigDetalle.getProducto().getId()));
							if (consignacionDetalle.getProducto().getId() == consigDetalle.getProducto().getId()) {
								int idConsignacionDetalle = consigDetalle.getId();
								int cantProd = consigDetalle.getCantidad();
								float subtot = consigDetalle.getSubtotal();
								cantProd = cantProd + consignacionDetalle.getCantidad();
								subtot = subtot + consignacionDetalle.getSubtotal();
								consignacionDetalle.setCantidad(cantProd);
								consignacionDetalle.setSubtotal(subtot);
								consignacionDetalle.setId(idConsignacionDetalle);
								existe = true;
							}
						}
						List<ConsignacionsDetalleUnidad> listAux = consignacionDetalle.getConsignacionsDetalleUnidads();
						consignacionDetalle.setConsignacionsDetalleUnidads(null);
						consignacionDetalle.setConsignacion(consignacion);					
						int idDetalle = 0;
						if (existe) {
							idDetalle = consignacionDetalleDAO.update(consignacionDetalle);
						} else {
							idDetalle = consignacionDetalleDAO.insertar(consignacionDetalle);
						}
						if(idDetalle != 0){
							consignacionDetalle.setId(idDetalle);							
							boolean insertoUnidad = true;
							for(ConsignacionsDetalleUnidad consignacionUnidad : listAux){
								consignacionUnidad.setEnabled(true);
								consignacionUnidad.setEliminado(false);
								consignacionUnidad.setFechaAlta(fechaAgrega);
								consignacionUnidad.setConsignacionsDetalle(consignacionDetalle);
								UnidadMovil unidad = unidadMovilDAO.get(consignacionUnidad.getNroImei());
								unidad.setEnConsignacion(true);
								unidadMovilDAO.update(unidad);
								Producto prod = productoDAO.get(consignacionUnidad.getProducto().getId());
								int stock = prod.getStock() - 1;
								int enConsig = prod.getEnConsignacion() + 1;
								prod.setStock(stock);
								prod.setEnConsignacion(enConsig);
								productoDAO.update(prod);
								int idDetalleUnidad = consignacionDetalleUnidadDAO.insertar(consignacionUnidad);								
								if(idDetalleUnidad == 0){
									insertoUnidad = false;
									break;
								}else {
									HistorialMovil hm = new HistorialMovil();
									hm.setFecha(new Date());
									hm.setUsuario(usuario);
									hm.setImei(consignacionUnidad.getNroImei());
									hm.setTipo("CONSIGNACION");
									hm.setDescripcion("Consignacion: " + cliente.getApellidoNombre());
									hm.setIdMovimiento(updCons);
									historialMovilDAO.insert(hm);
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
						listaClientes = new ArrayList<Cliente>();
						listaClientes = clienteDAO.getLista(true);
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consignacion guardada!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return "consignacionentrega";
						
					}else{						
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar el Detalle de Consignacion! "
								+ "Intente nuevamente!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return "";
					}
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al actualizar la consignacion!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return "";
				}				
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar fecha, e items a agregar a la consignacion!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al registrar los nuevos items! Error: " 
					+ e.getMessage(), null);			
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public String quitar() {
		try {
			consignacion.setEstado(true);
			consignacion.setFechaMod(new Date());
			consignacion.setMonto(montoTotal);
			consignacion.setUsuario3(usuario);
			int idConsignacion = consignacionDAO.update(consignacion);
			if (idConsignacion != 0) {
				for (ConsignacionsDetalle consigDetalle : listaConsignacionsDetallesQuitar) {
					boolean noExiste = true;
					List<ConsignacionsDetalleUnidad> listaUnidadsCons = new ArrayList<ConsignacionsDetalleUnidad>();
					for (ConsignacionsDetalle cDetalle : listaConsignacionsDetalles) {
						if (consigDetalle.getProducto().getId() == cDetalle.getProducto().getId()) {
							noExiste = false;
							listaUnidadsCons = cDetalle.getConsignacionsDetalleUnidads();
						}
					}
					if (noExiste) {
						List<ConsignacionsDetalleUnidad> listaUnidads = consignacionDetalleUnidadDAO.getLista(consigDetalle);
						for(ConsignacionsDetalleUnidad consignacionUnidad : listaUnidads){
							UnidadMovil unidad = unidadMovilDAO.get(consignacionUnidad.getUnidadMovil().getNroImei());
							unidad.setEnConsignacion(false);
							unidadMovilDAO.update(unidad);
							Producto prod = productoDAO.get(unidad.getProducto().getId());
							int stock = prod.getStock() + 1;
							int enConsig = prod.getEnConsignacion() - 1;
							prod.setStock(stock);
							prod.setEnConsignacion(enConsig);
							productoDAO.update(prod);
						}
						consignacionDetalleUnidadDAO.deletePorDetalle(consigDetalle);
						consignacionDetalleDAO.delete(consigDetalle);
					} else {
						List<ConsignacionsDetalleUnidad> listaUnidads = consignacionDetalleUnidadDAO.getLista(consigDetalle);
						for (ConsignacionsDetalleUnidad consigDetalleUnidad : listaUnidads) {
							boolean noExisteUnidad = true;
							for (ConsignacionsDetalleUnidad cDetalleUnidad : listaUnidadsCons) {
								if (consigDetalleUnidad.getNroImei().equals(cDetalleUnidad.getNroImei())) {
									noExisteUnidad = false;
								}
							}
							if (noExisteUnidad) {
								UnidadMovil unidad = unidadMovilDAO.get(consigDetalleUnidad.getUnidadMovil().getNroImei());
								unidad.setEnConsignacion(false);
								unidadMovilDAO.update(unidad);
								Producto prod = productoDAO.get(unidad.getProducto().getId());
								int stock = prod.getStock() + 1;
								int enConsig = prod.getEnConsignacion() - 1;
								prod.setStock(stock);
								prod.setEnConsignacion(enConsig);
								productoDAO.update(prod);
								consignacionDetalleUnidadDAO.deleteUnidad(consigDetalleUnidad);
							}
						}
					}
				}
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Se registraron los cambios en la Cosnignacion!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "consignaciones";
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al actualizar la consignacion!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "";
			}						
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al quitar items en consignacion! error: "
					+ e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
//	public String modifcar(){
//		FacesMessage msg = null;
//		String retorno = "";
//		if(consignacion.getFecha() != null && idCliente != 0 && !listaConsignacionsDetalles.isEmpty() && montoTotal != 0){
//			Cliente cli = clienteDAO.get(idCliente);
//			consignacion.setCliente(cli);
//			consignacion.setEstado(true);
//			consignacion.setFechaMod(new Date());
//			consignacion.setMonto(montoTotal);
//			consignacion.setUsuario3(usuario);
//			int idConsignacion = consignacionDAO.update(consignacion);
//			if(idConsignacion != 0){
//				consignacion.setId(idConsignacion);
//				for (ConsignacionsDetalle consignacionDetalle : consignacionDetalleDAO.getLista(consignacion)) {
//					if(!enVenta(consignacionDetalle)){
//						for(ConsignacionsDetalleUnidad consignacionUnidad : consignacionDetalleUnidadDAO.getLista(consignacionDetalle)){
//							UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
//							unidad.setEnConsignacion(false);
//							unidadMovilDAO.update(unidad);
//							Producto prod = productoDAO.get(unidad.getProducto().getId());
//							int stock = prod.getStock() + 1;
//							int enConsig = prod.getEnConsignacion() - 1;
//							prod.setStock(stock);
//							prod.setEnConsignacion(enConsig);
//							productoDAO.update(prod);
//						}
//						consignacionDetalleUnidadDAO.deletePorDetalle(consignacionDetalle);
//						consignacionDetalleDAO.delete(consignacionDetalle);
//					}else{
//						for(ConsignacionsDetalleUnidad consignacionUnidad : consignacionDetalleUnidadDAO.getLista(consignacionDetalle)){
//							if(!consignacionUnidad.getVendido()){
//								UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
//								unidad.setEnConsignacion(false);
//								unidadMovilDAO.update(unidad);
//								Producto prod = productoDAO.get(unidad.getProducto().getId());
//								int stock = prod.getStock() + 1;
//								int enConsig = prod.getEnConsignacion() - 1;
//								prod.setStock(stock);
//								prod.setEnConsignacion(enConsig);
//								productoDAO.update(prod);
//								consignacionDetalleUnidadDAO.deleteUnidad(consignacionUnidad);
//							}
//						}
//					}
//				}
//				boolean inserto = true;
//				for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
//					List<ConsignacionsDetalleUnidad> listAux = consignacionDetalle.getConsignacionsDetalleUnidads();
//					consignacionDetalle.setEliminado(false);
//					consignacionDetalle.setConsignacionsDetalleUnidads(null);
//					if(!enVenta(consignacionDetalle)){
//						consignacionDetalle.setConsignacion(consignacion);
//						int idDetalle = consignacionDetalleDAO.insertar(consignacionDetalle);
//						if(idDetalle != 0){
//							consignacionDetalle.setId(idDetalle);								
//							boolean insertoUnidad = true;
//							for(ConsignacionsDetalleUnidad consignacionUnidad : listAux){
//								UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
//								unidad.setEnConsignacion(true);
//								unidadMovilDAO.update(unidad);
//								Producto prod = productoDAO.get(unidad.getProducto().getId());
//								int stock = prod.getStock() - 1;
//								int enConsig = prod.getEnConsignacion() + 1;
//								prod.setStock(stock);
//								prod.setEnConsignacion(enConsig);
//								productoDAO.update(prod);
//								consignacionUnidad.setConsignacionsDetalle(consignacionDetalle);
//								consignacionUnidad.setEnabled(true);
//								consignacionUnidad.setEliminado(false);
//								int idDetalleUnidad = consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
//								if(idDetalleUnidad == 0){
//									insertoUnidad = false;
//									break;
//								}
//							}
//							if(!insertoUnidad){
//								inserto = false;
//								break;
//							}
//						}else{
//							inserto = false;
//							break;
//						}
//					}else{
//						consignacionDetalleDAO.update(consignacionDetalle);
//						boolean insertoUnidad = true;
//						for(ConsignacionsDetalleUnidad consignacionUnidad : listAux){
//							if(!consignacionUnidad.getVendido()){
//								UnidadMovil unidad = consignacionUnidad.getUnidadMovil();
//								unidad.setEnConsignacion(true);
//								unidadMovilDAO.update(unidad);
//								Producto prod = productoDAO.get(unidad.getProducto().getId());
//								int stock = prod.getStock() - 1;
//								int enConsig = prod.getEnConsignacion() + 1;
//								prod.setStock(stock);
//								prod.setEnConsignacion(enConsig);
//								productoDAO.update(prod);
//								consignacionUnidad.setConsignacionsDetalle(consignacionDetalle);
//								consignacionUnidad.setEnabled(true);
//								consignacionUnidad.setEliminado(false);
//								int idDetalleUnidad = consignacionDetalleUnidadDAO.insertar(consignacionUnidad);
//								if(idDetalleUnidad == 0){
//									insertoUnidad = false;
//									break;
//								}
//							}else{
//								consignacionDetalleUnidadDAO.update(consignacionUnidad);
//							}
//						}
//						if(!insertoUnidad){
//							inserto = false;
//							break;
//						}
//					}							
//				}
//				if(inserto){
//					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Consignaci�n guardada!", null);
//					retorno = "consignaciones";
//					listaClientes = new ArrayList<Cliente>();
//					listaClientes = clienteDAO.getLista(true);
//				}else{
//					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurri� un error al guardar el Detalle de Consignaci�n! "
//							+ "Int�ntelo nuevamente!", null);
//				}
//			}else{
//				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurri� un error al guardar la Consignaci�n! "
//						+ "Int�ntelo nuevamente!", null);
//			}
//		}else{
//			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Cliente, el Monto Total y el Detalle de la Consignaci�n no pueden estar vac�os!", null);
//		}
//		listaConsignacions = new ArrayList<Consignacion>();
//		filteredConsignacions = new ArrayList<Consignacion>();
//		listaConsignacions = consignacionDAO.getLista(true);
//		filteredConsignacions = listaConsignacions;
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		return retorno;
//	}
	
	public boolean enVenta(ConsignacionsDetalle consignacionDetalle){
		List<ConsignacionsDetalleUnidad> listAux = getDetalleUnidadConsignacion(consignacionDetalle);
		boolean vendido = false;
		for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listAux) {
			String imei = consignacionsDetalleUnidad.getNroImei();
			UnidadMovil unidad = unidadMovilDAO.get(imei);
			if (unidad.getId() != 0) {
				if (unidad.getEnGarantiaCliente()) {
					vendido = true;
				}
			}
			if(consignacionsDetalleUnidad.getVendido()){
				vendido = true;
			}
		}
		return vendido;
	}
	
	public boolean noBaja(ConsignacionsDetalleUnidad consignacionsUnidad) {
		boolean nobaja = false;
		String imei = consignacionsUnidad.getNroImei();
		UnidadMovil unidad = unidadMovilDAO.get(imei);
		if (unidad.getId() != 0) {
			if (unidad.getEnGarantiaCliente()) {
				nobaja = true;
			}
		}
		if(consignacionsUnidad.getVendido()){
			nobaja = true;
		}
		return nobaja;
	}
	
	public void generarReporteLista(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Comprobante> listComprobante = new ArrayList<Comprobante>();
		for (Consignacion consig : filteredConsignacions) {
			List<DetalleComprobante> listDetalle = new ArrayList<DetalleComprobante>();
			Comprobante comprobante = new Comprobante();
			for (ConsignacionsDetalle consignacionDetalle : getDetalleConsignacion(consig)) {
				DetalleComprobante detalle = new DetalleComprobante();
				detalle.setCantidad(consignacionDetalle.getCantidad());
				detalle.setDetalle(consignacionDetalle.getProducto().getNombre());
				detalle.setPrecioUnitario(formatoMonto.format(consignacionDetalle.getPrecioVenta()));
				detalle.setSubtotal(formatoMonto.format(consignacionDetalle.getSubtotal()));
				listDetalle.add(detalle);
			}
			comprobante.setFecha(formatoFecha.format(consig.getFecha()));
			comprobante.setListaDetalles(listDetalle);
			comprobante.setMonto(formatoMonto.format(consig.getMonto()));
			comprobante.setNumero(consig.getId());
			comprobante.setPersona(consig.getCliente().getNombreNegocio());
			comprobante.setTipo(consig.getTipoVenta());
			listComprobante.add(comprobante);
		}
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
		reporte.generar(parametros, listComprobante, "consignaciones", "inline");
	}
	
	public void generarReporteComprobante(Consignacion consig){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
//		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		for(ConsignacionsDetalle consignacionDetalle : getDetalleConsignacion(consig)){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(consignacionDetalle.getCantidad());
			detalle.setDetalle(consignacionDetalle.getProducto().getNombre());
			detalle.setPrecioUnitario(formatoMonto.format(consignacionDetalle.getPrecioVenta()));
			detalle.setSubtotal(formatoMonto.format(consignacionDetalle.getSubtotal()));
			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
			for (ConsignacionsDetalleUnidad consignacionsUnidad : getDetalleUnidadConsignacion(consignacionDetalle)) {
				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
				detalleUnidad.setNroImei(consignacionsUnidad.getNroImei());
				String vend = "No";
				if(consignacionsUnidad.getVendido()){
					vend = "Si";
				}
				detalleUnidad.setVendido(vend);
				detalleUnidad.setFechaAlta(consignacionsUnidad.getFechaAlta());
				detalleUnidad.setFechaVenta(consignacionsUnidad.getFechaVenta());
				listaUnidad.add(detalleUnidad);
			}
			detalle.setListaUnidads(listaUnidad);
			listaDetalle.add(detalle);
			cant++;
		}
//		parametros.put("numero", consig.getId());
		parametros.put("tipo", "Reporte");
		parametros.put("persona", consig.getCliente().getNombreNegocio());
		parametros.put("saldo", formatoMonto.format(consig.getCliente().getSaldo()));
//		parametros.put("fecha", formatoFecha.format(consig.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(consig.getMonto()));
		reporte.generar(parametros, listaDetalle, "consignacion", "attachment");
	}
	
	public void generarReporteConsig(Consignacion consig, String tipoRepor){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
//		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		float mont = 0;
		for(ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(consignacionDetalle.getCantidad());
			detalle.setDetalle(consignacionDetalle.getProducto().getNombre());
			detalle.setPrecioUnitario(formatoMonto.format(consignacionDetalle.getPrecioVenta()));
			detalle.setSubtotal(formatoMonto.format(consignacionDetalle.getSubtotal()));
			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
			for (ConsignacionsDetalleUnidad consignacionsUnidad : consignacionDetalle.getConsignacionsDetalleUnidads()) {
				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
				detalleUnidad.setNroImei(consignacionsUnidad.getNroImei());
				String vend = "No";
				if(consignacionsUnidad.getVendido()){
					vend = "Si";
				}
				detalleUnidad.setVendido(vend);
				detalleUnidad.setFechaAlta(consignacionsUnidad.getFechaAlta());
				detalleUnidad.setFechaVenta(consignacionsUnidad.getFechaVenta());
				listaUnidad.add(detalleUnidad);
			}
			detalle.setListaUnidads(listaUnidad);
			listaDetalle.add(detalle);
			cant++;			
			mont = mont + consignacionDetalle.getSubtotal();
		}
		String tipoReporte = "Reporte " + tipoRepor;		
		//parametros.put("numero", consig.getId());
		parametros.put("persona", consig.getCliente().getNombreNegocio());
		parametros.put("tipo", tipoReporte);
		parametros.put("saldo", formatoMonto.format(consig.getCliente().getSaldo()));
		//parametros.put("fecha", formatoFecha.format(consig.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(mont));
		reporte.generar(parametros, listaDetalle, "consignacion", "inline");
	}
	
	public void generarReporteStockConsig(Consignacion consig) throws ParseException {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		for(StockMoviles stockM : listaStockMoviles){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(stockM.getCantidad());
			detalle.setDetalle(stockM.getProducto());
			detalle.setPrecioUnitario(stockM.getPrecioUnitario());
			detalle.setSubtotal(stockM.getSubtotal());
			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
			for (Moviles mobile : stockM.getListaMoviles()) {
				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
				detalleUnidad.setNroImei(mobile.getNroImei());				
				detalleUnidad.setVendido("No");
				detalleUnidad.setFechaAlta(formatoFecha.parse(mobile.getFechaAlta()));
				listaUnidad.add(detalleUnidad);
			}
			detalle.setListaUnidads(listaUnidad);
			listaDetalle.add(detalle);
			cant++;
		}
		String tipoReporte = "Reporte Stock";		
		//parametros.put("numero", consig.getId());
		parametros.put("persona", consig.getCliente().getNombreNegocio());
		parametros.put("tipo", tipoReporte);
		parametros.put("saldo", formatoMonto.format(consig.getCliente().getSaldo()));
		//parametros.put("fecha", formatoFecha.format(consig.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		reporte.generar(parametros, listaDetalle, "consignacion", "inline");
	}
	
	public void generarReporteEntrega() {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		float mont = 0;
		for(EConsignacionsDetalle eConsignacionDetalle : listaEConsignacionsDetalles){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(eConsignacionDetalle.getCantidad());
			detalle.setDetalle(eConsignacionDetalle.getProducto().getNombre());
			detalle.setSubtotal(formatoMonto.format(eConsignacionDetalle.getSubtotal()));
			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
			for (EConsignacionsDetalleUnidad eConsignacionsUnidad : eConsignacionDetalle.getEConsignacionsDetalleUnidads()) {
				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
				detalleUnidad.setNroImei(eConsignacionsUnidad.getNroImei());
				detalleUnidad.setPrecioUnitario(formatoMonto.format(eConsignacionsUnidad.getPrecioVenta()));
				listaUnidad.add(detalleUnidad);
			}
			detalle.setListaUnidads(listaUnidad);
			listaDetalle.add(detalle);
			cant++;			
			mont = mont + eConsignacionDetalle.getSubtotal();
		}
		parametros.put("numero", eConsignacion.getId());
		parametros.put("persona", eConsignacion.getCliente().getNombreNegocio());
		parametros.put("fecha", formatoFecha.format(eConsignacion.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(mont));
		reporte.generar(parametros, listaDetalle, "eConsignacion", "attachment");
	}
	
	public void generarReporteEntregaMovil(EConsignacion eConsig) {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		List<EConsignacionsDetalle> listaEDetalle = eConsignacionDetalleDAO.getLista(eConsig);
		int cant = 0;
		float mont = 0;
		for(EConsignacionsDetalle eConsignacionDetalle : listaEDetalle){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(eConsignacionDetalle.getCantidad());
			detalle.setDetalle(eConsignacionDetalle.getProducto().getNombre());
			detalle.setSubtotal(formatoMonto.format(eConsignacionDetalle.getSubtotal()));
			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
			List<EConsignacionsDetalleUnidad> listaEUnidad = eConsignacionDetalleUnidadDAO.getLista(eConsignacionDetalle);
			for (EConsignacionsDetalleUnidad eConsignacionsUnidad : listaEUnidad) {
				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
				detalleUnidad.setNroImei(eConsignacionsUnidad.getNroImei());
				detalleUnidad.setPrecioUnitario(formatoMonto.format(eConsignacionsUnidad.getPrecioVenta()));
				listaUnidad.add(detalleUnidad);
			}
			detalle.setListaUnidads(listaUnidad);
			listaDetalle.add(detalle);
			cant++;			
			mont = mont + eConsignacionDetalle.getSubtotal();
		}
		parametros.put("numero", eConsig.getId());
		parametros.put("persona", eConsig.getCliente().getNombreNegocio());
		parametros.put("fecha", formatoFecha.format(eConsig.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(mont));
		reporte.generar(parametros, listaDetalle, "eConsignacion", "attachment");
	}
	
	public void generarPDFEntregasMoviles() {
		try {
			Reporte reporte = new Reporte();
			DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> parametros = new HashMap<String, Object>();
			List<Comprobante> listaComprobantes = new ArrayList<Comprobante>();
			for (EConsignacion eConsig : listaEConsignacions) {
				Comprobante comprobante = new Comprobante();
				List<DetalleComprobante> listaDetalles = new ArrayList<DetalleComprobante>();
				List<EConsignacionsDetalle> listaEDetalles = eConsignacionDetalleDAO.getLista(eConsig);
				for (EConsignacionsDetalle eConsignacionsDetalle : listaEDetalles) {
					DetalleComprobante detalleComprobante = new DetalleComprobante();
					detalleComprobante.setCantidad(eConsignacionsDetalle.getCantidad());
					detalleComprobante.setDetalle(eConsignacionsDetalle.getProducto().getNombre());
					detalleComprobante.setSubtotal(formatoMonto.format(eConsignacionsDetalle.getSubtotal()));
					listaDetalles.add(detalleComprobante);
				}
				comprobante.setNumero(eConsig.getId());
				comprobante.setFecha(formatoFecha.format(eConsig.getFecha()));
				comprobante.setPersona(eConsig.getCliente().getNombreNegocio());
				comprobante.setUsuario(eConsig.getUsuario().getUsername());
				comprobante.setMonto(formatoMonto.format(eConsig.getMonto()));
				comprobante.setListaDetalles(listaDetalles);
				listaComprobantes.add(comprobante);
			}
			String fechaD = "-";
			String fechaH = "-";
			if (fechaInicio != null && fechaFin != null) {
				fechaD = formatoFecha.format(fechaInicio);
				fechaH = formatoFecha.format(fechaFin);
			}
			parametros.put("cliente", consignacion.getCliente().getNombreNegocio());
			parametros.put("desde", fechaD);
			parametros.put("hasta", fechaH);
			reporte.generar(parametros, listaComprobantes, "eConsignaciones", "inline");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void generarXLSEntregasMoviles() {
		try {
			Reporte reporte = new Reporte();
			DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> parametros = new HashMap<String, Object>();
			List<Comprobante> listaComprobantes = new ArrayList<Comprobante>();
			for (EConsignacion eConsig : listaEConsignacions) {
				Comprobante comprobante = new Comprobante();
				List<DetalleComprobante> listaDetalles = new ArrayList<DetalleComprobante>();
				List<EConsignacionsDetalle> listaEDetalles = eConsignacionDetalleDAO.getLista(eConsig);
				for (EConsignacionsDetalle eConsignacionsDetalle : listaEDetalles) {
					DetalleComprobante detalleComprobante = new DetalleComprobante();
					detalleComprobante.setCantidad(eConsignacionsDetalle.getCantidad());
					detalleComprobante.setDetalle(eConsignacionsDetalle.getProducto().getNombre());
					detalleComprobante.setSubtotal(formatoMonto.format(eConsignacionsDetalle.getSubtotal()));
					listaDetalles.add(detalleComprobante);
				}
				comprobante.setNumero(eConsig.getId());
				comprobante.setFecha(formatoFecha.format(eConsig.getFecha()));
				comprobante.setPersona(eConsig.getCliente().getNombreNegocio());
				comprobante.setUsuario(eConsig.getUsuario().getUsername());
				comprobante.setMonto(formatoMonto.format(eConsig.getMonto()));
				comprobante.setListaDetalles(listaDetalles);
				listaComprobantes.add(comprobante);
			}
			String fechaD = "-";
			String fechaH = "-";
			if (fechaInicio != null && fechaFin != null) {
				fechaD = formatoFecha.format(fechaInicio);
				fechaH = formatoFecha.format(fechaFin);
			}
			parametros.put("cliente", consignacion.getCliente().getNombreNegocio());
			parametros.put("desde", fechaD);
			parametros.put("hasta", fechaH);
			reporte.exportXls(parametros, listaComprobantes, "excelEConsignaciones", "attachment");
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al generar el reporte! Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
}
