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
        indiceA = -1;
        indiceB = -1;
        bloqueA = new String[tamanioBloque];
        bloqueB = new String[tamanioBloque];
        ultimo = -1;
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
            System.out.println("Este es el bloqueA if que contiene:");
            for (int i = 0; i < bloqueA.length; ++i) System.out.println(bloqueA[i]);
            return bloqueA[(indice%tamanioBloque)];
        }
        else if (bloque == indiceB) {
            ultimo = 1;
            System.out.println("Este es el bloqueB if que contiene:");
            for (int i = 0; i < bloqueB.length; ++i) System.out.println(bloqueB[i]);
            return bloqueB[(indice%tamanioBloque)];
        }
        else {
            if (ultimo == 0) {
                ultimo = 1;
                indiceB = bloque;
                bloqueB = CCE.obtBloqueP(bloque, tamanioBloque).split("\n");
                System.out.println("Este es el bloqueB else que contiene:");
                for (int i = 0; i < bloqueB.length; ++i) System.out.println(bloqueB[i]);
                return bloqueB[(indice%tamanioBloque)];
            }
            else {
                ultimo = 0;
                indiceA = bloque;
                bloqueA = CCE.obtBloqueP(bloque, tamanioBloque).split("\n");
                System.out.println("Este es el bloqueA else que contiene:");
                for (int i = 0; i < bloqueA.length; ++i) System.out.println(bloqueA[i]);
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
