package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ventas_detalle database table.
 * 
 */
@Entity
@Table(name="ventas_detalle")
@NamedQuery(name="VentasDetalle.findAll", query="SELECT v FROM VentasDetalle v")
public class VentasDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean accesorio;

	private int cantidad;
	
	private boolean eliminado;
	
	@Column(name="nro_imei")
	private String nroImei;

	@Column(name="precio_compra")
	private float precioCompra;

	@Column(name="precio_venta")
	private float precioVenta;

	private float subtotal;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="id_venta")
	private Venta venta;

	//bi-directional many-to-one association to VentasDetalleUnidad
	@OneToMany(mappedBy="ventasDetalle")
	private List<VentasDetalleUnidad> ventasDetalleUnidads;
	
	//bi-directional many-to-one association to StocksVentasDetalle
	@OneToMany(mappedBy="ventasDetalle")
	private List<StocksVentasDetalle> stocksVentasDetalles;
	
	//bi-directional many-to-one association to ListaPrecio
	@ManyToOne
	@JoinColumn(name="id_lista_precio")
	private ListaPrecio listaPrecio;

	public VentasDetalle() {
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

	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<VentasDetalleUnidad> getVentasDetalleUnidads() {
		return this.ventasDetalleUnidads;
	}

	public void setVentasDetalleUnidads(List<VentasDetalleUnidad> ventasDetalleUnidads) {
		this.ventasDetalleUnidads = ventasDetalleUnidads;
	}

	public VentasDetalleUnidad addVentasDetalleUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		getVentasDetalleUnidads().add(ventasDetalleUnidad);
		ventasDetalleUnidad.setVentasDetalle(this);

		return ventasDetalleUnidad;
	}

	public VentasDetalleUnidad removeVentasDetalleUnidad(VentasDetalleUnidad ventasDetalleUnidad) {
		getVentasDetalleUnidads().remove(ventasDetalleUnidad);
		ventasDetalleUnidad.setVentasDetalle(null);

		return ventasDetalleUnidad;
	}
	
	public List<StocksVentasDetalle> getStocksVentasDetalles() {
		return this.stocksVentasDetalles;
	}

	public void setStocksVentasDetalles(List<StocksVentasDetalle> stocksVentasDetalles) {
		this.stocksVentasDetalles = stocksVentasDetalles;
	}

	public StocksVentasDetalle addStocksVentasDetalle(StocksVentasDetalle stocksVentasDetalle) {
		getStocksVentasDetalles().add(stocksVentasDetalle);
		stocksVentasDetalle.setVentasDetalle(this);

		return stocksVentasDetalle;
	}

	public StocksVentasDetalle removeStocksVentasDetalle(StocksVentasDetalle stocksVentasDetalle) {
		getStocksVentasDetalles().remove(stocksVentasDetalle);
		stocksVentasDetalle.setVentasDetalle(null);

		return stocksVentasDetalle;
	}
	
	public ListaPrecio getListaPrecio() {
		return this.listaPrecio;
	}

	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}
	
	public Integer getProductoId() {
		int idProducto = producto.getId();
		return idProducto;
	}
	
	public String getProductoString(){
		String nombre = producto.getNombre();
		return nombre;
	}

}