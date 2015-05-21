package Domini;


import java.util.Scanner;

public class DriverRelaciones {
    private static Relaciones  R;

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER DE TST<X>  X:Integer");
        print("0 Relaciones())");
        print("1 agregarRelacion(RelacionSimple r)");
        print("2 eliminarRelacion(RelacionSimple r)");
        print("3 eliminarRelaciones()");
        print("4 obtEventos(Congresista c):ArrayList<Evento> ");
        print("5 obtCongresistas(Evento e):ArrayList<Congresista>");
        print("6 obtRelaciones(Congresista c):ArrayList<RelacionSimple>");
        print("7 obtRelaciones(Evento e):ArrayList<RelacionSimple>");
        print("8 obtCongresistas():ArrayList<Congresista>");
        print("9 obtTodasLasRelaciones()):ArrayList<RelacionSimple>");
        print("10 Salir");
        print("Escribe la opcion");
    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        Scanner userInput = new Scanner(System.in);
        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        if (imprimir) PresentaMenu();
        do {
            //PresentaMenu();
            try {
                ProcesarLinea(LeerLinea(userInput),imprimir);
            } catch (Exception a) {
                print(a.getMessage());
            }
            if (imprimir) PresentaMenu();
        } while(userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str, boolean imprimir) throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"),"Edsger","Dijkstra",72,"Rotterdam","NT","Pirata");
        Congresista C2 = new Congresista(new Dni("00000000B"),"Gordon","Moore",86,"San Francisco","CA","Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"),"Richard","Hamming",82,"Monterey","CA","Pirata");
        Reunion E1 = new Profesional("Golf",new Fecha("12/12/2012"),10);
        Reunion E2 = new Profesional("Golf2",new Fecha("12/12/2012"),10);
        Votacion E3 = new Votacion("votos",new Fecha("12/12/2012"),11);
        Voto V1 = new Positivo();
        Voto V2 = new Negativo();
        RelacionSimpleSinVoto R1 = new RelacionSimpleSinVoto(C1,E1);
        RelacionSimpleSinVoto R2 = new RelacionSimpleSinVoto(C2,E1);
        RelacionSimpleSinVoto R3 = new RelacionSimpleSinVoto(C2,E2);

        if (!imprimir) print("E: "+str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                R = new Relaciones();
                break;
            case 1:
                R.agregarRelacion(R1);
                break;
            case 2:
                R.eliminarRelacion(R1);
                break;
            case 3:
                R.eliminarRelaciones();
                break;
            case 4:
                print(""+R.obtEventos(C1));
                break;
            case 5:
                print(""+R.obtCongresistas(E1));
                break;
            case 6:
                print(""+R.obtRelaciones(C1));
                break;
            case 7:
                print(""+R.obtRelaciones(E1));
                break;
            case 8:
                print(""+R.obtCongresistas());
                break;
            case 9:
                print(""+R.obtTodasLasRelaciones());
                break;
            case 10:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }
}
