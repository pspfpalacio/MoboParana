package ar.com.clases.auxiliares;

import java.io.Serializable;

public class Cuotas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nroConsignacion;
	private String nroVenta;
	private String cliente;
	private String equipo;
	private String nroImei;
	private String cuota;
	private String fechaVencimiento;
	private String monto;
	private String pago;
	private String fechaPago;
	private String cantCuotas;
	private String interes;

	public String getNroConsignacion() {
		return nroConsignacion;
	}
	public void setNroConsignacion(String nroConsignacion) {
		this.nroConsignacion = nroConsignacion;
	}
	public String getNroVenta() {
		return nroVenta;
	}
	public void setNroVenta(String nroVenta) {
		this.nroVenta = nroVenta;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getNroImei() {
		return nroImei;
	}
	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getPago() {
		return pago;
	}
	public void setPago(String pago) {
		this.pago = pago;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getCantCuotas() {
		return cantCuotas;
	}
	public void setCantCuotas(String cantCuotas) {
		this.cantCuotas = cantCuotas;
	}
	public String getInteres() {
		return interes;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}

}
