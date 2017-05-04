package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cuotas_detalle database table.
 * 
 */
@Entity
@Table(name="cuotas_detalle")
@NamedQuery(name="CuotasDetalle.findAll", query="SELECT c FROM CuotasDetalle c")
public class CuotasDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private boolean estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private float monto;
	
	private boolean pago;

	//bi-directional many-to-one association to Cuota
	@ManyToOne
	@JoinColumn(name="id_cuota")
	private Cuota cuota;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_baja")
	private Usuario usuario2;
	
	//bi-directional many-to-one association to EntregaConsignacion
	@OneToMany(mappedBy="cuotasDetalle")
	private List<EntregaConsignacion> entregaConsignacions;

	public CuotasDetalle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public boolean getPago() {
		return this.pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public Cuota getCuota() {
		return this.cuota;
	}

	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
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
	
	public List<EntregaConsignacion> getEntregaConsignacions() {
		return this.entregaConsignacions;
	}

	public void setEntregaConsignacions(List<EntregaConsignacion> entregaConsignacions) {
		this.entregaConsignacions = entregaConsignacions;
	}

	public EntregaConsignacion addEntregaConsignacion(EntregaConsignacion entregaConsignacion) {
		getEntregaConsignacions().add(entregaConsignacion);
		entregaConsignacion.setCuotasDetalle(this);

		return entregaConsignacion;
	}

	public EntregaConsignacion removeEntregaConsignacion(EntregaConsignacion entregaConsignacion) {
		getEntregaConsignacions().remove(entregaConsignacion);
		entregaConsignacion.setCuotasDetalle(null);

		return entregaConsignacion;
	}
	
	public String getFechaVencimientoString() {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String fecha = formato.format(fechaVencimiento);
			return fecha;
		} catch(Exception e) {
			return "";
		}
	}

}