package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="apellido_nombre")
	private String apellidoNombre;

	private String direccion;

	private String documento;

	private String email;

	private boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_alta")
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_baja")
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_mod")
	private Date fechaMod;

	private String localidad;

	@Column(name="nombre_negocio")
	private String nombreNegocio;

	private float saldo;

	private String telefono;

	@Column(name="tipo_documento")
	private String tipoDocumento;

	//bi-directional many-to-one association to ListaPrecio
	@ManyToOne
	@JoinColumn(name="id_listaprecio")
	private ListaPrecio listaPrecio;

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

	//bi-directional many-to-one association to CuentasCorrientesCliente
	@OneToMany(mappedBy="cliente")
	private List<CuentasCorrientesCliente> cuentasCorrientesClientes;

	//bi-directional many-to-one association to PagosCliente
	@OneToMany(mappedBy="cliente")
	private List<PagosCliente> pagosClientes;

	//bi-directional many-to-one association to Consignacion
	@OneToMany(mappedBy="cliente")
	private List<Consignacion> consignacions;
	
	//bi-directional many-to-one association to EquipoPendientePago
	@OneToMany(mappedBy="cliente")
	private List<EquipoPendientePago> equipoPendientePagos;
	
	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="cliente")
	private List<Usuario> usuarios;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="cliente")
	private List<Venta> ventas;
	
	//bi-directional many-to-one association to Devolucione
	@OneToMany(mappedBy="cliente")
	private List<Devolucione> devoluciones;
	
	//bi-directional many-to-one association to VentasCon
	@OneToMany(mappedBy="cliente")
	private List<VentasCon> ventasCons;
	
	//bi-directional many-to-one association to GarantiasCliente
	@OneToMany(mappedBy="cliente")
	private List<GarantiasCliente> garantiasClientes;
	
	//bi-directional many-to-one association to EConsignacion
	@OneToMany(mappedBy="cliente")
	private List<EConsignacion> EConsignacions;
	
	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="cliente")
	private List<Mensaje> mensajes;

	public Cliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidoNombre() {
		return this.apellidoNombre;
	}

	public void setApellidoNombre(String apellidoNombre) {
		this.apellidoNombre = apellidoNombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombreNegocio() {
		return this.nombreNegocio;
	}

	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}

	public float getSaldo() {
		return this.saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public ListaPrecio getListaPrecio() {
		return this.listaPrecio;
	}

	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
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

	public List<CuentasCorrientesCliente> getCuentasCorrientesClientes() {
		return this.cuentasCorrientesClientes;
	}

	public void setCuentasCorrientesClientes(List<CuentasCorrientesCliente> cuentasCorrientesClientes) {
		this.cuentasCorrientesClientes = cuentasCorrientesClientes;
	}

	public CuentasCorrientesCliente addCuentasCorrientesCliente(CuentasCorrientesCliente cuentasCorrientesCliente) {
		getCuentasCorrientesClientes().add(cuentasCorrientesCliente);
		cuentasCorrientesCliente.setCliente(this);

		return cuentasCorrientesCliente;
	}

	public CuentasCorrientesCliente removeCuentasCorrientesCliente(CuentasCorrientesCliente cuentasCorrientesCliente) {
		getCuentasCorrientesClientes().remove(cuentasCorrientesCliente);
		cuentasCorrientesCliente.setCliente(null);

		return cuentasCorrientesCliente;
	}

	public List<PagosCliente> getPagosClientes() {
		return this.pagosClientes;
	}

	public void setPagosClientes(List<PagosCliente> pagosClientes) {
		this.pagosClientes = pagosClientes;
	}

	public PagosCliente addPagosCliente(PagosCliente pagosCliente) {
		getPagosClientes().add(pagosCliente);
		pagosCliente.setCliente(this);

		return pagosCliente;
	}

	public PagosCliente removePagosCliente(PagosCliente pagosCliente) {
		getPagosClientes().remove(pagosCliente);
		pagosCliente.setCliente(null);

		return pagosCliente;
	}

	public List<Consignacion> getConsignacions() {
		return this.consignacions;
	}

	public void setConsignacions(List<Consignacion> consignacions) {
		this.consignacions = consignacions;
	}

	public Consignacion addConsignacion(Consignacion consignacion) {
		getConsignacions().add(consignacion);
		consignacion.setCliente(this);

		return consignacion;
	}

	public Consignacion removeConsignacion(Consignacion consignacion) {
		getConsignacions().remove(consignacion);
		consignacion.setCliente(null);

		return consignacion;
	}
	
	public List<EquipoPendientePago> getEquipoPendientePagos() {
		return this.equipoPendientePagos;
	}

	public void setEquipoPendientePagos(List<EquipoPendientePago> equipoPendientePagos) {
		this.equipoPendientePagos = equipoPendientePagos;
	}

	public EquipoPendientePago addEquipoPendientePago(EquipoPendientePago equipoPendientePago) {
		getEquipoPendientePagos().add(equipoPendientePago);
		equipoPendientePago.setCliente(this);

		return equipoPendientePago;
	}

	public EquipoPendientePago removeEquipoPendientePago(EquipoPendientePago equipoPendientePago) {
		getEquipoPendientePagos().remove(equipoPendientePago);
		equipoPendientePago.setCliente(null);

		return equipoPendientePago;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setCliente(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setCliente(null);

		return usuario;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setCliente(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setCliente(null);

		return venta;
	}
	
	public List<Devolucione> getDevoluciones() {
		return this.devoluciones;
	}

	public void setDevoluciones(List<Devolucione> devoluciones) {
		this.devoluciones = devoluciones;
	}

	public Devolucione addDevolucione(Devolucione devolucione) {
		getDevoluciones().add(devolucione);
		devolucione.setCliente(this);

		return devolucione;
	}

	public Devolucione removeDevolucione(Devolucione devolucione) {
		getDevoluciones().remove(devolucione);
		devolucione.setCliente(null);

		return devolucione;
	}
	
	public List<VentasCon> getVentasCons() {
		return this.ventasCons;
	}

	public void setVentasCons(List<VentasCon> ventasCons) {
		this.ventasCons = ventasCons;
	}

	public VentasCon addVentasCon(VentasCon ventasCon) {
		getVentasCons().add(ventasCon);
		ventasCon.setCliente(this);

		return ventasCon;
	}

	public VentasCon removeVentasCon(VentasCon ventasCon) {
		getVentasCons().remove(ventasCon);
		ventasCon.setCliente(null);

		return ventasCon;
	}
	
	public List<GarantiasCliente> getGarantiasClientes() {
		return this.garantiasClientes;
	}

	public void setGarantiasClientes(List<GarantiasCliente> garantiasClientes) {
		this.garantiasClientes = garantiasClientes;
	}

	public GarantiasCliente addGarantiasCliente(GarantiasCliente garantiasCliente) {
		getGarantiasClientes().add(garantiasCliente);
		garantiasCliente.setCliente(this);

		return garantiasCliente;
	}

	public GarantiasCliente removeGarantiasCliente(GarantiasCliente garantiasCliente) {
		getGarantiasClientes().remove(garantiasCliente);
		garantiasCliente.setCliente(null);

		return garantiasCliente;
	}
	
	public List<EConsignacion> getEConsignacions() {
		return this.EConsignacions;
	}

	public void setEConsignacions(List<EConsignacion> EConsignacions) {
		this.EConsignacions = EConsignacions;
	}

	public EConsignacion addEConsignacion(EConsignacion EConsignacion) {
		getEConsignacions().add(EConsignacion);
		EConsignacion.setCliente(this);

		return EConsignacion;
	}

	public EConsignacion removeEConsignacion(EConsignacion EConsignacion) {
		getEConsignacions().remove(EConsignacion);
		EConsignacion.setCliente(null);

		return EConsignacion;
	}
	
	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setCliente(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setCliente(null);

		return mensaje;
	}


}