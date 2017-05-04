package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proveedores database table.
 * 
 */
@Entity
@Table(name="proveedores")
@NamedQuery(name="Proveedore.findAll", query="SELECT p FROM Proveedore p")
public class Proveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="apellido_nombre")
	private String apellidoNombre;

	private String banco;

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

	@Column(name="nro_cliente")
	private String nroCliente;

	@Column(name="nro_cuenta")
	private String nroCuenta;

	private float saldo;

	private String sucursal;

	private String telefono;

	@Column(name="tipo_documento")
	private String tipoDocumento;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="proveedore")
	private List<Compra> compras;

	//bi-directional many-to-one association to CuentasCorrientesProveedore
	@OneToMany(mappedBy="proveedore")
	private List<CuentasCorrientesProveedore> cuentasCorrientesProveedores;

	//bi-directional many-to-one association to PagosProveedore
	@OneToMany(mappedBy="proveedore")
	private List<PagosProveedore> pagosProveedores;

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
	
	//bi-directional many-to-one association to GarantiasProveedore
	@OneToMany(mappedBy="proveedore")
	private List<GarantiasProveedore> garantiasProveedores;

	public Proveedore() {
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

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
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

	public String getNroCliente() {
		return this.nroCliente;
	}

	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getNroCuenta() {
		return this.nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public float getSaldo() {
		return this.saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setProveedore(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setProveedore(null);

		return compra;
	}

	public List<CuentasCorrientesProveedore> getCuentasCorrientesProveedores() {
		return this.cuentasCorrientesProveedores;
	}

	public void setCuentasCorrientesProveedores(List<CuentasCorrientesProveedore> cuentasCorrientesProveedores) {
		this.cuentasCorrientesProveedores = cuentasCorrientesProveedores;
	}

	public CuentasCorrientesProveedore addCuentasCorrientesProveedore(CuentasCorrientesProveedore cuentasCorrientesProveedore) {
		getCuentasCorrientesProveedores().add(cuentasCorrientesProveedore);
		cuentasCorrientesProveedore.setProveedore(this);

		return cuentasCorrientesProveedore;
	}

	public CuentasCorrientesProveedore removeCuentasCorrientesProveedore(CuentasCorrientesProveedore cuentasCorrientesProveedore) {
		getCuentasCorrientesProveedores().remove(cuentasCorrientesProveedore);
		cuentasCorrientesProveedore.setProveedore(null);

		return cuentasCorrientesProveedore;
	}

	public List<PagosProveedore> getPagosProveedores() {
		return this.pagosProveedores;
	}

	public void setPagosProveedores(List<PagosProveedore> pagosProveedores) {
		this.pagosProveedores = pagosProveedores;
	}

	public PagosProveedore addPagosProveedore(PagosProveedore pagosProveedore) {
		getPagosProveedores().add(pagosProveedore);
		pagosProveedore.setProveedore(this);

		return pagosProveedore;
	}

	public PagosProveedore removePagosProveedore(PagosProveedore pagosProveedore) {
		getPagosProveedores().remove(pagosProveedore);
		pagosProveedore.setProveedore(null);

		return pagosProveedore;
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
	
	public List<GarantiasProveedore> getGarantiasProveedores() {
		return this.garantiasProveedores;
	}

	public void setGarantiasProveedores(List<GarantiasProveedore> garantiasProveedores) {
		this.garantiasProveedores = garantiasProveedores;
	}

	public GarantiasProveedore addGarantiasProveedore(GarantiasProveedore garantiasProveedore) {
		getGarantiasProveedores().add(garantiasProveedore);
		garantiasProveedore.setProveedore(this);

		return garantiasProveedore;
	}

	public GarantiasProveedore removeGarantiasProveedore(GarantiasProveedore garantiasProveedore) {
		getGarantiasProveedores().remove(garantiasProveedore);
		garantiasProveedore.setProveedore(null);

		return garantiasProveedore;
	}

}