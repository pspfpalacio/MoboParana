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

import ar.com.clases.CostoPromedio;
import ar.com.clases.CuentaCorriente;
import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Comprobante;
import ar.com.clases.auxiliares.DetalleComprobante;
import model.entity.Caja;
import model.entity.Compra;
import model.entity.ComprasDetalle;
import model.entity.ComprasDetalleUnidad;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Producto;
import model.entity.Proveedore;
import model.entity.Rubro;
import model.entity.Stock;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import dao.interfaces.DAOCompra;
import dao.interfaces.DAOCompraDetalle;
import dao.interfaces.DAOCompraDetalleUnidad;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOProveedor;
import dao.interfaces.DAOStock;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanCompra.class);
	
	@ManagedProperty(value = "#{BeanCompraDAO}")
	private DAOCompra compraDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleDAO}")
	private DAOCompraDetalle compraDetalleDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleUnidadDAO}")
	private DAOCompraDetalleUnidad compraDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanProveedorDAO}")
	private DAOProveedor proveedorDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanStockDAO}")
	private DAOStock stockDAO;
	
	private List<Compra> listaCompras;
	private List<Compra> filteredCompras;
	private List<Proveedore> listaProveedores;
	private List<Producto> listaProductos;
	private List<ComprasDetalle> listaComprasDetalles;
	private List<ComprasDetalle> listaComprasDetalleAdd;
	private Compra compra;
	private Usuario usuario;
	private Date fechaInicio;
	private Date fechaFin;
	private String nroImei;
	private String tipo;
	private int nroCompra;
	private int estado;
	private int idTipo;
	private int idProveedor;
	private int idProducto;
	private int cantidad;
	private int cantidadTotal;
	private float precioCompra;
	private float montoTotal;
	private boolean panelMovil;
	private boolean panelAccesorio;

	public DAOCompra getCompraDAO() {
		return compraDAO;
	}

	public void setCompraDAO(DAOCompra compraDAO) {
		this.compraDAO = compraDAO;
	}

	public DAOCompraDetalle getCompraDetalleDAO() {
		return compraDetalleDAO;
	}

	public void setCompraDetalleDAO(DAOCompraDetalle compraDetalleDAO) {
		this.compraDetalleDAO = compraDetalleDAO;
	}

	public DAOCompraDetalleUnidad getCompraDetalleUnidadDAO() {
		return compraDetalleUnidadDAO;
	}

	public void setCompraDetalleUnidadDAO(
			DAOCompraDetalleUnidad compraDetalleUnidadDAO) {
		this.compraDetalleUnidadDAO = compraDetalleUnidadDAO;
	}

	public DAOProveedor getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(DAOProveedor proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
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

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
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

	public DAOStock getStockDAO() {
		return stockDAO;
	}

	public void setStockDAO(DAOStock stockDAO) {
		this.stockDAO = stockDAO;
	}

	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}

	public List<Compra> getFilteredCompras() {
		return filteredCompras;
	}

	public void setFilteredCompras(List<Compra> filteredCompras) {
		this.filteredCompras = filteredCompras;
	}

	public List<Proveedore> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<ComprasDetalle> getListaComprasDetalles() {
		return listaComprasDetalles;
	}

	public void setListaComprasDetalles(List<ComprasDetalle> listaComprasDetalles) {
		this.listaComprasDetalles = listaComprasDetalles;
	}
	
	public List<ComprasDetalle> getListaComprasDetalleAdd() {
		return listaComprasDetalleAdd;
	}

	public void setListaComprasDetalleAdd(List<ComprasDetalle> listaComprasDetalleAdd) {
		this.listaComprasDetalleAdd = listaComprasDetalleAdd;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
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

	public int getNroCompra() {
		return nroCompra;
	}

	public void setNroCompra(int nroCompra) {
		this.nroCompra = nroCompra;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
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

	public String goCompras(Usuario user){
		log.info("goCOmpras, Usuario: " + user.getId() );
		listaCompras = new ArrayList<Compra>();
		filteredCompras = new ArrayList<Compra>();
		listaProveedores = new ArrayList<Proveedore>();
		listaProveedores = proveedorDAO.getLista(true);
		listaCompras = compraDAO.getLista(true);
		filteredCompras = listaCompras;
		usuario = new Usuario();
		usuario = user;
		fechaInicio = null;
		fechaFin = null;
		estado = 0;
		idProveedor = 0;
		return "compras";
	}
	
	public String goCompraNueva(){
		log.info("goCompraNueva, Usuario: " + usuario.getId() );
		compra = new Compra();
		compra.setFecha(new Date());
		listaProveedores = new ArrayList<Proveedore>();
		listaProveedores = proveedorDAO.getLista(true);
		listaProductos = new ArrayList<Producto>();
		listaProductos = productoDAO.getLista(true);
		listaComprasDetalles = new ArrayList<ComprasDetalle>();
		listaComprasDetalleAdd  = new ArrayList<ComprasDetalle>();
		idProveedor = 0;
		idProducto = 0;
		idTipo = 0;
		precioCompra = 0;
		montoTotal = 0;	
		cantidad = 0;
		cantidadTotal = 0;
		panelMovil = false;
		panelAccesorio = false;
		nroImei = "";
		tipo = "c.c.";
		List<Compra> listAux = compraDAO.getListaSinOrden();
		nroCompra = 1;
		for (Compra compra : listAux) {
			nroCompra = compra.getId() + 1;
		}
		return "compra";
	}
	
	/**
	 * @param comp
	 * @return
	 */
	public String goCompraEditar(Compra comp){
		log.info("goCompraEditar, Usuario: " + usuario.getId() + " Compra: " + comp.getId());
		compra = new Compra();		
		listaProveedores = new ArrayList<Proveedore>();
		listaProductos = new ArrayList<Producto>();
		listaProveedores = proveedorDAO.getLista();
		listaComprasDetalles = new ArrayList<ComprasDetalle>();
		listaComprasDetalleAdd  = new ArrayList<ComprasDetalle>();
		idProveedor = 0;
		idProducto = 0;
		idTipo = 0;
		precioCompra = 0;	
		montoTotal = 0;	
		cantidad = 0;
		cantidadTotal = 0;
		panelMovil = false;
		panelAccesorio = false;
		nroImei = "";
		compra = comp;
		montoTotal = comp.getMonto();
		nroCompra = comp.getId();
		idProveedor = comp.getProveedore().getId();
		listaComprasDetalles = getDetalleDeCompra(comp);
		tipo = comp.getTipo();
		return "editarcompra";
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
			rubro.setId(1);
			listaProductos = new ArrayList<Producto>();
			listaProductos = productoDAO.getLista(true, rubro);
		}
		if(idTipo == 2){
			panelMovil = false;
			panelAccesorio = true;
			rubro.setId(2);
			listaProductos = new ArrayList<Producto>();
			listaProductos = productoDAO.getLista(true, rubro);
		}
	}
	
	public void filtro(){
		log.info("filtro");
		listaCompras = new ArrayList<Compra>();
		filteredCompras = new ArrayList<Compra>();
		if(fechaInicio == null && fechaFin == null && idProveedor == 0){
			listaCompras = compraDAO.getLista(true);
		}
		if(fechaInicio != null && fechaFin != null && idProveedor == 0){
			listaCompras = compraDAO.getLista(true, fechaInicio, fechaFin);
		}
		if(fechaInicio == null && fechaFin == null && idProveedor != 0){
			Proveedore prov = new Proveedore();
			prov.setId(idProveedor);
			listaCompras = compraDAO.getLista(true, prov);
		}
		if(fechaInicio != null && fechaFin != null && idProveedor != 0){
			Proveedore prov = new Proveedore();
			prov.setId(idProveedor);
			listaCompras = compraDAO.getLista(true, prov, fechaInicio, fechaFin);
		}
		filteredCompras = listaCompras;
	}
	
	public List<ComprasDetalle> getDetalleDeCompra(Compra comp){
		log.info("getDetalleDeCompra, Compra: " + comp.getId());
		List<ComprasDetalle> listAux = new ArrayList<ComprasDetalle>();
		listAux = compraDetalleDAO.getLista(comp);
		return listAux;
	}
	
	public int getCantidadDetalleCompra(Compra comp, Producto prod){
		log.info("getCantidadDetalleCompra, Compra: " + comp.getId() + " Producto: " + prod.getNombre());
		List<ComprasDetalle> listAux = getDetalleDeCompra(comp);
		int cantidad = 0;
		for(ComprasDetalle cd : listAux){
			if(cd.getProducto().getId() == prod.getId()) {
				cantidad += cd.getCantidad();
			}
		}
		return cantidad;
	}
	
	public int getCantidadDetalleCompra(Producto prod){
		int cantidad = 0;
		for(ComprasDetalle cd : listaComprasDetalles){
			if(cd.getProducto().getId() == prod.getId()) {
				cantidad += cd.getCantidad();
			}
		}
		return cantidad;
	}
	
	public float getSubtotalDetalleCompra(Compra comp, Producto prod){
		log.info("getSubtotalDetalleCompra, Compra: " + comp.getId() + " Producto: " + prod.getNombre());
		List<ComprasDetalle> listAux = getDetalleDeCompra(comp);
		float subtotal = 0;
		for(ComprasDetalle cd : listAux){
			if(cd.getProducto().getId() == prod.getId()) {
				if(cd.getAccesorio()) {
					subtotal += cd.getSubtotal();
				} else {
					subtotal += cd.getPrecioCompra();
				}
			}	
		}
		return subtotal;
	}
	
	public float getSubtotalDetalleCompra(Producto prod){
		float subtotal = 0;
		for(ComprasDetalle cd : listaComprasDetalles){
			if(cd.getProducto().getId() == prod.getId()) {
				if(cd.getAccesorio()) {
					subtotal += cd.getSubtotal();
				} else {
					subtotal += cd.getPrecioCompra();
				}
			}	
		}
		return subtotal;
	}
	
	/*
	public List<ComprasDetalleUnidad> getUnidadDetalle(ComprasDetalle compDetalle){
		List<ComprasDetalleUnidad> listAux = new ArrayList<ComprasDetalleUnidad>();
		listAux = compraDetalleUnidadDAO.getLista(compDetalle);
		return listAux;
	}
	*/
	public void agregarUnidad(){
		log.info("agregarUnidad");
		if(!nroImei.isEmpty()){
			log.info("Imei: " + nroImei);
			UnidadMovil unidadMovil = new UnidadMovil();
			unidadMovil = unidadMovilDAO.get(nroImei);
			if(unidadMovil.getId() == 0){
				boolean noExiste = true;
				for(ComprasDetalle compraDetalle : listaComprasDetalleAdd){
					if(nroImei.equals(compraDetalle.getImei())){
						noExiste = false;
					}
				}
				if(noExiste){
					ComprasDetalle cDetalle = new ComprasDetalle();
					cDetalle.setImei(nroImei);
					cDetalle.setPrecioCompra(precioCompra);
					cDetalle.setCompra(compra);
					cDetalle.setCantidad(1);
					cDetalle.setProducto(unidadMovil.getProducto());
					listaComprasDetalleAdd.add(cDetalle);
					nroImei = "";
				}else{
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Nro de Imei ya se encuentra registrado, modifiquelo por favor!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Nro de Imei ya se encuentra registrado, modifiquelo por favor!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "El Nro de imei es requerido!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void quitarUnidad(String imei){
		List<ComprasDetalle> listAux = new ArrayList<ComprasDetalle>();
		for(ComprasDetalle comprasDetalle : listaComprasDetalleAdd){
			if(!comprasDetalle.getImei().equals(imei)){
				listAux.add(comprasDetalle);
			}
		}
		listaComprasDetalleAdd = new ArrayList<ComprasDetalle>();
		listaComprasDetalleAdd = listAux;
	}
	
	public void quitarUnidadDetalle(ComprasDetalle detalle){
		log.info("quitarUnidadDetalle: " + detalle.getProducto().getNombre() + " - " + detalle.getId());
		List<ComprasDetalle> listAux = new ArrayList<ComprasDetalle>();
		for(ComprasDetalle cd :listaComprasDetalles) {
			if(detalle.getAccesorio()) {
				if(!(cd.getProducto().getId() == detalle.getProducto().getId())){
					listAux.add(cd);
				}
			}else {
				if(!cd.getImei().equals(detalle.getImei())){
					listAux.add(cd);
				}
			}
			
		}
		
		if(detalle.getAccesorio()) {
			montoTotal = montoTotal - detalle.getSubtotal();
			cantidadTotal = cantidadTotal - detalle.getCantidad();
			
		}else {
			montoTotal = montoTotal - detalle.getPrecioCompra();
			cantidadTotal = cantidadTotal - 1;
		}

		listaComprasDetalles = new ArrayList<ComprasDetalle>();
		listaComprasDetalles = listAux;
	}

	public void agregarDetalle(){
		if(!panelMovil && !panelAccesorio){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un tipo de producto a comprar!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			if(panelMovil){
				List<ComprasDetalle> listAux = new ArrayList<ComprasDetalle>();
				if(!listaComprasDetalleAdd.isEmpty() && idProducto != 0 && precioCompra != 0){
					for(ComprasDetalle cdAdd: listaComprasDetalleAdd) {
						boolean seProcesa = true;
						if(listaProductos.size()> 0) {
							for(ComprasDetalle cd : listaComprasDetalles){
								listAux.add(cd);
								if(cd.getImei().equals(cdAdd.getImei())) {
									seProcesa = false;
								}
							}
						}
						if(seProcesa) {
							log.info(cdAdd.getPrecioCompra());
							cdAdd.setProducto(productoDAO.get(idProducto));
							listAux.add(cdAdd);
							cantidadTotal ++;
							montoTotal = montoTotal + cdAdd.getPrecioCompra();
						}
					}
					listaComprasDetalles = new ArrayList<ComprasDetalle>();
					listaComprasDetalles = listAux;
					listaComprasDetalleAdd = new ArrayList<ComprasDetalle>();
					idProducto = 0;
					precioCompra = 0;
					nroImei = "";
				} else {
					if (listaComprasDetalleAdd.isEmpty()) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "La lista de Unidades no puede estar vacia!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
					if (idProducto == 0) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un Producto!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
					if (precioCompra == 0) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un Precio de Compra!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}
			}
			if(panelAccesorio){
				if(idProducto != 0 && precioCompra != 0){
					boolean noExiste = true;
					List<ComprasDetalle> listAux = new ArrayList<ComprasDetalle>();
					for(ComprasDetalle compraDetalle : listaComprasDetalles){
						if(compraDetalle.getProducto().getId() == idProducto){
							noExiste = false;
							montoTotal = montoTotal - compraDetalle.getSubtotal();
							cantidadTotal = cantidadTotal - compraDetalle.getCantidad();
							float subtotal = precioCompra * cantidad;
							compraDetalle.setCantidad(cantidad);
							compraDetalle.setPrecioCompra(precioCompra);
							compraDetalle.setSubtotal(subtotal);
							compraDetalle.setAccesorio(true);
							cantidadTotal = cantidadTotal + cantidad;
							montoTotal = montoTotal + subtotal;
						}
						listAux.add(compraDetalle);
					}
					if(noExiste){
						ComprasDetalle compraDetalle = new ComprasDetalle();
						Producto prod = productoDAO.get(idProducto);
						float subtotal = cantidad * precioCompra;
						compraDetalle.setCantidad(cantidad);
						compraDetalle.setPrecioCompra(precioCompra);
						compraDetalle.setProducto(prod);				
						compraDetalle.setSubtotal(subtotal);
						compraDetalle.setAccesorio(true);
						listaComprasDetalles.add(compraDetalle);
						montoTotal = montoTotal + subtotal;
						cantidadTotal = cantidadTotal + cantidad;
					}else{
						listaComprasDetalles = new ArrayList<ComprasDetalle>();
						listaComprasDetalles = listAux;
					}
					idProducto = 0;
					precioCompra = 0;
					cantidad = 0;
				} else {
					if (idProducto == 0) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un Producto!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
					if (precioCompra == 0) {
						FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe colocar un Precio de Compra!", null);
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				}
			}
		}
	}
	public void quitarDetalle(ComprasDetalle comprasDetalle){
		log.info("id comprasDetalle: " + comprasDetalle.getId());
		List<ComprasDetalle> listAux = new ArrayList<ComprasDetalle>();
		log.info("listaComprasDetalles.size:  " + listaComprasDetalles.size());
		for(ComprasDetalle comprasDetalle2 : listaComprasDetalles){
			if(comprasDetalle2.getId() != comprasDetalle.getId()){
				listAux.add(comprasDetalle2);
			}
		}
		log.info("listAux.size:  " + listAux.size());
		if(comprasDetalle.getAccesorio()) {
			montoTotal = montoTotal - comprasDetalle.getSubtotal();
			cantidadTotal = cantidadTotal - comprasDetalle.getCantidad();
		}else {
			montoTotal = montoTotal - comprasDetalle.getPrecioCompra();
			cantidadTotal --;
		}
		
		listaComprasDetalles = null;
		listaComprasDetalles = new ArrayList<ComprasDetalle>();
		listaComprasDetalles = listAux;
	}
	
	public void baja(Compra comp){
		log.info("baja, Compra: " + comp.getId());
		FacesMessage msg = null;
		boolean disponible = true;
		String mensaje = "";
		List<ComprasDetalle> listaDetalles = compraDetalleDAO.getLista(comp);
		for (ComprasDetalle comprasDetalle : listaDetalles) {
			if(comprasDetalle.getAccesorio()){
				Producto prod = comprasDetalle.getProducto();
				if(prod.getStock() < comprasDetalle.getCantidad()){
					disponible = false;
					mensaje = "Venta";
				}
			}
			
			String imei = comprasDetalle.getImei();
			log.info("Imei:" + imei);
			
			UnidadMovil unidad = unidadMovilDAO.get(imei);
			if (unidad.getEnGarantiaCliente() || unidad.getEnGarantiaProveedor()) {
				mensaje = mensaje + "Garantia ";
				disponible = false;
			}
			if (comprasDetalle.getConFalla()) {
				mensaje = mensaje + "Falla ";
				disponible = false;
			}
			// VER CUANDO SE MERGEE LA MODIFICACION DE VENTAS
//          VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(imei);
//			if(ventaUnidad.getId() != 0){
//				mensaje = mensaje + "Venta ";
//				disponible = false;
//			}
			// VER CUANDO SE MERGEE LA MODIFICACION DE CONSIGNACIONES
//			ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(imei);
//			if(consignacionUnidad.getId() != 0){
//				mensaje = mensaje + "Consignacion ";
//				disponible = false;
//			}
		}
		if(disponible){
			boolean actualizo = true;
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
			int idProv = comp.getProveedore().getId();
			Proveedore prov = proveedorDAO.get(idProv); 
			log.info("Proveedor: " + prov.getApellidoNombre());
			log.info("Tipo Compra: " + comp.getTipo());
			if(comp.getTipo().equals("c.c.")){
				int idComp = comp.getId();
				ccProveedor.setIdMovimiento(idComp);
				ccProveedor.setNombreTabla("Compra");
				ccProveedor.setProveedore(prov);
				log.info("deleteCuentaCorriente");
				int deleteCC = cuenta.deleteCuentaCorriente(ccProveedor);
				if(deleteCC == 0){
					actualizo = false;
				}
			}
			if(comp.getTipo().equals("ctdo.")){
				int idComp = comp.getId();
				int deleteMovProv = cuentaCorrienteDAO.deletePorMovimientoProveedor(idComp, "Compra", prov);
				if(deleteMovProv == 0){
					actualizo = false;
				}
				MovimientoCaja movCaja = new MovimientoCaja();
				Caja mov = new Caja();				
				mov.setIdMovimiento(idComp);
				mov.setNombreTabla("Compra");
				log.info("deleteCaja");
				int deleteCaja = movCaja.deleteCaja(mov);
				if(deleteCaja == 0){
					actualizo = false;
				}
			}
			List<ComprasDetalle> listaCompDeta = compraDetalleDAO.getLista(comp);
			for(ComprasDetalle comprasDetalle : listaCompDeta){
				int cantDetalle = comprasDetalle.getCantidad();
				log.info("Cant Detalle: " + cantDetalle);
				String nroImei = comprasDetalle.getImei();
				UnidadMovil unidadBaja = unidadMovilDAO.getBajaStock(nroImei);
				if (unidadBaja.getBajaStock()) {
					cantDetalle = cantDetalle - 1;
				}
				int deleteUnidImei = unidadMovilDAO.deletePorImei(nroImei);
				if(deleteUnidImei == 0){
					actualizo = false;
				}	
				int idProd = comprasDetalle.getProducto().getId();
				log.info("Producto: "+ idProd);
				Producto prod = productoDAO.get(idProd);
				int stock = prod.getStock() - cantDetalle;
				prod.setStock(stock);
				int updateProd = productoDAO.update(prod);
				if(updateProd == 0){
					actualizo = false;
				}				
				int deleteDetUnid = compraDetalleUnidadDAO.deleteDetalleUnidadPorDetalle(comprasDetalle);
				log.info("deleteDetUnid: "+ deleteDetUnid);
				if(deleteDetUnid == 0){
					actualizo = false;
				}				
			}
			comp.setEstado(false);
			comp.setFechaBaja(new Date());
			comp.setUsuario2(usuario);
			int updateComp = compraDAO.update(comp);
			if(updateComp != 0 && actualizo){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Compra!", null);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al dar de Baja la Compra! "
						+ "Intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible dar de baja la Compra, "
					+ "posee productos asociados a una " + mensaje, null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/*
	
	public String guardar() {
		FacesMessage msg = null;
		String retorno = "";
		if (compra.getFecha() != null && idProveedor != 0 && !listaComprasDetalles.isEmpty() && montoTotal != 0) {
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();
			Proveedore prov = proveedorDAO.get(idProveedor);
			compra.setProveedore(prov);
			compra.setTipo(tipo);
			compra.setEstado(true);
			compra.setFechaAlta(new Date());
			compra.setMonto(montoTotal);
			compra.setUsuario1(usuario);
			ccProveedor.setDebe(montoTotal);
			ccProveedor.setDetalle("Compra nro: " + nroCompra);
			ccProveedor.setFecha(compra.getFecha());
			ccProveedor.setIdMovimiento(nroCompra);
			ccProveedor.setMonto(montoTotal);
			ccProveedor.setNombreTabla("Compra");
			ccProveedor.setProveedore(prov);
			ccProveedor.setUsuario(usuario);
			cuenta.insertarCC(ccProveedor);
			if (tipo.equals("ctdo.")) {
				CuentasCorrientesProveedore ccProveedor2 = new CuentasCorrientesProveedore();
				ccProveedor2.setDetalle("Pago de contado - Compra nro: " + nroCompra);
				ccProveedor2.setFecha(compra.getFecha());
				ccProveedor2.setHaber(montoTotal);
				ccProveedor2.setMonto(montoTotal);
				ccProveedor2.setProveedore(prov);
				ccProveedor2.setUsuario(usuario);
				ccProveedor2.setIdMovimiento(nroCompra);
				ccProveedor2.setNombreTabla("Compra");
				cuenta.insertarCC(ccProveedor2);
				MovimientoCaja movimientoCaja = new MovimientoCaja();
				Caja caja = new Caja();
				caja.setConcepto("Pago de Compra nro: " + nroCompra);
				caja.setFecha(compra.getFecha());
				caja.setIdMovimiento(nroCompra);
				float montoCaja = (-1) * montoTotal;
				caja.setMonto(montoCaja);
				caja.setNombreTabla("Compra");
				caja.setUsuario(usuario);
				movimientoCaja.insertarCaja(caja);
			}
			int idCompra = compraDAO.insertar(compra);
			if (idCompra != 0) {
				compra.setId(idCompra);
				boolean inserto = true;
				for (ComprasDetalle compraDetalle : listaComprasDetalles) {
					Producto prod = productoDAO.get(compraDetalle.getProducto().getId());
					CostoPromedio costoPromedio = new CostoPromedio();
					int stock = prod.getStock();
					int cantDet = compraDetalle.getCantidad();
					stock = stock + cantDet;
					prod.setStock(stock);
					prod.setPrecioCosto(compraDetalle.getPrecioCompra());
					List<ComprasDetalleUnidad> listAux = compraDetalle.getComprasDetalleUnidads();
					compraDetalle.setComprasDetalleUnidads(null);
					compraDetalle.setCompra(compra);
					int idDetalle = compraDetalleDAO.insertar(compraDetalle);
					float costoProm = costoPromedio.calculaCostoPromedioFloat(prod, compra.getFecha());
//					float costoFinal = costo;
					prod.setCostoPromedio(costoProm);
					productoDAO.update(prod);
					if (idDetalle != 0) {
						compraDetalle.setId(idDetalle);
						if (compraDetalle.getAccesorio()) {
							Stock stk = stockDAO.get(compraDetalle.getProducto(), compraDetalle.getPrecioCompra());
							if (stk.getId() != 0) {
								int cant = stk.getCantidad();
								cant = cant + compraDetalle.getCantidad();
								stk.setCantidad(cant);
								stk.setFechaMod(new Date());
								stk.setUsuario3(usuario);
								stockDAO.update(stk);
							} else {
								stk = new Stock();
								stk.setCantidad(compraDetalle.getCantidad());
								stk.setFechaAlta(new Date());
								stk.setPrecioCompra(compraDetalle.getPrecioCompra());
								stk.setProducto(compraDetalle.getProducto());
								stk.setUsuario3(usuario);
								stockDAO.insertar(stk);
							}
						} else {
							for (ComprasDetalleUnidad compraUnidad : listAux) {
								compraUnidad.setPrecioCompra(compraDetalle.getPrecioCompra());
								compraUnidad.setComprasDetalle(compraDetalle);
								UnidadMovil unidad = new UnidadMovil();
								unidad.setEnStock(true);
								unidad.setEstado(true);
								unidad.setEnCompra(true);
								unidad.setFechaAlta(new Date());
								unidad.setNroImei(compraUnidad.getNroImei());
								unidad.setPrecioCompra(compraDetalle.getPrecioCompra());
								unidad.setProducto(compraDetalle.getProducto());
								unidad.setUsuario1(usuario);
								int idDetalleUnidad = compraDetalleUnidadDAO.insertar(compraUnidad);
								int idUnidad = unidadMovilDAO.insertar(unidad);
								if(idDetalleUnidad == 0 && idUnidad == 0){
									inserto = false;
								}
							}
						}
					} else {
						inserto = false;
					}
				}
				if (inserto) {
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra guardada!", null);
					listaProveedores = new ArrayList<Proveedore>();
					listaProveedores = proveedorDAO.getLista(true);
					retorno = "compras";
				} else {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al guardar el detalle de la Compra, "
							+ "intente nuevamente!", null);
				}
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al guardar la Compra, "
						+ "intente nuevamente!", null);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Proveedor, Monto Total y Detalles de Compra son requeridos!", null);
		}
		listaCompras = new ArrayList<Compra>();
		filteredCompras = new ArrayList<Compra>();
		listaCompras = compraDAO.getLista(true);
		filteredCompras = listaCompras;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public String modificarCompra() {
		FacesMessage msg = null;
		String retorno = "";
		try {
			if(compra.getFecha() != null && idProveedor != 0 && !listaComprasDetalles.isEmpty() && montoTotal != 0){
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();		
				if(compra.getTipo().equals("c.c.")){
					ccProveedor.setIdMovimiento(nroCompra);
					ccProveedor.setNombreTabla("Compra");
					ccProveedor.setProveedore(compra.getProveedore());
					cuenta.deleteCuentaCorriente(ccProveedor);
				}
				if(compra.getTipo().equals("ctdo.")){
					cuentaCorrienteDAO.deletePorMovimientoProveedor(nroCompra, "Compra", compra.getProveedore());
					MovimientoCaja movCaja = new MovimientoCaja();
					Caja mov = new Caja();
					mov.setIdMovimiento(nroCompra);
					mov.setNombreTabla("Compra");
					movCaja.deleteCaja(mov);
				}
				Proveedore proveedore = proveedorDAO.get(idProveedor);	
				compra.setProveedore(proveedore);
				compra.setTipo(tipo);
				compra.setFechaMod(new Date());
				compra.setMonto(montoTotal);
				compra.setUsuario3(usuario);
				ccProveedor.setDebe(montoTotal);
				ccProveedor.setDetalle("Compra nro: " + nroCompra);
				ccProveedor.setFecha(compra.getFecha());
				ccProveedor.setIdMovimiento(nroCompra);
				ccProveedor.setMonto(montoTotal);
				ccProveedor.setNombreTabla("Compra");
				ccProveedor.setProveedore(proveedore);
				ccProveedor.setUsuario(usuario);
				cuenta.insertarCC(ccProveedor);
				if(tipo.equals("ctdo.")){
					ccProveedor = new CuentasCorrientesProveedore();
					ccProveedor.setDetalle("Pago de contado - Compra nro: " + nroCompra);
					ccProveedor.setFecha(compra.getFecha());
					ccProveedor.setHaber(montoTotal);
					ccProveedor.setMonto(montoTotal);
					ccProveedor.setIdMovimiento(nroCompra);
					ccProveedor.setNombreTabla("Compra");
					ccProveedor.setProveedore(proveedore);
					ccProveedor.setUsuario(usuario);
					cuenta.insertarCC(ccProveedor);
					MovimientoCaja movimientoCaja = new MovimientoCaja();
					Caja caja = new Caja();
					caja.setConcepto("Pago de Compra nro: " + nroCompra);
					caja.setFecha(compra.getFecha());
					caja.setIdMovimiento(nroCompra);
					float montoCaja = (-1) * montoTotal;
					caja.setMonto(montoCaja);
					caja.setNombreTabla("Compra");
					caja.setUsuario(usuario);
					movimientoCaja.insertarCaja(caja);
				}

				boolean baja = true;
				int idCompra = compraDAO.update(compra);
				if(idCompra != 0){
					compra.setId(idCompra);
					List<ComprasDetalle> listaDetalleAnterior = getDetalleDeCompra(compra);
					baja = bajaDetalleAnterior(listaDetalleAnterior);
					boolean inserto = altaDetalleNuevo(listaComprasDetalles, compra, baja);
					if(inserto){
						msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra guardada!", null);
						retorno = "compras";
					}else{
						msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al guardar el detalle de la Compra, "
								+ "intente nuevamente!", null);
					}
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar la Compra, "
							+ "intente nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha, Proveedor, Monto Total y Detalles de Compra son requeridos", null);
			}
			listaCompras = new ArrayList<Compra>();
			filteredCompras = new ArrayList<Compra>();
			listaCompras = compraDAO.getLista(true);
			filteredCompras = listaCompras;
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al modificar la Compra, "
					+ "intente nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return retorno;
		}
	}
	
	public boolean altaDetalleNuevo(List<ComprasDetalle> listaCompraDetalleNuevo, Compra comp, boolean valor) {
		System.out.println("AltaDetalleNuevo()");
		boolean insert = true;
		for (ComprasDetalle compraDetalle : listaCompraDetalleNuevo) {
			int idProd = compraDetalle.getProducto().getId();
			Producto prod = productoDAO.get(idProd);
			int stock = prod.getStock();
			stock = stock + compraDetalle.getCantidad();
			prod.setStock(stock);
			List<ComprasDetalleUnidad> listCompDetaUnidAux = compraDetalle.getComprasDetalleUnidads();
			if (valor) {
				compraDetalle.setCompra(comp);
				compraDetalle.setComprasDetalleUnidads(null);
				int idDetalle = compraDetalleDAO.insertar(compraDetalle);
				compraDetalle.setId(idDetalle);
				if (idDetalle == 0) {
					insert = false;
					System.out.println("Fallo la insercion de la CompraDetalle");
				}
			} else {
				compraDetalle.setCompra(comp);
				compraDetalle.setComprasDetalleUnidads(null);
				int idValor = compraDetalleDAO.update(compraDetalle);
				if (idValor == 0) {
					insert = false;
					System.out.println("Fallo la actualizacion de la CompraDetalle");
				}
			}
			CostoPromedio costoPromedio = new CostoPromedio();
			Producto pro = costoPromedio.calculaCostoPromedio(prod, comp.getFecha());
			int idValorProd = productoDAO.update(pro);
			if (idValorProd == 0) {
				System.out.println("Fallo la actualizacion del producto");
				insert = false;
			}
			if (compraDetalle.getAccesorio()) {
				Stock stk = stockDAO.get(compraDetalle.getProducto(), compraDetalle.getPrecioCompra());
				if(stk.getId() != 0){
					int cant = stk.getCantidad();
					cant = cant + compraDetalle.getCantidad();
					stk.setCantidad(cant);
					stk.setFechaMod(new Date());
					stk.setUsuario3(usuario);
					int idValorStock = stockDAO.update(stk);
					if (idValorStock == 0) {
						insert = false;
						System.out.println("Fallo la actualizacion del Stock");
					}
				} else {
					stk = new Stock();
					stk.setCantidad(compraDetalle.getCantidad());
					stk.setFechaAlta(new Date());
					stk.setPrecioCompra(compraDetalle.getPrecioCompra());
					stk.setProducto(compraDetalle.getProducto());
					stk.setUsuario3(usuario);
					int idValorStock = stockDAO.insertar(stk);
					if (idValorStock == 0) {
						insert = false;
						System.out.println("Fallo la insercion del Stock");
					}
				}
			} else {
				for (ComprasDetalleUnidad comprasDetalleUnidad : listCompDetaUnidAux) {
					UnidadMovil unidMov = unidadMovilDAO.get(comprasDetalleUnidad.getNroImei());
					if (!unidMov.getEnConsignacion() && !unidMov.getEnVenta()) {
						comprasDetalleUnidad.setPrecioCompra(compraDetalle.getPrecioCompra());
						comprasDetalleUnidad.setComprasDetalle(compraDetalle);
						UnidadMovil unidad = new UnidadMovil();
						unidad.setEnStock(true);
						unidad.setEstado(true);
						unidad.setEnCompra(true);
						unidad.setFechaAlta(new Date());
						unidad.setNroImei(comprasDetalleUnidad.getNroImei());
						unidad.setPrecioCompra(compraDetalle.getPrecioCompra());
						unidad.setProducto(compraDetalle.getProducto());
						unidad.setUsuario1(usuario);
						int idDetalleUnidad = compraDetalleUnidadDAO.insertar(comprasDetalleUnidad);
						int idUnidad = unidadMovilDAO.insertar(unidad);
						if(idDetalleUnidad == 0 && idUnidad == 0){
							insert = false;
							System.out.println("Fallo la insercion de la unidad: " + unidad.getNroImei());
						}
					} else {
						comprasDetalleUnidad.setComprasDetalle(compraDetalle);
						comprasDetalleUnidad.setPrecioCompra(compraDetalle.getPrecioCompra());
						unidMov.setPrecioCompra(compraDetalle.getPrecioCompra());
						int idValorDetalleUnidad = compraDetalleUnidadDAO.insertar(comprasDetalleUnidad);
						int idValorUnidadMovil = unidadMovilDAO.update(unidMov);
						if (idValorDetalleUnidad == 0 && idValorUnidadMovil == 0) {
							insert = false;
							System.out.println("Fallo la actualizacion de la unidad: " + unidMov.getNroImei());
						}
					}
				}
			}						
		}
		return insert;
	}
	
	public boolean bajaDetalleAnterior(List<ComprasDetalle> listaCompraDetalleAnterior) {
		log.info("BajaDetalleAnterior");
		boolean baja = true;
		for(ComprasDetalle detalleCompra : listaCompraDetalleAnterior){
			int cantDetalle = detalleCompra.getCantidad();
			if (!detalleCompra.getAccesorio()) {
				List<ComprasDetalleUnidad> listaCompDetUnid = compraDetalleUnidadDAO.getLista(detalleCompra);
				for (ComprasDetalleUnidad compraUnidad : listaCompDetUnid) {
					UnidadMovil unidadMov = unidadMovilDAO.get(compraUnidad.getNroImei());
					if (!unidadMov.getEnConsignacion() && !unidadMov.getEnVenta()) {
						unidadMovilDAO.deletePorImei(compraUnidad.getNroImei());	
						compraDetalleUnidadDAO.deleteDetalleCompraUnidad(compraUnidad);	
					}
					if (unidadMov.getBajaStock()) {
						cantDetalle = cantDetalle - 1;
					}
				}
				compraDetalleUnidadDAO.deleteDetalleUnidadPorDetalle(detalleCompra);
				compraDetalleDAO.deleteDetalleCompra(detalleCompra);							
			} else {
				Stock stk = stockDAO.get(detalleCompra.getProducto(), detalleCompra.getPrecioCompra());
				int cant = stk.getCantidad() - detalleCompra.getCantidad();
				stk.setCantidad(cant);
				stockDAO.update(stk);
				compraDetalleDAO.deleteDetalleCompra(detalleCompra);
			}
			int idProd = detalleCompra.getProducto().getId();
			Producto prod = productoDAO.get(idProd);
			int stock = prod.getStock();
			stock = stock - cantDetalle;
			prod.setStock(stock);
			int idValorProd = productoDAO.update(prod);
			if (idValorProd == 0) {
				System.out.println("Fallo la actualizacion de la baja del Producto");
			}									
		}
		return baja;
	}
	*/
	public boolean noBaja(ComprasDetalle compraDetalle){
		log.info("noBaja: " + compraDetalle.getId());
		boolean nobaja = false;
		if(compraDetalle.getId() != 0){
			log.info("Accesorio: " + compraDetalle.getAccesorio());
			if(compraDetalle.getAccesorio()){
				if(compraDetalle.getProducto().getStock() < compraDetalle.getCantidad()){
					nobaja = true;
				}
			}else{
				UnidadMovil unidad = new UnidadMovil(); 
				unidad = unidadMovilDAO.get(compraDetalle.getImei());
				if(unidad.getId() != 0){
					if(!unidad.getEnStock()){
						nobaja = true;
					}
					if(!unidad.getEstado()){
						nobaja = true;
					}
					if(unidad.getEnConsignacion()){
						nobaja = true;
					}
					if(unidad.getEnVenta()){
						nobaja = true;
					}
					if (unidad.getConFalla()) {
						nobaja = true;
					}
					if (unidad.getEnGarantiaCliente() || unidad.getEnGarantiaProveedor()) {
						nobaja = true;
					}
					if (compraDetalle.getConFalla()) {
						nobaja = true;
					}
				}
			}
		}
		log.info("nobaja: " + nobaja);
		return nobaja;
	}
	/*
	public boolean noBajaUnidad(ComprasDetalleUnidad compraUnidad){
		boolean nobaja = false;
		UnidadMovil unidad = unidadMovilDAO.get(compraUnidad.getNroImei());
		if(unidad.getId() != 0){
			if(!unidad.getEnStock()){
				nobaja = true;
			}
			if(!unidad.getEstado()){
				nobaja = true;
			}
			if(unidad.getEnConsignacion()){
				nobaja = true;
			}
			if(unidad.getEnVenta()){
				nobaja = true;
			}
			if (unidad.getConFalla()) {
				nobaja = true;
			}
			if (unidad.getEnGarantiaCliente() || unidad.getEnGarantiaProveedor()) {
				nobaja = true;
			}
			if (compraUnidad.getConFalla()) {
				nobaja = true;
			}
		}
		return nobaja;
	}
	*/
	public void generarReporteLista(){
		log.info("generarReporteLista");
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Comprobante> listComprobante = new ArrayList<Comprobante>();
		for(Compra comp : filteredCompras){
			List<DetalleComprobante> listDetalle = new ArrayList<DetalleComprobante>();
			Comprobante comprobante = new Comprobante();
			for(ComprasDetalle compDetalle : getDetalleDeCompra(comp)){
				DetalleComprobante detalleComprobante = new DetalleComprobante();
				detalleComprobante.setCantidad(compDetalle.getCantidad());
				detalleComprobante.setDetalle(compDetalle.getProducto().getNombre());
				detalleComprobante.setPrecioUnitario(formatoMonto.format(compDetalle.getPrecioCompra()));
				detalleComprobante.setSubtotal(formatoMonto.format(compDetalle.getSubtotal()));
				listDetalle.add(detalleComprobante);
			}
			comprobante.setFecha(formatoFecha.format(comp.getFecha()));
			comprobante.setListaDetalles(listDetalle);
			comprobante.setMonto(formatoMonto.format(comp.getMonto()));
			comprobante.setNumero(comp.getId());
			comprobante.setPersona(comp.getProveedore().getNombreNegocio());
			comprobante.setTipo(comp.getTipo());
			listComprobante.add(comprobante);
		}
		log.info("idProveedor: " + idProveedor);
		if(idProveedor == 0){
			parametros.put("proveedor", "Todos");
		}else{
			Proveedore prov = proveedorDAO.get(idProveedor);
			parametros.put("proveedor", prov.getNombreNegocio());
		}
		log.info("fechaInicio: " + fechaInicio + " fechaFin: " + fechaFin);
		if(fechaInicio != null && fechaFin != null){
			parametros.put("desde", formatoFecha.format(fechaInicio));
			parametros.put("hasta", formatoFecha.format(fechaFin));			
		}else{
			parametros.put("desde", "Todas");
			parametros.put("hasta", "Todas");
		}
		log.info("generar reporte-> compras");
		reporte.generar(parametros, listComprobante, "compras", "inline");
	}
	
	public void generarReporteComprobante(Compra comp){
		log.info("generarReporteComprobante, Compra: " + comp.getId());
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<DetalleComprobante> listaDetalle = new ArrayList<DetalleComprobante>();
		int cant = 0;
		for(ComprasDetalle compraDetalle : getDetalleDeCompra(comp)){
			DetalleComprobante detalle = new DetalleComprobante();
			if(compraDetalle.getAccesorio()) {
				detalle.setDetalle(compraDetalle.getProducto().getNombre());
				detalle.setSubtotal(formatoMonto.format(compraDetalle.getSubtotal()));
			}else {
				detalle.setDetalle(compraDetalle.getProducto().getNombre() + " - " + compraDetalle.getImei());
				detalle.setSubtotal(formatoMonto.format(compraDetalle.getPrecioCompra()));
			}
			detalle.setCantidad(compraDetalle.getCantidad());
			detalle.setPrecioUnitario(formatoMonto.format(compraDetalle.getPrecioCompra()));
			listaDetalle.add(detalle);
			cant = cant + compraDetalle.getCantidad();
		}
		parametros.put("numero", comp.getId());
		parametros.put("persona", comp.getProveedore().getNombreNegocio());
		parametros.put("fecha", formatoFecha.format(comp.getFecha()));
		parametros.put("tipo", comp.getTipo());
		parametros.put("cantidadTotal", cant);
		parametros.put("montoTotal", formatoMonto.format(comp.getMonto()));
		log.info("generar reporte-> compra");
		reporte.generar(parametros, listaDetalle, "compra", "attachment");
	}
	
	public void goEditarTipo(Compra comp) {
		log.info("goEditarTipo, Compra: " + comp.getId());
		compra = new Compra();
		compra = comp;
		tipo = comp.getTipo();
	}
	
	public void editarTipo() {
		log.info("editarTipo, Tipo Compra: " + compra.getTipo());
		CuentaCorriente cuenta = new CuentaCorriente();
		CuentasCorrientesProveedore ccProveedor = new CuentasCorrientesProveedore();		
		if(compra.getTipo().equals("c.c.")){
			ccProveedor.setIdMovimiento(compra.getId());
			ccProveedor.setNombreTabla("Compra");
			ccProveedor.setProveedore(compra.getProveedore());
			cuenta.deleteCuentaCorriente(ccProveedor);
		}
		if(compra.getTipo().equals("ctdo.")){
			cuentaCorrienteDAO.deletePorMovimientoProveedor(compra.getId(), "Compra", compra.getProveedore());
			MovimientoCaja movCaja = new MovimientoCaja();
			Caja mov = new Caja();
			mov.setIdMovimiento(compra.getId());
			mov.setNombreTabla("Compra");
			movCaja.deleteCaja(mov);
		}
		Proveedore proveedore = compra.getProveedore();	
		compra.setProveedore(proveedore);
		compra.setTipo(tipo);
		compra.setFechaMod(new Date());
		compra.setMonto(compra.getMonto());
		compra.setUsuario3(usuario);
		ccProveedor.setDebe(compra.getMonto());
		ccProveedor.setDetalle("Compra nro: " + compra.getId());
		ccProveedor.setFecha(compra.getFecha());
		ccProveedor.setIdMovimiento(compra.getId());
		ccProveedor.setMonto(compra.getMonto());
		ccProveedor.setNombreTabla("Compra");
		ccProveedor.setProveedore(proveedore);
		ccProveedor.setUsuario(usuario);
		cuenta.insertarCC(ccProveedor);
		if(tipo.equals("ctdo.")){
			ccProveedor = new CuentasCorrientesProveedore();
			ccProveedor.setDetalle("Pago de contado - Compra nro: " + compra.getId());
			ccProveedor.setFecha(compra.getFecha());
			ccProveedor.setHaber(compra.getMonto());
			ccProveedor.setMonto(compra.getMonto());
			ccProveedor.setIdMovimiento(compra.getId());
			ccProveedor.setNombreTabla("Compra");
			ccProveedor.setProveedore(proveedore);
			ccProveedor.setUsuario(usuario);
			cuenta.insertarCC(ccProveedor);
			MovimientoCaja movimientoCaja = new MovimientoCaja();
			Caja caja = new Caja();
			caja.setConcepto("Pago de Compra nro: " + compra.getId());
			caja.setFecha(compra.getFecha());
			caja.setIdMovimiento(compra.getId());
			float montoCaja = (-1) * compra.getMonto();
			caja.setMonto(montoCaja);
			caja.setNombreTabla("Compra");
			caja.setUsuario(usuario);
			movimientoCaja.insertarCaja(caja);
		}
		int idCompra = compraDAO.update(compra);
		log.info("idCompra " + idCompra);
		if (idCompra != 0) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "CAMBIO DE TIPO DE COMPRA REGISTRADO", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OCURRIO UN ERROR AL CAMBIAR EL TIPO DE COMPRA, "
					+ "INTENTELO NUEVAMENTE!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
