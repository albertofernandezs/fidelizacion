package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import py.com.progweb.prueba.model.UsoDetalle;


@Stateless  
public class UsoDetalleDAO {
	
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregar(UsoDetalle entidad) {
		this.em.persist(entidad);
	}
	
	public List<UsoDetalle> lista(){
		Query q= this.em.createQuery("select d from UsoDetalle d");	
		return (List<UsoDetalle>) q.getResultList();
		
	}

}
