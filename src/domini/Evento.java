package domini;

import java.util.Date;

/**
 * Created by falc on 20/03/15.
 * Clase Evento
 */
public abstract class Evento {
    private String nombre;
    private Date fecha;
    private String subtipo;
    private int importancia;

    public Evento(String nombre, Date fecha, String subtipo, int importancia) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.subtipo = subtipo;
        this.importancia = importancia;
    }

    public Evento(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Evento() {}

    public void mod_nombre(String nombre) {
        this.nombre = nombre;
    }
    public void mod_fecha (Date fecha) {
        this.fecha = fecha;
    }
    public void mod_importancia(int importancia) {
        this.importancia = importancia;
    }
    public void mod_subtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String obt_nombre() {
        return this.nombre;
    }
    public Date obt_fecha() {
        return this.fecha;
    }
    public String obt_subtipo() { return this.subtipo; }
    public int obt_importancia() {
        return this.importancia;
    }
}
