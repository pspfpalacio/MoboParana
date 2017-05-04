package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.util.List;

public class StockMoviles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	private String producto;
	private String subtotal;
	private List<Moviles> listaMoviles;
	private String nroImei;
	private String fechaAlta;
	private String precioUnitario;

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

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public List<Moviles> getListaMoviles() {
		return listaMoviles;
	}

	public void setListaMoviles(List<Moviles> listaMoviles) {
		this.listaMoviles = listaMoviles;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

}
