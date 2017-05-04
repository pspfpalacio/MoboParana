package dao.interfaces;

import java.util.List;

import model.entity.Cliente;
import model.entity.ListaPrecio;

public interface DAOCliente {
	
	public int insertar(Cliente cliente);
	
	public int update(Cliente cliente);
	
	public Cliente get(int id);
	
	public List<Cliente> getLista();
	
	public List<Cliente> getLista(boolean estado);
	
	public List<Cliente> getLista(ListaPrecio listaPrecio);
	
	public List<Cliente> getLista(boolean estado, ListaPrecio listaPrecio);

}
