package ar.com.clases.auxiliares;

import java.io.Serializable;

public class Pagos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nroConsignacion;
	private String persona;
	private String fecha;
	private String monto;
	private String concepto;
	
	public String getNroConsignacion() {
		return nroConsignacion;
	}

	public void setNroConsignacion(String nroConsignacion) {
		this.nroConsignacion = nroConsignacion;
	}

	public String getPersona() {
		return persona;
	}
	
	public void setPersona(String persona) {
		this.persona = persona;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getMonto() {
		return monto;
	}
	
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
	public String getConcepto() {
		return concepto;
	}
	
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

}
