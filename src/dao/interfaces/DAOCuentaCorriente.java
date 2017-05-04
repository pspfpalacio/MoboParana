package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Proveedore;

public interface DAOCuentaCorriente {
	
	public int insertar(CuentasCorrientesCliente cuentasCorrientesCliente);
	
	public int update(CuentasCorrientesCliente cuentasCorrientesCliente);
	
	//public int delete(int id);
	
	public CuentasCorrientesCliente get(int id);
	
	public List<CuentasCorrientesCliente> getLista();
	
	public List<CuentasCorrientesCliente> getLista(Cliente cliente);
	
	public List<CuentasCorrientesCliente> getLista(Cliente cliente, Date fechaInicio, Date fechaFin);
	
	public int insertar(CuentasCorrientesProveedore cuentasCorrientesProveedore);
	
	public int update(CuentasCorrientesProveedore cuentasCorrientesProveedore);
	
	public CuentasCorrientesProveedore getProveedor(int id);
	
	public List<CuentasCorrientesProveedore> getListaProveedor();
	
	public List<CuentasCorrientesProveedore> getListaProveedor(Proveedore proveedore);
	
	public List<CuentasCorrientesProveedore> getListaProveedor(Proveedore proveedore, Date fechaInicio, Date fechaFin);
	
	public List<CuentasCorrientesCliente> getListaOrdenadaPorFechaCliente(Date fechaInicio, Date fechaFin, Cliente cliente);
	
	public List<CuentasCorrientesCliente> getListaOrdenadaPorFechaCliente(Date fechaInicio, Cliente cliente);
	
	public List<CuentasCorrientesProveedore> getListaOrdenadaPorFechaProveedor(Date fechaInicio, Date fechaFin, Proveedore proveedore);
	
	public List<CuentasCorrientesProveedore> getListaOrdenadaPorFechaProveedor(Date fechaInicio, Proveedore proveedore);
	
	public CuentasCorrientesCliente get(int idMovimiento, String nombreTabla);
	
	public List<CuentasCorrientesCliente> getLista(int idMovimiento, String nombreTabla);
	
	public CuentasCorrientesProveedore getProveedor(int idMovimiento, String nombreTabla);
	
	public int deletePorMovimientoCliente(int idMovimiento, String nombreTabla, Cliente cliente);
	
	public int deletePorMovimientoProveedor(int idMovimiento, String nombreTabla, Proveedore proveedor);
	
	public List<CuentasCorrientesProveedore> getListaProveedorOrdenadaPorFecha(Proveedore proveedore);
	
	public List<CuentasCorrientesCliente> getListaClienteOrdenadaPorFecha(Cliente cliente);

}
