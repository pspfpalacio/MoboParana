package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="apellido_nombre")
	private String apellidoNombre;

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

	private String password;

	private String username;

	//bi-directional many-to-one association to Caja
	@OneToMany(mappedBy="usuario")
	private List<Caja> cajas;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario1")
	private List<Cliente> clientes1;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario2")
	private List<Cliente> clientes2;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario3")
	private List<Cliente> clientes3;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="usuario1")
	private List<Compra> compras1;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="usuario2")
	private List<Compra> compras2;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="usuario3")
	private List<Compra> compras3;

	//bi-directional many-to-one association to CuentasCorrientesCliente
	@OneToMany(mappedBy="usuario")
	private List<CuentasCorrientesCliente> cuentasCorrientesClientes;

	//bi-directional many-to-one association to CuentasCorrientesProveedore
	@OneToMany(mappedBy="usuario")
	private List<CuentasCorrientesProveedore> cuentasCorrientesProveedores;
	
	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="usuario1")
	private List<Cuota> cuotas1;

	//bi-directional many-to-one association to Cuota
	@OneToMany(mappedBy="usuario2")
	private List<Cuota> cuotas2;

	//bi-directional many-to-one association to CuotasDetalle
	@OneToMany(mappedBy="usuario1")
	private List<CuotasDetalle> cuotasDetalles1;

	//bi-directional many-to-one association to CuotasDetalle
	@OneToMany(mappedBy="usuario2")
	private List<CuotasDetalle> cuotasDetalles2;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="usuario1")
	private List<Gasto> gastos1;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="usuario2")
	private List<Gasto> gastos2;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="usuario3")
	private List<Gasto> gastos3;

	//bi-directional many-to-one association to ListaPrecio
	@OneToMany(mappedBy="usuario1")
	private List<ListaPrecio> listaPrecios1;

	//bi-directional many-to-one association to ListaPrecio
	@OneToMany(mappedBy="usuario2")
	private List<ListaPrecio> listaPrecios2;

	//bi-directional many-to-one association to ListaPrecio
	@OneToMany(mappedBy="usuario3")
	private List<ListaPrecio> listaPrecios3;

	//bi-directional many-to-one association to PagosCliente
	@OneToMany(mappedBy="usuario")
	private List<PagosCliente> pagosClientes;

	//bi-directional many-to-one association to PagosProveedore
	@OneToMany(mappedBy="usuario")
	private List<PagosProveedore> pagosProveedores;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="usuario1")
	private List<Producto> productos1;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="usuario2")
	private List<Producto> productos2;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="usuario3")
	private List<Producto> productos3;

	//bi-directional many-to-one association to Proveedore
	@OneToMany(mappedBy="usuario1")
	private List<Proveedore> proveedores1;

	//bi-directional many-to-one association to Proveedore
	@OneToMany(mappedBy="usuario2")
	private List<Proveedore> proveedores2;

	//bi-directional many-to-one association to Proveedore
	@OneToMany(mappedBy="usuario3")
	private List<Proveedore> proveedores3;

	//bi-directional many-to-one association to UnidadMovil
	@OneToMany(mappedBy="usuario1")
	private List<UnidadMovil> unidadMovils1;

	//bi-directional many-to-one association to UnidadMovil
	@OneToMany(mappedBy="usuario2")
	private List<UnidadMovil> unidadMovils2;

	//bi-directional many-to-one association to UnidadMovil
	@OneToMany(mappedBy="usuario3")
	private List<UnidadMovil> unidadMovils3;
	
	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Role role;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_alta")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario1")
	private List<Usuario> usuarios1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_baja")
	private Usuario usuario2;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario2")
	private List<Usuario> usuarios2;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_mod")
	private Usuario usuario3;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="usuario3")
	private List<Usuario> usuarios3;

	//bi-directional many-to-one association to Consignacion
	@OneToMany(mappedBy="usuario1")
	private List<Consignacion> consignacions1;

	//bi-directional many-to-one association to Consignacion
	@OneToMany(mappedBy="usuario2")
	private List<Consignacion> consignacions2;

	//bi-directional many-to-one association to Consignacion
	@OneToMany(mappedBy="usuario3")
	private List<Consignacion> consignacions3;

	//bi-directional many-to-one association to Devolucione
	@OneToMany(mappedBy="usuario1")
	private List<Devolucione> devoluciones1;

	//bi-directional many-to-one association to Devolucione
	@OneToMany(mappedBy="usuario2")
	private List<Devolucione> devoluciones2;

	//bi-directional many-to-one association to Devolucione
	@OneToMany(mappedBy="usuario3")
	private List<Devolucione> devoluciones3;

	//bi-directional many-to-one association to Rubro
	@OneToMany(mappedBy="usuario1")
	private List<Rubro> rubros1;

	//bi-directional many-to-one association to Rubro
	@OneToMany(mappedBy="usuario2")
	private List<Rubro> rubros2;

	//bi-directional many-to-one association to Rubro
	@OneToMany(mappedBy="usuario3")
	private List<Rubro> rubros3;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="usuario1")
	private List<Venta> ventas1;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="usuario2")
	private List<Venta> ventas2;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="usuario3")
	private List<Venta> ventas3;
	
	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="usuario1")
	private List<Stock> stocks1;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="usuario2")
	private List<Stock> stocks2;

	//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="usuario3")
	private List<Stock> stocks3;
	
	//bi-directional many-to-one association to VentasCon
	@OneToMany(mappedBy="usuario1")
	private List<VentasCon> ventasCons1;

	//bi-directional many-to-one association to VentasCon
	@OneToMany(mappedBy="usuario2")
	private List<VentasCon> ventasCons2;

	//bi-directional many-to-one association to VentasCon
	@OneToMany(mappedBy="usuario3")
	private List<VentasCon> ventasCons3;
	
	//bi-directional many-to-one association to GarantiasCliente
	@OneToMany(mappedBy="usuario1")
	private List<GarantiasCliente> garantiasClientes1;

	//bi-directional many-to-one association to GarantiasCliente
	@OneToMany(mappedBy="usuario2")
	private List<GarantiasCliente> garantiasClientes2;

	//bi-directional many-to-one association to GarantiasCliente
	@OneToMany(mappedBy="usuario3")
	private List<GarantiasCliente> garantiasClientes3;
	
	//bi-directional many-to-one association to GarantiasProveedore
	@OneToMany(mappedBy="usuario1")
	private List<GarantiasProveedore> garantiasProveedores1;

	//bi-directional many-to-one association to GarantiasProveedore
	@OneToMany(mappedBy="usuario2")
	private List<GarantiasProveedore> garantiasProveedores2;

	//bi-directional many-to-one association to GarantiasProveedore
	@OneToMany(mappedBy="usuario3")
	private List<GarantiasProveedore> garantiasProveedores3;
	
	//bi-directional many-to-one association to CuotasVenta
	@OneToMany(mappedBy="usuario1")
	private List<CuotasVenta> cuotasVentas1;

	//bi-directional many-to-one association to CuotasVenta
	@OneToMany(mappedBy="usuario2")
	private List<CuotasVenta> cuotasVentas2;

	//bi-directional many-to-one association to CuotasVentasDetalle
	@OneToMany(mappedBy="usuario1")
	private List<CuotasVentasDetalle> cuotasVentasDetalles1;

	//bi-directional many-to-one association to CuotasVentasDetalle
	@OneToMany(mappedBy="usuario2")
	private List<CuotasVentasDetalle> cuotasVentasDetalles2;
	
	//bi-directional many-to-one association to EConsignacion
	@OneToMany(mappedBy="usuario")
	private List<EConsignacion> EConsignacions;
	
	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="usuario")
	private List<Mensaje> mensajes;
	
	//bi-directional many-to-one association to Tecnico
	@OneToMany(mappedBy="usuario1")
	private List<Tecnico> tecnicos1;

	//bi-directional many-to-one association to Tecnico
	@OneToMany(mappedBy="usuario2")
	private List<Tecnico> tecnicos2;

	//bi-directional many-to-one association to Tecnico
	@OneToMany(mappedBy="usuario3")
	private List<Tecnico> tecnicos3;

	public Usuario() {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Caja> getCajas() {
		return this.cajas;
	}

	public void setCajas(List<Caja> cajas) {
		this.cajas = cajas;
	}

	public Caja addCaja(Caja caja) {
		getCajas().add(caja);
		caja.setUsuario(this);

		return caja;
	}

	public Caja removeCaja(Caja caja) {
		getCajas().remove(caja);
		caja.setUsuario(null);

		return caja;
	}

	public List<Cliente> getClientes1() {
		return this.clientes1;
	}

	public void setClientes1(List<Cliente> clientes1) {
		this.clientes1 = clientes1;
	}

	public Cliente addClientes1(Cliente clientes1) {
		getClientes1().add(clientes1);
		clientes1.setUsuario1(this);

		return clientes1;
	}

	public Cliente removeClientes1(Cliente clientes1) {
		getClientes1().remove(clientes1);
		clientes1.setUsuario1(null);

		return clientes1;
	}

	public List<Cliente> getClientes2() {
		return this.clientes2;
	}

	public void setClientes2(List<Cliente> clientes2) {
		this.clientes2 = clientes2;
	}

	public Cliente addClientes2(Cliente clientes2) {
		getClientes2().add(clientes2);
		clientes2.setUsuario2(this);

		return clientes2;
	}

	public Cliente removeClientes2(Cliente clientes2) {
		getClientes2().remove(clientes2);
		clientes2.setUsuario2(null);

		return clientes2;
	}

	public List<Cliente> getClientes3() {
		return this.clientes3;
	}

	public void setClientes3(List<Cliente> clientes3) {
		this.clientes3 = clientes3;
	}

	public Cliente addClientes3(Cliente clientes3) {
		getClientes3().add(clientes3);
		clientes3.setUsuario3(this);

		return clientes3;
	}

	public Cliente removeClientes3(Cliente clientes3) {
		getClientes3().remove(clientes3);
		clientes3.setUsuario3(null);

		return clientes3;
	}

	public List<Compra> getCompras1() {
		return this.compras1;
	}

	public void setCompras1(List<Compra> compras1) {
		this.compras1 = compras1;
	}

	public Compra addCompras1(Compra compras1) {
		getCompras1().add(compras1);
		compras1.setUsuario1(this);

		return compras1;
	}

	public Compra removeCompras1(Compra compras1) {
		getCompras1().remove(compras1);
		compras1.setUsuario1(null);

		return compras1;
	}

	public List<Compra> getCompras2() {
		return this.compras2;
	}

	public void setCompras2(List<Compra> compras2) {
		this.compras2 = compras2;
	}

	public Compra addCompras2(Compra compras2) {
		getCompras2().add(compras2);
		compras2.setUsuario2(this);

		return compras2;
	}

	public Compra removeCompras2(Compra compras2) {
		getCompras2().remove(compras2);
		compras2.setUsuario2(null);

		return compras2;
	}

	public List<Compra> getCompras3() {
		return this.compras3;
	}

	public void setCompras3(List<Compra> compras3) {
		this.compras3 = compras3;
	}

	public Compra addCompras3(Compra compras3) {
		getCompras3().add(compras3);
		compras3.setUsuario3(this);

		return compras3;
	}

	public Compra removeCompras3(Compra compras3) {
		getCompras3().remove(compras3);
		compras3.setUsuario3(null);

		return compras3;
	}

	public List<CuentasCorrientesCliente> getCuentasCorrientesClientes() {
		return this.cuentasCorrientesClientes;
	}

	public void setCuentasCorrientesClientes(List<CuentasCorrientesCliente> cuentasCorrientesClientes) {
		this.cuentasCorrientesClientes = cuentasCorrientesClientes;
	}

	public CuentasCorrientesCliente addCuentasCorrientesCliente(CuentasCorrientesCliente cuentasCorrientesCliente) {
		getCuentasCorrientesClientes().add(cuentasCorrientesCliente);
		cuentasCorrientesCliente.setUsuario(this);

		return cuentasCorrientesCliente;
	}

	public CuentasCorrientesCliente removeCuentasCorrientesCliente(CuentasCorrientesCliente cuentasCorrientesCliente) {
		getCuentasCorrientesClientes().remove(cuentasCorrientesCliente);
		cuentasCorrientesCliente.setUsuario(null);

		return cuentasCorrientesCliente;
	}

	public List<CuentasCorrientesProveedore> getCuentasCorrientesProveedores() {
		return this.cuentasCorrientesProveedores;
	}

	public void setCuentasCorrientesProveedores(List<CuentasCorrientesProveedore> cuentasCorrientesProveedores) {
		this.cuentasCorrientesProveedores = cuentasCorrientesProveedores;
	}

	public CuentasCorrientesProveedore addCuentasCorrientesProveedore(CuentasCorrientesProveedore cuentasCorrientesProveedore) {
		getCuentasCorrientesProveedores().add(cuentasCorrientesProveedore);
		cuentasCorrientesProveedore.setUsuario(this);

		return cuentasCorrientesProveedore;
	}

	public CuentasCorrientesProveedore removeCuentasCorrientesProveedore(CuentasCorrientesProveedore cuentasCorrientesProveedore) {
		getCuentasCorrientesProveedores().remove(cuentasCorrientesProveedore);
		cuentasCorrientesProveedore.setUsuario(null);

		return cuentasCorrientesProveedore;
	}
	
	public List<Cuota> getCuotas1() {
		return this.cuotas1;
	}

	public void setCuotas1(List<Cuota> cuotas1) {
		this.cuotas1 = cuotas1;
	}

	public Cuota addCuotas1(Cuota cuotas1) {
		getCuotas1().add(cuotas1);
		cuotas1.setUsuario1(this);

		return cuotas1;
	}

	public Cuota removeCuotas1(Cuota cuotas1) {
		getCuotas1().remove(cuotas1);
		cuotas1.setUsuario1(null);

		return cuotas1;
	}

	public List<Cuota> getCuotas2() {
		return this.cuotas2;
	}

	public void setCuotas2(List<Cuota> cuotas2) {
		this.cuotas2 = cuotas2;
	}

	public Cuota addCuotas2(Cuota cuotas2) {
		getCuotas2().add(cuotas2);
		cuotas2.setUsuario2(this);

		return cuotas2;
	}

	public Cuota removeCuotas2(Cuota cuotas2) {
		getCuotas2().remove(cuotas2);
		cuotas2.setUsuario2(null);

		return cuotas2;
	}

	public List<CuotasDetalle> getCuotasDetalles1() {
		return this.cuotasDetalles1;
	}

	public void setCuotasDetalles1(List<CuotasDetalle> cuotasDetalles1) {
		this.cuotasDetalles1 = cuotasDetalles1;
	}

	public CuotasDetalle addCuotasDetalles1(CuotasDetalle cuotasDetalles1) {
		getCuotasDetalles1().add(cuotasDetalles1);
		cuotasDetalles1.setUsuario1(this);

		return cuotasDetalles1;
	}

	public CuotasDetalle removeCuotasDetalles1(CuotasDetalle cuotasDetalles1) {
		getCuotasDetalles1().remove(cuotasDetalles1);
		cuotasDetalles1.setUsuario1(null);

		return cuotasDetalles1;
	}

	public List<CuotasDetalle> getCuotasDetalles2() {
		return this.cuotasDetalles2;
	}

	public void setCuotasDetalles2(List<CuotasDetalle> cuotasDetalles2) {
		this.cuotasDetalles2 = cuotasDetalles2;
	}

	public CuotasDetalle addCuotasDetalles2(CuotasDetalle cuotasDetalles2) {
		getCuotasDetalles2().add(cuotasDetalles2);
		cuotasDetalles2.setUsuario2(this);

		return cuotasDetalles2;
	}

	public CuotasDetalle removeCuotasDetalles2(CuotasDetalle cuotasDetalles2) {
		getCuotasDetalles2().remove(cuotasDetalles2);
		cuotasDetalles2.setUsuario2(null);

		return cuotasDetalles2;
	}

	public List<Gasto> getGastos1() {
		return this.gastos1;
	}

	public void setGastos1(List<Gasto> gastos1) {
		this.gastos1 = gastos1;
	}

	public Gasto addGastos1(Gasto gastos1) {
		getGastos1().add(gastos1);
		gastos1.setUsuario1(this);

		return gastos1;
	}

	public Gasto removeGastos1(Gasto gastos1) {
		getGastos1().remove(gastos1);
		gastos1.setUsuario1(null);

		return gastos1;
	}

	public List<Gasto> getGastos2() {
		return this.gastos2;
	}

	public void setGastos2(List<Gasto> gastos2) {
		this.gastos2 = gastos2;
	}

	public Gasto addGastos2(Gasto gastos2) {
		getGastos2().add(gastos2);
		gastos2.setUsuario2(this);

		return gastos2;
	}

	public Gasto removeGastos2(Gasto gastos2) {
		getGastos2().remove(gastos2);
		gastos2.setUsuario2(null);

		return gastos2;
	}

	public List<Gasto> getGastos3() {
		return this.gastos3;
	}

	public void setGastos3(List<Gasto> gastos3) {
		this.gastos3 = gastos3;
	}

	public Gasto addGastos3(Gasto gastos3) {
		getGastos3().add(gastos3);
		gastos3.setUsuario3(this);

		return gastos3;
	}

	public Gasto removeGastos3(Gasto gastos3) {
		getGastos3().remove(gastos3);
		gastos3.setUsuario3(null);

		return gastos3;
	}

	public List<ListaPrecio> getListaPrecios1() {
		return this.listaPrecios1;
	}

	public void setListaPrecios1(List<ListaPrecio> listaPrecios1) {
		this.listaPrecios1 = listaPrecios1;
	}

	public ListaPrecio addListaPrecios1(ListaPrecio listaPrecios1) {
		getListaPrecios1().add(listaPrecios1);
		listaPrecios1.setUsuario1(this);

		return listaPrecios1;
	}

	public ListaPrecio removeListaPrecios1(ListaPrecio listaPrecios1) {
		getListaPrecios1().remove(listaPrecios1);
		listaPrecios1.setUsuario1(null);

		return listaPrecios1;
	}

	public List<ListaPrecio> getListaPrecios2() {
		return this.listaPrecios2;
	}

	public void setListaPrecios2(List<ListaPrecio> listaPrecios2) {
		this.listaPrecios2 = listaPrecios2;
	}

	public ListaPrecio addListaPrecios2(ListaPrecio listaPrecios2) {
		getListaPrecios2().add(listaPrecios2);
		listaPrecios2.setUsuario2(this);

		return listaPrecios2;
	}

	public ListaPrecio removeListaPrecios2(ListaPrecio listaPrecios2) {
		getListaPrecios2().remove(listaPrecios2);
		listaPrecios2.setUsuario2(null);

		return listaPrecios2;
	}

	public List<ListaPrecio> getListaPrecios3() {
		return this.listaPrecios3;
	}

	public void setListaPrecios3(List<ListaPrecio> listaPrecios3) {
		this.listaPrecios3 = listaPrecios3;
	}

	public ListaPrecio addListaPrecios3(ListaPrecio listaPrecios3) {
		getListaPrecios3().add(listaPrecios3);
		listaPrecios3.setUsuario3(this);

		return listaPrecios3;
	}

	public ListaPrecio removeListaPrecios3(ListaPrecio listaPrecios3) {
		getListaPrecios3().remove(listaPrecios3);
		listaPrecios3.setUsuario3(null);

		return listaPrecios3;
	}

	public List<PagosCliente> getPagosClientes() {
		return this.pagosClientes;
	}

	public void setPagosClientes(List<PagosCliente> pagosClientes) {
		this.pagosClientes = pagosClientes;
	}

	public PagosCliente addPagosCliente(PagosCliente pagosCliente) {
		getPagosClientes().add(pagosCliente);
		pagosCliente.setUsuario(this);

		return pagosCliente;
	}

	public PagosCliente removePagosCliente(PagosCliente pagosCliente) {
		getPagosClientes().remove(pagosCliente);
		pagosCliente.setUsuario(null);

		return pagosCliente;
	}

	public List<PagosProveedore> getPagosProveedores() {
		return this.pagosProveedores;
	}

	public void setPagosProveedores(List<PagosProveedore> pagosProveedores) {
		this.pagosProveedores = pagosProveedores;
	}

	public PagosProveedore addPagosProveedore(PagosProveedore pagosProveedore) {
		getPagosProveedores().add(pagosProveedore);
		pagosProveedore.setUsuario(this);

		return pagosProveedore;
	}

	public PagosProveedore removePagosProveedore(PagosProveedore pagosProveedore) {
		getPagosProveedores().remove(pagosProveedore);
		pagosProveedore.setUsuario(null);

		return pagosProveedore;
	}

	public List<Producto> getProductos1() {
		return this.productos1;
	}

	public void setProductos1(List<Producto> productos1) {
		this.productos1 = productos1;
	}

	public Producto addProductos1(Producto productos1) {
		getProductos1().add(productos1);
		productos1.setUsuario1(this);

		return productos1;
	}

	public Producto removeProductos1(Producto productos1) {
		getProductos1().remove(productos1);
		productos1.setUsuario1(null);

		return productos1;
	}

	public List<Producto> getProductos2() {
		return this.productos2;
	}

	public void setProductos2(List<Producto> productos2) {
		this.productos2 = productos2;
	}

	public Producto addProductos2(Producto productos2) {
		getProductos2().add(productos2);
		productos2.setUsuario2(this);

		return productos2;
	}

	public Producto removeProductos2(Producto productos2) {
		getProductos2().remove(productos2);
		productos2.setUsuario2(null);

		return productos2;
	}

	public List<Producto> getProductos3() {
		return this.productos3;
	}

	public void setProductos3(List<Producto> productos3) {
		this.productos3 = productos3;
	}

	public Producto addProductos3(Producto productos3) {
		getProductos3().add(productos3);
		productos3.setUsuario3(this);

		return productos3;
	}

	public Producto removeProductos3(Producto productos3) {
		getProductos3().remove(productos3);
		productos3.setUsuario3(null);

		return productos3;
	}

	public List<Proveedore> getProveedores1() {
		return this.proveedores1;
	}

	public void setProveedores1(List<Proveedore> proveedores1) {
		this.proveedores1 = proveedores1;
	}

	public Proveedore addProveedores1(Proveedore proveedores1) {
		getProveedores1().add(proveedores1);
		proveedores1.setUsuario1(this);

		return proveedores1;
	}

	public Proveedore removeProveedores1(Proveedore proveedores1) {
		getProveedores1().remove(proveedores1);
		proveedores1.setUsuario1(null);

		return proveedores1;
	}

	public List<Proveedore> getProveedores2() {
		return this.proveedores2;
	}

	public void setProveedores2(List<Proveedore> proveedores2) {
		this.proveedores2 = proveedores2;
	}

	public Proveedore addProveedores2(Proveedore proveedores2) {
		getProveedores2().add(proveedores2);
		proveedores2.setUsuario2(this);

		return proveedores2;
	}

	public Proveedore removeProveedores2(Proveedore proveedores2) {
		getProveedores2().remove(proveedores2);
		proveedores2.setUsuario2(null);

		return proveedores2;
	}

	public List<Proveedore> getProveedores3() {
		return this.proveedores3;
	}

	public void setProveedores3(List<Proveedore> proveedores3) {
		this.proveedores3 = proveedores3;
	}

	public Proveedore addProveedores3(Proveedore proveedores3) {
		getProveedores3().add(proveedores3);
		proveedores3.setUsuario3(this);

		return proveedores3;
	}

	public Proveedore removeProveedores3(Proveedore proveedores3) {
		getProveedores3().remove(proveedores3);
		proveedores3.setUsuario3(null);

		return proveedores3;
	}

	public List<UnidadMovil> getUnidadMovils1() {
		return this.unidadMovils1;
	}

	public void setUnidadMovils1(List<UnidadMovil> unidadMovils1) {
		this.unidadMovils1 = unidadMovils1;
	}

	public UnidadMovil addUnidadMovils1(UnidadMovil unidadMovils1) {
		getUnidadMovils1().add(unidadMovils1);
		unidadMovils1.setUsuario1(this);

		return unidadMovils1;
	}

	public UnidadMovil removeUnidadMovils1(UnidadMovil unidadMovils1) {
		getUnidadMovils1().remove(unidadMovils1);
		unidadMovils1.setUsuario1(null);

		return unidadMovils1;
	}

	public List<UnidadMovil> getUnidadMovils2() {
		return this.unidadMovils2;
	}

	public void setUnidadMovils2(List<UnidadMovil> unidadMovils2) {
		this.unidadMovils2 = unidadMovils2;
	}

	public UnidadMovil addUnidadMovils2(UnidadMovil unidadMovils2) {
		getUnidadMovils2().add(unidadMovils2);
		unidadMovils2.setUsuario2(this);

		return unidadMovils2;
	}

	public UnidadMovil removeUnidadMovils2(UnidadMovil unidadMovils2) {
		getUnidadMovils2().remove(unidadMovils2);
		unidadMovils2.setUsuario2(null);

		return unidadMovils2;
	}

	public List<UnidadMovil> getUnidadMovils3() {
		return this.unidadMovils3;
	}

	public void setUnidadMovils3(List<UnidadMovil> unidadMovils3) {
		this.unidadMovils3 = unidadMovils3;
	}

	public UnidadMovil addUnidadMovils3(UnidadMovil unidadMovils3) {
		getUnidadMovils3().add(unidadMovils3);
		unidadMovils3.setUsuario3(this);

		return unidadMovils3;
	}

	public UnidadMovil removeUnidadMovils3(UnidadMovil unidadMovils3) {
		getUnidadMovils3().remove(unidadMovils3);
		unidadMovils3.setUsuario3(null);

		return unidadMovils3;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public List<Usuario> getUsuarios1() {
		return this.usuarios1;
	}

	public void setUsuarios1(List<Usuario> usuarios1) {
		this.usuarios1 = usuarios1;
	}

	public Usuario addUsuarios1(Usuario usuarios1) {
		getUsuarios1().add(usuarios1);
		usuarios1.setUsuario1(this);

		return usuarios1;
	}

	public Usuario removeUsuarios1(Usuario usuarios1) {
		getUsuarios1().remove(usuarios1);
		usuarios1.setUsuario1(null);

		return usuarios1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Usuario> getUsuarios2() {
		return this.usuarios2;
	}

	public void setUsuarios2(List<Usuario> usuarios2) {
		this.usuarios2 = usuarios2;
	}

	public Usuario addUsuarios2(Usuario usuarios2) {
		getUsuarios2().add(usuarios2);
		usuarios2.setUsuario2(this);

		return usuarios2;
	}

	public Usuario removeUsuarios2(Usuario usuarios2) {
		getUsuarios2().remove(usuarios2);
		usuarios2.setUsuario2(null);

		return usuarios2;
	}

	public Usuario getUsuario3() {
		return this.usuario3;
	}

	public void setUsuario3(Usuario usuario3) {
		this.usuario3 = usuario3;
	}

	public List<Usuario> getUsuarios3() {
		return this.usuarios3;
	}

	public void setUsuarios3(List<Usuario> usuarios3) {
		this.usuarios3 = usuarios3;
	}

	public Usuario addUsuarios3(Usuario usuarios3) {
		getUsuarios3().add(usuarios3);
		usuarios3.setUsuario3(this);

		return usuarios3;
	}

	public Usuario removeUsuarios3(Usuario usuarios3) {
		getUsuarios3().remove(usuarios3);
		usuarios3.setUsuario3(null);

		return usuarios3;
	}

	public List<Consignacion> getConsignacions1() {
		return this.consignacions1;
	}

	public void setConsignacions1(List<Consignacion> consignacions1) {
		this.consignacions1 = consignacions1;
	}

	public Consignacion addConsignacions1(Consignacion consignacions1) {
		getConsignacions1().add(consignacions1);
		consignacions1.setUsuario1(this);

		return consignacions1;
	}

	public Consignacion removeConsignacions1(Consignacion consignacions1) {
		getConsignacions1().remove(consignacions1);
		consignacions1.setUsuario1(null);

		return consignacions1;
	}

	public List<Consignacion> getConsignacions2() {
		return this.consignacions2;
	}

	public void setConsignacions2(List<Consignacion> consignacions2) {
		this.consignacions2 = consignacions2;
	}

	public Consignacion addConsignacions2(Consignacion consignacions2) {
		getConsignacions2().add(consignacions2);
		consignacions2.setUsuario2(this);

		return consignacions2;
	}

	public Consignacion removeConsignacions2(Consignacion consignacions2) {
		getConsignacions2().remove(consignacions2);
		consignacions2.setUsuario2(null);

		return consignacions2;
	}

	public List<Consignacion> getConsignacions3() {
		return this.consignacions3;
	}

	public void setConsignacions3(List<Consignacion> consignacions3) {
		this.consignacions3 = consignacions3;
	}

	public Consignacion addConsignacions3(Consignacion consignacions3) {
		getConsignacions3().add(consignacions3);
		consignacions3.setUsuario3(this);

		return consignacions3;
	}

	public Consignacion removeConsignacions3(Consignacion consignacions3) {
		getConsignacions3().remove(consignacions3);
		consignacions3.setUsuario3(null);

		return consignacions3;
	}

	public List<Devolucione> getDevoluciones1() {
		return this.devoluciones1;
	}

	public void setDevoluciones1(List<Devolucione> devoluciones1) {
		this.devoluciones1 = devoluciones1;
	}

	public Devolucione addDevoluciones1(Devolucione devoluciones1) {
		getDevoluciones1().add(devoluciones1);
		devoluciones1.setUsuario1(this);

		return devoluciones1;
	}

	public Devolucione removeDevoluciones1(Devolucione devoluciones1) {
		getDevoluciones1().remove(devoluciones1);
		devoluciones1.setUsuario1(null);

		return devoluciones1;
	}

	public List<Devolucione> getDevoluciones2() {
		return this.devoluciones2;
	}

	public void setDevoluciones2(List<Devolucione> devoluciones2) {
		this.devoluciones2 = devoluciones2;
	}

	public Devolucione addDevoluciones2(Devolucione devoluciones2) {
		getDevoluciones2().add(devoluciones2);
		devoluciones2.setUsuario2(this);

		return devoluciones2;
	}

	public Devolucione removeDevoluciones2(Devolucione devoluciones2) {
		getDevoluciones2().remove(devoluciones2);
		devoluciones2.setUsuario2(null);

		return devoluciones2;
	}

	public List<Devolucione> getDevoluciones3() {
		return this.devoluciones3;
	}

	public void setDevoluciones3(List<Devolucione> devoluciones3) {
		this.devoluciones3 = devoluciones3;
	}

	public Devolucione addDevoluciones3(Devolucione devoluciones3) {
		getDevoluciones3().add(devoluciones3);
		devoluciones3.setUsuario3(this);

		return devoluciones3;
	}

	public Devolucione removeDevoluciones3(Devolucione devoluciones3) {
		getDevoluciones3().remove(devoluciones3);
		devoluciones3.setUsuario3(null);

		return devoluciones3;
	}

	public List<Rubro> getRubros1() {
		return this.rubros1;
	}

	public void setRubros1(List<Rubro> rubros1) {
		this.rubros1 = rubros1;
	}

	public Rubro addRubros1(Rubro rubros1) {
		getRubros1().add(rubros1);
		rubros1.setUsuario1(this);

		return rubros1;
	}

	public Rubro removeRubros1(Rubro rubros1) {
		getRubros1().remove(rubros1);
		rubros1.setUsuario1(null);

		return rubros1;
	}

	public List<Rubro> getRubros2() {
		return this.rubros2;
	}

	public void setRubros2(List<Rubro> rubros2) {
		this.rubros2 = rubros2;
	}

	public Rubro addRubros2(Rubro rubros2) {
		getRubros2().add(rubros2);
		rubros2.setUsuario2(this);

		return rubros2;
	}

	public Rubro removeRubros2(Rubro rubros2) {
		getRubros2().remove(rubros2);
		rubros2.setUsuario2(null);

		return rubros2;
	}

	public List<Rubro> getRubros3() {
		return this.rubros3;
	}

	public void setRubros3(List<Rubro> rubros3) {
		this.rubros3 = rubros3;
	}

	public Rubro addRubros3(Rubro rubros3) {
		getRubros3().add(rubros3);
		rubros3.setUsuario3(this);

		return rubros3;
	}

	public Rubro removeRubros3(Rubro rubros3) {
		getRubros3().remove(rubros3);
		rubros3.setUsuario3(null);

		return rubros3;
	}

	public List<Venta> getVentas1() {
		return this.ventas1;
	}

	public void setVentas1(List<Venta> ventas1) {
		this.ventas1 = ventas1;
	}

	public Venta addVentas1(Venta ventas1) {
		getVentas1().add(ventas1);
		ventas1.setUsuario1(this);

		return ventas1;
	}

	public Venta removeVentas1(Venta ventas1) {
		getVentas1().remove(ventas1);
		ventas1.setUsuario1(null);

		return ventas1;
	}

	public List<Venta> getVentas2() {
		return this.ventas2;
	}

	public void setVentas2(List<Venta> ventas2) {
		this.ventas2 = ventas2;
	}

	public Venta addVentas2(Venta ventas2) {
		getVentas2().add(ventas2);
		ventas2.setUsuario2(this);

		return ventas2;
	}

	public Venta removeVentas2(Venta ventas2) {
		getVentas2().remove(ventas2);
		ventas2.setUsuario2(null);

		return ventas2;
	}

	public List<Venta> getVentas3() {
		return this.ventas3;
	}

	public void setVentas3(List<Venta> ventas3) {
		this.ventas3 = ventas3;
	}

	public Venta addVentas3(Venta ventas3) {
		getVentas3().add(ventas3);
		ventas3.setUsuario3(this);

		return ventas3;
	}

	public Venta removeVentas3(Venta ventas3) {
		getVentas3().remove(ventas3);
		ventas3.setUsuario3(null);

		return ventas3;
	}
	
	public List<Stock> getStocks1() {
		return this.stocks1;
	}

	public void setStocks1(List<Stock> stocks1) {
		this.stocks1 = stocks1;
	}

	public Stock addStocks1(Stock stocks1) {
		getStocks1().add(stocks1);
		stocks1.setUsuario1(this);

		return stocks1;
	}

	public Stock removeStocks1(Stock stocks1) {
		getStocks1().remove(stocks1);
		stocks1.setUsuario1(null);

		return stocks1;
	}

	public List<Stock> getStocks2() {
		return this.stocks2;
	}

	public void setStocks2(List<Stock> stocks2) {
		this.stocks2 = stocks2;
	}

	public Stock addStocks2(Stock stocks2) {
		getStocks2().add(stocks2);
		stocks2.setUsuario2(this);

		return stocks2;
	}

	public Stock removeStocks2(Stock stocks2) {
		getStocks2().remove(stocks2);
		stocks2.setUsuario2(null);

		return stocks2;
	}

	public List<Stock> getStocks3() {
		return this.stocks3;
	}

	public void setStocks3(List<Stock> stocks3) {
		this.stocks3 = stocks3;
	}

	public Stock addStocks3(Stock stocks3) {
		getStocks3().add(stocks3);
		stocks3.setUsuario3(this);

		return stocks3;
	}

	public Stock removeStocks3(Stock stocks3) {
		getStocks3().remove(stocks3);
		stocks3.setUsuario3(null);

		return stocks3;
	}
	
	public List<VentasCon> getVentasCons1() {
		return this.ventasCons1;
	}

	public void setVentasCons1(List<VentasCon> ventasCons1) {
		this.ventasCons1 = ventasCons1;
	}

	public VentasCon addVentasCons1(VentasCon ventasCons1) {
		getVentasCons1().add(ventasCons1);
		ventasCons1.setUsuario1(this);

		return ventasCons1;
	}

	public VentasCon removeVentasCons1(VentasCon ventasCons1) {
		getVentasCons1().remove(ventasCons1);
		ventasCons1.setUsuario1(null);

		return ventasCons1;
	}

	public List<VentasCon> getVentasCons2() {
		return this.ventasCons2;
	}

	public void setVentasCons2(List<VentasCon> ventasCons2) {
		this.ventasCons2 = ventasCons2;
	}

	public VentasCon addVentasCons2(VentasCon ventasCons2) {
		getVentasCons2().add(ventasCons2);
		ventasCons2.setUsuario2(this);

		return ventasCons2;
	}

	public VentasCon removeVentasCons2(VentasCon ventasCons2) {
		getVentasCons2().remove(ventasCons2);
		ventasCons2.setUsuario2(null);

		return ventasCons2;
	}

	public List<VentasCon> getVentasCons3() {
		return this.ventasCons3;
	}

	public void setVentasCons3(List<VentasCon> ventasCons3) {
		this.ventasCons3 = ventasCons3;
	}

	public VentasCon addVentasCons3(VentasCon ventasCons3) {
		getVentasCons3().add(ventasCons3);
		ventasCons3.setUsuario3(this);

		return ventasCons3;
	}

	public VentasCon removeVentasCons3(VentasCon ventasCons3) {
		getVentasCons3().remove(ventasCons3);
		ventasCons3.setUsuario3(null);

		return ventasCons3;
	}
	
	public List<GarantiasCliente> getGarantiasClientes1() {
		return this.garantiasClientes1;
	}

	public void setGarantiasClientes1(List<GarantiasCliente> garantiasClientes1) {
		this.garantiasClientes1 = garantiasClientes1;
	}

	public GarantiasCliente addGarantiasClientes1(GarantiasCliente garantiasClientes1) {
		getGarantiasClientes1().add(garantiasClientes1);
		garantiasClientes1.setUsuario1(this);

		return garantiasClientes1;
	}

	public GarantiasCliente removeGarantiasClientes1(GarantiasCliente garantiasClientes1) {
		getGarantiasClientes1().remove(garantiasClientes1);
		garantiasClientes1.setUsuario1(null);

		return garantiasClientes1;
	}

	public List<GarantiasCliente> getGarantiasClientes2() {
		return this.garantiasClientes2;
	}

	public void setGarantiasClientes2(List<GarantiasCliente> garantiasClientes2) {
		this.garantiasClientes2 = garantiasClientes2;
	}

	public GarantiasCliente addGarantiasClientes2(GarantiasCliente garantiasClientes2) {
		getGarantiasClientes2().add(garantiasClientes2);
		garantiasClientes2.setUsuario2(this);

		return garantiasClientes2;
	}

	public GarantiasCliente removeGarantiasClientes2(GarantiasCliente garantiasClientes2) {
		getGarantiasClientes2().remove(garantiasClientes2);
		garantiasClientes2.setUsuario2(null);

		return garantiasClientes2;
	}

	public List<GarantiasCliente> getGarantiasClientes3() {
		return this.garantiasClientes3;
	}

	public void setGarantiasClientes3(List<GarantiasCliente> garantiasClientes3) {
		this.garantiasClientes3 = garantiasClientes3;
	}

	public GarantiasCliente addGarantiasClientes3(GarantiasCliente garantiasClientes3) {
		getGarantiasClientes3().add(garantiasClientes3);
		garantiasClientes3.setUsuario3(this);

		return garantiasClientes3;
	}

	public GarantiasCliente removeGarantiasClientes3(GarantiasCliente garantiasClientes3) {
		getGarantiasClientes3().remove(garantiasClientes3);
		garantiasClientes3.setUsuario3(null);

		return garantiasClientes3;
	}
	
	public List<GarantiasProveedore> getGarantiasProveedores1() {
		return this.garantiasProveedores1;
	}

	public void setGarantiasProveedores1(List<GarantiasProveedore> garantiasProveedores1) {
		this.garantiasProveedores1 = garantiasProveedores1;
	}

	public GarantiasProveedore addGarantiasProveedores1(GarantiasProveedore garantiasProveedores1) {
		getGarantiasProveedores1().add(garantiasProveedores1);
		garantiasProveedores1.setUsuario1(this);

		return garantiasProveedores1;
	}

	public GarantiasProveedore removeGarantiasProveedores1(GarantiasProveedore garantiasProveedores1) {
		getGarantiasProveedores1().remove(garantiasProveedores1);
		garantiasProveedores1.setUsuario1(null);

		return garantiasProveedores1;
	}

	public List<GarantiasProveedore> getGarantiasProveedores2() {
		return this.garantiasProveedores2;
	}

	public void setGarantiasProveedores2(List<GarantiasProveedore> garantiasProveedores2) {
		this.garantiasProveedores2 = garantiasProveedores2;
	}

	public GarantiasProveedore addGarantiasProveedores2(GarantiasProveedore garantiasProveedores2) {
		getGarantiasProveedores2().add(garantiasProveedores2);
		garantiasProveedores2.setUsuario2(this);

		return garantiasProveedores2;
	}

	public GarantiasProveedore removeGarantiasProveedores2(GarantiasProveedore garantiasProveedores2) {
		getGarantiasProveedores2().remove(garantiasProveedores2);
		garantiasProveedores2.setUsuario2(null);

		return garantiasProveedores2;
	}

	public List<GarantiasProveedore> getGarantiasProveedores3() {
		return this.garantiasProveedores3;
	}

	public void setGarantiasProveedores3(List<GarantiasProveedore> garantiasProveedores3) {
		this.garantiasProveedores3 = garantiasProveedores3;
	}

	public GarantiasProveedore addGarantiasProveedores3(GarantiasProveedore garantiasProveedores3) {
		getGarantiasProveedores3().add(garantiasProveedores3);
		garantiasProveedores3.setUsuario3(this);

		return garantiasProveedores3;
	}

	public GarantiasProveedore removeGarantiasProveedores3(GarantiasProveedore garantiasProveedores3) {
		getGarantiasProveedores3().remove(garantiasProveedores3);
		garantiasProveedores3.setUsuario3(null);

		return garantiasProveedores3;
	}
	
	public List<CuotasVenta> getCuotasVentas1() {
		return this.cuotasVentas1;
	}

	public void setCuotasVentas1(List<CuotasVenta> cuotasVentas1) {
		this.cuotasVentas1 = cuotasVentas1;
	}

	public CuotasVenta addCuotasVentas1(CuotasVenta cuotasVentas1) {
		getCuotasVentas1().add(cuotasVentas1);
		cuotasVentas1.setUsuario1(this);

		return cuotasVentas1;
	}

	public CuotasVenta removeCuotasVentas1(CuotasVenta cuotasVentas1) {
		getCuotasVentas1().remove(cuotasVentas1);
		cuotasVentas1.setUsuario1(null);

		return cuotasVentas1;
	}

	public List<CuotasVenta> getCuotasVentas2() {
		return this.cuotasVentas2;
	}

	public void setCuotasVentas2(List<CuotasVenta> cuotasVentas2) {
		this.cuotasVentas2 = cuotasVentas2;
	}

	public CuotasVenta addCuotasVentas2(CuotasVenta cuotasVentas2) {
		getCuotasVentas2().add(cuotasVentas2);
		cuotasVentas2.setUsuario2(this);

		return cuotasVentas2;
	}

	public CuotasVenta removeCuotasVentas2(CuotasVenta cuotasVentas2) {
		getCuotasVentas2().remove(cuotasVentas2);
		cuotasVentas2.setUsuario2(null);

		return cuotasVentas2;
	}

	public List<CuotasVentasDetalle> getCuotasVentasDetalles1() {
		return this.cuotasVentasDetalles1;
	}

	public void setCuotasVentasDetalles1(List<CuotasVentasDetalle> cuotasVentasDetalles1) {
		this.cuotasVentasDetalles1 = cuotasVentasDetalles1;
	}

	public CuotasVentasDetalle addCuotasVentasDetalles1(CuotasVentasDetalle cuotasVentasDetalles1) {
		getCuotasVentasDetalles1().add(cuotasVentasDetalles1);
		cuotasVentasDetalles1.setUsuario1(this);

		return cuotasVentasDetalles1;
	}

	public CuotasVentasDetalle removeCuotasVentasDetalles1(CuotasVentasDetalle cuotasVentasDetalles1) {
		getCuotasVentasDetalles1().remove(cuotasVentasDetalles1);
		cuotasVentasDetalles1.setUsuario1(null);

		return cuotasVentasDetalles1;
	}

	public List<CuotasVentasDetalle> getCuotasVentasDetalles2() {
		return this.cuotasVentasDetalles2;
	}

	public void setCuotasVentasDetalles2(List<CuotasVentasDetalle> cuotasVentasDetalles2) {
		this.cuotasVentasDetalles2 = cuotasVentasDetalles2;
	}

	public CuotasVentasDetalle addCuotasVentasDetalles2(CuotasVentasDetalle cuotasVentasDetalles2) {
		getCuotasVentasDetalles2().add(cuotasVentasDetalles2);
		cuotasVentasDetalles2.setUsuario2(this);

		return cuotasVentasDetalles2;
	}

	public CuotasVentasDetalle removeCuotasVentasDetalles2(CuotasVentasDetalle cuotasVentasDetalles2) {
		getCuotasVentasDetalles2().remove(cuotasVentasDetalles2);
		cuotasVentasDetalles2.setUsuario2(null);

		return cuotasVentasDetalles2;
	}
	
	public List<EConsignacion> getEConsignacions() {
		return this.EConsignacions;
	}

	public void setEConsignacions(List<EConsignacion> EConsignacions) {
		this.EConsignacions = EConsignacions;
	}

	public EConsignacion addEConsignacion(EConsignacion EConsignacion) {
		getEConsignacions().add(EConsignacion);
		EConsignacion.setUsuario(this);

		return EConsignacion;
	}

	public EConsignacion removeEConsignacion(EConsignacion EConsignacion) {
		getEConsignacions().remove(EConsignacion);
		EConsignacion.setUsuario(null);

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
		mensaje.setUsuario(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setUsuario(null);

		return mensaje;
	}
	
	public List<Tecnico> getTecnicos1() {
		return this.tecnicos1;
	}

	public void setTecnicos1(List<Tecnico> tecnicos1) {
		this.tecnicos1 = tecnicos1;
	}

	public Tecnico addTecnicos1(Tecnico tecnicos1) {
		getTecnicos1().add(tecnicos1);
		tecnicos1.setUsuario1(this);

		return tecnicos1;
	}

	public Tecnico removeTecnicos1(Tecnico tecnicos1) {
		getTecnicos1().remove(tecnicos1);
		tecnicos1.setUsuario1(null);

		return tecnicos1;
	}

	public List<Tecnico> getTecnicos2() {
		return this.tecnicos2;
	}

	public void setTecnicos2(List<Tecnico> tecnicos2) {
		this.tecnicos2 = tecnicos2;
	}

	public Tecnico addTecnicos2(Tecnico tecnicos2) {
		getTecnicos2().add(tecnicos2);
		tecnicos2.setUsuario2(this);

		return tecnicos2;
	}

	public Tecnico removeTecnicos2(Tecnico tecnicos2) {
		getTecnicos2().remove(tecnicos2);
		tecnicos2.setUsuario2(null);

		return tecnicos2;
	}

	public List<Tecnico> getTecnicos3() {
		return this.tecnicos3;
	}

	public void setTecnicos3(List<Tecnico> tecnicos3) {
		this.tecnicos3 = tecnicos3;
	}

	public Tecnico addTecnicos3(Tecnico tecnicos3) {
		getTecnicos3().add(tecnicos3);
		tecnicos3.setUsuario3(this);

		return tecnicos3;
	}

	public Tecnico removeTecnicos3(Tecnico tecnicos3) {
		getTecnicos3().remove(tecnicos3);
		tecnicos3.setUsuario3(null);

		return tecnicos3;
	}

}