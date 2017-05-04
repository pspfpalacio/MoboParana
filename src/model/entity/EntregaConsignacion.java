package model.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the entrega_consignacion database table.
 * 
 */
@Entity
@Table(name="entrega_consignacion")
@NamedQuery(name="EntregaConsignacion.findAll", query="SELECT e FROM EntregaConsignacion e")
public class EntregaConsignacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String concepto;
	
	private boolean cuota;

	private boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private float monto;

	//bi-directional many-to-one association to Consignacion
	@ManyToOne
	@JoinColumn(name="id_consignacion")
	private Consignacion consignacion;
	
	//bi-directional many-to-one association to CuotasDetalle
	@ManyToOne
	@JoinColumn(name="id_cuota_detalle")
	private CuotasDetalle cuotasDetalle;

	public EntregaConsignacion() {
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
	
	public boolean getCuota() {
		return this.cuota;
	}

	public void setCuota(boolean cuota) {
		this.cuota = cuota;
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
	
	public CuotasDetalle getCuotasDetalle() {
		return this.cuotasDetalle;
	}

	public void setCuotasDetalle(CuotasDetalle cuotasDetalle) {
		this.cuotasDetalle = cuotasDetalle;
	}

}