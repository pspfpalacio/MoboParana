package model.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name="parametros")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cant_meses_cp")
	private int cantMesesCp;

	public Parametro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantMesesCp() {
		return this.cantMesesCp;
	}

	public void setCantMesesCp(int cantMesesCp) {
		this.cantMesesCp = cantMesesCp;
	}

}