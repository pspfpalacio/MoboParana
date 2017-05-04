package ar.com.clases.reportes;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import model.entity.Compra;
import model.entity.Proveedore;

public class RankingProveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Proveedore proveedor;
	private float monto;
	private int cantidad;
	private List<Compra> listaCompras;

	public Proveedore getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedore proveedor) {
		this.proveedor = proveedor;
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

	public List<Compra> getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(List<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}
	
	public String getMontoString(){
		DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");
		String valor = formatoMonto.format(monto);
		return valor;
	}

	public String getNombreProveedor(){
		String nombre = proveedor.getNombreNegocio();
		return nombre;
	}

}
