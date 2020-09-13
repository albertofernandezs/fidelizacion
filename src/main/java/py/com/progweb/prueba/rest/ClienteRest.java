package py.com.progweb.prueba.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Bolsa;
import py.com.progweb.prueba.model.Cliente;

@Path("cliente")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@GET
	@Path("/")
	public Response listar() throws Exception{
		ArrayList resultado=new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Cliente c: clienteDAO.lista() ) {
			ArrayList listaBolsa=new ArrayList();
			Map<String, Object> cliente = new LinkedHashMap<String, Object>();
			cliente.put("id", c.getIdCliente());
			cliente.put("nombre", c.getNombre());
			cliente.put("apellido", c.getApellido());
			cliente.put("ci", c.getCi());
			cliente.put("email", c.getEmail());
			cliente.put("telefono", c.getTelefono());
			cliente.put("tipoDocumento", c.getTipoDocumento());
			cliente.put("fechaNacimiento", c.getFechaNacimiento());
			cliente.put("nacionalidad", c.getNacionalidad());
			for(Bolsa b: c.getListaBolsa()) {
				Map<String, Object> bolsa = new LinkedHashMap<String, Object>();
				bolsa.put("idBolsa",b.getIdBolsa());
				bolsa.put("idCliente",c.getIdCliente());
				bolsa.put("fechaAsignacion", dateFormat.format(b.getFechaAsignacion()));
				bolsa.put("fechaCaducidad", dateFormat.format(b.getFechaCaducidad()));
				bolsa.put("puntajeUtilizado", b.getPuntajeUtilizado());
				bolsa.put("saldo", b.getSaldo());
				bolsa.put("montoInicial", b.getMontoInicial());
				listaBolsa.add(bolsa);
			}
			cliente.put("listaBolsa", listaBolsa);
			resultado.add(cliente);
			
		}
		return Response.ok(resultado).build();
	}
	
	@POST
	@Path("/")
	public Response crear(Cliente c){
		this.clienteDAO.agregar(c);
		return Response.ok().build();
	}
	
	@GET
    @Path("/{id}")
    public Response getTodo(@PathParam("id") Integer id) {
        Cliente cliente_elegido = clienteDAO.findById(id);
        System.out.println("se ha encontrado: "+cliente_elegido.getNombre());
        
        return Response.ok(cliente_elegido).build();
    }
	
	 @DELETE
	 @Path("/{id}")
	 public Response eliminar(@PathParam("id") Integer id) {
	        Cliente cliente_elegido = clienteDAO.findById(id);
	        
	        clienteDAO.eliminar(cliente_elegido);

	        return Response.ok().build();
	 }
	 
	 @PUT
	 @Path("/{id}")
	 public Response update(@PathParam("id") Integer id, Cliente p) {
	        Cliente cliente_elegido = clienteDAO.findById(id);

	        cliente_elegido.setNombre(p.getNombre());
	        cliente_elegido.setApellido(p.getApellido());
	        clienteDAO.actualizar(cliente_elegido);
	        return Response.ok().build();
	 }

}
