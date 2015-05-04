package Domini;

/**
 * Created by bug on 4/05/15.
 */
public class RelacionSimpleSinVoto extends RelacionSimple {
    public RelacionSimpleSinVoto(Congresista a, Evento e) {
        super(false, a, e);
    }
    public Voto obtVoto()throws Exception {
        throw new Exception("No hay voto");
    }
    public void modVoto(Voto v1) throws Exception {
        throw new Exception("No hay voto");
    }

}
