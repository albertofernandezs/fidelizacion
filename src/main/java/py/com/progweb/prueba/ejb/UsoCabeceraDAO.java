package py.com.progweb.prueba.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import py.com.progweb.prueba.model.Bolsa;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.UsoCabecera;


@Stateless  
public class UsoCabeceraDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregar(UsoCabecera c) {
		this.em.persist(c);
	}
	
	public List<UsoCabecera> lista(){
		Query q= this.em.createQuery("select u from Cabecera u");	
		return (List<UsoCabecera>) q.getResultList();
		
	}

}
