package py.com.progweb.prueba.rest;

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


import py.com.progweb.prueba.ejb.VencimientoDAO;
import py.com.progweb.prueba.model.Vencimiento;



@Path("vencimiento")
@Consumes("application/json")
@Produces("application/json")

public class VencimientoRest {
	
	@Inject
	private VencimientoDAO vencimientoDAO;
	
	@GET
	@Path("/")
	public Response listar() throws Exception{
	
		return Response.ok(vencimientoDAO.lista()).build();
	}
	
	@POST
	@Path("/")
	public Response crear(Vencimiento c){
		this.vencimientoDAO.agregar(c);
		return Response.ok().build();
	}
	
	@GET
    @Path("/{id}")
    public Response getTodo(@PathParam("id") Integer id) {
        Vencimiento vencimiento_elegido = vencimientoDAO.findById(id);
        
        return Response.ok(vencimiento_elegido).build();
    }
	
	 @DELETE
	 @Path("/{id}")
	 public Response eliminar(@PathParam("id") Integer id) {
	        Vencimiento vencimiento_elegido = vencimientoDAO.findById(id);
	        
	        vencimientoDAO.eliminar(vencimiento_elegido);

	        return Response.ok().build();
	 }
	 
	 @PUT
	 @Path("/{id}")
	 public Response update(@PathParam("id") Integer id, Vencimiento p) {
	        Vencimiento vencimiento_elegido = vencimientoDAO.findById(id);
	        vencimientoDAO.actualizar(vencimiento_elegido);
	        return Response.ok().build();
	 }

}
