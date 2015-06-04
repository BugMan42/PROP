package Domini;

import sun.rmi.runtime.Log;

import java.util.*;

public class Congreso {
    private class ExisteC extends Exception {
        public ExisteC(String str) {
            super("Ya existe el Congresista: "+str);
        }
    }
    private class NoExisteC extends Exception {
        public NoExisteC(String str) {
            super("No existe el Congresista: "+str);
        }
    }
    //static
    private static String[] nombre = {"ARIF", "KONRAD", "EDSGER","GORDON","KEVIN","RICHARD","MAX","LINUS","ALLEN","JONVON", "SOPHIE","ALAN","DAVID","ARIF","ZAMAN","TIM","SEGEY","LARRY","MARK","BILL"};
    private static String[] apellido = {"DIJKSTRA", "NEUMANN", "ZUSE","AWAGNER","MOORE","BELL","HAMMING","COHEN","NEWMAN","KNUTH","ALLEN","TORVALDS","WARWICK","NEUMANN","STALLMAN","TURING","ZAMAN","GOODGER","GATES","ZUCKERBERG"};
    private static String[] ciudad = {"LN", "PARIS", "ISLAMABAD","BCN","TOKIO","WASHINGTON","SEATTLE","NY", "SF","CHICAGO","ALEXANDRIA","ANNAPOLIS","HAIFA","LOUVAIN","TORONTO","BERLIN"};
    private static String[] partido = {"REP","DEM","LIBRE","PIRATA"};

    /*private class Congresistas {
        private List<Congresista> datos;
        Congresistas(Congresista a) {
            datos = new LinkedList<Congresista>();
            datos.add(a);
        }
        public int size() {
            return datos.size();
        }
        public List<Congresista> obtDatos() {
            return datos;
        }
        public void insertar(Congresista a) {
            //for (int i = 0; i < datos.size(); ++i) {
            datos.add(a);
            //}
        }
        public boolean isEmpty() {
            return datos.isEmpty();
        }
        public void eliminarCongresista(Congresista a) {
            //datos.remove(a);
            for (int i = 0; i < datos.size(); ++i) {
                if (datos.get(i).obtDni().equals(a.obtDni())) {
                    datos.remove(i);
                }
            }
        }
    }*/
    //atributs
    private TST<Congresista> tst;
    private TST<Congresista> tstNombre;
    private TST<Congresista> tstApellido;
    private TST<Congresista> tstEdad;
    private TST<Congresista> tstCiudad;
    private TST<Congresista> tstEstado;
    private TST<Congresista> tstPartido;

    private ArrayList<TSTIterator> Cache;
    private ArrayList<TSTIterator> CacheNombre;
    private ArrayList<TSTIterator> CacheApellido;
    private ArrayList<TSTIterator> CacheEdad;
    private ArrayList<TSTIterator> CacheCiudad;
    private ArrayList<TSTIterator> CacheEstado;
    private ArrayList<TSTIterator> CachePartido;

