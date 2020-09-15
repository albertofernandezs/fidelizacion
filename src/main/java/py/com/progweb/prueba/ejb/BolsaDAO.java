package py.com.progweb.prueba.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	@Inject
	VencimientoDAO vencimientoDAO;
	
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
	
	public List<Bolsa> lista_rango(float inicial, float fin){
		Query q= this.em.createQuery("select b from Bolsa b where b.saldo between "+inicial+" and "+fin);
		return (List<Bolsa>)q.getResultList();
	}
	
	public int actualizar() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date hoy= new Date(); 
		Query q= this.em.createQuery("select b from Bolsa b where b.fechaCaducidad<= '"+dateFormat.format(hoy)+"'");
		for(Bolsa b: (List<Bolsa>)q.getResultList()) {
			b.setSaldo(0.0f);
			this.em.merge(b);
		}
		return q.getResultList().size();
	}
	
	public List<Bolsa> fecha_vence(Date hoy, Date fin){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Query q= this.em.createQuery("select b from Bolsa b where b.fechaCaducidad between '"+dateFormat.format(hoy)+"' and '"+dateFormat.format(fin)+"'");
		return (List<Bolsa>) q.getResultList();

	}
}
