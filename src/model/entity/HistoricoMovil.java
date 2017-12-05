package model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historico_movil database table.
 * 
 */
@Entity
@Table(name="historico_movil")
@NamedQuery(name="HistoricoMovil.findAll", query="SELECT h FROM HistoricoMovil h")
public class HistoricoMovil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name="id_movimiento")
	private int idMovimiento;

	private String movimiento;

	@Column(name="nro_imei")
	private String nroImei;

	private String tipo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public HistoricoMovil() {
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

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdMovimiento() {
		return this.idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getMovimiento() {
		return this.movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public String getNroImei() {
		return this.nroImei;
	}

	public void setNroImei(String nroImei) {
		this.nroImei = nroImei;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}