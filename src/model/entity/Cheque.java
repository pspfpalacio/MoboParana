package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cheques database table.
 * 
 */
@Entity
@Table(name="cheques")
@NamedQuery(name="Cheque.findAll", query="SELECT c FROM Cheque c")
public class Cheque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="codigo_cuenta_cliente")
	private String codigoCuentaCliente;

	@Column(name="datos_entidad")
	private String datosEntidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float importe;

	private String lugar;

	private String numero;

	private String persona;

	private String serie;

	private String tipo;

	//bi-directional many-to-one association to PagosCliente
	@OneToMany(mappedBy="cheque")
	private List<PagosCliente> pagosClientes;

	public Cheque() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoCuentaCliente() {
		return this.codigoCuentaCliente;
	}

	public void setCodigoCuentaCliente(String codigoCuentaCliente) {
		this.codigoCuentaCliente = codigoCuentaCliente;
	}

	public String getDatosEntidad() {
		return this.datosEntidad;
	}

	public void setDatosEntidad(String datosEntidad) {
		this.datosEntidad = datosEntidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return this.importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPersona() {
		return this.persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<PagosCliente> getPagosClientes() {
		return this.pagosClientes;
	}

	public void setPagosClientes(List<PagosCliente> pagosClientes) {
		this.pagosClientes = pagosClientes;
	}

	public PagosCliente addPagosCliente(PagosCliente pagosCliente) {
		getPagosClientes().add(pagosCliente);
		pagosCliente.setCheque(this);

		return pagosCliente;
	}

	public PagosCliente removePagosCliente(PagosCliente pagosCliente) {
		getPagosClientes().remove(pagosCliente);
		pagosCliente.setCheque(null);

		return pagosCliente;
	}

}