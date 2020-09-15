package py.com.progweb.prueba.timer;
import java.text.ParseException;
import java.util.Date;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Timer;
import javax.inject.Inject;

import py.com.progweb.prueba.ejb.BolsaDAO;

@Singleton
@LocalBean
public class TimerEJB {

	@Inject
	private BolsaDAO b;
	//proceso se ejecuta las 00:00:00 hs
	//Ejemplos: (second="5", minute="*", hour="*") -- cada vez que sea "5 seg" de cualquier minuto y hora
    @Schedule(second="0", minute="0", hour="0")
    public void execute(Timer timer) throws ParseException {
        System.out.println("Ejecutando actualizacion de asistencia de reservas ...");
        System.out.println("Cantidad reservas actualizadas: " + this.b.actualizar());
        System.out.println("Fecha y Hora de ejecucion : " + new Date());
        System.out.println("____________________________________________");   
    }
	
}
