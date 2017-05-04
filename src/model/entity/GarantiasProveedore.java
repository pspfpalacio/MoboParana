package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the garantias_proveedores database table.
 * 
 */
@Entity
@Table(name="garantias_proveedores")
@NamedQuery(name="GarantiasProveedore.findAll", query="SELECT g FROM GarantiasProveedore g")
public class GarantiasProveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="accion_realizar")
	private String accionRealizar;
	
	private String concepto;

	private float costo;

	private boolean estado;

	private String falla;

	@Column(name="falla_definitiva")
	private String fallaDefinitiva;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	private boolean finalizado;
	
	@Column(name="id_movimiento")
	private int idMovimiento;

	@Column(name="imei_falla")
	private String imeiFalla;

	@Column(name="imei_reintegro")
	private String imeiReintegro;
	
	@Column(name="nombre_movimiento")
	private String nombreMovimiento;

	private String observaciones;
	
	@Column(name="precio_unidad")
	private float precioUnidad;

	private String resolucion;

	@Column(name="telefono_falla")
	private String telefonoFalla;

	@Column(name="telefono_reintegro")
	private String telefonoReintegro;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private Proveedore proveedore;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto_falla")
	private Producto producto1;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto_reintegro")
	private Producto producto2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario3;

	public GarantiasProveedore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccionRealizar() {
		return this.accionRealizar;
	}

	public void setAccionRealizar(String accionRealizar) {
		this.accionRealizar = accionRealizar;
	}
	
	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public float getCosto() {
		return this.costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFalla() {
		return this.falla;
	}

	public void setFalla(String falla) {
		this.falla = falla;
	}

	public String getFallaDefinitiva() {
		return this.fallaDefinitiva;
	}

	public void setFallaDefinitiva(String fallaDefinitiva) {
		this.fallaDefinitiva = fallaDefinitiva;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public boolean getFinalizado() {
		return this.finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public int getIdMovimiento() {
		return this.idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getImeiFalla() {
		return this.imeiFalla;
	}

	public void setImeiFalla(String imeiFalla) {
		this.imeiFalla = imeiFalla;
	}

	public String getImeiReintegro() {
		return this.imeiReintegro;
	}

	public void setImeiReintegro(String imeiReintegro) {
		this.imeiReintegro = imeiReintegro;
	}
	
	public String getNombreMovimiento() {
		return this.nombreMovimiento;
	}

	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public float getPrecioUnidad() {
		return this.precioUnidad;
	}

	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public String getResolucion() {
		return this.resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getTelefonoFalla() {
		return this.telefonoFalla;
	}

	public void setTelefonoFalla(String telefonoFalla) {
		this.telefonoFalla = telefonoFalla;
	}

	public String getTelefonoReintegro() {
		return this.telefonoReintegro;
	}

	public void setTelefonoReintegro(String telefonoReintegro) {
		this.telefonoReintegro = telefonoReintegro;
	}

	public Proveedore getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedore proveedore) {
		this.proveedore = proveedore;
	}

	public Producto getProducto1() {
		return this.producto1;
	}

	public void setProducto1(Producto producto1) {
		this.producto1 = producto1;
	}

	public Producto getProducto2() {
		return this.producto2;
	}

	public void setProducto2(Producto producto2) {
		this.producto2 = producto2;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

}