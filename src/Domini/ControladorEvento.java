package Domini;


import java.util.ArrayList;
import java.util.Date;
import Domini.Evento;

public class ControladorEvento {
    private ArrayList<Evento> Listado;

    public ControladorEvento() {
        Listado = new ArrayList<Evento>();
    }
    public ArrayList<Evento> ConsultarTodosEventos() {
        return Listado;
    }
    public void AgregarEvento(Evento e) {
        Listado.add(e);
    }

    public void eliminar_relacion(Evento e) {
        Listado.remove(e);
    }
    public void AgregarEventoRandom() {}

    public Evento ConsultarEvento(String nombre, Date fecha) {
        int n = Listado.size();
        boolean found = false;
        int i = 0;
        while (!found && i < n) {
            if (Listado.get(i).igual(nombre, fecha)) found = true;
            else ++i;
        }
        return Listado.get(i);
        ////////////////Excepcion//////////////////////
    }

    /*: Evento
    ConsultarEventosAsociadosCongresista(nombre : string)
    ModificarEvento(nom : string, fecha : DateTime)
    EliminarCjtEventos()  */

}