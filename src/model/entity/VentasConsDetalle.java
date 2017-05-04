package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ventas_cons_detalle database table.
 * 
 */
@Entity
@Table(name="ventas_cons_detalle")
@NamedQuery(name="VentasConsDetalle.findAll", query="SELECT v FROM VentasConsDetalle v")
public class VentasConsDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private boolean eliminado;

	@Column(name="precio_venta")
	private float precioVenta;

	private float subtotal;

	//bi-directional many-to-one association to VentasCon
	@ManyToOne
	@JoinColumn(name="id_venta_cons")
	private VentasCon ventasCon;

	//bi-directional many-to-one association to ConsignacionsDetalle
	@ManyToOne
	@JoinColumn(name="id_consig_detalle")
	private ConsignacionsDetalle consignacionsDetalle;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to VentasConsDetalleUnidad
	@OneToMany(mappedBy="ventasConsDetalle")
	private List<VentasConsDetalleUnidad> ventasConsDetalleUnidads;

	public VentasConsDetalle() {
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

	public VentasCon getVentasCon() {
		return this.ventasCon;
	}

	public void setVentasCon(VentasCon ventasCon) {
		this.ventasCon = ventasCon;
	}

	public ConsignacionsDetalle getConsignacionsDetalle() {
		return this.consignacionsDetalle;
	}

	public void setConsignacionsDetalle(ConsignacionsDetalle consignacionsDetalle) {
		this.consignacionsDetalle = consignacionsDetalle;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<VentasConsDetalleUnidad> getVentasConsDetalleUnidads() {
		return this.ventasConsDetalleUnidads;
	}

	public void setVentasConsDetalleUnidads(List<VentasConsDetalleUnidad> ventasConsDetalleUnidads) {
		this.ventasConsDetalleUnidads = ventasConsDetalleUnidads;
	}

	public VentasConsDetalleUnidad addVentasConsDetalleUnidad(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		getVentasConsDetalleUnidads().add(ventasConsDetalleUnidad);
		ventasConsDetalleUnidad.setVentasConsDetalle(this);

		return ventasConsDetalleUnidad;
	}

	public VentasConsDetalleUnidad removeVentasConsDetalleUnidad(VentasConsDetalleUnidad ventasConsDetalleUnidad) {
		getVentasConsDetalleUnidads().remove(ventasConsDetalleUnidad);
		ventasConsDetalleUnidad.setVentasConsDetalle(null);

		return ventasConsDetalleUnidad;
	}

}