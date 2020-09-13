package py.com.progweb.prueba.model;


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="concepto")
public class Concepto {
	
	@Id
	@Column(name="id_concepto")
	@Basic(optional=false)
	@GeneratedValue(generator="concepto_Sec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="concepto_Sec", sequenceName = "concepto_sec", allocationSize = 0 )
	private Integer idConcepto;
	
	@Column(name="descripcion", length = 200)
	private String descripcion;
	
	
	@Column(name="puntos")
	private Float puntos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "concepto")
	//@Fetch(value = FetchMode.SUBSELECT)
	private List<UsoCabecera> listaCabecera;


	public Integer getIdConcepto() {
		return idConcepto;
	}


	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Float getPuntos() {
		return puntos;
	}


	public void setPuntos(Float puntos) {
		this.puntos = puntos;
	}


	public List<UsoCabecera> getListaCabecera() {
		return listaCabecera;
	}


	public void setListaCabecera(List<UsoCabecera> listaCabecera) {
		this.listaCabecera = listaCabecera;
	}
	
	
	
	
	

}
