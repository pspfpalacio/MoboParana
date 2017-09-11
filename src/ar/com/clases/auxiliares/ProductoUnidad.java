package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.util.List;

import model.entity.UnidadMovil;

public class ProductoUnidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String marcaModelo;
	private int stock;
	private int consignacion;
	private List<UnidadMovil> enStocks;
	private List<UnidadMovil> enConsignacions;	
	
	public String getMarcaModelo() {
		return marcaModelo;
	}
	
	public void setMarcaModelo(String marcaModelo) {
		this.marcaModelo = marcaModelo;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(int consignacion) {
		this.consignacion = consignacion;
	}

	public List<UnidadMovil> getEnStocks() {
		return enStocks;
	}

	public void setEnStocks(List<UnidadMovil> enStocks) {
		this.enStocks = enStocks;
	}

	public List<UnidadMovil> getEnConsignacions() {
		return enConsignacions;
	}

	public void setEnConsignacions(List<UnidadMovil> enConsignacions) {
		this.enConsignacions = enConsignacions;
	}

}
