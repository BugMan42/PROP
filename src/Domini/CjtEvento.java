package Domini;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CjtEvento {
    private TST<Evento> cjt;
    private TST<Evento> cjtFecha;
    private TST<Evento> cjtImp;
    private ArrayList<TSTIterator> Cache;
    private ArrayList<TSTIterator> CacheF;
    private ArrayList<TSTIterator> CacheI;

    //////////////////////////////////////CREADORA////////////////////////

    public CjtEvento() {
        cjt = new TST<Evento>();
        cjtFecha = new TST<Evento>();
        cjtImp = new TST<Evento>();
        restaurar();
    }

    /////////////////////////////////////ELIMINADORA/////////////////////
    public void EliminarCjtEvento() {
        cjt.vaciar();
        cjtFecha.vaciar();
        cjtImp.vaciar();
        restaurar();
    }

    /////////////////////////////////////MODIFICADORAS////////////////////////

    public void EliminarEvento(String nombre, Fecha fecha, int imp) throws Exception{
        //Siempre convierto el nombre a mayusculas para evitar errores de no
        // encontrar el elemento
        String name = nombre.toUpperCase();
        cjt.borrar(name + fecha.toString());
        cjtFecha.borrar(fecha.toString() + name);
        cjtImp.borrar(Integer.toString(imp) + name + fecha.toString());
        restaurar();
    }

    public void AgregarEvento(Evento e) throws Exception{
        cjt.insertar(e.ID(), e);
        cjtFecha.insertar(e.IDFecha(), e);
        cjtImp.insertar(e.IDImp(), e);
        restaurar();
    }

    public void ModificarNombreEvento(String nomViejo, Fecha fecha, String nomNuevo, int imp) throws Exception {
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
            String oldname = nomViejo.toUpperCase();
            String newname = nomNuevo.toUpperCase();
            if (!nomNuevo.equals(nomViejo)){
                //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el
                // conjunto de acuerdo a su nueva clave
                Evento aux = cjt.obtener(oldname + fecha.toString());
                aux.ModNombre(newname);
                cjt.modificar(oldname + fecha.toString(), newname + fecha.toString(), aux);
                cjtFecha.modificar(fecha.toString() + oldname, fecha.toString() + newname);
                cjtImp.modificar(Integer.toString(imp) + oldname + fecha.toString(), Integer.toString(imp) + newname + fecha.toString());
                restaurar();
            }
        //Si es igual nomViejo a nomNuevo no se hace nada no se hace nada
    }

    public void ModificarFechaEvento(String nombre, Fecha fechaVieja, Fecha fechaNueva, int imp) throws Exception {
        if (!fechaVieja.equals(fechaNueva)) {
            //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
            String name = nombre.toUpperCase();
            //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el
            // conjunto de acuerdo a su nueva clave
            Evento aux = cjt.obtener(name + fechaVieja.toString());
            aux.ModFecha(fechaNueva);
            cjt.modificar(name + fechaVieja.toString(), name + fechaNueva.toString(), aux);
            cjtFecha.modificar(name + fechaVieja.toString(), name + fechaNueva.toString());
            cjtImp.modificar(Integer.toString(imp)+ name+fechaVieja.toString(),Integer.toString(imp)+name+fechaNueva.toString());
            restaurar();
        }
        //Si es igual fechaVieja a fechaNueva no se hace nada
    }

    public void ModificarImpEvento(String nombre, Fecha fecha, int OldImp, int NewImp) throws Exception {
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        if (OldImp != NewImp) {
            String name = nombre.toUpperCase();
            //Como el tst devuelve la refencia al objeto directamente puedo cambiarle los atributos
            cjt.obtener(name+fecha.toString()).ModImportancia(NewImp);
            cjtImp.modificar(Integer.toString(OldImp)+name+fecha.toString(),Integer.toString(NewImp)+name+fecha.toString());
            restaurar();
        }
    }

    private TSTIterator desplazarIterador(int bloq, int tam){
        // Rellenar con los iteradores de todos los bloques.
        if (Cache.isEmpty()){
            TSTIterator res = new TSTIterator(cjt);
            int lim = (size()-1)/tam;
            for(int i = 0; i <= lim; ++i){
                Cache.add(new TSTIterator(res));
                int j = 0;
                while (res.hasNext() && j++<tam) res.next();
            }
            //System.out.println("Tam cache ev: " + Cache.size());
        }
        return new TSTIterator(Cache.get(bloq));
    }

    private TSTIterator desplazarIteradorF(int bloq, int tam){
        // Rellenar con los iteradores de todos los bloques.
        if (CacheF.isEmpty()){
            TSTIterator res = new TSTIterator(cjtFecha);
            int lim = (size()-1)/tam;
            for(int i = 0; i <= lim; ++i){
                CacheF.add(new TSTIterator(res));
                int j = 0;
                while (res.hasNext() && j++<tam) res.next();
            }
            //System.out.println("Tam cache ev: " + Cache.size());
        }
        return new TSTIterator(CacheF.get(bloq));
    }

    private TSTIterator desplazarIteradorI(int bloq, int tam){
        // Rellenar con los iteradores de todos los bloques.
        if (CacheI.isEmpty()){
            TSTIterator res = new TSTIterator(cjtImp);
            int lim = (size()-1)/tam;
            for(int i = 0; i <= lim; ++i){
                CacheI.add(new TSTIterator(res));
                int j = 0;
                while (res.hasNext() && j++<tam) res.next();
            }
            //System.out.println("Tam cache ev: " + Cache.size());
        }
        return new TSTIterator(CacheI.get(bloq));
    }

    public ArrayList<Evento> obtEventos(int bloq, int tam) {
        TSTIterator aux = desplazarIterador(bloq,tam);
        int i = 0;
        ArrayList<Evento> a = new ArrayList<Evento>();
        while (aux.hasNext() && i < tam) {
            Evento an = (Evento)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }

    public ArrayList<Evento> obtEventosF(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorF(bloq, tam);
        int i = 0;
        ArrayList<Evento> a = new ArrayList<Evento>();
        while (aux.hasNext() && i < tam) {
            Evento an = (Evento)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }

    public ArrayList<Evento> obtEventosI(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorI(bloq,tam);
        int i = 0;
        ArrayList<Evento> a = new ArrayList<Evento>();
        while (aux.hasNext() && i < tam) {
            Evento an = (Evento)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }

    private void restaurar() {
        Cache = new ArrayList<TSTIterator>();
        CacheF = new ArrayList<TSTIterator>();
        CacheI = new ArrayList<TSTIterator>();
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
                    AgregarEvento(e);
                    break;
                case 2:
                    e = new Personal(nombre, f, importancia);
                    AgregarEvento(e);
                    break;
                case 3:
                    e = new Profesional(nombre, f, importancia);
                    AgregarEvento(e);
                    break;
                case 4:
                    e = new Oficial(nombre, f, importancia);
                    AgregarEvento(e);
                    break;
                case 5:
                    e = new NoOficial(nombre, f, importancia);
                    AgregarEvento(e);
                    break;
                default:
            }
            restaurar();
        }
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
