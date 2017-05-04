package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.PagosCliente;
import model.entity.PagosProveedore;
import model.entity.Proveedore;

public interface DAOPago {
	
	public int insertar(PagosCliente pagosCliente);
	
	public int update(PagosCliente pagosCliente);
	
	public PagosCliente getPagoCliente(int id);
	
	public List<PagosCliente> getListaPagosCliente();
	
	public List<PagosCliente> getListaPagosCliente(Cliente cliente);
	
	public List<PagosCliente> getListaPagosCliente(Date fechaInicio, Date fechaFin);
	
	public List<PagosCliente> getListaPagosCliente(Cliente cliente, Date fechaInicio, Date fechaFin);
	
	public int insertar(PagosProveedore pagosProveedore);
	
	public int update(PagosProveedore pagosProveedore);
	
	public PagosProveedore getPagoProveedore(int id);
	
	public List<PagosProveedore> getListaPagosProveedore();
	
	public List<PagosProveedore> getListaPagosProveedore(Proveedore proveedore);
	
	public List<PagosProveedore> getListaPagosProveedore(Date fechaInicio, Date fechaFin);
	
	public List<PagosProveedore> getListaPagosProveedore(Proveedore proveedore, Date fechaInicio, Date fechaFin);

}
