package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.ListaPrecio;
import model.entity.ListaPrecioProducto;
import model.entity.Producto;
import dao.interfaces.DAOListaPrecio;

public class DAOListaPrecioImpl implements Serializable, DAOListaPrecio {

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

	public int insertar(ListaPrecio listaPrecio) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(listaPrecio);
			tx.commit();
			return listaPrecio.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(ListaPrecio listaPrecio) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ListaPrecio l SET l.base = :pBase, l.estado = :pEstado, l.fechaAlta = :pFechaAlta, "
					+ "l.fechaBaja = :pFechaBaja, l.fechaMod = :pFechaMod, l.nombre = :pNombre, l.relacionBase = :pRelacionBase, "
					+ "l.rubro = :pRubro, l.usuario1 = :pUsuarioAlta, l.usuario2 = :pUsuarioBaja, l.usuario3 = :pUsuarioMod "
					+ "WHERE l.id = :pId");
			locQuery.setParameter("pBase", listaPrecio.getBase());
			locQuery.setParameter("pEstado", listaPrecio.getEstado());
			locQuery.setParameter("pFechaAlta", listaPrecio.getFechaAlta());
			locQuery.setParameter("pFechaBaja", listaPrecio.getFechaBaja());
			locQuery.setParameter("pFechaMod", listaPrecio.getFechaMod());
			locQuery.setParameter("pNombre", listaPrecio.getNombre());
			locQuery.setParameter("pRelacionBase", listaPrecio.getRelacionBase());
			locQuery.setParameter("pRubro", listaPrecio.getRubro());
			locQuery.setParameter("pUsuarioAlta", listaPrecio.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", listaPrecio.getUsuario2());
			locQuery.setParameter("pUsuarioMod", listaPrecio.getUsuario3());
			locQuery.setParameter("pId", listaPrecio.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return listaPrecio.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}	

	public ListaPrecio get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecio l WHERE l.id = :pId", ListaPrecio.class);
		locQuery.setParameter("pId", id);
		ListaPrecio listaPrecio = new ListaPrecio();
		try{
			listaPrecio = (ListaPrecio) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			listaPrecio = new ListaPrecio();
		}
		return listaPrecio;
	}
	
	public ListaPrecio getBase() {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecio l WHERE l.base = :pBase", ListaPrecio.class);
		locQuery.setParameter("pBase", true);
		ListaPrecio listaPrecio = new ListaPrecio();
		try{
			listaPrecio = (ListaPrecio) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			listaPrecio = new ListaPrecio();
		}
		return listaPrecio;
	}

	public List<ListaPrecio> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecio l", ListaPrecio.class);
		List<ListaPrecio> lista = locQuery.getResultList();
		return lista;
	}

	public List<ListaPrecio> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecio l WHERE l.estado = :pEstado", ListaPrecio.class);
		locQuery.setParameter("pEstado", estado);
		List<ListaPrecio> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<ListaPrecio> getLista(boolean estado, boolean relacionBase) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecio l WHERE l.estado = :pEstado AND l.relacionBase = :pRelacionBase", ListaPrecio.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pRelacionBase", relacionBase);
		List<ListaPrecio> lista = locQuery.getResultList();
		return lista;
	}

	public int insertar(ListaPrecioProducto listaPrecioProducto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(listaPrecioProducto);
			tx.commit();
			return listaPrecioProducto.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(ListaPrecioProducto listaPrecioProducto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE ListaPrecioProducto l SET l.listaPrecio = :pListaPrecio, l.porcentaje = :pPorcentaje, "
					+ "l.precioVenta = :pPrecioVenta, l.producto = :pProducto "
					+ "WHERE l.id = :pId");
			locQuery.setParameter("pListaPrecio", listaPrecioProducto.getListaPrecio());
			locQuery.setParameter("pPorcentaje", listaPrecioProducto.getPorcentaje());
			locQuery.setParameter("pPrecioVenta", listaPrecioProducto.getPrecioVenta());
			locQuery.setParameter("pProducto", listaPrecioProducto.getProducto());
			locQuery.setParameter("pId", listaPrecioProducto.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return listaPrecioProducto.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public ListaPrecioProducto getItemProducto(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecioProducto l WHERE l.id = :pId", ListaPrecioProducto.class);
		locQuery.setParameter("pId", id);
		ListaPrecioProducto listaPrecioProducto = new ListaPrecioProducto();
		try{
			listaPrecioProducto = (ListaPrecioProducto) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			listaPrecioProducto = new ListaPrecioProducto();
		}
		return listaPrecioProducto;
	}

	public ListaPrecioProducto getItemProducto(ListaPrecio listaPrecio,
			Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecioProducto l WHERE l.listaPrecio = :pListaPrecio "
				+ "AND l.producto = :pProducto", ListaPrecioProducto.class);
		locQuery.setParameter("pListaPrecio", listaPrecio);
		locQuery.setParameter("pProducto", producto);
		ListaPrecioProducto listaPrecioProducto = new ListaPrecioProducto();
		try{
			listaPrecioProducto = (ListaPrecioProducto) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			listaPrecioProducto = new ListaPrecioProducto();
		}
		return listaPrecioProducto;
	}

	public List<ListaPrecioProducto> getListaPrecioProducto() {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecioProducto l", ListaPrecioProducto.class);
		List<ListaPrecioProducto> lista = locQuery.getResultList();
		return lista;
	}

	public List<ListaPrecioProducto> getListaPrecioProducto(
			ListaPrecio listaPrecio) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecioProducto l WHERE l.listaPrecio = :pListaPrecio "
				+ "ORDER BY l.producto.rubro, l.producto.marca, l.producto.modelo", ListaPrecioProducto.class);
		locQuery.setParameter("pListaPrecio", listaPrecio);
		List<ListaPrecioProducto> lista = locQuery.getResultList();
		return lista;
	}

	public List<ListaPrecioProducto> getListaPrecioProducto(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT l FROM ListaPrecioProducto l WHERE l.producto = :pProducto", ListaPrecioProducto.class);
		locQuery.setParameter("pProducto", producto);
		List<ListaPrecioProducto> lista = locQuery.getResultList();
		return lista;
	}
	
	public int deleteProductosPorLista(ListaPrecio listaPrecio) {
		inicializar();
		EntityTransaction tx = em.getTransaction();
		try {
			Query locQuery = em.createQuery("DELETE FROM ListaPrecioProducto l "
					+ "WHERE l.listaPrecio = :pListaPrecio");
			locQuery.setParameter("pListaPrecio", listaPrecio);
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

}
