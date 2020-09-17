package py.com.progweb.prueba.rest;

import java.util.Objects;

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


import py.com.progweb.prueba.ejb.ReglaDAO;
import py.com.progweb.prueba.model.Reglas;



@Path("regla")
@Consumes("application/json")
@Produces("application/json")
public class ReglaRest {
	@Inject
	private ReglaDAO reglaDAO;
	
	@GET
	@Path("/")
	public Response listar() throws Exception{
	
		return Response.ok(reglaDAO.lista()).build();
	}
	
	@POST
	@Path("/")
	public Response crear(Reglas c){
		this.reglaDAO.agregar(c);
		return Response.ok().build();
	}
	
	@GET
    @Path("/{id}")
    public Response getTodo(@PathParam("id") Integer id) {
        Reglas regla_elegido = reglaDAO.findById(id);
        
        return Response.ok(regla_elegido).build();
    }
	
	 @DELETE
	 @Path("/{id}")
	 public Response eliminar(@PathParam("id") Integer id) {
	        Reglas regla_elegido = reglaDAO.findById(id);
	        
	        reglaDAO.eliminar(regla_elegido);

	        return Response.ok().build();
	 }
	 
	 @PUT
	 @Path("/{id}")
	 public Response update(@PathParam("id") Integer id, Reglas p) throws Exception {
		 if (!Objects.equals(id, p.getIdRegla())) {
	            throw new Exception("Propiedad 'id de Objeto Locale debe coincidir con el parámetro mandado.");
	        }
	        reglaDAO.actualizar(p);
	        return Response.ok().build();
	 }


}
