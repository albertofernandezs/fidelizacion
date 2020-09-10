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
	
	
	public void agregar(Cliente entidad) {
		this.em.persist(entidad);
	
	}
	
	public void agregar_bolsa(Cliente entidad) {
		this.em.persist(entidad);
		for(Bolsa b: entidad.getListaBolsa()) {
			b.setCliente(entidad);
			bolsaDAO.agregar(b);
		}
	
	}
	
	public List<Cliente> lista(){
		Query q= this.em.createQuery("select c from Cliente c");	
		return (List<Cliente>) q.getResultList();
		
	}
	public Cliente findById(Integer id) {
        return em.find(Cliente.class, id);
        
    }
	
	public void eliminar(Cliente entidad) {
	        if (!em.contains(entidad)) {
	        	entidad = em.merge(entidad);
	        }

	         this.em.remove(entidad);
	}
	 public void actualizar(Cliente entidad) {
	        this.em.merge(entidad);
	 }
}
