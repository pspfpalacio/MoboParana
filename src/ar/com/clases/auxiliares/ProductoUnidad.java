package ar.com.clases.auxiliares;

import java.io.Serializable;

public class ProductoUnidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String marcaModelo;
	private int stock;
	private String nroImei;
	
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
	
	public String getNroImei() {
		return nroImei;
	}
	
	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

}
