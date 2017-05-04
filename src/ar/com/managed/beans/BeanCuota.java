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
import model.entity.Consignacion;
import model.entity.ConsignacionsDetalleUnidad;
import model.entity.CuentasCorrientesCliente;
import model.entity.Cuota;
import model.entity.CuotasDetalle;
import model.entity.EntregaConsignacion;
import model.entity.UnidadMovil;
import model.entity.Usuario;
import dao.interfaces.DAOConsignacion;
import dao.interfaces.DAOConsignacionDetalle;
import dao.interfaces.DAOConsignacionDetalleUnidad;
import dao.interfaces.DAOCuota;
import dao.interfaces.DAOCuotaDetalle;
import dao.interfaces.DAOEntregaConsignacion;
import dao.interfaces.DAOProducto;
import dao.interfaces.DAOUnidadMovil;

@ManagedBean
@SessionScoped
public class BeanCuota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BeanCuotaDAO}")
	private DAOCuota cuotaDAO;
	
	@ManagedProperty(value = "#{BeanCuotaDetalleDAO}")
	private DAOCuotaDetalle cuotaDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDAO}")
	private DAOConsignacion consignacionDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleDAO}")
	private DAOConsignacionDetalle consignacionDetalleDAO;
	
	@ManagedProperty(value = "#{BeanConsignacionDetalleUnidadDAO}")
	private DAOConsignacionDetalleUnidad consignacionDetalleUnidadDAO;
	
	@ManagedProperty(value = "#{BeanProductoDAO}")
	private DAOProducto productoDAO;
	
	@ManagedProperty(value = "#{BeanUnidadMovilDAO}")
	private DAOUnidadMovil unidadMovilDAO;
	
	@ManagedProperty(value = "#{BeanEntregaConsignacionDAO}")
	private DAOEntregaConsignacion entregaConsignacionDAO;
	
	private List<Cuota> listaCuotas;
	private List<CuotasDetalle> listaCuotasDetalles;
	private List<ConsignacionsDetalleUnidad> listaConsignacionsDetalleUnidads;
	private List<String> selectedMoviles;
	private Cuota cuota;
	private Usuario usuario;
	private Consignacion consignacion;
	private Date primerVencimiento;
	private int cantCuota;
	private int interes;

	public DAOCuota getCuotaDAO() {
		return cuotaDAO;
	}

	public void setCuotaDAO(DAOCuota cuotaDAO) {
		this.cuotaDAO = cuotaDAO;
	}

	public DAOCuotaDetalle getCuotaDetalleDAO() {
		return cuotaDetalleDAO;
	}

	public void setCuotaDetalleDAO(DAOCuotaDetalle cuotaDetalleDAO) {
		this.cuotaDetalleDAO = cuotaDetalleDAO;
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

	public DAOEntregaConsignacion getEntregaConsignacionDAO() {
		return entregaConsignacionDAO;
	}

	public void setEntregaConsignacionDAO(
			DAOEntregaConsignacion entregaConsignacionDAO) {
		this.entregaConsignacionDAO = entregaConsignacionDAO;
	}

	public List<Cuota> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<Cuota> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public List<CuotasDetalle> getListaCuotasDetalles() {
		return listaCuotasDetalles;
	}

	public void setListaCuotasDetalles(List<CuotasDetalle> listaCuotasDetalles) {
		this.listaCuotasDetalles = listaCuotasDetalles;
	}

	public List<ConsignacionsDetalleUnidad> getListaConsignacionsDetalleUnidads() {
		return listaConsignacionsDetalleUnidads;
	}

	public void setListaConsignacionsDetalleUnidads(
			List<ConsignacionsDetalleUnidad> listaConsignacionsDetalleUnidads) {
		this.listaConsignacionsDetalleUnidads = listaConsignacionsDetalleUnidads;
	}

	public List<String> getSelectedMoviles() {
		return selectedMoviles;
	}

	public void setSelectedMoviles(List<String> selectedMoviles) {
		this.selectedMoviles = selectedMoviles;
	}

	public Cuota getCuota() {
		return cuota;
	}

	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Consignacion getConsignacion() {
		return consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
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
	
	public String goCuota(Consignacion consig, Usuario user) {
		try{
			consignacion = new Consignacion();
			usuario = new Usuario();
			consignacion = consig;
			usuario = user;
			List<ConsignacionsDetalleUnidad> listaAuxConsDet = new ArrayList<ConsignacionsDetalleUnidad>();
			listaAuxConsDet = consignacionDetalleUnidadDAO.getLista(consig, true);
			List<Cuota> listCuot = new ArrayList<Cuota>();
			List<Cuota> listaAuxCuot = new ArrayList<Cuota>();
			listaAuxCuot = cuotaDAO.getLista(true, consig);
			listaConsignacionsDetalleUnidads = new ArrayList<ConsignacionsDetalleUnidad>();
			listaCuotas = new ArrayList<Cuota>();
			listaCuotasDetalles = new ArrayList<CuotasDetalle>();
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
			listaCuotasDetalles = cuotaDetalleDAO.getListaPorVencer(consig, date);
			for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listaAuxConsDet) {
				String imei = consignacionsDetalleUnidad.getNroImei();
				boolean noExiste = true;
				for (Cuota cuota : listaAuxCuot) {
					if (imei.equals(cuota.getNroImei())) {
						noExiste = false;
					}
				}
				if (noExiste) {
					listaConsignacionsDetalleUnidads.add(consignacionsDetalleUnidad);
				}
			}
			for (Cuota cuota : listaAuxCuot) {
				List<CuotasDetalle> listDet = cuotaDetalleDAO.getLista(true, cuota);
				cuota.setCuotasDetalles(listDet);
				listCuot.add(cuota);
			}
			listaCuotas = listCuot;
			primerVencimiento = null;
			cantCuota = 0;
			interes = 0;
			selectedMoviles = new ArrayList<String>();

			return "cuotas";
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
			listaCuotasDetalles = new ArrayList<CuotasDetalle>();
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
			listaCuotasDetalles = cuotaDetalleDAO.getListaPorVencer(date);			
			return "vercuotas";
		} catch (Exception e){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No es posible acceder a la vista de Cuotas, Inténtelo nuevamente!", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}		
	}
	
	public void verDetalle(Cuota cuot) {
		cuota = new Cuota();
		cuota = cuot;
	}
	
	public void generarCuotas() {
		FacesMessage msg = null;
		if (!selectedMoviles.isEmpty() && primerVencimiento != null && cantCuota != 0) {
			try {
				List<Cuota> listaAuxCuota = new ArrayList<Cuota>();
				for (String imei : selectedMoviles) {
					UnidadMovil unidad = unidadMovilDAO.get(imei);
					ConsignacionsDetalleUnidad consignacionUnidad = consignacionDetalleUnidadDAO.get(imei);
					Cuota cuo = new Cuota();
					cuo.setCantCuotas(cantCuota);
					cuo.setConsignacion(consignacion);
					cuo.setEquipo(unidad.getProducto().getNombre());
					cuo.setEstado(true);
					cuo.setFechaAlta(new Date());
					cuo.setUsuario1(usuario);
					cuo.setInteres(interes);
					cuo.setNroImei(imei);
					float montoTotal = consignacionUnidad.getPrecioVenta();
					float montoPorcen = 0;
					if (interes != 0) {
						montoPorcen = (montoTotal * interes)/100;
					}
					montoTotal = montoTotal + montoPorcen;
					cuo.setMontoTotal(montoTotal);
					int idCuota = cuotaDAO.insertar(cuo);
					cuo.setId(idCuota);
					listaAuxCuota.add(cuo);
				}		
				
				for (Cuota cuot : listaAuxCuota) {
					int cant = cuot.getCantCuotas();
					float monto = cuot.getMontoTotal()/cant;
					SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
					String sigVencimiento = formato.format(primerVencimiento);
					for (int i = 0; i < cant; i++) {
						Date fechaVenc;
						fechaVenc = formato.parse(sigVencimiento);
						CuotasDetalle cuotaDetalle = new CuotasDetalle();
						cuotaDetalle.setCuota(cuot);
						int nroCuota = i + 1;
						cuotaDetalle.setDescripcion("Cuota " + nroCuota);
						cuotaDetalle.setEstado(true);
						cuotaDetalle.setFechaVencimiento(fechaVenc);
						cuotaDetalle.setMonto(monto);
						cuotaDetalle.setPago(false);
						cuotaDetalle.setUsuario1(usuario);
						cuotaDetalleDAO.insertar(cuotaDetalle);		
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
				List<ConsignacionsDetalleUnidad> listaAuxConsDet = new ArrayList<ConsignacionsDetalleUnidad>();
				listaAuxConsDet = consignacionDetalleUnidadDAO.getLista(consignacion, true);
				primerVencimiento = null;
				cantCuota = 0;
				interes = 0;
				selectedMoviles = new ArrayList<String>();
				listaCuotas = new ArrayList<Cuota>();
				listaCuotasDetalles = new ArrayList<CuotasDetalle>();
				listaConsignacionsDetalleUnidads = new ArrayList<ConsignacionsDetalleUnidad>();
				List<Cuota> listaCuot = new ArrayList<Cuota>(); 
				List<Cuota> listCuotAux = cuotaDAO.getLista(true, consignacion);
				for (ConsignacionsDetalleUnidad consignacionsDetalleUnidad : listaAuxConsDet) {
					String imei = consignacionsDetalleUnidad.getNroImei();
					boolean noExiste = true;
					for (Cuota cuota : listCuotAux) {
						if (imei.equals(cuota.getNroImei())) {
							noExiste = false;
						}
					}
					if (noExiste) {
						listaConsignacionsDetalleUnidads.add(consignacionsDetalleUnidad);
					}
				}	
				for (Cuota cuota : listCuotAux) {
					List<CuotasDetalle> listDet = cuotaDetalleDAO.getLista(true, cuota);
					cuota.setCuotasDetalles(listDet);
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
				listaCuotas = listaCuot;
				listaCuotasDetalles = cuotaDetalleDAO.getListaPorVencer(consignacion, date);
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
	
	public List<CuotasDetalle> getDetallesDeCuota(Cuota cuo) {
		List<CuotasDetalle> listaAux = new ArrayList<CuotasDetalle>();
		listaAux = cuotaDetalleDAO.getLista(true, cuo);
		return listaAux;
	}
	
	public void pagarCuota(CuotasDetalle cuotaDet) {
		FacesMessage msg = null;
		cuotaDet.setFechaAlta(new Date());
		cuotaDet.setPago(true);
		cuotaDet.setUsuario1(usuario);
		int idCuotDet = cuotaDetalleDAO.update(cuotaDet);
		Cuota cuot = cuotaDet.getCuota();
		EntregaConsignacion entrega = new EntregaConsignacion();
		entrega.setConcepto("Pago de " + cuot.getEquipo() + " " + cuotaDet.getDescripcion());
		entrega.setConsignacion(consignacion);
		entrega.setCuota(true);
		entrega.setCuotasDetalle(cuotaDet);
		entrega.setEstado(true);
		entrega.setFecha(new Date());
		entrega.setMonto(cuotaDet.getMonto());
		int idEntrega = entregaConsignacionDAO.insertar(entrega);
		entrega.setId(idEntrega);
		Cliente cliente = consignacion.getCliente();
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		CuentaCorriente cuenta = new CuentaCorriente();
		CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
		ccCliente.setCliente(cliente);
		ccCliente.setDetalle("Pago de " + cuot.getEquipo() + " " + cuotaDet.getDescripcion() + " Consignacion Nro: " + consignacion.getId());
		ccCliente.setFecha(new Date());
		ccCliente.setHaber(cuotaDet.getMonto());
		ccCliente.setIdMovimiento(idCuotDet);
		ccCliente.setNombreTabla("CuotasDetalle");
		ccCliente.setMonto(cuotaDet.getMonto());
		ccCliente.setUsuario(usuario);
		int idCuentaCorriente = cuenta.insertarCC(ccCliente);
		Caja caja = new Caja();
		caja.setConcepto("Pago de cuota recibido de: " + cliente.getNombreNegocio() + " - Concepto: Pago de " + cuot.getEquipo() + " " + cuotaDet.getDescripcion() + " de Consignacion Nro " + consignacion.getId());
		caja.setFecha(new Date());
		caja.setIdMovimiento(idCuotDet);
		caja.setMonto(cuotaDet.getMonto());
		caja.setNombreTabla("CuotasDetalle");
		caja.setUsuario(usuario);
		int idCaja = movimientoCaja.insertarCaja(caja);
		if (idCaja != 0 && idCuentaCorriente != 0 && idCuotDet != 0
				&& idEntrega != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago de Cuota Registrado!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al registrar el pago de la cuota, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void pagoDeCuota(CuotasDetalle cuotaDet) {
		FacesMessage msg = null;
		cuotaDet.setFechaAlta(new Date());
		cuotaDet.setPago(true);
		cuotaDet.setUsuario1(usuario);
		int idCuotDet = cuotaDetalleDAO.update(cuotaDet);
		Cuota cuot = cuotaDet.getCuota();
		Consignacion consig = cuot.getConsignacion();
		EntregaConsignacion entrega = new EntregaConsignacion();
		entrega.setConcepto("Pago de " + cuot.getEquipo() + " " + cuotaDet.getDescripcion());
		entrega.setConsignacion(consig);
		entrega.setCuota(true);
		entrega.setCuotasDetalle(cuotaDet);
		entrega.setEstado(true);
		entrega.setFecha(new Date());
		entrega.setMonto(cuotaDet.getMonto());
		int idEntrega = entregaConsignacionDAO.insertar(entrega);
		entrega.setId(idEntrega);
		Cliente cliente = consig.getCliente();
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		CuentaCorriente cuenta = new CuentaCorriente();
		CuentasCorrientesCliente ccCliente = new CuentasCorrientesCliente();
		ccCliente.setCliente(cliente);
		ccCliente.setDetalle("Pago de " + cuot.getEquipo() + " " + cuotaDet.getDescripcion() + " Consignacion Nro: " + consig.getId());
		ccCliente.setFecha(new Date());
		ccCliente.setHaber(cuotaDet.getMonto());
		ccCliente.setIdMovimiento(idCuotDet);
		ccCliente.setNombreTabla("CuotasDetalle");
		ccCliente.setMonto(cuotaDet.getMonto());
		ccCliente.setUsuario(usuario);
		int idCuentaCorriente = cuenta.insertarCC(ccCliente);
		Caja caja = new Caja();
		caja.setConcepto("Pago de cuota recibido de: " + cliente.getNombreNegocio() + " - Concepto: Pago de " + cuot.getEquipo() + " " + cuotaDet.getDescripcion() + " de Consignacion Nro " + consig.getId());
		caja.setFecha(new Date());
		caja.setIdMovimiento(idCuotDet);
		caja.setMonto(cuotaDet.getMonto());
		caja.setNombreTabla("CuotasDetalle");
		caja.setUsuario(usuario);
		int idCaja = movimientoCaja.insertarCaja(caja);
		if (idCaja != 0 && idCuentaCorriente != 0 && idCuotDet != 0
				&& idEntrega != 0) {
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago de Cuota Registrado!", null);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al registrar el pago de la cuota, "
					+ "inténtelo nuevamente!", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void bajaDePago(CuotasDetalle cuotaDetalle) {
		FacesMessage msg = null;
		try {
			cuotaDetalle.setFechaAlta(null);
			cuotaDetalle.setUsuario1(null);
			cuotaDetalle.setPago(false);
			int idCuotaDet = cuotaDetalleDAO.update(cuotaDetalle);
			EntregaConsignacion entrega = entregaConsignacionDAO.get(cuotaDetalle);
			entrega.setEstado(false);
			int idEntrega = entregaConsignacionDAO.update(entrega);
			CuentaCorriente cuenta = new CuentaCorriente();
			CuentasCorrientesCliente cuentaAnterior = new CuentasCorrientesCliente();
			cuentaAnterior.setIdMovimiento(cuotaDetalle.getId());
			cuentaAnterior.setNombreTabla("CuotasDetalle");
			int deleteCuenta = cuenta.deleteCuentaCorriente(cuentaAnterior);
			MovimientoCaja movimientoCaja = new MovimientoCaja();
			Caja caja = new Caja();
			caja.setIdMovimiento(cuotaDetalle.getId());
			caja.setNombreTabla("CuotasDetalle");
			int deleteCaja = movimientoCaja.deleteCaja(caja);
			if (idCuotaDet != 0 && idEntrega != 0 && deleteCaja != 0 && deleteCuenta != 0) {
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
				listaCuotasDetalles = cuotaDetalleDAO.getListaPorVencer(consignacion, date);
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
	
	public void bajaDeCuota(Cuota cuot) {
		FacesMessage msg = null;
		try {
			List<CuotasDetalle> listaAux = cuotaDetalleDAO.getLista(true, cuot);
			for (CuotasDetalle cuotaDetalle : listaAux) {
				if (cuotaDetalle.getPago()) {
					cuotaDetalle.setFechaBaja(new Date());
					cuotaDetalle.setUsuario2(usuario);
					cuotaDetalle.setPago(false);
					cuotaDetalle.setEstado(false);
					cuotaDetalleDAO.update(cuotaDetalle);
					EntregaConsignacion entrega = entregaConsignacionDAO.get(cuotaDetalle);
					entrega.setEstado(false);
					entregaConsignacionDAO.update(entrega);
					CuentaCorriente cuenta = new CuentaCorriente();
					CuentasCorrientesCliente cuentaAnterior = new CuentasCorrientesCliente();
					cuentaAnterior.setIdMovimiento(cuotaDetalle.getId());
					cuentaAnterior.setNombreTabla("CuotasDetalle");
					cuenta.deleteCuentaCorriente(cuentaAnterior);
					MovimientoCaja movimientoCaja = new MovimientoCaja();
					Caja caja = new Caja();
					caja.setIdMovimiento(cuotaDetalle.getId());
					caja.setNombreTabla("CuotasDetalle");
					movimientoCaja.deleteCaja(caja);
				} else {
					cuotaDetalle.setFechaBaja(new Date());
					cuotaDetalle.setUsuario2(usuario);
					cuotaDetalle.setPago(false);
					cuotaDetalle.setEstado(false);
					cuotaDetalleDAO.update(cuotaDetalle);
				}			
			}
			cuot.setEstado(false);
			cuot.setFechaBaja(new Date());
			cuot.setUsuario2(usuario);
			int idCuota = cuotaDAO.update(cuot);
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
				listaCuotasDetalles = cuotaDetalleDAO.getListaPorVencer(consignacion, date);
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
			FacesContext.getCurrentInstance().getExternalContext().redirect("consignaciones.xhtml");
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
		for (Cuota cuot : listaCuotas) {
			Cuotas cuotas = new Cuotas();
			cuotas.setFechaPago(formatoFecha.format(cuot.getFechaAlta()));
			cuotas.setEquipo(cuot.getEquipo());
			cuotas.setNroImei(cuot.getNroImei());
			cuotas.setCantCuotas(Integer.toString(cuot.getCantCuotas()));
			cuotas.setInteres(Integer.toString(cuot.getInteres()));
			cuotas.setMonto(formatoMonto.format(cuot.getMontoTotal()));
			listCuotas.add(cuotas);
		}
		parametros.put("nroConsignacion", Integer.toString(consignacion.getId()));
		parametros.put("cliente", consignacion.getCliente().getNombreNegocio());
		reporte.generar(parametros, listCuotas, "listaCuotas", "inline");
	}
	
	public void generarReportePorVencer(){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		for (CuotasDetalle detalle : listaCuotasDetalles) {
			Cuotas cuotas = new Cuotas();
			cuotas.setNroConsignacion(Integer.toString(detalle.getCuota().getConsignacion().getId()));
			cuotas.setCliente(detalle.getCuota().getConsignacion().getCliente().getNombreNegocio());
			cuotas.setEquipo(detalle.getCuota().getEquipo());
			cuotas.setNroImei(detalle.getCuota().getNroImei());
			cuotas.setCuota(detalle.getDescripcion());
			cuotas.setFechaVencimiento(formatoFecha.format(detalle.getFechaVencimiento()));
			cuotas.setMonto(formatoMonto.format(detalle.getMonto()));
			listCuotas.add(cuotas);
		}
		reporte.generar(parametros, listCuotas, "cuotasporvencer", "inline");
	}
	
	public void generarReporteCuotas(Cuota cuot){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		for (CuotasDetalle detalle : getDetallesDeCuota(cuot)) {
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
		parametros.put("nroConsignacion", Integer.toString(cuot.getConsignacion().getId()));
		parametros.put("cliente", cuot.getConsignacion().getCliente().getNombreNegocio());
		parametros.put("equipo", cuot.getEquipo());
		parametros.put("nroImei", cuot.getNroImei());
		parametros.put("interes", Integer.toString(cuot.getInteres()));
		parametros.put("cantCuotas", Integer.toString(cuot.getCantCuotas()));
		parametros.put("montoTotal", formatoMonto.format(cuot.getMontoTotal()));
		reporte.generar(parametros, listCuotas, "cuotas", "attachment");
	}
	
	public void generarReporteComprobante(CuotasDetalle cuotDetalle){
		Reporte reporte = new Reporte();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat formatoMonto = new DecimalFormat("##,##0.00");
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<Cuotas> listCuotas = new ArrayList<Cuotas>();
		Cuotas cuotas = new Cuotas();
		cuotas.setNroConsignacion(Integer.toString(cuotDetalle.getCuota().getConsignacion().getId()));
		cuotas.setCliente(cuotDetalle.getCuota().getConsignacion().getCliente().getNombreNegocio());
		cuotas.setEquipo(cuotDetalle.getCuota().getEquipo());
		cuotas.setNroImei(cuotDetalle.getCuota().getNroImei());
		String detalle = cuotDetalle.getDescripcion() + " de " + Integer.toString(cuotDetalle.getCuota().getCantCuotas());
		cuotas.setCuota(detalle);
		cuotas.setFechaVencimiento(formatoFecha.format(cuotDetalle.getFechaVencimiento()));
		cuotas.setMonto(formatoMonto.format(cuotDetalle.getMonto()));
		String pago = "";
		String fechaPago = "";
		if (cuotDetalle.getPago()) {
			pago = "SI";
			fechaPago = formatoFecha.format(cuotDetalle.getFechaAlta());
		} else {
			pago = "NO";
			fechaPago = " - ";
		}
		cuotas.setPago(pago);
		cuotas.setFechaPago(fechaPago);
		listCuotas.add(cuotas);
		reporte.generar(parametros, listCuotas, "cuota", "attachment");
	}

}
