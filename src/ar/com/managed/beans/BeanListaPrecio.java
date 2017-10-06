package ar.com.managed.beans;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import org.apache.log4j.Logger;

import ar.com.clases.CostoPromedio;
import ar.com.clases.Mail;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.ListaDePrecio;
import ar.com.managed.beans.cliente.BeanVentaCliente;
import model.entity.Cliente;
import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import model.entity.Rubro;
import model.entity.Usuario;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOListaPrecio;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAORubro;

@ManagedBean
@SessionScoped
public class BeanListaPrecio implements Serializable {
	
	private final static Logger log = Logger.getLogger(BeanVentaCliente.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanListaPrecioDAO}")
	private DAOListaPrecio listaPrecioDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanRubroDAO}")
	private DAORubro rubroDAO;
	
	private List<ListaPrecio> listaPrecios;
	private List<ListaPrecio> filteredPrecios;
	private List<ListaPrecioProducto> listaPrecioProductos;
	private List<ListaPrecioProducto> filteredPrecioProductos;
	private List<ListaDePrecio> listaPrecioDinamica;
	private List<ListaDePrecio> filteredPrecioDinamica;
	private List<ListaPrecioProducto> selectedPrecioDinamica;
	private List<Cliente> listaCliente;
	private List<Cliente> listaClienteSelectos;
	private ListaPrecio listaPrecio;
	private Usuario usuario;
	private String headerText;
	private int estado;
	private int porcentaje;
	private int tipo;
	private int idRubro; 
	private boolean envioEmail;

	public DAOListaPrecio getListaPrecioDAO() {
		return listaPrecioDAO;
	}

	public void setListaPrecioDAO(DAOListaPrecio listaPrecioDAO) {
		this.listaPrecioDAO = listaPrecioDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAORubro getRubroDAO() {
		return rubroDAO;
	}

	public void setRubroDAO(DAORubro rubroDAO) {
		this.rubroDAO = rubroDAO;
	}

	public List<ListaPrecio> getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(List<ListaPrecio> listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	public List<ListaPrecio> getFilteredPrecios() {
		return filteredPrecios;
	}

	public void setFilteredPrecios(List<ListaPrecio> filteredPrecios) {
		this.filteredPrecios = filteredPrecios;
	}

	public List<ListaPrecioProducto> getListaPrecioProductos() {
		return listaPrecioProductos;
	}

	public void setListaPrecioProductos(
			List<ListaPrecioProducto> listaPrecioProductos) {
		this.listaPrecioProductos = listaPrecioProductos;
	}

	public List<ListaPrecioProducto> getFilteredPrecioProductos() {
		return filteredPrecioProductos;
	}

	public void setFilteredPrecioProductos(
			List<ListaPrecioProducto> filteredPrecioProductos) {
		this.filteredPrecioProductos = filteredPrecioProductos;
	}

	public List<ListaDePrecio> getListaPrecioDinamica() {
		return listaPrecioDinamica;
	}

	public void setListaPrecioDinamica(List<ListaDePrecio> listaPrecioDinamica) {
		this.listaPrecioDinamica = listaPrecioDinamica;
	}

	public List<ListaDePrecio> getFilteredPrecioDinamica() {
		return filteredPrecioDinamica;
	}

	public void setFilteredPrecioDinamica(List<ListaDePrecio> filteredPrecioDinamica) {
		this.filteredPrecioDinamica = filteredPrecioDinamica;
	}

	public List<ListaPrecioProducto> getSelectedPrecioDinamica() {
		return selectedPrecioDinamica;
	}

	public void setSelectedPrecioDinamica(
			List<ListaPrecioProducto> selectedPrecioDinamica) {
		this.selectedPrecioDinamica = selectedPrecioDinamica;
	}
	
	public ListaPrecio getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public List<Cliente> getListaClienteSelectos() {
		return listaClienteSelectos;
	}

	public void setListaClienteSelectos(List<Cliente> listaClienteSelectos) {
		this.listaClienteSelectos = listaClienteSelectos;
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
	
	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public boolean isEnvioEmail() {
		return envioEmail;
	}

	public void setEnvioEmail(boolean envioEmail) {
		this.envioEmail = envioEmail;
	}

	public String goListasPrecios(Usuario user){
		listaPrecios = new ArrayList<ListaPrecio>();
		filteredPrecios = new ArrayList<ListaPrecio>();
		listaPrecios = listaPrecioDAO.getLista(true);
		filteredPrecios = listaPrecios;
		usuario = new Usuario();
		usuario = user;
		estado = 0;
		tipo = 0;
		return "listasprecios";
	}
	
	public String goListaPrecioNueva(){
		listaPrecio = new ListaPrecio();
		headerText = "Lista de Precios Nueva";
		listaPrecioProductos = new ArrayList<ListaPrecioProducto>();
		filteredPrecioProductos = new ArrayList<ListaPrecioProducto>();
//		List<Producto> listAux = new ArrayList<Producto>();
//		listAux = productoDAO.getLista(true);
		CostoPromedio costoPromedio = new CostoPromedio();
		List<Producto> listAux = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
		for (Producto producto : listAux) {
			ListaPrecioProducto listaPrecioProd = new ListaPrecioProducto();
			listaPrecioProd.setProducto(producto);
			listaPrecioProductos.add(listaPrecioProd);
		}
		filteredPrecioProductos = listaPrecioProductos;
		porcentaje = 0;
		tipo = 3;
		idRubro = 0;
		return "listaprecio";
	}
	
	public String goListaPrecioEditar(ListaPrecio listaPre){		
		listaPrecio = new ListaPrecio();
		porcentaje = 0;
		idRubro = 0;
		headerText = "Modificar Lista de Precios";
		listaPrecioProductos = new ArrayList<ListaPrecioProducto>();
		filteredPrecioProductos = new ArrayList<ListaPrecioProducto>();
		List<ListaPrecioProducto> listaProductos = new ArrayList<ListaPrecioProducto>();
		List<ListaPrecioProducto> filteredProductos = new ArrayList<ListaPrecioProducto>();
		listaPrecio = listaPre;		
		listaCliente = new ArrayList<Cliente>();
		listaCliente = clienteDAO.getLista(listaPre);
		
		if (listaPre.getRelacionBase()) {
			List<ListaPrecioProducto> listPreProds = listaPrecioDAO.getListaPrecioProducto(listaPre);
			List<ListaPrecioProducto> listaPrecioAux = new ArrayList<ListaPrecioProducto>();
			CostoPromedio costoPromedio = new CostoPromedio();
			List<Producto> listAux = new ArrayList<Producto>();
			if (listaPre.getRubro() != null) {
				idRubro = listaPre.getRubro().getId();
				Rubro rub = rubroDAO.get(listaPre.getRubro().getId());
				listAux = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true, rub));
			} else {
				listAux = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
			}
			
			for (Producto producto : listAux) {
				boolean noExiste = true;
				for(ListaPrecioProducto listaPrecioProd : listPreProds){
					if(producto.getId() == listaPrecioProd.getProducto().getId()){
						noExiste = false;
						listaPrecioProd.setProducto(producto);
						listaPrecioAux.add(listaPrecioProd);
					}
				}
				if(noExiste){
					ListaPrecioProducto listaPrecioProd = new ListaPrecioProducto();
					listaPrecioProd.setProducto(producto);
					listaPrecioAux.add(listaPrecioProd);
				}
			}			
			
			ListaPrecio listaBase = listaPrecioDAO.getBase();
			List<ListaPrecioProducto> listPreBase = listaPrecioDAO.getListaPrecioProducto(listaBase);
			
			for (ListaPrecioProducto listaPrecioProd : listaPrecioAux) {
				boolean noExiste = true;
				for (ListaPrecioProducto listaPrecioBase : listPreBase) {
					if (listaPrecioBase.getProducto().getId() == listaPrecioProd.getProducto().getId()) {						
						if (listaPrecioBase.getPrecioVenta() != 0) {
							noExiste = false;
							Producto prod = listaPrecioProd.getProducto();
							prod.setCostoPromedio(listaPrecioBase.getPrecioVenta());
							listaPrecioProd.setProducto(prod);
							listaProductos.add(listaPrecioProd);
						}
					}
				}
				if (noExiste) {
					listaProductos.add(listaPrecioProd);
				}
			}
			
			//Completo lista filtered con todos los productos para metodo ActulizarLista
			List<ListaPrecioProducto> filteredPrecioAux = new ArrayList<ListaPrecioProducto>();
			costoPromedio = new CostoPromedio();
			List<Producto> listAuxProds = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
			for (Producto producto : listAuxProds) {
				boolean noExiste = true;
				for(ListaPrecioProducto listaPrecioProd : listPreProds){
					if(producto.getId() == listaPrecioProd.getProducto().getId()){
						noExiste = false;
						listaPrecioProd.setProducto(producto);
						filteredPrecioAux.add(listaPrecioProd);
					}
				}
				if(noExiste){
					ListaPrecioProducto listaPrecioProd = new ListaPrecioProducto();
					listaPrecioProd.setProducto(producto);
					filteredPrecioAux.add(listaPrecioProd);
				}
			}
			for (ListaPrecioProducto listaPrecioProd : filteredPrecioAux) {
				boolean noExiste = true;
				for (ListaPrecioProducto listaPrecioBase : listPreBase) {
					if (listaPrecioBase.getProducto().getId() == listaPrecioProd.getProducto().getId()) {						
						if (listaPrecioBase.getPrecioVenta() != 0) {
							noExiste = false;
							Producto prod = listaPrecioProd.getProducto();
							prod.setCostoPromedio(listaPrecioBase.getPrecioVenta());
							listaPrecioProd.setProducto(prod);
							filteredProductos.add(listaPrecioProd);
						}
					}
				}
				if (noExiste) {
					filteredProductos.add(listaPrecioProd);
				}
			}
			
		} else {
//			listaPrecioProductos = listaPrecioDAO.getListaPrecioProducto(listaPre);
			List<ListaPrecioProducto> listPreProds = listaPrecioDAO.getListaPrecioProducto(listaPre);
			CostoPromedio costoPromedio = new CostoPromedio();
			List<Producto> listAux = new ArrayList<Producto>();
			if (listaPre.getRubro() != null) {
				idRubro = listaPre.getRubro().getId();
				Rubro rub = rubroDAO.get(listaPre.getRubro().getId());
				listAux = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true, rub));
			} else {
				listAux = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
			}
//			System.out.println("isEmpty() " + listAux.isEmpty());
//			List<Producto> listAux = productoDAO.getLista(true);
			for (Producto producto : listAux) {
				boolean noExiste = true;
				for(ListaPrecioProducto listaPrecioProd : listPreProds){
					if(producto.getId() == listaPrecioProd.getProducto().getId()){
						noExiste = false;
						listaPrecioProd.setProducto(producto);
						listaProductos.add(listaPrecioProd);
					}
				}
				if(noExiste){
					ListaPrecioProducto listaPrecioProd = new ListaPrecioProducto();
					listaPrecioProd.setProducto(producto);
					listaProductos.add(listaPrecioProd);
				}
			}
			
			//Completo lista filtered con todos los productos para metodo ActulizarLista
			costoPromedio = new CostoPromedio();
			List<Producto> listAuxProds = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
			for (Producto producto : listAuxProds) {
				boolean noExiste = true;
				for(ListaPrecioProducto listaPrecioProd : listPreProds){
					if(producto.getId() == listaPrecioProd.getProducto().getId()){
						noExiste = false;
						listaPrecioProd.setProducto(producto);
						filteredProductos.add(listaPrecioProd);
					}
				}
				if(noExiste){
					ListaPrecioProducto listaPrecioProd = new ListaPrecioProducto();
					listaPrecioProd.setProducto(producto);
					filteredProductos.add(listaPrecioProd);
				}
			}
		}
		
		//Verifico que los productos esten todos activos
		for (ListaPrecioProducto listaProducto : listaProductos) {
			if (listaProducto.getProducto().getEstado()) {
				listaPrecioProductos.add(listaProducto);
			}
		}		
		for (ListaPrecioProducto filteredProducto : filteredProductos) {
			if (filteredProducto.getProducto().getEstado()) {
				filteredPrecioProductos.add(filteredProducto);
			}
		}
		
