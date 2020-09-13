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

@Path("servicios")
@Consumes("application/json")
@Produces("application/json")
public class ServiciosRest {

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
	
	
	

	@POST
	@Path("/equivalencia")
	public Response equivalencia(@QueryParam("monto") float monto) {
		float r = reglaDAO.monto(monto);
		if (r > 0) {
			System.out.println("la equivalencia del monto " + monto + " es: " + monto / r);
			return Response.ok(monto / r).build();
		} else {
			System.out.println("El monto dado no se encuentra en el rango");
			return Response.status(400).build();
		}
	}

	@POST
	@Path("/carga")
	public Response carga(@QueryParam("id") Integer id, @QueryParam("monto") float monto) {
		float puntos = monto / this.reglaDAO.monto(monto);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha_hoy = new Date();
		Date fecha_fin = this.vencimientoDAO.calcular_fecha(this.vencimientoDAO.duracion(dateFormat.format(fecha_hoy)));
		Cliente cliente = this.clienteDAO.findById(id);
		Bolsa bolsa = new Bolsa(fecha_hoy, fecha_fin, puntos, puntos);
		List<Bolsa> bolsas = cliente.getListaBolsa();
		bolsas.add(bolsa);
		System.out.println("el cliente eeeeeeeeeeeeeeeeeee "+cliente.getIdCliente());
		cliente.setTotalPuntos(cliente.getTotalPuntos() + puntos);
		bolsa.setCliente(cliente);
		//this.clienteDAO.actualizar(cliente);
		System.out.println("el cliente eeeeeeeeeeeeeeeeeee bolsa"+bolsa.getCliente().getIdCliente());
		this.bolsaDAO.agregar(bolsa);
		System.out.println("El cliente tiene actualmente: " + cliente.getTotalPuntos() + " puntos");
		this.clienteDAO.actualizar(cliente);
		return Response.ok(bolsa).build();
	}

	@POST
	@Path("/uso")
	public Response uso(@QueryParam("id_cliente") Integer id_cliente, @QueryParam("id_concepto") Integer id_concepto) {
		Cliente cliente = this.clienteDAO.findById(id_cliente);
		Concepto concepto = this.conceptoDAO.findById(id_concepto);
		float puntos_requeridos = concepto.getPuntos();
		Date hoy = new Date();
		if (cliente.getTotalPuntos() >= puntos_requeridos) {
			UsoCabecera cabecera = new UsoCabecera(hoy, puntos_requeridos, cliente, concepto);
			List<UsoCabecera> listaCabecera= concepto.getListaCabecera();
			concepto.setListaCabecera(listaCabecera);
			this.conceptoDAO.actualizar(concepto);
			this.cabeceraDAO.agregar(cabecera);
			for (Bolsa b : cliente.getListaBolsa()) {
				if (b.getSaldo() > 0) {
					if (b.getSaldo() <= puntos_requeridos) {
						cliente.setTotalPuntos(cliente.getTotalPuntos() - b.getSaldo());
						puntos_requeridos = puntos_requeridos - b.getSaldo();
						
						b.setPuntajeUtilizado(b.getPuntajeUtilizado() + b.getSaldo());
						b.setSaldo(0.0f);
						UsoDetalle detalle = new UsoDetalle(b.getPuntajeUtilizado(), cabecera, b);
						this.detalleDAO.agregar(detalle);

						List<UsoDetalle> listaDetalleBolsa = b.getListaDetalle();
						listaDetalleBolsa.add(detalle);
						b.setListaDetalle(listaDetalleBolsa);

						List<UsoDetalle> listaDetalleCabecera = cabecera.getListaDetalle();
						listaDetalleCabecera.add(detalle);
						cabecera.setListaDetalle(listaDetalleCabecera);

						this.bolsaDAO.actualizar(b);
					} else {
						cliente.setTotalPuntos(cliente.getTotalPuntos() - puntos_requeridos);
						
						b.setSaldo(b.getSaldo() - puntos_requeridos);
						b.setPuntajeUtilizado(b.getPuntajeUtilizado() + puntos_requeridos);
						UsoDetalle detalle = new UsoDetalle(puntos_requeridos, cabecera, b);
						this.detalleDAO.agregar(detalle);

						List<UsoDetalle> listaDetalleBolsa = b.getListaDetalle();
						listaDetalleBolsa.add(detalle);
						b.setListaDetalle(listaDetalleBolsa);

						List<UsoDetalle> listaDetalleCabecera = cabecera.getListaDetalle();
						listaDetalleCabecera.add(detalle);
						cabecera.setListaDetalle(listaDetalleCabecera);

						this.bolsaDAO.actualizar(b);
						break;
					}
				}
			}
			//this.cabeceraDAO.actualizar(cabecera);

			List<UsoCabecera> listaCabecerac = cliente.getListaCabecera();
			listaCabecerac.add(cabecera);
			cliente.setListaCabecera(listaCabecerac);
			
			this.clienteDAO.actualizar(cliente);
			return Response.status(200).build();

		} else {
			return Response.status(400).build();
		}

	}
}
