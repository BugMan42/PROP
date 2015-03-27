package Domini;

/**
 * Created by falc on 24/03/15.
 */
public class ControladorDomini {

    Congreso congreso;
    ControladorEvento cjteventos;
    public ControladorDomini() {
        congreso = new Congreso();
        cjteventos = new ControladorEvento();
    }

    public void crear_congresista(String S) {
        Congresista c = new Congresista(S);
        congreso.add_Congresista(c);
    }
}

