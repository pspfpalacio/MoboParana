package ar.com.clases.reportes;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import model.entity.Producto;

public class RankingProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Producto producto;
	private float monto;
	private float costo;
	private float ganancia;
	private int cantidad;
	private List<VentasRanking> listaVentas;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getGanancia() {
		return ganancia;
	}

	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<VentasRanking> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<VentasRanking> listaVentas) {
		this.listaVentas = listaVentas;
	}
	
	public String getMontoString(){
		DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");
		String valor = formatoMonto.format(monto);
		return valor;
	}
	
	public String getGananciaString(){
		DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");
		String valor = formatoMonto.format(ganancia);
		return valor;
	}
	
	public String getCostoString(){
		DecimalFormat formatoMonto = new DecimalFormat("###,#0.00");
		String valor = formatoMonto.format(costo);
		return valor;
	}
	
	public String getProductoString(){
		String nombre = producto.getNombre();
		return nombre;
	}
	
	public String getRubroString(){
		String nombre = producto.getRubro().getNombre();
		return nombre;
	}

}
