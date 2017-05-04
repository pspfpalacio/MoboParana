package ar.com.clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import model.entity.Caja;
import dao.impl.DAOCajaImpl;
import dao.interfaces.DAOCaja;

public class MovimientoCaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DAOCaja cajaDAO = new DAOCajaImpl();

	public DAOCaja getCajaDAO() {
		return cajaDAO;
	}

	public void setCajaDAO(DAOCaja cajaDAO) {
		this.cajaDAO = cajaDAO;
	}
	
	@SuppressWarnings("deprecation")
	public int insertarCaja(Caja movimiento){
		try{
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaFinal = new Date();			
			String fechaIni = formato.format(movimiento.getFecha());
			Date fechaPrincipio = formato.parse(fechaIni);	
			int month = fechaPrincipio.getMonth();
			month = month - 1;		
			fechaPrincipio.setMonth(month);
			List<Caja> listAux = cajaDAO.getListaOrdenadaParaInsercion(fechaPrincipio, fechaFinal);
			List<Caja> listAux2 = new ArrayList<Caja>();
			Caja cajaAnterior = new Caja();
			for (Caja caja : listAux) {
				//primera menor a la segunda: -1
				//primera es mayor a la segunda: 1
				//iguales: 0
				String inicio = formato.format(caja.getFecha());
				Date fechaCuenta2 = formato.parse(inicio);
				fechaCuenta2.setHours(0);
				fechaCuenta2.setMinutes(0);
				fechaCuenta2.setSeconds(0);
				int comparaFecha = fechaCuenta2.compareTo(movimiento.getFecha());
				if(comparaFecha > 0){
					listAux2.add(caja);
				}else{
					cajaAnterior = new Caja();
					cajaAnterior = caja;
				}
			}
			float saldo = cajaAnterior.getSaldo() + movimiento.getMonto();
			movimiento.setSaldo(saldo);
			if(cajaDAO.insertar(movimiento) != 0){
				boolean update = true;
				for (Caja caja : listAux2) {
					float sldo = caja.getSaldo() + movimiento.getMonto();
					caja.setSaldo(sldo);
					if(cajaDAO.update(caja) == 0){
						update = false;
					}
				}
				if(update){
					return 1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deleteCaja(Caja caja){
		try{
			Date fechaHasta = new Date();	
			Caja caja2 = cajaDAO.get(caja.getIdMovimiento(), caja.getNombreTabla());
			float monto = (-1) * caja2.getMonto();
			List<Caja> listAux = cajaDAO.getListaOrdenadaParaInsercion(caja2.getFecha(), fechaHasta);
			List<Caja> listAux2 = new ArrayList<Caja>();
			boolean update = true;
			boolean pasoRegistro = false;
			for (Caja caja3 : listAux) {
				if(caja2.getId() != caja3.getId()){
					if(pasoRegistro){
						listAux2.add(caja3);
					}
				}else{
					pasoRegistro = true;
				}
			}
			for (Caja caja3 : listAux2) {
				float saldo = caja3.getSaldo() + monto;
				caja3.setSaldo(saldo);
				if(cajaDAO.update(caja3) == 0){
					update = false;
				}
			}
			if(update){
				if(cajaDAO.delete(caja2) != 0){
					return 1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int upgradeDeleteCaja(Caja caja){
		try{
			Date fechaHasta = new Date();	
			Caja caja2 = cajaDAO.get(caja.getIdMovimiento(), caja.getNombreTabla());
			float monto = (-1) * caja2.getMonto();
			List<Caja> listAux = cajaDAO.getListaOrdenadaParaInsercion(caja2.getFecha(), fechaHasta);
			boolean update = true;
			for (Caja caja3 : listAux) {
				float saldo = caja3.getSaldo() + monto;
				caja3.setSaldo(saldo);
				if(cajaDAO.update(caja3) == 0){
					update = false;
				}
			}
			if(update){
				if(cajaDAO.delete(caja2) != 0){
					return 1;
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

}
