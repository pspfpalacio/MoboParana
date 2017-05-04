package model.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the compras_detalle_unidad database table.
 * 
 */
@Entity
@Table(name="compras_detalle_unidad")
@NamedQuery(name="ComprasDetalleUnidad.findAll", query="SELECT c FROM ComprasDetalleUnidad c")
public class ComprasDetalleUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="con_falla")
	private boolean conFalla;
	
	private boolean eliminado;

	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_compra")
	private float precioCompra;

	//bi-directional many-to-one association to ComprasDetalle
	@ManyToOne
	@JoinColumn(name="id_compra_detalle")
	private ComprasDetalle comprasDetalle;

	public ComprasDetalleUnidad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getConFalla() {
		return this.conFalla;
	}

	public void setConFalla(boolean conFalla) {
		this.conFalla = conFalla;
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

	public ComprasDetalle getComprasDetalle() {
		return this.comprasDetalle;
	}

	public void setComprasDetalle(ComprasDetalle comprasDetalle) {
		this.comprasDetalle = comprasDetalle;
	}

}