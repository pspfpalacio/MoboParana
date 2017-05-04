package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the consignacions database table.
 * 
 */
@Entity
@Table(name="consignacions")
@NamedQuery(name="Consignacion.findAll", query="SELECT c FROM Consignacion c")
public class Consignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	private float monto;
	
	@Column(name="saldo_cliente")
	private float saldoCliente;

	@Column(name="tipo_venta")
	private String tipoVenta;

	private boolean vendido;

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

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario3;

	//bi-directional many-to-one association to ConsignacionsDetalle
	@OneToMany(mappedBy="consignacion")
	private List<ConsignacionsDetalle> consignacionsDetalles;
	
	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="consignacion")
	private List<Cuota> cuotas;

	//bi-directional many-to-one association to EntregaConsignacion
	@OneToMany(mappedBy="consignacion")
	private List<EntregaConsignacion> entregaConsignacions;
	
	//bi-directional many-to-one association to VentasCon
	@OneToMany(mappedBy="consignacion")
	private List<VentasCon> ventasCons;
	
	//bi-directional many-to-one association to EConsignacion
	@OneToMany(mappedBy="consignacion")
	private List<EConsignacion> EConsignacions;

	public Consignacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public float getMonto() {
		return this.monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public float getSaldoCliente() {
		return this.saldoCliente;
	}

	public void setSaldoCliente(float saldoCliente) {
		this.saldoCliente = saldoCliente;
	}

	public String getTipoVenta() {
		return this.tipoVenta;
	}

	public void setTipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public boolean getVendido() {
		return this.vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
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

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

	public List<ConsignacionsDetalle> getConsignacionsDetalles() {
		return this.consignacionsDetalles;
	}

	public void setConsignacionsDetalles(List<ConsignacionsDetalle> consignacionsDetalles) {
		this.consignacionsDetalles = consignacionsDetalles;
	}

	public ConsignacionsDetalle addConsignacionsDetalle(ConsignacionsDetalle consignacionsDetalle) {
		getConsignacionsDetalles().add(consignacionsDetalle);
		consignacionsDetalle.setConsignacion(this);

		return consignacionsDetalle;
	}

	public ConsignacionsDetalle removeConsignacionsDetalle(ConsignacionsDetalle consignacionsDetalle) {
		getConsignacionsDetalles().remove(consignacionsDetalle);
		consignacionsDetalle.setConsignacion(null);

		return consignacionsDetalle;
	}
	
	public List<Cuota> getCuotas() {
		return this.cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public Cuota addCuota(Cuota cuota) {
		getCuotas().add(cuota);
		cuota.setConsignacion(this);

		return cuota;
	}

	public Cuota removeCuota(Cuota cuota) {
		getCuotas().remove(cuota);
		cuota.setConsignacion(null);

		return cuota;
	}

	public List<EntregaConsignacion> getEntregaConsignacions() {
		return this.entregaConsignacions;
	}

	public void setEntregaConsignacions(List<EntregaConsignacion> entregaConsignacions) {
		this.entregaConsignacions = entregaConsignacions;
	}

	public EntregaConsignacion addEntregaConsignacion(EntregaConsignacion entregaConsignacion) {
		getEntregaConsignacions().add(entregaConsignacion);
		entregaConsignacion.setConsignacion(this);

		return entregaConsignacion;
	}

	public EntregaConsignacion removeEntregaConsignacion(EntregaConsignacion entregaConsignacion) {
		getEntregaConsignacions().remove(entregaConsignacion);
		entregaConsignacion.setConsignacion(null);

		return entregaConsignacion;
	}
	
	public List<VentasCon> getVentasCons() {
		return this.ventasCons;
	}

	public void setVentasCons(List<VentasCon> ventasCons) {
		this.ventasCons = ventasCons;
	}

	public VentasCon addVentasCon(VentasCon ventasCon) {
		getVentasCons().add(ventasCon);
		ventasCon.setConsignacion(this);

		return ventasCon;
	}

	public VentasCon removeVentasCon(VentasCon ventasCon) {
		getVentasCons().remove(ventasCon);
		ventasCon.setConsignacion(null);

		return ventasCon;
	}
	
	public List<EConsignacion> getEConsignacions() {
		return this.EConsignacions;
	}

	public void setEConsignacions(List<EConsignacion> EConsignacions) {
		this.EConsignacions = EConsignacions;
	}

	public EConsignacion addEConsignacion(EConsignacion EConsignacion) {
		getEConsignacions().add(EConsignacion);
		EConsignacion.setConsignacion(this);

		return EConsignacion;
	}

	public EConsignacion removeEConsignacion(EConsignacion EConsignacion) {
		getEConsignacions().remove(EConsignacion);
		EConsignacion.setConsignacion(null);

		return EConsignacion;
	}

}