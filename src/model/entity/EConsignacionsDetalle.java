package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the e_consignacions_detalle database table.
 * 
 */
@Entity
@Table(name="e_consignacions_detalle")
@NamedQuery(name="EConsignacionsDetalle.findAll", query="SELECT e FROM EConsignacionsDetalle e")
public class EConsignacionsDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	@Column(name="precio_venta")
	private float precioVenta;

	private float subtotal;

	//bi-directional many-to-one association to EConsignacion
	@ManyToOne
	@JoinColumn(name="id_e_consignacion")
	private EConsignacion EConsignacion;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to EConsignacionsDetalleUnidad
	@OneToMany(mappedBy="EConsignacionsDetalle")
	private List<EConsignacionsDetalleUnidad> EConsignacionsDetalleUnidads;

	public EConsignacionsDetalle() {
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

	public EConsignacion getEConsignacion() {
		return this.EConsignacion;
	}

	public void setEConsignacion(EConsignacion EConsignacion) {
		this.EConsignacion = EConsignacion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<EConsignacionsDetalleUnidad> getEConsignacionsDetalleUnidads() {
		return this.EConsignacionsDetalleUnidads;
	}

	public void setEConsignacionsDetalleUnidads(List<EConsignacionsDetalleUnidad> EConsignacionsDetalleUnidads) {
		this.EConsignacionsDetalleUnidads = EConsignacionsDetalleUnidads;
	}

	public EConsignacionsDetalleUnidad addEConsignacionsDetalleUnidad(EConsignacionsDetalleUnidad EConsignacionsDetalleUnidad) {
		getEConsignacionsDetalleUnidads().add(EConsignacionsDetalleUnidad);
		EConsignacionsDetalleUnidad.setEConsignacionsDetalle(this);

		return EConsignacionsDetalleUnidad;
	}

	public EConsignacionsDetalleUnidad removeEConsignacionsDetalleUnidad(EConsignacionsDetalleUnidad EConsignacionsDetalleUnidad) {
		getEConsignacionsDetalleUnidads().remove(EConsignacionsDetalleUnidad);
		EConsignacionsDetalleUnidad.setEConsignacionsDetalle(null);

		return EConsignacionsDetalleUnidad;
	}

}