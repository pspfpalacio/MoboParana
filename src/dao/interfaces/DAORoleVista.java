package dao.interfaces;

import java.util.List;

import model.entity.Role;
import model.entity.RolesVista;
import model.entity.Vista;

public interface DAORoleVista {
	
	public int insertar(RolesVista roleVista);
	
	public int update(RolesVista roleVista);
	
	public RolesVista get(int id);
	
	public List<RolesVista> getLista();
	
	public List<RolesVista> getLista(Role role);
	
	public List<RolesVista> getLista(Vista vista);
	
	public int deleteVistasPorRol(Role role);

}
