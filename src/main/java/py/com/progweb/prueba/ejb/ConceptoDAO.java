package py.com.progweb.prueba.ejb;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Concepto;
import py.com.progweb.prueba.model.UsoCabecera;

@Stateless
public class ConceptoDAO {
	
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	public void agregar(Concepto entidad) {
		List<UsoCabecera> listaCabecera= new ArrayList <UsoCabecera>();
		entidad.setListaCabecera(listaCabecera);
		this.em.persist(entidad);
	
	}
	
	public List<Concepto> lista(){
		Query q= this.em.createQuery("select c from Concepto c");	
		return (List<Concepto>) q.getResultList();
		
	}
	public Concepto findById(Integer id) {
        return em.find(Concepto.class, id);
        
    }
	
	public void eliminar(Concepto entidad) {
	        if (!em.contains(entidad)) {
	        	entidad = em.merge(entidad);
	        }

	         this.em.remove(entidad);
	}
	 public void actualizar(Concepto entidad) {
	        this.em.merge(entidad);
	 }

}
