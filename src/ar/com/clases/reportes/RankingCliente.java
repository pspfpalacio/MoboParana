package ar.com.clases.reportes;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import model.entity.Cliente;

public class RankingCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private float monto;
	private int cantidad;
	private float ganancia;
//	private List<Venta> listaVentas;
	private List<VentasRanking> listaVentas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getGanancia() {
		return ganancia;
	}

	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}

	public List<VentasRanking> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<VentasRanking> listaVentas) {
		this.listaVentas = listaVentas;
	}
	
	public String getMontoString(){
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		String valor = formatoMonto.format(monto);
		return valor;
	}
	
	public String getGananciaString(){
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		String valor = formatoMonto.format(ganancia);
		return valor;
	}
	
	public String getNombreCliente(){
		String nombre = cliente.getNombreNegocio();
		return nombre;
	}

}
