package ar.com.managed.beans;

import java.io.IOException;
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

import ar.com.clases.CuentaCorriente;
import ar.com.clases.MovimientoCaja;
import ar.com.clases.Reporte;
import ar.com.clases.auxiliares.Cuotas;
import model.entity.Caja;
import model.entity.Cliente;
import model.entity.CuentasCorrientesCliente;
import model.entity.CuotasVenta;
import model.entity.CuotasVentasDetalle;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import model.entity.Venta;
import model.entity.VentasDetalleUnidad;
import dao.interfaces.DAOCuotaVenta;
import dao.interfaces.DAOCuotaVentaDetalle;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;
import dao.interfaces.DAOVenta;
import dao.interfaces.DAOVentaDetalle;
import dao.interfaces.DAOVentaDetalleUnidad;

@ManagedBean
@SessionScoped
public class BeanCuotaVenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCuotaVentaDAO}")
	private DAOCuotaVenta cuotaVentaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaVentaDetalleDAO}")
	private DAOCuotaVentaDetalle cuotaVentaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDAO}")
	private DAOVenta ventaDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleDAO}")
	private DAOVentaDetalle ventaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanVentaDetalleUnidadDAO}")
	private DAOVentaDetalleUnidad ventaDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	private List<CuotasVenta> listaCuotasVenta;
	private List<CuotasVentasDetalle> listaCuotasVentasDetalles;
	private List<VentasDetalleUnidad> listaVentasDetalleUnidads;
	private List<String> selectedMoviles;
	private CuotasVenta cuotasVenta;
	private Usuario usuario;
	private Venta venta;
	private Date primerVencimiento;
	private int cantCuota;
	private int interes;

	public DAOCuotaVenta getCuotaVentaDAO() {
		return cuotaVentaDAO;
	}

	public void setCuotaVentaDAO(DAOCuotaVenta cuotaVentaDAO) {
		this.cuotaVentaDAO = cuotaVentaDAO;
	}

	public DAOCuotaVentaDetalle getCuotaVentaDetalleDAO() {
		return cuotaVentaDetalleDAO;
	}

	public void setCuotaVentaDetalleDAO(DAOCuotaVentaDetalle cuotaVentaDetalleDAO) {
		this.cuotaVentaDetalleDAO = cuotaVentaDetalleDAO;
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

	public List<CuotasVenta> getListaCuotasVenta() {
		return listaCuotasVenta;
	}

	public void setListaCuotasVenta(List<CuotasVenta> listaCuotasVenta) {
		this.listaCuotasVenta = listaCuotasVenta;
	}

	public List<CuotasVentasDetalle> getListaCuotasVentasDetalles() {
		return listaCuotasVentasDetalles;
	}

	public void setListaCuotasVentasDetalles(
			List<CuotasVentasDetalle> listaCuotasVentasDetalles) {
		this.listaCuotasVentasDetalles = listaCuotasVentasDetalles;
	}

	public List<VentasDetalleUnidad> getListaVentasDetalleUnidads() {
		return listaVentasDetalleUnidads;
	}

	public void setListaVentasDetalleUnidads(
			List<VentasDetalleUnidad> listaVentasDetalleUnidads) {
		this.listaVentasDetalleUnidads = listaVentasDetalleUnidads;
	}

	public List<String> getSelectedMoviles() {
		return selectedMoviles;
	}

	public void setSelectedMoviles(List<String> selectedMoviles) {
		this.selectedMoviles = selectedMoviles;
	}

	public CuotasVenta getCuotasVenta() {
		return cuotasVenta;
	}

	public void setCuotasVenta(CuotasVenta cuotasVenta) {
		this.cuotasVenta = cuotasVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Date getPrimerVencimiento() {
		return primerVencimiento;
	}

	public void setPrimerVencimiento(Date primerVencimiento) {
		this.primerVencimiento = primerVencimiento;
	}

	public int getCantCuota() {
		return cantCuota;
	}

	public void setCantCuota(int cantCuota) {
		this.cantCuota = cantCuota;
	}

	public int getInteres() {
		return interes;
	}

	public void setInteres(int interes) {
		this.interes = interes;
	}
	
	public String goCuota(Venta ven, Usuario user) {
		try{
			if (ven.getTipo().equals("ctdo.")) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Para poder acceder a Cuotas, la venta debe ser de tipo c.c.", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "";
			} else {
				venta = new Venta();
				usuario = new Usuario();
				venta = ven;
				usuario = user;
				List<VentasDetalleUnidad> listaAuxVentaDet = new ArrayList<VentasDetalleUnidad>();
				listaAuxVentaDet = ventaDetalleUnidadDAO.getLista(ven);
				List<CuotasVenta> listCuot = new ArrayList<CuotasVenta>();
				List<CuotasVenta> listaAuxCuot = new ArrayList<CuotasVenta>();
				listaAuxCuot = cuotaVentaDAO.getLista(true, ven);
				listaVentasDetalleUnidads = new ArrayList<VentasDetalleUnidad>();
				listaCuotasVenta = new ArrayList<CuotasVenta>();
				listaCuotasVentasDetalles = new ArrayList<CuotasVentasDetalle>();
				//listaCuotas = listaAuxCuot;
				SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
				SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
				SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
				Date date;
				int mes = Integer.parseInt(formatoMes.format(new Date()));
				String anio = formatoAnio.format(new Date());						
				mes = mes + 1;
				String fechaFiltro = "";
				if (mes < 10) {
					fechaFiltro = "010" + Integer.toString(mes) + anio;
				} else {
					fechaFiltro = "01" + Integer.toString(mes) + anio;
				}
				date = formato.parse(fechaFiltro);
				listaCuotasVentasDetalles = cuotaVentaDetalleDAO.getListaPorVencer(ven, date);
				for (VentasDetalleUnidad ventaDetalleUnidad : listaAuxVentaDet) {
					String imei = ventaDetalleUnidad.getNroImei();
					boolean noExiste = true;
					for (CuotasVenta cuota : listaAuxCuot) {
						if (imei.equals(cuota.getNroImei())) {
							noExiste = false;
						}
					}
					if (noExiste) {
						listaVentasDetalleUnidads.add(ventaDetalleUnidad);
					}
				}
				for (CuotasVenta cuota : listaAuxCuot) {
					List<CuotasVentasDetalle> listDet = cuotaVentaDetalleDAO.getLista(true, cuota);
					cuota.setCuotasVentasDetalles(listDet);
					listCuot.add(cuota);
				}
				listaCuotasVenta = listCuot;
				primerVencimiento = null;
				cantCuota = 0;
				interes = 0;
				selectedMoviles = new ArrayList<String>();

				return "cuotasventa";
			}			
		} catch (Exception e){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible acceder a la vista de Cuotas, Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}

	public String verCuotas(Usuario user) {
		try{
			usuario = new Usuario();
			usuario = user;
			listaCuotasVentasDetalles = new ArrayList<CuotasVentasDetalle>();
			SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			Date date;
			int mes = Integer.parseInt(formatoMes.format(new Date()));
			String anio = formatoAnio.format(new Date());						
			mes = mes + 1;
			String fechaFiltro = "";
			if (mes < 10) {
				fechaFiltro = "010" + Integer.toString(mes) + anio;
			} else {
				fechaFiltro = "01" + Integer.toString(mes) + anio;
			}		
			date = formato.parse(fechaFiltro);
			listaCuotasVentasDetalles = cuotaVentaDetalleDAO.getListaPorVencer(date);			
			return "vercuotasventa";
		} catch (Exception e){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible acceder a la vista de Cuotas, Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public void verDetalle(CuotasVenta cuot) {
		cuotasVenta = new CuotasVenta();
		cuotasVenta = cuot;
	}
	
	public void generarCuotas() {
		FacesMessage msg = null;
		if (!selectedMoviles.isEmpty() && primerVencimiento != null && cantCuota != 0) {
			try {
				List<CuotasVenta> listaAuxCuota = new ArrayList<CuotasVenta>();
				for (String imei : selectedMoviles) {
					UnidadMovil unidad = unidadMovilDAO.get(imei);
					VentasDetalleUnidad ventaUnidad = ventaDetalleUnidadDAO.get(imei);
					CuotasVenta cuo = new CuotasVenta();
					cuo.setCantCuotas(cantCuota);
					cuo.setVenta(venta);
					cuo.setEquipo(unidad.getProducto().getNombre());
					cuo.setEstado(true);
					cuo.setFechaAlta(new Date());
					cuo.setUsuario1(usuario);
					cuo.setInteres(interes);
					cuo.setNroImei(imei);
					float montoTotal = ventaUnidad.getPrecioVenta();
					float montoPorcen = 0;
					if (interes != 0) {
						montoPorcen = (montoTotal * interes)/100;
					}
					montoTotal = montoTotal + montoPorcen;
					cuo.setMontoTotal(montoTotal);
					int idCuota = cuotaVentaDAO.insertar(cuo);
					cuo.setId(idCuota);
					listaAuxCuota.add(cuo);
				}		
				
				for (CuotasVenta cuot : listaAuxCuota) {
					int cant = cuot.getCantCuotas();
					float monto = cuot.getMontoTotal()/cant;
					SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
					String sigVencimiento = formato.format(primerVencimiento);
					for (int i = 0; i < cant; i++) {
						Date fechaVenc;
						fechaVenc = formato.parse(sigVencimiento);
						CuotasVentasDetalle cuotaDetalle = new CuotasVentasDetalle();
						cuotaDetalle.setCuotasVenta(cuot);
						int nroCuota = i + 1;
						cuotaDetalle.setDescripcion("Cuota " + nroCuota);
						cuotaDetalle.setEstado(true);
						cuotaDetalle.setFechaVencimiento(fechaVenc);
						cuotaDetalle.setMonto(monto);
						cuotaDetalle.setPago(false);
						cuotaDetalle.setUsuario1(usuario);
						cuotaVentaDetalleDAO.insertar(cuotaDetalle);		
						SimpleDateFormat formatoDiaSig = new SimpleDateFormat("dd");
						SimpleDateFormat formatoMesSig = new SimpleDateFormat("MM");
						SimpleDateFormat formatoAnioSig = new SimpleDateFormat("yyyy");
						int diaSig = Integer.parseInt(formatoDiaSig.format(fechaVenc));
						int mesSig = Integer.parseInt(formatoMesSig.format(fechaVenc));
						String daySig = "";
						String monthSig = "";
						String anioSig = formatoAnioSig.format(fechaVenc);
						mesSig = mesSig + 1;
						if (diaSig < 10) {
							daySig = "0" + Integer.toString(diaSig);
						} else {
							daySig = Integer.toString(diaSig);
						}
						if (mesSig < 10) {
							monthSig = "0" + Integer.toString(mesSig);
						} else {
							monthSig = Integer.toString(mesSig);
						}
						fechaVenc = null;
						sigVencimiento = daySig + monthSig + anioSig;
					}
				}
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuotas generadas!", null);
				List<VentasDetalleUnidad> listaAuxVentaDet = new ArrayList<VentasDetalleUnidad>();
				listaAuxVentaDet = ventaDetalleUnidadDAO.getLista(venta);
				primerVencimiento = null;
				cantCuota = 0;
				interes = 0;
				selectedMoviles = new ArrayList<String>();
				listaCuotasVenta = new ArrayList<CuotasVenta>();
				listaCuotasVentasDetalles = new ArrayList<CuotasVentasDetalle>();
				listaVentasDetalleUnidads = new ArrayList<VentasDetalleUnidad>();
				List<CuotasVenta> listaCuot = new ArrayList<CuotasVenta>(); 
				List<CuotasVenta> listCuotAux = cuotaVentaDAO.getLista(true, venta);
				for (VentasDetalleUnidad ventaDetalleUnidad : listaAuxVentaDet) {
					String imei = ventaDetalleUnidad.getNroImei();
					boolean noExiste = true;
					for (CuotasVenta cuota : listCuotAux) {
						if (imei.equals(cuota.getNroImei())) {
							noExiste = false;
						}
					}
					if (noExiste) {
						listaVentasDetalleUnidads.add(ventaDetalleUnidad);
					}
				}	
				for (CuotasVenta cuota : listCuotAux) {
					List<CuotasVentasDetalle> listDet = cuotaVentaDetalleDAO.getLista(true, cuota);
					cuota.setCuotasVentasDetalles(listDet);
					listaCuot.add(cuota);
				}
				SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
				SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
				SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
				Date date;
				int mes = Integer.parseInt(formatoMes.format(new Date()));
				String anio = formatoAnio.format(new Date());		
				mes = mes + 1;
				String fechaFiltro = "";
				if (mes < 10) {
					fechaFiltro = "010" + Integer.toString(mes) + anio;
				} else {
					fechaFiltro = "01" + Integer.toString(mes) + anio;
				}
				date = formato.parse(fechaFiltro);
				listaCuotasVenta = listaCuot;
				listaCuotasVentasDetalles = cuotaVentaDetalleDAO.getListaPorVencer(venta, date);
			} catch (Exception e) {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un Error al generar las cuotas, "
						+ "inténtelo nuevamente mas tarde!, Error original: " + e.getMessage(), null);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Al menos un equipo, el primer venc., cant. cuotas, "
					+ "e intereses son obligatorios!", null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public List<CuotasVentasDetalle> getDetallesDeCuota(CuotasVenta cuotaVen) {
		List<CuotasVentasDetalle> listaAux = new ArrayList<CuotasVentasDetalle>();
		listaAux = cuotaVentaDetalleDAO.getLista(true, cuotaVen);
		return listaAux;
	}
	
	public void pagarCuota(CuotasVentasDetalle cuotaVentaDet) {
		FacesMessage msg = null;
		cuotaVentaDet.setFechaAlta(new Date());
		cuotaVentaDet.setPago(true);
		cuotaVentaDet.setUsuario1(usuario);
		int idCuotDet = cuotaVentaDetalleDAO.update(cuotaVentaDet);
		CuotasVenta cuot = cuotaVentaDet.getCuotasVenta();		
		Cliente cliente = venta.getCliente();
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		CuentaCorriente cuenta = new CuentaCorriente();
		CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
		ccCliente.setCliente(cliente);
		ccCliente.setDetalle("Pago de " + cuot.getEquipo() + " " + cuotaVentaDet.getDescripcion() + " Venta Nro: " + venta.getId());
		ccCliente.setFecha(new Date());
		ccCliente.setHaber(cuotaVentaDet.getMonto());
		ccCliente.setIdMovimiento(idCuotDet);
		ccCliente.setNombreTabla("CuotasVentasDetalle");
		ccCliente.setMonto(cuotaVentaDet.getMonto());
		ccCliente.setUsuario(usuario);
		int idCuentaCorriente = cuenta.insertarCC(ccCliente);
		Caja caja = new Caja();
		caja.setConcepto("Pago de cuota recibido de: " + cliente.getNombreNegocio() + " - Concepto: Pago de " + cuot.getEquipo() + " " + cuotaVentaDet.getDescripcion() + " de Venta Nro " + venta.getId());
		caja.setFecha(new Date());
		caja.setIdMovimiento(idCuotDet);
		caja.setMonto(cuotaVentaDet.getMonto());
		caja.setNombreTabla("CuotasVentasDetalle");
		caja.setUsuario(usuario);
		int idCaja = movimientoCaja.insertarCaja(caja);
		if (idCaja != 0 && idCuentaCorriente != 0 && idCuotDet != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago de Cuota Registrado!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al registrar el pago de la cuota, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void pagoDeCuota(CuotasVentasDetalle cuotaVentaDet) {
		FacesMessage msg = null;
		cuotaVentaDet.setFechaAlta(new Date());
		cuotaVentaDet.setPago(true);
		cuotaVentaDet.setUsuario1(usuario);
		int idCuotDet = cuotaVentaDetalleDAO.update(cuotaVentaDet);
		CuotasVenta cuot = cuotaVentaDet.getCuotasVenta();
		Venta ven = cuot.getVenta();
		
		Cliente cliente = ven.getCliente();
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		CuentaCorriente cuenta = new CuentaCorriente();
		CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
		ccCliente.setCliente(cliente);
		ccCliente.setDetalle("Pago de " + cuot.getEquipo() + " " + cuotaVentaDet.getDescripcion() + " Venta Nro: " + venta.getId());
		ccCliente.setFecha(new Date());
		ccCliente.setHaber(cuotaVentaDet.getMonto());
		ccCliente.setIdMovimiento(idCuotDet);
		ccCliente.setNombreTabla("CuotasVentasDetalle");
		ccCliente.setMonto(cuotaVentaDet.getMonto());
		ccCliente.setUsuario(usuario);
		int idCuentaCorriente = cuenta.insertarCC(ccCliente);
		Caja caja = new Caja();
		caja.setConcepto("Pago de cuota recibido de: " + cliente.getNombreNegocio() + " - Concepto: Pago de " + cuot.getEquipo() + " " + cuotaVentaDet.getDescripcion() + " de Venta Nro " + ven.getId());
		caja.setFecha(new Date());
		caja.setIdMovimiento(idCuotDet);
		caja.setMonto(cuotaVentaDet.getMonto());
		caja.setNombreTabla("CuotasVentasDetalle");
		caja.setUsuario(usuario);
		int idCaja = movimientoCaja.insertarCaja(caja);
		if (idCaja != 0 && idCuentaCorriente != 0 && idCuotDet != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago de Cuota Registrado!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al registrar el pago de la cuota, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void bajaDePago(CuotasVentasDetalle cuotaVentaDetalle) {
		FacesMessage msg = null;
		try {
			cuotaVentaDetalle.setFechaAlta(null);
			cuotaVentaDetalle.setUsuario1(null);
			cuotaVentaDetalle.setPago(false);
			int idCuotaDet = cuotaVentaDetalleDAO.update(cuotaVentaDetalle);
			
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente cuentaAnterior = new CuentasCorrientesCliente();
			cuentaAnterior.setIdMovimiento(cuotaVentaDetalle.getId());
			cuentaAnterior.setNombreTabla("CuotasVentasDetalle");
			int deleteCuenta = cuenta.deleteCuentaCorriente(cuentaAnterior);
			MovimientoCaja movimientoCaja = new MovimientoCaja();
			Caja caja = new Caja();
			caja.setIdMovimiento(cuotaVentaDetalle.getId());
			caja.setNombreTabla("CuotasVentasDetalle");
			int deleteCaja = movimientoCaja.deleteCaja(caja);
			if (idCuotaDet != 0 && deleteCaja != 0 && deleteCuenta != 0) {
				SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
				SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
				SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
				Date date;
				int mes = Integer.parseInt(formatoMes.format(new Date()));
				String anio = formatoAnio.format(new Date());						
				mes = mes + 1;
				String fechaFiltro = "";
				if (mes < 10) {
					fechaFiltro = "010" + Integer.toString(mes) + anio;
				} else {
					fechaFiltro = "01" + Integer.toString(mes) + anio;
				}		
				date = formato.parse(fechaFiltro);
				listaCuotasVentasDetalles = cuotaVentaDetalleDAO.getListaPorVencer(venta, date);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de pago de cuota!", null);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al dar de baja el pago de la Cuota, "
						+ "inténtelo nuevamente!", null);
			}
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo registrar la baja del pago! Error originial: " + e.getMessage(), null);
		}		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void bajaDeCuota(CuotasVenta cuotaVen) {
		FacesMessage msg = null;
		try {
			List<CuotasVentasDetalle> listaAux = cuotaVentaDetalleDAO.getLista(true, cuotaVen);
			for (CuotasVentasDetalle cuotaDetalle : listaAux) {
				if (cuotaDetalle.getPago()) {
					cuotaDetalle.setFechaBaja(new Date());
					cuotaDetalle.setUsuario2(usuario);
					cuotaDetalle.setPago(false);
					cuotaDetalle.setEstado(false);
					cuotaVentaDetalleDAO.update(cuotaDetalle);
					
					CuentaCorriente cuenta = new CuentaCorriente();
					CuentasCorrientesCliente cuentaAnterior = new CuentasCorrientesCliente();
					cuentaAnterior.setIdMovimiento(cuotaDetalle.getId());
					cuentaAnterior.setNombreTabla("CuotasVentasDetalle");
					cuenta.deleteCuentaCorriente(cuentaAnterior);
					MovimientoCaja movimientoCaja = new MovimientoCaja();
					Caja caja = new Caja();
					caja.setIdMovimiento(cuotaDetalle.getId());
					caja.setNombreTabla("CuotasVentasDetalle");
					movimientoCaja.deleteCaja(caja);
				} else {
					cuotaDetalle.setFechaBaja(new Date());
					cuotaDetalle.setUsuario2(usuario);
					cuotaDetalle.setPago(false);
					cuotaDetalle.setEstado(false);
					cuotaVentaDetalleDAO.update(cuotaDetalle);
				}			
			}
			cuotaVen.setEstado(false);
			cuotaVen.setFechaBaja(new Date());
			cuotaVen.setUsuario2(usuario);
			int idCuota = cuotaVentaDAO.update(cuotaVen);
			if (idCuota != 0) {
				SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
				SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
				SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
				Date date;
				int mes = Integer.parseInt(formatoMes.format(new Date()));
				String anio = formatoAnio.format(new Date());						
				mes = mes + 1;
				String fechaFiltro = "";
				if (mes < 10) {
					fechaFiltro = "010" + Integer.toString(mes) + anio;
				} else {
					fechaFiltro = "01" + Integer.toString(mes) + anio;
				}		
				date = formato.parse(fechaFiltro);
				listaCuotasVentasDetalles = cuotaVentaDetalleDAO.getListaPorVencer(venta, date);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de Cuotas!", null);
			} else {
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error al dar de baja la Cuota, "
						+ "inténtelo nuevamente!", null);
			}
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo registrar la baja de Cuota! Error original: " + e.getMessage(), null);
		}		
	}
	
	public void volver() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("ventas.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generarReporte(){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		for (CuotasVenta cuotaV : listaCuotasVenta) {
			Cuotas cuotas = new Cuotas();
			cuotas.setFechaPago(formatoFecha.format(cuotaV.getFechaAlta()));
			cuotas.setEquipo(cuotaV.getEquipo());
			cuotas.setNroImei(cuotaV.getNroImei());
			cuotas.setCantCuotas(Integer.toString(cuotaV.getCantCuotas()));
			cuotas.setInteres(Integer.toString(cuotaV.getInteres()));
			cuotas.setMonto(formatoMonto.format(cuotaV.getMontoTotal()));
			listCuotas.add(cuotas);
		}
		parametros.put("nroVenta", Integer.toString(venta.getId()));
		parametros.put("cliente", venta.getCliente().getNombreNegocio());
		reporte.generar(parametros, listCuotas, "listaCuotas", "inline");
	}
	
	public void generarReportePorVencer(){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		for (CuotasVentasDetalle detalle : listaCuotasVentasDetalles) {
			Cuotas cuotas = new Cuotas();
			cuotas.setNroVenta(Integer.toString(detalle.getCuotasVenta().getVenta().getId()));
			
			cuotas.setCliente(detalle.getCuotasVenta().getVenta().getCliente().getNombreNegocio());
			cuotas.setEquipo(detalle.getCuotasVenta().getEquipo());
			cuotas.setNroImei(detalle.getCuotasVenta().getNroImei());
			cuotas.setCuota(detalle.getDescripcion());
			cuotas.setFechaVencimiento(formatoFecha.format(detalle.getFechaVencimiento()));
			cuotas.setMonto(formatoMonto.format(detalle.getMonto()));
			listCuotas.add(cuotas);
		}
		reporte.generar(parametros, listCuotas, "cuotasventasporvencer", "inline");
	}
	
	public void generarReporteCuotas(CuotasVenta cuotaV){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		for (CuotasVentasDetalle detalle : getDetallesDeCuota(cuotaV)) {
			Cuotas cuotas = new Cuotas();
			if (detalle.getPago()) {
				cuotas.setPago("SI");
			} else {
				cuotas.setPago("NO");
			}
			cuotas.setCuota(detalle.getDescripcion());
			cuotas.setFechaVencimiento(formatoFecha.format(detalle.getFechaVencimiento()));
			cuotas.setMonto(formatoMonto.format(detalle.getMonto()));
			listCuotas.add(cuotas);		
		}
		parametros.put("nroVenta", Integer.toString(cuotaV.getVenta().getId()));
		parametros.put("cliente", cuotaV.getVenta().getCliente().getNombreNegocio());
		parametros.put("equipo", cuotaV.getEquipo());
		parametros.put("nroImei", cuotaV.getNroImei());
		parametros.put("interes", Integer.toString(cuotaV.getInteres()));
		parametros.put("cantCuotas", Integer.toString(cuotaV.getCantCuotas()));
		parametros.put("montoTotal", formatoMonto.format(cuotaV.getMontoTotal()));
		reporte.generar(parametros, listCuotas, "cuotasventas", "attachment");
	}
	
	public void generarReporteComprobante(CuotasVentasDetalle cuotaDetalle){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		Cuotas cuotas = new Cuotas();
		cuotas.setNroVenta(Integer.toString(cuotaDetalle.getCuotasVenta().getVenta().getId()));
		cuotas.setCliente(cuotaDetalle.getCuotasVenta().getVenta().getCliente().getNombreNegocio());
		cuotas.setEquipo(cuotaDetalle.getCuotasVenta().getEquipo());
		cuotas.setNroImei(cuotaDetalle.getCuotasVenta().getNroImei());
		String detalle = cuotaDetalle.getDescripcion() + " de " + Integer.toString(cuotaDetalle.getCuotasVenta().getCantCuotas());
		cuotas.setCuota(detalle);
		cuotas.setFechaVencimiento(formatoFecha.format(cuotaDetalle.getFechaVencimiento()));
		cuotas.setMonto(formatoMonto.format(cuotaDetalle.getMonto()));
		String pago = "";
		String fechaPago = "";
		if (cuotaDetalle.getPago()) {
			pago = "SI";
			fechaPago = formatoFecha.format(cuotaDetalle.getFechaAlta());
		} else {
			pago = "NO";
			fechaPago = " - ";
		}
		cuotas.setPago(pago);
		cuotas.setFechaPago(fechaPago);
		listCuotas.add(cuotas);
		reporte.generar(parametros, listCuotas, "cuotaventa", "attachment");
	}

}
