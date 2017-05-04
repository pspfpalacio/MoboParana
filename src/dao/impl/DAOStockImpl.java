package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Producto;
import model.entity.Stock;
import dao.interfaces.DAOStock;

public class DAOStockImpl implements DAOStock, Serializable {

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

	public int insertar(Stock stock) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try{
			tx.begin();
			em.persist(stock);
			tx.commit();
			return stock.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public int update(Stock stock) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			String update = "UPDATE Stock s SET s.cantidad = :pCantidad, s.fechaAlta = :pFechaAlta, s.fechaBaja = :pFechaBaja, "
					+ "s.fechaMod = :pFechaMod, s.precioCompra = :pPrecioCompra, s.producto = :pProducto, s.usuario1 = :pUsuarioAlta, "
					+ "s.usuario2 = :pUsuarioBaja, s.usuario3 = :pUsuarioMod "
					+ "WHERE s.id = :pId";
			Query locQuery = em.createQuery(update);
			locQuery.setParameter("pCantidad", stock.getCantidad());
			locQuery.setParameter("pFechaAlta", stock.getFechaAlta());
			locQuery.setParameter("pFechaBaja", stock.getFechaBaja());
			locQuery.setParameter("pFechaMod", stock.getFechaMod());
			locQuery.setParameter("pPrecioCompra", stock.getPrecioCompra());
			locQuery.setParameter("pProducto", stock.getProducto());
			locQuery.setParameter("pUsuarioAlta", stock.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", stock.getUsuario2());
			locQuery.setParameter("pUsuarioMod", stock.getUsuario3());
			locQuery.setParameter("pId", stock.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return stock.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			return 0;
		}
	}

	public List<Stock> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM Stock s", Stock.class);
		List<Stock> lista = locQuery.getResultList();
		return lista;
	}

	public List<Stock> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM Stock s WHERE s.producto = :pProducto", Stock.class);
		locQuery.setParameter("pProducto", producto);
		List<Stock> lista = locQuery.getResultList();
		return lista;
	}

	public Stock get(Producto producto, float precio) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM Stock s WHERE s.producto = :pProducto AND s.precioCompra = :pPrecio", Stock.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pPrecio", precio);
		Stock stock = new Stock();
		try{
			stock = (Stock) locQuery.getSingleResult();
		}catch (Exception e){
			stock = new Stock();
		}
		return stock;
	}
	
	public List<Stock> getListaEnStock(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT s FROM Stock s WHERE s.producto.estado = :pEstado", Stock.class);
		locQuery.setParameter("pEstado", estado);
		List<Stock> lista = locQuery.getResultList();
		return lista;
	}

}
