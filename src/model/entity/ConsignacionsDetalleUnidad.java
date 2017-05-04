package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the consignacions_detalle_unidad database table.
 * 
 */
@Entity
@Table(name="consignacions_detalle_unidad")
@NamedQuery(name="ConsignacionsDetalleUnidad.findAll", query="SELECT c FROM ConsignacionsDetalleUnidad c")
public class ConsignacionsDetalleUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private boolean eliminado;
	
	private boolean enabled;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_venta")
	private Date fechaVenta;

	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_compra")
	private float precioCompra;

	@Column(name="precio_venta")
	private float precioVenta;

	@Column(name="tipo_venta")
	private String tipoVenta;

	private boolean vendido;

	//bi-directional many-to-one association to ConsignacionsDetalle
	@ManyToOne
	@JoinColumn(name="id_consignacion_detalle")
	private ConsignacionsDetalle consignacionsDetalle;
	
	//bi-directional many-to-one association to ListaPrecio
	@ManyToOne
	@JoinColumn(name="id_lista_precio")
	private ListaPrecio listaPrecio;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to UnidadMovil
	@ManyToOne
	@JoinColumn(name="id_unidad_movil")
	private UnidadMovil unidadMovil;
	
	//bi-directional many-to-one association to VentasConsDetalleUnidad
	@OneToMany(mappedBy="consignacionsDetalleUnidad")
	private List<VentasConsDetalleUnidad> ventasConsDetalleUnidads;

	public ConsignacionsDetalleUnidad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getEliminado() {
		return this.eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
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

	public float getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getTipoVenta() {
		return this.tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public boolean getVendido() {
		return this.vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public ConsignacionsDetalle getConsignacionsDetalle() {
		return this.consignacionsDetalle;
	}

	public void setConsignacionsDetalle(ConsignacionsDetalle consignacionsDetalle) {
		this.consignacionsDetalle = consignacionsDetalle;
	}
	
	public ListaPrecio getListaPrecio() {
		return this.listaPrecio;
	}

	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public UnidadMovil getUnidadMovil() {
		return this.unidadMovil;
	}

	public void setUnidadMovil(UnidadMovil unidadMovil) {
		this.unidadMovil = unidadMovil;
	}
	
	public List<VentasConsDetalleUnidad> getVentasConsDetalleUnidads() {
		return this.ventasConsDetalleUnidads;
	}

	public void setVentasConsDetalleUnidads(List<VentasConsDetalleUnidad> ventasConsDetalleUnidads) {
		this.ventasConsDetalleUnidads = ventasConsDetalleUnidads;
	}

	public VentasConsDetalleUnidad addVentasConsDetalleUnidad(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		getVentasConsDetalleUnidads().add(ventasConsDetalleUnidad);
		ventasConsDetalleUnidad.setConsignacionsDetalleUnidad(this);

		return ventasConsDetalleUnidad;
	}

	public VentasConsDetalleUnidad removeVentasConsDetalleUnidad(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		getVentasConsDetalleUnidads().remove(ventasConsDetalleUnidad);
		ventasConsDetalleUnidad.setConsignacionsDetalleUnidad(null);

		return ventasConsDetalleUnidad;
	}

}