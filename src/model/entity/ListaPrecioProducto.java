package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lista_precio_producto database table.
 * 
 */
@Entity
@Table(name="lista_precio_producto")
@NamedQuery(name="ListaPrecioProducto.findAll", query="SELECT l FROM ListaPrecioProducto l")
public class ListaPrecioProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private float porcentaje;

	@Column(name="precio_venta")
	private float precioVenta;

	//bi-directional many-to-one association to ListaPrecio
	@ManyToOne
	@JoinColumn(name="id_lista_precio")
	private ListaPrecio listaPrecio;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public ListaPrecioProducto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public float getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
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