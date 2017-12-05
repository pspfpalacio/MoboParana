package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean accesorio;

	private int cantidad;
	
	private boolean eliminado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	@Column(name="precio_compra")
	private float precioCompra;

	private float subtotal;
	
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

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public float getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
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