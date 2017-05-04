package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuotas_ventas database table.
 * 
 */
@Entity
@Table(name="cuotas_ventas")
@NamedQuery(name="CuotasVenta.findAll", query="SELECT c FROM CuotasVenta c")
public class CuotasVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cant_cuotas")
	private int cantCuotas;

	private String equipo;

	private boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	private int interes;

	@Column(name="monto_total")
	private float montoTotal;

	@Column(name="nro_imei")
	private String nroImei;

	//bi-directional many-to-one association to Venta
	@ManyToOne
	@JoinColumn(name="id_venta")
	private Venta venta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_baja")
	private Usuario usuario2;

	//bi-directional many-to-one association to CuotasVentasDetalle
	@OneToMany(mappedBy="cuotasVenta")
	private List<CuotasVentasDetalle> cuotasVentasDetalles;

	public CuotasVenta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantCuotas() {
		return this.cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public String getEquipo() {
		return this.equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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

	public int getInteres() {
		return this.interes;
	}

	public void setInteres(int interes) {
		this.interes = interes;
	}

	public float getMontoTotal() {
		return this.montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getNroImei() {
		return this.nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public Venta getVenta() {
		return this.venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
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

	public List<CuotasVentasDetalle> getCuotasVentasDetalles() {
		return this.cuotasVentasDetalles;
	}

	public void setCuotasVentasDetalles(List<CuotasVentasDetalle> cuotasVentasDetalles) {
		this.cuotasVentasDetalles = cuotasVentasDetalles;
	}

	public CuotasVentasDetalle addCuotasVentasDetalle(CuotasVentasDetalle cuotasVentasDetalle) {
		getCuotasVentasDetalles().add(cuotasVentasDetalle);
		cuotasVentasDetalle.setCuotasVenta(this);

		return cuotasVentasDetalle;
	}

	public CuotasVentasDetalle removeCuotasVentasDetalle(CuotasVentasDetalle cuotasVentasDetalle) {
		getCuotasVentasDetalles().remove(cuotasVentasDetalle);
		cuotasVentasDetalle.setCuotasVenta(null);

		return cuotasVentasDetalle;
	}

}