package Domini;


public class RelacionSimpleConVoto extends RelacionSimple {
    private Voto V;

    /*public RelacionSimpleConVoto(Congresista a, Evento e, Voto v) {
        super(a, e);
        V = v;
    }*/
    public RelacionSimpleConVoto(Congresista a, Votacion e, Voto v) {
        super(a, e);
        V = v;
    }

    public void modVoto(Voto v1) {
        V = v1;
    }
    public Voto obtVoto() {
        return V;
    }

    //Permite distinguir relacion simple con voto y sin voto
    public boolean tieneVoto() {
        return true;
    }
    public String toString() {
        return super.toString()+" "+V;
    }
}
