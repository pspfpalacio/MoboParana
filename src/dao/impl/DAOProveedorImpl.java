package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Proveedore;
import dao.interfaces.DAOProveedor;

public class DAOProveedorImpl implements Serializable, DAOProveedor {

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

	public int insertar(Proveedore proveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(proveedore);
			tx.commit();
			return proveedore.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Proveedore proveedore) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Proveedore p SET p.apellidoNombre = :pApellidoNombre, p.banco = :pBanco, "
					+ "p.direccion = :pDireccion, p.documento = :pDocumento, p.email = :pEmail, p.estado = :pEstado, "
					+ "p.fechaAlta = :pFechaAlta, p.fechaBaja = :pFechaBaja, p.fechaMod = :pFechaMod, p.localidad = :pLocalidad, "
					+ "p.nombreNegocio = :pNombreNegocio, p.nroCliente = :pNroCliente, p.nroCuenta = :pNroCuenta, "
					+ "p.saldo = :pSaldo, p.sucursal = :pSucursal, p.telefono = :pTelefono, p.tipoDocumento = :pTipoDocumento, "
					+ "p.usuario1 = :pUsuarioAlta, p.usuario2 = :pUsuarioBaja, p.usuario3 = :pUsuarioMod "
					+ "WHERE p.id = :pId");
			locQuery.setParameter("pApellidoNombre", proveedore.getApellidoNombre());
			locQuery.setParameter("pBanco", proveedore.getBanco());
			locQuery.setParameter("pDireccion", proveedore.getDireccion());
			locQuery.setParameter("pDocumento", proveedore.getDocumento());
			locQuery.setParameter("pEmail", proveedore.getEmail());
			locQuery.setParameter("pEstado", proveedore.getEstado());
			locQuery.setParameter("pFechaAlta", proveedore.getFechaAlta());
			locQuery.setParameter("pFechaBaja", proveedore.getFechaBaja());
			locQuery.setParameter("pFechaMod", proveedore.getFechaMod());
			locQuery.setParameter("pLocalidad", proveedore.getLocalidad());
			locQuery.setParameter("pNombreNegocio", proveedore.getNombreNegocio());
			locQuery.setParameter("pNroCliente", proveedore.getNroCliente());
			locQuery.setParameter("pNroCuenta", proveedore.getNroCuenta());
			locQuery.setParameter("pSaldo", proveedore.getSaldo());
			locQuery.setParameter("pSucursal", proveedore.getSucursal());
			locQuery.setParameter("pTelefono", proveedore.getTelefono());
			locQuery.setParameter("pTipoDocumento", proveedore.getTipoDocumento());
			locQuery.setParameter("pUsuarioAlta", proveedore.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", proveedore.getUsuario2());
			locQuery.setParameter("pUsuarioMod", proveedore.getUsuario3());
			locQuery.setParameter("pId", proveedore.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return proveedore.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Proveedore get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Proveedore p WHERE p.id = :pId", Proveedore.class);
		locQuery.setParameter("pId", id);
		Proveedore proveedore = new Proveedore();
		try{
			proveedore = (Proveedore) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			proveedore = new Proveedore();
		}
		return proveedore;
	}

	public List<Proveedore> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Proveedore p", Proveedore.class);
		List<Proveedore> lista = locQuery.getResultList();
		return lista;
	}

	public List<Proveedore> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Proveedore p WHERE p.estado = :pEstado", Proveedore.class);
		locQuery.setParameter("pEstado", estado);
		List<Proveedore> lista = locQuery.getResultList();
		return lista;
	}

}
