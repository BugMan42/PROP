package Domini;


import Persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControladorCjtEvento {

    static final int max_lineas_guardar = 300;
    static final int max_lineas_cargar = 300;

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

    public List<Evento> ConsultarTodosEventos() {
        return ce.ConsultarTodosEventos();
    }

    public Evento ConsultarEvento(String nombre, String fecha) throws Exception{
        return ce.ConsultarEvento(nombre, fecha);
    }

    public boolean ExisteEvento(String nombre, String fecha) throws NoValido {
        return ce.ExisteEvento(nombre, fecha);
    }

    /*
    public void guardar(String ruta) throws Exception {
        if (!ce.) {
            ControladorPersistencia cp = new ControladorPersistencia(ruta);
            ArrayList<Evento> es = ce.ConsultarTodosEventos();
            Iterator<Evento> it = es.iterator();
            cp.abrirEscritura();
            while (it.hasNext()){
                String datos = "";
                int j = 0;
                while (j < max_lineas_guardar && it.hasNext()){
                    datos += it.next().toString()+"\n";
                    ++j;
                }
                cp.escribir(datos);
            }
            cp.cerrarFichero();
        }
    }

    public void cargar(String ruta) throws Exception {
        ControladorPersistencia cp = new ControladorPersistencia(ruta);
        cp.abrirLectura();
        c.eliminarCongreso();
        String r = cp.leer(max_lineas_cargar);
        while (r != ""){
            String[] aux = r.split("\n");
            for(String con : aux){
                String[] prm = con.split("\\s");
                Dni d = new Dni(prm[0]);
                c.agregarCongresista(d, prm[1], prm[2], Integer.parseInt(prm[3]), prm[4], prm[5], prm[6]);
            }
            r = cp.leer(max_lineas_cargar);
        }
        cp.cerrarFichero();
    }
    */
}