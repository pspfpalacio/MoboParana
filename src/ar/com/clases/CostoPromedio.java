package ar.com.clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.Parametro;
import model.entity.Producto;
import dao.impl.DAOCompraDetalleImpl;
import dao.impl.DAOParametroImpl;
import dao.impl.DAOProductoImpl;
import dao.interfaces.DAOCompraDetalle;
import dao.interfaces.DAOParametro;
import dao.interfaces.DAOProducto;

public class CostoPromedio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DAOProducto productoDAO = new DAOProductoImpl();
	private DAOCompraDetalle compraDetalleDAO = new DAOCompraDetalleImpl();
	private DAOParametro parametroDAO = new DAOParametroImpl();

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOCompraDetalle getCompraDetalleDAO() {
		return compraDetalleDAO;
	}

	public void setCompraDetalleDAO(DAOCompraDetalle compraDetalleDAO) {
		this.compraDetalleDAO = compraDetalleDAO;
	}
	
	public DAOParametro getParametroDAO() {
		return parametroDAO;
	}

	public void setParametroDAO(DAOParametro parametroDAO) {
		this.parametroDAO = parametroDAO;
	}

	public List<Producto> calculaCostoPromedio(List<Producto> listaProductos) {
		try {
			System.out.println("CostoPromedio lista");
			Parametro parametro = parametroDAO.get(1);
			Date fechaInicio = new Date();
			Date fechaFin = new Date();
			int mes = fechaInicio.getMonth();
			mes = mes - parametro.getCantMesesCp();
			fechaInicio.setMonth(mes);
			List<Producto> listaProd = new ArrayList<Producto>();
			for (Producto producto : listaProductos) {
				float costoPromedio = 0;
				int cantidad = 0;
				List<Float> listaPrecios = compraDetalleDAO.getListaPorRango(producto, fechaInicio, fechaFin);
				for (Float costo : listaPrecios) {
					costoPromedio = costoPromedio + costo;
					cantidad = cantidad + 1;
				}
				float costoFinal = Math.round(costoPromedio / cantidad);
				if (costoFinal == 0) {
					costoFinal = producto.getPrecioCosto();
				}
				producto.setCostoPromedio(costoFinal);
				listaProd.add(producto);
			}
			return listaProd;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return listaProductos;
		}
	}
	
	public Producto calculaCostoPromedio(Producto producto, Date fechaFin) {
		try {
			System.out.println("CostoPromedio producto, fecha");
			Parametro parametro = parametroDAO.get(1);
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fInicio = formatoFecha.format(fechaFin);
			Date fechaInicio = formatoFecha.parse(fInicio);
			int mes = fechaInicio.getMonth();
			mes = mes - parametro.getCantMesesCp();
			fechaInicio.setMonth(mes);			
			float costoPromedio = 0;
			int cantidad = 0;
			List<Float> listaPrecios = compraDetalleDAO.getListaPorRango(producto, fechaInicio, fechaFin);
			for (Float costo : listaPrecios) {
				costoPromedio = costoPromedio + costo;
				cantidad = cantidad + 1;
			}
			float costoFinal = Math.round(costoPromedio / cantidad);
			producto.setCostoPromedio(costoFinal);			
			return producto;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new Producto();
		}
	}
	
	public float calculaCostoPromedioFloat(Producto producto, Date fechaFin) {
		try {
			System.out.println("CostoPromedio producto, fecha");
			Parametro parametro = parametroDAO.get(1);
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fInicio = formatoFecha.format(fechaFin);
			Date fechaInicio = formatoFecha.parse(fInicio);
			int mes = fechaInicio.getMonth();
			mes = mes - parametro.getCantMesesCp();
			fechaInicio.setMonth(mes);			
			float costoPromedio = 0;
			int cantidad = 0;
			List<Float> listaPrecios = compraDetalleDAO.getListaPorRango(producto, fechaInicio, fechaFin);
			for (Float costo : listaPrecios) {
				costoPromedio = costoPromedio + costo;
				cantidad = cantidad + 1;
			}
			float costoFinal = Math.round(costoPromedio / cantidad);
			return costoFinal;
		} catch (Exception e) {
			e.printStackTrace();
			float retorno = 0;
			return retorno;
		}
	}

}
