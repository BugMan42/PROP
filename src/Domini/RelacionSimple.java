package Domini;


public abstract class RelacionSimple extends Relacion {

    private Congresista C;
    private Evento E;

    public RelacionSimple(Congresista a, Evento e) {
        super();
        C = a;
        E = e;
    }
    //Modificadoras cualquier tipo de relacion Simple
    public void modRelacion(Congresista C1, Evento Ev) {
        C = C1;
        E = Ev;
    }

    public void modRelacion(Congresista C1) {
        C = C1;
    }
    public void modRelacion(Evento Ev) {
        E = Ev;
    }

    //Modificadora solo valida si tienes voto sino Excepcion
    public abstract void modVoto(Voto v) throws Exception;

    //Consultoras
    public Congresista obtCongresista() {
        return C;
    }
    public Evento obtEvento() {
        return E;
    }

    //Metodo que indica si tiene voto
    public abstract boolean tieneVoto();

    //Consultora solo valida si tienes voto
    public abstract Voto obtVoto() throws Exception;

    //para distinguir entre relacionsimple y compuesta
    public boolean esSimple() {
        return true;
    }
    public String toString() {
        return C.obtDni()+" "+E.obt_nombre()+" "+E.obt_fecha();
    }
}
