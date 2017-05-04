package dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.entity.Cliente;
import model.entity.Devolucione;
import model.entity.Producto;
import model.entity.UnidadMovil;
import dao.interfaces.DAODevolucion;

public class DAODevolucionImpl implements Serializable, DAODevolucion {

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

	public int insertar(Devolucione devolucione) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(devolucione);
			tx.commit();
			return devolucione.getId();
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int update(Devolucione devolucione) {
		try{
			inicializar();
			EntityTransaction tx = em.getTransaction();
			Query locQuery = em.createQuery("UPDATE Devolucione d SET d.cliente = :pCliente, d.descripcion = :pDescripcion, d.estado = :pEstado, "
					+ "d.fecha = :pFecha, d.fechaAlta = :pFechaAlta, d.fechaAltaConsignacion = :pFechaAltaConsignacion, d.fechaBaja = :pFechaBaja, "
					+ "d.fechaMod = :pFechaMod, d.fechaVentaConsignacion = :pFechaVentaConsignacion, d.idConVenta = :pIdConVenta, d.idMovimiento = :pIdMovimiento, "
					+ "d.nombreMovimiento = :pNombreMovimiento, d.nroImei = :pNroImei, d.precioUnidad = :pPrecioUnidad, d.producto = :pProducto, d.telefono = :pTelefono, "
					+ "d.usuario1 = :pUsuarioAlta, d.usuario2 = :pUsuarioBaja, d.usuario3 = :pUsuarioMod, d.vendido = :pVendido "
					+ "WHERE d.id = :pId");
			locQuery.setParameter("pCliente", devolucione.getCliente());
			locQuery.setParameter("pDescripcion", devolucione.getDescripcion());
			locQuery.setParameter("pEstado", devolucione.getEstado());
			locQuery.setParameter("pFecha", devolucione.getFecha());
			locQuery.setParameter("pFechaAlta", devolucione.getFechaAlta());
			locQuery.setParameter("pFechaAltaConsignacion", devolucione.getFechaAltaConsignacion());
			locQuery.setParameter("pFechaBaja", devolucione.getFechaBaja());
			locQuery.setParameter("pFechaMod", devolucione.getFechaMod());
			locQuery.setParameter("pFechaVentaConsignacion", devolucione.getFechaVentaConsignacion());
			locQuery.setParameter("pIdConVenta", devolucione.getIdConVenta());
			locQuery.setParameter("pIdMovimiento", devolucione.getIdMovimiento());
			locQuery.setParameter("pNombreMovimiento", devolucione.getNombreMovimiento());
			locQuery.setParameter("pNroImei", devolucione.getNroImei());
			locQuery.setParameter("pPrecioUnidad", devolucione.getPrecioUnidad());
			locQuery.setParameter("pProducto", devolucione.getProducto());
			locQuery.setParameter("pTelefono", devolucione.getTelefono());
			locQuery.setParameter("pUsuarioAlta", devolucione.getUsuario1());
			locQuery.setParameter("pUsuarioBaja", devolucione.getUsuario2());
			locQuery.setParameter("pUsuarioMod", devolucione.getUsuario3());
			locQuery.setParameter("pVendido", devolucione.getVendido());
			locQuery.setParameter("pId", devolucione.getId());
			tx.begin();
			locQuery.executeUpdate();
			tx.commit();
			return devolucione.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public Devolucione get(int id) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.id = :pId", Devolucione.class);
		locQuery.setParameter("pId", id);
		Devolucione devolucione = new Devolucione();
		try{
			devolucione = (Devolucione) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			devolucione = new Devolucione();
		}
		return devolucione;
	}

	public Devolucione get(String imei) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.unidadMovil.nroImei = :pNroImei", Devolucione.class);
		locQuery.setParameter("pNroImei", imei);
		Devolucione devolucione = new Devolucione();
		try{
			devolucione = (Devolucione) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println(e.getMessage());
			devolucione = new Devolucione();
		}
		return devolucione;
	}
	
	public Devolucione get(String imei, boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.unidadMovil.nroImei = :pNroImei AND d.estado = :pEstado", Devolucione.class);
		locQuery.setParameter("pNroImei", imei);
		locQuery.setParameter("pEstado", estado);
		Devolucione devolucione = new Devolucione();
		try{
			devolucione = (Devolucione) locQuery.getSingleResult();
		}catch (Exception e){
//			System.out.println(e.getMessage());
			devolucione = new Devolucione();
		}
		return devolucione;
	}

	public Devolucione get(UnidadMovil unidadMovil) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.unidadMovil = :pUnidadMovil", Devolucione.class);
		locQuery.setParameter("pUnidadMovil", unidadMovil);
		Devolucione devolucione = new Devolucione();
		try{
			devolucione = (Devolucione) locQuery.getSingleResult();
		}catch (Exception e){
			System.out.println(e.getMessage());
			devolucione = new Devolucione();
		}
		return devolucione;
	}

	public List<Devolucione> getLista() {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d", Devolucione.class);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.cliente = :pCliente", Devolucione.class);
		locQuery.setParameter("pCliente", cliente);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.unidadMovil.producto = :pProducto", Devolucione.class);
		locQuery.setParameter("pProducto", producto);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado, Cliente cliente) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.cliente = :pCliente", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.unidadMovil.producto = :pProducto", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado, Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Cliente cliente, Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.cliente = :pCliente AND d.unidadMovil.producto = :pProducto", Devolucione.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProducto", producto);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Cliente cliente, Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.cliente = :pCliente AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Producto producto, Date fechaInicio,
			Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.unidadMovil.producto = :pProducto AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado, Cliente cliente,
			Producto producto) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.cliente = :pCliente "
				+ "AND d.unidadMovil.producto = :pProducto", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProducto", producto);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado, Cliente cliente,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.cliente = :pCliente "
				+ "AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}	

	public List<Devolucione> getLista(boolean estado, Producto producto,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.unidadMovil.producto = :pProducto "
				+ "AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(Cliente cliente, Producto producto,
			Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.cliente = :pCliente AND d.unidadMovil.producto = :pProducto "
				+ "AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

	public List<Devolucione> getLista(boolean estado, Cliente cliente,
			Producto producto, Date fechaInicio, Date fechaFin) {
		inicializar();
		Query locQuery = em.createQuery("SELECT d FROM Devolucione d WHERE d.estado = :pEstado AND d.cliente = :pCliente AND d.unidadMovil.producto = :pProducto "
				+ "AND d.fecha BETWEEN :pInicio AND :pFin", Devolucione.class);
		locQuery.setParameter("pEstado", estado);
		locQuery.setParameter("pCliente", cliente);
		locQuery.setParameter("pProducto", producto);
		locQuery.setParameter("pInicio", fechaInicio);
		locQuery.setParameter("pFin", fechaFin);
		List<Devolucione> lista = locQuery.getResultList();
		return lista;
	}

}
