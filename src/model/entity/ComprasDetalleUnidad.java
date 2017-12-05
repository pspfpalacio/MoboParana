package model.entity;

import java.io.Serializable;
import java.util.Date;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_compra")
	private float precioCompra;
	
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

	public ComprasDetalle getComprasDetalle() {
		return this.comprasDetalle;
	}

	public void setComprasDetalle(ComprasDetalle comprasDetalle) {
		this.comprasDetalle = comprasDetalle;
	}

}