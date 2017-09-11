package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import ar.com.clases.auxiliares.DetalleComprobante;
import ar.com.clases.auxiliares.DetalleComprobanteUnidad;
import model.entity.Mensaje;
import model.entity.Usuario;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import dao.interfaces.DAOMensaje;
import dao.interfaces.DAOUsuario;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanMensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanMensaje.class);
	
	@ManagedProperty(value = "#{BeanMensajeDAO}")
	private DAOMensaje mensajeDAO;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	private List<Mensaje> listaMensajes;
	private Mensaje mensaje;
	private Usuario usuario;
	private String mensajeNuevo;
	private boolean verPanel;

	public DAOMensaje getMensajeDAO() {
		return mensajeDAO;
	}

	public void setMensajeDAO(DAOMensaje mensajeDAO) {
		this.mensajeDAO = mensajeDAO;
	}

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
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

	public void setVentaConsignacionDetalleDAO(DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO) {
		this.ventaConsignacionDetalleDAO = ventaConsignacionDetalleDAO;
	}

	public DAOVentaConsignacionDetalleUnidad getVentaConsignacionDetalleUnidadDAO() {
		return ventaConsignacionDetalleUnidadDAO;
	}

	public void setVentaConsignacionDetalleUnidadDAO(DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO) {
		this.ventaConsignacionDetalleUnidadDAO = ventaConsignacionDetalleUnidadDAO;
	}

	public List<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaMensajes(List<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensajeNuevo() {
		return mensajeNuevo;
	}

	public void setMensajeNuevo(String mensajeNuevo) {
		this.mensajeNuevo = mensajeNuevo;
	}

	public boolean isVerPanel() {
		return verPanel;
	}

	public void setVerPanel(boolean verPanel) {
		this.verPanel = verPanel;
	}

	public String goMensajes(Usuario user) {
		log.info("Intento redireccionar a vista de mensajes con usuario id: " + user.getId());
		listaMensajes = new ArrayList<Mensaje>();
		usuario = new Usuario();
		mensaje = new Mensaje();
		verPanel = false;
		usuario = user;
		listaMensajes = mensajeDAO.getLista();
		log.info("Lista mensajes size() " + listaMensajes.size());
		return "mensajes";
	}
	
	public boolean onIdle() {		
		List<Mensaje> listMensaje = new ArrayList<Mensaje>(); 
		listMensaje = mensajeDAO.getLista(false);
		if (listMensaje.size() > 0) {
			mensajeNuevo = "(" + Integer.toString(listMensaje.size()) + ")";
//	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//	                                        "Mensaje Nuevo.", "Tiene mensajes nuevos!"));
		} else {
			mensajeNuevo = "";
		}
		return false;
    }
	
	public void verMensaje(Mensaje msj) {
		log.info("Intento ver y actualizar como visto, mensaje con id: " + msj.getId());
		try {
			mensaje = new Mensaje();
			mensaje = msj;
			verPanel = true;
			msj.setVisto(true);
			mensajeDAO.update(msj);
		} catch(Exception e) {
			e.printStackTrace();
			log.error("Error al ver el mensaje. Error: " + e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Ocurri� un error grave al abrir el mensaje.", "Cont�ctese con el administrador!"));
		}		
	}
	
	public void descargarComprobante() {
		log.info("Intento descargar comprobante de venta de consignacion. Mensaje id: " + mensaje.getId() 
		+ ", Venta consignacion id: " + mensaje.getVentasCon().getId());
		try {
			if (mensaje.getId() != 0) {
				Reporte reporte = new Reporte();
				DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Map<String, Object> parametros = new HashMap<String, Object>();
				List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
				int cant = 0;
				List<VentasConsDetalle> listAux = new ArrayList<VentasConsDetalle>();
				VentasCon ventaConsignacion = mensaje.getVentasCon();
				listAux = ventaConsignacionDetalleDAO.getLista(ventaConsignacion);
				for(VentasConsDetalle ventasConsDetalle : listAux){
					DetalleComprobante detalle = new DetalleComprobante();
					detalle.setCantidad(ventasConsDetalle.getCantidad());
					detalle.setDetalle(ventasConsDetalle.getProducto().getNombre());
					detalle.setSubtotal(formatoMonto.format(ventasConsDetalle.getSubtotal()));
					List<DetalleComprobanteUnidad> listaUnidad = new ArrayList<DetalleComprobanteUnidad>();
					List<VentasConsDetalleUnidad> listAuxUnidades = ventaConsignacionDetalleUnidadDAO.getLista(ventasConsDetalle);
					for (VentasConsDetalleUnidad ventasConsDetalleUnidad : listAuxUnidades) {
						DetalleComprobanteUnidad detalleUnidad = new DetalleComprobanteUnidad();
						detalleUnidad.setNroImei(ventasConsDetalleUnidad.getNroImei());
						detalleUnidad.setPrecioVenta(ventasConsDetalleUnidad.getPrecioVenta());
						listaUnidad.add(detalleUnidad);
						cant++;
					}
					detalle.setListaUnidads(listaUnidad);
					listaDetalle.add(detalle);			
				}
				parametros.put("numero", ventaConsignacion.getId());
				parametros.put("persona", ventaConsignacion.getCliente().getNombreNegocio());
				parametros.put("fecha", formatoFecha.format(ventaConsignacion.getFecha()));
				parametros.put("cantidadTotal", cant);
				parametros.put("montoTotal", formatoMonto.format(ventaConsignacion.getMonto()));
				reporte.generar(parametros, listaDetalle, "ventaConsignacion", "attachment");
			}		
		} catch(Exception e) {
			e.printStackTrace();
			log.error("Ocurrio un error al descargar el comprobante. Error: " + e);
		}		
	}

}
