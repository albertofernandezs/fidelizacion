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

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;

import py.com.progweb.prueba.model.Bolsa;
import py.com.progweb.prueba.model.UsoCabecera;
@Entity
@Table(name="cliente")

public class Cliente {
	
	@Id
	@Column(name="id_cliente")
	@Basic(optional=false)
	@GeneratedValue(generator="cliente_sec", strategy =  GenerationType.SEQUENCE)
	@SequenceGenerator(name="cliente_sec", sequenceName = "cliente_sec", allocationSize = 0 )
	private Integer idCliente;
	
	@Column(name="nombre", length = 50)
	@Basic(optional=false)
	private String nombre;
	
	@Column(name="apellido", length = 50)
	@Basic(optional=false)
	private String apellido;
	
	@Column(name="email", length = 50)
	private String email;
	
	@Column(name="tipo_documento", length = 50)
	private String tipoDocumento;
	
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	@Column(name="telefono", length = 50)
	
	private String telefono;
	@Column(name="nacionalidad", length = 50)
	private String nacionalidad;
	
	@Column(name="ci", length = 50,  nullable = true)
	@Basic(optional=false)
	private Integer ci;
	
	@Column(name="total_puntos")
	private Float totalPuntos ;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "cliente")
	//@Fetch(value = FetchMode.SUBSELECT)
	private List<Bolsa> listaBolsa;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "cliente")
	//@Fetch(value = FetchMode.SUBSELECT)
	private List<UsoCabecera> listaCabecera;
	
	public Cliente() {
		
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public List<Bolsa> getListaBolsa() {
		return listaBolsa;
	}

	public void setListaBolsa(List<Bolsa> listaBolsa) {
		this.listaBolsa = listaBolsa;
	}

	public List<UsoCabecera> getListaCabecera() {
		return listaCabecera;
	}

	public void setListaCabecera(List<UsoCabecera> listaCabecera) {
		this.listaCabecera = listaCabecera;
	}

	public Float getTotalPuntos() {
		return totalPuntos;
	}

	public void setTotalPuntos(Float totalPuntos) {
		this.totalPuntos = totalPuntos;
	}
	
	

	
	
	
	
	

}
