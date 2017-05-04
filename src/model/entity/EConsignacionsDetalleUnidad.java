package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the e_consignacions_detalle_unidad database table.
 * 
 */
@Entity
@Table(name="e_consignacions_detalle_unidad")
@NamedQuery(name="EConsignacionsDetalleUnidad.findAll", query="SELECT e FROM EConsignacionsDetalleUnidad e")
public class EConsignacionsDetalleUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_venta")
	private float precioVenta;

	//bi-directional many-to-one association to EConsignacionsDetalle
	@ManyToOne
	@JoinColumn(name="id_e_consignacion_detalle")
	private EConsignacionsDetalle EConsignacionsDetalle;

	//bi-directional many-to-one association to ListaPrecio
	@ManyToOne
	@JoinColumn(name="id_lista_precio")
	private ListaPrecio listaPrecio;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public EConsignacionsDetalleUnidad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNroImei() {
		return this.nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public float getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public EConsignacionsDetalle getEConsignacionsDetalle() {
		return this.EConsignacionsDetalle;
	}

	public void setEConsignacionsDetalle(EConsignacionsDetalle EConsignacionsDetalle) {
		this.EConsignacionsDetalle = EConsignacionsDetalle;
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

}