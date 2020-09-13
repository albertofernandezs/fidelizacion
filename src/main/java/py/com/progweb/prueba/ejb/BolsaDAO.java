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
import py.com.progweb.prueba.model.Concepto;


@Stateless  
public class BolsaDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	
	@Inject
	ClienteDAO cl;
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregar(Bolsa b) {
		this.em.persist(b);
	}
	public void agregar_cliente(Bolsa b, Integer id) {
		Cliente c= cl.findById(id);
		b.setCliente(c);
		this.em.persist(b);
	}
	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Bolsa> lista(){
		Query q= this.em.createQuery("select b from Bolsa b");	
		return (List<Bolsa>) q.getResultList();
		
	}
	public void actualizar(Bolsa entidad) {
        this.em.merge(entidad);
 }
}
