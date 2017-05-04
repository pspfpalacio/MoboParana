package dao.interfaces;

import java.util.List;

import model.entity.Stock;
import model.entity.StocksVentasDetalle;
import model.entity.VentasDetalle;

public interface DAOStockVentaDetalle {
	
	public int insertar(StocksVentasDetalle stocksVentasDetalle);
	
	public int update(StocksVentasDetalle stocksVentasDetalle);
	
	public int delete(StocksVentasDetalle stocksVentasDetalle);
	
	public int delete(VentasDetalle ventasDetalle);
	
	public StocksVentasDetalle get(int id);
	
	public StocksVentasDetalle get(Stock stock, VentasDetalle ventasDetalle);
	
	public List<StocksVentasDetalle> getLista();
	
	public List<StocksVentasDetalle> getLista(Stock stock);
	
	public List<StocksVentasDetalle> getLista(VentasDetalle ventasDetalle);

}
