package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Mensaje;
import model.entity.Usuario;
import model.entity.VentasCon;
import dao.interfaces.DAOMensaje;

public class DAOMensajeImpl implements Serializable, DAOMensaje {

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

	public int insertar(Mensaje mensaje) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(mensaje);
			tx.commit();
			return mensaje.getId();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback();
			return 0;
		}
	}

	public int update(Mensaje mensaje) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{			
			Query locQuery = em.createQuery("UPDATE Mensaje m SET m.asunto = :pAsunto, m.cliente = :pCliente, m.detalle = :pDetalle, "
					+ "m.fechaHora = :pFechaHora, m.usuario = :pUsuario, m.ventasCon = :pVentasCon, m.visto = :pVisto "
					+ "WHERE m.id = :pId");
			locQuery.setParameter("pAsunto", mensaje.getAsunto());
			locQuery.setParameter("pCliente", mensaje.getCliente());
			locQuery.setParameter("pDetalle", mensaje.getDetalle());
			locQuery.setParameter("pFechaHora", mensaje.getFechaHora());
			locQuery.setParameter("pUsuario", mensaje.getUsuario());
			locQuery.setParameter("pVentasCon", mensaje.getVentasCon());
			locQuery.setParameter("pVisto", mensaje.getVisto());
			locQuery.setParameter("pId", mensaje.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return mensaje.getId();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return 0;
		}
	}
	
	public Mensaje get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mensaje m WHERE m.id = :pId", Mensaje.class);
		locQuery.setParameter("pId", id);
		Mensaje mensaje = new Mensaje();
		try{
			mensaje = (Mensaje) locQuery.getSingleResult();
		}catch (Exception e){
			e.printStackTrace();
			mensaje = new Mensaje();
		}
		return mensaje;
	}

	public List<Mensaje> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mensaje m ORDER BY m.fechaHora DESC", Mensaje.class);
		List<Mensaje> lista = locQuery.getResultList();
		return lista;
	}

	public List<Mensaje> getLista(boolean visto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mensaje m WHERE m.visto = :pVisto ORDER BY m.fechaHora DESC", Mensaje.class);
		locQuery.setParameter("pVisto", visto);
		List<Mensaje> lista = locQuery.getResultList();
		return lista;
	}

	public List<Mensaje> getLista(Usuario usuario) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mensaje m WHERE m.usuario = :pUsuario ORDER BY m.fechaHora DESC", Mensaje.class);
		locQuery.setParameter("pUsuario", usuario);
		List<Mensaje> lista = locQuery.getResultList();
		return lista;
	}

	public List<Mensaje> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mensaje m WHERE m.cliente = :pCliente ORDER BY m.fechaHora DESC", Mensaje.class);
		locQuery.setParameter("pCliente", cliente);
		List<Mensaje> lista = locQuery.getResultList();
		return lista;
	}

	public List<Mensaje> getLista(VentasCon ventasCon) {
		inicializar();
		Query locQuery = em.createQuery("SELECT m FROM Mensaje m WHERE m.ventasCon = :pVentasCon ORDER BY m.fechaHora DESC", Mensaje.class);
		locQuery.setParameter("pVentasCon", ventasCon);
		List<Mensaje> lista = locQuery.getResultList();
		return lista;
	}

}
