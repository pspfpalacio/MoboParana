package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.ListaPrecio;
import dao.interfaces.DAOCliente;

public class DAOClienteImpl implements Serializable, DAOCliente {

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

	public int insertar(Cliente cliente) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(cliente);
			tx.commit();
			return cliente.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Cliente cliente) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Cliente c SET c.apellidoNombre = :pApellidoNombre, c.direccion = :pDireccion, "
					+ "c.documento = :pDocumento, c.email = :pEmail, c.estado = :pEstado, c.fechaAlta = :pFechaAlta, c.fechaBaja = :pFechaBaja, "
					+ "c.fechaMod = :pFechaMod, c.listaPrecio = :pListaPrecio, c.localidad = :pLocalidad, c.nombreNegocio = :pNombreNegocio, "
					+ "c.saldo = :pSaldo, c.telefono = :pTelefono, "
					+ "c.tipoDocumento = :pTipoDocumento, c.usuario1 = :pUsuarioAlta, c.usuario2 = :pUsuarioBaja, c.usuario3 = :pUsuarioMod "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pApellidoNombre", cliente.getApellidoNombre());
			locQuery.setParameter("pDireccion", cliente.getDireccion());
			locQuery.setParameter("pDocumento", cliente.getDocumento());
			locQuery.setParameter("pEmail", cliente.getEmail());
			locQuery.setParameter("pEstado", cliente.getEstado());
			locQuery.setParameter("pFechaAlta", cliente.getFechaAlta());
			locQuery.setParameter("pFechaBaja", cliente.getFechaBaja());
			locQuery.setParameter("pFechaMod", cliente.getFechaMod());
			locQuery.setParameter("pListaPrecio", cliente.getListaPrecio());
			locQuery.setParameter("pLocalidad", cliente.getLocalidad());
			locQuery.setParameter("pNombreNegocio", cliente.getNombreNegocio());
			locQuery.setParameter("pSaldo", cliente.getSaldo());
			locQuery.setParameter("pTelefono", cliente.getTelefono());
			locQuery.setParameter("pTipoDocumento", cliente.getTipoDocumento());
			locQuery.setParameter("pUsuarioAlta", cliente.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", cliente.getUsuario2());
			locQuery.setParameter("pUsuarioMod", cliente.getUsuario3());
			locQuery.setParameter("pId", cliente.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cliente.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Cliente get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cliente c WHERE c.id = :pId", Cliente.class);
		locQuery.setParameter("pId", id);
		Cliente cliente = new Cliente();
		try{
			cliente = (Cliente) locQuery.getSingleResult();
		} catch (Exception e) {
			System.out.println("getCliente " + e.getMessage());
			cliente = new Cliente();
		}
		return cliente;
	}

	public List<Cliente> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		List<Cliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cliente> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cliente c WHERE c.estado = :pEstado", Cliente.class);
		locQuery.setParameter("pEstado", estado);
		List<Cliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cliente> getLista(ListaPrecio listaPrecio) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cliente c WHERE c.listaPrecio = :pListaPrecio", Cliente.class);
		locQuery.setParameter("pListaPrecio", listaPrecio);
		List<Cliente> lista = locQuery.getResultList();
		return lista;
	}

	public List<Cliente> getLista(boolean estado, ListaPrecio listaPrecio) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cliente c WHERE c.estado = :pEstado AND c.listaPrecio = :pListaPrecio", Cliente.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pListaPrecio", listaPrecio);
		List<Cliente> lista = locQuery.getResultList();
		return lista;
	}

}
