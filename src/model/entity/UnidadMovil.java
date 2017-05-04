package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the unidad_movil database table.
 * 
 */
@Entity
@Table(name="unidad_movil")
@NamedQuery(name="UnidadMovil.findAll", query="SELECT u FROM UnidadMovil u")
public class UnidadMovil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="baja_stock")
	private boolean bajaStock;
	
	@Column(name="con_falla")
	private boolean conFalla;

	private String descripcion;
	
	private boolean devuelto;
	
	private boolean eliminado;
	
	@Column(name="en_compra")
	private boolean enCompra;

	@Column(name="en_consignacion")
	private boolean enConsignacion;
	
	@Column(name="en_garantia_cliente")
	private boolean enGarantiaCliente;

	@Column(name="en_garantia_proveedor")
	private boolean enGarantiaProveedor;

	@Column(name="en_stock")
	private boolean enStock;

	@Column(name="en_venta")
	private boolean enVenta;

	private boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja_stock")
	private Date fechaBajaStock;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_compra")
	private float precioCompra;

	//bi-directional many-to-one association to ConsignacionsDetalleUnidad
	@OneToMany(mappedBy="unidadMovil")
	private List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario3;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_baja_stock")
	private Usuario usuario4;

	//bi-directional many-to-one association to VentasDetalleUnidad
	@OneToMany(mappedBy="unidadMovil")
	private List<VentasDetalleUnidad> ventasDetalleUnidads;

	public UnidadMovil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getBajaStock() {
		return this.bajaStock;
	}

	public void setBajaStock(boolean bajaStock) {
		this.bajaStock = bajaStock;
	}
	
	public boolean getConFalla() {
		return this.conFalla;
	}

	public void setConFalla(boolean conFalla) {
		this.conFalla = conFalla;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean getDevuelto() {
		return this.devuelto;
	}

	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	
	public boolean getEliminado() {
		return this.eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public boolean getEnCompra() {
		return this.enCompra;
	}

	public void setEnCompra(boolean enCompra) {
		this.enCompra = enCompra;
	}

	public boolean getEnConsignacion() {
		return this.enConsignacion;
	}

	public void setEnConsignacion(boolean enConsignacion) {
		this.enConsignacion = enConsignacion;
	}
	
	public boolean getEnGarantiaCliente() {
		return this.enGarantiaCliente;
	}

	public void setEnGarantiaCliente(boolean enGarantiaCliente) {
		this.enGarantiaCliente = enGarantiaCliente;
	}

	public boolean getEnGarantiaProveedor() {
		return this.enGarantiaProveedor;
	}

	public void setEnGarantiaProveedor(boolean enGarantiaProveedor) {
		this.enGarantiaProveedor = enGarantiaProveedor;
	}

	public boolean getEnStock() {
		return this.enStock;
	}

	public void setEnStock(boolean enStock) {
		this.enStock = enStock;
	}

	public boolean getEnVenta() {
		return this.enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public Date getFechaBajaStock() {
		return this.fechaBajaStock;
	}

	public void setFechaBajaStock(Date fechaBajaStock) {
		this.fechaBajaStock = fechaBajaStock;
	}

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public String getNroImei() {
		return this.nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public float getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public List<ConsignacionsDetalleUnidad> getConsignacionsDetalleUnidads() {
		return this.consignacionsDetalleUnidads;
	}

	public void setConsignacionsDetalleUnidads(List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads) {
		this.consignacionsDetalleUnidads = consignacionsDetalleUnidads;
	}

	public ConsignacionsDetalleUnidad addConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().add(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setUnidadMovil(this);

		return consignacionsDetalleUnidad;
	}

	public ConsignacionsDetalleUnidad removeConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().remove(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setUnidadMovil(null);

		return consignacionsDetalleUnidad;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}
	
	public Usuario getUsuario4() {
		return this.usuario4;
	}

	public void setUsuario4(Usuario usuario4) {
		this.usuario4 = usuario4;
	}

	public List<VentasDetalleUnidad> getVentasDetalleUnidads() {
		return this.ventasDetalleUnidads;
	}

	public void setVentasDetalleUnidads(List<VentasDetalleUnidad> ventasDetalleUnidads) {
		this.ventasDetalleUnidads = ventasDetalleUnidads;
	}

	public VentasDetalleUnidad addVentasDetalleUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		getVentasDetalleUnidads().add(ventasDetalleUnidad);
		ventasDetalleUnidad.setUnidadMovil(this);

		return ventasDetalleUnidad;
	}

	public VentasDetalleUnidad removeVentasDetalleUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		getVentasDetalleUnidads().remove(ventasDetalleUnidad);
		ventasDetalleUnidad.setUnidadMovil(null);

		return ventasDetalleUnidad;
	}

}