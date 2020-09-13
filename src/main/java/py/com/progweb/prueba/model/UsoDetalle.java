package py.com.progweb.prueba.model;

import java.util.Date;
import java.util.List;

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
@Table(name="detalle")
public class UsoDetalle {
	
	@Id
	@Column(name="id_detalle")
	@Basic(optional=false)
	@GeneratedValue(generator="detalle_sec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="detalle_sec", sequenceName = "detalle_sec", allocationSize = 0 )
	private Integer idDetalle;
	
	@Column(name="puntaje_utilizado")
	private Float puntajeUtilizado;
	
	@JoinColumn(name="id_cabecera", referencedColumnName = "id_cabecera")
	//@ManyToOne(cascade=CascadeType.PERSIST, fetch= FetchType.LAZY)
	@ManyToOne(optional=false)
	private UsoCabecera cabecera;
	
	@JoinColumn(name="id_bolsa", referencedColumnName = "id_bolsa")
	//@ManyToOne(cascade=CascadeType.PERSIST,fetch= FetchType.LAZY)
	@ManyToOne(optional=false)
	private Bolsa bolsa;

	
	public UsoDetalle() {
		super();
	}
	
	

	public UsoDetalle(Float puntajeUtilizado, UsoCabecera cabecera, Bolsa bolsa) {
		super();
		this.puntajeUtilizado = puntajeUtilizado;
		this.cabecera = cabecera;
		this.bolsa = bolsa;
	}



	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Float getPuntajeUtilizado() {
		return puntajeUtilizado;
	}

	public void setPuntajeUtilizado(Float puntajeUtilizado) {
		this.puntajeUtilizado = puntajeUtilizado;
	}

	public UsoCabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(UsoCabecera cabecera) {
		this.cabecera = cabecera;
	}

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	
	
}
