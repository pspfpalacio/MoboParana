package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.util.List;

public class Comprobante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;
	private String fecha;
	private String tipo;
	private String persona;
	private String monto;
	private String usuario;
	private List<DetalleComprobante> listaDetalles;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<DetalleComprobante> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<DetalleComprobante> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

}
