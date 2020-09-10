package py.com.progweb.prueba.model;



import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="reglas")
public class Reglas {
	
	@Id
	@Column(name="id_regla")
	@Basic(optional=false)
	@GeneratedValue(generator="regla_sec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="regla_sec", sequenceName = "regla_sec", allocationSize = 0 )
	private Integer idRegla;
	
	@Column(name="inferior")
	@Basic(optional=false)
	private Float inferior;
	
	@Column(name="superior")
	@Basic(optional=false)
	private Float superior;
	
	@Column(name="monto")
	@Basic(optional=false)
	private Float monto;

	public Integer getIdRegla() {
		return idRegla;
	}

	public void setIdRegla(Integer idRegla) {
		this.idRegla = idRegla;
	}

	public Float getInferior() {
		return inferior;
	}

	public void setInferior(Float inferior) {
		this.inferior = inferior;
	}

	public Float getSuperior() {
		return superior;
	}

	public void setSuperior(Float superior) {
		this.superior = superior;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
	

}
