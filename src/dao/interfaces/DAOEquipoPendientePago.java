package dao.interfaces;

import model.entity.EquipoPendientePago;
import model.entity.PagosCliente;
import model.entity.Cliente;
import java.util.List;

public interface DAOEquipoPendientePago {
	
	public int insert(EquipoPendientePago equipoPendientePago);
	
	public int update(EquipoPendientePago equipoPendientePago);
	
	public int pagar(EquipoPendientePago equipoPendientePago);
	
	public int baja(EquipoPendientePago equipoPendientePago);
	
	public EquipoPendientePago getPorImei(String imei);
	
	public EquipoPendientePago get(String imei, boolean pago, boolean enabled);
	
	public List<EquipoPendientePago> getListaPorCliente(Cliente cliente);
	
	public List<EquipoPendientePago> getListaNoPagosPorCliente(Cliente cliente);
	
	public List<EquipoPendientePago> getListaPagosPorCliente(Cliente cliente);
	
	public List<EquipoPendientePago> getLista(Cliente cliente, PagosCliente pagosCliente, boolean pago, boolean enabled);

}