//		filteredPrecioProductos = listaPrecioProductos;
		return "listaprecio";
	}
	
	public String goListaPrecioDinamica(ListaPrecio lista) {
		try {
			selectedPrecioDinamica = new ArrayList<ListaPrecioProducto>();
			listaPrecioDinamica = new ArrayList<ListaDePrecio>();
			filteredPrecioDinamica = new ArrayList<ListaDePrecio>();
			listaPrecio = new ListaPrecio();
			
			listaPrecio = lista;			
			DecimalFormat formatoMonto = new DecimalFormat("###,##0.00");			
			
			List<ListaPrecioProducto> listaAux = listaPrecioDAO.getListaPrecioProducto(lista);			
			for (ListaPrecioProducto listaPrecioProducto : listaAux) {
				if(listaPrecioProducto.getPrecioVenta() != 0){
					ListaDePrecio dePrecio = new ListaDePrecio();
					dePrecio.setId(listaPrecioProducto.getId());
					if (listaPrecioProducto.getProducto().getCostoPromedio() != 0) {
						dePrecio.setCosto(formatoMonto.format(listaPrecioProducto.getProducto().getCostoPromedio()));
					} else {
						dePrecio.setCosto(formatoMonto.format(listaPrecioProducto.getProducto().getPrecioCosto()));
					}					
					dePrecio.setPorcentaje(formatoMonto.format(listaPrecioProducto.getPorcentaje()));
//						float precioVenta = listaPrecioProducto.getProducto().getCostoPromedio() + 
//								(listaPrecioProducto.getProducto().getCostoPromedio() * listaPrecioProducto.getPorcentaje())/100;
					dePrecio.setPrecio(formatoMonto.format(listaPrecioProducto.getPrecioVenta()));
					dePrecio.setProducto(listaPrecioProducto.getProducto().getNombre());
					dePrecio.setTipo(listaPrecioProducto.getProducto().getRubro().getNombre());
					listaPrecioDinamica.add(dePrecio);
				}
			}
			filteredPrecioDinamica = listaPrecioDinamica;
			
			return "listapreciocliente";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}		
	}
	
	public void goEditarTipo(ListaPrecio lista) {
		tipo = 3;
		if (lista.getBase()) {
			tipo = 1;
		}
		if (lista.getRelacionBase()) {
			tipo = 2;
		}
		listaPrecio = new ListaPrecio();
		listaPrecio = lista;
	}
	
	public void actualizarTipo() {
		System.out.println("Tipo " + tipo);
		System.out.println("Base " + listaPrecio.getBase());
		System.out.println("Relacion Base " + listaPrecio.getRelacionBase());
		if (listaPrecio.getBase()) {			
			if (tipo == 2){//Relacion con Base
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible realizar el cambio a listas relacionadas con base, realice las bajas correspondientes!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			if (tipo == 3) {//Ninguna
				List<ListaPrecio> listas = listaPrecioDAO.getLista(true, true);
				if (listas.isEmpty()) {
					listaPrecio.setBase(false);
					listaPrecio.setFechaMod(new Date());
					listaPrecio.setUsuario3(usuario);
					listaPrecioDAO.update(listaPrecio);
					listaPrecios = new ArrayList<ListaPrecio>();
					filteredPrecios = new ArrayList<ListaPrecio>();
					listaPrecios = listaPrecioDAO.getLista(true);
					filteredPrecios = listaPrecios;
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Listas de precio actualizadas correctamente!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible realizar el cambio en lista, realice las bajas de listas relacionadas!", null);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		}
		
		if (listaPrecio.getRelacionBase()) {			
			if (tipo == 1) {//Base
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible realizar el cambio a lista base, realice las bajas correspondientes!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			if (tipo == 3) {//Ninguna
				listaPrecio.setRelacionBase(false);
				listaPrecio.setFechaMod(new Date());
				listaPrecio.setUsuario3(usuario);				
				CostoPromedio costoPromedio = new CostoPromedio();
				List<ListaPrecioProducto> listaPrecioProds = listaPrecioDAO.getListaPrecioProducto(listaPrecio);				
				List<Producto> listAux = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
				for (Producto producto : listAux) {
					for(ListaPrecioProducto listaPrecioProd : listaPrecioProds){
						if(producto.getId() == listaPrecioProd.getProducto().getId()){
							float costo = producto.getCostoPromedio();
							float porciento = listaPrecioProd.getPorcentaje();
							if (porciento != 0) {
								float precioVenta = costo + (costo * porciento)/100;
								listaPrecioProd.setPrecioVenta(precioVenta);
								listaPrecioDAO.update(listaPrecioProd);
							}
						}
					}
				}
				listaPrecioDAO.update(listaPrecio);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Listas de precio actualizadas correctamente!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
		
		if (tipo == 1 && !listaPrecio.getBase() && !listaPrecio.getRelacionBase()) {//Base
			ListaPrecio listaBase = listaPrecioDAO.getBase();
			listaBase.setBase(false);
			listaBase.setFechaMod(new Date());
			listaBase.setUsuario3(usuario);
			listaPrecio.setBase(true);
			listaPrecio.setFechaMod(new Date());
			listaPrecio.setUsuario3(usuario);
			List<ListaPrecio> listas = listaPrecioDAO.getLista(true, true);
			if (!listas.isEmpty()) {
				List<ListaPrecioProducto> listasProductosBase = listaPrecioDAO.getListaPrecioProducto(listaPrecio);
				for (ListaPrecio listaP : listas) {
					List<ListaPrecioProducto> listaPrecioProds = listaPrecioDAO.getListaPrecioProducto(listaP);
					for (ListaPrecioProducto listaPrecioProducto : listaPrecioProds) {
						for (ListaPrecioProducto listaPPB : listasProductosBase) {
							if (listaPPB.getProducto().getId() == listaPrecioProducto.getProducto().getId()) {
								float costo = listaPPB.getPrecioVenta();
								float porciento = listaPrecioProducto.getPorcentaje();
								if (porciento != 0) {
									float precioVenta = costo + (costo * porciento)/100;
									listaPrecioProducto.setPrecioVenta(precioVenta);
								} else {
									listaPrecioProducto.setPrecioVenta(costo);
								}								
								listaPrecioDAO.update(listaPrecioProducto);
							}
						}
					}
				}				
			}
			listaPrecioDAO.update(listaPrecio);
			listaPrecioDAO.update(listaBase);
			listaPrecios = new ArrayList<ListaPrecio>();
			filteredPrecios = new ArrayList<ListaPrecio>();
			listaPrecios = listaPrecioDAO.getLista(true);
			filteredPrecios = listaPrecios;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Listas de precio actualizadas correctamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		if (tipo == 2 && !listaPrecio.getBase() && !listaPrecio.getRelacionBase()) {//Relacion con base
			listaPrecio.setRelacionBase(true);
			listaPrecio.setFechaMod(new Date());
			listaPrecio.setUsuario3(usuario);
			ListaPrecio listaBase = listaPrecioDAO.getBase();
			if (listaBase.getId() != 0) {
				List<ListaPrecioProducto> listaProductosBase = listaPrecioDAO.getListaPrecioProducto(listaBase);
				List<ListaPrecioProducto> listaPrecioProds = listaPrecioDAO.getListaPrecioProducto(listaPrecio);
				for (ListaPrecioProducto listaPrecioProducto : listaPrecioProds) {
					for (ListaPrecioProducto listaPPB : listaProductosBase) {
						if (listaPPB.getProducto().getId() == listaPrecioProducto.getProducto().getId()) {
							System.out.println("Producto: " + listaPPB.getProducto().getNombre());
							float costo = listaPPB.getPrecioVenta();
							System.out.println("Costo: " + costo);
							float porciento = listaPrecioProducto.getPorcentaje();
							System.out.println("Porciento: " + porciento);
							float precioVenta = 0;
							if (porciento != 0) {
								precioVenta = costo + (costo * porciento)/100;								
							} else {
								precioVenta = costo;
							}
							System.out.println("Precio Venta " + precioVenta);
							listaPrecioProducto.setPrecioVenta(precioVenta);
							listaPrecioDAO.update(listaPrecioProducto);
						}
					}
				}
				listaPrecioDAO.update(listaPrecio);
				listaPrecios = new ArrayList<ListaPrecio>();
				filteredPrecios = new ArrayList<ListaPrecio>();
				listaPrecios = listaPrecioDAO.getLista(true);
				filteredPrecios = listaPrecios;
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Listas de precio actualizadas correctamente!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe seleccionada una lista como base!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}			
		}
	}
	
	public void actualizarPrecioVenta(){
		if (idRubro == 0) {
			List<ListaPrecioProducto> listAux = filteredPrecioProductos;
			listaPrecioProductos = new ArrayList<ListaPrecioProducto>();
			for (ListaPrecioProducto listaPrecioProducto : listAux) {
				if (porcentaje > 0) {
					listaPrecioProducto.setPorcentaje(porcentaje);
					float precioVenta = listaPrecioProducto.getProducto().getCostoPromedio() + 
							(listaPrecioProducto.getProducto().getCostoPromedio() * porcentaje)/100;
					listaPrecioProducto.setPrecioVenta(precioVenta);
				}					
				listaPrecioProductos.add(listaPrecioProducto);
			}
		} else {
			List<ListaPrecioProducto> listAux = filteredPrecioProductos;
			listaPrecioProductos = new ArrayList<ListaPrecioProducto>();
			for (ListaPrecioProducto listaPrecioProducto : listAux) {
				if (listaPrecioProducto.getProducto().getRubro().getId() == idRubro) {
					if (porcentaje > 0) {
						listaPrecioProducto.setPorcentaje(porcentaje);
						float precioVenta = listaPrecioProducto.getProducto().getCostoPromedio() + 
								(listaPrecioProducto.getProducto().getCostoPromedio() * porcentaje)/100;
						listaPrecioProducto.setPrecioVenta(precioVenta);
					}						
					listaPrecioProductos.add(listaPrecioProducto);
				}
			}
		}
	}
	
	public void onCellEdit(ListaPrecioProducto listaPrecioProd){
		List<ListaPrecioProducto> listAux = listaPrecioProductos;
		listaPrecioProductos = new ArrayList<ListaPrecioProducto>();
		for (ListaPrecioProducto listaPrecioProducto : listAux) {
			if(listaPrecioProducto.getProducto().getId() == listaPrecioProd.getProducto().getId()){
//				System.out.println("Porcentaje antes: " + listaPrecioProd.getPorcentaje());
//				System.out.println("Precio Venta: " + listaPrecioProd.getPrecioVenta());
				if(listaPrecioProd.getPorcentaje() != 0){
					float precioVenta = listaPrecioProducto.getProducto().getCostoPromedio() + 
							(listaPrecioProducto.getProducto().getCostoPromedio() * listaPrecioProd.getPorcentaje())/100;
					listaPrecioProducto.setPorcentaje(listaPrecioProd.getPorcentaje());
					listaPrecioProducto.setPrecioVenta(precioVenta);
				}
//				System.out.println("Precio Venta: " + listaPrecioProd.getPrecioVenta());
				if(listaPrecioProd.getPrecioVenta() != 0){
//					System.out.println("Precio Costo: " + listaPrecioProducto.getProducto().getCostoPromedio());
					float porcentaje = ((listaPrecioProd.getPrecioVenta() - listaPrecioProducto.getProducto().getCostoPromedio())*100)/listaPrecioProducto.getProducto().getCostoPromedio();
//					System.out.println("Porcentaje: " + porcentaje);
					listaPrecioProducto.setPorcentaje(porcentaje);
					listaPrecioProducto.setPrecioVenta(listaPrecioProd.getPrecioVenta());
				}
			}
			listaPrecioProductos.add(listaPrecioProducto);
		}
	}
	
	public void onCellCancel(ListaPrecioProducto listaPrecioProd){
		
	}
	
	public void filtro(){
		listaPrecios = new ArrayList<ListaPrecio>();
		filteredPrecios = new ArrayList<ListaPrecio>();
		if(estado == 0){
			listaPrecios = listaPrecioDAO.getLista();
		}
		if(estado == 1){
			listaPrecios = listaPrecioDAO.getLista(true);
		}
		if(estado == 2){
			listaPrecios = listaPrecioDAO.getLista(false);
		}
		filteredPrecios = listaPrecios;
	}
	
	public void alta(ListaPrecio listaPre){
		FacesMessage msg = null;
		listaPre.setEstado(true);
		listaPre.setFechaMod(new Date());
		listaPre.setUsuario3(usuario);
		if(listaPrecioDAO.update(listaPre) != 0){
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta de Lista de Precio!", null);
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al dar de Alta la Lista de Precio, "
					+ "intente nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void baja(ListaPrecio listaPre){
		FacesMessage msg = null;
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		listaClientes = clienteDAO.getLista(listaPre);
		if (listaClientes.isEmpty()) {
			listaPre.setEstado(false);
			listaPre.setFechaBaja(new Date());
			listaPre.setUsuario2(usuario);
			if(listaPrecioDAO.update(listaPre) != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Lista de Precio!", null);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al dar de Baja la Lista de Precio, "
						+ "intente nuevamente!", null);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible registrar la baja de la Lista, tiene Clientes asociados!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String guardar(){
		FacesMessage msg = null;
		String retorno = "";
		if(!listaPrecio.getNombre().isEmpty()){
			int idListaPrecio = 0;
			boolean update = false;
			if (idRubro != 0) {
				Rubro rub = rubroDAO.get(idRubro);
				listaPrecio.setRubro(rub);
			} else {
				listaPrecio.setRubro(null);
			}
			if(listaPrecio.getId() != 0){
				log.info("idListaPrecio: " + listaPrecio.getId());
				listaPrecio.setFechaMod(new Date());
				listaPrecio.setUsuario3(usuario);
				idListaPrecio = listaPrecioDAO.update(listaPrecio);
				update = true;
			}else{
				listaPrecio.setFechaAlta(new Date());
				listaPrecio.setUsuario1(usuario);
				listaPrecio.setEstado(true);
				idListaPrecio = listaPrecioDAO.insertar(listaPrecio);
			}
			if(idListaPrecio != 0){
				listaPrecio.setId(idListaPrecio);
				if(update){
					log.info("update: " + update);
					listaPrecioDAO.deleteProductosPorLista(listaPrecio);
				}
				boolean insert = true;
				for(ListaPrecioProducto listaPrecioProd : listaPrecioProductos){
					listaPrecioProd.setListaPrecio(listaPrecio);
					if(listaPrecioDAO.insertar(listaPrecioProd) == 0){
						insert = false;
					}
				}
				if (listaPrecio.getBase()) {
					List<ListaPrecio> listas = listaPrecioDAO.getLista(true, true);
					for (ListaPrecio listaP : listas) {
						List<ListaPrecioProducto> listaPrecioProd = listaPrecioDAO.getListaPrecioProducto(listaP);
						for (ListaPrecioProducto listaPrecioProducto : listaPrecioProd) {
							for(ListaPrecioProducto listaPPB : listaPrecioProductos){
								if (listaPPB.getProducto().getId() == listaPrecioProducto.getProducto().getId()) {
									float costo = listaPPB.getPrecioVenta();
									float porciento = listaPrecioProducto.getPorcentaje();
									float precioVenta = costo + (costo * porciento)/100;
									listaPrecioProducto.setPrecioVenta(precioVenta);
									listaPrecioDAO.update(listaPrecioProducto);
								}
							}
						}
					}
				}
				if(insert){
					
					if(update && listaClienteSelectos.size() > 0) {
						String cuerpo = generarHtml(listaPrecioProductos);
						List<Cliente> listaCliente = new ArrayList<Cliente>();
						listaCliente = clienteDAO.getLista(listaPrecio);
						String destinatarios = "";
						for(Cliente cli : listaClienteSelectos) {
							destinatarios = destinatarios + cli.getEmail() +",";
						}
						
						Mail mail = new Mail();
				    	mail.setAsunto("CB Telefonía - Actualizacion de lista de precios");
				    	mail.setCuerpo(cuerpo);
				    	mail.setDestinatarios(destinatarios);
				    	log.info(cuerpo);
				    	log.info(destinatarios);
				    	int send = mail.send();
						
					}
					
					msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista de Precio guardada!", null);
					retorno = "listasprecios";
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar los Productos asociados a la Lista, "
							+ "intente nuevamente!", null);
				}
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la Lista de Precio, "
						+ "intente nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre es requerido!", null);
		}
		listaPrecios = new ArrayList<ListaPrecio>();
		filteredPrecios = new ArrayList<ListaPrecio>();
		listaPrecios = listaPrecioDAO.getLista(true);
		filteredPrecios = listaPrecios;
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	public void generarComprobante(ListaPrecio lista, String nombre){
		Reporte reporte = new Reporte();
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<ListaDePrecio> listaDePrecio = new ArrayList<ListaDePrecio>();
		List<ListaPrecioProducto> listaAux = listaPrecioDAO.getListaPrecioProducto(lista);
		if(nombre.equals("listaPrecioCliente")){
			for (ListaPrecioProducto listaPrecioProducto : listaAux) {
				if(listaPrecioProducto.getPrecioVenta() != 0){
					ListaDePrecio dePrecio = new ListaDePrecio();
					if (listaPrecioProducto.getProducto().getCostoPromedio() != 0) {
						dePrecio.setCosto(formatoMonto.format(listaPrecioProducto.getProducto().getCostoPromedio()));
					} else {
						dePrecio.setCosto(formatoMonto.format(listaPrecioProducto.getProducto().getPrecioCosto()));
					}					
					dePrecio.setPorcentaje(formatoMonto.format(listaPrecioProducto.getPorcentaje()));
//					float precioVenta = listaPrecioProducto.getProducto().getCostoPromedio() + 
//							(listaPrecioProducto.getProducto().getCostoPromedio() * listaPrecioProducto.getPorcentaje())/100;
					dePrecio.setPrecio(formatoMonto.format(listaPrecioProducto.getPrecioVenta()));
					dePrecio.setProducto(listaPrecioProducto.getProducto().getNombre());
					dePrecio.setTipo(listaPrecioProducto.getProducto().getRubro().getNombre());
					listaDePrecio.add(dePrecio);
				}
			}
		}else{
			for (ListaPrecioProducto listaPrecioProducto : listaAux) {
				ListaDePrecio dePrecio = new ListaDePrecio();
				if (listaPrecioProducto.getProducto().getCostoPromedio() != 0) {
					dePrecio.setCosto(formatoMonto.format(listaPrecioProducto.getProducto().getCostoPromedio()));
				} else {
					dePrecio.setCosto(formatoMonto.format(listaPrecioProducto.getProducto().getPrecioCosto()));
				}	
				dePrecio.setPorcentaje(formatoMonto.format(listaPrecioProducto.getPorcentaje()));
//				float precioVenta = listaPrecioProducto.getProducto().getCostoPromedio() + 
//						(listaPrecioProducto.getProducto().getCostoPromedio() * listaPrecioProducto.getPorcentaje())/100;
				dePrecio.setPrecio(formatoMonto.format(listaPrecioProducto.getPrecioVenta()));
				dePrecio.setProducto(listaPrecioProducto.getProducto().getNombre());
				dePrecio.setTipo(listaPrecioProducto.getProducto().getRubro().getNombre());
				listaDePrecio.add(dePrecio);
			}
		}
		parametros.put("nombre", lista.getNombre());
		parametros.put("fecha", formatoFecha.format(new Date()));
		reporte.generar(parametros, listaDePrecio, nombre, "attachment");
	}
	
	public void generarComprobanteDinamico(String nombre) {
		try {
			Reporte reporte = new Reporte();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Map<String, Object> parametros = new HashMap<String, Object>();
			if (!selectedPrecioDinamica.isEmpty()) {
				parametros.put("nombre", listaPrecio.getNombre());
				parametros.put("fecha", formatoFecha.format(new Date()));
				reporte.generar(parametros, selectedPrecioDinamica, nombre, "attachment");
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar al menos un producto!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

    public String generarHtml(List<ListaPrecioProducto> listaPrecioProductos2) {
    	SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String html = "";
    	html += "<html>" + 
    			"<head>" + 
    			"<style>" + 
    			"table {" + 
    			"    font-family: arial, sans-serif;" + 
    			"    border-collapse: collapse;" + 
    			"    width: 100%;" + 
    			"}" +  
    			"td, th {" + 
    			"    border: 1px solid #dddddd;" + 
    			"    text-align: left;" + 
    			"    padding: 8px;" + 
    			"}" +  
    			"tr:nth-child(even) {" + 
    			"    background-color: #dddddd;" + 
    			"}" + 
    			".cabecera {" + 
    			"  font-family: arial, sans-serif;" + 
    			"    width: 100%;" + 
    			"}"+ 
    			"</style>" + 
    			"</head>" + 
    			"<body>" +  
    			"<table>" + 
    			"  <tr>" + 
    			"    <th>PRODUCTO</th>" + 
    			"    <th>PRECIO</th>" + 
    			"  </tr>";
    	
    	for (ListaPrecioProducto lpp : listaPrecioProductos2) {
    		if(lpp.getPrecioVenta() > 0) {
	    		html += "<tr>" + 
				"    <th>" + lpp.getProducto().getNombre() + "</th>" + 
				"    <th>$" + Float.toString(lpp.getPrecioVenta()) + "</th>" + 
				"  </tr>";
    		}
    	}
    	
    	html += "</table>" + 
    			"" + 
    			"</body>" + 
    			"</html>";
    	return html;
    }
}
