package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ventas_cons_detalle_unidad database table.
 * 
 */
@Entity
@Table(name="ventas_cons_detalle_unidad")
@NamedQuery(name="VentasConsDetalleUnidad.findAll", query="SELECT v FROM VentasConsDetalleUnidad v")
public class VentasConsDetalleUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean eliminado;

	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_compra")
	private float precioCompra;

	@Column(name="precio_venta")
	private float precioVenta;

	//bi-directional many-to-one association to VentasConsDetalle
	@ManyToOne
	@JoinColumn(name="id_venta_cons_detalle")
	private VentasConsDetalle ventasConsDetalle;

	//bi-directional many-to-one association to ConsignacionsDetalleUnidad
	@ManyToOne
	@JoinColumn(name="id_consignacion_detalle_unidad")
	private ConsignacionsDetalleUnidad consignacionsDetalleUnidad;

	public VentasConsDetalleUnidad() {
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

	public VentasConsDetalle getVentasConsDetalle() {
		return this.ventasConsDetalle;
	}

	public void setVentasConsDetalle(VentasConsDetalle ventasConsDetalle) {
		this.ventasConsDetalle = ventasConsDetalle;
	}

	public ConsignacionsDetalleUnidad getConsignacionsDetalleUnidad() {
		return this.consignacionsDetalleUnidad;
	}

	public void setConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		this.consignacionsDetalleUnidad = consignacionsDetalleUnidad;
	}

}