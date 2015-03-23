package domini;

import domini.Congresista;

/**
 * Created by bug on 20/03/15.
 */
public class Relacion {
    private domini.Congresista Congresista;
    //private Evento E;
    Relacion() {};

    Relacion(Congresista C) {
        this.Congresista = C;
    }
    void mod_congresista(Congresista C) {
        this.Congresista = C;
    }
    /*
    void mod_evento(Evento E) {
        this.Evento = E;
    }
     */
    Congresista obt_congresista() {
        return this.Congresista;
    }

    /*
    Evento obt_evento() {
    return this.Evento;
     */

}
