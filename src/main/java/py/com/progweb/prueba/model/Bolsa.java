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
@Table(name="bolsa_punto")
public class Bolsa {

	@Id
	@Column(name="id_bolsa")
	@Basic(optional=false)
	@GeneratedValue(generator="bolsa_sec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="bolsa_sec", sequenceName = "bolsa_sec", allocationSize = 0 )
	private Integer idBolsa;
	
	@Column(name="fecha_asignacion")
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaAsignacion;
	
	@Column(name="fecha_caducidad")
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaCaducidad;
	
	@Column(name="puntaje_utilizado")
	private Float puntajeUtilizado;
	
	@Column(name="saldo_puntos")
	private Float saldo;
	
	@Column(name="monto_inicial")
	private Float montoInicial;
	
	@JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
	@ManyToOne(optional=false)
	private Cliente cliente;

	public Integer getIdBolsa() {
		return idBolsa;
	}

	public void setIdBolsa(Integer idBolsa) {
		this.idBolsa = idBolsa;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Float getPuntajeUtilizado() {
		return puntajeUtilizado;
	}

	public void setPuntajeUtilizado(Float puntajeUtilizado) {
		this.puntajeUtilizado = puntajeUtilizado;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public Float getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(Float montoInicial) {
		this.montoInicial = montoInicial;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
