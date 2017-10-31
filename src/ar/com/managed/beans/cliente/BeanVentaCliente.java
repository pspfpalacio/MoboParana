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

import org.apache.log4j.Logger;

import ar.com.clases.CuentaCorriente;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Comprobante;
import ar.com.clases.auxiliares.DetalleComprobante;
import ar.com.clases.auxiliares.DetalleComprobanteUnidad;
import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesCliente;
import model.entity.EquipoPendientePago;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Mensaje;
import model.entity.Producto;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOEquipoPendientePago;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOMensaje;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOUsuario;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanVentaCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanVentaCliente.class);
	
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
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}") 
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanMensajeDAO}")
	private DAOMensaje mensajeDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;

	@ManagedProperty(value = "#{BeanEquipoPendientePagoDAO}")
	private DAOEquipoPendientePago equipoPendientePagoDAO;
	
	private List<ConsignacionsDetalle> listaConsignacionsDetalles;
	private List<VentasCon> listaVentasCons;
	private List<VentasCon> filteredVentasCons;
	private List<DetalleComprobante> listaDetalleComprobantes;
	private Usuario usuario;
	private Consignacion consignacion;
	private Cliente cliente;
	private Producto producto;
	private UnidadMovil unidadMovil;
	private Comprobante comprobante;
	private EquipoPendientePago equipoPendientePago;
	private Date fecha;
	private Date fechaInicio;
	private Date fechaFin;
	private String nroImei;
	private int cantidadTotal;
	private float montoTotal;
	private float precioVenta;
	private boolean agregar;

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

	public DAOMensaje getMensajeDAO() {
		return mensajeDAO;
	}

	public void setMensajeDAO(DAOMensaje mensajeDAO) {
		this.mensajeDAO = mensajeDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}
	
	public DAOEquipoPendientePago getEquipoPendientePagoDAO() {
		return equipoPendientePagoDAO;
	}

	public void setEquipoPendientePagoDAO(DAOEquipoPendientePago equipoPendientePagoDAO) {
		this.equipoPendientePagoDAO = equipoPendientePagoDAO;
	}

	public List<ConsignacionsDetalle> getListaConsignacionsDetalles() {
		return listaConsignacionsDetalles;
	}

	public void setListaConsignacionsDetalles(
			List<ConsignacionsDetalle> listaConsignacionsDetalles) {
		this.listaConsignacionsDetalles = listaConsignacionsDetalles;
	}

	public List<VentasCon> getListaVentasCons() {
		return listaVentasCons;
	}

	public void setListaVentasCons(List<VentasCon> listaVentasCons) {
		this.listaVentasCons = listaVentasCons;
	}

	public List<VentasCon> getFilteredVentasCons() {
		return filteredVentasCons;
	}

	public void setFilteredVentasCons(List<VentasCon> filteredVentasCons) {
		this.filteredVentasCons = filteredVentasCons;
	}

	public List<DetalleComprobante> getListaDetalleComprobantes() {
		return listaDetalleComprobantes;
	}

	public void setListaDetalleComprobantes(
			List<DetalleComprobante> listaDetalleComprobantes) {
		this.listaDetalleComprobantes = listaDetalleComprobantes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public EquipoPendientePago getEquipoPendientePago() {
		return equipoPendientePago;
	}

	public void setEquipoPendientePago(EquipoPendientePago equipoPendientePago) {
		this.equipoPendientePago = equipoPendientePago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
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

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	public boolean isAgregar() {
		return agregar;
	}

	public void setAgregar(boolean agregar) {
		this.agregar = agregar;
	}

	public String goVenta(Usuario user) {
		usuario = new Usuario();
		producto = new Producto();
		cliente = new Cliente();		
		consignacion = new Consignacion();
		unidadMovil = new UnidadMovil();
		comprobante = new Comprobante();
		listaDetalleComprobantes = new ArrayList<DetalleComprobante>();
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		nroImei = "";
		agregar = true;
		precioVenta = 0;
		montoTotal = 0;
		cantidadTotal = 0;
		fecha = new Date();
		usuario = user;
		cliente = user.getCliente();
		consignacion = consignacionDAO.get(cliente, true);
		return "ventaCliente";
	}
	
	public void onBlurNroImei(){
		log.info("onBlurNroImei() - nroImei: " + nroImei);
		producto = new Producto();
		agregar = true;
		precioVenta = 0;
		if(!nroImei.isEmpty() && nroImei != null){
			unidadMovil = unidadMovilDAO.get(nroImei);
			ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.getUnidad(nroImei, consignacion, cliente);
			log.info("ConsignacionUnidad id: " + consignacionUnidad.getId());
			if (consignacionUnidad.getId() != 0) {
				log.info("UnidadMovil id: " + unidadMovil.getId());
				if (unidadMovil.getId() != 0) {					
					if (!unidadMovil.getEnGarantiaCliente() && !unidadMovil.getEnGarantiaProveedor() && unidadMovil.getEnStock() && !unidadMovil.getEnVenta() && unidadMovil.getEnConsignacion() && unidadMovil.getEstado() && !unidadMovil.getEliminado()) {
						producto = unidadMovil.getProducto();
						ListaPrecio lista = new ListaPrecio();
						lista = consignacionUnidad.getListaPrecio();
						log.info("ListaPrecio id: " + lista.getId());
						if(lista.getId() != 0){
							ListaPrecioProducto precioProducto = new ListaPrecioProducto();
							precioProducto = listaPrecioDAO.getItemProducto(lista, producto);							
							precioVenta = precioProducto.getPrecioVenta();					
							log.info("PrecioProducto id: " + precioProducto.getId() + " - precioVenta: " + precioVenta);							
							if (precioVenta > 0) {
								float costoPromedio = producto.getCostoPromedio();
								log.info("CostoPromedio: " + costoPromedio);
								if (costoPromedio != 0) {
									if (precioVenta < costoPromedio) {
										FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible vender el producto, contactese con su proveedor!", null);
										FacesContext.getCurrentInstance().addMessage(null, msg);
									} else {
										float precioLista = consignacionUnidad.getPrecioLista();
										FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El precio que debe abonar por el movil es PRECIO: $" + precioVenta 
												+ ", el movil se consigno al PRECIO: $" + precioLista + ".", null);
										FacesContext.getCurrentInstance().addMessage(null, msg);
										agregar = false;
									}
								} else {
									float precioLista = consignacionUnidad.getPrecioLista();
									FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El precio que debe abonar por el movil es PRECIO: $" + precioVenta 
											+ ", el movil se consigno al PRECIO: $" + precioLista + ".", null);
									FacesContext.getCurrentInstance().addMessage(null, msg);
									agregar = false;
								}
							} else {
								log.info("PrecioVenta: " + precioVenta);
								FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible vender el producto, contactese con su proveedor!", null);
								FacesContext.getCurrentInstance().addMessage(null, msg);
							}
						} else {							
							log.info("ListaPrecio id: " + lista.getId());
							FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible vender el producto, contactese con su proveedor!", null);
							FacesContext.getCurrentInstance().addMessage(null, msg);
						}						
					} else {
						log.info("enGarantiaCliente: " + unidadMovil.getEnGarantiaCliente() + " - enGarantiaProveedor: " + unidadMovil.getEnGarantiaProveedor() + " - enStock: " + unidadMovil.getEnStock() + " - enVenta: " + unidadMovil.getEnVenta() + " - enConsignacion: " + unidadMovil.getEnConsignacion() + " - estado: " + unidadMovil.getEstado() + " - eliminado: " + unidadMovil.getEliminado());						
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un problema grave, contactese con su proveedor! El nro de Imei no corresponde a ningun producto en Stock!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un problema grave, contactese con su proveedor! El nro de Imei no corresponde a ningun producto en Stock!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un nro de Imei valido! El nro de Imei no corresponde a ningun producto en Stock!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		}
	}
	
	public void agregarProducto(){
		boolean noAgregado = true;
		log.info("Lista detalles size() - " + listaConsignacionsDetalles.size());
		for (ConsignacionsDetalle consignacionDetalle : listaConsignacionsDetalles) {
			List<ConsignacionsDetalleUnidad> listaUnidades = consignacionDetalle.getConsignacionsDetalleUnidads();
			log.info("Lista unidades size() - " + listaUnidades.size());
			for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listaUnidades) {
				log.info("Nro imei listado: " + consignacionsDetalleUnidad.getNroImei());
				log.info("Nro imei ingresado: " + unidadMovil.getNroImei());
				if (consignacionsDetalleUnidad.getNroImei().equals(unidadMovil.getNroImei())) {
					noAgregado = false;
				}
			}
		}
		if(producto.getId() != 0 && unidadMovil != null && precioVenta != 0 && noAgregado){
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
	
	public String generarVenta() {
		try {
			FacesMessage msg = null;
			String retorno = "";
			if (!listaConsignacionsDetalles.isEmpty() && montoTotal != 0) {
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				
				comprobante = new Comprobante();
				listaDetalleComprobantes = new ArrayList<DetalleComprobante>();
				
				VentasCon ventasCon = new VentasCon();
				ventasCon.setEstado(true);
				ventasCon.setFechaAlta(new Date());
				ventasCon.setMonto(montoTotal);
				ventasCon.setUsuario1(usuario);
				ventasCon.setCliente(cliente);
				ventasCon.setConsignacion(consignacion);
				ventasCon.setFecha(fecha);		
				ventasCon.setTipo("c.c.");
				
				float saldoCli = cliente.getSaldo();
				// INSERCION EN CUENTA CORRIENTE DE VENTA			
				ventasCon.setSaldoCliente(saldoCli);
				int idVenta = ventaConsignacionDAO.insertar(ventasCon);				
				
				ccCliente.setCliente(cliente);
				ccCliente.setDebe(montoTotal);
				ccCliente.setDetalle("Venta Consignación nro: " + idVenta);				
				ccCliente.setFecha(fecha);
				ccCliente.setIdMovimiento(idVenta);
				ccCliente.setMonto(montoTotal);
				ccCliente.setNombreTabla("VentasCon");
				ccCliente.setUsuario(usuario);
				cuenta.insertarCC(ccCliente);	
				
				log.info("idVenta " + idVenta);
				
				if (idVenta != 0) {
					boolean inserto = true;
					ventasCon.setId(idVenta);
					//SET DE COMPROBANTE PARA REPORTE
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
					comprobante.setNumero(idVenta);
					comprobante.setFecha(formatoFecha.format(fecha));
					comprobante.setPersona(cliente.getApellidoNombre());
					//INSERCION EN MENSAJE
					SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
					Mensaje mensaje = new Mensaje();
					String asunto = "Nueva venta de consignación realizada";
					String detalle = "El usuario cliente " + usuario.getCliente().getApellidoNombre() + " realizó una venta con número " + Integer.toString(idVenta) + " por " + formatoMonto.format(montoTotal) + " en el día y horario " + formatoFechaHora.format(new Date()) + ". Para ver el detalle de la venta diríjase a Consignaciones - Ventas de Consignaciones.";
					mensaje.setAsunto(asunto);
					mensaje.setCliente(cliente);
					mensaje.setDetalle(detalle);
					mensaje.setFechaHora(new Date());
					mensaje.setUsuario(usuario);
					mensaje.setVentasCon(ventasCon);
					mensaje.setVisto(false);
					mensajeDAO.insertar(mensaje);					
					for (ConsignacionsDetalle consignacionsDetalle : listaConsignacionsDetalles) {		
						ConsignacionsDetalle consDetalle = consignacionDetalleDAO.get(consignacion, consignacionsDetalle.getProducto());
						//ACTUAIZACION DE STOCK EN PRODUCTO
						int idProd = consignacionsDetalle.getProducto().getId();
						Producto prod = productoDAO.get(idProd);
						int stockConsignacion = prod.getEnConsignacion();
						int cantidad = stockConsignacion - consignacionsDetalle.getCantidad();
						prod.setEnConsignacion(cantidad);
						productoDAO.update(prod);
						//INSERTO VENTA DETALLE
						VentasConsDetalle ventaDetalle = new VentasConsDetalle();
						ventaDetalle.setCantidad(consignacionsDetalle.getCantidad());
						ventaDetalle.setConsignacionsDetalle(consDetalle);
						ventaDetalle.setEliminado(false);
						ventaDetalle.setPrecioVenta(consignacionsDetalle.getPrecioVenta());
						ventaDetalle.setProducto(consignacionsDetalle.getProducto());
						ventaDetalle.setSubtotal(consignacionsDetalle.getSubtotal());
						ventaDetalle.setVentasCon(ventasCon);
						int idDetalle = ventaConsignacionDetalleDAO.insertar(ventaDetalle);	
						List<DetalleComprobanteUnidad> listaComprobanteDetalleUnidads = new ArrayList<DetalleComprobanteUnidad>();
						if (idDetalle != 0) {
							ventaDetalle.setId(idDetalle);						
							boolean insertoUnidad = true;
							for (ConsignacionsDetalleUnidad consignacionUnidad : consignacionsDetalle.getConsignacionsDetalleUnidads()) {
								String imei = consignacionUnidad.getNroImei();
								//ACTUALIZO CONSIGNACION UNIDAD
								ConsignacionsDetalleUnidad consUnidad = consignacionDetalleUnidadDAO.get(imei);
								consUnidad.setVendido(true);
								consUnidad.setTipoVenta("c.c.");							
								consUnidad.setFechaVenta(fecha);								
								//ACTUAIZO UNIDAD MOVIL
								UnidadMovil unidad = unidadMovilDAO.get(imei);
								unidad.setEnStock(false);
								unidad.setEnVenta(true);
								//INSERTO DETALLE UNIDAD
								VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();	
								ventaUnidad.setConsignacionsDetalleUnidad(consUnidad);
								ventaUnidad.setEliminado(false);
								ventaUnidad.setNroImei(imei);
								ventaUnidad.setPrecioCompra(unidad.getPrecioCompra());
								ventaUnidad.setPrecioVenta(consignacionUnidad.getPrecioLista());	
								ventaUnidad.setVentasConsDetalle(ventaDetalle);
								int idDetalleUnidad = ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
								int updateUniCons = consignacionDetalleUnidadDAO.update(consUnidad);
								int updateUnidad = unidadMovilDAO.update(unidad);
								//SETEO UNIDAD DE COMPROBANTE
								DetalleComprobanteUnidad unidadComprobante = new DetalleComprobanteUnidad();
								unidadComprobante.setPrecioUnitario(formatoMonto.format(consignacionUnidad.getPrecioLista()));
								unidadComprobante.setNroImei(imei);
								listaComprobanteDetalleUnidads.add(unidadComprobante);
								//INSERTO EN TABLA DE PENDIENTE DE PAGO
								EquipoPendientePago ePendienteP = new EquipoPendientePago();
								ePendienteP.setMonto(consignacionUnidad.getPrecioLista());
								ePendienteP.setImei(imei);
								ePendienteP.setCliente(cliente);
								ePendienteP.setFechaAlta(new Date());
								ePendienteP.setUsuario1(usuario);
								ePendienteP.setEnabled(true);
								int idEPendienteP = equipoPendientePagoDAO.insert(ePendienteP);
								log.info("idDetalleUnidad " + idDetalleUnidad);
								log.info("updateUnidad " + updateUnidad);
								log.info("updateUniCons " + updateUniCons);
								log.info("idEPendienteP " + idEPendienteP);
								if (idDetalleUnidad == 0 || updateUnidad == 0 || updateUniCons == 0  || idEPendienteP == 0) {
									insertoUnidad = false;
									break;
								}
							}
							if (!insertoUnidad) {
								inserto = false;
								break;
							}
						} else {
							inserto = false;
							break;
						}
						//SETEO VALORES EN DETALLE DE COMPROBANTE
						DetalleComprobante detalleComprobante = new DetalleComprobante();
						detalleComprobante.setCantidad(consignacionsDetalle.getCantidad());
						detalleComprobante.setDetalle(consignacionsDetalle.getProducto().getNombre());
						detalleComprobante.setSubtotal(formatoMonto.format(consignacionsDetalle.getSubtotal()));
						detalleComprobante.setListaUnidads(listaComprobanteDetalleUnidads);
						listaDetalleComprobantes.add(detalleComprobante);
					}
					if (inserto) {
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta registrada!", null);
						retorno = "comprobante";
					} else {
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al registrar el Detalle de la Venta! "
								+ "Inténtelo nuevamente!", null);
					}					
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar la Venta! "
							+ "Inténtelo nuevamente!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Monto Total y el Detalle de la Venta no pueden estar vacíos!", null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error grave al generar la venta! Contáctese con el administrador! Error: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
	
	public String cancelar() {
		unidadMovil = new UnidadMovil();		
		comprobante = new Comprobante();
		listaDetalleComprobantes = new ArrayList<DetalleComprobante>();
		listaConsignacionsDetalles = new ArrayList<ConsignacionsDetalle>();
		nroImei = "";
		agregar = true;
		precioVenta = 0;
		fecha = new Date();
		return "ventaCliente";
	}
	
	public void generarReporteVenta() {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listDetalles = new ArrayList<DetalleComprobante>();		
		for (DetalleComprobante dComprobante : listaDetalleComprobantes) {
			DetalleComprobante detalleComprobante = new DetalleComprobante();
			detalleComprobante.setCantidad(dComprobante.getCantidad());
			detalleComprobante.setDetalle(dComprobante.getDetalle());
			detalleComprobante.setSubtotal(dComprobante.getSubtotal());
			
			for (DetalleComprobanteUnidad dUnidad : dComprobante.getListaUnidads()) {
				detalleComprobante.setNroImei(dUnidad.getNroImei());
				detalleComprobante.setPrecioUnitario(dUnidad.getPrecioUnitario());
				listDetalles.add(detalleComprobante);
			}
		}		
		parametros.put("cliente", comprobante.getPersona());
		parametros.put("fecha", comprobante.getFecha());
		parametros.put("numero", comprobante.getNumero());
		parametros.put("montoTotal", formatoMonto.format(montoTotal));
		parametros.put("cantidadTotal", cantidadTotal);
		parametros.put("fechaEmision", formatoFecha.format(new Date()));
		parametros.put("usuarioEmision", usuario.getUsername());		
		reporte.generar(parametros, listDetalles, "venta_clientes", "attachment");
	}
	
	public void generarReporteVenta(VentasCon ventasCon) {
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("$###,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listDetalles = new ArrayList<DetalleComprobante>();
		int cantTotal = 0;
		List<VentasConsDetalle> listaDetalleVenta = ventaConsignacionDetalleDAO.getLista(ventasCon);
		for (VentasConsDetalle ventaConsDetalle : listaDetalleVenta) {
			DetalleComprobante detalleComprobante = new DetalleComprobante();
			detalleComprobante.setCantidad(ventaConsDetalle.getCantidad());
			detalleComprobante.setDetalle(ventaConsDetalle.getProducto().getNombre());
			detalleComprobante.setSubtotal(formatoMonto.format(ventaConsDetalle.getSubtotal()));
			
			cantTotal = cantTotal + ventaConsDetalle.getCantidad();
			
			List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventaConsDetalle);			
			for (VentasConsDetalleUnidad dUnidad : listaUnidads) {
				detalleComprobante.setNroImei(dUnidad.getNroImei());
				detalleComprobante.setPrecioUnitario(formatoMonto.format(dUnidad.getPrecioVenta()));
				listDetalles.add(detalleComprobante);
			}
		}		
		parametros.put("cliente", ventasCon.getCliente().getApellidoNombre());
		parametros.put("fecha", formatoFecha.format(ventasCon.getFecha()));
		parametros.put("numero", ventasCon.getId());
		parametros.put("montoTotal", formatoMonto.format(ventasCon.getMonto()));
		parametros.put("cantidadTotal", cantTotal);
		parametros.put("fechaEmision", formatoFechaHora.format(new Date()));
		parametros.put("usuarioEmision", usuario.getUsername());		
		reporte.generar(parametros, listDetalles, "venta_clientes", "attachment");
	}
	
	public String goVentas(Usuario user) {
		try {
			usuario = new Usuario();
			cliente = new Cliente();
			consignacion = new Consignacion();		
			fechaInicio = null;
			fechaFin = null;
			listaVentasCons = new ArrayList<VentasCon>();
			usuario = user;
			cliente = user.getCliente();
			consignacion = consignacionDAO.get(cliente, true);
			listaVentasCons = ventaConsignacionDAO.getLista(true, consignacion, user);			
			if (listaVentasCons.isEmpty()) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La consignacion no posee ventas realizadas!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "";
			} else {
				return "ventasCliente";
			}	
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}			
	}
	
	public void filtro(){
		listaVentasCons = new ArrayList<VentasCon>();
		filteredVentasCons = new ArrayList<VentasCon>();
		if(fechaInicio == null && fechaFin == null){
			listaVentasCons = ventaConsignacionDAO.getLista(true, consignacion, usuario);
		}
		if(fechaInicio != null && fechaFin != null){
			listaVentasCons = ventaConsignacionDAO.getLista(true, consignacion, fechaInicio, fechaFin, usuario);
		}
		filteredVentasCons = listaVentasCons;
	}
	
	public List<VentasConsDetalle> getDetalleDeVenta(VentasCon venCons){
		List<VentasConsDetalle> listAux = new ArrayList<VentasConsDetalle>();
		listAux = ventaConsignacionDetalleDAO.getLista(venCons);
		return listAux;
	}
	
	public List<VentasConsDetalleUnidad> getDetalleUnidadVendidaConsignacion(VentasConsDetalle ventaDetalle){
		List<VentasConsDetalleUnidad> listAux = ventaConsignacionDetalleUnidadDAO.getLista(ventaDetalle);
		return listAux;
	}
	
//	public void generarReporteComprobante(VentasCon ventCons){
//		Reporte reporte = new Reporte();
//		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
//		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
//		int cant = 0;
//		for(VentasConsDetalle ventasConsDetalle : getDetalleDeVenta(ventCons)){
//			DetalleComprobante detalle = new DetalleComprobante();
//			detalle.setCantidad(ventasConsDetalle.getCantidad());
//			detalle.setDetalle(ventasConsDetalle.getProducto().getNombre());
//			detalle.setSubtotal(formatoMonto.format(ventasConsDetalle.getSubtotal()));
//			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
//			for (VentasConsDetalleUnidad ventasConsDetalleUnidad : getDetalleUnidadVendidaConsignacion(ventasConsDetalle)) {
//				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
//				detalleUnidad.setNroImei(ventasConsDetalleUnidad.getNroImei());
//				detalleUnidad.setPrecioVenta(ventasConsDetalleUnidad.getPrecioVenta());
//				listaUnidad.add(detalleUnidad);
//				cant++;
//			}
//			detalle.setListaUnidads(listaUnidad);
//			listaDetalle.add(detalle);			
//		}
//		parametros.put("numero", ventCons.getId());
//		parametros.put("persona", ventCons.getCliente().getNombreNegocio());
//		parametros.put("fecha", formatoFecha.format(ventCons.getFecha()));
//		parametros.put("cantidadTotal", cant);
//		parametros.put("montoTotal", formatoMonto.format(ventCons.getMonto()));
//		reporte.generar(parametros, listaDetalle, "ventaConsignacion", "attachment");
//	}

}
