package py.com.progweb.prueba.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import py.com.progweb.prueba.model.Vencimiento;

@Stateless
public class VencimientoDAO {
	@PersistenceContext(unitName = "fidelizacionPU")
	private EntityManager em;

	public void agregar(Vencimiento entidad) {
		this.em.persist(entidad);

	}

	public List<Vencimiento> lista() {
		Query q = this.em.createQuery("select c from Vencimiento c");
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

	public Integer duracion(String today) {
		int duracion = 0;
		try {
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			Date hoy = sdformat.parse(today);
			Query q = this.em.createQuery("SELECT v FROM Vencimiento v");
			List<Vencimiento> result = (List<Vencimiento>) q.getResultList();
			for (Vencimiento v : result) {
				Date inicio = v.getFechaInicio();
				Date fin = v.getFechaFin();
				if (hoy.after(inicio) && hoy.before(fin)) {
					duracion = v.getDuracion();
					break;
				}
			}
			// int duracion= q.getSingleResult();
			System.out.println("se ha encontrado en la base de datos: " + duracion);
		} catch (ParseException ex) {

		}
		return duracion;
	}
	
	public Date calcular_fecha(int dias) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dias); 
		Date result= calendar.getTime();
		return result;
	}

}
