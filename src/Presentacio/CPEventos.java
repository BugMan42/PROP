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
    private int orden;

    private int ultB;
    private String[] bloqueC, bloqueD;
    private int indiceC, indiceD;
    private int ordenB;

    public CPEventos() {
        CCE = new ControladorCjtEvento();
        bloqueA = new String[tamanioBloque];
        bloqueB = new String[tamanioBloque];
        orden = 0;
        refrescar();

        refrescarB();
        bloqueC = new String[100];
        bloqueD = new String[100];
        ordenB = 0;
    }

    public void ModOrden(int i) {
        if (i < 0 || i > 2) System.out.println("Orden incorrecto tiene que estar entre 0 y 2");
        else if (i != orden) {
            refrescar();
            orden = i;
        }
    }

    public void ModOrdenB(int i) {
        if (i < 0 || i > 2) System.out.println("Orden incorrecto tiene que estar entre 0 y 2");
        else if (i != ordenB) {
            refrescarB();
            ordenB = i;
        }
    }

    public PanelEventos obtPanel() {
        if (PE == null) PE = new PanelEventos(this);
        return PE;
    }

    public void refrescar() {
        ultimo = -1;
        indiceA = -1;
        indiceB = -1;
    }

    public void refrescarB() {
        ultB = -1;
        indiceC = -1;
        indiceD = -1;
    }

    public String[] obtBloqueB(String prefijo) {
        switch (orden) {
            case 0:
                return CCE.obtBloqueBN(prefijo).split("\n");
            case 1:
                return CCE.obtBloqueBF(prefijo).split("\n");
            case 2:
                return CCE.obtBloqueBI(prefijo).split("\n");
            default:
                return new String[1];
        }
    }

    public ControladorCjtEvento obtCCE() {return CCE;}

    public void modCR(CPRelaciones cpr) {CR = cpr;}

    public CPRelaciones obtCPR() {return CR;}

    public void nuevo(){
        if (CCE.size()>0) CCE.EliminarCjtEvento(CR.obtCR());
        actualizar();
    }

    private String[] obtBloque(int bloque) {
        switch (orden) {
            case 0:
                return CCE.obtBloqueP(bloque, tamanioBloque).split("\n");
            case 1:
                return CCE.obtBloquePF(bloque, tamanioBloque).split("\n");
            case 2:
                return CCE.obtBloquePI(bloque, tamanioBloque).split("\n");
            default:
                return new String[1];
        }
    }



    public String obtEvento(int indice) {
        int bloque = indice/tamanioBloque;
        if (bloque == indiceA) {
            ultimo = 0;
            //System.out.println("Este es el bloqueA if que contiene:");
            //for (int i = 0; i < bloqueA.length; ++i) System.out.println(bloqueA[i]);
            return bloqueA[(indice%tamanioBloque)];
        }
        else if (bloque == indiceB) {
            ultimo = 1;
            //System.out.println("Este es el bloqueB if que contiene:");
            //for (int i = 0; i < bloqueB.length; ++i) System.out.println(bloqueB[i]);
            return bloqueB[(indice%tamanioBloque)];
        }
        else {
            if (ultimo == 0) {
                ultimo = 1;
                indiceB = bloque;
                bloqueB = obtBloque(bloque);
                //System.out.println("Este es el bloqueB else que contiene:");
                //for (int i = 0; i < bloqueB.length; ++i) System.out.println(bloqueB[i]);
                return bloqueB[(indice%tamanioBloque)];
            }
            else {
                ultimo = 0;
                indiceA = bloque;
                bloqueA = obtBloque(bloque);
                //System.out.println("Este es el bloqueA else que contiene:");
                //for (int i = 0; i < bloqueA.length; ++i) System.out.println(bloqueA[i]);
                return bloqueA[(indice%tamanioBloque)];
            }
        }
    }

    /*public String obtEventoB(int indice) {
        int bloque = indice/tamanioBloque;
        if (bloque == indiceC) {
            ultB = 0;
            //System.out.println("Este es el bloqueA if que contiene:");
            //for (int i = 0; i < bloqueA.length; ++i) System.out.println(bloqueA[i]);
            return bloqueC[(indice%tamanioBloque)];
        }
        else if (bloque == indiceD) {
            ultimo = 1;
            //System.out.println("Este es el bloqueB if que contiene:");
            //for (int i = 0; i < bloqueB.length; ++i) System.out.println(bloqueB[i]);
            return bloqueD[(indice%tamanioBloque)];
        }
        else {
            if (ultB == 0) {
                ultB = 1;
                indiceD = bloque;
                bloqueD = obtBloqueB(bloque);
                //System.out.println("Este es el bloqueB else que contiene:");
                //for (int i = 0; i < bloqueB.length; ++i) System.out.println(bloqueB[i]);
                return bloqueD[(indice%tamanioBloque)];
            }
            else {
                ultB = 0;
                indiceC = bloque;
                bloqueC = obtBloqueB(bloque);
                //System.out.println("Este es el bloqueA else que contiene:");
                //for (int i = 0; i < bloqueA.length; ++i) System.out.println(bloqueA[i]);
                return bloqueC[(indice%tamanioBloque)];
            }
        }
    }*/



    public void actualizar(){
        if(PE!=null){
            PE.limpiarcampos();
            PE.actualizarLista();
        }
    }

}
