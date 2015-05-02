package Persistencia;

import java.io.*;

/**
 * Created by bug on 23/03/15.
 */
public class ControladorPersistencia {
    static final String E1 = "El fichero ya se ha abierto para escritura.";
    static final String E2 = "El fichero ya se ha abierto para lectura.";
    static final String E3 = "No se ha abierto ningún fichero para lectura.";
    static final String E4 = "No se ha abierto ningún fichero para escritura.";
    static final String E5 = "Ningún fichero abierto.";

    private BufferedReader r;
    private BufferedWriter w;
    private String archivo;

    public ControladorPersistencia(String ruta){
        r = null;
        w = null;
        archivo = ruta;
    }

    public void abrirLectura() throws Exception {
        if (r != null) throw new Exception(E2);
        if (w != null) throw new Exception(E1);
        r = new BufferedReader(new FileReader(archivo));
    }

    public void abrirEscritura() throws Exception {
        if (r != null) throw new Exception(E2);
        if (w != null) throw new Exception(E1);
        w = new BufferedWriter(new FileWriter(archivo));
    }

    public String leer(int num_lineas) throws Exception {
        if (r == null) throw new Exception(E3);
        String s = "";
        String l;
        int i = 0;
        while (i++ < num_lineas && (l = r.readLine()) != null) s += l+"\n";
        return s;
    }

    public void escribir(String datos) throws Exception {
        if (w == null) throw new Exception(E4);
        String l[] = datos.split("\n");
        int i = 0;
        while (i < l.length){
            w.write(l[i++]);
            w.newLine();
        }
    }

    public void cerrarFichero() throws Exception {
        if (r == null && w == null) throw new Exception(E5);
        else if (r != null) {
            r.close();
            r = null;
        }
        else {
            w.close();
            w = null;
        }
    }
}
