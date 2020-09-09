package py.com.progweb.prueba.rest;

import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import py.com.progweb.prueba.ejb.BolsaDAO;
import py.com.progweb.prueba.ejb.ClienteDAO;

import py.com.progweb.prueba.model.Bolsa;
import py.com.progweb.prueba.model.Cliente;

@Path("bolsa")
@Consumes("application/json")
@Produces("application/json")
public class BolsaRest {
	
	@Inject
	private BolsaDAO bolsaDAO;
	
	@GET()
	@Path("/")
	public Response listar() {
		
		ArrayList m=new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(Bolsa a: bolsaDAO.lista() ) {
			Map<String, Object> result = new LinkedHashMap<String, Object>();
			result.put("idBolsa",a.getIdBolsa());
			result.put("fecha_asignacion",dateFormat.format(a.getFechaAsignacion()) );
			result.put("fecha_asignacion",dateFormat.format(a.getFechaCaducidad()) );
			result.put("puntaje_utilizado",a.getPuntajeUtilizado());
			result.put("saldo",a.getSaldo());
			result.put("idBolsa",a.getMontoInicial());
			result.put("cliente",a.getCliente().getIdCliente() );
			m.add(result);
	        
		}
		
		return Response.ok(m).build();
	}
	
	@POST
	@Path("/")
	public Response crear(Bolsa b){
		this.bolsaDAO.agregar(b);
		return Response.ok().build();
	}

}
