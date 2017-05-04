package ar.com.clases.reportes;

import java.io.Serializable;

public class VentasDetalleRanking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	private String producto;
	private float precioVenta;
	private float subtotal;
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getProducto() {
		return producto;
	}
	
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	public float getPrecioVenta() {
		return precioVenta;
	}
	
	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	public float getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}
