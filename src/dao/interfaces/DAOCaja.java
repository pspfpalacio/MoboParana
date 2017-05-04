package dao.interfaces;

import java.util.Date;
import java.util.List;

import model.entity.Caja;

public interface DAOCaja {
	
	public int insertar(Caja caja);
	
	public int update(Caja caja);
	
	public Caja get(int id);
	
	public List<Caja> getLista();
	
	public List<Caja> getListaSinOrden();
	
	public List<Caja> getLista(Date fechaDesde, Date fechaHasta);
	
	public List<Caja> getListaOrdenadaPorFecha(Date fechaDesde, Date fechaHasta);
	
	public List<Caja> getListaOrdenadaParaInsercion(Date fechaDesde, Date fechaHasta);
	
	public Caja get(int idMovimiento, String nombreTabla);
	
	public int delete(Caja caja);

}
