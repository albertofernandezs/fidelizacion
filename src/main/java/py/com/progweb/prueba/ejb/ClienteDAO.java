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
public class ClienteDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	@Inject
	BolsaDAO bolsaDAO;
	
	
	public void agregar(Cliente c) {
		this.em.persist(c);
		for(Bolsa b: c.getListaBolsa()) {
			b.setCliente(c);
			bolsaDAO.agregar(b);
		}
	
	}
	
	public List<Cliente> lista(){
		Query q= this.em.createQuery("select c from Cliente c");	
		return (List<Cliente>) q.getResultList();
		
	}
}
