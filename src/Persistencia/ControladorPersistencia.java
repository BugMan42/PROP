package Persistencia;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Created by bug on 23/03/15.
 */
public class ControladorPersistencia {

    public ControladorPersistencia(){

    }

    public void guardar(String path, String data) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(data);
            oos.close();
        }
        catch (FileNotFoundException e){
            throw new IOException("Se ha producido un error al crear el fichero.");
        }
        catch (SecurityException e){
            throw new IOException("El fichero está bloqueado.");
        }
    }
}
