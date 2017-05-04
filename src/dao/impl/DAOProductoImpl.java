package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Producto;
import model.entity.Rubro;
import dao.interfaces.DAOProducto;

public class DAOProductoImpl implements Serializable, DAOProducto {

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

	public int insertar(Producto producto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(producto);
			tx.commit();
			return producto.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Producto producto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Producto p SET p.costoPromedio = :pCostoPromedio, p.descripcion = :pDescripcion, "
					+ "p.enConsignacion = :pEnConsignacion, p.estado = :pEstado, p.fechaAlta = :pFechaAlta, p.fechaBaja = :pFechaBaja, p.fechaMod = :pFechaMod, "
					+ "p.marca = :pMarca, p.modelo = :pModelo, p.nombre = :pNombre, p.precioCosto = :pPrecioCosto, p.stock = :pStock, "
					+ "p.stockMinimo = :pStockMinimo, p.usuario1 = :pUsuarioAlta, p.usuario2 = :pUsuarioBaja, p.usuario3 = :pUsuarioMod, p.rubro = :pRubro "
					+ "WHERE p.id = :pId");
			locQuery.setParameter("pCostoPromedio", producto.getCostoPromedio());
			locQuery.setParameter("pDescripcion", producto.getDescripcion());
			locQuery.setParameter("pEnConsignacion", producto.getEnConsignacion());
			locQuery.setParameter("pEstado", producto.getEstado());
			locQuery.setParameter("pFechaAlta", producto.getFechaAlta());
			locQuery.setParameter("pFechaBaja", producto.getFechaBaja());
			locQuery.setParameter("pFechaMod", producto.getFechaMod());
			locQuery.setParameter("pMarca", producto.getMarca());
			locQuery.setParameter("pModelo", producto.getModelo());
			locQuery.setParameter("pNombre", producto.getNombre());
			locQuery.setParameter("pPrecioCosto", producto.getPrecioCosto());
			locQuery.setParameter("pStock", producto.getStock());
			locQuery.setParameter("pStockMinimo", producto.getStockMinimo());
			locQuery.setParameter("pUsuarioAlta", producto.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", producto.getUsuario2());
			locQuery.setParameter("pUsuarioMod", producto.getUsuario3());
			locQuery.setParameter("pRubro", producto.getRubro());
			locQuery.setParameter("pId", producto.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return producto.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Producto get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Producto p WHERE p.id = :pId", Producto.class);
		locQuery.setParameter("pId", id);
		Producto producto = new Producto();
		try{
			producto = (Producto) locQuery.getSingleResult();
		}catch(Exception e){
			producto = new Producto();
		}
		return producto;
	}

	public List<Producto> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Producto p ORDER BY p.marca, p.modelo", Producto.class);
		List<Producto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Producto> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Producto p WHERE p.estado = :pEstado ORDER BY p.rubro, p.marca, p.modelo", Producto.class);
		locQuery.setParameter("pEstado", estado);
		List<Producto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Producto> getLista(Rubro rubro) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Producto p WHERE p.rubro = :pRubro ORDER BY p.marca, p.modelo", Producto.class);
		locQuery.setParameter("pRubro", rubro);
		List<Producto> lista = locQuery.getResultList();
		return lista;
	}

	public List<Producto> getLista(boolean estado, Rubro rubro) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Producto p WHERE p.estado = :pEstado AND "
				+ "p.rubro = :pRubro ORDER BY p.marca, p.modelo", Producto.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pRubro", rubro);
		List<Producto> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<Producto> getListaDebajoMinimo(Rubro rubro) {
		inicializar();
		Query locQuery = em.createQuery("SELECT p FROM Producto p WHERE p.stock <= p.stockMinimo AND p.stockMinimo <> 0 "
				+ "AND p.stockMinimo IS NOT null AND p.rubro = :pRubro", Producto.class);
		locQuery.setParameter("pRubro", rubro);
		List<Producto> lista = locQuery.getResultList();
		return lista;
	}

}
