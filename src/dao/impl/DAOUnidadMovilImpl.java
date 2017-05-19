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
import model.entity.UnidadMovil;
import dao.interfaces.DAOUnidadMovil;

public class DAOUnidadMovilImpl implements Serializable, DAOUnidadMovil {

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

	public int insertar(UnidadMovil unidadMovil) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(unidadMovil);
			tx.commit();
			return unidadMovil.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(UnidadMovil unidadMovil) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE UnidadMovil u SET u.bajaStock = :pBajaStock, u.conFalla = :pConFalla, u.descripcion = :pDescripcion, u.devuelto = :pDevuelto, "
					+ "u.eliminado = :pEliminado, u.enCompra = :pEnCompra, u.enConsignacion = :pEnConsignacion, u.enGarantiaCliente = :pEnGarantiaCliente, u.enGarantiaProveedor = :pEnGarantiaProveedor, "
					+ "u.enStock = :pEnStock, u.enVenta = :pEnVenta, u.estado = :pEstado, u.fechaAlta = :pFechaAlta, u.fechaBaja = :pFechaBaja, u.fechaBajaStock = :pFechaBajaStock, "
					+ "u.fechaMod = :pFechaMod, u.nroImei = :pNroImei, u.precioCompra = :pPrecioCompra, u.producto = :pProducto, u.usuario1 = :pUsuarioAlta, u.usuario2 = :pUsuarioBaja, "
					+ "u.usuario3 = :pUsuarioMod, u.usuario4 = :pUsuarioBajaStock "
					+ "WHERE u.id = :pId");
			locQuery.setParameter("pBajaStock", unidadMovil.getBajaStock());
			locQuery.setParameter("pConFalla", unidadMovil.getConFalla());
			locQuery.setParameter("pDescripcion", unidadMovil.getDescripcion());
			locQuery.setParameter("pDevuelto", unidadMovil.getDevuelto());
			locQuery.setParameter("pEliminado", unidadMovil.getEliminado());
			locQuery.setParameter("pEnCompra", unidadMovil.getEnCompra());
			locQuery.setParameter("pEnConsignacion", unidadMovil.getEnConsignacion());
			locQuery.setParameter("pEnGarantiaCliente", unidadMovil.getEnGarantiaCliente());
			locQuery.setParameter("pEnGarantiaProveedor", unidadMovil.getEnGarantiaProveedor());
			locQuery.setParameter("pEnStock", unidadMovil.getEnStock());
			locQuery.setParameter("pEnVenta", unidadMovil.getEnVenta());			
			locQuery.setParameter("pEstado", unidadMovil.getEstado());
			locQuery.setParameter("pFechaAlta", unidadMovil.getFechaAlta());			
			locQuery.setParameter("pFechaBaja", unidadMovil.getFechaBaja());
			locQuery.setParameter("pFechaBajaStock", unidadMovil.getFechaBajaStock());
			locQuery.setParameter("pFechaMod", unidadMovil.getFechaMod());
			locQuery.setParameter("pNroImei", unidadMovil.getNroImei());
			locQuery.setParameter("pPrecioCompra", unidadMovil.getPrecioCompra());			
			locQuery.setParameter("pProducto", unidadMovil.getProducto());
			locQuery.setParameter("pUsuarioAlta", unidadMovil.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", unidadMovil.getUsuario2());
			locQuery.setParameter("pUsuarioMod", unidadMovil.getUsuario3());
			locQuery.setParameter("pUsuarioBajaStock", unidadMovil.getUsuario4());			
			locQuery.setParameter("pId", unidadMovil.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return unidadMovil.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public UnidadMovil get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.id = :pId AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pId", id);
		locQuery.setParameter("pEliminado", false);
		UnidadMovil unidadMovil = new UnidadMovil();
		try{
			unidadMovil = (UnidadMovil) locQuery.getSingleResult();
			em.refresh(unidadMovil);
		}catch (Exception e){
			System.out.println(e.getMessage());
			unidadMovil = new UnidadMovil();
		}
		return unidadMovil;
	}

	public UnidadMovil get(String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei = :pNroImei AND u.eliminado = :pEliminado AND u.estado = :pEstado", UnidadMovil.class);
		locQuery.setParameter("pNroImei", nroImei);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		UnidadMovil unidadMovil = new UnidadMovil();
		try{
			unidadMovil = (UnidadMovil) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println("get UnidadMovil " + e.getMessage());
			unidadMovil = new UnidadMovil();
		}
		return unidadMovil;
	}
	
	public UnidadMovil getBajaStock(String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei = :pNroImei AND u.bajaStock = :pBajaStock "
				+ "AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pNroImei", nroImei);
		locQuery.setParameter("pBajaStock", true);
		locQuery.setParameter("pEliminado", false);
		UnidadMovil unidadMovil = new UnidadMovil();
		try{
			unidadMovil = (UnidadMovil) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println("get UnidadMovil " + e.getMessage());
			unidadMovil = new UnidadMovil();
		}
		return unidadMovil;
	}
	
	public UnidadMovil get(String nroImei, boolean estado, boolean enVenta) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei = :pNroImei AND u.eliminado = :pEliminado "
				+ "AND u.estado = :pEstado AND u.enVenta = :pEnVenta", UnidadMovil.class);
		locQuery.setParameter("pNroImei", nroImei);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnVenta", enVenta);
		UnidadMovil unidadMovil = new UnidadMovil();
		try{
			unidadMovil = (UnidadMovil) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			unidadMovil = new UnidadMovil();
		}
		return unidadMovil;
	}

	public List<UnidadMovil> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.estado = :pEstado AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.producto = :pProducto "
				+ "AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getLista(boolean estado, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.estado = :pEstado AND "
				+ "u.producto = :pProducto AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getListaEnStock(boolean enStock) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.enStock = :pEnStock "
				+ "AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getListaEnStock(boolean enStock, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.enStock = :pEnStock AND "
				+ "u.producto = :pProducto AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<UnidadMovil> getListaEnStock(boolean enStock, Producto producto, boolean enConsignacion, boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.enStock = :pEnStock AND "
				+ "u.producto = :pProducto AND u.enConsignacion = :pEnConsignacion AND u.eliminado = :pEliminado AND u.estado = :pEstado", UnidadMovil.class);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEnConsignacion", enConsignacion);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", estado);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getListaEnStock(boolean estado, boolean enStock) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.estado = :pEstado AND "
				+ "u.enStock = :pEnStock AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<UnidadMovil> getListaEnStock(boolean estado, boolean enStock, boolean enConsignacion, Rubro rubro){
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.estado = :pEstado AND u.enConsignacion = :pEnConsignacion AND "
				+ "u.enStock = :pEnStock AND u.producto.rubro = :pRubro AND u.producto.estado = :pEstado AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pEnConsignacion", enConsignacion);
		locQuery.setParameter("pRubro", rubro);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getListaEnStock(boolean estado, boolean enStock,
			Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.estado = :pEstado AND "
				+ "u.enStock = :pEnStock AND u.producto = :pProducto AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<UnidadMovil> getListaEnStockOrdenPrecio(boolean estado, boolean enStock,
			Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.estado = :pEstado AND "
				+ "u.enStock = :pEnStock AND u.producto = :pProducto AND u.eliminado = :pEliminado "
				+ "ORDER BY u.precioCompra", UnidadMovil.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public int deletePorProducto(Producto producto) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE UnidadMovil u SET u.eliminado = :pEliminado "
					+ "WHERE u.producto = :pProducto");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pProducto", producto);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int deletePorImei(String imei) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE UnidadMovil u SET u.eliminado = :pEliminado, u.estado = :pEstado, "
					+ "u.enStock = :pEnStock, u.bajaStock = :pBajaStock "
					+ "WHERE u.nroImei = :pNroImei");
			locQuery.setParameter("pEliminado", true);
			locQuery.setParameter("pEstado", false);
			locQuery.setParameter("pEnStock", false);
			locQuery.setParameter("pBajaStock", false);
			locQuery.setParameter("pNroImei", imei);
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public List<UnidadMovil> getLike(String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei LIKE :pImei "
				+ "AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pImei", "%" + nroImei + "%");
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getLike(boolean estado, boolean enStock,
			String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei LIKE :pImei AND u.estado = :pEstado "
				+ "AND u.enStock = :pEnStock AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pImei", "%" + nroImei + "%");
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

	public List<UnidadMovil> getLike(boolean estado, boolean enStock,
			Producto producto, String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei LIKE :pImei AND u.estado = :pEstado "
				+ "AND u.enStock = :pEnStock AND u.producto = :pProducto AND u.eliminado = :pEliminado", UnidadMovil.class);
		locQuery.setParameter("pImei", "%" + nroImei + "%");
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pEnStock", enStock);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pEliminado", false);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<String> getListaImeis(int idProducto) {
		inicializar();
		Query locQuery = em.createQuery("select distinct u.nroImei from UnidadMovil u where u.producto.id = :pIdProducto "
				+ "and u.eliminado = :pEliminado and u.estado = :pEstado", String.class);
		locQuery.setParameter("pIdProducto", idProducto);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<String> lista = locQuery.getResultList();
		return lista;
	}
	
	public List<UnidadMovil> getLista(String nroImei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT u FROM UnidadMovil u WHERE u.nroImei = :pNroImei AND u.eliminado = :pEliminado "
				+ "AND u.estado = :pEstado", UnidadMovil.class);
		locQuery.setParameter("pNroImei", nroImei);
		locQuery.setParameter("pEliminado", false);
		locQuery.setParameter("pEstado", true);
		List<UnidadMovil> lista = locQuery.getResultList();
		return lista;
	}

}
