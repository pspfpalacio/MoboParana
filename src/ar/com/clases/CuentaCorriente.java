package ar.com.clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.impl.DAOClienteImpl;
import dao.impl.DAOCuentaCorrienteImpl;
import dao.impl.DAOProveedorImpl;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOProveedor;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuentasCorrientesProveedore;
import model.entity.Proveedore;

public class CuentaCorriente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DAOCuentaCorriente cuentaCorrienteDAO = new DAOCuentaCorrienteImpl();
	private DAOProveedor proveedorDAO = new DAOProveedorImpl();
	private DAOCliente clienteDAO = new DAOClienteImpl();
	
	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public DAOProveedor getProveedorDAO() {
		return proveedorDAO;
	}

	public void setProveedorDAO(DAOProveedor proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public int insertarCC(CuentasCorrientesCliente cuenta){
		try{
			List<CuentasCorrientesCliente> lista = cuentaCorrienteDAO.getListaClienteOrdenadaPorFecha(cuenta.getCliente());
			boolean empty = lista.isEmpty();
			if (empty) {
				Cliente cliente = cuenta.getCliente();
				float saldoCliente = cliente.getSaldo();
				if(cuenta.getDebe() != 0){
					saldoCliente = saldoCliente + cuenta.getDebe();
				}
				if(cuenta.getHaber() != 0){
					saldoCliente = saldoCliente - cuenta.getHaber();
				}
				cuenta.setSaldo(saldoCliente);
				cliente.setSaldo(saldoCliente);
				int inserto = cuentaCorrienteDAO.insertar(cuenta);
				int update = clienteDAO.update(cliente);
				if (inserto != 0 && update != 0) {
					return 1;
				} else {
					return 0;
				}
			} else {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				List<CuentasCorrientesCliente> listPosterior = new ArrayList<CuentasCorrientesCliente>();
				CuentasCorrientesCliente cuentaAnterior = new CuentasCorrientesCliente();
				Cliente cliente = cuenta.getCliente();
				for (CuentasCorrientesCliente cuentasCorrientesCliente : lista) {
					String inicio = formato.format(cuentasCorrientesCliente.getFecha());
					Date fechaCuenta2 = formato.parse(inicio);
					fechaCuenta2.setHours(0);
					fechaCuenta2.setMinutes(0);
					fechaCuenta2.setSeconds(0);
					int comparaFecha = fechaCuenta2.compareTo(cuenta.getFecha());
					if(comparaFecha > 0){
						listPosterior.add(cuentasCorrientesCliente);
					}else{
						cuentaAnterior = new CuentasCorrientesCliente();
						cuentaAnterior = cuentasCorrientesCliente;
					}
				}
				float saldo = cuentaAnterior.getSaldo();
				if(cuenta.getDebe() != 0){
					saldo = saldo + cuenta.getDebe();
				}
				if(cuenta.getHaber() != 0){
					saldo = saldo - cuenta.getHaber();
				}
				cuenta.setSaldo(saldo);
				int inserto = cuentaCorrienteDAO.insertar(cuenta);
				if (inserto != 0) {
					boolean actualizo = true;
					for (CuentasCorrientesCliente cuentasCorrientesCliente : listPosterior) {
						float sldo = cuentasCorrientesCliente.getSaldo();
						if(cuenta.getDebe() != 0){					
							sldo = sldo + cuenta.getDebe();
						}
						if(cuenta.getHaber() != 0){
							sldo = sldo - cuenta.getHaber();
						}
						cuentasCorrientesCliente.setSaldo(sldo);
						saldo = sldo;
						int updateCC = cuentaCorrienteDAO.update(cuentasCorrientesCliente);
						if (updateCC == 0) {
							actualizo = false;
						}
					}
					cliente.setSaldo(saldo);
					int update = clienteDAO.update(cliente);
					if (actualizo && update != 0) {
						return 1;
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int insertarCC(CuentasCorrientesProveedore cuenta){
		try{
			List<CuentasCorrientesProveedore> lista = cuentaCorrienteDAO.getListaProveedorOrdenadaPorFecha(cuenta.getProveedore());
			boolean empty = lista.isEmpty();
			if (empty) {
				Proveedore proveedor = cuenta.getProveedore();
				float saldoProveedor = proveedor.getSaldo();
				if(cuenta.getDebe() != 0){
					saldoProveedor = saldoProveedor + cuenta.getDebe();
				}
				if(cuenta.getHaber() != 0){
					saldoProveedor = saldoProveedor - cuenta.getHaber();
				}
				cuenta.setSaldo(saldoProveedor);
				proveedor.setSaldo(saldoProveedor);
				int inserto = cuentaCorrienteDAO.insertar(cuenta);
				int update = proveedorDAO.update(proveedor);
				if (inserto != 0 && update != 0) {
					return 1;
				} else {
					return 0;
				}
			} else {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				List<CuentasCorrientesProveedore> listPosterior = new ArrayList<CuentasCorrientesProveedore>();
				CuentasCorrientesProveedore cuentaAnterior = new CuentasCorrientesProveedore();
				Proveedore proveedor = cuenta.getProveedore();
				for (CuentasCorrientesProveedore cuentasCorrientesProveedore : lista) {
					String inicio = formato.format(cuentasCorrientesProveedore.getFecha());
					Date fechaCuenta2 = formato.parse(inicio);
					fechaCuenta2.setHours(0);
					fechaCuenta2.setMinutes(0);
					fechaCuenta2.setSeconds(0);
					int comparaFecha = fechaCuenta2.compareTo(cuenta.getFecha());
					if(comparaFecha > 0){
						listPosterior.add(cuentasCorrientesProveedore);
					}else{
						cuentaAnterior = new CuentasCorrientesProveedore();
						cuentaAnterior = cuentasCorrientesProveedore;
					}
				}
				float saldo = cuentaAnterior.getSaldo();
				if(cuenta.getDebe() != 0){
					saldo = saldo + cuenta.getDebe();
				}
				if(cuenta.getHaber() != 0){
					saldo = saldo - cuenta.getHaber();
				}
				cuenta.setSaldo(saldo);
				int inserto = cuentaCorrienteDAO.insertar(cuenta);
				if (inserto != 0) {
					boolean actualizo = true;
					for (CuentasCorrientesProveedore cuentasCorrientesProveedore : listPosterior) {
						float sldo = cuentasCorrientesProveedore.getSaldo();
						if(cuenta.getDebe() != 0){					
							sldo = sldo + cuenta.getDebe();
						}
						if(cuenta.getHaber() != 0){
							sldo = sldo - cuenta.getHaber();
						}
						cuentasCorrientesProveedore.setSaldo(sldo);
						saldo = sldo;
						int updateCC = cuentaCorrienteDAO.update(cuentasCorrientesProveedore);
						if (updateCC == 0) {
							actualizo = false;
						}
					}
					proveedor.setSaldo(saldo);
					int update = proveedorDAO.update(proveedor);
					if (actualizo && update != 0) {
						return 1;
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deleteCuentaCorriente(CuentasCorrientesCliente cuenta){
		try{
			int idMovimiento = cuenta.getIdMovimiento();
			String nombreTabla = cuenta.getNombreTabla();
			CuentasCorrientesCliente ccCliente = cuentaCorrienteDAO.get(idMovimiento, nombreTabla);
			float debe = ccCliente.getDebe();
			float haber = ccCliente.getHaber();
			Date fechaCC = ccCliente.getFecha();
			int idCli = ccCliente.getCliente().getId();
			Cliente cliente = clienteDAO.get(idCli);
			List<CuentasCorrientesCliente> listAux = cuentaCorrienteDAO.getListaOrdenadaPorFechaCliente(fechaCC, cliente);
			List<CuentasCorrientesCliente> listAux2 = new ArrayList<CuentasCorrientesCliente>();
			CuentasCorrientesCliente ultimaCuenta = new CuentasCorrientesCliente();
			float saldoCliente = 0;
			boolean pasoCuentaAnterior = false;
			for (CuentasCorrientesCliente cuenta2 : listAux) {
				if(cuenta2.getId() != ccCliente.getId()){
					saldoCliente = cuenta2.getSaldo();
					if(pasoCuentaAnterior){
						listAux2.add(cuenta2);
					}else{
						ultimaCuenta = cuenta2;
					}
				}else{
					pasoCuentaAnterior = true;
				}
			}
			boolean update = true;
			if (listAux2.isEmpty()) {
				if (ultimaCuenta.getId() != 0) {
					saldoCliente = ultimaCuenta.getSaldo();
				} else {
					if (debe != 0) {
						saldoCliente = cliente.getSaldo() - debe;
					}
					if (haber != 0) {
						saldoCliente = cliente.getSaldo() + haber;
					}
				}
				
			} else {
				for(CuentasCorrientesCliente cuenta2 : listAux2){
					float sldo = cuenta2.getSaldo();
					if(debe != 0){					
						sldo = sldo - ccCliente.getDebe();
					}
					if(haber != 0){
						sldo = sldo + ccCliente.getHaber();
					}
					cuenta2.setSaldo(sldo);
					saldoCliente = sldo;
					if(cuentaCorrienteDAO.update(cuenta2) == 0){
						update = false;
					}
				}
			}
			idMovimiento = ccCliente.getIdMovimiento();
			nombreTabla = ccCliente.getNombreTabla();
			int delete = cuentaCorrienteDAO.deletePorMovimientoCliente(idMovimiento, nombreTabla, cliente);
			cliente.setSaldo(saldoCliente);
			if(update && delete != 0){
				clienteDAO.update(cliente);
				return 1;
			}else{
				return 0;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deleteCuentaCorrientePorId(CuentasCorrientesCliente ccCliente){
		try{
			float debe = ccCliente.getDebe();
			float haber = ccCliente.getHaber();
			Date fechaCC = ccCliente.getFecha();
			int idCli = ccCliente.getCliente().getId();
			Cliente cliente = clienteDAO.get(idCli);
			List<CuentasCorrientesCliente> listAux = cuentaCorrienteDAO.getListaOrdenadaPorFechaCliente(fechaCC, cliente);
			List<CuentasCorrientesCliente> listAux2 = new ArrayList<CuentasCorrientesCliente>();
			CuentasCorrientesCliente ultimaCuenta = new CuentasCorrientesCliente();
			float saldoCliente = 0;
			boolean pasoCuentaAnterior = false;
			for (CuentasCorrientesCliente cuenta2 : listAux) {
				if(cuenta2.getId() != ccCliente.getId()){
					saldoCliente = cuenta2.getSaldo();
					if(pasoCuentaAnterior){
						listAux2.add(cuenta2);
					}else{
						ultimaCuenta = cuenta2;
					}
				}else{
					pasoCuentaAnterior = true;
				}
			}
			boolean update = true;
			if (listAux2.isEmpty()) {
				if (ultimaCuenta.getId() != 0) {
					saldoCliente = ultimaCuenta.getSaldo();
				} else {
					if (debe != 0) {
						saldoCliente = cliente.getSaldo() - debe;
					}
					if (haber != 0) {
						saldoCliente = cliente.getSaldo() + haber;
					}
				}
				
			} else {
				for(CuentasCorrientesCliente cuenta2 : listAux2){
					float sldo = cuenta2.getSaldo();
					if(debe != 0){					
						sldo = sldo - ccCliente.getDebe();
					}
					if(haber != 0){
						sldo = sldo + ccCliente.getHaber();
					}
					cuenta2.setSaldo(sldo);
					saldoCliente = sldo;
					if(cuentaCorrienteDAO.update(cuenta2) == 0){
						update = false;
					}
				}
			}
			int delete = cuentaCorrienteDAO.deleteMovimientoCliente(ccCliente.getId());
			cliente.setSaldo(saldoCliente);
			if(update && delete != 0){
				clienteDAO.update(cliente);
				return 1;
			}else{
				return 0;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deleteCuentaCorriente(CuentasCorrientesProveedore cuenta){
		try{
			int idMovimiento = cuenta.getIdMovimiento();
			String nombreTabla = cuenta.getNombreTabla();
			CuentasCorrientesProveedore ccProveedor = cuentaCorrienteDAO.getProveedor(idMovimiento, nombreTabla);
			float debe = ccProveedor.getDebe();
			float haber = ccProveedor.getHaber();
			Date fechaCC = ccProveedor.getFecha();
			int idProv = ccProveedor.getProveedore().getId();
			Proveedore proveedor = proveedorDAO.get(idProv);
			List<CuentasCorrientesProveedore> listAux = cuentaCorrienteDAO.getListaOrdenadaPorFechaProveedor(fechaCC, proveedor);
			List<CuentasCorrientesProveedore> listAux2 = new ArrayList<CuentasCorrientesProveedore>();
			CuentasCorrientesProveedore ultimaCuenta = new CuentasCorrientesProveedore();
			float saldoProveedor = 0;
			boolean pasoCuentaAnterior = false;
			for (CuentasCorrientesProveedore cuenta2 : listAux) {
				if(cuenta2.getId() != ccProveedor.getId()){
					if(pasoCuentaAnterior){
						saldoProveedor = cuenta2.getSaldo();
						listAux2.add(cuenta2);
					}else{
						ultimaCuenta = cuenta2;
					}
				}else{
					pasoCuentaAnterior = true;
				}
			}
			boolean update = true;
			if (listAux2.isEmpty()) {
				if (ultimaCuenta.getId() != 0) {
					saldoProveedor = ultimaCuenta.getSaldo();
				} else {
					if (debe != 0) {
						saldoProveedor = proveedor.getSaldo() - debe;
					}
					if (haber != 0) {
						saldoProveedor = proveedor.getSaldo() + haber;
					}
				}
			} else {
				for(CuentasCorrientesProveedore cuenta2 : listAux2){
					float sldo = cuenta2.getSaldo();
					if(debe != 0){					
						sldo = sldo - ccProveedor.getDebe();
					}
					if(haber != 0){
						sldo = sldo + ccProveedor.getHaber();
					}
					cuenta2.setSaldo(sldo);
					saldoProveedor = sldo;
					if(cuentaCorrienteDAO.update(cuenta2) == 0){
						update = false;
					}
				}
			}			
			idMovimiento = ccProveedor.getIdMovimiento();
			nombreTabla = ccProveedor.getNombreTabla();
			int delete = cuentaCorrienteDAO.deletePorMovimientoProveedor(idMovimiento, nombreTabla, proveedor);
			proveedor.setSaldo(saldoProveedor);
			if(update && delete != 0){
				proveedorDAO.update(proveedor);
				return 1;
			}else{
				return 0;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int procesoActualizaSaldoParticular(int idCliente) {
		int retorno = 1;
		Cliente cliente = clienteDAO.get(idCliente); 
		System.out.println("Cliente: " + cliente.getNombreNegocio());
		List<CuentasCorrientesCliente> listaCuenta = cuentaCorrienteDAO.getListaClienteOrdenadaPorFecha(cliente);
		float saldo = 0;
		System.out.println("Lista Cuenta: " + listaCuenta.size());
		for (CuentasCorrientesCliente cuenta : listaCuenta) {
			if(cuenta.getDebe() != 0){					
				saldo = saldo + cuenta.getDebe();
			}
			if(cuenta.getHaber() != 0){
				saldo = saldo - cuenta.getHaber();
			}
			cuenta.setSaldo(saldo);
			int updt = cuentaCorrienteDAO.update(cuenta);
			if (updt == 0) {
				retorno = 0;
			}
		}
		System.out.println("Saldo: " + saldo);
		cliente.setSaldo(saldo);
		int update = clienteDAO.update(cliente);
		if (update == 0) {
			retorno = 0;
		}
		return retorno;
	}
	
	public int procesoActualizaSaldos() {
		List<Cliente> listaClientes = clienteDAO.getLista();
		int retorno = 1;
		System.out.println("Lista Cliente: " + listaClientes.size());
		for (Cliente cliente : listaClientes) {
			System.out.println("Cliente: " + cliente.getNombreNegocio());
			List<CuentasCorrientesCliente> listaCuenta = cuentaCorrienteDAO.getListaClienteOrdenadaPorFecha(cliente);
			float saldo = 0;
			System.out.println("Lista Cuenta: " + listaCuenta.size());
			for (CuentasCorrientesCliente cuenta : listaCuenta) {
				if(cuenta.getDebe() != 0){					
					saldo = saldo + cuenta.getDebe();
				}
				if(cuenta.getHaber() != 0){
					saldo = saldo - cuenta.getHaber();
				}
				cuenta.setSaldo(saldo);
				int updt = cuentaCorrienteDAO.update(cuenta);
				if (updt == 0) {
					retorno = 0;
				}
			}
			System.out.println("Saldo: " + saldo);
			cliente.setSaldo(saldo);
			int update = clienteDAO.update(cliente);
			if (update == 0) {
				retorno = 0;
			}
		}
		List<Proveedore> listaProveedores = proveedorDAO.getLista();
		for (Proveedore proveedor : listaProveedores) {
			System.out.println("Proveedor: " + proveedor.getNombreNegocio());
			List<CuentasCorrientesProveedore> listaCuenta = cuentaCorrienteDAO.getListaProveedorOrdenadaPorFecha(proveedor);
			float saldo = 0;
			for (CuentasCorrientesProveedore cuenta : listaCuenta) {
				if(cuenta.getDebe() != 0){					
					saldo = saldo + cuenta.getDebe();
				}
				if(cuenta.getHaber() != 0){
					saldo = saldo - cuenta.getHaber();
				}
				cuenta.setSaldo(saldo);
				int updt = cuentaCorrienteDAO.update(cuenta);
				if (updt == 0) {
					retorno = 0;
				}
			}
			System.out.println("Saldo: " + saldo);
			proveedor.setSaldo(saldo);
			int update = proveedorDAO.update(proveedor);
			if (update == 0) {
				retorno = 0;
			}
		}
		return retorno;
	}

}
