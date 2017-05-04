package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ar.com.clases.CuentaCorriente;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Clientes;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.ListaPrecio;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOListaPrecio;

@ManagedBean
@SessionScoped
public class BeanCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	private List<Cliente> listaClientes;
	private List<Cliente> filteredClientes;
	private List<ListaPrecio> listaPrecios;
	private Cliente cliente;
	private Usuario usuario;
	private String headerText;
	private int estado;
	private int idListaPrecio;
	private float saldo;
	private float saldoAnterior;

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Cliente> getFilteredClientes() {
		return filteredClientes;
	}

	public void setFilteredClientes(List<Cliente> filteredClientes) {
		this.filteredClientes = filteredClientes;
	}

	public List<ListaPrecio> getListaPrecios() {
		listaPrecios = listaPrecioDAO.getLista();
		return listaPrecios;
	}

	public void setListaPrecios(List<ListaPrecio> listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getIdListaPrecio() {
		return idListaPrecio;
	}

	public void setIdListaPrecio(int idListaPrecio) {
		this.idListaPrecio = idListaPrecio;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(float saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public String goClientes(Usuario user){
		usuario = new Usuario();
		usuario = user;
		listaClientes = clienteDAO.getLista(true);
		filteredClientes = listaClientes;
		estado = 0;
		return "clientes";
	}
	
	public String goClienteNuevo(){
		headerText = "Cliente Nuevo";
		cliente = new Cliente();
		idListaPrecio = 0;
		return "cliente";
	}
	
	public String goClienteEditar(Cliente cli){
		headerText = "Modificar Cliente";
		cliente = new Cliente();
		idListaPrecio = cli.getListaPrecio().getId();
		cliente = cli;
		saldoAnterior = cli.getSaldo();
		saldo = saldoAnterior;
		return "cliente";
	}
	
	public void filtroClientes(){
		listaClientes = new ArrayList<Cliente>();
		filteredClientes = new ArrayList<Cliente>();
		if (idListaPrecio == 0) {
			listaClientes = clienteDAO.getLista(true);
		} else {
			ListaPrecio lista = new ListaPrecio();
			lista.setId(idListaPrecio);
			listaClientes = clienteDAO.getLista(lista);
		}
		filteredClientes = listaClientes;
	}
	
	public void alta(Cliente cli){
		FacesMessage msg = null;
		cli.setEstado(true);
		cli.setFechaAlta(new Date());
		cli.setUsuario1(usuario);
		if(clienteDAO.update(cli) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta de Cliente!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Alta el Cliente, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(Cliente cli){
		FacesMessage msg = null;
		cli.setEstado(false);
		cli.setFechaBaja(new Date());
		cli.setUsuario2(usuario);
		if(clienteDAO.update(cli) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Cliente!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de Baja el Cliente, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardarCliente(){
		FacesMessage msg = null;
		String retorno = "";
		if(!cliente.getApellidoNombre().isEmpty() && !cliente.getNombreNegocio().isEmpty() && idListaPrecio != 0){
			ListaPrecio listaPrecio = listaPrecioDAO.get(idListaPrecio);
			cliente.setListaPrecio(listaPrecio);
			int idCliente = 0;
			boolean update = false;
			if(cliente.getId() != 0){
				cliente.setFechaMod(new Date());
				cliente.setUsuario3(usuario);
				idCliente = clienteDAO.update(cliente);
				update = true;
			}else{
				cliente.setEstado(true);
				cliente.setFechaAlta(new Date());
				cliente.setUsuario1(usuario);
				idCliente = clienteDAO.insertar(cliente);
			}
			if(idCliente != 0){
				cliente.setId(idCliente);
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				if(update){
					if(saldoAnterior != saldo){
						ccCliente.setCliente(cliente);
						ccCliente.setDetalle("Modificación de Cuenta Corriente");
						ccCliente.setFecha(new Date());
						ccCliente.setUsuario(usuario);
						float sldo = saldo;
						float monto = 0;
						if(sldo > saldoAnterior){
							monto = sldo - saldoAnterior;
							ccCliente.setDebe(monto);
						}
						if(sldo < saldoAnterior){
							monto = saldoAnterior - sldo;
							ccCliente.setHaber(monto);
						}
						ccCliente.setMonto(monto);
						cuenta.insertarCC(ccCliente);
					}
				}else{
					if(saldo != 0){
						ccCliente.setCliente(cliente);
						ccCliente.setDetalle("Inicialización de Cuenta Corriente");
						ccCliente.setFecha(new Date());
						ccCliente.setUsuario(usuario);
						float sldo = saldo;
						if(sldo > 0){
							ccCliente.setDebe(sldo);
						}
						if(sldo < 0){
							sldo = sldo * (-1);
							ccCliente.setHaber(sldo);
						}
						ccCliente.setMonto(sldo);
						cuenta.insertarCC(ccCliente);
					}
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente guardado!", null);
				retorno = "clientes";
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al guardar el Cliente, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Apellido y Nombre, Nombre de Negocio y "
					+ "Lista de Precio no pueden estar vacíos!", null);
		}
		listaClientes = new ArrayList<Cliente>();
		filteredClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(true);
		filteredClientes = listaClientes;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarReporte(){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Clientes> listCliente = new ArrayList<Clientes>();
		for(Cliente cli : filteredClientes){
			Clientes clientes = new Clientes();
			clientes.setApellidoNombre(cli.getApellidoNombre());
			clientes.setDireccion(cli.getDireccion());
			clientes.setEmail(cli.getEmail());
			clientes.setListaPrecio(cli.getListaPrecio().getNombre());
			clientes.setNombreNegocio(cli.getNombreNegocio());
			clientes.setSaldo(formatoMonto.format(cli.getSaldo()));
			clientes.setTelefono(cli.getTelefono());
			listCliente.add(clientes);
		}
//		if(estado == 0){
//			parametros.put("estado", "Todos");
//		}
//		if(estado == 1){
//			parametros.put("estado", "Alta");
//		}
//		if(estado == 2){
//			parametros.put("estado", "Baja");
//		}
		if(idListaPrecio == 0){
			parametros.put("listaPrecio", "Todas");
		}else{
			ListaPrecio listaP = listaPrecioDAO.get(idListaPrecio);
			parametros.put("listaPrecio", listaP.getNombre());
		}
		reporte.generar(parametros, listCliente, "clientes", "inline");
	}

}
