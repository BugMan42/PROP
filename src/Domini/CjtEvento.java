package Domini;

import java.util.List;
import java.util.Random;

public class CjtEvento {
    private TST<Evento> cjt;
    private TST<Evento> cjtFecha;
    private TST<Evento> cjtImp;

    private static final String error1 = "El Nombre no es valido";
    private static final String error2 = "La fecha no es valida";
    private static final String error3 = "El evento no es valido";
    private static final String error4 = "El nuevo nombre no es valido";
    private static final String error5 = "La Fecha nueva no es valida";
    private static final String error6 = "El nombre Nombre no valido";

    //////////////////////////////////////CREADORA////////////////////////

    public CjtEvento() {
        cjt = new TST<Evento>();
        /*cjtFecha = new TST<Evento>();
        cjtImp = new TST<Evento>();*/
    }

    /////////////////////////////////////ELIMINADORA/////////////////////
    public void EliminarCjtEvento() {
        cjt.vaciar();
        /*cjtFecha.vaciar();
        cjtImp.vaciar();*/
    }

    /////////////////////////////////////MODIFICADORAS////////////////////////

    public void EliminarEvento(String nombre, Fecha fecha) throws Exception{
        //Siempre convierto el nombre a mayusculas para evitar errores de no
        // encontrar el elemento
        String name = nombre.toUpperCase();
        cjt.borrar(name + fecha.toString());
        /*cjtFecha.borrar(fecha.toString() + name);
        //Ineficiente hacer de nuevo//////////////////////////////
        //Evento aux = cjt.obtener(name + fecha.toString());
        cjtImp.borrar(Integer.toString(importancia) + name + fecha.toString());*/
        ///////////////////////////////////////////////////
    }

    public void AgregarEvento(Evento e) throws Exception{
        cjt.insertar(e.ID(), e);
        /*cjtFecha.insertar(e.IDFecha(), e);
        cjtImp.insertar(e.IDImp(), e);*/
    }

    public void ModificarNombreEvento(String nomViejo, Fecha fecha, String nomNuevo) throws Exception {
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
            String oldname = nomViejo.toUpperCase();
            String newname = nomNuevo.toUpperCase();
            if (!nomNuevo.equals(nomViejo)){
                //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el
                // conjunto de acuerdo a su nueva clave
                Evento aux = cjt.obtener(oldname + fecha.toString());
                aux.ModNombre(newname);
                cjt.modificar(oldname + fecha.toString(), newname + fecha.toString(), aux);
                /*aux = cjtFecha.obtener(fecha.toString() + oldname);
                aux.ModNombre(newname);
                cjtFecha.modificar(fecha.toString() + oldname, fecha.toString() + newname, aux);
                aux = cjtImp.obtener(Integer.toString(importancia)+oldname+fecha.toString());
                aux.ModNombre(newname);
                cjtImp.modificar(Integer.toString(importancia) + oldname + fecha.toString(), Integer.toString(importancia) + newname + fecha.toString(), aux);*/
            }
        //Si es igual nomViejo a nomNuevo no se hace nada no se hace nada
    }

    public void ModificarFechaEvento(String nombre, Fecha fechaVieja, Fecha fechaNueva) throws Exception {
        if (!fechaVieja.equals(fechaNueva)) {
            //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
            String name = nombre.toUpperCase();
            //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el
            // conjunto de acuerdo a su nueva clave
            Evento aux = cjt.obtener(name + fechaVieja.toString());
            aux.ModFecha(fechaNueva);
            cjt.modificar(name + fechaVieja.toString(), name + fechaNueva.toString(), aux);
            /*aux = cjtFecha.obtener(fechaVieja.toString() + name);
            aux.ModFecha(fechaNueva);
            cjt.modificar(fechaVieja.toString() + name,fechaNueva.toString()+ name, aux);
            aux = cjtImp.obtener(Integer.toString(importancia)+ name + fechaVieja.toString());
            aux.ModFecha(fechaNueva);
            cjtImp.modificar(Integer.toString(importancia)+ name+fechaVieja.toString(),Integer.toString(importancia)+name+fechaNueva.toString(), aux);*/
        }
        //Si es igual fechaVieja a fechaNueva no se hace nada
    }

    public void ModificarImpEvento(String nombre, Fecha fecha, int NewImp) throws Exception {
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        String name = nombre.toUpperCase();
        //Como el tst devuelve la refencia al objeto directamente puedo cambiarle los atributos
        cjt.obtener(name+fecha.toString()).ModImportancia(NewImp);
        /*cjtFecha.obtener(fecha.toString() + name).ModImportancia(NewImp);
        Evento aux = cjtImp.obtener(Integer.toString(OldImp) + name + fecha.toString());
        aux.ModImportancia(NewImp);
        cjtImp.modificar(Integer.toString(OldImp)+name+fecha.toString(),Integer.toString(NewImp)+name+fecha.toString(), aux);*/
    }

    public void AgregarEventoRandom(int n) throws Exception {
        //Creo un nombre random concatenando letras de
        // la A-Z. 90 y 65 corresponde al codigo
        // ascii de la Z y la A respectivamente
        for (int contador = 0; contador < n;++contador) {
            String nombre;
            Fecha f;
            nombre = "";
            Random r = new Random();
            do {
                f = new Fecha(r);
                for (int i = 0; i < 8; ++i) {
                    int valorEntero = r.nextInt(90 - 65 + 1) + 65;
                    char c = (char) valorEntero;
                    nombre = nombre + c;
                }
            }
            while (cjt.existe(nombre + f.toString()));
            //Uso un numero random [1,10] para importancia
            int importancia = r.nextInt(10) + 1;
            //Uso un numero random [1,5] para seleccionar el tipo de evento
            int tipo = r.nextInt(5) + 1;
            Evento e;
            switch (tipo) {
                case 1:
                    e = new Votacion(nombre, f, importancia);
                    insertar(e);
                    break;
                case 2:
                    e = new Personal(nombre, f, importancia);
                    insertar(e);
                    break;
                case 3:
                    e = new Profesional(nombre, f, importancia);
                    insertar(e);
                    break;
                case 4:
                    e = new Oficial(nombre, f, importancia);
                    insertar(e);
                    break;
                case 5:
                    e = new NoOficial(nombre, f, importancia);
                    insertar(e);
                    break;
                default:
            }
        }
    }

    private void insertar(Evento e) throws Exception {
        cjt.insertar(e.ID(), e);
        /*cjtFecha.insertar(e.IDFecha(), e);
        cjtImp.insertar(e.IDImp(), e);*/
    }

    //Consultoras

    public List<Evento> ConsultarTodosEventos() {
        return cjt.consultarObjetos();
    }

    public List<Evento> ConsultarTodosEventosFecha() {
        return cjtFecha.consultarObjetos();
    }

    public List<Evento> ConsultarTodosEventosImp() {
        return cjtImp.consultarObjetos();
    }

    public Evento ConsultarEvento(String nombre, Fecha fecha) throws Exception {
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        String name = nombre.toUpperCase();
        return cjt.obtener(name+fecha.toString());
    }

    public boolean ExisteEvento(String nombre, Fecha fecha) throws Exception {
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        String name = nombre.toUpperCase();
        return cjt.existe(name+fecha.toString());
    }

    public int size() {
        return cjt.size();
    }
}
