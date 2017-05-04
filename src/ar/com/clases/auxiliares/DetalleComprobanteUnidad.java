package ar.com.clases.auxiliares;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleComprobanteUnidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vendido;
	private String nroImei;
	private String precioUnitario;
	private Date fechaAlta;
	private Date fechaVenta;
	private float precioVenta;

	public String getVendido() {
		return vendido;
	}

	public void setVendido(String vendido) {
		this.vendido = vendido;
	}

	public String getNroImei() {
		return nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getFechaString() {
		String fecha = "";
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			fecha = formato.format(fechaAlta);
		} catch (Exception e) {
			fecha = "";
		}
		return fecha;
	}
	
	public String getVentaString() {
		String fecha = "";
		try {
			if (vendido.equals("Si")) {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				fecha = formato.format(fechaVenta);
			} else {
				fecha = "";
			}
		} catch (Exception e) {
			fecha = "";
		}
		return fecha;
	}
	
	public String getPrecioString() {
		String precio = "";
		try {
			DecimalFormat formato = new DecimalFormat("##,##0.00");
			precio = formato.format(precioVenta);
		} catch (Exception e) {
			precio = "";
		}
		return precio;
	}

}
