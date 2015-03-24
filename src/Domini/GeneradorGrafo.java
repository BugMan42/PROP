package Domini;

/**
 * Created by bug on 23/03/15.
 */
public class GeneradorGrafo {
    private Grafo G;
    Congreso C;
    CjtRelacion R;
    CjtEvento E;
    public GeneradorGrafo() {
        G = new Grafo();
        C = new Congreso();
        R = new CjtRelacion();
        E = new CjtEvento();
    }
    public GeneradorGrafo(Congreso C, CjtRelacion R, CjtEvento E) {
        G = new Grafo();
        this.C = C;
        this.R = R;
        this.E = E;
    }
    public void GenerarGrafo() {

        //while all relaciones
        //add relacion grafo

    }
    public Grafo obt_Grafo() {
        return G;
    }

}
