package ar.com.managed.beans.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import dao.interfaces.DAOCliente;
import dao.interfaces.DAOEquipoPendientePago;
import dao.interfaces.DAOUnidadMovil;
import model.entity.Usuario;
import model.entity.Cliente;
import model.entity.EquipoPendientePago;
import model.entity.UnidadMovil;

@ManagedBean
@SessionScoped
public class BeanPendientePago implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(BeanVentaCliente.class);
	
	@ManagedProperty(value = "#{BeanEquipoPendientePagoDAO}")
	private DAOEquipoPendientePago equipoPendientePagoDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	private List<EquipoPendientePago> listaEpp;
	private float montoTotal;
	int filtro;
	Cliente cliente = new Cliente();
	
	public DAOEquipoPendientePago getEquipoPendientePagoDAO() {
		return equipoPendientePagoDAO;
	}

	public void setEquipoPendientePagoDAO(DAOEquipoPendientePago equipoPendientePagoDAO) {
		this.equipoPendientePagoDAO = equipoPendientePagoDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public List<EquipoPendientePago> getListaEpp() {
		return listaEpp;
	}

	public void setListaEpp(List<EquipoPendientePago> listaEpp) {
		this.listaEpp = listaEpp;
	}
	

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public int getFiltro() {
		return filtro;
	}

	public void setFiltro(int filtro) {
		this.filtro = filtro;
	}
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String goPendientePago(Usuario user) {
		cliente = user.getCliente();
		montoTotal = 0;
		filtro = 0;
		log.info("Equipos pendiente de pago cliente: " + cliente.getApellidoNombre());
		listaEpp = equipoPendientePagoDAO.getListaPorCliente(cliente);
		for(EquipoPendientePago epp : listaEpp)  {
			if(epp.getEnabled()) {
				montoTotal = montoTotal + epp.getMonto();
			}
		}
		log.info("Monto total: " + montoTotal);
		return "pendientesdepago";	
	}
	
	public void changeFiltro() {
		log.info("Filtro: " + filtro);
		listaEpp = new ArrayList<EquipoPendientePago>();
		switch (filtro) {
		case 1:
			listaEpp = equipoPendientePagoDAO.getListaPorCliente(cliente);
			break;
		case 2:
			listaEpp = equipoPendientePagoDAO.getListaPagosPorCliente(cliente);
			break;
		case 3:
			listaEpp = equipoPendientePagoDAO.getListaNoPagosPorCliente(cliente);
			break;
		default: 
			break;
		}
		log.info("Cantidad registros: " + listaEpp.size());
		
	}
	
	public String getNombrePorImei(String imei) {
		UnidadMovil unidadMovil = unidadMovilDAO.get(imei);
		return unidadMovil.getProducto().getNombre();
	}
	
	public String getStringPagado(Boolean pagado) {
		if(pagado) {
			return "Si";
		} else {
			return "No";
		}
	}

}
