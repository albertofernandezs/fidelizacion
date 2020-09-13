package py.com.progweb.prueba.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Reglas;

@Stateless
public class ReglaDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	public void agregar(Reglas entidad) {
		this.em.persist(entidad);
	
	}
	
	public List<Reglas> lista(){
		Query q= this.em.createQuery("select c from Reglas c");	
		return (List<Reglas>) q.getResultList();
		
	}
	public Reglas findById(Integer id) {
        return em.find(Reglas.class, id);
        
    }
	
	public void eliminar(Reglas entidad) {
	        if (!em.contains(entidad)) {
	        	entidad = em.merge(entidad);
	        }

	         this.em.remove(entidad);
	}
	 public void actualizar(Reglas entidad) {
	        this.em.merge(entidad);
	 }
	 
	 public float monto(float cantidad) {
		 float total=0;
		 Query q= this.em.createQuery("select r from Reglas r");
		 for(Reglas r: (List<Reglas>)q.getResultList()) {
			 if(r.getInferior()<= cantidad && r.getSuperior() >= cantidad) {
				 total=r.getMonto();
				 break;
			 }
		 }
		 return total;
		 
	 }

}
