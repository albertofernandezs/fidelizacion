package py.com.progweb.prueba.model;

import java.util.ArrayList;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="cabecera")
public class UsoCabecera {
	@Id
	@Column(name="id_cabecera")
	@Basic(optional=false)
	@GeneratedValue(generator="cabeceraSec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="cabeceraSec", sequenceName = "cabecera_sec", allocationSize = 0 )
	private Integer idCabecera;
	
	@Column(name="fecha_uso")
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaUso;
	
	
	@Column(name="puntaje_utilizado")
	private Float puntajeUtilizado;
	
	@JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
	@ManyToOne(optional=false)
	private Cliente cliente;
	
	@JoinColumn(name="id_concepto", referencedColumnName = "id_concepto")
	@ManyToOne(optional=false)
	private Concepto concepto;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cabecera")
	//@Fetch(value = FetchMode.SUBSELECT)
	private List<UsoDetalle> listaDetalle;

	
	

	public UsoCabecera() {
		
	}
	
	public UsoCabecera(Date fechaUso, Float puntajeUtilizado, Cliente cliente, Concepto concepto) {
		super();
		this.fechaUso = fechaUso;
		this.puntajeUtilizado = puntajeUtilizado;
		this.cliente = cliente;
		this.listaDetalle= new ArrayList<UsoDetalle>();
		this.concepto=concepto;
	}


	public Integer getIdCabecera() {
		return idCabecera;
	}

	public void setIdCabecera(Integer idCabecera) {
		this.idCabecera = idCabecera;
	}

	public Date getFechaUso() {
		return fechaUso;
	}

	public void setFechaUso(Date fechaUso) {
		this.fechaUso = fechaUso;
	}

	public Float getPuntajeUtilizado() {
		return puntajeUtilizado;
	}

	public void setPuntajeUtilizado(Float puntajeUtilizado) {
		this.puntajeUtilizado = puntajeUtilizado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<UsoDetalle> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<UsoDetalle> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}


	public Concepto getConcepto() {
		return concepto;
	}


	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
	
	
	
	
	
	
}