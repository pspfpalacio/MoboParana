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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ar.com.clases.CuentaCorriente;
import ar.com.clases.Mail;
import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Pagos;
import ar.com.managed.beans.cliente.BeanVentaCliente;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.EquipoPendientePago;
import model.entity.PagosCliente;
import model.entity.PagosProveedore;
import model.entity.Proveedore;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOEquipoPendientePago;
import dao.interfaces.DAOPago;
import dao.interfaces.DAOProveedor;
import dao.interfaces.DAOUnidadMovil;

@ManagedBean
@SessionScoped
public class BeanPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanVentaCliente.class);
	
	@ManagedProperty(value = "#{BeanEquipoPendientePagoDAO}")
	private DAOEquipoPendientePago equipoPendientePagoDAO;
	
	@ManagedProperty(value = "#{BeanPagoDAO}")
	private DAOPago pagoDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	private List<Cliente> listaClientes;
	private List<Proveedore> listaProveedores;
	private List<EquipoPendientePago> listaEpp;
	private PagosCliente pagoCliente;
	private PagosProveedore pagoProveedore;
	private List<EquipoPendientePago> equiposSelectos;
	private List<EquipoPendientePago> equiposParaPagar;
	private Cliente cliente;
	private Usuario usuario;
	private int idCliente;
	private int idProveedor;
	private String destinatarios;
	private boolean envioEmail;
	

	public DAOEquipoPendientePago getEquipoPendientePagoDAO() {
		return equipoPendientePagoDAO;
	}

	public void setEquipoPendientePagoDAO(DAOEquipoPendientePago equipoPendientePagoDAO) {
		this.equipoPendientePagoDAO = equipoPendientePagoDAO;
	}

	public DAOPago getPagoDAO() {
		return pagoDAO;
	}

	public void setPagoDAO(DAOPago pagoDAO) {
		this.pagoDAO = pagoDAO;
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

	public List<Cliente> getListaClientes() {
		listaClientes = clienteDAO.getLista(true);
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Proveedore> getListaProveedores() {
		listaProveedores = proveedorDAO.getLista(true);
		return listaProveedores;
	}

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public void setListaProveedores(List<Proveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	
	public List<EquipoPendientePago> getListaEpp() {
		return listaEpp;
	}

	public void setListaEpp(List<EquipoPendientePago> listaEpp) {
		this.listaEpp = listaEpp;
	}

	public PagosCliente getPagoCliente() {
		return pagoCliente;
	}

	public void setPagoCliente(PagosCliente pagoCliente) {
		this.pagoCliente = pagoCliente;
	}

	public PagosProveedore getPagoProveedore() {
		return pagoProveedore;
	}

	public void setPagoProveedore(PagosProveedore pagoProveedore) {
		this.pagoProveedore = pagoProveedore;
	}

	public List<EquipoPendientePago> getEquiposSelectos() {
		return equiposSelectos;
	}

	public void setEquiposSelectos(List<EquipoPendientePago> equiposSelectos) {
		this.equiposSelectos = equiposSelectos;
	}

	public List<EquipoPendientePago> getEquiposParaPagar() {
		return equiposParaPagar;
	}

	public void setEquiposParaPagar(List<EquipoPendientePago> equiposParaPagar) {
		this.equiposParaPagar = equiposParaPagar;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}

	public boolean isEnvioEmail() {
		return envioEmail;
	}

	public void setEnvioEmail(boolean envioEmail) {
		this.envioEmail = envioEmail;
	}

	public String goPagoCliente(Usuario user){
		log.info("pagocliente.xhtml");
		cliente = new Cliente();
		listaEpp = new ArrayList<EquipoPendientePago>();
		equiposSelectos = new ArrayList<EquipoPendientePago>();
		equiposParaPagar = new ArrayList<EquipoPendientePago>();
		idCliente = 0;
		pagoCliente = new PagosCliente();
		pagoCliente.setFecha(new Date());
		pagoCliente.setConcepto("");
		usuario = new Usuario();
		usuario = user;
		destinatarios = "";
		envioEmail = false;
		return "pagocliente";
	}
	
	public String goMarcarPagados(Usuario user){
		log.info("marcarpagados.xhtml");
		cliente = new Cliente();
		listaEpp = new ArrayList<EquipoPendientePago>();
		equiposSelectos = new ArrayList<EquipoPendientePago>();
		equiposParaPagar = new ArrayList<EquipoPendientePago>();
		idCliente = 0;
		pagoCliente = new PagosCliente();
		pagoCliente.setFecha(new Date());
		usuario = new Usuario();
		usuario = user;
		return "marcarpagados";
	}
	
	public String goPagoProveedor(Usuario user){
		log.info("pagoproveedor.xhtml");
		idProveedor = 0;
		pagoProveedore = new PagosProveedore();
		pagoProveedore.setFecha(new Date());
		usuario = new Usuario();
		usuario = user;
		return "pagoproveedor";
	}
	
	public String guardarPagoCliente(){
		FacesMessage msg = null;
		String retorno = "";
		if(pagoCliente.getFecha() != null && idCliente != 0 && pagoCliente.getMonto() != 0){
			Cliente cliente = clienteDAO.get(idCliente);
			log.info("Cliente: " + cliente.getApellidoNombre());
			pagoCliente.setCliente(cliente);
			pagoCliente.setFechaAlta(new Date());
			pagoCliente.setUsuario(usuario);
			int idPago = pagoDAO.insertar(pagoCliente);
			if(idPago != 0){
				log.info("idPago: " + idPago);
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				String conceptoP = pagoCliente.getConcepto();
				Date fechaP = pagoCliente.getFecha();
				float montoP = pagoCliente.getMonto();
				String nombreNegocio = cliente.getNombreNegocio();
				ccCliente.setCliente(cliente);
				ccCliente.setDetalle("Pago recibido - Concepto: " + conceptoP);
				ccCliente.setFecha(fechaP);
				ccCliente.setHaber(montoP);
				ccCliente.setIdMovimiento(idPago);
				ccCliente.setNombreTabla("PagosCliente");
				ccCliente.setMonto(montoP);
				ccCliente.setUsuario(usuario);
				int idCuentaCorriente = cuenta.insertarCC(ccCliente);
				Caja caja = new Caja();
				caja.setConcepto("Pago recibido de: " + nombreNegocio + " - Concepto : " + conceptoP);
				caja.setFecha(fechaP);
				caja.setIdMovimiento(idPago);
				caja.setMonto(montoP);
				caja.setNombreTabla("PagosCliente");
				caja.setUsuario(usuario);
				int idCaja = movimientoCaja.insertarCaja(caja);
				int eppPagado = 0;
				int tipoComprobante = 1;
				if(equiposParaPagar.size() == 0) {
					eppPagado = 1;
					tipoComprobante = 0;
				}
				log.info("Equipos Por Pagar: " + equiposParaPagar.size());
				for(EquipoPendientePago epp : equiposParaPagar) {
					epp.setFechaMod(new Date());
					epp.setUsuario2(usuario);
					eppPagado = equipoPendientePagoDAO.pagar(epp);
				}
					
				if(idCuentaCorriente != 0 && idCaja != 0 && eppPagado != 0){
					int send = 1;
					String sendMje = "";
				    	if(envioEmail) {
				    		String cuerpoMail = generarComprobante(tipoComprobante);
					    	log.info("Cuerpo de email: " + cuerpoMail);
				    		Mail mail = new Mail();
					    	mail.setAsunto("CB Telefonía - Comprobante de pago");
					    	mail.setCuerpo(cuerpoMail);
					    	mail.setDestinatarios(destinatarios);
					    	send = mail.send();
					    	log.info("SEND " + send);
					    	if(send == 1) {
					    		sendMje = ", Comprobante enviado";
					    	}else {
					    		sendMje = ", Comprobante NO enviado";
					    	}
				    	}
					
				    	String mensaje = "Pago registrado" + sendMje;
					log.info(mensaje);
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
					idCliente = 0;
					pagoCliente = new PagosCliente();
					pagoCliente.setFecha(new Date());
					listaEpp = new ArrayList<EquipoPendientePago>();
					equiposSelectos = new ArrayList<EquipoPendientePago>();
					equiposParaPagar = new ArrayList<EquipoPendientePago>();
					pagoCliente.setConcepto("");
					destinatarios = "";
					envioEmail = false;
					retorno = "pagocliente";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
							+ "inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
						+ "inténtelo nuevamente!", null);
			}			
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Cliente y Monto no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String guardarPagoProveedor(){
		FacesMessage msg = null;
		String retorno = "";
		if(pagoProveedore.getFecha() != null && idProveedor != 0 && pagoProveedore.getMonto() != 0){
			Proveedore proveedore = proveedorDAO.get(idProveedor);
			pagoProveedore.setProveedore(proveedore);
			pagoProveedore.setFechaAlta(new Date());
			pagoProveedore.setUsuario(usuario);
			int idPago = pagoDAO.insertar(pagoProveedore);
			if(idPago != 0){
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
				String conceptoP = pagoProveedore.getConcepto();
				Date fechaP = pagoProveedore.getFecha();
				float montoP = pagoProveedore.getMonto();
				String nombreNegocio = proveedore.getNombreNegocio();
				ccProveedor.setProveedore(proveedore);
				ccProveedor.setDetalle("Pago recibido - Concepto: " + conceptoP);
				ccProveedor.setFecha(fechaP);
				ccProveedor.setHaber(montoP);
				ccProveedor.setIdMovimiento(idPago);
				ccProveedor.setNombreTabla("PagosProveedore");
				ccProveedor.setMonto(montoP);
				ccProveedor.setUsuario(usuario);
				int idCuentaCorriente = cuenta.insertarCC(ccProveedor);
				Caja caja = new Caja();
				caja.setConcepto("Pago realizado a: " + nombreNegocio + " - Concepto : " + conceptoP);
				caja.setFecha(fechaP);
				caja.setIdMovimiento(idPago);
				float montoCaja = (-1) * montoP;
				caja.setMonto(montoCaja);
				caja.setNombreTabla("PagosProveedore");
				caja.setUsuario(usuario);
				int idCaja = movimientoCaja.insertarCaja(caja);
				if(idCuentaCorriente != 0 && idCaja != 0){
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago registrado!", null);
					idProveedor = 0;
					pagoProveedore = new PagosProveedore();
					pagoProveedore.setFecha(new Date());
					retorno = "pagoproveedor";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
							+ "inténtelo nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Pago, "
						+ "intentelo nuevamente!", null);
			}			
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Proveedor y Monto no pueden estar vacíos!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String marcarPagado() {
		FacesMessage msg = null;
		String retorno = "";
		if(equiposParaPagar.size() != 0) {
			boolean eppPagado = true;
			log.info("Equipos Por Pagar: " + equiposParaPagar.size());
			for(EquipoPendientePago epp : equiposParaPagar) {
				epp.setFechaMod(new Date());
				epp.setUsuario2(usuario);
				if(equipoPendientePagoDAO.pagar(epp) == 0) {
					eppPagado = false;
				}
			}
			if(eppPagado){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipos marcados correctamente!", null);
				idCliente = 0;
				pagoCliente = new PagosCliente();
				listaEpp = new ArrayList<EquipoPendientePago>();
				equiposSelectos = new ArrayList<EquipoPendientePago>();
				equiposParaPagar = new ArrayList<EquipoPendientePago>();
				retorno = "marcarpagados";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al marcar equipos como pagados, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se han seleccionado equipos", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarReporte(int idPagoC, int idPagoP){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Pagos> listPago = new ArrayList<Pagos>();
		if(idPagoC != 0){
			PagosCliente pagoC = pagoDAO.getPagoCliente(idPagoC);
			Pagos pagos = new Pagos();
			pagos.setConcepto(pagoC.getConcepto());
			pagos.setFecha(formatoFecha.format(pagoC.getFecha()));
			pagos.setMonto(formatoMonto.format(pagoC.getMonto()));
			pagos.setPersona("Recibi de: " + pagoC.getCliente().getNombreNegocio());
			listPago.add(pagos);
		}
		if(idPagoP != 0){
			PagosProveedore pagoP = pagoDAO.getPagoProveedore(idPagoP);
			Pagos pagos = new Pagos();
			pagos.setConcepto(pagoP.getConcepto());
			pagos.setFecha(formatoFecha.format(pagoP.getFecha()));
			pagos.setMonto(formatoMonto.format(pagoP.getMonto()));
			pagos.setPersona("Entregue a: " + pagoP.getProveedore().getNombreNegocio());
			listPago.add(pagos);
		}
		reporte.generar(parametros, listPago, "pago", "attachment");
	}
	
	public void updatePendientePagoList() {
		destinatarios = "";
		cliente = new Cliente();
		listaEpp = new ArrayList<EquipoPendientePago>();
		equiposSelectos = new ArrayList<EquipoPendientePago>();
		equiposParaPagar = new ArrayList<EquipoPendientePago>();
		pagoCliente.setMonto(0);
		cliente = clienteDAO.get(idCliente);
		destinatarios = cliente.getEmail();
		log.info("Cliente: " + cliente.getApellidoNombre());
		log.info("Mail cliente: " + destinatarios);
		listaEpp = equipoPendientePagoDAO.getListaNoPagosPorCliente(cliente);
	}
	
	public String getNombrePorImei(String imei) {
		UnidadMovil unidadMovil = unidadMovilDAO.get(imei);
		return unidadMovil.getProducto().getNombre();
	}
	
	public String getStringPagado(Boolean pagado) {
		if(pagado) {
			return "Si";
		} else {
			return "No";
		}
	}
	
	public void agregarEquiposParaPagar() {
		log.info("cantidad seleccionados: " + equiposSelectos.size());
		for(EquipoPendientePago epp : equiposSelectos) {
			equiposParaPagar.add(epp);
		}
		
		List<EquipoPendientePago> auxiliar  = new ArrayList<EquipoPendientePago>();
		for(EquipoPendientePago epp : listaEpp) {
			boolean flag = true;
			for(EquipoPendientePago eppSel : equiposSelectos) {
				if(epp.equals(eppSel)) {
					flag = false;
				}
			}
			if(flag) {
				auxiliar.add(epp);
			} else {
				String concepto = pagoCliente.getConcepto() + " " + unidadMovilDAO.get(epp.getImei()).getProducto().getNombre() + " (" + epp.getImei() + ") - $" + Float.toString(epp.getMonto()) + " |";  
				pagoCliente.setMonto(pagoCliente.getMonto() + epp.getMonto());
				pagoCliente.setConcepto(concepto);
			}
		}
		log.info(pagoCliente.getMonto());
		listaEpp = new ArrayList<EquipoPendientePago>();
		listaEpp = auxiliar;
	}
	
	public void limpiarEquiposParaPagar() {
		listaEpp = new ArrayList<EquipoPendientePago>();
		equiposSelectos = new ArrayList<EquipoPendientePago>();
		equiposParaPagar = new ArrayList<EquipoPendientePago>();
		cliente = clienteDAO.get(idCliente);
		listaEpp = equipoPendientePagoDAO.getListaNoPagosPorCliente(cliente);
		pagoCliente.setMonto(0);
		pagoCliente.setConcepto("");
	}
	
	public void onRowSelect(SelectEvent event) {
		log.info("SELECT:" + event.getObject());
    }
 
    public void onRowUnselect(UnselectEvent event) {
    		log.info("UNSELECT:" + event.getObject());
    }
    
    public String generarComprobante(int tipo) {
    	SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String html = "";
    	html += "<html>" + 
    			"<head>" + 
    			"<style>" + 
    			"table {" + 
    			"    font-family: arial, sans-serif;" + 
    			"    border-collapse: collapse;" + 
    			"    width: 100%;" + 
    			"}" +  
    			"td, th {" + 
    			"    border: 1px solid #dddddd;" + 
    			"    text-align: left;" + 
    			"    padding: 8px;" + 
    			"}" +  
    			"tr:nth-child(even) {" + 
    			"    background-color: #dddddd;" + 
    			"}" + 
    			".cabecera {" + 
    			"  font-family: arial, sans-serif;" + 
    			"    width: 100%;" + 
    			"}"+ 
    			"</style>" + 
    			"</head>" + 
    			"<body>" +  
    			"<div class='cabecera'>" +
    			"<h2>Comprobante de pago</h2>" +
    			"<p>Fecha: "+ dateformat.format(pagoCliente.getFecha()) +" </p>" +
    			"<p>Cliente: "+ cliente.getApellidoNombre() +" </p>" +
    			"<p>Monto total: $"+ Float.toString(pagoCliente.getMonto()) +"</p>" +
    			"<p>Concepto: "+ pagoCliente.getConcepto() +"</p>" +
    			"</div>" +
    			"<table>" + 
    			"  <tr>" + 
    			"    <th>DESCRIPCION</th>" + 
    			"    <th>MONTO</th>" + 
    			"  </tr>";
    	
    	
    	if(tipo == 0) {
    		
    		html += "<tr>"
    				+ "<td> "
    				+ pagoCliente.getConcepto()
    				+ "</td> "
    				+ "$" + Float.toString(pagoCliente.getMonto())
    				+ "<td>"
    				+ "</td>"
    				+ "</tr>";
    	}else {
    		
			for(EquipoPendientePago epp : equiposParaPagar) {
				String nombreEquipo = unidadMovilDAO.get(epp.getImei()).getProducto().getNombre();
	    		html += "<tr>"
	    				+ "<td> "
	    				+ nombreEquipo + " - " + epp.getImei()
	    				+ "</td> "
	    				+ "$" + epp.getMonto()
	    				+ "<td>"
	    				+ "</td>"
	    				+ "</tr>";
			}
    	}
    	
    	html += "</table>" + 
    			"" + 
    			"</body>" + 
    			"</html>";
    	return html;
    }
    
}
