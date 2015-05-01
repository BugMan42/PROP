package Domini;


import java.util.ArrayList;

public class ControladorCjtEvento {

    private CjtEvento ce;

    public ControladorCjtEvento() {
        ce = new CjtEvento();
    }

    public void EliminarEvento(String nombre, String fecha) throws Exception{
        ce.EliminarEvento(nombre, fecha);
    }

    public void EliminarCjtEvento() {
        ce.EliminarCjtEvento();
    }

    public void AgregarVotacion(String nombre, String fecha, int importancia) throws Exception{
        Votacion v = new Votacion(nombre, fecha, importancia);
        ce.AgregarEvento(v);
    }

    public void AgregarReunionPersonal(String nombre, String fecha, int importancia) throws Exception{
        Personal per = new Personal(nombre, fecha, importancia);
        ce.AgregarEvento(per);
    }

    public void AgregarReunionProfesional(String nombre, String fecha, int importancia) throws Exception{
        Profesional pro = new Profesional(nombre, fecha, importancia);
        ce.AgregarEvento(pro);
    }

    public void AgregarActo(String nombre, String fecha, String subtipo, int importancia) throws Exception{
        Acto a = new Acto(nombre, fecha, subtipo, importancia);
        ce.AgregarEvento(a);
    }

    public ArrayList<Evento> ConsultarTodosEventos() {
        return ce.ConsultarTodosEventos();
    }

    public Evento ConsultarEvento(String nombre, String fecha) throws Exception{
        return ce.ConsultarEvento(nombre, fecha);
    }

    public boolean ExisteEvento(String nombre, String fecha) throws NoValido {
        return ce.ExisteEvento(nombre, fecha);
    }
}