package py.com.progweb.prueba.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.BolsaDAO;
import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.ejb.ConceptoDAO;
import py.com.progweb.prueba.ejb.ReglaDAO;
import py.com.progweb.prueba.ejb.UsoCabeceraDAO;
import py.com.progweb.prueba.ejb.UsoDetalleDAO;
import py.com.progweb.prueba.ejb.VencimientoDAO;
import py.com.progweb.prueba.model.Bolsa;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.Concepto;
import py.com.progweb.prueba.model.UsoCabecera;
import py.com.progweb.prueba.model.UsoDetalle;

@Path("reportes")
@Consumes("application/json")
@Produces("application/json")
public class ResportesRest {
	@Inject
	private ClienteDAO clienteDAO;

	@Inject
	private ReglaDAO reglaDAO;

	@Inject
	private VencimientoDAO vencimientoDAO;

	@Inject
	private BolsaDAO bolsaDAO;

	@Inject
	private ConceptoDAO conceptoDAO;

	@Inject
	private UsoCabeceraDAO cabeceraDAO;

	@Inject
	private UsoDetalleDAO detalleDAO;

	@GET
	@Path("/uso/concepto")
	public Response uso_concepto(@QueryParam("id_concepto") Integer id_concepto) {
		Concepto concepto = this.conceptoDAO.findById(id_concepto);
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		for (UsoCabecera c : concepto.getListaCabecera()) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_cabecera", c.getIdCabecera());
			resultado.put("id_cliente", c.getCliente().getIdCliente());
			resultado.put("id_cabecera", c.getIdCabecera());
			resultado.put("puntaje_utilizado", c.getPuntajeUtilizado());
			List<Map<String, Object>> detalles= new ArrayList<Map<String, Object>>();
			for (UsoDetalle d : c.getListaDetalle()) {
				Map<String, Object> detalle = new LinkedHashMap<String, Object>();
				detalle.put("id_detalle", d.getIdDetalle());
				detalle.put("puntaje_utilizado", d.getPuntajeUtilizado());
				detalle.put("id_bolsa", d.getBolsa().getIdBolsa());
			}
			resultado.put("lista_detalle", detalles);
			lista.add(resultado);
		}
		return Response.ok(lista).build();
	}

	@GET
	@Path("/uso/fecha")
	public Response uso_fecha(@QueryParam("fecha") String fecha) {
		List<UsoCabecera> cabeceras = this.cabeceraDAO.lista_fecha(fecha);
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		if (cabeceras.size() > 0) {
			for (UsoCabecera c : cabeceras) {
				Map<String, Object> resultado = new LinkedHashMap<String, Object>();
				resultado.put("id_cabecera", c.getIdCabecera());
				resultado.put("id_cliente", c.getCliente().getIdCliente());
				resultado.put("id_cabecera", c.getIdCabecera());
				resultado.put("puntaje_utilizado", c.getPuntajeUtilizado());
				List<Map<String, Object>> detalles= new ArrayList<Map<String, Object>>();
				for (UsoDetalle d : c.getListaDetalle()) {
					Map<String, Object> detalle = new LinkedHashMap<String, Object>();
					detalle.put("id_detalle", d.getIdDetalle());
					detalle.put("puntaje_utilizado", d.getPuntajeUtilizado());
					detalle.put("id_bolsa", d.getBolsa().getIdBolsa());
					detalles.add(detalle);
				}
				resultado.put("lista_detalle", detalles);
				lista.add(resultado);
				
			}
			return Response.ok(lista).build();
		}else {
			return Response.status(400).build();
		}
	}
	@GET
	@Path("/uso/cliente")
	public Response uso_cliente(@QueryParam("id") Integer id) {
		Cliente cliente= this.clienteDAO.findById(id);
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		for(UsoCabecera c: cliente.getListaCabecera()) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_cabecera", c.getIdCabecera());
			resultado.put("id_cliente", c.getCliente().getIdCliente());
			resultado.put("id_cabecera", c.getIdCabecera());
			resultado.put("puntaje_utilizado", c.getPuntajeUtilizado());
			List<Map<String, Object>> detalles= new ArrayList<Map<String, Object>>();
			for (UsoDetalle d : c.getListaDetalle()) {
				Map<String, Object> detalle = new LinkedHashMap<String, Object>();
				detalle.put("id_detalle", d.getIdDetalle());
				detalle.put("puntaje_utilizado", d.getPuntajeUtilizado());
				detalle.put("id_bolsa", d.getBolsa().getIdBolsa());
				detalles.add(detalle);
			}
			resultado.put("lista_detalle", detalles);
			lista.add(resultado);	
		}
		return Response.ok(lista).build();
	}
	@GET
	@Path("/bolsa/cliente")
	public Response bolsa_cliente(@QueryParam("id") Integer id) {
		Cliente cliente= this.clienteDAO.findById(id);
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(Bolsa c: cliente.getListaBolsa()) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_bolsa", c.getIdBolsa());
			resultado.put("fecha_asignacion", dateFormat.format(c.getFechaAsignacion()));
			resultado.put("fecha_caducidad", dateFormat.format(c.getFechaCaducidad()));
			resultado.put("puntaje_utilizado", c.getPuntajeUtilizado());
			resultado.put("saldo", c.getSaldo());
			resultado.put("monto_inicial", c.getMontoInicial());
			lista.add(resultado);
		}
		return Response.ok(lista).build();
	}
	@GET
	@Path("/bolsa/rango")
	public Response bolsa_rango(@QueryParam("inicial") float inicial, @QueryParam("final") float fin) {
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(Bolsa c: this.bolsaDAO.lista_rango(inicial, fin)) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_bolsa", c.getIdBolsa());
			resultado.put("fecha_asignacion", dateFormat.format(c.getFechaAsignacion()));
			resultado.put("fecha_caducidad", dateFormat.format(c.getFechaCaducidad()));
			resultado.put("puntaje_utilizado", c.getPuntajeUtilizado());
			resultado.put("saldo", c.getSaldo());
			resultado.put("monto_inicial", c.getMontoInicial());
			lista.add(resultado);
		}
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/bolsa/dias")
	public Response bolsa_dias(@QueryParam("dias") Integer dias) {
		this.bolsaDAO.actualizar();
		Date hoy= new Date();
		Date vence= this.vencimientoDAO.calcular_fecha(dias);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		for(Bolsa c: this.bolsaDAO.fecha_vence(hoy, vence)) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("cliente", c.getCliente().getIdCliente());
			resultado.put("id_bolsa", c.getIdBolsa());
			resultado.put("fecha_asignacion", dateFormat.format(c.getFechaAsignacion()));
			resultado.put("fecha_caducidad", dateFormat.format(c.getFechaCaducidad()));
			resultado.put("monto_inicial", c.getMontoInicial());
			resultado.put("puntaje_utilizado", c.getPuntajeUtilizado());
			lista.add(resultado);
		}
		
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/cliente/nombre")
	public Response cliente_nombre(@QueryParam("nombre") String nombre) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		for(Cliente c: this.clienteDAO.lista_nombre(nombre)) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_cliente", c.getIdCliente());
			resultado.put("nombre", c.getNombre());
			resultado.put("apellido", c.getApellido());
			resultado.put("ci", c.getCi());
			resultado.put("email", c.getEmail());
			resultado.put("telefono", c.getTelefono());
			resultado.put("tipoDocumento", c.getTipoDocumento());
			resultado.put("fechaNacimiento", dateFormat.format(c.getFechaNacimiento()));
			resultado.put("nacionalidad", c.getNacionalidad());
			lista.add(resultado);
		}
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/cliente/apellido")
	public Response cliente_apellido(@QueryParam("apellido") String apellido) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		for(Cliente c: this.clienteDAO.lista_apellido(apellido)) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_cliente", c.getIdCliente());
			resultado.put("nombre", c.getNombre());
			resultado.put("apellido", c.getApellido());
			resultado.put("ci", c.getCi());
			resultado.put("email", c.getEmail());
			resultado.put("telefono", c.getTelefono());
			resultado.put("tipoDocumento", c.getTipoDocumento());
			resultado.put("fechaNacimiento", dateFormat.format(c.getFechaNacimiento()));
			resultado.put("nacionalidad", c.getNacionalidad());
			lista.add(resultado);
		}
		return Response.ok(lista).build();
	}
	
	@GET
	@Path("/cliente/cumple")
	public Response cliente_cumple(@QueryParam("fecha") String fecha) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> lista= new ArrayList<Map<String, Object>>();
		for(Cliente c: this.clienteDAO.lista_cumple(fecha)) {
			Map<String, Object> resultado = new LinkedHashMap<String, Object>();
			resultado.put("id_cliente", c.getIdCliente());
			resultado.put("nombre", c.getNombre());
			resultado.put("apellido", c.getApellido());
			resultado.put("ci", c.getCi());
			resultado.put("email", c.getEmail());
			resultado.put("telefono", c.getTelefono());
			resultado.put("tipoDocumento", c.getTipoDocumento());
			resultado.put("fechaNacimiento", dateFormat.format(c.getFechaNacimiento()));
			resultado.put("nacionalidad", c.getNacionalidad());
			lista.add(resultado);
		}
		return Response.ok(lista).build();
	}
	
	
	
	
	
	

}
