package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Role;
import model.entity.Usuario;
import dao.interfaces.DAOUsuario;

public class DAOUsuarioImpl implements Serializable, DAOUsuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public void inicializar(){
		emf = Persistence.createEntityManagerFactory("MoboParana");
		em = emf.createEntityManager();
	}
	
	public void cerrarInstancia() {
		em.close();
		emf.close();
	}

	public int insertar(Usuario usuario) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(usuario);
			tx.commit();
			return usuario.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Usuario usuario) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Usuario u SET u.apellidoNombre = :pApellidoNombre, u.cliente = :pCliente, "
					+ "u.estado = :pEstado, u.fechaAlta = :pFechaAlta, u.fechaBaja = :pFechaBaja, u.fechaMod = :pFechaMod, "
					+ "u.password = :pPassword, u.role = :pRole, u.username = :pUsername, u.usuario1 = :pUsuarioAlta, "
					+ "u.usuario2 = :pUsuarioBaja, u.usuario3 = :pUsuarioMod "
					+ "WHERE u.id = :pId");
			locQuery.setParameter("pApellidoNombre", usuario.getApellidoNombre());
			locQuery.setParameter("pCliente", usuario.getCliente());
			locQuery.setParameter("pEstado", usuario.getEstado());
			locQuery.setParameter("pFechaAlta", usuario.getFechaAlta());
			locQuery.setParameter("pFechaBaja", usuario.getFechaBaja());
			locQuery.setParameter("pFechaMod", usuario.getFechaMod());
			locQuery.setParameter("pPassword", usuario.getPassword());
			locQuery.setParameter("pRole", usuario.getRole());
			locQuery.setParameter("pUsername", usuario.getUsername());
			locQuery.setParameter("pUsuarioAlta", usuario.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", usuario.getUsuario2());
			locQuery.setParameter("pUsuarioMod", usuario.getUsuario3());
			locQuery.setParameter("pId", usuario.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return usuario.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Usuario get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.id = :pId", Usuario.class);
		locQuery.setParameter("pId", id);
		Usuario usuario = new Usuario();
		try{
			usuario = (Usuario) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			usuario = new Usuario();
		}
		return usuario;
	}

	public Usuario get(String username, String password) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :pUsername AND u.password = :pPassword AND u.estado = :pEstado", Usuario.class);
		locQuery.setParameter("pUsername", username);
		locQuery.setParameter("pPassword", password);
		locQuery.setParameter("pEstado", true);
		Usuario usuario = new Usuario();
		try{
			usuario = (Usuario) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			usuario = new Usuario();
		}
		return usuario;
	}

	public List<Usuario> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.id != 1", Usuario.class);
		List<Usuario> lista = locQuery.getResultList();
		return lista;
	}

	public List<Usuario> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.estado = :pEstado AND u.id != 1", Usuario.class);
		locQuery.setParameter("pEstado", estado);
		List<Usuario> lista = locQuery.getResultList();
		return lista;
	}

	public List<Usuario> getLista(Role role) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.role = :pRole AND u.id != 1", Usuario.class);
		locQuery.setParameter("pRole", role);
		List<Usuario> lista = locQuery.getResultList();
		return lista;
	}

	public List<Usuario> getLista(boolean estado, Role role) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM Usuario u WHERE u.estado = :pEstado AND u.role = :pRole AND u.id != 1", Usuario.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pRole", role);
		List<Usuario> lista = locQuery.getResultList();
		return lista;
	}

}
