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
    private static String[] nombre = {"Arif", "Konrad", "Edsger","Gordon","Kevin","Richard","Max","Linus","Allen","Jon-Von", "Sophie","Alan","David","Arif","Zaman","Tim","Sergey","Larry","Mark","Bill"};
    private static String[] apellido = {"Dijkstra", "Neumamnn", "Zuse","A.Wagner","Moore","Bell","Hamming","Cohen","Newman","Knuth","Allen","Torvalds","Warwick","Neumann","Stallman","Turing","Zaman","Goodger","Gates","Zuckerberg"};
    private static String[] ciudad = {"Londres", "Paris", "Islamabad","Barcelona","Tokio","Washington","Seattle","New-York", "San-Francisco","Chicago","Alexandria","Annapolis","Haifa","Louvain","Toronto","Berlin"};
    private static String[] partido = {"Republicano","Democrata","Libre","Pirata"};

    private class Congresistas {
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
    }
    //atributs
    private TST<Congresista> tst;
    private TST<Congresistas> tstNombre;
    private TST<Congresistas> tstApellido;
    private TST<Congresistas> tstEdad;
    private TST<Congresistas> tstCiudad;
    private TST<Congresistas> tstEstado;
    private TST<Congresistas> tstPartido;

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
        tstNombre = new TST<Congresistas>();
        tstApellido = new TST<Congresistas>();
        tstEdad = new TST<Congresistas>();
        tstCiudad = new TST<Congresistas>();
        tstEstado = new TST<Congresistas>();
        tstPartido = new TST<Congresistas>();
    }
    private void agregarEnTsts(Congresista con) {
        Congresistas a = tstNombre.insertarSinExc(con.obtNombre(), new Congresistas(con));
        if (a.size() > 1) a.insertar(con);
        Congresistas b = tstApellido.insertarSinExc(con.obtApellido(), new Congresistas(con));
        if (b.size() > 1) b.insertar(con);
        Congresistas c = tstEdad.insertarSinExc(String.valueOf(con.obtEdad()), new Congresistas(con));
        if (c.size() > 1) c.insertar(con);
        Congresistas d = tstCiudad.insertarSinExc(con.obtCiudad(), new Congresistas(con));
        if (d.size() > 1) d.insertar(con);
        Congresistas e = tstEstado.insertarSinExc(con.obtEstado(), new Congresistas(con));
        if (e.size() > 1) e.insertar(con);
        Congresistas f = tstPartido.insertarSinExc(con.obtPartido(), new Congresistas(con));
        if (f.size() > 1) f.insertar(con);
    }
    private void borrarEnTsts(Congresista con) throws Exception {
        Congresistas a = tstNombre.obtener(con.obtNombre());
        a.eliminarCongresista(con);
        if (a.isEmpty()) tstNombre.borrar(con.obtNombre());

        Congresistas b = tstApellido.obtener(con.obtApellido());
        b.eliminarCongresista(con);
        if (b.isEmpty()) tstApellido.borrar(con.obtApellido());

        Congresistas c = tstEdad.obtener(String.valueOf(con.obtEdad()));
        c.eliminarCongresista(con);
        if (c.isEmpty()) tstEdad.borrar(String.valueOf(con.obtEdad()));

        Congresistas d = tstCiudad.obtener(con.obtCiudad());
        d.eliminarCongresista(con);
        if (d.isEmpty()) tstCiudad.borrar(con.obtCiudad());

        Congresistas e = tstEstado.obtener(con.obtEstado());
        e.eliminarCongresista(con);
        if (e.isEmpty()) tstEstado.obtener(con.obtEstado());

        Congresistas f = tstPartido.obtener(con.obtPartido());
        f.eliminarCongresista(con);
        if (f.isEmpty()) tstPartido.borrar(con.obtPartido());
    }
    /** TODO testear */
    public void agregarCongresista(Congresista c) throws Exception {
        tst.insertar(c.obtID(), c);
        reiniciarCaches();
        agregarEnTsts(c);
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
            reiniciarCaches();
            agregarEnTsts(c);
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
    public void eliminarCongresistaNAIS(Congresista c) throws Exception {
        tst.borrar(c.obtDni().toString());
        reiniciarCaches();
        borrarEnTsts(c);
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
    //Modificadoras
    public void modDniCongresista(Dni dni, Dni dniNuevo) throws Exception {
        if (!dni.equals(dniNuevo)) {
            tst.modificar(dni.toString(), dniNuevo.toString());
            tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
            reiniciarCaches();
        }
    }

    public void modNombreCongresista(Dni dni,String nombre) throws Exception {
        tst.obtener(dni.toString()).modNombre(nombre);
        reiniciarCaches();
    }
    public void modApellidoCongresista(Dni dni,String apellido) throws Exception {
        tst.obtener(dni.toString()).modApellido(apellido);
        reiniciarCaches();
    }
    public void modEdadCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
        reiniciarCaches();
    }
    public void modCiudadCongresista(Dni dni,String ciudad) throws Exception {
        tst.obtener(dni.toString()).modCiudad(ciudad);
        reiniciarCaches();
    }
    public void modEstadoCongresista(Dni dni,String estado) throws Exception {
        tst.obtener(dni.toString()).modEstado(estado);
        reiniciarCaches();
    }
    public void modPartidoCongresista(Dni dni,String partido) throws Exception {
        tst.obtener(dni.toString()).modPartido(partido);
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
       // print("modificando");
        if (!original.obtDni().equals(nuevo.obtDni())) {
            tst.modificar(original.obtDni().toString(), nuevo.obtDni().toString());
            tst.obtener(nuevo.obtDni().toString()).mod(nuevo.obtDni(), nuevo.obtNombre(), nuevo.obtApellido(), nuevo.obtEdad(), nuevo.obtCiudad(), nuevo.obtEstado(), nuevo.obtPartido());
            if (!original.obtNombre().equals(nuevo.obtNombre())) {
                //print("he entrado en nombre");
                Congresistas a = tstNombre.obtener(original.obtNombre());
                a.eliminarCongresista(nuevo);
                if (a.isEmpty()) tstNombre.borrar(original.obtNombre());
                Congresistas aa = tstNombre.insertarSinExc(nuevo.obtNombre(), new Congresistas(nuevo));
                if (a.size() > 1) a.insertar(nuevo);

            }
            if (!original.obtApellido().equals(nuevo.obtApellido())) {
                //print("he entrado en apellido");
                Congresistas b = tstApellido.obtener(original.obtApellido());
                b.eliminarCongresista(nuevo);
                if (b.isEmpty()) tstApellido.borrar(original.obtApellido());
                Congresistas bb = tstApellido.insertarSinExc(nuevo.obtApellido(), new Congresistas(nuevo));
                if (b.size() > 1) b.insertar(nuevo);

            }
            if (original.obtEdad() != nuevo.obtEdad()) {
                //print("he entrado en edad "+nuevo.obtEdad()+" edad orig "+original.obtEdad());
                Congresistas c = tstEdad.obtener(String.valueOf(original.obtEdad()));
                c.eliminarCongresista(nuevo);
                if (c.isEmpty()) tstEdad.borrar(String.valueOf(original.obtEdad()));
                Congresistas cc = tstEdad.insertarSinExc(String.valueOf(nuevo.obtEdad()), new Congresistas(nuevo));
                if (c.size() > 1) c.insertar(nuevo);

            }
            if (!original.obtCiudad().equals(nuevo.obtCiudad())) {
                //print("he entrado en ciudad");
                Congresistas d = tstCiudad.obtener(original.obtCiudad());
                d.eliminarCongresista(nuevo);
                if (d.isEmpty()) tstCiudad.borrar(original.obtCiudad());
                Congresistas dd = tstCiudad.insertarSinExc(nuevo.obtCiudad(), new Congresistas(nuevo));
                if (d.size() > 1) d.insertar(nuevo);

            }
            if (!original.obtEstado().equals(nuevo.obtEstado())) {
                //print("he entrado en estado");
                Congresistas e = tstEstado.obtener(original.obtEstado());
                e.eliminarCongresista(nuevo);
                if (e.isEmpty()) tstEstado.obtener(original.obtEstado());
                Congresistas ee = tstEstado.insertarSinExc(nuevo.obtEstado(), new Congresistas(nuevo));
                if (e.size() > 1) e.insertar(nuevo);

            }
            if (!original.obtPartido().equals(nuevo.obtPartido())) {
                //print("he entrado en partido");
                Congresistas f = tstPartido.obtener(original.obtPartido());
                f.eliminarCongresista(nuevo);
                if (f.isEmpty()) tstPartido.borrar(original.obtPartido());
                Congresistas ff = tstPartido.insertarSinExc(nuevo.obtPartido(), new Congresistas(nuevo));
                if (f.size() > 1) f.insertar(nuevo);

            }
            reiniciarCaches();
        }
        else { //dnis iguales
            //print("Modificando");
            //print(original.toString());
            //print(nuevo.toString());
            tst.obtener(original.obtDni().toString()).mod(nuevo.obtNombre(), nuevo.obtApellido(), nuevo.obtEdad(), nuevo.obtCiudad(), nuevo.obtEstado(), nuevo.obtPartido());
            if (!original.obtNombre().equals(nuevo.obtNombre())) {
                //print("he entrado en nombre");
                Congresistas a = tstNombre.obtener(original.obtNombre());
                a.eliminarCongresista(nuevo);
                if (a.isEmpty()) tstNombre.borrar(original.obtNombre());
                Congresistas aa = tstNombre.insertarSinExc(nuevo.obtNombre(), new Congresistas(nuevo));
                if (a.size() > 1) a.insertar(nuevo);

            }
            if (!original.obtApellido().equals(nuevo.obtApellido())) {
                print("he entrado en apellido");
                Congresistas b = tstApellido.obtener(original.obtApellido());
                b.eliminarCongresista(nuevo);
                if (b.isEmpty()) tstApellido.borrar(original.obtApellido());
                Congresistas bb = tstApellido.insertarSinExc(nuevo.obtApellido(), new Congresistas(nuevo));
                if (b.size() > 1) b.insertar(nuevo);

            }
            if (original.obtEdad() != nuevo.obtEdad()) {
                //print("he entrado en edad");
                //print("he entrado en edad "+nuevo.obtEdad()+" edad orig "+original.obtEdad());
                Congresistas c = tstEdad.obtener(String.valueOf(original.obtEdad()));
                //print(c.obtDatos()+"");
                c.eliminarCongresista(nuevo);
                if (c.isEmpty()) tstEdad.borrar(String.valueOf(original.obtEdad()));
                Congresistas cc = tstEdad.insertarSinExc(String.valueOf(nuevo.obtEdad()), new Congresistas(nuevo));
                if (c.size() > 1) c.insertar(nuevo);

            }
            if (!original.obtCiudad().equals(nuevo.obtCiudad())) {
                //print("he entrado en ciudad");
                Congresistas d = tstCiudad.obtener(original.obtCiudad());
                d.eliminarCongresista(nuevo);
                if (d.isEmpty()) tstCiudad.borrar(original.obtCiudad());
                Congresistas dd = tstCiudad.insertarSinExc(nuevo.obtCiudad(), new Congresistas(nuevo));
                if (d.size() > 1) d.insertar(nuevo);

            }
            if (!original.obtEstado().equals(nuevo.obtEstado())) {
                //print("he entrado en estado");
                Congresistas e = tstEstado.obtener(original.obtEstado());
                e.eliminarCongresista(nuevo);
                if (e.isEmpty()) tstEstado.obtener(original.obtEstado());
                Congresistas ee = tstEstado.insertarSinExc(nuevo.obtEstado(), new Congresistas(nuevo));
                if (e.size() > 1) e.insertar(nuevo);

            }
            if (!original.obtPartido().equals(nuevo.obtPartido())) {
                //print("he entrado en partido");
                Congresistas f = tstPartido.obtener(original.obtPartido());
                f.eliminarCongresista(nuevo);
                if (f.isEmpty()) tstPartido.borrar(original.obtPartido());
                Congresistas ff = tstPartido.insertarSinExc(nuevo.obtPartido(), new Congresistas(nuevo));
                if (f.size() > 1) f.insertar(nuevo);

            }
            reiniciarCaches();
        }
    }




    private TSTIterator desplazarIterador(int bloq, int tam){
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
    public List<Congresista> searchPrefix(String prefix) {
        return tst.prefijo(prefix);
    }








}
