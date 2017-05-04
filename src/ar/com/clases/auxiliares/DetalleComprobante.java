package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.util.List;

public class DetalleComprobante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	private String detalle;
	private String precioUnitario;
	private String subtotal;
	private List<DetalleComprobanteUnidad> listaUnidads;
	private String nroImei;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public List<DetalleComprobanteUnidad> getListaUnidads() {
		return listaUnidads;
	}

	public void setListaUnidads(List<DetalleComprobanteUnidad> listaUnidads) {
		this.listaUnidads = listaUnidads;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}
	
}
