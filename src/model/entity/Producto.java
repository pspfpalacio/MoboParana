package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="costo_promedio")
	private float costoPromedio;

	private String descripcion;
	
	@Column(name="en_consignacion")
	private int enConsignacion;

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

	private String marca;

	private String modelo;

	private String nombre;

	@Column(name="precio_costo")
	private float precioCosto;

	private int stock;
	
	@Column(name="stock_minimo")
	private int stockMinimo;

	//bi-directional many-to-one association to ComprasDetalle
	@OneToMany(mappedBy="producto")
	private List<ComprasDetalle> comprasDetalles;

	//bi-directional many-to-one association to ListaPrecioProducto
	@OneToMany(mappedBy="producto")
	private List<ListaPrecioProducto> listaPrecioProductos;

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

	//bi-directional many-to-one association to UnidadMovil
	@OneToMany(mappedBy="producto")
	private List<UnidadMovil> unidadMovils;

	//bi-directional many-to-one association to ConsignacionsDetalle
	@OneToMany(mappedBy="producto")
	private List<ConsignacionsDetalle> consignacionsDetalles;
	
	//bi-directional many-to-one association to ConsignacionsDetalleUnidad
	@OneToMany(mappedBy="producto")
	private List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads;

	//bi-directional many-to-one association to Rubro
	@ManyToOne
	@JoinColumn(name="id_rubro")
	private Rubro rubro;

	//bi-directional many-to-one association to VentasDetalle
	@OneToMany(mappedBy="producto")
	private List<VentasDetalle> ventasDetalles;
	
	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="producto")
	private List<Stock> stocks;
	
	//bi-directional many-to-one association to VentasConsDetalle
	@OneToMany(mappedBy="producto")
	private List<VentasConsDetalle> ventasConsDetalles;
	
	//bi-directional many-to-one association to GarantiasCliente
	@OneToMany(mappedBy="producto1")
	private List<GarantiasCliente> garantiasClientes1;

	//bi-directional many-to-one association to GarantiasCliente
	@OneToMany(mappedBy="producto2")
	private List<GarantiasCliente> garantiasClientes2;
	
	//bi-directional many-to-one association to GarantiasProveedore
	@OneToMany(mappedBy="producto1")
	private List<GarantiasProveedore> garantiasProveedores1;

	//bi-directional many-to-one association to GarantiasProveedore
	@OneToMany(mappedBy="producto2")
	private List<GarantiasProveedore> garantiasProveedores2;
	
	//bi-directional many-to-one association to EConsignacionsDetalle
	@OneToMany(mappedBy="producto")
	private List<EConsignacionsDetalle> EConsignacionsDetalles;

	//bi-directional many-to-one association to EConsignacionsDetalleUnidad
	@OneToMany(mappedBy="producto")
	private List<EConsignacionsDetalleUnidad> EConsignacionsDetalleUnidads;

	public Producto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public float getCostoPromedio() {
		return this.costoPromedio;
	}

	public void setCostoPromedio(float costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getEnConsignacion() {
		return this.enConsignacion;
	}

	public void setEnConsignacion(int enConsignacion) {
		this.enConsignacion = enConsignacion;
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

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecioCosto() {
		return this.precioCosto;
	}

	public void setPrecioCosto(float precioCosto) {
		this.precioCosto = precioCosto;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public List<ComprasDetalle> getComprasDetalles() {
		return this.comprasDetalles;
	}

	public void setComprasDetalles(List<ComprasDetalle> comprasDetalles) {
		this.comprasDetalles = comprasDetalles;
	}

	public ComprasDetalle addComprasDetalle(ComprasDetalle comprasDetalle) {
		getComprasDetalles().add(comprasDetalle);
		comprasDetalle.setProducto(this);

		return comprasDetalle;
	}

	public ComprasDetalle removeComprasDetalle(ComprasDetalle comprasDetalle) {
		getComprasDetalles().remove(comprasDetalle);
		comprasDetalle.setProducto(null);

		return comprasDetalle;
	}

	public List<ListaPrecioProducto> getListaPrecioProductos() {
		return this.listaPrecioProductos;
	}

	public void setListaPrecioProductos(List<ListaPrecioProducto> listaPrecioProductos) {
		this.listaPrecioProductos = listaPrecioProductos;
	}

	public ListaPrecioProducto addListaPrecioProducto(ListaPrecioProducto listaPrecioProducto) {
		getListaPrecioProductos().add(listaPrecioProducto);
		listaPrecioProducto.setProducto(this);

		return listaPrecioProducto;
	}

	public ListaPrecioProducto removeListaPrecioProducto(ListaPrecioProducto listaPrecioProducto) {
		getListaPrecioProductos().remove(listaPrecioProducto);
		listaPrecioProducto.setProducto(null);

		return listaPrecioProducto;
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

	public List<UnidadMovil> getUnidadMovils() {
		return this.unidadMovils;
	}

	public void setUnidadMovils(List<UnidadMovil> unidadMovils) {
		this.unidadMovils = unidadMovils;
	}

	public UnidadMovil addUnidadMovil(UnidadMovil unidadMovil) {
		getUnidadMovils().add(unidadMovil);
		unidadMovil.setProducto(this);

		return unidadMovil;
	}

	public UnidadMovil removeUnidadMovil(UnidadMovil unidadMovil) {
		getUnidadMovils().remove(unidadMovil);
		unidadMovil.setProducto(null);

		return unidadMovil;
	}

	public List<ConsignacionsDetalle> getConsignacionsDetalles() {
		return this.consignacionsDetalles;
	}

	public void setConsignacionsDetalles(List<ConsignacionsDetalle> consignacionsDetalles) {
		this.consignacionsDetalles = consignacionsDetalles;
	}

	public ConsignacionsDetalle addConsignacionsDetalle(ConsignacionsDetalle consignacionsDetalle) {
		getConsignacionsDetalles().add(consignacionsDetalle);
		consignacionsDetalle.setProducto(this);

		return consignacionsDetalle;
	}

	public ConsignacionsDetalle removeConsignacionsDetalle(ConsignacionsDetalle consignacionsDetalle) {
		getConsignacionsDetalles().remove(consignacionsDetalle);
		consignacionsDetalle.setProducto(null);

		return consignacionsDetalle;
	}
	
	public List<ConsignacionsDetalleUnidad> getConsignacionsDetalleUnidads() {
		return this.consignacionsDetalleUnidads;
	}

	public void setConsignacionsDetalleUnidads(List<ConsignacionsDetalleUnidad> consignacionsDetalleUnidads) {
		this.consignacionsDetalleUnidads = consignacionsDetalleUnidads;
	}

	public ConsignacionsDetalleUnidad addConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().add(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setProducto(this);

		return consignacionsDetalleUnidad;
	}

	public ConsignacionsDetalleUnidad removeConsignacionsDetalleUnidad(ConsignacionsDetalleUnidad consignacionsDetalleUnidad) {
		getConsignacionsDetalleUnidads().remove(consignacionsDetalleUnidad);
		consignacionsDetalleUnidad.setProducto(null);

		return consignacionsDetalleUnidad;
	}

	public Rubro getRubro() {
		return this.rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public List<VentasDetalle> getVentasDetalles() {
		return this.ventasDetalles;
	}

	public void setVentasDetalles(List<VentasDetalle> ventasDetalles) {
		this.ventasDetalles = ventasDetalles;
	}

	public VentasDetalle addVentasDetalle(VentasDetalle ventasDetalle) {
		getVentasDetalles().add(ventasDetalle);
		ventasDetalle.setProducto(this);

		return ventasDetalle;
	}

	public VentasDetalle removeVentasDetalle(VentasDetalle ventasDetalle) {
		getVentasDetalles().remove(ventasDetalle);
		ventasDetalle.setProducto(null);

		return ventasDetalle;
	}
	
	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setProducto(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setProducto(null);

		return stock;
	}
	
	public List<VentasConsDetalle> getVentasConsDetalles() {
		return this.ventasConsDetalles;
	}

	public void setVentasConsDetalles(List<VentasConsDetalle> ventasConsDetalles) {
		this.ventasConsDetalles = ventasConsDetalles;
	}

	public VentasConsDetalle addVentasConsDetalle(VentasConsDetalle ventasConsDetalle) {
		getVentasConsDetalles().add(ventasConsDetalle);
		ventasConsDetalle.setProducto(this);

		return ventasConsDetalle;
	}

	public VentasConsDetalle removeVentasConsDetalle(VentasConsDetalle ventasConsDetalle) {
		getVentasConsDetalles().remove(ventasConsDetalle);
		ventasConsDetalle.setProducto(null);

		return ventasConsDetalle;
	}
	
	public List<GarantiasCliente> getGarantiasClientes1() {
		return this.garantiasClientes1;
	}

	public void setGarantiasClientes1(List<GarantiasCliente> garantiasClientes1) {
		this.garantiasClientes1 = garantiasClientes1;
	}

	public GarantiasCliente addGarantiasClientes1(GarantiasCliente garantiasClientes1) {
		getGarantiasClientes1().add(garantiasClientes1);
		garantiasClientes1.setProducto1(this);

		return garantiasClientes1;
	}

	public GarantiasCliente removeGarantiasClientes1(GarantiasCliente garantiasClientes1) {
		getGarantiasClientes1().remove(garantiasClientes1);
		garantiasClientes1.setProducto1(null);

		return garantiasClientes1;
	}

	public List<GarantiasCliente> getGarantiasClientes2() {
		return this.garantiasClientes2;
	}

	public void setGarantiasClientes2(List<GarantiasCliente> garantiasClientes2) {
		this.garantiasClientes2 = garantiasClientes2;
	}

	public GarantiasCliente addGarantiasClientes2(GarantiasCliente garantiasClientes2) {
		getGarantiasClientes2().add(garantiasClientes2);
		garantiasClientes2.setProducto2(this);

		return garantiasClientes2;
	}

	public GarantiasCliente removeGarantiasClientes2(GarantiasCliente garantiasClientes2) {
		getGarantiasClientes2().remove(garantiasClientes2);
		garantiasClientes2.setProducto2(null);

		return garantiasClientes2;
	}
	
	public List<GarantiasProveedore> getGarantiasProveedores1() {
		return this.garantiasProveedores1;
	}

	public void setGarantiasProveedores1(List<GarantiasProveedore> garantiasProveedores1) {
		this.garantiasProveedores1 = garantiasProveedores1;
	}

	public GarantiasProveedore addGarantiasProveedores1(GarantiasProveedore garantiasProveedores1) {
		getGarantiasProveedores1().add(garantiasProveedores1);
		garantiasProveedores1.setProducto1(this);

		return garantiasProveedores1;
	}

	public GarantiasProveedore removeGarantiasProveedores1(GarantiasProveedore garantiasProveedores1) {
		getGarantiasProveedores1().remove(garantiasProveedores1);
		garantiasProveedores1.setProducto1(null);

		return garantiasProveedores1;
	}

	public List<GarantiasProveedore> getGarantiasProveedores2() {
		return this.garantiasProveedores2;
	}

	public void setGarantiasProveedores2(List<GarantiasProveedore> garantiasProveedores2) {
		this.garantiasProveedores2 = garantiasProveedores2;
	}

	public GarantiasProveedore addGarantiasProveedores2(GarantiasProveedore garantiasProveedores2) {
		getGarantiasProveedores2().add(garantiasProveedores2);
		garantiasProveedores2.setProducto2(this);

		return garantiasProveedores2;
	}

	public GarantiasProveedore removeGarantiasProveedores2(GarantiasProveedore garantiasProveedores2) {
		getGarantiasProveedores2().remove(garantiasProveedores2);
		garantiasProveedores2.setProducto2(null);

		return garantiasProveedores2;
	}
	
	public List<EConsignacionsDetalle> getEConsignacionsDetalles() {
		return this.EConsignacionsDetalles;
	}

	public void setEConsignacionsDetalles(List<EConsignacionsDetalle> EConsignacionsDetalles) {
		this.EConsignacionsDetalles = EConsignacionsDetalles;
	}

	public EConsignacionsDetalle addEConsignacionsDetalle(EConsignacionsDetalle EConsignacionsDetalle) {
		getEConsignacionsDetalles().add(EConsignacionsDetalle);
		EConsignacionsDetalle.setProducto(this);

		return EConsignacionsDetalle;
	}

	public EConsignacionsDetalle removeEConsignacionsDetalle(EConsignacionsDetalle EConsignacionsDetalle) {
		getEConsignacionsDetalles().remove(EConsignacionsDetalle);
		EConsignacionsDetalle.setProducto(null);

		return EConsignacionsDetalle;
	}

	public List<EConsignacionsDetalleUnidad> getEConsignacionsDetalleUnidads() {
		return this.EConsignacionsDetalleUnidads;
	}

	public void setEConsignacionsDetalleUnidads(List<EConsignacionsDetalleUnidad> EConsignacionsDetalleUnidads) {
		this.EConsignacionsDetalleUnidads = EConsignacionsDetalleUnidads;
	}

	public EConsignacionsDetalleUnidad addEConsignacionsDetalleUnidad(EConsignacionsDetalleUnidad EConsignacionsDetalleUnidad) {
		getEConsignacionsDetalleUnidads().add(EConsignacionsDetalleUnidad);
		EConsignacionsDetalleUnidad.setProducto(this);

		return EConsignacionsDetalleUnidad;
	}

	public EConsignacionsDetalleUnidad removeEConsignacionsDetalleUnidad(EConsignacionsDetalleUnidad EConsignacionsDetalleUnidad) {
		getEConsignacionsDetalleUnidads().remove(EConsignacionsDetalleUnidad);
		EConsignacionsDetalleUnidad.setProducto(null);

		return EConsignacionsDetalleUnidad;
	}

}