package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Compra;
import model.entity.ComprasDetalle;
import model.entity.Producto;

public interface DAOCompraDetalle {
	
	public int insertar(ComprasDetalle comprasDetalle);
	
	public int update(ComprasDetalle comprasDetalle);
	
	public ComprasDetalle get(int id);
	
	public List<ComprasDetalle> getLista();
	
	public List<ComprasDetalle> getLista(Compra compra);
	
	public List<ComprasDetalle> getLista(Producto producto);
	
	public ComprasDetalle get(Compra compra, Producto producto);
	
	public int deleteDetallePorCompra(Compra compra);
	
	public int deleteDetalleCompra(ComprasDetalle comprasDetalle);
	
	public List<Float> getListaPorRango(Producto producto, Date fechaInicio, Date fechaFin);

}
