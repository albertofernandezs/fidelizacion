package py.com.progweb.prueba.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="vencimiento")
public class Vencimiento {
	
	@Id
	@Column(name="id_vencimiento")
	@Basic(optional=false)
	@GeneratedValue(generator="concepto_sec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="concepto_sec", sequenceName = "concepto_sec", allocationSize = 0 )
	private Integer idVencimiento;
	
	@Column(name="fecha_inicio")
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaInicio;
	
	@Column(name="fecha_fin")
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaFin;
	
	
	@Column(name="duracion")
	@Basic(optional=false)
	private Integer duracion;

	public Integer getIdVencimiento() {
		return idVencimiento;
	}

	public void setIdVencimiento(Integer idVencimiento) {
		this.idVencimiento = idVencimiento;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	
	
	

}
