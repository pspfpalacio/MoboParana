package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Compra;
import model.entity.Producto;
import model.entity.Proveedore;

public interface DAOCompra {
	
	public int insertar(Compra compra);
	
	public int update(Compra compra);
	
	public Compra get(int id);
	
	public List<Compra> getListaSinOrden();
	
	public List<Compra> getLista();
	
	public List<Compra> getLista(boolean estado);
	
	public List<Compra> getLista(Proveedore proveedor);
	
	public List<Compra> getLista(Date fechaInicio, Date fechaFin);
	
	public List<Compra> getLista(boolean estado, Proveedore proveedore);
	
	public List<Compra> getLista(boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getLista(Proveedore proveedore, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getLista(boolean estado, Proveedore proveedore, Date fechaInicio, Date fechaFin);	
	
	public List<Compra> getListaOrderFecha(boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderMonto(boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderFecha(boolean estado, List<Proveedore> proveedores, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderMonto(boolean estado, List<Proveedore> proveedores, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderFecha(Producto producto, boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderMonto(Producto producto, boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderFecha(Producto producto, List<Proveedore> proveedores, boolean estado, Date fechaInicio, Date fechaFin);
	
	public List<Compra> getListaOrderMonto(Producto producto, List<Proveedore> proveedores, boolean estado, Date fechaInicio, Date fechaFin);

}
