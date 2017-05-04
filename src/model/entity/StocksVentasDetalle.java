package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stocks_ventas_detalle database table.
 * 
 */
@Entity
@Table(name="stocks_ventas_detalle")
@NamedQuery(name="StocksVentasDetalle.findAll", query="SELECT s FROM StocksVentasDetalle s")
public class StocksVentasDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	//bi-directional many-to-one association to VentasDetalle
	@ManyToOne
	@JoinColumn(name="id_ventas_detalle")
	private VentasDetalle ventasDetalle;

	//bi-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumn(name="id_stock")
	private Stock stock;

	public StocksVentasDetalle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public VentasDetalle getVentasDetalle() {
		return this.ventasDetalle;
	}

	public void setVentasDetalle(VentasDetalle ventasDetalle) {
		this.ventasDetalle = ventasDetalle;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}