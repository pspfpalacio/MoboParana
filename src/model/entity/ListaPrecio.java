package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the lista_precio database table.
 * 
 */
@Entity
@Table(name="lista_precio")
@NamedQuery(name="ListaPrecio.findAll", query="SELECT l FROM ListaPrecio l")
public class ListaPrecio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private boolean base;

	private boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	private String nombre;
	
	@Column(name="relacion_base")
	private boolean relacionBase;
	
	//bi-directional many-to-one association to Rubro
	@ManyToOne
	@JoinColumn(name="id_rubro")
	private Rubro rubro;

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

	//bi-directional many-to-one association to ListaPrecioProducto
	@OneToMany(mappedBy="listaPrecio")
	private List<ListaPrecioProducto> listaPrecioProductos;
	
	//bi-directional many-to-one association to ConsignacionsDetalleUnidad
	@OneToMany(mappedBy="listaPrecio")
	private List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads;
	
	//bi-directional many-to-one association to EConsignacionsDetalleUnidad
	@OneToMany(mappedBy="listaPrecio")
	private List<EConsignacionsDetalleUnidad> EConsignacionsDetalleUnidads;
	
	//bi-directional many-to-one association to VentasDetalle
	@OneToMany(mappedBy="listaPrecio")
	private List<VentasDetalle> ventasDetalles;
	
	//bi-directional many-to-one association to VentasDetalleUnidad
	@OneToMany(mappedBy="listaPrecio")
	private List<VentasDetalleUnidad> ventasDetalleUnidads;

	public ListaPrecio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getBase() {
		return this.base;
	}

	public void setBase(boolean base) {
		this.base = base;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Rubro getRubro() {
		return this.rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}
	
	public boolean getRelacionBase() {
		return this.relacionBase;
	}

	public void setRelacionBase(boolean relacionBase) {
		this.relacionBase = relacionBase;
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

	public List<ListaPrecioProducto> getListaPrecioProductos() {
		return this.listaPrecioProductos;
	}

	public void setListaPrecioProductos(List<ListaPrecioProducto> listaPrecioProductos) {
		this.listaPrecioProductos = listaPrecioProductos;
	}

	public ListaPrecioProducto addListaPrecioProducto(ListaPrecioProducto listaPrecioProducto) {
		getListaPrecioProductos().add(listaPrecioProducto);
		listaPrecioProducto.setListaPrecio(this);

		return listaPrecioProducto;
	}

	public ListaPrecioProducto removeListaPrecioProducto(ListaPrecioProducto listaPrecioProducto) {
		getListaPrecioProductos().remove(listaPrecioProducto);
		listaPrecioProducto.setListaPrecio(null);

		return listaPrecioProducto;
	}
	
	public List<ConsignacionsDetalleUnidad> getConsignacionsDetalleUnidads() {
		return this.consignacionsDetalleUnidads;
	}

	public void setConsignacionsDetalleUnidads(List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads) {
		this.consignacionsDetalleUnidads = consignacionsDetalleUnidads;
	}

	public ConsignacionsDetalleUnidad addConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().add(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setListaPrecio(this);

		return consignacionsDetalleUnidad;
	}

	public ConsignacionsDetalleUnidad removeConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().remove(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setListaPrecio(null);

		return consignacionsDetalleUnidad;
	}
	
	public List<EConsignacionsDetalleUnidad> getEConsignacionsDetalleUnidads() {
		return this.EConsignacionsDetalleUnidads;
	}

	public void setEConsignacionsDetalleUnidads(List<EConsignacionsDetalleUnidad> EConsignacionsDetalleUnidads) {
		this.EConsignacionsDetalleUnidads = EConsignacionsDetalleUnidads;
	}

	public EConsignacionsDetalleUnidad addEConsignacionsDetalleUnidad(EConsignacionsDetalleUnidad EConsignacionsDetalleUnidad) {
		getEConsignacionsDetalleUnidads().add(EConsignacionsDetalleUnidad);
		EConsignacionsDetalleUnidad.setListaPrecio(this);

		return EConsignacionsDetalleUnidad;
	}

	public EConsignacionsDetalleUnidad removeEConsignacionsDetalleUnidad(EConsignacionsDetalleUnidad EConsignacionsDetalleUnidad) {
		getEConsignacionsDetalleUnidads().remove(EConsignacionsDetalleUnidad);
		EConsignacionsDetalleUnidad.setListaPrecio(null);

		return EConsignacionsDetalleUnidad;
	}
	
	public List<VentasDetalle> getVentasDetalles() {
		return this.ventasDetalles;
	}

	public void setVentasDetalles(List<VentasDetalle> ventasDetalles) {
		this.ventasDetalles = ventasDetalles;
	}

	public VentasDetalle addVentasDetalle(VentasDetalle ventasDetalle) {
		getVentasDetalles().add(ventasDetalle);
		ventasDetalle.setListaPrecio(this);

		return ventasDetalle;
	}

	public VentasDetalle removeVentasDetalle(VentasDetalle ventasDetalle) {
		getVentasDetalles().remove(ventasDetalle);
		ventasDetalle.setListaPrecio(null);

		return ventasDetalle;
	}
	
	public List<VentasDetalleUnidad> getVentasDetalleUnidads() {
		return this.ventasDetalleUnidads;
	}

	public void setVentasDetalleUnidads(List<VentasDetalleUnidad> ventasDetalleUnidads) {
		this.ventasDetalleUnidads = ventasDetalleUnidads;
	}

	public VentasDetalleUnidad addVentasDetalleUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		getVentasDetalleUnidads().add(ventasDetalleUnidad);
		ventasDetalleUnidad.setListaPrecio(this);

		return ventasDetalleUnidad;
	}

	public VentasDetalleUnidad removeVentasDetalleUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		getVentasDetalleUnidads().remove(ventasDetalleUnidad);
		ventasDetalleUnidad.setListaPrecio(null);

		return ventasDetalleUnidad;
	}

}