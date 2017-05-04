package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Stock;
import model.entity.StocksVentasDetalle;
import model.entity.VentasDetalle;
import dao.interfaces.DAOStockVentaDetalle;

public class DAOStockVentaDetalleImpl implements Serializable,
		DAOStockVentaDetalle {

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

	public int insertar(StocksVentasDetalle stocksVentasDetalle) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(stocksVentasDetalle);
			tx.commit();
			return stocksVentasDetalle.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(StocksVentasDetalle stocksVentasDetalle) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE StocksVentasDetalle s SET s.cantidad = :pCantidad, "
					+ "s.stock = :pStock, s.ventasDetalle = :pVentasDetalle "
					+ "WHERE s.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pCantidad", stocksVentasDetalle.getCantidad());
			locQuery.setParameter("pStock", stocksVentasDetalle.getStock());
			locQuery.setParameter("pVentasDetalle", stocksVentasDetalle.getVentasDetalle());
			locQuery.setParameter("pId", stocksVentasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return stocksVentasDetalle.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}
	
	public int delete(StocksVentasDetalle stocksVentasDetalle){
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM StocksVentasDetalle s "
					+ "WHERE s.id = :pId");
			locQuery.setParameter("pId", stocksVentasDetalle.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			//cerrarInstancia();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			//cerrarInstancia();
			return 0;
		}
	}
	
	public int delete(VentasDetalle ventasDetalle){
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM StocksVentasDetalle s "
					+ "WHERE s.ventasDetalle = :pVentasDetalle");
			locQuery.setParameter("pVentasDetalle", ventasDetalle);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			//cerrarInstancia();
			return 1;
		} catch (Exception e) {
			tx.rollback();
			//cerrarInstancia();
			return 0;
		}
	}

	public StocksVentasDetalle get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM StocksVentasDetalle s WHERE s.id = :pId", StocksVentasDetalle.class);
		locQuery.setParameter("pId", id);
		StocksVentasDetalle stocksVentasDetalle = new StocksVentasDetalle();
		try{
			stocksVentasDetalle = (StocksVentasDetalle) locQuery.getSingleResult();
		}catch (Exception e){
			stocksVentasDetalle = new StocksVentasDetalle();
		}
		return stocksVentasDetalle;
	}

	public StocksVentasDetalle get(Stock stock, VentasDetalle ventasDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM StocksVentasDetalle s WHERE s.stock = :pStock AND "
				+ "s.ventasDetalle = :pVentasDetalle", StocksVentasDetalle.class);
		locQuery.setParameter("pStock", stock);
		locQuery.setParameter("pVentasDetalle", ventasDetalle);
		StocksVentasDetalle stocksVentasDetalle = new StocksVentasDetalle();
		try{
			stocksVentasDetalle = (StocksVentasDetalle) locQuery.getSingleResult();
		}catch (Exception e){
			stocksVentasDetalle = new StocksVentasDetalle();
		}
		return stocksVentasDetalle;
	}

	public List<StocksVentasDetalle> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM StocksVentasDetalle s", StocksVentasDetalle.class);
		List<StocksVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<StocksVentasDetalle> getLista(Stock stock) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM StocksVentasDetalle s WHERE s.stock = :pStock", StocksVentasDetalle.class);
		locQuery.setParameter("pStock", stock);
		List<StocksVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

	public List<StocksVentasDetalle> getLista(VentasDetalle ventasDetalle) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM StocksVentasDetalle s WHERE s.ventasDetalle = :pVentasDetalle", StocksVentasDetalle.class);
		locQuery.setParameter("pVentasDetalle", ventasDetalle);
		List<StocksVentasDetalle> lista = locQuery.getResultList();
		return lista;
	}

}
