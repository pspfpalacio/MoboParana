package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the compras_detalle database table.
 * 
 */
@Entity
@Table(name="compras_detalle")
@NamedQuery(name="ComprasDetalle.findAll", query="SELECT c FROM ComprasDetalle c")
public class ComprasDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean accesorio;

	@Column(name="con_falla")
	private boolean conFalla;

	private boolean eliminado;

	private String imei;

	@Column(name="precio_compra")
	private float precioCompra;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	@JoinColumn(name="id_compra")
	private Compra compra;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to ComprasDetalleUnidad
	@OneToMany(mappedBy="comprasDetalle")
	private List<ComprasDetalleUnidad> comprasDetalleUnidads;

	public ComprasDetalle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAccesorio() {
		return this.accesorio;
	}

	public void setAccesorio(boolean accesorio) {
		this.accesorio = accesorio;
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

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public float getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<ComprasDetalleUnidad> getComprasDetalleUnidads() {
		return this.comprasDetalleUnidads;
	}

	public void setComprasDetalleUnidads(List<ComprasDetalleUnidad> comprasDetalleUnidads) {
		this.comprasDetalleUnidads = comprasDetalleUnidads;
	}

	public ComprasDetalleUnidad addComprasDetalleUnidad(ComprasDetalleUnidad comprasDetalleUnidad) {
		getComprasDetalleUnidads().add(comprasDetalleUnidad);
		comprasDetalleUnidad.setComprasDetalle(this);

		return comprasDetalleUnidad;
	}

	public ComprasDetalleUnidad removeComprasDetalleUnidad(ComprasDetalleUnidad comprasDetalleUnidad) {
		getComprasDetalleUnidads().remove(comprasDetalleUnidad);
		comprasDetalleUnidad.setComprasDetalle(null);

		return comprasDetalleUnidad;
	}

}