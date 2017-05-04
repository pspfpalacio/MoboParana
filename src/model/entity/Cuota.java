package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuotas database table.
 * 
 */
@Entity
@Table(name="cuotas")
@NamedQuery(name="Cuota.findAll", query="SELECT c FROM Cuota c")
public class Cuota implements Serializable {
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

	//bi-directional many-to-one association to Consignacion
	@ManyToOne
	@JoinColumn(name="id_consignacion")
	private Consignacion consignacion;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_baja")
	private Usuario usuario2;
	
	//bi-directional many-to-one association to CuotasDetalle
	@OneToMany(mappedBy="cuota")
	private List<CuotasDetalle> cuotasDetalles;

	public Cuota() {
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

	public Consignacion getConsignacion() {
		return this.consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
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
	
	public List<CuotasDetalle> getCuotasDetalles() {
		return this.cuotasDetalles;
	}

	public void setCuotasDetalles(List<CuotasDetalle> cuotasDetalles) {
		this.cuotasDetalles = cuotasDetalles;
	}

	public CuotasDetalle addCuotasDetalle(CuotasDetalle cuotasDetalle) {
		getCuotasDetalles().add(cuotasDetalle);
		cuotasDetalle.setCuota(this);

		return cuotasDetalle;
	}

	public CuotasDetalle removeCuotasDetalle(CuotasDetalle cuotasDetalle) {
		getCuotasDetalles().remove(cuotasDetalle);
		cuotasDetalle.setCuota(null);

		return cuotasDetalle;
	}

}