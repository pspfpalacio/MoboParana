package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.util.List;

import model.entity.UnidadMovil;

public class StockDisponible implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cantidad;
	private float precio;
	private List<UnidadMovil> listaUnidadMovils;

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public List<UnidadMovil> getListaUnidadMovils() {
		return listaUnidadMovils;
	}
	public void setListaUnidadMovils(List<UnidadMovil> listaUnidadMovils) {
		this.listaUnidadMovils = listaUnidadMovils;
	}

}
