package dao.interfaces;

import java.util.List;

import model.entity.Cliente;
import model.entity.Mensaje;
import model.entity.Usuario;
import model.entity.VentasCon;

public interface DAOMensaje {
	
	public int insertar(Mensaje mensaje);
	
	public int update(Mensaje mensaje);
	
	public Mensaje get(int id);
	
	public List<Mensaje> getLista();
	
	public List<Mensaje> getLista(boolean visto);
	
	public List<Mensaje> getLista(Usuario usuario);
	
	public List<Mensaje> getLista(Cliente cliente);
	
	public List<Mensaje> getLista(VentasCon ventasCon);

}
