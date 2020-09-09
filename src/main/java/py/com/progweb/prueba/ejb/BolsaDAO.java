package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
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


@Stateless  
public class BolsaDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregar(Bolsa b) {
		System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  "+b.getCliente().getIdCliente());
		this.em.persist(b);
	}
	
	public List<Bolsa> lista(){
		Query q= this.em.createQuery("select b from Bolsa b");	
		return (List<Bolsa>) q.getResultList();
		
	}
}
