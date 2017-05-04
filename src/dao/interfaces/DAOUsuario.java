package dao.interfaces;

import java.util.List;

import model.entity.Role;
import model.entity.Usuario;

public interface DAOUsuario {
	
	public int insertar(Usuario usuario);
	
	public int update(Usuario usuario);
	
	public Usuario get(int id);
	
	public Usuario get(String username, String password);
	
	public List<Usuario> getLista();
	
	public List<Usuario> getLista(boolean estado);
	
	public List<Usuario> getLista(Role role);
	
	public List<Usuario> getLista(boolean estado, Role role);

}
