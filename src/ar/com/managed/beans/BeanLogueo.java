package ar.com.managed.beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.entity.Cliente;
import model.entity.Compra;
import model.entity.ComprasDetalle;
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalle;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesCliente;
import model.entity.ListaPrecio;
import model.entity.Producto;
import model.entity.Role;
import model.entity.RolesVista;
import model.entity.Rubro;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasCon;
import model.entity.VentasConsDetalle;
import model.entity.VentasConsDetalleUnidad;
import model.entity.VentasDetalle;
import model.entity.VentasDetalleUnidad;
import model.entity.Vista;

import org.primefaces.context.RequestContext;

import ar.com.clases.CostoPromedio;
import ar.com.clases.CuentaCorriente;
import ar.com.clases.Helper;
import dao.interfaces.DAOCliente;
import dao.interfaces.DAOCompra;
import dao.interfaces.DAOCompraDetalle;
import dao.interfaces.DAOCompraDetalleUnidad;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuentaCorriente;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAORole;
import dao.interfaces.DAORoleVista;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOUsuario;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaConsignacion;
import dao.interfaces.DAOVentaConsignacionDetalle;
import dao.interfaces.DAOVentaConsignacionDetalleUnidad;
import dao.interfaces.DAOVentaDetalle;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean(name = "beanLogueo")
@SessionScoped
public class BeanLogueo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanUsuarioDAO}")
	private DAOUsuario usuarioDAO;
	
	@ManagedProperty(value = "#{BeanRoleDAO}")
	private DAORole roleDAO;
	
	@ManagedProperty(value = "#{BeanRoleVistaDAO}")
	private DAORoleVista roleVistaDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleDAO}")
	private DAOVentaDetalle ventaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanClienteDAO}")
	private DAOCliente clienteDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDAO}")
	private DAOVentaConsignacion ventaConsignacionDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleDAO}")
	private DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaConsignacionDetalleUnidadDAO}")
	private DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanCuentaCorrienteDAO}")
	private DAOCuentaCorriente cuentaCorrienteDAO;
	
	@ManagedProperty(value = "#{BeanCompraDAO}")
	private DAOCompra compraDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleDAO}")
	private DAOCompraDetalle compraDetalleDAO;
	
	@ManagedProperty(value = "#{BeanCompraDetalleUnidadDAO}")
	private DAOCompraDetalleUnidad compraDetalleUnidadDAO;
	
	private List<Role> listaRoles; 
	private Usuario usuario;
	private String nombreLogin;
	private String passLogin;
	private String passNueva;
	private String confirmPass;
	private String textOk;
	private int idRol;
	private boolean checkButton;
	private boolean logeado;
	private boolean menuCliente;
	private boolean menuProveedor;
	private boolean menuProducto;
	private boolean menuListaPrecio;
	private boolean menuCompra;
	private boolean menuRol;
	private boolean menuUsuario;
	private boolean menuPagoCliente;
	private boolean menuPagoProveedor;
	private boolean menuCreditoDebitoCliente;
	private boolean menuCreditoDebitoProveedor;
	private boolean menuGasto;
	private boolean menuCaja;
	private boolean menuConsignacion;
	private boolean menuVenta;
	private boolean menuGarantia;
	private boolean menuDevolucion;
	private boolean menuRankingCliente;
	private boolean menuRankingProveedor;
	private boolean menuRankingProducto;
	private boolean menuGanancia;
	private boolean menuPatrimonio;
	private boolean menuAccesorio;
	private boolean menuUsado;
	private boolean menuBusqueda;
	private boolean submenuPersona;
	private boolean submenuProducto;
	private boolean submenuUsuario;
	private boolean submenuPago;
	private boolean submenuCreditoDebito;
	private boolean submenuPagoMovimiento;
	private boolean submenuVenta;
	private boolean submenuGarantiasDevoluciones;
	private boolean submenuReportes;
	private boolean esCliente;

	public DAOUsuario getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DAOUsuario usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public DAORole getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(DAORole roleDAO) {
		this.roleDAO = roleDAO;
	}

	public DAORoleVista getRoleVistaDAO() {
		return roleVistaDAO;
	}

	public void setRoleVistaDAO(DAORoleVista roleVistaDAO) {
		this.roleVistaDAO = roleVistaDAO;
	}

	public DAOProducto getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(DAOProducto productoDAO) {
		this.productoDAO = productoDAO;
	}

	public DAOUnidadMovil getUnidadMovilDAO() {
		return unidadMovilDAO;
	}

	public void setUnidadMovilDAO(DAOUnidadMovil unidadMovilDAO) {
		this.unidadMovilDAO = unidadMovilDAO;
	}

	public DAOVenta getVentaDAO() {
		return ventaDAO;
	}

	public void setVentaDAO(DAOVenta ventaDAO) {
		this.ventaDAO = ventaDAO;
	}

	public DAOVentaDetalle getVentaDetalleDAO() {
		return ventaDetalleDAO;
	}

	public void setVentaDetalleDAO(DAOVentaDetalle ventaDetalleDAO) {
		this.ventaDetalleDAO = ventaDetalleDAO;
	}

	public DAOVentaDetalleUnidad getVentaDetalleUnidadDAO() {
		return ventaDetalleUnidadDAO;
	}

	public void setVentaDetalleUnidadDAO(DAOVentaDetalleUnidad ventaDetalleUnidadDAO) {
		this.ventaDetalleUnidadDAO = ventaDetalleUnidadDAO;
	}

	public DAOConsignacion getConsignacionDAO() {
		return consignacionDAO;
	}

	public void setConsignacionDAO(DAOConsignacion consignacionDAO) {
		this.consignacionDAO = consignacionDAO;
	}

	public DAOConsignacionDetalle getConsignacionDetalleDAO() {
		return consignacionDetalleDAO;
	}

	public void setConsignacionDetalleDAO(
			DAOConsignacionDetalle consignacionDetalleDAO) {
		this.consignacionDetalleDAO = consignacionDetalleDAO;
	}

	public DAOConsignacionDetalleUnidad getConsignacionDetalleUnidadDAO() {
		return consignacionDetalleUnidadDAO;
	}

	public void setConsignacionDetalleUnidadDAO(
			DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO) {
		this.consignacionDetalleUnidadDAO = consignacionDetalleUnidadDAO;
	}

	public DAOCliente getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(DAOCliente clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public DAOVentaConsignacion getVentaConsignacionDAO() {
		return ventaConsignacionDAO;
	}

	public void setVentaConsignacionDAO(DAOVentaConsignacion ventaConsignacionDAO) {
		this.ventaConsignacionDAO = ventaConsignacionDAO;
	}

	public DAOVentaConsignacionDetalle getVentaConsignacionDetalleDAO() {
		return ventaConsignacionDetalleDAO;
	}

	public void setVentaConsignacionDetalleDAO(
			DAOVentaConsignacionDetalle ventaConsignacionDetalleDAO) {
		this.ventaConsignacionDetalleDAO = ventaConsignacionDetalleDAO;
	}

	public DAOVentaConsignacionDetalleUnidad getVentaConsignacionDetalleUnidadDAO() {
		return ventaConsignacionDetalleUnidadDAO;
	}

	public void setVentaConsignacionDetalleUnidadDAO(
			DAOVentaConsignacionDetalleUnidad ventaConsignacionDetalleUnidadDAO) {
		this.ventaConsignacionDetalleUnidadDAO = ventaConsignacionDetalleUnidadDAO;
	}

	public DAOCuentaCorriente getCuentaCorrienteDAO() {
		return cuentaCorrienteDAO;
	}

	public void setCuentaCorrienteDAO(DAOCuentaCorriente cuentaCorrienteDAO) {
		this.cuentaCorrienteDAO = cuentaCorrienteDAO;
	}

	public DAOCompra getCompraDAO() {
		return compraDAO;
	}

	public void setCompraDAO(DAOCompra compraDAO) {
		this.compraDAO = compraDAO;
	}

	public DAOCompraDetalle getCompraDetalleDAO() {
		return compraDetalleDAO;
	}

	public void setCompraDetalleDAO(DAOCompraDetalle compraDetalleDAO) {
		this.compraDetalleDAO = compraDetalleDAO;
	}

	public DAOCompraDetalleUnidad getCompraDetalleUnidadDAO() {
		return compraDetalleUnidadDAO;
	}

	public void setCompraDetalleUnidadDAO(
			DAOCompraDetalleUnidad compraDetalleUnidadDAO) {
		this.compraDetalleUnidadDAO = compraDetalleUnidadDAO;
	}

	public List<Role> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Role> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombreLogin() {
		return nombreLogin;
	}

	public void setNombreLogin(String nombreLogin) {
		this.nombreLogin = nombreLogin;
	}

	public String getPassLogin() {
		return passLogin;
	}

	public void setPassLogin(String passLogin) {
		this.passLogin = passLogin;
	}

	public String getPassNueva() {
		return passNueva;
	}

	public void setPassNueva(String passNueva) {
		this.passNueva = passNueva;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public String getTextOk() {
		return textOk;
	}

	public void setTextOk(String textOk) {
		this.textOk = textOk;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public boolean isCheckButton() {
		return checkButton;
	}

	public void setCheckButton(boolean checkButton) {
		this.checkButton = checkButton;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public boolean isMenuCliente() {
		return menuCliente;
	}

	public void setMenuCliente(boolean menuCliente) {
		this.menuCliente = menuCliente;
	}

	public boolean isMenuProveedor() {
		return menuProveedor;
	}

	public void setMenuProveedor(boolean menuProveedor) {
		this.menuProveedor = menuProveedor;
	}

	public boolean isMenuProducto() {
		return menuProducto;
	}

	public void setMenuProducto(boolean menuProducto) {
		this.menuProducto = menuProducto;
	}

	public boolean isMenuListaPrecio() {
		return menuListaPrecio;
	}

	public void setMenuListaPrecio(boolean menuListaPrecio) {
		this.menuListaPrecio = menuListaPrecio;
	}

	public boolean isMenuCompra() {
		return menuCompra;
	}

	public void setMenuCompra(boolean menuCompra) {
		this.menuCompra = menuCompra;
	}

	public boolean isMenuRol() {
		return menuRol;
	}

	public void setMenuRol(boolean menuRol) {
		this.menuRol = menuRol;
	}

	public boolean isMenuUsuario() {
		return menuUsuario;
	}

	public void setMenuUsuario(boolean menuUsuario) {
		this.menuUsuario = menuUsuario;
	}

	public boolean isMenuPagoCliente() {
		return menuPagoCliente;
	}

	public void setMenuPagoCliente(boolean menuPagoCliente) {
		this.menuPagoCliente = menuPagoCliente;
	}

	public boolean isMenuPagoProveedor() {
		return menuPagoProveedor;
	}

	public void setMenuPagoProveedor(boolean menuPagoProveedor) {
		this.menuPagoProveedor = menuPagoProveedor;
	}

	public boolean isMenuCreditoDebitoCliente() {
		return menuCreditoDebitoCliente;
	}

	public void setMenuCreditoDebitoCliente(boolean menuCreditoDebitoCliente) {
		this.menuCreditoDebitoCliente = menuCreditoDebitoCliente;
	}

	public boolean isMenuCreditoDebitoProveedor() {
		return menuCreditoDebitoProveedor;
	}

	public void setMenuCreditoDebitoProveedor(boolean menuCreditoDebitoProveedor) {
		this.menuCreditoDebitoProveedor = menuCreditoDebitoProveedor;
	}

	public boolean isMenuGasto() {
		return menuGasto;
	}

	public void setMenuGasto(boolean menuGasto) {
		this.menuGasto = menuGasto;
	}

	public boolean isMenuCaja() {
		return menuCaja;
	}

	public void setMenuCaja(boolean menuCaja) {
		this.menuCaja = menuCaja;
	}

	public boolean isMenuConsignacion() {
		return menuConsignacion;
	}

	public void setMenuConsignacion(boolean menuConsignacion) {
		this.menuConsignacion = menuConsignacion;
	}

	public boolean isMenuVenta() {
		return menuVenta;
	}

	public void setMenuVenta(boolean menuVenta) {
		this.menuVenta = menuVenta;
	}

	public boolean isMenuGarantia() {
		return menuGarantia;
	}

	public void setMenuGarantia(boolean menuGarantia) {
		this.menuGarantia = menuGarantia;
	}

	public boolean isMenuDevolucion() {
		return menuDevolucion;
	}

	public void setMenuDevolucion(boolean menuDevolucion) {
		this.menuDevolucion = menuDevolucion;
	}

	public boolean isMenuRankingCliente() {
		return menuRankingCliente;
	}

	public void setMenuRankingCliente(boolean menuRankingCliente) {
		this.menuRankingCliente = menuRankingCliente;
	}

	public boolean isMenuRankingProveedor() {
		return menuRankingProveedor;
	}

	public void setMenuRankingProveedor(boolean menuRankingProveedor) {
		this.menuRankingProveedor = menuRankingProveedor;
	}

	public boolean isMenuRankingProducto() {
		return menuRankingProducto;
	}

	public void setMenuRankingProducto(boolean menuRankingProducto) {
		this.menuRankingProducto = menuRankingProducto;
	}

	public boolean isMenuGanancia() {
		return menuGanancia;
	}

	public void setMenuGanancia(boolean menuGanancia) {
		this.menuGanancia = menuGanancia;
	}

	public boolean isMenuPatrimonio() {
		return menuPatrimonio;
	}

	public void setMenuPatrimonio(boolean menuPatrimonio) {
		this.menuPatrimonio = menuPatrimonio;
	}

	public boolean isMenuAccesorio() {
		return menuAccesorio;
	}

	public void setMenuAccesorio(boolean menuAccesorio) {
		this.menuAccesorio = menuAccesorio;
	}

	public boolean isMenuUsado() {
		return menuUsado;
	}

	public void setMenuUsado(boolean menuUsado) {
		this.menuUsado = menuUsado;
	}

	public boolean isMenuBusqueda() {
		return menuBusqueda;
	}

	public void setMenuBusqueda(boolean menuBusqueda) {
		this.menuBusqueda = menuBusqueda;
	}

	public boolean isSubmenuPersona() {
		return submenuPersona;
	}

	public void setSubmenuPersona(boolean submenuPersona) {
		this.submenuPersona = submenuPersona;
	}

	public boolean isSubmenuProducto() {
		return submenuProducto;
	}

	public void setSubmenuProducto(boolean submenuProducto) {
		this.submenuProducto = submenuProducto;
	}

	public boolean isSubmenuUsuario() {
		return submenuUsuario;
	}

	public void setSubmenuUsuario(boolean submenuUsuario) {
		this.submenuUsuario = submenuUsuario;
	}

	public boolean isSubmenuPago() {
		return submenuPago;
	}

	public void setSubmenuPago(boolean submenuPago) {
		this.submenuPago = submenuPago;
	}

	public boolean isSubmenuCreditoDebito() {
		return submenuCreditoDebito;
	}

	public void setSubmenuCreditoDebito(boolean submenuCreditoDebito) {
		this.submenuCreditoDebito = submenuCreditoDebito;
	}

	public boolean isSubmenuPagoMovimiento() {
		return submenuPagoMovimiento;
	}

	public void setSubmenuPagoMovimiento(boolean submenuPagoMovimiento) {
		this.submenuPagoMovimiento = submenuPagoMovimiento;
	}

	public boolean isSubmenuVenta() {
		return submenuVenta;
	}

	public void setSubmenuVenta(boolean submenuVenta) {
		this.submenuVenta = submenuVenta;
	}

	public boolean isSubmenuGarantiasDevoluciones() {
		return submenuGarantiasDevoluciones;
	}

	public void setSubmenuGarantiasDevoluciones(boolean submenuGarantiasDevoluciones) {
		this.submenuGarantiasDevoluciones = submenuGarantiasDevoluciones;
	}

	public boolean isSubmenuReportes() {
		return submenuReportes;
	}

	public void setSubmenuReportes(boolean submenuReportes) {
		this.submenuReportes = submenuReportes;
	}

	public boolean isEsCliente() {
		return esCliente;
	}

	public void setEsCliente(boolean esCliente) {
		this.esCliente = esCliente;
	}

	public void login() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		usuario = new Usuario();
		Helper helper = new Helper();
		String hash = helper.EncodePassword(passLogin);
		usuario = usuarioDAO.get(nombreLogin, hash);
		if (usuario.getId() != 0) {
			logeado = true;
			verificarAcceso(usuario);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ " + nombreLogin,
					null);
		} else {
			logeado = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error! User y Pass no validas",
					null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("estaLogeado", logeado);
		if (logeado) {
			String paginaInicio = "index.xhtml";
			if (esCliente) {
				paginaInicio = "inicio.xhtml";
			}
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(paginaInicio);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void logout() {
		String host = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServerName();
		int port = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestServerPort();
		StringBuffer url = new StringBuffer("http://");
		url.append(host);
		url.append(":");
		url.append(port);
		//Beta
//		url.append("/MoboParana/login.xhtml");
		//Produccion
		url.append("/login.xhtml");
		String urlFinal = url.toString();
		FacesContext contexto = FacesContext.getCurrentInstance();
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.invalidate();
			usuario = new Usuario();
			logeado = false;
			cambiarEstado(false);
			contexto.getExternalContext().redirect(urlFinal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setting(){
		passNueva = "";
		confirmPass = "";
		listaRoles = roleDAO.getLista();
		idRol = usuario.getRole().getId();
		checkButton = false;
	}
	
	public void handleKeyEvent() {
		if (passNueva.equals(confirmPass)) {
			textOk = "Ok";
			checkButton = true;
		} else {
			textOk = "No";
			checkButton = false;
		}
	}
	
	public void editarUsuario(){
		FacesMessage msg = null;
		if(!usuario.getUsername().isEmpty()){
			Role role = new Role();
			role.setId(idRol);
			usuario.setFechaMod(new Date());
			usuario.setRole(role);
			usuario.setUsuario3(usuario);
			if(!passNueva.isEmpty() && textOk.equals("Ok")){
				Helper helper = new Helper();
				String password = helper.EncodePassword(passNueva);
				usuario.setPassword(password);
			}
			if(usuarioDAO.update(usuario) != 0){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario modificado!", null);
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al modificar el Usuario, "
						+ "inténtelo nuevamente!", null);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Username no puede estar vacío!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private void cambiarEstado(boolean estado){
		menuCaja = estado;
		menuCliente = estado;
		menuCompra = estado;
		menuConsignacion = estado;
		menuCreditoDebitoCliente = estado;
		menuCreditoDebitoProveedor = estado;
		menuGasto = estado;
		menuListaPrecio = estado;
		menuPagoCliente = estado;
		menuPagoProveedor = estado;
		menuProducto = estado;
		menuProveedor = estado;
		menuRol = estado;
		menuUsuario = estado;
		menuVenta = estado;
		menuGarantia = estado;
		menuDevolucion = estado;
		menuRankingCliente = estado;
		menuRankingProducto = estado;
		menuRankingProveedor = estado;
		menuGanancia = estado;
		menuPatrimonio = estado;
		menuAccesorio = estado;
		menuUsado = estado;
		menuBusqueda = estado;
		submenuCreditoDebito = estado;
		submenuPago = estado;
		submenuPagoMovimiento = estado;
		submenuPersona = estado;
		submenuProducto = estado;
		submenuUsuario = estado;
		submenuVenta = estado;
		submenuGarantiasDevoluciones = estado;
		submenuReportes = estado;
	}
	
	private void verificarAcceso(Usuario user){
		cambiarEstado(false);
		esCliente = false;
		Role role = user.getRole();
		if(role.getId() == 1){
			cambiarEstado(true);
		}
		if (role.getId() == 3) {
			esCliente = true;
		}
		if(role.getId() != 1){
			List<RolesVista> listAux = roleVistaDAO.getLista(role);
			for (RolesVista rolesVista : listAux) {
				Vista vista = rolesVista.getVista();
				if(vista.getNombre().equals("Caja")){
					menuCaja = true;
				}
				if(vista.getNombre().equals("Clientes")){
					menuCliente = true;
				}
				if(vista.getNombre().equals("Compras")){
					menuCompra = true;
				}
				if(vista.getNombre().equals("Consignacion")){
					menuConsignacion = true;
				}
				if(vista.getNombre().equals("Credito/Debito Cliente")){
					menuCreditoDebitoCliente = true;
				}
				if(vista.getNombre().equals("Credito/Debito Proveedor")){
					menuCreditoDebitoProveedor = true;
				}
				if(vista.getNombre().equals("Gastos")){
					menuGasto = true;
				}
				if(vista.getNombre().equals("Listas de Precio")){
					menuListaPrecio = true;
				}
				if(vista.getNombre().equals("Pagos de Cliente")){
					menuPagoCliente = true;
				}
				if(vista.getNombre().equals("Pagos de Proveedor")){
					menuPagoProveedor = true;
				}
				if(vista.getNombre().equals("Moviles")){
					menuProducto = true;
				}
				if(vista.getNombre().equals("Proveedores")){
					menuProveedor = true;
				}
				if(vista.getNombre().equals("Roles")){
					menuRol = true;
				}
				if(vista.getNombre().equals("Usuarios")){
					menuUsuario = true;
				}
				if(vista.getNombre().equals("Ventas")){
					menuVenta = true;
				}
				if(vista.getNombre().equals("Garantia")){
					menuGarantia = true;
				}
				if(vista.getNombre().equals("Devolucion")){
					menuDevolucion = true;
				}
				if(vista.getNombre().equals("Ranking Clientes")){
					menuRankingCliente = true;
				}
				if(vista.getNombre().equals("Ranking Proveedores")){
					menuRankingProveedor = true;
				}
				if(vista.getNombre().equals("Ranking Productos")){
					menuRankingProducto = true;
				}
				if(vista.getNombre().equals("Ganancias")){
					menuGanancia = true;
				}
				if(vista.getNombre().equals("Patrimonio")){
					menuPatrimonio = true;
				}
				if(vista.getNombre().equals("Accesorios")){
					menuAccesorio = true;
				}
				if(vista.getNombre().equals("Moviles Usados")){
					menuUsado = true;
				}
				if(vista.getNombre().equals("Busqueda")){
					menuBusqueda = true;
				}
			}
			if(menuCliente || menuProveedor){
				submenuPersona = true;
			}
			if(menuProducto || menuListaPrecio || menuCompra || menuAccesorio || menuUsado){
				submenuProducto = true;
			}
			if(menuRol || menuUsuario){
				submenuUsuario = true;
			}
			if(menuPagoCliente || menuPagoProveedor){
				submenuPago = true;
			}
			if(menuCreditoDebitoCliente || menuCreditoDebitoProveedor){
				submenuCreditoDebito = true;
			}
			if(menuGasto || menuCaja || submenuPago || submenuCreditoDebito){
				submenuPagoMovimiento = true;
			}
			if(menuConsignacion || menuVenta){
				submenuVenta = true;
			}
			if(menuGarantia || menuDevolucion){
				submenuGarantiasDevoluciones = true;
			}
			if(menuRankingCliente || menuRankingProveedor || menuRankingProducto || menuPatrimonio || menuGanancia){
				submenuReportes = true;
			}
		}
	}
	
//	public void proceso() {
//		List<Producto> lista = productoDAO.getLista();
//		for (Producto producto : lista) {
//			List<UnidadMovil> listAux = unidadMovilDAO.getLista(producto);
//			int cant = 0;
//			for (UnidadMovil unidadMovil : listAux) {
//				String imei = unidadMovil.getNroImei();
//				VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(imei);
//				ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(imei);
//				if (ventaUnidad.getId() == 0 ) {
//					if (consignacionUnidad.getId() != 0) {
//						if (!consignacionUnidad.getVendido()) {
//							cant = cant + 1;
//						}
//					}
//					if (consignacionUnidad.getId() == 0) {
//						cant = cant + 1;
//					}
//				}				
//				
//			}
//			producto.setStock(cant);
//			productoDAO.update(producto);
//		}
//	}
	
	public void proceso2() {
		List<Consignacion> listaConsignacion = consignacionDAO.getLista();
		for (Consignacion consignacion : listaConsignacion) {
			Date fechaAlta = consignacion.getFechaAlta();
			List<ConsignacionsDetalle> listaConsignacionDetalle = consignacionDetalleDAO.getLista(consignacion);
			for (ConsignacionsDetalle consignacionsDetalle : listaConsignacionDetalle) {
				List<ConsignacionsDetalleUnidad> listaConsignacionDetalleUnidad = consignacionDetalleUnidadDAO.getLista(consignacionsDetalle);
				for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listaConsignacionDetalleUnidad) {
					UnidadMovil unidad = unidadMovilDAO.get(consignacionsDetalleUnidad.getNroImei(), true, true);
					if(unidad.getId() != 0){
						consignacionsDetalleUnidad.setFechaVenta(fechaAlta);
						consignacionsDetalleUnidad.setVendido(true);
					}
					consignacionsDetalleUnidad.setFechaAlta(fechaAlta);
					consignacionsDetalleUnidad.setEnabled(true);
					consignacionDetalleUnidadDAO.update(consignacionsDetalleUnidad);
				}
			}
		}
	}
	
	public void procesoConsignacionVenta() {
		List<Producto> lista = productoDAO.getLista();
		int actualizo = 1;
		for (Producto producto : lista) {
			System.out.println("Producto: " + producto.getNombre());
			int consignacion = 0;
			List<UnidadMovil> listaUnidad = unidadMovilDAO.getLista(true, producto);
			for (UnidadMovil unidadMovil : listaUnidad) {
				String nroImei = unidadMovil.getNroImei();
				boolean noEntroConsig = true;
				boolean noEntroVent = true;
				System.out.println("Nro Imei: " + nroImei);
				ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(nroImei);				
				if (consignacionUnidad.getId() != 0) {
					noEntroConsig = false;
					System.out.println("Consignacion");
					boolean estadoCU = consignacionUnidad.getEnabled();
					boolean eliminadoCU = consignacionUnidad.getEliminado();
					boolean vendidoCU = consignacionUnidad.getVendido();
					if (estadoCU && !eliminadoCU) {
						int idConsigDetalle = consignacionUnidad.getConsignacionsDetalle().getId();
						ConsignacionsDetalle consigDetalle = consignacionDetalleDAO.get(idConsigDetalle);
						if (consigDetalle.getId() != 0) {
							boolean eliminadoCD = consigDetalle.getEliminado();
							if (!eliminadoCD) {
								int idConsig = consigDetalle.getConsignacion().getId();
								Consignacion consig = consignacionDAO.get(idConsig);
								boolean estadoC = consig.getEstado();
								if (estadoC) {
									unidadMovil.setEnConsignacion(true);
									if (vendidoCU) {
										unidadMovil.setEnStock(false);
										unidadMovil.setEnVenta(true);
									} else {
										unidadMovil.setEnStock(true);
										unidadMovil.setEnVenta(false);
										consignacion++;
									}								
									unidadMovil.setFechaMod(new Date());
									unidadMovil.setUsuario3(usuario);
									unidadMovilDAO.update(unidadMovil);
								}
							} else {
								Date fechaUC = consignacionUnidad.getFechaAlta();
								Date fechaPro = new Date(2016, 1, 1);
								int compareTo = fechaUC.compareTo(fechaPro);//fechaUC menor que fechaPro == 1;
								if (compareTo == 1) {                       //fechaUC mayor que fechaPro == -1;
									unidadMovil.setEnStock(false);          //fechaUC mayor que fechaPro == 0;
									unidadMovil.setEnConsignacion(false);
									unidadMovil.setEliminado(true);
									unidadMovil.setEnVenta(false);
									unidadMovil.setEstado(false);
								} else {
									unidadMovil.setEnConsignacion(false);
									unidadMovil.setEnStock(true);
									unidadMovil.setEliminado(false);
									unidadMovil.setEstado(true);
								}
								unidadMovilDAO.update(unidadMovil);
							}
						} else {
							Date fechaUC = consignacionUnidad.getFechaAlta();
							Date fechaPro = new Date(2016, 1, 1);
							int compareTo = fechaUC.compareTo(fechaPro);//fechaUC menor que fechaPro == 1;
							if (compareTo == 1) {                       //fechaUC mayor que fechaPro == -1;
								unidadMovil.setEnStock(false);          //fechaUC mayor que fechaPro == 0;
								unidadMovil.setEnConsignacion(false);
								unidadMovil.setEliminado(true);
								unidadMovil.setEnVenta(false);
								unidadMovil.setEstado(false);
							} else {
								unidadMovil.setEnConsignacion(false);
								unidadMovil.setEnStock(true);
								unidadMovil.setEliminado(false);
								unidadMovil.setEstado(true);
							}
							unidadMovilDAO.update(unidadMovil);
						}
					} else {
						unidadMovil.setEnConsignacion(false);
						unidadMovilDAO.update(unidadMovil);
					}
				}
				VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(nroImei);
				if (ventaUnidad.getId() != 0) {
					noEntroVent = false;
					System.out.println("Venta");
					boolean eliminadoVU = ventaUnidad.getEliminado();
					if (!eliminadoVU) {
						int idVentDet = ventaUnidad.getVentasDetalle().getId();
						VentasDetalle ventDetalle = ventaDetalleDAO.get(idVentDet);
						if (ventDetalle.getId() != 0) {
							boolean eliminadoVD = ventDetalle.getEliminado();
							if (!eliminadoVD) {
								int idVent = ventDetalle.getVenta().getId();
								Venta vent = ventaDAO.get(idVent);
								boolean estadoV = vent.getEstado();
								if (estadoV) {
									unidadMovil.setEnVenta(true);
									unidadMovil.setEnStock(false);
									unidadMovil.setFechaMod(new Date());
									unidadMovil.setUsuario3(usuario);
									unidadMovilDAO.update(unidadMovil);
								}
							}
						}
					}
				}
				if (noEntroConsig && noEntroVent) {
					unidadMovil.setEnConsignacion(false);
					unidadMovil.setEnVenta(false);
					unidadMovilDAO.update(unidadMovil);
				}
			}
			System.out.println("En consignacion: " + consignacion);
			producto.setEnConsignacion(consignacion);
			int updateProd = productoDAO.update(producto);
			System.out.println("updateProd: " + updateProd);
			if (updateProd == 0) {
				actualizo = 0;
			}
		}
		FacesMessage msg = null;
		if (actualizo != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZO!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void procesoStock() {
		List<Producto> lista = productoDAO.getLista();
		int actualizo = 1;
		for (Producto producto : lista) {
			int stock = 0;
			int consignacion = 0;
			List<UnidadMovil> listaUnidadStock = unidadMovilDAO.getListaEnStock(true, producto, false, true);
			List<UnidadMovil> listaUnidadCons = unidadMovilDAO.getListaEnStock(true, producto, true, true);
			stock = listaUnidadStock.size();
			consignacion = listaUnidadCons.size();
//			List<UnidadMovil> listaUnidad = unidadMovilDAO.getLista(true, producto);
//			for (UnidadMovil unidadMovil : listaUnidad) {
//				boolean enConsignacion = unidadMovil.getEnConsignacion();
//				boolean enStock = unidadMovil.getEnStock();
//				if (enStock && !enConsignacion) {
//					stock++;
//				}
//			}
			System.out.println("Producto: " + producto.getNombre());
			System.out.println("Stock definitivo: " + stock);
			System.out.println("En COnsignacion: " + consignacion);
			producto.setStock(stock);
			producto.setEnConsignacion(consignacion);
			int updateProd = productoDAO.update(producto);
			System.out.println("updateProd: " + updateProd);
			if (updateProd == 0) {
				actualizo = 0;
			}
		}
		FacesMessage msg = null;
		if (actualizo != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZO!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void procesoVerificaStock() {
		List<Producto> listaProd = productoDAO.getLista(true);
		for (Producto producto : listaProd) {
			System.out.println("Producto: " + producto.getNombre());
			int stock = producto.getStock();
			System.out.println("Stock de prod: " + stock);
			List<UnidadMovil> listaUnidMovil = unidadMovilDAO.getListaEnStock(true, producto, false, true);
			int size = listaUnidMovil.size();
			System.out.println("Cant en lista stock: " + size);
			int consignacion = producto.getEnConsignacion();
			System.out.println("Consignacion de prod: " + consignacion);
			List<UnidadMovil> listaUnidadCons = unidadMovilDAO.getListaEnStock(true, producto, true, true);
			int sizeC = listaUnidadCons.size();
			System.out.println("Cant en lista consignacion: " + sizeC);
		}
	}
	
	public void procesoCuentaCorriente() {
		CuentaCorriente cuenta = new CuentaCorriente();
		int proc = cuenta.procesoActualizaSaldos();
		FacesMessage msg = null;
		if (proc != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizo", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Actualizo", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void procesoCCParticular() {
		CuentaCorriente cuenta = new CuentaCorriente();
		int proc = cuenta.procesoActualizaSaldoParticular(18);
		FacesMessage msg = null;
		if (proc != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizo", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "NO Actualizo", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void procesoImeiMuestra() {
		List<Producto> listaProducto = productoDAO.getLista(true);
		for (Producto producto : listaProducto) {
			System.out.println("Producto: " + producto.getNombre());
			int idProducto = producto.getId();
			List<String> listaImei = unidadMovilDAO.getListaImeis(idProducto);
			for (String string : listaImei) {
				UnidadMovil unidad = unidadMovilDAO.get(string);
				if (unidad.getId() == 0) {
					System.out.println(string);
				}
			}
		}
//		UnidadMovil unidad = unidadMovilDAO.get("866439021111730");
//		if (unidad.getId() == 0) {
//			System.out.println("V");
//		} else {
//			System.out.println("F");
//		}
	}
	
	public void procesoActualizaImei() {
		List<Producto> listaProducto = productoDAO.getLista(true);
		for (Producto producto : listaProducto) {
			System.out.println("Producto: " + producto.getNombre());
			int idProducto = producto.getId();
			List<String> listaImei = unidadMovilDAO.getListaImeis(idProducto);
			for (String string : listaImei) {
				UnidadMovil unidad = unidadMovilDAO.get(string);
				if (unidad.getId() == 0) {
					System.out.println(string);
					List<UnidadMovil> listaUnidades = unidadMovilDAO.getLista(string);
					int cant = listaUnidades.size();
					int i = 1;
					System.out.println("Cant: " + cant);
					for (UnidadMovil unidadMovil : listaUnidades) {
						System.out.println("i: " + i);
						unidadMovil.setEliminado(true);
						unidadMovil.setEstado(false);
						unidadMovil.setEnCompra(false);
						unidadMovil.setEnConsignacion(false);
						unidadMovil.setEnStock(false);
						unidadMovil.setEnVenta(false);
						unidadMovil.setDevuelto(false);
						unidadMovil.setFechaBaja(new Date());
						unidadMovil.setUsuario2(usuario);
						int idUnidad = unidadMovilDAO.update(unidadMovil);
						System.out.println("Id: " + idUnidad);
						i++;
						if (i == cant) {
							break;
						}			
					}
					System.out.println("Salio");
				}
			}
		}
	}
	
	public void procesoActualizaCons() {
		try {
			List<Cliente> listaClientes = clienteDAO.getLista(true);
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			for (Cliente cliente : listaClientes) {
				CuentaCorriente cuenta = new CuentaCorriente();
				CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
				System.out.println("Cliente: " + cliente.getNombreNegocio());
				Consignacion consig = consignacionDAO.get(cliente, true);
				ccCliente.setCliente(cliente);
				ccCliente.setIdMovimiento(consig.getId());
				ccCliente.setNombreTabla("Consignacion");
				cuenta.deleteCuentaCorriente(ccCliente);
				List<ConsignacionsDetalleUnidad> listConsignacionUnidad = consignacionDetalleUnidadDAO.getListaOrderFechaVenta(consig, true);
				System.out.println(listConsignacionUnidad.size());
				for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listConsignacionUnidad) {
//					System.out.println(consignacionsDetalleUnidad.getNroImei());
					Date fechaUnidadVenta = consignacionsDetalleUnidad.getFechaVenta();
					String fVenta = formato.format(fechaUnidadVenta);
					Date fecVenta = formato.parse(fVenta);
					fecVenta.setHours(0);
					fecVenta.setMinutes(0);
					fecVenta.setSeconds(0);
//					int idConsDetalle = consignacionsDetalleUnidad.getConsignacionsDetalle().getId();
//					ConsignacionsDetalle consignacionDetalle = consignacionDetalleDAO.get(idConsDetalle);
					cuenta = new CuentaCorriente();
					ccCliente = new CuentasCorrientesCliente();
					int idProd = consignacionsDetalleUnidad.getConsignacionsDetalle().getProducto().getId();
					float precioVenta = consignacionsDetalleUnidad.getPrecioVenta();
					float precioCompra = consignacionsDetalleUnidad.getPrecioCompra();
					String nroImei = consignacionsDetalleUnidad.getNroImei();
//					Producto prod = productoDAO.get(idProd);
					List<VentasCon> listaVentasCon = ventaConsignacionDAO.getLista(true, consig);
					boolean existe = false;
					VentasCon ventaCon = new VentasCon();
					for (VentasCon ventasCon : listaVentasCon) {
						System.out.println(fecVenta);
						Date fechaVentaCon = ventasCon.getFecha();
						System.out.println(fechaVentaCon);
						int compara = fechaVentaCon.compareTo(fecVenta);
						System.out.println(compara);
						if (compara == 0) {							
							ventaCon = ventasCon;
							existe = true;
						}
					}
					if (existe) {
						ccCliente.setCliente(cliente);
						ccCliente.setIdMovimiento(ventaCon.getId());
						ccCliente.setNombreTabla("VentasCon");
						cuenta.deleteCuentaCorriente(ccCliente);
						ccCliente = new CuentasCorrientesCliente();
						cuenta = new CuentaCorriente();
						List<VentasConsDetalle> listaDetalle = ventaConsignacionDetalleDAO.getLista(ventaCon);
						VentasConsDetalle ventaConsDetalle = new VentasConsDetalle();
						boolean existeDetalle = false;
						for (VentasConsDetalle ventasConsDetalle : listaDetalle) {
							int idProdDetalle = ventasConsDetalle.getProducto().getId();
							if (idProd == idProdDetalle) {
								ventaConsDetalle = ventasConsDetalle;
								existeDetalle = true;
							}
						}
						if (existeDetalle) {
							int cantidad = ventaConsDetalle.getCantidad();
							cantidad = cantidad + 1;
							float subtotal = ventaConsDetalle.getSubtotal();
							subtotal = subtotal + precioVenta;
							ventaConsDetalle.setCantidad(cantidad);
							ventaConsDetalle.setSubtotal(subtotal);
							int idDetalle = ventaConsignacionDetalleDAO.update(ventaConsDetalle);
							ventaConsDetalle.setId(idDetalle);
							VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
							ventaUnidad.setEliminado(false);
							ventaUnidad.setNroImei(nroImei);
							ventaUnidad.setPrecioCompra(precioCompra);
							ventaUnidad.setPrecioVenta(precioVenta);
							ventaUnidad.setVentasConsDetalle(ventaConsDetalle);
							ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
						} else {
							Producto prod = productoDAO.get(idProd);
							VentasConsDetalle ventaDetalle = new VentasConsDetalle();
							ventaDetalle.setCantidad(1);
							ventaDetalle.setEliminado(false);
							ventaDetalle.setSubtotal(precioVenta);
							ventaDetalle.setProducto(prod);
							ventaDetalle.setVentasCon(ventaCon);
							int idDetalle = ventaConsignacionDetalleDAO.insertar(ventaDetalle);
							ventaDetalle.setId(idDetalle);
							VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
							ventaUnidad.setEliminado(false);
							ventaUnidad.setNroImei(nroImei);
							ventaUnidad.setPrecioCompra(precioCompra);
							ventaUnidad.setPrecioVenta(precioVenta);
							ventaUnidad.setVentasConsDetalle(ventaDetalle);
							ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
						}
						float monto = ventaCon.getMonto();
						monto = monto + precioVenta;
						ventaCon.setMonto(monto);
						ccCliente.setCliente(cliente);
						ccCliente.setDebe(monto);
						ccCliente.setDetalle("Venta Consignación nro: " + ventaCon.getId());
						ccCliente.setFecha(ventaCon.getFecha());
						ccCliente.setIdMovimiento(ventaCon.getId());
						ccCliente.setMonto(monto);
						ccCliente.setNombreTabla("VentasCon");
						ccCliente.setUsuario(usuario);
						cuenta.insertarCC(ccCliente);
						ventaConsignacionDAO.update(ventaCon);
					} else {
						ccCliente = new CuentasCorrientesCliente();
						cuenta = new CuentaCorriente();
						Producto prod = productoDAO.get(idProd);
						VentasCon venta = new VentasCon();
						venta.setCliente(cliente);
						venta.setConsignacion(consig);
						venta.setEstado(true);
						venta.setFecha(fecVenta);
						venta.setFechaAlta(new Date());
						venta.setMonto(precioVenta);
						venta.setSaldoCliente(cliente.getSaldo());
						venta.setTipo("c.c.");
						venta.setUsuario1(usuario);
						int idVenta = ventaConsignacionDAO.insertar(venta);
						venta.setId(idVenta);
						VentasConsDetalle ventaDetalle = new VentasConsDetalle();
						ventaDetalle.setCantidad(1);
						ventaDetalle.setEliminado(false);
						ventaDetalle.setProducto(prod);
						ventaDetalle.setSubtotal(precioVenta);
						ventaDetalle.setVentasCon(venta);
						int idDetalle = ventaConsignacionDetalleDAO.insertar(ventaDetalle);
						ventaDetalle.setId(idDetalle);
						VentasConsDetalleUnidad ventaUnidad = new VentasConsDetalleUnidad();
						ventaUnidad.setEliminado(false);
						ventaUnidad.setNroImei(nroImei);
						ventaUnidad.setPrecioCompra(precioCompra);
						ventaUnidad.setPrecioVenta(precioVenta);
						ventaUnidad.setVentasConsDetalle(ventaDetalle);
						ventaConsignacionDetalleUnidadDAO.insertar(ventaUnidad);
						ccCliente.setCliente(cliente);
						ccCliente.setDebe(precioVenta);
						ccCliente.setDetalle("Venta Consignación nro: " + idVenta);
						ccCliente.setFecha(fecVenta);
						ccCliente.setIdMovimiento(idVenta);
						ccCliente.setMonto(precioVenta);
						ccCliente.setNombreTabla("VentasCon");
						ccCliente.setUsuario(usuario);
						cuenta.insertarCC(ccCliente);
					}
				}		
			}	
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERRORRRR!!!!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}			
	}
	
	public void procesoActualizaCostoPromedio() {
		try {
			CostoPromedio costoPromedio = new CostoPromedio();
			List<Producto> lista = costoPromedio.calculaCostoPromedio(productoDAO.getLista(true));
			boolean actualizo = true;
			for (Producto producto : lista) {
				int idProd = productoDAO.update(producto);
				if (idProd == 0) {
					actualizo = false;
				}
			}
			if (!actualizo) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "No actualizo algun producto!", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch(Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void postLoad() {
		if (logeado) {
			String retorno = "";
			if (!esCliente) {
				retorno = "index.xhtml";
			}
			if (esCliente) {
				retorno = "inicio.xhtml";
			}
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(retorno);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void procesoLPConsig() {
		try {
			List<ConsignacionsDetalleUnidad> listaUnidads = consignacionDetalleUnidadDAO.getLista();
			for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listaUnidads) {
				ConsignacionsDetalle consigDetalle = consignacionsDetalleUnidad.getConsignacionsDetalle();
				Consignacion consig = consigDetalle.getConsignacion();
				Producto prod = consigDetalle.getProducto();
				ListaPrecio lista = consig.getCliente().getListaPrecio();
				consignacionsDetalleUnidad.setListaPrecio(lista);
				consignacionsDetalleUnidad.setProducto(prod);
				int upt = consignacionDetalleUnidadDAO.update(consignacionsDetalleUnidad);
				if (upt == 0) {
					System.out.println("Fallo la actualizacion!");
					System.out.println("Nro Imei: " + consignacionsDetalleUnidad.getNroImei());
					System.out.println("Producto: " + prod.getNombre());
				}
			}
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("OCURRIO UN ERROR: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
	}
	
	public void procesoActualizaUltimoCosto() {
		List<Producto> listaProducts = new ArrayList<Producto>();
		Rubro rub = new Rubro();
		rub.setId(1);
		listaProducts = productoDAO.getLista(rub);
		for (Producto producto : listaProducts) {
			float ultimoCosto = 0;
			List<ComprasDetalle> listaComprasDetalles = compraDetalleDAO.getLista(producto);
			for (ComprasDetalle comprasDetalle : listaComprasDetalles) {
				Compra compra = comprasDetalle.getCompra();
				if (compra.getEstado()) {
					ultimoCosto = comprasDetalle.getPrecioCompra();
				}
			}
			producto.setPrecioCosto(ultimoCosto);
			int updateo = productoDAO.update(producto);
			System.out.println("Móvil: " + producto.getNombre() + " Actualizó: " + updateo + " con ultimo costo: " + ultimoCosto);
		}
	}

}
