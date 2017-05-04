package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the e_consignacions database table.
 * 
 */
@Entity
@Table(name="e_consignacions")
@NamedQuery(name="EConsignacion.findAll", query="SELECT e FROM EConsignacion e")
public class EConsignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	private float monto;

	//bi-directional many-to-one association to Consignacion
	@ManyToOne
	@JoinColumn(name="id_consignacion")
	private Consignacion consignacion;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario;

	//bi-directional many-to-one association to EConsignacionsDetalle
	@OneToMany(mappedBy="EConsignacion")
	private List<EConsignacionsDetalle> EConsignacionsDetalles;

	public EConsignacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Consignacion getConsignacion() {
		return this.consignacion;
	}

	public void setConsignacion(Consignacion consignacion) {
		this.consignacion = consignacion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<EConsignacionsDetalle> getEConsignacionsDetalles() {
		return this.EConsignacionsDetalles;
	}

	public void setEConsignacionsDetalles(List<EConsignacionsDetalle> EConsignacionsDetalles) {
		this.EConsignacionsDetalles = EConsignacionsDetalles;
	}

	public EConsignacionsDetalle addEConsignacionsDetalle(EConsignacionsDetalle EConsignacionsDetalle) {
		getEConsignacionsDetalles().add(EConsignacionsDetalle);
		EConsignacionsDetalle.setEConsignacion(this);

		return EConsignacionsDetalle;
	}

	public EConsignacionsDetalle removeEConsignacionsDetalle(EConsignacionsDetalle EConsignacionsDetalle) {
		getEConsignacionsDetalles().remove(EConsignacionsDetalle);
		EConsignacionsDetalle.setEConsignacion(null);

		return EConsignacionsDetalle;
	}

}