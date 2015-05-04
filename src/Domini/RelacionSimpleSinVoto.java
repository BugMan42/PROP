package Domini;

public class RelacionSimpleSinVoto extends RelacionSimple {

    public RelacionSimpleSinVoto(Congresista a, Evento e) {
        super(a, e);
    }

    //Lanzar excepion si se intenta modificar el voto de una relacion sin voto
    public void modVoto(Voto v1) throws Exception {
        throw new Exception("Relacion Simple Sin Voto No tiene voto");
    }

    //Lanzar excepion si se consulta el voto de una relacion sin voto
    public Voto obtVoto()throws Exception {
        throw new Exception("Relacion Simple Sin Voto No tiene voto");
    }

    //retorna false, no tiene voto
    public boolean tieneVoto() {
        return false;
    }

}
