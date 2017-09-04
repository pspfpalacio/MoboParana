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

import ar.com.clases.CuentaCorriente;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Comprobante;
import ar.com.clases.auxiliares.DetalleComprobante;
import ar.com.clases.auxiliares.DetalleComprobanteUnidad;
import model.entity.Cliente;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesCliente;
import model.entity.Cuota;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
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
import dao.interfaces.DAOCuota;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanVentaConsignacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
	private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	private List<VentasCon> listaVentasCons;
	private List<VentasCon> filteredVentasCons;
	private List<VentasConsDetalle> listaVentasConsDetalles;
	private List<VentasConsDetalle> listaDetalleBaja;
	private List<ConsignacionsDetalleUnidad> listaConsignacionsDetallesUnidad;
	private List<ConsignacionsDetalleUnidad> selectionConsignacionsDetallesUnidad;
	private VentasCon ventasCon;
	private Consignacion consignacion;
	private Usuario usuario;
	private Date fechaInicio;
	private Date fechaFin;
	private int cantidadTotal;
	private float montoTotal;

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

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
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

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
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

	public List<VentasConsDetalle> getListaVentasConsDetalles() {
		return listaVentasConsDetalles;
	}

	public void setListaVentasConsDetalles(
			List<VentasConsDetalle> listaVentasConsDetalles) {
		this.listaVentasConsDetalles = listaVentasConsDetalles;
	}

	public List<VentasConsDetalle> getListaDetalleBaja() {
		return listaDetalleBaja;
	}

	public void setListaDetalleBaja(List<VentasConsDetalle> listaDetalleBaja) {
		this.listaDetalleBaja = listaDetalleBaja;
	}

	public List<ConsignacionsDetalleUnidad> getListaConsignacionsDetallesUnidad() {
		return listaConsignacionsDetallesUnidad;
	}

	public void setListaConsignacionsDetallesUnidad(
			List<ConsignacionsDetalleUnidad> listaConsignacionsDetallesUnidad) {
		this.listaConsignacionsDetallesUnidad = listaConsignacionsDetallesUnidad;
	}

	public List<ConsignacionsDetalleUnidad> getSelectionConsignacionsDetallesUnidad() {
		return selectionConsignacionsDetallesUnidad;
	}

	public void setSelectionConsignacionsDetallesUnidad(
			List<ConsignacionsDetalleUnidad> selectionConsignacionsDetallesUnidad) {
		this.selectionConsignacionsDetallesUnidad = selectionConsignacionsDetallesUnidad;
	}

	public VentasCon getVentasCon() {
		return ventasCon;
	}

	public void setVentasCon(VentasCon ventasCon) {
		this.ventasCon = ventasCon;
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
	
	public String goVentas(Consignacion consig, Usuario user) {
		consignacion = new Consignacion();
		usuario = new Usuario();
		listaVentasCons = new ArrayList<VentasCon>();
		consignacion = consig;
		usuario = user;
		listaVentasCons = ventaConsignacionDAO.getLista(true, consig);
		if (listaVentasCons.isEmpty()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La consignación no posee ventas realizadas!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		} else {
			return "ventas_consignacion";
		}
	}

	public String goGenerarVenta(Consignacion consig, Usuario user){
		consignacion = new Consignacion();
		usuario = new Usuario();
		montoTotal = 0;
		cantidadTotal = 0;
		listaVentasConsDetalles = new ArrayList<VentasConsDetalle>();
		listaConsignacionsDetallesUnidad = new ArrayList<ConsignacionsDetalleUnidad>();
		selectionConsignacionsDetallesUnidad = new ArrayList<ConsignacionsDetalleUnidad>();
		consignacion = consig;
		usuario = user;
		List<ConsignacionsDetalleUnidad> listaAux = consignacionDetalleUnidadDAO.getLista(consig, false);
		List<Cuota> listaCuotas = cuotaDAO.getLista(true, consig);
		for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listaAux) {
			String imei = consignacionsDetalleUnidad.getNroImei();
			
			boolean noExiste = true;
			for (Cuota cuota : listaCuotas) {
				if (imei.equals(cuota.getNroImei())) {
					noExiste = false;
				}
			}
			if (noExiste) {
				ListaPrecio lista = consignacionsDetalleUnidad.getListaPrecio();
				Producto prod = consignacionsDetalleUnidad.getProducto();
				ListaPrecioProducto precioProducto = listaPrecioDAO.getItemProducto(lista, prod);
				float precioV = precioProducto.getPrecioVenta();
				consignacionsDetalleUnidad.setPrecioLista(precioV);
				listaConsignacionsDetallesUnidad.add(consignacionsDetalleUnidad);
			}
		}
		return "ventaconsignacion";
	}
	
	public String goVenta() {
		FacesMessage msg = null;
		try {
			String retorno = "";
			if (!selectionConsignacionsDetallesUnidad.isEmpty()) {
				montoTotal = 0;
				cantidadTotal = 0;
				ventasCon = new VentasCon();
				Cliente cliente = clienteDAO.get(consignacion.getCliente().getId());
				ventasCon.setCliente(cliente);
				ventasCon.setConsignacion(consignacion);
				ventasCon.setEstado(true);
				ventasCon.setFecha(new Date());
				ventasCon.setFechaAlta(new Date());
				ventasCon.setTipo("c.c.");
				ventasCon.setUsuario1(usuario);
				
				List<VentasConsDetalle> listAux = new ArrayList<VentasConsDetalle>();
				List<ConsignacionsDetalleUnidad> listaConsUnidades = selectionConsignacionsDetallesUnidad;
				listaVentasConsDetalles = new ArrayList<VentasConsDetalle>();
				for (ConsignacionsDetalleUnidad consignacionUnidad : selectionConsignacionsDetallesUnidad) {
					String imei = consignacionUnidad.getNroImei();
					UnidadMovil unidad = unidadMovilDAO.get(imei);
					int idProducto = unidad.getProducto().getId();
					Producto producto = productoDAO.get(idProducto);
					boolean noExiste = true;
					for (VentasConsDetalle ventaDetalle : listAux) {
						if (ventaDetalle.getProducto().getId() == producto.getId()) {
							noExiste = false;
						}
					}
					if (listAux.isEmpty() || noExiste) {
						VentasConsDetalle ventasDetalle = new VentasConsDetalle();
						ventasDetalle = getDetalleDeConsignacion(producto, listaConsUnidades);
						listAux.add(ventasDetalle);
					}
				}
				listaVentasConsDetalles = listAux;
				retorno = "venta_consignacion";
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe seleccionar al menos una Unidad!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			return retorno;
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error con la seleccion de productos! Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}	
	
	public String goVentaEditar(VentasCon venCons) {
		try {
			ventasCon = new VentasCon();
			ventasCon = venCons;
			listaDetalleBaja = new ArrayList<VentasConsDetalle>();
			listaVentasConsDetalles = new ArrayList<VentasConsDetalle>();
			List<VentasConsDetalle> listAux = ventaConsignacionDetalleDAO.getLista(venCons);
			cantidadTotal = 0;
			montoTotal = 0;
			for (VentasConsDetalle ventasConsDetalle : listAux) {
//				System.out.println("Producto: " + ventasConsDetalle.getProducto().getNombre());
				List<VentasConsDetalleUnidad> listUnidads = new ArrayList<VentasConsDetalleUnidad>();
				listUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventasConsDetalle);
				ventasConsDetalle.setVentasConsDetalleUnidads(listUnidads);
//				for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listUnidads) {
//					System.out.println("Nro Imei: " + ventasConsDetalleUnidad.getNroImei());
//				}
				VentasConsDetalle ventaDetalle = new VentasConsDetalle();
				ventaDetalle.setCantidad(ventasConsDetalle.getCantidad());
				ventaDetalle.setConsignacionsDetalle(ventasConsDetalle.getConsignacionsDetalle());
				ventaDetalle.setEliminado(ventasConsDetalle.getEliminado());
				ventaDetalle.setId(ventasConsDetalle.getId());
				ventaDetalle.setPrecioVenta(ventasConsDetalle.getPrecioVenta());
				ventaDetalle.setProducto(ventasConsDetalle.getProducto());
				ventaDetalle.setSubtotal(ventasConsDetalle.getSubtotal());
				ventaDetalle.setVentasCon(ventasConsDetalle.getVentasCon());
				ventaDetalle.setVentasConsDetalleUnidads(listUnidads);
				listaVentasConsDetalles.add(ventaDetalle);
				VentasConsDetalle ventaDetalleBaja = new VentasConsDetalle();
				ventaDetalleBaja.setCantidad(ventasConsDetalle.getCantidad());
				ventaDetalleBaja.setConsignacionsDetalle(ventasConsDetalle.getConsignacionsDetalle());
				ventaDetalleBaja.setEliminado(ventasConsDetalle.getEliminado());
				ventaDetalleBaja.setId(ventasConsDetalle.getId());
				ventaDetalleBaja.setPrecioVenta(ventasConsDetalle.getPrecioVenta());
				ventaDetalleBaja.setProducto(ventasConsDetalle.getProducto());
				ventaDetalleBaja.setSubtotal(ventasConsDetalle.getSubtotal());
				ventaDetalleBaja.setVentasCon(ventasConsDetalle.getVentasCon());
				ventaDetalleBaja.setVentasConsDetalleUnidads(listUnidads);
				listaDetalleBaja.add(ventaDetalleBaja);
				cantidadTotal = cantidadTotal + ventasConsDetalle.getCantidad();
			}
			montoTotal = venCons.getMonto();
			return "ventamodif";
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al redireccionar a la página de modificación! Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
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
	
	public float getMontoEnVenta(List<ConsignacionsDetalleUnidad> listAux){
		float total = 0;
		for (ConsignacionsDetalleUnidad consignacionUnidad : listAux){
			total = total + consignacionUnidad.getPrecioLista();
		}
		return total;
	}
	
	public void filtro(){
		listaVentasCons = new ArrayList<VentasCon>();
		filteredVentasCons = new ArrayList<VentasCon>();
		if(fechaInicio == null && fechaFin == null){
			listaVentasCons = ventaConsignacionDAO.getLista(true, consignacion);
		}
		if(fechaInicio != null && fechaFin != null){
			listaVentasCons = ventaConsignacionDAO.getLista(true, consignacion, fechaInicio, fechaFin);
		}
		filteredVentasCons = listaVentasCons;
	}
	
	public void quitarProductoVenta(VentasConsDetalle ventaDetalle) {
		List<VentasConsDetalle> listAux = new ArrayList<VentasConsDetalle>();
		for (VentasConsDetalle ventasConsDetalle : listaVentasConsDetalles) {
			if (ventasConsDetalle.getProducto().getId() != ventaDetalle.getProducto().getId()) {
				listAux.add(ventasConsDetalle);
			} else {
				montoTotal = montoTotal - ventaDetalle.getSubtotal();
				cantidadTotal = cantidadTotal - ventaDetalle.getCantidad();
			}
		}
		listaVentasConsDetalles = new ArrayList<VentasConsDetalle>();
		listaVentasConsDetalles = listAux;
	}
	
	public void quitarUnidadDetalleVenta (VentasConsDetalle ventaDetalle, VentasConsDetalleUnidad ventaUnidad) {
		List<VentasConsDetalleUnidad> listAux = new ArrayList<VentasConsDetalleUnidad>();
		List<VentasConsDetalleUnidad> listaUnidades = ventaDetalle.getVentasConsDetalleUnidads();
		for (VentasConsDetalleUnidad unidadDetalle : listaUnidades) {
			if (!unidadDetalle.getNroImei().equals(ventaUnidad.getNroImei())) {
				listAux.add(unidadDetalle);
			}
		}
		montoTotal = montoTotal - ventaUnidad.getPrecioVenta();
		float subtotal = ventaDetalle.getSubtotal() - ventaUnidad.getPrecioVenta();
		cantidadTotal = cantidadTotal - 1;
		int cantidad = ventaDetalle.getCantidad() - 1;
		ventaDetalle.setCantidad(cantidad);
		ventaDetalle.setSubtotal(subtotal);
		ventaDetalle.setVentasConsDetalleUnidads(listAux);
		List<VentasConsDetalle> listaDetalles = new ArrayList<VentasConsDetalle>();
		for (VentasConsDetalle ventasConsDetalle : listaVentasConsDetalles) {
			if (ventasConsDetalle.getProducto().getId() != ventaDetalle.getProducto().getId()) {
				listaDetalles.add(ventasConsDetalle);
			} else {
				if (cantidad != 0) {
					listaDetalles.add(ventaDetalle);
				}
			}
		}
		listaVentasConsDetalles = new ArrayList<VentasConsDetalle>();
		listaVentasConsDetalles = listaDetalles;
	}
	
	public void baja(VentasCon venCons) {
		FacesMessage msg = null;
		try {
			boolean noAutorizo = false;
			Consignacion cons = venCons.getConsignacion();
			List<Cuota> listaCuotas = cuotaDAO.getLista(true, cons);
			List<VentasConsDetalle> listVentDet = ventaConsignacionDetalleDAO.getLista(venCons);
			List<VentasConsDetalleUnidad> listVentDetUnid = ventaConsignacionDetalleUnidadDAO.getLista(venCons);
			//CONTROL QUE LA UNIDAD MOVIL NO POSEA CUOTAS
			for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listVentDetUnid) {
				String nroImei = ventasConsDetalleUnidad.getNroImei();
				for (Cuota cuota : listaCuotas) {
					String imeiCuota = cuota.getNroImei();
					if (nroImei.equals(imeiCuota)) {
						noAutorizo = true;
					}
				}
			}
			//CONTROL DE FALLA DEL DETALLE
			for (VentasConsDetalle ventasDetalle : listVentDet) {
				noAutorizo = conFalla(ventasDetalle);
			}
			if(!noAutorizo){//SINO TIENE FALLA
				boolean actualizo = true;
				int idVen = venCons.getId();
				//CANCELACION DE MOVIMIENTO EN C.C.
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
//				int idCliente = venCons.getCliente().getId();
//				Cliente cli = clienteDAO.get(idCliente);
				ccCliente.setIdMovimiento(idVen);
				ccCliente.setNombreTabla("VentasCon");
				int deleteCC = cuenta.deleteCuentaCorriente(ccCliente);
				if(deleteCC == 0){
					actualizo = false;
				}
				//UPDATE DE STOCK DE PRODUCTOS Y MOVILES
				for (VentasConsDetalle ventasDetalle : listVentDet) {
					//UPDATE DE PRODUCTO
					int idProd = ventasDetalle.getProducto().getId();
					Producto prod = productoDAO.get(idProd);
					int enConsignacion = prod.getEnConsignacion();
					enConsignacion = enConsignacion + ventasDetalle.getCantidad();
					prod.setEnConsignacion(enConsignacion);
					int updateProd = productoDAO.update(prod);
					if(updateProd == 0){
						actualizo = false;
					}
					//UPDATE DE UNIDADES MOVILES EN CONSIGNACION
					List<VentasConsDetalleUnidad> listaUnidads = ventaConsignacionDetalleUnidadDAO.getLista(ventasDetalle);
					for(VentasConsDetalleUnidad ventasUnidad : listaUnidads){
						String nroImei = ventasUnidad.getNroImei();
						UnidadMovil unidad = unidadMovilDAO.get(nroImei);
						ConsignacionsDetalleUnidad unidadConsignacion = consignacionDetalleUnidadDAO.get(nroImei);
						unidadConsignacion.setVendido(false);
						unidadConsignacion.setTipoVenta(null);							
						unidadConsignacion.setFechaVenta(null);	
						unidad.setEnStock(true);
						unidad.setEnVenta(false);
						int updateUnid = unidadMovilDAO.update(unidad);
						int updateUniCons = consignacionDetalleUnidadDAO.update(unidadConsignacion);
						if(updateUnid == 0 || updateUniCons == 0){
							actualizo = false;
						}
					}
					//ELIMINACION LOGICA DEL DETALLE DE LA VENTA
					int deleteVentDet = ventaConsignacionDetalleDAO.delete(ventasDetalle);
					if(deleteVentDet == 0){
						actualizo = false;
					}			
				}
				//BAJA LOGICA DE VENTA
				venCons.setEstado(false);
				venCons.setFechaBaja(new Date());
				venCons.setUsuario2(usuario);
				int updateVent = ventaConsignacionDAO.update(venCons);
				if(updateVent != 0 && actualizo){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Venta!", null);
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un Error al dar de Baja la Venta, "
							+ "Inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Venta posee Móviles en Garantía, realice la baja correspondiente e "
						+ "inténtelo nuevamente!", null);
			}
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No pudo ser posible dar de baja la Venta, inténtelo nuevamente mas tarde!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardarVenta() {
		FacesMessage msg = null;
		String retorno = "";
		if(!listaVentasConsDetalles.isEmpty() && montoTotal != 0){
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
			ventasCon.setEstado(true);
			ventasCon.setFechaAlta(new Date());
			ventasCon.setMonto(montoTotal);
			ventasCon.setUsuario1(usuario);
			Date fechaVen = ventasCon.getFecha();
			Cliente client = ventasCon.getCliente();
			float saldoCli = client.getSaldo();
			// INSERCION EN CUENTA CORRIENTE DE VENTA			
			ventasCon.setSaldoCliente(saldoCli);
			int idVenta = ventaConsignacionDAO.insertar(ventasCon);
			
			ccCliente.setCliente(client);
			ccCliente.setDebe(montoTotal);
			ccCliente.setDetalle("Venta Consignación nro: " + idVenta);				
			ccCliente.setFecha(fechaVen);
			ccCliente.setIdMovimiento(idVenta);
			ccCliente.setMonto(montoTotal);
			ccCliente.setNombreTabla("VentasCon");
			ccCliente.setUsuario(usuario);
			cuenta.insertarCC(ccCliente);			
			
			if(idVenta != 0){
				boolean inserto = true;
				ventasCon.setId(idVenta);
				for (VentasConsDetalle ventaDetalle : listaVentasConsDetalles) {
					int idProd = ventaDetalle.getProducto().getId();
					Producto prod = productoDAO.get(idProd);
					int stockConsignacion = prod.getEnConsignacion();
					int cantidad = stockConsignacion - ventaDetalle.getCantidad();
					prod.setEnConsignacion(cantidad);
					productoDAO.update(prod);
					List<VentasConsDetalleUnidad> listAux = ventaDetalle.getVentasConsDetalleUnidads();
					ventaDetalle.setVentasConsDetalleUnidads(null);
					ventaDetalle.setVentasCon(ventasCon);					
					int idDetalle = ventaConsignacionDetalleDAO.insertar(ventaDetalle);
					if(idDetalle != 0){
						ventaDetalle.setId(idDetalle);						
						boolean insertoUnidad = true;
						for(VentasConsDetalleUnidad ventasUnidad : listAux){
							String imei = ventasUnidad.getNroImei();
							UnidadMovil unidad = unidadMovilDAO.get(imei);
							ConsignacionsDetalleUnidad unidadConsignacion = consignacionDetalleUnidadDAO.get(imei);
							unidadConsignacion.setVendido(true);
							unidadConsignacion.setTipoVenta("c.c.");							
							unidadConsignacion.setFechaVenta(fechaVen);							
							unidad.setEnStock(false);
							unidad.setEnVenta(true);
							ventasUnidad.setVentasConsDetalle(ventaDetalle);
							int idDetalleUnidad = ventaConsignacionDetalleUnidadDAO.insertar(ventasUnidad);
							int updateUnidad = unidadMovilDAO.update(unidad);
							int updateUniCons = consignacionDetalleUnidadDAO.update(unidadConsignacion);
							if(idDetalleUnidad == 0 || updateUnidad == 0 || updateUniCons == 0){
								insertoUnidad = false;
								break;
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
					retorno = "consignaciones";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Detalle de la Venta! "
							+ "Inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar la Venta! "
						+ "Inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Monto Total y el Detalle de la Venta no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;		
	}
	
	public String modificar(){
		try {
			FacesMessage msg = null;
			String retorno = "";
			if(ventasCon.getFecha() != null && !listaVentasConsDetalles.isEmpty() && montoTotal != 0){
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				//Elimino movimiento en cuenta corriente
				ccCliente.setIdMovimiento(ventasCon.getId());
				ccCliente.setNombreTabla("VentasCon");
				cuenta.deleteCuentaCorriente(ccCliente);
				
				ventasCon.setEstado(true);
				ventasCon.setFechaMod(new Date());
				ventasCon.setMonto(montoTotal);
				ventasCon.setTipo("c.c.");
				ventasCon.setUsuario3(usuario);
				int idVen = ventasCon.getId();
				Date fechaVen = ventasCon.getFecha();
				
				Cliente cli = clienteDAO.get(ventasCon.getCliente().getId());
				ventasCon.setCliente(cli);
				ventasCon.setSaldoCliente(cli.getSaldo());
				//Inserto movimiento en cuenta corriente
				ccCliente.setCliente(cli);
				ccCliente.setDebe(montoTotal);
				ccCliente.setDetalle("Venta Consignación nro: " + idVen);
				ccCliente.setFecha(fechaVen);
				ccCliente.setIdMovimiento(idVen);
				ccCliente.setMonto(montoTotal);
				ccCliente.setNombreTabla("VentasCon");
				ccCliente.setUsuario(usuario);
				cuenta.insertarCC(ccCliente);			
				
				int idVenta = ventaConsignacionDAO.update(ventasCon);
				if(idVenta != 0){
					ventasCon.setId(idVenta);
					//Baja detalle anterior
					boolean deleteDetalleUnidad = true;
					for (VentasConsDetalle ventaDetalle : listaDetalleBaja) {
						int idProd = ventaDetalle.getProducto().getId();
						Producto prod = productoDAO.get(idProd);
						int stockConsignacion = prod.getEnConsignacion();
						int cantidad = stockConsignacion + ventaDetalle.getCantidad();
						prod.setEnConsignacion(cantidad);
						productoDAO.update(prod);
						
						List<VentasConsDetalleUnidad> listAux = ventaDetalle.getVentasConsDetalleUnidads();
						ventaDetalle.setVentasConsDetalleUnidads(null);
						
						for(VentasConsDetalleUnidad ventaUnidad : listAux){						
							String imeiUndadMvile = ventaUnidad.getNroImei();
							UnidadMovil unidad = unidadMovilDAO.get(imeiUndadMvile);
							ConsignacionsDetalleUnidad unidadConsignacion = consignacionDetalleUnidadDAO.get(imeiUndadMvile);
							unidadConsignacion.setVendido(false);
							unidadConsignacion.setTipoVenta(null);							
							unidadConsignacion.setFechaVenta(null);	
							unidad.setEnStock(true);
							unidad.setEnVenta(false);
							int updaUnidadMov = unidadMovilDAO.update(unidad);
							int updateUniCons = consignacionDetalleUnidadDAO.update(unidadConsignacion);
							if(updaUnidadMov == 0 || updateUniCons == 0){
								deleteDetalleUnidad = false;
							}						
						}
						
						int deletePorDetalle = ventaConsignacionDetalleUnidadDAO.deletePorDetalle(ventaDetalle);
						if(deletePorDetalle == 0){
							deleteDetalleUnidad = false;
						}
						ventaConsignacionDetalleDAO.delete(ventaDetalle);
					}
					//Alta de nuevo detalle de venta
					if(deleteDetalleUnidad){
						boolean inserto = true;
						ventasCon.setId(idVenta);
						for (VentasConsDetalle ventaDetalle : listaVentasConsDetalles) {
							int idProd = ventaDetalle.getProducto().getId();
							Producto prod = productoDAO.get(idProd);
							int stockConsignacion = prod.getEnConsignacion();
							int cantidad = stockConsignacion - ventaDetalle.getCantidad();
							prod.setEnConsignacion(cantidad);
							productoDAO.update(prod);
							List<VentasConsDetalleUnidad> listAux = ventaDetalle.getVentasConsDetalleUnidads();
							ventaDetalle.setVentasConsDetalleUnidads(null);
							ventaDetalle.setVentasCon(ventasCon);					
							int idDetalle = ventaConsignacionDetalleDAO.insertar(ventaDetalle);
							if(idDetalle != 0){
								ventaDetalle.setId(idDetalle);						
								boolean insertoUnidad = true;
								for(VentasConsDetalleUnidad ventasUnidad : listAux){
									String imei = ventasUnidad.getNroImei();
									UnidadMovil unidad = unidadMovilDAO.get(imei);
									ConsignacionsDetalleUnidad unidadConsignacion = consignacionDetalleUnidadDAO.get(imei);
									unidadConsignacion.setVendido(true);
									unidadConsignacion.setTipoVenta("c.c.");							
									unidadConsignacion.setFechaVenta(fechaVen);							
									unidad.setEnStock(false);
									unidad.setEnVenta(true);
									ventasUnidad.setVentasConsDetalle(ventaDetalle);
									int idDetalleUnidad = ventaConsignacionDetalleUnidadDAO.insertar(ventasUnidad);
									int updateUnidad = unidadMovilDAO.update(unidad);
									int updateUniCons = consignacionDetalleUnidadDAO.update(unidadConsignacion);
									if(idDetalleUnidad == 0 || updateUnidad == 0 || updateUniCons == 0){
										insertoUnidad = false;
										break;
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
							retorno = "ventas_consignacion";
						}else{
							msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Detalle de la Venta! "
									+ "Inténtelo nuevamente!", null);
						}
					}else{
						msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al insertar la Unidad Móvil del Detalle de la Venta! "
								+ "Inténtelo nuevamente!", null);
					}
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar la Venta! "
							+ "Inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La Fecha, el Monto Total y el Detalle de la Venta no pueden estar vacíos!", null);
			}
			listaVentasCons = new ArrayList<VentasCon>();
			listaVentasCons = ventaConsignacionDAO.getLista(true, consignacion);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al guardar la venta! Error original: " + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public String cancelarVenta() {
		listaVentasConsDetalles = new ArrayList<VentasConsDetalle>();
		ventasCon = new VentasCon();
		montoTotal = 0;
		cantidadTotal = 0;
		return "ventaconsignacion";
	}
	
	public void generarReporteComprobante(VentasCon ventCons){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		for(VentasConsDetalle ventasConsDetalle : getDetalleDeVenta(ventCons)){
			DetalleComprobante detalle = new DetalleComprobante();
			detalle.setCantidad(ventasConsDetalle.getCantidad());
			detalle.setDetalle(ventasConsDetalle.getProducto().getNombre());
			detalle.setSubtotal(formatoMonto.format(ventasConsDetalle.getSubtotal()));
			List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
			for (VentasConsDetalleUnidad ventasConsDetalleUnidad : getDetalleUnidadVendidaConsignacion(ventasConsDetalle)) {
				DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
				detalleUnidad.setNroImei(ventasConsDetalleUnidad.getNroImei());
				detalleUnidad.setPrecioVenta(ventasConsDetalleUnidad.getPrecioVenta());
				listaUnidad.add(detalleUnidad);
				cant++;
			}
			detalle.setListaUnidads(listaUnidad);
			listaDetalle.add(detalle);			
		}
		parametros.put("numero", ventCons.getId());
		parametros.put("persona", ventCons.getCliente().getNombreNegocio());
		parametros.put("fecha", formatoFecha.format(ventCons.getFecha()));
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(ventCons.getMonto()));
		reporte.generar(parametros, listaDetalle, "ventaConsignacion", "attachment");
	}
	
	public void generarReporteLista(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Comprobante> listComprobante = new ArrayList<Comprobante>();
		int idCliente = 0;
		for (VentasCon ventasCon : listaVentasCons) {
			List<DetalleComprobante> listDetalle = new ArrayList<DetalleComprobante>();
			Comprobante comprobante = new Comprobante();
			for (VentasConsDetalle ventasConsDetalle : getDetalleDeVenta(ventasCon)) {
				DetalleComprobante detalle = new DetalleComprobante();
				detalle.setCantidad(ventasConsDetalle.getCantidad());
				detalle.setDetalle(ventasConsDetalle.getProducto().getNombre());
				detalle.setSubtotal(formatoMonto.format(ventasConsDetalle.getSubtotal()));
				listDetalle.add(detalle);
			}
			comprobante.setFecha(formatoFecha.format(ventasCon.getFecha()));
			comprobante.setListaDetalles(listDetalle);
			comprobante.setMonto(formatoMonto.format(ventasCon.getMonto()));
			comprobante.setNumero(ventasCon.getId());
			comprobante.setPersona(ventasCon.getUsuario1().getUsername());
			idCliente = ventasCon.getCliente().getId();
			listComprobante.add(comprobante);
		}
		Cliente cli = clienteDAO.get(idCliente);
		parametros.put("cliente", cli.getNombreNegocio());
		if(fechaInicio != null && fechaFin != null){
			parametros.put("desde", formatoFecha.format(fechaInicio));
			parametros.put("hasta", formatoFecha.format(fechaFin));			
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.generar(parametros, listComprobante, "ventasConsignacion", "inline");
	}
	
	public void generarExcelLista(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Comprobante> listComprobante = new ArrayList<Comprobante>();
		int idCliente = 0;
		for (VentasCon ventasCon : listaVentasCons) {
			List<DetalleComprobante> listDetalle = new ArrayList<DetalleComprobante>();
			Comprobante comprobante = new Comprobante();
			for (VentasConsDetalle ventasConsDetalle : getDetalleDeVenta(ventasCon)) {
				DetalleComprobante detalle = new DetalleComprobante();
				detalle.setCantidad(ventasConsDetalle.getCantidad());
				detalle.setDetalle(ventasConsDetalle.getProducto().getNombre());
				detalle.setSubtotal(formatoMonto.format(ventasConsDetalle.getSubtotal()));
				listDetalle.add(detalle);
			}
			comprobante.setFecha(formatoFecha.format(ventasCon.getFecha()));
			comprobante.setListaDetalles(listDetalle);
			comprobante.setMonto(formatoMonto.format(ventasCon.getMonto()));
			comprobante.setNumero(ventasCon.getId());
			comprobante.setPersona(ventasCon.getUsuario1().getUsername());
			idCliente = ventasCon.getCliente().getId();
			listComprobante.add(comprobante);
		}
		Cliente cli = clienteDAO.get(idCliente);
		parametros.put("cliente", cli.getNombreNegocio());
		if(fechaInicio != null && fechaFin != null){
			parametros.put("desde", formatoFecha.format(fechaInicio));
			parametros.put("hasta", formatoFecha.format(fechaFin));			
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		reporte.exportXls(parametros, listComprobante, "excelVentasConsignacion", "inline");
	}
	
	private VentasConsDetalle getDetalleDeConsignacion(Producto producto, List<ConsignacionsDetalleUnidad> listaUnidades) {
		List<VentasConsDetalleUnidad> listaUnidadsVenta = new ArrayList<VentasConsDetalleUnidad>();
		VentasConsDetalle ventaDetalle = new VentasConsDetalle();
		int cantidad = 0;
		float subtotal = 0;
		for (ConsignacionsDetalleUnidad consigUnidad : listaUnidades) {
			VentasConsDetalleUnidad unidadDetalle = new VentasConsDetalleUnidad();
			String imei = consigUnidad.getNroImei();
			UnidadMovil unidad = unidadMovilDAO.get(imei);
			int idProducto = unidad.getProducto().getId();
			Producto prod = productoDAO.get(idProducto);
			float precioCompra = consigUnidad.getPrecioCompra();
			float precioVenta = consigUnidad.getPrecioLista();
			if (prod.getId() == producto.getId()) {
				cantidad = cantidad + 1;
				subtotal = subtotal + precioVenta;
				unidadDetalle.setConsignacionsDetalleUnidad(consigUnidad);
				unidadDetalle.setEliminado(false);
				unidadDetalle.setNroImei(imei);
				unidadDetalle.setPrecioCompra(precioCompra);
				unidadDetalle.setPrecioVenta(precioVenta);				
				listaUnidadsVenta.add(unidadDetalle);
			}
		}
		montoTotal = montoTotal + subtotal;
		cantidadTotal = cantidadTotal + cantidad;
		ventaDetalle.setCantidad(cantidad);
		ventaDetalle.setEliminado(false);
		ventaDetalle.setProducto(producto);
		ventaDetalle.setSubtotal(subtotal);
		ventaDetalle.setVentasConsDetalleUnidads(listaUnidadsVenta);
		return ventaDetalle;
	}
	
	private boolean conFalla(VentasConsDetalle ventaDetalle){
		boolean falla = false;
		List<VentasConsDetalleUnidad> listAux = ventaConsignacionDetalleUnidadDAO.getLista(ventaDetalle);
		for (VentasConsDetalleUnidad ventasDetalleUnidad : listAux) {
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
	
	public boolean conFallaUnidad(VentasConsDetalleUnidad ventaUnidad){
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

}
