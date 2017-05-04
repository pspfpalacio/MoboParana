package model.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ventas_detalle_unidad database table.
 * 
 */
@Entity
@Table(name="ventas_detalle_unidad")
@NamedQuery(name="VentasDetalleUnidad.findAll", query="SELECT v FROM VentasDetalleUnidad v")
public class VentasDetalleUnidad implements Serializable {
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

	//bi-directional many-to-one association to VentasDetalle
	@ManyToOne
	@JoinColumn(name="id_venta_detalle")
	private VentasDetalle ventasDetalle;

	//bi-directional many-to-one association to UnidadMovil
	@ManyToOne
	@JoinColumn(name="id_unidad_movil")
	private UnidadMovil unidadMovil;

	public VentasDetalleUnidad() {
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

	public VentasDetalle getVentasDetalle() {
		return this.ventasDetalle;
	}

	public void setVentasDetalle(VentasDetalle ventasDetalle) {
		this.ventasDetalle = ventasDetalle;
	}

	public UnidadMovil getUnidadMovil() {
		return this.unidadMovil;
	}

	public void setUnidadMovil(UnidadMovil unidadMovil) {
		this.unidadMovil = unidadMovil;
	}

}