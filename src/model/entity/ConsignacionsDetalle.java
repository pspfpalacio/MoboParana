package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the consignacions_detalle database table.
 * 
 */
@Entity
@Table(name="consignacions_detalle")
@NamedQuery(name="ConsignacionsDetalle.findAll", query="SELECT c FROM ConsignacionsDetalle c")
public class ConsignacionsDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;
	
	private boolean eliminado;

	@Column(name="precio_venta")
	private float precioVenta;

	private float subtotal;

	@Column(name="tipo_venta")
	private String tipoVenta;

	private boolean vendido;

	//bi-directional many-to-one association to Consignacion
	@ManyToOne
	@JoinColumn(name="id_consignacion")
	private Consignacion consignacion;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to ConsignacionsDetalleUnidad
	@OneToMany(mappedBy="consignacionsDetalle")
	private List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads;
	
	//bi-directional many-to-one association to VentasConsDetalle
	@OneToMany(mappedBy="consignacionsDetalle")
	private List<VentasConsDetalle> ventasConsDetalles;

	public ConsignacionsDetalle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public boolean getEliminado() {
		return this.eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public float getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
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

	public Consignacion getConsignacion() {
		return this.consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<ConsignacionsDetalleUnidad> getConsignacionsDetalleUnidads() {
		return this.consignacionsDetalleUnidads;
	}

	public void setConsignacionsDetalleUnidads(List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads) {
		this.consignacionsDetalleUnidads = consignacionsDetalleUnidads;
	}

	public ConsignacionsDetalleUnidad addConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().add(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setConsignacionsDetalle(this);

		return consignacionsDetalleUnidad;
	}

	public ConsignacionsDetalleUnidad removeConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().remove(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setConsignacionsDetalle(null);

		return consignacionsDetalleUnidad;
	}
	
	public List<VentasConsDetalle> getVentasConsDetalles() {
		return this.ventasConsDetalles;
	}

	public void setVentasConsDetalles(List<VentasConsDetalle> ventasConsDetalles) {
		this.ventasConsDetalles = ventasConsDetalles;
	}

	public VentasConsDetalle addVentasConsDetalle(VentasConsDetalle ventasConsDetalle) {
		getVentasConsDetalles().add(ventasConsDetalle);
		ventasConsDetalle.setConsignacionsDetalle(this);

		return ventasConsDetalle;
	}

	public VentasConsDetalle removeVentasConsDetalle(VentasConsDetalle ventasConsDetalle) {
		getVentasConsDetalles().remove(ventasConsDetalle);
		ventasConsDetalle.setConsignacionsDetalle(null);

		return ventasConsDetalle;
	}

}