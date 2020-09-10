package py.com.progweb.prueba.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Vencimiento;

@Stateless
public class VencimientoDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	public void agregar(Vencimiento entidad) {
		this.em.persist(entidad);
	
	}
	
	public List<Vencimiento> lista(){
		Query q= this.em.createQuery("select c from Vencimiento c");	
		return (List<Vencimiento>) q.getResultList();
		
	}
	public Vencimiento findById(Integer id) {
        return em.find(Vencimiento.class, id);
        
    }
	
	public void eliminar(Vencimiento entidad) {
	        if (!em.contains(entidad)) {
	        	entidad = em.merge(entidad);
	        }

	         this.em.remove(entidad);
	}
	 public void actualizar(Vencimiento entidad) {
	        this.em.merge(entidad);
	 }

}
