package dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.interfaces.DAOCheque;
import model.entity.Cheque;
import model.entity.PagosCliente;

public class DAOChequeImpl implements Serializable, DAOCheque {

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

	public int insertar(Cheque cheque) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cheque);
			tx.commit();
			return cheque.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Cheque cheque) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Cheque c SET c.codigoCuentaCliente = :pCodigoCuentaCliente, "
					+ "c.datosEntidad = :pDatosEntidad, c.fecha = :pFecha, c.importe = :pImporte, c.lugar = :pLugar, "
					+ "c.numero = :pNumero, c.pagosCliente = :pPagosCliente, c.persona = :pPersona, c.serie = :pSerie, "
					+ "c.tipo = :pTipo "
					+ "WHERE c.id = :pId");
			locQuery.setParameter("pCodigoCuentaCliente", cheque.getCodigoCuentaCliente());
			locQuery.setParameter("pDatosEntidad", cheque.getDatosEntidad());
			locQuery.setParameter("pFecha", cheque.getFecha());
			locQuery.setParameter("pImporte", cheque.getImporte());
			locQuery.setParameter("pLugar", cheque.getLugar());
			locQuery.setParameter("pNumero", cheque.getNumero());
			locQuery.setParameter("pPagosCliente", cheque.getPagosClientes());
			locQuery.setParameter("pPersona", cheque.getPersona());
			locQuery.setParameter("pSerie", cheque.getSerie());
			locQuery.setParameter("pTipo", cheque.getTipo());
			locQuery.setParameter("pId", cheque.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return cheque.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Cheque get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cheque c WHERE c.id = :pId", Cheque.class);
		locQuery.setParameter("pId", id);
		Cheque cheque = new Cheque();
		try{
			cheque = (Cheque) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			cheque = new Cheque();
		}
		return cheque;
	}

	public Cheque get(PagosCliente pagosCliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT c FROM Cheque c WHERE c.pagosCliente = :pPagosCliente", Cheque.class);
		locQuery.setParameter("pPagosCliente", pagosCliente);
		Cheque cheque = new Cheque();
		try{
			cheque = (Cheque) locQuery.getSingleResult();
		}catch(Exception e){
			System.out.println(e.getMessage());
			cheque = new Cheque();
		}
		return cheque;
	}

}
