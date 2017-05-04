package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Garantias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private Date fechaIngreso;
	private String imeiFalla;
	private String producto;
	private String persona;
	private String concepto;
	private String fallaInicial;
	private float costo;
	private String resolucion;
	private String imeiCambio;
	private String accion;
	private String fallaFinal;
	private String observaciones;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getImeiFalla() {
		return imeiFalla;
	}

	public void setImeiFalla(String imeiFalla) {
		this.imeiFalla = imeiFalla;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getFallaInicial() {
		return fallaInicial;
	}

	public void setFallaInicial(String fallaInicial) {
		this.fallaInicial = fallaInicial;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getImeiCambio() {
		return imeiCambio;
	}

	public void setImeiCambio(String imeiCambio) {
		this.imeiCambio = imeiCambio;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getFallaFinal() {
		return fallaFinal;
	}

	public void setFallaFinal(String fallaFinal) {
		this.fallaFinal = fallaFinal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public String getFechaIngresoString() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = formato.format(fechaIngreso);
			return fecha;
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getCostoString() {
		try {
			DecimalFormat formato = new DecimalFormat("##,##0.00");
			String monto = formato.format(costo);
			return monto;
		} catch (Exception e) {
			return "";
		}		
	}

}
