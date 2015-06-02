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
    private static String[] nombre = {"Arif", "Konrad", "Edsger","Gordon","Kevin","Richard","Max","Linus","Allen","Jon-Von",
            "Sophie","Alan","David","Arif","Zaman","Tim","Sergey","Larry","Mark","Bill"};
    private static String[] apellido = {"Dijkstra", "Neumamnn", "Zuse","A.Wagner","Moore","Bell","Hamming","Cohen","Newman","Knuth",
            "Allen","Torvalds","Warwick","Neumann","Stallman","Turing","Zaman","Goodger","Gates","Zuckerberg"};
    private static String[] ciudad = {"Londres", "Paris", "Islamabad","Barcelona","Tokio","Washington","Seattle","New-York",
            "San-Francisco","Chicago","Alexandria","Annapolis","Haifa","Louvain","Toronto","Berlin"};
    private static String[] partido = {"Republicano","Democrata","Libre","Pirata"};

    //atributs
    private TST<Congresista> tst;
    private ArrayList<TSTIterator> Cache;
    private int bloque;

    //Creadoras
    public Congreso() {
        tst = new TST<Congresista>();
        Cache = new ArrayList<TSTIterator>();
        bloque = 0;
    }
    public void agregarCongresista(Congresista c) throws Exception {
        tst.insertar(c.obtID(), c);
        Cache = new ArrayList<TSTIterator>();
        bloque = 0;
    }
    private void print(String str) {
        System.out.println(str);
    }
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
            Cache = new ArrayList<TSTIterator>();
            bloque = 0;
        } catch (Exception a) {
            //Do nothing
        }
    }

    public void eliminarCongresista(Dni dni) throws Exception {
        tst.borrar(dni.toString());
        Cache = new ArrayList<TSTIterator>();
        bloque = 0;
    }
    public void eliminarCongreso() {
        tst.vaciar();
        Cache = new ArrayList<TSTIterator>();
        bloque = 0;
    }

    //Modificadoras
    public void modDniCongresista(Dni dni, Dni dniNuevo) throws Exception {
        if (!dni.equals(dniNuevo)) {
            tst.modificar(dni.toString(), dniNuevo.toString());
            tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
            Cache = new ArrayList<TSTIterator>();
            bloque = 0;
        }
    }
    public void modNombreCongresista(Dni dni,String nombre) throws Exception {
        tst.obtener(dni.toString()).modNombre(nombre);
    }
    public void modApellidoCongresista(Dni dni,String apellido) throws Exception {
        tst.obtener(dni.toString()).modApellido(apellido);
    }
    public void modEdadCongresista(Dni dni,int edad) throws Exception {
        tst.obtener(dni.toString()).modEdad(edad);
    }
    public void modCiudadCongresista(Dni dni,String ciudad) throws Exception {
        tst.obtener(dni.toString()).modCiudad(ciudad);
    }
    public void modEstadoCongresista(Dni dni,String estado) throws Exception {
        tst.obtener(dni.toString()).modEstado(estado);
    }
    public void modPartidoCongresista(Dni dni,String partido) throws Exception {
        tst.obtener(dni.toString()).modPartido(partido);
    }
    public void modCongresista(Dni dni,Dni dniNuevo , String nombre, String apellido,
                               int edad, String ciudad, String estado, String partido) throws  Exception{
        if (!dni.equals(dniNuevo)) {
            tst.modificar(dni.toString(), dniNuevo.toString());
            //tst.obtener(dniNuevo.toString()).modDni(dniNuevo);
            tst.obtener(dniNuevo.toString()).mod(dniNuevo, nombre, apellido, edad, ciudad, estado, partido);
            Cache = new ArrayList<TSTIterator>();
            bloque = 0;
        }
        else {
            tst.obtener(dni.toString()).mod(nombre, apellido, edad, ciudad, estado, partido);
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
            print("Tam cache: "+Cache.size());
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
    public ArrayList<Congresista> obtCongresoFunciona(int bloq, int tam) {
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
    }
    public List<Congresista> searchPrefix(String prefix) {
        return tst.prefijo(prefix);
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
    public ArrayList<String> obtenerListaID() {
        return tst.consultarClaves();
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

}
