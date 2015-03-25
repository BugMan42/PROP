package Domini;

/**
 * Created by falc on 24/03/15.
 */
public class ControladorDomini {

    Congreso congreso;
    CjtEvento cjteventos;
    public ControladorDomini() {
        congreso = new Congreso();
        cjteventos = new CjtEvento();
    }

    public void crear_congresista(String S) {
        Congresista c = new Congresista(S);
        congreso.add_Congresista(c);
    }
}

