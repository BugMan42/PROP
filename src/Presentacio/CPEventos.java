package Presentacio;

import Domini.ControladorCjtEvento;

public class CPEventos {
    static final int tamanioBloque = 100;
    private PanelEventos PE;
    private ControladorCjtEvento CCE;
    private CPRelaciones CR;
    private int ultimo;
    private String[] bloqueA, bloqueB;
    private int indiceA, indiceB;


    public CPEventos() {
        CCE = new ControladorCjtEvento();
        indiceA = 0;
        indiceB = -1;
        bloqueA = new String[100];
        bloqueB = new String[100];
        ultimo = 0;
    }

    public PanelEventos obtPanel() {
        if (PE == null) PE = new PanelEventos(this);
        return PE;
    }

    public ControladorCjtEvento obtCCE() {return CCE;}

    public void modCR(CPRelaciones cpr) {CR = cpr;}

    public CPRelaciones obtCPR() {return CR;}

    public void nuevo(){
        if (CCE.size()>0) CCE.EliminarCjtEvento(CR.obtCR());
        actualizar();
    }

    public String obtEvento(int indice){
        int bloque = indice/tamanioBloque;
        if (bloque == indiceA) {
            ultimo = 0;
            return bloqueA[(indice%tamanioBloque)];
        }
        else if (bloque == indiceB) {
            ultimo = 1;
            return bloqueB[(indice%tamanioBloque)];
        }
        else {
            if (ultimo == 0) {
                ultimo = 1;
                indiceB = bloque;
                bloqueB = CCE.obtBloqueP(bloque, tamanioBloque).split("\n");
                return bloqueB[(indice%tamanioBloque)];
            }
            else {
                ultimo = 0;
                indiceA = bloque;
                bloqueA = CCE.obtBloqueP(bloque, tamanioBloque).split("\n");
                return bloqueA[(indice%tamanioBloque)];
            }

        }
    }

    public void actualizar(){
        if(PE!=null){
            PE.limpiarcampos();
            PE.actualizarLista();
        }
    }

}
