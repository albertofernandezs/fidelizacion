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


import py.com.progweb.prueba.ejb.ConceptoDAO;
import py.com.progweb.prueba.model.Concepto;



@Path("concepto")
@Consumes("application/json")
@Produces("application/json")

public class ConceptoRest {
	
	@Inject
	private ConceptoDAO conceptoDAO;
	
	@GET
	@Path("/")
	public Response listar() throws Exception{
	
		return Response.ok(conceptoDAO.lista()).build();
	}
	
	@POST
	@Path("/")
	public Response crear(Concepto c){
		this.conceptoDAO.agregar(c);
		return Response.ok().build();
	}
	
	@GET
    @Path("/{id}")
    public Response getTodo(@PathParam("id") Integer id) {
        Concepto concepto_elegido = conceptoDAO.findById(id);
        
        return Response.ok(concepto_elegido).build();
    }
	
	 @DELETE
	 @Path("/{id}")
	 public Response eliminar(@PathParam("id") Integer id) {
	        Concepto concepto_elegido = conceptoDAO.findById(id);
	        
	        conceptoDAO.eliminar(concepto_elegido);

	        return Response.ok().build();
	 }
	 
	 @PUT
	 @Path("/{id}")
	 public Response update(@PathParam("id") Integer id, Concepto p) throws Exception {
		 if (!Objects.equals(id, p.getIdConcepto())) {
	            throw new Exception("Propiedad 'id de Objeto Locale debe coincidir con el parámetro mandado.");
	        }
	        conceptoDAO.actualizar(p);
	        return Response.ok().build();
	 }

}
