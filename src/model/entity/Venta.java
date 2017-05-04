package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="consumidor_final")
	private String consumidorFinal;

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

	private String tipo;

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

	//bi-directional many-to-one association to VentasDetalle
	@OneToMany(mappedBy="venta")
	private List<VentasDetalle> ventasDetalles;
	
	//bi-directional many-to-one association to CuotasVenta
	@OneToMany(mappedBy="venta")
	private List<CuotasVenta> cuotasVentas;

	public Venta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getConsumidorFinal() {
		return this.consumidorFinal;
	}

	public void setConsumidorFinal(String consumidorFinal) {
		this.consumidorFinal = consumidorFinal;
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public List<VentasDetalle> getVentasDetalles() {
		return this.ventasDetalles;
	}

	public void setVentasDetalles(List<VentasDetalle> ventasDetalles) {
		this.ventasDetalles = ventasDetalles;
	}

	public VentasDetalle addVentasDetalle(VentasDetalle ventasDetalle) {
		getVentasDetalles().add(ventasDetalle);
		ventasDetalle.setVenta(this);

		return ventasDetalle;
	}

	public VentasDetalle removeVentasDetalle(VentasDetalle ventasDetalle) {
		getVentasDetalles().remove(ventasDetalle);
		ventasDetalle.setVenta(null);

		return ventasDetalle;
	}
	
	public List<CuotasVenta> getCuotasVentas() {
		return this.cuotasVentas;
	}

	public void setCuotasVentas(List<CuotasVenta> cuotasVentas) {
		this.cuotasVentas = cuotasVentas;
	}

	public CuotasVenta addCuotasVenta(CuotasVenta cuotasVenta) {
		getCuotasVentas().add(cuotasVenta);
		cuotasVenta.setVenta(this);

		return cuotasVenta;
	}

	public CuotasVenta removeCuotasVenta(CuotasVenta cuotasVenta) {
		getCuotasVentas().remove(cuotasVenta);
		cuotasVenta.setVenta(null);

		return cuotasVenta;
	}
	
	public String getClienteString(){
		String valor = " - ";
		if(cliente != null){
			valor = cliente.getNombreNegocio();
		}
		return valor;
	}

}