    //Creadoras
    public Congreso() {
        inicializar();
        reiniciarCaches();
    }
    private void reiniciarCaches() {
        Cache = new ArrayList<TSTIterator>();
        CacheNombre = new ArrayList<TSTIterator>();
        CacheApellido = new ArrayList<TSTIterator>();
        CacheEdad = new ArrayList<TSTIterator>();
        CacheCiudad = new ArrayList<TSTIterator>();
        CacheEstado = new ArrayList<TSTIterator>();
        CachePartido = new ArrayList<TSTIterator>();
    }
    private void inicializar() {
        tst = new TST<Congresista>();
        tstNombre = new TST<Congresista>();
        tstApellido = new TST<Congresista>();
        tstEdad = new TST<Congresista>();
        tstCiudad = new TST<Congresista>();
        tstEstado = new TST<Congresista>();
        tstPartido = new TST<Congresista>();
    }
    private void agregarEnTsts(Congresista con) throws Exception {
        tstNombre.insertar(con.obtNombre()+con.obtID(), con);
        tstApellido.insertar(con.obtApellido() + con.obtID(), con);
        tstEdad.insertar(String.valueOf(con.obtEdad()) + con.obtID(), con);
        tstCiudad.insertar(con.obtCiudad() + con.obtID(), con);
        tstEstado.insertar(con.obtEstado() + con.obtID(), con);
        tstPartido.insertar(con.obtPartido() + con.obtID(), con);
    }
    private void borrarEnTsts(Congresista con) throws Exception {
        tstNombre.borrar(con.obtNombre() + con.obtID());
        tstApellido.borrar(con.obtApellido() + con.obtID());
        tstEdad.borrar(String.valueOf(con.obtEdad()) + con.obtID());
        tstCiudad.borrar(con.obtCiudad() + con.obtID());
        tstEstado.borrar(con.obtEstado() + con.obtID());
        tstPartido.borrar(con.obtPartido() + con.obtID());
    }
    /** TODO testear */
    public void agregarCongresista(Congresista c) throws Exception {
        tst.insertar(c.obtID(), c);
        agregarEnTsts(c);
        reiniciarCaches();
    }
    /** **/
    public void agregarCongresistaRandom()  {
        Random rand = new Random();
        Dni r = new Dni();
        int i = 0;
        while (tst.existe(r.toString())) {
            try {
                r.cambiarLetra((char) ('A'+ i));
            } catch (Exception a) {

            }
            ++i;
            if (i == 26) r = new Dni();
        }
        int edad = rand.nextInt(62)+18 ;
        int name = rand.nextInt(20);
        int surname = rand.nextInt(20);
        int city = rand.nextInt(16);
        int part = rand.nextInt(4);
        try {
            Congresista c = new Congresista(r,nombre[name],apellido[surname],edad,ciudad[city],ciudad[city],partido[part]);
            tst.insertar(c.obtID(), c);
            agregarEnTsts(c);
            reiniciarCaches();
        } catch (Exception a) {
            //Do nothing
        }
    }
    public void eliminarCongresista(Dni dni) throws Exception {
        tst.borrar(dni.toString());
        reiniciarCaches();
       // borrarEnTsts(c);
    }
    /**TODO testear */
    public void eliminarCongresista(Congresista c) throws Exception {
        tst.borrar(c.obtDni().toString());
        borrarEnTsts(c);
        reiniciarCaches();
        if (size() == 0) eliminarCongreso();
    }
    public void eliminarCongreso() {
        tst.vaciar();
        tstNombre.vaciar();
        tstApellido.vaciar();
        tstEdad.vaciar();
        tstCiudad.vaciar();
        tstEstado.vaciar();
        tstPartido.vaciar();
        reiniciarCaches();
    }
    public void modCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido,
                               int edad, String ciudad, String estado, String partido) throws  Exception{
        if (!dni.equals(dniNuevo)) {
            tst.modificar(dni.toString(), dniNuevo.toString());
            //tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
            tst.obtener(dniNuevo.toString()).mod(dniNuevo, nombre, apellido, edad, ciudad, estado, partido);
            reiniciarCaches();
        }
        else {
            tst.obtener(dni.toString()).mod(nombre, apellido, edad, ciudad, estado, partido);
            reiniciarCaches();
        }
    }
    //TODO testear
    public void modCongresista(Congresista origina, Congresista nuevo) throws Exception {
        Congresista original = new Congresista(origina);
        //print(original.toString());
        //print(nuevo.toString());
       // print("modificando");
        if (!original.obtID().equals(nuevo.obtID())) {
            print("has entrado en dni dif");
            tst.modificar(original.obtDni().toString(), nuevo.obtDni().toString());
            Congresista aux = tst.obtener(nuevo.obtDni().toString());
            aux.mod(nuevo.obtDni(), nuevo.obtNombre(), nuevo.obtApellido(), nuevo.obtEdad(), nuevo.obtCiudad(), nuevo.obtEstado(), nuevo.obtPartido());
            tstNombre.borrar(original.obtNombre() + original.obtID());
            tstNombre.insertar(nuevo.obtNombre() + nuevo.obtID(), aux);
            tstApellido.borrar(original.obtApellido()+original.obtID());
            tstApellido.insertar(nuevo.obtApellido()+nuevo.obtID(),aux);
            tstEdad.borrar(original.obtEdad()+original.obtID());
            tstEdad.insertar(nuevo.obtEdad()+nuevo.obtID(), aux);
            tstCiudad.borrar(original.obtCiudad()+original.obtID());
            tstCiudad.insertar(nuevo.obtCiudad()+nuevo.obtID(),aux);
            tstEstado.borrar(original.obtEstado()+original.obtID());
            tstEstado.insertar(nuevo.obtEstado()+nuevo.obtID(),aux);
            tstPartido.borrar(original.obtPartido()+original.obtID());
            tstPartido.insertar(nuevo.obtPartido()+nuevo.obtID(),aux);
        }
        else { //dnis iguales
            //print("has entrat en dni igual");
            Congresista aux = tst.obtener(original.obtDni().toString());
            aux.mod(nuevo.obtNombre(), nuevo.obtApellido(), nuevo.obtEdad(), nuevo.obtCiudad(), nuevo.obtEstado(), nuevo.obtPartido());
            if (!original.obtNombre().equals(nuevo.obtNombre())) {
                tstNombre.borrar(original.obtNombre() + original.obtID());
                tstNombre.insertar(nuevo.obtNombre()+nuevo.obtID(), aux);
            }
            if (!original.obtApellido().equals(nuevo.obtApellido())) {
                tstApellido.borrar(original.obtApellido()+original.obtID());
                tstApellido.insertar(nuevo.obtApellido()+nuevo.obtID(),aux);
            }
            if (original.obtEdad() != nuevo.obtEdad()) {
                tstEdad.borrar(original.obtEdad()+original.obtID());
                tstEdad.insertar(nuevo.obtEdad()+nuevo.obtID(), aux);

            }
            if (!original.obtCiudad().equals(nuevo.obtCiudad())) {
                tstCiudad.borrar(original.obtCiudad()+original.obtID());
                tstCiudad.insertar(nuevo.obtCiudad()+nuevo.obtID(),aux);
            }
            if (!original.obtEstado().equals(nuevo.obtEstado())) {
                tstEstado.borrar(original.obtEstado()+original.obtID());
                tstEstado.insertar(nuevo.obtEstado()+nuevo.obtID(),aux);
            }
            if (!original.obtPartido().equals(nuevo.obtPartido())) {
                tstPartido.borrar(original.obtPartido()+original.obtID());
                tstPartido.insertar(nuevo.obtPartido()+nuevo.obtID(),aux);
            }
        }
        reiniciarCaches();
    }

    private TSTIterator desplazarIterador(int bloq, int tam) {
        // Rellenar con los iteradores de todos los bloques.
        if (Cache.isEmpty()){
            TSTIterator res = new TSTIterator(tst);
            int lim = (size()-1)/tam;
            for(int i = 0; i <= lim; ++i){
                Cache.add(new TSTIterator(res));
                int j = 0;
                while (res.hasNext() && j++<tam) res.next();
            }
            //print("Tam cache congr: "+Cache.size());
        }
        return new TSTIterator(Cache.get(bloq));
    }
    public ArrayList<Congresista> obtCongreso(int bloq, int tam) {
        TSTIterator aux = desplazarIterador(bloq,tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }
    private TSTIterator desplazarIteradorUniversal(TST<Congresista> t, ArrayList<TSTIterator> cache,int bloq, int tam){
        if (cache.isEmpty()){
            TSTIterator res = new TSTIterator(t);
            int lim = (size()-1)/tam;
            for(int i = 0; i <= lim; ++i){
                cache.add(new TSTIterator(res));
                int j = 0;
                while (res.hasNext() && j++<tam) res.next();
            }
            //print("Tam cache congr: "+Cache.size());
        }
        return new TSTIterator(cache.get(bloq));
    }

    public ArrayList<Congresista> obtCongresoNombre(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorUniversal(tstNombre,CacheNombre, bloq, tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }
    public ArrayList<Congresista> obtCongresoApellido(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorUniversal(tstApellido,CacheApellido, bloq, tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }
    public ArrayList<Congresista> obtCongresoEdad(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorUniversal(tstEdad,CacheEdad, bloq, tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }
    public ArrayList<Congresista> obtCongresoCiudad(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorUniversal(tstCiudad,CacheCiudad, bloq, tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }
    public ArrayList<Congresista> obtCongresoEstado(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorUniversal(tstEstado, CacheEstado, bloq, tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }
    public ArrayList<Congresista> obtCongresoPartido(int bloq, int tam) {
        TSTIterator aux = desplazarIteradorUniversal(tstPartido,CachePartido, bloq, tam);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < tam) {
            Congresista an = (Congresista)aux.next();
            a.add(an);
            ++i;
        }
        return a;
    }

    //Consultoras
    public Congresista consultarCongresista(Dni dni) throws Exception {
        return tst.obtener(dni.toString());
    }
    public Congresista copiaConsultarCongresista(Dni dni) throws Exception {
        //Congresista aux = new Congresista(tst.obtener(dni.toString()));
        return new Congresista(tst.obtener(dni.toString()));
    }
    public int size() {
        return tst.size();
    }
    public boolean esVacio() {
        return tst.esVacio();
    }
    public List<Congresista> obtenerCongreso() {
        return tst.consultarObjetos();
    }
    public boolean contieneCongresista(Dni dni) {
        return tst.existe(dni.toString());
    }
    public String toString() {
        String aux = tst.toString();
        return aux;
    }
    private void print(String str) {
        System.out.println(str);
    }
    public ArrayList<String> obtenerListaID() {
        return tst.consultarClaves();
    }



    public ArrayList<String> obtenerListaNombre() {
        return tstNombre.consultarClaves();
    }
    public ArrayList<String> obtenerListaApellido() {
        return tstApellido.consultarClaves();
    }
    public ArrayList<String> obtenerListaEdad() {
        return tstEdad.consultarClaves();
    }
    public ArrayList<String> obtenerListaCiudad() {
        return tstCiudad.consultarClaves();
    }
    public ArrayList<String> obtenerListaEstado() {
        return tstEstado.consultarClaves();
    }
    public ArrayList<String> obtenerListaPartido() {
        return tstPartido.consultarClaves();
    }

    public List<Congresista> obtenerListaNombre1() {
        return tstNombre.consultarObjetos();
    }
    public List<Congresista> obtenerListaApellido1() {
        return tstApellido.consultarObjetos();
    }
    public List<Congresista> obtenerListaEdad1() {
        return tstEdad.consultarObjetos();
    }
    public List<Congresista> obtenerListaCiudad1() {
        return tstCiudad.consultarObjetos();
    }
    public List<Congresista> obtenerListaEstado1() {
        return tstEstado.consultarObjetos();
    }
    public List<Congresista> obtenerListaPartido1() {
        return tstPartido.consultarObjetos();
    }
    /*public ArrayList<Congresista> obtCongresoFunciona(int bloq, int tam) {
        //TSTIterator aux;
        TSTIterator aux = new TSTIterator(tst);
        int i = 0;
        ArrayList<Congresista> a = new ArrayList<Congresista>();
        while ( aux.hasNext() && i < bloq*tam+ tam) {
            Congresista an = (Congresista)aux.next();
            if (i >= bloq*tam && i < (bloq*tam)+tam) {
                a.add(an);
            }
            ++i;
        }
        return a;
    }*/
    public List<Congresista> searchPrefixDni(String prefix) {
        //print("prefix"+prefix.toString());
        List<Congresista> aux = tst.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }
    public List<Congresista> searchPrefixNombre(String prefix) {
        List<Congresista> aux = tstNombre.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }
    public List<Congresista> searchPrefixApellido(String prefix) {
        List<Congresista> aux = tstApellido.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }
    public List<Congresista> searchPrefixEdad(String prefix) {
        List<Congresista> aux = tstEdad.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }
    public List<Congresista> searchPrefixCiudad(String prefix) {
        List<Congresista> aux = tstCiudad.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }
    public List<Congresista> searchPrefixEstado(String prefix) {
        List<Congresista> aux = tstEstado.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }
    public List<Congresista> searchPrefixPartido(String prefix) {
        List<Congresista> aux = tstPartido.prefijo(prefix);
        if (aux == null) return new ArrayList<Congresista>();
        return aux;
    }









}
