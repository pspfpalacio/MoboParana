package ar.com.clases.reportes;

import java.io.Serializable;
import java.text.DecimalFormat;

import model.entity.Producto;
import model.entity.VentasDetalle;

public class GananciaDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VentasDetalle ventaDetalle;
	private Producto producto;
	private int cantidad;
	private float monto;
	private float costo;
	private float ganancia;

	public VentasDetalle getVentaDetalle() {
		return ventaDetalle;
	}
	
	public void setVentaDetalle(VentasDetalle ventaDetalle) {
		this.ventaDetalle = ventaDetalle;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");
		String valor = formatoMonto.format(costo);
		return valor;
	}
	
	public String getNombreProducto(){
		String nombre = producto.getNombre();
		return nombre;
	}
}
