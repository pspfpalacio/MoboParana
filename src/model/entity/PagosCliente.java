package model.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pagos_clientes database table.
 * 
 */
@Entity
@Table(name="pagos_clientes")
@NamedQuery(name="PagosCliente.findAll", query="SELECT p FROM PagosCliente p")
public class PagosCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String concepto;
	
	private boolean enabled;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	private float monto;
	
	//bi-directional many-to-one association to EquipoPendientePago
	@OneToMany(mappedBy="pagosCliente")
	private List<EquipoPendientePago> equipoPendientePagos;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;

	public PagosCliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConcepto() {
		return this.concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public List<EquipoPendientePago> getEquipoPendientePagos() {
		return this.equipoPendientePagos;
	}

	public void setEquipoPendientePagos(List<EquipoPendientePago> equipoPendientePagos) {
		this.equipoPendientePagos = equipoPendientePagos;
	}

	public EquipoPendientePago addEquipoPendientePago(EquipoPendientePago equipoPendientePago) {
		getEquipoPendientePagos().add(equipoPendientePago);
		equipoPendientePago.setPagosCliente(this);

		return equipoPendientePago;
	}

	public EquipoPendientePago removeEquipoPendientePago(EquipoPendientePago equipoPendientePago) {
		getEquipoPendientePagos().remove(equipoPendientePago);
		equipoPendientePago.setPagosCliente(null);

		return equipoPendientePago;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

}