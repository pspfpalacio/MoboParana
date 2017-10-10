package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.util.List;

import model.entity.Producto;
import model.entity.VentasDetalleUnidad;

public class DetalleVenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	private Producto producto;
	private float subtotal;
	private boolean accesorio;
	private boolean noBaja;
	private List<VentasDetalleUnidad> listaVentasDetalleUnidads;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public boolean isAccesorio() {
		return accesorio;
	}

	public void setAccesorio(boolean accesorio) {
		this.accesorio = accesorio;
	}

	public boolean isNoBaja() {
		return noBaja;
	}

	public void setNoBaja(boolean noBaja) {
		this.noBaja = noBaja;
	}

	public List<VentasDetalleUnidad> getListaVentasDetalleUnidads() {
		return listaVentasDetalleUnidads;
	}

	public void setListaVentasDetalleUnidads(
			List<VentasDetalleUnidad> listaVentasDetalleUnidads) {
		this.listaVentasDetalleUnidads = listaVentasDetalleUnidads;
	}

}
