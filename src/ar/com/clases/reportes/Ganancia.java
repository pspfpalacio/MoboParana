package ar.com.clases.reportes;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Ganancia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	private String cliente;
	private String clase;
	private String tipo;
	private int id;
	private int cantidad;
	private float monto;
	private float costo;
	private float ganancia;
	private List<VentasDetalleRanking> listaDetallesRanking;
	private List<GananciaDetalle> listaGananciaDetalle;

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
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public List<GananciaDetalle> getListaGananciaDetalle() {
		return listaGananciaDetalle;
	}

	public void setListaGananciaDetalle(List<GananciaDetalle> listaGananciaDetalle) {
		this.listaGananciaDetalle = listaGananciaDetalle;
	}
	
	public List<VentasDetalleRanking> getListaDetallesRanking() {
		return listaDetallesRanking;
	}

	public void setListaDetallesRanking(List<VentasDetalleRanking> listaDetallesRanking) {
		this.listaDetallesRanking = listaDetallesRanking;
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
	
	public String getFechaString() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String dateF = formato.format(fecha);
			return dateF;
		} catch (Exception e) {
			return "";
		}
	}

}
