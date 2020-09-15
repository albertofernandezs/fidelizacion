package py.com.progweb.prueba.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import py.com.progweb.prueba.model.UsoDetalle;

@Stateless
public class ClienteDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;
	@Inject
	BolsaDAO bolsaDAO;

	public void agregar(Cliente entidad) {
		entidad.setTotalPuntos(0.0f);
		this.em.persist(entidad);

	}

	public void agregar_bolsa(Cliente entidad) {
		this.em.persist(entidad);
		for (Bolsa b : entidad.getListaBolsa()) {
			b.setCliente(entidad);
			bolsaDAO.agregar(b);
		}

	}

	// @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Cliente> lista() {
		Query q = this.em.createQuery("select c from Cliente c");
		return (List<Cliente>) q.getResultList();

	}

	// @TransactionAttribute(TransactionAttributeType.REQUIRED)
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

	public List<Cliente> lista_nombre(String nombre) {
		Query q = this.em.createQuery("select c from Cliente c where c.nombre = '" + nombre + "'");
		return (List<Cliente>) q.getResultList();
	}

	public List<Cliente> lista_apellido(String apellido) {
		Query q = this.em.createQuery("select c from Cliente c where c.apellido = '" + apellido + "'");
		return (List<Cliente>) q.getResultList();
	}

	public List<Cliente> lista_cumple(String fecha) {
		Calendar calendar = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		fecha = calendar.get(Calendar.YEAR) + "-" + fecha;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			Date date = dateFormat.parse(fecha);
			calendar.setTime(date);
			Query q = this.em.createQuery("select c from Cliente c ");
			for(Cliente c:(List<Cliente>) q.getResultList()) {
				cal.setTime(c.getFechaNacimiento());
				if(cal.get(Calendar.DAY_OF_MONTH)== calendar.get(Calendar.DAY_OF_MONTH) && cal.get(Calendar.MONTH)==calendar.get(Calendar.MONTH)) {
					lista.add(c);
				}
			}
			return lista;
		} catch (ParseException ex) {
			return lista;
		}
	}
}
